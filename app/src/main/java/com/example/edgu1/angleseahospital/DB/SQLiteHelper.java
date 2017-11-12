package com.example.edgu1.angleseahospital.DB;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "angleseaHospital.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public User getUserById(Integer uId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        User user = null;
        db = getReadableDatabase();
        cursor = db.query("USERS", new String[] {"id","name","email","password"}, "id" + " = "+uId , null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                user.setName(cursor.getString(cursor.getColumnIndex("name")));
                user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            }
        }
        return user;
    }

    public List<User> getUsersByName(String name){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<User> users = new ArrayList<User>();
        String whereClause=null;
        if(name!=null && !"".equals(name)){
            whereClause=" name like '%"+name+"%'";
        }
        try{
            db = getReadableDatabase();
            cursor = db.query("USERS", new String[] {"id","name","email","password"}, whereClause , null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    User user = new User();
                    user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    user.setName(cursor.getString(cursor.getColumnIndex("name")));
                    user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                    user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return users;
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        values.put("email",user.getName());
        values.put("password",user.getName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("USERS", null, values);
        db.close();
    }

    public void deleteUser(int uId){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM USERS WHERE id="+uId+";");
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public void updateUser(User user){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",user.getName());
            values.put("email",user.getName());
            values.put("password",user.getName());
            db.update("USERS",values,"id="+user.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists USERS(" +
                "id integer primary key AUTOINCREMENT," +
                "name text NOT NULL," +
                "email text NOT NULL," +
                "password  text NOT NULL" +
                ")";
        db.execSQL(sql);
        sql = "create table if not exists PATIENT(" +
                "id integer primary key AUTOINCREMENT," +
                "name text NOT NULL," +
                "roomNo text NOT NULL," +
                "NHINo text NOT NULL," +
                "birthDay DATE NOT NULL," +
                "weight double" +
                ")";
        db.execSQL(sql);
        sql = "create table if not exists DRUGS(" +
                "id integer primary key AUTOINCREMENT," +
                "name text NOT NULL," +
                "manufacturer text NOT NULL," +
                "productionDate DATE NOT NULL," +
                "shelfLife DATE NOT NULL," +
                "specification text NOT NULL" +
                ")";
        db.execSQL(sql);
        sql = "create table if not exists PATIENTDRUGS(" +
                "id integer primary key AUTOINCREMENT," +
                "patientId integer NOT NULL," +
                "drugsId integer NOT NULL," +
                "dosageStart text," +
                "dosageEnd text," +
                "frequency double," +
                "timeStamp DATE," +
                "signTime DATE," +
                "CONSTRAINT fk_patient FOREIGN KEY (patientId) REFERENCES PATIENT(id)," +
                "CONSTRAINT fk_drugs FOREIGN KEY (drugsId) REFERENCES DRUGS(id)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS USERS";
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS PATIENT";
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS DRUGS";
        db.execSQL(sql);
        sql = "DROP TABLE IF EXISTS PATIENTDRUGS";
        db.execSQL(sql);
        onCreate(db);
    }

}
