//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:23:17 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.retConsSitCTe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Pedido de Consulta da Situação Atual do Conhecimento de Transporte eletrônico
 *
 * <p>Classe Java de TConsSitCTe complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TConsSitCTe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tpAmb" type="{http://www.portalfiscal.inf.br/cte}TAmb"/&gt;
 *         &lt;element name="xServ" type="{http://www.portalfiscal.inf.br/cte}TServ"/&gt;
 *         &lt;element name="chCTe" type="{http://www.portalfiscal.inf.br/cte}TChNFe"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="versao" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TVerConsSitCTe"&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConsSitCTe", namespace = "http://www.portalfiscal.inf.br/cte", propOrder = {
        "tpAmb",
        "xServ",
        "chCTe"
})
public class TConsSitCTe {

    @XmlElement(required = true)
    protected String tpAmb;
    @XmlElement(required = true)
    protected String xServ;
    @XmlElement(required = true)
    protected String chCTe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;

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
     * ObtxE9m o valor da propriedade xServ.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXServ() {
        return xServ;
    }

    /**
     * Define o valor da propriedade xServ.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXServ(String value) {
        this.xServ = value;
    }

    /**
     * ObtxE9m o valor da propriedade chCTe.
     *
     * @return possible object is
     * {@link String }
     */
    public String getChCTe() {
        return chCTe;
    }

    /**
     * Define o valor da propriedade chCTe.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setChCTe(String value) {
        this.chCTe = value;
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

}
