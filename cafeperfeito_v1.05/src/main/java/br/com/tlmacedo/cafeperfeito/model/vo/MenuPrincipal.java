package br.com.tlmacedo.cafeperfeito.model.vo;


import javafx.beans.property.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "MenuPrincipal")
@Table(name = "menu_principal")
public class MenuPrincipal implements Serializable {
    private static final long serialVersionUID = 1L;

    private LongProperty id = new SimpleLongProperty();
    private StringProperty menu = new SimpleStringProperty();
    private StringProperty menuLabel = new SimpleStringProperty();
    private IntegerProperty menuPai_id = new SimpleIntegerProperty();
    private StringProperty icoMenu = new SimpleStringProperty();
    private BooleanProperty tabPane = new SimpleBooleanProperty();
    private StringProperty teclaAtalho = new SimpleStringProperty();
    private IntegerProperty dropdown = new SimpleIntegerProperty();

    public MenuPrincipal() {
    }

    public MenuPrincipal(String menu, String menuLabel, Integer menuPai_id, String icoMenu, Boolean tabPane, String teclaAtalho, Integer dropdown) {
        this.menu = new SimpleStringProperty(menu);
        this.menuLabel = new SimpleStringProperty(menuLabel);
        this.menuPai_id = new SimpleIntegerProperty(menuPai_id);
        this.icoMenu = new SimpleStringProperty(icoMenu);
        this.tabPane = new SimpleBooleanProperty(tabPane);
        this.teclaAtalho = new SimpleStringProperty(teclaAtalho);
        this.dropdown = new SimpleIntegerProperty(dropdown);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Column(length = 45, nullable = false)
    public String getMenu() {
        return menu.get();
    }

    public StringProperty menuProperty() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu.set(menu);
    }

    @Column(length = 45, nullable = false)
    public String getMenuLabel() {
        return menuLabel.get();
    }

    public StringProperty menuLabelProperty() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel.set(menuLabel);
    }

    @Column(length = 2, nullable = false)
    public int getMenuPai_id() {
        return menuPai_id.get();
    }

    public IntegerProperty menuPai_idProperty() {
        return menuPai_id;
    }

    public void setMenuPai_id(int menuPai_id) {
        this.menuPai_id.set(menuPai_id);
    }

    @Column(length = 80)
    public String getIcoMenu() {
        return icoMenu.get();
    }

    public StringProperty icoMenuProperty() {
        return icoMenu;
    }

    public void setIcoMenu(String icoMenu) {
        this.icoMenu.set(icoMenu);
    }

    @Column(length = 1, nullable = false)
    public boolean isTabPane() {
        return tabPane.get();
    }

    public BooleanProperty tabPaneProperty() {
        if (tabPane == null) tabPane = new SimpleBooleanProperty(false);
        return tabPane;
    }

    public void setTabPane(boolean tabPane) {
        this.tabPane.set(tabPane);
    }

    @Column(length = 45)
    public String getTeclaAtalho() {
        return teclaAtalho.get();
    }

    public StringProperty teclaAtalhoProperty() {
        return teclaAtalho;
    }

    public void setTeclaAtalho(String teclaAtalho) {
        this.teclaAtalho.set(teclaAtalho);
    }

    @Column(length = 2, nullable = false)
    public int getDropdown() {
        return dropdown.get();
    }

    public void setDropdown(int dropdown) {
        this.dropdown.set(dropdown);
    }

    public IntegerProperty dropdownProperty() {
        return dropdown;
    }

    @Override
    public String toString() {
        return menuLabelProperty().get();
    }
}
