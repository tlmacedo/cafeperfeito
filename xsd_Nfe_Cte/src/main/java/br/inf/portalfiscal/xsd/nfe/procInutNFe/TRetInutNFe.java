//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:21:20 PM AMT 
//


package br.inf.portalfiscal.xsd.nfe.procInutNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Tipo retorno do Pedido de Inutilização de Numeração da Nota Fiscal Eletrônica
 *
 * <p>Classe Java de TRetInutNFe complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TRetInutNFe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="infInut"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/&gt;
 *                   &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/&gt;
 *                   &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/&gt;
 *                   &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/&gt;
 *                   &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/&gt;
 *                   &lt;element name="ano" type="{http://www.portalfiscal.inf.br/nfe}Tano" minOccurs="0"/&gt;
 *                   &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj" minOccurs="0"/&gt;
 *                   &lt;element name="mod" type="{http://www.portalfiscal.inf.br/nfe}TMod" minOccurs="0"/&gt;
 *                   &lt;element name="serie" type="{http://www.portalfiscal.inf.br/nfe}TSerie" minOccurs="0"/&gt;
 *                   &lt;element name="nNFIni" type="{http://www.portalfiscal.inf.br/nfe}TNF" minOccurs="0"/&gt;
 *                   &lt;element name="nNFFin" type="{http://www.portalfiscal.inf.br/nfe}TNF" minOccurs="0"/&gt;
 *                   &lt;element name="dhRecbto" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/&gt;
 *                   &lt;element name="nProt" type="{http://www.portalfiscal.inf.br/nfe}TProt" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/nfe}TVerInutNFe" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRetInutNFe", namespace = "http://www.portalfiscal.inf.br/nfe", propOrder = {
        "infInut",
        "signature"
})
public class TRetInutNFe {

    @XmlElement(required = true)
    protected TRetInutNFe.InfInut infInut;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlAttribute(name = "versao", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versao;

    /**
     * ObtxE9m o valor da propriedade infInut.
     *
     * @return possible object is
     * {@link TRetInutNFe.InfInut }
     */
    public TRetInutNFe.InfInut getInfInut() {
        return infInut;
    }

    /**
     * Define o valor da propriedade infInut.
     *
     * @param value allowed object is
     *              {@link TRetInutNFe.InfInut }
     */
    public void setInfInut(TRetInutNFe.InfInut value) {
        this.infInut = value;
    }

    /**
     * ObtxE9m o valor da propriedade signature.
     *
     * @return possible object is
     * {@link SignatureType }
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Define o valor da propriedade signature.
     *
     * @param value allowed object is
     *              {@link SignatureType }
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * ObtxE9m o valor da propriedade versao.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersao(String value) {
        this.versao = value;
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
     *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/nfe}TAmb"/&gt;
     *         &lt;element name="verAplic" type="{http://www.portalfiscal.inf.br/nfe}TVerAplic"/&gt;
     *         &lt;element name="cStat" type="{http://www.portalfiscal.inf.br/nfe}TStat"/&gt;
     *         &lt;element name="xMotivo" type="{http://www.portalfiscal.inf.br/nfe}TMotivo"/&gt;
     *         &lt;element name="cUF" type="{http://www.portalfiscal.inf.br/nfe}TCodUfIBGE"/&gt;
     *         &lt;element name="ano" type="{http://www.portalfiscal.inf.br/nfe}Tano" minOccurs="0"/&gt;
     *         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/nfe}TCnpj" minOccurs="0"/&gt;
     *         &lt;element name="mod" type="{http://www.portalfiscal.inf.br/nfe}TMod" minOccurs="0"/&gt;
     *         &lt;element name="serie" type="{http://www.portalfiscal.inf.br/nfe}TSerie" minOccurs="0"/&gt;
     *         &lt;element name="nNFIni" type="{http://www.portalfiscal.inf.br/nfe}TNF" minOccurs="0"/&gt;
     *         &lt;element name="nNFFin" type="{http://www.portalfiscal.inf.br/nfe}TNF" minOccurs="0"/&gt;
     *         &lt;element name="dhRecbto" type="{http://www.portalfiscal.inf.br/nfe}TDateTimeUTC"/&gt;
     *         &lt;element name="nProt" type="{http://www.portalfiscal.inf.br/nfe}TProt" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "tpAmb",
            "verAplic",
            "cStat",
            "xMotivo",
            "cuf",
            "ano",
            "cnpj",
            "mod",
            "serie",
            "nnfIni",
            "nnfFin",
            "dhRecbto",
            "nProt"
    })
    public static class InfInut {

        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String tpAmb;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String verAplic;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cStat;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String xMotivo;
        @XmlElement(name = "cUF", namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String cuf;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String ano;
        @XmlElement(name = "CNPJ", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String cnpj;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String mod;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String serie;
        @XmlElement(name = "nNFIni", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String nnfIni;
        @XmlElement(name = "nNFFin", namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String nnfFin;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe", required = true)
        protected String dhRecbto;
        @XmlElement(namespace = "http://www.portalfiscal.inf.br/nfe")
        protected String nProt;
        @XmlAttribute(name = "Id")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

        /**
         * ObtxE9m o valor da propriedade tpAmb.
         *
         * @return possible object is
         * {@link String }
         */
        public String getTpAmb() {
            return tpAmb;
        }

        /**
         * Define o valor da propriedade tpAmb.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setTpAmb(String value) {
            this.tpAmb = value;
        }

        /**
         * ObtxE9m o valor da propriedade verAplic.
         *
         * @return possible object is
         * {@link String }
         */
        public String getVerAplic() {
            return verAplic;
        }

        /**
         * Define o valor da propriedade verAplic.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setVerAplic(String value) {
            this.verAplic = value;
        }

        /**
         * ObtxE9m o valor da propriedade cStat.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCStat() {
            return cStat;
        }

        /**
         * Define o valor da propriedade cStat.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCStat(String value) {
            this.cStat = value;
        }

        /**
         * ObtxE9m o valor da propriedade xMotivo.
         *
         * @return possible object is
         * {@link String }
         */
        public String getXMotivo() {
            return xMotivo;
        }

        /**
         * Define o valor da propriedade xMotivo.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setXMotivo(String value) {
            this.xMotivo = value;
        }

        /**
         * ObtxE9m o valor da propriedade cuf.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCUF() {
            return cuf;
        }

        /**
         * Define o valor da propriedade cuf.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCUF(String value) {
            this.cuf = value;
        }

        /**
         * ObtxE9m o valor da propriedade ano.
         *
         * @return possible object is
         * {@link String }
         */
        public String getAno() {
            return ano;
        }

        /**
         * Define o valor da propriedade ano.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setAno(String value) {
            this.ano = value;
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
         * ObtxE9m o valor da propriedade mod.
         *
         * @return possible object is
         * {@link String }
         */
        public String getMod() {
            return mod;
        }

        /**
         * Define o valor da propriedade mod.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setMod(String value) {
            this.mod = value;
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
         * ObtxE9m o valor da propriedade nnfIni.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNNFIni() {
            return nnfIni;
        }

        /**
         * Define o valor da propriedade nnfIni.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setNNFIni(String value) {
            this.nnfIni = value;
        }

        /**
         * ObtxE9m o valor da propriedade nnfFin.
         *
         * @return possible object is
         * {@link String }
         */
        public String getNNFFin() {
            return nnfFin;
        }

        /**
         * Define o valor da propriedade nnfFin.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setNNFFin(String value) {
            this.nnfFin = value;
        }

        /**
         * ObtxE9m o valor da propriedade dhRecbto.
         *
         * @return possible object is
         * {@link String }
         */
        public String getDhRecbto() {
            return dhRecbto;
        }

        /**
         * Define o valor da propriedade dhRecbto.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setDhRecbto(String value) {
            this.dhRecbto = value;
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

    }

}
