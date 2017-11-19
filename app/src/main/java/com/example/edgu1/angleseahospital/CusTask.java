package com.example.edgu1.angleseahospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;

public class CusTask extends AppCompatActivity {

    private SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_task);
        db = new SQLiteHelper(this);
        List<PatientDrugs> pds = db.getTasks();

    }
}
