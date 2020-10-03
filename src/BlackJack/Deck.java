package BlackJack;

///////////////////////////////////////////
//file           Deck.java               //
//brief          山札の設定クラス　　  　　      //
//author         Shogo Fukui             //
//date           3rd Oct, 2020           //
///////////////////////////////////////////

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	public static final int CARDCNT = 52;
	private ArrayList<CardConf> deck;
	private int deckDrowCnt;
	private int deckCnt;
	
	//コンストラクタ
    public Deck() {
        this.deckCnt = CARDCNT;
        this.cardsInit();
        this.deckInit();
    }

    //ゲッター・セッターの設定
    public ArrayList<CardConf> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<CardConf> deck) {
        this.deck = deck;
    }

    public int getDeckDrowCnt() {
        return deckDrowCnt;
    }

    public void setDeckDrowCnt(int deckDrowCnt) {
        this.deckDrowCnt = deckDrowCnt;
    }

    public int getDeckCnt() {
        return deckCnt;
    }

    public void setDeckCnt(int deckCnt) {
        this.deckCnt = deckCnt;
    }

    //カード初期化
    private void cardsInit() {
        this.deck = new ArrayList<CardConf>();
        for (int i = 0; i < this.deckCnt; i++) {
            CardConf curCard = new CardConf();
            curCard.setDrowFlg(false);
            if (0 <= i && i <= 12) {
                //スペード
                curCard.setType(CardConf.DEF_CARD_TYPE_SPADE);
                curCard.setValue(i + 1);
            } else if (13 <= i && i <= 25) {
                //クラブ
                curCard.setType(CardConf.DEF_CARD_TYPE_CLOVER);
                curCard.setValue((i - 13) + 1);
            } else if (26 <= i && i <= 38) {
                //ハート
                curCard.setType(CardConf.DEF_CARD_TYPE_HEART);
                curCard.setValue((i - 26) + 1);
            } else if (39 <= i && i <= 51) {
                //ダイヤ
                curCard.setType(CardConf.DEF_CARD_TYPE_DIAMOND);
                curCard.setValue((i - 39) + 1);
            }
            this.deck.add(curCard);
        }
    }

    //山札ドローフラグ初期化
    private void deckDrowFlgInit() {
        for (int i = 0; i < this.deckCnt; i++) {
            this.deck.get(i).setDrowFlg(false);
        }
    }

    //山札初期化
    private void deckInit() {
        this.deckDrowFlgInit();
        this.deckDrowCnt = 0;
    }

    //山札シャッフル
    public void shuffle() {
        Collections.shuffle(this.deck);
        this.deckDrowFlgInit();
        this.deckDrowCnt = 0;
    }

    //初手の作成
    public CardConf drowCard() {
        CardConf res = null;
        if (this.deckDrowCnt < this.deckCnt) {
        	this.deck.get(this.deckDrowCnt).setDrowFlg(true);
            res = this.deck.get(this.deckDrowCnt++);
        }
        return res;
    }

}
