//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:48:49 PM AMT 
//


package br.com.cafeperfeito.xsd.config_nfe.layoutConfig;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Minhas Informações de preencimento
 *
 * <p>Classe Java de MyConfig complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="MyConfig"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="certificadoCfgs"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="certificadoCfg" type="{}CertificadoCfg" maxOccurs="2"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="certificadoToken" type="{}Tstr"/&gt;
 *         &lt;element name="certificadoCacerts" type="{}Tstr"/&gt;
 *         &lt;element name="portaSSl" type="{}Tint_3"/&gt;
 *         &lt;element name="versao" type="{}Tstr"/&gt;
 *         &lt;element name="indSinc" type="{}Tint_1"/&gt;
 *         &lt;element name="natOp" type="{}Tint_1"/&gt;
 *         &lt;element name="mod" type="{}Tint_1"/&gt;
 *         &lt;element name="serie" type="{}Tint_3"/&gt;
 *         &lt;element name="tpNF" type="{}Tint_1"/&gt;
 *         &lt;element name="idDest" type="{}Tint_1"/&gt;
 *         &lt;element name="cMunFG" type="{}Tint_7"/&gt;
 *         &lt;element name="tpImp" type="{}Tint_1"/&gt;
 *         &lt;element name="tpEmis" type="{}Tint_1"/&gt;
 *         &lt;element name="tpAmb" type="{}Tint_1"/&gt;
 *         &lt;element name="finNFe" type="{}Tint_1"/&gt;
 *         &lt;element name="indPres" type="{}Tint_1"/&gt;
 *         &lt;element name="procEmi" type="{}Tint_1"/&gt;
 *         &lt;element name="verProc" type="{}Tstr"/&gt;
 *         &lt;element name="indPag" type="{}Tint_1"/&gt;
 *         &lt;element name="cPais" type="{}Tint_4"/&gt;
 *         &lt;element name="nPais" type="{}Tstr"/&gt;
 *         &lt;element name="CRT" type="{}Tint_1"/&gt;
 *         &lt;element name="infAdic" type="{}Tstr"/&gt;
 *         &lt;element name="homCNPJ" type="{}Tstr"/&gt;
 *         &lt;element name="homRazao" type="{}Tstr"/&gt;
 *         &lt;element name="infRespTecs"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="infRespTec" type="{}InfRespTec" maxOccurs="unbounded"/&gt;
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
@XmlType(name = "MyConfig", propOrder = {
        "certificadoCfgs",
        "certificadoToken",
        "certificadoCacerts",
        "portaSSl",
        "versao",
        "indSinc",
        "natOp",
        "mod",
        "serie",
        "tpNF",
        "idDest",
        "cMunFG",
        "tpImp",
        "tpEmis",
        "tpAmb",
        "finNFe",
        "indPres",
        "procEmi",
        "verProc",
        "indPag",
        "cPais",
        "nPais",
        "crt",
        "infAdic",
        "homCNPJ",
        "homRazao",
        "infRespTecs"
})
public class MyConfig {

    @XmlElement(required = true)
    protected MyConfig.CertificadoCfgs certificadoCfgs;
    @XmlElement(required = true)
    protected String certificadoToken;
    @XmlElement(required = true)
    protected String certificadoCacerts;
    @XmlElement(required = true)
    protected BigInteger portaSSl;
    @XmlElement(required = true)
    protected String versao;
    @XmlElement(required = true)
    protected BigInteger indSinc;
    @XmlElement(required = true)
    protected BigInteger natOp;
    @XmlElement(required = true)
    protected BigInteger mod;
    @XmlElement(required = true)
    protected BigInteger serie;
    @XmlElement(required = true)
    protected BigInteger tpNF;
    @XmlElement(required = true)
    protected BigInteger idDest;
    @XmlElement(required = true)
    protected BigInteger cMunFG;
    @XmlElement(required = true)
    protected BigInteger tpImp;
    @XmlElement(required = true)
    protected BigInteger tpEmis;
    @XmlElement(required = true)
    protected BigInteger tpAmb;
    @XmlElement(required = true)
    protected BigInteger finNFe;
    @XmlElement(required = true)
    protected BigInteger indPres;
    @XmlElement(required = true)
    protected BigInteger procEmi;
    @XmlElement(required = true)
    protected String verProc;
    @XmlElement(required = true)
    protected BigInteger indPag;
    @XmlElement(required = true)
    protected BigInteger cPais;
    @XmlElement(required = true)
    protected String nPais;
    @XmlElement(name = "CRT", required = true)
    protected BigInteger crt;
    @XmlElement(required = true)
    protected String infAdic;
    @XmlElement(required = true)
    protected String homCNPJ;
    @XmlElement(required = true)
    protected String homRazao;
    @XmlElement(required = true)
    protected MyConfig.InfRespTecs infRespTecs;

    /**
     * ObtxE9m o valor da propriedade certificadoCfgs.
     *
     * @return possible object is
     * {@link MyConfig.CertificadoCfgs }
     */
    public MyConfig.CertificadoCfgs getCertificadoCfgs() {
        return certificadoCfgs;
    }

    /**
     * Define o valor da propriedade certificadoCfgs.
     *
     * @param value allowed object is
     *              {@link MyConfig.CertificadoCfgs }
     */
    public void setCertificadoCfgs(MyConfig.CertificadoCfgs value) {
        this.certificadoCfgs = value;
    }

    /**
     * ObtxE9m o valor da propriedade certificadoToken.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCertificadoToken() {
        return certificadoToken;
    }

    /**
     * Define o valor da propriedade certificadoToken.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCertificadoToken(String value) {
        this.certificadoToken = value;
    }

    /**
     * ObtxE9m o valor da propriedade certificadoCacerts.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCertificadoCacerts() {
        return certificadoCacerts;
    }

    /**
     * Define o valor da propriedade certificadoCacerts.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCertificadoCacerts(String value) {
        this.certificadoCacerts = value;
    }

    /**
     * ObtxE9m o valor da propriedade portaSSl.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getPortaSSl() {
        return portaSSl;
    }

    /**
     * Define o valor da propriedade portaSSl.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setPortaSSl(BigInteger value) {
        this.portaSSl = value;
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
     * ObtxE9m o valor da propriedade indSinc.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getIndSinc() {
        return indSinc;
    }

    /**
     * Define o valor da propriedade indSinc.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setIndSinc(BigInteger value) {
        this.indSinc = value;
    }

    /**
     * ObtxE9m o valor da propriedade natOp.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getNatOp() {
        return natOp;
    }

    /**
     * Define o valor da propriedade natOp.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setNatOp(BigInteger value) {
        this.natOp = value;
    }

    /**
     * ObtxE9m o valor da propriedade mod.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getMod() {
        return mod;
    }

    /**
     * Define o valor da propriedade mod.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setMod(BigInteger value) {
        this.mod = value;
    }

    /**
     * ObtxE9m o valor da propriedade serie.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getSerie() {
        return serie;
    }

    /**
     * Define o valor da propriedade serie.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setSerie(BigInteger value) {
        this.serie = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpNF.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getTpNF() {
        return tpNF;
    }

    /**
     * Define o valor da propriedade tpNF.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setTpNF(BigInteger value) {
        this.tpNF = value;
    }

    /**
     * ObtxE9m o valor da propriedade idDest.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getIdDest() {
        return idDest;
    }

    /**
     * Define o valor da propriedade idDest.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setIdDest(BigInteger value) {
        this.idDest = value;
    }

    /**
     * ObtxE9m o valor da propriedade cMunFG.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getCMunFG() {
        return cMunFG;
    }

    /**
     * Define o valor da propriedade cMunFG.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setCMunFG(BigInteger value) {
        this.cMunFG = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpImp.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getTpImp() {
        return tpImp;
    }

    /**
     * Define o valor da propriedade tpImp.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setTpImp(BigInteger value) {
        this.tpImp = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpEmis.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getTpEmis() {
        return tpEmis;
    }

    /**
     * Define o valor da propriedade tpEmis.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setTpEmis(BigInteger value) {
        this.tpEmis = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpAmb.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getTpAmb() {
        return tpAmb;
    }

    /**
     * Define o valor da propriedade tpAmb.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setTpAmb(BigInteger value) {
        this.tpAmb = value;
    }

    /**
     * ObtxE9m o valor da propriedade finNFe.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getFinNFe() {
        return finNFe;
    }

    /**
     * Define o valor da propriedade finNFe.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setFinNFe(BigInteger value) {
        this.finNFe = value;
    }

    /**
     * ObtxE9m o valor da propriedade indPres.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getIndPres() {
        return indPres;
    }

    /**
     * Define o valor da propriedade indPres.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setIndPres(BigInteger value) {
        this.indPres = value;
    }

    /**
     * ObtxE9m o valor da propriedade procEmi.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getProcEmi() {
        return procEmi;
    }

    /**
     * Define o valor da propriedade procEmi.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setProcEmi(BigInteger value) {
        this.procEmi = value;
    }

    /**
     * ObtxE9m o valor da propriedade verProc.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVerProc() {
        return verProc;
    }

    /**
     * Define o valor da propriedade verProc.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVerProc(String value) {
        this.verProc = value;
    }

    /**
     * ObtxE9m o valor da propriedade indPag.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getIndPag() {
        return indPag;
    }

    /**
     * Define o valor da propriedade indPag.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setIndPag(BigInteger value) {
        this.indPag = value;
    }

    /**
     * ObtxE9m o valor da propriedade cPais.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getCPais() {
        return cPais;
    }

    /**
     * Define o valor da propriedade cPais.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setCPais(BigInteger value) {
        this.cPais = value;
    }

    /**
     * ObtxE9m o valor da propriedade nPais.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNPais() {
        return nPais;
    }

    /**
     * Define o valor da propriedade nPais.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNPais(String value) {
        this.nPais = value;
    }

    /**
     * ObtxE9m o valor da propriedade crt.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getCRT() {
        return crt;
    }

    /**
     * Define o valor da propriedade crt.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setCRT(BigInteger value) {
        this.crt = value;
    }

    /**
     * ObtxE9m o valor da propriedade infAdic.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInfAdic() {
        return infAdic;
    }

    /**
     * Define o valor da propriedade infAdic.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInfAdic(String value) {
        this.infAdic = value;
    }

    /**
     * ObtxE9m o valor da propriedade homCNPJ.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHomCNPJ() {
        return homCNPJ;
    }

    /**
     * Define o valor da propriedade homCNPJ.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHomCNPJ(String value) {
        this.homCNPJ = value;
    }

    /**
     * ObtxE9m o valor da propriedade homRazao.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHomRazao() {
        return homRazao;
    }

    /**
     * Define o valor da propriedade homRazao.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHomRazao(String value) {
        this.homRazao = value;
    }

    /**
     * ObtxE9m o valor da propriedade infRespTecs.
     *
     * @return possible object is
     * {@link MyConfig.InfRespTecs }
     */
    public MyConfig.InfRespTecs getInfRespTecs() {
        return infRespTecs;
    }

    /**
     * Define o valor da propriedade infRespTecs.
     *
     * @param value allowed object is
     *              {@link MyConfig.InfRespTecs }
     */
    public void setInfRespTecs(MyConfig.InfRespTecs value) {
        this.infRespTecs = value;
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
     *         &lt;element name="certificadoCfg" type="{}CertificadoCfg" maxOccurs="2"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "certificadoCfg"
    })
    public static class CertificadoCfgs {

        @XmlElement(required = true)
        protected List<CertificadoCfg> certificadoCfg;

        /**
         * Gets the value of the certificadoCfg property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the certificadoCfg property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCertificadoCfg().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CertificadoCfg }
         */
        public List<CertificadoCfg> getCertificadoCfg() {
            if (certificadoCfg == null) {
                certificadoCfg = new ArrayList<CertificadoCfg>();
            }
            return this.certificadoCfg;
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
     *         &lt;element name="infRespTec" type="{}InfRespTec" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "infRespTec"
    })
    public static class InfRespTecs {

        @XmlElement(required = true)
        protected List<InfRespTec> infRespTec;

        /**
         * Gets the value of the infRespTec property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the infRespTec property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInfRespTec().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InfRespTec }
         */
        public List<InfRespTec> getInfRespTec() {
            if (infRespTec == null) {
                infRespTec = new ArrayList<InfRespTec>();
            }
            return this.infRespTec;
        }

    }

}
