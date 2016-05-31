package co.uk.taurasystems.application.ui.root;

import java.net.URL;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.Metacube;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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
		menuNewButton.setOnAction(e -> Metacube.loadNewDialog());
	}
}
