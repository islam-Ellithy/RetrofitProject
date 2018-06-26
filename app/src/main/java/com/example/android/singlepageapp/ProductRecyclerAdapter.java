package com.example.android.singlepageapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> {


    private List<Product> products;
    private Context context;
    private LayoutInflater inflater;

    public ProductRecyclerAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void updateProductList(ArrayList<Product> newProducts) {
        this.products = newProducts;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_product_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productName.setText(products.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (products != null)
            return products.size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName;

        ViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);

        }

    }
}
