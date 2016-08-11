package co.uk.taurasystems.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Database {

    @JvmStatic var driverClassName: String? = null
    @JvmStatic var URL: String? = null
    @JvmStatic var useraname: String? = null
    @JvmStatic var password: String? = null

    private var connection: Connection? = null
    private var connected = false

    @JvmStatic fun initConnection(): Connection? {
        if (driverClassName == null || driverClassName!!.isEmpty()) {
            println("Driver class name not specified...")
            return null
        }
        if (URL == null || URL!!.isEmpty()) {
            println("Database URL not specified...")
            return null
        }
        if (useraname == null || useraname!!.isEmpty()) {
            println("Username not specified...")
            return null
        }
        if (password == null || password!!.isEmpty()) {
            println("Password not specified...")
            return null
        }

        try {
            Class.forName(driverClassName)
            connection = DriverManager.getConnection(URL!!, useraname, password)
            connected = true
            return getConnection()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return null
    }

    @JvmStatic fun getConnection(): Connection? {
        if (!connected) {
            println("No connection...")
            return null
        }
        return connection
    }

    @JvmStatic fun executeUpdate(updateStatement: String) {
        try {
            getConnection()!!.createStatement().executeUpdate(updateStatement)
        } catch (e: SQLException) {
            println("This query couldn't run: " + updateStatement)
            e.printStackTrace()
        }

    }
}
