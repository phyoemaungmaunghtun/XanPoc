package com.webstarterz.happycheetah.pop_up_design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_FOR_BRAND = 1;
    public static final int REQUEST_CODE_FOR_MODEL = 2;
    public static final int REQUEST_CODE_FOR_YEAR = 3;
    public static final String IE_REQUEST_CODE = "requestCode";
    public static final String IE_DATA = "data";
    public static final String IE_TITLE = "Title";
    Button btnReset;
    TextView tvBrand, tvModel, tvYear, tvPrice;

    String brandLable = "Brand";
    String modelLable = "Model";
    String priceLable = "Price";
    String yearLable = "Year";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = this;
        tvBrand = (TextView) findViewById(R.id.brand);
        tvModel = (TextView) findViewById(R.id.model);
        tvYear = (TextView) findViewById(R.id.year);
        tvPrice = (TextView) findViewById(R.id.price);
        btnReset = (Button) findViewById(R.id.button);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReset();
            }
        });


        tvBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                i.putExtra(IE_REQUEST_CODE, REQUEST_CODE_FOR_BRAND);
                i.putExtra(IE_DATA, tvBrand.getText());
                i.putExtra(IE_TITLE, brandLable);
                startActivityForResult(i, REQUEST_CODE_FOR_BRAND);

            }
        });


        tvModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                i.putExtra(IE_REQUEST_CODE, REQUEST_CODE_FOR_MODEL);
                i.putExtra(IE_DATA, tvBrand.getText());
                i.putExtra(IE_TITLE, modelLable);
                startActivityForResult(i, REQUEST_CODE_FOR_MODEL);

            }
        });


        tvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                i.putExtra(IE_REQUEST_CODE, REQUEST_CODE_FOR_YEAR);
                i.putExtra(IE_DATA, tvModel.getText());
                i.putExtra(IE_TITLE, yearLable);
                startActivityForResult(i, REQUEST_CODE_FOR_YEAR);

            }
        });

        tvBrand.setText(brandLable);
        tvModel.setText(modelLable);
        tvYear.setText(yearLable);

        tvPrice.setText(priceLable);

        if (tvBrand.getText() == brandLable) {
            tvModel.setEnabled(false);
            tvYear.setEnabled(false);
            btnReset.setEnabled(false);
        }
    }

    public void btnReset() {

        tvBrand.setText(brandLable);
        tvModel.setText(modelLable);
        tvYear.setText(yearLable);
        tvPrice.setText(priceLable);
        tvModel.setEnabled(false);
        tvYear.setEnabled(false);
        btnReset.setEnabled(false);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOR_BRAND) {
            if (resultCode == RESULT_OK) {

                if (!tvBrand.getText().equals(data.getStringExtra("result"))) {
                    tvBrand.setText(data.getStringExtra("result"));
                    tvModel.setEnabled(true);
                    tvYear.setEnabled(false);
                    btnReset.setEnabled(true);
                    if (tvModel.getText() != modelLable) {
                        tvModel.setText(modelLable);
                        tvYear.setText(yearLable);
                        tvPrice.setText(priceLable);

                    }
                }
            }
        } else if (requestCode == REQUEST_CODE_FOR_MODEL) {
            if (resultCode == RESULT_OK) {

                if (!tvModel.getText().equals(data.getStringExtra("result"))) {
                    tvModel.setText(data.getStringExtra("result"));
                    tvYear.setEnabled(true);
                    if (tvYear.getText() != yearLable) {
                        tvYear.setText(yearLable);
                        tvPrice.setText(priceLable);

                    }
                }
            }
        } else if (requestCode == REQUEST_CODE_FOR_YEAR) {
            if (resultCode == RESULT_OK) {

                if (!tvYear.getText().equals(data.getStringExtra("result"))) {
                    tvYear.setText(data.getStringExtra("result"));
                    tvPrice.setText("$" + data.getStringExtra("Price"));

                }
            }
        }
    }
}
