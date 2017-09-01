package com.eds.Converter_Solution;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.eds.Converter_Solution.utils.Converter;
import com.eds.Converter_Solution.utils.XMLBridge;
import com.eds.EDS_Library.diagram.Diagram;

public class App {
	
    public static void main(String[] args) {
    	
        if (args.length < 2){
        	System.out.println("ERROR: You have to pass data flow diagram location!");
        	System.out.println("ERROR: Parameter 1 - Location of an input diagram");
        	System.out.println("ERROR: Parameter 2 - Location where an output diagram will be stored");
            return;
        }
        
        String inputDiagramLocation = args[0];
        String outputDiagramLocation = args[1];
        String inputDiagramSchemaLocation = "resources\\TM7Schema.xsd";
        
        File inputDiagramFile = new File(inputDiagramLocation);
        File outputDiagramFile = new File(outputDiagramLocation);
        File inputDiagramSchemaFile = new File(inputDiagramSchemaLocation);
        
                
        if (!checkInputDiagram(inputDiagramFile, inputDiagramSchemaFile)) {
        	return;
        }
        /*
        Dididid inputDiagram = readInputDiagram(inputDiagramFile);
        if (inputDiagram == null) {
        	return;
        }
        
        Converter converter = new Converter(inputDiagram);
        Diagram outputDiagram = converter.convert();
        
        if (!writeOutputDiagram(outputDiagram, outputDiagramFile)) {
        	return;
        }
    	*/
    }
    
    private static boolean checkInputDiagram(File inputDiagramFile, File inputDiagramSchema) {
    	
        try {
        	
			XMLBridge.checkWellFormness(inputDiagramFile);
			
		} catch (ParserConfigurationException e) {
        	System.out.println("ERROR: Could not initialize XML parser!");
			return false;
		} catch (SAXException e) {
        	System.out.println("ERROR: Input diagram is not well formed!");
			return false;
		} catch (IOException e) {
        	System.out.println("ERROR: Could not find input file!");
			return false;
		}
        
			
        try {
        	
			XMLBridge.checkExploitDefinitionsValidity(inputDiagramFile, inputDiagramSchema);
			
		} catch (SAXException e) {
        	System.out.println("ERROR: Input diagram contains semantic errors!");
			return false;
		} catch (IOException e) {
        	System.out.println("ERROR: Could not find input file!");
			return false;
		}		
        
    	return true;
    }
    
    private static Dididid readInputDiagram(File inputDiagramFile) {    	
    	Dididid inputDiagram;
    	
    	try {
			inputDiagram = XMLBridge.readInputDiagram(inputDiagramFile);
		} catch (JAXBException e) {
        	System.out.println("ERROR: Input diagram could not be readed!");
			return null;
		}
    	
    	return inputDiagram;    	
    }
    
    private static boolean writeOutputDiagram(Diagram outputDiagram, File outputFile) {
    	
    	try {
			XMLBridge.writeXMLDiagram(outputDiagram, outputFile);
		} catch (JAXBException e) {
        	System.out.println("ERROR: Output diagram could not be saved!");
			return false;
		}
    	
    	return true;
    }
}
