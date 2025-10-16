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
    public static final String COL_ID ="ID";
    public static final String COL_Name = "Username";
    public static final String COL_Password = "Password";
    public static final String BOOK_TABLE = "Books";
    public static final String BOOK_ID = "BookID";
    public static final String BOOK_TITLE = "Title";
    public static final String BOOK_AUTHOR = "Author";
    public static final String BOOK_COST = "COST";





    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_Name + " TEXT, " +
                COL_Password + " TEXT)";
        db.execSQL(CREATE_USERS);

        String CREATE_BOOK = "CREATE TABLE " + BOOK_TABLE + " (" +
                BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOK_TITLE + " TEXT, " +
                BOOK_AUTHOR + " TEXT, " +
                BOOK_COST + "INTEGER, " +
                "BookImage TEXT)";
        db.execSQL("INSERT INTO " + BOOK_TABLE + " (" +
            BOOK_TITLE + ", " + BOOK_AUTHOR + ", " + BOOK_COST + ", BookImage) VALUES " +
            "('The Odyssey', 'โฮเมอร์ (Homer)',899, 'https://press.uchicago.edu/.imaging/mte/ucp/medium/dam/ucp/books/jacket/978/02/26/60/9780226604428.jpg/jcr:content/9780226604428.jpg')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_STUDENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String Drop_BOOK_NAME = "DROP TABLE IF EXISTS " + BOOK_TABLE;
        db.execSQL(DROP_STUDENT_TABLE);
        db.execSQL(Drop_BOOK_NAME);
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_Name + " = ? AND " + COL_Password + " = ?", new String[]{Username, Password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;


    }
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
    public Cursor getAllBooks(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + BOOK_TABLE,null);
    }

}