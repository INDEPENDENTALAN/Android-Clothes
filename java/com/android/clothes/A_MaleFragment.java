package com.android.clothes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class A_MaleFragment extends Fragment {

    public A_MaleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a__male, container, false);
        ArrayList<String> stringArrayList = new ArrayList<>();
        String male = "s__male_";
        for (int i = 1; i <= 15; i++) {
            stringArrayList.add(male + i);
        }
        RecyclerView a__male_recycler_view = view.findViewById(R.id.a__male_recycler_view);
        RecyclerViewAdapterA_Clothes recyclerViewAdapterA_clothes = new RecyclerViewAdapterA_Clothes(getContext(), R.layout.a__clothes, stringArrayList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        a__male_recycler_view.setHasFixedSize(true);
        a__male_recycler_view.setLayoutManager(layoutManager);
        a__male_recycler_view.setAdapter(recyclerViewAdapterA_clothes);
        return view;
    }
}
