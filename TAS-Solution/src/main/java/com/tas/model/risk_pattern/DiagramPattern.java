package com.tas.model.risk_pattern;

import java.util.List;

import com.tas.model.diagram.Assets;
import com.tas.model.diagram.BlockElement;

public class DiagramPattern {
	
	private BlockElement destination;
	private List<Assets.Asset> assets;
	private List<ElementTrace> traces;
	
	
	public DiagramPattern(BlockElement destination, List<Assets.Asset> assets, List<ElementTrace> traces) {
		this.destination = destination;
		this.assets = assets;
		this.traces = traces;
	}


	public BlockElement getDestination() {
		return destination;
	}

	public List<Assets.Asset> getAssets() {
		return assets;
	}

	public List<ElementTrace> getTraces() {
		return traces;
	}

	public void setDestination(BlockElement destination) {
		this.destination = destination;
	}

	public void setAssets(List<Assets.Asset> assets) {
		this.assets = assets;
	}

	public void setTraces(List<ElementTrace> traces) {
		this.traces = traces;
	}
	
}
