package br.com.tlmacedo.cafeperfeito.controller;

import br.com.tlmacedo.cafeperfeito.interfaces.ModeloCafePerfeito;
import br.com.tlmacedo.cafeperfeito.model.dao.MenuPrincipalDAO;
import br.com.tlmacedo.cafeperfeito.model.vo.MenuPrincipal;
import br.com.tlmacedo.cafeperfeito.service.ServiceComandoTecladoMouse;
import br.com.tlmacedo.cafeperfeito.service.ServiceToolBar;
import br.com.tlmacedo.cafeperfeito.view.ViewPrincipal;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToolbar;
import com.jfoenix.controls.JFXTreeView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
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

    private Stage stagePrincipal = ViewPrincipal.getStage();
    private static ControllerPrincipal controllerPrincipal;
    private List<MenuPrincipal> menuPrincipalList = new ArrayList<>();
    private StringProperty tabSelecionada = new SimpleStringProperty();
    private EventHandler<KeyEvent> eventHandler_Principal;
    private Image icoJanela;
    private ServiceToolBar serviceToolBar;
    private static KeyCode lastKey;

    public JFXTreeView jfxMenuTreeViewPrincipal;
    public ImageView imgMenuPrincipalExpande;
    public ImageView imgMenuPrincipalRetrair;
    public Label lblImagePrincipal;
    public BorderPane panePrincipal;
    public JFXTabPane jfxTabPanePrincipal;
    public JFXToolbar jfxToolBarPrincipal;
    public Label stb_lblLeft;
    public Label stb_lblCenter;
    public Label stb_lblRight;
    public Label lblCopyRight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
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

        getStagePrincipal().close();

    }

    @Override
    public void criarObjetos() throws Exception {

        getLblImagePrincipal().setVisible(true);

    }

    @Override
    public void preencherObjetos() throws Exception {

        preencheListaDeMenus();
        setServiceToolBar(new ServiceToolBar(getJfxToolBarPrincipal(), getStb_lblLeft(), getStb_lblCenter(), getStb_lblRight()));

    }

    @Override
    public void fatorarObjetos() throws Exception {

    }

    @Override
    public void escutarTecla() throws Exception {

        getImgMenuPrincipalExpande().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> expandeMenus(true));
        getImgMenuPrincipalRetrair().addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> expandeMenus(false));
        setEventHandler_Principal(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (CODE_KEY_SHIFT_CTRL_POSITIVO.match(keyEvent) || CHAR_KEY_SHIFT_CTRL_POSITIVO.match(keyEvent))
                    getImgMenuPrincipalExpande().fireEvent(ServiceComandoTecladoMouse.clickMouse(1));
                if (CODE_KEY_SHIFT_CTRL_NEGATIVO.match(keyEvent) || CHAR_KEY_SHIFT_CTRL_NEGATIVO.match(keyEvent))
                    getImgMenuPrincipalRetrair().fireEvent(ServiceComandoTecladoMouse.clickMouse(1));
                if (keyEvent.getCode() == KeyCode.F12) {
                    if (getJfxTabPanePrincipal().getTabs().size() == 0)
                        fechar();
                }
                if (keyEvent.isShiftDown() && keyEvent.isControlDown()) {

                }
            }
        });
        getPanePrincipal().addEventHandler(KeyEvent.KEY_PRESSED, getEventHandler_Principal());

    }

    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

    private void expandeMenus(boolean isExpand) {

        for (int i = 0; i < getJfxMenuTreeViewPrincipal().getExpandedItemCount(); i++)
            getJfxMenuTreeViewPrincipal().getTreeItem(i).setExpanded(isExpand);

    }

    private void preencheListaDeMenus() {
        getLblCopyRight().setText(
                String.format("%s %d %s",
                        TCONFIG.getInfLoja().getCopyright(),
                        LocalDate.now().getYear(),
                        TCONFIG.getInfLoja().getTitulo()));

        setMenuPrincipalList(new MenuPrincipalDAO().getAll(MenuPrincipal.class, null, null));

        TreeItem[] treeItems = new TreeItem[getMenuPrincipalList().size() + 1];
        treeItems[0] = new TreeItem();
        for (MenuPrincipal menuPrincipal : getMenuPrincipalList()) {
            int id = (int) menuPrincipal.getId();
            treeItems[id] = new TreeItem(menuPrincipal);
            if (!menuPrincipal.getIcoMenu().equals(menuPrincipal)) {
                treeItems[id].setGraphic(
                        new ImageView(getClass().getResource(TCONFIG.getPaths().getPathIconeSistema()
                                + menuPrincipal.getIcoMenu()).toString()));
            }
            treeItems[id].setExpanded(true);
            treeItems[menuPrincipal.getMenuPai_id()].getChildren().add(treeItems[id]);
        }
        getJfxMenuTreeViewPrincipal().setRoot(treeItems[0]);
        getJfxMenuTreeViewPrincipal().setShowRoot(false);

    }

    private int tabJaAberta(MenuPrincipal menuPrincipal) {
        for (int tabId = 0; tabId < getJfxTabPanePrincipal().getTabs().size(); tabId++) {
            if (getJfxTabPanePrincipal().getTabs().get(tabId).getText().equals(menuPrincipal.getMenuLabel()))
                return tabId;
        }
        return -1;
    }

    private void addNewTab(MenuPrincipal menuPrincipal) {

        if (menuPrincipal == null) return;
        int tabId;
        if ((tabId = tabJaAberta(menuPrincipal)) < 0) {
            Tab tab = null;
            switch (menuPrincipal.get_menu()) {
                case "empresa" -> {
                    //tab = new ViewEmpre
                }
            }
            if (!tab.equals(null)) {
                tabId = getJfxTabPanePrincipal().getTabs().size();
                getJfxTabPanePrincipal().getTabs().add(tab);
            }
        }
        if (getJfxTabPanePrincipal().getTabs().size() > 0) {
            getJfxTabPanePrincipal().getSelectionModel().select(tabId);
            //getJfxTabPanePrincipal().getSelectionModel().getSelectedItem().setOnCloseRequest(event -> {});
        }
    }

    /**
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     */

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    public static ControllerPrincipal getControllerPrincipal() {
        return controllerPrincipal;
    }

    public static void setControllerPrincipal(ControllerPrincipal controllerPrincipal) {
        ControllerPrincipal.controllerPrincipal = controllerPrincipal;
    }

    public List<MenuPrincipal> getMenuPrincipalList() {
        return menuPrincipalList;
    }

    public void setMenuPrincipalList(List<MenuPrincipal> menuPrincipalList) {
        this.menuPrincipalList = menuPrincipalList;
    }

    public String getTabSelecionada() {
        return tabSelecionada.get();
    }

    public StringProperty tabSelecionadaProperty() {
        return tabSelecionada;
    }

    public void setTabSelecionada(String tabSelecionada) {
        this.tabSelecionada.set(tabSelecionada);
    }

    public EventHandler<KeyEvent> getEventHandler_Principal() {
        return eventHandler_Principal;
    }

    public void setEventHandler_Principal(EventHandler<KeyEvent> eventHandler_Principal) {
        this.eventHandler_Principal = eventHandler_Principal;
    }

    public Image getIcoJanela() {
        return icoJanela;
    }

    public void setIcoJanela(Image icoJanela) {
        this.icoJanela = icoJanela;
    }

    public ServiceToolBar getServiceToolBar() {
        return serviceToolBar;
    }

    public void setServiceToolBar(ServiceToolBar serviceToolBar) {
        this.serviceToolBar = serviceToolBar;
    }

    public static KeyCode getLastKey() {
        return lastKey;
    }

    public static void setLastKey(KeyCode lastKey) {
        ControllerPrincipal.lastKey = lastKey;
    }

    public JFXTreeView getJfxMenuTreeViewPrincipal() {
        return jfxMenuTreeViewPrincipal;
    }

    public void setJfxMenuTreeViewPrincipal(JFXTreeView jfxMenuTreeViewPrincipal) {
        this.jfxMenuTreeViewPrincipal = jfxMenuTreeViewPrincipal;
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

    public Label getLblImagePrincipal() {
        return lblImagePrincipal;
    }

    public void setLblImagePrincipal(Label lblImagePrincipal) {
        this.lblImagePrincipal = lblImagePrincipal;
    }

    public BorderPane getPanePrincipal() {
        return panePrincipal;
    }

    public void setPanePrincipal(BorderPane panePrincipal) {
        this.panePrincipal = panePrincipal;
    }

    public JFXTabPane getJfxTabPanePrincipal() {
        return jfxTabPanePrincipal;
    }

    public void setJfxTabPanePrincipal(JFXTabPane jfxTabPanePrincipal) {
        this.jfxTabPanePrincipal = jfxTabPanePrincipal;
    }

    public JFXToolbar getJfxToolBarPrincipal() {
        return jfxToolBarPrincipal;
    }

    public void setJfxToolBarPrincipal(JFXToolbar jfxToolBarPrincipal) {
        this.jfxToolBarPrincipal = jfxToolBarPrincipal;
    }

    public Label getStb_lblLeft() {
        return stb_lblLeft;
    }

    public void setStb_lblLeft(Label stb_lblLeft) {
        this.stb_lblLeft = stb_lblLeft;
    }

    public Label getStb_lblCenter() {
        return stb_lblCenter;
    }

    public void setStb_lblCenter(Label stb_lblCenter) {
        this.stb_lblCenter = stb_lblCenter;
    }

    public Label getStb_lblRight() {
        return stb_lblRight;
    }

    public void setStb_lblRight(Label stb_lblRight) {
        this.stb_lblRight = stb_lblRight;
    }

    public Label getLblCopyRight() {
        return lblCopyRight;
    }

    public void setLblCopyRight(Label lblCopyRight) {
        this.lblCopyRight = lblCopyRight;
    }
}