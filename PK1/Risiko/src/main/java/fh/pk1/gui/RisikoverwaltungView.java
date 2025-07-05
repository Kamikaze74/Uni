package fh.pk1.gui;

import fh.pk1.beans.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RisikoverwaltungView extends RisikoErfassungView {

    RisikoverwaltungBean beans = new RisikoverwaltungBean();

    public RisikoverwaltungView(Stage primaryStage, RisikoBean bean) {
        super(primaryStage, bean);

        this.setTitle("Risikoverwaltung");
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.TOP_LEFT);

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

//      ------------------ AKTIONEN --------------------------    //
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
        });
//      ------------------------------------------------------    //
//      ------------------ VBox ------------------------------    //
        layout.getChildren().addAll(bar);

        Scene scene = new Scene(layout, 500, 750);
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