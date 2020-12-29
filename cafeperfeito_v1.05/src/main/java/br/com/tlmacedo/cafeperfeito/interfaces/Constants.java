package br.com.tlmacedo.cafeperfeito.interfaces;

public interface Constants {
//
//
//    /*
//     * Costants de referencia para o sistema
//     */
//    String SIS_COPYRIGHT = "Café Perfeito " + "\u00a9 " + LocalDate.now().getYear();
//    String SIS_TEMA = "orange";
//    String SIS_LOJA_ID = "1";
//    int SIS_DDD = 92;
//    int SIS_PORTA = 443;
//    Image SIS_PRODUTO_IMG_DEFAULT = null;
//    int SIS_PRODUTO_IMAGE_WIDTH = 250;
//    int SIS_PRODUTO_IMAGE_HEIGHT = 250;
//    String SIS_DOWNLOAD_IMAGE_TYPE = ".png";
//    String SIS_TOKEN_EXTENSAO = "cfg";
//    //String ServiceVariaveisSistematConfig.getPersonalizacao().getStyleSheets() = "/style/min/sidtm.min.css";
//    String SIS_PATH_DIR_IMAGE_DOWNLOAD = "/volumes/macOS/Usuários/thiagomacedo/Imagens/";
//
//
//    /*
//     * Cryptografia no Sistema
//     */
//    String CRYPT_PALAVRA_CHAVE = "CafePerfeito.Com";
//    String CRYPT_INITVECTOR = "RandomInitVector";
//
//
//    /*
//     * Mascaras para formatação dos campos nos formularios
//     */
//    String MASK_CARACTER_DIGITO = "#";
//    String MASK_CARACTER_UPPER = "U";
//    String MASK_CARACTER_LOWER = "L";
//    String MASK_CARACTER_DIGITO_TEXT = "A";
//    String MASK_CARACTER_INTERROGACAO = "?";
//    String MASK_CARACTER_ASTERISCO = "*";
//
//
//    /*
//     * Constantes para conexão com banco de dados MySql
//     */
//    String BD_DRIVER = "com.mysql.cj.jdbc.Driver";
//    String BD_DATABASE = "cafeperfeito";//ServiceCryptografia.decrypt("0+PeSCliRZ0xWuIPgPR44Q==");
//    String BD_HOST = "127.0.0.1";
//    String BD_DRIVER_CONN = "jdbc:mysql://";
//    String BD_PORTA = ":3306/";
//    String BD_USER = "cafeperfeito";// ServiceCryptografia.decrypt("rE58q+UsGH2Y1nsOECjpqw==");
//    String BD_PASS = "Tlm487901";//ServiceCryptografia.decrypt("ZmljSHSsQtjEZJJT1NDBIQ==");
//    String BD_URL = BD_DRIVER_CONN + BD_HOST + BD_PORTA + BD_DATABASE + "?useSSL=false";//"?useTimezone=true&serverTimezone=UTC&useSSL=true";
//    String BD_DATABASE_STB = BD_HOST + BD_PORTA + BD_DATABASE;
//
//
//    /*
//     * NF-e
//     */
//    String NFE_CODIGO_ESTADO = "13";
//    String SENHA_CERTIFICADO_TOKEN_A3 = "4879";// ServiceCryptografia.decrypt("JZ4WgTIDJiviuZ7agiW2/A==");
//
//
//    /*
//     * Paths para dados do sistema
//     */
//    String PATH_CLASS_IMAGE = "/image/";
//    String SIS_LOGO_240DP = PATH_CLASS_IMAGE + "ico/sis_logo_240dp.png";
//    //String ServiceVariaveisSistema.tConfig.getPaths().getPathIconeSistema() = PATH_CLASS_IMAGE + "ico/" + SIS_TEMA + "/";
//    String PATH_CLASS_SPLASH_IMG = PATH_CLASS_IMAGE + "splash/";
//    String PATH_CLASS_TOKEN_LIB = "/certificado/";
//    String PATH_CLASS_ARQ_NFE_CACERT = PATH_CLASS_TOKEN_LIB + "cacert";
//    String PATH_CLASS_XML_NFE_CTE = "/xml/";
//    String PATH_CLASS_IMAGE_FAVICON = "/favicon/";
//    String PATH_CLASS_IMAGE_PAINEL = "/painel/";
//
//    String PATH_DIR = "/Volumes/150GB-Development/Java/Intellij/sidtmcafe10/src/sidtmcafe/resources/";
//    String PATH_DIR_TOKEN_LIB = PATH_DIR + "certificado/";
//    String PATH_DIR_ARQ_NFE_CACERT = PATH_DIR_TOKEN_LIB + "cacert";
//    String PATH_DIR_XML_NFE_CTE = PATH_DIR + "/xml/";
//    String PATH_DIR_IMAGE_FAVICON = PATH_DIR + "/favicon/";
//    String PATH_DIR_IMAGE_PAINEL = PATH_DIR + "/painel/";
//
//
//    /*
//     *FXML do sistema
//     */
//    String PATH_FXML = "/fxml/";
//    String FXML_LOGIN = PATH_FXML + "FxmlLogin.fxml";
//    String FXML_LOGIN_TITLE = "Login";
//    //String FXML_LOGIN_ICON = ServiceVariaveisSistema.tConfig.getPaths().getPathIconeSistema() + "ic_cadeado_senha_24dp.png";
//
//    String FXML_PRINCIPAL = PATH_FXML + "FxmlPrincipal.fxml";
//    String FXML_PRINCIPAL_TITLE = "Café Perfeito";
//    //String FXML_PRINCIPAL_ICON_ATIVO = ServiceVariaveisSistema.tConfig.getPaths().getPathIconeSistema() + "ic_principal_ativo_24dp.png";
//    //String FXML_PRINCIPAL_ICON_DESATIVO = ServiceVariaveisSistema.tConfig.getPaths().getPathIconeSistema() + "ic_principal_desativo_24dp.png";
//
//    String FXML_CADASTRO_EMPRESA = PATH_FXML + "FxmlCadastroEmpresa.fxml";
//    String FXML_CADASTRO_PRODUTO = PATH_FXML + "FxmlCadastroProduto.fxml";
//    String FXML_ENTRADA_PRODUTO = PATH_FXML + "FxmlEntradaProduto.fxml";
//
//
//    /*
//     * Personalização do sistema
//     */
//    RadialGradient FUNDO_RADIAL_GRADIENT =
//            new RadialGradient(0,
//                    0,
//                    0.5056179775280899,
//                    0.5,
//                    1.0,
//                    true, CycleMethod.REFLECT,
//                    new Stop(0.0, Color.color(1.0f, 0.4f, 0.0f, 1.0)),
//                    new Stop(1.0, Color.color(1.0f, 1.0f, 1.0f, 1.0))
//            );
//    String[] SPLASH_IMAGENS = {
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_0.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_1.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_2.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_3.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_4.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_5.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_6.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_7.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_8.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_9.gif",
//            PATH_CLASS_SPLASH_IMG + "img_splash_coffe_10.gif"};
//
//
//    /*
//     * Constantes para data e horas
//     */
//    Locale LOCALE_MY = new Locale("pt", "BR");
//    DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
//    DecimalFormat PESO_FORMAT = new DecimalFormat("0.000");
//    String LOCAL_TIME_ZONE = "America/Manaus";
//    ZoneId MY_ZONE_TIME = ZoneId.of(LOCAL_TIME_ZONE);
//    LocalDateTime DATAHORA_LOCAL = LocalDateTime.now().atZone(MY_ZONE_TIME).toLocalDateTime();
//    DataFormat DT_DATA = new DataFormat("dd/MM/yyyy");
//    DateTimeFormatter DTF_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    DateTimeFormatter DTF_HORA = DateTimeFormatter.ofPattern("HH:mm:ss");
//    DateTimeFormatter DTF_DATAHORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//    DateTimeFormatter DTF_NFE_TO_LOCAL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
//    DateTimeFormatter DTF_DATAHORAFUSO = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ssXXX");
//    DateTimeFormatter DTF_MYSQL_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    DateTimeFormatter DTF_MYSQL_DATAHORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    DateTimeFormatter DTF_MYSQL_DATAHORAFUSO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
//
//    KeyCombination CODE_KEY_CTRL_ALT_B = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
//    KeyCombination CHAR_KEY_CTRL_ALT_B = new KeyCharacterCombination("b".toLowerCase(), KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
//    KeyCombination CODE_KEY_CTRL_Z = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
//    KeyCombination CHAR_KEY_CTRL_Z = new KeyCharacterCombination("z".toLowerCase(), KeyCombination.CONTROL_DOWN);
//    KeyCombination CODE_KEY_SHIFT_CTRL_POSITIVO = new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
//    KeyCombination CHAR_KEY_SHIFT_CTRL_POSITIVO = new KeyCharacterCombination("+", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
//    KeyCombination CODE_KEY_SHIFT_CTRL_NEGATIVO = new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
//    KeyCombination CHAR_KEY_SHIFT_CTRL_NEGATIVO = new KeyCharacterCombination("-", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
//
//
//
//    /*
//     *
//     * WebServices
//     *
//     * */
//
//
//    /*
//     * http://bwipjs-api.metafloor.com/?bcid=ean13&text=7896078301063&includetext&scale=1&guardwhitespace
//     */
//    String WS_BARCODE_URL = "http://bwipjs-api.metafloor.com/?bcid=ean13&includetext&scale=1&guardwhitespace&text=";
//
//
//    /*
//     * cosmos */
//    String WS_COSMOS_URL = "https://api.cosmos.bluesoft.com.br";
//    String WS_COSMOS_SER_NCM = "/ncms/";
//    String WS_COSMOS_SER_GTINS = "/gtins/";
//    String WS_COSMOS_SER_GPCS = "/gpcs/";
//    String WS_COSMOS_TOKEN = "o65EDRPgFu7mFNuv5vj5Aw";
//
//    /*
//     * receitaws */
//    String WS_RECEITAWS_URL = "https://www.receitaws.com.br/v1/cnpj/";
//    String WS_RECEITAWS_TOKEN = "1953100c818519b43b895394c25b0fa38525e2800587a8b140a42e6baff7a8af";
//
//    /*
//     * webmania */
//    String WS_WEBMANIA_URL = "https://webmaniabr.com/api/1/cep/";
//    String WS_WEBMANIA_APP_KEY = "GOxHMxSXNbX99szfTE7A6mMDmb26P1Ch";
//    String WS_WEBMANIA_APP_SECRET = "kMx5QczId1GqVLbpZ52qgEgfRhiKWFPZfa39IZfp6NZhFmTq";
//
//    /*
//     * postmon */
//    String WS_POSTMON_URL = "http://api.postmon.com.br/v1/cep/";
//
//    /*
//     * portabilidade celular */
//    String WS_PORTABILIDADE_CELULAR_USER = "user=tlmacedo";
//    String WS_PORTABILIDADE_CELULAR_PASS = "pass=Tlm487901";
//    String WS_PORTABILIDADE_CELULAR_URL = String.format("http://consultas.portabilidadecelular.com/painel/consulta_numero.php?%s&%s&search_number=",
//            WS_PORTABILIDADE_CELULAR_USER, WS_PORTABILIDADE_CELULAR_PASS);
//
//
//    String REGEX_MASK_MOEDA_NUMERO = "#,##0";
//    String REGEX_EXTENSAO_NFE = "\\.(xml|wsdl)";
//    String REGEX_EXTENSAO_IMAGENS = "\\.(jpg|jpeg|png|gif)";
//    String REGEX_CNPJ_CPF = "(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})|(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})";
//    String REGEX_EMAIL = "([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})";
//    String REGEX_HOME_PAGE = "((http)|(https)|(ftp)):\\/\\/([\\- \\w]+\\.)+\\w{2,3}(\\/ [%\\-\\w]+(\\.\\w{2,})?)*";
//    String REGEX_DDD = "(.(\\d{2}.))";
//    String REGEX_TELEFONE_DDD = "(.(\\d{2}.))?\\s9?\\d{4}-\\d{4}";
//    String REGEX_TELEFONE = "\\d{4}-\\d{4}";
//    String REGEX_PONTUACAO = "[ !\"$%&'()*+,-./:;_`{|}]";
//    Pair<String, String> REGEX_FS_CNPJ = new Pair<String, String>("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
//    Pair<String, String> REGEX_FS_CPF = new Pair<String, String>("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
//    Pair<String, String> REGEX_FS_CEP = new Pair<String, String>("(\\d{2})(\\d{3})(\\d{3})", "$1.$2-$3");
//    Pair<String, String> REGEX_FS_TELEFONE = new Pair<String, String>("(\\d{4})(\\d{4})", "$1-$2");
//    Pair<String, String> REGEX_FS_CELULAR = new Pair<String, String>("(\\d{1})?(\\d{4})(\\d{4})", "$1 $2-$3");
//    Pair<String, String> REGEX_FS_NCM = new Pair<String, String>("(\\d{4})(\\d{0,2})(\\d{0,2})", "$1.$2.$3");
//    Pair<String, String> REGEX_FS_CEST = new Pair<String, String>("(\\d{2})(\\d{3})(\\d{2})", "$1.$2.$3");
//    Pair<String, String> REGEX_FS_NFE_CHAVE = new Pair<String, String>("(\\d{4}+)(\\d{4}?)", "$1 $2 ");
//    Pair<String, String> REGEX_FS_NFE_NUMERO = new Pair<String, String>("(\\d{3})?(\\d{3})", "$1.$2");
//    Pair<String, String> REGEX_FS_NFE_DOC_ORIGEM = new Pair<String, String>("(\\d{11})(\\d{1})", "$1-$2");

}
