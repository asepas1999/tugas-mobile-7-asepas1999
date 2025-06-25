package com.project6_dzikir;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DzikirPagerAdapter extends FragmentPagerAdapter {

    public DzikirPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return DzikirFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2; // Pagi & Petang
    }
}
