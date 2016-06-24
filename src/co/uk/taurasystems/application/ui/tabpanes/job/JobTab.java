package co.uk.taurasystems.application.ui.tabpanes.job;

import java.io.IOException;

import co.uk.taurasystems.db.models.Job;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class JobTab extends Tab {
	
	private Job job;

	public JobTab(Job job) {
		this.job = job;
		setText("ID: "+job.getID());
		load();
	}
	
	public void load() {
		FXMLLoader fxmlLoader = new FXMLLoader(JobTabPaneController.class.getResource("JobTabPane.fxml"));
		try {
			Parent tabContents = (Parent)fxmlLoader.load();
			JobTabPaneController controller = (JobTabPaneController)fxmlLoader.getController();
			controller.setJob(job);
			controller.initialize(fxmlLoader.getLocation(), fxmlLoader.getResources());
			AnchorPane pane = new AnchorPane();
			AnchorPane.setTopAnchor(tabContents, 0.0);
			AnchorPane.setRightAnchor(tabContents, 0.0);
			AnchorPane.setLeftAnchor(tabContents, 0.0);
			AnchorPane.setBottomAnchor(tabContents, 0.0);
			pane.getChildren().addAll(tabContents);
			setContent(pane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Job getJob() {
		return job;
	}
}
