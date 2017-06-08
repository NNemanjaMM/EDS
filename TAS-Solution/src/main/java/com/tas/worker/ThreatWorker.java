package com.tas.worker;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.core.FactException;
import org.drools.core.RuleBase;
import org.drools.core.WorkingMemory;
import org.xml.sax.SAXException;

import com.tas.gui.WorkingDialog;
import com.tas.model.diagram.Diagram;
import com.tas.model.risk_pattern.DiagramPiece;
import com.tas.utils.Decomposer;
import com.tas.utils.Marshaller;
import com.tas.utils.RulesBase;
import com.tas.utils.Validator;

public class ThreatWorker extends SwingWorker<Boolean, Object> {
	
	private WorkingDialog dialog;

	private File diagramFile;
	private File reportFile;
	private boolean analyseComponents;
	private Diagram diagram;
	
	public ThreatWorker(File diagram, File report, boolean analyseComponents) {
		this.diagramFile = diagram;
		this.reportFile = report;
		this.analyseComponents = analyseComponents;
	}

	@Override
	protected Boolean doInBackground() throws Exception {		
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(1);
		
		/* **********	VALIDATING XML FILE			**********	- DONE */
		if (!validateDiagram()) {
			//return false;
		}
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(10);

		/* **********	READING XML FILE TO MEMORY	**********	- DONE */
		if (!readDiagram()) {
			return false;
		}
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(20);

		/* **********	DECOMPOSING XML DIAGRAM		**********	- DONE */
		Decomposer decomposer = new Decomposer(diagram);
		List<DiagramPiece> pieces = decomposer.decomposeAllPieces();
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(45);
		

		/* **********	ANALYZING DECOMPOSED DATA	********** */
		
		if (!createAndFireRules(pieces)) {
			return false;
		}		
		
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(70);
		

		/* **********	ANALYZING COMPONENTS 		********** */
		
		if (analyseComponents) {
			if (!analyseComponentsThreats()) {
				return false;
			}
		}		
		
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(70);
		

		/* **********	CREATING XML REPORT			********** */
		
		
		if(Thread.currentThread().isInterrupted()) {
			return false;
		}
		setProgress(90);
		

		/* **********	SAVING XML REPORT			********** */
		
		
		if(Thread.currentThread().isInterrupted()) {
			return false;
		}
		
		for (DiagramPiece diagramPiece : pieces) {						
			System.out.println("*******************\nThreats between " + 
					diagramPiece.getCoreSource().getName() + " and " + diagramPiece.getCoreDestination().getName());
			for (Integer threat : diagramPiece.getThreats()) {
				System.out.println("Threat id: " + threat);
			}
		}
		
		return true;
	}
	
	private boolean validateDiagram() {
		String message;
		
		try {
			
			Validator.checkWellFormness(diagramFile);
			
		} catch (ParserConfigurationException e) {
			message = "Parser can not be initialized!\nPlease try again.";
			JOptionPane.showMessageDialog(dialog, message, "Parser Initialization Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (SAXException e) {
			message = "XML Diagram is not well formed!\nXML Diagram contains syntax errors. Please choose another diagram or fix current.";
			JOptionPane.showMessageDialog(dialog, message, "Diagram Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (IOException e) {
			message = "File can not be read!\nPlease load file again and then try.";
			JOptionPane.showMessageDialog(dialog, message, "File Read Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		
		try {
			
			Validator.checkValidity(diagramFile);
			
		} catch (SAXException e) {
			message = "XML Diagram is not valid!\nXML Diagram contains semantic errors. Please choose another diagram or fix current.";
			JOptionPane.showMessageDialog(dialog, message, "Diagram Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (IOException e) {
			message = "File can not be read!\nPlease load file again and then try.";
			JOptionPane.showMessageDialog(dialog, message, "File Read Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} 
		
		return true;
	}
	
	private boolean readDiagram() {

		try {
			
			diagram = Marshaller.readXMLDiagram(diagramFile);
			
		} catch (JAXBException e) {
			String message = "Parser can not be initialized!\nPlease try again.";
			JOptionPane.showMessageDialog(dialog, message, "Parser Initialization Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private boolean createAndFireRules(List<DiagramPiece> diagramParts) {		
		String message;		
		
		RuleBase ruleBase = null;
		try {
			ruleBase = RulesBase.createBase();
		} catch (DroolsParserException e) {
			message = "Defined rules can not be initialized!\n.Check rules definitions or contact supervisors.";
			JOptionPane.showMessageDialog(dialog, message, "Rules Initialization Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (IOException e) {
			message = "File can not be read!\nPlease load file again and then try.";
			JOptionPane.showMessageDialog(dialog, message, "File Read Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}		
		
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		for (DiagramPiece diagramPiece : diagramParts) {
			workingMemory.insert(diagramPiece);
		}

		try {
			workingMemory.fireAllRules();	
		} catch (FactException e) {
			message = "Defined rules can not be applied!\n.Check your diagram or contact supervisors.";
			JOptionPane.showMessageDialog(dialog, message, "Rules Applying Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}			
		
		return true;
	}
	
	private boolean analyseComponentsThreats() {
		
		
		return true;
	}
	
	public void setDialog(WorkingDialog dialog) {
		this.dialog = dialog;
	}

}
