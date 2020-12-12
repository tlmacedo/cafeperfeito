//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:22:46 PM AMT 
//


package br.inf.portalfiscal.xsd.cte.cteModalRodoviario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Dados da Responsável Técnico
 *
 * <p>Classe Java de TRespTec complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TRespTec"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CNPJ" type="{http://www.portalfiscal.inf.br/cte}TCnpj"/&gt;
 *         &lt;element name="xContato"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.portalfiscal.inf.br/cte}TString"&gt;
 *               &lt;maxLength value="60"/&gt;
 *               &lt;minLength value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="email" type="{http://www.portalfiscal.inf.br/cte}TEmail"/&gt;
 *         &lt;element name="fone"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;whiteSpace value="preserve"/&gt;
 *               &lt;pattern value="[0-9]{7,12}"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;sequence minOccurs="0"&gt;
 *           &lt;element name="idCSRT"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                 &lt;pattern value="[0-9]{3}"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="hashCSRT"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}base64Binary"&gt;
 *                 &lt;length value="20"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *         &lt;/sequence&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRespTec", propOrder = {
        "cnpj",
        "xContato",
        "email",
        "fone",
        "idCSRT",
        "hashCSRT"
})
public class TRespTec {

    @XmlElement(name = "CNPJ", required = true)
    protected String cnpj;
    @XmlElement(required = true)
    protected String xContato;
    @XmlElement(required = true)
    protected String email;
    @XmlElement(required = true)
    protected String fone;
    protected String idCSRT;
    protected byte[] hashCSRT;

    /**
     * ObtxE9m o valor da propriedade cnpj.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCNPJ() {
        return cnpj;
    }

    /**
     * Define o valor da propriedade cnpj.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCNPJ(String value) {
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

    /**
     * ObtxE9m o valor da propriedade idCSRT.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIdCSRT() {
        return idCSRT;
    }

    /**
     * Define o valor da propriedade idCSRT.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdCSRT(String value) {
        this.idCSRT = value;
    }

    /**
     * ObtxE9m o valor da propriedade hashCSRT.
     *
     * @return possible object is
     * byte[]
     */
    public byte[] getHashCSRT() {
        return hashCSRT;
    }

    /**
     * Define o valor da propriedade hashCSRT.
     *
     * @param value allowed object is
     *              byte[]
     */
    public void setHashCSRT(byte[] value) {
        this.hashCSRT = value;
    }

}
