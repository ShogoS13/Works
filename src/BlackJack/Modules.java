package BlackJack;
///////////////////////////////////////////
//file           Modules.java            //
//brief          �Q�[���̐ݒ�N���X    �@�@      //
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

	//�R���X�g���N�^
    public Modules() {
        this.deck = new Deck();
        this.player = new Role();
        this.dealer = new Role();
        this.gameInit();
    }
    
    //�Q�b�^�[�E�Z�b�^�[�̐ݒ�
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

    //�Q�[��������
    public void gameInit() {
        this.gameEndFlg = false;

        //�R�D�V���b�t��
        for (int i = 0; i < DEF_SHUFFLE_CNT; i++) {
            this.deck.shuffle();
        }
        
        //�v���C���[�E�f�B�[���[������
        this.player.setCard(new ArrayList<CardConf>());
        this.player.setCardCnt(0);
        this.dealer.setCard(new ArrayList<CardConf>());
        this.dealer.setCardCnt(0);

        //�v���C���[�E�f�B�[���[�Ɏ�D��2���Âz��
        for (int i = 0; i < 2; i++) {

        	this.player.getCard().add(this.deck.drowCard());
            this.player.setCardCnt(this.player.getCardCnt() + 1);
            this.dealer.getCard().add(this.deck.drowCard());
            this.dealer.setCardCnt(this.dealer.getCardCnt() + 1);

        }
    }

    //�v���C���[�q�b�g
    public void playerHit() {
        if (this.gameEndFlg == false) {

        	this.player.getCard().add(this.deck.drowCard());
            this.player.setCardCnt(this.player.getCardCnt() + 1);
            int score = this.getScore(this.player.getCard(), this.player.getCardCnt());
            if (22 <= score)
            	this.playerStand(); // �o�[�X�g�����̂ŋ����I��
        }
    }

    //�v���C���[�X�^���h
   public void playerStand() {
        this.dealerHit();
    }

    //�f�B�[���[�q�b�g
    private void dealerHit() {
        for (;;) {

        	int score = this.getScore(this.dealer.getCard(), this.dealer.getCardCnt());
            if (score < 17) {
            	//�f�B�[���[�͎莝���̃J�[�h�̍��v���u17�v�ȏ�ɂȂ�܂ň���                           *** //
                this.dealer.getCard().add(this.deck.drowCard());
                this.dealer.setCardCnt(this.dealer.getCardCnt() + 1);
            } else {
            	//�f�B�[���[�͎莝���̃J�[�h�̍��v���u17�v�ȏ�ɂȂ���������Ȃ�                             *** //
                this.dealerStand();
                break;
            }
        }
    }

    //�f�B�[���[�X�^���h
    private void dealerStand() {
        this.gameEndFlg = true;
    }

    //���݂̃X�R�A�擾
    public int getScore(ArrayList<CardConf> card, int cardCnt) {

    	int res = 0;
        boolean aceFlg = false;

        for (int i = 0; i < cardCnt; i++) {

        	if (2 <= card.get(i).getValue() && card.get(i).getValue() <= 10) {
                //�J�[�h�̒l��2�`10�̂Ƃ��͂��̒l���i�[
                res += card.get(i).getValue();
            } else if (11 <= card.get(i).getValue() && card.get(i).getValue() <= 13) {
                //J, Q, K�̂Ƃ��͒l��10�ɐݒ�
                res += 10;
            } else {

            	if (aceFlg) {
                    //2���ڂ̃G�[�X�͋����I�ɒl��1�ɐݒ�
                    res += 1;
                } else {
                    //�G�[�X�v�Z�ɐi��
                    aceFlg = true;
                }
            
            }

        }
        
        //�G�[�X�v�Z
        if (aceFlg) {

        	var tmpScore1 = res + 1;
            var tmpScore2 = res + 11;
            var diff1 = 21 - tmpScore1;
            var diff2 = 21 - tmpScore2;

            if ((22 <= tmpScore1) && (22 <= tmpScore2)) {
                //�ǂ�����o�[�X�g���Ă���Ƃ��l��1�ɐݒ�
                res = tmpScore1;
            } else if ((22 <= tmpScore1) && (tmpScore2 <= 21)) {
                //�G�[�X��1�Ńo�[�X�g���Ă���Ƃ��l��11�ɐݒ�
                res = tmpScore2;
            } else if ((tmpScore1 <= 21) && (22 <= tmpScore2)) {
                //�G�[�X��11�Ńo�[�X�g���Ă���Ƃ��l��1�ɐݒ�
                res = tmpScore1;
            } else {

            	//�ǂ�����o�[�X�g���Ă��Ȃ��Ƃ�21�Ƃ̍������������l��ݒ�
            	if (diff1 < diff2)
                    res = tmpScore1;
                else
                    res = tmpScore2;
            	
            }
        }
        return res;
    }

    //�Q�[�����s����
    public int gameJudgment() {

    	int res = 0;
        int score1 = this.getScore(this.player.getCard(), this.player.getCardCnt());
        int score2 = this.getScore(this.dealer.getCard(), this.dealer.getCardCnt());
        int diff1 = 21 - score1;
        int diff2 = 21 - score2;

        if (22 <= score1 && 22 <= score2) {
            //�v���C���[�E�f�B�[���[���Ƀo�[�X�g���Ă���̂ŕ���
            res = -1;
        } else if (22 <= score1 && score2 <= 21) {
            //�v���C���[���o�[�X�g���Ă���̂ŕ���
            res = -1;
        } else if (score1 <= 21 && 22 <= score2) {
            //�f�B�[���[���o�[�X�g���Ă���̂ŏ���
            res = 1;
        } else {

        	if (diff1 == diff2) {
                //���X�R�A�Ȃ̂ň�������
                res = 0;

                if (score1 == 21 && this.player.getCardCnt() == 2 && this.dealer.getCardCnt() != 2) {
                    //�v���C���[�݂̂��s���A�u���b�N�W���b�N�Ȃ珟��
                    res = 1;
                }

            } else if (diff1 < diff2) {
                //�v���C���[�̕���21�ɋ߂��̂ŏ���
                res = 1;
            } else {
                //�f�B�[���[�̕���21�ɋ߂��̂ŕ���
                res = -1;
            }
        }
        return res;
    }

}
