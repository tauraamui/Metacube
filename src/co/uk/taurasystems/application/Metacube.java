package co.uk.taurasystems.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
			primaryStage.setMaximized(true);
			Properties properties = new Properties();
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
			dialog.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initDatabaseConnection() {
		try {
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:~/Metacube";
			String user = "sa";
			String pwds = "849353475893479768347";
			Connection conn = DriverManager.getConnection(url, user, pwds);
			// add application code here
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		initDatabaseConnection();
		launch(args);
	}
}
