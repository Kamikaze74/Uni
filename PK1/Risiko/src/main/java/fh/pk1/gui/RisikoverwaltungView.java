package fh.pk1.gui;

import java.io.ByteArrayOutputStream;
import java.io.File;

import fh.pk1.fachebene.Risiko;
import fh.pk1.fachebene.Risikoverwaltung;
import fh.pk1.gui.beans.*;
import fh.pk1.gui.guiutil.pk1.gui.util.MessageView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RisikoverwaltungView extends RisikoErfassungView {

    RisikoverwaltungBean beans = new RisikoverwaltungBean();

    public RisikoverwaltungView(Stage primaryStage, RisikoBean bean) {
        super(primaryStage, bean);
        this.setTitle("Risikoverwaltung");

//      ------------------ VBox/ListView ------------------------------    //
        VBox layout = new VBox(0);
        layout.setAlignment(Pos.TOP_LEFT);
        ListView<String> listView = new ListView<>();
        VBox.setVgrow(listView, javafx.scene.layout.Priority.ALWAYS);
        listView.getSelectionModel().selectFirst();
        listView.requestFocus();
        listView.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setPrefHeight(25); // Zeilengröße auf 25 Pixel setzen
                setText(item);
                if (!empty) {
                    if (isFocused()) {
                        setStyle("-fx-background-color: #90caf9;"); // hellblau für Fokus
                    } else if (getIndex() % 2 == 0) {
                        setStyle("-fx-background-color: lightgray;");
                    } else {
                        setStyle("-fx-background-color: white;");
                    }
                } else {
                    setStyle("");
                }
            }
        });

//      ------------------------------------------------------    //
//      ------------------ MENÜLEISTE -----------------------    //
        MenuBar bar = new MenuBar();
        Menu datei = new Menu("Datei");
        Menu risiko = new Menu("Risiko");
        Menu anzeige = new Menu("Anzeige");

        MenuItem laden = new MenuItem("Laden");
        MenuItem speichern = new MenuItem("Speichern");
        MenuItem risikolisteInDatei = new MenuItem("Risiko in Datei");
        MenuItem beenden = new MenuItem("Beenden");
        MenuItem neusRisiko = new MenuItem("Neues Risiko");
        MenuItem maxiRueckstellug = new MenuItem("Risiko mit maximaler Rückstellung");
        MenuItem summeAllerRückstellungen = new MenuItem("Summe aller Rückstellungen");

        datei.getItems().addAll(laden, new SeparatorMenuItem(), speichern, new SeparatorMenuItem(), risikolisteInDatei, new SeparatorMenuItem(), beenden);
        risiko.getItems().addAll(neusRisiko);
        anzeige.getItems().addAll(maxiRueckstellug, new SeparatorMenuItem(), summeAllerRückstellungen);

        bar.getMenus().addAll(datei, risiko, anzeige);
        layout.getChildren().addAll(bar, listView);

//      ------------------------------------------------------    //
//      ------------------ AKTIONEN --------------------------    //
        laden.setOnAction(e -> {
            // TODO: Ladefunktion implementieren
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Risiko-Datei laden");
            File file = fileChooser.showOpenDialog(primaryStage);

            Risikoverwaltung verwaltung = new Risikoverwaltung();
            verwaltung.deserialsierung(file);

            for (Risiko r : verwaltung.getRisikos()) {

                RisikoBean risikoBean = null;
                if (r instanceof fh.pk1.fachebene.AkzeptablesRisiko) {
                    risikoBean = new AkzeptablesRisikoBean();
                } else if (r instanceof fh.pk1.fachebene.ExtremesRisiko) {
                    risikoBean = new ExtremesRisikoBean();
                } else if (r instanceof fh.pk1.fachebene.InakzeptablesRisiko) {
                    risikoBean = new InakzeptablesRisikoBean();
                }
                if (risikoBean != null) {
                    risikoBean.fromFE(r);
                    beans.add(risikoBean);
                }
            }
            listView.getItems().clear();
            String[] risiken = beans.zeigeRisiken().split("\\r?\\n");
            listView.getItems().addAll(risiken);
        });

        speichern.setOnAction(e -> {
            // TODO: Ladefunktion implementieren
            Risikoverwaltung verwaltung = new Risikoverwaltung();
            for (RisikoBean risikoBeanItem : beans.getRisikos()) {
                verwaltung.getRisikos().add(risikoBeanItem.toFE());
            }
            verwaltung.serialsierung();
        });

        risikolisteInDatei.setOnAction(event -> {
            Risikoverwaltung verwaltung = new Risikoverwaltung();
            for (RisikoBean risikoBeanItem : beans.getRisikos()) {
                verwaltung.getRisikos().add(risikoBeanItem.toFE());
            }
            verwaltung.listeInDatei("risikoliste.txt");
            MessageView messageView = MessageView.create(primaryStage, "Risikoliste gespeichert", "Die Risikoliste wurde erfolgreich gespeichert.");
        });

        beenden.setOnAction(e -> {
            this.close();
        });

        neusRisiko.setOnAction(e -> {
            AkzeptablesRisikoView erfassung = new AkzeptablesRisikoView(primaryStage, new AkzeptablesRisikoBean());
            RisikoBean aBean = openWindow(erfassung);

            if (aBean == null)
                return;

            if (aBean.getKosten_im_schadenfall() >= 1000000.00) {
                ExtremesRisikoView view = new ExtremesRisikoView(primaryStage, aBean);
                RisikoBean extremesBean = openWindow(view);
                if (extremesBean != null) {
                    beans.add(extremesBean);
                    System.out.printf("%n%n%n%nDIE ELEMENTE WURDEN HINZUGEFÜGT: %n%s%n%n%n", beans.zeigeRisiken());
                }
            }else if (aBean.berechneRisikowert() >= 10000.00) {
                InakzeptablesRisikoView view = new InakzeptablesRisikoView(primaryStage, aBean);
                RisikoBean inakzeptabelBean = openWindow(view);
                if (inakzeptabelBean != null) {
                    beans.add(inakzeptabelBean);
                    System.out.printf("%n%n%n%nDIE ELEMENTE WURDEN HINZUGEFÜGT: %n%s%n%n%n", beans.zeigeRisiken());
                }
            } else {
                beans.add(aBean);
                System.out.printf("%n%n%n%nDIE ELEMENTE WURDEN HINZUGEFÜGT: %n%s%n%n%n", beans.zeigeRisiken());
            }
            //listView.getItems().clear();
            String[] risiken = beans.zeigeRisiken().split("\\r?\\n");
            listView.getItems().setAll(risiken);
            //listView.refresh(); Nur wenn sich in dem Risiko Objekt etwas geändert hat, was wir nicht implementieren
        });

        maxiRueckstellug.setOnAction(e -> {
            RisikoBean maxRueckstellung = beans.sucheRisikoMitmaxRueckstellung();
            if (maxRueckstellung != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                maxRueckstellung.druckeDaten(baos);
                MessageView messageView = MessageView.create(primaryStage, "Maximale Rückstellung", baos.toString());
                messageView.showView();
            } else {
                MessageView messageView = MessageView.create(primaryStage, "Maximale Rückstellung", "Es wurde kein Risiko mit einer Rückstellung gefunden.");
                messageView.showView();
            }
        });

        summeAllerRückstellungen.setOnAction(e -> {
            float summe = beans.berechneSummeRueckstellungen();
            if (summe > 0) {
                MessageView messageView = MessageView.create(primaryStage, "Summe aller Rückstellungen", "Die Summe aller Rückstellungen beträgt: " + summe);
                messageView.showView();
            } else {
                MessageView messageView = MessageView.create(primaryStage, "Summe aller Rückstellungen", "Es wurden keine Rückstellungen gefunden.");
                messageView.showView();
            }

        });
//      ------------------------------------------------------    //
//      -------------------Fenster----------------------------    //
        Scene scene = new Scene(layout, 700, 400);
        this.setScene(scene);
    }
//      ------------------------------------------------------    //
//      ------------------ METHODEN --------------------------    //
    private RisikoBean openWindow(RisikoErfassungView view){
        view.initModality(Modality.WINDOW_MODAL); // blockiert Hauptfenster
        view.initOwner(this); // Risikoverwaltung ist Besitzer
        view.showAndWait();
        if (view.isGespeichert()) {
            return view.getBean();
        }
        return null;
    }

    public void showView() {
        this.show();
    }
}