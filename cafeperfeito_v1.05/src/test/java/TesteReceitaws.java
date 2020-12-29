import br.com.tlmacedo.cafeperfeito.model.dao.EmpresaDAO;
import br.com.tlmacedo.cafeperfeito.model.tm.TabModelEmpresa;
import br.com.tlmacedo.cafeperfeito.model.vo.Empresa;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.enums.ClassificacaoJuridica;
import br.com.tlmacedo.cafeperfeito.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.REGEX_CNPJ_CPF;
import static br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema.TCONFIG;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-06-11
 * Time: 14:29
 */

public class TesteReceitaws {

    private TabModelEmpresa modelEmpresa = new TabModelEmpresa();
    private Empresa empresa;
    private ObservableList<Empresa> empresaObservableList = FXCollections.observableArrayList(new EmpresaDAO().getAll(Empresa.class, null, null, null, null));
    private FilteredList<Empresa> empresaFilteredList = new FilteredList<>(empresaObservableList);
    private Pattern pattern;
    private Matcher matcher;
    private String txtCNPJ;
    private ServiceAlertMensagem alertMensagem;
    private ClassificacaoJuridica cboClassificacaoJuridica;

    public static void main(String[] args) {
        new ServiceVariaveisSistema().getVariaveisSistemaSimples();
        System.out.printf("qual cnpj vc deseja verificar?: ");
        String cnpjTmp = new Scanner(System.in).nextLine().replaceAll("\\D", "").trim();
        String cnpj = ServiceMascara.getCnpj(cnpjTmp);
        System.out.printf("o cnpj que vai ser pesquisado é : [%s]\n", cnpj);
        TesteReceitaws receitaws = new TesteReceitaws(cnpj);
        receitaws.carregaEmpresas();
        receitaws.consulta();
    }

    public TesteReceitaws(String cnpj) {
        System.out.printf("o cnpjtmp é: [%s]\n", cnpj);
        setTxtCNPJ(cnpj);
        System.out.printf("o cnpj que está é: [%s]\n", getTxtCNPJ());
    }

    private void carregaEmpresas() {
        getModelEmpresa().setEmpresaObservableList(getEmpresaObservableList());
        getModelEmpresa().setEmpresaFilteredList(getEmpresaFilteredList());
        setEmpresa(new Empresa());
        setCboClassificacaoJuridica(ClassificacaoJuridica.PESSOAJURIDICA);
    }

    private void consulta() {
        setPattern(Pattern.compile(REGEX_CNPJ_CPF, Pattern.CASE_INSENSITIVE));
        setMatcher(getPattern().matcher(getTxtCNPJ()));
        Empresa empTmp;
        if (getMatcher().find()) {
            if (!ServiceValidarDado.isCnpjCpfValido(getMatcher().group())) {
                setAlertMensagem(new ServiceAlertMensagem());
                getAlertMensagem().setCabecalho("Dado inválido!");
                getAlertMensagem().setPromptText(String.format("%s, o %s: [%s] é inválido!",
                        StringUtils.capitalize(LogadoInf.getUserLog().getApelido()),
                        getTxtCNPJ(), getMatcher().group()));
                getAlertMensagem().setStrIco("ic_webservice_24dp.png");
                getAlertMensagem().getRetornoAlert_OK();
                System.out.printf("[%s]\nfocus foi para txtCNPJ\n", "isCnpjCpfValido_False");
                return;
            } else if ((empTmp = getModelEmpresa().jaExiste(getMatcher().group(), getEmpresa(), false)) != null) {
                System.out.printf("[%s]\nCnpj: [%s]\nRazão :[%s]\nFantasia: [%s]\n", "jaExiste_True", empTmp.getCnpj(), empTmp.getRazao(), empTmp.getFantasia());
                return;
            } else {
                if (getCboClassificacaoJuridica().getCod() == ClassificacaoJuridica.PESSOAJURIDICA.getCod()
                        && pesqEmpresa(getEmpresa(), getMatcher().group())) {
                    System.out.printf("[%s]\nfocus foi para txtIE\n", "exiir_empresa_0001");
                    exibirEmpresa();
                }
                System.out.printf("[%s]\nfocus foi para txtIE\n", "exiir_empresa_0002");
//                exibirEmpresa();
            }
        }
    }

    private boolean pesqEmpresa(Empresa empresa, String busca) {

//        System.out.printf("Paramentros:\n\tbusca: [%s]", busca);
//        System.out.printf("\n\turl: [%s]", TCONFIG.getWs().getReceitaws().getUrl() + busca.replaceAll("\\D", ""));
//        System.out.printf("\n\ttoken: [%s]", TCONFIG.getWs().getReceitaws().getToken() + "/days/0");
//        System.out.printf("\n\tempresa: [%s]\n", empresa.toString());

        return Empresa.setWsEmpresa_ReceitaWs(empresa, ServiceBuscaWebService.getJsonObjectHttpUrlConnection(
                TCONFIG.getWs().getReceitaws().getUrl() + busca.replaceAll("\\D", ""),
                TCONFIG.getWs().getReceitaws().getToken(),
                "/days/0"
        ));
    }

    private void exibirEmpresa() {
        System.out.println(getEmpresa().toString());
        ServiceJSonUtil.printJsonFromObject(getEmpresa(), "Empresa:::::");
    }

    public String getTxtCNPJ() {
        return txtCNPJ;
    }

    public void setTxtCNPJ(String txtCNPJ) {
        this.txtCNPJ = txtCNPJ;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ServiceAlertMensagem getAlertMensagem() {
        return alertMensagem;
    }

    public void setAlertMensagem(ServiceAlertMensagem alertMensagem) {
        this.alertMensagem = alertMensagem;
    }

    public TabModelEmpresa getModelEmpresa() {
        return modelEmpresa;
    }

    public void setModelEmpresa(TabModelEmpresa modelEmpresa) {
        this.modelEmpresa = modelEmpresa;
    }

    public ObservableList<Empresa> getEmpresaObservableList() {
        return empresaObservableList;
    }

    public void setEmpresaObservableList(ObservableList<Empresa> empresaObservableList) {
        this.empresaObservableList = empresaObservableList;
    }

    public FilteredList<Empresa> getEmpresaFilteredList() {
        return empresaFilteredList;
    }

    public void setEmpresaFilteredList(FilteredList<Empresa> empresaFilteredList) {
        this.empresaFilteredList = empresaFilteredList;
    }

    public ClassificacaoJuridica getCboClassificacaoJuridica() {
        return cboClassificacaoJuridica;
    }

    public void setCboClassificacaoJuridica(ClassificacaoJuridica cboClassificacaoJuridica) {
        this.cboClassificacaoJuridica = cboClassificacaoJuridica;
    }
}
