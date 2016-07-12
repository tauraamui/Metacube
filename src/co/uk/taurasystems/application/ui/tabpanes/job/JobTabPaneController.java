package co.uk.taurasystems.application.ui.tabpanes.job;

import java.net.URL;
import java.util.ResourceBundle;

import co.uk.taurasystems.db.models.Job;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class JobTabPaneController implements Initializable {
	
	private Job job;
	
	@FXML Label jobNumberLabel;

	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		if (job == null) return;
		jobNumberLabel.setText(jobNumberLabel.getText() + " " + job.getID());
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
}
