package jp.gr.java_conf.kou512.rakuwari;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	ListView personList;
	ArrayAdapter<String> personList_adapter;
	ArrayList<PersonList> personListArray;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        personListArray = new ArrayList<PersonList>();    
        
        //「追加ボタン」の取得
        View btn_AddPerson = findViewById(R.id.btn_AddPerson);
        btn_AddPerson.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addPerson();
			}
		});
    }

    //リストに項目を追加する
    private void addPerson(){
    	// 名前を取得
    	EditText editTextPerson = (EditText) findViewById(R.id.eText_personName);
    	//名前をセット
    	String personName = editTextPerson.getText().toString();
    	PersonList person = new PersonList();
    	person.setPersonName(personName);
    	person.isAttendChecked();
    	person.setAttendChecked(false);
    	person.setPaidChecked(false);

        //リストの取得
    	personListArray.add(person);
        CustomPersonListAdapter personAdapter  = new CustomPersonListAdapter(getApplicationContext(), personListArray);
       
        setListAdapter(personAdapter);
    }

    private static final int MENU_ID_MENU1 = (Menu.FIRST + 1);
    private static final int MENU_ID_MENU2 = (Menu.FIRST + 2);
    private static final int MENU_ID_MENU3 = (Menu.FIRST + 3);
    private boolean visible = true;
    
    // オプションメニューが最初に呼び出される時に1度だけ呼び出されます
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // メニューアイテムを追加します
        menu.add(Menu.NONE, MENU_ID_MENU1, Menu.NONE, "出席者追加");
        menu.add(Menu.NONE, MENU_ID_MENU2, Menu.NONE, "金額設定");
        menu.add(Menu.NONE, MENU_ID_MENU3, Menu.NONE, "CSV取り込み");
        return super.onCreateOptionsMenu(menu);
    }

    // オプションメニューが表示される度に呼び出されます
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(MENU_ID_MENU2).setVisible(visible);
        visible = !visible;
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
            break;
        case MENU_ID_MENU2:
            ret = true;
            break;
        case MENU_ID_MENU3:
            ret = true;
            break;
        }
        return ret;
    }}
