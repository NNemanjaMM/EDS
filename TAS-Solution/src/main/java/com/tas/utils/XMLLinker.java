package com.tas.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.tas.model.diagram.AssetDefinitions;
import com.tas.model.diagram.Diagram;
import com.tas.model.diagram.VulnerabilitiesDefinitions;
import com.tas.model.report.ReportClass;

public class XMLLinker {

	public static Diagram readXMLDiagram(File diagramFile) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Diagram.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Diagram diagram = (Diagram) jaxbUnmarshaller.unmarshal(diagramFile);
		
		return diagram;
	}

	public static AssetDefinitions readXMLAssets(File assetsFile) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(AssetDefinitions.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		AssetDefinitions assets = (AssetDefinitions) jaxbUnmarshaller.unmarshal(assetsFile);
		
		return assets;
	}

	public static VulnerabilitiesDefinitions readXMLVulnerabilities(File vulnerabilitiesFile) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(VulnerabilitiesDefinitions.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		VulnerabilitiesDefinitions vulnerabilities = (VulnerabilitiesDefinitions) jaxbUnmarshaller.unmarshal(vulnerabilitiesFile);
		
		return vulnerabilities;
	}
	
	public static void writeXMLReportFile(ReportClass report, File reportFile) throws JAXBException {
		
        JAXBContext jaxbContext = JAXBContext.newInstance(ReportClass.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(report, reportFile);		
		
	}
}
