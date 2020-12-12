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
 * <p>Classe Java de Banco complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="Banco"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *       &lt;attribute name="nome" use="required" type="{}Tstr_120" /&gt;
 *       &lt;attribute name="agencia" use="required" type="{}Tstr_14" /&gt;
 *       &lt;attribute name="conta" use="required" type="{}Tstr_14" /&gt;
 *       &lt;attribute name="padrão" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Banco")
public class Banco {

    @XmlAttribute(name = "id", required = true)
    protected long id;
    @XmlAttribute(name = "nome", required = true)
    protected String nome;
    @XmlAttribute(name = "agencia", required = true)
    protected String agencia;
    @XmlAttribute(name = "conta", required = true)
    protected String conta;
    @XmlAttribute(name = "padr\u00e3o", required = true)
    protected boolean padrão;

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
     * ObtxE9m o valor da propriedade nome.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o valor da propriedade nome.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * ObtxE9m o valor da propriedade agencia.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * Define o valor da propriedade agencia.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAgencia(String value) {
        this.agencia = value;
    }

    /**
     * ObtxE9m o valor da propriedade conta.
     *
     * @return possible object is
     * {@link String }
     */
    public String getConta() {
        return conta;
    }

    /**
     * Define o valor da propriedade conta.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConta(String value) {
        this.conta = value;
    }

    /**
     * ObtxE9m o valor da propriedade padrão.
     */
    public boolean isPadrão() {
        return padrão;
    }

    /**
     * Define o valor da propriedade padrão.
     */
    public void setPadrão(boolean value) {
        this.padrão = value;
    }

}
