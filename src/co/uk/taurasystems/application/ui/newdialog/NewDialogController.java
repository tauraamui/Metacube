package co.uk.taurasystems.application.ui.newdialog;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.Metacube;
import co.uk.taurasystems.db.Database;
import co.uk.taurasystems.db.Statement;
import co.uk.taurasystems.db.models.Customer;
import co.uk.taurasystems.db.models.controllers.CustomerController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewDialogController implements Initializable {
	
	private Stage newDialogStage;
	
	@FXML private AnchorPane anchorPane;
	@FXML private Button okButton;
	@FXML private Button cancelButton;
	@FXML private TextField firstNameTextField;
	@FXML private TextField surnameTextField;
	@FXML private TextField phoneNumberTextField;
	@FXML private TextField addressFirstLineTextField;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		cancelButton.setOnAction(e -> newDialogStage.close());
		okButton.setOnAction(e -> createCustomer());
		firstNameTextField.setOnKeyTyped(e -> firstNameTextField.setStyle(""));
		surnameTextField.setOnKeyTyped(e -> surnameTextField.setStyle(""));
	}
	
	public void setStage(Stage newDialogStage) {
		this.newDialogStage = newDialogStage;
	}
	
	private void createCustomer() {
		if (validateFieldContents()) {
			Customer customer = new Customer();
			customer.setFirstName(firstNameTextField.getText());
			customer.setSurname(surnameTextField.getText());
			customer.setPhoneNumber(phoneNumberTextField.getText());
			customer.setAddressFirstLine(addressFirstLineTextField.getText());
			String insertStatement = Statement.getInsertStatement("customer",
					new String[]{"firstname", "surname", "phonenumber", "addressfirstline"},
					new String[]{"'"+customer.getFirstName()+"'","'"+customer.getSurname()+"'","'"+customer.getPhoneNumber()+"'","'"+customer.getAddressFirstLine()+"'"});
			Database.executeUpdate(insertStatement);
			customer = CustomerController.getLatestOf(customer);
			if (customer != null) {
				newDialogStage.close();
				Metacube._rootController.openCustomerTab(customer, true);
				Metacube._rootController.updateCustomerList();
			}
		}
	}

	private boolean validateFieldContents() {
		boolean noBlankFields = true;
		if (firstNameTextField.getText().isEmpty()) {
			firstNameTextField.setStyle("-fx-prompt-text-fill: rgba(255, 0, 0, 1)");
			noBlankFields = false;
		}
		if (surnameTextField.getText().isEmpty()) {
			surnameTextField.setStyle("-fx-prompt-text-fill: rgba(255, 0, 0, 1)");
			noBlankFields = false;
		}
		return noBlankFields;
	}
}
