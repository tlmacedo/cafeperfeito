//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:23:00 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.evCECTe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="descEvento"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;enumeration value="Comprovante de Entrega do CT-e"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="nProt" type="{http://www.portalfiscal.inf.br/cte}TProt"/&gt;
 *         &lt;element name="dhEntrega"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TDateTimeUTC"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="nDoc"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *               &lt;minLength value="2"/&gt;
 *               &lt;maxLength value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="xNome"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *               &lt;maxLength value="60"/&gt;
 *               &lt;minLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="latitude" type="{http://www.portalfiscal.inf.br/cte}TLatitude" minOccurs="0"/&gt;
 *         &lt;element name="longitude" type="{http://www.portalfiscal.inf.br/cte}TLongitude" minOccurs="0"/&gt;
 *         &lt;element name="hashEntrega"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}base64Binary"&gt;
 *               &lt;length value="20"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="dhHashEntrega"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TDateTimeUTC"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="infEntrega" maxOccurs="2000" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/cte}TChNFe"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "descEvento",
        "nProt",
        "dhEntrega",
        "nDoc",
        "xNome",
        "latitude",
        "longitude",
        "hashEntrega",
        "dhHashEntrega",
        "infEntrega"
})
@XmlRootElement(name = "evCECTe", namespace = "http://www.portalfiscal.inf.br/cte")
public class EvCECTe {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String nProt;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String dhEntrega;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String nDoc;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String xNome;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
    protected String latitude;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
    protected String longitude;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected byte[] hashEntrega;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String dhHashEntrega;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
    protected List<EvCECTe.InfEntrega> infEntrega;

    /**
     * ObtxE9m o valor da propriedade descEvento.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDescEvento() {
        return descEvento;
    }

    /**
     * Define o valor da propriedade descEvento.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescEvento(String value) {
        this.descEvento = value;
    }

    /**
     * ObtxE9m o valor da propriedade nProt.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNProt() {
        return nProt;
    }

    /**
     * Define o valor da propriedade nProt.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNProt(String value) {
        this.nProt = value;
    }

    /**
     * ObtxE9m o valor da propriedade dhEntrega.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDhEntrega() {
        return dhEntrega;
    }

    /**
     * Define o valor da propriedade dhEntrega.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDhEntrega(String value) {
        this.dhEntrega = value;
    }

    /**
     * ObtxE9m o valor da propriedade nDoc.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNDoc() {
        return nDoc;
    }

    /**
     * Define o valor da propriedade nDoc.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNDoc(String value) {
        this.nDoc = value;
    }

    /**
     * ObtxE9m o valor da propriedade xNome.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXNome() {
        return xNome;
    }

    /**
     * Define o valor da propriedade xNome.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXNome(String value) {
        this.xNome = value;
    }

    /**
     * ObtxE9m o valor da propriedade latitude.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Define o valor da propriedade latitude.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

    /**
     * ObtxE9m o valor da propriedade longitude.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Define o valor da propriedade longitude.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLongitude(String value) {
        this.longitude = value;
    }

    /**
     * ObtxE9m o valor da propriedade hashEntrega.
     *
     * @return possible object is
     * byte[]
     */
    public byte[] getHashEntrega() {
        return hashEntrega;
    }

    /**
     * Define o valor da propriedade hashEntrega.
     *
     * @param value allowed object is
     *              byte[]
     */
    public void setHashEntrega(byte[] value) {
        this.hashEntrega = value;
    }

    /**
     * ObtxE9m o valor da propriedade dhHashEntrega.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDhHashEntrega() {
        return dhHashEntrega;
    }

    /**
     * Define o valor da propriedade dhHashEntrega.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDhHashEntrega(String value) {
        this.dhHashEntrega = value;
    }

    /**
     * Gets the value of the infEntrega property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infEntrega property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfEntrega().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvCECTe.InfEntrega }
     */
    public List<EvCECTe.InfEntrega> getInfEntrega() {
        if (infEntrega == null) {
            infEntrega = new ArrayList<EvCECTe.InfEntrega>();
        }
        return this.infEntrega;
    }


    /**
     * <p>Classe Java de anonymous complex type.
     *
     * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
     *
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="chNFe" type="{http://www.portalfiscal.inf.br/cte}TChNFe"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "chNFe"
    })
    public static class InfEntrega {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected String chNFe;

        /**
         * ObtxE9m o valor da propriedade chNFe.
         *
         * @return possible object is
         * {@link String }
         */
        public String getChNFe() {
            return chNFe;
        }

        /**
         * Define o valor da propriedade chNFe.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setChNFe(String value) {
            this.chNFe = value;
        }

    }

}
