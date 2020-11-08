package model;

public class BetLogic {
	public boolean execute(Player player, int bet) {
		int chip = player.getChip();

		if(bet < 1 || chip < bet) {
			return false;
		}

		player.setBet(bet);

		return true;
	}

}
