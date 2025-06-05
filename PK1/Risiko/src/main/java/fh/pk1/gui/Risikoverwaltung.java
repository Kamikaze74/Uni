package fh.pk1.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Risikoverwaltung extends RisikoErfassungView {

    public Risikoverwaltung(Risiko risiko, Stage primaryStage) {
        super(risiko, primaryStage);
        this.initOwner(primaryStage);

        VBox layout = new VBox(15);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        Label beschriftung = new Label("Risikoverwaltung");
        beschriftung.setFont(Font.font(16));

        Button erfassungOeffnenButton = new Button("Risiko erfassen");
        
        // Beim Klick auf den Button wird ein neues Fenster geöffnet
            erfassungOeffnenButton.setOnAction(e -> {
            Risikoerfassung erfassung = new Risikoerfassung(risiko, primaryStage);
            erfassung.initModality(Modality.WINDOW_MODAL); // blockiert Hauptfenster
            erfassung.initOwner(this); // Risikoverwaltung ist Besitzer
            erfassung.showAndWait(); // blockiert bis Fenster geschlossen wird
        });


        layout.getChildren().addAll(beschriftung, erfassungOeffnenButton);

        Scene scene = new Scene(layout, 300, 200);
        this.setScene(scene);
    }

    public void showView() {
        this.show();
    }
}
