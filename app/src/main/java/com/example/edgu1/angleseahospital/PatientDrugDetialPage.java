package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by WX on 2017/11/21.
 */

public class PatientDrugDetialPage extends Activity {

    private SQLiteHelper dbHandler = null;
    private Drug oldDrug = null;
    private PatientDrugs oldPatientDrug = null;
    private List<Map<String,String>> patientDrugs;
    private List<Drug> drugs;
    private int age=0;
    private String DrugID;
    private String PatientID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_drug_details);
        dbHandler = new SQLiteHelper(this);

        //Get Patient ID & Drug ID
        String ids = getIntent().getStringExtra("ids");
        String[] idsarr = ids.split(",");
        DrugID = String.valueOf(idsarr[0]);
        PatientID = String.valueOf(idsarr[1]);

        dbHandler = new SQLiteHelper(this);

        oldDrug =dbHandler.getDrugById(DrugID);
        Patient p =dbHandler.getPatientById(Integer.parseInt(PatientID));

        EditText pdd_dmg = (EditText)findViewById(R.id.pdd_dmg);
        pdd_dmg.setText(oldDrug.getMilligrams());

        EditText pdd_dml = (EditText)findViewById(R.id.pdd_dml);
        pdd_dml.setText(oldDrug.getMilliliters());

        EditText pdd_wight = (EditText)findViewById(R.id.pdd_wight);
        pdd_wight.setText(p.getWeight()+"");

        try {
            age = TaskTimeTool.getAge(p.getBirthDay());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(age<16){
            EditText pdd_so = (EditText)findViewById(R.id.pdd_so);
            pdd_so.setEnabled(false);
            EditText pddWight = (EditText)findViewById(R.id.pdd_wight);
            pddWight.setText("0");
            pddWight.setEnabled(false);
        }
    }


   public void pdd_calculate_onClick(View v) {
       EditText standing_order = (EditText) findViewById(R.id.pdd_so);
       String getsOrder = standing_order.getText().toString();

       EditText weight = (EditText) findViewById(R.id.pdd_wight);
       String getweight = weight.getText().toString();

       EditText pdd_get_dosage = (EditText) findViewById(R.id.pdd_get_dosage);
       String getdosage = pdd_get_dosage.getText().toString();


       EditText pdd_dmg = (EditText) findViewById(R.id.pdd_dmg);

       EditText pdd_dml = (EditText) findViewById(R.id.pdd_dml);
       //
       Double dosage;
       Double total_medication;

       if (age > 16) {
           if (getsOrder != null && !"".equals(getsOrder) && getweight != null && !"".equals(getweight)) {
               try {
                   // it means it is double & calculate dosage
                   dosage = Double.parseDouble(getsOrder) * Double.parseDouble(getweight);
                   TextView dosageText = (TextView) findViewById(R.id.pdd_getDosage);
                   dosageText.setText(String.valueOf(dosage));
                   pdd_get_dosage.setText(String.valueOf(dosage));

                   //calculate Total Medication
                   total_medication = (dosage / Double.parseDouble(pdd_dmg.getText().toString())) * Double.parseDouble(pdd_dml.getText().toString());
                   TextView total_medicationText = (TextView) findViewById(R.id.pdd_getTm);
                   total_medicationText.setText(String.valueOf(total_medication));

               } catch (Exception e1) {
                   // this means it is not double
                   e1.printStackTrace();
               }
           }
       }else{
           String dmgStr=pdd_dmg.getText().toString();
           String dmlStr=pdd_dml.getText().toString();
           if (!"".equals(dmgStr) && !"".equals(getdosage)&& !"".equals(dmlStr)) {
               try {
                   //calculate Total Medication
                   total_medication = (Double.parseDouble(getdosage) / Double.parseDouble(pdd_dmg.getText().toString())) * Double.parseDouble(pdd_dml.getText().toString());
                   TextView total_medicationText = (TextView) findViewById(R.id.pdd_getTm);
                   total_medicationText.setText(String.valueOf(total_medication));
               } catch (Exception e1) {
                   // this means it is not double
                   e1.printStackTrace();
               }
           }
       }
   }
   public void comfirm(View v){
        Intent i= new Intent(this,Signature.class);
        PatientDrugs pd = setPatientDrugsDetail();
        i.putExtra("pd",pd);
        startActivity(i);
   }

   private PatientDrugs setPatientDrugsDetail(){
       PatientDrugs p = new PatientDrugs();
       p.setPatientId(Integer.parseInt(PatientID));
       p.setDrugsId(Integer.parseInt(DrugID));
       TextView dosageText = (TextView) findViewById(R.id.pdd_getDosage);
       p.setDosage(dosageText.getText().toString());
       TextView pdd_getTm = (TextView) findViewById(R.id.pdd_getTm);
       p.setMedication(pdd_getTm.getText().toString());
       EditText pdd_FHours = (EditText) findViewById(R.id.pdd_FHours);
       p.setFrequency(Double.parseDouble(pdd_FHours.getText().toString()));
       return p;
   }

}
