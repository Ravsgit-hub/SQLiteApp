package com.example.sqlite1app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final  String DATABASE_NAME="Client_DB";
    private static final  String TABLE="Client";

    private static  int id;
    private static String Name;
    private static String Phone;
    private static String Role;
    private static String Company;



    public DbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
       String query ="CREATE TABLE Client(id INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Phone TEXT,Role TEXT,Company TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE if EXISTS Client");
     onCreate(db);
    }
    void  addClient(model md)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",md.getName());
        values.put("Phone",md.getPhone());
        values.put("Role",md.getRole());
        values.put("Company",md.getCompany());
        db.insert("Client",null,values);
        db.close();
    }

    public List<model> getAllClient(){
        List<model> clientlist=new ArrayList<model>();
        String selectQuery="SELECT * FROM Client";
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                model md=new model();
                md.setId(Integer.parseInt(cursor.getString(0)));
                md.setName(cursor.getString(1));
                md.setPhone(cursor.getString(2));
                md.setRole(cursor.getString(3));
                md.setCompany(cursor.getString(4));
                clientlist.add(md);
            }while (cursor.moveToNext());
        }
        return clientlist;
    }
    public  int updateClient( model md,int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",md.getName());
        values.put("Phone",md.getPhone());
        values.put("Role",md.getRole());
        values.put("Company",md.getCompany());
        return db.update("Client",values,"id=?",new String[]{String.valueOf(id)});

    }
    public void deleteClient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Client","id =?",new String[] {String.valueOf((id))});
        db.close();
    }
    public int getClientCount()
    {
        String countQuery="SELECT *FROM Client where id =11";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }



}
