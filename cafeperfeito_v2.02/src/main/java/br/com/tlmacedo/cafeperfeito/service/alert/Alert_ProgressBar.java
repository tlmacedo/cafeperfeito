package br.com.tlmacedo.cafeperfeito.service.alert;

import br.com.tlmacedo.nfe.service.ExceptionWsNFe;
import javafx.concurrent.Task;
import javafx.scene.control.Button;

import java.io.Serializable;


public class Alert_ProgressBar extends AlertMensagem implements Serializable {

    public static final long serialVersionUID = 1L;

    public Alert_ProgressBar(Task<?> task, String titulo, boolean isWait) {
        setCabecalho(titulo);
        setTask(task);
        setWaitReturn(isWait);

        setTimeline(newTimeLine(0));

        loadDialog();
        loadDialogPane();
        getDialogPane().getStyleClass().remove("alertMsg_return");
        getDialogPane().getStyleClass().add("alertMsg_progress");


        if (isWait) {
            setBtnOk(new Button());
            getBtns().add(getBtnOk());
        }
        setBtnCancel(new Button());
        getBtns().add(getBtnCancel());

        addButton();

        getDialogPane().setContent(contentProgress(!isWait));

        startContagemRegressiva();

        getBtnCancel().setOnAction(actionEvent -> getTask().cancel());

        getTimeline().setOnFinished(event -> {
            dialogClose();
        });

        getTask().setOnFailed(event -> {
            dialogClose();
        });

        getTask().setOnCancelled(event -> {
            dialogClose();
        });

        getTask().setOnSucceeded(event -> {
            setRetornoValido(true);
            getTimeline().stop();
            if (isWaitReturn()) {
                getProgressBar().setProgress(100);
                addImage("/image/sis_logo_240dp.png");
                getBtnOk().setDisable(false);
            } else {
                dialogClose();
            }
        });

        setThread(new Thread(getTask()));
        getThread().setDaemon(true);
        getThread().start();

        getDialog().showAndWait();

    }

    public boolean retorno() throws Exception {
        switch (getTask().getState()) {
            case SUCCEEDED -> {
                return true;
            }
            case FAILED -> {
                if (getTask().getException() instanceof ExceptionWsNFe)
                    throw new ExceptionWsNFe(getTask().getException());
                else
                    throw new Exception(getTask().getException());
            }
            default -> {
                return false;
            }
        }

    }

}
