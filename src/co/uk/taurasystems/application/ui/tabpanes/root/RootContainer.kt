package co.uk.taurasystems.application.ui.tabpanes.root

import co.uk.taurasystems.application.Metacube
import co.uk.taurasystems.application.ui.tabpanes.customer.CustomerTab
import co.uk.taurasystems.db.models.Customer
import co.uk.taurasystems.db.models.controllers.CustomerController
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import tornadofx.View
import java.net.URL
import java.util.*

class RootContainer {

    @FXML private var fileMenuList: MenuItem? = null
    @FXML private var editMenuList: MenuItem? = null
    @FXML private var menuHelpList: MenuItem? = null

    @FXML private var menuNewButton: MenuItem? = null
    @FXML private var menuCloseButton: MenuItem? = null
    @FXML private var menuDeleteButton: MenuItem? = null
    @FXML private var menuAboutButton: MenuItem? = null

    @FXML private var customerTabPane: TabPane? = null
    @FXML private var customerListView: ListView<String>? = null
    @FXML private var splitPane: SplitPane? = null
    private var customerList: ArrayList<Customer> = ArrayList<Customer>()

    internal val customerListItems = FXCollections.observableArrayList<String>()

    fun initialize() {
        //		assert menuBar != null : "fx:id=\"menuBar\"was not injected: check your FXML file 'Root.fxml'.";
        //		assert menuCloseButton != null : "fx:id=\"menuCloseButton\"was not injected: check your FXML file 'Root.fxml'.";
        customerListView?.items = customerListItems
        menuCloseButton?.setOnAction({ e -> System.exit(0) })
        menuNewButton?.setOnAction({ e -> Metacube.loadNewDialog() })
        customerListView?.setOnMouseClicked({ e -> onListClick(e) })
        customerTabPane?.tabClosingPolicy = TabPane.TabClosingPolicy.ALL_TABS
        updateCustomerList()
    }

    private fun openCustomerTab(customer: Customer) {
        val customerTab = CustomerTab(customer)
        customerTabPane?.getTabs()?.add(customerTab)
        customerTabPane?.getSelectionModel()?.select(customerTab)
    }

    fun openCustomerTab(customer: Customer, checkForAlreadyOpen: Boolean) {
        if (!checkForAlreadyOpen) {
            openCustomerTab(customer)
            return
        } else {
            for (currentTab in customerTabPane?.tabs!!) {
                if (currentTab is CustomerTab) {
                    if (currentTab.customer.ID == customer.ID) {
                        customerTabPane?.getSelectionModel()!!.select(currentTab)
                        return
                    }
                }
            }
            openCustomerTab(customer)
        }
    }

    fun updateCustomerList() {
        customerList = CustomerController.sortAlphabetically(CustomerController.allCustomers)
        customerListItems.clear()
        for (customer in customerList) {
            customerListItems.add(customer.firstName + " " + customer.surname)
        }
    }

    fun onListClick(e: MouseEvent) {
        if (e.button == MouseButton.PRIMARY) {
            //if double clicked
            if (e.clickCount >= 2) {
                val selectionIndex = customerListView?.getSelectionModel()!!.getSelectedIndex()
                if (selectionIndex < 0 || selectionIndex > customerList.size) return
                openCustomerTab(customerList.get(selectionIndex), true)
            }
        } else if (e.button == MouseButton.SECONDARY) {
            val selectionIndex = customerListView?.getSelectionModel()!!.getSelectedIndex()
            println(selectionIndex)
        }
    }
}
