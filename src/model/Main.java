package model;

public class Main{
	public void start (Player player, Dealer dealer, Deck deck, Deck graveDeck) {
		int chip = player.getChip();
		int bet = player.getBet();
		player.setChip(chip - bet);
		dealer.start(deck, graveDeck);
		player.start(deck, graveDeck);
		dealer.draw(deck, graveDeck);
		player.draw(deck, graveDeck);
	}

	public boolean action(String action, Player player, Deck deck, Deck graveDeck) {
		if(action.equals("hit")) { //Hitのとき
			player.draw(deck, graveDeck);
		}else if(action.equals("stand")){ //Standのとき
			player.setTurnEndFlag(true);
		}else if(action.equals("double")) { //Double Downのとき
			int bet = player.getBet();
			player.setBet(bet*2);

			int chip = player.getChip();
			player.setChip(chip - bet);

			player.draw(deck, graveDeck);
			player.setTurnEndFlag(true);
		}else if(action.equals("fold")) { //Foldのとき
			player.setTurnEndFlag(true);
			player.setFoldFlag(true);
		}
		return player.getTurnEndFlag();
	}

	public String result(Player player, Dealer dealer) {
		String result = "Error";
		int chip = player.getChip();
		int bet = player.getBet();

		int playerSum = player.getHandSum();
		int dealerSum = dealer.getHandSum();

		if(player.getFoldFlag()) {
			result ="You are FOLD";
			player.setChip(chip + bet / 2);
		}else if(playerSum == dealerSum) {
			if(playerSum == 21) {
				if(player.getHand().size() == 2 && dealer.getHand().size() == 2) {
					result ="Draw";
					player.setChip(chip + bet);
				}else if(player.getHand().size() == 2) {
					result = "✨BLACK JACK✨ \r\n You Win!!!";
					player.setChip(chip + bet * 5 / 2);
				}else if(dealer.getHand().size() == 2) {
					result = "Dealer is BLACK JACK!　You Lose...";
				}else {
					result ="Draw";
					player.setChip(chip + bet);
				}
			}else {
				result ="Draw";
				player.setChip(chip + bet);
			}
		}else if(playerSum == 21) {
			if(player.getHand().size() == 2) {
				result = "✨BLACK JACK✨ \r\n You Win!!!";
				player.setChip(chip + bet * 5 / 2);
			}else {
				result ="You Win!!!";
				player.setChip(chip + bet * 2);
			}
		}else if(dealerSum == 21) {
			result = "You Lose...";
		}else if(playerSum > 21) {
			result = "You Lose...";
		}else if(dealerSum > 21) {
			result ="You Win!!!";
			player.setChip(chip + bet * 2);
		}else if(playerSum > dealerSum) {
			result = "You Win!!!";
			player.setChip(chip + bet * 2);
		}else if(playerSum < dealerSum) {
			result = "You Lose...";
		}

		return result;
	}



}
