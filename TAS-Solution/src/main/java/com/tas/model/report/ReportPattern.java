package com.tas.model.report;

import com.tas.model.risk_pattern.DiagramPattern;

public class ReportPattern {

	private static int counter = 10000000;
	private int id;
	private ReportPatternElement destinationElement;
	private ReportPatternElement sourceElement;
	private ReportPatternVulnerabilities vulnerabilities;
	
	public ReportPattern() {		
		
	}
	
	public ReportPattern(DiagramPattern diagramPattern) {
		counter++;
		this.id = counter;
		this.destinationElement = new ReportPatternElement(diagramPattern.getElement());
		this.sourceElement = new ReportPatternElement(diagramPattern.getTraceStart());
		this.vulnerabilities = new ReportPatternVulnerabilities(diagramPattern.getVulnerabilityValues());
	}
	
		
	public static int getCounter() {
		return counter;
	}

	public int getId() {
		return id;
	}

	public ReportPatternElement getDestinationElement() {
		return destinationElement;
	}

	public ReportPatternElement getSourceElement() {
		return sourceElement;
	}

	public ReportPatternVulnerabilities getVulnerabilities() {
		return vulnerabilities;
	}

	public static void setCounter(int counter) {
		ReportPattern.counter = counter;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDestinationElement(ReportPatternElement destinationElement) {
		this.destinationElement = destinationElement;
	}

	public void setSourceElement(ReportPatternElement sourceElement) {
		this.sourceElement = sourceElement;
	}

	public void setVulnerabilities(ReportPatternVulnerabilities vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

}
