package BlackJack;

///////////////////////////////////////////
//file           Role.java               //
//brief          プレイヤーの設定クラス 　　      //
//author         Shogo Fukui             //
//date           3rd Oct, 2020           //
///////////////////////////////////////////

import java.util.ArrayList;

public class Role {

	private ArrayList<CardConf> cards;
	private int cardCnt;
	private int score;
	
	//コンストラクタ
	public Role() {
	        this.cards = new ArrayList<CardConf>();
	        this.cardCnt = 0;
	        this.score = 0;
	}

	//ゲッター・セッターの設定
	public ArrayList<CardConf> getCard() {
	        return cards;
    }

    public void setCard(ArrayList<CardConf> cards) {
        this.cards = cards;
    }

    public int getCardCnt() {
        return cardCnt;
    }

    public void setCardCnt(int cardCnt) {
	        this.cardCnt = cardCnt;
	    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
	        this.score = score;
    }

}
