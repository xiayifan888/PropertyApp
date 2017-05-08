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

import com.glory.bianyitong.util.DataUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.GoodsDetailsActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.ArrayList;

/**
 * Created by lucy on 2016/11/14.
 * 品质首选列表
 */
public class QualityFirstAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<LinkedTreeMap<String, Object>> list;

    private LayoutInflater mInflater = null;

    public QualityFirstAdapter(Context context, ArrayList<LinkedTreeMap<String, Object>> list) {
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
            convertView = mInflater.inflate(R.layout.item_gv_quality_first, null);

            holder.rl_quality_first1 = (RelativeLayout) convertView.findViewById(R.id.rl_quality_first1);
            holder.pic_quality_first1 = (ImageView) convertView.findViewById(R.id.pic_quality_first1);
            holder.tv_quality_first_name1 = (TextView) convertView.findViewById(R.id.tv_quality_first_name1);
            holder.tv_quality_first_weight1 = (TextView) convertView.findViewById(R.id.tv_quality_first_weight1);
            holder.tv_tv_quality_first_price1 = (TextView) convertView.findViewById(R.id.tv_tv_quality_first_price1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list != null && list.size() != 0 && list.get(position) != null
                && list.get(position).get("fresh") != null) {
            LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) list.get(position).get("fresh");
            if (map.get("freshName") != null && !map.get("freshName").equals("")) {
                holder.tv_quality_first_name1.setText(map.get("freshName").toString());//  商品名称
            } else {
                holder.tv_quality_first_name1.setText("");
            }
            if (map.get("weight") != null && !map.get("weight").equals("")) {
                holder.tv_quality_first_weight1.setText(Double.valueOf(map.get("weight").toString()).intValue() + "g");//  商品重量
            } else {
                holder.tv_quality_first_weight1.setText("g");
            }
            if (map.get("freshPrice") != null && !map.get("freshPrice").equals("")) {
                holder.tv_tv_quality_first_price1.setText("¥ " + map.get("freshPrice").toString());//  商品价格
            } else {
                holder.tv_tv_quality_first_price1.setText("¥ ");
            }
            if (map.get("freshPicture") != null && !map.get("freshPicture").equals("")) {//  商品图片
                String good_spic = map.get("freshPicture").toString();
                String[] pics;
                if (good_spic.split(",") != null && good_spic.split(",").length > 0) { //如果只有一张图 就没有,
                    pics = good_spic.split(",");
                    ServiceDialog.setPicture(pics[0], holder.pic_quality_first1, null);//多张图 显示第一张
                } else {
                    ServiceDialog.setPicture(good_spic, holder.pic_quality_first1, null);
                }
            } else {
                holder.pic_quality_first1.setImageResource(R.drawable.wait);
            }
        } else {
            holder.tv_quality_first_name1.setText("");
            holder.tv_quality_first_weight1.setText("g");
            holder.tv_tv_quality_first_price1.setText("¥ ");
            holder.pic_quality_first1.setImageResource(R.drawable.wait);
        }

        final RelativeLayout layout = holder.rl_quality_first1;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list != null && list.size() != 0 && list.get(position) != null
                        && list.get(position).get("fresh") != null) {
                    LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) list.get(position).get("fresh");
                    DataUtils.getgoods(map);
                }
//                Database.goodsdetails = (LinkedTreeMap<String, Object>) list.get(position).get("fresh");
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                context.startActivity(intent);

            }
        });


//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {

        public RelativeLayout rl_quality_first1; //商品布局
        public TextView tv_quality_first_name1; //商品名称
        public TextView tv_quality_first_weight1;  //商品重量
        public TextView tv_tv_quality_first_price1;//商品价格
        public ImageView pic_quality_first1;  //商品图片
    }

}
