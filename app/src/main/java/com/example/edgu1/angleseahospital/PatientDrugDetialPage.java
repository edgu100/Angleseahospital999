package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

/**
 * Created by WX on 2017/11/21.
 */

public class PatientDrugDetialPage extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_drug_details);

        String did = getIntent().getStringExtra("did");
        String pid = getIntent().getStringExtra("pid");

    }


   public void pdd_calculate_onClick(View v){
       EditText standing_order = (EditText)findViewById(R.id.pdd_so);
       String getsOrder = standing_order.getText().toString();

       EditText weight = (EditText)findViewById(R.id.pdd_wight);
       String getweight = weight.getText().toString();

       EditText pdd_dmg = (EditText)findViewById(R.id.pdd_dmg);
       String getpdd_dmg = pdd_dmg.getText().toString();

       EditText pdd_dml = (EditText)findViewById(R.id.pdd_dml);
       String getpdd_dml = pdd_dml.getText().toString();

       Double dosage;
       Double total_medication;

       if(getsOrder!=null&&!"".equals(getsOrder)&&getweight!=null&&!"".equals(getweight)){
           try
           {
               // it means it is double & calculate dosage
               dosage = Double.parseDouble(getsOrder) * Double.parseDouble(getweight);
               TextView dosageText = (TextView) findViewById(R.id.pdd_getDosage);
               dosageText.setText(String.valueOf(dosage));

               //calculate Total Medication
               total_medication = (dosage / Double.parseDouble(getpdd_dmg)) * Double.parseDouble(getpdd_dml);
               TextView total_medicationText = (TextView) findViewById(R.id.pdd_getTm);
               total_medicationText.setText(String.valueOf(total_medication));

           } catch (Exception e1) {
               // this means it is not double
               e1.printStackTrace();
           }


       }


   }




}
