package co.uk.taurasystems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static String _DriverClassName;
	private static String _URL;
	private static String _Username;
	private static String _Password;
	
	private static Connection connection;
	private static boolean connected = false;
	
	public static String getDriverClassName() {
		return _DriverClassName;
	}
	public static void setDriverClassName(String driverClassName) {
		_DriverClassName = driverClassName;
	}
	public static String getURL() {
		return _URL;
	}
	public static void setURL(String URL) {
		_URL = URL;
	}
	public static String getUseraname() {
		return _Username;
	}
	public static void setUseraname(String useraname) {
		_Username = useraname;
	}
	public static String getPassword() {
		return _Password;
	}
	public static void setPassword(String password) {
		_Password = password;
	}
	
	public static Connection initConnection() {
		if (_DriverClassName == null || _DriverClassName.isEmpty()) {System.out.println("Driver class name not specified..."); return null;}
		if (_URL == null || _URL.isEmpty()) {System.out.println("Database URL not specified..."); return null;}
		if (_Username == null || _Username.isEmpty()) {System.out.println("Username not specified..."); return null;}
		if (_Password == null || _Password.isEmpty()) {System.out.println("Password not specified..."); return null;}

		try {
			Class.forName(_DriverClassName);
			connection = DriverManager.getConnection(_URL, _Username, _Password);
			connected = true;
			return getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Connection getConnection() {
		if (!connected) { System.out.println("No connection..."); return null; }
		return connection;
	}
	
	public static void executeUpdate(String updateStatement) {
		try {
			getConnection().createStatement().executeUpdate(updateStatement);
		} catch (SQLException e) {
			System.out.println("This query couldn't run: "+updateStatement);
			e.printStackTrace();
		}
	}
}
