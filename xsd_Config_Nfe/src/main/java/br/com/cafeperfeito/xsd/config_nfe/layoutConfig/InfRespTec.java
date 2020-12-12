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
 * <p>Classe Java de InfRespTec complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="InfRespTec"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="cnpj" use="required" type="{}Tcnpj" /&gt;
 *       &lt;attribute name="xContato" use="required" type="{}Tstr_60" /&gt;
 *       &lt;attribute name="email" use="required" type="{}Tstr_60" /&gt;
 *       &lt;attribute name="fone" use="required" type="{}Tstr_14" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfRespTec")
public class InfRespTec {

    @XmlAttribute(name = "id", required = true)
    protected long id;
    @XmlAttribute(name = "cnpj", required = true)
    protected String cnpj;
    @XmlAttribute(name = "xContato", required = true)
    protected String xContato;
    @XmlAttribute(name = "email", required = true)
    protected String email;
    @XmlAttribute(name = "fone", required = true)
    protected String fone;

    /**
     * ObtxE9m o valor da propriedade id.
     */
    public long getId() {
        return id;
    }

    /**
     * Define o valor da propriedade id.
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * ObtxE9m o valor da propriedade cnpj.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o valor da propriedade cnpj.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCnpj(String value) {
        this.cnpj = value;
    }

    /**
     * ObtxE9m o valor da propriedade xContato.
     *
     * @return possible object is
     * {@link String }
     */
    public String getXContato() {
        return xContato;
    }

    /**
     * Define o valor da propriedade xContato.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setXContato(String value) {
        this.xContato = value;
    }

    /**
     * ObtxE9m o valor da propriedade email.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o valor da propriedade email.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * ObtxE9m o valor da propriedade fone.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFone() {
        return fone;
    }

    /**
     * Define o valor da propriedade fone.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFone(String value) {
        this.fone = value;
    }

}
