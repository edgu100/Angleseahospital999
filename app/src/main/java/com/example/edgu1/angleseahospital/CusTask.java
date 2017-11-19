package com.example.edgu1.angleseahospital;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private SQLiteHelper db;
    List<Map<String,String>> pds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_task);
        db = new SQLiteHelper(this);
        pds = db.getTasks();
        CusTaskAdapter CusTaskAdapter = new CusTaskAdapter();
        ListView taskList = (ListView)findViewById(R.id.taskList);
        taskList.setAdapter(CusTaskAdapter);

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> taskInfo = pds.get(position);
//                Intent intent=new Intent(getApplicationContext(),PatientAddPage.class);
//                intent.putExtra("patient",patient);
//                startActivity(intent);
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
            //Get Patient information
            TextView taskName = (TextView) view.findViewById(R.id.task_name);
            taskName.setText(taskInfo.get("pdid"));
            return view;
        }
    }
}
