package com.android.clothes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentPagerAdapterA_Clothes extends FragmentPagerAdapter {

    final private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    final private ArrayList<String> stringArrayList = new ArrayList<>();

    public FragmentPagerAdapterA_Clothes(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentArrayList.get(i);
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return stringArrayList.get(position);
    }

    public void AddFragment(Fragment fragment, String string) {
        fragmentArrayList.add(fragment);
        stringArrayList.add(string);
    }

}
