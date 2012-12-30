package com.example.warikan;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	ListView personList;
	ArrayAdapter<String> personList_adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        


        
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

        //リストの取得
        ArrayList<PersonList> personList = new ArrayList<PersonList>();    
        personList.add(person);
        CustomPersonListAdapter personAdapter  = new CustomPersonListAdapter(getApplicationContext(), personList);
       
        setListAdapter(personAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
