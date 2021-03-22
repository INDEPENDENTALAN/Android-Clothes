package com.android.clothes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class N_EditActivity extends AppCompatActivity {

    private String name;
    private String picture;
    private String birthday;
    private String email;
    private static final int premission_request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__edit);
        ImageView n__edit_close = findViewById(R.id.n__edit_close);
        n__edit_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        ImageView n__edit_apply = findViewById(R.id.n__edit_apply);
        n__edit_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(N_EditActivity.this);
        SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM n_profile", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
            picture = cursor.getString(cursor.getColumnIndex("picture"));
            birthday = cursor.getString(cursor.getColumnIndex("birthday"));
            email = cursor.getString(cursor.getColumnIndex("_email"));
            cursor.moveToNext();
        }
        cursor.close();
        ImageView n__edit_picture = findViewById(R.id.n__edit_picture);
        ImageView n__edit_change = findViewById(R.id.n__edit_change);
        n__edit_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(N_EditActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(N_EditActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        ActivityCompat.requestPermissions(N_EditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, premission_request);
                    } else {
                        ActivityCompat.requestPermissions(N_EditActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, premission_request);
                    }
                } else {
                    startActivityForResult(new Intent(N_EditActivity.this, N_PictureActivity.class), 6);
                }
            }
        });
        EditText n__edit_name = findViewById(R.id.n__edit_name);
        n__edit_name.setText(name);
        EditText n__edit_birthday = findViewById(R.id.n__edit_birthday);
        n__edit_birthday.setText(birthday);
        EditText n__edit_email = findViewById(R.id.n__edit_email);
        n__edit_email.setText(email);
        EditText n__edit_phone = findViewById(R.id.n__edit_phone);
        Spinner n__edit_gender = findViewById(R.id.n__edit_gender);
        n__edit_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case premission_request:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(N_EditActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(new Intent(N_EditActivity.this, N_PictureActivity.class), 6);
                    }
                } else {

                }
                return;
        }
    }
}
