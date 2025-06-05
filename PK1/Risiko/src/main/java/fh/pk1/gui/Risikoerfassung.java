package fh.pk1.gui;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Risikoerfassung extends RisikoErfassungView{

    public Risikoerfassung(Risiko risiko, Stage owner) {
        super(risiko, owner);
        this.initOwner(owner);
        this.initModality(Modality.WINDOW_MODAL);
        this.setTitle("Risikoerfassung");

        // Vorbereitendes leeres Layout
        VBox layout = new VBox();
        Scene scene = new Scene(layout, 300, 200);
        this.setScene(scene);

    }

    public void showView() {
        show();
    }
}
