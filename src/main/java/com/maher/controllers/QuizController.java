package com.maher.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import database.GradesTable;
import database.QuizTable;
import database.StudentTable;
import objects.Student;
import objects.Question;
import objects.Quiz;

@RestController
@RequestMapping("/")
public class QuizController {

	/*
	 * get all question for one quiz
	 */
	@RequestMapping(value = "/quizes/{id}", method = RequestMethod.GET)
	public ArrayList<Question> getQuestionForAQuiz(@PathVariable("id") int id) {
		QuizTable db;
		ArrayList<Question> list = new ArrayList<Question>();
		try {
			db = new QuizTable();
			Statement s = QuizTable.getConnection();
			ResultSet rs = db.getAllQuestions(id);
			while (rs.next()) {
				Question q = new Question(rs.getInt(QuizTable.QUESTIONS_COLUMN_QUIZ_ID),
						rs.getInt(QuizTable.QUESTIONS_COLUMN_QUESTION_NUMBER),
						rs.getString(QuizTable.QUESTIONS_COLUMN_QUESTION),
						rs.getString(QuizTable.QUESTIONS_COLUMN_ANS1), rs.getString(QuizTable.QUESTIONS_COLUMN_ANS2),
						rs.getString(QuizTable.QUESTIONS_COLUMN_ANS3), rs.getInt(QuizTable.QUESTIONS_COLUMN_CORRECT));
				list.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	/*
	 * get all quizes
	 */
	@RequestMapping(value = "/quizes", method = RequestMethod.GET)
	public ArrayList<Quiz> getAllQuizes() throws ClassNotFoundException {

		ArrayList<Quiz> list = new ArrayList<Quiz>();
		try {
			QuizTable db = new QuizTable();
			Statement s = QuizTable.getConnection();

			ResultSet rs = s.executeQuery("select * from " + db.Quiz_TABLE);

			while (rs.next()) {
				int qid = rs.getInt(db.Quiz_COLUMN_ID);
				String startTime = rs.getString(db.Quiz_COLUMN_START_TIME);
				String Duration = rs.getString(db.Quiz_COLUMN_DURATION);
				Quiz quiz = new Quiz(qid, startTime, Duration);
				list.add(quiz);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * get all quiz info
	 */
	@RequestMapping(value = "/quizes/info/{id}", method = RequestMethod.GET)
	public Quiz getQuizInfo(@PathVariable("id") int id) {
		QuizTable db;
		Quiz q = null;
		try {
			db = new QuizTable();
			Statement s = QuizTable.getConnection();
			System.out.println("Select * from "+db.Quiz_TABLE + " WHERE " + db.Quiz_COLUMN_ID +" = "  +id);
			ResultSet rs = s.executeQuery("Select * from "+db.Quiz_TABLE + " WHERE " + db.Quiz_COLUMN_ID +" = "  +id );
			while (rs.next()) {
				System.out.println("YAA");
				q = new Quiz(rs.getInt(QuizTable.Quiz_COLUMN_ID),
						rs.getString(db.Quiz_COLUMN_START_TIME ) ,
						rs.getString(db.Quiz_COLUMN_DURATION));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;

	}

	/*
	 * add new qustion for particular quiz
	 */
	@RequestMapping(value = "/quizes/qustion", method = RequestMethod.POST)
	public HashMap<String, String> addNewQuestion(HttpServletRequest request) {

		HashMap<String, String> response = new HashMap<String, String>();
		try {
			QuizTable db = new QuizTable();

			Question q = new Question(Integer.parseInt(request.getParameter(QuizTable.QUESTIONS_COLUMN_QUIZ_ID)),
					Integer.parseInt(request.getParameter(QuizTable.QUESTIONS_COLUMN_QUESTION_NUMBER)),
					request.getParameter(QuizTable.QUESTIONS_COLUMN_QUESTION),
					request.getParameter(QuizTable.QUESTIONS_COLUMN_ANS1),
					request.getParameter(QuizTable.QUESTIONS_COLUMN_ANS2),
					request.getParameter(QuizTable.QUESTIONS_COLUMN_ANS3),
					Integer.parseInt(request.getParameter(QuizTable.QUESTIONS_COLUMN_CORRECT)));
			db.addQuestion(q);
			response.put("msg", "Question added successfully");
		} catch (Exception e) {
			response.put("msg", "failed");
			e.printStackTrace();
		}
		return response;

	}

	/*
	 * add new quiz
	 */
	@RequestMapping(value = "/quizes", method = RequestMethod.POST)
	public HashMap<String, String> addNewQuiz(HttpServletRequest request) {

		HashMap<String, String> response = new HashMap<String, String>();
		try {
			QuizTable db = new QuizTable();
			GradesTable db2 = new GradesTable();
			// get the data from request
			int quiz_id = Integer.parseInt(request.getParameter("qid"));
			String startTime = request.getParameter(db.Quiz_COLUMN_START_TIME);
			String duration = request.getParameter(db.Quiz_COLUMN_DURATION);
			System.out.println(quiz_id + " " + startTime + " " + duration);
			Quiz quiz = new Quiz(quiz_id, startTime, duration);

			db.addQuiz(quiz);
			db2.CreateGradeForAllStudents(quiz_id);
			response.put("msg", "Quiz added successfully");
		} catch (Exception e) {
			response.put("msg", "failed to add the quiz");
			e.printStackTrace();
		}
		return response;

	}

	/*
	 * get all active quizes(but assuming there is one active quiz)
	 */
	@RequestMapping(value = "/quizes/active/{stu_id}", method = RequestMethod.GET)
	public ArrayList<Quiz> getActiveQuizes(@PathVariable("stu_id") int stu_id) throws ClassNotFoundException {

		ArrayList<Quiz> list = new ArrayList<Quiz>();
		try {
			QuizTable db = new QuizTable();
			Statement s = QuizTable.getConnection();

			ResultSet rs = s.executeQuery(
					"SELECT q.qid,q.s_time,q.duration from quiz q , grades g"
					+ " WHERE q.qid=g.quiz_id AND "
					+ "g.stu_id="+stu_id +" AND"
					+ " g.finished=0 "
					+ "AND CURRENT_TIMESTAMP() >q.s_time AND CURRENT_TIMESTAMP() < q.duration");

			while (rs.next()) {
				int qid = rs.getInt(db.Quiz_COLUMN_ID);
				String startTime = rs.getString(db.Quiz_COLUMN_START_TIME);
				String Duration = rs.getString(db.Quiz_COLUMN_DURATION);
				Quiz quiz = new Quiz(qid, startTime, Duration);
				list.add(quiz);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * get list of upcoming quizes
	 */
	@RequestMapping(value = "/quizes/upcomming", method = RequestMethod.GET)
	public ArrayList<Quiz> getPendingQuizes() throws ClassNotFoundException {

		ArrayList<Quiz> list = new ArrayList<Quiz>();
		try {
			QuizTable db = new QuizTable();
			Statement s = QuizTable.getConnection();

			ResultSet rs = s.executeQuery("SELECT * from quiz q WHERE CURRENT_TIMESTAMP() <q.s_time ");

			while (rs.next()) {
				int qid = rs.getInt(db.Quiz_COLUMN_ID);
				String startTime = rs.getString(db.Quiz_COLUMN_START_TIME);
				String Duration = rs.getString(db.Quiz_COLUMN_DURATION);
				Quiz quiz = new Quiz(qid, startTime, Duration);
				list.add(quiz);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// get remaining time for particular quiz
	@RequestMapping(value = "/quizes/remainingTime/{quiz_id}", method = RequestMethod.GET)
	public String getRemainingTime(@PathVariable("quiz_id") int quiz_id) {
		String remainingTime = "ERROR";
		try {
			QuizTable db = new QuizTable();
			remainingTime = db.getRemainingTimeForQuiz(quiz_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return remainingTime;

	}

	// Deactivating a Quiz
	@RequestMapping(value = "/quizes/deactivate/{quiz_id}", method = RequestMethod.GET)
	public HashMap<String, String> deactivateQuiz(@PathVariable("quiz_id") int quiz_id) {
		HashMap<String, String> response = new HashMap<String, String>();

		try {
			QuizTable db = new QuizTable();
			db.DeactivateQuiz(quiz_id);
			response.put("status", "deactivated Successfully");
		} catch (Exception e) {
			response.put("status", "Error in deactivating");
			e.printStackTrace();
		}
		return response;

	}
	
	// Extends Time for quiz
	@RequestMapping(value = "/quizes/extends", method = RequestMethod.POST)
	public HashMap<String, String> extendsquiz(HttpServletRequest request) {
		int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
		String newTime = request.getParameter("newTime");
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			QuizTable db = new QuizTable();
			db.extendsQuiz(quiz_id, newTime);
			response.put("status", "Changed Successfully");
		} catch (Exception e) {
			response.put("status", "Error in Changed");
			e.printStackTrace();
		}
		return response;

	}


}
