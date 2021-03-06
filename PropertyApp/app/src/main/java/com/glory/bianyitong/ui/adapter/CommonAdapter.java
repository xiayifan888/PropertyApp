package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.glory.bianyitong.util.ViewHolders;

import java.util.List;

/**
 * Created by lucy on 2016/11/10.
 * 基础适配器
 */
public abstract  class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater inflater;
    protected int layoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId){
        this.mContext =context;
        this.mDatas =datas;
        this.layoutId =layoutId;
        inflater =LayoutInflater.from(context);

    }

    public List<T> getList() {
        return mDatas;
    }

    public void appendToList(List<T> list) {
        if (list == null) {
            return;
        }
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void appendToTopList(List<T> list) {
        if (list == null) {
            return;
        }
        mDatas.addAll(0, list);
        notifyDataSetChanged();
    }

    public void appendT(T object) {
        if (object == null) {
            return;
        }
        mDatas.add(object);
        notifyDataSetChanged();
    }

    public void removeT(T object) {
        if (object == null) {
            return;
        }
        mDatas.remove(object);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDatas.size()>0?mDatas.size():0;
    }

    @Override
    public T getItem(int position) {
        // TODO Auto-generated method stub
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolders holder = ViewHolders.get(mContext, convertView, parent, layoutId, position);

        convert(holder, getItem(position));

        return holder.getmConvertView();
    };

    public abstract void convert(ViewHolders holder,T t);

}

