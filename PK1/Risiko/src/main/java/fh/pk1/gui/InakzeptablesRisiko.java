package fh.pk1.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InakzeptablesRisiko extends RisikoErfassungView{


    public InakzeptablesRisiko(Stage stage, Risiko risiko) {
        super(stage, risiko);
    }

    @Override
    void Showview() {
        Label nachricht = new Label("Hallo Programmierkurs 1");
        nachricht.setFont(new Font(50));
        Scene szene = new Scene(nachricht);
        setScene(szene);
        show();
    }


    
}