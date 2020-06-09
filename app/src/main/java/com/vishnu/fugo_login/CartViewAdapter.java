package com.vishnu.fugo_login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.ViewHolder> {

    ArrayList<String> data, price;
    ArrayList<Integer> img;
    ArrayList<Integer> Total = new ArrayList<>();
    ArrayList<String> Quantity = new ArrayList<>();

    String finaltotal = "";
    String test = "this is a joke";
    Context context;

    public CartViewAdapter(Context ct, ArrayList<String> Itemnames, ArrayList<String> Prices, ArrayList<Integer> images){
        context = ct;
        data = Itemnames;
        price = Prices;
        img = images;

    }

    @NonNull
    @Override
    public CartViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewAdapter.ViewHolder holder, final int position) {
        holder.Title.setText(data.get(position));
        holder.Price.setText(price.get(position));
        holder.Image.setImageResource(img.get(position));



        holder.Increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = holder.Quantity.getText().toString();
                String pricedisplay = price.get(position);

                Integer quantityVal = Integer.parseInt(value);
                Integer qty = quantityVal + 1;

                Integer priceVal = Integer.parseInt(pricedisplay);
                Integer priceRes = priceVal * qty;

                String Qtyres = qty.toString();
                String Priceres = priceRes.toString();

                holder.Quantity.setText(Qtyres);
                holder.Price.setText(Priceres);

                Total.set(position,priceRes);
                Quantity.set(position, Qtyres);
            }
        });

        holder.Decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //Quantity module
                    String value = holder.Quantity.getText().toString();

                    Integer qty = Integer.parseInt(value) - 1;
                    String res = qty.toString();
                    holder.Quantity.setText(res);

                    //Price module
                    String pricetextvalue = holder.Price.getText().toString();

                    Integer priceintvalue = Integer.parseInt(pricetextvalue);
                    Integer priceoriginalvalue = Integer.parseInt(price.get(position));
                    Integer decrement = priceintvalue - priceoriginalvalue;
                    holder.Price.setText(decrement.toString());

                    Total.set(position,decrement);
                    Quantity.set(position, res);
            }
        });

        if(!Total.contains(position)){
            Integer val = Integer.parseInt(price.get(position));
            Total.add(val);
        }

        if(!Quantity.contains(position)){
            Quantity.add("1");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Price, Quantity;
        ImageView Image;
        Button Increment,Decrement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.title);
            Price = itemView.findViewById(R.id.price);
            Image = itemView.findViewById(R.id.Image);

            Quantity = itemView.findViewById(R.id.quantity);
            Increment = itemView.findViewById(R.id.increase);
            Decrement = itemView.findViewById(R.id.decrease);
        }
    }
}
