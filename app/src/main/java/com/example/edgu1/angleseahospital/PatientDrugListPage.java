package com.example.edgu1.angleseahospital;

/**
 * Created by WX on 2017/11/20.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;
import java.util.Map;

public class PatientDrugListPage extends Activity {

//    private SQLiteHelper patientsgdb = null;
//    private List<PatientDrugs> patientdrug;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.patient_drug_list);
//
//        patientsgdb = new SQLiteHelper(this);
//        patientdrug = patientsgdb.getPatientDrugsBypatientId(null);
//
//        try{
//            PatientDrugListAdapter patientDrugListAdapter = new PatientDrugListAdapter();
//            ListView patientDrugList = (ListView)findViewById(R.id.pdl_ListView);
//            patientDrugList.setAdapter(patientDrugListAdapter);
//
//            patientDrugList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                    Patient patient = patientdrug.get(position);
////                    Intent intent=new Intent(getApplicationContext(),PatientAddPage.class);
////                    intent.putExtra("patient",patient);
////                    startActivity(intent);
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    //PatientDrug CustomAdapter
//    class PatientDrugListAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return patientdrug.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View view, ViewGroup viewGroup) {
//            view = getLayoutInflater().inflate(R.layout.patient_drug_list_row, null);
//            PatientDrugs patientdrugInfo = patientdrug.get(position);
//
//           //Get PatientDrug information
//           TextView PatientDrugName = (TextView) view.findViewById(R.id.pdlr_drug);
//
//            //Set the info on screen
//            PatientDrugName.setText(patientInfo.getName());
//
//
//            return view;
//        }
//    }

    private SQLiteHelper patientsgdb = null;
    private List<Map<String,String>> patientdrug;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_drug_list);
        patientsgdb = new SQLiteHelper(this);
        patientdrug = patientsgdb.getTasks();
        CusTaskAdapter CusTaskAdapter = new CusTaskAdapter();
        ListView taskList = (ListView)findViewById(R.id.pdl_ListView);
        taskList.setAdapter(CusTaskAdapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> taskInfo = patientdrug.get(position);
//                Intent intent=new Intent(getApplicationContext(),Signature.class);
//                intent.putExtra("pdid",taskInfo.get("pdid"));
//                startActivity(intent);
            }
        });

    }

    //PatientDrug CustomAdapter
    class CusTaskAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return patientdrug.size();
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
            view = getLayoutInflater().inflate(R.layout.patient_drug_list_row, null);
            Map<String,String> taskInfo = patientdrug.get(position);
            TextView taskName = (TextView) view.findViewById(R.id.pdlr_drug);
            String str = taskInfo.get("dname");
            taskName.setText(str);
            return view;
        }
    }

    public void pdl_add_onClick(View v){
        Intent pdl_add = new Intent(PatientDrugListPage.this, PatientDrugAddListPage.class);
        startActivity(pdl_add);
    }

    public void pdl_cancel_onClick(View v){
        Intent pdl_cancel = new Intent(PatientDrugListPage.this, Patient_info.class);
        startActivity(pdl_cancel);
    }


}
