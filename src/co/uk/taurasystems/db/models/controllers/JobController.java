package co.uk.taurasystems.db.models.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.uk.taurasystems.db.Database;
import co.uk.taurasystems.db.models.Job;

public class JobController {

	public static ArrayList<Job> getAllJobs() {
		ArrayList<Job> jobs =  null;
		try {
			ResultSet results = Database.getConnection().createStatement().executeQuery("SELECT * FROM job");
			jobs = new ArrayList<Job>();
			while (results.next()) {
				Job job = mapResultSetToJob(results);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public static ArrayList<Job> getJobsByCustomerID(long customerIDToFind) {
		ArrayList<Job> jobs = null;
		try {
			ResultSet results = Database.getConnection().createStatement().executeQuery("SELECT * FROM job where customerid="+String.valueOf(customerIDToFind));
			jobs = new ArrayList<Job>();
			while (results.next()) {
				Job job = mapResultSetToJob(results);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public static ArrayList<Job> getJobByID(long jobIDToFind) {
		ArrayList<Job> jobs = null;
		try {
			ResultSet results = Database.getConnection().createStatement().executeQuery("SELECT * FROM job WHERE ID = "+String.valueOf(jobIDToFind));
			jobs = new ArrayList<Job>();
			while (results.next()) {
				Job job = mapResultSetToJob(results);
				jobs.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobs;
	}

	public static String getCreationStruct() {
		String creationStruct = "CREATE TABLE JOB" +
								"(ID BIGSERIAL NOT NULL," +
								"CUSTOMERID BIGINT REFERENCES CUSTOMER(ID) ON DELETE CASCADE," +
								"DATE VARCHAR(50)," +
								"TIME VARCHAR(50)," +
								"DEVICENAME VARCHAR(100)," +
								"BAGORCASE INTEGER," +
								"DESCRIPTION VARCHAR(600)," +
								"PRICE VARCHAR(100)," +
								"PRIMARY KEY (ID, CUSTOMERID)" +
								");";
		return creationStruct;
	}
	
	private static Job mapResultSetToJob(ResultSet results) {
		Job job = null;
		try {
			long ID = results.getLong("ID");
			long customerID = results.getLong("customerid");
			String date = results.getString("date");
			String time = results.getString("time");
//		Priority priority = (Priority)results.getObject("priority");
			String deviceName = results.getString("devicename");
			boolean bagOrCase = results.getBoolean("bagorcase");
			String description = results.getString("description");
			float price = results.getFloat("price");
			job = new Job();
			job.setID(ID);
			job.setCustomerID(customerID);
			job.setDate(date);
			job.setTime(time);
//		job.setPriority(priority);
			job.setDeviceName(deviceName);
			job.setBagOrCase(bagOrCase);
			job.setDescription(description);
			job.setPrice(price);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return job;
	}
}
