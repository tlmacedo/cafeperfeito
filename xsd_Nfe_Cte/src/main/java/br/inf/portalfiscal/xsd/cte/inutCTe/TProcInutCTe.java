//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:23:07 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.inutCTe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Pedido de inutilzação de númeração de CT-e processado
 *
 * <p>Classe Java de TProcInutCTe complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TProcInutCTe"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inutCTe" type="{http://www.portalfiscal.inf.br/cte}TInutCTe"/&gt;
 *         &lt;element name="retInutCTe" type="{http://www.portalfiscal.inf.br/cte}TRetInutCTe"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="versao" use="required" type="{http://www.portalfiscal.inf.br/cte}TVerInutCTe" /&gt;
 *       &lt;attribute name="ipTransmissor" type="{http://www.portalfiscal.inf.br/cte}TIPv4" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TProcInutCTe", namespace = "http://www.portalfiscal.inf.br/cte", propOrder = {
        "inutCTe",
        "retInutCTe"
})
public class TProcInutCTe {

    @XmlElement(required = true)
    protected TInutCTe inutCTe;
    @XmlElement(required = true)
    protected TRetInutCTe retInutCTe;
    @XmlAttribute(name = "versao", required = true)
    protected String versao;
    @XmlAttribute(name = "ipTransmissor")
    protected String ipTransmissor;

    /**
     * ObtxE9m o valor da propriedade inutCTe.
     *
     * @return possible object is
     * {@link TInutCTe }
     */
    public TInutCTe getInutCTe() {
        return inutCTe;
    }

    /**
     * Define o valor da propriedade inutCTe.
     *
     * @param value allowed object is
     *              {@link TInutCTe }
     */
    public void setInutCTe(TInutCTe value) {
        this.inutCTe = value;
    }

    /**
     * ObtxE9m o valor da propriedade retInutCTe.
     *
     * @return possible object is
     * {@link TRetInutCTe }
     */
    public TRetInutCTe getRetInutCTe() {
        return retInutCTe;
    }

    /**
     * Define o valor da propriedade retInutCTe.
     *
     * @param value allowed object is
     *              {@link TRetInutCTe }
     */
    public void setRetInutCTe(TRetInutCTe value) {
        this.retInutCTe = value;
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
     * ObtxE9m o valor da propriedade ipTransmissor.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIpTransmissor() {
        return ipTransmissor;
    }

    /**
     * Define o valor da propriedade ipTransmissor.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIpTransmissor(String value) {
        this.ipTransmissor = value;
    }

}
