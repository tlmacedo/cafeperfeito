package br.com.tlmacedo.nfe.v400;

import br.com.tlmacedo.nfe.model.vo.IdeVO;
import br.com.tlmacedo.nfe.service.NFev400;
import br.inf.portalfiscal.xsd.nfe.enviNFe.TNFe.InfNFe.Ide;

import java.time.ZonedDateTime;


public class Ide_v400 {

    Ide ide;
    IdeVO ideVO = EnviNfe_v400.getNfeVO().getInfNfe().getIde();

    public Ide_v400() {
        setIde(new Ide());

        /**Código da UF do emitente do Documento Fiscal
         * Código da UF do emitente do Documento Fiscal.
         * Utilizar a Tabela do IBGE de código de unidades da federação (Anexo IX - Tabela de UF, Município e País).
         */
        if (!getIdeVO().getcUF().equals(""))
            getIde().setCUF(getIdeVO().getcUF());

        /**Código Numérico que compõe a Chave de Acesso
         * Código numérico que compõe a Chave de Acesso.
         * Número aleatório gerado pelo emitente para cada NF-e para evitar acessos indevidos da NF-e. (v2.0)
         */
        if (!getIdeVO().getcNF().equals(""))
            getIde().setCNF(getIdeVO().getcNF());

        /**Descrição da Natureza da Operação
         * Informar a natureza da operação de que decorrer a saída ou a entrada, tais como:
         * venda, compra, transferência, devolução, importação, consignação,
         * remessa (para fins de demonstração, de industrialização ou outra), conforme previsto na alínea 'i',
         * inciso I, art. 19 do CONVÊNIO S/No, de 15 de dezembro de 1970.
         */
        if (!getIdeVO().getNatOp().equals(""))
            getIde().setNatOp(getIdeVO().getNatOp());

        /**Código do Modelo do Documento Fiscal
         * 55=NF-e emitida em substituição ao modelo 1 ou 1A;
         * 65=NFC-e, utilizada nas operações de venda no varejo (a critério da UF aceitar este modelo de documento).
         */
        if (!getIdeVO().getMod().equals(""))
            getIde().setMod(getIdeVO().getMod());

        /**Série do Documento Fiscal
         * Série do Documento Fiscal, preencher com zeros na hipótese de a NF-e não possuir série.
         * Série na faixa:
         * - [000-889]: Aplicativo do Contribuinte; Emitente=CNPJ; Assinatura pelo e-CNPJ do contribuinte (procEmi<>1,2);
         * - [890-899]: Emissão no site do Fisco (NFA-e - Avulsa); Emitente= CNPJ / CPF; Assinatura pelo e-CNPJ da SEFAZ (procEmi=1);
         * - [900-909]: Emissão no site do Fisco (NFA-e); Emitente= CNPJ; Assinatura pelo e-CNPJ da SEFAZ (procEmi=1), ou Assinatura pelo e-CNPJ do contribuinte (procEmi=2);
         * - [910-919]: Emissão no site do Fisco (NFA-e); Emitente= CPF; Assinatura pelo e-CNPJ da SEFAZ (procEmi=1), ou Assinatura pelo e-CPF do contribuinte (procEmi=2);
         * - [920-969]: Aplicativo do Contribuinte; Emitente=CPF; Assinatura pelo e-CPF do contribuinte (procEmi<>1,2);
         * (Atualizado NT 2018/001)
         */
        if (!getIdeVO().getSerie().equals(""))
            getIde().setSerie(getIdeVO().getSerie());

        /**Número do Documento Fiscal
         * Número do Documento Fiscal.
         */
        if (!getIdeVO().getnNF().equals(""))
            getIde().setNNF(getIdeVO().getnNF());

        /**Data e hora de emissão do Documento Fiscal
         * Data e hora no formato UTC (Universal Coordinated Time):
         * AAAA-MM-DDThh:mm:ssTZD
         */
        if (!getIdeVO().getDhEmi().equals(""))
            getIde().setDhEmi(ZonedDateTime.of(getIdeVO().getDhEmi(), NFev400.getZoneId()).format(NFev400.getDtfNfeToLocalDate()));

        /**Data e hora de Saída ou da Entrada da Mercadoria/Produto
         * Data e hora no formato UTC (Universal Coordinated Time):
         * AAAA-MM-DDThh:mm:ssTZD.
         * Observação: Não informar este campo para a NFC-e.
         */
        if (!getIdeVO().getDhSaiEnt().equals(""))
            getIde().setDhSaiEnt(ZonedDateTime.of(getIdeVO().getDhSaiEnt(), NFev400.getZoneId()).format(NFev400.getDtfNfeToLocalDate()));    //Data e hora de Saída ou da Entrada da Mercadoria/Produto

        /**Tipo de Operação
         * 0=Entrada;
         * 1=Saída
         */
        if (!getIdeVO().getTpNF().equals(""))
            getIde().setTpNF(getIdeVO().getTpNF());

        /**dentificador de local de destino da operação
         * 1=Operação interna;
         * 2=Operação interestadual;
         * 3=Operação com exterior.
         */
        if (!getIdeVO().getIdDest().equals(""))
            getIde().setIdDest(getIdeVO().getIdDest());

        /**Código do Município de Ocorrência do Fato Gerador
         * Informar o município de ocorrência do fato gerador do ICMS.
         * Utilizar a Tabela do IBGE (Anexo IX - Tabela de UF, Município e País)
         */
        if (!getIdeVO().getcMunFG().equals(""))
            getIde().setCMunFG(getIdeVO().getcMunFG());   //Código do Município de Ocorrência do Fato Gerador

        /**Formato de Impressão do DANFE
         * 0=Sem geração de DANFE;
         * 1=DANFE normal, Retrato;
         * 2=DANFE normal, Paisagem;
         * 3=DANFE Simplificado;
         * 4=DANFE NFC-e;
         * 5=DANFE NFC-e em mensagem eletrônica (o envio de mensagem eletrônica pode ser feita de forma simultânea
         * com a impressão do DANFE; usar o tpImp=5 quando esta for a única forma de disponibilização do DANFE).
         */
        if (!getIdeVO().getTpImp().equals(""))
            getIde().setTpImp(getIdeVO().getTpImp());

        /**Tipo de Emissão da NF-e
         * 1=Emissão normal (não em contingência);
         * 2=Contingência FS-IA, com impressão do DANFE em Formulário de Segurança - Impressor Autônomo;
         * 3=Contingência SCAN (Sistema de Contingência do Ambiente Nacional); *Desativado * NT 2015/002
         * 4=Contingência EPEC (Evento Prévio da Emissão em Contingência);
         * 5=Contingência FS-DA, com impressão do DANFE em Formulário de Segurança - Documento Auxiliar;
         * 6=Contingência SVC-AN (SEFAZ Virtual de Contingência do AN);
         * 7=Contingência SVC-RS (SEFAZ Virtual de Contingência do RS);
         * 9=Contingência off-line da NFC-e;
         *
         * Observação: Para a NFC-e somente é válida a opção de
         * contingência: 9-Contingência Off-Line e, a critério da
         *   UF, opção 4-Contingência EPEC. (NT 2015/002)
         */
        if (!getIdeVO().getTpEmis().equals(""))
            getIde().setTpEmis(getIdeVO().getTpEmis());

        /**Dígito Verificador da Chave de Acesso da NF-e
         * Informar o DV da Chave de Acesso da NF-e,
         * o DV será calculado com a aplicação do algoritmo módulo 11 (base 2,9) da Chave de Acesso.
         * (vide item 5.4 do MOC – Visão Geral)
         */
        if (!getIdeVO().getcDV().equals(""))
            getIde().setCDV(getIdeVO().getcDV());

        /**Identificação do Ambiente
         * 1=Produção;
         * 2=Homologação
         */
        if (!getIdeVO().getTpAmb().equals(""))
            getIde().setTpAmb(getIdeVO().getTpAmb());

        /**Finalidade de emissão da NF-e
         *1=NF-e normal;
         * 2=NF-e complementar;
         * 3=NF-e de ajuste;
         * 4=Devolução de mercadoria.
         */
        if (!getIdeVO().getFinNFe().equals(""))
            getIde().setFinNFe(getIdeVO().getFinNFe());

        /**Indica operação com Consumidor final
         * 0=Normal;
         * 1=Consumidor final;
         */
        if (!getIdeVO().getIndFinal().equals(""))
            getIde().setIndFinal(getIdeVO().getIndFinal());

        /**Indicador de presença do comprador no estabelecimento comercial no momento da operação
         * 0=Não se aplica (por exemplo, Nota Fiscal complementar ou de ajuste);
         * 1=Operação presencial;
         * 2=Operação não presencial, pela Internet;
         * 3=Operação não presencial, Teleatendimento;
         * 4=NFC-e em operação com entrega a domicílio;
         * 5=Operação presencial, fora do estabelecimento;   (incluído NT 2016/002)
         * 9=Operação não presencial, outros.
         */
        if (!getIdeVO().getIndPres().equals(""))
            getIde().setIndPres(getIdeVO().getIndPres());

        /**Processo de emissão da NF-e
         * 0=Emissão de NF-e com aplicativo do contribuinte;
         * 1=Emissão de NF-e avulsa pelo Fisco;
         * 2=Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco;
         * 3=Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco.
         */
        if (!getIdeVO().getProcEmi().equals(""))
            getIde().setProcEmi(getIdeVO().getProcEmi());

        /**Versão do Processo de emissão da NF-e
         * Informar a versão do aplicativo emissor de NF-e.
         */
        if (!getIdeVO().getVerProc().equals(""))
            getIde().setVerProc(getIdeVO().getVerProc());  //Versão do Processo de emissão da NF-e

    }


    /**
     * Begin Getters and Setters
     */

    public Ide getIde() {
        return ide;
    }

    public void setIde(Ide ide) {
        this.ide = ide;
    }

    public IdeVO getIdeVO() {
        return ideVO;
    }

    public void setIdeVO(IdeVO ideVO) {
        this.ideVO = ideVO;
    }

    /**
     * Begin Getters and Setters
     */
}
