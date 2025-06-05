package fh.pk1.gui;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InakzeptablesRisiko extends ExtremesRisiko{


    public InakzeptablesRisiko(Risiko risiko, Stage stage) {
        super(risiko, stage);
    
        this.initOwner(stage);
        this.initModality(Modality.WINDOW_MODAL);
        this.setTitle("Risikoerfassung");

        // Vorbereitendes leeres Layout
        VBox layout = new VBox();
        Scene scene = new Scene(layout, 300, 200);
        this.setScene(scene);
    }
    

    public void showView() {
        this.show();
    }
    
}