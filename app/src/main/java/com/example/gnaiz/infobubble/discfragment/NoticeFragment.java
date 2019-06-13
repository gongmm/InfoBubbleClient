package com.example.gnaiz.infobubble.discfragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gnaiz.infobubble.R;
import com.example.gnaiz.infobubble.adapter.NewsAdapter;
import com.example.gnaiz.infobubble.view.EmptyRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment {

    private EmptyRecyclerView noticeRecyclerView;
    private View mEmptyView;
    ProgressDialog dialog;

    public NoticeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new NoticeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        noticeRecyclerView = view.findViewById(R.id.recyclerView_notice_info);
        mEmptyView = view.findViewById(R.id.empty_iv);

        //设置RecyclerView管理器
        setRecyclerView();

        //获得初始化数据
        initData();

        return view;
    }

    protected void initData()
    {

        //dialog = ProgressDialog.show(getContext(), "", "正在加载...");
        //getHistoryOrderByCoupon();
    }

    protected void setRecyclerView(){

        NewsAdapter newsAdapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        noticeRecyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getActivity());
        noticeRecyclerView.setAdapter(newsAdapter);
        noticeRecyclerView.setEmptyView(mEmptyView); //设置空布局
    }

}
