package jp.gr.java_conf.kou512.rakuwari;

//-----------------------
//�J�X�^�����X�g�p�N���X
//-----------------------

public class PersonList {

	//�o�ȃ`�F�b�N
	private boolean isAttendChecked;
	//�x���σ`�F�b�N
	private boolean isPaidChecked;
	//���O
	private String personName;
	//�x�����z
	private String cost;
	
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
