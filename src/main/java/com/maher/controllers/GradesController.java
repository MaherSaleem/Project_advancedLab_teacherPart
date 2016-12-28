package com.maher.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;

import database.GradesTable;
import database.QuizTable;
import objects.Question;
import objects.Quiz;

@RestController
@RequestMapping("/")
public class GradesController {

	// get all grades for paricular student
	@RequestMapping(value = "/grades/{student_id}", method = RequestMethod.GET)
	public String getGradesForAStudent(@PathVariable("student_id") int studentId) {
		GradesTable db;
		ArrayList<ObjectNode> list = new ArrayList<ObjectNode>();
		try {
			db = new GradesTable();
			ResultSet rs = db.getAllGradesForStudent(studentId);

			while (rs.next()) {
				ObjectNode node = new ObjectMapper().createObjectNode();
				node.put("stu_id", rs.getString("stu_id"));
				node.put("quiz_id", rs.getString("quiz_id"));
				node.put("grade", rs.getString("grade"));
				list.add(node);
			}
			return list.toString(); // this is proper JSON
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * set grade for particular student for particular quiz
	 */
	@RequestMapping(value = "/grades/set_grade", method = RequestMethod.POST)
	public HashMap<String, String> addNewQuiz(HttpServletRequest request) {

		HashMap<String, String> response = new HashMap<String, String>();
		try {
			GradesTable db = new GradesTable();
			// get the data from request
			int stuId= Integer.parseInt(request.getParameter(db.Grades_COLUMN_STUDENT_ID));
			int quizId = Integer.parseInt(request.getParameter(db.Grades_COLUMN_QUIZ_ID));
			Double grade = Double.parseDouble(request.getParameter(db.Grades_COLUMN_GRADE));
			System.out.println(stuId + " " + quizId + " " + grade);

			db.setGrade(stuId, quizId, grade);
			response.put("msg", "grade change successfully");
		} catch (Exception e) {
			response.put("msg", "failed to change the grade");
			e.printStackTrace();
		}
		return response;

	}

	/*
	 * get all grades for particular quiz
	 */
	@RequestMapping(value = "/grades/quiz/{quiz_id}", method = RequestMethod.GET)
	public String getGradesForAQuiz(@PathVariable("quiz_id") int quizId) {
		GradesTable db;
		ArrayList<ObjectNode> list = new ArrayList<ObjectNode>();

		try {
			db = new GradesTable();
			ResultSet rs = db.getAllGradesForQuiz(quizId);

			while (rs.next()) {
				ObjectNode node = new ObjectMapper().createObjectNode();
				node.put("stu_id", rs.getString("stu_id"));
				node.put("stu_name", rs.getString("name"));
				node.put("grade", rs.getString("grade"));
				list.add(node);
			}
			return list.toString(); // this is proper JSON
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
