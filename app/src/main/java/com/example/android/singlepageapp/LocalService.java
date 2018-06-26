package com.example.android.singlepageapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LocalService {

    @GET("product")
    Call<Result> listProducts();
}
