package com.thoughtworks.twinout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public static final String TABLE_DATE_TIME = "date_times";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DATE_TIME = "_date_time";

	private static final String DATABASE_NAME = "twinout.db";
	private static final int DATABASE_VERSION = 1;
	private static String[] ALL_COLUMNS = { COLUMN_ID, COLUMN_DATE_TIME };
	private SQLiteDatabase database;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_DATE_TIME + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_DATE_TIME
			+ " text not null);";

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

	public void open() throws SQLException {
		database = getWritableDatabase();
	}

	public Date getLastInDate() {
		open();
		Cursor cursor = database.query(DBHelper.TABLE_DATE_TIME, ALL_COLUMNS,
				null, null, null, null, COLUMN_DATE_TIME + " DESC");

		cursor.moveToFirst();
		String dateString = cursor.getString(1);

		SimpleDateFormat formatedDate = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date date = null;
		try {
			date = formatedDate.parse(dateString);
		} catch (ParseException e) {
		}
		
		// Make sure to close the cursor
		cursor.close();
		close();
		return date;
	}

	public boolean save(Date currentDate) {
		open();
		SimpleDateFormat formatedDate = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		ContentValues values = new ContentValues();

		values.put(COLUMN_DATE_TIME, formatedDate.format(currentDate));
		long insertId = database.insert(DBHelper.TABLE_DATE_TIME, null, values);

		close();

		return insertId > 0;
	}

}