package jp.gr.java_conf.kou512.rakuwari.bean;


public class PersonBean {
	//oÈÒNo
	private int personNum;
	//¼O
	private String personName;
	//êl ½èÌx¥àz
	private float cost;
	//ÂÊx¥¢tO
	private int isKobetsuPay;
	//XgNo
	private int listNo;
	//
	private String memo;
	//x¥ÏtO
	private int isPaid;
	//oÈtO
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
