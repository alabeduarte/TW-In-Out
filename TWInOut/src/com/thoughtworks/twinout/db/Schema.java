package com.thoughtworks.twinout.db;

import android.database.sqlite.SQLiteDatabase;

public class Schema {

	public static final String TABLE_DATE_TIME = "date_times";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DATE_TIME = "_date_time";

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_DATE_TIME + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_DATE_TIME
			+ " text not null);";

	public static void create(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void dropAll(SQLiteDatabase database) {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE_TIME);
	}
	

}
