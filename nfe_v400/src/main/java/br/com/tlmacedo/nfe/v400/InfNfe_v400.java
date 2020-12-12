package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.InfNfeVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det;

public class InfNfe_v400 {

    InfNFe infNFe;
    InfNfeVO infNfeVO = EnviNfe_v400.getNfeVO().getInfNfe();

    /**
     * Grupo A. Dados da Nota Fiscal eletrônica
     */
    public InfNfe_v400() {
        /** Informações da NF-e
         * Grupo que contém as informações da NF-e
         */

        setInfNFe(new InfNFe());

        /** Versão do leiaute
         * Versão do leiaute (v2.0)
         */
        getInfNFe().setVersao(getInfNfeVO().getVersao());

        /** Identificador da TAG a ser assinada
         * Informar a Chave de Acesso precedida do literal ‘NFe’,
         */
        getInfNFe().setId(String.format("NFe%s", getInfNfeVO().getId()));

        /**
         * Grupo B. Identificação da Nota Fiscal eletrônica
         */
        getInfNFe().setIde(new Ide_v400().getIde());

        /**
         * Grupo C. Identificação do Emitente da Nota Fiscal eletrônica
         */
        getInfNFe().setEmit(new Emit_v400().getEmit());

        /**
         * Grupo E. Identificação do Destinatário da Nota Fiscal eletrônica
         */
        getInfNFe().setDest(new Dest_v400().getDest());

        /**
         * Grupo G. Identificação do Local de Entrega
         */
        if (EnviNfe_v400.getNfeVO().getInfNfe().getEntrega() != null)
            getInfNFe().setEntrega(new Entrega_v400().gettLocal());

        /**
         * Grupo H. Detalhamento de Produtos e Serviços da NF-e
         */

        getInfNfeVO().getDetList().stream().forEach(detVO -> {
            Det det = new Det_v400(detVO).getDet();
            det.setNItem(detVO.getnItem());
            getInfNFe().getDet().add(det);
        });

        /**
         * Grupo W. Total da NF-e
         */
        getInfNFe().setTotal(new Total_v400().getTotal());

        /**
         * Grupo X. Informações do Transporte da NF-e
         */
        getInfNFe().setTransp(new Transp_v400().getTransp());

        /**
         * Grupo Y. Dados da Cobrança
         */
        getInfNFe().setCobr(new Cobr_v400().getCobr());

        /**
         * Grupo YA. Informações de Pagamento
         */
        getInfNFe().setPag(new Pag_v400().getPag());

        /**
         * Grupo Z. Informações Adicionais da NF-e
         */
        getInfNFe().setInfAdic(new InfAdic_v400().getInfAdic());

        /**
         * Grupo ZD. Informações do Responsável Técnico
         */
        getInfNFe().setInfRespTec(new InfRespTec_v400().gettInfRespTec());
    }


    /**
     * Begin Getters and Setters
     */

    public InfNFe getInfNFe() {
        return infNFe;
    }

    public void setInfNFe(InfNFe infNFe) {
        this.infNFe = infNFe;
    }

    public InfNfeVO getInfNfeVO() {
        return infNfeVO;
    }

    public void setInfNfeVO(InfNfeVO infNfeVO) {
        this.infNfeVO = infNfeVO;
    }

    /**
     * END Getters and Setters
     */

    @Override
    public String toString() {
        return "InfNfe_v400{" +
                "infNFe=" + infNFe +
                ", infNfeVO=" + infNfeVO +
                '}';
    }
}
