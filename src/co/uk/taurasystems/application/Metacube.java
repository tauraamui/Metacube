package co.uk.taurasystems.application;

import java.io.IOException;

import co.uk.taurasystems.application.ui.newdialog.NewDialogController;
import co.uk.taurasystems.application.ui.tabpanes.root.RootController;
import co.uk.taurasystems.db.H2Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Metacube extends Application {

	private static Stage _primaryStage;
	public static RootController _rootController;
	public static NewDialogController _newDialogController;

	@Override
	public void start(Stage primaryStage) {
		Metacube._primaryStage = primaryStage;
		loadMainWindow();
	}

	public static void loadMainWindow() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(RootController.class.getResource("Root.fxml"));
			Parent root = fxmlLoader.load();
			_rootController = (RootController)fxmlLoader.getController();
			Scene scene = new Scene(root);
			_primaryStage.setTitle("Metacube");
			_primaryStage.setScene(scene);
			_primaryStage.setMaximized(true);
			_primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadNewDialog() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Metacube.class.getResource("\\ui\\newdialog\\NewDialog.fxml"));
			Parent root = (Parent)fxmlLoader.load();
			Scene scene = new Scene(root);
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

	public static void main(String[] args) {
		H2Database.setDriverClassName("org.h2.Driver");
		H2Database.setURL("jdbc:h2:~/Metacube");
		H2Database.setUseraname("sa");
		H2Database.setPassword("849353475893479768347");
		H2Database.initConnection();
		System.out.println("loaded db... loading UI");
		/*database = initDatabaseConnection();
		try {
			database.createStatement().executeUpdate("INSERT INTO customer(firstname, surname, phonenumber) values('Java', 'Bean', '93480238932')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ResultSet results = database.createStatement().executeQuery("SELECT * FROM customer");
			while (results.next()) {
				String firstname = (String)results.getObject("FIRSTNAME");
				System.out.println(firstname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		launch(args);
	}
}
