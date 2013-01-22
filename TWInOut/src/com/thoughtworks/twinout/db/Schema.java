package com.thoughtworks.twinout.db;

import android.database.sqlite.SQLiteDatabase;

public class Schema {

	public static final String TABLE_TIME_CARD = "timecard";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DATE_TIME = "_date_time";
	public static final String COLUMN_TYPE = "_type";

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_TIME_CARD + "(" 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_DATE_TIME + " text not null, " 
			+ COLUMN_TYPE + " integer not null);";

	public static void create(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void dropAll(SQLiteDatabase database) {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME_CARD);
	}
	

}
