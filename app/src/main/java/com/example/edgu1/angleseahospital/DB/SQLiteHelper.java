package com.example.edgu1.angleseahospital.DB;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class SQLiteHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "angleseaHospital.db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    ////////////////////////////////////
    //      User Database Query       //
    ////////////////////////////////////

    public User getUserByEmail(String email) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        User user = null;
        db = getReadableDatabase();
        cursor = db.query("USERS", new String[] {"id","name","email","password"}, "email=?",new String[] {email}, null, null, null);
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

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        values.put("email",user.getEmail());
        values.put("password",user.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("USERS", null, values);
        db.close();
    }

    public void updateUser(User user){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",user.getName());
            values.put("email",user.getEmail());
            values.put("password",user.getPassword());
            db.update("USERS",values,"id="+user.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    ////////////////////////////////////
    //      Patient Database Query    //
    ////////////////////////////////////

    //Search patient by ID
    public Patient getPatientById(Integer pId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Patient patient = null;
        db = getReadableDatabase();
        cursor = db.query("PATIENT", new String[] {"id","name","roomNo","NHINo","birthDay","weight"}, "id" + " = "+pId , null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                patient = new Patient();
                patient.setId(cursor.getInt(cursor.getColumnIndex("id")));
                patient.setName(cursor.getString(cursor.getColumnIndex("name")));
                patient.setRoomNo(cursor.getString(cursor.getColumnIndex("roomNo")));
                patient.setNHINo(cursor.getString(cursor.getColumnIndex("NHINo")));
                patient.setBirthDay(cursor.getString(cursor.getColumnIndex("birthDay")));
                patient.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));
            }
        }
        return patient;
    }

    //Search patient by NHI-No
    public List<Patient> getPatientByNHINo(String NHINo) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Patient> patients = new ArrayList<Patient>();
        String whereClause=null;
        if(NHINo!=null && !"".equals(NHINo)){
            whereClause=" NHINo like '%"+NHINo+"%'";
        }
        try{
            db = getReadableDatabase();
            cursor = db.query("PATIENT", new String[] {"id","name","roomNo","NHINo","birthDay","weight"}, whereClause , null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Patient patient = new Patient();
                    patient.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    patient.setName(cursor.getString(cursor.getColumnIndex("name")));
                    patient.setRoomNo(cursor.getString(cursor.getColumnIndex("roomNo")));
                    patient.setNHINo(cursor.getString(cursor.getColumnIndex("NHINo")));
                    patient.setBirthDay(cursor.getString(cursor.getColumnIndex("birthDay")));
                    patient.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));
                    patients.add(patient);
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
        return patients;
    }

    //Search patient by Room-No
    public List<Patient> getPatientByRoomNo(String RoomNo) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Patient> patients = new ArrayList<Patient>();
        String whereClause=null;
        if(RoomNo!=null && !"".equals(RoomNo)){
            whereClause=" roomNo like '%"+RoomNo+"%'";
        }
        try{
            db = getReadableDatabase();
            cursor = db.query("PATIENT", new String[] {"id","name","roomNo","NHINo","birthDay","weight"}, whereClause , null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Patient patient = new Patient();
                    patient.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    patient.setName(cursor.getString(cursor.getColumnIndex("name")));
                    patient.setRoomNo(cursor.getString(cursor.getColumnIndex("roomNo")));
                    patient.setNHINo(cursor.getString(cursor.getColumnIndex("NHINo")));
                    patient.setBirthDay(cursor.getString(cursor.getColumnIndex("birthDay")));
                    patient.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));
                    patients.add(patient);
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
        return patients;
    }


    //Search patient by Name
    public List<Patient> getPatientsByName(String name){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Patient> patients = new ArrayList<Patient>();
        String whereClause=null;
        if(name!=null && !"".equals(name)){
            whereClause=" name like '%"+name+"%'";
        }
        try{
            db = getReadableDatabase();
            cursor = db.query("PATIENT", new String[] {"id","name","roomNo","NHINo","birthDay","weight"}, whereClause , null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Patient patient = new Patient();
                    patient.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    patient.setName(cursor.getString(cursor.getColumnIndex("name")));
                    patient.setRoomNo(cursor.getString(cursor.getColumnIndex("roomNo")));
                    patient.setNHINo(cursor.getString(cursor.getColumnIndex("NHINo")));
                    patient.setBirthDay(cursor.getString(cursor.getColumnIndex("birthDay")));
                    patient.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));
                    patients.add(patient);
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
        return patients;
    }


    //Add new patient
    public void addPatient(Patient patient){
        ContentValues values = new ContentValues();

        values.put("name",patient.getName());
        values.put("roomNo",patient.getRoomNo());
        values.put("NHINo",patient.getNHINo());
        values.put("birthDay",patient.getBirthDay());
        values.put("weight",patient.getWeight());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("PATIENT", null, values);
        db.close();
    }

    //Delete the patient
    public void deletePatient(int pId){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM PATIENT WHERE id="+pId+";");
            db.execSQL("DELETE FROM TRACKS WHERE patientId="+pId+";");
            db.execSQL("DELETE FROM PATIENTDRUGS WHERE patientId="+pId+";");
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

    public void updatePatient(Patient patient){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",patient.getName());
            values.put("roomNo",patient.getRoomNo());
            values.put("NHINo",patient.getNHINo());
            values.put("birthDay",patient.getBirthDay());
            values.put("weight",patient.getWeight());
            db.update("PATIENT",values,"id="+patient.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    ////////////////////////////////////
    //      Drugs Database Query       //
    ////////////////////////////////////

    public List<Drug> getSelectDrugs(String pid){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Drug> drugs = new ArrayList<Drug>();
        try{
            db = getReadableDatabase();
            cursor = getWritableDatabase().rawQuery("select * from DRUGS where id not in (" +
                    "select drugsId from PATIENTDRUGS pd where pd.patientId=" +pid+
                    ")",null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Drug drug=new Drug();
                    drug.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    drug.setName(cursor.getString(cursor.getColumnIndex("name")));
                    drugs.add(drug);
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
        return drugs;
    }


    public List<Drug> getDrugsByName(String name){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Drug> users = new ArrayList<Drug>();
        String whereClause=null;
        if(name!=null && !"".equals(name)){
            whereClause=" name like '%"+name+"%'";
        }
        try{
            db = getReadableDatabase();
            cursor = db.query("DRUGS", new String[] {"id","name","manufacturer","productionDate","shelfLife","milliliters","milligrams"}, whereClause , null, null, null, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Drug drugs = new Drug();
                    drugs.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    drugs.setName(cursor.getString(cursor.getColumnIndex("name")));
                    drugs.setManufacturer(cursor.getString(cursor.getColumnIndex("manufacturer")));
                    drugs.setProductionDate(cursor.getString(cursor.getColumnIndex("productionDate")));
                    drugs.setShelfLife(cursor.getString(cursor.getColumnIndex("shelfLife")));
                    drugs.setMilliliters(cursor.getString(cursor.getColumnIndex("milliliters")));
                    drugs.setMilligrams(cursor.getString(cursor.getColumnIndex("milligrams")));
                    users.add(drugs);
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

    public void addDrug(Drug drug){
        ContentValues values = new ContentValues();
        values.put("name",drug.getName());
        values.put("manufacturer",drug.getManufacturer());
        values.put("milliliters",drug.getMilliliters());
        values.put("productionDate",drug.getProductionDate());
        values.put("shelfLife",drug.getShelfLife());
        values.put("milligrams",drug.getMilligrams());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("DRUGS", null, values);
        db.close();
    }

    public void deleteDrug(int uId){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.beginTransaction();
            db.execSQL("DELETE FROM DRUGS WHERE id="+uId+";");
            db.execSQL("DELETE FROM TRACKS WHERE drugsId="+uId+";");
            db.execSQL("DELETE FROM PATIENTDRUGS WHERE drugsId="+uId+";");
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

    public Drug getDrugById(String dId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Drug drugs = null;
        db = getReadableDatabase();
        cursor = db.query("DRUGS", new String[] {"id","name","manufacturer","productionDate","shelfLife","milliliters","milligrams"}, "id" + " = "+dId , null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                drugs = new Drug();
                drugs.setId(cursor.getInt(cursor.getColumnIndex("id")));
                drugs.setName(cursor.getString(cursor.getColumnIndex("name")));
                drugs.setManufacturer(cursor.getString(cursor.getColumnIndex("manufacturer")));
                drugs.setProductionDate(cursor.getString(cursor.getColumnIndex("productionDate")));
                drugs.setShelfLife(cursor.getString(cursor.getColumnIndex("shelfLife")));
                drugs.setMilliliters(cursor.getString(cursor.getColumnIndex("milliliters")));
                drugs.setMilligrams(cursor.getString(cursor.getColumnIndex("milligrams")));
            }
        }
        return drugs;
    }

    ////////////////////////////////////
    //      Patient Drugs Database Query       //
    ////////////////////////////////////

    public void addPatientDrugs(PatientDrugs patientDrugs){
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("patientId",patientDrugs.getPatientId());
            values.put("drugsId",patientDrugs.getDrugsId());
            values.put("dosage",patientDrugs.getDosage());
            values.put("medication",patientDrugs.getMedication());
            values.put("frequency",patientDrugs.getFrequency());
            values.put("timeStamp",patientDrugs.getTimeStamp());
            values.put("signTime",patientDrugs.getSignTime());
            values.put("signImg",patientDrugs.getSignImg());
            db.insert("PATIENTDRUGS", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void updatePatientDrugs(PatientDrugs patientDrugs){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("patientId",patientDrugs.getPatientId());
            values.put("drugsId",patientDrugs.getDrugsId());
            values.put("dosage",patientDrugs.getDosage());
            values.put("medication",patientDrugs.getMedication());
            values.put("frequency",patientDrugs.getFrequency());
            values.put("timeStamp",patientDrugs.getTimeStamp());
            values.put("signTime",patientDrugs.getSignTime());
            values.put("signImg",patientDrugs.getSignImg());
            db.update("PATIENTDRUGS",values,"id="+patientDrugs.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public PatientDrugs getPatientDrugsById(String pdId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        PatientDrugs pd = null;
        db = getReadableDatabase();
        cursor = db.query("PATIENTDRUGS", new String[] {"id","patientId","drugsId","dosage","medication","frequency","timeStamp","signTime","signImg"}, "id" + " = "+pdId , null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                pd = new PatientDrugs();
                pd.setId(cursor.getInt(cursor.getColumnIndex("id")));
                pd.setPatientId(cursor.getInt(cursor.getColumnIndex("patientId")));
                pd.setDrugsId(cursor.getInt(cursor.getColumnIndex("drugsId")));
                pd.setDosage(cursor.getString(cursor.getColumnIndex("dosage")));
                pd.setMedication(cursor.getString(cursor.getColumnIndex("medication")));
                pd.setFrequency(cursor.getDouble(cursor.getColumnIndex("frequency")));
                pd.setTimeStamp(cursor.getString(cursor.getColumnIndex("timeStamp")));
                pd.setSignTime(cursor.getString(cursor.getColumnIndex("signTime")));
                pd.setSignImg(cursor.getString(cursor.getColumnIndex("signImg")));
            }
        }
        return pd;
    }

    public void updateDrug(Drug drug){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",drug.getName());
            values.put("manufacturer",drug.getManufacturer());
            values.put("milliliters",drug.getMilliliters());
            values.put("productionDate",drug.getProductionDate());
            values.put("shelfLife",drug.getShelfLife());
            values.put("milligrams",drug.getMilligrams());
            db.update("DRUGS",values,"id="+drug.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


    public List<Map<String,String>> patientDrugs(int pid){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Map<String,String>> tasks = new ArrayList<Map<String,String>>();
        try{
            db = getReadableDatabase();
            cursor = getWritableDatabase().rawQuery("select pd.id pdid, p.name pname,p.roomNo pRoomNo,p.NHINo NHINo,p.weight weight,p.birthDay birthDay," +
                    "d.name dname,pd.dosage dosage,pd.frequency frequency,pd.timeStamp timeStamp,pd.signTime signTime" +
                    " from PATIENTDRUGS pd left join PATIENT p on pd.patientId = p.id" +
                    " left join DRUGS d on pd.drugsId = d.id" +
                    " where pd.patientId = " + pid,null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Map<String,String> dr=new HashMap<String,String>();
                    dr.put("pdid",cursor.getInt(cursor.getColumnIndex("pdid"))+"");
                    dr.put("pname",cursor.getString(cursor.getColumnIndex("pname")));
                    dr.put("dosage",cursor.getString(cursor.getColumnIndex("dosage")));
                    dr.put("frequency",cursor.getString(cursor.getColumnIndex("frequency")));
                    dr.put("timeStamp",cursor.getString(cursor.getColumnIndex("timeStamp")));
                    dr.put("signTime",cursor.getString(cursor.getColumnIndex("signTime")));
                    dr.put("pRoomNo",cursor.getString(cursor.getColumnIndex("pRoomNo")));
                    dr.put("NHINo",cursor.getString(cursor.getColumnIndex("NHINo")));
                    dr.put("weight",cursor.getString(cursor.getColumnIndex("weight")));
                    dr.put("birthDay",cursor.getString(cursor.getColumnIndex("birthDay")));
                    dr.put("dname",cursor.getString(cursor.getColumnIndex("dname")));
                    dr.put("pRoomNo",cursor.getString(cursor.getColumnIndex("pRoomNo")));
                    tasks.add(dr);
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
        return tasks;
    }


    public List<Map<String,String>> getPatientDrugName(){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Map<String,String>> PGname = new ArrayList<Map<String,String>>();
        try{
            db = getReadableDatabase();
            cursor = getWritableDatabase().rawQuery("select pd.id pdid, p.name pname, d.name dname" +
                    " from PATIENTDRUGS pd left join PATIENT p on pd.patientId = p.id" +
                    " left join DRUGS d on pd.drugsId = d.id" +
                    " where signTime is null",null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Map<String,String> dr=new HashMap<String,String>();
                    dr.put("pdid",cursor.getInt(cursor.getColumnIndex("pdid"))+"");
                    dr.put("pname",cursor.getString(cursor.getColumnIndex("pname")));
                    dr.put("dname",cursor.getString(cursor.getColumnIndex("dname")));
                    PGname.add(dr);
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
        return PGname;
    }

    public PatientDrugs getPatientDrugsByPDID(int patientId,int drugsId){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        PatientDrugs pd = null;
        try {
            db = getWritableDatabase();
            cursor = db.rawQuery("select * from PATIENTDRUGS" +
                    " where patientId=" + patientId + " and drugsId=" + drugsId, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    pd = new PatientDrugs();
                    pd.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    pd.setPatientId(cursor.getInt(cursor.getColumnIndex("patientId")));
                    pd.setDrugsId(cursor.getInt(cursor.getColumnIndex("drugsId")));
                    pd.setDosage(cursor.getString(cursor.getColumnIndex("dosage")));
                    pd.setFrequency(cursor.getDouble(cursor.getColumnIndex("frequency")));
                    pd.setTimeStamp(cursor.getString(cursor.getColumnIndex("timeStamp")));
                    pd.setSignTime(cursor.getString(cursor.getColumnIndex("signTime")));
                    pd.setSignImg(cursor.getString(cursor.getColumnIndex("signImg")));
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
        return pd;
    }

//    public List<PatientDrugs> getPatientDrugsBypatientId(String patientId){
//        SQLiteDatabase db = null;
//        Cursor cursor = null;
//        List<PatientDrugs> pg = new ArrayList<PatientDrugs>();
//        String whereClause=null;
//        if(patientId!=null && !"".equals(patientId)){
//            whereClause=" patientId like '%"+patientId+"%'";
//        }
//        try{
//            db = getReadableDatabase();
//            cursor = db.query("PATIENTDRUGS", new String[] {"id","patientId","drugsId","dosage","frequency","timeStamp","signTime","signImg"}, whereClause , null, null, null, null);
//            if (cursor.getCount() > 0) {
//                while (cursor.moveToNext()) {
//                    PatientDrugs patientdrugs = new PatientDrugs();
//                    patientdrugs.setId(cursor.getInt(cursor.getColumnIndex("id")));
//                    patientdrugs.setPatientId(cursor.getInt(cursor.getColumnIndex("patientId")));
//                    patientdrugs.setDrugsId(cursor.getInt(cursor.getColumnIndex("drugsId")));
//                    patientdrugs.setDosage(cursor.getString(cursor.getColumnIndex("dosage")));
//                    patientdrugs.setFrequency(cursor.getDouble(cursor.getColumnIndex("frequency")));
//                    patientdrugs.setTimeStamp(cursor.getString(cursor.getColumnIndex("timeStamp")));
//                    patientdrugs.setSignTime(cursor.getString(cursor.getColumnIndex("signTime")));
//                    patientdrugs.setSignImg(cursor.getString(cursor.getColumnIndex("signImg")));
//                    pg.add(patientdrugs);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//            if (db != null) {
//                db.close();
//            }
//        }
//        return pg;
//    }

    ////////////////////////////////////
    //      Patient Tracks Database Query       //
    ////////////////////////////////////


    public List<Map<String,String>> getTracks(String pid){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Map<String,String>> tracks = new ArrayList<Map<String,String>>();
        try{
            db = getReadableDatabase();
            cursor = getWritableDatabase().rawQuery("select t.id tid,d.name dname, t.focustime focustime, t.realtime realtime" +
                    " from TRACKS t left join PATIENT p on t.patientId = p.id" +
                    " left join DRUGS d on t.drugsId = d.id"+
                    " WHERE t.patientId = "+pid+
                    " ORDER BY t.realtime",null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Map<String,String> dr=new HashMap<String,String>();
                    dr.put("tid",cursor.getInt(cursor.getColumnIndex("tid"))+"");
                    dr.put("dname",cursor.getString(cursor.getColumnIndex("dname")));
                    dr.put("focustime",cursor.getString(cursor.getColumnIndex("focustime")));
                    dr.put("realtime",cursor.getString(cursor.getColumnIndex("realtime")));
                    tracks.add(dr);
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
        return tracks;
    }

    public void updateTracks(Track track){
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("patientId",track.getPatientId());
            values.put("drugsId",track.getDrugsId());
            values.put("focustime",track.getFocustime());
            values.put("realtime",track.getRealtime());
            values.put("signature1",track.getSignature1());
            values.put("signature2",track.getSignature2());
            db.update("TRACKS",values,"id="+track.getId(),null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void addTracks(Track track){
        SQLiteDatabase db = getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("patientId",track.getPatientId());
            values.put("drugsId",track.getDrugsId());
            values.put("focustime",track.getFocustime());
            db.insert("TRACKS", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public Track getTrackById(String tId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        Track track = null;
        db = getReadableDatabase();
        cursor = db.query("TRACKS", new String[] {"id","patientId","drugsId","focustime","realtime","signature1","signature2"}, "id" + " = "+tId , null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                track = new Track();
                track.setId(cursor.getInt(cursor.getColumnIndex("id")));
                track.setPatientId(cursor.getInt(cursor.getColumnIndex("patientId")));
                track.setDrugsId(cursor.getInt(cursor.getColumnIndex("drugsId")));
                track.setFocustime(cursor.getString(cursor.getColumnIndex("focustime")));
                track.setRealtime(cursor.getString(cursor.getColumnIndex("realtime")));
                track.setSignature1(cursor.getString(cursor.getColumnIndex("signature1")));
                track.setSignature2(cursor.getString(cursor.getColumnIndex("signature2")));
            }
        }
        return track;
    }

    public List<Map<String,String>> getTasks(){
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Map<String,String>> tasks = new ArrayList<Map<String,String>>();
        try{
            db = getReadableDatabase();
            cursor = getWritableDatabase().rawQuery("select pd.id pdid, p.name pname, d.name dname" +
                    " from TRACKS pd left join PATIENT p on pd.patientId = p.id" +
                    " left join DRUGS d on pd.drugsId = d.id" +
                    " where realtime is null",null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Map<String,String> dr=new HashMap<String,String>();
                    dr.put("pdid",cursor.getInt(cursor.getColumnIndex("pdid"))+"");
                    dr.put("pname",cursor.getString(cursor.getColumnIndex("pname")));
                    dr.put("dname",cursor.getString(cursor.getColumnIndex("dname")));
                    tasks.add(dr);
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
        return tasks;
    }

    ////////////////////////////////////
    //             Database           //
    ////////////////////////////////////

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
                "birthDay text NOT NULL," +
                "weight double" +
                ")";
        db.execSQL(sql);
        sql = "create table if not exists DRUGS(" +
                "id integer primary key AUTOINCREMENT," +
                "name text NOT NULL," +
                "manufacturer text NOT NULL," +
                "productionDate text NOT NULL," +
                "shelfLife text NOT NULL," +
                "milliliters text NOT NULL," +
                "milligrams text NOT NULL" +
                ")";
        db.execSQL(sql);
        sql = "create table if not exists PATIENTDRUGS(" +
                "id integer primary key AUTOINCREMENT," +
                "patientId integer NOT NULL," +
                "drugsId integer NOT NULL," +
                "dosage text," +
                "medication text," +
                "frequency double," +
                "timeStamp text," +
                "signTime text," +
                "signImg text," +
                "CONSTRAINT fk_patient FOREIGN KEY (patientId) REFERENCES PATIENT(id)," +
                "CONSTRAINT fk_drugs FOREIGN KEY (drugsId) REFERENCES DRUGS(id)" +
                ")";
        db.execSQL(sql);
        sql = "create table if not exists TRACKS(" +
                "id integer primary key AUTOINCREMENT," +
                "patientId integer NOT NULL," +
                "drugsId integer NOT NULL," +
                "focustime text," +
                "realtime text," +
                "signature1 text," +
                "signature2 text," +
                "CONSTRAINT fk_trackp FOREIGN KEY (patientId) REFERENCES PATIENT(id)," +
                "CONSTRAINT fk_trackd FOREIGN KEY (drugsId) REFERENCES DRUGS(id)" +
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
        sql = "DROP TABLE IF EXISTS TRACKS";
        db.execSQL(sql);
        onCreate(db);
    }

}
