package co.uk.taurasystems.application.ui.root;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.ui.newdialog.NewDialogController;
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
	
	private NewDialogController newDialogController = new NewDialogController();

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
		menuNewButton.setOnAction(e -> newDialogController.showWindow());
	}
}
