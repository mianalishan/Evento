package com.example.evento;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Mysql extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "MyDBName.db";
	   public static final String CONTACTS_TABLE_NAME = "contacts";
	   public static final String CONTACTS_COLUMN_ID = "id";
	   public static final String CONTACTS_COLUMN_NAME = "name";
	   public static final String CONTACTS_COLUMN_DATE = "date";
	   public static final String CONTACTS_COLUMN_TIME = "time";
	   public static final String CONTACTS_COLUMN_MAIL = "mail";
	   public static final String CONTACTS_COLUMN_add = "palace";
	   private HashMap hp;

	   public Mysql(Context context)
	   {
	      super(context, DATABASE_NAME , null, 1);
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	      // TODO Auto-generated method stub
	      db.execSQL(
	      "create table Events " +
	      "(id integer primary key AUTOINCREMENT, name text,date text,time text, mail text,place text)"
	      );
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      // TODO Auto-generated method stub
	      db.execSQL("DROP TABLE IF EXISTS Events");
	      onCreate(db);
	   }

	   public boolean insertContact  (String name, String date, String time, String mail,String place)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	     
	      contentValues.put("name", name);
	      contentValues.put("date", date);
	      contentValues.put("time", time);	
	      contentValues.put("mail", mail);
	      contentValues.put("place", place);
	      db.insert("Events", null, contentValues);
	      return true;
	   }
	   
	   public Cursor getData(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from Events where id="+id+"", null );
	      return res;
	   }
	   
	   public int numberOfRows(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
	      return numRows;
	   }
	   
	   public boolean updateContact (Integer id, String name, String date, String time, String mail,String place)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      ContentValues contentValues = new ContentValues();
	      contentValues.put("name", name);
	      contentValues.put("date",date);
	      contentValues.put("time", time);
	      contentValues.put("mail", mail);
	      contentValues.put("place", place);
	      db.update("Events", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
	      return true;
	   }

	   public Integer deleteContact (Integer id)
	   {
	      SQLiteDatabase db = this.getWritableDatabase();
	      return db.delete("Events", 
	      "id = ? ", 
	      new String[] { Integer.toString(id) });
	   }
	   
	   public ArrayList<String> getAllCotacts()
	   {
		   
	      ArrayList<String> array_list = new ArrayList<String>();
	      
	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from Events", null );
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex("name")));
	         res.moveToNext();
	      }
	      res.close();
	   return array_list;
	   }
	   public ArrayList<String> todayEvent()
	   {
		   
	      ArrayList<String> array_list = new ArrayList<String>();
	      
	      //hp = new HashMap();
	      SQLiteDatabase db = this.getReadableDatabase();
	      Calendar c = Calendar.getInstance();
      	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
      	String formattedDate = df.format(c.getTime());
	      Cursor res =  db.rawQuery( "select * from Events where date=?" ,new String[] { formattedDate } );
	    
	      res.moveToFirst();
	      
	      while(res.isAfterLast() == false){
	         array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
	         res.moveToNext();
	      }
	      res.close();
	   return array_list;
	   }
	   public ArrayList<String>[] getAllEvent()
	   {
		   
	      ArrayList<String>[] array_list = (ArrayList<String>[])new ArrayList[6];
	      
	      //hp = new HashMap();
	      
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from Events", null );
	      
	      res.moveToFirst();
	      array_list[0]=new ArrayList<String>();
	      array_list[1]=new ArrayList<String>();
	      array_list[2]=new ArrayList<String>();
	      array_list[3]=new ArrayList<String>();
	      array_list[4]=new ArrayList<String>();
	      array_list[5]=new ArrayList<String>();
	      while(res.isAfterLast() == false){
	         array_list[0].add(res.getString(res.getColumnIndex("name")));
	         array_list[1].add(res.getString(res.getColumnIndex("date")));
	         array_list[2].add(res.getString(res.getColumnIndex("time")));
	         array_list[3].add(res.getString(res.getColumnIndex("mail")));
	         array_list[4].add(res.getString(res.getColumnIndex("place")));
	         array_list[5].add(res.getString(res.getColumnIndex("id")).toString());
	         res.moveToNext();
	      }
	      res.close();
	   return array_list;
	   }
	
}