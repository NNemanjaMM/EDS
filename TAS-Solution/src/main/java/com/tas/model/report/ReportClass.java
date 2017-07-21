package com.tas.model.report;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "reports")
public class ReportClass {
	
	private List<ReportPattern> report;

	public ReportClass() {
		
	}

	public ReportClass(List<ReportPattern> report) {
		this.report = report;
	}

	public List<ReportPattern> getReport() {
		return report;
	}

	public void setReport(List<ReportPattern> report) {
		this.report = report;
	}
	
}
