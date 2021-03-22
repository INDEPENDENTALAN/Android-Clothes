package com.android.clothes;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class N_PictureActivity extends AppCompatActivity {

    ArrayList<Bitmap> bitmapArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__picture);
        ImageView n__picture_close = findViewById(R.id.n__picture_close);
        n__picture_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
        bitmapArrayList = new ArrayList<>();
        getPicture();
        RecyclerView a__images_recycler_view = findViewById(R.id.n__picture_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(N_PictureActivity.this, 4);
        a__images_recycler_view.setHasFixedSize(true);
        a__images_recycler_view.setLayoutManager(layoutManager);
        RecyclerViewAdapterN_Picture recycler_view_adapter_a__images = new RecyclerViewAdapterN_Picture(N_PictureActivity.this, bitmapArrayList);
        a__images_recycler_view.setAdapter(recycler_view_adapter_a__images);
        ImageView n__picture_apply = findViewById(R.id.n__picture_apply);
        n__picture_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(9);
                finish();
            }
        });
    }

    private void getPicture() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            do {
                Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(contentResolver, cursor.getLong(id), MediaStore.Images.Thumbnails.MINI_KIND, null);
                bitmapArrayList.add(bitmap);
            } while (cursor.moveToNext());
        }
    }
}
