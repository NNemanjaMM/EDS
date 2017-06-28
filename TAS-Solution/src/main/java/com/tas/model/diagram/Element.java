//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.28 at 08:37:30 AM CEST 
//


package com.tas.model.diagram;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for element complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="element">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.tas.org/tas-schemas}base">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="vulnerabilities" type="{https://www.tas.org/tas-schemas}vulnerabilities" minOccurs="0" form="qualified"/>
 *         &lt;element name="importAssets" type="{https://www.tas.org/tas-schemas}importAssets" minOccurs="0"/>
 *         &lt;element name="importVulnerabilities" type="{https://www.tas.org/tas-schemas}importVulnerabilities" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="outOfScope" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="outOfScopeReason" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "element", propOrder = {
    "vulnerabilities",
    "importAssets",
    "importVulnerabilities"
})
@XmlSeeAlso({
    BlockElement.class,
    OriginalFlow.class
})
public abstract class Element
    extends Base
{

    protected Vulnerabilities vulnerabilities;
    protected ImportAssets importAssets;
    protected ImportVulnerabilities importVulnerabilities;
    @XmlAttribute(name = "outOfScope")
    protected Boolean outOfScope;
    @XmlAttribute(name = "outOfScopeReason")
    protected String outOfScopeReason;

    /**
     * Gets the value of the vulnerabilities property.
     * 
     * @return
     *     possible object is
     *     {@link Vulnerabilities }
     *     
     */
    public Vulnerabilities getVulnerabilities() {
        return vulnerabilities;
    }

    /**
     * Sets the value of the vulnerabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link Vulnerabilities }
     *     
     */
    public void setVulnerabilities(Vulnerabilities value) {
        this.vulnerabilities = value;
    }

    /**
     * Gets the value of the importAssets property.
     * 
     * @return
     *     possible object is
     *     {@link ImportAssets }
     *     
     */
    public ImportAssets getImportAssets() {
        return importAssets;
    }

    /**
     * Sets the value of the importAssets property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportAssets }
     *     
     */
    public void setImportAssets(ImportAssets value) {
        this.importAssets = value;
    }

    /**
     * Gets the value of the importVulnerabilities property.
     * 
     * @return
     *     possible object is
     *     {@link ImportVulnerabilities }
     *     
     */
    public ImportVulnerabilities getImportVulnerabilities() {
        return importVulnerabilities;
    }

    /**
     * Sets the value of the importVulnerabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportVulnerabilities }
     *     
     */
    public void setImportVulnerabilities(ImportVulnerabilities value) {
        this.importVulnerabilities = value;
    }

    /**
     * Gets the value of the outOfScope property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isOutOfScope() {
        if (outOfScope == null) {
            return false;
        } else {
            return outOfScope;
        }
    }

    /**
     * Sets the value of the outOfScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOutOfScope(Boolean value) {
        this.outOfScope = value;
    }

    /**
     * Gets the value of the outOfScopeReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutOfScopeReason() {
        return outOfScopeReason;
    }

    /**
     * Sets the value of the outOfScopeReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutOfScopeReason(String value) {
        this.outOfScopeReason = value;
    }

}
