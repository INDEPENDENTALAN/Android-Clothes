package com.android.clothes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class N_PrivacyActivity extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__privacy);
        ImageView n__privacy_back = findViewById(R.id.n__privacy_back);
        n__privacy_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        TextView n__privacy_manage = findViewById(R.id.n__privacy_manage);
        n__privacy_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_safely = findViewById(R.id.n__privacy_safely);
        n__privacy_safely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_notifications = findViewById(R.id.n__privacy_notifications);
        n__privacy_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_general = findViewById(R.id.n__privacy_general);
        n__privacy_general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_terms = findViewById(R.id.n__privacy_terms);
        n__privacy_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_privacy = findViewById(R.id.n__privacy_privacy);
        n__privacy_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_copyright = findViewById(R.id.n__privacy_copyright);
        n__privacy_copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_report = findViewById(R.id.n__privacy_report);
        n__privacy_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView n__privacy_a_clothes = findViewById(R.id.n__privacy_a_clothes);
        n__privacy_a_clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(N_PrivacyActivity.this, A_ClothesActivity.class), 6);
            }
        });
        TextView n__privacy_logout = findViewById(R.id.n__privacy_logout);
        n__privacy_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignInClient.signOut().addOnCompleteListener(N_PrivacyActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("have_it", 0);
                        editor.apply();
                    }
                });
            }
        });
    }
}
