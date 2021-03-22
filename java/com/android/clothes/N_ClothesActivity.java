package com.android.clothes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class N_ClothesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__clothes);
        final SharedPreferences sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
        final ConstraintLayout n__clothes_constrain_layout = findViewById(R.id.n__clothes_constrain_layout);
        n__clothes_constrain_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n__clothes_constrain_layout.setVisibility(View.GONE);
            }
        });
        ConstraintLayout n__clothes_info = findViewById(R.id.n__clothes_info);
        n__clothes_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        final GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(N_ClothesActivity.this, googleSignInOptions);
        SignInButton n__clothes_google = findViewById(R.id.n__clothes_google);
        n__clothes_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 2);
            }
        });
        FrameLayout n__clothes_frame_layout = findViewById(R.id.n__clothes_frame_layout);
        final N_ShowedFragment n_showedFragment = new N_ShowedFragment();
        final N_SearchFragment n_searchFragment = new N_SearchFragment();
        final N_LovelyFragment n_lovelyFragment = new N_LovelyFragment();
        final N_ProfileFragment n_profileFragment = new N_ProfileFragment();
        final ImageView n__clothes_showed = findViewById(R.id.n__clothes_showed);
        final ImageView n__clothes_search = findViewById(R.id.n__clothes_search);
        ImageView n__clothes_purchases = findViewById(R.id.n__clothes_purchases);
        final ImageView n__clothes_lovely = findViewById(R.id.n__clothes_lovely);
        final ImageView n__clothes_profile = findViewById(R.id.n__clothes_profile);
        setFragment(n_showedFragment);
        n__clothes_showed.setImageDrawable(getDrawable(R.drawable.ic_showed_s));
        n__clothes_showed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(n_showedFragment);
                n__clothes_showed.setImageDrawable(getDrawable(R.drawable.ic_showed_s));
                n__clothes_search.setImageDrawable(getDrawable(R.drawable.ic_search));
                n__clothes_lovely.setImageDrawable(getDrawable(R.drawable.ic_lovely));
                n__clothes_profile.setImageDrawable(getDrawable(R.drawable.ic_profile));
            }
        });
        n__clothes_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(n_searchFragment);
                n__clothes_showed.setImageDrawable(getDrawable(R.drawable.ic_showed));
                n__clothes_search.setImageDrawable(getDrawable(R.drawable.ic_search_s));
                n__clothes_lovely.setImageDrawable(getDrawable(R.drawable.ic_lovely));
                n__clothes_profile.setImageDrawable(getDrawable(R.drawable.ic_profile));
            }
        });
        n__clothes_purchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt("have_it", 0) != 1) {
                    n__clothes_constrain_layout.setVisibility(View.VISIBLE);
                } else {
                    startActivityForResult(new Intent(N_ClothesActivity.this, N_PurchasesActivity.class), 6);
                }
            }
        });
        n__clothes_lovely.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt("have_it", 0) != 1) {
                    n__clothes_constrain_layout.setVisibility(View.VISIBLE);
                } else {
                    setFragment(n_lovelyFragment);
                    n__clothes_showed.setImageDrawable(getDrawable(R.drawable.ic_showed));
                    n__clothes_search.setImageDrawable(getDrawable(R.drawable.ic_search));
                    n__clothes_lovely.setImageDrawable(getDrawable(R.drawable.ic_lovely_s));
                    n__clothes_profile.setImageDrawable(getDrawable(R.drawable.ic_profile));
                }
            }
        });
        n__clothes_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getInt("have_it", 0) != 1) {
                    n__clothes_constrain_layout.setVisibility(View.VISIBLE);
                } else {
                    setFragment(n_profileFragment);
                    n__clothes_showed.setImageDrawable(getDrawable(R.drawable.ic_showed));
                    n__clothes_search.setImageDrawable(getDrawable(R.drawable.ic_search));
                    n__clothes_lovely.setImageDrawable(getDrawable(R.drawable.ic_lovely));
                    n__clothes_profile.setImageDrawable(getDrawable(R.drawable.ic_profile_s));
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.n__clothes_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final SharedPreferences sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
        if (requestCode == 2 && sharedPreferences.getInt("have_it", 0) != 1) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            ConstraintLayout n__clothes_constrain_layout = findViewById(R.id.n__clothes_constrain_layout);
            n__clothes_constrain_layout.setVisibility(View.GONE);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            SharedPreferences sharedPreferences = getSharedPreferences("Account", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("have_it", 1);
            editor.apply();
            startActivityForResult(new Intent(N_ClothesActivity.this, N_NameActivity.class).putExtra("Email", account.getEmail()).putExtra("Name", account.getDisplayName()).putExtra("Picture", account.getPhotoUrl()), 6);
        } catch (ApiException e) {

        }
    }
}
