package com.vishnu.fugo_login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    String data1[], data2[], data3[];
    int images[];
    Context context;

    //Creating a constructor
    public RecyclerViewAdapter(Context ct, String Itemsname[], String Description[],String price[], int img[]){
        context = ct;
        data1 = Itemsname;
        data2 = Description;
        data3 = price;
        images = img;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.Title.setText(data1[position]);
        holder.Description.setText(data2[position]);
        holder.Price.setText(data3[position]);
        holder.Image.setImageResource(images[position]);

        holder.Clayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent to go to the description activity.
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("data1", data1[position] );
                intent.putExtra("data2", data2[position]);
                intent.putExtra("Image", images[position] );
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Title, Description, Price;
        ImageView Image;
        ConstraintLayout Clayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.title);
            Description = itemView.findViewById(R.id.Description);
            Price = itemView.findViewById(R.id.price);
            Image = itemView.findViewById(R.id.Image);
            Clayout = itemView.findViewById(R.id.mainLayout2);

        }
    }
}

