package com.tas.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.tas.model.diagram.Diagram;

public class Marshaller {

	public static Diagram readXMLDiagram(File inputFile) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Diagram.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Diagram diagram = (Diagram) jaxbUnmarshaller.unmarshal(inputFile);
		
		return diagram;
	}
}
