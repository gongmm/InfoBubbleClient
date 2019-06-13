package com.example.gnaiz.infobubble;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.example.gnaiz.infobubble.adapter.CommentAdapter;
import com.example.gnaiz.infobubble.adapter.NewsAdapter;
import com.example.gnaiz.infobubble.util.RecycleViewDivider;
import com.example.gnaiz.infobubble.util.TitleBar;
import com.example.gnaiz.infobubble.view.EmptyRecyclerView;
import com.example.gnaiz.infobubble.vo.Comment;

public class NewsDetailActivity extends AppCompatActivity {
    private EmptyRecyclerView commentRecyclerView;
    private View mEmptyView;
    ProgressDialog dialog;
    private CommentAdapter commentAdapter;
    private Button commentButton;
    private Context context = NewsDetailActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        toolBar();

        initView();

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

    }

    protected void initView(){
        commentRecyclerView = findViewById(R.id.rv_comment);
        mEmptyView = findViewById(R.id.empty_iv);
        commentButton = findViewById(R.id.button_comment);
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToComment = new Intent(context, CommentActivity.class);
                startActivity(intentToComment);
            }
        });

    }

    protected void initData()
    {

        //dialog = ProgressDialog.show(getContext(), "", "正在加载...");
        //getHistoryOrderByCoupon();
        commentAdapter.addData("王小五","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2200771021,146642879&fm=26&gp=0.jpg",
                "2 hours ago","信部怎么又停水啦，又要跋山涉水去教五上厕所了");
    }

    protected void setRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        commentRecyclerView.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(context);
        commentRecyclerView.setAdapter(commentAdapter);
        commentRecyclerView.setEmptyView(mEmptyView); //设置空布局
        //设置分割线
        commentRecyclerView.addItemDecoration(new RecycleViewDivider(
                context, LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.colorDivide)));
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

                Intent intentToDiscover = new Intent(NewsDetailActivity.this, DiscoverActivity.class);
                startActivity(intentToDiscover);
                finish();
            }
        });

        titleBar.setTitle("资讯详情");
        titleBar.setTitleSize(25);
        titleBar.setTitleColor(getResources().getColor(R.color.colorPrimaryDark));


    }
}
