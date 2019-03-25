package com.webstarterz.happycheetah.pop_up_design.Interfaces;


import com.webstarterz.happycheetah.pop_up_design.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("retroSearch.php")
    Call<List<Car>> getContact(
            @Query("filter_key") String filter_key,
            @Query("table") String table,
            @Query("data") String data
    );


}
