package com.tas.worker;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.core.FactException;
import org.drools.core.RuleBase;
import org.drools.core.WorkingMemory;
import org.xml.sax.SAXException;

import com.tas.codes.ProgressCode;
import com.tas.gui.WorkingDialog;
import com.tas.model.diagram.AssetDefinitions;
import com.tas.model.diagram.Diagram;
import com.tas.model.diagram.VulnerabilitiesDefinitions;
import com.tas.model.diagram.VulnerabilityDefinition;
import com.tas.model.risk_pattern.DiagramPiece;
import com.tas.utils.Decomposer;
import com.tas.utils.Marshaller;
import com.tas.utils.MergeDiagram;
import com.tas.utils.RulesBase;
import com.tas.utils.Validator;

public class ThreatWorker extends SwingWorker<Boolean, Object> {
	
	private WorkingDialog dialog;

	private File diagramFile;
	private File assetsFile;
	private File vulnerabilitiesFile;
	private File reportFile;
	private boolean analyseComponents;
	
	private Diagram diagram;
	private AssetDefinitions assetDefinitions;
	private VulnerabilitiesDefinitions vulnerabilityDefinitions;
	
	public ThreatWorker(File diagram, File assets, File vulnerabilities, File report, boolean analyseComponents) {
		this.diagramFile = diagram;
		this.assetsFile = assets;
		this.vulnerabilitiesFile = vulnerabilities;
		this.reportFile = report;
		this.analyseComponents = analyseComponents;
	}

	@Override
	protected Boolean doInBackground() throws Exception {		
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(ProgressCode.STARTED);
		
		
		/* 1 ********	VALIDATING XML DIAGRAM			**********	- DONE	*/
		if (!validateDiagram()) {
			return false;
		}		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(ProgressCode.VALIDATED_DIAGRAM);
		

		/* 1 ********	VALIDATING XML ASSETS			**********	- DONE	*/
		if (!validateAssetDefinitions()) {
			return false;
		}		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(ProgressCode.VALIDATED_ASSETS);
		

		/* 1 ********	VALIDATING XML VULNERABILITIES	**********	- DONE	*/
		if (!validateVulnerabilityDefinitions()) {
			return false;
		}		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(ProgressCode.VALIDATED_VULNERABILITIES);
		

		/* 2 ********	READING XML DIAGRAM TO MEMORY	**********	- DONE	*/
		if (!readDiagram()) {
			return false;
		}
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(ProgressCode.READED_DIAGRAM);
		

		/* 2 ********	READING XML ASSETS TO MEMORY	**********	- DONE	*/
		if (!readAssetDefinitions()) {
			return false;
		}
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(ProgressCode.READED_ASSETS);
		

		/* 3 ********	MERGING DIAGRAM	AND ASSETS		**********	- DONE	*/
		MergeDiagram mergeAssets = new MergeDiagram(diagram, assetDefinitions);
		diagram = mergeAssets.mergeAssetsToDiagram();
		
		if(Thread.currentThread().isInterrupted()) {
			 return false;
		}
		setProgress(ProgressCode.MERGED_DIAGRAM_ASSETS);


		/* 4 ********	DECOMPOSING XML DIAGRAM			**********	- DONE	*/
		Decomposer decomposer = new Decomposer(diagram);
		List<DiagramPiece> pieces = decomposer.decomposeAllPieces();
		
		if (pieces.size() == 0) {
			return false;
		}
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(ProgressCode.DECOMPOSED_DIAGRAM);
		

		/* 5 ********	ANALYZING DIAGRAM COMPONENTS	**********	- DONE	*/		
		if (!createAndFireRules(pieces)) {
			return false;
		}				
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(ProgressCode.RULES_ANALYZED);	
		

		/* 6 ********	READING VULNERABILITIES 		**********	- DONE  */		
		if (!readVulnerabilityDefinitions()) {
			return false;
		}				
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(ProgressCode.READED_VULNERABILITIES);	
		
		
		/* 6 ********	MERGING VULNERAB. AND DIAGRAMS	**********	- DONE	*/	
		MergeDiagram mergeVulnerabilities = new MergeDiagram(pieces, vulnerabilityDefinitions);
		pieces = mergeVulnerabilities.mergeVulnerabilitiesToDiagramPieces();
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(ProgressCode.MERGED_DIAGRAM_VULNERABILITIES);	
		

		/* 7 ********	CREATING XML REPORT				**********	-  */	
		//if () {
		//	return false;
		//}				
		
		if(Thread.currentThread().isInterrupted()) {
			return false;
		}
		setProgress(ProgressCode.GENERATED_REPORT);
		

		/* 7 ********	SAVING XML REPORT				**********	-  */	
		//if () {
		//	return false;
		//}				
		
		if(Thread.currentThread().isInterrupted()) {
			return false;
		}
		setProgress(ProgressCode.SAVED_REPORT);

		
		/* ******************************************************* */	

		
		for (DiagramPiece diagramPiece : pieces) {						
			System.out.println("*******************\nVulnerabilities between " + 
					diagramPiece.getCoreSource().getName() + " and " + diagramPiece.getCoreDestination().getName());
			for (VulnerabilityDefinition vulnerability : diagramPiece.getVulnerabilityValues()) {
				System.out.println("Vulnerability name: " + vulnerability.getVulnerabilityTitle());
			}
		}
		
		return true;
	}
	
	private boolean validateDiagram() {
		String message;
		
		try {
			
			Validator.checkWellFormness(diagramFile);
			
		} catch (ParserConfigurationException e) {
			message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		} catch (SAXException e) {
			message = "XML Diagram is not well formed!\nXML Diagram contains syntax errors. Please choose another diagram or fix current.";
			dialog.setErrorMessage(message);
			return false;
		} catch (IOException e) {
			message = "File could not be read!\nPlease load file again and try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		
		try {
			
			Validator.checkDiagramValidity(diagramFile);
			
		} catch (SAXException e) {
			message = "XML Diagram is not valid!\nXML Diagram contains semantic errors. Please choose another diagram or fix current.";
			dialog.setErrorMessage(message);
			return false;
		} catch (IOException e) {
			message = "File could not be read!\nPlease load file again and try again.";
			dialog.setErrorMessage(message);
			return false;
		} 
		
		return true;
	}
	
	private boolean validateAssetDefinitions() {
		String message;
		
		try {
			
			Validator.checkWellFormness(assetsFile);
			
		} catch (ParserConfigurationException e) {
			message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		} catch (SAXException e) {
			message = "Asset Definitions XML file is not well formed!\nXML File contains syntax errors. Please choose another file or fix current.";
			dialog.setErrorMessage(message);
			return false;
		} catch (IOException e) {
			message = "File could not be read!\nPlease load file again and try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		
		try {
			
			Validator.checkAssetDefinitionsValidity(assetsFile);
			
		} catch (SAXException e) {
			message = "Asset Definitions XML file is not valid!\nXML File contains semantic errors. Please choose another file or fix current.";
			dialog.setErrorMessage(message);
			return false;
		} catch (IOException e) {
			message = "File could not be read!\nPlease load file again and try again.";
			dialog.setErrorMessage(message);
			return false;
		} 
		
		return true;
	}
	
	private boolean validateVulnerabilityDefinitions() {
		String message;
		
		try {
			
			Validator.checkWellFormness(assetsFile);
			
		} catch (ParserConfigurationException e) {
			message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		} catch (SAXException e) {
			message = "Vulnerability Definitions XML file is not well formed!\nXML File has been unauthorizedly modified and contains syntax errors. Please contact the support.";
			dialog.setErrorMessage(message);
			return false;
		} catch (IOException e) {
			message = "File could not be read!\nPlease load file again and try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		
		try {
			
			Validator.checkVulnerabilityDefinitionsValidity(vulnerabilitiesFile);
			
		} catch (SAXException e) {
			message = "Vulnerability Definitions XML file is not valid!\nXML File has been unauthorizedly modified and contains semantic errors. Please contact the support.";
			dialog.setErrorMessage(message);
			return false;
		} catch (IOException e) {
			message = "File could not be read!\nPlease load file again and try again.";
			dialog.setErrorMessage(message);
			return false;
		} 
		
		return true;
	}
	
	private boolean readDiagram() {

		try {
			
			diagram = Marshaller.readXMLDiagram(diagramFile);
			
		} catch (JAXBException e) {
			String message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		return true;
	}
	
	private boolean readAssetDefinitions() {

		try {
			
			assetDefinitions = Marshaller.readXMLAssets(assetsFile);
			
		} catch (JAXBException e) {
			String message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		return true;
	}
	
	private boolean readVulnerabilityDefinitions() {

		try {
			
			vulnerabilityDefinitions = Marshaller.readXMLVulnerabilities(vulnerabilitiesFile);
			
		} catch (JAXBException e) {
			String message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
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
			message = "Defined rules could not be initialized!\n.Check rules definitions or contact supervisors.";
			dialog.setErrorMessage(message);
			return false;
		} catch (IOException e) {
			message = "File could not be read!\nPlease load file again and then try.";
			dialog.setErrorMessage(message);
			return false;
		}		
		
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		for (DiagramPiece diagramPiece : diagramParts) {
			workingMemory.insert(diagramPiece);
		}

		try {
			workingMemory.fireAllRules();	
		} catch (FactException e) {
			message = "Defined rules could not be applied!\n.Check your diagram or contact supervisors.";
			dialog.setErrorMessage(message);
			return false;
		}			
		
		workingMemory.dispose();
		
		return true;
	}
	
	public void setDialog(WorkingDialog dialog) {
		this.dialog = dialog;
	}

}
