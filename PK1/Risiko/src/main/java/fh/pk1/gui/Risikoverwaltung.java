package fh.pk1.gui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Risikoverwaltung extends RisikoErfassungView{

    public Risikoverwaltung(Risiko risiko, Stage owner){
        super(risiko, owner);

        this.initOwner(owner);
        this.initModality(Modality.WINDOW_MODAL);
        this.setTitle("Risikoerfassung");

        // Vorbereitendes leeres Layout
        VBox layout = new VBox();
        Scene scene = new Scene(layout, 300, 200);
        this.setScene(scene);

        Risikoerfassung erfassung = new Risikoerfassung(risiko, owner);
        ExtremesRisiko extrem = new ExtremesRisiko(risiko, owner);
        InakzeptablesRisiko inakzeptable = new InakzeptablesRisiko(risiko, owner);

        this.showView();
        erfassung.showView();
        extrem.showView();
        inakzeptable.showView();
    }

    public void showView(){
        this.show();
    }
}