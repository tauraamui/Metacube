package co.uk.taurasystems.application.ui.newdialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewDialogController {
	
	private Stage dialog = new Stage();
	
	@FXML
	private Button cancelButton;
	
	public NewDialogController() {
		init();
	}
	
	private void init() {
		cancelButton.setOnAction(e -> dialog.close());
	}
	
	public void showWindow() {
		FXMLLoader newDialogLoader = new FXMLLoader();
		newDialogLoader.setLocation(getClass().getResource("NewDialog.fxml"));
		Parent newDialog;
		try {
			newDialog = (Parent)newDialogLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setResizable(false);
        dialog.setTitle("New...");
        dialog.setScene(new Scene(newDialog));
        dialog.sizeToScene();
        dialog.show();
	}
}
