package com.example.edgu1.angleseahospital;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edgu1.angleseahospital.DB.Drug;
import com.example.edgu1.angleseahospital.DB.SQLiteHelper;

import java.util.List;

public class DrugsInformation extends Activity {

    private SQLiteHelper dbHandler=null;
    private List<Drug> drugs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drugsinfotmation);
        dbHandler = new SQLiteHelper(this);
        drugs = dbHandler.getDrugsByName(null);


//Drug d=new Drug();
//d.setName("A1");
//d.setManufacturer("a2");
//d.setSpecification("5ML");
//d.setProductionDate("21/2/1561");
//d.setShelfLife("21/2/1811");
//        dbHandler.addDrug(d);



        Toast.makeText(this,dbHandler.getDrugsByName(null).size()+"", Toast.LENGTH_LONG).show();


        try{
            ProductListAdapter productListAdapter=new ProductListAdapter();
            ListView productList =(ListView) findViewById(R.id.DrugsDetails);
            productList.setAdapter(productListAdapter);

            productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Drug product = drugs.get(position);
                    Intent intent=new Intent(getApplicationContext(),Drug.class);
                    intent.putExtra("product",product);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    public void searchProduct(View v){
//        EditText searchText = (EditText) findViewById(R.id.searchText);
//        products = dbHandler.queryAllProducts(searchText.getText().toString());
//        ProductListAdapter productListAdapter=new ProductListAdapter();
//        ListView productList =(ListView)findViewById(R.id.productList);
//        productList.setAdapter(productListAdapter);
//        productList.deferNotifyDataSetChanged();
//    }

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
            drugspecification.setText(String.valueOf(drug.getSpecification()));
            return view;
        }
    }

}
