package com.webstarterz.happycheetah.pop_up_design;

import com.google.gson.annotations.SerializedName;

public class Car {
    @SerializedName("model") private String Model;
    @SerializedName("price") private String Price;
    public String getModel() {
        return Model;
    }
    public String getPrice() {
        return Price;
    }
}
