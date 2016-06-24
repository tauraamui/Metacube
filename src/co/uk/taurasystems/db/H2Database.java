package co.uk.taurasystems.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database {

	private static String DriverClassName;
	private static String URL;
	private static String Username;
	private static String Password;
	
	private static Connection connection;
	private static boolean connected = false;
	
	public static String getDriverClassName() {
		return DriverClassName;
	}
	public static void setDriverClassName(String driverClassName) {
		DriverClassName = driverClassName;
	}
	public static String getURL() {
		return URL;
	}
	public static void setURL(String uRL) {
		URL = uRL;
	}
	public static String getUseraname() {
		return Username;
	}
	public static void setUseraname(String useraname) {
		Username = useraname;
	}
	public static String getPassword() {
		return Password;
	}
	public static void setPassword(String password) {
		Password = password;
	}
	
	public static Connection initConnection() {
		if (DriverClassName == null || DriverClassName.isEmpty()) {System.out.println("Driver class name not specified..."); return null;}
		if (URL == null || URL.isEmpty()) {System.out.println("Driver class name not specified..."); return null;}
		if (Username == null || Username.isEmpty()) {System.out.println("Username not specified..."); return null;}
		if (Password == null || Password.isEmpty()) {System.out.println("Password not specified..."); return null;}
		
		try {
			Class.forName(DriverClassName);
			connection = DriverManager.getConnection(URL, Username, Password);
			// add application code here
			connected = true;
			return getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			e.printStackTrace();
		}
	}
}
