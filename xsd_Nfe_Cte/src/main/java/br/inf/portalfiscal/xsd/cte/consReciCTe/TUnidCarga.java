//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:22:30 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.consReciCTe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Dados Unidade de Carga
 *
 * <p>Classe Java de TUnidCarga complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TUnidCarga"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tpUnidCarga" type="{http://www.portalfiscal.inf.br/cte}TtipoUnidCarga"/&gt;
 *         &lt;element name="idUnidCarga" type="{http://www.portalfiscal.inf.br/cte}TContainer"/&gt;
 *         &lt;element name="lacUnidCarga" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="nLacre"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="qtdRat" type="{http://www.portalfiscal.inf.br/cte}TDec_0302_0303" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TUnidCarga", propOrder = {
        "tpUnidCarga",
        "idUnidCarga",
        "lacUnidCarga",
        "qtdRat"
})
public class TUnidCarga {

    @XmlElement(required = true)
    protected String tpUnidCarga;
    @XmlElement(required = true)
    protected String idUnidCarga;
    protected List<TUnidCarga.LacUnidCarga> lacUnidCarga;
    protected String qtdRat;

    /**
     * ObtxE9m o valor da propriedade tpUnidCarga.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTpUnidCarga() {
        return tpUnidCarga;
    }

    /**
     * Define o valor da propriedade tpUnidCarga.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTpUnidCarga(String value) {
        this.tpUnidCarga = value;
    }

    /**
     * ObtxE9m o valor da propriedade idUnidCarga.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdUnidCarga() {
        return idUnidCarga;
    }

    /**
     * Define o valor da propriedade idUnidCarga.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdUnidCarga(String value) {
        this.idUnidCarga = value;
    }

    /**
     * Gets the value of the lacUnidCarga property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lacUnidCarga property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLacUnidCarga().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TUnidCarga.LacUnidCarga }
     */
    public List<TUnidCarga.LacUnidCarga> getLacUnidCarga() {
        if (lacUnidCarga == null) {
            lacUnidCarga = new ArrayList<TUnidCarga.LacUnidCarga>();
        }
        return this.lacUnidCarga;
    }

    /**
     * ObtxE9m o valor da propriedade qtdRat.
     *
     * @return possible object is
     * {@link String }
     */
    public String getQtdRat() {
        return qtdRat;
    }

    /**
     * Define o valor da propriedade qtdRat.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQtdRat(String value) {
        this.qtdRat = value;
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
     *         &lt;element name="nLacre"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "nLacre"
    })
    public static class LacUnidCarga {

        @XmlElement(required = true)
        protected String nLacre;

        /**
         * ObtxE9m o valor da propriedade nLacre.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNLacre() {
            return nLacre;
        }

        /**
         * Define o valor da propriedade nLacre.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setNLacre(String value) {
            this.nLacre = value;
        }

    }

}
