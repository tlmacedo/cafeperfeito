//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:23:05 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.evPrestDesacordo;

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
 *               &lt;enumeration value="Prestação do Serviço em Desacordo"/&gt;
 *               &lt;enumeration value="Prestacao do Servico em Desacordo"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="indDesacordoOper"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="1"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="xObs"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="15"/&gt;
 *               &lt;maxLength value="255"/&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;pattern value="[!-ÿ]{1}[ -ÿ]{0,}[!-ÿ]{1}|[!-ÿ]{1}"/&gt;
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
        "indDesacordoOper",
        "xObs"
})
@XmlRootElement(name = "evPrestDesacordo", namespace = "http://www.portalfiscal.inf.br/cte")
public class EvPrestDesacordo {

    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String descEvento;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String indDesacordoOper;
    @XmlElement(namespace = "http://www.portalfiscal.inf.br/cte", required = true)
    protected String xObs;

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
     * ObtxE9m o valor da propriedade indDesacordoOper.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIndDesacordoOper() {
        return indDesacordoOper;
    }

    /**
     * Define o valor da propriedade indDesacordoOper.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIndDesacordoOper(String value) {
        this.indDesacordoOper = value;
    }

    /**
     * ObtxE9m o valor da propriedade xObs.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXObs() {
        return xObs;
    }

    /**
     * Define o valor da propriedade xObs.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXObs(String value) {
        this.xObs = value;
    }

}
