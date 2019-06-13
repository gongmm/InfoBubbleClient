package com.example.gnaiz.infobubble.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gnaiz.infobubble.R;
import com.example.gnaiz.infobubble.vo.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAsideAdapter extends RecyclerView.Adapter<NewsAsideAdapter.MyViewHolder> {
    //数据源
    private List<News> items;
    private Context context;

    //构造方法
    public NewsAsideAdapter(Context context) {
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
    public NewsAsideAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        NewsAsideAdapter.MyViewHolder viewHolder = null;
        viewHolder = new NewsAsideAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_news_aside, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAsideAdapter.MyViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading_card)    //加载成功之前占位图
                .error(R.drawable.loading_card)    //加载错误之后的错误图
                .override(60, 60);

        Glide.with(context).load(items.get(position).getImgURL()).apply(options).into(holder.image);
        holder.title.setText(items.get(position).getTitle());
        holder.location.setText(items.get(position).getLocation());
        holder.date.setText(items.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clearAll(){
        items.clear();
        notifyDataSetChanged();
    }

    /**
     * ViewHolder类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        View view;
        //title
        TextView title;
        //image
        ImageView image;
        // location
        TextView location;
        //时间
        TextView date;

        public MyViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.title_news_aside);
            image = view.findViewById(R.id.image_news_aside);
            date = view.findViewById(R.id.time_news_aside);
            location = view.findViewById(R.id.location_news_aside);
        }
    }
}
