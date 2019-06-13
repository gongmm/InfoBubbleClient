package com.example.gnaiz.infobubble;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.gnaiz.infobubble.adapter.NewsAdapter;
import com.example.gnaiz.infobubble.adapter.NewsAsideAdapter;
import com.example.gnaiz.infobubble.util.TitleBar;
import com.leochuan.CenterSnapHelper;
import com.leochuan.ScaleLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    private static final int PERMISSON_REQUESTCODE = 0;

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;
    private AMap aMap;
    private RecyclerView rv_news_aside;
    private NewsAsideAdapter newsAsideAdapter;

    MapView mMapView = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象

    public void setMap(Bundle savedInstanceState){
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        myLocationStyle.strokeColor(R.color.colorLocation);
        myLocationStyle.radiusFillColor(R.color.colorLocation);
        //myLocationStyle.strokeWidth(10);//设置定位蓝点精度圈的边框宽度的方法
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.
                fromResource(R.drawable.ic_location_24dp));
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

    }
    public void setUI(){
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar();
        setMap(savedInstanceState);
        setUI();
        initRecyclerView();
        initData();


    }

    private void initRecyclerView(){
        rv_news_aside = findViewById(R.id.rv_news_aside);
        rv_news_aside.setLayoutManager(
                new ScaleLayoutManager
                        .Builder(context, 2)
                        .setMinScale(1.0f)
                        .setOrientation(OrientationHelper.HORIZONTAL)
                        .build());
        new CenterSnapHelper().attachToRecyclerView(rv_news_aside);
        newsAsideAdapter = new NewsAsideAdapter(context);
        rv_news_aside.setAdapter(newsAsideAdapter);
        rv_news_aside.setItemAnimator(new DefaultItemAnimator());

    }

    protected void initData()
    {

        //dialog = ProgressDialog.show(getContext(), "", "正在加载...");
        //getHistoryOrderByCoupon();
        newsAsideAdapter.addData("1","文献","总馆D栋117培训室", "2018-3-10","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2200771021,146642879&fm=26&gp=0.jpg");
        newsAsideAdapter.addData("1","文献","总馆D栋117培训室", "2018-3-10","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2200771021,146642879&fm=26&gp=0.jpg");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
        if (isNeedCheck) {
            checkPermissions(needPermissions);
        }

        if(newsAsideAdapter.getItemCount() == 0){
            newsAsideAdapter.clearAll();
            //getRecommendedActivity();
            //getRecommendedMerchants();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    //----------以下动态获取权限---------


    /**
     * 检查权限
     *
     * @param permissions:权限
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        //获取权限列表
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            //list.toarray将集合转化为数组
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        }
    }


    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions：权限列表
     * @return List
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        //for (循环变量类型 循环变量名称 : 要被遍历的对象)
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }

    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults:授权结果
     * @return boolean
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] paramArrayOfInt) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {      //没有授权
                showMissingPermissionDialog();              //显示提示信息
                isNeedCheck = false;
            }
        }
    }

    /**
     * 显示提示信息
     *
     * @since 2.5.0
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.notifyTitle);
        builder.setMessage(R.string.notifyMsg);

        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setPositiveButton(R.string.setting,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }


    /**
     * 启动应用的设置
     *
     * @since 2.5.0
     */
    private void startAppSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    public void toolBar(){


        final TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
        titleBar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        titleBar.setHeight(160);
        // left
        titleBar.setLeftImageResource(R.drawable.ic_search_24dp);
        titleBar.setLeftTextColor(getResources().getColor(R.color.colorPrimaryDark));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            // 跳转回主页
            @Override
            public void onClick(View v) {

                Intent intentToMain = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intentToMain);
                finish();
            }
        });

        titleBar.setTitle("探索");
        titleBar.setTitleSize(25);
        titleBar.setTitleColor(getResources().getColor(R.color.colorPrimaryDark));

        // right
        //右侧
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_format_list_24dp) {
            @Override
            public void performAction(View view) {
                // 跳转到发现
                Intent intentToMain = new Intent(MainActivity.this, DiscoverActivity.class);
                startActivity(intentToMain);
                finish();
            }
        });
    }
}
