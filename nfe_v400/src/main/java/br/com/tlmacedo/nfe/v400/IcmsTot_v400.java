package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.IcmsTotVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Total.ICMSTot;


public class IcmsTot_v400 {

    ICMSTot icmsTot;
    IcmsTotVO icmsTotVO = EnviNfe_v400.getNfeVO().getInfNfe().getTotal().getIcmsTot();

    /**
     * Grupo Totais referentes ao ICMS
     */
    public IcmsTot_v400() {
        setIcmsTot(new ICMSTot());

        /**Base de Cálculo do ICMS
         */
        getIcmsTot().setVBC(getIcmsTotVO().getvBC().setScale(2).toString());

        /**Valor Total do ICMS
         */
        getIcmsTot().setVICMS(getIcmsTotVO().getvICMS().setScale(2).toString());

        /**Valor Total do ICMS desonerado
         */
        getIcmsTot().setVICMSDeson(getIcmsTotVO().getvICMSDeson().setScale(2).toString());

        /**Valor total do ICMS relativo Fundo de Combate à Pobreza (FCP) da UF de destino
         * Valor total do ICMS relativo ao Fundo de Combate à Pobreza (FCP) para a UF de destino.
         *  (Incluído na NT 2015/003)
         */
        getIcmsTot().setVFCPUFDest(getIcmsTotVO().getvFCPUFDest().setScale(2).toString());

        /**Valor total do ICMS Interestadual para a UF de destino
         * Valor total do ICMS Interestadual para a UF de destino, já considerando o valor do ICMS relativo ao Fundo de Combate à Pobreza naquela UF.
         * (Incluído na NT 2015/003)
         */
        getIcmsTot().setVICMSUFDest(getIcmsTotVO().getvICMSUFDest().setScale(2).toString());

        /**Valor total do ICMS Interestadual para a UF do remetente
         * Valor total do ICMS Interestadual para a UF do remetente. Nota: A partir de 2019, este valor será zero.
         * (Incluído na NT 2015/003)
         */
        getIcmsTot().setVICMSUFRemet(getIcmsTotVO().getvICMSUFRemet().setScale(2).toString());

        /**Valor Total do FCP (Fundo de Combate à Pobreza)
         * Corresponde ao total da soma dos campos id: N17c (Incluído na NT 2016/002)
         */
        getIcmsTot().setVFCP(getIcmsTotVO().getvFCP().setScale(2).toString());

        /**Base de Cálculo do ICMS ST
         */
        getIcmsTot().setVBCST(getIcmsTotVO().getvBCST().setScale(2).toString());

        /**Valor Total do ICMS ST
         */
        getIcmsTot().setVST(getIcmsTotVO().getvST().setScale(2).toString());

        /**Valor Total do FCP (Fundo de Combate à
         Pobreza) retido por substituição tributária
         * Corresponde ao total da soma dos campos id:N23d
         * (Incluído na NT 2016/002)
         */
        getIcmsTot().setVFCPST(getIcmsTotVO().getvFCPST().setScale(2).toString());

        /**Valor Total do FCP retido anteriormente por Substituição Tributária
         * Corresponde ao total da soma dos campos id:N27d
         * (Incluído na NT 2016/002)
         */
        getIcmsTot().setVFCPSTRet(getIcmsTotVO().getvFCPSTRet().setScale(2).toString());

        /**Valor Total dos produtos e serviços
         */
        getIcmsTot().setVProd(getIcmsTotVO().getvProd().setScale(2).toString()
        );

        /**Valor Total do Frete
         */
        getIcmsTot().setVFrete(getIcmsTotVO().getvFrete().setScale(2).toString());

        /**Valor Total do Seguro
         */
        getIcmsTot().setVSeg(getIcmsTotVO().getvSeg().setScale(2).toString());

        /**Valor Total do Desconto
         */
        getIcmsTot().setVDesc(getIcmsTotVO().getvDesc().setScale(2).toString());

        /**Valor Total do II
         */
        getIcmsTot().setVII(getIcmsTotVO().getvII().setScale(2).toString());

        /**Valor Total do IPI
         */
        getIcmsTot().setVIPI(getIcmsTotVO().getvIPI().setScale(2).toString());

        /**Valor Total do IPI devolvido
         * Deve ser informado quando preenchido o Grupo Tributos Devolvidos na emissão de nota finNFe=4
         * (devolução) nas operações com não contribuintes do IPI. Corresponde ao total da soma dos campos id:UA04.
         * (Incluído na NT 2016/002)
         */
        getIcmsTot().setVIPIDevol(getIcmsTotVO().getvIPIDevol().setScale(2).toString());

        /**Valor do PIS
         */
        getIcmsTot().setVPIS(getIcmsTotVO().getvPIS().setScale(2).toString());

        /**Valor da COFINS
         */
        getIcmsTot().setVCOFINS(getIcmsTotVO().getvCOFINS().setScale(2).toString());

        /**Outras Despesas acessórias
         */
        getIcmsTot().setVOutro(getIcmsTotVO().getvOutro().setScale(2).toString());

        /**Valor Total da NF-e
         * Vide validação para este campo na regra de validação "W16-xx".
         */
        getIcmsTot().setVNF(getIcmsTotVO().getvNF().setScale(2).toString());

        /**Valor aproximado total de tributos federais, estaduais e municipais.
         * (NT 2013/003)
         */
        getIcmsTot().setVTotTrib(getIcmsTotVO().getvTotTrib().setScale(2).toString());
    }


    /**
     * Begin Getters and Setters
     */
    public ICMSTot getIcmsTot() {
        return icmsTot;
    }

    public void setIcmsTot(ICMSTot icmsTot) {
        this.icmsTot = icmsTot;
    }

    public IcmsTotVO getIcmsTotVO() {
        return icmsTotVO;
    }

    public void setIcmsTotVO(IcmsTotVO icmsTotVO) {
        this.icmsTotVO = icmsTotVO;
    }

    /**
     * END Getters and Setters
     */
}
