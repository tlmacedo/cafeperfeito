//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.14 xe0s 07:23:44 PM AMT 
//


package br.com.cafeperfeito.xsd.config_sis.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Nota Fiscal Eletrônica
 *
 * <p>Classe Java de TConfig complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="TConfig"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="myLocale" type="{}Tstr_120"/&gt;
 *         &lt;element name="timeOut" type="{}Tint_2"/&gt;
 *         &lt;element name="versao" type="{}Tstr_14"/&gt;
 *         &lt;element name="sis"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="connect_DB"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="dbDriver" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbDriverConn" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbHost" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbPorta" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbDatabase" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbAddConfig" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbUrl" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbUser" type="{}Tstr_120"/&gt;
 *                             &lt;element name="dbPass" type="{}Tstr_120"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="FilesType"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="token" type="{}Tstr_14"/&gt;
 *                             &lt;element name="imagem" type="{}Tstr_14"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="maskCaracter"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="digit" type="{}Tstr_120"/&gt;
 *                             &lt;element name="upper" type="{}Tstr_120"/&gt;
 *                             &lt;element name="lower" type="{}Tstr_120"/&gt;
 *                             &lt;element name="digitoAndText" type="{}Tstr_120"/&gt;
 *                             &lt;element name="interrogacao" type="{}Tstr_120"/&gt;
 *                             &lt;element name="asterisco" type="{}Tstr_120"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="infLoja"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="id" type="{}Tint_3"/&gt;
 *                   &lt;element name="cnpj" type="{}Tstr_14"/&gt;
 *                   &lt;element name="titulo" type="{}Tstr_120"/&gt;
 *                   &lt;element name="copyright" type="{}Tstr_120"/&gt;
 *                   &lt;element name="uf" type="{}Tstr_120"/&gt;
 *                   &lt;element name="cUF" type="{}Tint_2"/&gt;
 *                   &lt;element name="municipio" type="{}Tstr_120"/&gt;
 *                   &lt;element name="ddd" type="{}Tint_2"/&gt;
 *                   &lt;element name="descMax" type="{}TDec_3_2"/&gt;
 *                   &lt;element name="banco" type="{}Tstr_120"/&gt;
 *                   &lt;element name="agencia" type="{}Tstr_120"/&gt;
 *                   &lt;element name="contaCorrente" type="{}Tstr_120"/&gt;
 *                   &lt;element name="imageDefaultProduto"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="width" type="{}Tint_3"/&gt;
 *                             &lt;element name="height" type="{}Tint_3"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="personalizacao"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="tema" type="{}Tstr_120"/&gt;
 *                   &lt;element name="style-sheets" type="{}Tstr_120"/&gt;
 *                   &lt;element name="style-sheets-min" type="{}Tstr_120"/&gt;
 *                   &lt;element name="splash-imagens"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="image" type="{}Tstr_120" maxOccurs="unbounded"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="paths"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="versaoDanfeNFe" type="{}Tstr"/&gt;
 *                   &lt;element name="versaoRecibo" type="{}Tstr"/&gt;
 *                   &lt;element name="pathDanfeNFe" type="{}Tstr"/&gt;
 *                   &lt;element name="pathRecibo" type="{}Tstr"/&gt;
 *                   &lt;element name="pathSalvarArquivo" type="{}Tstr"/&gt;
 *                   &lt;element name="pathIconeSistema" type="{}Tstr"/&gt;
 *                   &lt;element name="pathStyleSheets" type="{}Tstr"/&gt;
 *                   &lt;element name="pathFXML" type="{}Tstr"/&gt;
 *                   &lt;element name="pathDownloadImage" type="{}Tstr"/&gt;
 *                   &lt;element name="pathNFeSaveXmlOut" type="{}Tstr"/&gt;
 *                   &lt;element name="pathNFeSaveXmlIn" type="{}Tstr"/&gt;
 *                   &lt;element name="pathCTeSaveXmlOut" type="{}Tstr"/&gt;
 *                   &lt;element name="pathCTeSaveXmlIn" type="{}Tstr"/&gt;
 *                   &lt;element name="pathNFeGetXmlOut" type="{}Tstr"/&gt;
 *                   &lt;element name="pathNFeGetXmlIn" type="{}Tstr"/&gt;
 *                   &lt;element name="pathCTeGetXmlOut" type="{}Tstr"/&gt;
 *                   &lt;element name="pathCTeGetXmlIn" type="{}Tstr"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="fxml"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="login"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="principal"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="iconeAtivo" type="{}Tstr"/&gt;
 *                             &lt;element name="iconeInativo" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cadastroProduto"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cadastroEmpresa"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="entradaProduto"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="saidaProduto"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="contasAReceber"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="pedidoNFe"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="recebimento"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="contasAPagar"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="fxml" type="{}Tstr"/&gt;
 *                             &lt;element name="titulo" type="{}Tstr"/&gt;
 *                             &lt;element name="icone" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ws"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="barcode"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="url" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="cosmos"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="url" type="{}Tstr"/&gt;
 *                             &lt;element name="consulta_ncm" type="{}Tstr"/&gt;
 *                             &lt;element name="consulta_gtins" type="{}Tstr"/&gt;
 *                             &lt;element name="consulta_gpcs" type="{}Tstr"/&gt;
 *                             &lt;element name="token" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="receitaws"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="url" type="{}Tstr"/&gt;
 *                             &lt;element name="token" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="webmania"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="url" type="{}Tstr"/&gt;
 *                             &lt;element name="appkey" type="{}Tstr"/&gt;
 *                             &lt;element name="appsecret" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="postmon"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="url" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="portabilidadecelular"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="pass" type="{}Tstr"/&gt;
 *                             &lt;element name="user" type="{}Tstr"/&gt;
 *                             &lt;element name="url" type="{}Tstr"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="statusBars" type="{}StatusBars"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConfig", propOrder = {
        "myLocale",
        "timeOut",
        "versao",
        "sis",
        "infLoja",
        "personalizacao",
        "paths",
        "fxml",
        "ws",
        "statusBars"
})
public class TConfig {

    @XmlElement(required = true)
    protected String myLocale;
    @XmlElement(required = true)
    protected BigInteger timeOut;
    @XmlElement(required = true)
    protected String versao;
    @XmlElement(required = true)
    protected TConfig.Sis sis;
    @XmlElement(required = true)
    protected TConfig.InfLoja infLoja;
    @XmlElement(required = true)
    protected TConfig.Personalizacao personalizacao;
    @XmlElement(required = true)
    protected TConfig.Paths paths;
    @XmlElement(required = true)
    protected TConfig.Fxml fxml;
    @XmlElement(required = true)
    protected TConfig.Ws ws;
    @XmlElement(required = true)
    protected StatusBars statusBars;

    /**
     * ObtxE9m o valor da propriedade myLocale.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMyLocale() {
        return myLocale;
    }

    /**
     * Define o valor da propriedade myLocale.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMyLocale(String value) {
        this.myLocale = value;
    }

    /**
     * ObtxE9m o valor da propriedade timeOut.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getTimeOut() {
        return timeOut;
    }

    /**
     * Define o valor da propriedade timeOut.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setTimeOut(BigInteger value) {
        this.timeOut = value;
    }

    /**
     * ObtxE9m o valor da propriedade versao.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVersao() {
        return versao;
    }

    /**
     * Define o valor da propriedade versao.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVersao(String value) {
        this.versao = value;
    }

    /**
     * ObtxE9m o valor da propriedade sis.
     *
     * @return
     *     possible object is
     *     {@link TConfig.Sis }
     *
     */
    public TConfig.Sis getSis() {
        return sis;
    }

    /**
     * Define o valor da propriedade sis.
     *
     * @param value
     *     allowed object is
     *     {@link TConfig.Sis }
     *
     */
    public void setSis(TConfig.Sis value) {
        this.sis = value;
    }

    /**
     * ObtxE9m o valor da propriedade infLoja.
     *
     * @return
     *     possible object is
     *     {@link TConfig.InfLoja }
     *
     */
    public TConfig.InfLoja getInfLoja() {
        return infLoja;
    }

    /**
     * Define o valor da propriedade infLoja.
     *
     * @param value
     *     allowed object is
     *     {@link TConfig.InfLoja }
     *
     */
    public void setInfLoja(TConfig.InfLoja value) {
        this.infLoja = value;
    }

    /**
     * ObtxE9m o valor da propriedade personalizacao.
     *
     * @return
     *     possible object is
     *     {@link TConfig.Personalizacao }
     *
     */
    public TConfig.Personalizacao getPersonalizacao() {
        return personalizacao;
    }

    /**
     * Define o valor da propriedade personalizacao.
     *
     * @param value
     *     allowed object is
     *     {@link TConfig.Personalizacao }
     *
     */
    public void setPersonalizacao(TConfig.Personalizacao value) {
        this.personalizacao = value;
    }

    /**
     * ObtxE9m o valor da propriedade paths.
     *
     * @return
     *     possible object is
     *     {@link TConfig.Paths }
     *
     */
    public TConfig.Paths getPaths() {
        return paths;
    }

    /**
     * Define o valor da propriedade paths.
     *
     * @param value
     *     allowed object is
     *     {@link TConfig.Paths }
     *
     */
    public void setPaths(TConfig.Paths value) {
        this.paths = value;
    }

    /**
     * ObtxE9m o valor da propriedade fxml.
     *
     * @return
     *     possible object is
     *     {@link TConfig.Fxml }
     *
     */
    public TConfig.Fxml getFxml() {
        return fxml;
    }

    /**
     * Define o valor da propriedade fxml.
     *
     * @param value
     *     allowed object is
     *     {@link TConfig.Fxml }
     *
     */
    public void setFxml(TConfig.Fxml value) {
        this.fxml = value;
    }

    /**
     * ObtxE9m o valor da propriedade ws.
     *
     * @return
     *     possible object is
     *     {@link TConfig.Ws }
     *
     */
    public TConfig.Ws getWs() {
        return ws;
    }

    /**
     * Define o valor da propriedade ws.
     *
     * @param value
     *     allowed object is
     *     {@link TConfig.Ws }
     *
     */
    public void setWs(TConfig.Ws value) {
        this.ws = value;
    }

    /**
     * ObtxE9m o valor da propriedade statusBars.
     *
     * @return
     *     possible object is
     *     {@link StatusBars }
     *
     */
    public StatusBars getStatusBars() {
        return statusBars;
    }

    /**
     * Define o valor da propriedade statusBars.
     *
     * @param value
     *     allowed object is
     *     {@link StatusBars }
     *
     */
    public void setStatusBars(StatusBars value) {
        this.statusBars = value;
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
     *         &lt;element name="login"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="principal"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="iconeAtivo" type="{}Tstr"/&gt;
     *                   &lt;element name="iconeInativo" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cadastroProduto"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cadastroEmpresa"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="entradaProduto"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="saidaProduto"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="contasAReceber"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="pedidoNFe"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="recebimento"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="contasAPagar"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="fxml" type="{}Tstr"/&gt;
     *                   &lt;element name="titulo" type="{}Tstr"/&gt;
     *                   &lt;element name="icone" type="{}Tstr"/&gt;
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
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "login",
            "principal",
            "cadastroProduto",
            "cadastroEmpresa",
            "entradaProduto",
            "saidaProduto",
            "contasAReceber",
            "pedidoNFe",
            "recebimento",
            "contasAPagar"
    })
    public static class Fxml {

        @XmlElement(required = true)
        protected TConfig.Fxml.Login login;
        @XmlElement(required = true)
        protected TConfig.Fxml.Principal principal;
        @XmlElement(required = true)
        protected TConfig.Fxml.CadastroProduto cadastroProduto;
        @XmlElement(required = true)
        protected TConfig.Fxml.CadastroEmpresa cadastroEmpresa;
        @XmlElement(required = true)
        protected TConfig.Fxml.EntradaProduto entradaProduto;
        @XmlElement(required = true)
        protected TConfig.Fxml.SaidaProduto saidaProduto;
        @XmlElement(required = true)
        protected TConfig.Fxml.ContasAReceber contasAReceber;
        @XmlElement(required = true)
        protected TConfig.Fxml.PedidoNFe pedidoNFe;
        @XmlElement(required = true)
        protected TConfig.Fxml.Recebimento recebimento;
        @XmlElement(required = true)
        protected TConfig.Fxml.ContasAPagar contasAPagar;

        /**
         * ObtxE9m o valor da propriedade login.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.Login }
         *
         */
        public TConfig.Fxml.Login getLogin() {
            return login;
        }

        /**
         * Define o valor da propriedade login.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.Login }
         *
         */
        public void setLogin(TConfig.Fxml.Login value) {
            this.login = value;
        }

        /**
         * ObtxE9m o valor da propriedade principal.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.Principal }
         *
         */
        public TConfig.Fxml.Principal getPrincipal() {
            return principal;
        }

        /**
         * Define o valor da propriedade principal.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.Principal }
         *
         */
        public void setPrincipal(TConfig.Fxml.Principal value) {
            this.principal = value;
        }

        /**
         * ObtxE9m o valor da propriedade cadastroProduto.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.CadastroProduto }
         *
         */
        public TConfig.Fxml.CadastroProduto getCadastroProduto() {
            return cadastroProduto;
        }

        /**
         * Define o valor da propriedade cadastroProduto.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.CadastroProduto }
         *
         */
        public void setCadastroProduto(TConfig.Fxml.CadastroProduto value) {
            this.cadastroProduto = value;
        }

        /**
         * ObtxE9m o valor da propriedade cadastroEmpresa.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.CadastroEmpresa }
         *
         */
        public TConfig.Fxml.CadastroEmpresa getCadastroEmpresa() {
            return cadastroEmpresa;
        }

        /**
         * Define o valor da propriedade cadastroEmpresa.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.CadastroEmpresa }
         *
         */
        public void setCadastroEmpresa(TConfig.Fxml.CadastroEmpresa value) {
            this.cadastroEmpresa = value;
        }

        /**
         * ObtxE9m o valor da propriedade entradaProduto.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.EntradaProduto }
         *
         */
        public TConfig.Fxml.EntradaProduto getEntradaProduto() {
            return entradaProduto;
        }

        /**
         * Define o valor da propriedade entradaProduto.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.EntradaProduto }
         *
         */
        public void setEntradaProduto(TConfig.Fxml.EntradaProduto value) {
            this.entradaProduto = value;
        }

        /**
         * ObtxE9m o valor da propriedade saidaProduto.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.SaidaProduto }
         *
         */
        public TConfig.Fxml.SaidaProduto getSaidaProduto() {
            return saidaProduto;
        }

        /**
         * Define o valor da propriedade saidaProduto.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.SaidaProduto }
         *
         */
        public void setSaidaProduto(TConfig.Fxml.SaidaProduto value) {
            this.saidaProduto = value;
        }

        /**
         * ObtxE9m o valor da propriedade contasAReceber.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.ContasAReceber }
         *
         */
        public TConfig.Fxml.ContasAReceber getContasAReceber() {
            return contasAReceber;
        }

        /**
         * Define o valor da propriedade contasAReceber.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.ContasAReceber }
         *
         */
        public void setContasAReceber(TConfig.Fxml.ContasAReceber value) {
            this.contasAReceber = value;
        }

        /**
         * ObtxE9m o valor da propriedade pedidoNFe.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.PedidoNFe }
         *
         */
        public TConfig.Fxml.PedidoNFe getPedidoNFe() {
            return pedidoNFe;
        }

        /**
         * Define o valor da propriedade pedidoNFe.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.PedidoNFe }
         *
         */
        public void setPedidoNFe(TConfig.Fxml.PedidoNFe value) {
            this.pedidoNFe = value;
        }

        /**
         * ObtxE9m o valor da propriedade recebimento.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.Recebimento }
         *
         */
        public TConfig.Fxml.Recebimento getRecebimento() {
            return recebimento;
        }

        /**
         * Define o valor da propriedade recebimento.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.Recebimento }
         *
         */
        public void setRecebimento(TConfig.Fxml.Recebimento value) {
            this.recebimento = value;
        }

        /**
         * ObtxE9m o valor da propriedade contasAPagar.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Fxml.ContasAPagar }
         *
         */
        public TConfig.Fxml.ContasAPagar getContasAPagar() {
            return contasAPagar;
        }

        /**
         * Define o valor da propriedade contasAPagar.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Fxml.ContasAPagar }
         *
         */
        public void setContasAPagar(TConfig.Fxml.ContasAPagar value) {
            this.contasAPagar = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class CadastroEmpresa {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class CadastroProduto {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class ContasAPagar {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class ContasAReceber {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class EntradaProduto {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class Login {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class PedidoNFe {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="iconeAtivo" type="{}Tstr"/&gt;
         *         &lt;element name="iconeInativo" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "iconeAtivo",
                "iconeInativo"
        })
        public static class Principal {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String iconeAtivo;
            @XmlElement(required = true)
            protected String iconeInativo;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade iconeAtivo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIconeAtivo() {
                return iconeAtivo;
            }

            /**
             * Define o valor da propriedade iconeAtivo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIconeAtivo(String value) {
                this.iconeAtivo = value;
            }

            /**
             * ObtxE9m o valor da propriedade iconeInativo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIconeInativo() {
                return iconeInativo;
            }

            /**
             * Define o valor da propriedade iconeInativo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIconeInativo(String value) {
                this.iconeInativo = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class Recebimento {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
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
         *         &lt;element name="fxml" type="{}Tstr"/&gt;
         *         &lt;element name="titulo" type="{}Tstr"/&gt;
         *         &lt;element name="icone" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "fxml",
                "titulo",
                "icone"
        })
        public static class SaidaProduto {

            @XmlElement(required = true)
            protected String fxml;
            @XmlElement(required = true)
            protected String titulo;
            @XmlElement(required = true)
            protected String icone;

            /**
             * ObtxE9m o valor da propriedade fxml.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getFxml() {
                return fxml;
            }

            /**
             * Define o valor da propriedade fxml.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setFxml(String value) {
                this.fxml = value;
            }

            /**
             * ObtxE9m o valor da propriedade titulo.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getTitulo() {
                return titulo;
            }

            /**
             * Define o valor da propriedade titulo.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setTitulo(String value) {
                this.titulo = value;
            }

            /**
             * ObtxE9m o valor da propriedade icone.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getIcone() {
                return icone;
            }

            /**
             * Define o valor da propriedade icone.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setIcone(String value) {
                this.icone = value;
            }

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
     *         &lt;element name="id" type="{}Tint_3"/&gt;
     *         &lt;element name="cnpj" type="{}Tstr_14"/&gt;
     *         &lt;element name="titulo" type="{}Tstr_120"/&gt;
     *         &lt;element name="copyright" type="{}Tstr_120"/&gt;
     *         &lt;element name="uf" type="{}Tstr_120"/&gt;
     *         &lt;element name="cUF" type="{}Tint_2"/&gt;
     *         &lt;element name="municipio" type="{}Tstr_120"/&gt;
     *         &lt;element name="ddd" type="{}Tint_2"/&gt;
     *         &lt;element name="descMax" type="{}TDec_3_2"/&gt;
     *         &lt;element name="banco" type="{}Tstr_120"/&gt;
     *         &lt;element name="agencia" type="{}Tstr_120"/&gt;
     *         &lt;element name="contaCorrente" type="{}Tstr_120"/&gt;
     *         &lt;element name="imageDefaultProduto"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="width" type="{}Tint_3"/&gt;
     *                   &lt;element name="height" type="{}Tint_3"/&gt;
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
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "id",
            "cnpj",
            "titulo",
            "copyright",
            "uf",
            "cuf",
            "municipio",
            "ddd",
            "descMax",
            "banco",
            "agencia",
            "contaCorrente",
            "imageDefaultProduto"
    })
    public static class InfLoja {

        @XmlElement(required = true)
        protected BigInteger id;
        @XmlElement(required = true)
        protected String cnpj;
        @XmlElement(required = true)
        protected String titulo;
        @XmlElement(required = true)
        protected String copyright;
        @XmlElement(required = true)
        protected String uf;
        @XmlElement(name = "cUF", required = true)
        protected BigInteger cuf;
        @XmlElement(required = true)
        protected String municipio;
        @XmlElement(required = true)
        protected BigInteger ddd;
        @XmlElement(required = true)
        protected BigDecimal descMax;
        @XmlElement(required = true)
        protected String banco;
        @XmlElement(required = true)
        protected String agencia;
        @XmlElement(required = true)
        protected String contaCorrente;
        @XmlElement(required = true)
        protected TConfig.InfLoja.ImageDefaultProduto imageDefaultProduto;

        /**
         * ObtxE9m o valor da propriedade id.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getId() {
            return id;
        }

        /**
         * Define o valor da propriedade id.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setId(BigInteger value) {
            this.id = value;
        }

        /**
         * ObtxE9m o valor da propriedade cnpj.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCnpj() {
            return cnpj;
        }

        /**
         * Define o valor da propriedade cnpj.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCnpj(String value) {
            this.cnpj = value;
        }

        /**
         * ObtxE9m o valor da propriedade titulo.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTitulo() {
            return titulo;
        }

        /**
         * Define o valor da propriedade titulo.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTitulo(String value) {
            this.titulo = value;
        }

        /**
         * ObtxE9m o valor da propriedade copyright.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getCopyright() {
            return copyright;
        }

        /**
         * Define o valor da propriedade copyright.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setCopyright(String value) {
            this.copyright = value;
        }

        /**
         * ObtxE9m o valor da propriedade uf.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getUf() {
            return uf;
        }

        /**
         * Define o valor da propriedade uf.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setUf(String value) {
            this.uf = value;
        }

        /**
         * ObtxE9m o valor da propriedade cuf.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getCUF() {
            return cuf;
        }

        /**
         * Define o valor da propriedade cuf.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setCUF(BigInteger value) {
            this.cuf = value;
        }

        /**
         * ObtxE9m o valor da propriedade municipio.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getMunicipio() {
            return municipio;
        }

        /**
         * Define o valor da propriedade municipio.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setMunicipio(String value) {
            this.municipio = value;
        }

        /**
         * ObtxE9m o valor da propriedade ddd.
         *
         * @return
         *     possible object is
         *     {@link BigInteger }
         *
         */
        public BigInteger getDdd() {
            return ddd;
        }

        /**
         * Define o valor da propriedade ddd.
         *
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *
         */
        public void setDdd(BigInteger value) {
            this.ddd = value;
        }

        /**
         * ObtxE9m o valor da propriedade descMax.
         *
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *
         */
        public BigDecimal getDescMax() {
            return descMax;
        }

        /**
         * Define o valor da propriedade descMax.
         *
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *
         */
        public void setDescMax(BigDecimal value) {
            this.descMax = value;
        }

        /**
         * ObtxE9m o valor da propriedade banco.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getBanco() {
            return banco;
        }

        /**
         * Define o valor da propriedade banco.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setBanco(String value) {
            this.banco = value;
        }

        /**
         * ObtxE9m o valor da propriedade agencia.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getAgencia() {
            return agencia;
        }

        /**
         * Define o valor da propriedade agencia.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setAgencia(String value) {
            this.agencia = value;
        }

        /**
         * ObtxE9m o valor da propriedade contaCorrente.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getContaCorrente() {
            return contaCorrente;
        }

        /**
         * Define o valor da propriedade contaCorrente.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setContaCorrente(String value) {
            this.contaCorrente = value;
        }

        /**
         * ObtxE9m o valor da propriedade imageDefaultProduto.
         *
         * @return
         *     possible object is
         *     {@link TConfig.InfLoja.ImageDefaultProduto }
         *
         */
        public TConfig.InfLoja.ImageDefaultProduto getImageDefaultProduto() {
            return imageDefaultProduto;
        }

        /**
         * Define o valor da propriedade imageDefaultProduto.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.InfLoja.ImageDefaultProduto }
         *
         */
        public void setImageDefaultProduto(TConfig.InfLoja.ImageDefaultProduto value) {
            this.imageDefaultProduto = value;
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
         *         &lt;element name="width" type="{}Tint_3"/&gt;
         *         &lt;element name="height" type="{}Tint_3"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "width",
                "height"
        })
        public static class ImageDefaultProduto {

            @XmlElement(required = true)
            protected BigInteger width;
            @XmlElement(required = true)
            protected BigInteger height;

            /**
             * ObtxE9m o valor da propriedade width.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getWidth() {
                return width;
            }

            /**
             * Define o valor da propriedade width.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setWidth(BigInteger value) {
                this.width = value;
            }

            /**
             * ObtxE9m o valor da propriedade height.
             *
             * @return
             *     possible object is
             *     {@link BigInteger }
             *
             */
            public BigInteger getHeight() {
                return height;
            }

            /**
             * Define o valor da propriedade height.
             *
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *
             */
            public void setHeight(BigInteger value) {
                this.height = value;
            }

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
     *         &lt;element name="versaoDanfeNFe" type="{}Tstr"/&gt;
     *         &lt;element name="versaoRecibo" type="{}Tstr"/&gt;
     *         &lt;element name="pathDanfeNFe" type="{}Tstr"/&gt;
     *         &lt;element name="pathRecibo" type="{}Tstr"/&gt;
     *         &lt;element name="pathSalvarArquivo" type="{}Tstr"/&gt;
     *         &lt;element name="pathIconeSistema" type="{}Tstr"/&gt;
     *         &lt;element name="pathStyleSheets" type="{}Tstr"/&gt;
     *         &lt;element name="pathFXML" type="{}Tstr"/&gt;
     *         &lt;element name="pathDownloadImage" type="{}Tstr"/&gt;
     *         &lt;element name="pathNFeSaveXmlOut" type="{}Tstr"/&gt;
     *         &lt;element name="pathNFeSaveXmlIn" type="{}Tstr"/&gt;
     *         &lt;element name="pathCTeSaveXmlOut" type="{}Tstr"/&gt;
     *         &lt;element name="pathCTeSaveXmlIn" type="{}Tstr"/&gt;
     *         &lt;element name="pathNFeGetXmlOut" type="{}Tstr"/&gt;
     *         &lt;element name="pathNFeGetXmlIn" type="{}Tstr"/&gt;
     *         &lt;element name="pathCTeGetXmlOut" type="{}Tstr"/&gt;
     *         &lt;element name="pathCTeGetXmlIn" type="{}Tstr"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "versaoDanfeNFe",
            "versaoRecibo",
            "pathDanfeNFe",
            "pathRecibo",
            "pathSalvarArquivo",
            "pathIconeSistema",
            "pathStyleSheets",
            "pathFXML",
            "pathDownloadImage",
            "pathNFeSaveXmlOut",
            "pathNFeSaveXmlIn",
            "pathCTeSaveXmlOut",
            "pathCTeSaveXmlIn",
            "pathNFeGetXmlOut",
            "pathNFeGetXmlIn",
            "pathCTeGetXmlOut",
            "pathCTeGetXmlIn"
    })
    public static class Paths {

        @XmlElement(required = true)
        protected String versaoDanfeNFe;
        @XmlElement(required = true)
        protected String versaoRecibo;
        @XmlElement(required = true)
        protected String pathDanfeNFe;
        @XmlElement(required = true)
        protected String pathRecibo;
        @XmlElement(required = true)
        protected String pathSalvarArquivo;
        @XmlElement(required = true)
        protected String pathIconeSistema;
        @XmlElement(required = true)
        protected String pathStyleSheets;
        @XmlElement(required = true)
        protected String pathFXML;
        @XmlElement(required = true)
        protected String pathDownloadImage;
        @XmlElement(required = true)
        protected String pathNFeSaveXmlOut;
        @XmlElement(required = true)
        protected String pathNFeSaveXmlIn;
        @XmlElement(required = true)
        protected String pathCTeSaveXmlOut;
        @XmlElement(required = true)
        protected String pathCTeSaveXmlIn;
        @XmlElement(required = true)
        protected String pathNFeGetXmlOut;
        @XmlElement(required = true)
        protected String pathNFeGetXmlIn;
        @XmlElement(required = true)
        protected String pathCTeGetXmlOut;
        @XmlElement(required = true)
        protected String pathCTeGetXmlIn;

        /**
         * ObtxE9m o valor da propriedade versaoDanfeNFe.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVersaoDanfeNFe() {
            return versaoDanfeNFe;
        }

        /**
         * Define o valor da propriedade versaoDanfeNFe.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVersaoDanfeNFe(String value) {
            this.versaoDanfeNFe = value;
        }

        /**
         * ObtxE9m o valor da propriedade versaoRecibo.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getVersaoRecibo() {
            return versaoRecibo;
        }

        /**
         * Define o valor da propriedade versaoRecibo.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setVersaoRecibo(String value) {
            this.versaoRecibo = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathDanfeNFe.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathDanfeNFe() {
            return pathDanfeNFe;
        }

        /**
         * Define o valor da propriedade pathDanfeNFe.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathDanfeNFe(String value) {
            this.pathDanfeNFe = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathRecibo.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathRecibo() {
            return pathRecibo;
        }

        /**
         * Define o valor da propriedade pathRecibo.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathRecibo(String value) {
            this.pathRecibo = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathSalvarArquivo.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathSalvarArquivo() {
            return pathSalvarArquivo;
        }

        /**
         * Define o valor da propriedade pathSalvarArquivo.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathSalvarArquivo(String value) {
            this.pathSalvarArquivo = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathIconeSistema.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathIconeSistema() {
            return pathIconeSistema;
        }

        /**
         * Define o valor da propriedade pathIconeSistema.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathIconeSistema(String value) {
            this.pathIconeSistema = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathStyleSheets.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathStyleSheets() {
            return pathStyleSheets;
        }

        /**
         * Define o valor da propriedade pathStyleSheets.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathStyleSheets(String value) {
            this.pathStyleSheets = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathFXML.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathFXML() {
            return pathFXML;
        }

        /**
         * Define o valor da propriedade pathFXML.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathFXML(String value) {
            this.pathFXML = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathDownloadImage.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathDownloadImage() {
            return pathDownloadImage;
        }

        /**
         * Define o valor da propriedade pathDownloadImage.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathDownloadImage(String value) {
            this.pathDownloadImage = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathNFeSaveXmlOut.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathNFeSaveXmlOut() {
            return pathNFeSaveXmlOut;
        }

        /**
         * Define o valor da propriedade pathNFeSaveXmlOut.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathNFeSaveXmlOut(String value) {
            this.pathNFeSaveXmlOut = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathNFeSaveXmlIn.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathNFeSaveXmlIn() {
            return pathNFeSaveXmlIn;
        }

        /**
         * Define o valor da propriedade pathNFeSaveXmlIn.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathNFeSaveXmlIn(String value) {
            this.pathNFeSaveXmlIn = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathCTeSaveXmlOut.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathCTeSaveXmlOut() {
            return pathCTeSaveXmlOut;
        }

        /**
         * Define o valor da propriedade pathCTeSaveXmlOut.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathCTeSaveXmlOut(String value) {
            this.pathCTeSaveXmlOut = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathCTeSaveXmlIn.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathCTeSaveXmlIn() {
            return pathCTeSaveXmlIn;
        }

        /**
         * Define o valor da propriedade pathCTeSaveXmlIn.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathCTeSaveXmlIn(String value) {
            this.pathCTeSaveXmlIn = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathNFeGetXmlOut.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathNFeGetXmlOut() {
            return pathNFeGetXmlOut;
        }

        /**
         * Define o valor da propriedade pathNFeGetXmlOut.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathNFeGetXmlOut(String value) {
            this.pathNFeGetXmlOut = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathNFeGetXmlIn.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathNFeGetXmlIn() {
            return pathNFeGetXmlIn;
        }

        /**
         * Define o valor da propriedade pathNFeGetXmlIn.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathNFeGetXmlIn(String value) {
            this.pathNFeGetXmlIn = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathCTeGetXmlOut.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathCTeGetXmlOut() {
            return pathCTeGetXmlOut;
        }

        /**
         * Define o valor da propriedade pathCTeGetXmlOut.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathCTeGetXmlOut(String value) {
            this.pathCTeGetXmlOut = value;
        }

        /**
         * ObtxE9m o valor da propriedade pathCTeGetXmlIn.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getPathCTeGetXmlIn() {
            return pathCTeGetXmlIn;
        }

        /**
         * Define o valor da propriedade pathCTeGetXmlIn.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setPathCTeGetXmlIn(String value) {
            this.pathCTeGetXmlIn = value;
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
     *         &lt;element name="tema" type="{}Tstr_120"/&gt;
     *         &lt;element name="style-sheets" type="{}Tstr_120"/&gt;
     *         &lt;element name="style-sheets-min" type="{}Tstr_120"/&gt;
     *         &lt;element name="splash-imagens"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="image" type="{}Tstr_120" maxOccurs="unbounded"/&gt;
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
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "tema",
            "styleSheets",
            "styleSheetsMin",
            "splashImagens"
    })
    public static class Personalizacao {

        @XmlElement(required = true)
        protected String tema;
        @XmlElement(name = "style-sheets", required = true)
        protected String styleSheets;
        @XmlElement(name = "style-sheets-min", required = true)
        protected String styleSheetsMin;
        @XmlElement(name = "splash-imagens", required = true)
        protected TConfig.Personalizacao.SplashImagens splashImagens;

        /**
         * ObtxE9m o valor da propriedade tema.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getTema() {
            return tema;
        }

        /**
         * Define o valor da propriedade tema.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setTema(String value) {
            this.tema = value;
        }

        /**
         * ObtxE9m o valor da propriedade styleSheets.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getStyleSheets() {
            return styleSheets;
        }

        /**
         * Define o valor da propriedade styleSheets.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setStyleSheets(String value) {
            this.styleSheets = value;
        }

        /**
         * ObtxE9m o valor da propriedade styleSheetsMin.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getStyleSheetsMin() {
            return styleSheetsMin;
        }

        /**
         * Define o valor da propriedade styleSheetsMin.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setStyleSheetsMin(String value) {
            this.styleSheetsMin = value;
        }

        /**
         * ObtxE9m o valor da propriedade splashImagens.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Personalizacao.SplashImagens }
         *
         */
        public TConfig.Personalizacao.SplashImagens getSplashImagens() {
            return splashImagens;
        }

        /**
         * Define o valor da propriedade splashImagens.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Personalizacao.SplashImagens }
         *
         */
        public void setSplashImagens(TConfig.Personalizacao.SplashImagens value) {
            this.splashImagens = value;
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
         *         &lt;element name="image" type="{}Tstr_120" maxOccurs="unbounded"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "image"
        })
        public static class SplashImagens {

            @XmlElement(required = true)
            protected List<String> image;

            /**
             * Gets the value of the image property.
             *
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the image property.
             *
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getImage().add(newItem);
             * </pre>
             *
             *
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             *
             *
             */
            public List<String> getImage() {
                if (image == null) {
                    image = new ArrayList<String>();
                }
                return this.image;
            }

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
     *         &lt;element name="connect_DB"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="dbDriver" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbDriverConn" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbHost" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbPorta" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbDatabase" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbAddConfig" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbUrl" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbUser" type="{}Tstr_120"/&gt;
     *                   &lt;element name="dbPass" type="{}Tstr_120"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="FilesType"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="token" type="{}Tstr_14"/&gt;
     *                   &lt;element name="imagem" type="{}Tstr_14"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="maskCaracter"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="digit" type="{}Tstr_120"/&gt;
     *                   &lt;element name="upper" type="{}Tstr_120"/&gt;
     *                   &lt;element name="lower" type="{}Tstr_120"/&gt;
     *                   &lt;element name="digitoAndText" type="{}Tstr_120"/&gt;
     *                   &lt;element name="interrogacao" type="{}Tstr_120"/&gt;
     *                   &lt;element name="asterisco" type="{}Tstr_120"/&gt;
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
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "connectDB",
            "filesType",
            "maskCaracter"
    })
    public static class Sis {

        @XmlElement(name = "connect_DB", required = true)
        protected TConfig.Sis.ConnectDB connectDB;
        @XmlElement(name = "FilesType", required = true)
        protected TConfig.Sis.FilesType filesType;
        @XmlElement(required = true)
        protected TConfig.Sis.MaskCaracter maskCaracter;

        /**
         * ObtxE9m o valor da propriedade connectDB.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Sis.ConnectDB }
         *
         */
        public TConfig.Sis.ConnectDB getConnectDB() {
            return connectDB;
        }

        /**
         * Define o valor da propriedade connectDB.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Sis.ConnectDB }
         *
         */
        public void setConnectDB(TConfig.Sis.ConnectDB value) {
            this.connectDB = value;
        }

        /**
         * ObtxE9m o valor da propriedade filesType.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Sis.FilesType }
         *
         */
        public TConfig.Sis.FilesType getFilesType() {
            return filesType;
        }

        /**
         * Define o valor da propriedade filesType.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Sis.FilesType }
         *
         */
        public void setFilesType(TConfig.Sis.FilesType value) {
            this.filesType = value;
        }

        /**
         * ObtxE9m o valor da propriedade maskCaracter.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Sis.MaskCaracter }
         *
         */
        public TConfig.Sis.MaskCaracter getMaskCaracter() {
            return maskCaracter;
        }

        /**
         * Define o valor da propriedade maskCaracter.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Sis.MaskCaracter }
         *
         */
        public void setMaskCaracter(TConfig.Sis.MaskCaracter value) {
            this.maskCaracter = value;
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
         *         &lt;element name="dbDriver" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbDriverConn" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbHost" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbPorta" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbDatabase" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbAddConfig" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbUrl" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbUser" type="{}Tstr_120"/&gt;
         *         &lt;element name="dbPass" type="{}Tstr_120"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "dbDriver",
                "dbDriverConn",
                "dbHost",
                "dbPorta",
                "dbDatabase",
                "dbAddConfig",
                "dbUrl",
                "dbUser",
                "dbPass"
        })
        public static class ConnectDB {

            @XmlElement(required = true)
            protected String dbDriver;
            @XmlElement(required = true)
            protected String dbDriverConn;
            @XmlElement(required = true)
            protected String dbHost;
            @XmlElement(required = true)
            protected String dbPorta;
            @XmlElement(required = true)
            protected String dbDatabase;
            @XmlElement(required = true)
            protected String dbAddConfig;
            @XmlElement(required = true)
            protected String dbUrl;
            @XmlElement(required = true)
            protected String dbUser;
            @XmlElement(required = true)
            protected String dbPass;

            /**
             * ObtxE9m o valor da propriedade dbDriver.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbDriver() {
                return dbDriver;
            }

            /**
             * Define o valor da propriedade dbDriver.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbDriver(String value) {
                this.dbDriver = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbDriverConn.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbDriverConn() {
                return dbDriverConn;
            }

            /**
             * Define o valor da propriedade dbDriverConn.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbDriverConn(String value) {
                this.dbDriverConn = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbHost.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbHost() {
                return dbHost;
            }

            /**
             * Define o valor da propriedade dbHost.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbHost(String value) {
                this.dbHost = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbPorta.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbPorta() {
                return dbPorta;
            }

            /**
             * Define o valor da propriedade dbPorta.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbPorta(String value) {
                this.dbPorta = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbDatabase.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbDatabase() {
                return dbDatabase;
            }

            /**
             * Define o valor da propriedade dbDatabase.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbDatabase(String value) {
                this.dbDatabase = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbAddConfig.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbAddConfig() {
                return dbAddConfig;
            }

            /**
             * Define o valor da propriedade dbAddConfig.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbAddConfig(String value) {
                this.dbAddConfig = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbUrl.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbUrl() {
                return dbUrl;
            }

            /**
             * Define o valor da propriedade dbUrl.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbUrl(String value) {
                this.dbUrl = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbUser.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbUser() {
                return dbUser;
            }

            /**
             * Define o valor da propriedade dbUser.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbUser(String value) {
                this.dbUser = value;
            }

            /**
             * ObtxE9m o valor da propriedade dbPass.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDbPass() {
                return dbPass;
            }

            /**
             * Define o valor da propriedade dbPass.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDbPass(String value) {
                this.dbPass = value;
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
         *         &lt;element name="token" type="{}Tstr_14"/&gt;
         *         &lt;element name="imagem" type="{}Tstr_14"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "token",
                "imagem"
        })
        public static class FilesType {

            @XmlElement(required = true)
            protected String token;
            @XmlElement(required = true)
            protected String imagem;

            /**
             * ObtxE9m o valor da propriedade token.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getToken() {
                return token;
            }

            /**
             * Define o valor da propriedade token.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setToken(String value) {
                this.token = value;
            }

            /**
             * ObtxE9m o valor da propriedade imagem.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getImagem() {
                return imagem;
            }

            /**
             * Define o valor da propriedade imagem.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setImagem(String value) {
                this.imagem = value;
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
         *         &lt;element name="digit" type="{}Tstr_120"/&gt;
         *         &lt;element name="upper" type="{}Tstr_120"/&gt;
         *         &lt;element name="lower" type="{}Tstr_120"/&gt;
         *         &lt;element name="digitoAndText" type="{}Tstr_120"/&gt;
         *         &lt;element name="interrogacao" type="{}Tstr_120"/&gt;
         *         &lt;element name="asterisco" type="{}Tstr_120"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "digit",
                "upper",
                "lower",
                "digitoAndText",
                "interrogacao",
                "asterisco"
        })
        public static class MaskCaracter {

            @XmlElement(required = true)
            protected String digit;
            @XmlElement(required = true)
            protected String upper;
            @XmlElement(required = true)
            protected String lower;
            @XmlElement(required = true)
            protected String digitoAndText;
            @XmlElement(required = true)
            protected String interrogacao;
            @XmlElement(required = true)
            protected String asterisco;

            /**
             * ObtxE9m o valor da propriedade digit.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDigit() {
                return digit;
            }

            /**
             * Define o valor da propriedade digit.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDigit(String value) {
                this.digit = value;
            }

            /**
             * ObtxE9m o valor da propriedade upper.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUpper() {
                return upper;
            }

            /**
             * Define o valor da propriedade upper.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUpper(String value) {
                this.upper = value;
            }

            /**
             * ObtxE9m o valor da propriedade lower.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getLower() {
                return lower;
            }

            /**
             * Define o valor da propriedade lower.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setLower(String value) {
                this.lower = value;
            }

            /**
             * ObtxE9m o valor da propriedade digitoAndText.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getDigitoAndText() {
                return digitoAndText;
            }

            /**
             * Define o valor da propriedade digitoAndText.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setDigitoAndText(String value) {
                this.digitoAndText = value;
            }

            /**
             * ObtxE9m o valor da propriedade interrogacao.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getInterrogacao() {
                return interrogacao;
            }

            /**
             * Define o valor da propriedade interrogacao.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setInterrogacao(String value) {
                this.interrogacao = value;
            }

            /**
             * ObtxE9m o valor da propriedade asterisco.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAsterisco() {
                return asterisco;
            }

            /**
             * Define o valor da propriedade asterisco.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAsterisco(String value) {
                this.asterisco = value;
            }

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
     *         &lt;element name="barcode"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="url" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="cosmos"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="url" type="{}Tstr"/&gt;
     *                   &lt;element name="consulta_ncm" type="{}Tstr"/&gt;
     *                   &lt;element name="consulta_gtins" type="{}Tstr"/&gt;
     *                   &lt;element name="consulta_gpcs" type="{}Tstr"/&gt;
     *                   &lt;element name="token" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="receitaws"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="url" type="{}Tstr"/&gt;
     *                   &lt;element name="token" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="webmania"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="url" type="{}Tstr"/&gt;
     *                   &lt;element name="appkey" type="{}Tstr"/&gt;
     *                   &lt;element name="appsecret" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="postmon"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="url" type="{}Tstr"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="portabilidadecelular"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="pass" type="{}Tstr"/&gt;
     *                   &lt;element name="user" type="{}Tstr"/&gt;
     *                   &lt;element name="url" type="{}Tstr"/&gt;
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
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "barcode",
            "cosmos",
            "receitaws",
            "webmania",
            "postmon",
            "portabilidadecelular"
    })
    public static class Ws {

        @XmlElement(required = true)
        protected TConfig.Ws.Barcode barcode;
        @XmlElement(required = true)
        protected TConfig.Ws.Cosmos cosmos;
        @XmlElement(required = true)
        protected TConfig.Ws.Receitaws receitaws;
        @XmlElement(required = true)
        protected TConfig.Ws.Webmania webmania;
        @XmlElement(required = true)
        protected TConfig.Ws.Postmon postmon;
        @XmlElement(required = true)
        protected TConfig.Ws.Portabilidadecelular portabilidadecelular;

        /**
         * ObtxE9m o valor da propriedade barcode.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Ws.Barcode }
         *
         */
        public TConfig.Ws.Barcode getBarcode() {
            return barcode;
        }

        /**
         * Define o valor da propriedade barcode.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Ws.Barcode }
         *
         */
        public void setBarcode(TConfig.Ws.Barcode value) {
            this.barcode = value;
        }

        /**
         * ObtxE9m o valor da propriedade cosmos.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Ws.Cosmos }
         *
         */
        public TConfig.Ws.Cosmos getCosmos() {
            return cosmos;
        }

        /**
         * Define o valor da propriedade cosmos.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Ws.Cosmos }
         *
         */
        public void setCosmos(TConfig.Ws.Cosmos value) {
            this.cosmos = value;
        }

        /**
         * ObtxE9m o valor da propriedade receitaws.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Ws.Receitaws }
         *
         */
        public TConfig.Ws.Receitaws getReceitaws() {
            return receitaws;
        }

        /**
         * Define o valor da propriedade receitaws.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Ws.Receitaws }
         *
         */
        public void setReceitaws(TConfig.Ws.Receitaws value) {
            this.receitaws = value;
        }

        /**
         * ObtxE9m o valor da propriedade webmania.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Ws.Webmania }
         *
         */
        public TConfig.Ws.Webmania getWebmania() {
            return webmania;
        }

        /**
         * Define o valor da propriedade webmania.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Ws.Webmania }
         *
         */
        public void setWebmania(TConfig.Ws.Webmania value) {
            this.webmania = value;
        }

        /**
         * ObtxE9m o valor da propriedade postmon.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Ws.Postmon }
         *
         */
        public TConfig.Ws.Postmon getPostmon() {
            return postmon;
        }

        /**
         * Define o valor da propriedade postmon.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Ws.Postmon }
         *
         */
        public void setPostmon(TConfig.Ws.Postmon value) {
            this.postmon = value;
        }

        /**
         * ObtxE9m o valor da propriedade portabilidadecelular.
         *
         * @return
         *     possible object is
         *     {@link TConfig.Ws.Portabilidadecelular }
         *
         */
        public TConfig.Ws.Portabilidadecelular getPortabilidadecelular() {
            return portabilidadecelular;
        }

        /**
         * Define o valor da propriedade portabilidadecelular.
         *
         * @param value
         *     allowed object is
         *     {@link TConfig.Ws.Portabilidadecelular }
         *
         */
        public void setPortabilidadecelular(TConfig.Ws.Portabilidadecelular value) {
            this.portabilidadecelular = value;
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
         *         &lt;element name="url" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "url"
        })
        public static class Barcode {

            @XmlElement(required = true)
            protected String url;

            /**
             * ObtxE9m o valor da propriedade url.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUrl() {
                return url;
            }

            /**
             * Define o valor da propriedade url.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUrl(String value) {
                this.url = value;
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
         *         &lt;element name="url" type="{}Tstr"/&gt;
         *         &lt;element name="consulta_ncm" type="{}Tstr"/&gt;
         *         &lt;element name="consulta_gtins" type="{}Tstr"/&gt;
         *         &lt;element name="consulta_gpcs" type="{}Tstr"/&gt;
         *         &lt;element name="token" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "url",
                "consultaNcm",
                "consultaGtins",
                "consultaGpcs",
                "token"
        })
        public static class Cosmos {

            @XmlElement(required = true)
            protected String url;
            @XmlElement(name = "consulta_ncm", required = true)
            protected String consultaNcm;
            @XmlElement(name = "consulta_gtins", required = true)
            protected String consultaGtins;
            @XmlElement(name = "consulta_gpcs", required = true)
            protected String consultaGpcs;
            @XmlElement(required = true)
            protected String token;

            /**
             * ObtxE9m o valor da propriedade url.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUrl() {
                return url;
            }

            /**
             * Define o valor da propriedade url.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUrl(String value) {
                this.url = value;
            }

            /**
             * ObtxE9m o valor da propriedade consultaNcm.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getConsultaNcm() {
                return consultaNcm;
            }

            /**
             * Define o valor da propriedade consultaNcm.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setConsultaNcm(String value) {
                this.consultaNcm = value;
            }

            /**
             * ObtxE9m o valor da propriedade consultaGtins.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getConsultaGtins() {
                return consultaGtins;
            }

            /**
             * Define o valor da propriedade consultaGtins.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setConsultaGtins(String value) {
                this.consultaGtins = value;
            }

            /**
             * ObtxE9m o valor da propriedade consultaGpcs.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getConsultaGpcs() {
                return consultaGpcs;
            }

            /**
             * Define o valor da propriedade consultaGpcs.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setConsultaGpcs(String value) {
                this.consultaGpcs = value;
            }

            /**
             * ObtxE9m o valor da propriedade token.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getToken() {
                return token;
            }

            /**
             * Define o valor da propriedade token.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setToken(String value) {
                this.token = value;
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
         *         &lt;element name="pass" type="{}Tstr"/&gt;
         *         &lt;element name="user" type="{}Tstr"/&gt;
         *         &lt;element name="url" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "pass",
                "user",
                "url"
        })
        public static class Portabilidadecelular {

            @XmlElement(required = true)
            protected String pass;
            @XmlElement(required = true)
            protected String user;
            @XmlElement(required = true)
            protected String url;

            /**
             * ObtxE9m o valor da propriedade pass.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getPass() {
                return pass;
            }

            /**
             * Define o valor da propriedade pass.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setPass(String value) {
                this.pass = value;
            }

            /**
             * ObtxE9m o valor da propriedade user.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUser() {
                return user;
            }

            /**
             * Define o valor da propriedade user.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUser(String value) {
                this.user = value;
            }

            /**
             * ObtxE9m o valor da propriedade url.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUrl() {
                return url;
            }

            /**
             * Define o valor da propriedade url.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUrl(String value) {
                this.url = value;
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
         *         &lt;element name="url" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "url"
        })
        public static class Postmon {

            @XmlElement(required = true)
            protected String url;

            /**
             * ObtxE9m o valor da propriedade url.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUrl() {
                return url;
            }

            /**
             * Define o valor da propriedade url.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUrl(String value) {
                this.url = value;
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
         *         &lt;element name="url" type="{}Tstr"/&gt;
         *         &lt;element name="token" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "url",
                "token"
        })
        public static class Receitaws {

            @XmlElement(required = true)
            protected String url;
            @XmlElement(required = true)
            protected String token;

            /**
             * ObtxE9m o valor da propriedade url.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUrl() {
                return url;
            }

            /**
             * Define o valor da propriedade url.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUrl(String value) {
                this.url = value;
            }

            /**
             * ObtxE9m o valor da propriedade token.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getToken() {
                return token;
            }

            /**
             * Define o valor da propriedade token.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setToken(String value) {
                this.token = value;
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
         *         &lt;element name="url" type="{}Tstr"/&gt;
         *         &lt;element name="appkey" type="{}Tstr"/&gt;
         *         &lt;element name="appsecret" type="{}Tstr"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         *
         *
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "url",
                "appkey",
                "appsecret"
        })
        public static class Webmania {

            @XmlElement(required = true)
            protected String url;
            @XmlElement(required = true)
            protected String appkey;
            @XmlElement(required = true)
            protected String appsecret;

            /**
             * ObtxE9m o valor da propriedade url.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getUrl() {
                return url;
            }

            /**
             * Define o valor da propriedade url.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setUrl(String value) {
                this.url = value;
            }

            /**
             * ObtxE9m o valor da propriedade appkey.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAppkey() {
                return appkey;
            }

            /**
             * Define o valor da propriedade appkey.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAppkey(String value) {
                this.appkey = value;
            }

            /**
             * ObtxE9m o valor da propriedade appsecret.
             *
             * @return
             *     possible object is
             *     {@link String }
             *
             */
            public String getAppsecret() {
                return appsecret;
            }

            /**
             * Define o valor da propriedade appsecret.
             *
             * @param value
             *     allowed object is
             *     {@link String }
             *
             */
            public void setAppsecret(String value) {
                this.appsecret = value;
            }

        }

    }

}
