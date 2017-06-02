//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.02 at 08:30:42 AM CEST 
//

package tas.model.diagram;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="elements">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}processes"/>
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}dataStores"/>
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}externalEntities"/>
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}customElements"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="flows">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}flows"/>
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}customFlow"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="shapes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}boundaries"/>
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}trustSections"/>
 *                   &lt;group ref="{https://www.tas.org/diagram-schema}networkSections"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "elements",
    "flows",
    "shapes"
})
@XmlRootElement(name = "diagram")
public class Diagram {

    @XmlElement(required = true)
    protected Diagram.Elements elements;
    @XmlElement(required = true)
    protected Diagram.Flows flows;
    @XmlElement(required = true)
    protected Diagram.Shapes shapes;

    /**
     * Gets the value of the elements property.
     * 
     * @return
     *     possible object is
     *     {@link Diagram.Elements }
     *     
     */
    public Diagram.Elements getElements() {
        return elements;
    }

    /**
     * Sets the value of the elements property.
     * 
     * @param value
     *     allowed object is
     *     {@link Diagram.Elements }
     *     
     */
    public void setElements(Diagram.Elements value) {
        this.elements = value;
    }

    /**
     * Gets the value of the flows property.
     * 
     * @return
     *     possible object is
     *     {@link Diagram.Flows }
     *     
     */
    public Diagram.Flows getFlows() {
        return flows;
    }

    /**
     * Sets the value of the flows property.
     * 
     * @param value
     *     allowed object is
     *     {@link Diagram.Flows }
     *     
     */
    public void setFlows(Diagram.Flows value) {
        this.flows = value;
    }

    /**
     * Gets the value of the shapes property.
     * 
     * @return
     *     possible object is
     *     {@link Diagram.Shapes }
     *     
     */
    public Diagram.Shapes getShapes() {
        return shapes;
    }

    /**
     * Sets the value of the shapes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Diagram.Shapes }
     *     
     */
    public void setShapes(Diagram.Shapes value) {
        this.shapes = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;group ref="{https://www.tas.org/diagram-schema}processes"/>
     *         &lt;group ref="{https://www.tas.org/diagram-schema}dataStores"/>
     *         &lt;group ref="{https://www.tas.org/diagram-schema}externalEntities"/>
     *         &lt;group ref="{https://www.tas.org/diagram-schema}customElements"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orWebApplicationOrWebServiceOrWebServer"
    })
    public static class Elements {

        @XmlElementRefs({
            @XmlElementRef(name = "webService", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "logFile", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "specialApplication", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "user", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "externalWebService", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "customDataStore", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "html5LocalStorage", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "noSqlDatabase", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sqlDatabase", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "securityGateway", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "browser", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "webServer", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "thickClient", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "cloudStorage", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "fileSystem", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "authorizedProvider", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "webApplication", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "managedApplication", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "customProcess", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "smartPhone", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "customExternalEntities", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "configurationFile", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "externalWebApplication", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "cache", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false)
        })
        protected List<JAXBElement<? extends BlockElement>> orWebApplicationOrWebServiceOrWebServer;

        /**
         * Gets the value of the orWebApplicationOrWebServiceOrWebServer property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the orWebApplicationOrWebServiceOrWebServer property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrWebApplicationOrWebServiceOrWebServer().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link Process }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link Process }{@code >}
         * {@link JAXBElement }{@code <}{@link ExternalEntity }{@code >}
         * {@link JAXBElement }{@code <}{@link ExternalEntity }{@code >}
         * {@link JAXBElement }{@code <}{@link Diagram.Elements.CustomDataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link ExternalEntity }{@code >}
         * {@link JAXBElement }{@code <}{@link ExternalEntity }{@code >}
         * {@link JAXBElement }{@code <}{@link Process }{@code >}
         * {@link JAXBElement }{@code <}{@link Process }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link ExternalEntity }{@code >}
         * {@link JAXBElement }{@code <}{@link Process }{@code >}
         * {@link JAXBElement }{@code <}{@link Process }{@code >}
         * {@link JAXBElement }{@code <}{@link Diagram.Elements.CustomProcess }{@code >}
         * {@link JAXBElement }{@code <}{@link ExternalEntity }{@code >}
         * {@link JAXBElement }{@code <}{@link Diagram.Elements.CustomExternalEntities }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * {@link JAXBElement }{@code <}{@link ExternalEntity }{@code >}
         * {@link JAXBElement }{@code <}{@link DataStore }{@code >}
         * 
         * 
         */
        public List<JAXBElement<? extends BlockElement>> getOrWebApplicationOrWebServiceOrWebServer() {
            if (orWebApplicationOrWebServiceOrWebServer == null) {
                orWebApplicationOrWebServiceOrWebServer = new ArrayList<JAXBElement<? extends BlockElement>>();
            }
            return this.orWebApplicationOrWebServiceOrWebServer;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{https://www.tas.org/diagram-schema}dataStore">
         *       &lt;attGroup ref="{https://www.tas.org/diagram-schema}customElementAttributes"/>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class CustomDataStore
            extends DataStore
        {

            @XmlAttribute(name = "title", required = true)
            protected String title;

            /**
             * Gets the value of the title property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Sets the value of the title property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{https://www.tas.org/diagram-schema}externalEntity">
         *       &lt;attGroup ref="{https://www.tas.org/diagram-schema}customElementAttributes"/>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class CustomExternalEntities
            extends ExternalEntity
        {

            @XmlAttribute(name = "title", required = true)
            protected String title;

            /**
             * Gets the value of the title property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Sets the value of the title property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{https://www.tas.org/diagram-schema}process">
         *       &lt;attGroup ref="{https://www.tas.org/diagram-schema}customElementAttributes"/>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class CustomProcess
            extends Process
        {

            @XmlAttribute(name = "title", required = true)
            protected String title;

            /**
             * Gets the value of the title property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Sets the value of the title property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;group ref="{https://www.tas.org/diagram-schema}flows"/>
     *         &lt;group ref="{https://www.tas.org/diagram-schema}customFlow"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orBinaryOrHttpOrHttps"
    })
    public static class Flows {

        @XmlElementRefs({
            @XmlElementRef(name = "binary", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sql", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "ssl", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "udp", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "namedPipe", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "rpcOrDcom", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "tcp", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "smb", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "customFlow", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "https", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "http", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "ipSec", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false)
        })
        protected List<JAXBElement<? extends Flow>> orBinaryOrHttpOrHttps;

        /**
         * Gets the value of the orBinaryOrHttpOrHttps property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the orBinaryOrHttpOrHttps property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrBinaryOrHttpOrHttps().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Diagram.Flows.CustomFlow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * {@link JAXBElement }{@code <}{@link Flow }{@code >}
         * 
         * 
         */
        public List<JAXBElement<? extends Flow>> getOrBinaryOrHttpOrHttps() {
            if (orBinaryOrHttpOrHttps == null) {
                orBinaryOrHttpOrHttps = new ArrayList<JAXBElement<? extends Flow>>();
            }
            return this.orBinaryOrHttpOrHttps;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{https://www.tas.org/diagram-schema}flow">
         *       &lt;attGroup ref="{https://www.tas.org/diagram-schema}customElementAttributes"/>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class CustomFlow
            extends Flow
        {

            @XmlAttribute(name = "title", required = true)
            protected String title;

            /**
             * Gets the value of the title property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Sets the value of the title property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;group ref="{https://www.tas.org/diagram-schema}boundaries"/>
     *         &lt;group ref="{https://www.tas.org/diagram-schema}trustSections"/>
     *         &lt;group ref="{https://www.tas.org/diagram-schema}networkSections"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary"
    })
    public static class Shapes {

        @XmlElementRefs({
            @XmlElementRef(name = "internetBoundary", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "internetDmz", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "otherTrustSection", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "appContainerBoundary", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "officeNetwork", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "companyTrustSection", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "localDMZBoundary", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "machineBoundary", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sandboxTrustSection", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "sharedNetwork", namespace = "https://www.tas.org/diagram-schema", type = JAXBElement.class, required = false)
        })
        protected List<JAXBElement<? extends Shape>> orInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary;

        /**
         * Gets the value of the orInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the orInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link Shape }{@code >}
         * {@link JAXBElement }{@code <}{@link BlockShape }{@code >}
         * {@link JAXBElement }{@code <}{@link BlockShape }{@code >}
         * {@link JAXBElement }{@code <}{@link Shape }{@code >}
         * {@link JAXBElement }{@code <}{@link BlockShape }{@code >}
         * {@link JAXBElement }{@code <}{@link BlockShape }{@code >}
         * {@link JAXBElement }{@code <}{@link Shape }{@code >}
         * {@link JAXBElement }{@code <}{@link Shape }{@code >}
         * {@link JAXBElement }{@code <}{@link BlockShape }{@code >}
         * {@link JAXBElement }{@code <}{@link BlockShape }{@code >}
         * 
         * 
         */
        public List<JAXBElement<? extends Shape>> getOrInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary() {
            if (orInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary == null) {
                orInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary = new ArrayList<JAXBElement<? extends Shape>>();
            }
            return this.orInternetBoundaryOrMachineBoundaryOrLocalDMZBoundary;
        }

    }

}
