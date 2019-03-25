package com.webstarterz.happycheetah.pop_up_design.Interfaces;


import com.webstarterz.happycheetah.pop_up_design.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getBrand.php")
    Call<List<Car>> getBrand(
            @Query("filter_key") String filter_key,
            @Query("data") String data
    );

    @GET("getModel.php")
    Call<List<Car>> getModel(
            @Query("filter_key") String filter_key,
            @Query("data") String data
    );

    @GET("getYear.php")
    Call<List<Car>> getYear(
            @Query("filter_key") String filter_key,
            @Query("data") String data
    );


}
