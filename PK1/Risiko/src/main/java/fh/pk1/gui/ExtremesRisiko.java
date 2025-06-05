package fh.pk1.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;

public class ExtremesRisiko extends RisikoErfassungView{


    public ExtremesRisiko(Risiko risiko, Stage primaryStage) {
        super(risiko, primaryStage);
        this.initOwner(primaryStage);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);


        Label beschriftung = new Label("Erfassung extremes Risiko");
        beschriftung.setFont(Font.font(16));
        

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.BASELINE_LEFT);

        TextField tf1 = new TextField("Vorgabe");
        tf1.setPrefWidth(250);

        inputGrid.addRow(1, new Label("Bezeichnung: TODO"));
        inputGrid.addRow(2, new Label("Maßnahme:"), tf1);
        inputGrid.addRow(2, new Label("Versicherungsbeitrag:"), tf1);



        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(40);
        buttonPane.setAlignment(Pos.CENTER);

        Button neu = new Button("Neu");
        Button abbrechen = new Button("Abbrechen");
        buttonPane.getChildren().addAll(neu, abbrechen);
        VBox.setMargin(buttonPane, new Insets(20, 0, 0, 0));


        root.getChildren().addAll(beschriftung, inputGrid, buttonPane);

        Scene scene = new Scene(root, 400, 250);
        setScene(scene);
    }

    public void showView() {
        this.show();
    }

}