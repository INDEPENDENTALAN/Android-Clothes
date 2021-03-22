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

public class RecyclerViewAdapterN_Purchases extends RecyclerView.Adapter<RecyclerViewAdapterN_Purchases.N_PurchasesViewHolder> {

    Context context;
    ArrayList<N_Clothes> n_clothesArrayList;

    public RecyclerViewAdapterN_Purchases(Context context, ArrayList<N_Clothes> n_clothesArrayList) {
        this.context = context;
        this.n_clothesArrayList = n_clothesArrayList;
    }

    public static class N_PurchasesViewHolder extends RecyclerView.ViewHolder {

        ImageView n__purchases_picture;
        TextView n__purchases_name;
        TextView n__purchases_price;

        public N_PurchasesViewHolder(View itemView) {
            super(itemView);
            n__purchases_picture = itemView.findViewById(R.id.n__purchases_picture);
            n__purchases_name = itemView.findViewById(R.id.n__purchases_name);
            n__purchases_price = itemView.findViewById(R.id.n__purchases_price);
        }
    }

    @Override
    public N_PurchasesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        N_PurchasesViewHolder n_purchasesViewHolder = new N_PurchasesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.n__purchases, parent, false));
        return n_purchasesViewHolder;
    }

    @Override
    public void onBindViewHolder(N_PurchasesViewHolder holder, int position) {
        holder.n__purchases_picture.setImageResource(context.getResources().getIdentifier(n_clothesArrayList.get(position).getPicture(), "drawable", context.getPackageName()));
        holder.n__purchases_name.setText(n_clothesArrayList.get(position).getName());
        holder.n__purchases_price.setText(n_clothesArrayList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return n_clothesArrayList.size();
    }
}
