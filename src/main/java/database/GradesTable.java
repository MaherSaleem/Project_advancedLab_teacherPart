package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	/*
	 * this methode is used to get all grades for some quiz
	 */
	public ResultSet getAllGradesForQuiz(int quiz_id) {

		ResultSet rs = null;
		try {
			String Query = "SELECT stu_id ,s.name, g.grade FROM grades g , student s  WHERE g.stu_id = s.id AND g.quiz_id = "
					+ quiz_id;
			rs = statement.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}

	/*
	 * this method is used to get all grades for some student
	 */
	public ResultSet getAllGradesForStudent(int studentId) {

		ResultSet rs = null;
		try {
			String Query = "SELECT stu_id ,g.quiz_id, g.grade FROM grades g , student s  WHERE g.stu_id = s.id AND g.stu_id =  "
					+ studentId;
			rs = statement.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}

	// this method is invoked when a teacher creates a new quiz
	// all grades are set to zero
	public void CreateGradeForAllStudents(int quiz_id) {

		ResultSet rs = null;
		try {
			String Query = "select id from " + StudentTable.STUDENT_TABLE; // id
																			// fro
																			// all
																			// students
			rs = statement.executeQuery(Query);
			ArrayList<Integer> ids = new ArrayList<Integer>();
			while (rs.next()) {
				ids.add(rs.getInt("id"));
			}
			for(Integer i : ids){
				System.out.println(i);
				Query = String.format("INSERT INTO grades VALUES ('%d', '%d', '0' , '0')", i, quiz_id);
				statement.executeUpdate(Query);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * this funtion used to set a grade for particaular student in particular quiz
	 */
	public void setGrade(int studentId , int QuizId , double grade){
		String query = String.format("UPDATE grades SET  grade='%.2f' , finished=1 WHERE stu_id = '%d' AND quiz_id = '%d'", grade,studentId , QuizId  );
		try {
			int affectedRows= statement.executeUpdate(query);
			System.out.println(affectedRows);
			if (affectedRows == 0){
				query = String.format("INSERT INTO grades VALUES ('%d', '%d', '%.2f' ,1)", studentId, QuizId , grade);
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
