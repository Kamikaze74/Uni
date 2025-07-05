package fh.pk1.gui;

import fh.pk1.beans.ExtremesRisikoBean;
import fh.pk1.beans.RisikoBean;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ExtremesRisikoView extends RisikoErfassungView{

    ExtremesRisikoBean newBean = null;

    public ExtremesRisikoView(Stage primaryStage, RisikoBean bean) {
        super(primaryStage, bean);
        convertBean(bean);

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
        TextField tf2 = new TextField("Vorgabe");
        tf1.setPrefWidth(250);

        inputGrid.addRow(1, new Label(bean.getBezeichnung()));
        inputGrid.addRow(2, new Label("Maßnahme:"), tf1);
        inputGrid.addRow(3, new Label("Versicherungsbeitrag:"), tf2);



        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(40);
        buttonPane.setAlignment(Pos.CENTER);

        Button neu = new Button("Neu");
        Button abbrechen = new Button("Abbrechen");
        buttonPane.getChildren().addAll(neu, abbrechen);
        VBox.setMargin(buttonPane, new Insets(20, 0, 0, 0));

        neu.setOnAction(e -> {
            newBean.setMaßnahme(tf1.getText());
            newBean.setVersicherungsbeitrag(Float.valueOf(tf2.getText()));
            wurdeGespeichert = true;
            close();
        });

        abbrechen.setOnAction(e -> {
            close();
        });
        
        root.getChildren().addAll(beschriftung, inputGrid, buttonPane);

        Scene scene = new Scene(root, 450, 250);
        setScene(scene);
    }

    public void convertBean(RisikoBean bean){
        newBean = new ExtremesRisikoBean();
        newBean.setBezeichnung(bean.getBezeichnung());
        newBean.setEintrittswahrscheinlichkeit(bean.getEintrittswahrscheinlichkeit());
        newBean.setKosten_im_schadenfall(bean.getKosten_im_schadenfall());
    }

    @Override
    public ExtremesRisikoBean getBean(){
        return newBean;
    }

    public void showView() {
        this.show();
    }
}