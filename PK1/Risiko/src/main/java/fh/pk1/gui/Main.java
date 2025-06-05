package fh.pk1.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage stage) {
		Risikoverwaltung verwaltung = new Risikoverwaltung(stage, new Risiko());
		verwaltung.Showview();
	}
	public static void main(String[] args){
		launch();
	}
}