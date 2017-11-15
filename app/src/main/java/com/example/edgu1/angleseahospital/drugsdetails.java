package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugsdetails);
        dbHandler = new SQLiteHelper(this);


    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        Drug drug = drugs.get(position);
        TextView drugName = (TextView) findViewById(R.id.Drugname01);
        TextView drugproductionDate = (TextView) findViewById(R.id.productionDate01);
        TextView drugshelfLife = (TextView) findViewById(R.id.shelfLife01);
        TextView drugspecification = (TextView) findViewById(R.id.specification01);
        TextView drugsmanufacturer = (TextView) findViewById(R.id.Manufacturer01);

        drugName.setText(drug.getName());
        drugproductionDate.setText(String.valueOf(drug.getProductionDate()));
        drugshelfLife.setText(String.valueOf(drug.getShelfLife()));
        drugspecification.setText(String.valueOf(drug.getSpecification()));
        return view;
    }

}

