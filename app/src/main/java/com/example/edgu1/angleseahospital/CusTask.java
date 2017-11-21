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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;
import java.util.Map;

public class CusTask extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    private SQLiteHelper db;
    List<Map<String,String>> pds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_task);

        dl = (DrawerLayout)findViewById(R.id.cus_list);
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
                        Intent h = new Intent(CusTask.this, Mainpage.class);
                        startActivity(h);
                        break;
                    case R.id.nav_patient:
                        Intent p = new Intent(CusTask.this, PatientListPage.class);
                        startActivity(p);
                        break;
                    case R.id.nav_drug:
                        Intent d = new Intent(CusTask.this, DrugsInformation.class);
                        startActivity(d);
                        break;
                    case R.id.nav_task:
//                        Intent t = new Intent(CusTask.this, CusTask.class);
//                        startActivity(t);
                        break;
                }

                return true;
            }
        });



        db = new SQLiteHelper(this);
        pds = db.getTasks();
        CusTaskAdapter CusTaskAdapter = new CusTaskAdapter();
        ListView taskList = (ListView)findViewById(R.id.taskList);
        taskList.setAdapter(CusTaskAdapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> taskInfo = pds.get(position);
                Intent intent=new Intent(getApplicationContext(),Signature.class);
                intent.putExtra("track",taskInfo.get("pdid"));
                startActivity(intent);
            }
        });

    }

    //Patient CustomAdapter
    class CusTaskAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pds.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.task_row, null);
            Map<String,String> taskInfo = pds.get(position);
            TextView taskName = (TextView) view.findViewById(R.id.task_name);
            String str="Patient:" + taskInfo.get("pname")+"    Drug:"+taskInfo.get("dname");
            taskName.setText(str);
            return view;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
