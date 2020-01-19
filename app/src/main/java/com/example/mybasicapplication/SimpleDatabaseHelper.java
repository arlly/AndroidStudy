package com.example.mybasicapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SimpleDatabaseHelper extends SQLiteOpenHelper {
    static final private String DBNAME = "sample.sqlite";
    static final private int VERSION = 1;

    public SimpleDatabaseHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE books (isbn TEXT PRIMARY KEY, title TEXT, price INTEGER)");

        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-5', 'テストです', 2980)");
        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-6', 'テストです2', 2981)");
        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-7', 'テストです3', 2982)");
        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-8', 'テストです4', 2983)");
        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-9', 'テストです5', 2984)");
        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-10', 'テストです6', 2985)");
        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-11', 'テストです7', 2986)");
        db.execSQL("INSERT INTO books (isbn, title, price) VALUES ('1234-12', 'テストです8', 2987)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }
}
