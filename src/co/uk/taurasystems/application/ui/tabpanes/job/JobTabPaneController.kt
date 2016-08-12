package co.uk.taurasystems.application.ui.tabpanes.job

import java.net.URL
import java.util.ResourceBundle

import co.uk.taurasystems.db.models.Job
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label

class JobTabPaneController {

    private var job: Job = Job()

    @FXML internal var jobNumberLabel: Label? = null

    fun initialize(fxmlFileLocation: URL, resources: ResourceBundle) {
        jobNumberLabel?.text = jobNumberLabel?.text + " " + job.ID
    }

    fun setJob(job: Job) {
        this.job = job
    }
}
