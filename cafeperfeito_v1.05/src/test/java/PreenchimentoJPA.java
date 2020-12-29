package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PreenchimentoJPA {

    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) throws ParseException, IOException {
////        EntityManager em = new ConnectionFactory().getEntityManager();
//
//        UfDAO ufDAO = new UfDAO();
//        inserirUf(ufDAO);
//        ServiceImprimirListaJSon.printJsonFromObject(ufDAO.getAll(Uf.class));
//
//
//        TelefoneOperadoraDAO telefoneOperadora_DAO_dao = new TelefoneOperadoraDAO();
//        inserirTelefoneOperadora(telefoneOperadora_DAO_dao);
//        ServiceImprimirListaJSon.printJsonFromObject(telefoneOperadora_DAO_dao.getAll(TelefoneOperadora.class));
//
//        CargoDAO cargo_DAO_dao = new CargoDAO();
//        //inserirCargo(cargo_DAO_dao);
//        ServiceImprimirListaJSon.printJsonFromObject(cargo_DAO_dao.getAll(Cargo.class));
//
//
//        EmpresaDAO empresa_DAO_dao = new EmpresaDAO();
//        Empresa empresa_vo = new Empresa(true, 2, "08009246000136", false, "042171865",
//                "T. L. MACEDO", "CAFE PERFEITO", true, true, true, SituacaoNoSistema.ATIVO,
//                null, LocalDateTime.parse("2006-04-28 01:00:00", DTF_MYSQL_DATAHORA), null, null,
//                LocalDateTime.parse("2006-04-28 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL)");
//        empresa_vo = empresa_DAO_dao.save(empresa_vo);
//        ServiceImprimirListaJSon.printJsonFromObject(empresa_DAO_dao.getAll(Empresa.class));
//
//        UsuarioDAO usuario_DAO_dao = new UsuarioDAO();
//        inserirUsuario(usuario_DAO_dao);
//        ServiceImprimirListaJSon.printJsonFromObject(usuario_DAO_dao.getAll(Usuario.class));
//
//        empresa_vo.setUsuarioCadastro(usuario_DAO_dao.getById(Usuario.class, 1L));
//        if (empresa_vo.getId() > 0) empresa_vo.setUsuarioAtualizacao(usuario_DAO_dao.getById(Usuario.class, 1L));
//        empresa_DAO_dao.save(empresa_vo);
//        ServiceImprimirListaJSon.printJsonFromObject(empresa_DAO_dao.getAll(Empresa.class));
//
//        inserirEmpresa(empresa_DAO_dao);
//        ServiceImprimirListaJSon.printJsonFromObject(empresa_DAO_dao.getAll(Empresa.class));
//
//
    }

//    private static void inserirUf(UfDAO ufDAO) {
//        ufDAO.save(new Uf(11L, "Rondônia", "RO"));
//        ufDAO.save(new Uf(12L, "Acre", "AC"));
//        ufDAO.save(new Uf(13L, "Amazonas", "AM"));
//        ufDAO.save(new Uf(14L, "Roraima", "RR"));
//        ufDAO.save(new Uf(15L, "Pará", "PA"));
//        ufDAO.save(new Uf(16L, "Amapá", "AP"));
//        ufDAO.save(new Uf(17L, "Tocantins", "TO"));
//        ufDAO.save(new Uf(21L, "Maranhão", "MA"));
//        ufDAO.save(new Uf(22L, "Piauí", "PI"));
//        ufDAO.save(new Uf(23L, "Ceara", "CE"));
//        ufDAO.save(new Uf(24L, "Rio Grande do Norte", "RN"));
//        ufDAO.save(new Uf(25L, "Paraíba", "PB"));
//        ufDAO.save(new Uf(26L, "Pernambuco", "PE"));
//        ufDAO.save(new Uf(27L, "Alagoas", "AL"));
//        ufDAO.save(new Uf(28L, "Sergipe", "SE"));
//        ufDAO.save(new Uf(29L, "Bahia", "BA"));
//        ufDAO.save(new Uf(31L, "Minas Gerais", "MG"));
//        ufDAO.save(new Uf(32L, "Espirito Santo", "ES"));
//        ufDAO.save(new Uf(33L, "Rio de Janeiro", "RJ"));
//        ufDAO.save(new Uf(35L, "São Paulo", "SP"));
//        ufDAO.save(new Uf(41L, "Paraná", "PR"));
//        ufDAO.save(new Uf(42L, "Santa Catarina", "SC"));
//        ufDAO.save(new Uf(43L, "Rio Grande do Sul", "RS"));
//        ufDAO.save(new Uf(50L, "Mato Grosso do Sul", "MS"));
//        ufDAO.save(new Uf(51L, "Mato Grosso", "MT"));
//        ufDAO.save(new Uf(52L, "Goiás", "GO"));
//        ufDAO.save(new Uf(53L, "Distrito Federal", "DF"));
//
//    }
//
//    private static void inserirUsuario(UsuarioDAO usuario_DAO_dao) throws ParseException {
//        CargoDAO cargo_DAO_dao = new CargoDAO();
//        EmpresaDAO empresa_DAO_dao = new EmpresaDAO();
//
//        Usuario usuario_vo = new Usuario("Thiago Macedo", "thiago", "123456", LocalDateTime.parse("2006-04-28 00:00:00", DTF_MYSQL_DATAHORA), BigDecimal.valueOf(8000.0),
//                SituacaoNoSistema.ATIVO, cargo_DAO_dao.getById(Cargo.class, 1L),
//                empresa_DAO_dao.getById(Empresa.class, 1L),
//                "$2a$10$S2kmOQJwK8ZCWbdsjyAHYedKbJU.FADOuyJQlV6GKF2DO/4VyPmFy", "carlinha.figueiredo32@hotmail.com", null);
//        usuario_DAO_dao.save(usuario_vo);
//        usuario_vo = new Usuario("Carla Macedo", "carla", "567890", LocalDateTime.parse("2013-05-18 09:00:00", DTF_MYSQL_DATAHORA), BigDecimal.valueOf(5000.0),
//                SituacaoNoSistema.ATIVO, cargo_DAO_dao.getById(Cargo.class, 2L),
//                empresa_DAO_dao.getById(Empresa.class, 1L),
//                "$2a$10$QEvQV3QpYHDG7cnZYPLtw.FFiVHoG3UN9bJVC2byjLUMpTU9vuI/K", "tl.macedo@hotmail.com", null);
//        usuario_DAO_dao.save(usuario_vo);
//    }
//
//    private static void inserirEmpresa(EmpresaDAO empresaDAO) throws ParseException {
//        UsuarioDAO usuarioDAO = new UsuarioDAO();
//
////    empresaDAO.save(new EmpresaDAO(true, 2, "08009246000136", false, "042171865",
////        "T. L. MACEDO", "CAFE PERFEITO", true, true, true, 1,
////        null, LocalDateTime.parse("28/04/2006 01:00:00", DTF_DATAHORA), null, null,
////        LocalDateTime.parse("28/04/2006 00:00:00", DTF_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL)"));
//
//
//        empresaDAO.save(new Empresa(false, 2, "01703285000190", false, "115298039114",
//                "BRASIL ESPRESSO COMERCIO ATACADISTA LTDA.", "BRANCO PERES COMERCIO ATACADISTA LTDA", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-01 18:05:43", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1997-02-13 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "05694248000113", false, "635485405115",
//                "SUPREMO ARABICA COMERCIO IMPORTACAO E EXPORTACAO DE MAQUINAS LTDA", "***", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-02 14:18:44", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2003-06-02 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "04561379000160", false, "041008057",
//                "J J R COMERCIO DE PRODUTOS ALIMENTICIOS LTDA", "ARMAZEM SANTA CLARA", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-03 16:14:12", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1966-08-08 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "18466212000124", false, "053402766",
//                "MENDES E DAMASCENO LTDA", "D R DAMASCENO", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-04 16:15:45", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2013-07-11 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "84509264000165", false, "041278321",
//                "MAXPEL COMERCIAL LTDA", "***", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-05 18:35:51", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1994-01-06 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "01487193000110", false, "041096282",
//                "B S DISTRIBUICAO E REPRESENTACAO LTDA", "***", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-06 17:53:05", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1996-10-14 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "47427653000468", false, "041484452",
//                "MAKRO ATACADISTA SOCIEDADE ANONIMA", "***", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-07 13:00:46", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2001-09-04 00:00:00", DTF_MYSQL_DATAHORA), "205-4 - SOCIEDADE ANÔNIMA FECHADA)"));
//        empresaDAO.save(new Empresa(false, 2, "04121789000190", false, "336826969110",
//                "REDYAR - OTM TRANSPORTES LTDA", "REDYAR TRANSPORTES", false, false, true, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-08 16:04:15", DTF_MYSQL_DATAHORA),
//                null, null, LocalDateTime.parse("2000-10-09 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "06186733000220", false, "336942124118",
//                "EXATA CARGO LTDA", "EXATA CARGO", false, false, true, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-09 14:22:18", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2006-06-14 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "60541240000125", false, "113551215114",
//                "TECNOLOG TRANSPORTE RODO-AEREO E LOGISTICA LTDA.", "TECNOLOG EXPRESS CARGO", false, false, true, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-02-10 16:58:36", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1989-04-27 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "05518915000107", false, "041775260",
//                "ALEMA RESTAURANTES LTDA", "ALEMA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-03-01 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1985-10-04 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "09812871000184", true, "",
//                "CAFERETTO SERVICO DE ALIMENTACAO E COMERCIO LTDA", "CAFERETTO", true, false, false, SituacaoNoSistema.BAIXADA,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-03-08 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2008-04-28 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "63702476000194", false, "042966829",
//                "H L PUBLICACOES LTDA", "LIVRARIA AJURICABA", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-03-25 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:17:56", DTF_MYSQL_DATAHORA), LocalDateTime.parse("1991-06-11 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "10701631000191", false, "042936829",
//                "HR RESTAURANTE LTDA", "PICANHA MANIA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-03-28 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2009-03-19 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "04336889000133", false, "041143310",
//                "AMANDA CABELEIREIROS EIRELI", "AMANDA BEAUTY CENTER", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-04-01 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1978-08-29 00:00:00", DTF_MYSQL_DATAHORA), "230-5 - EMPRESA INDIVIDUAL DE RESPONSABILIDADE LIMITADA (DE NATUREZA EMPRESÁRIA))"));
//        empresaDAO.save(new Empresa(false, 2, "05073167000104", false, "041526066",
//                "NICE VEICULOS LTDA", "NICE VEICULOS", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-04-24 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2002-05-17 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "11371389000106", false, "042966400",
//                "X-MANAUS RESTAURANTE LTDA", "X PICANHA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-04-27 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2009-12-04 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "06522265000306", false, "042904510",
//                "PONTA NEGRA SOLUCOES, LOGISTICAS E TRANSPORTES LTDA", "PONTA NEGRA IMPORT", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-05-11 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2008-07-23 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "07819538000171", false, "042168295",
//                "LUTRA COMERCIO DE ALIMENTOS LTDA", "X PICANHA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-05-26 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2006-01-25 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "09497477000107", false, "042249333",
//                "M M RESTAURANTE LTDA", "NAJUA RESTAURANTE", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-05-27 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2008-03-06 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "63740237000129", false, "041189663",
//                "A L PARENTE", "DELIVERY DO DEDE", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-05-27 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-10 15:14:23", DTF_MYSQL_DATAHORA), LocalDateTime.parse("1991-10-04 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "10868702000145", false, "042938856",
//                "F L - COMERCIO DE ALIMENTOS LTDA", "PANIFICADORA PAO DA VILLA", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-06-21 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:17:31", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2009-05-21 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "10189959000170", false, "041168267",
//                "RIVER JUNGLE HOTEL LTDA", "HOTEL ARIAU", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-06-27 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1986-01-07 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "09449408000110", false, "042240646",
//                "SPEED A M EQUIPAMENTOS DE COMUNICACAO LTDA", "SPEED A. M. EMPRESARIAL LTDA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-07-12 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2008-03-07 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "84489269000173", false, "041253477",
//                "HABIPAR PARTICIPACOES LTDA.", "CAESAR BUSINESS MANAUS AMAZONAS", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-07-12 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1993-06-14 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "10818339000153", false, "042935229",
//                "VIEIRA E VIEIRA COMERCIO DE ARTIGOS ESPORTIVOS LTDA", "PONTO DOS ESPORTES", true, false, false, SituacaoNoSistema.BAIXADA,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-08-17 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2009-05-13 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "02651424000141", false, "041395433",
//                "CALDAS BUFFET LTDA", "O LENHADOR", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2010-11-10 02:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:16:29", DTF_MYSQL_DATAHORA), LocalDateTime.parse("1998-07-16 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "11303271000132", false, "042966760",
//                "CR LOJA DE CONVENIENCIA LTDA", "CR CONVENIENCIA", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-04-07 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:16:42", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2009-11-10 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "84496694000190", false, "041302567",
//                "IMA COMERCIO DE ALIMENTOS LTDA", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-04-13 01:00:00", DTF_MYSQL_DATAHORA),
//                null, null, LocalDateTime.parse("1993-10-22 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "84496694000432", false, "042940893",
//                "IMA COMERCIO DE ALIMENTOS LTDA", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-05-19 01:00:00", DTF_MYSQL_DATAHORA),
//                null, null, LocalDateTime.parse("2009-01-08 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "66542002003055", false, "042281687",
//                "BLUE TREE HOTELS & RESORTS DO BRASIL S/A.", "BLUE TREE PREMIUM MANAUS", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-05-19 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:16:06", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2010-06-29 00:00:00", DTF_MYSQL_DATAHORA), "205-4 - SOCIEDADE ANÔNIMA FECHADA)"));
//        empresaDAO.save(new Empresa(false, 2, "04920058000104", false, "240105525",
//                "PINHEIRO & CIA LTDA", "PADARIA TRIGO'S", true, false, false, SituacaoNoSistema.TERCEIROS,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-07-09 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2001-10-31 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "04082624000156", false, "000010197",
//                "IRMAOS GONCALVES COMERCIO E INDUSTRIA LTDA.", "SUPERMERCADO IRMAOS GONCALVES", true, false, false, SituacaoNoSistema.TERCEIROS,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-07-15 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1977-04-20 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "15775273000185", false, "041818911",
//                "CHURRASCARIA BUFALO LTDA", "CHURRASCARIA BUFALO", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-07-22 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:16:36", DTF_MYSQL_DATAHORA), LocalDateTime.parse("1986-12-09 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "84496694000351", false, "044005377",
//                "IMA COMERCIO DE ALIMENTOS LTDA", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2011-07-23 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2005-01-04 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "00512663000195", false, "041017323",
//                "VIA MARCONI VEICULOS LTDA", "WAY", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-02-20 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1995-03-29 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "02584924002151", false, "053395786",
//                "ICH ADMINISTRACAO DE HOTEIS S.A.", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-04-10 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2011-04-18 00:00:00", DTF_MYSQL_DATAHORA), "205-4 - SOCIEDADE ANÔNIMA FECHADA)"));
//        empresaDAO.save(new Empresa(false, 2, "09137053000123", false, "042290384",
//                "C C DE OMENA MICHILES", "CAFE DO PONTO", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-04-12 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:16:17", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2007-10-18 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "10939609000346", false, "053209818",
//                "DIEGO BRINGEL AVELINO", "SUBWAY", true, false, false, SituacaoNoSistema.TERCEIROS,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-04-29 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:16:58", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2011-11-03 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "10939609000265", false, "04966221",
//                "DIEGO BRINGEL AVELINO", "SUBWAY", true, false, false, SituacaoNoSistema.TERCEIROS,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-04-29 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:17:14", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2009-11-05 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "10939609000184", false, "042942519",
//                "DIEGO BRINGEL AVELINO", "SUBWAY", true, false, false, SituacaoNoSistema.TERCEIROS,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-04-29 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:17:08", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2009-06-30 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "10964541000193", false, "042949157",
//                "MENEZES & MENEZES, SERVICOS DE ALIMENTACAO LTDA", "SABOR A MI BISTRO", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-10-10 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2009-07-15 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "67894360000317", true, "",
//                "GRAO FRANCHISING E PARTICIPACOES LTDA", "MODELLI", true, false, false, SituacaoNoSistema.BAIXADA,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-10-10 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1997-05-28 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "11875229000196", false, "042274303",
//                "MT COMERCIO DE ALIMENTOS LTDA", "YOGU MANIA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-11-14 02:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2010-04-29 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "60701190411589", true, "",
//                "ITAU UNIBANCO S.A.", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-11-26 02:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2009-04-29 00:00:00", DTF_MYSQL_DATAHORA), "205-4 - SOCIEDADE ANÔNIMA FECHADA)"));
//        empresaDAO.save(new Empresa(false, 2, "05424423000152", false, "042154316",
//                "DE PASQUAL HOTEIS E TURISMO LTDA.", "COMFORT HOTEL", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-12-17 02:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:16:48", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2002-12-10 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "09256342000141", false, "042902363",
//                "RAL EMPREENDIMENTOS LTDA", "CACHACARIA DO DEDE & EMPORIO", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-12-17 02:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2007-12-12 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "16799056000198", false, "053299760",
//                "IGOR DA S.MENDONCA - LANCHONETE", "VIGOR CAFE", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-12-17 02:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2012-09-04 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "08975700000294", false, "042960606",
//                "VIFOUR COMERCIO DE CONFECCOES E ALIMENTOS LTDA", "YOGO MANIA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2013-12-18 02:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2007-08-08 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "02844344000102", true, "",
//                "FUNDACAO AMAZONICA DE AMPARO A PESQUISA E DESENVOLVIMENTO TECNOLOGICO DESEMBARGA", "FPF TECH", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-02-03 02:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1998-11-04 00:00:00", DTF_MYSQL_DATAHORA), "306-9 - FUNDAÇÃO PRIVADA)"));
//        empresaDAO.save(new Empresa(false, 2, "84467307000197", false, "041233786",
//                "DINAMICA DISTRIBUIDORA LTDA", "***", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-02-18 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:17:21", DTF_MYSQL_DATAHORA), LocalDateTime.parse("1992-12-22 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "42289025000440", false, "042962943",
//                "INTERCONTINENTAL HOTELS GROUP DO BRASIL LTDA.", "HOTEL HOLIDAY INN MANAUS", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-05-06 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2009-08-06 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "14629567000136", false, "053247264",
//                "QUALITE ALIMENTOS PREPARADOS LTDA EPP", "QUALITE ALIMENTOS", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-06-04 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2011-11-17 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "20069719000196", false, "053522249",
//                "FUTURA COMERCIO DE ALIMENTOS E BEBIDAS LTDA", "LA FINESTRA", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-06-07 01:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:17:38", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2014-04-11 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "23033327000408", false, "053522249",
//                "HTS SERVICOS DE HOTELARIA E TURISMO LTDA", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-06-10 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2004-10-20 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "60701190134305", true, "",
//                "ITAU UNIBANCO S.A.", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-06-12 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1991-12-09 00:00:00", DTF_MYSQL_DATAHORA), "205-4 - SOCIEDADE ANÔNIMA FECHADA)"));
//        empresaDAO.save(new Empresa(false, 2, "23033327000327", false, "053522230",
//                "HTS SERVICOS DE HOTELARIA E TURISMO LTDA", "HTS - ED.ADRIANOPOLIS - FILIAL I", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-06-18 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2001-05-03 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "19380220000116", false, "053495357",
//                "IVANEIDE CARACAS BEZERRA", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2014-07-15 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2013-12-09 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "10865719000149", false, "042969271",
//                "B. THOME ARAUJO REFEICOES LTDA-ME", "B.T.A REFEICOES", true, false, false, SituacaoNoSistema.DESATIVADO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-02-09 02:00:00", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-08-03 14:15:55", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2009-05-12 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "10013843000186", false, "042902509",
//                "AMANDA BEAUTY CENTER EIRELI", "AMANDA BEAUTY CENTER", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-03-01 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2008-06-30 00:00:00", DTF_MYSQL_DATAHORA), "230-5 - EMPRESA INDIVIDUAL DE RESPONSABILIDADE LIMITADA (DE NATUREZA EMPRESÁRIA))"));
//        empresaDAO.save(new Empresa(false, 2, "60701190159200", true, "",
//                "ITAU UNIBANCO S.A.", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-04-22 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1996-10-30 00:00:00", DTF_MYSQL_DATAHORA), "205-4 - SOCIEDADE ANÔNIMA FECHADA)"));
//        empresaDAO.save(new Empresa(false, 2, "08941325000180", false, "407521997117",
//                "SL CAFES DO BRASIL PROFESSIONAL LTDA", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-07-02 01:00:00", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2007-06-11 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "04336889000214", false, "042960126",
//                "AMANDA CABELEIREIROS LTDA", "AMANDA BEAUTY CENTER", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-07-28 20:55:43", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2009-10-23 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "16684606000123", false, "053280520",
//                "RODRIGUES E SMITH COMERCIO DE ALIMENTOS LTDA", "CASA DO PAO DE QUEIJO AMAZONAS SHOPPING", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-08-12 11:50:50", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2012-08-13 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "60701190057637", true, "",
//                "ITAU UNIBANCO S.A.", "***", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-08-14 12:13:43", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1975-01-08 00:00:00", DTF_MYSQL_DATAHORA), "205-4 - SOCIEDADE ANÔNIMA FECHADA)"));
//        empresaDAO.save(new Empresa(false, 2, "07414941000110", false, "042172342",
//                "PAOZINHO COMERCIO LTDA", "PAOZINHO", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2015-11-12 12:15:35", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2005-06-01 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "19783732000123", false, "053498771",
//                "NUTRI CASAS DE CHA LTDA.", "CASA DO PAO DE QUEIJO", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2016-01-07 18:04:22", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2014-02-21 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "08038545001413", false, "053727290",
//                "NATUREZA COMERCIO DE DESCARTAVEIS LTDA", "QUEIROZ", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2016-04-25 15:18:03", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2015-10-22 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "06981833000248", false, "63585819611",
//                "TORREFACAO NISHIDA SAN LTDA", "***", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2018-03-14 11:00:07", DTF_MYSQL_DATAHORA), usuarioDAO.getById(Usuario.class, 1L),
//                LocalDateTime.parse("2018-09-27 10:51:10", DTF_MYSQL_DATAHORA), LocalDateTime.parse("2015-10-15 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "04802134000268", true, "",
//                "INDT - INSTITUTO DE DESENVOLVIMENTO TECNOLOGICO", "INDT", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2018-06-01 15:59:20", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2002-11-01 00:00:00", DTF_MYSQL_DATAHORA), "399-9 - ASSOCIAÇÃO PRIVADA)"));
//        empresaDAO.save(new Empresa(false, 2, "29260745000171", false, "053984722",
//                "PANIFICADORA ALDEIA DOS PAES EIRELI", "PANIFICADORA ALDEIA DOS PAES", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2018-06-08 16:01:46", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2017-12-13 00:00:00", DTF_MYSQL_DATAHORA), "230-5 - EMPRESA INDIVIDUAL DE RESPONSABILIDADE LIMITADA (DE NATUREZA EMPRESÁRIA))"));
//        empresaDAO.save(new Empresa(false, 2, "04012611000100", false, "041452097",
//                "RONALDO MAIA BARBOSA EIRELI", "DISTRIBUIDORA LONDRINA", true, false, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2018-07-19 10:13:47", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2000-08-25 00:00:00", DTF_MYSQL_DATAHORA), "230-5 - EMPRESA INDIVIDUAL DE RESPONSABILIDADE LIMITADA (DE NATUREZA EMPRESÁRIA))"));
//        empresaDAO.save(new Empresa(false, 2, "12564461000176", false, "042295513",
//                "JANAINA F B MATOS", "SHALOM FESTAS", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2018-10-03 15:26:25", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2010-09-22 00:00:00", DTF_MYSQL_DATAHORA), "213-5 - EMPRESÁRIO (INDIVIDUAL))"));
//        empresaDAO.save(new Empresa(false, 2, "02392576000177", false, "041395808",
//                "BANDEIRA DE MELO E FILHOS LTDA", "ATACADAO TROPICAL", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2018-10-03 15:28:41", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("1998-02-04 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//        empresaDAO.save(new Empresa(false, 2, "00835261000393", false, "053583574",
//                "A.M. DA S RODRIGUES & CIA LTDA", "SUPERMERCADO RODRIGUES", false, true, false, SituacaoNoSistema.ATIVO,
//                usuarioDAO.getById(Usuario.class, 1L), LocalDateTime.parse("2018-10-16 14:25:10", DTF_MYSQL_DATAHORA), null,
//                null, LocalDateTime.parse("2013-12-16 00:00:00", DTF_MYSQL_DATAHORA), "206-2 - SOCIEDADE EMPRESÁRIA LIMITADA)"));
//
//    }
//
//    private static void inserirTelefoneOperadora(TelefoneOperadoraDAO telefoneOperadoraDAO) {
//        telefoneOperadoraDAO.save(new TelefoneOperadora("TIM", TelefoneTipo.FIXO_CELULAR, 41, "55341;55141"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("CLARO / EMBRATEL / NET", TelefoneTipo.FIXO_CELULAR, 21, "55321;55121"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("CTBC TELECOM", TelefoneTipo.FIXO_CELULAR, 12, "55312"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("OI", TelefoneTipo.FIXO_CELULAR, 31, "55314;55331;55114;55131"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("VIVO", TelefoneTipo.FIXO_CELULAR, 15, "55320;55323;55115;55215"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("SERCOMTEL", TelefoneTipo.FIXO_CELULAR, 43, "55343;55143"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("NEXTEL", TelefoneTipo.FIXO_CELULAR, 0, "55351;55377"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("GENTE TELECOM", TelefoneTipo.FIXO, 10, "55277"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("LIGUEMAX", TelefoneTipo.FIXO, 11, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("FONAR", TelefoneTipo.FIXO, 13, "55113"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("BRASIL TELECOM", TelefoneTipo.FIXO, 14, "55274"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("VIACOM", TelefoneTipo.FIXO, 16, "55192"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("TRANSIT TELECOM", TelefoneTipo.FIXO, 17, "55117"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("SPIN", TelefoneTipo.FIXO, 18, "55118"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("EPSILON", TelefoneTipo.FIXO, 19, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("INTELIG", TelefoneTipo.FIXO, 23, "55123"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("PRIMEIRA ESCOLHA", TelefoneTipo.FIXO, 24, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("GVT", TelefoneTipo.FIXO, 25, "55125"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("IDT", TelefoneTipo.FIXO, 26, "55126"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("AEROTECH", TelefoneTipo.FIXO, 27, "55127"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("ALPAMAYO", TelefoneTipo.FIXO, 28, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("CONVERGIA", TelefoneTipo.FIXO, 32, "55132"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("TELEDADOS", TelefoneTipo.FIXO, 34, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("EASYTONE", TelefoneTipo.FIXO, 35, "55135"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("DSLI", TelefoneTipo.FIXO, 36, "55136"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("GOLDEN LINE", TelefoneTipo.FIXO, 37, "55137"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("VIPER", TelefoneTipo.FIXO, 38, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("GT GROUP", TelefoneTipo.FIXO, 42, "55142"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("GLOBAL CROSSING (IMPSAT)", TelefoneTipo.FIXO, 45, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("HOJE", TelefoneTipo.FIXO, 46, "55140"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("BT COMMUNICATIONS", TelefoneTipo.FIXO, 47, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("PLENNA", TelefoneTipo.FIXO, 48, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("CAMBRIDGE", TelefoneTipo.FIXO, 49, "55150"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("51 BRASIL", TelefoneTipo.FIXO, 51, "55197"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("LINKNET", TelefoneTipo.FIXO, 52, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("TELEBIT", TelefoneTipo.FIXO, 54, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("ESPAS", TelefoneTipo.FIXO, 56, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("VOITEL", TelefoneTipo.FIXO, 58, "55158"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("NEXUS", TelefoneTipo.FIXO, 61, "55161"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("HELLO BRAZIL", TelefoneTipo.FIXO, 63, "55163"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("NEOTELECOM", TelefoneTipo.FIXO, 64, "55235"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("CGB VOIP", TelefoneTipo.FIXO, 65, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("REDEVOX", TelefoneTipo.FIXO, 69, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("LOCAWEB", TelefoneTipo.FIXO, 72, "55170"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("SERMATEL", TelefoneTipo.FIXO, 81, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("BBT BRASIL", TelefoneTipo.FIXO, 84, "55184"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("AMERICA NET", TelefoneTipo.FIXO, 85, "55106"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("KONECTA", TelefoneTipo.FIXO, 89, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("NEBRACAM", TelefoneTipo.FIXO, 95, null));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("AMIGO", TelefoneTipo.FIXO, 96, "55146"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("NAO IDENTIFICADO", TelefoneTipo.FIXO, 0, "55999"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("BRASTEL", TelefoneTipo.FIXO, 92, "55173"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("IPCORPTELECOM", TelefoneTipo.FIXO, 92, "55191"));
//        telefoneOperadoraDAO.save(new TelefoneOperadora("ALGAR", TelefoneTipo.FIXO, 92, "55112"));
//
//    }

}
