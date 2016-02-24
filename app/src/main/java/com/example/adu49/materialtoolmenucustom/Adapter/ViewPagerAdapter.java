package com.example.adu49.materialtoolmenucustom.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.adu49.materialtoolmenucustom.Fragments.Tab_1;
import com.example.adu49.materialtoolmenucustom.Fragments.Tab_2;
import com.example.adu49.materialtoolmenucustom.Fragments.Tab_3;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Tab_1();
                break;
            case 1:
                fragment = new Tab_2();
                break;
            case 2:
                fragment = new Tab_3();
            default:
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "page " + position;
    }
}

