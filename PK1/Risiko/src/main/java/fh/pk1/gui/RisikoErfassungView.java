package fh.pk1.gui;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class RisikoErfassungView extends Stage{

    public RisikoErfassungView(Stage stage, Risiko risiko){

        Window primaryStage = stage;
        this.initOwner(primaryStage);
        this.initModality(Modality.WINDOW_MODAL);
    }

    abstract void Showview();
}
