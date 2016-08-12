package co.uk.taurasystems.application.ui.tabpanes.job

import java.io.IOException

import co.uk.taurasystems.db.models.Job
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Tab
import javafx.scene.layout.AnchorPane

class JobTab(val job: Job) : Tab() {

    init {
        text = "ID: " + job.ID
        load()
    }

    fun load() {
        val fxmlLoader = FXMLLoader(JobTabPaneController::class.java.getResource("JobTabPane.fxml"))
        try {
            val tabContents = fxmlLoader.load<Any>() as Parent
            val controller = fxmlLoader.getController<Any>() as JobTabPaneController
            controller.setJob(job)
            controller.initialize(fxmlLoader.location, fxmlLoader.resources)
            val pane = AnchorPane()
            AnchorPane.setTopAnchor(tabContents, 0.0)
            AnchorPane.setRightAnchor(tabContents, 0.0)
            AnchorPane.setLeftAnchor(tabContents, 0.0)
            AnchorPane.setBottomAnchor(tabContents, 0.0)
            pane.children.addAll(tabContents)
            content = pane
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
