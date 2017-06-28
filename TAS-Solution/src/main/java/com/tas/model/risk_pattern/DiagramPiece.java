package com.tas.model.risk_pattern;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.tas.model.diagram.BlockElement;
import com.tas.model.diagram.Flow;
import com.tas.model.diagram.VulnerabilityDefinition;

public class DiagramPiece {
	
	private JAXBElement<Flow> coreFlow;
	private BlockElement coreSource;
	private BlockElement coreDestination;
	
	private List<JAXBElement<Flow>> coreReturnFlows;
	
	private List<JAXBElement<Flow>> coreDestinationFlowsIn;
	private List<JAXBElement<Flow>> coreDestinationFlowsOut;
	private List<JAXBElement<Flow>> coreSourceFlowsIn;
	private List<JAXBElement<Flow>> coreSourceFlowsOut;
	
	private List<String> vulnerabilities;
	private List<VulnerabilityDefinition> vulnerabilityValues;
	
	
	public DiagramPiece(JAXBElement<Flow> flow) {
		this.coreFlow = flow;
		this.coreSource = (BlockElement) flow.getValue().getSource();
		this.coreDestination = (BlockElement) flow.getValue().getDestination();
		
		coreReturnFlows = new ArrayList<JAXBElement<Flow>>();		
		coreDestinationFlowsIn = new ArrayList<JAXBElement<Flow>>();
		coreDestinationFlowsOut = new ArrayList<JAXBElement<Flow>>();
		coreSourceFlowsIn = new ArrayList<JAXBElement<Flow>>();
		coreSourceFlowsOut = new ArrayList<JAXBElement<Flow>>();
		
		vulnerabilities = new ArrayList<String>();
		vulnerabilityValues = new ArrayList<VulnerabilityDefinition>();
	}
	
	
	public JAXBElement<Flow> getCoreFlow() {
		return coreFlow;
	}
	
	public BlockElement getCoreSource() {
		return coreSource;
	}
	
	public BlockElement getCoreDestination() {
		return coreDestination;
	}
	
	public List<JAXBElement<Flow>> getCoreReturnFlows() {
		return coreReturnFlows;
	}
	
	public List<JAXBElement<Flow>> getCoreDestinationFlowsIn() {
		return coreDestinationFlowsIn;
	}
	
	public List<JAXBElement<Flow>> getCoreDestinationFlowsOut() {
		return coreDestinationFlowsOut;
	}
	
	public List<JAXBElement<Flow>> getCoreSourceFlowsIn() {
		return coreSourceFlowsIn;
	}
	
	public List<JAXBElement<Flow>> getCoreSourceFlowsOut() {
		return coreSourceFlowsOut;
	}
	
	public List<String> getVulnerabilities() {
		return vulnerabilities;
	}
	
	public List<VulnerabilityDefinition> getVulnerabilityValues() {
		return vulnerabilityValues;
	}
	
	
	public void setCoreFlow(JAXBElement<Flow> coreFlow) {
		this.coreFlow = coreFlow;
	}
	
	public void setCoreSource(BlockElement coreSource) {
		this.coreSource = coreSource;
	}
	
	public void setCoreDestination(BlockElement coreDestination) {
		this.coreDestination = coreDestination;
	}
	
	public void setCoreReturnFlows(List<JAXBElement<Flow>> coreReturnFlows) {
		this.coreReturnFlows = coreReturnFlows;
	}
	
	public void setCoreDestinationFlowsIn(List<JAXBElement<Flow>> coreDestinationFlows) {
		this.coreDestinationFlowsIn = coreDestinationFlows;
	}
	
	public void setCoreDestinationFlowsOut(List<JAXBElement<Flow>> coreSourceFlows) {
		this.coreDestinationFlowsOut = coreSourceFlows;
	}
	
	public void setCoreSourceFlowsIn(List<JAXBElement<Flow>> coreDestinationFlows) {
		this.coreSourceFlowsIn = coreDestinationFlows;
	}
	
	public void setCoreSourceFlowsOut(List<JAXBElement<Flow>> coreSourceFlows) {
		this.coreSourceFlowsOut = coreSourceFlows;
	}
	
	public void setVulnerabilities(List<String> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}
	
	public void setVulnerabilityValues(List<VulnerabilityDefinition> vulnerabilityValues) {
		this.vulnerabilityValues = vulnerabilityValues;
	}
		
	
	public void addCoreReturnFlow(JAXBElement<Flow> coreReturnFlow) {
		this.coreReturnFlows.add(coreReturnFlow);
	}
	
	public void addCoreDestinationFlowIn(JAXBElement<Flow> coreDestinationFlowIn) {
		this.coreDestinationFlowsIn.add(coreDestinationFlowIn);
	}
	
	public void addCoreDestinationFlowOut(JAXBElement<Flow> coreDestinationFlowOut) {
		this.coreDestinationFlowsOut.add(coreDestinationFlowOut);
	}
	
	public void addCoreSourceFlowIn(JAXBElement<Flow> coreSourceFlowIn) {
		this.coreSourceFlowsIn.add(coreSourceFlowIn);
	}
	
	public void addCoreSourceFlowOut(JAXBElement<Flow> coreSourceFlowOut) {
		this.coreSourceFlowsOut.add(coreSourceFlowOut);
	}
	
	public void addVulnerability(String vulnerabilityId) {
		this.vulnerabilities.add(vulnerabilityId);
	}
	
	public void addVulnerabilityValue(VulnerabilityDefinition vulnerability) {
		this.vulnerabilityValues.add(vulnerability);
	}
	
	public String toString() {
		return this.coreFlow.getValue().getId();
	}
}
