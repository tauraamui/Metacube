package co.uk.taurasystems.application.ui.root;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {

	@FXML
	private MenuBar menuBar;

	//------MENU LISTS-------
	@FXML
	private MenuItem fileMenuList;
	@FXML
	private MenuItem editMenuList;
	@FXML
	private MenuItem menuHelpList;
	//------MENU LISTS-------
	
	//------MENU BUTTONS-----
	@FXML
	private MenuItem menuNewButton;
	@FXML
	private MenuItem menuCloseButton;
	@FXML
	private MenuItem menuDeleteButton;
	@FXML
	private MenuItem menuAboutButton;
	//------MENU BUTTONS-----

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
//		assert menuBar != null : "fx:id=\"menuBar\"was not injected: check your FXML file 'Root.fxml'.";
//		assert menuCloseButton != null : "fx:id=\"menuCloseButton\"was not injected: check your FXML file 'Root.fxml'.";
		menuCloseButton.setOnAction(e -> System.exit(0));
		menuNewButton.setOnAction(e -> showNewDialog());
		menuAboutButton.setOnAction(e -> System.out.println("Testing"));
	}
	
	private void showNewDialog() {
		FXMLLoader newDialogLoader = new FXMLLoader();
		newDialogLoader.setLocation(getClass().getResource("NewDialog.fxml"));
		Parent newDialog;
		try {
			newDialog = (Parent)newDialogLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("New...");
        stage.setScene(new Scene(newDialog));
        stage.show();
	}
}
