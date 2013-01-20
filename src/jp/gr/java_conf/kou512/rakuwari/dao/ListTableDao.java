package jp.gr.java_conf.kou512.rakuwari.dao;

import java.util.ArrayList;
import java.util.List;

import jp.gr.java_conf.kou512.rakuwari.bean.ListBean;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ListTableDao {
	public static final String TABLE_NAME = "LIST";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_LIST_NO = "list_no";
	public static final String COLUMN_LIST_NAME = "list_name";
	public static final String COLUMN_TOTAL_COST = "total_cost";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_LIST_NO, COLUMN_LIST_NAME,COLUMN_TOTAL_COST };

	private SQLiteDatabase db;
	
	//コンストラクタ
	public ListTableDao(SQLiteDatabase db){
		this.db = db;
	}
	
	//インサート処理
	public long insert(ListBean listBean){
		ContentValues values = new ContentValues();
		values.putNull(COLUMN_LIST_NO);
		values.put(COLUMN_LIST_NAME, listBean.getListName());
		values.put(COLUMN_TOTAL_COST, listBean.getTotalCost());
		//インサート実行
		return db.insert(TABLE_NAME, null, values);
	}
	
	//全項目取得
	public List<ListBean> findAll(){
		List<ListBean> listList = new ArrayList<ListBean>();
		String sqlString = "select * from LIST";
		Cursor cursor= db.rawQuery(sqlString, null);
		
		while(cursor.moveToNext())
		{
			ListBean listBean = new ListBean();
			listBean.setListNo(cursor.getInt(1));
			listBean.setListName(cursor.getString(2));
			listBean.setTotalCost(cursor.getInt(3));

			listList.add(listBean);
		}
		cursor.close();
		return listList;
		
		}
	}