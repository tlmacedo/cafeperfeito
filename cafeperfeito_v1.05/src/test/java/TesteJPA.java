import br.com.tlmacedo.cafeperfeito.model.dao.SaidaProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProduto;
import br.com.tlmacedo.cafeperfeito.service.ServiceJSonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TesteJPA {


    @SuppressWarnings("Duplicates")
    private static Pattern pattern;
    private static Matcher matcher;

    public static void main(String[] args) throws IOException {

//        System.out.printf("qual empresa vc deseja verificar?: ");
//        Empresa empresa = new EmpresaDAO().getById(Empresa.class, Long.valueOf(new Scanner(System.in).nextLine()));
//
//        System.out.printf("cnpj: [%s]\n", empresa.getCnpj());
//        System.out.printf(empresa.toString());
//        ServiceJSonUtil.printJsonFromObject(empresa, "Empresa");

//        System.out.printf("qual saida de produto vc deseja verificar?: ");
//        SaidaProduto saidaProduto = new SaidaProdutoDAO().getById(SaidaProduto.class, Long.valueOf(new Scanner(System.in).nextLine()));
//
//        ServiceJSonUtil.printJsonFromObject(saidaProduto.getCliente(), "\nCliente");
//
//        ServiceJSonUtil.printJsonFromObject(saidaProduto, "\nSaida de Produto");


        //System.out.printf("qual saida de produto vc deseja verificar?: ");
        List<SaidaProduto> saidaProdutoList = new SaidaProdutoDAO().getAll(SaidaProduto.class, null, null, null, null);

        StringBuilder documento = new StringBuilder("");
        documento.append(ServiceJSonUtil.getJsonFromList(saidaProdutoList));
        ServiceJSonUtil.printJsonFromList(saidaProdutoList, null);

//        List<EntradaProduto> entradaProdutoList = new EntradaProdutoDAO().getAll(EntradaProduto.class, null, null, null, null);
//
//        StringBuilder documento = new StringBuilder("");
//        documento.append(ServiceJSonUtil.getJsonFromList(entradaProdutoList));
//        ServiceJSonUtil.printJsonFromList(entradaProdutoList, null);


        System.out.printf("\n\nFinalizamos por aqui!!!!");

        String diretorio = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito_v1.03/src/main/resources/xml/nfe/out/SaidaProdutos";
        FileWriter arquivo = new FileWriter(new File(diretorio + ".txt"));
        arquivo.write(documento.toString());
        arquivo.close();

        arquivo = new FileWriter(new File(diretorio + ".xml"));
        arquivo.write("");
        arquivo.close();


//        String diretorio = fileEnviNFe.substring(0, fileEnviNFe.length() - 4) + "_assinado.xml";
//        FileWriter arquivo = new FileWriter(new File(diretorio));
//        arquivo.write(xmlNFeAssinado);
//        arquivo.close();


//        HashMap<String, String> hashMap;
//
////        BigDecimal meuValor = ServiceMascara.getBigDecimalFromTextField("0,02", 2);
//        String getJfxTextField = "0,01";
//
//        System.out.printf("length: [%d]\n", getJfxTextField.length()-1);
//        System.out.printf("index: [%d]\n", getJfxTextField.indexOf(","));
//
//        BigDecimal meuValor =  ServiceMascara.getBigDecimalFromTextField(getJfxTextField,
//                (getJfxTextField.length() - getJfxTextField.indexOf(",")));
//        String strHasMap = String.format("%s::%s;", MIN_BIG, "0.01");
//        System.out.printf("strHasMap: [%1$s]\n", strHasMap);
//
//        hashMap = ServiceMascara.getFieldFormatMap(strHasMap);
//        System.out.printf("hashMap: [%1$s]\n", hashMap);
//
//        System.out.printf("meuValor: [%s]\n", meuValor);
//
//        BigDecimal bigMin = new BigDecimal(hashMap.get(MIN_BIG.getDescricao()));
//
//        System.out.printf("MIN_BIG: [%s]\n", bigMin);
//
//        System.out.printf("\nresultado: [%s]\n", meuValor.compareTo(bigMin));

//        String data = "data abertura: 28/04/2006";
//        pattern = Pattern.compile(REGEX_DTF_DATA);
//        matcher = pattern.matcher(data);
//        System.out.printf("data: [%s]\n", data);
//        if (matcher.find()) {
//            System.out.printf("matcher1: [%s]\n", matcher.group());
//            LocalDate localDate = LocalDate.parse(matcher.group(), DTF_DATA);
//            System.out.printf("matcher2: [%s]\n", localDate);
//            System.out.printf("dt: [%s]\n", LocalDate.parse(matcher.group(),DTF_DATA).atTime(0,0,0));
//        }


//        String ean = "7891000140307";
//        System.out.printf("result: = %s\n", ServiceValidarDado.isEan13Valido(ean));


//        new ServiceVariaveisSistema().getVariaveisSistemaSimples();


//        new ServiceRecibo().imprimeRecibo(new RecebimentoDAO().getById(Recebimento.class, Long.valueOf(3)));


//        BigDecimal prcFabrica = new BigDecimal("25.5");
//        System.out.printf("prcFabrica: [%s]\n", prcFabrica);
//        System.out.printf("prcFabrica: [%s]\n", prcFabrica.setScale(2));
//        BigDecimal prcConsumidor = new BigDecimal("65");
//        System.out.printf("prcConsumidor: [%s]\n", prcConsumidor);
//        System.out.printf("prcConsumidor: [%s]\n", prcConsumidor.setScale(2));
//
//
//        BigDecimal margem = prcConsumidor.divide(prcFabrica, 2, RoundingMode.HALF_DOWN);
//        System.out.printf("margem: [%s]\n", margem);
//        System.out.printf("margem: [%s]\n", margem.setScale(2));
//        BigDecimal margem10 = prcConsumidor.divide(prcFabrica, 2, RoundingMode.HALF_DOWN).subtract(new BigDecimal("1"));
//        System.out.printf("margem10: [%s]\n", margem10);
//        System.out.printf("margem10: [%s]\n", margem10.setScale(2));
//        BigDecimal margem20 = prcConsumidor.divide(prcFabrica, 2, RoundingMode.HALF_DOWN).subtract(new BigDecimal("1")).multiply(new BigDecimal("100"));
//        System.out.printf("margem20: [%s]\n", margem20);
//        System.out.printf("margem20: [%s]\n\n\n", margem20.setScale(2));
//
//        BigDecimal newPrcFabrica = new BigDecimal("25.500001");
//        System.out.printf("result: [%s]\n\n", prcFabrica.compareTo(newPrcFabrica));
//        System.out.printf("newPrcFabrica: [%s]\n", newPrcFabrica);
//        System.out.printf("newPrcFabrica: [%s]\n", newPrcFabrica.setScale(2));
//        margem = prcConsumidor.divide(prcFabrica, 2, RoundingMode.HALF_DOWN).subtract(new BigDecimal("1")).multiply(new BigDecimal("100"));
//        System.out.printf("margem: [%s]\n", margem);
//        System.out.printf("margem: [%s]\n", margem.setScale(2));
//        BigDecimal newPrcConsumidor = margem.divide(new BigDecimal("100"), 2, RoundingMode.HALF_DOWN).add(BigDecimal.ONE).multiply(newPrcFabrica);
//        System.out.printf("newPrcConsumidor: [%s]\n", newPrcConsumidor);
//        System.out.printf("newPrcConsumidor: [%s]\n", newPrcConsumidor.setScale(2, RoundingMode.HALF_DOWN));

//        Double prcFabrica = produto.getPrecoFabrica().doubleValue();
//        Double margem = (((prcConsumidor / prcFabrica) - 1) * 100);
//        System.out.printf("fabrica: [%s]\n", produto.getPrecoFabrica());
//        BigDecimal margem = produto.getPrecoConsumidor().divide(produto.getPrecoFabrica(), RoundingMode.HALF_EVEN).subtract(BigDecimal.valueOf(-1)).multiply(BigDecimal.valueOf(100));
//        System.out.printf("margem: [%s]\n", margem);
//        System.out.printf("consumidor: [%s]\n", produto.getPrecoConsumidor());
//        produto.setPrecoFabrica(entradaProdutoProduto.getVlrFabrica());
//        System.out.printf("newFabrica: [%s]\n", produto.getPrecoFabrica());
//        System.out.printf("margem: [%s]\n", margem);
//        BigDecimal consumidor = ((produto.getPrecoFabrica().multiply(margem)).divide(BigDecimal.valueOf(100))).add(produto.getPrecoFabrica());
//        System.out.printf("newConsumidor: [%s]\n", consumidor);
//        produto.setPrecoConsumidor(consumidor.setScale(2, RoundingMode.HALF_EVEN));

//        ServiceJSonUtil.printJsonFromObject(new EmpresaDAO().getById(Empresa.class, 1L));

//        List<EntradaTemp> entradaTempList = new EntradaTempDAO().getAll(EntradaTemp.class, null);
//        EntradaTemp temp = entradaTempList.get(0);
//
//        ObjectMapper mapper = new ObjectMapper();
//        EntradaProduto entradaProduto = mapper.readValue(temp.getTemp(), EntradaProduto.class);
//
//        System.out.printf("0001: [%s]\n", entradaProduto);
//        ServiceJSonUtil.printJsonFromObject(entradaProduto);

//        EntradaProduto_Json entProd_Json = null;
//        entProd_Json = mapper.readValue(temp.getTemp(), EntradaProduto_Json.class);
//
//        if (entProd_Json == null)
//            System.out.println("deu merda");
//        System.out.printf("nfe: [%s]\n", entProd_Json);
//        System.out.println("deu certo!!!");


//        System.out.println("qual id vc deseja verificar?:");
//        Usuario usuarioVO = new Usuario();
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//        usuarioVO = usuarioDAO.getById(Usuario.class, Long.valueOf(new Scanner(System.in).nextLine()));
////        ModelMapper modelMapper = new ModelMapper();
////        UsuarioDTO usuarioDTO = modelMapper.map(usuarioVO, UsuarioDTO.class);
//        List list = new ArrayList();
//        list.add(usuarioVO);
//        ServiceImprimirListaJSon.printJsonFromObject(list);
//
////        TelefoneDAO telefoneDAO = new TelefoneDAO();
////        ServiceImprimirListaJSon.printJsonFromObject(telefoneDAO.getAll(TelefoneDAO.class));

//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        ServiceJSonUtil.printJsonFromList(produtoDAO.getAll(Produto.class, null));
//        List<Produto> list = new ArrayList();
////        Long id = Long.valueOf(new Scanner(System.in).nextLine());
////        System.out.printf("id: [%s]\n", id.toString());
//        list.add(produtoDAO.getById(Produto.class, id));
//        ServiceJSonUtil.printJsonFromObject();


//        List<EntradaProduto> list = new ArrayList();
////        Long id = Long.valueOf(new Scanner(System.in).nextLine());
////        System.out.printf("id: [%s]\n", id.toString());
//        list = new EntradaProdutoDAO().getAll(EntradaProduto.class,null);
//        ServiceJSonUtil.printJsonFromObject(list);
    }

    private static String getText() {
        return "{\n" +
                "  \"id\": 0,\n" +
                "  \"situacao\": \"NULL\",\n" +
                "  \"loja\": {\n" +
                "    \"id\": 1,\n" +
                "    \"lojaSistema\": true,\n" +
                "    \"cnpj\": \"08009246000136\",\n" +
                "    \"ieIsento\": false,\n" +
                "    \"ie\": \"042171865\",\n" +
                "    \"razao\": \"T. L. MACEDO\",\n" +
                "    \"fantasia\": \"CAFE PERFEITO\",\n" +
                "    \"cliente\": true,\n" +
                "    \"fornecedor\": true,\n" +
                "    \"transportadora\": true\n" +
                "  }\n" +
                "}";
    }


    private static String getText123() {
        return "{\n" +
                "  \"id\" : 0,\n" +
                "  \"situacao\" : \"NULL\",\n" +
                "  \"loja\" : {\n" +
                "    \"children\" : [ ],\n" +
                "    \"groupedColumn\" : null,\n" +
                "    \"groupedValue\" : null,\n" +
                "    \"id\" : 1,\n" +
                "    \"lojaSistema\" : true,\n" +
                "    \"cnpj\" : \"08009246000136\",\n" +
                "    \"ieIsento\" : false,\n" +
                "    \"ie\" : \"042171865\",\n" +
                "    \"razao\" : \"T. L. MACEDO\",\n" +
                "    \"fantasia\" : \"CAFE PERFEITO\",\n" +
                "    \"cliente\" : true,\n" +
                "    \"fornecedor\" : true,\n" +
                "    \"transportadora\" : true,\n" +
                "    \"dataCadastro\" : \"28/04/2006 01:00:00\",\n" +
                "    \"dataAtualizacao\" : \"13/03/2019 15:59:34\",\n" +
                "    \"dataAbetura\" : \"28/04/2006 01:00:00\",\n" +
                "    \"naturezaJuridica\" : \"213-5 - EMPRESÁRIO (INDIVIDUAL)\",\n" +
                "    \"observacoes\" : \"WebService:\\nreceitaWs:\\tgerouCobrança: [false]\\tBD_Ws: [true]\\n\",\n" +
                "    \"classifJuridica\" : \"PESSOAJURIDICA\",\n" +
                "    \"situacao\" : \"ATIVO\",\n" +
                "    \"enderecoList\" : [ {\n" +
                "      \"id\" : 1,\n" +
                "      \"cep\" : \"69080440\",\n" +
                "      \"logradouro\" : \"R TREZE DE MAIO\",\n" +
                "      \"numero\" : \"159\",\n" +
                "      \"complemento\" : \"\",\n" +
                "      \"bairro\" : \"COROADO\",\n" +
                "      \"prox\" : \"\",\n" +
                "      \"municipio\" : {\n" +
                "        \"id\" : 112,\n" +
                "        \"descricao\" : \"MANAUS\",\n" +
                "        \"capital\" : true,\n" +
                "        \"ibge_codigo\" : \"1302603\",\n" +
                "        \"ddd\" : 92,\n" +
                "        \"uf\" : {\n" +
                "          \"id\" : 13,\n" +
                "          \"descricao\" : \"Amazonas\",\n" +
                "          \"sigla\" : \"AM\",\n" +
                "          \"ddd\" : 92\n" +
                "        }\n" +
                "      },\n" +
                "      \"tipo\" : \"PRINCIPAL\"\n" +
                "    }, {\n" +
                "      \"id\" : 2,\n" +
                "      \"cep\" : \"69067360\",\n" +
                "      \"logradouro\" : \"TRAVESSA NOVA OLINDA (CJ JD PETRÓPOLIS)\",\n" +
                "      \"numero\" : \"90\",\n" +
                "      \"complemento\" : \"\",\n" +
                "      \"bairro\" : \"PETRÓPOLIS\",\n" +
                "      \"prox\" : \"\",\n" +
                "      \"municipio\" : {\n" +
                "        \"id\" : 112,\n" +
                "        \"descricao\" : \"MANAUS\",\n" +
                "        \"capital\" : true,\n" +
                "        \"ibge_codigo\" : \"1302603\",\n" +
                "        \"ddd\" : 92,\n" +
                "        \"uf\" : {\n" +
                "          \"id\" : 13,\n" +
                "          \"descricao\" : \"Amazonas\",\n" +
                "          \"sigla\" : \"AM\",\n" +
                "          \"ddd\" : 92\n" +
                "        }\n" +
                "      },\n" +
                "      \"tipo\" : \"RESIDENCIAL\"\n" +
                "    } ],\n" +
                "    \"emailHomePageList\" : [ {\n" +
                "      \"id\" : 1,\n" +
                "      \"descricao\" : \"cafeperfeito@cafeperfeito.com.br\",\n" +
                "      \"tipo\" : \"EMAIL\"\n" +
                "    }, {\n" +
                "      \"id\" : 2,\n" +
                "      \"descricao\" : \"www.cafeperfeito.com.br\",\n" +
                "      \"tipo\" : \"NULL\"\n" +
                "    }, {\n" +
                "      \"id\" : 3,\n" +
                "      \"descricao\" : \"contato@cafeperfeito.com.br\",\n" +
                "      \"tipo\" : \"EMAIL\"\n" +
                "    }, {\n" +
                "      \"id\" : 66,\n" +
                "      \"descricao\" : \"www.cafeperfeito.com.br\",\n" +
                "      \"tipo\" : \"HOMEPAGE\"\n" +
                "    } ],\n" +
                "    \"telefoneList\" : [ {\n" +
                "      \"id\" : 1,\n" +
                "      \"descricao\" : \"9238776148\",\n" +
                "      \"telefoneOperadora\" : {\n" +
                "        \"id\" : 314,\n" +
                "        \"descricao\" : \"Embratel - Fixo\",\n" +
                "        \"codWsPortabilidade\" : \"55121\"\n" +
                "      }\n" +
                "    } ],\n" +
                "    \"contatoList\" : [ {\n" +
                "      \"id\" : 1,\n" +
                "      \"descricao\" : \"THIAGO MACEDO\",\n" +
                "      \"cargo\" : {\n" +
                "        \"id\" : 2,\n" +
                "        \"descricao\" : \"PROPRIETARIO\"\n" +
                "      },\n" +
                "      \"emailHomePageList\" : [ {\n" +
                "        \"id\" : 5,\n" +
                "        \"descricao\" : \"tl.macedo@hotmail.com\",\n" +
                "        \"tipo\" : \"EMAIL\"\n" +
                "      }, {\n" +
                "        \"id\" : 6,\n" +
                "        \"descricao\" : \"thiago@cafeperfeito.com.br\",\n" +
                "        \"tipo\" : \"EMAIL\"\n" +
                "      } ],\n" +
                "      \"telefoneList\" : [ {\n" +
                "        \"id\" : 2,\n" +
                "        \"descricao\" : \"92981686148\",\n" +
                "        \"telefoneOperadora\" : {\n" +
                "          \"id\" : 126,\n" +
                "          \"descricao\" : \"Claro - Celular\",\n" +
                "          \"codWsPortabilidade\" : \"55321\"\n" +
                "        }\n" +
                "      } ]\n" +
                "    }, {\n" +
                "      \"id\" : 2,\n" +
                "      \"descricao\" : \"CARLA MACEDO\",\n" +
                "      \"cargo\" : {\n" +
                "        \"id\" : 3,\n" +
                "        \"descricao\" : \"GERENTE\"\n" +
                "      },\n" +
                "      \"emailHomePageList\" : [ {\n" +
                "        \"id\" : 8,\n" +
                "        \"descricao\" : \"carlinha.figueiredo32@hotmail.com\",\n" +
                "        \"tipo\" : \"EMAIL\"\n" +
                "      } ],\n" +
                "      \"telefoneList\" : [ {\n" +
                "        \"id\" : 4,\n" +
                "        \"descricao\" : \"92992412974\",\n" +
                "        \"telefoneOperadora\" : {\n" +
                "          \"id\" : 126,\n" +
                "          \"descricao\" : \"Claro - Celular\",\n" +
                "          \"codWsPortabilidade\" : \"55321\"\n" +
                "        }\n" +
                "      } ]\n" +
                "    } ],\n" +
                "    \"infoReceitaFederalList\" : [ {\n" +
                "      \"id\" : 332,\n" +
                "      \"code\" : \"47.53-9-00\",\n" +
                "      \"text\" : \"Comércio varejista especializado de eletrodomésticos e equipamentos de áudio e vídeo\",\n" +
                "      \"tipo\" : \"PRINCIPAL\",\n" +
                "      \"detalheInfoReceitaFederal\" : \"[item]: 47.53-9-00\\r\\n    [Descrição]: Comércio varejista especializado de eletrodomésticos e equipamentos de\\r\\n áudio e vídeo\"\n" +
                "    }, {\n" +
                "      \"id\" : 333,\n" +
                "      \"code\" : \"85.99-6-04\",\n" +
                "      \"text\" : \"Treinamento em desenvolvimento profissional e gerencial\",\n" +
                "      \"tipo\" : \"SECUNDARIA\",\n" +
                "      \"detalheInfoReceitaFederal\" : \"[item]: 85.99-6-04\\r\\n    [Descrição]: Treinamento em desenvolvimento profissional e gerencial\"\n" +
                "    }, {\n" +
                "      \"id\" : 334,\n" +
                "      \"code\" : \"77.33-1-00\",\n" +
                "      \"text\" : \"Aluguel de máquinas e equipamentos para escritórios\",\n" +
                "      \"tipo\" : \"SECUNDARIA\",\n" +
                "      \"detalheInfoReceitaFederal\" : \"[item]: 77.33-1-00\\r\\n    [Descrição]: Aluguel de máquinas e equipamentos para escritórios\"\n" +
                "    }, {\n" +
                "      \"id\" : 335,\n" +
                "      \"code\" : \"33.14-7-10\",\n" +
                "      \"text\" : \"Manutenção e reparação de máquinas e equipamentos para uso geral não especificados anteriormente\",\n" +
                "      \"tipo\" : \"SECUNDARIA\",\n" +
                "      \"detalheInfoReceitaFederal\" : \"[item]: 33.14-7-10\\r\\n    [Descrição]: Manutenção e reparação de máquinas e equipamentos para uso geral não e\\r\\nspecificados anteriormente\"\n" +
                "    }, {\n" +
                "      \"id\" : 336,\n" +
                "      \"code\" : \"33.29-5-01\",\n" +
                "      \"text\" : \"Serviços de montagem de móveis de qualquer material\",\n" +
                "      \"tipo\" : \"SECUNDARIA\",\n" +
                "      \"detalheInfoReceitaFederal\" : \"[item]: 33.29-5-01\\r\\n    [Descrição]: Serviços de montagem de móveis de qualquer material\"\n" +
                "    }, {\n" +
                "      \"id\" : 337,\n" +
                "      \"code\" : \"Capital social\",\n" +
                "      \"text\" : \"10.000,00\",\n" +
                "      \"tipo\" : \"QSA\",\n" +
                "      \"detalheInfoReceitaFederal\" : \"[item]: Capital social\\r\\n    [Descrição]: 10.000,00\"\n" +
                "    } ]\n" +
                "  },\n" +
                "  \"entradaNfe\" : {\n" +
                "    \"id\" : 0,\n" +
                "    \"chave\" : \"35190306981833000248550010000001501580649055\",\n" +
                "    \"numero\" : \"150\",\n" +
                "    \"serie\" : \"1\",\n" +
                "    \"modelo\" : \"MOD55\",\n" +
                "    \"dataEmissao\" : \"01/03/2019 20:40:49\",\n" +
                "    \"dataEntrada\" : \"15/03/2019 20:40:49\",\n" +
                "    \"emissor\" : {\n" +
                "      \"children\" : [ ],\n" +
                "      \"groupedColumn\" : null,\n" +
                "      \"groupedValue\" : null,\n" +
                "      \"id\" : 70,\n" +
                "      \"lojaSistema\" : false,\n" +
                "      \"cnpj\" : \"06981833000248\",\n" +
                "      \"ieIsento\" : false,\n" +
                "      \"ie\" : \"635858196113\",\n" +
                "      \"razao\" : \"TORREFACAO NISHIDA SAN LTDA\",\n" +
                "      \"fantasia\" : \"***\",\n" +
                "      \"cliente\" : false,\n" +
                "      \"fornecedor\" : true,\n" +
                "      \"transportadora\" : false,\n" +
                "      \"dataCadastro\" : \"14/03/2018 11:00:07\",\n" +
                "      \"dataAtualizacao\" : \"13/03/2019 16:00:49\",\n" +
                "      \"dataAbetura\" : \"15/10/2015 00:00:00\",\n" +
                "      \"naturezaJuridica\" : \"206-2 - SOCIEDADE EMPRESÁRIA LIMITADA\",\n" +
                "      \"observacoes\" : \"WebService:\\nreceitaWs:\\tgerouCobrança: [false]\\tBD_Ws: [true]\\n\",\n" +
                "      \"classifJuridica\" : \"PESSOAJURIDICA\",\n" +
                "      \"situacao\" : \"ATIVO\",\n" +
                "      \"enderecoList\" : [ {\n" +
                "        \"id\" : 71,\n" +
                "        \"cep\" : \"09726150\",\n" +
                "        \"logradouro\" : \"AVENIDA ANTARTICO\",\n" +
                "        \"numero\" : \"175\",\n" +
                "        \"complemento\" : \"CONJ 2A\",\n" +
                "        \"bairro\" : \"JARDIM DO MAR\",\n" +
                "        \"prox\" : \"\",\n" +
                "        \"municipio\" : {\n" +
                "          \"id\" : 3813,\n" +
                "          \"descricao\" : \"SAO BERNARDO DO CAMPO\",\n" +
                "          \"capital\" : false,\n" +
                "          \"ibge_codigo\" : \"3548708\",\n" +
                "          \"ddd\" : 11,\n" +
                "          \"uf\" : {\n" +
                "            \"id\" : 35,\n" +
                "            \"descricao\" : \"São Paulo\",\n" +
                "            \"sigla\" : \"SP\",\n" +
                "            \"ddd\" : 11\n" +
                "          }\n" +
                "        },\n" +
                "        \"tipo\" : \"PRINCIPAL\"\n" +
                "      } ],\n" +
                "      \"emailHomePageList\" : [ {\n" +
                "        \"id\" : 54,\n" +
                "        \"descricao\" : \"marvil@netsite.com.br\",\n" +
                "        \"tipo\" : \"EMAIL\"\n" +
                "      } ],\n" +
                "      \"telefoneList\" : [ {\n" +
                "        \"id\" : 140,\n" +
                "        \"descricao\" : \"3535911810\",\n" +
                "        \"telefoneOperadora\" : {\n" +
                "          \"id\" : 323,\n" +
                "          \"descricao\" : \"ALGAR Telecom\",\n" +
                "          \"codWsPortabilidade\" : \"55112\"\n" +
                "        }\n" +
                "      }, {\n" +
                "        \"id\" : 141,\n" +
                "        \"descricao\" : \"3535911739\",\n" +
                "        \"telefoneOperadora\" : {\n" +
                "          \"id\" : 323,\n" +
                "          \"descricao\" : \"ALGAR Telecom\",\n" +
                "          \"codWsPortabilidade\" : \"55112\"\n" +
                "        }\n" +
                "      } ],\n" +
                "      \"contatoList\" : [ ],\n" +
                "      \"infoReceitaFederalList\" : [ {\n" +
                "        \"id\" : 338,\n" +
                "        \"code\" : \"46.37-1-01\",\n" +
                "        \"text\" : \"Comércio atacadista de café torrado, moído e solúvel\",\n" +
                "        \"tipo\" : \"PRINCIPAL\",\n" +
                "        \"detalheInfoReceitaFederal\" : \"[item]: 46.37-1-01\\r\\n    [Descrição]: Comércio atacadista de café torrado, moído e solúvel\"\n" +
                "      }, {\n" +
                "        \"id\" : 339,\n" +
                "        \"code\" : \"00.00-0-00\",\n" +
                "        \"text\" : \"Não informada\",\n" +
                "        \"tipo\" : \"SECUNDARIA\",\n" +
                "        \"detalheInfoReceitaFederal\" : \"[item]: 00.00-0-00\\r\\n    [Descrição]: Não informada\"\n" +
                "      }, {\n" +
                "        \"id\" : 340,\n" +
                "        \"code\" : \"Capital social\",\n" +
                "        \"text\" : \"0,00\",\n" +
                "        \"tipo\" : \"QSA\",\n" +
                "        \"detalheInfoReceitaFederal\" : \"[item]: Capital social\\r\\n    [Descrição]: 0,00\"\n" +
                "      } ]\n" +
                "    },\n" +
                "    \"entradaFiscal\" : null\n" +
                "  },\n" +
                "  \"entradaCte\" : {\n" +
                "    \"id\" : 0,\n" +
                "    \"chave\" : \"35190326010257000207570000000114451000129266\",\n" +
                "    \"numero\" : \"11445\",\n" +
                "    \"serie\" : \"0\",\n" +
                "    \"qtdVolume\" : 10,\n" +
                "    \"tomadorServico\" : \"DESTINATARIO\",\n" +
                "    \"modelo\" : \"MOD57\",\n" +
                "    \"vlrCte\" : 268.18,\n" +
                "    \"pesoBruto\" : 52.00,\n" +
                "    \"vlrFreteBruto\" : 225.00,\n" +
                "    \"vlrTaxas\" : 32.45,\n" +
                "    \"vlrColeta\" : 0.00,\n" +
                "    \"vlrImpostoFrete\" : 10.73,\n" +
                "    \"dataEmissao\" : \"01/03/2019 20:40:49\",\n" +
                "    \"situacaoTributaria\" : {\n" +
                "      \"id\" : 0,\n" +
                "      \"descricao\" : \"Tributação normal ICMS\"\n" +
                "    },\n" +
                "    \"emissor\" : {\n" +
                "      \"children\" : [ ],\n" +
                "      \"groupedColumn\" : null,\n" +
                "      \"groupedValue\" : null,\n" +
                "      \"id\" : 78,\n" +
                "      \"lojaSistema\" : false,\n" +
                "      \"cnpj\" : \"26010257000207\",\n" +
                "      \"ieIsento\" : false,\n" +
                "      \"ie\" : \"286478402113\",\n" +
                "      \"razao\" : \"ROTA AIR BRASIL TRANSPORTE LOGISTICA E ARMAZENAGEM LTDA\",\n" +
                "      \"fantasia\" : \"ROTA AIR\",\n" +
                "      \"cliente\" : false,\n" +
                "      \"fornecedor\" : false,\n" +
                "      \"transportadora\" : true,\n" +
                "      \"dataCadastro\" : \"30/11/2018 21:58:03\",\n" +
                "      \"dataAtualizacao\" : \"12/03/2019 13:46:42\",\n" +
                "      \"dataAbetura\" : \"25/11/2017 00:00:00\",\n" +
                "      \"naturezaJuridica\" : \"206-2 - SOCIEDADE EMPRESÁRIA LIMITADA\",\n" +
                "      \"observacoes\" : \"WebService:\\nreceitaWs:\\tgerouCobrança: [false]\\tBD_Ws: [true]\\n\",\n" +
                "      \"classifJuridica\" : \"PESSOAJURIDICA\",\n" +
                "      \"situacao\" : \"ATIVO\",\n" +
                "      \"enderecoList\" : [ {\n" +
                "        \"id\" : 79,\n" +
                "        \"cep\" : \"09920370\",\n" +
                "        \"logradouro\" : \"RUA JACAREI (PRQ JABOTICABEIRAS)\",\n" +
                "        \"numero\" : \"120\",\n" +
                "        \"complemento\" : \"QUADRAD\",\n" +
                "        \"bairro\" : \"CENTRO\",\n" +
                "        \"prox\" : \"\",\n" +
                "        \"municipio\" : {\n" +
                "          \"id\" : 3420,\n" +
                "          \"descricao\" : \"DIADEMA\",\n" +
                "          \"capital\" : false,\n" +
                "          \"ibge_codigo\" : \"3513801\",\n" +
                "          \"ddd\" : 11,\n" +
                "          \"uf\" : {\n" +
                "            \"id\" : 35,\n" +
                "            \"descricao\" : \"São Paulo\",\n" +
                "            \"sigla\" : \"SP\",\n" +
                "            \"ddd\" : 11\n" +
                "          }\n" +
                "        },\n" +
                "        \"tipo\" : \"PRINCIPAL\"\n" +
                "      } ],\n" +
                "      \"emailHomePageList\" : [ {\n" +
                "        \"id\" : 62,\n" +
                "        \"descricao\" : \"vrsdocumental@hotmail.com\",\n" +
                "        \"tipo\" : \"EMAIL\"\n" +
                "      } ],\n" +
                "      \"telefoneList\" : [ {\n" +
                "        \"id\" : 114,\n" +
                "        \"descricao\" : \"1147486289\",\n" +
                "        \"telefoneOperadora\" : {\n" +
                "          \"id\" : 320,\n" +
                "          \"descricao\" : \"Vivo - Fixo\",\n" +
                "          \"codWsPortabilidade\" : \"55115\"\n" +
                "        }\n" +
                "      }, {\n" +
                "        \"id\" : 115,\n" +
                "        \"descricao\" : \"1147496289\",\n" +
                "        \"telefoneOperadora\" : {\n" +
                "          \"id\" : 320,\n" +
                "          \"descricao\" : \"Vivo - Fixo\",\n" +
                "          \"codWsPortabilidade\" : \"55115\"\n" +
                "        }\n" +
                "      } ],\n" +
                "      \"contatoList\" : [ {\n" +
                "        \"id\" : 18,\n" +
                "        \"descricao\" : \"EDSON\",\n" +
                "        \"cargo\" : {\n" +
                "          \"id\" : 2,\n" +
                "          \"descricao\" : \"PROPRIETARIO\"\n" +
                "        },\n" +
                "        \"emailHomePageList\" : [ ],\n" +
                "        \"telefoneList\" : [ {\n" +
                "          \"id\" : 113,\n" +
                "          \"descricao\" : \"11985366193\",\n" +
                "          \"telefoneOperadora\" : {\n" +
                "            \"id\" : 122,\n" +
                "            \"descricao\" : \"TIM - Celular\",\n" +
                "            \"codWsPortabilidade\" : \"55341\"\n" +
                "          }\n" +
                "        } ]\n" +
                "      }, {\n" +
                "        \"id\" : 19,\n" +
                "        \"descricao\" : \"VIVIANE\",\n" +
                "        \"cargo\" : {\n" +
                "          \"id\" : 18,\n" +
                "          \"descricao\" : \"ESCRITORIO\"\n" +
                "        },\n" +
                "        \"emailHomePageList\" : [ ],\n" +
                "        \"telefoneList\" : [ {\n" +
                "          \"id\" : 118,\n" +
                "          \"descricao\" : \"92993356825\",\n" +
                "          \"telefoneOperadora\" : {\n" +
                "            \"id\" : 127,\n" +
                "            \"descricao\" : \"Vivo - Celular\",\n" +
                "            \"codWsPortabilidade\" : \"55320\"\n" +
                "          }\n" +
                "        } ]\n" +
                "      }, {\n" +
                "        \"id\" : 20,\n" +
                "        \"descricao\" : \"PAULA NASCIMENTO\",\n" +
                "        \"cargo\" : {\n" +
                "          \"id\" : 18,\n" +
                "          \"descricao\" : \"ESCRITORIO\"\n" +
                "        },\n" +
                "        \"emailHomePageList\" : [ {\n" +
                "          \"id\" : 65,\n" +
                "          \"descricao\" : \"financeiro.mao@rotaair.com.br\",\n" +
                "          \"tipo\" : \"EMAIL\"\n" +
                "        } ],\n" +
                "        \"telefoneList\" : [ {\n" +
                "          \"id\" : 119,\n" +
                "          \"descricao\" : \"92981831450\",\n" +
                "          \"telefoneOperadora\" : {\n" +
                "            \"id\" : 126,\n" +
                "            \"descricao\" : \"Claro - Celular\",\n" +
                "            \"codWsPortabilidade\" : \"55321\"\n" +
                "          }\n" +
                "        }, {\n" +
                "          \"id\" : 120,\n" +
                "          \"descricao\" : \"9230161914\",\n" +
                "          \"telefoneOperadora\" : {\n" +
                "            \"id\" : 223,\n" +
                "            \"descricao\" : \"Vivo Fixo\",\n" +
                "            \"codWsPortabilidade\" : \"55215\"\n" +
                "          }\n" +
                "        } ]\n" +
                "      }, {\n" +
                "        \"id\" : 21,\n" +
                "        \"descricao\" : \"DANIELE SPO\",\n" +
                "        \"cargo\" : {\n" +
                "          \"id\" : 18,\n" +
                "          \"descricao\" : \"ESCRITORIO\"\n" +
                "        },\n" +
                "        \"emailHomePageList\" : [ ],\n" +
                "        \"telefoneList\" : [ {\n" +
                "          \"id\" : 121,\n" +
                "          \"descricao\" : \"1143088500\",\n" +
                "          \"telefoneOperadora\" : {\n" +
                "            \"id\" : 314,\n" +
                "            \"descricao\" : \"Embratel - Fixo\",\n" +
                "            \"codWsPortabilidade\" : \"55121\"\n" +
                "          }\n" +
                "        } ]\n" +
                "      }, {\n" +
                "        \"id\" : 22,\n" +
                "        \"descricao\" : \"ENTREGA1\",\n" +
                "        \"cargo\" : {\n" +
                "          \"id\" : 10,\n" +
                "          \"descricao\" : \"ENTREGADOR\"\n" +
                "        },\n" +
                "        \"emailHomePageList\" : [ ],\n" +
                "        \"telefoneList\" : [ {\n" +
                "          \"id\" : 122,\n" +
                "          \"descricao\" : \"92992960684\",\n" +
                "          \"telefoneOperadora\" : {\n" +
                "            \"id\" : 127,\n" +
                "            \"descricao\" : \"Vivo - Celular\",\n" +
                "            \"codWsPortabilidade\" : \"55320\"\n" +
                "          }\n" +
                "        } ]\n" +
                "      }, {\n" +
                "        \"id\" : 23,\n" +
                "        \"descricao\" : \"ENTREGA2\",\n" +
                "        \"cargo\" : {\n" +
                "          \"id\" : 10,\n" +
                "          \"descricao\" : \"ENTREGADOR\"\n" +
                "        },\n" +
                "        \"emailHomePageList\" : [ ],\n" +
                "        \"telefoneList\" : [ {\n" +
                "          \"id\" : 123,\n" +
                "          \"descricao\" : \"92994341500\",\n" +
                "          \"telefoneOperadora\" : {\n" +
                "            \"id\" : 127,\n" +
                "            \"descricao\" : \"Vivo - Celular\",\n" +
                "            \"codWsPortabilidade\" : \"55320\"\n" +
                "          }\n" +
                "        } ]\n" +
                "      } ],\n" +
                "      \"infoReceitaFederalList\" : [ {\n" +
                "        \"id\" : 89,\n" +
                "        \"code\" : \"49.30-2-02\",\n" +
                "        \"text\" : \"Transporte rodoviário de carga, exceto produtos perigosos e mudanças, intermunicipal, interestadual e internacional\",\n" +
                "        \"tipo\" : \"PRINCIPAL\",\n" +
                "        \"detalheInfoReceitaFederal\" : \"[item]: 49.30-2-02\\r\\n    [Descrição]: Transporte rodoviário de carga, exceto produtos perigosos e mudanças, \\r\\nintermunicipal, interestadual e internacional\"\n" +
                "      }, {\n" +
                "        \"id\" : 90,\n" +
                "        \"code\" : \"00.00-0-00\",\n" +
                "        \"text\" : \"Não informada\",\n" +
                "        \"tipo\" : \"SECUNDARIA\",\n" +
                "        \"detalheInfoReceitaFederal\" : \"[item]: 00.00-0-00\\r\\n    [Descrição]: Não informada\"\n" +
                "      }, {\n" +
                "        \"id\" : 91,\n" +
                "        \"code\" : \"Capital social\",\n" +
                "        \"text\" : \"0,00\",\n" +
                "        \"tipo\" : \"QSA\",\n" +
                "        \"detalheInfoReceitaFederal\" : \"[item]: Capital social\\r\\n    [Descrição]: 0,00\"\n" +
                "      } ]\n" +
                "    },\n" +
                "    \"entradaFiscal\" : null\n" +
                "  },\n" +
                "  \"entradaProdutoProdutoList\" : null\n" +
                "}";
    }

}
