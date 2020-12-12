//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementaxe7xe3o de Referxeancia (JAXB) de Bind XML, v2.3.1-b171012.0423 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificaxe7xf5es neste arquivo serxe3o perdidas apxf3s a recompilaxe7xe3o do esquema de origem. 
// Gerado em: 2020.12.09 xe0s 02:48:49 PM AMT 
//


package br.com.cafeperfeito.xsd.config_nfe.layoutConfig;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Identificação do Destinatário da Nota Fiscal eletrônica
 *
 * <p>Classe Java de Dest complex type.
 *
 * <p>O seguinte fragmento do esquema especifica o contexFAdo esperado contido dentro desta classe.
 *
 * <pre>
 * &lt;complexType name="Dest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="indIEDests"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="indIEDest" type="{}IndIEDest" maxOccurs="unbounded"/&gt;
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
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dest", propOrder = {
        "indIEDests"
})
public class Dest {

    @XmlElement(required = true)
    protected Dest.IndIEDests indIEDests;

    /**
     * ObtxE9m o valor da propriedade indIEDests.
     *
     * @return possible object is
     * {@link Dest.IndIEDests }
     */
    public Dest.IndIEDests getIndIEDests() {
        return indIEDests;
    }

    /**
     * Define o valor da propriedade indIEDests.
     *
     * @param value allowed object is
     *              {@link Dest.IndIEDests }
     */
    public void setIndIEDests(Dest.IndIEDests value) {
        this.indIEDests = value;
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
     *         &lt;element name="indIEDest" type="{}IndIEDest" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "indIEDest"
    })
    public static class IndIEDests {

        @XmlElement(required = true)
        protected List<IndIEDest> indIEDest;

        /**
         * Gets the value of the indIEDest property.
         *
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the indIEDest property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIndIEDest().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IndIEDest }
         */
        public List<IndIEDest> getIndIEDest() {
            if (indIEDest == null) {
                indIEDest = new ArrayList<IndIEDest>();
            }
            return this.indIEDest;
        }

    }

}
