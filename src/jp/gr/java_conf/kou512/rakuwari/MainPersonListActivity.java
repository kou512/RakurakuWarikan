package jp.gr.java_conf.kou512.rakuwari;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import jp.gr.java_conf.kou512.rakuwari.bean.PersonBean;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainPersonListActivity extends ListActivity {

	ListView personList;
	ArrayAdapter<String> personList_adapter;
	ArrayList<PersonBean> personListArray;
	Globals globals;
	int totalCost = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		personListArray = new ArrayList<PersonBean>();

		globals = (Globals) this.getApplication();
		totalCost = globals.totalCost;

		// �u�ǉ��{�^���v�̎擾
		View btn_AddPerson = findViewById(R.id.btn_AddPerson);
		btn_AddPerson.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addPerson();

			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("MainPersonListActivity", "onPause");
	}

	
	//onResume���ɁA�O���[�o���ϐ����獇�v���z���擾���A���ꂼ��̎x�����z���Čv�Z����B
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("MainPersonListActivity", "onResume");

		globals = (Globals) getApplication();
		totalCost = globals.totalCost;
		CustomPersonListAdapter personAdapter = new CustomPersonListAdapter(
				getApplicationContext(), personListArray);
		setListAdapter(personAdapter);
		setCost();
	}

	// ���X�g�ɍ��ڂ�ǉ�����
	private void addPerson() {
		// ���O���擾
		EditText editTextPerson = (EditText) findViewById(R.id.eText_personName);
		String personName = editTextPerson.getText().toString();

		// Bean�ɂ��ꂼ��̒l���Z�b�g
		PersonBean person = new PersonBean();
		person.setIsAttend(0);
		person.setIsPaid(0);
		person.setPersonName(personName);

		// ���X�g�̎擾
		personListArray.add(person);
		CustomPersonListAdapter personAdapter = new CustomPersonListAdapter(
				getApplicationContext(), personListArray);

		setListAdapter(personAdapter);
		setCost();
		// ���O���͗��̃N���A
		editTextPerson.getEditableText().clear();
	}

	// ���z��\������
	private void setCost() {

		// ��l������̉��i���擾
		float costPerPerson = 0;
		if (personListArray.size() > 0) {
			BigDecimal bd_costPerPerson = new BigDecimal(totalCost);
			bd_costPerPerson = bd_costPerPerson.divide(new BigDecimal(
					personListArray.size()), 0, RoundingMode.HALF_UP);
			costPerPerson = bd_costPerPerson.floatValue();

			// �S�Ă̌ʋ��z�ɑ΂��A���i��ݒ肵�Ă���
			for (int i = 0; i < personListArray.size(); i++) {
				PersonBean person = (PersonBean) personListArray.get(i);
				person.setCost(costPerPerson);
			}
		}
		//�O���[�o���ϐ��ֈꎞ�ۑ�
		globals.personArrayList = personListArray; 

	}

}
