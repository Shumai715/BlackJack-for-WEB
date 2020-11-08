package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements Serializable{
	private List<Card> deck;
	private boolean deckEndFlag = false;

	public Deck() {
		deck = new ArrayList<Card>();
	}

	public List<Card> getDeck(){return this.deck;}
	public boolean getDeckEndFlag() {return this.deckEndFlag;}

	public void setDeckEndFlag(boolean deckEndFlag) {this.deckEndFlag = deckEndFlag;}

	public void create() {
		for(int i = 0; i < 52; i++) {
			Card card = new Card(i + 1);
			deck.add(card);
		}
	}

	public void createTest() {
		for(int i = 0; i < 26; i++) {
			Card card = new Card(i + 1);
			deck.add(card);
		}
	}

	public void add(Card card) {
		deck.add(card);
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public void remove() {
		deck.remove(0);
	}

	public Card getCard() {
		return deck.get(0);
	}

	public int getSize() {
		return deck.size();
	}

	public boolean isEmpty() {
		return deck.isEmpty();
	}
}
