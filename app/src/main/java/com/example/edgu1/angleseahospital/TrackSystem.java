package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by edgu1 on 2017/11/20.
 */

public class TrackSystem extends Activity{
    private SQLiteHelper dbHandler=null;
    private List<Drug> drugstrack;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracksystem);

        dbHandler = new SQLiteHelper(this);
        drugstrack = dbHandler.getDrugsByName(null);

        DrugListAdapter productListAdapter = new DrugListAdapter();
        ListView DrugList = (ListView) findViewById(R.id.DrugsDetails);
        DrugList.setAdapter(productListAdapter);

    }

    //Patient CustomAdapter
    class DrugListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return drugstrack.size();
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
            view = getLayoutInflater().inflate(R.layout.simple_track_list, null);
            Drug taskInfo = drugstrack.get(position);
            TextView foucustime = (TextView) view.findViewById(R.id.foucustime);
            foucustime.setText("a");
            TextView Realtime = (TextView) view.findViewById(R.id.Realtime);
            Realtime.setText("b");
            return view;
        }
    }

}
