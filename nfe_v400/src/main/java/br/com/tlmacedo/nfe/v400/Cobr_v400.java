package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.CobrVO;
import br.com.tlmacedo.nfe.model.vo.DupVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Cobr;

import java.math.BigDecimal;

public class Cobr_v400 {

    Cobr cobr;
    CobrVO cobrVO = EnviNfe_v400.getNfeVO().getInfNfe().getCobr();

    public Cobr_v400() {
        setCobr(new Cobr());

        if (EnviNfe_v400.getNfeVO().getInfNfe().getTotal().getIcmsTot().getvNF().compareTo(BigDecimal.ZERO) == 0)
            return;
        if (getCobrVO().getFat() != null)
            getCobr().setFat(new Fat_v400().getFat());

        if (getCobrVO().getDupVOList().size() > 0)
            for (DupVO dupVO : getCobrVO().getDupVOList())
                getCobr().getDup().add(new Dup_v400(dupVO).getDup());
    }

    public Cobr getCobr() {
        return cobr;
    }

    public void setCobr(Cobr cobr) {
        this.cobr = cobr;
    }

    public CobrVO getCobrVO() {
        return cobrVO;
    }

    public void setCobrVO(CobrVO cobrVO) {
        this.cobrVO = cobrVO;
    }

    @Override
    public String toString() {
        return "Cobr_v400{" +
                "cobr=" + cobr +
                ", cobrVO=" + cobrVO +
                '}';
    }
}
