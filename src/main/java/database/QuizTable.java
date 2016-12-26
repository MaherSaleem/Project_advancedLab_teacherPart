package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Question;

public class QuizTable {
	public static Connection connection = null;
	public static Statement statement;

	public static final String Quiz_TABLE = "quiz";
	public static final String QUESTIONS_TABLE = "questions";

	public static final String Quiz_COLUMN_ID = "qid";
	public static final String Quiz_COLUMN_START_TIME = "s_time";
	public static final String Quiz_COLUMN_DURATION = "duration";
	
	public static final String QUESTIONS_COLUMN_QUIZ_ID = "quiz_id";
	public static final String QUESTIONS_COLUMN_QUESTION_NUMBER = "qNum";
	public static final String QUESTIONS_COLUMN_QUESTION = "question";
	public static final String QUESTIONS_COLUMN_ANS1 = "ans1";
	public static final String QUESTIONS_COLUMN_ANS2 = "ans2";
	public static final String QUESTIONS_COLUMN_ANS3 = "ans3";
	public static final String QUESTIONS_COLUMN_CORRECT = "correct_ans";


	public static final int MYSQL_DATABASE = 1;
	public static final int SQLITE_DATABASE = 2;

	public static int myDataBase = MYSQL_DATABASE;

	public QuizTable() throws ClassNotFoundException, SQLException {
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
	
	public ResultSet getAllQuestions(int quiz_id){
		
		ResultSet rs = null;
		try {
			String Query = "SELECT * FROM "+ QUESTIONS_TABLE+" WHERE "+QUESTIONS_COLUMN_QUIZ_ID+ " = " + quiz_id;
			rs = statement.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;

	}
	
	public void addQuestion(Question q){
		String query = String.format
				("INSERT INTO questions  VALUES ('%d', '%d', '%s', '%s', '%s', '%s', '%d')", 
						q.getQuizId(),q.getqNum(),q.getQuestion(),q.getAns1(),q.getAns2(),q.getAns3(),q.getCorrectAns());
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
