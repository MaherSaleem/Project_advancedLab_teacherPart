package objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public class Student {

	int id;
	String name;
	String password;
	
	@JsonCreator
	public Student(@JsonProperty("id")int id,@JsonProperty("name") String name,@JsonProperty("password") String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	public void toJson(){
		Gson gson = new Gson();
		String jsonInString = gson.toJson(this);
		System.out.println(jsonInString);


	}
	//https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
	public static void main(String[] args) {
		Student student = new Student(1130258, "Maher", "1");
		student.toJson();
	}
	
	
	
}
