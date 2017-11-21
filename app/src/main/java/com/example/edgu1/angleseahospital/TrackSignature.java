package com.example.edgu1.angleseahospital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.edgu1.angleseahospital.DB.Track;

public class TrackSignature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_signature);

        Track track= (Track) getIntent().getSerializableExtra("track");
        if(track!=null){

        }
    }
}
