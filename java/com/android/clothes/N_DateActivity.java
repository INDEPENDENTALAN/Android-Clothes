package com.android.clothes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class N_DateActivity extends AppCompatActivity {

    private String birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__date);
        final Intent intent = getIntent();
        ImageView n__date_back = findViewById(R.id.n__date_back);
        n__date_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        final TextView n__date_birthday = findViewById(R.id.n__date_birthday);
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(N_DateActivity.this);
        final SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getWritableDatabase();
        final ImageView n__date_next = findViewById(R.id.n__date_next);
        n__date_next.setEnabled(false);
        final ContentValues contentValues = new ContentValues();
        n__date_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentValues.put("_email", intent.getStringExtra("Email"));
                contentValues.put("name", intent.getStringExtra("Name"));
                contentValues.put("picture", intent.getStringExtra("Picture"));
                contentValues.put("birthday", birthday);
                sqLiteDatabase.insert("n_profile", null, contentValues);
                sqLiteDatabase.close();
                setResult(9);
                finish();
            }
        });
        final DatePicker n__date_date = findViewById(R.id.n__date_date);
        Calendar calendar = Calendar.getInstance();
        n__date_date.setMaxDate(calendar.getTimeInMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            n__date_date.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    monthOfYear++;
                    String month = "JAN";
                    switch (monthOfYear) {
                        case 1:
                            month = "JAN";
                            break;
                        case 2:
                            month = "FEB";
                            break;
                        case 3:
                            month = "MAR";
                            break;
                        case 4:
                            month = "APR";
                            break;
                        case 5:
                            month = "MAY";
                            break;
                        case 6:
                            month = "JUN";
                            break;
                        case 7:
                            month = "JUL";
                            break;
                        case 8:
                            month = "AUG";
                            break;
                        case 9:
                            month = "SEP";
                            break;
                        case 10:
                            month = "OCT";
                            break;
                        case 11:
                            month = "NOV";
                            break;
                        case 12:
                            month = "DEC";
                            break;
                    }
                    n__date_birthday.setTextColor(getColor(R.color.colorN));
                    n__date_birthday.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                    n__date_birthday.setText(month + "  " + dayOfMonth + "  " + year);
                    n__date_next.setImageTintList(getColorStateList(R.color.colorAccent));
                    n__date_next.setEnabled(true);
                    birthday = month + "  " + dayOfMonth + "  " + year;
                }
            });
        }
    }
}
