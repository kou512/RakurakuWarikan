package jp.gr.java_conf.kou512.rakuwari;

//-----------------------
//カスタムリスト用クラス
//-----------------------

public class PersonList {

	//出席チェック
	private boolean isAttendChecked;
	//支払済チェック
	private boolean isPaidChecked;
	//名前
	private String personName;
	//支払金額
	private String cost;
	
	//個別設定されているか判別するフラグ
	//（設定されている・・・true　設定されていない・・・false）
	private boolean isSetAnotherCost;
	
	public boolean isSetAnotherCost() {
		return isSetAnotherCost;
	}
	public void setSetAnotherCost(boolean isSetAnotherCost) {
		this.isSetAnotherCost = isSetAnotherCost;
	}
	public boolean isPaidChecked() {
		return isPaidChecked;
	}
	public void setPaidChecked(boolean isPaidChecked) {
		this.isPaidChecked = isPaidChecked;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public boolean isAttendChecked() {
		return isAttendChecked;
	}
	public void setAttendChecked(boolean isAttendChecked) {
		this.isAttendChecked = isAttendChecked;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	
}
