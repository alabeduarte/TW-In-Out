package com.thoughtworks.twinout.db;

import java.text.ParseException;
import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thoughtworks.twinout.Util;

public class DateTimeDataSource {
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_DATE_TIME = "_date_time";
	private static final String TABLE_DATE_TIME = "date_times";
	private static String[] ALL_COLUMNS = { COLUMN_ID, COLUMN_DATE_TIME };
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_DATE_TIME + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_DATE_TIME
			+ " text not null);";

	private DBHelper dbHelper;
	private SQLiteDatabase database;

	public DateTimeDataSource(DBHelper dbHelper) {
		this.dbHelper = dbHelper;
	}
	
	public static String createTable() {
		return DATABASE_CREATE;
	}
	
	public static String dropTable() {
		return "DROP TABLE IF EXISTS " + TABLE_DATE_TIME;
	}
	
	public Date getLastInDate() {
		database = dbHelper.open();
		Cursor cursor = database.query(TABLE_DATE_TIME, ALL_COLUMNS,
				null, null, null, null, COLUMN_DATE_TIME + " DESC");

		cursor.moveToFirst();
		String dateString = cursor.getString(1);

		Date date = null;
		try {
			date = Util.parse(dateString);
		} catch (ParseException e) {
		}
		
		// Make sure to close the cursor
		cursor.close();
		dbHelper.close();
		return date;
	}

	public boolean save(Date currentDate) {
		database = dbHelper.open();
		ContentValues values = new ContentValues();

		values.put(COLUMN_DATE_TIME, Util.format(currentDate));
		long insertId = database.insert(TABLE_DATE_TIME, null, values);

		dbHelper.close();

		return insertId > 0;
	}
}