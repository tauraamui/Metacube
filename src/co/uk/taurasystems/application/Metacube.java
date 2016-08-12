package co.uk.taurasystems.application;

import java.io.IOException;

import co.uk.taurasystems.application.ui.newdialog.NewDialogController;
import co.uk.taurasystems.application.ui.tabpanes.root.RootContainer;
import co.uk.taurasystems.db.Database;
import co.uk.taurasystems.db.models.controllers.CustomerController;
import co.uk.taurasystems.db.models.controllers.JobController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Metacube extends Application {

	public static RootContainer _rootController;
	public static NewDialogController _newDialogController;

	@Override
	public void start(Stage primaryStage) {
		loadMainWindow(primaryStage);
	}

	public static void loadMainWindow(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(RootContainer.class.getResource("RootContainer.fxml"));
			//FXMLLoader fxmlLoader = new FXMLLoader(RootController.class.getResource("Root.fxml"));
			Parent root = fxmlLoader.load();
			//_rootController = (RootContainer)fxmlLoader1.getController();
			Scene scene = new Scene(root);
			primaryStage.setTitle("Metacube");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadNewDialog() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(NewDialogController.class.getResource("NewDialog.fxml"));
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			loadNewDialog();
			Stage dialog = new Stage();
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.setResizable(false);
			dialog.setTitle("New...");
			dialog.sizeToScene();
			//give this dialog instance to the NewDialogController
			_newDialogController = (NewDialogController)fxmlLoader.getController();
			((NewDialogController)fxmlLoader.getController()).setStage(dialog);
			dialog.show();
			dialog.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setupDatabaseTables() {
		Database.executeUpdate(CustomerController.getCreationStruct());
		Database.executeUpdate(JobController.getCreationStruct());
	}

	public static void main(String[] args) {
		System.out.println("loading db...");
		Database.setDriverClassName("org.postgresql.Driver");
		Database.setURL("jdbc:postgresql://store.taurasystems.co.uk/rtpvmply");
		Database.setUseraname("rtpvmply");
		//I change the following DB password everytime I upload my source to GitHub. So fuck you ;)
		Database.setPassword("GPxCvv8QiTkadRf_I4QBWQOa5-UVhJTK");
		Database.initConnection();
		setupDatabaseTables();
		System.out.println("loaded db...");
		System.out.println("loading UI...");
		launch(args);
	}
}
