package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.LacresVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Transp.Vol.Lacres;

public class Lacres_v400 {

    Lacres lacres;
    LacresVO lacresVO;

    public Lacres_v400(LacresVO lacresVO) {
        setLacres(new Lacres());
        getLacres().setNLacre(lacresVO.getnLacre());
    }


    /**
     * Begin Getters and Setters
     */

    public Lacres getLacres() {
        return lacres;
    }

    public void setLacres(Lacres lacres) {
        this.lacres = lacres;
    }

    public LacresVO getLacresVO() {
        return lacresVO;
    }

    public void setLacresVO(LacresVO lacresVO) {
        this.lacresVO = lacresVO;
    }

    /**
     * END Getters and Setters
     */
}
