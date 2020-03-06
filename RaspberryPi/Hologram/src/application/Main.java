package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage)throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("test.fxml"));
	
		stage.setScene(new Scene(root));
		stage.setMaximized(true);
		stage.setTitle("Hologram Watch");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}