package objects;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {
	int qid;
	String startTime;
	String duration ; //in seconds
	ArrayList<Question> questions;
	
	
	
	public Quiz(int qid, String startTime, String duration) {
		super();
		this.qid = qid;
		this.startTime = startTime;
		this.duration = duration;
	}
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}

