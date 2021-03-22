package com.android.clothes;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterA_Clothes extends RecyclerView.Adapter<RecyclerViewAdapterA_Clothes.A_ClothesViewHolder> {

    Context context;
    int layout_id;
    ArrayList<String> stringArrayList;

    public RecyclerViewAdapterA_Clothes(Context context, int layout_id, ArrayList<String> stringArrayList) {
        this.context = context;
        this.layout_id = layout_id;
        this.stringArrayList = stringArrayList;
    }

    public static class A_ClothesViewHolder extends RecyclerView.ViewHolder {

        ImageView a__clothes_picture;
        ImageView a__clothes_done;

        public A_ClothesViewHolder(View itemView) {
            super(itemView);
            a__clothes_picture = itemView.findViewById(R.id.a__clothes_picture);
            a__clothes_done = itemView.findViewById(R.id.a__clothes_done);
        }
    }

    @Override
    public A_ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        A_ClothesViewHolder a_clothesViewHolder = new A_ClothesViewHolder(LayoutInflater.from(context).inflate(layout_id, parent, false));
        return a_clothesViewHolder;
    }

    @Override
    public void onBindViewHolder(final A_ClothesViewHolder holder, final int position) {
        holder.a__clothes_picture.setImageResource(context.getResources().getIdentifier(stringArrayList.get(position), "drawable", context.getPackageName()));
        SharedPreferences sharedPreferences = context.getSharedPreferences("Clothes", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        holder.a__clothes_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.a__clothes_done.setVisibility(View.VISIBLE);
                editor.putString("clothes", stringArrayList.get(position));
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }
}
