package com.android.clothes;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.clothes.N_Clothes.N_Clothes;

import java.util.ArrayList;

public class N_ShowedFragment extends Fragment {

    public N_ShowedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_n__showed, container, false);
        ArrayList<N_Clothes> n_clothesArrayList = new ArrayList<>();
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(getContext());
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Showed", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.getString("showed", "").equals("done!")) {
            SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getWritableDatabase();
            sqLiteDatabase.execSQL("INSERT INTO n_clothes(name,picture,price)VALUES('Suit','s__male_7','$160')");
            sqLiteDatabase.execSQL("INSERT INTO n_clothes(name,picture,price)VALUES('Shirt','s__male_6','$28')");
            sqLiteDatabase.execSQL("INSERT INTO n_clothes(name,picture,price)VALUES('Troucers','s__male_2','$32')");
            sqLiteDatabase.execSQL("INSERT INTO n_clothes(name,picture,price)VALUES('T-Shirt','s__female_3','$23')");
            sqLiteDatabase.execSQL("INSERT INTO n_clothes(name,picture,price)VALUES('Pullover','s__female_1','$36')");
            sqLiteDatabase.close();
            editor.putString("showed", "done!");
            editor.apply();
        }
        SQLiteDatabase sqLiteDatabase_ = sqLiteOpenHelperN_clothes.getReadableDatabase();
        Cursor cursor = sqLiteDatabase_.rawQuery("SELECT * FROM n_clothes", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            N_Clothes n_clothes = new N_Clothes();
            n_clothes.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            n_clothes.setName(cursor.getString(cursor.getColumnIndex("name")));
            n_clothes.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            n_clothes.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
            n_clothes.setIs_Lovely(cursor.getInt(cursor.getColumnIndex("is_lovely")));
            n_clothes.setIs_Opinion(cursor.getInt(cursor.getColumnIndex("is_opinion")));
            n_clothes.setOpinion(cursor.getString(cursor.getColumnIndex("opinion")));
            n_clothes.setIS_Purchases(cursor.getInt(cursor.getColumnIndex("is_purchases")));
            n_clothesArrayList.add(n_clothes);
            cursor.moveToNext();
        }
        cursor.close();
        RecyclerView n__showed_recycler_view = view.findViewById(R.id.n__showed_recycler_view);
        RecyclerViewAdapterN_Showed recyclerViewAdapterN_showed = new RecyclerViewAdapterN_Showed(getContext(), R.layout.n__showed, n_clothesArrayList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        n__showed_recycler_view.setHasFixedSize(true);
        n__showed_recycler_view.setLayoutManager(layoutManager);
        n__showed_recycler_view.setAdapter(recyclerViewAdapterN_showed);
        return view;
    }
}
