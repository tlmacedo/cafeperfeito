//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:23:03 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.evEPECCTe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *               &lt;enumeration value="EPEC"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="xJust" type="{http://www.portalfiscal.inf.br/cte}TJust"/&gt;
 *         &lt;element name="vICMS" type="{http://www.portalfiscal.inf.br/cte}TDec_1302"/&gt;
 *         &lt;element name="vICMSST" type="{http://www.portalfiscal.inf.br/cte}TDec_1302" minOccurs="0"/&gt;
 *         &lt;element name="vTPrest" type="{http://www.portalfiscal.inf.br/cte}TDec_1302"/&gt;
 *         &lt;element name="vCarga" type="{http://www.portalfiscal.inf.br/cte}TDec_1302"/&gt;
 *         &lt;element name="toma4"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="toma"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;whiteSpace value="preserve"/&gt;
 *                         &lt;enumeration value="0"/&gt;
 *                         &lt;enumeration value="1"/&gt;
 *                         &lt;enumeration value="2"/&gt;
 *                         &lt;enumeration value="3"/&gt;
 *                         &lt;enumeration value="4"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
 *                   &lt;choice&gt;
 *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
 *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
 *                   &lt;/choice&gt;
 *                   &lt;element name="IE" type="{http://www.portalfiscal.inf.br/cte}TIeDest" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="modal" type="{http://www.portalfiscal.inf.br/cte}TModTransp"/&gt;
 *         &lt;element name="UFIni" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
 *         &lt;element name="UFFim" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
 *         &lt;element name="tpCTe"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;length value="1"/&gt;
 *               &lt;enumeration value="0"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="dhEmi"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TDateTimeUTC"&gt;
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
        "descEvento",
        "xJust",
        "vicms",
        "vicmsst",
        "vtPrest",
        "vCarga",
        "toma4",
        "modal",
        "ufIni",
        "ufFim",
        "tpCTe",
        "dhEmi"
})
@XmlRootElement(name = "evEPECCTe", namespace = "http://www.portalfiscal.inf.br/cte")
public class EvEPECCTe {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String xJust;
    @XmlElement(name = "vICMS", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String vicms;
    @XmlElement(name = "vICMSST", namespace = "http://www.portalfiscal.inf.br/cte")
    protected String vicmsst;
    @XmlElement(name = "vTPrest", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String vtPrest;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String vCarga;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected EvEPECCTe.Toma4 toma4;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String modal;
    @XmlElement(name = "UFIni", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    @XmlSchemaType(name = "string")
    protected TUf ufIni;
    @XmlElement(name = "UFFim", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    @XmlSchemaType(name = "string")
    protected TUf ufFim;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String tpCTe;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String dhEmi;

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
     * ObtxE9m o valor da propriedade xJust.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXJust() {
        return xJust;
    }

    /**
     * Define o valor da propriedade xJust.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXJust(String value) {
        this.xJust = value;
    }

    /**
     * ObtxE9m o valor da propriedade vicms.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVICMS() {
        return vicms;
    }

    /**
     * Define o valor da propriedade vicms.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVICMS(String value) {
        this.vicms = value;
    }

    /**
     * ObtxE9m o valor da propriedade vicmsst.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVICMSST() {
        return vicmsst;
    }

    /**
     * Define o valor da propriedade vicmsst.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVICMSST(String value) {
        this.vicmsst = value;
    }

    /**
     * ObtxE9m o valor da propriedade vtPrest.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVTPrest() {
        return vtPrest;
    }

    /**
     * Define o valor da propriedade vtPrest.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVTPrest(String value) {
        this.vtPrest = value;
    }

    /**
     * ObtxE9m o valor da propriedade vCarga.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVCarga() {
        return vCarga;
    }

    /**
     * Define o valor da propriedade vCarga.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVCarga(String value) {
        this.vCarga = value;
    }

    /**
     * ObtxE9m o valor da propriedade toma4.
     *
     * @return possible object is
     * {@link EvEPECCTe.Toma4 }
     */
    public EvEPECCTe.Toma4 getToma4() {
        return toma4;
    }

    /**
     * Define o valor da propriedade toma4.
     *
     * @param value allowed object is
     *              {@link EvEPECCTe.Toma4 }
     */
    public void setToma4(EvEPECCTe.Toma4 value) {
        this.toma4 = value;
    }

    /**
     * ObtxE9m o valor da propriedade modal.
     *
     * @return possible object is
     * {@link String }
     */
    public String getModal() {
        return modal;
    }

    /**
     * Define o valor da propriedade modal.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setModal(String value) {
        this.modal = value;
    }

    /**
     * ObtxE9m o valor da propriedade ufIni.
     *
     * @return possible object is
     * {@link TUf }
     */
    public TUf getUFIni() {
        return ufIni;
    }

    /**
     * Define o valor da propriedade ufIni.
     *
     * @param value allowed object is
     *              {@link TUf }
     */
    public void setUFIni(TUf value) {
        this.ufIni = value;
    }

    /**
     * ObtxE9m o valor da propriedade ufFim.
     *
     * @return possible object is
     * {@link TUf }
     */
    public TUf getUFFim() {
        return ufFim;
    }

    /**
     * Define o valor da propriedade ufFim.
     *
     * @param value allowed object is
     *              {@link TUf }
     */
    public void setUFFim(TUf value) {
        this.ufFim = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpCTe.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTpCTe() {
        return tpCTe;
    }

    /**
     * Define o valor da propriedade tpCTe.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTpCTe(String value) {
        this.tpCTe = value;
    }

    /**
     * ObtxE9m o valor da propriedade dhEmi.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDhEmi() {
        return dhEmi;
    }

    /**
     * Define o valor da propriedade dhEmi.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDhEmi(String value) {
        this.dhEmi = value;
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
     *         &lt;element name="toma"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;whiteSpace value="preserve"/&gt;
     *               &lt;enumeration value="0"/&gt;
     *               &lt;enumeration value="1"/&gt;
     *               &lt;enumeration value="2"/&gt;
     *               &lt;enumeration value="3"/&gt;
     *               &lt;enumeration value="4"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
     *         &lt;choice&gt;
     *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
     *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
     *         &lt;/choice&gt;
     *         &lt;element name="IE" type="{http://www.portalfiscal.inf.br/cte}TIeDest" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "toma",
            "uf",
            "cnpj",
            "cpf",
            "ie"
    })
    public static class Toma4 {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected String toma;
        @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        @XmlSchemaType(name = "string")
        protected TUf uf;
        @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/cte")
        protected String cnpj;
        @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/cte")
        protected String cpf;
        @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/cte")
        protected String ie;

        /**
         * ObtxE9m o valor da propriedade toma.
         *
         * @return possible object is
         * {@link String }
         */
        public String getToma() {
            return toma;
        }

        /**
         * Define o valor da propriedade toma.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setToma(String value) {
            this.toma = value;
        }

        /**
         * ObtxE9m o valor da propriedade uf.
         *
         * @return possible object is
         * {@link TUf }
         */
        public TUf getUF() {
            return uf;
        }

        /**
         * Define o valor da propriedade uf.
         *
         * @param value allowed object is
         *              {@link TUf }
         */
        public void setUF(TUf value) {
            this.uf = value;
        }

        /**
         * ObtxE9m o valor da propriedade cnpj.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCNPJ() {
            return cnpj;
        }

        /**
         * Define o valor da propriedade cnpj.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCNPJ(String value) {
            this.cnpj = value;
        }

        /**
         * ObtxE9m o valor da propriedade cpf.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCPF() {
            return cpf;
        }

        /**
         * Define o valor da propriedade cpf.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCPF(String value) {
            this.cpf = value;
        }

        /**
         * ObtxE9m o valor da propriedade ie.
         *
         * @return possible object is
         * {@link String }
         */
        public String getIE() {
            return ie;
        }

        /**
         * Define o valor da propriedade ie.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setIE(String value) {
            this.ie = value;
        }

    }

}
