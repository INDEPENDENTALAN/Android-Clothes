package com.android.clothes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class N_NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__name);
        ImageView n__name_back = findViewById(R.id.n__name_back);
        final Intent intent = getIntent();
        intent.getStringExtra("Email");
        intent.getStringExtra("Name");
        intent.getStringExtra("Picture");
        n__name_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        final EditText n__name_name = findViewById(R.id.n__name_name);
        n__name_name.setText(intent.getStringExtra("Name").toLowerCase().replace(" ", "_"));
        final TextView n__name_account = findViewById(R.id.n__name_account);
        n__name_account.setText(intent.getStringExtra("Email"));
        ImageView n__name_next = findViewById(R.id.n__name_next);
        n__name_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(N_NameActivity.this, N_DateActivity.class).putExtra("Email", n__name_account.getText().toString()).putExtra("Name", n__name_name.getText().toString()).putExtra("Picture", intent.getStringExtra("Picture")), 6);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(9);
        finish();
    }
}
