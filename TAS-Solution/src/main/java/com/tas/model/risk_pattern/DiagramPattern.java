package com.tas.model.risk_pattern;

import java.util.ArrayList;
import java.util.List;

import com.tas.model.diagram.AssetDefinition;
import com.tas.model.diagram.BlockElement;
import com.tas.model.diagram.Element;
import com.tas.model.diagram.ExploitDefinition;

public class DiagramPattern {

	private BlockElement element;
	private List<AssetDefinition> assets;

	private BlockElement traceStart;
	private List<Element> trace;	
	private List<String> exploits;
	private List<String> assetsEndangered;
	private List<ExploitDefinition> exploitValues;
	private List<AssetDefinition> assetValues;
	
	
	public DiagramPattern(BlockElement element, BlockElement traceStart, List<Element> trace) {
		this.element = element;
		this.assets = element.getImportAssets().getImportAsset();
		this.traceStart = traceStart;
		this.trace = trace;
		
		exploits = new ArrayList<>();
		assetsEndangered = new ArrayList<>();
		exploitValues = new ArrayList<>();
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

	public List<String> getExploits() {
		return exploits;
	}

	public List<ExploitDefinition> getExploitValues() {
		return exploitValues;
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

	public void setExploits(List<String> exploits) {
		this.exploits = exploits;
	}

	public void setExploitValues(List<ExploitDefinition> exploitValues) {
		this.exploitValues = exploitValues;
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

	public void addExploitValue(ExploitDefinition exploitDefinition) {
		this.exploitValues.add(exploitDefinition);		
	}

	public void addAssetValue(AssetDefinition assetDefinition) {
		this.assetValues.add(assetDefinition);		
	}


	public void addExploitAndAsset(String exploit, String asset) {
		if (!this.exploits.contains(exploit)) {
			this.exploits.add(exploit);
			this.assetsEndangered.add(asset);
		}
	}

	public void removeExploitAndAsset(String exploit) {
		int index = this.exploits.indexOf(exploit);
		if (index != -1) {
			this.exploits.remove(index);
			this.assetsEndangered.remove(index);
		}
	}
	
}
