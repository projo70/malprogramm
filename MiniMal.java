package malprogramm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiniMal extends Application{
	@Override
	public void start(Stage meineStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("sbminimal.fxml"));
		
		Scene meineScene = new Scene (root,800,600);
		
		meineStage.setTitle("Ein einfaches Malprogramm");
		meineStage.setScene(meineScene);
		meineStage.setResizable(false);
		meineStage.show();
	}

	public static void main(String[] args) {
		// TODO Automatisch generierter Methodenstub
		launch(args);
	}
}
