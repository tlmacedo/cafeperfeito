package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.NfeVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe;

public class Nfe_v400 {


    private TNFe tnFe;
    private NfeVO nfeVO = EnviNfe_v400.getNfeVO();

    public Nfe_v400() {
        setTnFe(new TNFe());

        /**
         * Grupo A. Dados da Nota Fiscal eletrônica
         */
        getTnFe().setInfNFe(new InfNfe_v400().getInfNFe());
    }

    /**
     * Begin Getters and Setters
     */
    public TNFe getTnFe() {
        return tnFe;
    }

    public void setTnFe(TNFe tnFe) {
        this.tnFe = tnFe;
    }

    public NfeVO getNfeVO() {
        return nfeVO;
    }

    public void setNfeVO(NfeVO nfeVO) {
        this.nfeVO = nfeVO;
    }

    /**
     * END Getters and Setters
     */

}
