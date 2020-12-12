package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.LacresVO;
import br.com.tlmacedo.nfe.model.vo.VolVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Transp.Vol;

public class Vol_v400 {

    Vol vol;
    VolVO volVO;

    public Vol_v400(VolVO volVO) {
        setVol(new Vol());
        setVolVO(volVO);

        /**Quantidade de volumes transportados
         */
        if (!getVolVO().getqVol().equals(""))
            getVol().setQVol(getVolVO().getqVol());

        /**Espécie dos volumes transportados
         */
        if (!getVolVO().getEsp().equals(""))
            getVol().setEsp(getVolVO().getEsp());

        /**Marca dos volumes transportados
         */
        if (!getVolVO().getMarca().equals(""))
            getVol().setMarca(getVolVO().getMarca());

        /**Numeração dos volumes transportados
         */
        if (!getVolVO().getnVol().equals(""))
            getVol().setNVol(getVolVO().getnVol());

        /**Peso Líquido (em kg)
         */
        if (!getVolVO().getPesoL().equals(""))
            getVol().setPesoL(getVolVO().getPesoL());

        /**Peso Bruto (em kg)
         */
        if (!getVolVO().getPesoB().equals(""))
            getVol().setPesoB(getVolVO().getPesoB());

        /**Número dos Lacres
         */
        if (getVolVO().getLacresVOList().size() > 0)
            for (LacresVO lacresVO : getVolVO().getLacresVOList())
                getVol().getLacres().add(new Lacres_v400(lacresVO).getLacres());
    }

    /**
     * Begin Getters and Setters
     */

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public VolVO getVolVO() {
        return volVO;
    }

    public void setVolVO(VolVO volVO) {
        this.volVO = volVO;
    }
    /**
     * Begin Getters and Setters
     */
}
