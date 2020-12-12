package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.InfAdicVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.InfAdic;

public class InfAdic_v400 {

    InfAdic infAdic;
    InfAdicVO infAdicVO = EnviNfe_v400.getNfeVO().getInfNfe().getInfAdic();

    /**
     * Grupo Z. Informações Adicionais da NF-e
     */
    public InfAdic_v400() {
        setInfAdic(new InfAdic());

        /**Informações Adicionais de Interesse do Fisco
         */
        if (!getInfAdicVO().getInfAdFisco().equals(""))
            getInfAdic().setInfAdFisco(getInfAdicVO().getInfAdFisco());

        /**Informações Complementares de interesse do Contribuinte
         */
        if (!getInfAdicVO().getInfCpl().equals(""))
            getInfAdic().setInfCpl(getInfAdicVO().getInfCpl());


    }


    /**
     * Begin Getters and Setters
     */

    public InfAdic getInfAdic() {
        return infAdic;
    }

    public void setInfAdic(InfAdic infAdic) {
        this.infAdic = infAdic;
    }

    public InfAdicVO getInfAdicVO() {
        return infAdicVO;
    }

    public void setInfAdicVO(InfAdicVO infAdicVO) {
        this.infAdicVO = infAdicVO;
    }


    /**
     * END Getters and Setters
     */
}
