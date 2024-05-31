package com.markcode.flashshareapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdopter extends FragmentStateAdapter {


    public ViewPagerAdopter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if(position == 0)
        {
            return new Dashboard();
        }
        else if (position == 1)
        {
            return new FlashCard();
        }
        else {

            return new Profile();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
