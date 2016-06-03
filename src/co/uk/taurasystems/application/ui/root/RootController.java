package co.uk.taurasystems.application.ui.root;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.Metacube;
import co.uk.taurasystems.db.models.Customer;
import co.uk.taurasystems.db.models.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController implements Initializable {

	@FXML private MenuBar menuBar;

	//------MENU LISTS-------
	@FXML private MenuItem fileMenuList;
	@FXML private MenuItem editMenuList;
	@FXML private MenuItem menuHelpList;
	//------MENU LISTS-------

	//------MENU BUTTONS-----
	@FXML private MenuItem menuNewButton;
	@FXML private MenuItem menuCloseButton;
	@FXML private MenuItem menuDeleteButton;
	@FXML private MenuItem menuAboutButton;
	//------MENU BUTTONS-----
	
	@FXML private TabPane tabbedPane;
	@FXML private ListView<String> customerListView;
	final ObservableList<String> customerListItems = FXCollections.observableArrayList();
	private ArrayList<Customer> customerList;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		//		assert menuBar != null : "fx:id=\"menuBar\"was not injected: check your FXML file 'Root.fxml'.";
		//		assert menuCloseButton != null : "fx:id=\"menuCloseButton\"was not injected: check your FXML file 'Root.fxml'.";
		customerListView.setItems(customerListItems);
		menuCloseButton.setOnAction(e -> System.exit(0));
		menuNewButton.setOnAction(e -> Metacube.loadNewDialog());

		updateCustomerList();
	}
	
	public void openCustomerTab(Customer customer) {
		tabbedPane.getTabs().add(new Tab(customer.getFirstName()+" "+customer.getSurname()));
	}
	
	public void updateCustomerList() {
		customerList = CustomerController.getAllCustomers();
		if (customerList == null) return;
		customerListItems.clear();
		for (Customer customer : customerList) {
			customerListItems.add(customer.getFirstName()+" "+customer.getSurname());
			System.out.println(customer.getID());
		}
	}
}
