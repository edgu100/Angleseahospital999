package com.example.edgu1.angleseahospital;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.Patient;
import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;
import com.example.edgu1.angleseahospital.DB.Track;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Signature extends Activity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SignaturePad mSignaturePad;
    private Button mClearButton;
    private Button mSaveButton;
    private SQLiteHelper sqLiteHelper=null;
    //Track
    private Track track;

    private PatientDrugs pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView(R.layout.activity_signature);
        sqLiteHelper = new SQLiteHelper(this);
        pd = (PatientDrugs) getIntent().getSerializableExtra("pd");

        String tid = getIntent().getStringExtra("track");
        if(tid!=null&&!"".equals(tid)){
            track = sqLiteHelper.getTrackById(tid);
        }

        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Toast.makeText(Signature.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton = (Button) findViewById(R.id.clear_button);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    Toast.makeText(Signature.this, "Signature saved into the Gallery", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signature.this, "Unable to store the signature", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length <= 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(Signature.this, "Cannot write images to external storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            String pname = String.format("Signature_%d.jpg", System.currentTimeMillis());
            File photo = new File(getAlbumStorageDir("SignaturePad"), pname);
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;

            Patient patient =new Patient();
            Date nowTime = new Date();
            SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            if(track==null){
                pd.setSignImg(pname);
                pd.setSignTime(time.format(nowTime));
                sqLiteHelper.addPatientDrugs(pd);
                Track ntrack = new Track();
                ntrack.setPatientId(pd.getPatientId());
                ntrack.setDrugsId(pd.getDrugsId());
                ntrack.setFocustime(TaskTimeTool.culTime(pd.getFrequency()));
                sqLiteHelper.addTracks(ntrack);
                patient = sqLiteHelper.getPatientById(pd.getPatientId());
            }else{
                if(track.getSignature1()==null||"".equals(track.getSignature1())){
                    track.setSignature1(pname);
                }else{
                    track.setSignature2(pname);
                    track.setRealtime(time.format(nowTime));
                }
                if(track.getRealtime()!=null&&!"".equals(track.getRealtime())){
                    Track ntrack = new Track();
                    ntrack.setPatientId(track.getPatientId());
                    ntrack.setDrugsId(track.getDrugsId());
                    PatientDrugs patientDrugs = sqLiteHelper.getPatientDrugsByPDID(track.getPatientId(),track.getDrugsId());
                    ntrack.setFocustime(TaskTimeTool.culTime(patientDrugs.getFrequency()));
                    sqLiteHelper.addTracks(ntrack);
                }
                sqLiteHelper.updateTracks(track);
                patient = sqLiteHelper.getPatientById(track.getPatientId());
            }
            Intent i = new Intent(Signature.this,Patient_info.class);
            i.putExtra("patient",patient);
            startActivity(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        Signature.this.sendBroadcast(mediaScanIntent);
    }

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity the activity from which permissions are checked
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
