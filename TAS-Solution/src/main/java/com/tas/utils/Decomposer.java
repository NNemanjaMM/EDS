package com.tas.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBElement;

import com.tas.model.diagram.Assets;
import com.tas.model.diagram.BlockElement;
import com.tas.model.diagram.Diagram;
import com.tas.model.diagram.Element;
import com.tas.model.diagram.Flow;
import com.tas.model.risk_pattern.DiagramPattern;
import com.tas.model.risk_pattern.ElementTrace;

public class Decomposer {
	
	private List<JAXBElement<? extends BlockElement>> diagramElements;
	private List<JAXBElement<Flow>> diagramFlows;
	
	
	public Decomposer(Diagram diagram) {
		this.diagramElements = diagram.getElements().getOrWebApplicationOrWebServiceOrWebServer();
		this.diagramFlows = diagram.getFlows().getOrBinaryOrHttpOrHttps();
	}

	
	public List<DiagramPattern> decomposeAllPatterns() {
		ArrayList<DiagramPattern> patterns = new ArrayList<DiagramPattern>();

		for (JAXBElement<? extends BlockElement> blockElement : diagramElements) {
			patterns.add(decomposePattern(blockElement.getValue()));
		}
		
		return patterns;
	}
	
	public DiagramPattern decomposeSinglePatterns(BlockElement blockElement) {
		return decomposePattern(blockElement);
	}
	
	private DiagramPattern decomposePattern(BlockElement blockElement) {

		List<Assets.Asset> assets = blockElement.getAssets().getAsset();
		Map<BlockElement, ElementTrace> traces = getTracesForElement(blockElement);		
		
		DiagramPattern pattern = new DiagramPattern(blockElement, assets, traces);	
	
		return pattern;
	}
	
	private Map<BlockElement, ElementTrace> getTracesForElement(BlockElement baseAnalyzeElement) {
		Map<BlockElement, ElementTrace> traces = new HashMap<BlockElement, ElementTrace>();
		
		HashSet<String> analyzedElements = new HashSet<>();
		Stack<BlockElement> toAnalyzeElements = new Stack<BlockElement>();
		Stack<Element> analyzingRow = new Stack<Element>();
		
		toAnalyzeElements.push(baseAnalyzeElement);	
		analyzedElements.add(baseAnalyzeElement.getId());

		while (!toAnalyzeElements.isEmpty()) {
			
			boolean discoveredNew = false;
			BlockElement analyzeElement = toAnalyzeElements.peek();
			
			List<JAXBElement<Flow>> flows = diagramFlows.stream().filter(f -> ((BlockElement)f.getValue().getDestination()).getId().equals(analyzeElement.getId())).collect(Collectors.toList());
			Flow linkingFlow = null;
			
			for (JAXBElement<Flow> flow : flows) {
				if (!analyzedElements.contains(((BlockElement)flow.getValue().getSource()).getId())) {
					discoveredNew = true;
					linkingFlow = flow.getValue();
					toAnalyzeElements.push((BlockElement) flow.getValue().getSource());
					analyzedElements.add(((BlockElement) flow.getValue().getSource()).getId());
					break;
				}
			} 

			analyzingRow.push(analyzeElement);	
			if (linkingFlow != null) {
				analyzingRow.push(linkingFlow);
			}
			
			if (baseAnalyzeElement.getId() != analyzeElement.getId()) {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				ElementTrace trace = new ElementTrace(analyzeElement, new ArrayList(analyzingRow));
				traces.put(analyzeElement, trace);
			}

			if (!discoveredNew && !analyzingRow.isEmpty()) {
				toAnalyzeElements.pop();
				analyzingRow.pop();	
				if (!analyzingRow.isEmpty()) {
					analyzingRow.pop();
				}
				if (!analyzingRow.isEmpty()) {
					analyzingRow.pop();
				}
			}
		
		}	
		
		return traces;
	}
	
}
