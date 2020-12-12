//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:21:33 PM AMT 
//


package br.inf.portalfiscal.xsd.nfe.retEnviNFe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Dados do Veículo
 *
 * <p>Classe Java de TVeiculo complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TVeiculo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="placa"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;pattern value="[A-Z]{2,3}[0-9]{4}|[A-Z]{3,4}[0-9]{3}|[A-Z0-9]{7}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="UF" type="{http://www.portalfiscal.inf.br/nfe}TUf"/&gt;
 *         &lt;element name="RNTC" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/nfe}TString"&gt;
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
@XmlType(name = "TVeiculo", propOrder = {
        "placa",
        "uf",
        "rntc"
})
public class TVeiculo {

    @XmlElement(required = true)
    protected String placa;
    @XmlElement(name = "UF", required = true)
    @XmlSchemaType(name = "string")
    protected TUf uf;
    @XmlElement(name = "RNTC")
    protected String rntc;

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
     * ObtxE9m o valor da propriedade rntc.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRNTC() {
        return rntc;
    }

    /**
     * Define o valor da propriedade rntc.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRNTC(String value) {
        this.rntc = value;
    }

}
