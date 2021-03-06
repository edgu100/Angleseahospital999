package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.SQLiteHelper;
import com.example.edgu1.angleseahospital.DB.User;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




/**
 * Created by edgu1 on 2017/10/23.
 */



public class loginpage extends Activity {

    private TextView fpas;
    private Button regi;
    private Context context = null;
    private SQLiteHelper sqLiteHelper=null;
    private ProgressDialog progressDialog;

    EditText e1, e2;
    ImageView m1, m2;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        init();
        context = this;
        sqLiteHelper = new SQLiteHelper(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Send email...");

        //Find out password
        fpas=(TextView)findViewById(R.id.findoutpasswoed);
        fpas.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        fpas.setText("Forget your password?");
        fpas.setTextColor(Color.rgb(0,99,175));
        fpas.setTextSize(15);
        fpas.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailText=(EditText)findViewById(R.id.Email);
                email = emailText.getText().toString().trim();
                if(!"".equals(email)){
                    User user = sqLiteHelper.getUserByEmail(email);
                    if(user == null){
                        Toast.makeText(context,"Please register first!", Toast.LENGTH_LONG).show();
                    }else{
                        progressDialog.show();
                        pwd = randomPwd();
                        user.setPassword(pwd);
                        sqLiteHelper.updateUser(user);
                        sendEmail();
                    }
                }else{
                    Toast.makeText(context,"Please enter your email!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Register
        regi=(Button)findViewById(R.id.Register);
    }

    //Register
    public void Register(View v){
        Intent i= new Intent(loginpage.this,registerpage.class);
        startActivity(i);
    }

    public void signIn(View v){
        EditText emailText=(EditText)findViewById(R.id.Email);
        email = emailText.getText().toString().trim();
        EditText passwordText=(EditText)findViewById(R.id.password);
        User user = sqLiteHelper.getUserByEmail(email);
        if(user == null){
            Toast.makeText(context,"Email or password is incorrect!", Toast.LENGTH_LONG).show();
        }else{
            if(!user.getPassword().equals(passwordText.getText().toString().trim())){
                Toast.makeText(context,"Email or password is incorrect!", Toast.LENGTH_LONG).show();
            }else{
                Intent i= new Intent(loginpage.this,Mainpage.class);
                startActivity(i);
            }
        }
    }

    Session session = null;
    String email="";
    String pwd="";

    public String randomPwd() {
        String randomcode = "";
        for(int i=0;i<6;i++) {
            int value = (int)(Math.random()*58+65);
            while(value>=91 && value<=96)
                value = (int)(Math.random()*58+65);
            randomcode = randomcode + (char)value;

        }
        return randomcode;
    }

    private void sendEmail(){
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("duanlanling@gmail.com","$Test123");
            }
        });

        loginpage.RetriveFeedTask task = new loginpage.RetriveFeedTask();
        task.execute();
    }

    private void init() {
        // TODO Auto-generated method stub
        e1 = (EditText) findViewById(R.id.Email);
        e2 = (EditText) findViewById(R.id.password);
        m1 = (ImageView) findViewById(R.id.del_email);
        m2 = (ImageView) findViewById(R.id.del_password);
        // 添加清楚监听器大气
        addclerListener(e1, m1);
        addclerListener(e2, m2);

    }


    class RetriveFeedTask extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("duanlanling@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
                message.setSubject("Dosage system password reset");
                message.setContent("Your new password is:"+pwd,"text/html;charset=utf-8");
                Transport.send(message);
            }catch (Exception e){
                Toast.makeText(context,"Failed to send email!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(context,"Please check your email!", Toast.LENGTH_LONG).show();
            progressDialog.cancel();
        }
    }

    public static void addclerListener(final EditText e1, final ImageView m1) {

        e1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                // 监听如果输入串长度大于0那么就显示clear按钮。
                String s1 = s + "";
                if (s.length() > 0) {
                    m1.setVisibility(View.VISIBLE);
                } else {
                    m1.setVisibility(View.INVISIBLE);
                }

            }
        });

        m1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 清空输入框
                e1.setText("");

            }
        });



}

}



