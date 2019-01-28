package com.example.xareen.pwdmanager_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "OneKey.db";
    public static final String TABLE_NAME = "onekey_table";
    public static final String COL_ID = "ID";
    public static final String COL_TITLE = "Title";
    public static final String COL_USERNAME = "Username";
    public static final String COL_PWD = "Password";
    public static final String COL_PWD2 = "Password2";
    public static final String COL_EMAIL = "E-mail";
    public static final String COL_WEBSITE = "Website";
    public static final String COL_NOTES = "Notes";
    public static final String COL_WHEN_CREATED = "when_created";
    public static final String COL_LAST_MOD = "last_mod";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
