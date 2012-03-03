package com.lawal.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLDbAdapter extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "reducerDB.db";
	private static final String DICT_TABLE = "dict_table";
	private static final String WORD_COL = "word";
	private static final String ABBR_COL = "abbr";
	private static final String TYPE_COL = "type";

	public static final String DRAFTS_TABLE = "drafts_table";
	public static final String DRAFT_BODY_COL = "draft";
	public static final String RECIPIENT_COL = "recipient";

	private static final int DATABASE_VERSION = 1;
	private static final String TAG = "sqldbadapter";
	private final Context _context;

	private static final String CREATE_TABLE_DICTIONARY =

	"CREATE TABLE " + DICT_TABLE
			+ " ( _id integer NOT NULL primary key autoincrement, " + WORD_COL
			+ " varchar(30) NOT NULL," + TYPE_COL + " varchar(30) NOT NULL,"
			+ ABBR_COL + " varchar(30) NOT NULL );";

	private static final String CREATE_TABLE_DRAFTS =

	"CREATE TABLE " + DRAFTS_TABLE
			+ " ( _id integer NOT NULL primary key autoincrement, "
			+ DRAFT_BODY_COL + " test NOT NULL," + RECIPIENT_COL
			+ " varchar(15) NOT NULL);";

	public SQLDbAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		_context = context;
	}

	public void execMultipleSQL(SQLiteDatabase db, String[] sql) {
		for (String s : sql)
			if (s.trim().length() > 0) {
				Log.i(TAG, s);
				db.execSQL(s);
			}
	}

	public String getAbbrIfExist(String word) {

		String sql = "SELECT * FROM " + DICT_TABLE + " WHERE word = ?";
		SQLiteDatabase d = getReadableDatabase();
		Cursor c = d.rawQuery(sql, new String[] { word });

		if (c.getCount() > 0) {
			c.moveToFirst();
			int ind = c.getColumnIndexOrThrow("abbr");

			System.out.println("SQLDbAdapter.getAbbrIfExist() " + ind);
			return c.getString(ind);
		}

		return word;

	}

	/*
	 * This method gets the draft from the drafts table depending on the shorten
	 * parameter; if it is true; it returns only the first 30 characters of the
	 * body column
	 */
	public Cursor getDrafts(boolean shorten_body) {
		String sql = "";

		if (shorten_body) {
			// shorten the text body
			// TODO find a more elegant solution to this
			sql = "SELECT _id, recipient,substr(draft,0,30) as draft FROM "
					+ DRAFTS_TABLE;
		} else {
			sql = "SELECT * FROM " + DRAFTS_TABLE;
		}

		SQLiteDatabase d = getReadableDatabase();
		Cursor c = d.rawQuery(sql, null);

		if (c.getCount() > 0) {
			return c;
		}
		return null;
	}

	public boolean deleteDraft(long rowId) {
		boolean success = true;
		SQLiteDatabase d = getReadableDatabase();
		return d.delete(DRAFTS_TABLE, "_id=" + rowId, null) > 0;
	}

	public boolean insertWord(String word, String abbr, AbbrType type) {
		boolean success = true;
		SQLiteDatabase d = getReadableDatabase();

		try {

			String sql = String.format("INSERT INTO " + DICT_TABLE
					+ " (word, abbr , type )" + " VALUES ('%s','%s','%s'  ) ",
					word, abbr, type.toString());
			d.execSQL(sql);
			Log.v("inserted", word + "," + abbr);

		} catch (Exception e) {
			success = false;
		} finally {
			d.close();
		}
		System.out.println("SQLDbAdapter.insertWord() " + success);
		return success;
	}

	public boolean saveDraft(String draft_body, String draft_recipient) {
		boolean success = true;
		SQLiteDatabase d = getReadableDatabase();

		try {

			String sql = String.format("INSERT INTO " + DRAFTS_TABLE
					+ " (draft, recipient )" + " VALUES ('%s','%s') ",
					draft_body, draft_recipient);
			d.execSQL(sql);
			Log.v("inserted", draft_body + "," + draft_recipient);

		} catch (Exception e) {
			success = false;
		} finally {
			d.close();
		}
		System.out.println("SQLDbAdapter.saveDraft() " + success);
		return success;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String[] sql = new String[] { CREATE_TABLE_DICTIONARY,
				CREATE_TABLE_DRAFTS };
		db.beginTransaction();

		try {
			// Create tables & test data
			execMultipleSQL(db, sql);
			// db.setTransactionSuccessful();
			db.setTransactionSuccessful();

			//
			System.out.println("SQLDbAdapter created");
		} catch (SQLException e) {
			Log.e("Error creating tables and debug data", e.toString());
		} finally {
			db.endTransaction();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading Database from version " + oldVersion + " to "
				+ newVersion);
		// db.execSQL("DROP TABLE IF EXISTS " + DICT_TABLE);
		// db.execSQL("DROP TABLE IF EXISTS " + DRAFTS_TABLE);

		// onCreate(db);
	}

}
