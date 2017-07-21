package com.tas.model.report;

import com.tas.model.diagram.BlockElement;

public class ReportPatternElement {

	private String elementId;
	private String elementName;
	private String elementType;
	
	public ReportPatternElement() {	
		
	}
	
	public ReportPatternElement(BlockElement element) {
		this.elementId = element.getId();
		this.elementName = element.getName();
		this.elementType = element.getClass().toString();
	}

	public String getElementId() {
		return elementId;
	}

	public String getElementName() {
		return elementName;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
		
}
