package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.Calendar;
import java.util.List;


/**
 * Created by edgu1 on 2017/11/20.
 */

public class Add_drugs extends Activity {
    private SQLiteHelper dbHandler=null;
    private Drug olddrug = null;

    private DatePickerDialog datePickerDialog;
    private Calendar calendar;


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
            TextView productiondataEdit=(TextView)findViewById(R.id.AdddrugsProductionData);
            drug.setProductionDate(productiondataEdit.getText().toString());
            TextView shelflifeEdit =(TextView)findViewById(R.id.AdddrugsShelfLife) ;
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

////////////////choose date////////////////
    public void DatePick(View v){
        switch (v.getId()) {
            case R.id.AdddrugsProductionData:
                showDailog();
                break;
            case R.id.AdddrugsShelfLife:
                showDailog2();
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
                String time =Integer.toString(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" +  String.valueOf(year);
                TextView tx=(TextView)findViewById(R.id.AdddrugsProductionData) ;
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


    private void showDailog2() {
        calendar =  Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //monthOfYear 得到的月份会减1所以我们要加1
                String time =Integer.toString(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" +  String.valueOf(year);
                TextView tx=(TextView)findViewById(R.id.AdddrugsShelfLife) ;
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
