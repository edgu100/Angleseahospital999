package com.example.edgu1.angleseahospital.DB;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.example.edgu1.angleseahospital.PatientListPage;
import com.example.edgu1.angleseahospital.Patient_info;
import com.example.edgu1.angleseahospital.R;


import java.util.List;

public class editPatient extends AppCompatActivity {
    private SQLiteHelper pdb = null;
    private List<Patient> patients;
    private Patient oldPatient = null;
    private Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        pdb = new SQLiteHelper(this);
        String pid = getIntent().getStringExtra("pid");
        patient  =  pdb.getPatientById(Integer.parseInt(pid));


        //set the patient information

        EditText RoomNo = (EditText) findViewById(R.id.e_add_RoomNo);
        EditText PatientName = (EditText) findViewById(R.id.e_add_name);
        EditText BOD = (EditText) findViewById(R.id.e_add_birthday);
        EditText WIGHTEdit = (EditText) findViewById(R.id.e_add_wight);
        EditText NHI = (EditText) findViewById(R.id.e_add_NHINo);

        RoomNo.setText(patient.getRoomNo());

        PatientName.setText(patient.getName());
        BOD.setText(patient.getBirthDay());
        WIGHTEdit.setText(String.valueOf(patient.getWeight()));
        NHI.setText(patient.getNHINo());

    }


// save button
    public void e_save_click(View v) {

        //Create PatientInfo share information file, MODE_PRIVATE is just this device can access it
        SharedPreferences sharedPre = getSharedPreferences("info", Context.MODE_PRIVATE);
        //Give the write right
        SharedPreferences.Editor editor = sharedPre.edit();
        String info = "";
        try {
            patient = new Patient();
            //Add name
            EditText e_add_name = (EditText) findViewById(R.id.e_add_name);
            patient.setName(e_add_name.getText().toString());
            //Add birthday
            EditText e_add_birthday = (EditText) findViewById(R.id.e_add_birthday);
            patient.setBirthDay(e_add_birthday.getText().toString());
            //Add wight
            EditText e_add_wight = (EditText) findViewById(R.id.e_add_wight);
            patient.setWeight(Double.valueOf(e_add_wight.getText().toString()));
            //Add NHI-No
            EditText e_add_NHINo = (EditText) findViewById(R.id.e_add_NHINo);
            patient.setNHINo(e_add_NHINo.getText().toString());
            //Add room-No
            EditText e_add_RoomNo = (EditText) findViewById(R.id.e_add_RoomNo);
            patient.setRoomNo(e_add_RoomNo.getText().toString());

            pdb.updatePatient(patient);
            //sent it to database
            if (oldPatient == null) {
                pdb.addPatient(patient);
                info = "Save it successfully!";
            }
        } catch (Exception e) {
            if (oldPatient == null) {
                info = "Save failed!";
            }
            e.printStackTrace();
        } finally {
            editor.putString("msg", info);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), Patient_info.class);
            startActivity(intent);
            finish();
        }
    }
//cancle button

    public void e_cancle_onClick(View v) {
        Intent e_cancle = new Intent(editPatient.this, PatientListPage.class);
        startActivity(e_cancle);
    }

}


