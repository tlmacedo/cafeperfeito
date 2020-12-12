package br.com.tlmacedo.cafeperfeito.service.alert;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.SPLASH_IMAGENS;
import static br.com.tlmacedo.cafeperfeito.service.ServiceConfigSis.TCONFIG;

public class AlertMensagem implements Serializable {

    private final long serialVersionUID = 1L;

    private Thread thread;
    private Dialog dialog = new Dialog();
    private DialogPane dialogPane = dialog.getDialogPane();
    private Task<?> task;
    private String cabecalho = null, contentText = null, strIco = null;

    private boolean retornoValido = false;
    private boolean waitReturn = false;
    private HBox hBox = new HBox();
    private VBox vBox = new VBox();
    private ImageView imageView;
    private List<Button> btns = new ArrayList<>();
    private Button btnOk, btnCancel, btnYes, btnNo, btnApply, btnClose, btnFinish;
    private Label lblMsg = new Label(), lblContagem = new Label();
    private int timeOut = TCONFIG.getTimeOut().intValue();
    private Timeline timeline;
    private ProgressBar progressBar;
    private ProgressIndicator progressIndicator;

    /**
     * Begin Returns
     */

    public VBox contentProgress(boolean loading) {
        lblMsg.autosize();
        setProgressBar(new ProgressBar());
        getProgressBar().setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        getProgressBar().prefWidthProperty().bind(getDialogPane().widthProperty().subtract(20));

        getLblMsg().getStyleClass().add("dialog-update-msg");

        gethBox().setSpacing(7);
        gethBox().setAlignment(Pos.CENTER_LEFT);
        getvBox().setAlignment(Pos.CENTER);

        if (loading) {
            int random = (int) (Math.random() * SPLASH_IMAGENS.size());
            addImage(SPLASH_IMAGENS.get(random).toString());
            getImageView().setClip(new Circle(120, 120, 120));
            getvBox().getChildren().add(getImageView());
            getvBox().getChildren().add(new Label(""));
            getvBox().getChildren().add(new Label(""));
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(getLblMsg(), getLblContagem());
            getvBox().getChildren().add(hBox1);
        } else {
            setProgressIndicator(new ProgressIndicator());
            getProgressIndicator().setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
            getProgressIndicator().setPrefSize(25, 25);
            gethBox().getChildren().addAll(getProgressIndicator(), getLblMsg());
            getvBox().getChildren().add(gethBox());
        }

        if (!isRetornoValido()) {
            if (getTask().getTotalWork() > 1)
                getProgressBar().progressProperty().bind(getTask().progressProperty());
        }

        getvBox().getChildren().add(getProgressBar());
        return getvBox();
    }

    /**
     * END Returns
     */

    /**
     * Begin Voids
     */

    public void dialogClose() {
        getDialog().setResult(ButtonType.CANCEL);
        getDialog().close();
    }

    public void loadDialog() {
        getDialogPane().getStylesheets().add(getClass().getResource(TCONFIG.getPersonalizacao().getStyleSheets()).toString());
        getDialogPane().getButtonTypes().clear();
        getDialogPane().getStyleClass().add("alertMsg_return");
    }

    public void loadDialogPane() {
        getDialogPane().setHeaderText(getCabecalho());
        getDialogPane().setContentText(getContentText());
        if (getStrIco() != null)
            getDialog().setGraphic(new ImageView(AlertMensagem.class.getResource(getStrIco()).toString()));
    }

    public void addButton() {
        for (Button btn : getBtns()) {
            if (btn == getBtnOk()) {
                getDialogPane().getButtonTypes().add(ButtonType.OK);
                setBtnOk((Button) getDialogPane().lookupButton(ButtonType.OK));
                getBtnOk().setDisable(true);
                getBtnOk().setDefaultButton(true);
            } else if (btn == getBtnCancel()) {
                getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                setBtnCancel((Button) getDialogPane().lookupButton(ButtonType.CANCEL));
                getBtnCancel().setCancelButton(true);
            } else if (btn == getBtnYes()) {
                getDialogPane().getButtonTypes().add(ButtonType.YES);
                setBtnYes((Button) getDialogPane().lookupButton(ButtonType.YES));
                getBtnYes().setDefaultButton(true);
            } else if (btn == getBtnNo()) {
                getDialogPane().getButtonTypes().add(ButtonType.NO);
                setBtnNo((Button) getDialogPane().lookupButton(ButtonType.NO));
                if (!getBtns().contains(getBtnCancel()))
                    getBtnNo().setCancelButton(true);
            }
        }
    }

    public void addImage(String pathImg) {
        if (getImageView() == null)
            setImageView(new ImageView());
        getImageView().setImage(new Image(AlertMensagem.class.getResource(pathImg).toString()));
    }

    public void startContagemRegressiva() {
        getTimeline().setCycleCount(getTimeOut() * 10);
        getTimeline().play();
    }

    public Timeline newTimeLine(int tempo) {
        if (tempo > 0)
            setTimeOut(tempo);
        getLblMsg().textProperty().bind(getTask().messageProperty());
        final String[] pontos = {""}, contagem = {""};
        final int[] i = {0};

        return new Timeline(new KeyFrame(
                Duration.millis(100),
                ae -> {
                    if (i[0] % 10 == 1)
                        if (pontos[0].length() < 3)
                            pontos[0] += ".";
                        else
                            pontos[0] = "";
                    i[0]++;
                    contagem[0] = String.format("(%d) %s", (getTimeOut() - (i[0] / 10)), pontos[0]);
                    getLblContagem().setText(contagem[0]);
                }));
    }


    /**
     * END Voids
     */


    /**
     * Begin Getters and Setters
     */

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public DialogPane getDialogPane() {
        return dialogPane;
    }

    public void setDialogPane(DialogPane dialogPane) {
        this.dialogPane = dialogPane;
    }

    public Task<?> getTask() {
        return task;
    }

    public void setTask(Task<?> task) {
        this.task = task;
    }

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getStrIco() {
        return strIco;
    }

    public void setStrIco(String strIco) {
        this.strIco = strIco;
    }

//    public boolean isRetornoWait() {
//        return retornoWait;
//    }
//
//    public void setRetornoWait(boolean retornoWait) {
//        this.retornoWait = retornoWait;
//    }
//
//    public boolean isRetornoProgressBar() {
//        return retornoProgressBar;
//    }
//
//    public void setRetornoProgressBar(boolean retornoProgressBar) {
//        this.retornoProgressBar = retornoProgressBar;
//    }


    public boolean isRetornoValido() {
        return retornoValido;
    }

    public void setRetornoValido(boolean retornoValido) {
        this.retornoValido = retornoValido;
    }

    public boolean isWaitReturn() {
        return waitReturn;
    }

    public void setWaitReturn(boolean waitReturn) {
        this.waitReturn = waitReturn;
    }

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public List<Button> getBtns() {
        return btns;
    }

    public void setBtns(List<Button> btns) {
        this.btns = btns;
    }

    public Button getBtnOk() {
        return btnOk;
    }

    public void setBtnOk(Button btnOk) {
        this.btnOk = btnOk;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public Button getBtnYes() {
        return btnYes;
    }

    public void setBtnYes(Button btnYes) {
        this.btnYes = btnYes;
    }

    public Button getBtnNo() {
        return btnNo;
    }

    public void setBtnNo(Button btnNo) {
        this.btnNo = btnNo;
    }

    public Button getBtnApply() {
        return btnApply;
    }

    public void setBtnApply(Button btnApply) {
        this.btnApply = btnApply;
    }

    public Button getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    public Button getBtnFinish() {
        return btnFinish;
    }

    public void setBtnFinish(Button btnFinish) {
        this.btnFinish = btnFinish;
    }

    public Label getLblMsg() {
        return lblMsg;
    }

    public void setLblMsg(Label lblMsg) {
        this.lblMsg = lblMsg;
    }

    public Label getLblContagem() {
        return lblContagem;
    }

    public void setLblContagem(Label lblContagem) {
        this.lblContagem = lblContagem;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public void setProgressIndicator(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
    }

    /**
     * END Getters and Setters
     */

}
