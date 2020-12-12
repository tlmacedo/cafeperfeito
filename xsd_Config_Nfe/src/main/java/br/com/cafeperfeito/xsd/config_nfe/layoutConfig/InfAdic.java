//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:48:49 PM AMT 
//


package br.com.cafeperfeito.xsd.config_nfe.layoutConfig;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de InfAdic complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="InfAdic"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="campo" use="required" type="{}Tstr_14" /&gt;
 *       &lt;attribute name="texto" use="required" type="{}Tstr_120" /&gt;
 *       &lt;attribute name="variavel" type="{}Tstr_60" /&gt;
 *       &lt;attribute name="quebraPag" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfAdic")
public class InfAdic {

    @XmlAttribute(name = "campo", required = true)
    protected String campo;
    @XmlAttribute(name = "texto", required = true)
    protected String texto;
    @XmlAttribute(name = "variavel")
    protected String variavel;
    @XmlAttribute(name = "quebraPag", required = true)
    protected boolean quebraPag;

    /**
     * ObtxE9m o valor da propriedade campo.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCampo() {
        return campo;
    }

    /**
     * Define o valor da propriedade campo.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCampo(String value) {
        this.campo = value;
    }

    /**
     * ObtxE9m o valor da propriedade texto.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Define o valor da propriedade texto.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTexto(String value) {
        this.texto = value;
    }

    /**
     * ObtxE9m o valor da propriedade variavel.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVariavel() {
        return variavel;
    }

    /**
     * Define o valor da propriedade variavel.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVariavel(String value) {
        this.variavel = value;
    }

    /**
     * ObtxE9m o valor da propriedade quebraPag.
     */
    public boolean isQuebraPag() {
        return quebraPag;
    }

    /**
     * Define o valor da propriedade quebraPag.
     */
    public void setQuebraPag(boolean value) {
        this.quebraPag = value;
    }

}
