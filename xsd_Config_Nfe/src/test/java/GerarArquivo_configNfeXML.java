import br.com.cafeperfeito.xsd.config_nfe.config.*;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class GerarArquivo_configNfeXML {

    private static final String PATHRESOURCES = "/src/main/resources";
    private static final String VERSAO = "2.03";

    private static MyInfNfe MYINFNFE = new MyInfNfe();

    public static void main(String[] args) {
        try {

            MyConfig myConfig = new MyConfig();
            MYINFNFE.setMyConfig(myConfig);
            Ide ide = new Ide();
            MYINFNFE.setIde(ide);
            Emit emit = new Emit();
            MYINFNFE.setEmit(emit);
            Dest dest = new Dest();
            MYINFNFE.setDest(dest);
            Sefaz sefaz = new Sefaz();
            MYINFNFE.setSefaz(sefaz);
            Transp transp = new Transp();
            MYINFNFE.setTransp(transp);
            MeusBancos meusBancos = new MeusBancos();
            MYINFNFE.setMeusBancos(meusBancos);


            /**
             *
             *
             * Begin MeusBancos []
             *
             */
            Banco banco;
            MeusBancos.Bancos bancos = new MeusBancos.Bancos();
            meusBancos.setBancos(bancos);
            //
            banco = new Banco();
            bancos.getBanco().add(banco);
            banco.setId(0);
            banco.setNome("BANCO NUBANK");
            banco.setAgencia("0001");
            banco.setConta("90.846.088-3");
            banco.setPadrão(false);
            //
            banco = new Banco();
            bancos.getBanco().add(banco);
            banco.setId(1);
            banco.setNome("BANCO ITAÚ");
            banco.setAgencia("1677");
            banco.setConta("35.456-3");
            banco.setPadrão(false);
            //
            banco = new Banco();
            bancos.getBanco().add(banco);
            banco.setId(2);
            banco.setNome("BANCO SANTANDER");
            banco.setAgencia("3273");
            banco.setConta("2.011.754-7");
            banco.setPadrão(true);
            /**
             *
             *
             * END MeusBancos []
             *
             */


            /**
             *
             *
             * Begin MyConfig [Minhas configurações]
             *
             */
            /**
             * Begin Cfg []
             */
            CertificadoCfg cfg;
            MyConfig.CertificadoCfgs cfgs = new MyConfig.CertificadoCfgs();
            myConfig.setCertificadoCfgs(cfgs);
            //
            cfg = new CertificadoCfg();
            cfg.setTipo("SafeNet");
            cfg.setPath("tokenSafeNet5100.cfg");
            //
            cfg = new CertificadoCfg();
            cfg.setTipo("GD");
            cfg.setPath("tokenGDStarSign.cfg");
            /**
             * END Cfg []
             */

            myConfig.setCertificadoToken("tokenSafeNet5100.cfg");
            myConfig.setCertificadoCacerts("NfeCacerts");
            myConfig.setPortaSSl(BigInteger.valueOf(443));
            myConfig.setVersao("4.00");
            myConfig.setIndSinc(BigInteger.valueOf(0));
            myConfig.setNatOp(BigInteger.valueOf(0));
            myConfig.setMod(BigInteger.valueOf(55));//55=NF-e emitida em substituição ao modelo 1 ou 1A; 65=NFC-e, utilizada nas operações de venda no varejo (a critério da UF aceitar este modelo de documento).
            myConfig.setSerie(BigInteger.valueOf(1));
            myConfig.setTpNF(BigInteger.valueOf(1));//0=Entrada; 1=Saída
            myConfig.setIdDest(BigInteger.valueOf(1));//1=Operação interna; 2=Operação interestadual; 3=Operação com exterior.
            myConfig.setCMunFG(BigInteger.valueOf(1302603));
            myConfig.setTpImp(BigInteger.valueOf(1));//0=Sem DANFE; 1=DANFE normal, Retrato; 2=DANFE normal, Paisagem; 3=DANFE Simplificado; 4=DANFE NFC-e; 5=DANFE NFC-e
            myConfig.setTpEmis(BigInteger.valueOf(1));
            myConfig.setTpAmb(BigInteger.valueOf(2));//1=Produção/2=Homologação
            myConfig.setFinNFe(BigInteger.valueOf(1));//1=NF-e normal; 2=NF-e complementar; 3=NF-e de ajuste; 4=Devolução de mercadoria.
            myConfig.setIndPres(BigInteger.valueOf(3));
            myConfig.setProcEmi(BigInteger.valueOf(0));
            myConfig.setVerProc(VERSAO);
            myConfig.setIndPag(BigInteger.valueOf(1));
            myConfig.setCPais(BigInteger.valueOf(1058));
            myConfig.setNPais("BRASIL");
            myConfig.setCRT(BigInteger.valueOf(3));
            myConfig.setInfAdic("TOTAL R$ %s*t*DT. VENC.: %s*n*DADOS BANCARIOS PARA TRANSFERENCIA*n*BANCO %s AG: %s C/C: %s");
            myConfig.setHomCNPJ("99999999000191");
            myConfig.setHomRazao("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");

            InfRespTec respTec;
            MyConfig.InfRespTecs infRespTecs = new MyConfig.InfRespTecs();
            myConfig.setInfRespTecs(infRespTecs);
            //
            respTec = new InfRespTec();
            infRespTecs.getInfRespTec().add(respTec);
            respTec.setId(1);
            respTec.setCnpj("08009246000136");
            respTec.setXContato("THIAGO LEIROS MACEDO");
            respTec.setEmail("tl.macedo@hotmail.com");
            respTec.setFone("92 98168-6148");
            //
            respTec = new InfRespTec();
            infRespTecs.getInfRespTec().add(respTec);
            respTec.setId(2);
            respTec.setCnpj("08009246000136");
            respTec.setXContato("CARLA FIGUEIREDO LEIROS MACEDO");
            respTec.setEmail("carlinha.figueiredo32@hotmail.com");
            respTec.setFone("92 99241-2974");

            /**
             *
             *
             * END MyConfig [Minhas configurações]
             *
             */


            /**
             *
             *
             * Begin Ide [Identificação da Nota Fiscal eletrônica]
             *
             */
            /**
             * Begin natOp [Descrição da Natureza da Operação]
             */
            NatOp natOp;
            Ide.NatOps natOps = new Ide.NatOps();
            ide.setNatOps(natOps);
            //
            natOp = new NatOp();
            natOps.getNatOp().add(natOp);
            natOp.setId(0);
            natOp.setDescricao("Dentro Do Estado");
            //
            natOp = new NatOp();
            natOps.getNatOp().add(natOp);
            natOp.setId(1);
            natOp.setDescricao("Fora Do Estado");
            //
            natOp = new NatOp();
            natOps.getNatOp().add(natOp);
            natOp.setId(2);
            natOp.setDescricao("Fora Do País");
            /**
             * END natOp [Descrição da Natureza da Operação]
             */

            /**
             * Begin indPag [Indicador da forma de pagamento]
             */
            IndPag indPag;
            Ide.IndPags indPags = new Ide.IndPags();
            ide.setIndPags(indPags);
            //
            indPag = new IndPag();
            indPags.getIndPag().add(indPag);
            indPag.setId(0);
            indPag.setDescricao("Pagamento á vista");
            //
            indPag = new IndPag();
            indPags.getIndPag().add(indPag);
            indPag.setId(1);
            indPag.setDescricao("Pagamento a prazo");
            //
            indPag = new IndPag();
            indPags.getIndPag().add(indPag);
            indPag.setId(2);
            indPag.setDescricao("Outros");
            /**
             * END indPag [Indicador da forma de pagamento]
             */


            /**
             * Begin mod [Código do Modelo do Documento Fiscal]
             */
            Mod mod;
            Ide.Mods mods = new Ide.Mods();
            ide.setMods(mods);
            //
            mod = new Mod();
            mods.getMod().add(mod);
            mod.setId(55);
            mod.setDescricao("55");
            //
            mod = new Mod();
            mods.getMod().add(mod);
            mod.setId(1);
            mod.setDescricao("01");
            //
            mod = new Mod();
            mods.getMod().add(mod);
            mod.setId(57);
            mod.setDescricao("57");
            //
            mod = new Mod();
            mods.getMod().add(mod);
            mod.setId(65);
            mod.setDescricao("Nfc-e");
            /**
             * END mod [Código do Modelo do Documento Fiscal]
             */


            /**
             * Begin idDest [Identificador de local de destino da operação]
             */
            IdDest idDest;
            Ide.IdDests idDests = new Ide.IdDests();
            ide.setIdDests(idDests);
            //
            idDest = new IdDest();
            idDests.getIdDest().add(idDest);
            idDest.setId(1);
            idDest.setDescricao("Operação interna");
            //
            idDest = new IdDest();
            idDests.getIdDest().add(idDest);
            idDest.setId(2);
            idDest.setDescricao("Operação interestadual");
            //
            idDest = new IdDest();
            idDests.getIdDest().add(idDest);
            idDest.setId(3);
            idDest.setDescricao("Operação com exterior");
            /**
             * END idDest [Identificador de local de destino da operação]
             */


            /**
             * Begin tpImp [Formato de Impressão do DANFE]
             */
            TpImp tpImp;
            Ide.TpImps tpImps = new Ide.TpImps();
            ide.setTpImps(tpImps);
            //
            tpImp = new TpImp();
            tpImps.getTpImp().add(tpImp);
            tpImp.setId(0);
            tpImp.setDescricao("Sem geração de DANFE");
            //
            tpImp = new TpImp();
            tpImps.getTpImp().add(tpImp);
            tpImp.setId(1);
            tpImp.setDescricao("DANFE normal, Retrato");
            //
            tpImp = new TpImp();
            tpImps.getTpImp().add(tpImp);
            tpImp.setId(2);
            tpImp.setDescricao("DANFE normal, Paisagem");
            //
            tpImp = new TpImp();
            tpImps.getTpImp().add(tpImp);
            tpImp.setId(3);
            tpImp.setDescricao("DANFE Simplificado");
            //
            tpImp = new TpImp();
            tpImps.getTpImp().add(tpImp);
            tpImp.setId(4);
            tpImp.setDescricao("DANFE NFC-e");
            //
            tpImp = new TpImp();
            tpImps.getTpImp().add(tpImp);
            tpImp.setId(5);
            tpImp.setDescricao("DANFE NFC-e em mensagem eletrônica");
            /**
             * Begin tpImp [Formato de Impressão do DANFE]
             */


            /**
             * Begin tpEmis [Tipo de Emissão da NF-e]
             */
            TpEmis tpEmis;
            Ide.TpEmiss tpEmiss = new Ide.TpEmiss();
            ide.setTpEmiss(tpEmiss);
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(1);
            tpEmis.setDescricao("Emissão normal");
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(2);
            tpEmis.setDescricao("Contingência FS-IA");
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(3);
            tpEmis.setDescricao("Contingência SCAN");
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(4);
            tpEmis.setDescricao("Contingência DPEC");
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(5);
            tpEmis.setDescricao("Contingência FS-DA");
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(6);
            tpEmis.setDescricao("Contingência SVC-AN");
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(7);
            tpEmis.setDescricao("Contingência SVC-RS");
            //
            tpEmis = new TpEmis();
            tpEmiss.getTpEmis().add(tpEmis);
            tpEmis.setId(9);
            tpEmis.setDescricao("Contingência off-line da NFC-e");
            /**
             * END tpEmis [Tipo de Emissão da NF-e]
             */


            /**
             * Begin tpAmb [Identificação do Ambiente]
             */
            TpAmb tpAmb;
            Ide.TpAmbs tpAmbs = new Ide.TpAmbs();
            ide.setTpAmbs(tpAmbs);
            //
            tpAmb = new TpAmb();
            tpAmbs.getTpAmb().add(tpAmb);
            tpAmb.setId(1);
            tpAmb.setDescricao("Produção");
            //
            tpAmb = new TpAmb();
            tpAmbs.getTpAmb().add(tpAmb);
            tpAmb.setId(2);
            tpAmb.setDescricao("Homologação");
            /**
             * END tpAmb [Identificação do Ambiente]
             */


            /**
             * Begin finNFe [Finalidade de emissão da NF-e]
             */
            FinNFe finNFe;
            Ide.FinNFes finNFes = new Ide.FinNFes();
            ide.setFinNFes(finNFes);
            //
            finNFe = new FinNFe();
            finNFes.getFinNFe().add(finNFe);
            finNFe.setId(1);
            finNFe.setDescricao("NF-e normal");
            //
            finNFe = new FinNFe();
            finNFes.getFinNFe().add(finNFe);
            finNFe.setId(2);
            finNFe.setDescricao("NF-e complementar");
            //
            finNFe = new FinNFe();
            finNFes.getFinNFe().add(finNFe);
            finNFe.setId(3);
            finNFe.setDescricao("NF-e de ajuste");
            //
            finNFe = new FinNFe();
            finNFes.getFinNFe().add(finNFe);
            finNFe.setId(4);
            finNFe.setDescricao("Devolução de mercadoria");
            /**
             * END finNFe [Finalidade de emissão da NF-e]
             */


            /**
             * Begin indFinal [Indica operação com Consumidor final]
             */
            IndFinal indFinal;
            Ide.IndFinals indFinals = new Ide.IndFinals();
            ide.setIndFinals(indFinals);
            //
            indFinal = new IndFinal();
            indFinals.getIndFinal().add(indFinal);
            indFinal.setId(0);
            indFinal.setDescricao("Normal");
            //
            indFinal = new IndFinal();
            indFinals.getIndFinal().add(indFinal);
            indFinal.setId(1);
            indFinal.setDescricao("Consumidor final");
            /**
             * END indFinal [Indica operação com Consumidor final]
             */


            /**
             * Begin indPres [Indicador de presença do comprador no estabelecimento comercial no momento da operação]
             */
            IndPres indPres;
            Ide.IndPress indPress = new Ide.IndPress();
            ide.setIndPress(indPress);
            //
            indPres = new IndPres();
            indPress.getIndPres().add(indPres);
            indPres.setId(0);
            indPres.setDescricao("Não se aplica");
            //
            indPres = new IndPres();
            indPress.getIndPres().add(indPres);
            indPres.setId(1);
            indPres.setDescricao("Operação presencial");
            //
            indPres = new IndPres();
            indPress.getIndPres().add(indPres);
            indPres.setId(2);
            indPres.setDescricao("Operação não presencial, pela Internet");
            //
            indPres = new IndPres();
            indPress.getIndPres().add(indPres);
            indPres.setId(3);
            indPres.setDescricao("Operação não presencial, Teleatendimento");
            //
            indPres = new IndPres();
            indPress.getIndPres().add(indPres);
            indPres.setId(4);
            indPres.setDescricao("NFC-e em operação com entrega a domicílio");
            //
            indPres = new IndPres();
            indPress.getIndPres().add(indPres);
            indPres.setId(9);
            indPres.setDescricao("Operação não presencial, outros");
            /**
             * END indPres [Indicador de presença do comprador no estabelecimento comercial no momento da operação]
             */


            /**
             * Begin procEmi [Processo de emissão da NF-e]
             */
            ProcEmi procEmi;
            Ide.ProcEmis procEmis = new Ide.ProcEmis();
            ide.setProcEmis(procEmis);
            //
            procEmi = new ProcEmi();
            procEmis.getProcEmi().add(procEmi);
            procEmi.setId(0);
            procEmi.setDescricao("Emissão de NF-e com aplicativo do contribuinte");
            //
            procEmi = new ProcEmi();
            procEmis.getProcEmi().add(procEmi);
            procEmi.setId(1);
            procEmi.setDescricao("Emissão de NF-e avulsa pelo Fisco");
            //
            procEmi = new ProcEmi();
            procEmis.getProcEmi().add(procEmi);
            procEmi.setId(2);
            procEmi.setDescricao("Emissão de NF-e avulsa, pelo contribuinte com seu certificado digital, através do site do Fisco");
            //
            procEmi = new ProcEmi();
            procEmis.getProcEmi().add(procEmi);
            procEmi.setId(3);
            procEmi.setDescricao("Emissão NF-e pelo contribuinte com aplicativo fornecido pelo Fisco");
            /**
             * END procEmi [Processo de emissão da NF-e]
             */
            /**
             *
             *
             * END Ide
             *
             */


            /**
             *
             *
             * Begin Emit [Identificação da Nota Fiscal eletrônica]
             *
             */

            /**
             * Begin CRT [Código de Regime Tributário]
             */
            Crt crt;
            Emit.Crts crTs = new Emit.Crts();
            emit.setCrts(crTs);
            //
            crt = new Crt();
            crTs.getCrt().add(crt);
            crt.setId(1);
            crt.setDescricao("Simples Nacional");
            //
            crt = new Crt();
            crTs.getCrt().add(crt);
            crt.setId(2);
            crt.setDescricao("Simples Nacional, excesso sublimite de receita bruta");
            //
            crt = new Crt();
            crTs.getCrt().add(crt);
            crt.setId(3);
            crt.setDescricao("Regime Normal. (v2.0)");
            /**
             * END CRT [Código de Regime Tributário]
             */

            /**
             *
             *
             * END Emit [Identificação da Nota Fiscal eletrônica]
             *
             */


            /**
             *
             *
             * Begin Dest [Identificação do Destinatário da Nota Fiscal eletrônica]
             *
             */

            /**
             * Begin IndIEDest [Indicador da IE do Destinatário]
             */
            IndIEDest indIEDest;
            Dest.IndIEDests indIEDests = new Dest.IndIEDests();
            dest.setIndIEDests(indIEDests);
            //
            indIEDest = new IndIEDest();
            indIEDests.getIndIEDest().add(indIEDest);
            indIEDest.setId(1);
            indIEDest.setDescricao("Contribuinte ICMS");
            //
            indIEDest = new IndIEDest();
            indIEDests.getIndIEDest().add(indIEDest);
            indIEDest.setId(2);
            indIEDest.setDescricao("Contribuinte isento de ICMS");
            //
            indIEDest = new IndIEDest();
            indIEDests.getIndIEDest().add(indIEDest);
            indIEDest.setId(9);
            indIEDest.setDescricao("Não Contribuinte");
            /**
             * END IndIEDest [Indicador da IE do Destinatário]
             */
            /**
             *
             *
             * END Dest [Identificação do Destinatário da Nota Fiscal eletrônica]
             *
             */


            /**
             *
             *
             * Begin Transp [Informações do Transporte da NF-e]
             *
             */

            /**
             * Begin ModFrete [Modalidade do frete]
             */
            ModFrete modFrete;
            Transp.ModFretes modFretes = new Transp.ModFretes();
            transp.setModFretes(modFretes);
            //
            modFrete = new ModFrete();
            modFretes.getModFrete().add(modFrete);
            modFrete.setId(0);
            modFrete.setDescricao("Por conta do emitente");
            //
            modFrete = new ModFrete();
            modFretes.getModFrete().add(modFrete);
            modFrete.setId(1);
            modFrete.setDescricao("Por conta do destinatário/remetente");
            //
            modFrete = new ModFrete();
            modFretes.getModFrete().add(modFrete);
            modFrete.setId(2);
            modFrete.setDescricao("Por conta de terceiros");
            //
            modFrete = new ModFrete();
            modFretes.getModFrete().add(modFrete);
            modFrete.setId(9);
            modFrete.setDescricao("Sem frete");
            /**
             * END ModFrete [Modalidade do frete]
             */
            /**
             *
             *
             * END Transp [Informações do Transporte da NF-e]
             *
             */


            /**
             *
             *
             * Begin Sefaz []
             *
             */
            /**
             * Begin Status []
             */
            Status status;
            Sefaz.Statuss statuss = new Sefaz.Statuss();
            sefaz.setStatuss(statuss);
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(1);
            status.setDescricao("Digitação");
            status.setSimplificada("DIGITACAO");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(2);
            status.setDescricao("Assinada");
            status.setSimplificada("ASSINADA");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(100);
            status.setDescricao("Autorizado o uso da NF-e");
            status.setSimplificada("AUTORIZADA");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(102);
            status.setDescricao("Inutilização de número homologado");
            status.setSimplificada("INUTILIZADA");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(103);
            status.setDescricao("Lote recebido com sucesso");
            status.setSimplificada("LOTE_RECEBIDO");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(104);
            status.setDescricao("Lote processado");
            status.setSimplificada("LOTE_PROCESSADO");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(105);
            status.setDescricao("Lote em processamento");
            status.setSimplificada("LOTE_PROCESSAMENTO");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(106);
            status.setDescricao("Lote não localizado");
            status.setSimplificada("LOTE_NAO_LOCALIZADO");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(108);
            status.setDescricao("Paralização para manutenção");
            status.setSimplificada("PARALIZACAO_MANUTENCAO");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(109);
            status.setDescricao("Paralização por problemas tecnicos");
            status.setSimplificada("PARALIZACAO_PROBLEMAS");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(110);
            status.setDescricao("Uso Denegado");
            status.setSimplificada("DENEGADA");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(111);
            status.setDescricao("Consulta cadastro com uma ocorrência");
            status.setSimplificada("CONSULTA_CAD_UMA_OCORRENCIA");
            //
            status = new Status();
            statuss.getStatus().add(status);
            status.setId(112);
            status.setDescricao("Consulta cadastro com mais de uma ocorrência");
            status.setSimplificada("CONSULTA_CAD_VAIRAS_OCORRENCIAS");

            /**
             * END Status []
             */
            /**
             *
             *
             * END Sefaz []
             *
             */


            geraXml();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void geraXml() throws IOException, JAXBException {
        String xml = ServiceUtilXml.objectToXml(MYINFNFE);
        String diretorio = String.format("/Volumes/150GB-Development/CafePerfeito/cafeperfeito_v%s%s/configNfe.xml",
                VERSAO, PATHRESOURCES);

        FileWriter arquivo = new FileWriter(new File(diretorio));
        arquivo.write(xml);
        arquivo.close();
    }


}
