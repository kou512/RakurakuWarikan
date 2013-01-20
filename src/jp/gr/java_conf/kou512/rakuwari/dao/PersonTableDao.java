package jp.gr.java_conf.kou512.rakuwari.dao;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.kou512.rakuwari.bean.PersonBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonTableDao {
	//テーブル名・カラム名
	private static final String TABLE_NAME = "PERSON";
	private static final String COLUMN_LIST_NO = "list_no";
	private static final String COLUMN_PERSON_NO = "person_no";
	private static final String COLUMN_PERSON_NAME = "person_name";
	private static final String COLUMN_PERSON_COST = "person_cost";
	private static final String COLUMN_KOBETSU_FLG = "kobetsu_flg";
	private static final String COLUMN_MEMO = "memo";
	private static final String[] COLUMNS = {TABLE_NAME, COLUMN_LIST_NO, COLUMN_PERSON_NO,
		COLUMN_PERSON_NAME, COLUMN_PERSON_COST ,COLUMN_KOBETSU_FLG,COLUMN_MEMO };
	
	private SQLiteDatabase db;
	
	//コンストラクタ
	public PersonTableDao(SQLiteDatabase db){
		this.db = db;
	}
	
	//インサート処理
	public long insert(PersonBean person){
		ContentValues values = new ContentValues();
		values.put(COLUMN_LIST_NO, person.getListNo());
		values.put(COLUMN_PERSON_NO, person.getPersonNum());
		values.put(COLUMN_PERSON_NAME, person.getPersonName());
		values.put(COLUMN_PERSON_COST, person.getCost());
		values.put(COLUMN_KOBETSU_FLG, person.isKobetsuPay());
		values.put(COLUMN_MEMO, person.getMemo());
		//インサート実行
		return db.insert(TABLE_NAME, null, values);
	}
	
	//全項目取得
	public List<PersonBean> findAll(){
		List<PersonBean> personList = new ArrayList<PersonBean>();
		Cursor cursor= db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_PERSON_NO);
		
		while(cursor.moveToNext())
		{
			PersonBean personBean = new PersonBean();
			personBean.setListNo(cursor.getInt(1));
			personBean.setPersonNum(cursor.getInt(2));
			personBean.setPersonName(cursor.getString(3));
			personBean.setCost(cursor.getFloat(4));
			personBean.setKobetsuPay(cursor.getInt(5));

			personList.add(personBean);
		}
		return personList;
		
		}
	}
	
	
