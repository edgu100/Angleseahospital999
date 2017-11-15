package com.example.edgu1.angleseahospital;

/**
 * Created by WX on 2017/11/15.
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.text.SimpleDateFormat;
import 	java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PatientListPage extends Activity {

    private SQLiteHelper patientsdb = null;
    private List<Patient> patients;

    ///////////////////////----ListView Function----/////////////////////////////////
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_list);

        patientsdb = new SQLiteHelper(this);
        patients = patientsdb.getPatientsByName(null);

        try{
            PatientListAdapter patientListAdapter = new PatientListAdapter();
            ListView patientList =(ListView)findViewById(R.id.Patient_List);
            patientList.setAdapter(patientListAdapter);

//            patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Patient product = patients.get(position);
//                    Intent intent=new Intent(getApplicationContext(),AddProduct.class);
//                    intent.putExtra("product",patient);
//                    startActivity(intent);
//                }
//            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    class PatientListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return patients.size();
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
            view = getLayoutInflater().inflate(R.layout.patient_list_row, null);
            Patient patientInfo = patients.get(position);

            //Get Patient information
            TextView PatientName = (TextView) view.findViewById(R.id.Patient_name);
            TextView PatientAge = (TextView) view.findViewById(R.id.Patient_age);
            TextView PatientWight = (TextView) view.findViewById(R.id.Patient_wight);
            TextView PatientNHINo = (TextView) view.findViewById(R.id.Patient_ID);
            TextView PatientRoomNo = (TextView) view.findViewById(R.id.Patient_RoomNo);

            //Set the info on screen
            PatientName.setText(patientInfo.getName());
            PatientAge.setText(getCurrentAgeByBirthdate(patientInfo.getBirthDay()));
            PatientWight.setText(String.valueOf(patientInfo.getWeight()));
            PatientNHINo.setText(patientInfo.getNHINo());
            PatientRoomNo.setText(patientInfo.getRoomNo());

            return view;
        }
    }


    //Get Patient Age by Birthday
    public static int getCurrentAgeByBirthdate(String brithday) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/mm/dd");
            String currentTime = formatDate.format(calendar.getTime());
            Date today = formatDate.parse(currentTime);
            Date brithDay = formatDate.parse(brithday);
            return today.getYear() - brithDay.getYear();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return 0;
    }




    ///////////////////////----Button Function----/////////////////////////////////




    ///////////////////////----Search Function----/////////////////////////////////

}

