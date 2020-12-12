import br.com.tlmacedo.cafeperfeito.model.dao.FichaKardexDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.SaidaProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.FichaKardex;
import br.com.tlmacedo.cafeperfeito.model.vo.Recebimento;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class RefreshDataCadastroSaidas {
    SaidaProdutoDAO saidaProdutoDAO = new SaidaProdutoDAO();
    FichaKardexDAO fichaKardexDAO = new FichaKardexDAO();

    public static void main(String[] args) throws Exception {
        new RefreshDataCadastroSaidas().atualizaDatas();
    }

    private void atualizaDatas() throws Exception {
        System.out.printf("iniciando...\n");

        ObservableList<SaidaProduto> saidaProdutoObservableList = getSaidaProdutoDAO()
                .getAll(SaidaProduto.class, "id>=185", null).stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        System.out.printf("lista de SaidaProduto já preparada...\n");
        System.out.printf("percorre lista:\n");
        for (SaidaProduto saida : saidaProdutoObservableList) {
            System.out.printf("\t %s\n", saida);
            LocalDateTime ldtSaida = LocalDateTime.of(saida.dtSaidaProperty().getValue(), LocalTime.of(0, 0, 0));
            saida.dtCadastroProperty().setValue(ldtSaida);
            ObservableList<FichaKardex> fichaKardexObservableList = getFichaKardexDAO()
                    .getAll(FichaKardex.class, String.format("qtdSaida>0 AND documento=%s",
                            saida.idProperty().getValue().intValue()), null).stream()
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            for (FichaKardex ficha : fichaKardexObservableList) {
                System.out.printf("\t\t\t fichaKardex: %s\n", ficha);
                ficha.dtMovimentoProperty().setValue(ldtSaida);
                getFichaKardexDAO().merger(ficha);
            }

            System.out.printf("\t\tdtContasAReceber\n");
            saida.getContasAReceber().dtCadastroProperty().setValue(ldtSaida);
            for (Recebimento recebimento : saida.getContasAReceber().getRecebimentoList()) {
                System.out.printf("\t\t\trecebimento: %s\n", recebimento);
                recebimento.dtCadastroProperty().setValue(ldtSaida);
            }
            getSaidaProdutoDAO().merger(saida);

            System.out.printf("Salvou item\n\n");
        }
        System.out.printf("finalizou!!!!\n");
    }

    public SaidaProdutoDAO getSaidaProdutoDAO() {
        return saidaProdutoDAO;
    }

    public void setSaidaProdutoDAO(SaidaProdutoDAO saidaProdutoDAO) {
        this.saidaProdutoDAO = saidaProdutoDAO;
    }

    public FichaKardexDAO getFichaKardexDAO() {
        return fichaKardexDAO;
    }

    public void setFichaKardexDAO(FichaKardexDAO fichaKardexDAO) {
        this.fichaKardexDAO = fichaKardexDAO;
    }
}
