package br.com.tlmacedo.cafeperfeito.interfaces;

import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface Regex_Convert {

    KeyCombination CODE_KEY_SHIFT_CTRL_POSITIVO = new KeyCodeCombination(KeyCode.PLUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_POSITIVO = new KeyCharacterCombination("+", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CODE_KEY_SHIFT_CTRL_NEGATIVO = new KeyCodeCombination(KeyCode.MINUS, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_NEGATIVO = new KeyCharacterCombination("-", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CODE_KEY_SHIFT_CTRL_N = new KeyCodeCombination(KeyCode.N, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_N = new KeyCharacterCombination("[nN]", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);


    RadialGradient FUNDO_RADIAL_GRADIENT =
            new RadialGradient(0,
                    0,
                    0.5056179775280899,
                    0.5,
                    1.0,
                    true, CycleMethod.REFLECT,
                    new Stop(0.0, Color.color(1.0f, 0.4f, 0.0f, 1.0)),
                    new Stop(1.0, Color.color(1.0f, 1.0f, 1.0f, 1.0))
            );
    /**
     *
     */


    String REGEX_PONTUACAO = "[ !\"$%&'()*+,-./:;_`{|}]";

    String MASK_BAR_CODE = "#############";
    String REGEX_BAR_CODE = "(\\d{13})";
    String REGEX_BAR_CODE_FS = "$1";

    String MASK_CNPJ = "##.###.###/####-##";
    String REGEX_CNPJ_FS = "$1.$2.$3/$4-$5";
    String MASK_CPF = "###.###.###-##";
    String REGEX_CPF_FS = "$1.$2.$3-$4";
    String REGEX_CNPJ_CPF = "(\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})|(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})";

    String REGEX_EMAIL = "([\\w\\-]+\\.)*[\\w\\- ]+@([\\w\\- ]+\\.)+([\\w\\-]{2,3})";
    String REGEX_HOME_PAGE = "(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?";

    String MASK_NCM = "####.##.##";
    String REGEX_NCM_FS = "$1.$2.$3";
    String REGEX_NCM = "(\\d{4}\\.\\d{0,2}\\.\\d{0,2})";

    String MASK_CEST = "##.###.##";
    String REGEX_CEST_FS = "$1.$2.$3";
    String REGEX_CEST = "(\\d{2}\\.\\d{0,3}\\.\\d{0,2})";

    String MASK_TELEFONE = "(##) ####-####";
    String REGEX_TELEFONE_FS = "($1) $2-$3";
    String REGEX_TELEFONE = "(\\d{2})?(\\d{4,5})-(\\d{4})";
    String REGEX_TELEFONE_BD = "(\\d{2})?(\\d{4,5})(\\d{4})";

    String MASK_CEP = "##.###-###";
    String REGEX_CEP_FS = "$1.$2-$3";
    String REGEX_CEP = "(\\d{2}\\.\\d{3}-\\d{3})";

    String MASK_NFE_CHAVE = "#### #### #### #### #### #### #### #### #### #### ####";
    String REGEX_NFE_CHAVE_FS = "$1 ";
    String REGEX_NFE_CHAVE = "(\\d{44})";


    String REGEX_MASK_MOEDA_NUMERO = "#,##0";
    String REGEX_EXTENSAO_NFE = "\\.(xml|wsdl)";
    String REGEX_EXTENSAO_IMAGENS = "\\.(jpg|jpeg|png|gif)";


    String MASK_NFE_NUMERO = "###.###.###";
    String REGEX_NFE_NUMERO = "\\d{9}";

    String MASK_FISCAL_DOC_ORIGEM = "###########-#";
    String REGEX_FISCAL_DOC_ORIGEM = "\\d{11}-\\d{1}";

    String REGEX_DTF_DATA = "(\\d{2}/\\d{2}/\\d{4})";
    String REGEX_DTF_DATA_FS = "$1/$2/$3";
    String MASK_HORA_HMS = "##:##:##";
    String MASK_HORA_HM = "##:##";


    String PATH_CLASS_ARQ_NFE_CACERT = "/Volumes/150GB-Development/cafeperfeito/cafeperfeito/src/main/resources/certificado/cacertttt";

    DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    DecimalFormat PESO_FORMAT = new DecimalFormat("0.000");
    Locale LOCALE = new Locale("pt", "br");
    String LOCAL_TIME_ZONE = "America/Manaus";
    ZoneId MY_ZONE_TIME = ZoneId.of(LOCAL_TIME_ZONE);
    LocalDateTime DATAHORA_LOCAL = LocalDateTime.now().atZone(MY_ZONE_TIME).toLocalDateTime();
    DataFormat DT_DATA = new DataFormat("dd/MM/yyyy");
    DateTimeFormatter DTF_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter DTF_HORA_HM = DateTimeFormatter.ofPattern("HH:mm", LOCALE);
    DateTimeFormatter DTF_HORA_HMS = DateTimeFormatter.ofPattern("HH:mm:ss", LOCALE);
    DateTimeFormatter DTF_DATAHORA_HM = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", LOCALE);
    DateTimeFormatter DTF_DATAHORA_HMS = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", LOCALE);
    DateTimeFormatter DTF_NFE_TO_LOCAL_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", LOCALE);
    DateTimeFormatter DTF_DATAHORAFUSO = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ssXXX", LOCALE);
    DateTimeFormatter DTF_MYSQL_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter DTF_MYSQL_DATAHORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", LOCALE);
    DateTimeFormatter DTF_MYSQL_DATAHORA_HM = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", LOCALE);
    DateTimeFormatter DTF_MYSQL_DATAHORA_HMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", LOCALE);
    DateTimeFormatter DTF_MYSQL_DATAHORAFUSO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX", LOCALE);

    KeyCombination CODE_KEY_CTRL_ALT_B = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
    KeyCombination CHAR_KEY_CTRL_ALT_B = new KeyCharacterCombination("b".toLowerCase(), KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
    KeyCombination CODE_KEY_CTRL_ALT_N = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
    KeyCombination CHAR_KEY_CTRL_ALT_N = new KeyCharacterCombination("n".toLowerCase(), KeyCombination.CONTROL_DOWN, KeyCombination.ALT_DOWN);
    KeyCombination CODE_KEY_CTRL_Z = new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_CTRL_Z = new KeyCharacterCombination("z".toLowerCase(), KeyCombination.CONTROL_DOWN);


    KeyCombination CODE_KEY_SHIFT_CTRL_S = new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
    KeyCombination CHAR_KEY_SHIFT_CTRL_S = new KeyCharacterCombination("s", KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);


    /*
     *
     * WebServices
     *
     * */


    /*
     * http://bwipjs-api.metafloor.com/?bcid=ean13&text=7896078301063&includetext&scale=1&guardwhitespace
     */
    String WS_BARCODE_URL = "http://bwipjs-api.metafloor.com/?bcid=ean13&includetext&scale=1&guardwhitespace&text=";


    /*
     * cosmos */
    String WS_COSMOS_URL = "https://api.cosmos.bluesoft.com.br";
    String WS_COSMOS_SER_NCM = "/ncms/";
    String WS_COSMOS_SER_GTINS = "/gtins/";
    String WS_COSMOS_SER_GPCS = "/gpcs/";
    String WS_COSMOS_TOKEN = "o65EDRPgFu7mFNuv5vj5Aw";

    /*
     * receitaws */
    String WS_RECEITAWS_URL = "https://www.receitaws.com.br/v1/cnpj/";
    String WS_RECEITAWS_TOKEN = "1953100c818519b43b895394c25b0fa38525e2800587a8b140a42e6baff7a8af";

    /*
     * webmania */
    String WS_WEBMANIA_URL = "https://webmaniabr.com/api/1/cep/";
    String WS_WEBMANIA_APP_KEY = "GOxHMxSXNbX99szfTE7A6mMDmb26P1Ch";
    String WS_WEBMANIA_APP_SECRET = "kMx5QczId1GqVLbpZ52qgEgfRhiKWFPZfa39IZfp6NZhFmTq";

    /*
     * postmon */
    String WS_POSTMON_URL = "http://api.postmon.com.br/v1/cep/";

    /*
     * portabilidade celular */
//    String WS_PORTABILIDADE_CELULAR_USER = "user=tlmacedo";
//    String WS_PORTABILIDADE_CELULAR_PASS = "pass=Tlm487901";
//    String WS_PORTABILIDADE_CELULAR_URL = String.format("http://consultas.portabilidadecelular.com/painel/consulta_numero.php?%s&%s&search_number=",
//            WS_PORTABILIDADE_CELULAR_USER, WS_PORTABILIDADE_CELULAR_PASS);


}
