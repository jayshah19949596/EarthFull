package com.example.jaysh.earthfull.data;

/**
 * Created by jaysh on 4/22/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        Log.d("DataBaseHelper", "DataBaseHelper");
        _db.execSQL(DBMgr.DATABASE_CREATE_USER);
        _db.execSQL(DBMgr.DATABASE_CREATE_NGO);
        _db.execSQL(DBMgr.DATABASE_CREATE_EVENT);
        _db.execSQL(DBMgr.DATABASE_CREATE_CREATES);
        _db.execSQL(DBMgr.DATABASE_CREATE_REGISTERS);
        _db.execSQL(DBMgr.DATABASE_CREATE_REFERAL);
        _db.execSQL(DBMgr.DATABASE_CREATE_GRADES);
        _db.execSQL(DBMgr.DATABASE_CREATE_BRANCHES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        Log.w("TaskDBAdapter", "Upgrading from version " + _oldVersion + " to "
                + _newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.USER_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.NGO_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.EVENT_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.CREATES_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.REGISTERS_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.REFERAL_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.GRADES_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS " + DBMgr.BRANCHE_TABLE);

        onCreate(_db);
    }

}
