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

		// 「追加ボタン」の取得
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

	
	//onResume時に、グローバル変数から合計金額を取得し、それぞれの支払金額を再計算する。
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

	// リストに項目を追加する
	private void addPerson() {
		// 名前を取得
		EditText editTextPerson = (EditText) findViewById(R.id.eText_personName);
		String personName = editTextPerson.getText().toString();

		// Beanにそれぞれの値をセット
		PersonBean person = new PersonBean();
		person.setIsAttend(0);
		person.setIsPaid(0);
		person.setPersonName(personName);

		// リストの取得
		personListArray.add(person);
		CustomPersonListAdapter personAdapter = new CustomPersonListAdapter(
				getApplicationContext(), personListArray);

		setListAdapter(personAdapter);
		setCost();
		// 名前入力欄のクリア
		editTextPerson.getEditableText().clear();
	}

	// 金額を表示する
	private void setCost() {

		// 一人あたりの価格を取得
		float costPerPerson = 0;
		if (personListArray.size() > 0) {
			BigDecimal bd_costPerPerson = new BigDecimal(totalCost);
			bd_costPerPerson = bd_costPerPerson.divide(new BigDecimal(
					personListArray.size()), 0, RoundingMode.HALF_UP);
			costPerPerson = bd_costPerPerson.floatValue();

			// 全ての個別金額に対し、価格を設定していく
			for (int i = 0; i < personListArray.size(); i++) {
				PersonBean person = (PersonBean) personListArray.get(i);
				person.setCost(costPerPerson);
			}
		}
		//グローバル変数へ一時保存
		globals.personArrayList = personListArray; 

	}

}
