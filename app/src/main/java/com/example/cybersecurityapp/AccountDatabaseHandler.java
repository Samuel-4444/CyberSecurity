package com.example.cybersecurityapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AccountDatabaseHandler extends SQLiteOpenHelper {

    public AccountDatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE accounts(email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS accounts");
    }

    public Boolean insertAccount(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("accounts", null, contentValues);

        return result != -1;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM accounts WHERE email = ?", new String[] {email});
        return cursor.getCount() > 0;
    }

    public Boolean checkAccountDetails(String email, String providedPassword){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM accounts WHERE email = ? AND password = ?", new String[] {email,providedPassword});
        return cursor.getCount() > 0;
    }
}
