package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objects.*;

public class StudentTable {

	public static Connection connection = null;
	public static Statement statement;

	public static final String STUDENT_TABLE = "student";
	public static final String STUDENT_COLUMN_ID = "id";
	public static final String STUDENT_COLUMN_NAME = "name";
	public static final String STUDENT_COLUMN_PASSWORD = "password";

	public static final int MYSQL_DATABASE = 1;
	public static final int SQLITE_DATABASE = 2;
	
	public static int myDataBase = MYSQL_DATABASE;
	public StudentTable() throws ClassNotFoundException, SQLException {
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
		if(myDataBase == MYSQL_DATABASE){
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advandedlab", "root", "");
			statement = connection.createStatement();
			return statement;
		}
		else{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
			statement = connection.createStatement();
			return statement;
		}

	}

	public void createTables() {

		try {
			statement.executeUpdate("drop table if exists "+ STUDENT_TABLE);
			statement.executeUpdate("create table student ("+STUDENT_COLUMN_ID+" integer, "+STUDENT_COLUMN_NAME+" string , "+STUDENT_COLUMN_PASSWORD+" String)");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("failed to create tables");
		}
	}

	public boolean addStudent(Student s) throws Exception {
		int id = s.getId();
		String name = s.getName();
		String password = s.getPassword();

		int result = 0;
			String inseartQuery = String.format("insert into "+STUDENT_TABLE+" values(%d, '%s' , '%s')", id, name, password);
			System.out.println(inseartQuery);
			result = statement.executeUpdate(inseartQuery);
			System.out.println("failed to add stuednt");

		
		return result != -1;
	}


//	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		maherDatabase db = new maherDatabase();
////		db.createTables();
////		Student student = new Student(1130258, "Maher", "1");
////		db.addStudent(student);
//		ResultSet rs = statement.executeQuery("select * from " + STUDENT_TABLE);
//		while (rs.next()) {
//			// read the result set
//			System.out.println("name = " + rs.getString(STUDENT_COLUMN_NAME));
//			System.out.println("id = " + rs.getInt(STUDENT_COLUMN_ID));
//			System.out.println("password = " + rs.getInt(STUDENT_COLUMN_PASSWORD));
//			System.out.println("============");
//
//		}
////
//	}
	
	
	
}