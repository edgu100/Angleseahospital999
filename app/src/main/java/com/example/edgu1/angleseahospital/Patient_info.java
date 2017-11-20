package com.example.edgu1.angleseahospital;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.edgu1.angleseahospital.PatientListPage.getCurrentAgeByBirthdate;

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
        Map<String, String> d = patientDrugs.get(0);
        Button AddButton = (Button)findViewById(R.id.ADD);
        Button TrackButton = (Button)findViewById(R.id.TrackButton);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Patient_info.this, Add_drugs.class);

                Intent i = new Intent(Patient_info.this,TrackSystem.class);
                i.putExtra("pid","");
                startActivity(i);                                ////ADD PATIENT
                startActivity(intent);

            }
        });
        TrackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Patient_info.this, TrackSystem.class);
                Intent i = new Intent(Patient_info.this,TrackSystem.class);
                i.putExtra("pid","");
                startActivity(i);                                ////track System
                startActivity(intent);

            }
        });






        TextView RoomNo = (TextView) findViewById(R.id.RoomEditView);
        RoomNo.setText(d.get("pRoomNo"));
        TextView PatientName = (TextView) findViewById(R.id.PatientNameEdit);
        PatientName.setText(d.get("pName"));
        TextView BOD = (TextView) findViewById(R.id.BODEdit);
        BOD.setText(d.get("birthDay"));
        TextView WIGHTEdit = (TextView) findViewById(R.id.WIGHTEdit);
        WIGHTEdit.setText(d.get("weight"));
        TextView NHI = (TextView) findViewById(R.id.NHINO);
        NHI.setText(d.get("NHINo"));

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

}



