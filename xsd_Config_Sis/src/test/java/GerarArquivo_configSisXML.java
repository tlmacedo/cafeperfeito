import br.com.cafeperfeito.xsd.config_sis.config.*;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

public class GerarArquivo_configSisXML {

    //    public static TConfig configSistema;
    private static final String MYLOCALE = "pt_br";
    private static final String PATHRESOURCES = "/src/main/resources";
    private static final String VERSAO = "2.03";

    public static void main(String... args) {
        try {


            TConfig tConfig = new TConfig();

            tConfig.setMyLocale(MYLOCALE);
            tConfig.setTimeOut(BigInteger.valueOf(30));
            tConfig.setVersao(VERSAO);

            TConfig.Sis sis = new TConfig.Sis();
            TConfig.Sis.ConnectDB connectDB = new TConfig.Sis.ConnectDB();
            connectDB.setDbDriver("com.mysql.cj.jdbc.Driver");
            connectDB.setDbDriverConn("jdbc:mysql:");
            connectDB.setDbHost("127.0.0.1");
            connectDB.setDbPorta("3306");
            connectDB.setDbDatabase("cafeperfeito");
            connectDB.setDbAddConfig("?useSSL=false");
            connectDB.setDbUrl(String.format("%s//%s:%s/%s%s",
                    connectDB.getDbDriverConn(),
                    connectDB.getDbHost(),
                    connectDB.getDbPorta(),
                    connectDB.getDbDatabase(),
                    connectDB.getDbAddConfig()));
            connectDB.setDbUser("root");
            connectDB.setDbPass("Tlm$487901");
            sis.setConnectDB(connectDB);

            TConfig.Sis.FilesType filesType = new TConfig.Sis.FilesType();
            filesType.setToken(".cfg");
            filesType.setImagem(".png");
            sis.setFilesType(filesType);

            TConfig.Sis.MaskCaracter maskCaracter = new TConfig.Sis.MaskCaracter();
            maskCaracter.setDigit("#");
            maskCaracter.setUpper("U");
            maskCaracter.setLower("L");
            maskCaracter.setDigitoAndText("A");
            maskCaracter.setInterrogacao("?");
            maskCaracter.setAsterisco("*");
            sis.setMaskCaracter(maskCaracter);

            tConfig.setSis(sis);

            TConfig.InfLoja infLoja = new TConfig.InfLoja();
            infLoja.setId(BigInteger.valueOf(1));
            infLoja.setCnpj("08009246000136");
            infLoja.setTitulo("Café Perfeito");
            infLoja.setCopyright("\u00a9" + " Copyright 2006 - ");

            infLoja.setUf("AM");
            infLoja.setCUF(BigInteger.valueOf(13));
            infLoja.setMunicipio("Manaus");
            infLoja.setDdd(BigInteger.valueOf(92));
            infLoja.setDescMax(new BigDecimal("10.").setScale(2));
            infLoja.setBanco("NuBank");
            infLoja.setAgencia("0001");
            infLoja.setContaCorrente("90.846.088-3");

            TConfig.InfLoja.ImageDefaultProduto imageDefaultProduto = new TConfig.InfLoja.ImageDefaultProduto();
            imageDefaultProduto.setHeight(BigInteger.valueOf(250));
            imageDefaultProduto.setWidth(BigInteger.valueOf(250));
            infLoja.setImageDefaultProduto(imageDefaultProduto);

            tConfig.setInfLoja(infLoja);

            TConfig.Personalizacao personalizacao = new TConfig.Personalizacao();
            personalizacao.setTema("principal");
            personalizacao.setStyleSheets("/style/css/cafeperfeito.css");
            personalizacao.setStyleSheetsMin("/style/css/cafeperfeito.css");
            TConfig.Personalizacao.SplashImagens splashImagens = new TConfig.Personalizacao.SplashImagens();
            for (int i = 0; i <= 10; i++) {
                splashImagens.getImage().add("/image/splash/img_splash_coffe_" + i + ".gif");
            }
            personalizacao.setSplashImagens(splashImagens);
            tConfig.setPersonalizacao(personalizacao);

//            TConfig.Nfe nfe = new TConfig.Nfe();
//            nfe.setCertificadoCfg(pathResources + "/certificado/tokenSafeNet5100.cfg");
//            nfe.setCertificadoCacerts(pathResources + "/certificado/NFeCacerts");
//            nfe.setPortaSSl(443);
//            nfe.setVersao("4.00");
//            nfe.setIndSinc("0");
//            nfe.setNatOp(0);
//            nfe.setMod(55);
//            nfe.setSerie(1);
//            nfe.setTpNF(1);
//            nfe.setIdDest(1);
//            nfe.setCMunFG(1302603);
//            nfe.setTpImp(1);
//            nfe.setTpEmis(1);
//            nfe.setTpAmb(2);
//            nfe.setFinNFe(1);
//            nfe.setIndPres(3);
//            nfe.setProcEmi(0);
//            nfe.setIndPag(1);
//            nfe.setCPais("1058");
//            nfe.setNPais("BRASIL");
//            nfe.setCRT(3);
//            nfe.setVerProc(tConfig.getVersao());
//            nfe.setInfAdic("TOTAL R$ %s*t*%s*n*DADOS BANCARIOS PARA TRANSFERENCIA*n*BANCO %s AG: %s C/C: %s");
//
//            TConfig.Nfe.InfRespTec respTec = new TConfig.Nfe.InfRespTec();
//            respTec.setCnpj(infLoja.getCnpj());
//            respTec.setXContato("THIAGO LEIROS MACEDO");
//            respTec.setEmail("tl.macedo@hotmail.com");
//            respTec.setFone("92 98168-6148");
//
//            nfe.setInfRespTec(respTec);
//            tConfig.setNfe(nfe);

            StatusBars statusBars = new StatusBars();
            tConfig.setStatusBars(statusBars);

            MyForm myForm = new MyForm();
            statusBars.getMyForm().add(myForm);

            Stat stat;
            Status status = new Status();
            myForm.setName("saidaProduto");
            myForm.setStatus(status);
            //
            stat = new Stat();
            status.getStat().add(stat);
            stat.setId(0);
            stat.setTipo("DIGITACAO");
            stat.setDescricao("[F1-Novo]  [F2-Finalizar venda]  [F6-Cliente]  [F7-Pesquisa produto]  [F8-Itens venda]  [F9-nfe]  [F10-Print Lote NFe]  [F12-Sair]");
            //
            stat = new Stat();
            status.getStat().add(stat);
            stat.setId(1);
            stat.setTipo("FINALIZADA");
            stat.setDescricao("[F12-Sair]");

            TConfig.Paths paths = new TConfig.Paths();
            paths.setVersaoDanfeNFe(VERSAO);
            paths.setVersaoRecibo(VERSAO);
            paths.setPathDanfeNFe(String.format("/relatorio_v%1$s/recibo_v%1$s.jasper", paths.getVersaoDanfeNFe()));
            paths.setPathRecibo(String.format("/relatorio_v%1$s/danfe_v%1$s.jasper", paths.getVersaoRecibo()));
            paths.setPathSalvarArquivo(PATHRESOURCES);
            paths.setPathIconeSistema(String.format("/image/ico/%s/", personalizacao.getTema()));
            paths.setPathStyleSheets("/style/");
            paths.setPathFXML("/fxml/");
            paths.setPathDownloadImage(String.format("/volumes/macOS/Usuários/thiagomacedo/Imagens/%s/", connectDB.getDbDatabase()));
            paths.setPathNFeGetXmlIn("/xml/nfe/in/");
            paths.setPathNFeGetXmlOut("/xml/nfe/out/");
            paths.setPathCTeGetXmlIn("/xml/cte/in/");
            paths.setPathCTeGetXmlOut("/xml/cte/out/");
            paths.setPathNFeSaveXmlIn(String.format("%s%s", paths.getPathSalvarArquivo(), paths.getPathNFeGetXmlIn()));
            paths.setPathNFeSaveXmlOut(String.format("%s%s", paths.getPathSalvarArquivo(), paths.getPathNFeGetXmlOut()));
            paths.setPathCTeSaveXmlIn(String.format("%s%s", paths.getPathSalvarArquivo(), paths.getPathCTeGetXmlIn()));
            paths.setPathCTeSaveXmlOut(String.format("%s%s", paths.getPathSalvarArquivo(), paths.getPathCTeGetXmlOut()));

            tConfig.setPaths(paths);

            TConfig.Fxml fxml = new TConfig.Fxml();
            TConfig.Fxml.Login login = new TConfig.Fxml.Login();
            login.setFxml(paths.getPathFXML() + "FxmlLogin.fxml");
            login.setTitulo("Login no sistema");
            login.setIcone(paths.getPathIconeSistema() + "ic_cadeado_dp24.png");
            fxml.setLogin(login);

            TConfig.Fxml.Principal principal = new TConfig.Fxml.Principal();
            principal.setFxml(paths.getPathFXML() + "FxmlPrincipal.fxml");
            principal.setTitulo("Café Perfeito");
            principal.setIconeAtivo(paths.getPathIconeSistema().replace("/principal", "") + "ic_coffee_ativo_dp24.png");
            principal.setIconeInativo(paths.getPathIconeSistema().replace("/principal", "") + "ic_coffee_inativo_dp24.png");
            fxml.setPrincipal(principal);

            TConfig.Fxml.CadastroProduto cadastroProduto = new TConfig.Fxml.CadastroProduto();
            cadastroProduto.setFxml(paths.getPathFXML() + "FxmlCadastroProduto.fxml");
            cadastroProduto.setTitulo("Cadastro de produtos");
            cadastroProduto.setIcone(paths.getPathIconeSistema());
            fxml.setCadastroProduto(cadastroProduto);

            TConfig.Fxml.CadastroEmpresa cadastroEmpresa = new TConfig.Fxml.CadastroEmpresa();
            cadastroEmpresa.setFxml(paths.getPathFXML() + "FxmlCadastroEmpresa.fxml");
            cadastroEmpresa.setTitulo("Cadastro de empresas");
            cadastroEmpresa.setIcone(paths.getPathIconeSistema());
            fxml.setCadastroEmpresa(cadastroEmpresa);

            TConfig.Fxml.EntradaProduto entradaProduto = new TConfig.Fxml.EntradaProduto();
            entradaProduto.setFxml(paths.getPathFXML() + "FxmlEntradaProduto.fxml");
            entradaProduto.setTitulo("Entrada de produtos");
            entradaProduto.setIcone(paths.getPathIconeSistema());
            fxml.setEntradaProduto(entradaProduto);

            TConfig.Fxml.SaidaProduto saidaProduto = new TConfig.Fxml.SaidaProduto();
            saidaProduto.setFxml(paths.getPathFXML() + "FxmlSaidaProduto.fxml");
            saidaProduto.setTitulo("Saida de produtos");
            saidaProduto.setIcone(paths.getPathIconeSistema());
            fxml.setSaidaProduto(saidaProduto);

            TConfig.Fxml.ContasAReceber contasAReceber = new TConfig.Fxml.ContasAReceber();
            contasAReceber.setFxml(paths.getPathFXML() + "FxmlContasAReceber.fxml");
            contasAReceber.setTitulo("Contas a receber");
            contasAReceber.setIcone(paths.getPathIconeSistema());
            fxml.setContasAReceber(contasAReceber);

            TConfig.Fxml.PedidoNFe pedidoNFe = new TConfig.Fxml.PedidoNFe();
            pedidoNFe.setFxml(String.format("%s%s.fxml", paths.getPathFXML(), "FxmlPedidoNFe"));
            pedidoNFe.setTitulo("Pedidos e Notas Fiscais Eletrônicas");
            pedidoNFe.setIcone(paths.getPathIconeSistema());
            fxml.setPedidoNFe(pedidoNFe);

            TConfig.Fxml.ContasAPagar contasAPagar = new TConfig.Fxml.ContasAPagar();
            contasAPagar.setFxml(paths.getPathFXML() + "FxmlContasAPagar.fxml");
            contasAPagar.setTitulo("Contas a pagar");
            contasAPagar.setIcone(paths.getPathIconeSistema());
            fxml.setContasAPagar(contasAPagar);

            TConfig.Fxml.Recebimento recebimento = new TConfig.Fxml.Recebimento();
            recebimento.setFxml(paths.getPathFXML() + "FxmlRecebimento.fxml");
            recebimento.setTitulo("Recebimento");
            recebimento.setIcone(paths.getPathIconeSistema());
            fxml.setRecebimento(recebimento);

            tConfig.setFxml(fxml);

            TConfig.Ws ws = new TConfig.Ws();
            TConfig.Ws.Barcode barcode = new TConfig.Ws.Barcode();
            //barcode.setUrl("http://bwipjs-api.metafloor.com/?bcid=ean13&includetext&scale=1&guardwhitespace&text=");
            barcode.setUrl("");
            ws.setBarcode(barcode);

            TConfig.Ws.Cosmos cosmos = new TConfig.Ws.Cosmos();
            cosmos.setUrl("https://api.cosmos.bluesoft.com.br");
            cosmos.setConsultaNcm(cosmos.getUrl() + "/ncms/");
            cosmos.setConsultaGtins(cosmos.getUrl() + "/gtins/");
            cosmos.setConsultaGpcs(cosmos.getUrl() + "/gpcs/");
            cosmos.setToken("o65EDRPgFu7mFNuv5vj5Aw");
            ws.setCosmos(cosmos);

            TConfig.Ws.Receitaws receitaws = new TConfig.Ws.Receitaws();
            receitaws.setUrl("https://www.receitaws.com.br/v1/cnpj/");
            receitaws.setToken("1953100c818519b43b895394c25b0fa38525e2800587a8b140a42e6baff7a8af");
            ws.setReceitaws(receitaws);

            TConfig.Ws.Webmania webmania = new TConfig.Ws.Webmania();
            webmania.setUrl("https://webmaniabr.com/api/1/cep/");
            webmania.setAppkey("GOxHMxSXNbX99szfTE7A6mMDmb26P1Ch");
            webmania.setAppsecret("kMx5QczId1GqVLbpZ52qgEgfRhiKWFPZfa39IZfp6NZhFmTq");
            ws.setWebmania(webmania);

            TConfig.Ws.Postmon postmon = new TConfig.Ws.Postmon();
            postmon.setUrl("http://api.postmon.com.br/v1/cep/");
            ws.setPostmon(postmon);

            TConfig.Ws.Portabilidadecelular portabilidadecelular = new TConfig.Ws.Portabilidadecelular();
            portabilidadecelular.setPass("Tlm487901");
            portabilidadecelular.setUser("tlmacedo");
            portabilidadecelular.setUrl(String.format("http://consultas.portabilidadecelular.com/painel/consulta_numero.php",
                    portabilidadecelular.getUser(), portabilidadecelular.getPass()));
            ws.setPortabilidadecelular(portabilidadecelular);

            tConfig.setWs(ws);

            String xml = ServiceUtilXml.objectToXml(tConfig);
            //"/Volumes/150GB-Development/cafeperfeito/cafeperfeito_v1.03/src/main/resources/xml/configSistema.xml"
            //"/Volumes/150GB-Development/cafeperfeito/cafeperfeito_v1.03/src/main/resources/xml/configSistema.xml"
            String diretorio = String.format("/Volumes/150GB-Development/CafePerfeito/cafeperfeito_v%s%s/configSis.xml",
                    VERSAO, PATHRESOURCES);
            FileWriter arquivo = new FileWriter(new File(diretorio));
            arquivo.write(xml);
            arquivo.close();

            //xml = ServiceXmlUtil.leXml(new FileInputStream("/Volumes/150GB-Development/Java/Intellij/sidtmcafe10/src/sidtmcafe/resources/xml/configSistema.xml"));
//            xml = ServiceXmlUtil.leXml(new FileInputStream("/Users/thiagomacedo/Desktop/configSistema.xml"));
//            configSistema = ServiceXmlUtil.xmlToObject(xml, TConfig.class);

//        System.out.println(String.format("%s: [%s]", "getIdLoja", configSistema.getIdLoja()));
//        System.out.println(String.format("%s: [%s]", "getTituloLoja", configSistema.getTituloLoja()));
//        System.out.println(String.format("%s: [%s]", "getCopyright", configSistema.getCopyright()));
//        System.out.println(String.format("%s: [%s]", "getTheme", configSistema.getTheme()));
//        System.out.println(String.format("%s: [%s]", "getDdd", configSistema.getDdd()));
//        System.out.println(String.format("%s: [%s]", "getPortaSSL", configSistema.getPortaSSL()));
//        System.out.println(String.format("%s: [%s]", "getMyLocale", configSistema.getMyLocale()));
//        System.out.println(String.format("%s: [%s]", "Locale.getDefault()", Locale.getDefault()));
//            Locale.setDefault(new Locale(configSistema.getInfLoja().getMyLocale().substring(0, 2), configSistema.getInfLoja().getMyLocale().substring(3)));
//        System.out.println(String.format("%s: [%s]", "getImagemProdutoHeight", configSistema.getImagemProdutoHeight()));
//        System.out.println(String.format("%s: [%s]", "getPathDownloadImage", configSistema.getPathDownloadImage()));
//        System.out.println(String.format("%s: [%s]", "getExtensaoArquivoToken", configSistema.getExtensaoArquivoToken()));
//        System.out.println(String.format("%s: [%s]", "getPathStyleSheets", configSistema.getPathStyleSheets()));
//        System.out.println(String.format("%s: [%s]", "getExtensaoArquivoImagemDownload", configSistema.getExtensaoArquivoImagemDownload()));
//        System.out.println(String.format("%s: [%s]", "getCryptPalavraChave", configSistema.getCryptPalavraChave()));
//        System.out.println(String.format("%s: [%s]", "getCryptInitVector", configSistema.getCryptInitVector()));
//        System.out.println(String.format("%s: [%s]", "getMaskCaracterDigito", configSistema.getMaskCaracterDigito()));
//        System.out.println(String.format("%s: [%s]", "getMaskCaracterUpper", configSistema.getMaskCaracterUpper()));
//        System.out.println(String.format("%s: [%s]", "getMaskCaracterLower", configSistema.getMaskCaracterLower()));
//        System.out.println(String.format("%s: [%s]", "getMaskCaracterDigitoEText", configSistema.getMaskCaracterDigitoEText()));
//        System.out.println(String.format("%s: [%s]", "getMaskCaracterInterrogacao", configSistema.getMaskCaracterInterrogacao()));
//        System.out.println(String.format("%s: [%s]", "getMaskCaracterAsterisco", configSistema.getMaskCaracterAsterisco()));

//        System.out.println(String.format("%s: [%s]","", configSistema));
//        System.out.println(String.format("%s: [%s]","", configSistema));
//        System.out.println(String.format("%s: [%s]","", configSistema));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
