package com.thoughtworks.twinout.db;

import android.database.sqlite.SQLiteDatabase;

public class Schema {

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ DateTimeDataSource.TABLE_DATE_TIME + "(" + DateTimeDataSource.COLUMN_ID
			+ " integer primary key autoincrement, " + DateTimeDataSource.COLUMN_DATE_TIME
			+ " text not null);";

	public static void create(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void dropAll(SQLiteDatabase database) {
		database.execSQL("DROP TABLE IF EXISTS " + DateTimeDataSource.TABLE_DATE_TIME);
	}
	

}
