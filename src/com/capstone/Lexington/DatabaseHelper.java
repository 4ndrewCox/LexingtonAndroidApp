package com.capstone.Lexington;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	static ArrayList<Media> a;
	static String name = "lexington.sqlite";
	static String path = "";
	static SQLiteDatabase sdb;
	String query;

	// constructor
	DatabaseHelper(Context paramContext, String paramString) {
		
		super(paramContext, name, null, 1);
	
		// set databases path
		path = "/data/data/" + paramContext.getApplicationContext().getPackageName() + "/databases/";
		//String filePath = paramContext.getFilesDir().getPath();
		//path = filePath.substring(0, filePath.lastIndexOf("/")) + "/databases/";
		
		this.query = paramString;
	}
	
	// Concern: not referenced by anything
	public static DatabaseHelper getDBAdapter(Context paramContext, String paramString) {
		
		DatabaseHelper localDatabaseHelper;

		try {
			
			localDatabaseHelper = new DatabaseHelper(paramContext, paramString);

		} catch (Exception e) {
			Log.e("Database Helper", e.toString());
			return null;
		}

		return localDatabaseHelper;
	}
	
	private boolean checkDatabase() {
		
		boolean checkdb = false;
		   
		File dbfile = new File(path + name + "/");
		
		checkdb = dbfile.exists();
		
		return checkdb;
	}
	
	public boolean openDatabase() {
		
		//boolean checkdb = checkDatabase();
		boolean checkdb = true;
		
		if(checkdb)
		{
			try
			{
				sdb = SQLiteDatabase.openDatabase(path + name, null, SQLiteDatabase.OPEN_READONLY);
			}
			catch(SQLiteException e)
			{	

			}
			finally
			{
				return checkdb;
			}
		}
		else
		{
			Log.e("Database Helper", "Database does not exist");
			return checkdb;
		}

		
	}
	
	// close database
	public void closeDatabase() {
		sdb.close();
	}

	// get exhibit meta data
	public Exhibit getExhibits() {

		Cursor localCursor = sdb.rawQuery(
				"SELECT * FROM obj_main WHERE qr_code = " + this.query, null);

		// new exhibit structure
		Exhibit localExhibit = new Exhibit();

		localCursor.moveToFirst();
		localExhibit.setQr(localCursor.getInt(0));
		localExhibit.setName(localCursor.getString(1));
		localExhibit.setX(localCursor.getDouble(2));
		localExhibit.setY(localCursor.getDouble(3));
		localExhibit.setFloor(localCursor.getInt(4));
		localExhibit.setThumb(localCursor.getString(5));
		
		return localExhibit;
	}
	
	// get exhibit contents
	public ArrayList<Media> getMedia(int paramInt) {
		
		// get results of database query
		Cursor localCursor = sdb.rawQuery(
				"SELECT * FROM obj_media WHERE qr_code = " + this.query
						+ " AND media_type = " + paramInt, null);
		
		// holds exhibit data in media structure
		ArrayList<Media> a = new ArrayList<Media>();
		
		// read through database query results
		while (true) {
			
			// no more rows
			if (!localCursor.moveToNext())
			{
				return a;
			}
			
			// Concern: compiler assumed Media meant Audio.Media
			
			// new media structure
			Media localMedia = new Media();
			localMedia.setId(localCursor.getInt(0));
			localMedia.setType(localCursor.getInt(1));
			localMedia.setName(localCursor.getString(2));
			localMedia.setPath(localCursor.getString(3));
			localMedia.setDesc(localCursor.getString(4));
			localMedia.setQr(localCursor.getInt(5));
			a.add(localMedia);
		}
	}

	public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
		// do nothing
	}

	public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1,
			int paramInt2) {
		// do nothing
	}
}