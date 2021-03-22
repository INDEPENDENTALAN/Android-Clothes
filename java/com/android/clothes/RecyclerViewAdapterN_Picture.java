package com.android.clothes;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterN_Picture extends RecyclerView.Adapter<RecyclerViewAdapterN_Picture.N_PictureViewHolder> {

    private Context context;
    private ArrayList<Bitmap> bitmapArrayList;

    public RecyclerViewAdapterN_Picture(Context context, ArrayList<Bitmap> bitmapArrayList) {
        this.context = context;
        this.bitmapArrayList = bitmapArrayList;
    }

    public static class N_PictureViewHolder extends RecyclerView.ViewHolder {

        ImageView n__picture_picture;

        public N_PictureViewHolder(View itemView) {
            super(itemView);
            n__picture_picture = itemView.findViewById(R.id.n__picture_picture);
        }
    }

    @Override
    public N_PictureViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.n__picture, viewGroup, false);
        final N_PictureViewHolder n_pictureViewHolder = new N_PictureViewHolder(view);
        return n_pictureViewHolder;
    }

    @Override
    public void onBindViewHolder(final N_PictureViewHolder viewHolder, final int i) {
        Bitmap bitmap = bitmapArrayList.get(i);
        viewHolder.n__picture_picture.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return bitmapArrayList.size();
    }
}
