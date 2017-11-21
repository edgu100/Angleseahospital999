package com.example.edgu1.angleseahospital;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;


import java.util.List;
import java.util.Map;

import static com.example.edgu1.angleseahospital.Parameters.pid;


public class Patient_info extends AppCompatActivity {
    private SQLiteHelper drugdb = null;
    private List<Map<String,String>> patientDrugs;
    private Patient patient;

    ///////////////////////----ListView Function----/////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        drugdb = new SQLiteHelper(this);
        patient = (Patient) getIntent().getSerializableExtra("patient");
        patientDrugs = drugdb.patientDrugs(patient.getId());


        TextView RoomNo = (TextView) findViewById(R.id.RoomEditView);
        RoomNo.setText(patient.getRoomNo());
        TextView PatientName = (TextView) findViewById(R.id.PatientNameEdit);
        PatientName.setText(patient.getName());
        TextView BOD = (TextView) findViewById(R.id.BODEdit);
        BOD.setText(patient.getBirthDay());
        TextView WIGHTEdit = (TextView) findViewById(R.id.WIGHTEdit);
        WIGHTEdit.setText(patient.getWeight()+"");
        TextView NHI = (TextView) findViewById(R.id.NHINO);
        NHI.setText(patient.getNHINo());

        Button AddButton = (Button)findViewById(R.id.AddButton);
        Button TrackButton = (Button)findViewById(R.id.TrackButton);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Patient_info.this, PatientDrugAddListPage.class);
                pid = patient.getId();
                startActivity(i);
            }
        });
        TrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Patient_info.this,TrackSystem.class);
                i.putExtra("pid",patient.getId());
                startActivity(i);                                ////track System
            }
        });



        Patient_info.patientDrugsAdapter CusTaskAdapter = new Patient_info.patientDrugsAdapter();
        ListView taskList = (ListView)findViewById(R.id.DrugList);
        taskList.setAdapter(CusTaskAdapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> patient = patientDrugs.get(position);
                PatientDrugs pds= drugdb.getPatientDrugsById(patient.get("pdid"));
                Intent intent=new Intent(getApplicationContext(),PatientAddPage.class);
                intent.putExtra("pid",pds.getPatientId());
                startActivity(intent);
            }
        });




    }




    //Patient CustomAdapter
    class patientDrugsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return patientDrugs.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.activity_drugs_detail_row, null);
            Map<String,String> Drug = patientDrugs.get(position);

            TextView drugName = (TextView) view.findViewById(R.id.apir_DrugEditName);
            drugName.setText(Drug.get("pName"));
            TextView drugDosage = (TextView) view.findViewById(R.id.apir_DosageEditView);
            drugDosage.setText(Drug.get("dosage"));
            TextView Frequency = (TextView) view.findViewById(R.id.apir_FrequencyEdit);
            Frequency.setText(Drug.get("frequency"));
            TextView timestamp = (TextView) view.findViewById(R.id.apir_timeStampEdit);
            timestamp.setText(Drug.get("timeStamp"));
            TextView signTime = (TextView) view.findViewById(R.id.apir_SignTimeEdit);
            signTime.setText(Drug.get("signTime"));
            return view;
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Create PatientInfo share information file, MODE_PRIVATE is just this device can access it
        SharedPreferences sharedPre = getSharedPreferences("info", Context.MODE_PRIVATE);
        //Give the write right
        SharedPreferences.Editor editor = sharedPre.edit();
        String info = "";
        int id=item.getItemId();
        if(id==R.id.setting){
            Intent intent = new Intent(Patient_info.this, editPatient.class);
            pid=patient.getId();
            startActivity(intent);
            return true;
        }
        if(id==R.id.delete) {
            pid=patient.getId();
            drugdb.deletePatient(pid);
            info = "Delete successfully!";
            editor.putString("msg", info);
            Intent intent = new Intent(Patient_info.this, PatientListPage.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



