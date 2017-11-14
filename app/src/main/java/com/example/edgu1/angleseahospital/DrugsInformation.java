package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by edgu1 on 2017/11/15.
 */

public class DrugsInformation extends Activity {
    @Override
    protected void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugsinfotmation);

        String[] drugs={};
        ListAdapter drugsAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,drugs);
        ListView drugsListView=(ListView)findViewById(R.id.DrugsDetails);
        drugsListView.setAdapter(drugsAdapter);
    }
}
