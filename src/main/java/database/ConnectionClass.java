package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionClass {
	
	public static Connection connection = null;
	public static Statement statement;

	public static final int MYSQL_DATABASE = 1;
	public static final int SQLITE_DATABASE = 2;

	public static int myDataBase = MYSQL_DATABASE;

	public ConnectionClass() throws ClassNotFoundException, SQLException {
		if(myDataBase == MYSQL_DATABASE){
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advandedlab", "root", "");
			statement = connection.createStatement();

		}
		else{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			statement = connection.createStatement();
		}

	}

	public static Statement getConnection() throws ClassNotFoundException, SQLException {
		if (myDataBase == MYSQL_DATABASE) {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advandedlab", "root", "");
			statement = connection.createStatement();
			return statement;
		} else {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			statement = connection.createStatement();
			return statement;
		}

	}
}
