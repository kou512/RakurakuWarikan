package com.example.warikan;

//-----------------------
//カスタムリスト用クラス
//-----------------------

public class PersonList {
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
	private boolean isPaidChecked;
	private String personName;
}
