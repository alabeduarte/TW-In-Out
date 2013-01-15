package com.thoughtworks.twinout;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TABLE_DATE_TIME = "date_times";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DATE = "_date";
	public static final String COLUMN_TIME = "_time";

	private static final String DATABASE_NAME = "twinout.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_DATE_TIME + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " 
			+ COLUMN_DATE + " date not null, " 
			+ COLUMN_TIME + " time not null);";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBHelper.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE_TIME);
		onCreate(db);
	}

	public static Date getLastInDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void save(Date currentDate) {
		// TODO Auto-generated method stub

	}

}
