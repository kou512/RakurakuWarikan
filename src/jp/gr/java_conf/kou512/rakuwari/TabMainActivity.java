package jp.gr.java_conf.kou512.rakuwari;

import java.io.IOException;
import java.util.ArrayList;

import jp.gr.java_conf.kou512.rakuwari.bean.ListBean;
import jp.gr.java_conf.kou512.rakuwari.bean.PersonBean;
import jp.gr.java_conf.kou512.rakuwari.dao.ListTableDao;
import jp.gr.java_conf.kou512.rakuwari.dao.PersonTableDao;
import jp.gr.java_conf.kou512.rakuwari.sqlite.ListOpenHelper;
import jp.gr.java_conf.kou512.rakuwari.sqlite.PersonOpenHelper;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class TabMainActivity extends TabActivity  {
	
	String listNameStr = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_main);
		initTabs();
	}

	// �^�u�̏�����
	protected void initTabs() {
		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		// Tab1
		intent = new Intent().setClass(this, MainPersonListActivity.class);
		spec = tabHost
				.newTabSpec("Tab1")
				.setIndicator("�o�ȃ��X�g",
						res.getDrawable(R.drawable.ic_tab_icon_list_selected))
				.setContent(intent);
		tabHost.addTab(spec);

		// Tab2
		intent = new Intent().setClass(this, CostInputActivity.class);
		spec = tabHost
				.newTabSpec("Tab2")
				.setIndicator(
						"���z�ݒ�",
						res.getDrawable(R.drawable.ic_tab_icon_settings_unselected))
				.setContent(intent);
		tabHost.addTab(spec);

		// Set Default Tab - zero based index
		tabHost.setCurrentTab(0);

	}

	private static final int MENU_ID_MENU1 = (Menu.FIRST + 1);
	private static final int MENU_ID_MENU2 = (Menu.FIRST + 2);
	private static final int MENU_ID_MENU3 = (Menu.FIRST + 3);
	private static final int MENU_ID_MENU4 = (Menu.FIRST + 4);
	private boolean visible = true;

	// �I�v�V�������j���[���ŏ��ɌĂяo����鎞��1�x�����Ăяo����܂�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// ���j���[�A�C�e����ǉ����܂�
		menu.add(Menu.NONE, MENU_ID_MENU1, Menu.NONE, "���̃��X�g�f�[�^");
		menu.add(Menu.NONE, MENU_ID_MENU2, Menu.NONE, "�ۑ�");
		menu.add(Menu.NONE, MENU_ID_MENU3, Menu.NONE, "CSV��荞��");
		menu.add(Menu.NONE, MENU_ID_MENU4, Menu.NONE, "�ݒ�");
		return super.onCreateOptionsMenu(menu);
	}

	// �I�v�V�������j���[���\�������x�ɌĂяo����܂�
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// menu.findItem(MENU_ID_MENU2).setVisible(visible);
		// visible = !visible;
		return super.onPrepareOptionsMenu(menu);
	}

	// �I�v�V�������j���[�A�C�e�����I�����ꂽ���ɌĂяo����܂�
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean ret = true;
		switch (item.getItemId()) {
		default:
			ret = super.onOptionsItemSelected(item);
			break;
		case MENU_ID_MENU1:
			ret = true;
			Intent intent = new Intent(this, ListSelectActivity.class);
			startActivity(intent);
			break;

		// �ۑ��{�^������������
		case MENU_ID_MENU2:
			ret = true;
			// Create EditText
			EditText edtInput = new EditText(this);
			edtInput.setHint("���X�g��");

			// ���͂��ꂽ�e�L�X�g�̎擾
			listNameStr = edtInput.getText().toString();	
			
			// �_�C�A���O�̕\��
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_tab_icon_list_selected)
					.setTitle("���݂̃��X�g��ۑ����܂����H")
					.setView(edtInput)
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
											saveList();								
								}
							})
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									/* Cancel �{�^�����N���b�N�������̏��� */
								}
							}).show();
			break;
			
		case MENU_ID_MENU3:
			ret = true;

			break;

		case MENU_ID_MENU4:
			ret = true;
			break;

		}
		return ret;

	}

	//���X�g�̕ۑ�����
	private void saveList() {
		/* OK�{�^�����N���b�N�������̏��� */
		// LIST�e�[�u���֓o�^
		ListOpenHelper listOpenHelper = new ListOpenHelper(
				getApplicationContext()); // DB Helper�̌Ăяo��
		SQLiteDatabase db = listOpenHelper.getWritableDatabase();
		ListTableDao listTableDao = new ListTableDao(db);

		// ------ �g�����U�N�V���������J�n----------------
		db.beginTransaction();
		try {
			// �O���[�o���ϐ�����l�̎擾
			Globals globals = (Globals) getApplication();
			int totalCost = globals.totalCost; // ���v���z

			ListBean listBean = new ListBean();
			listBean.setListName(listNameStr); // ���X�g���iEditText����擾�j
			listBean.setTotalCost(totalCost); // �O���[�o���ϐ�����擾

			// �C���T�[�g���s
			listTableDao.insert(listBean);

			// --------------------------------

			// PERSON�e�[�u���f���[�g

			// PERSON�e�[�u���֓o�^
			PersonTableDao personTableDao = new PersonTableDao(db);

			ArrayList<PersonBean> personBeanArrayList = globals.personArrayList;
			for (int i = 0; i < personBeanArrayList.size(); i++) {
				PersonBean personBean = personBeanArrayList.get(i);
				personBean.setCost(personBean.getCost());
				personBean.setIsAttend(personBean.getIsAttend());
				personBean.setIsKobetsuPay(personBean.getIsKobetsuPay());
				personBean.setIsPaid(personBean.getIsPaid());
				personBean.setMemo(personBean.getMemo());
				personBean.setPersonName(personBean.getPersonName());

				personTableDao.insert(personBean);
			}

			db.setTransactionSuccessful(); // �R�~�b�g
		} finally {
			// �g�����U�N�V���������I��----------------
			db.endTransaction();
		}

	}

}
