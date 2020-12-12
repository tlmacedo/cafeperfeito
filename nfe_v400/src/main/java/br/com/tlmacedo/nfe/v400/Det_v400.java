package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.DetVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det;

public class Det_v400 {

    private Det det;
    private static DetVO detVO;

    /**
     * Grupo H. Detalhamento de Produtos e Serviços da NF-e
     */
    public Det_v400(DetVO detVO) {
        setDet(new Det());
        setDetVO(detVO);

        /**
         * Grupo I. Produtos e Serviços da NF-e
         */
        getDet().setProd(new Prod_v400().getProd());

        /**
         * Grupo N. Grupo Tributação
         */
        getDet().setImposto(new Imposto_v400().getImposto());

    }


    /**
     * Begin Getters and Setters
     */

    public Det getDet() {
        return det;
    }

    public void setDet(Det det) {
        this.det = det;
    }

    public static DetVO getDetVO() {
        return detVO;
    }

    public static void setDetVO(DetVO detVO) {
        Det_v400.detVO = detVO;
    }

/**
 * END Getters and Setters
 */
}
