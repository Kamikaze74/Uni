package fh.pk1.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class Risikoverwaltung extends RisikoErfassungView{

    ExtremesRisiko extrem;
    InakzeptablesRisiko inakzeptable;
    Risikoerfassung erfassung;

    public Risikoverwaltung(Stage stage, Risiko risiko) {
        super(stage, risiko);

        ExtremesRisiko extrem = new ExtremesRisiko(stage, risiko);
        InakzeptablesRisiko inakzeptable = new InakzeptablesRisiko(stage, risiko);
        Risikoerfassung erfassung = new Risikoerfassung(stage, risiko);
    }

    
    
    @Override
    void Showview() {
        Label nachricht = new Label("Hallo Programmierkurs 1");
        nachricht.setFont(new Font(50));
        Scene szene = new Scene(nachricht);
        setScene(szene);
        show();

        erfassung.Showview();
        extrem.Showview();
        inakzeptable.Showview();
    }
}

