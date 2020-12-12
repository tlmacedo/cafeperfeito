package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.DestVO;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Dest;


public class Dest_v400 {

    Dest dest;
    DestVO destVO = EnviNfe_v400.getNfeVO().getInfNfe().getDest();

    /**
     * Grupo E. Identificação do Destinatário da Nota Fiscal eletrônica
     */
    public Dest_v400() {
        setDest(new Dest());

        /**
         * nformar o CNPJ ou o CPF do destinatário, preenchendo os zeros não significativos.
         * No caso de operação com o exterior, ou para comprador estrangeiro informar a tag
         * "idEstrangeiro”.
         */
        if (!getDestVO().getCnpj().equals(""))
            getDest().setCNPJ(getDestVO().getCnpj());
        else
            getDest().setCPF(getDestVO().getCpf());

        /**
         * Informar esta tag no caso de operação com o exterior, ou para comprador estrangeiro.
         * Informar o número do passaporte ou outro documento legal para identificar pessoa estrangeira (campo aceita valor nulo).
         * Observação: Campo aceita algarismos, letras (maiúsculas e minúsculas) e os caracteres do conjunto que segue: [:.+-/()]
         */
        if (!getDestVO().getIdEstrangeiro().equals(""))
            getDest().setIdEstrangeiro(getDestVO().getIdEstrangeiro());

        /**Razão Social ou nome do destinatário
         * Tag obrigatória para a NF-e (modelo 55) e opcional para a NFC-e.
         */

        if (!getDestVO().getxNome().equals(""))
            getDest().setXNome(getDestVO().getxNome());

        /**
         * Grupo obrigatório para a NF-e (modelo 55).
         */
        if (getDestVO().getEnder() != null)
            getDest().setEnderDest(new Ender_v400(getDestVO().getEnder()).gettEndereco());


//        if (destinatario.getEnderecoNFeEntrega() != null)
//            getTnFe().getInfNFe().setEntrega(getNewEntrega(destinatario.getEnderecoNFeEntrega()));

        /**Indicador da IE do Destinatário
         * 1=Contribuinte ICMS (informar a IE do destinatário);
         * 2=Contribuinte isento de Inscrição no cadastro de Contribuintes
         * 9=Não Contribuinte, que pode ou não possuir Inscrição Estadual no Cadastro de Contribuintes do ICMS.
         * Nota 1: No caso de NFC-e informar indIEDest=9 e não informar a tag IE do destinatário;
         * Nota 2: No caso de operação com o Exterior informar indIEDest=9 e não informar a tag IE do destinatário;
         * Nota 3: No caso de Contribuinte Isento de Inscrição (indIEDest=2), não informar a tag IE do destinatário.
         */
        if (!getDestVO().getIndIEDest().equals(""))
            getDest().setIndIEDest(getDestVO().getIndIEDest());

        /**Inscrição Estadual do Destinatário
         * Campo opcional. Informar somente os algarismos, sem os caracteres de formatação (ponto, barra, hífen, etc.).
         */
        if (!getDestVO().getIE().equals(""))
            getDest().setIE(getDestVO().getIE());

        /**Inscrição na SUFRAMA
         * Obrigatório, nas operações que se beneficiam de incentivos fiscais existentes nas áreas sob controle da SUFRAMA.
         * A omissão desta informação impede o processamento da operação pelo Sistema de Mercadoria Nacional da SUFRAMA e a
         * liberação da Declaração de Ingresso, prejudicando a comprovação do ingresso / internamento da mercadoria nestas áreas. (v2.0)
         */
        if (!getDestVO().getISUF().equals(""))
            getDest().setISUF(getDestVO().getISUF());

        /**Inscrição Municipal do Tomador do Serviço
         * Campo opcional, pode ser informado na NF-e conjugada, com itens de produtos sujeitos ao ICMS e itens de serviços sujeitos ao ISSQN.
         */
        if (!getDestVO().getIM().equals(""))
            getDest().setIM(getDestVO().getIM());

        /**email
         * Campo pode ser utilizado para informar o e-mail de recepção da NF-e indicada pelo destinatário (v2.0)
         */
        if (!getDestVO().getEmail().equals(""))
            getDest().setEmail(getDestVO().getEmail());

    }


    /**
     * Begin Getters and Setters
     */
    public Dest getDest() {
        return dest;
    }

    public void setDest(Dest dest) {
        this.dest = dest;
    }

    public DestVO getDestVO() {
        return destVO;
    }

    public void setDestVO(DestVO destVO) {
        this.destVO = destVO;
    }
    /**
     * END Getters and Setters
     */
}
