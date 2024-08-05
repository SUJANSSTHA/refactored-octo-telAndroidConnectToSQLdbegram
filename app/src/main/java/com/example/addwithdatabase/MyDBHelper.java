package com.example.addwithdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(@Nullable Context context) {
        super(context, "myDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Person (id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT NOT NULL, Description TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Person");
        onCreate(db);
    }

    public boolean insertData(Person p) {
        SQLiteDatabase db = getWritableDatabase(); // to write or insert data
        ContentValues values = new ContentValues();
        values.put("Name", p.Name);
        values.put("Description", p.Description); // Fixed typo here
        long count = db.insert("Person", null, values);
        db.close();

        return count != -1; // Return true if insertion was successful
    }

    public ArrayList<Person> getPerson() {
        ArrayList<Person> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, Name, Description FROM Person", null);
        while (cursor.moveToNext()) {
            Person p = new Person();
            p.id = cursor.getInt(0); // Fixed typo here
            p.Name = cursor.getString(1);
            p.Description = cursor.getString(2);
            list.add(p);
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean deleteData (int id){
        SQLiteDatabase db = getWritableDatabase();
        long count = db.delete("Person", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return count ==1;
    }
    public boolean updateData(Person p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",p.Name);
        values.put("Description",p.Description);
        int id = db.update("Person", values,"id=?", new String[]{String.valueOf(p.Id)});
        db.close();
        return id>0;
    }
}
