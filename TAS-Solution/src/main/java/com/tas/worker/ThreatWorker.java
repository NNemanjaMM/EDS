package com.tas.worker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.kie.api.runtime.StatelessKieSession;
import org.xml.sax.SAXException;

import com.tas.codes.ProgressCode;
import com.tas.gui.WorkingDialog;
import com.tas.model.diagram.AssetDefinitions;
import com.tas.model.diagram.Diagram;
import com.tas.model.diagram.Element;
import com.tas.model.diagram.VulnerabilitiesDefinitions;
import com.tas.model.diagram.VulnerabilityDefinition;
import com.tas.model.report.ReportClass;
import com.tas.model.report.ReportPattern;
import com.tas.model.risk_pattern.DiagramPattern;
import com.tas.utils.Decomposer;
import com.tas.utils.KieRulesBase;
import com.tas.utils.XMLLinker;
import com.tas.utils.MergeDiagram;
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
	
	private List<DiagramPattern> patterns;
	private ReportClass report;
	
	
	
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
		patterns = decomposer.decomposeAllPatterns();
		
		if (patterns.size() == 0) {
			return false;
		}		
		
		
		
		//printDiagramPatterns(patterns);
		//if (patterns.size() > 0) return true;
				
		
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(ProgressCode.DECOMPOSED_DIAGRAM);
		

		/* 5 ********	ANALYZING DIAGRAM COMPONENTS	**********	- DONE	*/		
		createAndFireRules();				
		
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
		MergeDiagram mergeVulnerabilities = new MergeDiagram(patterns, vulnerabilityDefinitions);
		patterns = mergeVulnerabilities.mergeVulnerabilitiesToDiagramPieces();
		
		if(Thread.currentThread().isInterrupted()) {
			 return false; 
		}
		setProgress(ProgressCode.MERGED_DIAGRAM_VULNERABILITIES);	
		

		/* 7 ********	CREATING REPORT	PATTERN			**********	-  */	
		report = createReportPatternsFromDiagramPatterns();				
		
		if(Thread.currentThread().isInterrupted()) {
			return false;
		}
		setProgress(ProgressCode.GENERATIED_REPORT_PATTERNS);
		

		/* 7 ********	CREATING XML REPORT				**********	-  */	
		if (!createXMLReportFile()) {
			return false;
		}				
		
		if(Thread.currentThread().isInterrupted()) {
			return false;
		}
		setProgress(ProgressCode.GENERATED_REPORT);

		
		/* ******************************************************* */	

		printVulnerabilitiesForDiagramPatterns(patterns);		
		
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
			
			diagram = XMLLinker.readXMLDiagram(diagramFile);
			
		} catch (JAXBException e) {
			String message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		return true;
	}
	
	private boolean readAssetDefinitions() {

		try {
			
			assetDefinitions = XMLLinker.readXMLAssets(assetsFile);
			
		} catch (JAXBException e) {
			String message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		return true;
	}
	
	private boolean readVulnerabilityDefinitions() {

		try {
			
			vulnerabilityDefinitions = XMLLinker.readXMLVulnerabilities(vulnerabilitiesFile);
			
		} catch (JAXBException e) {
			String message = "Parser could not be initialized!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		return true;
	}
	
	private void createAndFireRules() {
		
		StatelessKieSession session = KieRulesBase.createStatelessSession();
		
		 for (DiagramPattern diagramPattern : patterns) {
			 session.execute(diagramPattern);				
		 }
	}
	
	private ReportClass createReportPatternsFromDiagramPatterns() {
		List<ReportPattern> reports = new ArrayList<>();
		
		for (DiagramPattern diagramPattern : patterns) {
			reports.add(new ReportPattern(diagramPattern));
		}
		
		ReportClass report = new ReportClass(reports);
		
		return report;
	}
	
	private boolean createXMLReportFile() {
		
		try {
			XMLLinker.writeXMLReportFile(report, reportFile);
		} catch (JAXBException e) {
			String message = "Report File could not be written!\nPlease try again.";
			dialog.setErrorMessage(message);
			return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("unused")
	private void printDiagramPatterns(List<DiagramPattern> diagramPatterns) {
		for (DiagramPattern pattern : diagramPatterns) {
			System.out.println("\n********* Pattern for " + pattern.getElement().getName() + "\n- Trace for " + pattern.getTraceStart().getName() + ":");
			for (Element element : pattern.getTrace()) {
				System.out.println("\t " + element.getName());
			}
		}	
	}

	@SuppressWarnings("unused")
	private void printVulnerabilitiesForDiagramPatterns(List<DiagramPattern> diagramPatterns) {
		for (DiagramPattern pattern : diagramPatterns) {			
			System.out.println("*******************\nVulnerabilities on element " + pattern.getElement().getName() + "\n- Trace for "  + pattern.getTraceStart().getName() + ":");
			for (VulnerabilityDefinition vulnerability : pattern.getVulnerabilityValues()) {
				if (vulnerability != null) {
					System.out.println("\t\t- " + vulnerability.getVulnerabilityTitle());
				}
			}
		}
	}

	public void setDialog(WorkingDialog dialog) {
		this.dialog = dialog;
	}

}
