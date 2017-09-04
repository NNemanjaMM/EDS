package com.eds.Converter_Solution.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBElement;

import com.eds.Converter_Solution.model.input.ThreatModel;
import com.eds.Converter_Solution.model.input.ThreatModel.Borders.AKeyValueOfguidanyType;
import com.eds.Converter_Solution.model.input.ThreatModel.Borders.AKeyValueOfguidanyType.AValue.Properties.AanyType;
import com.eds.Converter_Solution.model.input.ThreatModel.ThreatInstances.AKeyValueOfstringThreatpcP0PhOB;
import com.eds.Converter_Solution.model.output.BlockElement;
import com.eds.Converter_Solution.model.output.BlockShape;
import com.eds.Converter_Solution.model.output.DataStore;
import com.eds.Converter_Solution.model.output.Diagram;
import com.eds.Converter_Solution.model.output.Process;
import com.eds.Converter_Solution.model.output.Shape;
import com.eds.Converter_Solution.model.output.Diagram.Boundaries.InternetBoundary;
import com.eds.Converter_Solution.model.output.Diagram.Boundaries.MachineBoundary;
import com.eds.Converter_Solution.model.output.Diagram.Elements.Browser;
import com.eds.Converter_Solution.model.output.Diagram.Elements.CloudStorage;
import com.eds.Converter_Solution.model.output.Diagram.Elements.ExternalWebApplication;
import com.eds.Converter_Solution.model.output.Diagram.Elements.ExternalWebService;
import com.eds.Converter_Solution.model.output.Diagram.Elements.NoSqlDatabase;
import com.eds.Converter_Solution.model.output.Diagram.Elements.SqlDatabase;
import com.eds.Converter_Solution.model.output.Diagram.Elements.FileSystem;
import com.eds.Converter_Solution.model.output.Diagram.Elements.ThickClient;
import com.eds.Converter_Solution.model.output.Diagram.Elements.WebApplication;
import com.eds.Converter_Solution.model.output.Diagram.Elements.WebServer;
import com.eds.Converter_Solution.model.output.Diagram.Elements.WebService;
import com.eds.Converter_Solution.model.output.Diagram.Flows.Binary;
import com.eds.Converter_Solution.model.output.Diagram.Flows.Http;
import com.eds.Converter_Solution.model.output.Diagram.Flows.Https;
import com.eds.Converter_Solution.model.output.Diagram.Sections.CompanyTrustSection;
import com.eds.Converter_Solution.model.output.Diagram.Sections.SandboxTrustSection;
import com.eds.Converter_Solution.model.output.ExternalEntity;
import com.eds.Converter_Solution.model.output.Flow;

public class Converter {
	
	private ThreatModel inputDiagram;
	
	private List<AKeyValueOfguidanyType> nodesSectionsAnnotations;
	private List<com.eds.Converter_Solution.model.input.ThreatModel.Lines.AKeyValueOfguidanyType> flowsBorders;
	private List<AKeyValueOfstringThreatpcP0PhOB> flowEndpoints;
	

	private List<BlockElement> convertedElements;
	private List<BlockShape> convertedSections;
	private List<Flow> convertedFlows;
	private List<Shape> convertedBoundaries;
	
	
	public Converter(ThreatModel inputDiagram) {
		this.inputDiagram = inputDiagram;

		nodesSectionsAnnotations = inputDiagram.getBorders().getAKeyValueOfguidanyType();
		flowsBorders = inputDiagram.getLines().getAKeyValueOfguidanyType();
		flowEndpoints = inputDiagram.getThreatInstances().getAKeyValueOfstringThreatpcP0PhOB();
		
		convertedElements = new ArrayList<>();
		convertedSections = new ArrayList<>();
		convertedFlows = new ArrayList<>();
		convertedBoundaries = new ArrayList<>();
	}
	
	public boolean convert() {
		
		convertNodesAndSections();
		
		convertFlowsAndBorders();
		
		connectNodesByFlows();
		
		return true;
	}
	
	private void convertNodesAndSections() {
		
		for (AKeyValueOfguidanyType element : nodesSectionsAnnotations) {
			
			//Process: SE.P.TMCore.OSProcess,SE.P.TMCore.Thread,SE.P.TMCore.KernelThread,SE.P.TMCore.WinApp,SE.P.TMCore.NetApp,SE.P.TMCore.BrowserClient,SE.P.TMCore.PlugIn,SE.P.TMCore.Modern,SE.P.TMCore.Win32Service,SE.P.TMCore.VM,SE.P.TMCore.NonMS
			//ExternalEntities: SE.EI.TMCore.AuthProvider,SE.EI.TMCore.User,SE.EI.TMCore.Megasevrice,SE.EI.TMCore.CRT,SE.EI.TMCore.NFX,SE.EI.TMCore.WinRT
			//DataStore: SE.DS.TMCore.Registry,SE.DS.TMCore.ConfigFile,SE.DS.TMCore.Cache,SE.DS.TMCore.HTML5LS,SE.DS.TMCore.Cookie,SE.DS.TMCore.Device
			//TextField: GE.A
			//Sections: SE.TB.B.TMCore.IEB,SE.TB.B.TMCore.NonIEB,SE.TB.B.TMCore.Sandbox
			
			String type = element.getAValue().getTypeId().getValue();
			
			String id = element.getAValue().getGuid().getValue();
			
			//PROCESI
			if (type.equals("SE.P.TMCore.WebApp")) {
				WebApplication instance = new WebApplication();
				instance.setId(id);
				fillProcesAttributes(instance, element);
				convertedElements.add(instance);
			} else if (type.equals("SE.P.TMCore.WebServer")) {
				WebServer instance = new WebServer();
				instance.setId(id);
				fillProcesAttributes(instance, element);
				convertedElements.add(instance);
			} else if (type.equals("SE.P.TMCore.WebSvc")) {
				WebService instance = new WebService();
				instance.setId(id);
				fillProcesAttributes(instance, element);
				convertedElements.add(instance);
			} else if (type.equals("SE.P.TMCore.ThickClient")) {
				ThickClient instance = new ThickClient();
				instance.setId(id);
				fillProcesAttributes(instance, element);
				convertedElements.add(instance);
				
			//SPOLJASNJI
			} else if (type.equals("SE.EI.TMCore.Browser")) {
				Browser instance = new Browser();
				instance.setId(id);
				fillExternalEntityAttributes(instance, element);
				convertedElements.add(instance);				
			} else if (type.equals("SE.EI.TMCore.WebApp")) {
				ExternalWebApplication instance = new ExternalWebApplication();
				instance.setId(id);
				fillExternalEntityAttributes(instance, element);
				convertedElements.add(instance);				
			} else if (type.equals("SE.EI.TMCore.WebSvc")) {
				ExternalWebService instance = new ExternalWebService();
				instance.setId(id);
				fillExternalEntityAttributes(instance, element);
				convertedElements.add(instance);				
				
			//SKLADISTA
			} else if (type.equals("SE.DS.TMCore.CloudStorage")) {
				CloudStorage instance = new CloudStorage();
				instance.setId(id);
				fillDataStoreAttributes(instance, element);
				convertedElements.add(instance);		
			} else if (type.equals("SE.DS.TMCore.SQL")) {
				SqlDatabase instance = new SqlDatabase();
				instance.setId(id);
				fillDataStoreAttributes(instance, element);
				convertedElements.add(instance);		
			} else if (type.equals("SE.DS.TMCore.NoSQL")) {
				NoSqlDatabase instance = new NoSqlDatabase();
				instance.setId(id);
				fillDataStoreAttributes(instance, element);
				convertedElements.add(instance);		
			} else if (type.equals("SE.DS.TMCore.FS")) {
				FileSystem instance = new FileSystem();
				instance.setId(id);
				fillDataStoreAttributes(instance, element);
				convertedElements.add(instance);		
				
			//SEKCIJE
			} else if (type.equals("SE.TB.B.TMCore.CorpNet")) {
				CompanyTrustSection instance = new CompanyTrustSection();
				instance.setId(id);
				fillSectionAttributes(instance, element);
				convertedSections.add(instance);		
						
			} else if (type.equals("SE.TB.B.TMCore.Sandbox")) {
				SandboxTrustSection instance = new SandboxTrustSection();
				instance.setId(id);
				fillSectionAttributes(instance, element);
				convertedSections.add(instance);					
			}
		}		
	}
	
	private void convertFlowsAndBorders() {
				
		for (com.eds.Converter_Solution.model.input.ThreatModel.Lines.AKeyValueOfguidanyType element : flowsBorders) {
			
			//Boundaries: SE.TB.L.TMCore.Kernel,SE.TB.L.TMCore.AppContainer
			//Flows: SE.DF.TMCore.IPsec,SE.DF.TMCore.NamedPipe,SE.DF.TMCore.SMB,SE.DF.TMCore.RPC,SE.DF.TMCore.ALPC,SE.DF.TMCore.UDP,SE.DF.TMCore.IOCTL

			String type = element.getAValue().getTypeId().getValue();
			
			String id = element.getAValue().getGuid().getValue();

			//TOKOVI
			if (type.equals("SE.DF.TMCore.HTTP")) {
				Http instance = new Http();
				instance.setId(id);
				fillFlowAttributes(instance, element);
				convertedFlows.add(instance);
			} else if (type.equals("SE.DF.TMCore.HTTPS")) {
				Https instance = new Https();
				instance.setId(id);
				fillFlowAttributes(instance, element);
				convertedFlows.add(instance);
			} else if (type.equals("SE.DF.TMCore.Binary")) {
				Binary instance = new Binary();
				instance.setId(id);
				fillFlowAttributes(instance, element);
				convertedFlows.add(instance);
				
			//GRANICE
			} else if (type.equals("SE.TB.L.TMCore.Machine")) {
				MachineBoundary instance = new MachineBoundary();
				instance.setId(id);
				fillBoundaryAttributes(instance, element);
				convertedBoundaries.add(instance);
			} else if (type.equals("SE.TB.L.TMCore.Internet")) {
				InternetBoundary instance = new InternetBoundary();
				instance.setId(id);
				fillBoundaryAttributes(instance, element);
				convertedBoundaries.add(instance);
			}
		}		
	}
		
	private void connectNodesByFlows() {
		
		for (AKeyValueOfstringThreatpcP0PhOB endpoint : flowEndpoints) {
			
			String flowId = endpoint.getAValue().getBFlowGuid();
			String sourceId = endpoint.getAValue().getBSourceGuid();
			String targetId = endpoint.getAValue().getBTargetGuid();
			
			Flow connectingFlow = convertedFlows.stream().filter(f -> ((Flow)f).getId().equals(flowId)).collect(Collectors.toList()).get(0);		
			
			if (connectingFlow.getDestination() == null || connectingFlow.getDestination().equals("")) {				
				connectingFlow.setSource(sourceId);
				connectingFlow.setDestination(targetId);
			}
			/*
			List<BlockElement> source = convertedElements.stream().filter(f -> ((BlockElement)f).getId().equals(sourceId)).collect(Collectors.toList());
			List<BlockElement> dest = convertedElements.stream().filter(f -> ((BlockElement)f).getId().equals(targetId)).collect(Collectors.toList());
			
			if (name.size() > 0 && source.size() > 0 && dest.size() > 0) 
				System.out.println("flow " + name.get(0).getName() + " connects " +source.get(0).getName() +" with " +dest.get(0).getName());
			else 
				System.out.println("flow " + flowId + " connects " +sourceId +" with " +targetId);
			*/
			//System.out.println("flow " + name.size()  + " source " +source.size() +" targ " +dest.size());
			
			
		}
		/*
		System.out.println();
		for (Flow shape : convertedFlows) {
			System.out.println(shape.getId());
		}
		
		System.out.println();
		for (BlockElement shape : convertedElements) {
			System.out.println(shape.getId());
		}
		*/
	}
	

	private void fillProcesAttributes(Process instance, AKeyValueOfguidanyType source) {
		//Atributi: Predefined Static Attributes,Code Type,Configurable Attributes,As Generic Process,Running As,Accepts Input From,Implements or Uses a Communication Protocol
		
		for (AanyType property : source.getAValue().getProperties().getAanyType()) {
			
			String propertyName = property.getBDisplayName();
			
			if (propertyName.equals("Name")) {				 
				instance.setName(property.getBValue().getContent().get(0).toString());
			} else if (propertyName.equals("Out Of Scope")) {
				instance.setOutOfScope(property.getBValue().getContent().get(0).equals("true"));
			} else if (propertyName.equals("Reason For Out Of Scope")) {
				if (property.getBValue().getContent().size() > 0) {
					instance.setOutOfScopeReason(property.getBValue().getContent().get(0).toString());
				} else {
					instance.setOutOfScopeReason("");
				}
			} else if (propertyName.equals("Sanitizes Input")) {
				instance.setSanitizeInput(property.getBSelectedIndex() == 1);
			} else if (propertyName.equals("Sanitizes Output")) {
				instance.setSanitizeOutput(property.getBSelectedIndex() == 1);				
			} else if (propertyName.equals("Isolation Level")) {
				switch (property.getBSelectedIndex()) {
					case 0:
						instance.setRunLevel("HIGH_PRIVILEGE");
						break;
					case 3:
						instance.setRunLevel("SANDBOX");
						break;
					case 4:
						instance.setRunLevel("SANDBOX");
						break;
					default:
						instance.setRunLevel("LOW_PRIVILEGE");
				}
			} else if (propertyName.equals("Implements or Uses an Authentication Mechanism")) {
				instance.setRequiresAuthentication(property.getBSelectedIndex() == 1);				
			} else if (propertyName.equals("Implements or Uses an Authorization Mechanism")) {
				instance.setRequiresAuthorization(property.getBSelectedIndex() == 1);							
			}
			
		}
	}
	
	private void fillExternalEntityAttributes(ExternalEntity instance, AKeyValueOfguidanyType source) {		
		//Atributi: Predefined Static Attributes,Type,Configurable Attributes,As Generic External Interactor,Authenticates Itself,Microsoft
		
		for (AanyType property : source.getAValue().getProperties().getAanyType()) {
			
			String propertyName = property.getBDisplayName();

			if (propertyName.equals("Name")) {				 
				instance.setName(property.getBValue().getContent().get(0).toString());
			} else if (propertyName.equals("Out Of Scope")) {
				instance.setOutOfScope(property.getBValue().getContent().get(0).equals("true"));
			} else if (propertyName.equals("Reason For Out Of Scope")) {
				if (property.getBValue().getContent().size() > 0) {
					instance.setOutOfScopeReason(property.getBValue().getContent().get(0).toString());
				} else {
					instance.setOutOfScopeReason("");
				}	
			}
			
		}
		
		instance.setRunLevel("HIGH_PRIVILEGE");
		instance.setSanitizeInput(false);
		instance.setSanitizeOutput(false);	
	}

	private void fillDataStoreAttributes(DataStore instance, AKeyValueOfguidanyType source) {	
		//Atributi: Predefined Static Attributes,Store Type,Configurable Attributes,As Generic Data Store,Stores Log Data,Write Access,Removable Storage,Shared
	
		for (AanyType property : source.getAValue().getProperties().getAanyType()) {	
			String propertyName = property.getBDisplayName();

			if (propertyName.equals("Name")) {				 
				instance.setName(property.getBValue().getContent().get(0).toString());
			} else if (propertyName.equals("Out Of Scope")) {
				instance.setOutOfScope(property.getBValue().getContent().get(0).equals("true"));
			} else if (propertyName.equals("Reason For Out Of Scope")) {
				if (property.getBValue().getContent().size() > 0) {
					instance.setOutOfScopeReason(property.getBValue().getContent().get(0).toString());
				} else {
					instance.setOutOfScopeReason("");
				}				

			} else if (propertyName.equals("Stores Credentials")) {
				instance.setStoreCredentials(property.getBSelectedIndex() == 1);
			} else if (propertyName.equals("Encrypted")) {
				instance.setDataIsEncrypted(property.getBSelectedIndex() == 1);
			} else if (propertyName.equals("Signed")) {
				instance.setDataIsSigned(property.getBSelectedIndex() == 1);
			} else if (propertyName.equals("Backup")) {
				instance.setHasBackup(property.getBSelectedIndex() == 1);
			}
		
		}
		instance.setRunLevel("HIGH_PRIVILEGE");
	}

	private void fillSectionAttributes(BlockShape instance, AKeyValueOfguidanyType source) {

		for (AanyType property : source.getAValue().getProperties().getAanyType()) {	
			String propertyName = property.getBDisplayName();

			if (propertyName.equals("Name")) {				 
				instance.setName(property.getBValue().getContent().get(0).toString());
			}
			
		}		
	}
	

	
	private void fillFlowAttributes(Flow instance, com.eds.Converter_Solution.model.input.ThreatModel.Lines.AKeyValueOfguidanyType source) {
		//Atributi: Predefined Static Attributes,Source Authenticated,Destination Authenticated,Provides Confidentiality,Provides Integrity,Configurable Attributes,As Generic Data Flow,Physical Network,SOAP Payload,REST Payload,RSS Payload,JSON Payload,Forgery Protection 
		
		for (com.eds.Converter_Solution.model.input.ThreatModel.Lines.AKeyValueOfguidanyType.AValue.Properties.AanyType property : source.getAValue().getProperties().getAanyType()) {	
			String propertyName = property.getBDisplayName();

			if (propertyName.equals("Name")) {				 
				instance.setName(property.getBValue().getContent().get(0).toString());
			} else if (propertyName.equals("Out Of Scope")) {
				instance.setOutOfScope(property.getBValue().getContent().get(0).equals("true"));
			} else if (propertyName.equals("Reason For Out Of Scope")) {
				if (property.getBValue().getContent().size() > 0) {
					instance.setOutOfScopeReason(property.getBValue().getContent().get(0).toString());
				} else {
					instance.setOutOfScopeReason("");
				}
			} else if (propertyName.equals("Transmits XML")) {
				instance.setContainsXML(property.getBSelectedIndex() == 1);
			} else if (propertyName.equals("Contains Cookies")) {
				instance.setContainsCookies(property.getBSelectedIndex() == 0);				
			}
				
		}
	}
	
	private void fillBoundaryAttributes(Shape instance, com.eds.Converter_Solution.model.input.ThreatModel.Lines.AKeyValueOfguidanyType source) {

		for (com.eds.Converter_Solution.model.input.ThreatModel.Lines.AKeyValueOfguidanyType.AValue.Properties.AanyType property : source.getAValue().getProperties().getAanyType()) {	
			String propertyName = property.getBDisplayName();

			if (propertyName.equals("Name")) {				 
				instance.setName(property.getBValue().getContent().get(0).toString());
			}
			
		}		
	}

	
	
	
	
	public List<BlockElement> getConvertedElements() {
		return convertedElements;
	}
	

	public List<BlockShape> getConvertedSections() {
		return convertedSections;
	}
	

	public List<Flow> getConvertedFlows() {
		return convertedFlows;
	}
	

	public List<Shape> getConvertedBoundaries() {
		return convertedBoundaries;
	}
	
}
