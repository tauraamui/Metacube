package co.uk.taurasystems.application.ui.tabpanes.root;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.Metacube;
import co.uk.taurasystems.application.ui.tabpanes.customer.CustomerTab;
import co.uk.taurasystems.db.models.Customer;
import co.uk.taurasystems.db.models.controllers.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
	@FXML private SplitPane splitPane;
	final ObservableList<String> customerListItems = FXCollections.observableArrayList();
	private ArrayList<Customer> customerList;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		//		assert menuBar != null : "fx:id=\"menuBar\"was not injected: check your FXML file 'Root.fxml'.";
		//		assert menuCloseButton != null : "fx:id=\"menuCloseButton\"was not injected: check your FXML file 'Root.fxml'.";
		customerListView.setItems(customerListItems);
		menuCloseButton.setOnAction(e -> System.exit(0));
		menuNewButton.setOnAction(e -> Metacube.loadNewDialog());
		customerListView.setOnMouseClicked(e -> onListClick(e));
		updateCustomerList();
	}
	
	private void openCustomerTab(Customer customer) {
		CustomerTab customerTab = new CustomerTab(customer);
		tabbedPane.getTabs().add(customerTab);
		tabbedPane.getSelectionModel().select(customerTab);
	}
	
	public void openCustomerTab(Customer customer, boolean checkForAlreadyOpen) {
		if (!checkForAlreadyOpen) {openCustomerTab(customer); return;}
		ObservableList<Tab> existingTabs = tabbedPane.getTabs();
		for (Tab currentTab : existingTabs) {
			if (currentTab instanceof CustomerTab) {
				if (((CustomerTab) currentTab).getCustomer().getID() == customer.getID()) {
					tabbedPane.getSelectionModel().select(currentTab);
					return;
				}
			}
		}
		openCustomerTab(customer);
	}
	
	public void updateCustomerList() {
		customerList = CustomerController.sortAlphabetically(CustomerController.getAllCustomers());
		if (customerList == null) return;
		customerListItems.clear();
		for (Customer customer : customerList) {
			customerListItems.add(customer.getFirstName()+" "+customer.getSurname());
		}
	}
	
	public void onListClick(MouseEvent e) {
		if (e.getButton().equals(MouseButton.PRIMARY)) {
			//if double clicked
			if (e.getClickCount() >= 2) {
				int selectionIndex = customerListView.getSelectionModel().getSelectedIndex();
				if (selectionIndex < 0 || selectionIndex > customerList.size()) return;
				openCustomerTab(customerList.get(selectionIndex), true);
			}
		}
	}
}
