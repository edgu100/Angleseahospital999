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
        msg.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Mainpage.this,DrugsInformation.class);
                startActivity(i);
            }
        });
    }

}
