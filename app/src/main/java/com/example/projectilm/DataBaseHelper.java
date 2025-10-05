package com.example.projectilm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserDB.db";
    public static final String TABLE_NAME = "Users";
    public static final String COL_Name = "Username";
    public static final String COL_Password = "Password";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS = "CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "COL_Name TEXT," +
                "COL_Password TEXT)";
        db.execSQL(CREATE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_STUDENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_STUDENT_TABLE);
        onCreate(db);


    }

    public boolean insertUser(String Username, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_Name, Username);
        values.put(COL_Password, Password);

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;

    }

    public boolean checkUsernamePassword(String Username, String Password) {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME, "USERNAME = ? AND PASSWORD = ?", new String[]{Username, Password});

        if (count > 0){
            return true;
        }else {
            return false;
        }


    }
}