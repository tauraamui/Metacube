package co.uk.taurasystems.db.models.controllers

import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator

import co.uk.taurasystems.db.Database
import co.uk.taurasystems.db.models.Customer

import javax.xml.crypto.Data
import javax.xml.transform.Result

object CustomerController {

    @JvmStatic val allCustomers: ArrayList<Customer>
        get() {
            var customers = ArrayList<Customer>()
            try {
                val results = Database.getConnection()!!.createStatement().executeQuery("SELECT * FROM customer")
                customers = ArrayList<Customer>()
                while (results.next()) {
                    val ID = results.getObject("ID") as Long
                    var firstName: String? = results.getObject("firstname") as String
                    var surname: String? = results.getObject("surname") as String
                    var phonenumber: String? = results.getObject("phonenumber") as String
                    var addressfirstline: String? = results.getObject("addressfirstline") as String
                    if (firstName == null) firstName = ""
                    if (surname == null) surname = ""
                    if (phonenumber == null) phonenumber = ""
                    if (addressfirstline == null) addressfirstline = ""
                    customers.add(Customer(ID, firstName, surname, phonenumber, addressfirstline))
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            return customers
        }

    @JvmStatic fun getLatestOf(customer: Customer) : Customer {
        var latestCustomerVer = customer
        try {
            val results = Database.getConnection()!!.createStatement().executeQuery("SELECT * FROM CUSTOMER WHERE ID = " + customer.ID.toString())
            while (results.next()) {
                val ID = results.getObject("ID") as Long
                var firstName: String? = results.getObject("firstname") as String
                var surname: String? = results.getObject("surname") as String
                var phonenumber: String? = results.getObject("phonenumber") as String
                var addressfirstline: String? = results.getObject("addressfirstline") as String
                if (firstName == null) firstName = ""
                if (surname == null) surname = ""
                if (phonenumber == null) phonenumber = ""
                if (addressfirstline == null) addressfirstline = ""
                latestCustomerVer = Customer(ID, firstName, surname, phonenumber, addressfirstline)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return latestCustomerVer
    }

    @JvmStatic val getLatestOf: Customer
        get() {
            var customer = Customer()

            return customer
        }

    /*
    @JvmStatic fun getLatestOf(customer: Customer): Customer {
        println("getting latest of Customer: " + customer.firstName + " " + customer.surname)
        try {
            val result = Database.getConnection()!!.createStatement().executeQuery("SELECT * FROM CUSTOMER WHERE ID = " + customer.ID.toString())
            while (result.next()) {
                val firstname: String? = result.getObject("firstname") as String
                val surname: String? = result.getObject("surname") as String
                val phonenumber: String? = result.getObject("phonenumber") as String
                val addressFirstLine = result.getObject("addressfirstline") as String
                customer.firstName = firstname
                customer.surname = surname
                customer.phoneNumber = phonenumber
                customer.addressFirstLine = addressFirstLine
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            return Customer()
        }

        return customer
    }
    */

    @JvmStatic fun sortAlphabetically(customers: ArrayList<Customer>): ArrayList<Customer> {
        Collections.sort(customers) { customer1, customer2 -> customer1.firstName.compareTo(customer2.firstName) }
        return customers
    }

    @JvmStatic val creationStruct: String
        get() {
            val creationStruct = "CREATE TABLE CUSTOMER" +
                    "(ID BIGSERIAL NOT NULL PRIMARY KEY," +
                    "FIRSTNAME VARCHAR(50)," +
                    "SURNAME VARCHAR(50)," +
                    "PHONENUMBER VARCHAR(50)," +
                    "ADDRESSFIRSTLINE VARCHAR(100)" +
                    ");"
            return creationStruct
        }
}
