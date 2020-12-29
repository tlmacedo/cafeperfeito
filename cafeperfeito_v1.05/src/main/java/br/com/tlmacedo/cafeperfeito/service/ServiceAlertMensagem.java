package br.com.tlmacedo.cafeperfeito.service;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.Pair;

import javax.swing.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;


public class ServiceAlertMensagem extends JFrame {

    private Thread thread;
    private Dialog dialog = new Dialog();
    private DialogPane dialogPane = dialog.getDialogPane();
    private Task<?> dialogTask;
    private Boolean dialogGeraRetorno = false;
    private Boolean dialogResultProgressBar = false;
    private Color dialogBackground = Color.TRANSPARENT;
    private Image dialogImagem;
    private ImageView dialogImageView;
    private HBox hBox = new HBox();
    private VBox vBox = new VBox();
    private int progressQtdTarefa = 2;
    private ProgressBar progressBar;
    private ProgressIndicator progressIndicator;
    private Button btnOk, btnYes, btnApply, btnNo, btnCancel, btnClose, btnFinish;
    private Label lblMensagem = new Label(), lblTextoMsg = new Label();
    private JFXComboBox<?> comboBox = new JFXComboBox<>();
    private JFXTextField textField = new JFXTextField();
    private JFXPasswordField passwordField = new JFXPasswordField();
    private ServiceMascara formatTextField = new ServiceMascara();
    //    private List<?> list = new ArrayList<>();
    private Timeline timelineContagemRegressiva;

    private String cabecalho = null, promptText = null, strIco = null;

    public ServiceAlertMensagem() {
    }

    public ServiceAlertMensagem(String cabecalho, String promptText, String strIco) {
        this.cabecalho = cabecalho;
        this.promptText = promptText;
        this.strIco = strIco;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public String getStrIco() {
        return strIco;
    }

    public void setStrIco(String strIco) {
        this.strIco = strIco;
    }

    public Label getLblMensagem() {
        return lblMensagem;
    }

    public void setLblMensagem(Label lblMensagem) {
        this.lblMensagem = lblMensagem;
    }

    public Label getLblTextoMsg() {
        return lblTextoMsg;
    }

    public void setLblTextoMsg(Label lblTextoMsg) {
        this.lblTextoMsg = lblTextoMsg;
    }

    private void closeDialog() {
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }

    private void addButton(String button) {
        button = button.toLowerCase();
        if (button.contains("ok")) {
            btnOk = new Button();
            if (!button.contains("+"))
                btnOk.setOnAction(event -> closeDialog());
            dialogPane.getButtonTypes().add(ButtonType.OK);
            btnOk = (Button) dialogPane.lookupButton(ButtonType.OK);
            btnOk.setDefaultButton(true);
        }
        if (button.contains("cancel")) {
            btnCancel = new Button();
            if (!button.contains("+"))
                btnCancel.setOnAction(event -> closeDialog());
            dialogPane.getButtonTypes().add(ButtonType.CANCEL);
            btnCancel = (Button) dialogPane.lookupButton(ButtonType.CANCEL);
            btnCancel.setCancelButton(true);
        }
        if (button.contains("yes")) {
            btnYes = new Button();
            if (!button.contains("+"))
                btnYes.setOnAction(event -> closeDialog());
            dialogPane.getButtonTypes().add(ButtonType.YES);
            btnYes = (Button) dialogPane.lookupButton(ButtonType.YES);
            btnYes.setDefaultButton(true);
        }
        if (button.contains("no")) {
            btnNo = new Button();
            if (!button.contains("+"))
                btnNo.setOnAction(event -> closeDialog());
            dialogPane.getButtonTypes().add(ButtonType.NO);
            btnNo = (Button) dialogPane.lookupButton(ButtonType.NO);
            btnNo.setCancelButton(true);
        }
    }

    private void addImagem(String strImage) {
        dialogImageView = new ImageView();
        dialogImageView.setImage(new Image(getClass().getResource(strImage).toString()));
    }

    private void addPasswordField() {
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().add(passwordField);

        dialogPane.setContent(vBox);
    }

    private void addTextField(String mascara, String textPreLoader, String textFieldPrompt) {
        //textField = new JFXTextField();
        textField.setPromptText(textFieldPrompt);

        formatTextField.fieldMask(textField, mascara);

        textField.setText(textPreLoader);
        //vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().add(textField);

        dialogPane.setContent(vBox);

//        textField.textProperty().addListener((ov, o, n) -> {
//            if (textFieldPrompt.toLowerCase().contains("telefone")) {
//                if (n.length() > 0) {
//                    if (Integer.valueOf(n.substring(0, 1)) > 6)
//                        formatTextField.setMascara(MASK_CELULAR);
//                    else
//                        formatTextField.setMascara(MASK_TELEFONE);
//                }
//            }
//        });
//
//        if (textFieldPrompt.toLowerCase().contains("telefone"))
//            textField.textProperty().addListener((ov, o, n) -> {
//            });


    }

    private void addComboBox(List listCombo, Object cboPreLoader) {
        comboBox.getItems().setAll(listCombo);
        int index = 0;
        if (cboPreLoader != null)
            for (int i = 0; i < listCombo.size(); i++)
                if (cboPreLoader.toString().equals(listCombo.get(i).toString())) {
                    index = i;
                    break;
                }
        comboBox.getSelectionModel().select(index);
        comboBox.setPromptText(getPromptText());

        //vBox = new VBox();
        vBox.setSpacing(9);
        vBox.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().add(comboBox);

        dialogPane.setContent(vBox);
    }

//    @SuppressWarnings("Duplicates")
//    private void addComboBox_textBox(List listCombo, String mascara, String textPreLoader) {
//        comboBox = new JFXComboBox<>();
//        comboBox.getItems().setAll(listCombo);
//        comboBox.getSelectionModel().select(0);
//        comboBox.setPromptText(getPromptText());
//
//        textField = new JFXTextField();
//        textField.setPromptText(getPromptText());
//        new ServiceMascara().fieldMask((JFXTextField) textField, mascara);
//        textField.setText(textPreLoader);
//
//        vBox = new VBox();
//        vBox.setSpacing(9);
//        vBox.setAlignment(Pos.CENTER_LEFT);
//
//        vBox.getChildren().addAll(comboBox, textField);
//
//        dialogPane.setContent(vBox);
//    }

    private void loadDialog() {
        dialog.initStyle(StageStyle.TRANSPARENT);

        dialogPane.getScene().setFill(Color.TRANSPARENT);
        dialogPane.getStylesheets().add(getClass().getResource(ServiceVariaveisSistema.TCONFIG.getPersonalizacao().getStyleSheetsMin()).toString());

        dialogPane.getButtonTypes().clear();
        dialogPane.getStyleClass().add("alertMsg_return");
    }

    private void loadDialogPane() {
        dialog.setHeaderText(getCabecalho());
        dialog.setContentText(getPromptText());
        if (getStrIco() != null)
            try {
                dialog.setGraphic(new ImageView(getClass().getResource(getStrIco()).toString()));
            } catch (Exception ex) {

            }
    }

    private VBox loadDialogContent_Progress() {
        progressBar = new ProgressBar();
        progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        progressBar.prefWidthProperty().bind(dialogPane.widthProperty().subtract(20));
        lblMensagem.prefWidthProperty().bind(dialogPane.widthProperty().subtract(15));
        lblMensagem.getStyleClass().add("dialog-update-msg");
        hBox.setSpacing(7);
        hBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setAlignment(Pos.CENTER);
        if (dialogBackground == Color.TRANSPARENT) {
            int random = (int) (Math.random() * ServiceVariaveisSistema.SPLASH_IMAGENS.size());
            addImagem(ServiceVariaveisSistema.SPLASH_IMAGENS.get(random).toString());
            dialogImageView.setClip(new Circle(120, 120, 120));
            vBox.getChildren().add(dialogImageView);
            vBox.getChildren().add(lblMensagem);
        } else {
            progressIndicator = new ProgressIndicator();
            progressIndicator.progressProperty().bind(dialogTask.progressProperty());
            progressIndicator.setPrefSize(25, 25);
            hBox.getChildren().addAll(progressIndicator, lblMensagem);
            vBox.getChildren().add(hBox);
        }

        if (dialogGeraRetorno) {

        } else {
            if (progressQtdTarefa > 1)
                progressBar.progressProperty().bind(dialogTask.progressProperty());
        }

        vBox.getChildren().add(progressBar);
        return vBox;
    }

    public boolean getProgressBar(Task<?> task, Color background, boolean showAndWait, int qtdTarefas) {
        dialogGeraRetorno = showAndWait;
        dialogBackground = background;
        dialogTask = task;

        loadDialog();
        loadDialogPane();
        dialogPane.getStyleClass().remove("alertMsg_return");
        dialogPane.getStyleClass().add("alertMsg_progress");

        if (showAndWait) {
            addButton("ok");
            btnOk.setDisable(true);
        }

        dialogPane.setContent(loadDialogContent_Progress());

        contagemRegressiva(0);

        dialogTask.setOnFailed(event -> {
            System.out.printf("dialogTask.setOnFailed==>> [%s]\n", false);
            dialogResultProgressBar = false;
            closeDialog();
        });

        dialogTask.setOnCancelled(event -> {
            System.out.printf("dialogTask.setOnCancelled==>> [%s]\n", false);
            dialogResultProgressBar = false;
            closeDialog();
        });

        dialogTask.setOnSucceeded(event -> {
            dialogResultProgressBar = true;
            timelineContagemRegressiva.stop();
            if (showAndWait) {
                addImagem("image/sis_logo_240dp.png");
                btnOk.setDisable(false);
            } else {
                closeDialog();
            }
        });


        timelineContagemRegressiva.setOnFinished(event -> {
            dialogResultProgressBar = false;
            closeDialog();
            return;
        });

        thread = new Thread(dialogTask);
        thread.setDaemon(true);
        thread.start();

        dialog.showAndWait();

        return dialogResultProgressBar;
    }

    public void getRetornoAlert_OK() {
        loadDialog();
        loadDialogPane();

        addButton("ok");

        dialog.showAndWait();
    }

    private void contagemRegressiva(Integer tempo) {
        if (tempo == 0)
            tempo = ServiceVariaveisSistema.TCONFIG.getTimeOut();
        final Integer timeOut = tempo;
        lblTextoMsg.textProperty().bind(dialogTask.messageProperty());
        final String[] pontos = {""}, contagem = {""};
        final int[] i = {0};
        timelineContagemRegressiva = new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> {
                    if (i[0] % 10 == 1)
                        if (pontos[0].length() < 3)
                            pontos[0] += ".";
                        else
                            pontos[0] = "";
                    i[0]++;
                    contagem[0] = String.format("(%d) %s", (timeOut - (i[0] / 10)), pontos[0]);
                    lblMensagem.setText(String.format("%s %s", lblTextoMsg.getText(), contagem[0]));
                }));
        timelineContagemRegressiva.setCycleCount(timeOut * 10);
        timelineContagemRegressiva.play();
    }

    public Optional<ButtonType> getRetornoAlert_Yes_No() {
        loadDialog();
        loadDialogPane();

        addButton("no");
        addButton("yes");


        dialog.setResultConverter(new Callback() {
            @Override
            public Object call(Object param) {
                if (param == ButtonType.YES)
                    return ButtonType.YES;
                return ButtonType.NO;
            }
        });

        Optional<ButtonType> result = dialog.showAndWait();
        return result;
    }


    public Optional<String> getRetornoAlert_TextField(String mascara, String textPreLoader, String textFieldPrompt) {
        loadDialog();
        loadDialogPane();
        dialogPane.getStyleClass().remove("alertMsg_return");
        dialogPane.getStyleClass().add("alertMsg_with_text_field");

        addButton("ok");
        addButton("cancel");

        addTextField(mascara, textPreLoader, textFieldPrompt);

        textField.textProperty().addListener((ov, o, n) -> {

        });

        Platform.runLater(() -> textField.requestFocus());

        dialog.setResultConverter(new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType param) {
                if (param.getButtonData().isDefaultButton())
                    if (!textField.getText().equals(textPreLoader))
                        return textField.getText();
                return null;
            }
        });

        Optional<String> result = dialog.showAndWait();
        return result;
    }

    public Optional<String> getRetornoAlert_PasswordField() {
        loadDialog();
        loadDialogPane();

        addButton("ok");
        addButton("cancel");

        addPasswordField();

        btnOk.disableProperty().bind(passwordField.textProperty().isEmpty());

        Platform.runLater(() -> passwordField.requestFocus());

        dialog.setResultConverter(new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType param) {
                if (param.getButtonData().isDefaultButton())
                    return passwordField.getText();
                return null;
            }
        });

        Optional<String> result = dialog.showAndWait();
        return result;
    }

    @SuppressWarnings("Duplicates")
    public Optional<?> getRetornoAlert_comboBox(List<?> listCombo) {
        loadDialog();
        loadDialogPane();
        dialogPane.getStyleClass().remove("alertMsg_return");
        dialogPane.getStyleClass().add("alertMsg_with_text_field");

        addButton("ok");
        addButton("cancel");

        addComboBox(listCombo, null);

        Platform.runLater(() -> comboBox.requestFocus());

        btnOk.disableProperty().bind(comboBox.getSelectionModel().selectedItemProperty().isNull());

        dialog.setResultConverter(new Callback<ButtonType, Object>() {
            @Override
            public Object call(ButtonType param) {
                if (param.getButtonData().isDefaultButton())
                    return comboBox.getSelectionModel().getSelectedItem();
                return null;
            }
        });

        Optional<?> result = dialog.showAndWait();
        return result;
    }

    public Optional<Pair<String, ?>> getRetornoAlert_textField_comboBox(String mascara, String textPreLoader, Object cboPreLoader, String textFieldPrompt, List<?> listCombo) {
        loadDialog();
        loadDialogPane();
        dialogPane.getStyleClass().remove("alertMsg_return");
        dialogPane.getStyleClass().add("alertMsg_with_text_field");

        addButton("ok");
        addButton("cancel");

        btnOk.disableProperty().bind(
                Bindings.createBooleanBinding(
                        () -> textField.getText().isEmpty()
                                || comboBox.getSelectionModel().isEmpty(),
                        textField.textProperty(), comboBox.getSelectionModel().selectedItemProperty()
                )
        );

        //btnOk.setDisable(true);

        addComboBox(listCombo, cboPreLoader);
        addTextField(mascara, textPreLoader, textFieldPrompt);

        dialogPane.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER && btnOk.isDisable())
                if (comboBox.isFocused())
                    textField.requestFocus();
                else
                    comboBox.requestFocus();
            if (event.getCode() == KeyCode.F12 || event.getCode() == KeyCode.ESCAPE)
                btnCancel.fire();
        });

        Platform.runLater(() -> comboBox.requestFocus());

        dialog.setResultConverter(new Callback<ButtonType, Object>() {
            @Override
            public Object call(ButtonType param) {
                if (param.getButtonData().isDefaultButton())
                    return new Pair<>(textField.getText(), comboBox.getSelectionModel().getSelectedItem());
                return null;
            }
        });

        Optional<Pair<String, ?>> result = dialog.showAndWait();
        return result;
    }

    public void errorException(Exception exceptionErr) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(getCabecalho());
            alert.setContentText("Erro ocorrido em: " + promptText);

            Exception ex = exceptionErr;

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        } catch (Exception ex) {
            if (ex instanceof IllegalStateException) {
                Platform.runLater(() -> {
                    errorException(exceptionErr);
                });
            } else {
                //ex.printStackTrace();
            }
        }
    }


}
