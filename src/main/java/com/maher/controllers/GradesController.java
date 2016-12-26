package com.maher.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;

import database.GradesTable;
import objects.Question;

@RestController
@RequestMapping("/")
public class GradesController {

	
	@RequestMapping(value = "/grades/{student_id}", method = RequestMethod.GET)
	public String getQuestionForAQuiz(@PathVariable("student_id") int studentId) {
		GradesTable db;
		try {
			db = new GradesTable();
			ResultSet rs = db.getAllGradesForStudent(studentId);
			
			ObjectNode node = new ObjectMapper().createObjectNode();
			rs.next();
			node.put("stu_id", rs.getString("stu_id"));
			node.put("quiz_id" , rs.getString("quiz_id"));
			node.put("grade", rs.getString("grade"));
			return node.toString(); // this is proper JSON
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

