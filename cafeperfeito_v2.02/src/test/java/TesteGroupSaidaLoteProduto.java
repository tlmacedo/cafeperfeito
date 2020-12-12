import br.com.tlmacedo.cafeperfeito.model.dao.SaidaProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoNfe;
import br.com.tlmacedo.cafeperfeito.nfe.Nfe;
import br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TesteGroupSaidaLoteProduto {

    static Long nPed = 85L;
    //    static Long nPed = 102L;
    static SaidaProduto pedido;
    private static StringProperty xmlNFe = new SimpleStringProperty();


    public static void main(String[] args) throws Exception {
        new ServiceConfigSis().getVariaveisSistema();
        setPedido(new SaidaProdutoDAO().getById(SaidaProduto.class, getnPed()));

        System.out.printf("iniciando nova NF-e\n");
        SaidaProdutoNfe saidaProdutoNfe;
        if ((saidaProdutoNfe = getPedido().getSaidaProdutoNfeList().stream()
                .filter(saidaProdutoNfe1 -> !saidaProdutoNfe1.isCancelada())
                .findFirst().orElse(null)) == null) {
            saidaProdutoNfe = new SaidaProdutoNfe();
            saidaProdutoNfe.saidaProdutoProperty().setValue(getPedido());
            getPedido().getSaidaProdutoNfeList().add(saidaProdutoNfe);
        }
        new Nfe(saidaProdutoNfe, true);


    }


    public static Long getnPed() {
        return nPed;
    }

    public static void setnPed(Long nPed) {
        TesteGroupSaidaLoteProduto.nPed = nPed;
    }

    public static SaidaProduto getPedido() {
        return pedido;
    }

    public static void setPedido(SaidaProduto pedido) {
        TesteGroupSaidaLoteProduto.pedido = pedido;
    }

    public static String getXmlNFe() {
        return xmlNFe.get();
    }

    public static StringProperty xmlNFeProperty() {
        return xmlNFe;
    }

    public static void setXmlNFe(String xmlNFe) {
        TesteGroupSaidaLoteProduto.xmlNFe.set(xmlNFe);
    }
}
