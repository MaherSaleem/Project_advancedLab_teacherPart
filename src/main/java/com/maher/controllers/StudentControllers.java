package com.maher.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import database.maherDatabase;
import objects.Student;

@RestController
@RequestMapping("/")
public class StudentControllers {

	Student notFoundStudnet = new Student(-1, "STUDENT_NOT_FOUND", "");
	Student ErrorStudnet = new Student(-5, "EXCEPTION", "");

	@RequestMapping(value = "/testMyApp", method = RequestMethod.GET)
	public String test() throws ClassNotFoundException {
		return "Awsome, its working";
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public ArrayList<Student> getAllStudents() throws ClassNotFoundException {

		ArrayList<Student> list = new ArrayList<Student>();
		try {
			maherDatabase db = new maherDatabase();
			Statement s = maherDatabase.getConnection();

			ResultSet rs = s.executeQuery("select * from " + db.STUDENT_TABLE);

			while (rs.next()) {
				int id = rs.getInt(db.STUDENT_COLUMN_ID);
				String name = rs.getString(db.STUDENT_COLUMN_NAME);
				String password = rs.getString(db.STUDENT_COLUMN_PASSWORD);
				Student studnet = new Student(id, name, password);
				list.add(studnet);
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			list = new ArrayList<Student>();// emptying the list
			list.add(ErrorStudnet);
			return list;
		}
	}

	/*
	 * get one student by id
	 * 
	 * @return json for one student
	 * 
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public Student handlePassedParams(@PathVariable("id") int id) {
		maherDatabase db;
		String result = "";
		try {
			db = new maherDatabase();
			Statement s = maherDatabase.getConnection();
			ResultSet rs = s
					.executeQuery("select * from " + db.STUDENT_TABLE + "  WHERE " + db.STUDENT_COLUMN_ID + " = " + id);
			if (!rs.next())
				return notFoundStudnet;
			else {
				String name = rs.getString(db.STUDENT_COLUMN_NAME);
				String password = rs.getString(db.STUDENT_COLUMN_PASSWORD);
				Student studnet = new Student(id, name, password);
				return studnet;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ErrorStudnet;//
	}

	/*
	 * adding new studnet
	 */
	@RequestMapping(value = "/students", method = RequestMethod.POST)
	public HashMap<String, String> addNewStudent(HttpServletRequest request) {
		
		HashMap<String, String> response = new HashMap<String, String>();
		try {
			maherDatabase db = new maherDatabase();
			
			// get the data from request
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter(db.STUDENT_COLUMN_NAME);
			String password = request.getParameter(db.STUDENT_COLUMN_PASSWORD);
			
			Student student = new Student(id , name , password);
			db.addStudent(student);
			response.put("msg", "student added successfully");
		} catch (Exception e) {
			response.put("msg", "failed");
			e.printStackTrace();
		}
		return response;

	}

}
