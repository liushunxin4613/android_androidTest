package entity;

public class CardViewInfo1 {
	
	/**
	 * �ı�ID
	 */
	private int textId;
	
	/**
	 * ͼƬID
	 */
	private int imgId;
	
	/**
	 * ��ɫID
	 */
	private int colorId;
	
	/**
	 * ��絥λ,Ĭ��ֵΪ1
	 */
	private int numW = 1;
	
	/**
	 * �ݿ絥λ,Ĭ��ֵΪ1
	 */
	private int numH = 1;

	public int getTextId() {
		return textId;
	}

	public void setTextId(int textId) {
		this.textId = textId;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public int getNumW() {
		return numW;
	}

	public void setNumW(int numW) {
		this.numW = numW;
	}

	public int getNumH() {
		return numH;
	}

	public void setNumH(int numH) {
		this.numH = numH;
	}

	public CardViewInfo1(int textId, int imgId, int colorId, int numW, int numH) {
		super();
		this.textId = textId;
		this.imgId = imgId;
		this.colorId = colorId;
		this.numW = numW;
		this.numH = numH;
	}

	public CardViewInfo1() {
		super();
	}

	public CardViewInfo1(int textId, int imgId, int colorId) {
		super();
		this.textId = textId;
		this.imgId = imgId;
		this.colorId = colorId;
	}
	
}
