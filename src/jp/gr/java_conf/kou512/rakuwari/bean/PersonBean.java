package jp.gr.java_conf.kou512.rakuwari.bean;


public class PersonBean {
	//出席者No
	private int personNum;
	//名前
	private String personName;
	//一人あたりの支払金額
	private float cost;
	//個別支払いフラグ
	private int isKobetsuPay;
	//リストNo
	private int listNo;
	//メモ
	private String memo;
	//支払済フラグ
	private int isPaid;
	//出席フラグ
	private int isAttend;
	
	
	public int getIsKobetsuPay() {
		return isKobetsuPay;
	}
	public void setIsKobetsuPay(int isKobetsuPay) {
		this.isKobetsuPay = isKobetsuPay;
	}
	public int getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
	public int getIsAttend() {
		return isAttend;
	}
	public void setIsAttend(int isAttend) {
		this.isAttend = isAttend;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getPersonNum() {
		return personNum;
	}
	public void setPersonNum(int personNum) {
		this.personNum = personNum;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int isKobetsuPay() {
		return isKobetsuPay;
	}
	public void setKobetsuPay(int isKobetsuPay) {
		this.isKobetsuPay = isKobetsuPay;
	}
	public int getListNo() {
		return listNo;
	}
	public void setListNo(int listNo) {
		this.listNo = listNo;
	}
	
}
