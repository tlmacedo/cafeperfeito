package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.ProdVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Det.Prod;

import java.math.RoundingMode;

public class Prod_v400 {

    private Prod prod;
    private ProdVO prodVO = Det_v400.getDetVO().getProd();

    public Prod_v400() {
        setProd(new Prod());

        /**Código do produto ou serviço
         * Preencher com CFOP, caso se trate de itens não relacionados com mercadorias/produtos e que o
         * contribuinte não possua codificação própria.Formato: ”CFOP9999”
         */
        if (!getProdVO().getcProd().equals(""))
            getProd().setCProd(getProdVO().getcProd());

        /**GTIN (Global Trade Item Number) do produto, antigo código EAN ou
         código de barra
         * Preencher com o código GTIN-8, GTIN-12, GTIN-13 ou GTIN-14 (antigos códigos EAN, UPC e DUN-14).
         * Para produtos que não possuem código de barras com GTIN, deve ser informado o literal “SEM GTIN”;
         * (atualizado NT 2017/001)
         */
        if (!getProdVO().getcEAN().equals(""))
            getProd().setCEAN(getProdVO().getcEAN());
        else
            getProd().setCEAN("SEM GTIN");

        /**Descrição do produto ou serviço
         *
         */
        if (!getProdVO().getxProd().equals(""))
            getProd().setXProd(getProdVO().getxProd());

        /**Código NCM com 8 dígitos
         * Obrigatória informação do NCM completo (8 dígitos). Nota: Em caso de item de serviço ou item que não
         * tenham produto (ex. transferência de crédito, crédito do ativo imobilizado, etc.), informar o valor 00 (dois zeros).
         * (NT 2014/004)
         */
        if (!getProdVO().getNCM().equals(""))
            getProd().setNCM(getProdVO().getNCM());

        /**Codificação NVE - Nomenclatura de Valor Aduaneiro e Estatística.
         * Codificação opcional que detalha alguns NCM. Formato: duas letras maiúsculas e 4 algarismos.
         * Se a mercadoria se enquadrar em mais de uma codificação, informar até 8 codificações principais.
         * Vide: Anexo XII.03 - Identificador NVE.
         */
        if (!getProdVO().getNVE().equals(""))
            getProd().getNVE().add(getProdVO().getNVE());

        /**Código CEST
         * Campo CEST (Código Especificador da Substituição Tributária), que estabelece a sistemática de uniformização e
         * identificação das mercadorias e bens passíveis de sujeição aos regimes de substituição tributária e de
         * antecipação de recolhimento do ICMS.
         */
        if (!getProdVO().getCEST().equals(""))
            getProd().setCEST(getProdVO().getCEST());

        /**Indicador de Escala Relevante
         * Indicador de Produção em escala relevante, conforme Cláusula 23 do Convenio ICMS 52/2017: S - Produzido em Escala Relevante; N – Produzido em Escala NÃO Relevante. Nota: preenchimento obrigatório para produtos com NCM
         * relacionado no Anexo XXVII do Convenio 52/2017
         * (Incluído na NT 2016/002)
         */
        if (!getProdVO().getIndEscala().equals(""))
            getProd().setIndEscala(getProdVO().getIndEscala());

        /**CNPJ do Fabricante da Mercadoria
         * CNPJ do Fabricante da Mercadoria, obrigatório para produto em escala NÃO relevante.
         * (Incluído na NT 2016/002)
         */
        if (!getProdVO().getCNPJFab().equals(""))
            getProd().setCNPJFab(getProdVO().getCNPJFab());

        /**Código de Benefício Fiscal na UF aplicado ao item. Obs.: Deve ser
         utilizado o mesmo código adotado na EFD e outras declarações, nas UF que o exigem.
         * Código de Benefício Fiscal utilizado pela UF, aplicado ao item.
         * Obs.: Deve ser utilizado o mesmo código adotado na EFD e outras declarações, nas UF que o exigem.
         * (Incluído na NT 2016/002)
         */
        if (!getProdVO().getcBenef().equals(""))
            getProd().setCBenef(getProdVO().getcBenef());

        /**EX_TIPI
         * Preencher de acordo com o código EX da TIPI. Em caso de serviço, não incluir a TAG.
         */
        if (!getProdVO().getEXTIPI().equals(""))
            getProd().setEXTIPI(getProdVO().getEXTIPI());

        /**Código Fiscal de Operações e Prestações
         * Utilizar Tabela de CFOP.
         */
        if (!getProdVO().getCFOP().equals(""))
            getProd().setCFOP(getProdVO().getCFOP());

        /**Unidade Comercial
         * Informar a unidade de comercialização do produto.
         */
        if (!getProdVO().getuCom().equals(""))
            getProd().setUCom(getProdVO().getuCom());

        /**Quantidade Comercial
         * Informar a quantidade de comercialização do produto (v2.0).
         */
        if (getProdVO().getqCom() != null)
            getProd().setQCom(getProdVO().getqCom().setScale(4).toString());

        /**Valor Unitário de Comercialização
         * Informar o valor unitário de comercialização do produto, campo meramente informativo,
         * o contribuinte pode utilizar a precisão desejada (0-10 decimais). Para efeitos de cálculo,
         * o valor unitário será obtido pela divisão do valor do produto pela quantidade comercial. (v2.0)
         */
        if (getProdVO().getvUnCom() != null)
            getProd().setVUnCom(getProdVO().getvUnCom().setScale(10, RoundingMode.HALF_UP).toString());

        /**Valor Total Bruto dos Produtos ou Serviços.
         * O valor do ICMS faz parte do Valor Total Bruto
         */
        if (getProdVO().getvProd() != null)
            getProd().setVProd(getProdVO().getvProd().setScale(2, RoundingMode.HALF_UP).toString());

        /**GTIN (Global Trade Item Number) da unidade tributável, antigo código EAN ou código de barras
         * Preencher com o código GTIN-8, GTIN-12, GTIN-13 ou GTIN-14 (antigos códigos EAN, UPC e DUN-14) da unidade tributável do produto.
         * O GTIN da unidade tributável deve corresponder àquele da menor unidade comercializável identificada por código GTIN.
         * Para produtos que não possuem código de barras com GTIN, deve ser informado o literal "SEM GTIN”;
         * (Atualizado NT 2017/001)
         */
        if (!getProdVO().getcEANTrib().equals(""))
            getProd().setCEANTrib(getProdVO().getcEANTrib());
        else
            getProd().setCEANTrib("SEM GTIN");

        /**Unidade Tributável
         *
         */
        if (!getProdVO().getuTrib().equals(""))
            getProd().setUTrib(getProdVO().getuTrib());

        /**Quantidade Tributável
         * O GTIN da unidade tributável deve corresponder àquele da menor unidade comercializável identificada
         * por código GTIN.
         */
        if (getProdVO().getqTrib() != null)
            getProd().setQTrib(getProdVO().getqTrib().setScale(4, RoundingMode.HALF_UP).toString());

        /**Valor Unitário de tributação
         *
         */
        if (getProdVO().getvUnTrib() != null)
            getProd().setVUnTrib(getProdVO().getvUnTrib().setScale(10, RoundingMode.HALF_UP).toString());

        /**Valor Total do Frete
         * Para produtos que não possuem código de barras com GTIN, deve ser informado o literal "SEM GTIN”;
         */
        if (getProdVO().getvFrete() != null)
            getProd().setVFrete(getProdVO().getvFrete().setScale(2, RoundingMode.HALF_UP).toString());

        /**Valor Total do Seguro
         *
         */
        if (getProdVO().getvSeg() != null)
            getProd().setVSeg(getProdVO().getvSeg().setScale(2, RoundingMode.HALF_UP).toString());

        /**Valor do Desconto
         *
         */
        if (getProdVO().getvDesc() != null)
            getProd().setVDesc(getProdVO().getvDesc().setScale(2, RoundingMode.HALF_UP).toString());

        /**Outras despesas acessórias
         * (v2.0)
         */
        if (getProdVO().getvOutro() != null)
            getProd().setVOutro(getProdVO().getvOutro().setScale(2, RoundingMode.HALF_UP).toString());

        /**ndica se valor do Item (vProd) entra no valor total da NF-e (vProd)
         * 0=Valor do item (vProd) não compõe o valor total da NF-e
         * 1=Valor do item (vProd) compõe o valor total da NF-e (vProd)
         * (v2.0)
         */
        if (!getProdVO().getIndTot().equals(""))
            getProd().setIndTot(getProdVO().getIndTot());
    }

    /**
     * Begin Getters and Setters
     */

    public Prod getProd() {
        return prod;
    }

    public void setProd(Prod prod) {
        this.prod = prod;
    }

    public ProdVO getProdVO() {
        return prodVO;
    }

    public void setProdVO(ProdVO prodVO) {
        this.prodVO = prodVO;
    }
    /**
     * END Getters and Setters
     */
}
