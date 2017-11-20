package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;
import com.example.edgu1.angleseahospital.R;

import java.util.List;


/**
 * Created by edgu1 on 2017/11/15.
 */

public class drugsdetails extends Activity {

    private SQLiteHelper dbHandler=null;
    private List<Drug> drugs;
    private Drug drug = null;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugsdetails);



        dbHandler = new SQLiteHelper(this);
        drug =(Drug)getIntent().getSerializableExtra("drug");

        TextView drugName = (TextView)findViewById(R.id.Drugname01);
        TextView drugproductionDate = (TextView)findViewById(R.id.productionDate01);
        TextView drugshelfLife = (TextView)findViewById(R.id.shelfLife01);
        TextView drugspecification = (TextView)findViewById(R.id.specification01);
        TextView drugsManufacturer = (TextView)findViewById(R.id.Manufacturer01);
        drugName.setText(String.valueOf(drug.getName()));
        drugproductionDate.setText(String.valueOf(drug.getProductionDate()));
        drugshelfLife.setText(String.valueOf(drug.getShelfLife()));
        drugspecification.setText(String.valueOf(drug.getSpecification()));
        drugsManufacturer.setText(String.valueOf(drug.getManufacturer()));
    }

    public void DrugUpdate(View view){
        Intent intent=new Intent(this,Add_drugs.class);
        intent.putExtra("drug",drug);
        startActivity(intent);
    }

    public void DrugsDelete(View view){
        dbHandler.deleteDrug(drug.getId());
        Intent intent=new Intent(this,DrugsInformation.class);
        intent.putExtra("drug","Delete successfully");
        startActivity(intent);
    }
}

