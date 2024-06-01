package com.markcode.flashshareapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class HomeActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    TabLayoutMediator tabLayoutMediator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        viewPager2 =  findViewById(R.id.ViewPager);
        viewPager2.setAdapter(new ViewPagerAdopter(this));
        tabLayout = findViewById(R.id.TabLayout);

        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                if(position == 0)
                {
                    tab.setIcon(R.drawable.icc_dashboard);
                }
                else if(position == 1)
                {
                    tab.setIcon(R.drawable.icc_flashcard);
                }
                else if(position == 2)
                {
                    tab.setIcon(R.drawable.icc_profile);
                }
            }
        });

        tabLayoutMediator.attach();
    }

    private void BadgeDrawable(TabLayout.Tab tab)
    {
        BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
        badgeDrawable.setBackgroundColor(
                ContextCompat.getColor(getApplicationContext(),R.color.electric_blue)
        );
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(0);
        badgeDrawable.setMaxCharacterCount(4);
    }

}