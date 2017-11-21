package com.example.edgu1.angleseahospital;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.edgu1.angleseahospital.DB.Track;

import java.io.File;

public class TrackSignature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_signature);

        Track track= (Track) getIntent().getSerializableExtra("track");
        if(track!=null){
            String path1 = getAlbumStorageDir().getPath()+"/"+track.getSignature1();
            ImageView img1 = (ImageView) findViewById (R.id.imageView1);
            BitmapFactory.Options opts1 = new BitmapFactory.Options();
            opts1.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path1, opts1);
            opts1.inSampleSize = 4;
            opts1.inJustDecodeBounds = false;
            Bitmap bitmap1 = BitmapFactory.decodeFile(path1, opts1);
            img1.setImageBitmap(bitmap1);

            String path2 = getAlbumStorageDir().getPath()+"/"+track.getSignature2();
            ImageView img2 = (ImageView) findViewById (R.id.imageView2);
            BitmapFactory.Options opts2 = new BitmapFactory.Options();
            opts2.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path2, opts2);
            opts2.inSampleSize = 4;
            opts2.inJustDecodeBounds = false;
            Bitmap bitmap2 = BitmapFactory.decodeFile(path2, opts2);
            img2.setImageBitmap(bitmap2);
        }
    }

    public File getAlbumStorageDir() {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "SignaturePad");
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }
}
