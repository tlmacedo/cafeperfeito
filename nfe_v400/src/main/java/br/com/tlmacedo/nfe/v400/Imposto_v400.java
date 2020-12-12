package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.ImpostoVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.ObjectFactory;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Imposto;


public class Imposto_v400 {

    private Imposto imposto;
    private static ImpostoVO impostoVO = Det_v400.getDetVO().getImposto();

    public Imposto_v400() {
        setImposto(new Imposto());

        /**
         * Grupo N. Grupo Tributação
         */
        if (getImpostoVO().getIcms() != null)
            getImposto().getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(new Icms_v400().getIcms()));

        /**
         * Grupo Q. PIS
         */
        if (getImpostoVO().getPis() != null)
            getImposto().getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(new Pis_v400().getPis()));

        /**
         * Grupo S. COFINS
         */
        if (getImpostoVO().getCofins() != null)
            getImposto().getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(new Cofins_v400().getCofins()));

        /**
         * Grupo T. COFINS ST
         */
        if (getImpostoVO().getCofinsST() != null)
            getImposto().getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINSST(new CofinsST_v400().getCofinsst()));

    }

    /**
     * Begin Getters and Setters
     */

    public Imposto getImposto() {
        return imposto;
    }

    public void setImposto(Imposto imposto) {
        this.imposto = imposto;
    }

    public static ImpostoVO getImpostoVO() {
        return impostoVO;
    }

    public static void setImpostoVO(ImpostoVO impostoVO) {
        Imposto_v400.impostoVO = impostoVO;
    }

/**
 * Begin Getters and Setters
 */
}
