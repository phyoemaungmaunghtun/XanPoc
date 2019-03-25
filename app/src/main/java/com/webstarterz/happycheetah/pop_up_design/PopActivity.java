package com.webstarterz.happycheetah.pop_up_design;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.webstarterz.happycheetah.pop_up_design.Interfaces.ApiClient;
import com.webstarterz.happycheetah.pop_up_design.Interfaces.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopActivity extends AppCompatActivity {

    private List<Car> cars;
    private ApiInterface apiInterface;
    private CarAdapter carAdapter;
    Car car;
    String data,title;
    int requsetCode;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        lstView = (ListView) findViewById(R.id.modellist);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.parseColor("#313333"));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .7), (int) (height * .35));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 10;
        params.y = -10;
        getWindow().setAttributes(params);
        Bundle bundle1 = getIntent().getExtras();
        requsetCode = bundle1.getInt(MainActivity.IE_REQUEST_CODE);
        data = bundle1.getString(MainActivity.IE_DATA);
        title = bundle1.getString(MainActivity.IE_TITLE);
        getSupportActionBar().setTitle(title);

        fetch_car("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetch_car(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetch_car(newText);
                return false;
            }
        });
        return true;
    }

    public void fetch_Brand(String filter_key, final String data) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Car>> call = apiInterface.getBrand(filter_key, data);

        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {

                cars = response.body();
                list_car();
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(PopActivity.this, "Error\n" + t.toString() + "\n", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetch_Model(String filter_key, final String data) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Car>> call = apiInterface.getModel(filter_key, data);

        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {

                cars = response.body();
                list_car();
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(PopActivity.this, "Error\n" + t.toString() + "\n", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetch_Year(String filter_key, final String data) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Car>> call = apiInterface.getYear(filter_key, data);

        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {

                cars = response.body();
                list_car();
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(PopActivity.this, "Error\n" + t.toString() + "\n", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void fetch_car(String filter_key) {
        switch (requsetCode) {
            case MainActivity.REQUEST_CODE_FOR_BRAND:
                fetch_Brand(filter_key, data);
                break;
            case MainActivity.REQUEST_CODE_FOR_MODEL:
                fetch_Model(filter_key, data);
                break;
            case MainActivity.REQUEST_CODE_FOR_YEAR:
                fetch_Year(filter_key, data);
                break;
            default:
                return;

        }
    }

    public void list_car() {
        carAdapter = new CarAdapter(PopActivity.this, 0, cars);
        lstView = (ListView) findViewById(R.id.modellist);
        lstView.setAdapter(carAdapter);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent returnIntent = new Intent();
                car = cars.get(arg2);
                switch (requsetCode) {
                    case 3:
                        returnIntent.putExtra("Price", "" + car.getPrice());
                        break;
                }
                returnIntent.putExtra("result", "" + car.getModel());
                PopActivity.this.setResult(RESULT_OK, returnIntent);
                PopActivity.this.finish();
            }

        });
    }
}
