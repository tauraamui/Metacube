package co.uk.taurasystems.application.ui.root.tabpanes.job;

import java.net.URL;
import java.util.ResourceBundle;

import co.uk.taurasystems.db.models.Job;
import javafx.fxml.Initializable;

public class JobTabPaneController implements Initializable {
	
	private Job job;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		if (job == null) return;
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
}
