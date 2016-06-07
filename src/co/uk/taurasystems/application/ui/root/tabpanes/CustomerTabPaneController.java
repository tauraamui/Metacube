package co.uk.taurasystems.application.ui.root.tabpanes;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.taurasystems.db.models.Customer;
import co.uk.taurasystems.db.models.Job;
import co.uk.taurasystems.db.models.JobController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CustomerTabPaneController implements Initializable {
	
	private Customer customer;
	private ArrayList<Job> jobList;
	final ObservableList<String> jobListItems = FXCollections.observableArrayList();
	
	@FXML TextField firstNameField;
	@FXML TextField surnameField;
	@FXML TextField phoneNumberField;
	
	@FXML ListView<String> jobListView;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		if (customer == null) return;
		firstNameField.setText(customer.getFirstName());
		surnameField.setText(customer.getSurname());
		phoneNumberField.setText(customer.getPhoneNumber());
		jobListView.setItems(jobListItems);
		updateJobList();
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	public void updateJobList() {
		jobList = JobController.getJobsByCustomerID(customer.getID());
		if (jobList == null) return;
		jobListItems.clear();
		for (Job job : jobList) {
			jobListItems.add(String.valueOf(job.getID()));
		}
	}
}
