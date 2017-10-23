package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by edgu1 on 2017/10/23.
 */



public class loginpage extends Activity {

    private TextView fpas;
    private Button regi;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);


        //Find out password
        fpas=(TextView)findViewById(R.id.findoutpasswoed);
        fpas.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        fpas.setText("Forget your password?");
        fpas.setTextColor(Color.rgb(0,99,175));
        fpas.setTextSize(15);

        //Register
        regi=(Button)findViewById(R.id.Register);
    }

    //Register
    public void Register(View v){
        Intent i= new Intent(loginpage.this,registerpage.class);
        startActivity(i);
    }




}
