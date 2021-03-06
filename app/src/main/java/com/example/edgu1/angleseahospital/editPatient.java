package com.example.edgu1.angleseahospital;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;


import java.util.Calendar;
import java.util.List;

public class editPatient extends AppCompatActivity {
    private SQLiteHelper pdb = null;
    private List<Patient> patients;
    private Patient oldPatient = null;
    private Patient patient;

    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        pdb = new SQLiteHelper(this);
        patient  =  pdb.getPatientById(Parameters.pid);
        oldPatient=patient;


        //set the patient information
        if(oldPatient!=null) {
            EditText RoomNo = (EditText) findViewById(R.id.e_add_RoomNo);
            EditText PatientName = (EditText) findViewById(R.id.e_add_name);
            TextView BOD = (TextView) findViewById(R.id.e_add_birthday);
            EditText WIGHTEdit = (EditText) findViewById(R.id.e_add_wight);
            EditText NHI = (EditText) findViewById(R.id.e_add_NHINo);

            RoomNo.setText(patient.getRoomNo());

            PatientName.setText(patient.getName());
            BOD.setText(patient.getBirthDay());
            WIGHTEdit.setText(String.valueOf(patient.getWeight()));
            NHI.setText(patient.getNHINo());
        }
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
            TextView e_add_birthday = (TextView) findViewById(R.id.e_add_birthday);
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

            //pdb.updatePatient(patient);
            //sent it to database
            if (oldPatient == null) {
                pdb.updatePatient(patient);
                info = "Save it successfully!";
            }else{
                patient.setId(oldPatient.getId());
                pdb.updatePatient(patient);
                info="Update successfully!";
            }
        } catch (Exception e) {
            if (oldPatient == null) {
                info = "Save failed!";
            }else{
                info = "Update failed!";
            }
            e.printStackTrace();
        } finally {
            editor.putString("msg", info);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), PatientListPage.class);
            startActivity(intent);
            finish();
        }

    }
//cancle button

    public void Patient_Information_Cancle(View v) {
        Intent e_cancle = new Intent(editPatient.this, PatientListPage.class);
        startActivity(e_cancle);
    }


    ////////////////choose date////////////////
    public void DatePick(View v){
        switch (v.getId()) {
            case R.id.e_add_birthday:
                showDailog();
                break;

        }
    }


    private void showDailog() {
        calendar =  Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //monthOfYear 得到的月份会减1所以我们要加1
                String time =Integer.toString(year) + "/" + String.valueOf(monthOfYear + 1) + "/" +  String.valueOf(dayOfMonth);
                TextView tx=(TextView)findViewById(R.id.e_add_birthday) ;
                tx.setText(time);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        //自动弹出键盘问题解决
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


    }




}

