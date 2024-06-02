package com.markcode.flashshareapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DashboardViewPagerAdapter  extends FragmentStateAdapter {


    public DashboardViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0)
        {

            return new CreateFlashCard();

        }
        else
        {

            return new MyFlashCard();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
