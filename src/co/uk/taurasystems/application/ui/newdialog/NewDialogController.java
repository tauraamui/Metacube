package co.uk.taurasystems.application.ui.newdialog;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import co.uk.taurasystems.models.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewDialogController implements Initializable {
	
	private Stage newDialogStage;
	
	@FXML private AnchorPane anchorPane;
	
	@FXML private Button okButton;
	
	@FXML private Button cancelButton;
	
	@FXML private TextField firstNameTextField;
	
	@FXML private TextField surnameTextField;
	
	@FXML private TextField phoneNumberTextField;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		cancelButton.setOnAction(e -> newDialogStage.close());
		okButton.setOnAction(e -> createCustomer());
	}
	
	public void setStage(Stage newDialogStage) {
		this.newDialogStage = newDialogStage;
	}
	
	private void createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName(firstNameTextField.getText());
		customer.setSurname(surnameTextField.getText());
		customer.setPhoneNumber(phoneNumberTextField.getText());
		Properties customerProperties = new Properties();
		customerProperties.setProperty("customer_firstname", customer.getFirstName());
		customerProperties.setProperty("customer_surname", customer.getSurname());
		customerProperties.setProperty("customer_phonenumber", customer.getPhoneNumber());
	}
}
