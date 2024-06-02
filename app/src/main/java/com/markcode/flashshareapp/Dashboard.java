package com.markcode.flashshareapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Dashboard extends Fragment {

    ViewPager2 viewPager2DashBoard;
    TabLayout tabLayoutDashBaord;
    TabLayoutMediator tabLayoutMediator;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        viewPager2DashBoard = view.findViewById(R.id.DashBoardViewPager);
        tabLayoutDashBaord =  view.findViewById(R.id.DashboardTab);

        viewPager2DashBoard.setAdapter(new DashboardViewPagerAdapter(this));

        tabLayoutMediator = new TabLayoutMediator(tabLayoutDashBaord, viewPager2DashBoard, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                if(position == 0)
                {
                    tab.setText("Create Flash Card");
                }
                else
                {
                    tab.setText("My Flash Card");
                }

            }

        });

        tabLayoutMediator.attach();

        viewPager2DashBoard.post(new Runnable() {
            @Override
            public void run() {
                viewPager2DashBoard.setCurrentItem(1,false);
            }
        });


        return view;
    }
}