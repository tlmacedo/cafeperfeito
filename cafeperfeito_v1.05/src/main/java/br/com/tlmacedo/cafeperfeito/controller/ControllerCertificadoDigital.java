package br.com.tlmacedo.cafeperfeito.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Created by IntelliJ IDEA.
 * User: thiagomacedo
 * Date: 2019-06-06
 * Time: 11:33
 */

public class ControllerCertificadoDigital {
    public AnchorPane pnlCertificadosDigitais;
    public JFXComboBox cboCertificadosDigitais;
    public Label lblTitularNomeComum_CN;
    public Label lblTitularEmpresa_O;
    public Label lblTitularEmpresa_OU;
    public Label lblTitularLocalidade_L;
    public Label lblTitularEstado_ST;
    public Label lblTitularPais_C;
    public Label lblCertificadoraNomeComum_CN;
    public Label lblCertificadoraEmpresa_O;
    public Label lblCertificadoraOrganizacional_OU;
    public Label lblCertificadoraPais_C;
    public Label lblCertificadoDispositivoSeguranca;
    public Label lblCertificadoValidadeInicio;
    public Label lblCertificadoValidadeFim;
    public Label lblResponsavelNome;
    public Label lblResponsavelRG;
    public Label lblResponsavelCPF;
    public Label lblResponsavelDtNascimento;
    public Label lblResponsavelEmail;
    public JFXButton btnCancela;
    public JFXButton btnOK;

    public ControllerCertificadoDigital() {
    }
}
