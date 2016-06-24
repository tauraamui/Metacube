package co.uk.taurasystems.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.uk.taurasystems.application.ui.newdialog.NewDialogController;
import co.uk.taurasystems.application.ui.root.RootController;
import co.uk.taurasystems.db.H2Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Metacube extends Application {

	private static Stage primaryStage;
	public static Connection database;
	public static RootController rootController;
	public static NewDialogController newDialogController;

	@Override
	public void start(Stage primaryStage) {
		Metacube.primaryStage = primaryStage;
		loadMainWindow();
	}

	public static void loadMainWindow() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Metacube.class.getResource("\\ui\\root\\Root.fxml"));
			Parent root = fxmlLoader.load();
			rootController = (RootController)fxmlLoader.getController();
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
			Parent root = (Parent)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage dialog = new Stage();
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.setResizable(false);
			dialog.setTitle("New...");
			dialog.sizeToScene();
			//give this dialog instance to the NewDialogController
			newDialogController = (NewDialogController)fxmlLoader.getController();
			((NewDialogController)fxmlLoader.getController()).setStage(dialog);
			dialog.show();
			dialog.centerOnScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection initDatabaseConnection() {
		try {
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:~/Metacube";
			String username = "sa";
			String password = "849353475893479768347";
			Connection connection = DriverManager.getConnection(url, username, password);
			// add application code here
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		H2Database.setDriverClassName("org.h2.Driver");
		H2Database.setURL("jdbc:h2:~/Metacube");
		H2Database.setUseraname("sa");
		H2Database.setPassword("849353475893479768347");
		H2Database.initConnection();
		/*database = initDatabaseConnection();
		try {
			database.createStatement().executeUpdate("INSERT INTO customer(firstname, surname, phonenumber) values('Java', 'Bean', '93480238932')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ResultSet results = database.createStatement().executeQuery("SELECT * FROM customer");
			while (results.next()) {
				String firstname = (String)results.getObject("FIRSTNAME");
				System.out.println(firstname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		launch(args);
	}
}
