package com.android.clothes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.clothes.N_Clothes.N_Clothes;

import java.util.ArrayList;

public class N_OpinionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__opinion);
        ImageView n__opinion_back = findViewById(R.id.n__opinion_back);
        n__opinion_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        ArrayList<N_Clothes> n_clothesArrayList = new ArrayList<>();
        RecyclerView n__opinion_recycler = findViewById(R.id.n__opinion_recycler_view);
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(N_OpinionActivity.this);
        SQLiteDatabase sqLiteDatabase_ = sqLiteOpenHelperN_clothes.getReadableDatabase();
        Cursor cursor = sqLiteDatabase_.rawQuery("SELECT * FROM n_clothes WHERE is_opinion LIKE '1'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            N_Clothes n_clothes = new N_Clothes();
            n_clothes.setName(cursor.getString(cursor.getColumnIndex("name")));
            n_clothes.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
            n_clothes.setOpinion(cursor.getString(cursor.getColumnIndex("opinion")));
            n_clothesArrayList.add(n_clothes);
            cursor.moveToNext();
        }
        cursor.close();
        RecyclerViewAdapterN_Opinion recyclerViewAdapterN_opinion = new RecyclerViewAdapterN_Opinion(N_OpinionActivity.this, n_clothesArrayList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(N_OpinionActivity.this, 1);
        n__opinion_recycler.setHasFixedSize(true);
        n__opinion_recycler.setLayoutManager(layoutManager);
        n__opinion_recycler.setAdapter(recyclerViewAdapterN_opinion);
    }
}
