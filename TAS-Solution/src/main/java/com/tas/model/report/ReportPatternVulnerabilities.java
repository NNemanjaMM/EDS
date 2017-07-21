package com.tas.model.report;

import java.util.List;

import com.tas.model.diagram.VulnerabilityDefinition;

public class ReportPatternVulnerabilities {
	
	List<VulnerabilityDefinition> vulnerability;
	
	
	public ReportPatternVulnerabilities() {
		
	}
	
	public ReportPatternVulnerabilities(List<VulnerabilityDefinition> vulnerability) {
		this.vulnerability = vulnerability;
	}
	

	public List<VulnerabilityDefinition> getVulnerability() {
		return vulnerability;
	}

	public void setVulnerability(List<VulnerabilityDefinition> vulnerability) {
		this.vulnerability = vulnerability;
	}	
	
}
