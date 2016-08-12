package co.uk.taurasystems.application.ui.tabpanes.customer

import java.io.IOException

import co.uk.taurasystems.db.models.Customer
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Tab
import javafx.scene.layout.AnchorPane

class CustomerTab(val customer: Customer) : Tab() {

    init {
        text = customer.firstName + " " + customer.surname
        load()
    }

    fun load() {
        val fxmlLoader = FXMLLoader(CustomerTabPaneController::class.java.getResource("CustomerTabPane.fxml"))
        try {
            val tabContents = fxmlLoader.load<Any>() as Parent
            val controller = fxmlLoader.getController<Any>() as CustomerTabPaneController
            controller.setCustomer(customer)
            controller.initialize(fxmlLoader.location, fxmlLoader.resources)
            val pane = AnchorPane()
            pane.children.addAll(tabContents)
            AnchorPane.setTopAnchor(tabContents, 0.0)
            AnchorPane.setRightAnchor(tabContents, 0.0)
            AnchorPane.setLeftAnchor(tabContents, 0.0)
            AnchorPane.setBottomAnchor(tabContents, 0.0)
            content = pane
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
