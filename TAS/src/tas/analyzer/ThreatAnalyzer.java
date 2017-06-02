package tas.analyzer;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import tas.gui.WorkingDialog;
import tas.model.diagram.Diagram;
import xml_components.Marshaller;
import xml_components.SchemaValidator;

public class ThreatAnalyzer extends SwingWorker<Boolean, Object> {
	
	private WorkingDialog dialog;

	private File diagramFile;
	private File reportFile;
	private Diagram diagram;
	
	public ThreatAnalyzer(File diagram, File report) {
		this.diagramFile = diagram;
		this.reportFile = report;
	}

	@Override
	protected Boolean doInBackground() throws Exception {		
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(1);
		
		/* **********	VALIDATING XML FILE			********** */
		if (!validateDiagram()) {
			return false;
		}
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(10);

		/* **********	READING XML FILE TO MEMORY	********** */
		if (!readDiagram()) {
			return false;
		}
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(20);

		/* **********	DECOMPOSING XML DIAGRAM		********** */
		
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(45);
		

		/* **********	ANALYZING DECOMPOSED DATA	********** */
		
		
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
		
		
		return true;
	}
	
	private boolean validateDiagram() {
		String message;
		
		try {
			
			SchemaValidator.checkWellFormness(diagramFile);
			
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
			
			SchemaValidator.checkValidity(diagramFile);
			
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
	
	public void setDialog(WorkingDialog dialog) {
		this.dialog = dialog;
	}

}
