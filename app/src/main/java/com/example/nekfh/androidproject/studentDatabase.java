package com.example.nekfh.androidproject;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;
import android.widget.Toast;

public class studentDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "student2.db";
//tables
public static final String TABLE_NAME = "studentGrades";
    public static final String COLUMN_ID = "studentID";
    public static final String COLUMN_NAME = "studentName";
    public static final String COLUMN_GPA = "GPA";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_POINTS = "points";

    public studentDatabase(Context context){

       super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStmt = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_NAME + " TEXT," +
                COLUMN_GPA + " DOUBLE, " +
                COLUMN_HOURS + " INTEGER," +
                COLUMN_POINTS + " DOUBLE" +");";
        db.execSQL(sqlStmt);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d("DB", "The table has been removed!");
        onCreate(db);
    }


//Warning ugly code here:





    public void addRecord(int stdID, String stdName,double stdGPA,int stdHours, double stdPoints){


        SQLiteDatabase db = getWritableDatabase();

        ContentValues rowValues= new ContentValues();

        rowValues.put("studentID",stdID);
        rowValues.put("studentName",stdName);
        rowValues.put("GPA",stdGPA);
        rowValues.put("hours",stdHours);
        rowValues.put("points",stdPoints);
        db.insert("studentGrades",null,rowValues);
        //long rowPosition =db.insert("studentGrades",null,rowValues);

        // db.execSQL("insert into "+ TABLE_NAME + "("+COLUMN_NAME + ","+ COLUMN_PHONE
        //  +") VALUES (?,?)", new String[] {naStr,phStr});




       // db.close();
    }


    public void updateRecord(String id,double stdGPA,int stdHours,double stdPoints){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues rowValues= new ContentValues();

        rowValues.put("GPA",stdGPA);
        rowValues.put("hours",stdHours);
        rowValues.put("points",stdPoints);
        db.update("studentGrades",rowValues,"studentID="+id,null);
    }


    // more clean code

    public Cursor getData(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from studentGrades " ,null);

        return res;

    }

    public Cursor sortByGpa(){

        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select * from studentGrades ORDER BY GPA DESC" ,null);

        return res;

    }

    public String delTsk(String id){

        String text="no matched ID found";


        SQLiteDatabase db=getWritableDatabase();
       // db.execSQL("DELETE FROM "+ TABLE_NAME +" WHERE " + COLUMN_ID + "= ?;", new String[] {id} );
       int x= db.delete(TABLE_NAME, COLUMN_ID + "=" + id, null);
       if (x==1)
           return text="record  deleted!";
       else return text="No such ID";


    }

    public Cursor getGPA(String id){

        SQLiteDatabase db = getReadableDatabase();

        Cursor query = db.rawQuery("select * from studentGrades where studentID='"+id+"'",null);




       // if (cursor != null) {
        //    cursor.moveToFirst();
       // }
       // return cursor;

        return query;

    }
}
