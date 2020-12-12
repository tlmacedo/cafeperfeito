package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.TransportaVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Transp.Transporta;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TUf;

public class Transporta_v400 {

    Transporta transporta;
    TransportaVO transportaVO = EnviNfe_v400.getNfeVO().getInfNfe().getTransp().getTransporta();

    public Transporta_v400() {
        setTransporta(new Transporta());

        /**CNPJ do Transportador
         */
        if (!getTransportaVO().getCNPJ().equals(""))
            getTransporta().setCNPJ(getTransportaVO().getCNPJ());
        else if (!getTransportaVO().getCPF().equals(""))
            getTransporta().setCPF(getTransportaVO().getCPF());

        /**Razão Social ou nome
         */
        if (!getTransportaVO().getxNome().equals(""))
            getTransporta().setXNome(getTransportaVO().getxNome());

        /**Inscrição Estadual do Transportador
         */
        if (!getTransportaVO().getIE().equals(""))
            getTransporta().setIE(getTransportaVO().getIE());

        /**Endereço Completo
         */
        if (!getTransportaVO().getxEnder().equals(""))
            getTransporta().setXEnder(getTransportaVO().getxEnder());

        /**Nome do município
         */
        if (!getTransportaVO().getxMun().equals(""))
            getTransporta().setXMun(getTransportaVO().getxMun());

        /**Sigla da UF
         */
        if (!getTransportaVO().getUF().equals(""))
            getTransporta().setUF(TUf.valueOf(getTransportaVO().getUF()));
    }


    /**
     * Begin Getters and Setters
     */

    public Transporta getTransporta() {
        return transporta;
    }

    public void setTransporta(Transporta transporta) {
        this.transporta = transporta;
    }

    public TransportaVO getTransportaVO() {
        return transportaVO;
    }

    public void setTransportaVO(TransportaVO transportaVO) {
        this.transportaVO = transportaVO;
    }


    /**
     * END Getters and Setters
     */
}
