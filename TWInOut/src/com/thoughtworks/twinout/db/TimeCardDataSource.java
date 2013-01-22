package com.thoughtworks.twinout.db;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thoughtworks.twinout.TimeCard;
import com.thoughtworks.twinout.TimeCardType;
import com.thoughtworks.twinout.Util;

public class TimeCardDataSource {
	private static String[] ALL_COLUMNS = { Schema.COLUMN_ID, Schema.COLUMN_DATE_TIME, Schema.COLUMN_TYPE };

	private DBHelper dbHelper;
	private SQLiteDatabase database;

	public TimeCardDataSource(Context context) {
		this.dbHelper = new DBHelper(context);
	}

	public void open() {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public TimeCard getLastInDate() {
		TimeCard timeCard;
		Cursor cursor = database.query(Schema.TABLE_TIME_CARD, ALL_COLUMNS,
				null, null, null, null, Schema.COLUMN_DATE_TIME + " DESC");

		cursor.moveToFirst();
		
		Long id = cursor.getLong(0);
		String dateString = cursor.getString(1);
		int type = cursor.getInt(2);
		
		Date date = null;
		date = Util.parse(dateString);
		
		timeCard = new TimeCard(id, date, TimeCardType.getType(type));
		// Make sure to close the cursor
		cursor.close();
		return timeCard;
	}

	public TimeCard save(Date currentDate, TimeCardType type) {
		
		String formatedDate = Util.format(currentDate);
		
		TimeCard timeCard = new TimeCard();
		timeCard.setDateTime(Util.parse(formatedDate));
		timeCard.setType(type);
		
		ContentValues values = new ContentValues();

		values.put(Schema.COLUMN_DATE_TIME, formatedDate);
		values.put(Schema.COLUMN_TYPE, timeCard.getType().getCode());
		timeCard.setId(database.insert(Schema.TABLE_TIME_CARD, null, values));

		return timeCard;
	}
}