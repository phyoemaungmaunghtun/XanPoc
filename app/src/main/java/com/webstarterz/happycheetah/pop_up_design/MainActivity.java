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
    public static final String REQUEST_CODE = "requestCode";
    public static final String DATA = "data";
    public static final String TITLE = "Title";
    Button btn;
    TextView brand, model, year, price;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReset();
            }
        });

        brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                i.putExtra(REQUEST_CODE, REQUEST_CODE_FOR_BRAND);
                i.putExtra(DATA, brand.getText());
                i.putExtra(TITLE, "Brand");
                startActivityForResult(i, REQUEST_CODE_FOR_BRAND);

            }
        });

        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                i.putExtra(REQUEST_CODE, REQUEST_CODE_FOR_MODEL);
                i.putExtra(DATA, brand.getText());
                i.putExtra(TITLE, "Model");
                startActivityForResult(i, REQUEST_CODE_FOR_MODEL);

            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                i.putExtra(REQUEST_CODE, REQUEST_CODE_FOR_YEAR);
                i.putExtra(DATA, model.getText());
                i.putExtra(TITLE, "Year");
                startActivityForResult(i, REQUEST_CODE_FOR_YEAR);

            }
        });

        brand.setText("Brand");
        model.setText("Model");
        year.setText("Year");
        price.setText("Price");

        if (brand.getText() == "Brand") {
            model.setEnabled(false);
            year.setEnabled(false);
            btn.setEnabled(false);
        }
    }

    public void btnReset() {

        brand.setText("Brand");
        model.setText("Model");
        year.setText("Year");
        price.setText("Price");
        model.setEnabled(false);
        year.setEnabled(false);
        btn.setEnabled(false);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOR_BRAND) {
            if (resultCode == RESULT_OK) {

                if (!brand.getText().equals(data.getStringExtra("result"))) {
                    brand.setText(data.getStringExtra("result"));
                    model.setEnabled(true);
                    year.setEnabled(false);
                    btn.setEnabled(true);
                    if (model.getText() != "Model") {
                        model.setText("Model");
                        year.setText("Year");
                        price.setText("Price");

                    }
                }
            }
        } else if (requestCode == REQUEST_CODE_FOR_MODEL) {
            if (resultCode == RESULT_OK) {

                if (!model.getText().equals(data.getStringExtra("result"))) {
                    model.setText(data.getStringExtra("result"));
                    year.setEnabled(true);
                    if (year.getText() != "Year") {
                        year.setText("Year");
                        price.setText("Price");

                    }
                }
            }
        } else if (requestCode == REQUEST_CODE_FOR_YEAR) {
            if (resultCode == RESULT_OK) {

                if (!year.getText().equals(data.getStringExtra("result"))) {
                    year.setText(data.getStringExtra("result"));
                    price.setText("$" + data.getStringExtra("price"));

                }
            }
        }
    }
}
