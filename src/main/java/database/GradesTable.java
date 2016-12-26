package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GradesTable {
	public static Connection connection = null;
	public static Statement statement;

	public static final String Grades_TABLE = "geades";

	public static final String Grades_COLUMN_STUDENT_ID = "stu_id";
	public static final String Grades_COLUMN_QUIZ_ID = "quiz_id";
	public static final String Grades_COLUMN_GRADE = "grade";

	public static final int MYSQL_DATABASE = 1;
	public static final int SQLITE_DATABASE = 2;

	public static int myDataBase = MYSQL_DATABASE;

	public GradesTable() throws ClassNotFoundException, SQLException {
		if (myDataBase == MYSQL_DATABASE) {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/advandedlab", "root", "");
			statement = connection.createStatement();

		} else {
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

	public ResultSet getAllGradesForQuiz(int quiz_id) {

		ResultSet rs = null;
		try {
			String Query = "SELECT stu_id ,s.name, g.grade FROM grades g , student s  WHERE g.stu_id = s.id AND g.quiz_id = " + quiz_id; 
			rs = statement.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}
	
	public ResultSet getAllGradesForStudent(int studentId) {

		ResultSet rs = null;
		try {
			String Query = "SELECT stu_id ,g.quiz_id, g.grade FROM grades g , student s  WHERE g.stu_id = s.id AND g.stu_id =  " + studentId; 
			rs = statement.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}

	

}
