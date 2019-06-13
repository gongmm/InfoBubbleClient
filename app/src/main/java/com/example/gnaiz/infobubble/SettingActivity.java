package com.example.gnaiz.infobubble;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gnaiz.infobubble.adapter.MenuListAdapter;
import com.example.gnaiz.infobubble.util.DataCleanManager;
import com.example.gnaiz.infobubble.util.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingActivity extends AppCompatActivity {

    ListView listViewMenu;
    ArrayList<String> menuItem = new ArrayList<String>(Arrays.asList("字体大小","清除缓存"));
    ArrayList<Integer> menuIcon = new ArrayList<Integer>(Arrays.asList(R.drawable.font_size,R.drawable.clear));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolBar();
        setListViewMenu();
    }

    public void setListViewMenu(){
        listViewMenu = (ListView)findViewById(R.id.listView_menu_setting);
        MenuListAdapter menuListAdapter = new MenuListAdapter(SettingActivity.this,menuItem,menuIcon);
        listViewMenu.setAdapter(menuListAdapter);

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){

                    case 0:

                        break;
                    case 1:
                        String size = "0M";
                        try {
                            size = DataCleanManager.getCacheSize(getCacheDir());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String alter = "缓存共"+size+", 确认要清除吗？";
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this);
                        alertDialog.setTitle(alter).setPositiveButton("确认", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                DataCleanManager.deleteCache(getApplicationContext());
                            }
                        }).setNegativeButton("取消", null).show();
                        break;
                }
            }
        });
    }

    public void toolBar(){

        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setHeight(160);
        titleBar.setTitle("通用设置");
        titleBar.setTitleSize(20);
        titleBar.setTitleColor(R.color.colorPrimaryDark);
        titleBar.setBackgroundColor(Color.WHITE);
        //titleBar.setDividerColor(Color.GRAY);
        //左侧
        titleBar.setLeftImageResource(R.drawable.ic_back_24dp);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorPrimaryDark));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        //沉浸式
        //titleBar.setImmersive(isImmersive);
    }
}
