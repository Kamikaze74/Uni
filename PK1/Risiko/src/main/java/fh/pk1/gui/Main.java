package fh.pk1.gui;

import fh.pk1.beans.AkzeptablesRisikoBean;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	public void start(Stage primaryStage){

		RisikoverwaltungView verwatung = new RisikoverwaltungView(primaryStage, new AkzeptablesRisikoBean());
		verwatung.showView();
	}

	public static void main(String[] args){
		launch();
	}
}