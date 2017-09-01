package com.eds.Converter_Solution.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.eds.Converter_Solution.Dididid;
import com.eds.EDS_Library.diagram.Diagram;

public class XMLBridge {

	@SuppressWarnings("unused")
 	public static void checkWellFormness(File inputDiagramFile) throws ParserConfigurationException, SAXException, IOException {
		
 		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 		
		DocumentBuilder builder = factory.newDocumentBuilder();	
		InputStream stream = new FileInputStream(inputDiagramFile);
		
		Document document = builder.parse(stream);
 	}
 
 	public static void checkExploitDefinitionsValidity(File inputDiagramFile, File inputDiagramSchema) throws SAXException, IOException {	
 			
		Source stream = new StreamSource(inputDiagramFile);
		
	    SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    Schema schema = factory.newSchema(inputDiagramSchema);
	    javax.xml.validation.Validator validator = schema.newValidator();	
	    
        validator.validate(stream);
	}

	public static Dididid readInputDiagram(File inputDiagramFile) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Diagram.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Dididid inputDiagram = (Dididid) jaxbUnmarshaller.unmarshal(inputDiagramFile);
		
		return inputDiagram;
	}
	
	public static void writeXMLDiagram(Diagram outputDiagram, File outputFile) throws JAXBException {
		
        JAXBContext jaxbContext = JAXBContext.newInstance(Diagram.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(outputDiagram, outputFile);		
		
	}
}