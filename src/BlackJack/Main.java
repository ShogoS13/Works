package BlackJack;

///////////////////////////////////////////
//file           Main.java               //
//brief          メインクラス         　　      //
//author         Shogo Fukui             //
//date           3rd Oct, 2020           //
///////////////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Main main = new Main();
		Modules modules = new Modules();
		Scanner sc = new Scanner(System.in);
		
		while(true) {

			System.out.println("ゲームを開始します。");
			System.out.println("下記のコマンドを入力してください。");
		    System.out.println("q・・・終了");
		    System.out.println("r・・・やり直し");
		    System.out.println("h・・・ヒット");
		    System.out.println("s・・・スタンド");
		    main.showStatus(modules);
		    String inputStr = sc.nextLine();

		    switch (inputStr) {

		        case "q":
		            //終了
		            System.out.println("終了します...");
		            sc.close();
		            System.exit(0);
		            break;
		        
		        case "r":
		            //やり直し
		            modules.gameInit();
		            break;
		        
		        case "h":
		            //ヒット
		            modules.playerHit();
		            break;
		        
		        case "s":
		            //スタンド
		            modules.playerStand();
		            break;
		            
		        default:
		            //それ以外のコマンド
		            System.out.println("キーが対応していません。");
		            break;
		        
		    }
		}

    }

	//ステータス表示
	public void showStatus(Modules modules) {

		System.out.println("----------");
		//dealer
		ArrayList<CardConf> dc = modules.getDealer().getCard();
		int dcc = modules.getDealer().getCardCnt();
		System.out.println("相手の値は" + (modules.getGameEndFlg() ? modules.getScore(dc, dcc) : ""));
		String cardStr = "";
		
		if (modules.getGameEndFlg()) {
		
			for (int i = 0; i < dcc; i++) {
		    
				if (i != 0)
		            cardStr += ",";
                    cardStr += getCardStr(dc.get(i));
	        }
		        
		} else {
		    cardStr = getCardStr(dc.get(0)) + ",*";
        }
	
		System.out.println(cardStr);		
		System.out.println("----------");

		// player
		ArrayList<CardConf> pc = modules.getPlayer().getCard();
		int pcc = modules.getPlayer().getCardCnt();
		System.out.println("あなたのスコアは" + modules.getScore(pc, pcc));
		cardStr = "";
		
		for (int i = 0; i < pcc; i++) {
			if (i != 0)
                cardStr += ",";
			    cardStr += getCardStr(pc.get(i));
	    }
		System.out.println(cardStr);
		System.out.println("----------");
		
		if (modules.getGameEndFlg()) {
		 
			if (modules.gameJudgment() == 1) {
		        System.out.println("あなたの勝利です。");
	        } else if (modules.gameJudgment() == 0) {
	            System.out.println("引き分けです。");
	        } else {
	            System.out.println("あなたの負けです。");
	        }
	
			System.out.println("----------");
        }
    }

	
	//カード情報文字列取得
	public String getCardStr(CardConf card) {
	
		String res = "";
        switch (card.getType()) {
        
		    case CardConf.DEF_CARD_TYPE_SPADE:
                res = "スペードの";
                break;
	
		    case CardConf.DEF_CARD_TYPE_CLOVER:
		        res = "クラブの";
                break;
	
		    case CardConf.DEF_CARD_TYPE_HEART:
		        res = "ハートの";
		        break;
		        
		    case CardConf.DEF_CARD_TYPE_DIAMOND:
		        res = "ダイヤの";
		        break;
		       
		    default:
		        res = "未対応カードです。";
		        break;
		}
		
        res = res + card.getValue();
		return res;
    }
}

