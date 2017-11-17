package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;
import com.example.edgu1.angleseahospital.R;

import java.util.List;


/**
 * Created by edgu1 on 2017/11/15.
 */

public class drugsdetails extends Activity {

    private SQLiteHelper dbHandler=null;
    private List<Drug> drugs;
    private Drug drug = null;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugsdetails);
        dbHandler = new SQLiteHelper(this);

        //get product
        String name = getIntent().getStringExtra("name");
        if(name!=null&&!"".equals(name)){
            oldProduct = dbHandler.getProduct(resultCode);
        }else{
            oldProduct = (Product) getIntent().getSerializableExtra("product");
        }
        EditText barCodeEdit= (EditText)findViewById(R.id.barCodeEdit);
        if(oldProduct != null){
            EditText nameEdit= (EditText)findViewById(R.id.nameEdit);
            nameEdit.setText(oldProduct.getProduct());

            EditText priceEdit= (EditText)findViewById(R.id.priceEdit);
            priceEdit.setText(String.valueOf(oldProduct.getPrice()));

            EditText quantityEdit= (EditText)findViewById(R.id.quantityEdit);
            quantityEdit.setText(String.valueOf(oldProduct.getQuantity()));

            barCodeEdit.setText(String.valueOf(oldProduct.getBarcode()));

            EditText descriptionEdit= (EditText)findViewById(R.id.descriptionEdit);
            descriptionEdit.setText(String.valueOf(oldProduct.getDescription()));

            ImageView photoView= (ImageView)findViewById(R.id.photoView);
            photoView.setImageBitmap(loadImageFromStorage(oldProduct.getImage()));
        }else{
            barCodeEdit.setText(resultCode);
        }
    }



}

