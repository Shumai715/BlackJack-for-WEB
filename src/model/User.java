package model;

import java.io.Serializable;

public class User implements Serializable{
	private String name;
	private String pass;
	private int maxScore;

	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public User(String name, int maxScore) {
		this.name = name;
		this.maxScore = maxScore;
	}

	public String getName() {return name;}
	public String getPass() {return pass;}
	public int getMaxScore() {return maxScore;}

	public void setMaxScore(int score) {
		this.maxScore = score;
	}

}
