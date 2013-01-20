package jp.gr.java_conf.kou512.rakuwari.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListOpenHelper extends SQLiteOpenHelper {
	final static private int DB_VERSION = 1;
	
	//コンストラクタ
	public ListOpenHelper(Context context) {
		super(context, "database", null, DB_VERSION);
	}
	
	//テーブル作成
	@Override
	public void onCreate(SQLiteDatabase db) {	
        db.execSQL(
        		"create table LIST (" +
        				"_id  integer primary key autoincrement not null," +
        				"list_no integer ," +
        				"list_name text, " +
        				"total_cost integer not null" +
        				");"
    );
        db.execSQL(
        		"create table PERSON (" +
        				"_id  integer primary key autoincrement not null," +
        				"list_no integer ," +
        				"person_no text not null," +
        				"person_name text not null, " +
        				"person_cost integer not null, " +
        				"kobetsu_flg integer not null, " +
        				"memo text" +
        				");"
    );

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
