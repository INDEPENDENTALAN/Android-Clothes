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

public class RecyclerViewAdapterN_Opinion extends RecyclerView.Adapter<RecyclerViewAdapterN_Opinion.N_OpinionViewHolder> {

    Context context;
    ArrayList<N_Clothes> n_clothesArrayList;

    public RecyclerViewAdapterN_Opinion(Context context, ArrayList<N_Clothes> n_clothesArrayList) {
        this.context = context;
        this.n_clothesArrayList = n_clothesArrayList;
    }

    public static class N_OpinionViewHolder extends RecyclerView.ViewHolder {

        ImageView n__opinion_picture;
        TextView n__opinion_name;
        ImageView n__opinion_opinion_;
        TextView n__opinion_opinion;
        View n__opinion_opinion__;

        public N_OpinionViewHolder(View itemView) {
            super(itemView);
            n__opinion_picture = itemView.findViewById(R.id.n__opinion_picture);
            n__opinion_name = itemView.findViewById(R.id.n__opinion_name);
            n__opinion_opinion_ = itemView.findViewById(R.id.n__opinion_opinion_);
            n__opinion_opinion = itemView.findViewById(R.id.n__opinion_opinion);
            n__opinion_opinion__ = itemView.findViewById(R.id.n__opinion_opinion__);
        }
    }

    @Override
    public N_OpinionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        N_OpinionViewHolder n_opinionViewHolder = new N_OpinionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.n__opinion, parent, false));
        return n_opinionViewHolder;
    }

    @Override
    public void onBindViewHolder(N_OpinionViewHolder holder, int position) {
        holder.n__opinion_picture.setImageResource(context.getResources().getIdentifier(n_clothesArrayList.get(position).getPicture(), "drawable", context.getPackageName()));
        holder.n__opinion_name.setText(n_clothesArrayList.get(position).getName());
        holder.n__opinion_opinion.setText(n_clothesArrayList.get(position).getOpinion());
    }

    @Override
    public int getItemCount() {
        return n_clothesArrayList.size();
    }
}
