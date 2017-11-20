package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;


/**
 * Created by edgu1 on 2017/11/20.
 */

public class Add_drugs extends Activity {
    private SQLiteHelper dbHandler=null;
    private Drug olddrug = null;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drugs);
        dbHandler = new SQLiteHelper(this);

        olddrug = (Drug) getIntent().getSerializableExtra("drug");



        if(olddrug!=null)
        {
            TextView drugName = (TextView)findViewById(R.id.AdddrugsName);
            TextView drugproductionDate = (TextView)findViewById(R.id.AdddrugsProductionData);
            TextView drugshelfLife = (TextView)findViewById(R.id.AdddrugsShelfLife);
            TextView drugspecification = (TextView)findViewById(R.id.AdddrugsSpecification);
            TextView drugsManufacturer = (TextView)findViewById(R.id.AdddrugsManufacture);
            drugName.setText(String.valueOf(olddrug.getName()));
            drugproductionDate.setText(String.valueOf(olddrug.getProductionDate()));
            drugshelfLife.setText(String.valueOf(olddrug.getShelfLife()));
            drugspecification.setText(String.valueOf(olddrug.getSpecification()));
            drugsManufacturer.setText(String.valueOf(olddrug.getManufacturer()));
        }
    }



    public void cancle(View v){
        Intent i= new Intent(this,DrugsInformation.class);
        startActivity(i);
    }

    public void DrugsDetails_Confirm(View v){
        SharedPreferences sharedPre = getSharedPreferences("info", Context.MODE_PRIVATE);
        //Give the write right
        SharedPreferences.Editor editor = sharedPre.edit();
        String info="";
        try {
            Drug drug = new Drug();
            EditText nameEdit=(EditText)findViewById(R.id.AdddrugsName) ;
            drug.setName(nameEdit.getText().toString());
            EditText manufactureEdit=(EditText)findViewById(R.id.AdddrugsManufacture) ;
            drug.setManufacturer(manufactureEdit.getText().toString());
            EditText specificationEdit=(EditText)findViewById(R.id.AdddrugsSpecification) ;
            drug.setSpecification(specificationEdit.getText().toString());
            EditText productiondataEdit=(EditText)findViewById(R.id.AdddrugsProductionData);
            drug.setProductionDate(productiondataEdit.getText().toString());
            EditText shelflifeEdit =(EditText)findViewById(R.id.AdddrugsShelfLife) ;
            drug.setShelfLife(shelflifeEdit.getText().toString());


            Intent intent=new Intent(getApplicationContext(),DrugsInformation.class);

        if(olddrug == null){
            dbHandler.addDrug(drug);
            info="Save successfully!" ;

        }else{
            drug.setId(olddrug.getId());
            dbHandler.updateDrug(drug);
            info="Update successfully!";


        }
        }catch (Exception e){
            if(olddrug == null){
                info= "Save failed!";
            }else{
                info = "Update failed!";
            }
            e.printStackTrace();
        }finally {
            editor.putString("msg",info);
            editor.apply();
            Intent intent = new Intent(getApplicationContext(),DrugsInformation.class);
            intent.putExtra("drug",info);
            startActivity(intent);
            finish();
        }


}
}
