package co.uk.taurasystems.application;
	

import java.io.IOException;

import co.uk.taurasystems.application.ui.newdialog.NewDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Metacube extends Application {
	
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		loadMainWindow();
	}
	
	public static void loadMainWindow() {
		try {
			Parent root = FXMLLoader.load(Metacube.class.getResource("\\ui\\root\\Root.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Metacube");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadNewDialog() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(NewDialogController.class.getResource("NewDialog.fxml"));
			Parent root = (Parent)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage dialog = new Stage();
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.setResizable(false);
			dialog.setTitle("New...");
			dialog.sizeToScene();
			//give this dialog instance to the NewDialogController
			((NewDialogController)fxmlLoader.getController()).setStage(dialog);
			dialog.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
