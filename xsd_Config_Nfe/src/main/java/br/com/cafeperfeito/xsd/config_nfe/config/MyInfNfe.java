//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:48:48 PM AMT 
//


package br.com.cafeperfeito.xsd.config_nfe.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de MyInfNfe complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="MyInfNfe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="meusBancos" type="{}MeusBancos"/&gt;
 *         &lt;element name="myConfig" type="{}MyConfig"/&gt;
 *         &lt;element name="ide" type="{}Ide"/&gt;
 *         &lt;element name="emit" type="{}Emit"/&gt;
 *         &lt;element name="dest" type="{}Dest"/&gt;
 *         &lt;element name="transp" type="{}Transp"/&gt;
 *         &lt;element name="sefaz" type="{}Sefaz"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MyInfNfe", propOrder = {
        "meusBancos",
        "myConfig",
        "ide",
        "emit",
        "dest",
        "transp",
        "sefaz"
})
public class MyInfNfe {

    @XmlElement(required = true)
    protected MeusBancos meusBancos;
    @XmlElement(required = true)
    protected MyConfig myConfig;
    @XmlElement(required = true)
    protected Ide ide;
    @XmlElement(required = true)
    protected Emit emit;
    @XmlElement(required = true)
    protected Dest dest;
    @XmlElement(required = true)
    protected Transp transp;
    @XmlElement(required = true)
    protected Sefaz sefaz;

    /**
     * ObtxE9m o valor da propriedade meusBancos.
     *
     * @return possible object is
     * {@link MeusBancos }
     */
    public MeusBancos getMeusBancos() {
        return meusBancos;
    }

    /**
     * Define o valor da propriedade meusBancos.
     *
     * @param value allowed object is
     *              {@link MeusBancos }
     */
    public void setMeusBancos(MeusBancos value) {
        this.meusBancos = value;
    }

    /**
     * ObtxE9m o valor da propriedade myConfig.
     *
     * @return possible object is
     * {@link MyConfig }
     */
    public MyConfig getMyConfig() {
        return myConfig;
    }

    /**
     * Define o valor da propriedade myConfig.
     *
     * @param value allowed object is
     *              {@link MyConfig }
     */
    public void setMyConfig(MyConfig value) {
        this.myConfig = value;
    }

    /**
     * ObtxE9m o valor da propriedade ide.
     *
     * @return possible object is
     * {@link Ide }
     */
    public Ide getIde() {
        return ide;
    }

    /**
     * Define o valor da propriedade ide.
     *
     * @param value allowed object is
     *              {@link Ide }
     */
    public void setIde(Ide value) {
        this.ide = value;
    }

    /**
     * ObtxE9m o valor da propriedade emit.
     *
     * @return possible object is
     * {@link Emit }
     */
    public Emit getEmit() {
        return emit;
    }

    /**
     * Define o valor da propriedade emit.
     *
     * @param value allowed object is
     *              {@link Emit }
     */
    public void setEmit(Emit value) {
        this.emit = value;
    }

    /**
     * ObtxE9m o valor da propriedade dest.
     *
     * @return possible object is
     * {@link Dest }
     */
    public Dest getDest() {
        return dest;
    }

    /**
     * Define o valor da propriedade dest.
     *
     * @param value allowed object is
     *              {@link Dest }
     */
    public void setDest(Dest value) {
        this.dest = value;
    }

    /**
     * ObtxE9m o valor da propriedade transp.
     *
     * @return possible object is
     * {@link Transp }
     */
    public Transp getTransp() {
        return transp;
    }

    /**
     * Define o valor da propriedade transp.
     *
     * @param value allowed object is
     *              {@link Transp }
     */
    public void setTransp(Transp value) {
        this.transp = value;
    }

    /**
     * ObtxE9m o valor da propriedade sefaz.
     *
     * @return possible object is
     * {@link Sefaz }
     */
    public Sefaz getSefaz() {
        return sefaz;
    }

    /**
     * Define o valor da propriedade sefaz.
     *
     * @param value allowed object is
     *              {@link Sefaz }
     */
    public void setSefaz(Sefaz value) {
        this.sefaz = value;
    }

}
