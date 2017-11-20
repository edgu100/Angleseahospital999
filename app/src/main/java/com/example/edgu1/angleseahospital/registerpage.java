package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.SQLiteHelper;
import com.example.edgu1.angleseahospital.DB.User;

/**
 * Created by edgu1 on 2017/10/23.
 */

public class registerpage extends Activity {

    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);

        sqLiteHelper = new SQLiteHelper(this);
    }

    public void addUser(View v){
        EditText name=(EditText)findViewById(R.id.et1);
        EditText pwd=(EditText)findViewById(R.id.et3);
        EditText email=(EditText)findViewById(R.id.et2);
        User u=sqLiteHelper.getUserByEmail(email.getText().toString());
        if(u != null){
            Toast.makeText(this,"This email already exists!", Toast.LENGTH_LONG).show();
        }else{
            u=new User();
            u.setName(name.getText().toString());
            u.setEmail(email.getText().toString());
            u.setPassword(pwd.getText().toString());
            sqLiteHelper.addUser(u);
            Button bnt1 = (Button) findViewById(R.id.b1);
            Button bnt2 = (Button) findViewById(R.id.b2);
            bnt1.setEnabled(false);
            bnt2.setEnabled(false);
            Toast.makeText(this,"Registration success!", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplicationContext(),loginpage.class);
            startActivity(intent);
        }
    }

    public void cancleUser(View v){
        Intent intent=new Intent(getApplicationContext(),loginpage.class);
        startActivity(intent);
    }

}
