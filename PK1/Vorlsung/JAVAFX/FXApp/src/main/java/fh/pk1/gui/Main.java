package fh.pk1.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{

	public void start(Stage primaryStage){
         Label label = new Label("Hallo JavaFX");
		 label.setFont(new Font(40));
		 Scene scene = new Scene(label);

		 primaryStage.setScene(scene);

		 primaryStage.show();
	}

	public static void main(String[] args){
		launch();
	}
}