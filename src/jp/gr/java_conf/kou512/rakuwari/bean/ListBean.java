package jp.gr.java_conf.kou512.rakuwari.bean;

public class ListBean {
	//リストNo
	private int listNo;
	//リスト名
	private String listName;
	//全体金額
	private int totalCost;
	
	public int getListNo() {
		return listNo;
	}
	public void setListNo(int listNo) {
		this.listNo = listNo;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
}
