package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.EntregaVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TLocal;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TUf;

public class Entrega_v400 {

    TLocal tLocal;
    EntregaVO entregaVO = EnviNfe_v400.getNfeVO().getInfNfe().getEntrega();

    /**
     * Grupo C.1 Endereço do emitente
     */
    public Entrega_v400() {
        settLocal(new TLocal());

        /**
         * CNPJ
         */
        if (!getEntregaVO().getCnpj().equals(""))
            gettLocal().setCNPJ(getEntregaVO().getCnpj());
        else
            gettLocal().setCPF(getEntregaVO().getCpf());

        /**
         * Razão Social ou Nome do Recebedor
         */
        if (!getEntregaVO().getxNome().equals(""))
            gettLocal().setXNome(getEntregaVO().getxNome());

        /**
         * Logradouro
         */
        if (!getEntregaVO().getxLgr().equals(""))
            gettLocal().setXLgr(getEntregaVO().getxLgr());

        /**
         * Número
         */
        if (!getEntregaVO().getNro().equals(""))
            gettLocal().setNro(getEntregaVO().getNro());

        /**
         * Complemento
         */
        if (!getEntregaVO().getxCpl().equals(""))
            gettLocal().setXCpl(getEntregaVO().getxCpl());

        /**
         * Bairro
         */
        if (!getEntregaVO().getxBairro().equals(""))
            gettLocal().setXBairro(getEntregaVO().getxBairro());

        /**Código do município
         * Utilizar a Tabela do IBGE (Anexo IX- Tabela de UF, Município e País).
         */
        if (!getEntregaVO().getcMun().equals(""))
            gettLocal().setCMun(getEntregaVO().getcMun());

        /**
         * Nome do município
         */
        if (!getEntregaVO().getxMun().equals(""))
            gettLocal().setXMun(getEntregaVO().getxMun());

        /**
         * Sigla da UF
         */
        if (!getEntregaVO().getUF().equals(""))
            gettLocal().setUF(TUf.valueOf(getEntregaVO().getUF()));

        /**Código do CEP
         * Informar os zeros não significativos. (NT 2011/004)
         */
        if (!getEntregaVO().getCEP().equals(""))
            gettLocal().setCEP(getEntregaVO().getCEP());

        /**Código do País
         * 1058=Brasil
         */
        if (!getEntregaVO().getcPais().equals(""))
            gettLocal().setCPais(getEntregaVO().getcPais());

        /**Nome do País
         * Brasil ou BRASIL
         */
        if (!getEntregaVO().getxPais().equals(""))
            gettLocal().setXPais(getEntregaVO().getxPais());

        /**Telefone
         * Preencher com o Código DDD + número do telefone. Nas operações com exterior é permitido informar o
         * código do país + código da localidade + número do telefone (v2.0)
         */
        if (!getEntregaVO().getxNome().equals(""))
            gettLocal().setFone(getEntregaVO().getFone().replaceAll("\\D", ""));

        /**
         * Endereço de e-mail do Recebedor
         */
        if (!getEntregaVO().getEmail().equals(""))
            gettLocal().setEmail(getEntregaVO().getEmail());

        /**Inscrição Estadual do Estabelecimento Recebedor
         * Informar somente os algarismos, sem os caracteres de formatação (ponto, barra, hífen, etc.).
         * (Criado na NT 2018.005)
         */
        if (!getEntregaVO().getIE().equals(""))
            gettLocal().setIE(getEntregaVO().getIE());

    }

    /**
     * Begin Getters and Setters
     */

    public TLocal gettLocal() {
        return tLocal;
    }

    public void settLocal(TLocal tLocal) {
        this.tLocal = tLocal;
    }

    public EntregaVO getEntregaVO() {
        return entregaVO;
    }

    public void setEntregaVO(EntregaVO entregaVO) {
        this.entregaVO = entregaVO;
    }

    /**
     * END Getters and Setters
     */

}
