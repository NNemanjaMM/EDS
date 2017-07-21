package com.tas.utils;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.tas.model.diagram.AssetDefinition;
import com.tas.model.diagram.AssetDefinitions;
import com.tas.model.diagram.Assets.Asset;
import com.tas.model.diagram.BlockElement;
import com.tas.model.diagram.Diagram;
import com.tas.model.diagram.Flow;
import com.tas.model.diagram.ImportAssets;
import com.tas.model.diagram.ImportVulnerabilities;
import com.tas.model.diagram.Vulnerabilities.Vulnerability;
import com.tas.model.diagram.VulnerabilitiesDefinitions;
import com.tas.model.diagram.VulnerabilityDefinition;
import com.tas.model.risk_pattern.DiagramPattern;

public class MergeDiagram {
	
	private Diagram diagram;
	private AssetDefinitions assetDefinitions;
	private List<DiagramPattern> diagramPatterns;
	private VulnerabilitiesDefinitions vulnerabilityDefinitions;

	public MergeDiagram(Diagram diagram, VulnerabilitiesDefinitions vulnerabilityDefinitions) {
		this.diagram = diagram;
		this.vulnerabilityDefinitions = vulnerabilityDefinitions;
		this.assetDefinitions = null;
	}

	public MergeDiagram(Diagram diagram, AssetDefinitions assetDefinitions) {
		this.diagram = diagram;
		this.assetDefinitions = assetDefinitions;
		this.vulnerabilityDefinitions = null;
	}

	public MergeDiagram(List<DiagramPattern> diagramPatterns, VulnerabilitiesDefinitions vulnerabilityDefinitions) {
		this.diagramPatterns = diagramPatterns;
		this.vulnerabilityDefinitions = vulnerabilityDefinitions;
		this.assetDefinitions = null;
		this.diagram = null;
	}
	
	public Diagram mergeAssetsToDiagram() {
		HashMap<String, AssetDefinition> assetsMap = makeAssetsMap();
		
		List<JAXBElement<? extends BlockElement>> elements = diagram.getElements().getOrWebApplicationOrWebServiceOrWebServer();
		for (JAXBElement<? extends BlockElement> element : elements) {
			element.getValue().setImportAssets(new ImportAssets());
			if (element.getValue().getAssets() != null) {
				for (Asset asset : element.getValue().getAssets().getAsset()) {
					AssetDefinition add = assetsMap.get(asset.getAssetId());
					element.getValue().getImportAssets().getImportAsset().add(add);
				}
			}
		}
		
		List<JAXBElement<? extends Flow>> flows = diagram.getFlows().getOrBinaryOrHttpOrHttps();	
		for (JAXBElement<? extends Flow> flow : flows) {
			flow.getValue().setImportAssets(new ImportAssets());
			if (flow.getValue().getAssets() != null) {
				for (Asset asset : flow.getValue().getAssets().getAsset()) {
					AssetDefinition add = assetsMap.get(asset.getAssetId());
					flow.getValue().getImportAssets().getImportAsset().add(add);
				}
			}
		}
		
		return this.diagram;
	}
	
	public Diagram mergeVulnerabilitiesToDiagram() {
		HashMap<String, VulnerabilityDefinition> vulnerabilitiesMap = makeVulnerabilitiesMap();
		
		List<JAXBElement<? extends BlockElement>> elements = diagram.getElements().getOrWebApplicationOrWebServiceOrWebServer();
		for (JAXBElement<? extends BlockElement> element : elements) {
			element.getValue().setImportVulnerabilities(new ImportVulnerabilities());
			if (element.getValue().getVulnerabilities() != null) {
				for (Vulnerability vulnerability : element.getValue().getVulnerabilities().getVulnerability()) {
					VulnerabilityDefinition add = vulnerabilitiesMap.get(vulnerability.getVulnerabilityId());
					element.getValue().getImportVulnerabilities().getImportVulnerability().add(add);
				}
			}
		}
		
		List<JAXBElement<? extends Flow>> flows = diagram.getFlows().getOrBinaryOrHttpOrHttps();		
		for (JAXBElement<? extends Flow> flow : flows) {
			flow.getValue().setImportVulnerabilities(new ImportVulnerabilities());
			if (flow.getValue().getVulnerabilities() != null) {
				for (Vulnerability vulnerability : flow.getValue().getVulnerabilities().getVulnerability()) {
					VulnerabilityDefinition add = vulnerabilitiesMap.get(vulnerability.getVulnerabilityId());
					flow.getValue().getImportVulnerabilities().getImportVulnerability().add(add);
				}
			}
		}

		return this.diagram;
	}
	
	public List<DiagramPattern> mergeVulnerabilitiesToDiagramPieces() {
		HashMap<String, VulnerabilityDefinition> vulnerabilitiesMap = makeVulnerabilitiesMap();
		
		for (DiagramPattern pattern : diagramPatterns) {
			for (String vulnerability : pattern.getVulnerabilities()) {
				pattern.addVulnerabilityValue(vulnerabilitiesMap.get(vulnerability));
			}
		}
		/*
		for (DiagramPattern pattern : diagramPatterns) {
			for (ElementTrace trace : pattern.getTraces()) {
				for (String vulnerability : trace.getVulnerabilities()) {
					trace.addVulnerabilityValue(vulnerabilitiesMap.get(vulnerability));
				}
			}
		}
		*/
		
		return diagramPatterns;
	}
	
	private HashMap<String, AssetDefinition> makeAssetsMap() {
		HashMap<String, AssetDefinition> assetsMap = new HashMap<>();

		for (AssetDefinition asset : assetDefinitions.getAssetDefinition()) {
			assetsMap.put(asset.getAssetId(), asset);
		}
		
		return assetsMap;
	}
	
	private HashMap<String, VulnerabilityDefinition> makeVulnerabilitiesMap() {
		HashMap<String, VulnerabilityDefinition> vulnerabilitiesMap = new HashMap<>();
		
		for (VulnerabilityDefinition vulnerability : vulnerabilityDefinitions.getVulnerabilityDefinition()) {
			vulnerabilitiesMap.put(vulnerability.getVulnerabilityId(), vulnerability);
		}
		
		return vulnerabilitiesMap;
	}
	
}
