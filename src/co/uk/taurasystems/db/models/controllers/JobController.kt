package co.uk.taurasystems.db.models.controllers

import co.uk.taurasystems.db.Database
import co.uk.taurasystems.db.models.Job
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

object JobController {

    @JvmStatic val allJobs: ArrayList<Job>
        get() {
            var jobs: ArrayList<Job> = ArrayList<Job>()
            try {
                val results = Database.getConnection()!!.createStatement().executeQuery("SELECT * FROM job")
                jobs = ArrayList<Job>()
                while (results.next()) {
                    val job = mapResultSetToJob(results)
                    jobs.add(job)
                }
            } catch (e: SQLException) {
                e.printStackTrace()
            }
            return jobs
        }

    @JvmStatic fun getJobsByCustomerID(customerIDToFind: Long): ArrayList<Job> {
        var jobs: ArrayList<Job> = ArrayList<Job>()
        try {
            val results = Database.getConnection()!!.createStatement().executeQuery("SELECT * FROM job where customerid=" + customerIDToFind.toString())
            jobs = ArrayList<Job>()
            while (results.next()) {
                val job = mapResultSetToJob(results)
                jobs.add(job)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return jobs
    }

    @JvmStatic  fun getJobByID(jobIDToFind: Long): ArrayList<Job> {
        var jobs: ArrayList<Job> = ArrayList<Job>()
        try {
            val results = Database.getConnection()!!.createStatement().executeQuery("SELECT * FROM job WHERE ID = " + jobIDToFind.toString())
            jobs = ArrayList<Job>()
            while (results.next()) {
                val job = mapResultSetToJob(results)
                jobs.add(job)
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return jobs
    }

    @JvmStatic val creationStruct: String
        get() {
            val creationStruct = "CREATE TABLE JOB" +
                    "(ID BIGSERIAL NOT NULL," +
                    "CUSTOMERID BIGINT REFERENCES CUSTOMER(ID) ON DELETE CASCADE," +
                    "DATE VARCHAR(50)," +
                    "TIME VARCHAR(50)," +
                    "DEVICENAME VARCHAR(100)," +
                    "BAGORCASE INTEGER," +
                    "DESCRIPTION VARCHAR(600)," +
                    "PRICE VARCHAR(100)," +
                    "PRIMARY KEY (ID, CUSTOMERID)" +
                    ");"
            return creationStruct
        }

    private fun mapResultSetToJob(results: ResultSet): Job {
        var job: Job = Job()
        try {
            val ID = results.getLong("ID")
            val customerID = results.getLong("customerid")
            val date = results.getString("date")
            val time = results.getString("time")
            //		Priority priority = (Priority)results.getObject("priority");
            val deviceName = results.getString("devicename")
            val bagOrCase = results.getBoolean("bagorcase")
            val description = results.getString("description")
            val price = results.getFloat("price")
            job = Job()
            job.ID = ID
            job.customerID = customerID
            job.date = date
            job.time = time
            //		job.setPriority(priority);
            job.deviceName = deviceName
            job.isBagOrCase = bagOrCase
            job.description = description
            job.price = price
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return job
    }
}
