package co.uk.taurasystems.application.ui.newdialog;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewDialogController implements Initializable {
	
	private Stage newDialogStage;
	
	@FXML private AnchorPane anchorPane;
	
	@FXML private Button cancelButton;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		cancelButton.setOnAction(e -> newDialogStage.close());
	}
	
	public void setStage(Stage newDialogStage) {
		this.newDialogStage = newDialogStage;
	}
}
