package com.example.edgu1.angleseahospital;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

public class Mainpage extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        SQLiteHelper db=new SQLiteHelper(this);

        dl = (DrawerLayout)findViewById(R.id.tomainpage);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_home:
//                        Intent h = new Intent(Mainpage.this, Mainpage.class);
//                        startActivity(h);
                        break;
                    case R.id.nav_patient:
                        Intent p = new Intent(Mainpage.this, PatientListPage.class);
                        startActivity(p);
                        break;
                    case R.id.nav_drug:
                        Intent d = new Intent(Mainpage.this, DrugsInformation.class);
                        startActivity(d);
                        break;
                    case R.id.nav_task:
                        Intent t = new Intent(Mainpage.this, CusTask.class);
                        startActivity(t);
                        break;
                }

                return true;
            }
        });

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
