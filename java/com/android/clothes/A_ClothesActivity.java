package com.android.clothes;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class A_ClothesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__clothes);
        ImageView a__clothes_back = findViewById(R.id.a__clothes_back);
        a__clothes_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        final EditText a__clothes_name = findViewById(R.id.a__clothes_name);
        final EditText a__clothes_price = findViewById(R.id.a__clothes_price);
        final TextView a__clothes_erorr = findViewById(R.id.a__clothes_erorr);
        final SharedPreferences sharedPreferences = getSharedPreferences("Clothes", MODE_PRIVATE);
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(A_ClothesActivity.this);
        final SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();
        TextView a__clothes_create = findViewById(R.id.a__clothes_create);
        a__clothes_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a__clothes_name.getText().toString().equals("") || a__clothes_price.getText().toString().equals("") || sharedPreferences.getString("clothes", "").equals("")) {
                    a__clothes_erorr.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            a__clothes_erorr.setVisibility(View.GONE);
                        }
                    }, 2600);
                } else {
                    contentValues.put("name", a__clothes_name.getText().toString());
                    contentValues.put("picture", sharedPreferences.getString("clothes", ""));
                    contentValues.put("price", a__clothes_price.getText().toString());
                    sqLiteDatabase.insert("n_clothes", null, contentValues);
                    sqLiteDatabase.close();
                    setResult(9);
                    finish();
                }
            }
        });
        TabLayout a__clothes_tab_layout = findViewById(R.id.a__clothes_tab_layout);
        ViewPager a__clothes_view_pager = findViewById(R.id.a__clothes_view_pager);
        FragmentPagerAdapterA_Clothes fragment_pager_adapter_a__create = new FragmentPagerAdapterA_Clothes(getSupportFragmentManager());
        fragment_pager_adapter_a__create.AddFragment(new A_MaleFragment(), "Meals");
        fragment_pager_adapter_a__create.AddFragment(new A_FemaleFragment(), "Special Meals");
        a__clothes_view_pager.setAdapter(fragment_pager_adapter_a__create);
        a__clothes_tab_layout.setupWithViewPager(a__clothes_view_pager);
        a__clothes_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
