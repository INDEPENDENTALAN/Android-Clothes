package com.android.clothes;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.clothes.N_Clothes.N_Clothes;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;


public class N_PurchasesActivity extends AppCompatActivity {

    private static final int ERORR = 9001;
    private int all = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__purchases);
        ImageView n__purchases_back = findViewById(R.id.n__purchases_back);
        n__purchases_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        ArrayList<N_Clothes> n_clothesArrayList = new ArrayList<>();
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(N_PurchasesActivity.this);
        SQLiteDatabase sqLiteDatabase_ = sqLiteOpenHelperN_clothes.getReadableDatabase();
        Cursor cursor = sqLiteDatabase_.rawQuery("SELECT * FROM n_clothes WHERE is_purchases LIKE '1'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            N_Clothes n_clothes = new N_Clothes();
            n_clothes.setName(cursor.getString(cursor.getColumnIndex("name")));
            n_clothes.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            n_clothes.setPicture(cursor.getString(cursor.getColumnIndex("picture")));
            n_clothesArrayList.add(n_clothes);
            cursor.moveToNext();
        }
        cursor.close();
        for (int i = 0; i < n_clothesArrayList.size(); i++) {
            all += Integer.parseInt(n_clothesArrayList.get(i).getPrice().replace("$", ""));
        }
        TextView n__purchases_all = findViewById(R.id.n__purchases_all);
        n__purchases_all.setText("$" + all + "");
        RecyclerView n__purchases_recycler_view = findViewById(R.id.n__purchases_recycler_view);
        RecyclerViewAdapterN_Purchases recyclerViewAdapterN_purchases = new RecyclerViewAdapterN_Purchases(N_PurchasesActivity.this, n_clothesArrayList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(N_PurchasesActivity.this, 1);
        n__purchases_recycler_view.setHasFixedSize(true);
        n__purchases_recycler_view.setLayoutManager(layoutManager);
        n__purchases_recycler_view.setAdapter(recyclerViewAdapterN_purchases);
        if (isServicesOk()) {
            init();
        }
        ImageView n__purchases_send = findViewById(R.id.n__purchases_send);
        n__purchases_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void init() {
        TextView n__purchases_map = findViewById(R.id.n__purchases_map);
        n__purchases_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(N_PurchasesActivity.this, N_MapActivity.class), 6);
            }
        });
    }

    public boolean isServicesOk() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(N_PurchasesActivity.this);
        if (available == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(N_PurchasesActivity.this, available, ERORR);
            dialog.show();
        } else {
            Toast.makeText(N_PurchasesActivity.this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
