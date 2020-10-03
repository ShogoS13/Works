package BlackJack;

///////////////////////////////////////////
//file           Main.java               //
//brief          ���C���N���X         �@�@      //
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

			System.out.println("�Q�[�����J�n���܂��B");
			System.out.println("���L�̃R�}���h����͂��Ă��������B");
		    System.out.println("q�E�E�E�I��");
		    System.out.println("r�E�E�E��蒼��");
		    System.out.println("h�E�E�E�q�b�g");
		    System.out.println("s�E�E�E�X�^���h");
		    main.showStatus(modules);
		    String inputStr = sc.nextLine();

		    switch (inputStr) {

		        case "q":
		            //�I��
		            System.out.println("�I�����܂�...");
		            sc.close();
		            System.exit(0);
		            break;
		        
		        case "r":
		            //��蒼��
		            modules.gameInit();
		            break;
		        
		        case "h":
		            //�q�b�g
		            modules.playerHit();
		            break;
		        
		        case "s":
		            //�X�^���h
		            modules.playerStand();
		            break;
		            
		        default:
		            //����ȊO�̃R�}���h
		            System.out.println("�L�[���Ή����Ă��܂���B");
		            break;
		        
		    }
		}

    }

	//�X�e�[�^�X�\��
	public void showStatus(Modules modules) {

		System.out.println("----------");
		//dealer
		ArrayList<CardConf> dc = modules.getDealer().getCard();
		int dcc = modules.getDealer().getCardCnt();
		System.out.println("����̒l��" + (modules.getGameEndFlg() ? modules.getScore(dc, dcc) : ""));
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
		System.out.println("���Ȃ��̃X�R�A��" + modules.getScore(pc, pcc));
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
		        System.out.println("���Ȃ��̏����ł��B");
	        } else if (modules.gameJudgment() == 0) {
	            System.out.println("���������ł��B");
	        } else {
	            System.out.println("���Ȃ��̕����ł��B");
	        }
	
			System.out.println("----------");
        }
    }

	
	//�J�[�h��񕶎���擾
	public String getCardStr(CardConf card) {
	
		String res = "";
        switch (card.getType()) {
        
		    case CardConf.DEF_CARD_TYPE_SPADE:
                res = "�X�y�[�h��";
                break;
	
		    case CardConf.DEF_CARD_TYPE_CLOVER:
		        res = "�N���u��";
                break;
	
		    case CardConf.DEF_CARD_TYPE_HEART:
		        res = "�n�[�g��";
		        break;
		        
		    case CardConf.DEF_CARD_TYPE_DIAMOND:
		        res = "�_�C����";
		        break;
		       
		    default:
		        res = "���Ή��J�[�h�ł��B";
		        break;
		}
		
        res = res + card.getValue();
		return res;
    }
}

