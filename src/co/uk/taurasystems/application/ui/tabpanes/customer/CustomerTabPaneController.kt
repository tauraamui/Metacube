package co.uk.taurasystems.application.ui.tabpanes.customer

import co.uk.taurasystems.application.ui.tabpanes.job.JobTab
import co.uk.taurasystems.db.models.Customer
import co.uk.taurasystems.db.models.Job
import co.uk.taurasystems.db.models.controllers.JobController
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.TabPane
import javafx.scene.control.TextField
import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import java.net.URL
import java.util.*

class CustomerTabPaneController {

    private var customer: Customer = Customer()
    private var jobList: ArrayList<Job> = ArrayList<Job>()
    internal val jobListItems = FXCollections.observableArrayList<String>()

    @FXML var firstNameField: TextField? = null
    @FXML var surnameField: TextField? = null
    @FXML var phoneNumberField: TextField? = null
    @FXML var addressFirstLineField: TextField? = null
    @FXML var jobTabPane: TabPane? = null
    @FXML var jobListView: ListView<String>? = null

    fun initialize(url: URL, resouceBundle: ResourceBundle?) {
        firstNameField?.text = customer.firstName
        surnameField?.text = customer.surname
        phoneNumberField?.text = customer.phoneNumber
        addressFirstLineField?.text = customer.addressFirstLine
        jobListView?.items = jobListItems
        jobListView?.setOnMouseClicked({ e -> onListClick(e) })
        jobTabPane?.tabClosingPolicy = TabPane.TabClosingPolicy.ALL_TABS
        updateJobList()
    }

    fun setCustomer(customer: Customer) {
        this.customer = customer
    }

    private fun openJobTab(job: Job) {
        val jobTab = JobTab(job)
        jobTabPane?.tabs?.add(jobTab)
        jobTabPane?.selectionModel?.select(jobTab)
    }

    fun openJobTab(job: Job, checkForAlreadyOpen: Boolean) {
        if (!checkForAlreadyOpen) {
            openJobTab(job)
            return
        }
        val existingTabs = jobTabPane!!.tabs
        for (currentTab in existingTabs) {
            if (currentTab is JobTab) {
                if (currentTab.job.ID == job.ID) {
                    jobTabPane!!.selectionModel.select(currentTab)
                    return
                }
            }
        }
        openJobTab(job)
    }

    fun updateJobList() {
        jobList = JobController.getJobsByCustomerID(customer.ID)
        jobListItems.clear()
        for (job in jobList) {
            jobListItems.add(job.ID.toString())
        }
    }

    fun onListClick(e: MouseEvent) {
        if (e.button == MouseButton.PRIMARY) {
            //if double clicked
            if (e.clickCount >= 2) {
                val selectionIndex = jobListView!!.selectionModel.selectedIndex
                if (selectionIndex < 0 || selectionIndex > jobList!!.size) return
                openJobTab(jobList!![selectionIndex], true)
            }
        }
    }
}