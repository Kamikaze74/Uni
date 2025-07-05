package fh.pk1.gui;

import fh.pk1.beans.RisikoBean;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AkzeptablesRisikoView extends RisikoErfassungView{

    public AkzeptablesRisikoView(Stage primaryStage, RisikoBean bean) {
        super (primaryStage, bean);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);


        Label beschriftung = new Label("Risikoerfassung");
        beschriftung.setFont(Font.font(16));


        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.CENTER_LEFT);

        TextField tf1 = new TextField("Vorgabe");
        tf1.setPrefWidth(250);
        TextField tf2 = new TextField("Vorgabe");
        tf2.setPrefWidth(250);
        TextField tf3 = new TextField("Vorgabe");
        tf3.setPrefWidth(250);

        inputGrid.addRow(1, new Label("Bezeichnung:"), tf1);
        inputGrid.addRow(2, new Label("Eintrittswahrscheinlichkeit:"), tf2);
        inputGrid.addRow(3, new Label("Kosten im Schadensfall:"), tf3);


        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(40);
        buttonPane.setAlignment(Pos.CENTER);

        Button neu = new Button("Neu");
        Button abbrechen = new Button("Abbrechen");
        buttonPane.getChildren().addAll(neu, abbrechen);
        VBox.setMargin(buttonPane, new Insets(20, 0, 0, 0));


        neu.setOnAction(e -> {
            bean.setBezeichnung(tf1.getText());
            bean.setEintrittswahrscheinlichkeit(Float.valueOf(tf2.getText()));
            bean.setKosten_im_schadenfall(Float.valueOf(tf3.getText()));
            wurdeGespeichert = true;
            close();
        });

        abbrechen.setOnAction(e -> {
            close();
        });

        root.getChildren().addAll(beschriftung, inputGrid, buttonPane);

        Scene scene = new Scene(root, 500, 300);
        setScene(scene);
    }

    public void showView() {
        show();
    }
}