package com.example.gnaiz.infobubble.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gnaiz.infobubble.NewsDetailActivity;
import com.example.gnaiz.infobubble.R;
import com.example.gnaiz.infobubble.vo.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    //数据源
    private List<News> items;
    //private String type;
    private Context context;

    //构造方法
    public NewsAdapter(Context context) {
        this.items = new ArrayList<>();

        this.context = context;
    }

    public void addData (String newsID, String title, String location, String time, String imgURL){
        News itemModel = new News(newsID, title, location, time, imgURL);
        items.add(itemModel);
        //this.type = type;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public NewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        NewsAdapter.MyViewHolder viewHolder = null;
        viewHolder = new NewsAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_news, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.logo)	//加载成功之前占位图
                .error(R.drawable.logo)	//加载错误之后的错误图
                .override(60,60);

        Glide.with(context).load(items.get(position).getImgURL()).apply(options).into(holder.logo);
        holder.title.setText(items.get(position).getTitle());
        holder.description.setText(items.get(position).getAuthor());
        holder.date.setText(items.get(position).getTime());
        holder.tag.setText(items.get(position).getNum_like());

        //final String logoURL = items.get(position).getLogoURL();
        //final String name = items.get(position).getMerchantName();



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetails = new Intent(context,NewsDetailActivity.class);
                //Bundle bundle = new Bundle();
                //
                //bundle.putString("logoURL",logoURL);
                //bundle.putString("points",points);
                //bundle.putString("name",name);
                //bundle.putString("exchangeDate",exchangeDate);
                //bundle.putString("validityDate",validityDate);
                //bundle.putString("itemID",itemID);
                //bundle.putString("description",description);
                //bundle.putString("type",type);
                //
                //intentToDetails.putExtras(bundle);
                context.startActivity(intentToDetails);


            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        //兑换描述
        TextView description;
        //时间
        TextView date;
        //logo
        ImageView logo;
        //tag
        TextView tag;
        //title
        TextView title;

        public MyViewHolder(View view) {
            super(view);

            description = view.findViewById(R.id.textView_description);
            date = view.findViewById(R.id.textView_date);
            logo = view.findViewById(R.id.image_order_logo);

            tag = view.findViewById(R.id.textView_tag);
            title = view.findViewById(R.id.textView_title);


        }


    }
}
