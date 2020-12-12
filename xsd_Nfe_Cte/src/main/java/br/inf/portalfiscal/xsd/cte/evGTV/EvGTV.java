//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:23:04 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.evGTV;

import java.util.ArrayList;
import java.util.List;
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
 *               &lt;enumeration value="Informações da GTV"/&gt;
 *               &lt;enumeration value="Informacoes da GTV"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="infGTV" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="nDoc"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="id"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="20"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="serie" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="subserie" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="3"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="dEmi" type="{http://www.portalfiscal.inf.br/cte}TData"/&gt;
 *                   &lt;element name="nDV"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                         &lt;minLength value="1"/&gt;
 *                         &lt;maxLength value="1"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="qCarga" type="{http://www.portalfiscal.inf.br/cte}TDec_1104"/&gt;
 *                   &lt;element name="infEspecie" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="tpEspecie"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                   &lt;whiteSpace value="preserve"/&gt;
 *                                   &lt;enumeration value="1"/&gt;
 *                                   &lt;enumeration value="2"/&gt;
 *                                   &lt;enumeration value="3"/&gt;
 *                                   &lt;enumeration value="4"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="vEspecie" type="{http://www.portalfiscal.inf.br/cte}TDec_1302" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="rem"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;choice&gt;
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
 *                             &lt;/choice&gt;
 *                             &lt;element name="IE" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TIeDest"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
 *                             &lt;element name="xNome"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                                   &lt;maxLength value="60"/&gt;
 *                                   &lt;minLength value="2"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="dest"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;choice&gt;
 *                               &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
 *                               &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
 *                             &lt;/choice&gt;
 *                             &lt;element name="IE" minOccurs="0"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TIeDest"&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
 *                             &lt;element name="xNome"&gt;
 *                               &lt;simpleType&gt;
 *                                 &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *                                   &lt;maxLength value="60"/&gt;
 *                                   &lt;minLength value="2"/&gt;
 *                                 &lt;/restriction&gt;
 *                               &lt;/simpleType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="placa" type="{http://www.portalfiscal.inf.br/cte}TPlaca" minOccurs="0"/&gt;
 *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf" minOccurs="0"/&gt;
 *                   &lt;element name="RNTRC" minOccurs="0"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;whiteSpace value="preserve"/&gt;
 *                         &lt;pattern value="[0-9]{8}|ISENTO"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
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
        "infGTV"
})
@XmlRootElement(name = "evGTV", namespace = "http://www.portalfiscal.inf.br/cte")
public class EvGTV {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected List<EvGTV.InfGTV> infGTV;

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
     * Gets the value of the infGTV property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the infGTV property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInfGTV().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvGTV.InfGTV }
     */
    public List<EvGTV.InfGTV> getInfGTV() {
        if (infGTV == null) {
            infGTV = new ArrayList<EvGTV.InfGTV>();
        }
        return this.infGTV;
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
     *         &lt;element name="nDoc"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="id"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="20"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="serie" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="subserie" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="3"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="dEmi" type="{http://www.portalfiscal.inf.br/cte}TData"/&gt;
     *         &lt;element name="nDV"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *               &lt;minLength value="1"/&gt;
     *               &lt;maxLength value="1"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="qCarga" type="{http://www.portalfiscal.inf.br/cte}TDec_1104"/&gt;
     *         &lt;element name="infEspecie" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="tpEspecie"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                         &lt;whiteSpace value="preserve"/&gt;
     *                         &lt;enumeration value="1"/&gt;
     *                         &lt;enumeration value="2"/&gt;
     *                         &lt;enumeration value="3"/&gt;
     *                         &lt;enumeration value="4"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="vEspecie" type="{http://www.portalfiscal.inf.br/cte}TDec_1302" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="rem"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;choice&gt;
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
     *                   &lt;/choice&gt;
     *                   &lt;element name="IE" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TIeDest"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
     *                   &lt;element name="xNome"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *                         &lt;maxLength value="60"/&gt;
     *                         &lt;minLength value="2"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="dest"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;choice&gt;
     *                     &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
     *                     &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
     *                   &lt;/choice&gt;
     *                   &lt;element name="IE" minOccurs="0"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TIeDest"&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
     *                   &lt;element name="xNome"&gt;
     *                     &lt;simpleType&gt;
     *                       &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
     *                         &lt;maxLength value="60"/&gt;
     *                         &lt;minLength value="2"/&gt;
     *                       &lt;/restriction&gt;
     *                     &lt;/simpleType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="placa" type="{http://www.portalfiscal.inf.br/cte}TPlaca" minOccurs="0"/&gt;
     *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf" minOccurs="0"/&gt;
     *         &lt;element name="RNTRC" minOccurs="0"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *               &lt;whiteSpace value="preserve"/&gt;
     *               &lt;pattern value="[0-9]{8}|ISENTO"/&gt;
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
            "nDoc",
            "id",
            "serie",
            "subserie",
            "dEmi",
            "ndv",
            "qCarga",
            "infEspecie",
            "rem",
            "dest",
            "placa",
            "uf",
            "rntrc"
    })
    public static class InfGTV {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected String nDoc;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected String id;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
        protected String serie;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
        protected String subserie;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected String dEmi;
        @XmlElement(name = "nDV", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected String ndv;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected String qCarga;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected List<EvGTV.InfGTV.InfEspecie> infEspecie;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected EvGTV.InfGTV.Rem rem;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
        protected EvGTV.InfGTV.Dest dest;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
        protected String placa;
        @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/cte")
        @XmlSchemaType(name = "string")
        protected TUf uf;
        @XmlElement(name = "RNTRC", namespace = "http://www.portalfiscal.inf.br/cte")
        protected String rntrc;

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
         * ObtxE9m o valor da propriedade id.
         *
         * @return possible object is
         * {@link String }
         */
        public String getId() {
            return id;
        }

        /**
         * Define o valor da propriedade id.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setId(String value) {
            this.id = value;
        }

        /**
         * ObtxE9m o valor da propriedade serie.
         *
         * @return possible object is
         * {@link String }
         */
        public String getSerie() {
            return serie;
        }

        /**
         * Define o valor da propriedade serie.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSerie(String value) {
            this.serie = value;
        }

        /**
         * ObtxE9m o valor da propriedade subserie.
         *
         * @return possible object is
         * {@link String }
         */
        public String getSubserie() {
            return subserie;
        }

        /**
         * Define o valor da propriedade subserie.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setSubserie(String value) {
            this.subserie = value;
        }

        /**
         * ObtxE9m o valor da propriedade dEmi.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDEmi() {
            return dEmi;
        }

        /**
         * Define o valor da propriedade dEmi.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDEmi(String value) {
            this.dEmi = value;
        }

        /**
         * ObtxE9m o valor da propriedade ndv.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNDV() {
            return ndv;
        }

        /**
         * Define o valor da propriedade ndv.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setNDV(String value) {
            this.ndv = value;
        }

        /**
         * ObtxE9m o valor da propriedade qCarga.
         *
         * @return possible object is
         * {@link String }
         */
        public String getQCarga() {
            return qCarga;
        }

        /**
         * Define o valor da propriedade qCarga.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setQCarga(String value) {
            this.qCarga = value;
        }

        /**
         * Gets the value of the infEspecie property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infEspecie property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfEspecie().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvGTV.InfGTV.InfEspecie }
         */
        public List<EvGTV.InfGTV.InfEspecie> getInfEspecie() {
            if (infEspecie == null) {
                infEspecie = new ArrayList<EvGTV.InfGTV.InfEspecie>();
            }
            return this.infEspecie;
        }

        /**
         * ObtxE9m o valor da propriedade rem.
         *
         * @return possible object is
         * {@link EvGTV.InfGTV.Rem }
         */
        public EvGTV.InfGTV.Rem getRem() {
            return rem;
        }

        /**
         * Define o valor da propriedade rem.
         *
         * @param value allowed object is
         *              {@link EvGTV.InfGTV.Rem }
         */
        public void setRem(EvGTV.InfGTV.Rem value) {
            this.rem = value;
        }

        /**
         * ObtxE9m o valor da propriedade dest.
         *
         * @return possible object is
         * {@link EvGTV.InfGTV.Dest }
         */
        public EvGTV.InfGTV.Dest getDest() {
            return dest;
        }

        /**
         * Define o valor da propriedade dest.
         *
         * @param value allowed object is
         *              {@link EvGTV.InfGTV.Dest }
         */
        public void setDest(EvGTV.InfGTV.Dest value) {
            this.dest = value;
        }

        /**
         * ObtxE9m o valor da propriedade placa.
         *
         * @return possible object is
         * {@link String }
         */
        public String getPlaca() {
            return placa;
        }

        /**
         * Define o valor da propriedade placa.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setPlaca(String value) {
            this.placa = value;
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
         * ObtxE9m o valor da propriedade rntrc.
         *
         * @return possible object is
         * {@link String }
         */
        public String getRNTRC() {
            return rntrc;
        }

        /**
         * Define o valor da propriedade rntrc.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setRNTRC(String value) {
            this.rntrc = value;
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
         *         &lt;choice&gt;
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
         *         &lt;/choice&gt;
         *         &lt;element name="IE" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TIeDest"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
         *         &lt;element name="xNome"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
         *               &lt;maxLength value="60"/&gt;
         *               &lt;minLength value="2"/&gt;
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
                "cnpj",
                "cpf",
                "ie",
                "uf",
                "xNome"
        })
        public static class Dest {

            @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/cte")
            protected String cnpj;
            @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/cte")
            protected String cpf;
            @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/cte")
            protected String ie;
            @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
            @XmlSchemaType(name = "string")
            protected TUf uf;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
            protected String xNome;

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
         *         &lt;element name="tpEspecie"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *               &lt;whiteSpace value="preserve"/&gt;
         *               &lt;enumeration value="1"/&gt;
         *               &lt;enumeration value="2"/&gt;
         *               &lt;enumeration value="3"/&gt;
         *               &lt;enumeration value="4"/&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="vEspecie" type="{http://www.portalfiscal.inf.br/cte}TDec_1302" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "tpEspecie",
                "vEspecie"
        })
        public static class InfEspecie {

            @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
            protected String tpEspecie;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte")
            protected String vEspecie;

            /**
             * ObtxE9m o valor da propriedade tpEspecie.
             *
             * @return possible object is
             * {@link String }
             */
            public String getTpEspecie() {
                return tpEspecie;
            }

            /**
             * Define o valor da propriedade tpEspecie.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setTpEspecie(String value) {
                this.tpEspecie = value;
            }

            /**
             * ObtxE9m o valor da propriedade vEspecie.
             *
             * @return possible object is
             * {@link String }
             */
            public String getVEspecie() {
                return vEspecie;
            }

            /**
             * Define o valor da propriedade vEspecie.
             *
             * @param value allowed object is
             *              {@link String }
             */
            public void setVEspecie(String value) {
                this.vEspecie = value;
            }

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
         *         &lt;choice&gt;
         *           &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpjOpc"/&gt;
         *           &lt;element name="CPF" type="{http://www.portalfiscal.inf.br/cte}TCpf"/&gt;
         *         &lt;/choice&gt;
         *         &lt;element name="IE" minOccurs="0"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TIeDest"&gt;
         *             &lt;/restriction&gt;
         *           &lt;/simpleType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/cte}TUf"/&gt;
         *         &lt;element name="xNome"&gt;
         *           &lt;simpleType&gt;
         *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
         *               &lt;maxLength value="60"/&gt;
         *               &lt;minLength value="2"/&gt;
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
                "cnpj",
                "cpf",
                "ie",
                "uf",
                "xNome"
        })
        public static class Rem {

            @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/cte")
            protected String cnpj;
            @XmlElement(name = "CPF", namespace = "http://www.portalfiscal.inf.br/cte")
            protected String cpf;
            @XmlElement(name = "IE", namespace = "http://www.portalfiscal.inf.br/cte")
            protected String ie;
            @XmlElement(name = "UF", namespace = "http://www.portalfiscal.inf.br/cte", required = true)
            @XmlSchemaType(name = "string")
            protected TUf uf;
            @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
            protected String xNome;

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

        }

    }

}
