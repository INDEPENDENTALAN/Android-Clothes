package com.android.clothes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpenHelperN_Clothes extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DATABASE_N_CLOTHES";
    private static final int DATABASE_VERSION = 1;

    public SQLiteOpenHelperN_Clothes(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE n_profile(_email TEXT PRIMARY KEY,name TEXT,picture TEXT,birthday TEXT,phone TEXT,gender TEXT)");
        db.execSQL("CREATE TABLE n_clothes(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,picture TEXT,price TEXT,is_lovely INTEGER,is_opinion INTEGER,opinion TEXT,is_purchases INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
