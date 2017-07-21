//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.21 at 01:12:52 PM CEST 
//


package com.tas.model.diagram;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for process complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="process">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.tas.org/tas-schemas}blockElement">
 *       &lt;attribute name="sanitizeInput" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="sanitizeOutput" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="hasForgeryProtection" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="sessionHasTimeouts" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="requiresAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="requiresAuthorization" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlSeeAlso({
    Process.class
})
public class OriginalProcess
    extends BlockElement
{

    @XmlAttribute(name = "sanitizeInput")
    protected Boolean sanitizeInput;
    @XmlAttribute(name = "sanitizeOutput")
    protected Boolean sanitizeOutput;
    @XmlAttribute(name = "hasForgeryProtection")
    protected Boolean hasForgeryProtection;
    @XmlAttribute(name = "sessionHasTimeouts")
    protected Boolean sessionHasTimeouts;
    @XmlAttribute(name = "requiresAuthentication")
    protected Boolean requiresAuthentication;
    @XmlAttribute(name = "requiresAuthorization")
    protected Boolean requiresAuthorization;

    /**
     * Gets the value of the sanitizeInput property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSanitizeInput() {
        if (sanitizeInput == null) {
            return false;
        } else {
            return sanitizeInput;
        }
    }

    /**
     * Sets the value of the sanitizeInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSanitizeInput(Boolean value) {
        this.sanitizeInput = value;
    }

    /**
     * Gets the value of the sanitizeOutput property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSanitizeOutput() {
        if (sanitizeOutput == null) {
            return false;
        } else {
            return sanitizeOutput;
        }
    }

    /**
     * Sets the value of the sanitizeOutput property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSanitizeOutput(Boolean value) {
        this.sanitizeOutput = value;
    }

    /**
     * Gets the value of the hasForgeryProtection property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isHasForgeryProtection() {
        if (hasForgeryProtection == null) {
            return false;
        } else {
            return hasForgeryProtection;
        }
    }

    /**
     * Sets the value of the hasForgeryProtection property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasForgeryProtection(Boolean value) {
        this.hasForgeryProtection = value;
    }

    /**
     * Gets the value of the sessionHasTimeouts property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSessionHasTimeouts() {
        if (sessionHasTimeouts == null) {
            return false;
        } else {
            return sessionHasTimeouts;
        }
    }

    /**
     * Sets the value of the sessionHasTimeouts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSessionHasTimeouts(Boolean value) {
        this.sessionHasTimeouts = value;
    }

    /**
     * Gets the value of the requiresAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isRequiresAuthentication() {
        if (requiresAuthentication == null) {
            return false;
        } else {
            return requiresAuthentication;
        }
    }

    /**
     * Sets the value of the requiresAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiresAuthentication(Boolean value) {
        this.requiresAuthentication = value;
    }

    /**
     * Gets the value of the requiresAuthorization property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isRequiresAuthorization() {
        if (requiresAuthorization == null) {
            return false;
        } else {
            return requiresAuthorization;
        }
    }

    /**
     * Sets the value of the requiresAuthorization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequiresAuthorization(Boolean value) {
        this.requiresAuthorization = value;
    }

}
