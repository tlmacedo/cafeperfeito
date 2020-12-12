package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.EnderVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TEnderEmi;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TEndereco;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TUf;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TUfEmi;

public class Ender_v400 {

    TEnderEmi tEnderEmi;
    TEndereco tEndereco;
    EnderVO enderVO;

    /**
     * Grupo C.1 Endereço do emitente
     */
    public Ender_v400(EnderVO enderVO) {
        settEnderEmi(new TEnderEmi());
        settEndereco(new TEndereco());
        setEnderVO(enderVO);

        /**
         * Logradouro
         */
        if (!getEnderVO().getxLgr().equals("")) {
            gettEnderEmi().setXLgr(getEnderVO().getxLgr());
            gettEndereco().setXLgr(getEnderVO().getxLgr());
        }

        /**
         * Número
         */
        if (!getEnderVO().getNro().equals("")) {
            gettEnderEmi().setNro(getEnderVO().getNro());
            gettEndereco().setNro(getEnderVO().getNro());
        }

        /**
         * Complemento
         */
        if (getEnderVO().getxCpl() != null && !getEnderVO().getxCpl().equals("")) {
            gettEnderEmi().setXCpl(getEnderVO().getxCpl());
            gettEndereco().setXCpl(getEnderVO().getxCpl());
        }

        /**
         * Bairro
         */
        if (!getEnderVO().getxBairro().equals("")) {
            gettEnderEmi().setXBairro(getEnderVO().getxBairro());
            gettEndereco().setXBairro(getEnderVO().getxBairro());
        }

        /**Código do município
         * Utilizar a Tabela do IBGE (Anexo IX- Tabela de UF, Município e País).
         */
        if (!getEnderVO().getcMun().equals("")) {
            gettEnderEmi().setCMun(getEnderVO().getcMun());
            gettEndereco().setCMun(getEnderVO().getcMun());
        }

        /**
         * Nome do município
         */
        if (!getEnderVO().getxMun().equals("")) {
            gettEnderEmi().setXMun(getEnderVO().getxMun());
            gettEndereco().setXMun(getEnderVO().getxMun());
        }

        /**
         * Sigla da UF
         */
        if (!getEnderVO().getUF().equals("")) {
            gettEnderEmi().setUF(TUfEmi.valueOf(getEnderVO().getUF()));
            gettEndereco().setUF(TUf.valueOf(getEnderVO().getUF()));
        }

        /**Código do CEP
         * Informar os zeros não significativos. (NT 2011/004)
         */
        if (!getEnderVO().getCEP().equals("")) {
            gettEnderEmi().setCEP(getEnderVO().getCEP());
            gettEndereco().setCEP(getEnderVO().getCEP());
        }

        /**Código do País
         * 1058=Brasil
         */
        if (!getEnderVO().getcPais().equals("")) {
            gettEnderEmi().setCPais(getEnderVO().getcPais());
            gettEndereco().setCPais(getEnderVO().getcPais());
        }

        /**Nome do País
         * Brasil ou BRASIL
         */
        if (!getEnderVO().getxPais().equals("")) {
            gettEnderEmi().setXPais(getEnderVO().getxPais());
            gettEndereco().setXPais(getEnderVO().getxPais());
        }

        /**Telefone
         * Preencher com o Código DDD + número do telefone. Nas operações com exterior é permitido informar o
         * código do país + código da localidade + número do telefone (v2.0)
         */
        if (!getEnderVO().getFone().equals("")) {
            gettEnderEmi().setFone(getEnderVO().getFone().replaceAll("\\D", ""));
            gettEndereco().setFone(getEnderVO().getFone().replaceAll("\\D", ""));
        }
    }

    /**
     * Begin Getters and Setters
     */

    public TEndereco gettEndereco() {
        return tEndereco;
    }

    public void settEndereco(TEndereco tEndereco) {
        this.tEndereco = tEndereco;
    }

    public TEnderEmi gettEnderEmi() {
        return tEnderEmi;
    }

    public void settEnderEmi(TEnderEmi tEnderEmi) {
        this.tEnderEmi = tEnderEmi;
    }

    public EnderVO getEnderVO() {
        return enderVO;
    }

    public void setEnderVO(EnderVO enderVO) {
        this.enderVO = enderVO;
    }

    /**
     * END Getters and Setters
     */

}
