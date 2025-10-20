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
    public static final String BOOKIMAGE = "BookImage";
    public static final String BOOK_DESCRIPTION = "Description";

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
                BOOKIMAGE + " TEXT,"+
                BOOK_DESCRIPTION + " TEXT)";
        db.execSQL(CREATE_BOOK);


        db.execSQL("INSERT INTO " + BOOK_TABLE + " (" +
                BOOK_TITLE + ", " + BOOK_AUTHOR + ", " + BOOK_COST + ", " + BOOKIMAGE +", " + BOOK_DESCRIPTION +
                ") VALUES ('The Odyssey', 'Homer', 899, 'odyssey',  'The Odyssey หมายถึง บทกวีมหากาพย์กรีกโบราณ ที่แต่งโดยโฮเมอร์ โดยบทกวีเล่าถึงการเดินทางอันยาวนาน 10 ปีของวีรบุรุษกรีกชื่อ โอดิสเซียส เพื่อกลับบ้านหลังสงครามเมืองทรอย\n')" +
                ", ('Don Quixote de la Mancha', 'Miguel de Cervantes Saavedra', 599, 'don_quixote','ดอนกิโฆเต้ เด ลามันชา เป็นเรื่องราวของ อาลอนโซ กิฮาโน ชายผู้คลั่งไคล้นิยายอัศวินจนตัดสินใจเป็น อัศวินพเนจร ในนาม ดอนกิโฆเต้ โดยมี ซานโช ปันซา ชาวนาผู้ซื่อสัตย์เป็นผู้ติดตาม ทั้งสองออกเดินทางผจญภัย พบเจอกับเหตุการณ์ที่ทั้งขบขันและน่าเศร้า โดยเฉพาะอย่างยิ่งเมื่อดอนกิโฆเต้เข้าใจผิดว่ากังหันลมเป็นยักษ์ เรื่องราวได้ล้อเลียนนิยายอัศวินในยุคโบราณ และจบลงด้วยการที่ดอนกิโฆเต้พ่ายแพ้ในการต่อสู้ ทำให้เขาต้องกลับบ้านและในที่สุดก็กลับมาเป็นอาลอนโซ กิฮาโน เหมือนเดิม และเสียชีวิตในที่สุด ')" +
                ", ('Divine Comedy', 'Dante Alighieri', 699, 'dante', 'The Divine Comedy เป็นบทกวีมหากาพย์ที่เล่าเรื่องการเดินทางทางจิตวิญญาณของดันเต้ผ่าน 3 ภพ คือ Inferno (นรก), Purgatorio (แดนชำระบาป) และ Paradiso (สวรรค์) การเดินทางเริ่มต้นเมื่อดันเต้หลงทางในป่าและได้รับการช่วยเหลือจากเวอร์จิล กวีชาวโรมัน เพื่อนำทางเขาผ่านแดนหลังความตาย โดยในแต่ละภพจะมีการสำรวจเรื่องราวของบาป การไถ่บาป และความสมบูรณ์แบบที่แตกต่างกันไป')" +
                ", ('Call of Cthulhu', 'H.P Lovecraft', 1099, 'nuad', 'เสียงเรียกของคธูลู (The Call of Cthulhu) คือเรื่องราวที่ฟรานซิส เวย์แลนด์ เธิร์สตัน ค้นพบเบาะแสจากบันทึกของอาจารย์ผู้ล่วงลับของเขา ซึ่งนำไปสู่การเปิดโปงลัทธิลึกลับและการมีอยู่ของคธูลู สิ่งมีชีวิตโบราณที่ถูกกักขังอยู่ใต้ทะเลลึก')" +
                ", ('Murder on the Orient Express', 'Agatha Christie', 999, 'murderonorientexpress','Murder on the Orient Express คือ คดีฆาตกรรมบนรถไฟหรู ซึ่งนักสืบชื่อดัง แอร์กูล ปัวโรต์ ได้รับการว่าจ้างให้สืบสวน แต่กลับต้องพบกับเรื่องซับซ้อนเมื่อรถไฟต้องหยุดชะงักเพราะหิมะถล่ม ทำให้ปัวโรต์ต้องรีบหาฆาตกรจากกลุ่มผู้โดยสารทั้ง 13 คน ซึ่งแต่ละคนมีความเกี่ยวข้องกับคดีลักพาตัวเรียกค่าไถ่ในอดีต')" +
                ", ('Crime and Punishment', 'Fyodor Dostoevsky', 1199, 'crimeandpunish', 'อาชญากรรมและการลงทัณฑ์ (Crime and Punishment) กล่าวถึง ราสโคลนิคอฟ อดีตนักศึกษาผู้ยากจนในเซนต์ปีเตอร์สเบิร์ก ที่ก่อเหตุฆาตกรรมหญิงชราเจ้าของโรงรับจำนำเพื่อนำเงินมาใช้ และเพื่อทดสอบทฤษฎีที่ว่าคนบางคนมีสิทธิ์เหนือกว่าผู้อื่น อย่างไรก็ตาม การฆาตกรรมกลับทำให้เขารู้สึกโดดเดี่ยวและทุกข์ทรมานจากความผิดในใจ นวนิยายติดตามการต่อสู้ภายในจิตใจของเขา การเผชิญหน้ากับนักสืบ พอร์ฟิรี เปโตรวิช และการเดินทางทางศีลธรรมจนท้ายที่สุดเขาตัดสินใจสารภาพและยอมรับโทษ')"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 5) {
            db.execSQL("CREATE TABLE temp_users AS SELECT * FROM " + TABLE_NAME);
            db.execSQL("DROP TABLE " + TABLE_NAME);
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
                String image = cursor.getString(cursor.getColumnIndexOrThrow(BOOKIMAGE));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(BOOK_DESCRIPTION));
               bookList.add(new Book(id, title, author, cost, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookList;
    }
    public Cursor getAllUsers() { SQLiteDatabase db = this.getReadableDatabase(); return db.rawQuery("SELECT * FROM " + TABLE_NAME, null); }
}
