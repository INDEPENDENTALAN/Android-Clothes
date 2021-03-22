package com.android.clothes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.clothes.N_Clothes.N_Clothes;

import java.util.ArrayList;

public class RecyclerViewAdapterN_Lovely extends RecyclerView.Adapter<RecyclerViewAdapterN_Lovely.N_LovelyViewHolder> {

    Context context;
    ArrayList<N_Clothes> n_clothesArrayList;

    public RecyclerViewAdapterN_Lovely(Context context, ArrayList<N_Clothes> n_clothesArrayList) {
        this.context = context;
        this.n_clothesArrayList = n_clothesArrayList;
    }

    public static class N_LovelyViewHolder extends RecyclerView.ViewHolder {

        ImageView n__lovely_picture;
        TextView n__lovely_name;
        ImageView n__lovely_lovely;

        public N_LovelyViewHolder(View itemView) {
            super(itemView);
            n__lovely_picture = itemView.findViewById(R.id.n__lovely_picture);
            n__lovely_name = itemView.findViewById(R.id.n__lovely_name);
            n__lovely_lovely = itemView.findViewById(R.id.n__lovely_lovely);
        }
    }

    @Override
    public N_LovelyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        N_LovelyViewHolder n_lovelyViewHolder = new N_LovelyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.n__lovely, parent, false));
        return n_lovelyViewHolder;
    }

    @Override
    public void onBindViewHolder(N_LovelyViewHolder holder, int position) {
        holder.n__lovely_picture.setImageResource(context.getResources().getIdentifier(n_clothesArrayList.get(position).getPicture(), "drawable", context.getPackageName()));
        holder.n__lovely_name.setText(n_clothesArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return n_clothesArrayList.size();
    }
}
