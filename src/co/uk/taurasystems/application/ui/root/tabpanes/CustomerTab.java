package co.uk.taurasystems.application.ui.root.tabpanes;

import java.io.IOException;

import co.uk.taurasystems.db.models.Customer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

public class CustomerTab extends Tab {
	
	private Tab tab;
	private Customer customer;

	public CustomerTab(Customer customer) {
		this.customer = customer;
		setText(customer.getFirstName()+" "+customer.getSurname());
		load();
	}
	
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(CustomerTabPaneController.class.getResource("CustomerTabPane.fxml"));
		try {
			Parent tabContents = (Parent)fxmlLoader.load();
			CustomerTabPaneController controller = (CustomerTabPaneController)fxmlLoader.getController();
			controller.setCustomer(customer);
			controller.initialize(fxmlLoader.getLocation(), fxmlLoader.getResources());
			setContent(tabContents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Customer getCustomer() {
		return customer;
	}
}