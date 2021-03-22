package com.android.clothes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.clothes.N_Clothes.N_Clothes;

import java.util.ArrayList;

public class RecyclerViewAdapterN_Showed extends RecyclerView.Adapter<RecyclerViewAdapterN_Showed.N_ShowedViewHolder> {

    Context context;
    int layout_id;
    ArrayList<N_Clothes> n_clothesArrayList;

    public RecyclerViewAdapterN_Showed(Context context, int layout_id, ArrayList<N_Clothes> n_clothesArrayList) {
        this.context = context;
        this.layout_id = layout_id;
        this.n_clothesArrayList = n_clothesArrayList;
    }

    public static class N_ShowedViewHolder extends RecyclerView.ViewHolder {

        TextView n__showed_name;
        ImageView n__showed_picture;
        TextView n__showed_price;
        ImageView n__showed_lovely;
        ImageView n__showed_opinion;
        ImageView n__showed_purchases;
        View n__showed_line;
        EditText n__showed_text;
        ImageView n__showed_send;
        View n__showed_line_;

        public N_ShowedViewHolder(View itemView) {
            super(itemView);
            n__showed_name = itemView.findViewById(R.id.n__showed_name);
            n__showed_picture = itemView.findViewById(R.id.n__showed_picture);
            n__showed_price = itemView.findViewById(R.id.n__showed_price);
            n__showed_lovely = itemView.findViewById(R.id.n__showed_lovely);
            n__showed_opinion = itemView.findViewById(R.id.n__showed_opinion);
            n__showed_purchases = itemView.findViewById(R.id.n__showed_purchases);
            n__showed_line = itemView.findViewById(R.id.n__showed_line);
            n__showed_text = itemView.findViewById(R.id.n__showed_text);
            n__showed_send = itemView.findViewById(R.id.n__showed_send);
            n__showed_line_ = itemView.findViewById(R.id.n__showed_line_);
        }
    }

    @Override
    public N_ShowedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        N_ShowedViewHolder n_showedViewHolder = new N_ShowedViewHolder(LayoutInflater.from(context).inflate(layout_id, parent, false));
        return n_showedViewHolder;
    }

    @Override
    public void onBindViewHolder(final N_ShowedViewHolder holder, final int position) {
        final SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(context);
        holder.n__showed_name.setText(n_clothesArrayList.get(position).getName());
        holder.n__showed_picture.setImageResource(context.getResources().getIdentifier(n_clothesArrayList.get(position).getPicture(), "drawable", context.getPackageName()));
        holder.n__showed_price.setText(n_clothesArrayList.get(position).getPrice());
        if (n_clothesArrayList.get(position).getIs_Lovely() == 1) {
            holder.n__showed_lovely.setImageResource(R.drawable.ic_lovely_d);
        }
        if (n_clothesArrayList.get(position).getIs_Opinion() == 1) {
            holder.n__showed_opinion.setImageResource(R.drawable.ic_opinion_d);
        }
        if (n_clothesArrayList.get(position).getIS_Purchases() == 1) {
            holder.n__showed_purchases.setImageResource(R.drawable.ic_done_d);
        }
        holder.n__showed_lovely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true) {
                    SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getWritableDatabase();
                    sqLiteDatabase.execSQL("REPLACE INTO n_clothes(_id,name,picture,price,is_lovely,is_opinion,opinion,is_purchases)VALUES(" + n_clothesArrayList.get(position).getId() + ",'" + n_clothesArrayList.get(position).getName() + "','" + n_clothesArrayList.get(position).getPicture() + "','" + n_clothesArrayList.get(position).getPrice() + "',1," + n_clothesArrayList.get(position).getIs_Opinion() + ",'" + n_clothesArrayList.get(position).getOpinion() + "'," + n_clothesArrayList.get(position).getIS_Purchases() + ")");
                    sqLiteDatabase.close();
                    holder.n__showed_lovely.setImageResource(R.drawable.ic_lovely_d);
                }
            }
        });
        holder.n__showed_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n_clothesArrayList.get(position).getIs_Opinion() != 1) {
                    holder.n__showed_text.setVisibility(View.VISIBLE);
                    holder.n__showed_send.setVisibility(View.VISIBLE);
                    holder.n__showed_line_.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.n__showed_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getWritableDatabase();
                sqLiteDatabase.execSQL("REPLACE INTO n_clothes(_id,name,picture,price,is_lovely,is_opinion,opinion,is_purchases)VALUES(" + n_clothesArrayList.get(position).getId() + ",'" + n_clothesArrayList.get(position).getName() + "','" + n_clothesArrayList.get(position).getPicture() + "','" + n_clothesArrayList.get(position).getPrice() + "'," + n_clothesArrayList.get(position).getIs_Lovely() + ",1,'" + holder.n__showed_text.getText().toString() + "'," + n_clothesArrayList.get(position).getIS_Purchases() + ")");
                sqLiteDatabase.close();
                holder.n__showed_text.setVisibility(View.GONE);
                holder.n__showed_send.setVisibility(View.GONE);
                holder.n__showed_line_.setVisibility(View.GONE);
                holder.n__showed_opinion.setImageResource(R.drawable.ic_opinion_d);
            }
        });
        holder.n__showed_purchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n_clothesArrayList.get(position).getIS_Purchases() != 1) {
                    SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getWritableDatabase();
                    sqLiteDatabase.execSQL("REPLACE INTO n_clothes(_id,name,picture,price,is_lovely,is_opinion,opinion,is_purchases)VALUES(" + n_clothesArrayList.get(position).getId() + ",'" + n_clothesArrayList.get(position).getName() + "','" + n_clothesArrayList.get(position).getPicture() + "','" + n_clothesArrayList.get(position).getPrice() + "'," + n_clothesArrayList.get(position).getIs_Lovely() + "," + n_clothesArrayList.get(position).getIs_Opinion() + ",'" + n_clothesArrayList.get(position).getOpinion() + "',1)");
                    sqLiteDatabase.close();
                    holder.n__showed_purchases.setImageResource(R.drawable.ic_done_d);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return n_clothesArrayList.size();
    }
}
