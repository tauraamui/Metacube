package co.uk.taurasystems.application.ui.root.tabpanes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uk.taurasystems.application.ui.root.tabpanes.job.JobTab;
import co.uk.taurasystems.application.ui.root.tabpanes.job.JobTabPaneController;
import co.uk.taurasystems.db.models.Customer;
import co.uk.taurasystems.db.models.Job;
import co.uk.taurasystems.db.models.JobController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CustomerTabPaneController implements Initializable {
	
	private Customer customer;
	private ArrayList<Job> jobList;
	final ObservableList<String> jobListItems = FXCollections.observableArrayList();
	
	@FXML TextField firstNameField;
	@FXML TextField surnameField;
	@FXML TextField phoneNumberField;
	@FXML TextField addressFirstLineField;
	@FXML TabPane jobTabPane;
	
	@FXML ListView<String> jobListView;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		if (customer == null) return;
		firstNameField.setText(customer.getFirstName());
		surnameField.setText(customer.getSurname());
		phoneNumberField.setText(customer.getPhoneNumber());
		addressFirstLineField.setText(customer.getAddressFirstLine());
		jobListView.setItems(jobListItems);
		jobListView.setOnMouseClicked(e -> onListClick(e));
		updateJobList();
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public void openJobTab(Job job) {
		JobTab jobTab = new JobTab(job);
		jobTabPane.getTabs().add(jobTab);
		jobTabPane.getSelectionModel().select(jobTab);
	}
	
	public void openJobTab(Job job, boolean checkForAlreadyOpen) {
		if (!checkForAlreadyOpen) {openJobTab(job); return;}
		ObservableList<Tab> existingTabs = jobTabPane.getTabs();
		for (Tab currentTab : existingTabs) {
			if (currentTab instanceof JobTab) {
				if (((JobTab) currentTab).getJob().getID() == job.getID()) {
					jobTabPane.getSelectionModel().select(currentTab);
					return;
				}
			}
		}
		openJobTab(job);
	}
	
	public void updateJobList() {
		jobList = JobController.getJobsByCustomerID(customer.getID());
		if (jobList == null) return;
		jobListItems.clear();
		for (Job job : jobList) {
			jobListItems.add(String.valueOf(job.getID()));
		}
	}
	
	public void onListClick(MouseEvent e) {
		if (e.getButton().equals(MouseButton.PRIMARY)) {
			//if double clicked
			if (e.getClickCount() >= 2) {
				int selectionIndex = jobListView.getSelectionModel().getSelectedIndex();
				if (selectionIndex < 0 || selectionIndex > jobList.size()) return;
				openJobTab(jobList.get(selectionIndex));
			}
		}
	}
}
