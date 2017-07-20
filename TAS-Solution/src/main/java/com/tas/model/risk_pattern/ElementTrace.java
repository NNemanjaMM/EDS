package com.tas.model.risk_pattern;

import java.util.ArrayList;
import java.util.List;

import com.tas.model.diagram.BlockElement;
import com.tas.model.diagram.Element;
import com.tas.model.diagram.VulnerabilityDefinition;

public class ElementTrace {

	private BlockElement sourceElement;
	private List<Element> trace;
	
	private List<String> vulnerabilities;
	private List<VulnerabilityDefinition> vulnerabilityValues;
	
	public ElementTrace(BlockElement sourceElement, List<Element> trace) {
		this.sourceElement = sourceElement;
		this.trace = trace;
		
		vulnerabilities = new ArrayList<String>();
		vulnerabilityValues = new ArrayList<VulnerabilityDefinition>();		
	}

	public BlockElement getSourceElement() {
		return sourceElement;
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

	public void setSourceElement(BlockElement sourceElement) {
		this.sourceElement = sourceElement;
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

	public void addVulnerability(String vulnerability) {
		this.vulnerabilities.add(vulnerability);
	}

	public void addVulnerabilityValue(VulnerabilityDefinition vulnerabilityDefinition) {
		this.vulnerabilityValues.add(vulnerabilityDefinition);		
	}	
	
}
