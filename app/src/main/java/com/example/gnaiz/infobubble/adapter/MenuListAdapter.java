package com.example.gnaiz.infobubble.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gnaiz.infobubble.R;

import java.util.ArrayList;

public class MenuListAdapter extends BaseAdapter {

    private ArrayList<String> mData;
    private ArrayList<Integer> mIcon;
    private Context mContext;

    public MenuListAdapter(Context mContext, ArrayList<String> mData, ArrayList<Integer> mIcon){
        this.mContext=mContext;
        this.mData=mData;
        this.mIcon=mIcon;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_custom_list, parent,false);
        }

        final ImageView imageViewIcon=(ImageView)convertView.findViewById(R.id.imageView_custom_list_item);
        imageViewIcon.setImageResource(mIcon.get(position));

        final TextView textViewContent=(TextView)convertView.findViewById(R.id.textView_custom_list_item);
        textViewContent.setText(mData.get(position));

        return convertView;

    }
}

