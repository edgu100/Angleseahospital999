package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;
import com.example.edgu1.angleseahospital.DB.Track;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by edgu1 on 2017/11/20.
 */

public class TrackSystem extends Activity{
    private SQLiteHelper dbHandler=null;
    private List<Map<String,String>> tracks;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracksystem);
        //PatientId
        String pid = String.valueOf(Parameters.pid);
        dbHandler = new SQLiteHelper(this);
        tracks = dbHandler.getTracks(pid);

        TrackAdapter trackAdapter = new TrackAdapter();
        ListView trackList = (ListView) findViewById(R.id.trackList);
        trackList.setAdapter(trackAdapter);
        trackList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> track = tracks.get(position);
                String tid = track.get("tid");
                String realtime = track.get("realtime");
                if(realtime==null||"".equals(realtime)){
                    Intent intent=new Intent(getApplicationContext(),Signature.class);
                    intent.putExtra("track",tid);
                    startActivity(intent);
                }else{
                    Track t = dbHandler.getTrackById(tid);
                    Intent intent=new Intent(getApplicationContext(),TrackSignature.class);
                    intent.putExtra("track",t);
                    startActivity(intent);
                }
            }
        });

    }

    //Patient CustomAdapter
    class TrackAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return tracks.size();
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
            view = getLayoutInflater().inflate(R.layout.tracks_row, null);
            Map<String,String> track = tracks.get(position);
            TextView dName = (TextView) view.findViewById(R.id.dName);
            dName.setText(track.get("dname"));
            TextView focustime = (TextView) view.findViewById(R.id.focustime);
            focustime.setText(track.get("focustime"));
            TextView realtime = (TextView) view.findViewById(R.id.realtime);
            realtime.setText(track.get("realtime"));
            return view;
        }
    }

    public void cancle(View v){
        Intent i=new Intent(this,Patient_info.class);
        startActivity(i);

    }

}
