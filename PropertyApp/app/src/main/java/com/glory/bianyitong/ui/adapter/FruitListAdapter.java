package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.bean.FreashInfo;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.GoodsDetailsActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2016/11/14.
 * 生鲜商品列表
 */
public class FruitListAdapter extends BaseAdapter {
    private Context context;
//    private ArrayList<LinkedTreeMap<String, Object>> list;
    private List<FreashInfo.ListFreshBean> list;

    private LayoutInflater mInflater = null;

    public FruitListAdapter(Context context, List<FreashInfo.ListFreshBean> list) {//ArrayList<LinkedTreeMap<String, Object>> list
        this.context = context;
        this.list = list;

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.view_item_commodity, null);

            holder.lay_list_item_goods = (RelativeLayout) convertView.findViewById(R.id.lay_list_item_goods);
            holder.iv_list_item_goods_pic = (ImageView) convertView.findViewById(R.id.iv_list_item_goods_pic);
            holder.tv_list_item_goods_price = (TextView) convertView.findViewById(R.id.tv_list_item_goods_price);
            holder.tv_list_item_goods_name = (TextView) convertView.findViewById(R.id.tv_list_item_goods_name);
            holder.tv_list_item_goods_content = (TextView) convertView.findViewById(R.id.tv_list_item_goods_content);
            holder.view_goods_line = (TextView) convertView.findViewById(R.id.view_goods_line);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list != null && list.size() != 0 && list.get(position) != null) {
            if(position == list.size() - 1){
                holder.view_goods_line.setVisibility(View.GONE);
            }
//            if (list.get(position).get("freshName") != null && !list.get(position).get("freshName").equals("")) {
//                holder.tv_list_item_goods_name.setText(list.get(position).get("freshName").toString());//  商品名称
//            }else {
//                holder.tv_list_item_goods_name.setText("");
//            }
            if (list.get(position).getFreshName() != null && !list.get(position).getFreshName().equals("")) {
                holder.tv_list_item_goods_name.setText(list.get(position).getFreshName());//  商品名称
            }else {
                holder.tv_list_item_goods_name.setText("");
            }
//            if (list.get(position).get("freshPrice") != null && !list.get(position).get("freshPrice").equals("")) {
//                holder.tv_list_item_goods_price.setText("¥ "+list.get(position).get("freshPrice").toString());//  商品价格
//            }else {
//                holder.tv_list_item_goods_price.setText("¥ ");
//            }
            if (list.get(position).getFreshPrice() != 0) {
                holder.tv_list_item_goods_price.setText("¥ "+list.get(position).getFreshPrice());//  商品价格
            }else {
                holder.tv_list_item_goods_price.setText("¥ ");
            }
//            if (list.get(position).get("freshContent") != null && !list.get(position).get("freshContent").equals("")) {
//                holder.tv_list_item_goods_content.setText(list.get(position).get("freshContent").toString());//  商品简介
//            }else {
//                holder.tv_list_item_goods_content.setText("");
//            }
            if (list.get(position).getFreshContent() != null && !list.get(position).getFreshContent().equals("")) {
                holder.tv_list_item_goods_content.setText(list.get(position).getFreshContent());//  商品简介
            }else {
                holder.tv_list_item_goods_content.setText("");
            }
//            if (list.get(position).get("freshPicture") != null && !list.get(position).get("freshPicture").equals("")) {//  商品图片
//                String good_spic = list.get(position).get("freshPicture").toString();
//                String[] pics;
//                if (good_spic.split(",") != null && good_spic.split(",").length > 0) { //如果只有一张图 就没有,
//                    pics = good_spic.split(",");
//                    ServiceDialog.setPicture(pics[0], holder.iv_list_item_goods_pic, null);//多张图 显示第一张
//                }else {
//                    ServiceDialog.setPicture(good_spic, holder.iv_list_item_goods_pic, null);
//                }
//            }else {
//                holder.iv_list_item_goods_pic.setImageResource(R.drawable.wait);
//            }
            if (list.get(position).getFreshPicture() != null && !list.get(position).getFreshPicture().equals("")) {//  商品图片
                String good_spic = list.get(position).getFreshPicture();
                String[] pics;
                if (good_spic.split(",") != null && good_spic.split(",").length > 0) { //如果只有一张图 就没有,
                    pics = good_spic.split(",");
                    ServiceDialog.setPicture(pics[0], holder.iv_list_item_goods_pic, null);//多张图 显示第一张
                }else {
                    ServiceDialog.setPicture(good_spic, holder.iv_list_item_goods_pic, null);
                }
            }else {
                holder.iv_list_item_goods_pic.setImageResource(R.drawable.wait);
            }
        }else {
            holder.tv_list_item_goods_name.setText("");
            holder.tv_list_item_goods_price.setText("¥ ");
            holder.tv_list_item_goods_content.setText("");
            holder.iv_list_item_goods_pic.setImageResource(R.drawable.wait);
        }

        final RelativeLayout layout = holder.lay_list_item_goods;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.goodsdetails = list.get(position);
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                context.startActivity(intent);
            }
        });


//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {

        public RelativeLayout lay_list_item_goods; //商品布局
        public ImageView iv_list_item_goods_pic;  //商品图片
        public TextView tv_list_item_goods_price; //商品价格
        public TextView tv_list_item_goods_name;  //商品名称
        public TextView tv_list_item_goods_content;//商品简介
        public TextView view_goods_line;
    }

}
