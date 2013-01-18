package com.thoughtworks.twinout.db;

import java.text.ParseException;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thoughtworks.twinout.Util;

public class DateTimeDataSource {
	private static String[] ALL_COLUMNS = { Schema.COLUMN_ID, Schema.COLUMN_DATE_TIME };

	private DBHelper dbHelper;
	private SQLiteDatabase database;

	public DateTimeDataSource(Context context) {
		this.dbHelper = new DBHelper(context);
	}

	public void open() {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public Date getLastInDate() {
		Cursor cursor = database.query(Schema.TABLE_DATE_TIME, ALL_COLUMNS,
				null, null, null, null, Schema.COLUMN_DATE_TIME + " DESC");

		cursor.moveToFirst();
		String dateString = cursor.getString(1);

		Date date = null;
		try {
			date = Util.parse(dateString);
		} catch (ParseException e) {
		}
		
		// Make sure to close the cursor
		cursor.close();
		return date;
	}

	public boolean save(Date currentDate) {
		ContentValues values = new ContentValues();

		values.put(Schema.COLUMN_DATE_TIME, Util.format(currentDate));
		long insertId = database.insert(Schema.TABLE_DATE_TIME, null, values);

		return insertId > 0;
	}
}