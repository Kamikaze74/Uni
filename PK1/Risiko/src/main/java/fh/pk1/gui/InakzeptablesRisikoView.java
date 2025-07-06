package fh.pk1.gui;

import fh.pk1.gui.beans.InakzeptablesRisikoBean;
import fh.pk1.gui.beans.RisikoBean;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InakzeptablesRisikoView extends RisikoErfassungView{

    InakzeptablesRisikoBean newBean = null;

    public InakzeptablesRisikoView(Stage primaryStage, RisikoBean bean) {
        super(primaryStage, bean);
        convertBean(bean);


        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);


        Label beschriftung = new Label("Erfassung inakzeptables Risiko");
        beschriftung.setFont(Font.font(16));


        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.BASELINE_LEFT);

        TextField tf1 = new TextField("Vorgabe");
        tf1.setPrefWidth(250);

        inputGrid.addRow(1, new Label(bean.getBezeichnung())); // Bezeichnung muss von Risikoerfassung übergeben werden
        inputGrid.addRow(2, new Label("Maßnahme:"), tf1);


        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(40);
        buttonPane.setAlignment(Pos.CENTER);

        Button neu = new Button("Neu");
        Button abbrechen = new Button("Abbrechen");
        buttonPane.getChildren().addAll(neu, abbrechen);
        VBox.setMargin(buttonPane, new Insets(20, 0, 0, 0));

        neu.setOnAction(e -> {
            newBean.setMaßnahme(tf1.getText());
            wurdeGespeichert = true;
            close();
        });

        abbrechen.setOnAction(e -> {
            close();
        });

        root.getChildren().addAll(beschriftung, inputGrid, buttonPane);

        Scene scene = new Scene(root, 400, 250);
        setScene(scene);
    }

    public void convertBean(RisikoBean bean){
        newBean = new InakzeptablesRisikoBean();
        newBean.setBezeichnung(bean.getBezeichnung());
        newBean.setEintrittswahrscheinlichkeit(bean.getEintrittswahrscheinlichkeit());
        newBean.setKosten_im_schadenfall(bean.getKosten_im_schadenfall());
    }

    @Override
    public InakzeptablesRisikoBean getBean(){
        return newBean;
    }

    public void showView() {
        this.show();
    }
}