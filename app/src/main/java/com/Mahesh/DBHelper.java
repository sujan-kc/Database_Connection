package com.Mahesh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "test_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "user_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_PASSWORD = "password";
    //public static final String COLUMN_CONFIRMPASSWORD = "confirmpassword";
    private final Context context;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT," + COLUMN_CONTACT + " TEXT, "
                + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qry = ("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(qry);
        onCreate(db);
    }

    // this function is for add user in database
    public boolean adduser(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, model.getName());
        cv.put(COLUMN_CONTACT, model.getContact());
        cv.put(COLUMN_PASSWORD, model.getPassword());

        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1) {
            Toast.makeText(context, "Data insert fail ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Data inserted Successfully ", Toast.LENGTH_SHORT).show();
            return true;
        }

    }

    // this function is for display database data in Recyclerview
    public ArrayList<Model> getAllusers() {
        ArrayList<Model> model = new ArrayList<>();
        String query = " SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String contact = cursor.getString(2);
                String password = cursor.getString(3);
                // String confirmpassword= cursor.getString(4);
                Model model1 = new Model(id, name, contact, password);
                model.add(model1);
            } while (cursor.moveToNext());
        }
        return model;
    }

    // this function is for update user
    public int updateUser(Model m) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, m.getName());
        cv.put(COLUMN_CONTACT, m.getContact());
        cv.put(COLUMN_PASSWORD, m.getPassword());
        return db.update(TABLE_NAME, cv, "id=?", new String[]{String.valueOf(m.getId())});

    }

    // this function is for delete user
    public int DeleteUser(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
    }
}
