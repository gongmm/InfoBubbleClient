package com.example.gnaiz.infobubble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.gnaiz.infobubble.discfragment.ExaminationFragment;
import com.example.gnaiz.infobubble.discfragment.LivingFragment;
import com.example.gnaiz.infobubble.discfragment.NoticeFragment;
import com.example.gnaiz.infobubble.discfragment.SupplyDemandFragment;
import com.example.gnaiz.infobubble.util.TitleBar;

import java.lang.reflect.Field;

public class DiscoverActivity extends AppCompatActivity {

    private Fragment[] mFragmentArrays = new Fragment[4];
    private String[] mTabTitles = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        toolBar();
        initView();
    }

    private void initView() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.tab_viewpager);

        mTabTitles[0] = "生活";
        mTabTitles[1] = "通知";
        mTabTitles[2] = "供需";
        mTabTitles[3] = "考试";

        //tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //设置tablayout距离上下左右的距离
        //tab_title.setPadding(20,20,20,20);
        mFragmentArrays[0] = LivingFragment.newInstance();
        mFragmentArrays[1] = NoticeFragment.newInstance();
        mFragmentArrays[2] = SupplyDemandFragment.newInstance();
        mFragmentArrays[3] = ExaminationFragment.newInstance();

        PagerAdapter pagerAdapter = new DiscoverActivity.MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将ViewPager和TabLayout绑定
        tabLayout.setupWithViewPager(viewPager);

        // 设置Tab间隙
        int TAB_MARGIN_DIP = 20;
        //setIndicator(this, tabLayout, TAB_MARGIN_DIP, TAB_MARGIN_DIP);
    }


    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }
    }

    public void toolBar(){


        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        titleBar.setHeight(160);
        // left
        titleBar.setLeftImageResource(R.drawable.ic_map_24dp);
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorPrimaryDark));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            // 跳转回主页
            @Override
            public void onClick(View v) {

                Intent intentToMain = new Intent(DiscoverActivity.this, MainActivity.class);
                startActivity(intentToMain);
                finish();
            }
        });

        titleBar.setTitle("发现");
        titleBar.setTitleSize(25);
        titleBar.setTitleColor(getResources().getColor(R.color.colorPrimaryDark));

        // right
        //右侧
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_person_outline_24dp) {
            @Override
            public void performAction(View view) {
                // 跳转回主页
                Intent intentToMain = new Intent(DiscoverActivity.this, UserActivity.class);
                startActivity(intentToMain);
                finish();
            }
        });
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * leftDip);
        int right = (int) (getDisplayMetrics(context).density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }
}
