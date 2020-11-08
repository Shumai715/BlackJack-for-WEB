package model;

import java.util.Iterator;

public class Player extends Dealer{

	private int chip = 100;
	private int bet = 0;
	private boolean foldFlag = false;

	public int getChip() {return this.chip;}
	public int getBet() {return this.bet;}
	public boolean getFoldFlag(){return this.foldFlag;}

	public void setChip(int chip) {this.chip = chip;}
	public void setBet(int bet) {this.bet = bet;}
	public void setFoldFlag(boolean foldFlag) {this.foldFlag = foldFlag;}

	public Player() {
		setName("ゲスト");
	}
	public Player(String name) {
		setName(name);
	}

	public void reset(Deck graveDeck) {
		for(Iterator it = getHand().iterator(); it.hasNext();) {
			Card card = (Card)it.next();
			graveDeck.add(card);
		}
		getHand().clear();

		setTurnEndFlag(false);
		setExist1(false);
		setFoldFlag(false);
	}
}
