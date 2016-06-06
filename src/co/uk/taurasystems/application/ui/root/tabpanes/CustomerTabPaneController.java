package co.uk.taurasystems.application.ui.root.tabpanes;

import java.net.URL;
import java.util.ResourceBundle;

import co.uk.taurasystems.db.models.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CustomerTabPaneController implements Initializable {
	
	private Customer customer;
	
	@FXML TextField firstNameField;
	@FXML TextField surnameField;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		if (customer == null) return;
		firstNameField.setText(customer.getFirstName());
		surnameField.setText(customer.getSurname());
//		surnameField.setText(customer.getSurname());
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
