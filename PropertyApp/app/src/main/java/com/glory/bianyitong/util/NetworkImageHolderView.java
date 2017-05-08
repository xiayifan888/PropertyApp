package com.glory.bianyitong.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.widght.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2016/4/29.
 */
public class NetworkImageHolderView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        try{
            Glide.with(context).load(data).error(R.drawable.wait).placeholder(R.drawable.wait).into(imageView);
//        Glide.with(context).load(data).into(imageView);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}