package com.example.projectilm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserDB.db";
    public static final String TABLE_NAME = "Users";
    public static final String COL_ID = "ID";
    public static final String COL_Name = "Username";
    public static final String COL_Password = "Password";
    public static final String COL_Email = "Email";  // เพิ่มคอลัมน์ Email

    public static final String BOOK_TABLE = "Books";
    public static final String BOOK_ID = "BookID";
    public static final String BOOK_TITLE = "Title";
    public static final String BOOK_AUTHOR = "Author";
    public static final String BOOK_COST = "Cost";
    public static final String BookImage = "BookImage";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 5);  // เพิ่มเวอร์ชันเป็น 5 เพื่อให้เกิดการอัพเกรด
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_Email + " TEXT, " +  // ย้าย Email มาเป็นคอลัมน์แรกหลัง ID
                COL_Name + " TEXT, " +
                COL_Password + " TEXT)";
        db.execSQL(CREATE_USERS);


        String CREATE_BOOK = "CREATE TABLE " + BOOK_TABLE + " (" +
                BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOK_TITLE + " TEXT, " +
                BOOK_AUTHOR + " TEXT, " +
                BOOK_COST + " INTEGER, " +
                BookImage + " TEXT)";
        db.execSQL(CREATE_BOOK);


        db.execSQL("INSERT INTO " + BOOK_TABLE + " (" +
                BOOK_TITLE + ", " + BOOK_AUTHOR + ", " + BOOK_COST + ", " + BookImage +
                ") VALUES ('The Odyssey', 'Homer', 899, 'odyssey')" +
                ", ('Don Quixote de la Mancha', 'Miguel de Cervantes Saavedra', 599, 'don_quixote')" +
                ", ('Divine Comedy', 'Dante Alighieri', 699, 'dante')" +
                ", ('Call of Cthulhu', 'H.P Lovecraft', 1099, 'nuad')" +
                ", ('Murder on the Orient Express', 'Agatha Christie', 999, 'murderonorientexpress')" +
                ", ('Crime and Punishment', 'Fyodor Dostoevsky', 1199, 'crimeandpunish')"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 5) {  // ถ้าเวอร์ชันเก่ากว่า 5
            // สร้างตารางชั่วคราวเพื่อเก็บข้อมูลเดิม
            db.execSQL("CREATE TABLE temp_users AS SELECT * FROM " + TABLE_NAME);
            // ลบตารางเดิม
            db.execSQL("DROP TABLE " + TABLE_NAME);
            // สร้างตารางใหม่พร้อมคอลัมน์ Email ในตำแหน่งใหม่
            String CREATE_USERS = "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_Email + " TEXT, " +
                    COL_Name + " TEXT, " +
                    COL_Password + " TEXT)";
            db.execSQL(CREATE_USERS);
            db.execSQL("INSERT INTO " + TABLE_NAME + "(" +
                    COL_ID + ", " +
                    COL_Email + ", " +
                    COL_Name + ", " +
                    COL_Password +
                    ") SELECT " +
                    COL_ID + ", " +
                    COL_Email + ", " +
                    COL_Name + ", " +
                    COL_Password +
                    " FROM temp_users");
            // ลบตารางชั่วคราว
            db.execSQL("DROP TABLE temp_users");
        }

        // อัพเกรดตาราง Books ตามปกติ
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE);
        onCreate(db);
    }

    public boolean insertUser(String Username, String Password, String Email) {  // เพิ่ม parameter Email
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_Name, Username);
        values.put(COL_Password, Password);
        values.put(COL_Email, Email);  // เพิ่ม Email

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean checkUsernamePassword(String Username, String Password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_Name + " = ? AND " + COL_Password + " = ?",
                new String[]{Username, Password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BOOK_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(BOOK_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_TITLE));
                String author = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_AUTHOR));
                int cost = cursor.getInt(cursor.getColumnIndexOrThrow(BOOK_COST));
                String image = cursor.getString(cursor.getColumnIndexOrThrow(BookImage));

                bookList.add(new Book(id, title, author, cost, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookList;
    }
    public Cursor getAllUsers() { SQLiteDatabase db = this.getReadableDatabase(); return db.rawQuery("SELECT * FROM " + TABLE_NAME, null); }
}
