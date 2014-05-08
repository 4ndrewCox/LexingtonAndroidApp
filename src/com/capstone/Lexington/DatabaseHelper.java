package com.capstone.Lexington;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	static ArrayList<Media> a;
	static String name = "lexington.sqlite";
	static String path = "";
	static final String dataPath = "/data/data/";
	static SQLiteDatabase sdb;
	String query = "";
	Context myContext;

	// constructor
	DatabaseHelper(Context context, String query) {
		
		super(context, name, null, 1);
	
		path = dataPath + context.getApplicationContext().getPackageName() + "/databases/";
		
		this.query = query;
        this.myContext = context;
	}
	
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
	
	public void createDataBase() throws IOException{
		 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
	
	private boolean checkDataBase(){
		 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = path + name;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
	
	private void copyDataBase() throws IOException{
		 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(name);
 
    	// Path to the just created empty db
    	String outFileName = path + name;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
	
    public void openDataBase() throws SQLException{
    	 
    	//Open the database
        String myPath = path + name;
    	sdb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }
	

    @Override
	public synchronized void close() {
 
    	    if(sdb != null)
    		    sdb.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}
	
	// close database
	public void closeDatabase() {
		sdb.close();
	}

	// get exhibit meta data
	public Exhibit getExhibit() 
	{

		Cursor localCursor = sdb.rawQuery(
				"SELECT * FROM obj_main WHERE qr_code = " + this.query, null);

		// new exhibit structure
		Exhibit localExhibit = new Exhibit();

		localCursor.moveToFirst();
		localExhibit.setQR(localCursor.getInt(0));
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
}