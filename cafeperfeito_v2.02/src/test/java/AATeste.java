import javafx.scene.image.Image;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public class AATeste {
//    public static void main(String[] args) throws IOException, SQLException {
////        ObservableList<ProdutoCodigoBarra> barraObservableList = FXCollections.observableArrayList(
////                new ProdutoCodigoBarraDAO().getAll(ProdutoCodigoBarra.class, null, null)
////        );
////        ProdutoCodigoBarraDAO codigoBarraDAO = new ProdutoCodigoBarraDAO();
////        barraObservableList.stream()
////                .forEach(produtoCodigoBarra -> {
////                    try {
////                        produtoCodigoBarra.setImgCodigoBarra(new ServiceEan13(produtoCodigoBarra.getCodigoBarra()).getBlobEAN13());
////                        codigoBarraDAO.merger(produtoCodigoBarra);
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    } catch (Exception throwables) {
////                        throwables.printStackTrace();
////                    }
////                });
//
//        final int[] cont = {0};
//        final File[] fileTmp = new File[1];
//
//        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
//        ObservableList<Colaborador> colaboradorObservableList = FXCollections.observableArrayList(
//                colaboradorDAO.getAll(Colaborador.class, null, null)
//        );
//        colaboradorObservableList.stream()
//                .forEach(colaborador -> {
//                    try {
//                        System.out.printf("ini_Colab:[%02d]", cont[0]);
//                        fileTmp[0] = ServiceFileFinder.finder("/Users/thiagomacedo/Desktop/MySqlTestes/",
//                                colaborador.getApelido(), "\\.(jpg|jpeg|png)");
//                        if (fileTmp[0] != null)
//                            colaborador.setImagem(ServiceImageUtil.getBobFromImage(fileTmp[0], 250, 250));
//                        System.out.printf("fileTmp: [%s]\n", fileTmp[0]);
//                        if (colaborador.getImagem() != null)
//                            colaboradorDAO.merger(colaborador);
//                        System.out.printf("\tEnd_Colab:[%02d]\n", cont[0]);
//                    } catch (Exception ex) {
//                        System.out.printf("\nerr_Colab:[%02d]", cont[0]);
//                        ex.printStackTrace();
//                    }
//                    cont[0]++;
//                });
//
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(
//                produtoDAO.getAll(Produto.class, null, null)
//        );
//        cont[0] = 0;
//        produtoObservableList.stream()
//                .forEach(produto -> {
//                    try {
//                        System.out.printf("ini_Prod:[%02d]", cont[0]);
//                        if (produto.getProdutoCodigoBarraList().size() > 0) {
//                            for (ProdutoCodigoBarra cod : produto.getProdutoCodigoBarraList()) {
//                                cod.setImgCodigoBarra(new ServiceEan13(cod.getCodigoBarra()).getBlobEAN13());
//                                fileTmp[0] = ServiceFileFinder.finder("/Users/thiagomacedo/Desktop/MySqlTestes/",
//                                        cod.getCodigoBarra(), "\\.(jpg|jpeg|png)");
//                                if (fileTmp[0] != null)
//                                    produto.setImgProduto(ServiceImageUtil.getBobFromImage(fileTmp[0], 250, 250));
//                            }
//                            if (produto.getImgProduto() != null)
//                                produtoDAO.merger(produto);
//                        }
//                        System.out.printf("\tEnd_Prod:[%02d]\n", cont[0]);
//                    } catch (Exception ex) {
//                        System.out.printf("\nerr_Prod:[%02d]", cont[0]);
//                        ex.printStackTrace();
//                    }
//                    cont[0]++;
//                });
//
//        System.out.printf("\n\nTerminamos Uffa!!!");
//
////
////        ObservableList<Produto> produtoObservableList = FXCollections.observableArrayList(
////                new ProdutoDAO().getAll(Produto.class, null, null)
////        );
////        produtoObservableList.stream()
////                .forEach(produto -> {
////                    try {
////
////                    } catch (Exception ex) {
////                        ex.printStackTrace();
////                    }
////                });
////
////
////        ObservableList<ProdutoCodigoBarra> barraObservableList = FXCollections.observableArrayList(
////                new ProdutoCodigoBarraDAO().getAll(ProdutoCodigoBarra.class, null, null)
////        );
////        barraObservableList.stream()
////                .forEach(produtoCodigoBarra -> {
////                    File fileTmp;
////                    Produto prod = new ProdutoDAO().getById(Produto.class, produtoCodigoBarra.getId());
////                    prod.setImgProduto(null);
//////                        System.out.printf("prod: [%s]\n", prod.descricaoProperty().getValue());
//////                        System.out.printf("código0: [%s]", produtoCodigoBarra.getCodigoBarra());
//////                        fileTmp = ServiceFileFinder.finder("/Users/thiagomacedo/Desktop/MySqlTestes/",
//////                                produtoCodigoBarra.getCodigoBarra(), "\\.(jpg|jpeg|png)");
//////                        if (fileTmp != null)
//////                            prod.setImgProduto(imagem2Blob(ServiceImageUtil.getImageResized(new Image(new FileInputStream(fileTmp)),
//////                                    250, 250)));
////                    new ProdutoDAO().merger(prod);
//////                        if (prod.getImgProduto() != null)
//////                            System.out.printf("\ncódigo1: [%s]\timgAdd: %s\n", fileTmp.getName(), prod.getImgProduto());
//////                        else
//////                            System.out.printf("\tsemImagem[]\n");
//////                        System.out.printf("\n\n");
////                });
//    }

    private static Blob imagem2Blob(Image image) throws IOException, SQLException {
        return null;
//        return new SerialBlob(ServiceImageUtil.getInputStreamFromImage(image).readAllBytes());
    }
}