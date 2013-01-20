package jp.gr.java_conf.kou512.rakuwari.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonOpenHelper extends SQLiteOpenHelper {

	final static private int DB_VERSION = 1;
	
	public PersonOpenHelper(Context context) {
		super(context, null, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
        db.execSQL(
        		"create table PERSON (" +
        				"_id  integer primary key autoincrement not null," +
        				"list_no integer not null," +
        				"person_no text not null," +
        				"person_name text not null" +
        				"person_cost integer not null" +
        				"kobetsu_flg integer not null" +
        				"memo text" +
        				");"
    );
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
