package com.glory.bianyitong.util;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class ViewHolders {

    private SparseArray<View> mViews;
    private int mPositon;
    private View mConvertView;

    public ViewHolders(Context context, ViewGroup parent, int layoutId,
                       int position) {
        this.mPositon = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);

        mConvertView.setTag(this);

    }

    public static ViewHolders get(Context context, View convertView,
                                  ViewGroup parent, int layoutid, int position) {
        if (convertView == null) {
            return new ViewHolders(context, parent, layoutid, position);
        } else {
            ViewHolders holder = (ViewHolders) convertView.getTag();
            holder.mPositon =position;
            return holder;
        }
    }



    public int getPositon() {
        return mPositon;
    }

    /**
     * 通过iewId获取控件
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view =mViews.get(viewId);
        if (view ==null) {
            view =mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getmConvertView() {
        return mConvertView;
    }

    /**
     * 如果Adapter是TextView ，设置TextView的值
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolders setText(int viewId, String text){
        TextView tv =getView(viewId);
        tv.setText(text);
        return this;

    }
    /**
     * 如果Adapter是ImageView ，设置图片1111111
     * @param viewId
     * @param
     * @return
     */
    public ViewHolders setImageResource(int viewId, int resId){
        ImageView view =getView(viewId);
        view.setImageResource(resId);
        return this;
    }
    /**
     * 如果Adapter是ImageView ，设置图片22222
     * @param viewId
     * @param
     * @return
     */
    public ViewHolders setImageBitmap(int viewId, Bitmap bitmap){
        ImageView view =getView(viewId);
        view.setImageBitmap(bitmap);
        return this;

    }
    /**
     * 如果Adapter是ImageView ，设置图片33333
     * @param viewId
     * @param
     * @return
     */
    public ViewHolders setImageURL(int viewId, String url){
        ImageView view =getView(viewId);
        ImageLoader.getInstance().displayImage(url,view);
        return this;

    }
}