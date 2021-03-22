package com.android.clothes;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class N_ProfileFragment extends Fragment {

    private static final int premission_request = 1;
    private String name;
    private String picture;

    public N_ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_n__profile, container, false);
        ImageView n__profile_privacy = view.findViewById(R.id.n__profile_privacy);
        n__profile_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), N_PrivacyActivity.class), 6);
            }
        });
        SQLiteOpenHelperN_Clothes sqLiteOpenHelperN_clothes = new SQLiteOpenHelperN_Clothes(getContext());
        SQLiteDatabase sqLiteDatabase = sqLiteOpenHelperN_clothes.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM n_profile", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            name = cursor.getString(cursor.getColumnIndex("name"));
            picture = cursor.getString(cursor.getColumnIndex("picture"));
            cursor.moveToNext();
        }
        cursor.close();
        ImageView n__profile_picture = view.findViewById(R.id.n__profile_picture);
        ImageView n__profile_change = view.findViewById(R.id.n__profile_change);
        n__profile_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, premission_request);
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, premission_request);
                    }
                } else {
                    startActivityForResult(new Intent(getContext(), N_PictureActivity.class), 6);
                }
            }
        });
        TextView n__profile_name = view.findViewById(R.id.n__profile_name);
        n__profile_name.setText(name);
        TextView n__profile_edit = view.findViewById(R.id.n__profile_edit);
        n__profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), N_EditActivity.class), 6);
            }
        });
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case premission_request:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(new Intent(getContext(), N_PictureActivity.class), 6);
                    }
                } else {

                }
                return;
        }
    }
}
