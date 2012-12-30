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
        
        //�u�ǉ��{�^���v�̎擾
        View btn_AddPerson = findViewById(R.id.btn_AddPerson);
        btn_AddPerson.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addPerson();
			}
		});
    }

    //���X�g�ɍ��ڂ�ǉ�����
    private void addPerson(){
    	// ���O���擾
    	EditText editTextPerson = (EditText) findViewById(R.id.eText_personName);
    	//���O���Z�b�g
    	String personName = editTextPerson.getText().toString();
    	PersonList person = new PersonList();
    	person.setPersonName(personName);
    	person.isAttendChecked();
    	person.setAttendChecked(false);
    	person.setPaidChecked(false);

        //���X�g�̎擾
    	personListArray.add(person);
        CustomPersonListAdapter personAdapter  = new CustomPersonListAdapter(getApplicationContext(), personListArray);
       
        setListAdapter(personAdapter);
    }

    private static final int MENU_ID_MENU1 = (Menu.FIRST + 1);
    private static final int MENU_ID_MENU2 = (Menu.FIRST + 2);
    private static final int MENU_ID_MENU3 = (Menu.FIRST + 3);
    private boolean visible = true;
    
    // �I�v�V�������j���[���ŏ��ɌĂяo����鎞��1�x�����Ăяo����܂�
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // ���j���[�A�C�e����ǉ����܂�
        menu.add(Menu.NONE, MENU_ID_MENU1, Menu.NONE, "�o�ȎҒǉ�");
        menu.add(Menu.NONE, MENU_ID_MENU2, Menu.NONE, "���z�ݒ�");
        menu.add(Menu.NONE, MENU_ID_MENU3, Menu.NONE, "CSV��荞��");
        return super.onCreateOptionsMenu(menu);
    }

    // �I�v�V�������j���[���\�������x�ɌĂяo����܂�
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(MENU_ID_MENU2).setVisible(visible);
        visible = !visible;
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
