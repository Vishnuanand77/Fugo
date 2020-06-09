package com.vishnu.fugo_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BillViewAdapter extends RecyclerView.Adapter<BillViewAdapter.ViewHolder> {

    Context context;
    ArrayList<String> data, Quantity;

    public BillViewAdapter(Context ct,ArrayList<String> Itemnames, ArrayList<String> quantity){
        context = ct;
        data = Itemnames;
        Quantity = quantity;
    }
    @NonNull
    @Override
    public BillViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.bill_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewAdapter.ViewHolder holder, int position) {

        holder.Title.setText(data.get(position));
        holder.QuantityTextView.setText(Quantity.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Title, QuantityTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.Itemname);
            QuantityTextView = itemView.findViewById(R.id.Quantity);

        }
    }
}
