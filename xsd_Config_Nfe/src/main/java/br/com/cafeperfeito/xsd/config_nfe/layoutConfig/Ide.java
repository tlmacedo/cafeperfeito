//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:48:49 PM AMT 
//


package br.com.cafeperfeito.xsd.config_nfe.layoutConfig;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Informações de identificação da NF-e
 *
 * <p>Classe Java de Ide complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="Ide"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="natOps"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="natOp" type="{}NatOp" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="indPags"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="indPag" type="{}IndPag" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="mods"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="mod" type="{}Mod" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="idDests"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="idDest" type="{}IdDest" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tpImps"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="tpImp" type="{}TpImp" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tpEmiss"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="tpEmis" type="{}TpEmis" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="tpAmbs"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="tpAmb" type="{}TpAmb" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="finNFes"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="finNFe" type="{}FinNFe" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="indFinals"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="indFinal" type="{}IndFinal" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="indPress"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="indPres" type="{}IndPres" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="procEmis"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="procEmi" type="{}ProcEmi" maxOccurs="unbounded"/&gt;
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
@XmlType(name = "Ide", propOrder = {
        "natOps",
        "indPags",
        "mods",
        "idDests",
        "tpImps",
        "tpEmiss",
        "tpAmbs",
        "finNFes",
        "indFinals",
        "indPress",
        "procEmis"
})
public class Ide {

    @XmlElement(required = true)
    protected Ide.NatOps natOps;
    @XmlElement(required = true)
    protected Ide.IndPags indPags;
    @XmlElement(required = true)
    protected Ide.Mods mods;
    @XmlElement(required = true)
    protected Ide.IdDests idDests;
    @XmlElement(required = true)
    protected Ide.TpImps tpImps;
    @XmlElement(required = true)
    protected Ide.TpEmiss tpEmiss;
    @XmlElement(required = true)
    protected Ide.TpAmbs tpAmbs;
    @XmlElement(required = true)
    protected Ide.FinNFes finNFes;
    @XmlElement(required = true)
    protected Ide.IndFinals indFinals;
    @XmlElement(required = true)
    protected Ide.IndPress indPress;
    @XmlElement(required = true)
    protected Ide.ProcEmis procEmis;

    /**
     * ObtxE9m o valor da propriedade natOps.
     *
     * @return possible object is
     * {@link Ide.NatOps }
     */
    public Ide.NatOps getNatOps() {
        return natOps;
    }

    /**
     * Define o valor da propriedade natOps.
     *
     * @param value allowed object is
     *              {@link Ide.NatOps }
     */
    public void setNatOps(Ide.NatOps value) {
        this.natOps = value;
    }

    /**
     * ObtxE9m o valor da propriedade indPags.
     *
     * @return possible object is
     * {@link Ide.IndPags }
     */
    public Ide.IndPags getIndPags() {
        return indPags;
    }

    /**
     * Define o valor da propriedade indPags.
     *
     * @param value allowed object is
     *              {@link Ide.IndPags }
     */
    public void setIndPags(Ide.IndPags value) {
        this.indPags = value;
    }

    /**
     * ObtxE9m o valor da propriedade mods.
     *
     * @return possible object is
     * {@link Ide.Mods }
     */
    public Ide.Mods getMods() {
        return mods;
    }

    /**
     * Define o valor da propriedade mods.
     *
     * @param value allowed object is
     *              {@link Ide.Mods }
     */
    public void setMods(Ide.Mods value) {
        this.mods = value;
    }

    /**
     * ObtxE9m o valor da propriedade idDests.
     *
     * @return possible object is
     * {@link Ide.IdDests }
     */
    public Ide.IdDests getIdDests() {
        return idDests;
    }

    /**
     * Define o valor da propriedade idDests.
     *
     * @param value allowed object is
     *              {@link Ide.IdDests }
     */
    public void setIdDests(Ide.IdDests value) {
        this.idDests = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpImps.
     *
     * @return possible object is
     * {@link Ide.TpImps }
     */
    public Ide.TpImps getTpImps() {
        return tpImps;
    }

    /**
     * Define o valor da propriedade tpImps.
     *
     * @param value allowed object is
     *              {@link Ide.TpImps }
     */
    public void setTpImps(Ide.TpImps value) {
        this.tpImps = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpEmiss.
     *
     * @return possible object is
     * {@link Ide.TpEmiss }
     */
    public Ide.TpEmiss getTpEmiss() {
        return tpEmiss;
    }

    /**
     * Define o valor da propriedade tpEmiss.
     *
     * @param value allowed object is
     *              {@link Ide.TpEmiss }
     */
    public void setTpEmiss(Ide.TpEmiss value) {
        this.tpEmiss = value;
    }

    /**
     * ObtxE9m o valor da propriedade tpAmbs.
     *
     * @return possible object is
     * {@link Ide.TpAmbs }
     */
    public Ide.TpAmbs getTpAmbs() {
        return tpAmbs;
    }

    /**
     * Define o valor da propriedade tpAmbs.
     *
     * @param value allowed object is
     *              {@link Ide.TpAmbs }
     */
    public void setTpAmbs(Ide.TpAmbs value) {
        this.tpAmbs = value;
    }

    /**
     * ObtxE9m o valor da propriedade finNFes.
     *
     * @return possible object is
     * {@link Ide.FinNFes }
     */
    public Ide.FinNFes getFinNFes() {
        return finNFes;
    }

    /**
     * Define o valor da propriedade finNFes.
     *
     * @param value allowed object is
     *              {@link Ide.FinNFes }
     */
    public void setFinNFes(Ide.FinNFes value) {
        this.finNFes = value;
    }

    /**
     * ObtxE9m o valor da propriedade indFinals.
     *
     * @return possible object is
     * {@link Ide.IndFinals }
     */
    public Ide.IndFinals getIndFinals() {
        return indFinals;
    }

    /**
     * Define o valor da propriedade indFinals.
     *
     * @param value allowed object is
     *              {@link Ide.IndFinals }
     */
    public void setIndFinals(Ide.IndFinals value) {
        this.indFinals = value;
    }

    /**
     * ObtxE9m o valor da propriedade indPress.
     *
     * @return possible object is
     * {@link Ide.IndPress }
     */
    public Ide.IndPress getIndPress() {
        return indPress;
    }

    /**
     * Define o valor da propriedade indPress.
     *
     * @param value allowed object is
     *              {@link Ide.IndPress }
     */
    public void setIndPress(Ide.IndPress value) {
        this.indPress = value;
    }

    /**
     * ObtxE9m o valor da propriedade procEmis.
     *
     * @return possible object is
     * {@link Ide.ProcEmis }
     */
    public Ide.ProcEmis getProcEmis() {
        return procEmis;
    }

    /**
     * Define o valor da propriedade procEmis.
     *
     * @param value allowed object is
     *              {@link Ide.ProcEmis }
     */
    public void setProcEmis(Ide.ProcEmis value) {
        this.procEmis = value;
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
     *         &lt;element name="finNFe" type="{}FinNFe" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "finNFe"
    })
    public static class FinNFes {

        @XmlElement(required = true)
        protected List<FinNFe> finNFe;

        /**
         * Gets the value of the finNFe property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the finNFe property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFinNFe().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link FinNFe }
         */
        public List<FinNFe> getFinNFe() {
            if (finNFe == null) {
                finNFe = new ArrayList<FinNFe>();
            }
            return this.finNFe;
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
     *         &lt;element name="idDest" type="{}IdDest" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "idDest"
    })
    public static class IdDests {

        @XmlElement(required = true)
        protected List<IdDest> idDest;

        /**
         * Gets the value of the idDest property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the idDest property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIdDest().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IdDest }
         */
        public List<IdDest> getIdDest() {
            if (idDest == null) {
                idDest = new ArrayList<IdDest>();
            }
            return this.idDest;
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
     *         &lt;element name="indFinal" type="{}IndFinal" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "indFinal"
    })
    public static class IndFinals {

        @XmlElement(required = true)
        protected List<IndFinal> indFinal;

        /**
         * Gets the value of the indFinal property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the indFinal property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIndFinal().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IndFinal }
         */
        public List<IndFinal> getIndFinal() {
            if (indFinal == null) {
                indFinal = new ArrayList<IndFinal>();
            }
            return this.indFinal;
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
     *         &lt;element name="indPag" type="{}IndPag" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "indPag"
    })
    public static class IndPags {

        @XmlElement(required = true)
        protected List<IndPag> indPag;

        /**
         * Gets the value of the indPag property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the indPag property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIndPag().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IndPag }
         */
        public List<IndPag> getIndPag() {
            if (indPag == null) {
                indPag = new ArrayList<IndPag>();
            }
            return this.indPag;
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
     *         &lt;element name="indPres" type="{}IndPres" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "indPres"
    })
    public static class IndPress {

        @XmlElement(required = true)
        protected List<IndPres> indPres;

        /**
         * Gets the value of the indPres property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the indPres property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIndPres().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IndPres }
         */
        public List<IndPres> getIndPres() {
            if (indPres == null) {
                indPres = new ArrayList<IndPres>();
            }
            return this.indPres;
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
     *         &lt;element name="mod" type="{}Mod" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "mod"
    })
    public static class Mods {

        @XmlElement(required = true)
        protected List<Mod> mod;

        /**
         * Gets the value of the mod property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mod property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMod().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Mod }
         */
        public List<Mod> getMod() {
            if (mod == null) {
                mod = new ArrayList<Mod>();
            }
            return this.mod;
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
     *         &lt;element name="natOp" type="{}NatOp" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "natOp"
    })
    public static class NatOps {

        @XmlElement(required = true)
        protected List<NatOp> natOp;

        /**
         * Gets the value of the natOp property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the natOp property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNatOp().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NatOp }
         */
        public List<NatOp> getNatOp() {
            if (natOp == null) {
                natOp = new ArrayList<NatOp>();
            }
            return this.natOp;
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
     *         &lt;element name="procEmi" type="{}ProcEmi" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "procEmi"
    })
    public static class ProcEmis {

        @XmlElement(required = true)
        protected List<ProcEmi> procEmi;

        /**
         * Gets the value of the procEmi property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the procEmi property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProcEmi().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProcEmi }
         */
        public List<ProcEmi> getProcEmi() {
            if (procEmi == null) {
                procEmi = new ArrayList<ProcEmi>();
            }
            return this.procEmi;
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
     *         &lt;element name="tpAmb" type="{}TpAmb" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "tpAmb"
    })
    public static class TpAmbs {

        @XmlElement(required = true)
        protected List<TpAmb> tpAmb;

        /**
         * Gets the value of the tpAmb property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tpAmb property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTpAmb().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TpAmb }
         */
        public List<TpAmb> getTpAmb() {
            if (tpAmb == null) {
                tpAmb = new ArrayList<TpAmb>();
            }
            return this.tpAmb;
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
     *         &lt;element name="tpEmis" type="{}TpEmis" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "tpEmis"
    })
    public static class TpEmiss {

        @XmlElement(required = true)
        protected List<TpEmis> tpEmis;

        /**
         * Gets the value of the tpEmis property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tpEmis property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTpEmis().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TpEmis }
         */
        public List<TpEmis> getTpEmis() {
            if (tpEmis == null) {
                tpEmis = new ArrayList<TpEmis>();
            }
            return this.tpEmis;
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
     *         &lt;element name="tpImp" type="{}TpImp" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "tpImp"
    })
    public static class TpImps {

        @XmlElement(required = true)
        protected List<TpImp> tpImp;

        /**
         * Gets the value of the tpImp property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tpImp property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTpImp().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TpImp }
         */
        public List<TpImp> getTpImp() {
            if (tpImp == null) {
                tpImp = new ArrayList<TpImp>();
            }
            return this.tpImp;
        }

    }

}
