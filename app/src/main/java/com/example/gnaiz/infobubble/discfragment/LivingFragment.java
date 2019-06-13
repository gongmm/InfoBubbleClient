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
import com.example.gnaiz.infobubble.util.RecycleViewDivider;
import com.example.gnaiz.infobubble.view.EmptyRecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LivingFragment extends Fragment {

    private EmptyRecyclerView newsRecyclerView;
    private View mEmptyView;
    ProgressDialog dialog;
    private NewsAdapter newsAdapter;

    public LivingFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new LivingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_living, container, false);

        newsRecyclerView = view.findViewById(R.id.recyclerView_living_info);
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
        newsAdapter.addData("1","文献","总馆D栋117培训室", "2018-3-10","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2200771021,146642879&fm=26&gp=0.jpg");
        newsAdapter.addData("1","文献","总馆D栋117培训室", "2018-3-10","https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2200771021,146642879&fm=26&gp=0.jpg");
    }

    protected void setRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newsRecyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getActivity());
        newsRecyclerView.setAdapter(newsAdapter);
        newsRecyclerView.setEmptyView(mEmptyView); //设置空布局
        //设置分割线
        newsRecyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.VERTICAL, 1, getResources().getColor(R.color.colorPrimaryDark)));
    }

}
