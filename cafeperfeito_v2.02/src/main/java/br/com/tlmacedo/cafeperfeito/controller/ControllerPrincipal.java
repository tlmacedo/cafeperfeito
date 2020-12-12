package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.MenuPrincipalDAO;
import br.com.tlmacedo.cafeperfeito.model.dao.SaidaProdutoDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.MenuPrincipal;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProduto;
import br.com.tlmacedo.cafeperfeito.model.vo.SaidaProdutoNfe;
import br.com.tlmacedo.cafeperfeito.nfe.Nfe;
import br.com.tlmacedo.cafeperfeito.service.ServiceComandoTecladoMouse;
import br.com.tlmacedo.cafeperfeito.service.ServiceStatusBar;
import br.com.tlmacedo.cafeperfeito.view.*;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static br.com.tlmacedo.cafeperfeito.interfaces.Regex_Convert.*;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;


public class ControllerPrincipal implements Initializable, ModeloCafePerfeito {

    public ImageView imgTesteExecute;

    public ImageView getImgTesteExecute() {
        return imgTesteExecute;
    }

    public void setImgTesteExecute(ImageView imgTesteExecute) {
        this.imgTesteExecute = imgTesteExecute;
    }

    public BorderPane painelViewPrincipal;
    public TabPane tabPaneViewPrincipal;
    public Label lblImageLogoViewPrincipal;
    public TreeView<MenuPrincipal> treeMenuViewPrincipal;
    public ImageView imgMenuPrincipalExpande;
    public ImageView imgMenuPrincipalRetrair;
    public Label lblCopyRight;
    public ToolBar statusBar_ViewPrincipal;
    public Label stbLogadoInf;
    public Label stbTeclas;
    public Label stbRelogio;

    private Stage principalStage = ViewPrincipal.getStage();
    public static ControllerPrincipal ctrlPrincipal;
    private List<MenuPrincipal> menuPrincipalList = new ArrayList<>();
    private String tabSelecionada = "";
    private EventHandler<KeyEvent> eventHandlerPrincipal;
    private Image icoJanelaAtivado = new Image(getClass().getResource(TCONFIG.getFxml().getPrincipal().getIconeAtivo()).toString());
    private Image icoJanelaDesativado = new Image(getClass().getResource(TCONFIG.getFxml().getPrincipal().getIconeDesativo()).toString());
    private ServiceStatusBar serviceStatusBar;
    public static String lastKey;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ctrlPrincipal = this;
            getLblImageLogoViewPrincipal().setVisible(true);
            criarObjetos();
            preencherObjetos();
            fatorarObjetos();
            escutarTecla();
            fieldsFormat();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void fieldsFormat() throws Exception {

    }

    @Override
    public void fechar() {
        getPrincipalStage().close();
    }

    @Override
    public void criarObjetos() throws Exception {
        getLblImageLogoViewPrincipal().setVisible(true);
    }

    @Override
    public void preencherObjetos() throws Exception {
        carregaMenuPrincipal();
        setServiceStatusBar(new ServiceStatusBar(getStatusBar_ViewPrincipal(), getStbLogadoInf(), getStbTeclas(), getStbRelogio()));
    }

    @Override
    public void fatorarObjetos() throws Exception {

    }

    @Override
    public void escutarTecla() {
        getImgMenuPrincipalExpande().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> expandeAllMenuPrincipal(true));
        getImgMenuPrincipalRetrair().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> expandeAllMenuPrincipal(false));

        setEventHandlerPrincipal(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (CODE_KEY_SHIFT_CTRL_POSITIVO.match(event) || CHAR_KEY_SHIFT_CTRL_POSITIVO.match(event))
                    getImgMenuPrincipalExpande().fireEvent(ServiceComandoTecladoMouse.clickMouse(1));
                if (CODE_KEY_SHIFT_CTRL_NEGATIVO.match(event) || CHAR_KEY_SHIFT_CTRL_NEGATIVO.match(event))
                    getImgMenuPrincipalRetrair().fireEvent(ServiceComandoTecladoMouse.clickMouse(1));
                if (event.getCode() == KeyCode.F12)
                    if (getTabPaneViewPrincipal().getTabs().size() == 0)
                        fechar();
                if (event.isShiftDown() && event.isControlDown()) {
                    addTab(
                            getMenuPrincipalList().stream()
                                    .filter(mn -> mn.getTeclaAtalho().equals("ctrl+shift+" + event.getCode().toString().toLowerCase()))
                                    .findFirst().orElse(null)
                    );
                }
            }
        });
        getPainelViewPrincipal().addEventHandler(KeyEvent.KEY_PRESSED, getEventHandlerPrincipal());

        getTabPaneViewPrincipal().getTabs().addListener((ListChangeListener<? super Tab>) c -> {
            if (c.getList().size() > 0) {
                getPrincipalStage().getIcons().setAll(getIcoJanelaAtivado());
            } else {
                getPrincipalStage().getIcons().setAll(getIcoJanelaDesativado());
                getServiceStatusBar().atualizaStatusBar("");
            }
            getLblImageLogoViewPrincipal().setVisible(getTabPaneViewPrincipal().getTabs().size() == 0);
        });

        getTreeMenuViewPrincipal().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            MenuPrincipal menuClicado;
            if ((menuClicado = getTreeMenuViewPrincipal().getSelectionModel().getSelectedItem().getValue()) == null)
                return;
            if (event.getClickCount() == 1) {
                if (menuClicado.get_menu_label().equals("sair"))
                    fechar();
            } else {
                if (!menuClicado.get_menu_label().equals("sair"))
                    addTab(menuClicado);
            }
        });

    }


    private int tabAberta(String menuLabel) {
        for (int i = 0; i < getTabPaneViewPrincipal().getTabs().size(); i++)
            if (getTabPaneViewPrincipal().getTabs().get(i).getText().equals(menuLabel))
                return i;
        return -1;
    }

    private void expandeAllMenuPrincipal(boolean expand) {
        for (int i = 0; i < getTreeMenuViewPrincipal().getExpandedItemCount(); i++)
            getTreeMenuViewPrincipal().getTreeItem(i).setExpanded(expand);
    }

    private void carregaMenuPrincipal() {
        getLblCopyRight().setText(String.format("%s %d %s",
                TCONFIG.getInfLoja().getCopyright(),
                LocalDate.now().getYear(),
                TCONFIG.getInfLoja().getTitulo()
        ));
        String path = TCONFIG.getPaths().getPathIconeSistema();
        setMenuPrincipalList(
                new MenuPrincipalDAO()
                        .getAll(MenuPrincipal.class, null, null)
        );
        TreeItem[] treeItems = new TreeItem[getMenuPrincipalList().size() + 1];
        treeItems[0] = new TreeItem();
        int i = 0;
        for (MenuPrincipal menu : getMenuPrincipalList()) {
            i = (int) menu.getId();
            treeItems[i] = new TreeItem(menu);
            if (!menu.getIcoMenu().equals(menu))
                treeItems[i].setGraphic(new ImageView(getClass().getResource(path + menu.getIcoMenu()).toString()));
            treeItems[i].setExpanded(true);
            treeItems[menu.getMenuPai_id()].getChildren().add(treeItems[i]);
        }
        getTreeMenuViewPrincipal().setRoot(treeItems[0]);
        getTreeMenuViewPrincipal().setShowRoot(false);
    }

    private void addTab(MenuPrincipal menu) {
        if (menu == null) return;
        int tabId;
        if ((tabId = tabAberta(menu.getMenuLabel())) < 0) {
            Tab tab = null;
            switch (menu.get_menu()) {
                case "empresa":
                    tab = new ViewCadastroEmpresa().tabCadastroEmpresa(menu.getMenuLabel());
                    break;
                case "entradaproduto":
                    tab = new ViewEntradaProduto().tabEntradaProduto(menu.getMenuLabel());
                    break;
                case "saidaproduto":
                    tab = new ViewSaidaProduto().tabSaidaProduto(menu.getMenuLabel());
                    break;
                case "contasareceber":
                    tab = new ViewContasAReceber().tabContasAReceber(menu.getMenuLabel());
                    break;
//                case "notafiscal":
//                    tab = new ViewPedidoNFe().tabPedidoNFe(menu.getMenuLabel());
//                    break;
            }
            if (tab != null) {
                tabId = getTabPaneViewPrincipal().getTabs().size();
                getTabPaneViewPrincipal().getTabs().add(tab);
            }
        }
        if (getTabPaneViewPrincipal().getTabs().size() > 0) {
            getTabPaneViewPrincipal().getSelectionModel().select(tabId);

            getTabPaneViewPrincipal().getSelectionModel().getSelectedItem().setOnCloseRequest(event -> {

            });
            getTabPaneViewPrincipal().getSelectionModel().select(tabId);
        }
    }

    public BorderPane getPainelViewPrincipal() {
        return painelViewPrincipal;
    }

    public void setPainelViewPrincipal(BorderPane painelViewPrincipal) {
        this.painelViewPrincipal = painelViewPrincipal;
    }

    public TabPane getTabPaneViewPrincipal() {
        return tabPaneViewPrincipal;
    }

    public void setTabPaneViewPrincipal(TabPane tabPaneViewPrincipal) {
        this.tabPaneViewPrincipal = tabPaneViewPrincipal;
    }

    public Label getLblImageLogoViewPrincipal() {
        return lblImageLogoViewPrincipal;
    }

    public void setLblImageLogoViewPrincipal(Label lblImageLogoViewPrincipal) {
        this.lblImageLogoViewPrincipal = lblImageLogoViewPrincipal;
    }

    public TreeView<MenuPrincipal> getTreeMenuViewPrincipal() {
        return treeMenuViewPrincipal;
    }

    public void setTreeMenuViewPrincipal(TreeView<MenuPrincipal> treeMenuViewPrincipal) {
        this.treeMenuViewPrincipal = treeMenuViewPrincipal;
    }

    public ImageView getImgMenuPrincipalExpande() {
        return imgMenuPrincipalExpande;
    }

    public void setImgMenuPrincipalExpande(ImageView imgMenuPrincipalExpande) {
        this.imgMenuPrincipalExpande = imgMenuPrincipalExpande;
    }

    public ImageView getImgMenuPrincipalRetrair() {
        return imgMenuPrincipalRetrair;
    }

    public void setImgMenuPrincipalRetrair(ImageView imgMenuPrincipalRetrair) {
        this.imgMenuPrincipalRetrair = imgMenuPrincipalRetrair;
    }

    public Label getLblCopyRight() {
        return lblCopyRight;
    }

    public void setLblCopyRight(Label lblCopyRight) {
        this.lblCopyRight = lblCopyRight;
    }

    public ToolBar getStatusBar_ViewPrincipal() {
        return statusBar_ViewPrincipal;
    }

    public void setStatusBar_ViewPrincipal(ToolBar statusBar_ViewPrincipal) {
        this.statusBar_ViewPrincipal = statusBar_ViewPrincipal;
    }

    public Label getStbLogadoInf() {
        return stbLogadoInf;
    }

    public void setStbLogadoInf(Label stbLogadoInf) {
        this.stbLogadoInf = stbLogadoInf;
    }

    public Label getStbTeclas() {
        return stbTeclas;
    }

    public void setStbTeclas(Label stbTeclas) {
        this.stbTeclas = stbTeclas;
    }

    public Label getStbRelogio() {
        return stbRelogio;
    }

    public void setStbRelogio(Label stbRelogio) {
        this.stbRelogio = stbRelogio;
    }

    public Stage getPrincipalStage() {
        return principalStage;
    }

    public void setPrincipalStage(Stage principalStage) {
        this.principalStage = principalStage;
    }

    public static ControllerPrincipal getCtrlPrincipal() {
        return ctrlPrincipal;
    }

    public static void setCtrlPrincipal(ControllerPrincipal ctrlPrincipal) {
        ControllerPrincipal.ctrlPrincipal = ctrlPrincipal;
    }

    public List<MenuPrincipal> getMenuPrincipalList() {
        return menuPrincipalList;
    }

    public void setMenuPrincipalList(List<MenuPrincipal> menuPrincipalList) {
        this.menuPrincipalList = menuPrincipalList;
    }

    public String getTabSelecionada() {
        return tabSelecionada;
    }

    public void setTabSelecionada(String tabSelecionada) {
        this.tabSelecionada = tabSelecionada;
    }

    public EventHandler<KeyEvent> getEventHandlerPrincipal() {
        return eventHandlerPrincipal;
    }

    public void setEventHandlerPrincipal(EventHandler<KeyEvent> eventHandlerPrincipal) {
        this.eventHandlerPrincipal = eventHandlerPrincipal;
    }

    public Image getIcoJanelaAtivado() {
        return icoJanelaAtivado;
    }

    public void setIcoJanelaAtivado(Image icoJanelaAtivado) {
        this.icoJanelaAtivado = icoJanelaAtivado;
    }

    public Image getIcoJanelaDesativado() {
        return icoJanelaDesativado;
    }

    public void setIcoJanelaDesativado(Image icoJanelaDesativado) {
        this.icoJanelaDesativado = icoJanelaDesativado;
    }

    public ServiceStatusBar getServiceStatusBar() {
        return serviceStatusBar;
    }

    public void setServiceStatusBar(ServiceStatusBar serviceStatusBar) {
        this.serviceStatusBar = serviceStatusBar;
    }

    public static String getLastKey() {
        return lastKey;
    }

    public static void setLastKey(String lastKey) {
        ControllerPrincipal.lastKey = lastKey;
    }
}