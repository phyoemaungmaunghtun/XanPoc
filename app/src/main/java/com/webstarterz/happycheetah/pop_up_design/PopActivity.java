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

public class PopActivity extends AppCompatActivity{

    private List<Car> cars;
    private ApiInterface apiInterface;
    private CarAdapter personAdapter;
    Car car;
    String type;
    String data;
    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        lstView = (ListView)findViewById(R.id.modellist);

        setSupportActionBar(toolbar);

        toolbar.setTitleTextColor(Color.parseColor("#313333"));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.7),(int)(height*.35));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 10;
        params.y = -10;
        getWindow().setAttributes(params);
        Bundle bundle1 = getIntent().getExtras();
        type = bundle1.getString("type");
        data = bundle1.getString("data");
        getSupportActionBar().setTitle(type);

        fetchCar("users", "",type,data);

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
                fetchCar("users", query,type,data);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchCar("users", newText,type,data);
                return false;
            }
        });
        return true;
    }

    public void fetchCar(String user, String key, final String table, String data){

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Car>> call = apiInterface.getContact(user, key,table,data);
        call.enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {

                cars = response.body();
                personAdapter = new CarAdapter(PopActivity.this, 0, cars);
                lstView = (ListView) findViewById(R.id.modellist);
                lstView.setAdapter(personAdapter);
                lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        Intent returnIntent = new Intent();
                        car = cars.get(arg2);
                        switch(table) {
                            case "Year":
                                returnIntent.putExtra("price",""+ car.getPrice());
                                break;
                        }

                        returnIntent.putExtra("result",""+ car.getModel());
                        PopActivity.this.setResult(RESULT_OK,returnIntent);
                        PopActivity.this.finish();

                    }

                });
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                Toast.makeText(PopActivity.this, "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    }
