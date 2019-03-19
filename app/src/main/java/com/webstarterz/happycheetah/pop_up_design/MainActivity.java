package com.webstarterz.happycheetah.pop_up_design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView brand,model,year,price;
    public String br = "Brand";
    public String mo = "Model";
    public String ye = "Year";
    public String pr = "Price";

    @Override
    protected void onResume(){
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        brand = (TextView) findViewById(R.id.brand);
        model = (TextView) findViewById(R.id.model);
        year = (TextView) findViewById(R.id.year);
        price = (TextView) findViewById(R.id.price);
        btn = (Button) findViewById(R.id.button);

        if(br == "Brand"){
            model.setEnabled(false);
            year.setEnabled(false);
            btn.setEnabled(false);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReset();
            }
        });

        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),PopActivity.class);
                i.putExtra("type","Brand");
                i.putExtra("data",br);
                startActivityForResult(i, 1);

            }
        });

        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),PopActivity.class);
                i.putExtra("type","Model");
                i.putExtra("data",br);
                startActivityForResult(i, 2);

            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),PopActivity.class);
                i.putExtra("type","Year");
                i.putExtra("data",mo);
                startActivityForResult(i, 3);

            }
        });

        brand.setText(br);
        model.setText(mo);
        year.setText(ye);
        price.setText(pr);

    }

    public void btnReset(){

        br = "Brand";
        mo = "Model";
        ye = "Year";
        pr = "Price";
        brand.setText(br);
        model.setText(mo);
        year.setText(ye);
        price.setText(pr);
        model.setEnabled(false);
        year.setEnabled(false);
        btn.setEnabled(false);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){

                if(br.equals( data.getStringExtra("result"))){

                }else{

                    br = data.getStringExtra("result");
                    brand.setText(br);
                    model.setEnabled(true);
                    year.setEnabled(false);
                    btn.setEnabled(true);
                    if(mo != "model"){
                        mo = "Model";
                        ye = "Year";
                        pr = "Price";
                        model.setText(mo);
                        year.setText(ye);
                        price.setText(pr);
                    }
                }

            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }else if (requestCode == 2){
            if(resultCode == RESULT_OK){

                if(mo.equals(data.getStringExtra("result"))){

                }else{

                    mo = data.getStringExtra("result");
                    model.setText(mo);
                    year.setEnabled(true);
                    if(ye != "Year"){
                        ye = "Year";
                        pr = "Price";
                        year.setText(ye);
                        price.setText(pr);
                    }

                }

            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }else if (requestCode == 3){
            if(resultCode == RESULT_OK){

                if(ye.equals(data.getStringExtra("result"))){

                }else{

                    ye = data.getStringExtra("result");
                    pr = data.getStringExtra("price");
                    year.setText(ye);
                    price.setText("$"+pr);

                }

            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }


}
