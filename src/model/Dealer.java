package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dealer implements Serializable{
	private String name;
	private List<Card> hand;
	private int handSum = 0;
	private boolean exist1 = false;  //手札に１があるかどうか。（特別な計算の必要性の有無）
	private boolean turnEndFlag = false; //ターン終了のフラグ


	public Dealer(){
		this.name = "ディーラー";
		hand = new ArrayList<>();
	}

	public String getName() {return this.name;}
	public List<Card> getHand() {return this.hand;}
	public int getHandSum() {return this.handSum;}
	public boolean getExist1() {return this.exist1;}
	public boolean getTurnEndFlag() {return this.turnEndFlag;}

	public void setName(String name) {this.name = name;}
	public void setHand(List <Card> hand) {this.hand = hand;}
	public void setHandSum(int handSum) {this.handSum = handSum;}
	public void setExist1(boolean exist1) {this.exist1 = exist1;}
	public void setTurnEndFlag(boolean turnEndFlag) {this.turnEndFlag = turnEndFlag;}

	//ゲーム開始時の1枚目
	public void start(Deck deck, Deck graveDeck) {
		if(deck.getSize() == 1) {
			deck.setDeckEndFlag(true);
		}

		if(deck.isEmpty() == true) {
			graveDeck.shuffle();
			Card card = graveDeck.getCard();
			hand.add(card);
			if(card.numberInt == 1) {
				this.exist1 = true;
			}
			graveDeck.remove();
		}else {
			Card card = deck.getCard();
			hand.add(card);
			if(card.numberInt == 1) {
				this.exist1 = true;
			}
			deck.remove();
		}
	}


	//手札を引く
	public void draw(Deck deck, Deck graveDeck) {
		if(deck.getSize() == 1) {
			deck.setDeckEndFlag(true);
		}

		if(deck.isEmpty() == true) {
			graveDeck.shuffle();
			Card card = graveDeck.getCard();
			hand.add(card);
			if(card.numberInt == 1) {
				this.exist1 = true;
			}
			graveDeck.remove();
		}else {
			Card card = deck.getCard();
			hand.add(card);
			if(card.numberInt == 1) {
				this.exist1 = true;
			}
			deck.remove();
		}


		if(this.sumup() > 21) {
			turnEndFlag = true;
		}

		handSum = this.sumup();

	}

	//手札の数字を計算
	public int sumup() {
		int sum = 0;

		for(Iterator it = hand.iterator(); it.hasNext(); ) {
			Card card = (Card)it.next();
			sum += card.numberInt;
		}

		if(this.exist1) {
			if(sum + 10 < 22) {
				sum += 10;
			}else {
				this.exist1 = false;
			}
		}

		return sum;
	}

	//手札を捨て山へ、各変数を初期化
	public void reset(Deck graveDeck) {
		for(Iterator it = hand.iterator(); it.hasNext();) {
			Card card = (Card)it.next();
			graveDeck.add(card);
		}
		this.hand.clear();

		this.turnEndFlag = false;
		this.exist1 = false;
	}


}

