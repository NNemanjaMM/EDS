//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.04 at 02:52:43 PM CEST 
//


package com.eds.Converter_Solution.model.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataStore complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataStore">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.eds.org/eds-schemas}blockElement">
 *       &lt;attribute name="dataIsEncrypted" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="dataIsSigned" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="storeCredentials" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="hasBackup" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
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
    DataStore.class
})
public class OriginalDataStore
    extends BlockElement
{

    @XmlAttribute(name = "dataIsEncrypted")
    protected Boolean dataIsEncrypted;
    @XmlAttribute(name = "dataIsSigned")
    protected Boolean dataIsSigned;
    @XmlAttribute(name = "storeCredentials")
    protected Boolean storeCredentials;
    @XmlAttribute(name = "hasBackup")
    protected Boolean hasBackup;

    /**
     * Gets the value of the dataIsEncrypted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDataIsEncrypted() {
        if (dataIsEncrypted == null) {
            return false;
        } else {
            return dataIsEncrypted;
        }
    }

    /**
     * Sets the value of the dataIsEncrypted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDataIsEncrypted(Boolean value) {
        this.dataIsEncrypted = value;
    }

    /**
     * Gets the value of the dataIsSigned property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isDataIsSigned() {
        if (dataIsSigned == null) {
            return false;
        } else {
            return dataIsSigned;
        }
    }

    /**
     * Sets the value of the dataIsSigned property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDataIsSigned(Boolean value) {
        this.dataIsSigned = value;
    }

    /**
     * Gets the value of the storeCredentials property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isStoreCredentials() {
        if (storeCredentials == null) {
            return false;
        } else {
            return storeCredentials;
        }
    }

    /**
     * Sets the value of the storeCredentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStoreCredentials(Boolean value) {
        this.storeCredentials = value;
    }

    /**
     * Gets the value of the hasBackup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isHasBackup() {
        if (hasBackup == null) {
            return false;
        } else {
            return hasBackup;
        }
    }

    /**
     * Sets the value of the hasBackup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasBackup(Boolean value) {
        this.hasBackup = value;
    }

}
