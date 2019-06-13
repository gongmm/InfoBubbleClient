package com.example.gnaiz.infobubble;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.gnaiz.infobubble.adapter.MenuListAdapter;
import com.example.gnaiz.infobubble.util.TitleBar;
import com.example.gnaiz.infobubble.view.MyListView;
import com.example.gnaiz.infobubble.vo.User;

import java.util.ArrayList;
import java.util.Arrays;

public class UserActivity extends AppCompatActivity {

    MyListView listViewMenu;
    //ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("积分兑换记录","查看历史订单","绑定花旗账户","通用","反馈","关于"));
    ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("设置","帮助","反馈"));
    ArrayList<Integer> menuIcon = new ArrayList<Integer>(Arrays.asList(R.drawable.settings,R.drawable.support,R.drawable.terms));
    private Context context = UserActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        toolBar();
        listViewMenu = (MyListView) findViewById(R.id.listView_setting);
        MenuListAdapter menuListAdapter = new MenuListAdapter(UserActivity.this,menuItem,menuIcon);
        listViewMenu.setAdapter(menuListAdapter);
        setListViewHeightBasedOnChildren(listViewMenu);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:
                        Intent intentToBind = new Intent(context, SettingActivity.class);
                        startActivity(intentToBind);
                        break;
                    case 1:
                        //Intent intentToModifyPassword = new Intent(context, ModifyPasswordActivity.class);
                        //startActivity(intentToModifyPassword);

                }
            }
        });

    }

    public void toolBar(){

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        titleBar.setHeight(160);
        // left
        titleBar.setLeftImageResource(R.drawable.ic_back_24dp);
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorPrimaryDark));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            // 跳转回主页
            @Override
            public void onClick(View v) {

                Intent intentToDiscover = new Intent(UserActivity.this, DiscoverActivity.class);
                startActivity(intentToDiscover);
                finish();
            }
        });

        titleBar.setTitle("个人中心");
        titleBar.setTitleSize(25);
        titleBar.setTitleColor(getResources().getColor(R.color.colorPrimaryDark));

        // right
        //右侧
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_edit__24dp) {
            @Override
            public void performAction(View view) {
                // 跳转回主页
                Intent intentToPublish = new Intent(UserActivity.this, PublishActivity.class);
                startActivity(intentToPublish);
            }
        });


    }

    public void setListViewHeightBasedOnChildren(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0); // 计算子项View 的宽高

            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);

    }
}
