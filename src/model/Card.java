package model;

public class Card {
	public int markInt; //スートを数字に置き換えたもの. 4で割った余りを代入.
	public int numberInt; //計算上の数。（11以上は一律10）
	public String markStr;
	public String numberStr;

	Card(int n){

		if(n % 13 < 11 && n % 13 != 0) {
			numberInt = n % 13;

			if(n % 13 == 1) {
				numberStr = "A";
			}else {
				numberStr = String.valueOf(numberInt);
			}

		}else {
			numberInt = 10;
			switch(n % 13) {
			case 11:
				numberStr = "J";
				break;
			case 12:
				numberStr = "Q";
				break;
			case 0:
				numberStr = "K";
				break;
			}
		}

		switch(n % 4) {
		case 0:
			markStr = "♠";
			markInt = 0;
			break;
		case 1:
			markStr = "♥";
			markInt = 1;
			break;
		case 2:
			markStr = "♣";
			markInt = 2;
			break;
		case 3:
			markStr = "♦";
			markInt = 3;
			break;
		}
	}
}
