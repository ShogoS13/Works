package BlackJack;

///////////////////////////////////////////
//  file           CardConf.java         //
//  brief          �J�[�h�̐ݒ�N���X�@�@�@�@      //
//  author         Shogo Fukui           //
//  date           3rd Oct, 2020         //
///////////////////////////////////////////


public class CardConf {

	public static final int DEF_CARD_TYPE_SPADE = 1;
	public static final int DEF_CARD_TYPE_CLOVER = 2;
	public static final int DEF_CARD_TYPE_HEART = 3;
	public static final int DEF_CARD_TYPE_DIAMOND = 4;
	public static final int DEF_CARD_TYPE_MIN = DEF_CARD_TYPE_SPADE;
	public static final int DEF_CARD_TYPE_MAX = DEF_CARD_TYPE_DIAMOND;

	public static final int DEF_CARD_VALUE_MIN = 1;
	public static final int DEF_CARD_VALUE_MAX = 13;

	private int type; //�X�[�g
	private int value; //�l
	private boolean drowFlg;

    //�R���X�g���N�^
    public CardConf() {

    	this.type = DEF_CARD_TYPE_MIN;
        this.value = DEF_CARD_VALUE_MIN;
        this.drowFlg = false;
        
    }

    //�Q�b�^�[�E�Z�b�^�[�̐ݒ�
    public int getType() {
        return type;
    }

    public void setType(int type) {

    	if(DEF_CARD_TYPE_MIN <= type && type <= DEF_CARD_TYPE_MAX){
            this.type = type;
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {

    	if(DEF_CARD_VALUE_MIN <= value && value <= DEF_CARD_VALUE_MAX){
            this.value = value;
        }
    }

    public boolean getDrowFlg() {
        return drowFlg;
    }

    public void setDrowFlg(boolean drowFlg) {
        this.drowFlg = drowFlg;
    }

}