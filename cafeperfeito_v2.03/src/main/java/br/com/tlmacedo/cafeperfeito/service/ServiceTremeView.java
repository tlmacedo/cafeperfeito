package br.com.tlmacedo.cafeperfeito.service;

import javafx.stage.Stage;

public class ServiceTremeView {

    Stage stage;
    int originalX = 0;

    public ServiceTremeView(Stage stage) {
        setStage(stage);
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i <= 8; i++) {
                    if (i % 2 == 0)
                        getStage().setX(getOriginalX() + 5);
                    else
                        getStage().setX(getOriginalX() - 5);
                    Thread.sleep(40);
                }
                getStage().setX(getOriginalX());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        thread.start();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.originalX = (int) stage.getX();
    }

    public int getOriginalX() {
        return originalX;
    }

    public void setOriginalX(int originalX) {
        this.originalX = originalX;
    }

    //    Stage stage;
//
//    public static void TremeView(Stage stage) {
//        try {
//            int originalX = (int) stage.getX();
//            long sleepTime = 400;
//
//            for (int i = 0; i <= 8; i++) {
//                Thread.sleep(sleepTime);
//                if (i % 2 == 0)
//                    stage.setX(originalX + 5);
//                else
//                    stage.setX(originalX - 5);
//            }
//            Thread.sleep(sleepTime);
//            stage.setX(originalX);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void setStage(Stage stage) {
//        this.stage = stage;
//        run();
//    }
//
//    @Override
//    public void run() {
//        try {
//            int originalX = (int) stage.getX();
//            long sleepTime = 400;
//
//            for (int i = 0; i <= 8; i++) {
//                Thread.sleep(sleepTime);
//                if (i % 2 == 0)
//                stage.setX(originalX + 5);
//                else
//                    stage.setX(originalX - 5);
//            }
//            Thread.sleep(sleepTime);
//            stage.setX(originalX);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//    }
}
