package objects;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {
	int qid;
	String startTime;
	int duration ; //in seconds
	ArrayList<Question> questions;
}

