package co.uk.taurasystems.application.ui.newdialog;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.Metacube;
import co.uk.taurasystems.db.H2Database;
import co.uk.taurasystems.db.H2Statement;
import co.uk.taurasystems.db.models.Customer;
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
	@FXML private TextField addressFirstLineTextField;
	
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
		customer.setAddressFirstLine(addressFirstLineTextField.getText());
		String insertStatement = H2Statement.getInsertStatement("customer",
				//TODO: Fix the autoincrement field issue in the SQL structs
				   new String[]{"ID", "firstname", "surname", "phonenumber", "addressfirstline"},
				   new String[]{"'"+customer.getFirstName()+"'", "'"+
				   customer.getSurname()+"'", "'"+customer.getPhoneNumber()+"'","'"+customer.getAddressFirstLine()+"'"});
		H2Database.executeUpdate(insertStatement);
		newDialogStage.close();
		Metacube._rootController.openCustomerTab(customer, true);
		Metacube._rootController.updateCustomerList();
	}
}
