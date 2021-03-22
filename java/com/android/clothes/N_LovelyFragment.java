package com.android.clothes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.clothes.N_Clothes.N_Clothes;

import java.util.ArrayList;

public class N_LovelyFragment extends Fragment {

    public N_LovelyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_n__lovely, container, false);
        ImageView n__lovely_opinion = view.findViewById(R.id.n__lovely_opinion);
        n__lovely_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), N_OpinionActivity.class), 6);
            }
        });
        ArrayList<N_Clothes> n_clothesArrayList = new ArrayList<>();
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(getContext());
        SQLiteDatabase sqLiteDatabase_ = sqLiteOpenHelperN_clothes.getReadableDatabase();
        Cursor cursor = sqLiteDatabase_.rawQuery("SELECT * FROM n_clothes WHERE is_lovely LIKE '1'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            N_Clothes n_clothes = new N_Clothes();
            n_clothes.setName(cursor.getString(cursor.getColumnIndex("name")));
            n_clothes.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
            n_clothesArrayList.add(n_clothes);
            cursor.moveToNext();
        }
        cursor.close();
        RecyclerView n__lovely_recycler_view = view.findViewById(R.id.n__lovely_recycler_view);
        RecyclerViewAdapterN_Lovely recyclerViewAdapterN_lovely = new RecyclerViewAdapterN_Lovely(getContext(), n_clothesArrayList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        n__lovely_recycler_view.setHasFixedSize(true);
        n__lovely_recycler_view.setLayoutManager(layoutManager);
        n__lovely_recycler_view.setAdapter(recyclerViewAdapterN_lovely);
        return view;
    }
}
