package com.example.edgu1.angleseahospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

public class Mainpage extends AppCompatActivity {

    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        SQLiteHelper db=new SQLiteHelper(this);

        msg = (TextView)findViewById(R.id.notice);
        int num = db.getTasks().size();
        if(num > 0){
            String mStr = "You have "+num+" messages!";
            msg.setText(mStr);
        }
    }

    public void taskDetail(View v){
        Intent i= new Intent(Mainpage.this,CusTask.class);
        startActivity(i);
    }

    public void DrugsDetails(View v){
        Intent i= new Intent(Mainpage.this,DrugsInformation.class);
        startActivity(i);
    }

    public void patientDetail(View v){
        Intent i= new Intent(Mainpage.this,PatientListPage.class);
        startActivity(i);
    }

}
