package model;

public class PlayRecord {
	private String playerName;
	private String date;
	private int score = 0;

	public PlayRecord(String name, int score, String date){
		this.playerName = name;
		this.score = score;
		this.date = date;
	}

	public String getPlayerName() {return this.playerName;}
	public String getDate() {return this.date;}
	public int getScore() {return this.score;}

}
