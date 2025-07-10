package com;

import com.guiutil.pk1.gui.util.MessageView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Zeitansage extends Application {

    @Override
    public void start(Stage primaryStage) {
        TimerListener listener = new TimerListener() {
            int count = 0;

            @Override
            public void signalPerformed() {
                System.out.println(count + " Sekunden seit Start");
                count++;
            }
        };

        Thread a = new Thread(new Timer(listener));
        a.setDaemon(true); // beendet sich automatisch mit dem JavaFX-Thread
        a.start();

        MessageView view = MessageView.create(primaryStage, "Message", "Zeitansage stoppen");
        view.showView();

        a.interrupt(); // ohne interrupt beendet sich das Programm nicht

        try {
            a.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
