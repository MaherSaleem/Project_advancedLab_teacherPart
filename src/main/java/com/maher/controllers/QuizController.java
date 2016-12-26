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
	 * add new qustion for particular quiz
	 */
	@RequestMapping(value = "/quizes/qustion", method = RequestMethod.POST)
	public HashMap<String, String> addNewQuestion(HttpServletRequest request) {

		HashMap<String, String> response = new HashMap<String, String>();
		try {
			QuizTable db = new QuizTable();

			Question q = new Question(
					Integer.parseInt(request.getParameter(QuizTable.QUESTIONS_COLUMN_QUIZ_ID)),
					Integer.parseInt(request.getParameter(QuizTable.QUESTIONS_COLUMN_QUESTION_NUMBER)),
					request.getParameter(QuizTable.QUESTIONS_COLUMN_QUESTION), 
					request.getParameter(QuizTable.QUESTIONS_COLUMN_ANS1),
					request.getParameter(QuizTable.QUESTIONS_COLUMN_ANS2),
					request.getParameter(QuizTable.QUESTIONS_COLUMN_ANS3),
					Integer.parseInt(request.getParameter(QuizTable.QUESTIONS_COLUMN_CORRECT)));
			db.addQuestion(q);
			response.put("msg", "student added successfully");
		} catch (Exception e) {
			response.put("msg", "failed");
			e.printStackTrace();
		}
		return response;

	}

}
