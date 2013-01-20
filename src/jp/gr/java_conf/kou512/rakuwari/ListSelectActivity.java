package jp.gr.java_conf.kou512.rakuwari;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.kou512.rakuwari.bean.ListBean;
import jp.gr.java_conf.kou512.rakuwari.bean.PersonBean;
import jp.gr.java_conf.kou512.rakuwari.dao.ListTableDao;
import jp.gr.java_conf.kou512.rakuwari.sqlite.ListOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ListSelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);

		//��ʏ㕔�A�������̃e�L�X�g
		TextView tv_list_desreption = new TextView(this);
		tv_list_desreption.setText("�J�����X�g��I�����Ă��������B�V�K�ɍ쐬����ꍇ�́A���j���[����u�V�K�v��I�����Ă��������B");
		layout.addView(tv_list_desreption);
		
		//�w���p�[�N���X�̍쐬
		ListOpenHelper helper = new ListOpenHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();

		//DAO�̌Ăяo��
		ListTableDao listTableDao = new ListTableDao(db);

		//�r���[�̒ǉ�
		List<ListBean> listBeanList = listTableDao.findAll();

		//ArrayAdapter�̗p��
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		
		// ���X�g�̎擾
		for(int i = 0; i<listBeanList.size(); i++){
			String listItem = listBeanList.get(i).getListName();
			adapter.add(listItem);
		}
		
		ListView listView = (ListView) findViewById(R.id.lv_ListSelect);
		listView.setAdapter(adapter);
		
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_select, menu);
		return true;
	}

}
