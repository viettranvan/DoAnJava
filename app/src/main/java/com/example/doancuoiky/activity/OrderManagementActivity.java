package com.example.doancuoiky.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.doancuoiky.R;
import com.example.doancuoiky.adapter.OrderViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class OrderManagementActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_management);

        anhXa();

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void anhXa() {
        mTabLayout = findViewById(R.id.tab_layout_order);
        mViewPager = findViewById(R.id.view_pager_order);
        goBack = findViewById(R.id.iv_back_order_management);

        OrderViewPagerAdapter adapter = new OrderViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);

        // chuyen den man hinh tuong ung
        Intent intent = getIntent();
        String toProfile = intent.getStringExtra("gotoOrderManagement");
        if (toProfile != null && toProfile.contentEquals("all")) {
            TabLayout.Tab tab = mTabLayout.getTabAt(0);
            tab.select();
        }
        if (toProfile != null && toProfile.contentEquals("pending")) {
            TabLayout.Tab tab = mTabLayout.getTabAt(1);
            tab.select();
        }
        if (toProfile != null && toProfile.contentEquals("shipping")) {
            TabLayout.Tab tab = mTabLayout.getTabAt(2);
            tab.select();
        }
        if (toProfile != null && toProfile.contentEquals("success")) {
            TabLayout.Tab tab = mTabLayout.getTabAt(3);
            tab.select();
        }

    }
}