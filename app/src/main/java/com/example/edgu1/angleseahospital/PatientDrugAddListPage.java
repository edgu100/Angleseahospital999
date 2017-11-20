package com.example.edgu1.angleseahospital;

/**
 * Created by WX on 2017/11/20.
 */

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.PatientDrugs;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class PatientDrugAddListPage extends Activity {

    ArrayList<String> selectedItems = new ArrayList<>();
    private SQLiteHelper sqLiteHelper = null;
    private PatientDrugs oldpatientdrug;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_drug_list_add);

        sqLiteHelper = new SQLiteHelper(this);

        ListView chl = (ListView) findViewById(R.id.pdla_list);
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        List<Drug> drugs = sqLiteHelper.getDrugsByName(null);
        String [] items = new String[drugs.size()];
        for(int i = 0;i < items.length;i++){
            items[i] = drugs.get(i).getName();
        }

        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, R.layout.patient_drug_list_add_row, R.id.pdlar_row, items);
        chl.setAdapter(adpater);

        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedItem = ((TextView)view).getText().toString();

                if(selectedItems.contains(selectedItem)){

                    selectedItems.remove(selectedItem);//uncheck item
                }
                else
                    selectedItems.add(selectedItem);
            }
        });
    }

    public void pdla_save_onClick(View view){
        String items = "";
        for(String item:selectedItems){
            items += "-" + items + "\n";
        }
        Toast.makeText(this, "You have selected \n"+ items, Toast.LENGTH_LONG).show();





    }
}
