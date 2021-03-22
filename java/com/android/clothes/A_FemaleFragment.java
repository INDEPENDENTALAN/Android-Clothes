package com.android.clothes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class A_FemaleFragment extends Fragment {

    public A_FemaleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a__female, container, false);
        ArrayList<String> stringArrayList = new ArrayList<>();
        String male = "s__female_";
        for (int i = 1; i <= 17; i++) {
            stringArrayList.add(male + i);
        }
        RecyclerView a__female_recycler_view = view.findViewById(R.id.a__female_recycler_view);
        RecyclerViewAdapterA_Clothes recyclerViewAdapterA_clothes = new RecyclerViewAdapterA_Clothes(getContext(), R.layout.a__clothes, stringArrayList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        a__female_recycler_view.setHasFixedSize(true);
        a__female_recycler_view.setLayoutManager(layoutManager);
        a__female_recycler_view.setAdapter(recyclerViewAdapterA_clothes);
        return view;
    }
}
