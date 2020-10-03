package BlackJack;
///////////////////////////////////////////
//file           Modules.java            //
//brief          ゲームの設定クラス    　　      //
//author         Shogo Fukui             //
//date           3rd Oct, 2020           //
///////////////////////////////////////////

import java.util.ArrayList;

public class Modules {

	public static final int DEF_SHUFFLE_CNT = 5;
    private Deck deck;
    private Role player;
    private Role dealer;
    private boolean gameEndFlg;

	//コンストラクタ
    public Modules() {
        this.deck = new Deck();
        this.player = new Role();
        this.dealer = new Role();
        this.gameInit();
    }
    
    //ゲッター・セッターの設定
    public Deck getTrumpCards() {
        return deck;
    }

    public void setTrumpCards(Deck deck) {
        this.deck = deck;
    }

    public Role getPlayer() {
        return player;
    }

    public void setPlayer(Role player) {
        this.player = player;
    }

    public Role getDealer() {
        return dealer;
    }

    public void setDealer(Role dealer) {
        this.dealer = dealer;
    }

    public boolean getGameEndFlg() {
        return gameEndFlg;
    }

    public void setGameEndFlg(boolean gameEndFlg) {
        this.gameEndFlg = gameEndFlg;
    }

    //ゲーム初期化
    public void gameInit() {
        this.gameEndFlg = false;

        //山札シャッフル
        for (int i = 0; i < DEF_SHUFFLE_CNT; i++) {
            this.deck.shuffle();
        }
        
        //プレイヤー・ディーラー初期化
        this.player.setCard(new ArrayList<CardConf>());
        this.player.setCardCnt(0);
        this.dealer.setCard(new ArrayList<CardConf>());
        this.dealer.setCardCnt(0);

        //プレイヤー・ディーラーに手札を2枚づつ配る
        for (int i = 0; i < 2; i++) {

        	this.player.getCard().add(this.deck.drowCard());
            this.player.setCardCnt(this.player.getCardCnt() + 1);
            this.dealer.getCard().add(this.deck.drowCard());
            this.dealer.setCardCnt(this.dealer.getCardCnt() + 1);

        }
    }

    //プレイヤーヒット
    public void playerHit() {
        if (this.gameEndFlg == false) {

        	this.player.getCard().add(this.deck.drowCard());
            this.player.setCardCnt(this.player.getCardCnt() + 1);
            int score = this.getScore(this.player.getCard(), this.player.getCardCnt());
            if (22 <= score)
            	this.playerStand(); // バーストしたので強制終了
        }
    }

    //プレイヤースタンド
   public void playerStand() {
        this.dealerHit();
    }

    //ディーラーヒット
    private void dealerHit() {
        for (;;) {

        	int score = this.getScore(this.dealer.getCard(), this.dealer.getCardCnt());
            if (score < 17) {
            	//ディーラーは手持ちのカードの合計が「17」以上になるまで引く                           *** //
                this.dealer.getCard().add(this.deck.drowCard());
                this.dealer.setCardCnt(this.dealer.getCardCnt() + 1);
            } else {
            	//ディーラーは手持ちのカードの合計が「17」以上になったら引かない                             *** //
                this.dealerStand();
                break;
            }
        }
    }

    //ディーラースタンド
    private void dealerStand() {
        this.gameEndFlg = true;
    }

    //現在のスコア取得
    public int getScore(ArrayList<CardConf> card, int cardCnt) {

    	int res = 0;
        boolean aceFlg = false;

        for (int i = 0; i < cardCnt; i++) {

        	if (2 <= card.get(i).getValue() && card.get(i).getValue() <= 10) {
                //カードの値が2～10のときはその値を格納
                res += card.get(i).getValue();
            } else if (11 <= card.get(i).getValue() && card.get(i).getValue() <= 13) {
                //J, Q, Kのときは値を10に設定
                res += 10;
            } else {

            	if (aceFlg) {
                    //2枚目のエースは強制的に値を1に設定
                    res += 1;
                } else {
                    //エース計算に進む
                    aceFlg = true;
                }
            
            }

        }
        
        //エース計算
        if (aceFlg) {

        	var tmpScore1 = res + 1;
            var tmpScore2 = res + 11;
            var diff1 = 21 - tmpScore1;
            var diff2 = 21 - tmpScore2;

            if ((22 <= tmpScore1) && (22 <= tmpScore2)) {
                //どちらもバーストしているとき値を1に設定
                res = tmpScore1;
            } else if ((22 <= tmpScore1) && (tmpScore2 <= 21)) {
                //エースが1でバーストしているとき値を11に設定
                res = tmpScore2;
            } else if ((tmpScore1 <= 21) && (22 <= tmpScore2)) {
                //エースが11でバーストしているとき値を1に設定
                res = tmpScore1;
            } else {

            	//どちらもバーストしていないとき21との差分が小さい値を設定
            	if (diff1 < diff2)
                    res = tmpScore1;
                else
                    res = tmpScore2;
            	
            }
        }
        return res;
    }

    //ゲーム勝敗判定
    public int gameJudgment() {

    	int res = 0;
        int score1 = this.getScore(this.player.getCard(), this.player.getCardCnt());
        int score2 = this.getScore(this.dealer.getCard(), this.dealer.getCardCnt());
        int diff1 = 21 - score1;
        int diff2 = 21 - score2;

        if (22 <= score1 && 22 <= score2) {
            //プレイヤー・ディーラー共にバーストしているので負け
            res = -1;
        } else if (22 <= score1 && score2 <= 21) {
            //プレイヤーがバーストしているので負け
            res = -1;
        } else if (score1 <= 21 && 22 <= score2) {
            //ディーラーがバーストしているので勝ち
            res = 1;
        } else {

        	if (diff1 == diff2) {
                //同スコアなので引き分け
                res = 0;

                if (score1 == 21 && this.player.getCardCnt() == 2 && this.dealer.getCardCnt() != 2) {
                    //プレイヤーのみがピュアブラックジャックなら勝ち
                    res = 1;
                }

            } else if (diff1 < diff2) {
                //プレイヤーの方が21に近いので勝ち
                res = 1;
            } else {
                //ディーラーの方が21に近いので負け
                res = -1;
            }
        }
        return res;
    }

}
