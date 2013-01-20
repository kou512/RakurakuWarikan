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

	// タブの初期化
	protected void initTabs() {
		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		// Tab1
		intent = new Intent().setClass(this, MainPersonListActivity.class);
		spec = tabHost
				.newTabSpec("Tab1")
				.setIndicator("出席リスト",
						res.getDrawable(R.drawable.ic_tab_icon_list_selected))
				.setContent(intent);
		tabHost.addTab(spec);

		// Tab2
		intent = new Intent().setClass(this, CostInputActivity.class);
		spec = tabHost
				.newTabSpec("Tab2")
				.setIndicator(
						"金額設定",
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

	// オプションメニューが最初に呼び出される時に1度だけ呼び出されます
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// メニューアイテムを追加します
		menu.add(Menu.NONE, MENU_ID_MENU1, Menu.NONE, "他のリストデータ");
		menu.add(Menu.NONE, MENU_ID_MENU2, Menu.NONE, "保存");
		menu.add(Menu.NONE, MENU_ID_MENU3, Menu.NONE, "CSV取り込み");
		menu.add(Menu.NONE, MENU_ID_MENU4, Menu.NONE, "設定");
		return super.onCreateOptionsMenu(menu);
	}

	// オプションメニューが表示される度に呼び出されます
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// menu.findItem(MENU_ID_MENU2).setVisible(visible);
		// visible = !visible;
		return super.onPrepareOptionsMenu(menu);
	}

	// オプションメニューアイテムが選択された時に呼び出されます
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

		// 保存ボタン押下時処理
		case MENU_ID_MENU2:
			ret = true;
			// Create EditText
			EditText edtInput = new EditText(this);
			edtInput.setHint("リスト名");

			// 入力されたテキストの取得
			listNameStr = edtInput.getText().toString();	
			
			// ダイアログの表示
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_tab_icon_list_selected)
					.setTitle("現在のリストを保存しますか？")
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
									/* Cancel ボタンをクリックした時の処理 */
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

	//リストの保存処理
	private void saveList() {
		/* OKボタンをクリックした時の処理 */
		// LISTテーブルへ登録
		ListOpenHelper listOpenHelper = new ListOpenHelper(
				getApplicationContext()); // DB Helperの呼び出し
		SQLiteDatabase db = listOpenHelper.getWritableDatabase();
		ListTableDao listTableDao = new ListTableDao(db);

		// ------ トランザクション処理開始----------------
		db.beginTransaction();
		try {
			// グローバル変数から値の取得
			Globals globals = (Globals) getApplication();
			int totalCost = globals.totalCost; // 合計金額

			ListBean listBean = new ListBean();
			listBean.setListName(listNameStr); // リスト名（EditTextから取得）
			listBean.setTotalCost(totalCost); // グローバル変数から取得

			// インサート実行
			listTableDao.insert(listBean);

			// --------------------------------

			// PERSONテーブルデリート

			// PERSONテーブルへ登録
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

			db.setTransactionSuccessful(); // コミット
		} finally {
			// トランザクション処理終了----------------
			db.endTransaction();
		}

	}

}
