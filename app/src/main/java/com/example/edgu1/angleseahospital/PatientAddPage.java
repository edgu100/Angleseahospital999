package com.example.edgu1.angleseahospital;

/**
 * Created by WX on 2017/11/17.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;


public class PatientAddPage extends AppCompatActivity {

    private SQLiteHelper dbHandler = null;
    private Patient oldPatient = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_add);

    }

    //Add new patient into database
    public void pa_save_click(View v){
        //Create PatientInfo share information file, MODE_PRIVATE is just this device can access it
        SharedPreferences sharedPre = getSharedPreferences("info", Context.MODE_PRIVATE);
        //Give the write right
        SharedPreferences.Editor editor = sharedPre.edit();
        String info = "";

        try {
            Patient patient = new Patient();
            //Add name
            EditText pa_add_name= (EditText)findViewById(R.id.pa_add_name);
            patient.setName(pa_add_name.getText().toString());
            //Add birthday
            EditText pa_add_birthday= (EditText)findViewById(R.id.pa_add_birthday);
            patient.setBirthDay(pa_add_birthday.getText().toString());
            //Add wight
            EditText pa_add_wight= (EditText)findViewById(R.id.pa_add_wight);
            patient.setWeight(Double.valueOf(pa_add_wight.getText().toString()));
            //Add NHI-No
            EditText pa_add_NHINo= (EditText)findViewById(R.id.pa_add_NHINo);
            patient.setNHINo(pa_add_NHINo.getText().toString());
            //Add room-No
            EditText pa_add_RoomNo= (EditText)findViewById(R.id.pa_add_RoomNo);
            patient.setRoomNo(pa_add_RoomNo.getText().toString());

            if(oldPatient == null){
                dbHandler.addPatient(patient);
                info = "Save successfully!";
            }
        }catch (Exception e){
            if(oldPatient == null){
                info= "Save failed!";
            }
            e.printStackTrace();
        }finally {
            editor.putString("msg",info);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(),PatientListPage.class);
            startActivity(intent);
            finish();
        }
    }


    public void pa_cancle_onClick(View v){
        Intent pa_cancle = new Intent(PatientAddPage.this, PatientListPage.class);
        startActivity(pa_cancle);
    }


}
