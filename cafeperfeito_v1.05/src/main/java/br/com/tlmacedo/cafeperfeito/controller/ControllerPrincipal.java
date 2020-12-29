package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.nfe.NFeStatusServicoFactoryDinamicoA3;
import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.MenuPrincipalDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.LogadoInf;
import br.com.tlmacedo.cafeperfeito.model.vo.MenuPrincipal;
import br.com.tlmacedo.cafeperfeito.service.ServiceAlertMensagem;
import br.com.tlmacedo.cafeperfeito.service.ServiceComandoTecladoMouse;
import br.com.tlmacedo.cafeperfeito.service.ServiceStatusBar;
import br.com.tlmacedo.cafeperfeito.service.ServiceVariaveisSistema;
import br.com.tlmacedo.cafeperfeito.view.*;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.controls.JFXTreeView;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static br.com.tlmacedo.cafeperfeito.interfaces.Convert_Date_Key.*;

public class ControllerPrincipal implements Initializable, ModeloCafePerfeito {

    public static String lastKey;

    public BorderPane painelViewPrincipal;
    public TabPane tabPaneViewPrincipal;
    public Label lblImageLogoViewPrincipal;
    public JFXTreeView<MenuPrincipal> treeMenuViewPrincipal;
    public Label lblMenuPrincipalRetrair;
    public Label lblMenuPrincipalExpande;
    public Label lblCopyRight;
    public JFXToolbar statusBar_ViewPrincipal;
    public Label stbLogadoInf;
    public Label stbTeclas;
    public Label stbRelogio;
    private ServiceStatusBar serviceStatusBar;

    private MenuPrincipalDAO menuPrincipalDAO = new MenuPrincipalDAO();
    private List<MenuPrincipal> menuPrincipalList = new ArrayList<>();

    public static ControllerPrincipal ctrlPrincipal;
    public ServiceAlertMensagem alertMensagem;
    EventHandler<KeyEvent> eventHandlerPrincial;
    private String tabSelecionada = "";

    @Override
    public void fechar() {
        ViewPrincipal.getStage().close();
    }

    @Override
    public void criarObjetos() {

    }

    @Override
    public void preencherObjetos() {
        preencheMenuPrincipal();
        serviceStatusBar = new ServiceStatusBar();
        serviceStatusBar.setLblUsuario(stbLogadoInf);
        serviceStatusBar.setLblHorario(stbRelogio);
        serviceStatusBar.setLblTeclas(stbTeclas);
        serviceStatusBar.setStatusBar(statusBar_ViewPrincipal);
        serviceStatusBar.atualizaStatusBar();
//        serviceStatusBar = new ServiceStatusBar(statusBar_ViewPrincipal);
//        serviceStatusBar.atualizaStatusBar();
    }

    @Override
    public void fatorarObjetos() {

    }

    @Override
    public void escutarTecla() {
        lblMenuPrincipalExpande.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> menuPrincipalExpandeAll(true));
        lblMenuPrincipalRetrair.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> menuPrincipalExpandeAll(false));

        eventHandlerPrincial = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (CODE_KEY_SHIFT_CTRL_POSITIVO.match(event) || CHAR_KEY_SHIFT_CTRL_POSITIVO.match(event))
                    lblMenuPrincipalExpande.fireEvent(ServiceComandoTecladoMouse.clickMouse(1));
                if (CODE_KEY_SHIFT_CTRL_NEGATIVO.match(event) || CHAR_KEY_SHIFT_CTRL_NEGATIVO.match(event))
                    lblMenuPrincipalRetrair.fireEvent(ServiceComandoTecladoMouse.clickMouse(1));
                if (event.getCode() == KeyCode.F12)
                    if (tabPaneViewPrincipal.getTabs().size() == 0)
                        fechar();
                    else
                        ;
                if (CODE_KEY_SHIFT_CTRL_S.match(event) || CHAR_KEY_SHIFT_CTRL_S.match(event)) {
                    System.out.printf("Tentando statusNfe!!!");
                    new NFeStatusServicoFactoryDinamicoA3();
                }
                if (event.isShiftDown() && event.isControlDown()) {
                    MenuPrincipal menu;
                    if ((menu = menuPrincipalList.stream().filter(mn -> mn.getTeclaAtalho().equals("ctrl+shift+" + event.getCode().toString().toLowerCase()))
                            .findFirst().orElse(null)) != null)
                        addTab(menu);
                }
            }
        };
        painelViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerPrincial);

        if (tabPaneViewPrincipal.getSelectionModel().getSelectedIndex() >= 0)
            tabPaneViewPrincipal.getSelectionModel().getSelectedItem().setOnCloseRequest(event -> {
                if (tabPaneViewPrincipal.getTabs().size() > 0 && !statusBar_ViewPrincipal.getCenter().toString().toLowerCase().contains("sair"))
                    event.consume();
            });

        tabPaneViewPrincipal.getTabs().addListener(new ListChangeListener<Tab>() {
            @Override
            public void onChanged(Change<? extends Tab> c) {
                String icon = ServiceVariaveisSistema.TCONFIG.getFxml().getPrincipal().getIconeDesativo();
                if (tabPaneViewPrincipal.getTabs().size() > 0)
                    icon = ServiceVariaveisSistema.TCONFIG.getFxml().getPrincipal().getIconeAtivo();
                lblImageLogoViewPrincipal.setVisible(tabPaneViewPrincipal.getTabs().size() == 0);

                ViewPrincipal.getStage().getIcons().setAll(new Image(getClass().getResource(icon).toString()));
            }
        });

        treeMenuViewPrincipal.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (treeMenuViewPrincipal.getSelectionModel().getSelectedIndex() < 0) return;
            MenuPrincipal menu;
            if ((menu = treeMenuViewPrincipal.getSelectionModel().getSelectedItem().getValue()) == null) return;
            if (!menu.getMenuLabel().toLowerCase().equals("sair") && !(event.getClickCount() == 2)) return;
            addTab(menu);
        });

        treeMenuViewPrincipal.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER)
                treeMenuViewPrincipal.fireEvent(ServiceComandoTecladoMouse.clickMouse(2));
        });

//        tabPaneViewPrincipal.getTabs().addListener((ListChangeListener<? super Tab>) c -> {
//            System.out.printf("getTabs: [%s]\n", c.getList());
//            if (tabPaneViewPrincipal.getTabs().size() <= 0) {
//                setTabSelecionada("");
//                serviceStatusBar.atualizaStatusBar("");
//            }
////            else {
////                setTabSelecionada(tabPaneViewPrincipal.getSelectionModel().getSelectedItem().getText());
////            }
//        });
        tabPaneViewPrincipal.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (tabPaneViewPrincipal.getTabs().size() <= 0) {
                setTabSelecionada("");
                serviceStatusBar.atualizaStatusBar("");
            } else {
                setTabSelecionada(newValue.getText());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrlPrincipal = this;
        lblImageLogoViewPrincipal.setVisible(true);
        criarObjetos();
        preencherObjetos();
        fatorarObjetos();
        escutarTecla();
    }

    public static String getLastKey() {
        return lastKey;
    }

    public static void setLastKey(String lastKey) {
        ControllerPrincipal.lastKey = lastKey;
    }

    public String getTabSelecionada() {
        return tabSelecionada;
    }

    public void setTabSelecionada(String tabSelecionada) {
        this.tabSelecionada = tabSelecionada;
    }

    public ServiceStatusBar getServiceStatusBar() {
        return serviceStatusBar;
    }

    public void setServiceStatusBar(ServiceStatusBar serviceStatusBar) {
        this.serviceStatusBar = serviceStatusBar;
    }

    private void menuPrincipalExpandeAll(boolean expand) {
        for (int i = 0; i < treeMenuViewPrincipal.getExpandedItemCount(); i++)
            treeMenuViewPrincipal.getTreeItem(i).setExpanded(expand);
    }

    private void preencheMenuPrincipal() {
        lblCopyRight.setText(String.format("%s %d",
                ServiceVariaveisSistema.TCONFIG.getInfLoja().getCopyright(),
                LocalDate.now().getYear()));
        String path = ServiceVariaveisSistema.TCONFIG.getPaths().getPathIconeSistema();
        menuPrincipalList = menuPrincipalDAO.getAll(MenuPrincipal.class, null, null, null, null);
        TreeItem[] treeItems = new TreeItem[menuPrincipalList.size() + 1];
        treeItems[0] = new TreeItem();
        try {
            for (MenuPrincipal menu : menuPrincipalList) {
                int i = menu.idProperty().intValue();
                treeItems[i] = new TreeItem(menu);
                if (!menu.getIcoMenu().equals(""))
                    treeItems[i].setGraphic(new ImageView(getClass().getResource(path + menu.getIcoMenu()).toString()));
                treeItems[i].setExpanded(true);
                treeItems[menu.getMenuPai_id()].getChildren().add(treeItems[i]);
            }
            treeMenuViewPrincipal.setRoot(treeItems[0]);
            treeMenuViewPrincipal.setShowRoot(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int tabAberta(String menuLabel) {
        for (int i = 0; i < tabPaneViewPrincipal.getTabs().size(); i++)
            if (tabPaneViewPrincipal.getTabs().get(i).getText().equals(menuLabel))
                return i;
        return -1;
    }

    private void addTab(MenuPrincipal menu) {
        int tabId;
        if ((tabId = tabAberta(menu.getMenuLabel())) < 0) {
            Tab tab = null;
            switch (menu.getMenu().toLowerCase()) {
                case "sair":
                    fechar();
                    break;
                case "empresa":
                    tab = new ViewCadastroEmpresa().openTabCadastroEmpresa(menu.getMenuLabel());
                    break;
                case "produto":
                    tab = new ViewCadastroProduto().openTabCadastroProduto(menu.getMenuLabel());
                    break;
                case "entradaproduto":
                    tab = new ViewEntradaProduto().openTabEntradaProduto(menu.getMenuLabel());
                    break;
                case "saidaproduto":
                    tab = new ViewSaidaProduto().openTabSaidaProduto(menu.getMenuLabel());
                    break;
                case "contasareceber":
                    tab = new ViewContasAReceber().openTabContasAReceber(menu.getMenuLabel());
                    break;
            }
            if (tab != null) {
                tabPaneViewPrincipal.getTabs().add(tab);
                tabId = (tabPaneViewPrincipal.getTabs().size() - 1);
            }
        }
        if (tabPaneViewPrincipal.getTabs().size() > 0) {
            tabPaneViewPrincipal.getSelectionModel().getSelectedItem().setOnCloseRequest(event -> {
                if (!serviceStatusBar.getLblTeclas().getText().toLowerCase().contains("sair")) {
                    alertMensagem = new ServiceAlertMensagem();
                    alertMensagem.setCabecalho("Opção não permitida!");
                    alertMensagem.setPromptText(String.format("%s, para sair... Cancele a inclusão ou edição de dados",
                            LogadoInf.getUserLog().getApelido()));
                    alertMensagem.setStrIco("ic_atencao_triangulo");
                    //**alertMensagem.getRetornoAlert_OK();
                    event.consume();
                }
            });
            tabPaneViewPrincipal.getSelectionModel().select(tabId);
        }
    }
}
