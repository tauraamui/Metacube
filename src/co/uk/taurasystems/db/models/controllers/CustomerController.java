package co.uk.taurasystems.db.models.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import co.uk.taurasystems.db.Database;
import co.uk.taurasystems.db.models.Customer;

public class CustomerController {

	public static ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customers =  new ArrayList<Customer>();
		try {
			ResultSet results = Database.getConnection().createStatement().executeQuery("SELECT * FROM customer");
			customers = new ArrayList<Customer>();
			while (results.next()) {
				long ID = (long)results.getObject("ID");
				String firstname = (String)results.getObject("firstname");
				String surname = (String)results.getObject("surname");
				String phonenumber = (String)results.getObject("phonenumber");
				String addressfirstline = (String)results.getObject("addressfirstline");
				if (firstname == null) firstname = "";
				if (surname == null) surname = "";
				if (phonenumber == null) phonenumber = "";
				if (addressfirstline == null) addressfirstline = "";
				customers.add(new Customer(ID, firstname, surname, phonenumber, addressfirstline));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public static ArrayList<Customer> sortAlphabetically(ArrayList<Customer> customers) {
		Collections.sort(customers, new Comparator<Customer>() {
		        @Override
		        public int compare(Customer customer1, Customer  customer2) {
		            return  customer1.getFirstName().compareTo(customer2.getFirstName());
		        }
		});
		return customers;
	}
	
	public static String getCreationStruct() {
		String creationStruct = "CREATE TABLE CUSTOMER" +
								"(ID BIGSERIAL NOT NULL PRIMARY KEY," +
								"FIRSTNAME VARCHAR(50)," +
								"SURNAME VARCHAR(50)," +
								"PHONENUMBER VARCHAR(50)," +
								"ADDRESSFIRSTLINE VARCHAR(100)" +
								");";
		return creationStruct;
	}
}
