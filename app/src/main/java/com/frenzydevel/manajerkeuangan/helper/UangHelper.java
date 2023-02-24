package com.frenzydevel.manajerkeuangan.helper;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class UangHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "frenz";

    public UangHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String sql_create_table = "CREATE TABLE moneys (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "profile_id INTEGER," +
                "income TEXT," +
                "outcome TEXT," +
                "FOREIGN KEY(profile_id) REFERENCES profiles(id))";
        sqLiteDatabase.execSQL(sql_create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS moneys");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getData(){
        // inisiasi objek list untuk menampung data
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        String query = "SELECT * FROM moneys"; // query data
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("profile_id", cursor.getString(1));
                map.put("income", cursor.getString(3));
                map.put("outcome", cursor.getString(4));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(HashMap<String, String> data, Activity activity){


        SQLiteDatabase database = this.getWritableDatabase();

        // buat query string
        String query = "INSERT INTO moneys (profile_id,income,outcome)" +
                "VALUES ("+ data.get("profile_id") +", "+ data.get("income") +", "+ data.get("outcome") +")";

        // Toast.makeText(activity, query, Toast.LENGTH_SHORT).show();
        database.execSQL(query);
    }

    public void update(int id, int profile_id, double cash, double income, double outcome){
        SQLiteDatabase database = this.getWritableDatabase();

        // buat query string
        String query = "UPDATE moneys" +
                "SET profile_id = "+ profile_id +", cash = "+ cash +", income = "+ income +", outcome = "+ outcome +" WHERE id = "+id;
        database.execSQL(query);
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        // buat query string
        String query = "DELETE FROM moneys WHERE id = " + id;
        database.execSQL(query);
    }
}

