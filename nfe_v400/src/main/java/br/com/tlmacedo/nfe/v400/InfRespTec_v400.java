package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.InfRespTecVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TInfRespTec;

public class InfRespTec_v400 {

    TInfRespTec tInfRespTec;
    InfRespTecVO infRespTecVO = EnviNfe_v400.getNfeVO().getInfNfe().getInfRespTec();

    /**
     * Grupo ZD. Informações do Responsável Técnico
     */
    public InfRespTec_v400() {
        settInfRespTec(new TInfRespTec());

        /**CNPJ da pessoa jurídica responsável pelo sistema utilizado na emissão do documento fiscal eletrônico
         */
        if (!getInfRespTecVO().getCnpj().equals(""))
            gettInfRespTec().setCNPJ(getInfRespTecVO().getCnpj());

        /**Nome da pessoa a ser contatada
         */
        if (!getInfRespTecVO().getxContato().equals(""))
            gettInfRespTec().setXContato(getInfRespTecVO().getxContato());

        /**E-mail da pessoa jurídica a ser contatad
         */
        if (!getInfRespTecVO().getEmail().equals(""))
            gettInfRespTec().setEmail(getInfRespTecVO().getEmail());

        /**Telefone da pessoa jurídica/física a ser contatada
         */
        if (!getInfRespTecVO().getFone().equals(""))
            gettInfRespTec().setFone(getInfRespTecVO().getFone().replaceAll("\\D", ""));

    }

    /**
     * Begin Getters and Setters
     */

    public TInfRespTec gettInfRespTec() {
        return tInfRespTec;
    }

    public void settInfRespTec(TInfRespTec tInfRespTec) {
        this.tInfRespTec = tInfRespTec;
    }

    public InfRespTecVO getInfRespTecVO() {
        return infRespTecVO;
    }

    public void setInfRespTecVO(InfRespTecVO infRespTecVO) {
        this.infRespTecVO = infRespTecVO;
    }

    /**
     * END Getters and Setters
     */
}
