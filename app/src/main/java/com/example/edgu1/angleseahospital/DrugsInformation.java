package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;

public class DrugsInformation extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;

    private SQLiteHelper dbHandler=null;
    private List<Drug> drugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugsinfotmation);

        dl = (DrawerLayout)findViewById(R.id.drug_list);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();

        final NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.nav_home:
                        Intent h = new Intent(DrugsInformation.this, Mainpage.class);
                        startActivity(h);
                        break;
                    case R.id.nav_patient:
                        Intent p = new Intent(DrugsInformation.this, PatientListPage.class);
                        startActivity(p);
                        break;
                    case R.id.nav_drug:
//                        Intent d = new Intent(DrugsInformation.this, DrugsInformation.class);
//                        startActivity(d);
                        break;
                    case R.id.nav_task:
                        Intent t = new Intent(DrugsInformation.this, CusTask.class);
                        startActivity(t);
                        break;
                }

                return true;
            }
        });

        String msg = getIntent().getStringExtra("drug");
        if(msg!=null&&!"".equals(msg)){
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }

        dbHandler = new SQLiteHelper(this);
        drugs = dbHandler.getDrugsByName(null);
        try{
            ProductListAdapter productListAdapter=new ProductListAdapter();
            ListView productList =(ListView) findViewById(R.id.DrugsDetails);
            productList.setAdapter(productListAdapter);

            productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Drug drug = drugs.get(position);
                    Intent intent=new Intent(getApplicationContext(),drugsdetails.class);
                    intent.putExtra("drug",drug);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /////////////////////////////
    /////////Show Drugslist///////
    ////////////////////////////
    class ProductListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return drugs.size();
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
            view = getLayoutInflater().inflate(R.layout.simple_drugs_list, null);
            Drug drug = drugs.get(position);
            TextView drugName = (TextView) view.findViewById(R.id.DrugName);
            TextView drugproductionDate = (TextView) view.findViewById(R.id.DrugproductionDate);
            TextView drugshelfLife = (TextView) view.findViewById(R.id.DrugshelfLife);
            TextView drugspecification = (TextView) view.findViewById(R.id.Drugspecification);
            drugName.setText(drug.getName());
            drugproductionDate.setText(String.valueOf(drug.getProductionDate()));
            drugshelfLife.setText(String.valueOf(drug.getShelfLife()));
            drugspecification.setText(String.valueOf(drug.getMilliliters()));
            return view;
        }
    }

    /////////////////////////////
    /////////Search/////////////
    ////////////////////////////
    public void searchProduct(View v){
        EditText searchText = (EditText) findViewById(R.id.searchText);
        drugs = dbHandler.getDrugsByName(searchText.getText().toString());
        ProductListAdapter productListAdapter=new ProductListAdapter();
        ListView productList =(ListView)findViewById(R.id.DrugsDetails);
        productList.setAdapter(productListAdapter);
        productList.deferNotifyDataSetChanged();
    }

    //ADD Drugsdetail
    public void ADD(View view){
        Intent i= new Intent(this,Add_drugs.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

}
