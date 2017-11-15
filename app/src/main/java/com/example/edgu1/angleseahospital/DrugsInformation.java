package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;

/**
 * Created by edgu1 on 2017/11/15.
 */

public class DrugsInformation extends Activity {
    private SQLiteHelper dbHandler=null;
    private List<Drug> drugs;

    @Override
    protected void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugsinfotmation);
        //drugs = dbHandler

        ListView DrugslistView = (ListView) findViewById(R.id.DrugsDetails);
        //创建数据适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_drugs_list, databaseList());
        //给ListView添加数据
        DrugslistView.setAdapter(adapter);
    }



}

