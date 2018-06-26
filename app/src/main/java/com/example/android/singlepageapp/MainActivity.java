package com.example.android.singlepageapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView recyclerView;
    private ArrayList<Product> products;
    private ProductRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>();
        title = findViewById(R.id.title);
        recyclerView = findViewById(R.id.recycler);
        adapter = new ProductRecyclerAdapter(products, this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocalService service = retrofit.create(LocalService.class);

        Call<Result> products = service.listProducts();


        products.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {

                    adapter.updateProductList(response.body().getProducts());

                    title.setText(response.body().getProducts().get(0).getName());
                    Toast.makeText(MainActivity.this, response.body()
                            .getProducts().get(0).getName(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}
