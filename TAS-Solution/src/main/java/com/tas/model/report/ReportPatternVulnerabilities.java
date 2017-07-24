package com.tas.model.report;

import java.util.ArrayList;
import java.util.List;

import com.tas.model.diagram.AssetDefinition;
import com.tas.model.diagram.VulnerabilityDefinition;
import com.tas.model.diagram.VulnerabilityDefinitionReport;

public class ReportPatternVulnerabilities {
	
	List<VulnerabilityDefinitionReport> vulnerability;
	
	
	public ReportPatternVulnerabilities() {
		vulnerability = new ArrayList<>();
	}	

	public ReportPatternVulnerabilities(List<VulnerabilityDefinition> vulnerabilityValues, List<AssetDefinition> assetValues) {
		vulnerability = new ArrayList<>();
		
		for (int i = 0; i < vulnerabilityValues.size(); i++) {
			VulnerabilityDefinitionReport repportVulnerability = new VulnerabilityDefinitionReport();
			repportVulnerability.setCountermeasures(vulnerabilityValues.get(i).getCountermeasures());
			repportVulnerability.setDescription(vulnerabilityValues.get(i).getDescription());
			repportVulnerability.setVulnerabilityId(vulnerabilityValues.get(i).getVulnerabilityId());
			repportVulnerability.setVulnerabilityTitle(vulnerabilityValues.get(i).getVulnerabilityTitle());
			repportVulnerability.setEndangeredAsset(assetValues.get(i));
			
			this.vulnerability.add(repportVulnerability);
		}		
	}

	public List<VulnerabilityDefinitionReport> getVulnerability() {
		return vulnerability;
	}

	public void setVulnerability(List<VulnerabilityDefinitionReport> vulnerability) {
		this.vulnerability = vulnerability;
	}	
	
}
