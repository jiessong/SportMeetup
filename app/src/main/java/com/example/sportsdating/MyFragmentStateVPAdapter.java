package com.example.sportsdating;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Adapter
 * ViewPager ,ButtomNavigationView
 * FragmentStatePagerAdapter vs FragmentPagerAdapter
 * FragmentStatePagerAdapter: recycled after slipping away---better:easy to manage lifecycle
 * FragmentPagerAdapter：can not be recycled
 */
public class MyFragmentStateVPAdapter extends FragmentStatePagerAdapter {
    //
    private List<Fragment> mFragmentList;

    public MyFragmentStateVPAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList == null ? null:mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList == null ? 0:mFragmentList.size();
    }
}
