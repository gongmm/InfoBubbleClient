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
import com.example.gnaiz.infobubble.vo.Comment;
import com.example.gnaiz.infobubble.vo.News;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    //数据源
    private List<Comment> items;
    //private String type;
    private Context context;

    //构造方法
    public CommentAdapter(Context context) {
        this.items = new ArrayList<>();

        this.context = context;
    }

    public void addData (String username, String imgURL, String time, String content){
        Comment itemModel = new Comment(username, imgURL, time, content);
        items.add(itemModel);
        //this.type = type;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*列表布局*/
        CommentAdapter.MyViewHolder viewHolder = null;
        viewHolder = new CommentAdapter.MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_comment, parent, false));

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.logo)    //加载成功之前占位图
                .error(R.drawable.logo)    //加载错误之后的错误图
                .override(40, 40);

        Glide.with(context).load(items.get(position).getImgURL()).apply(options).into(holder.portrait);
        holder.username.setText(items.get(position).getUsername());
        holder.time.setText(items.get(position).getTime());
        holder.content.setText(items.get(position).getContent());

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
        //username
        TextView username;
        //时间
        TextView time;
        //logo
        ImageView portrait;
        //tag
        TextView content;


        public MyViewHolder(View view) {
            super(view);

            username = view.findViewById(R.id.comment_username);
            time = view.findViewById(R.id.comment_time);
            portrait = view.findViewById(R.id.comment_portrait);
            content = view.findViewById(R.id.comment_content);

        }


    }
}
