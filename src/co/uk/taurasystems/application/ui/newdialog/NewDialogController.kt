package co.uk.taurasystems.application.ui.newdialog

import co.uk.taurasystems.application.Metacube
import co.uk.taurasystems.db.Database
import co.uk.taurasystems.db.Statement
import co.uk.taurasystems.db.models.Customer
import co.uk.taurasystems.db.models.controllers.CustomerController
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import java.net.URL
import java.util.*

class NewDialogController {

    private var newDialogStage: Stage = Stage()

    @FXML var anchorPane: AnchorPane? = null
    @FXML var okButton: Button? = null
    @FXML var cancelButton: Button? = null
    @FXML var firstNameTextField: TextField? = null
    @FXML var surnameTextField: TextField? = null
    @FXML var phoneNumberTextField: TextField? = null
    @FXML var addressFirstLineTextField: TextField? = null

    fun initialize(fxmlFileLocation: URL, resources: ResourceBundle?) {
        cancelButton?.setOnAction({ e -> newDialogStage.close() })
        okButton?.setOnAction({ e -> createCustomer() })
        firstNameTextField?.setOnKeyTyped({ e -> firstNameTextField?.style = "" })
        surnameTextField?.setOnKeyTyped({ e -> surnameTextField?.style = "" })
        firstNameTextField?.style = "-fx-prompt-text-fill: rgba(0, 0, 0, 0)"
        surnameTextField?.style = "-fx-prompt-text-fill: rgba(0, 0, 0, 0)"
    }

    fun setStage(newDialogStage: Stage) {
        this.newDialogStage = newDialogStage
    }

    private fun createCustomer() {
        if (validateFieldContents()) {
            var customer: Customer = Customer()
            customer.firstName = firstNameTextField?.text!!
            customer.surname = surnameTextField?.text!!
            customer.phoneNumber = phoneNumberTextField?.text!!
            customer.addressFirstLine = addressFirstLineTextField!!.text
            val insertStatement = Statement.getInsertStatement("customer",
                    arrayOf("firstname", "surname", "phonenumber", "addressfirstline"),
                    arrayOf("'" + customer.firstName + "'", "'" + customer.surname + "'", "'" + customer.phoneNumber + "'", "'" + customer.addressFirstLine + "'"))
            Database.executeUpdate(insertStatement)
            customer = CustomerController.getLatestOf(customer)
            if (customer != null) {
                newDialogStage.close()
                Metacube._rootController.openCustomerTab(customer, true)
                Metacube._rootController.updateCustomerList()
            }
        }
    }

    private fun validateFieldContents(): Boolean {
        var noBlankFields = true
        if (firstNameTextField?.text?.isEmpty()!!) {
            firstNameTextField?.style = "-fx-prompt-text-fill: rgba(255, 0, 0, 1)"
            noBlankFields = false
        }
        if (surnameTextField?.text?.isEmpty()!!) {
            surnameTextField?.style = "-fx-prompt-text-fill: rgba(255, 0, 0, 1)"
            noBlankFields = false
        }
        return noBlankFields
    }
}
