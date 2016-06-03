package co.uk.taurasystems.application.ui.root;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.Metacube;
import co.uk.taurasystems.db.H2Database;
import co.uk.taurasystems.db.models.Customer;
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
	
	@FXML private TabPane tabbedPane;
	@FXML private ListView<String> customerListView;
	final ObservableList<String> customerListItems = FXCollections.observableArrayList();

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		//		assert menuBar != null : "fx:id=\"menuBar\"was not injected: check your FXML file 'Root.fxml'.";
		//		assert menuCloseButton != null : "fx:id=\"menuCloseButton\"was not injected: check your FXML file 'Root.fxml'.";
		customerListView.setItems(customerListItems);
		menuCloseButton.setOnAction(e -> System.exit(0));
		menuNewButton.setOnAction(e -> Metacube.loadNewDialog());

		populateCustomerList();
	}
	
	public void openCustomerTab(Customer customer) {
		tabbedPane.getTabs().add(new Tab(customer.getFirstName()+" "+customer.getSurname()));
	}
	
	public void addCustomerToList(Customer customer) {
		customerListItems.add(customer.getFirstName()+" "+customer.getSurname());
	}
	
	private void populateCustomerList() {
		try {
			ResultSet results = H2Database.getConnection().createStatement().executeQuery("SELECT * FROM customer");
			while (results.next()) {
				String firstname = (String)results.getObject("firstname");
				String surname = (String)results.getObject("surname");
				customerListItems.add(firstname + " " + surname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
