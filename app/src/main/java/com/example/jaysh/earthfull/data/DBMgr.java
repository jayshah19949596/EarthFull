package com.example.jaysh.earthfull.data;

/**
 * Created by jaysh on 4/22/2017.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.Iterator;
import android.util.Log;


public class DBMgr {
    static final String DATABASE_NAME = "earthfull.db";

    static final int DATABASE_VERSION = 1;

    public final static String USER_TABLE = "user";

    public final static String USER_ID = "user_id";

    public final static String USER_NAME = "user_name";

    public final static String USER_NUMBER = "user_phone_number";

    public final static String USER_EMAIL = "user_email";

    public final static String USER_LOCATION = "user_location";

    public final static String USER_PASSWORD = "user_password";

    public final static String USER_REFERER = "user_referer";

    public final static String USER_POINTS = "user_points";


  /*===============================NGO==========================================*/


    public final static String NGO_TABLE = "ngo";

    public final static String NGO_ID = "ngo_id";

    public final static String NGO_NAME = "ngo_name";

    public final static String NGO_EMAIL = "nog_email";

    public final static String NGO_PASSWORD = "ngo_password";

    public final static String NGO_ADDRESS = "ngo_address";



  /*===============================EVENT==========================================*/

    public final static String EVENT_TABLE = "event";

    public final static String EVENT_NAME = "event_name";

    public final static String EVENT_DATE = "event_date";

    public final static String EVENT_TIME = "event_time";

    public final static String EVENT_ID = "_id";

    public final static String EVENT_LOCATION = "event_location";

    public final static String EVENT_DESCRIPTION = "event_description";

    public final static String EVENT_ORGANISER = "event_organiser";

    public final static String EVENT_TYPE = "event_type";



    /*===============================CREATES==========================================*/

    public final static String CREATES_TABLE = "creates";

    public final static String CREATES_ID = "create_id";

    public final static String CREATES_CATEGORY = "category";

    public final static String CREATES_NGO_EMAIL = "ngo_email";

    public final static String CREATES_EVENT_ID = "event_id";

    /*===============================REGISTERS==========================================*/

    public final static String REGISTERS_TABLE = "registers";

    public final static String REGISTERS_ID = "registers_id";

    public final static String REGISTERS_USER_EMAIL = "user_email";

    public final static String REGISTERS_EVENT_ID = "event_id";


    /*===============================REGISTERS==========================================*/

    public final static String REFERAL_TABLE = "referal";

    public final static String REFERAL_ID = "referal_id";

    public final static String REFERAL_REFERER = "referer";

    public final static String REFERAL_NEW_USER = "new_user";

    /*===============================GRADES==========================================*/

    public final static String GRADES_TABLE = "grades";

    public final static String GRADES_ID = "grades_id";

    public final static String GRADES_NGO_EMAIL = "ngo_email";

    public final static String GRADES_EVENT_ID = "event_id";

    public final static String GRADES_USER_EMAIL = "user_email";

    /*===============================BRANCHES==========================================*/

    public final static String BRANCHE_TABLE = "branche";

    public final static String BRANCHE_ID = "branche_id";

    public final static String BRANCHE_NAME = "branch_name";

    public final static String BRANCHE_NGO_EMAIL = "ngo_email";

    /*===========================================================================*/




    static final String DATABASE_CREATE_REFERAL = "create table if not exists " + REFERAL_TABLE + " ( "
            + REFERAL_ID+ " INTEGER ,"
            + REFERAL_REFERER+" text ,"+REFERAL_NEW_USER+" text, PRIMARY KEY ("+REFERAL_ID+" ,"+ REFERAL_REFERER+"));";

    static final String DATABASE_CREATE_USER = "create table if not exists " + USER_TABLE + " ( "
            + USER_ID + " INTEGER ,"
            + USER_NAME +" text, "+USER_NUMBER+ " INTEGER, "+USER_EMAIL+ " text ,"
            + USER_LOCATION +" text, "+USER_PASSWORD+" text, "+USER_REFERER+" text,"+USER_POINTS +" INTEGER,"
            + " PRIMARY KEY ("+USER_EMAIL+" ,"+ USER_ID+"), FOREIGN KEY ("+USER_REFERER+") REFERENCES "+REFERAL_TABLE+"("+REFERAL_REFERER+"));";


    static final String DATABASE_CREATE_NGO = "create table if not exists " + NGO_TABLE + " ( "
            + NGO_ID+ " INTEGER ,"
            + NGO_NAME+" text, "+NGO_EMAIL+"  text , "+NGO_PASSWORD+" text,"+NGO_ADDRESS+" text, " +
            "PRIMARY KEY ("+NGO_EMAIL+" ,"+ NGO_ID+"));";

    static final String DATABASE_CREATE_EVENT = "create table if not exists " + EVENT_TABLE + " ( "
            + EVENT_ID+ " INTEGER primary key AUTOINCREMENT," +EVENT_NAME+" text,"
            + EVENT_DATE+" text, "+EVENT_TIME+"  text, "+EVENT_LOCATION+" text,"+EVENT_ORGANISER+" text,"
            +EVENT_DESCRIPTION+" text );";

    static final String DATABASE_CREATE_CREATES = "create table if not exists " + CREATES_TABLE + " ( "
            + CREATES_ID+ " INTEGER primary key AUTOINCREMENT,"
            + CREATES_CATEGORY+" text, "+CREATES_NGO_EMAIL+"  text, "+CREATES_EVENT_ID+" INTEGER,"
            + " FOREIGN KEY ("+CREATES_NGO_EMAIL+") REFERENCES "+NGO_TABLE+"("+NGO_EMAIL+"), "
            + " FOREIGN KEY ("+CREATES_EVENT_ID+") REFERENCES "+EVENT_TABLE+"("+EVENT_ID+"));";

    static final String DATABASE_CREATE_REGISTERS = "create table if not exists " + REGISTERS_TABLE + " ( "
            + REGISTERS_ID+ " INTEGER primary key AUTOINCREMENT,"
            + REGISTERS_USER_EMAIL+" text, "+REGISTERS_EVENT_ID+"  INTEGER,"
            + " FOREIGN KEY ("+REGISTERS_USER_EMAIL+") REFERENCES "+USER_TABLE+"("+USER_EMAIL+"));";

    static final String DATABASE_CREATE_GRADES = "create table if not exists " + GRADES_TABLE + " ( "
            + GRADES_ID+ " INTEGER primary key AUTOINCREMENT,"
            + GRADES_NGO_EMAIL+" text, "+GRADES_EVENT_ID+" INTEGER, "+GRADES_USER_EMAIL+" text,"
            + " FOREIGN KEY ("+GRADES_NGO_EMAIL+") REFERENCES "+NGO_TABLE+"("+NGO_EMAIL+"), "
            + " FOREIGN KEY ("+GRADES_EVENT_ID+") REFERENCES "+EVENT_TABLE+"("+EVENT_ID+"), "
            + " FOREIGN KEY ("+GRADES_USER_EMAIL+") REFERENCES "+USER_TABLE+"("+USER_EMAIL+"));";

    static final String DATABASE_CREATE_BRANCHES = "create table if not exists " + BRANCHE_TABLE + " ( "
            + BRANCHE_ID+ " INTEGER ,"
            + BRANCHE_NAME+" text , "+BRANCHE_NGO_EMAIL+"  text,"
            +"PRIMARY KEY ("+BRANCHE_ID+" ,"+ BRANCHE_NAME+"),"
            + " FOREIGN KEY ("+BRANCHE_NGO_EMAIL+") REFERENCES "+NGO_TABLE+"("+NGO_EMAIL+"));";

    /*==============================================================================================*/

    public SQLiteDatabase db;
    private Context mContext;
    private DataBaseHelper dbHelper;
    private static DBMgr mUniqueInstance;

    public static DBMgr getInstance(Context context) {

        if (mUniqueInstance == null) {
            mUniqueInstance = new DBMgr(context);
        }
        return mUniqueInstance;
    }

    private DBMgr(Context context) {
        mContext = context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,
                DATABASE_VERSION);

        String s = String.valueOf(Preference.getInstance(mContext).loadBooleanKey(Preference.IS_FIRST_TIME, false));

        db = dbHelper.getWritableDatabase();
        if(!Preference.getInstance(mContext).loadBooleanKey(Preference.IS_FIRST_TIME, false)) {
            Log.d("DBMGr", "IN Preference");
            initialisingDataBase();

            Preference.getInstance(mContext).saveState(Preference.IS_FIRST_TIME, true);

        }
    }

    public void initialisingDataBase() {
        Log.d("DBMGr", "initialisingDataBase");
        ContentValues newValues1 = new ContentValues();
        newValues1.put(USER_NAME, "JAY");
        newValues1.put(USER_EMAIL, "jay@gmail.com");
        newValues1.put(USER_PASSWORD, "JAY");
        newValues1.put(USER_LOCATION, "Vintage Pads., 212 S.Cooper Street ");
        newValues1.put(USER_REFERER, "ANCHAL");
        newValues1.put(USER_POINTS,31);
        db.insert(USER_TABLE, null, newValues1);

        ContentValues newValues2 = new ContentValues();
        newValues2.put(NGO_NAME, "SAM");
        newValues2.put(NGO_PASSWORD, "SAM");
        newValues2.put(NGO_EMAIL, "ngo@gmail.com");
        newValues2.put(NGO_ADDRESS, "308 South West st. ");
        db.insert(NGO_TABLE, null, newValues2);

        ContentValues newValues3 = new ContentValues();
        newValues3.put(EVENT_NAME, "Garbage Collection - Govt.Clean Program");
        newValues3.put(EVENT_ID, 1);
        newValues3.put(EVENT_DATE, "05-04-2016");
        newValues3.put(EVENT_TIME, "10:35");
        newValues3.put(EVENT_LOCATION, "Dallas");
        newValues3.put(EVENT_DESCRIPTION,"Collecting Garbage from streets and recycling it");
        newValues3.put(EVENT_ORGANISER,"helping hand");
        db.insert(EVENT_TABLE, null, newValues3);


        ContentValues newValues5 = new ContentValues();
        newValues5.put(EVENT_NAME, "Tree Plantaion");
        newValues5.put(EVENT_ID, 2);
        newValues5.put(EVENT_DATE, "03-06-2016");
        newValues5.put(EVENT_TIME, "09:39");
        newValues5.put(EVENT_LOCATION, "Arlington");
        newValues5.put(EVENT_DESCRIPTION,"Promoting Afforestation, Planting Trees, And Having Fun");
        newValues5.put(EVENT_ORGANISER,"Mission Plant");
        db.insert(EVENT_TABLE, null, newValues5);

        ContentValues newValues4 = new ContentValues();
        newValues4.put(CREATES_CATEGORY, "Garbage Collection");
        newValues4.put(CREATES_NGO_EMAIL, "help_sharing.com");
        newValues4.put(CREATES_EVENT_ID, 1);

        db.insert(CREATES_TABLE, null, newValues4);


    }

    public DBMgr open() throws SQLException {
        return this;
    }

    public Cursor getSinlgeUser(String emailID) {
        Cursor cursor = db.query(USER_TABLE, null, USER_EMAIL+"=?",
                new String[]{emailID}, null, null, null);

        cursor.moveToFirst();

        return cursor;
    }

    public String getPassword(String emailID) {
        Cursor cursor = db.query(USER_TABLE, null, USER_EMAIL+"=?",
                new String[]{emailID}, null, null, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String Row = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
        cursor.close();
        return Row;
    }

    public Cursor getEventsLists(String event) {
        String selectQuery = "SELECT  "+EVENT_NAME+" FROM " + EVENT_TABLE +","+CREATES_TABLE+ " WHERE "+EVENT_TABLE+"."
                + EVENT_ID + " = "+CREATES_TABLE+"." + CREATES_EVENT_ID;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return cursor;
        }
        cursor.moveToFirst();

        return cursor;
    }

    public Cursor getEventsList(String event) {
        Cursor cursor = db.query(EVENT_TABLE, null, null,
                null, null, null, null);

        cursor.moveToFirst();
        String cstr = cursor.getString(cursor.getColumnIndex(EVENT_DESCRIPTION));
        Log.d("DBMGr", cstr);
        return cursor;
    }

    public Cursor singleEvent(String description) {
        Cursor cursor = db.query(EVENT_TABLE, null, EVENT_DESCRIPTION+"=?",
                new String[]{description}, null, null, null);

        cursor.moveToFirst();
        String cstr = cursor.getString(cursor.getColumnIndex(EVENT_DESCRIPTION));
        Log.d("DBMGr", cstr);
        return cursor;
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

}