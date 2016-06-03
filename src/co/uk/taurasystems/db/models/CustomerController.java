package co.uk.taurasystems.db.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.uk.taurasystems.db.H2Database;

public class CustomerController {

	public static ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customers =  null;
		try {
			ResultSet results = H2Database.getConnection().createStatement().executeQuery("SELECT * FROM customer");
			customers = new ArrayList<Customer>();
			while (results.next()) {
				long ID = (long)results.getObject("ID");
				String firstname = (String)results.getObject("firstname");
				String surname = (String)results.getObject("surname");
				String phonenumber = (String)results.getObject("phonenumber");
				customers.add(new Customer(ID, firstname, surname, phonenumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
}
