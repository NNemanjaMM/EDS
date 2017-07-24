package com.tas.model.risk_pattern;

import java.util.ArrayList;
import java.util.List;

import com.tas.model.diagram.AssetDefinition;
import com.tas.model.diagram.BlockElement;
import com.tas.model.diagram.Element;
import com.tas.model.diagram.VulnerabilityDefinition;

public class DiagramPattern {

	private BlockElement element;
	private List<AssetDefinition> assets;

	private BlockElement traceStart;
	private List<Element> trace;	
	private List<String> vulnerabilities;
	private List<String> assetsEndangered;
	private List<VulnerabilityDefinition> vulnerabilityValues;
	private List<AssetDefinition> assetValues;
	
	
	public DiagramPattern(BlockElement element, BlockElement traceStart, List<Element> trace) {
		this.element = element;
		this.assets = element.getImportAssets().getImportAsset();
		this.traceStart = traceStart;
		this.trace = trace;
		
		vulnerabilities = new ArrayList<>();
		assetsEndangered = new ArrayList<>();
		vulnerabilityValues = new ArrayList<>();
		assetValues = new ArrayList<>();
	}


	public BlockElement getElement() {
		return element;
	}

	public List<AssetDefinition> getAssets() {
		return assets;
	}

	public BlockElement getTraceStart() {
		return traceStart;
	}

	public List<Element> getTrace() {
		return trace;
	}

	public List<String> getVulnerabilities() {
		return vulnerabilities;
	}

	public List<VulnerabilityDefinition> getVulnerabilityValues() {
		return vulnerabilityValues;
	}

	public void setElement(BlockElement element) {
		this.element = element;
	}

	public void setAssets(List<AssetDefinition> assets) {
		this.assets = assets;
	}

	public void setTraceStart(BlockElement traceStart) {
		this.traceStart = traceStart;
	}

	public void setTrace(List<Element> trace) {
		this.trace = trace;
	}

	public void setVulnerabilities(List<String> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

	public void setVulnerabilityValues(List<VulnerabilityDefinition> vulnerabilityValues) {
		this.vulnerabilityValues = vulnerabilityValues;
	}	

	public List<String> getAssetsEndangered() {
		return assetsEndangered;
	}

	public List<AssetDefinition> getAssetValues() {
		return assetValues;
	}

	public void setAssetsEndangered(List<String> assetsEndangered) {
		this.assetsEndangered = assetsEndangered;
	}

	public void setAssetValues(List<AssetDefinition> assetValues) {
		this.assetValues = assetValues;
	}

	public void addVulnerabilityValue(VulnerabilityDefinition vulnerabilityDefinition) {
		this.vulnerabilityValues.add(vulnerabilityDefinition);		
	}

	public void addAssetValue(AssetDefinition assetDefinition) {
		this.assetValues.add(assetDefinition);		
	}


	public void addVulnerabilityAndAsset(String vulnerability, String asset) {
		if (!this.vulnerabilities.contains(vulnerability)) {
			this.vulnerabilities.add(vulnerability);
			this.assetsEndangered.add(asset);
		}
	}

	public void removeVulnerabilityAndAsset(String vulnerability) {
		int index = this.vulnerabilities.indexOf(vulnerability);
		if (index != -1) {
			this.vulnerabilities.remove(index);
			this.assetsEndangered.remove(index);
		}
	}
	
}
