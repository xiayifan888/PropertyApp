package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;

import java.util.ArrayList;

/**
 * Created by lucy on 2016/11/14.
 * 生鲜标签
 */
public class GridTypeAdapter extends BaseAdapter {
    private Context context;
    private Handler handler;
    private ArrayList<LinkedTreeMap<String, Object>> list;
    private int index;

    private LayoutInflater mInflater = null;

    public GridTypeAdapter(Context context, ArrayList<LinkedTreeMap<String, Object>> list, Handler handler, int index) {
        this.context = context;
        this.list = list;
        this.handler = handler;
        this.index = index;
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
            convertView = mInflater.inflate(R.layout.view_item_grid_type, null);
            holder.item_lay_tag = (LinearLayout) convertView.findViewById(R.id.item_lay_tag);
            holder.tv_type_name = (TextView) convertView.findViewById(R.id.tv_type_name);
            holder.iv_more_tag = (ImageView) convertView.findViewById(R.id.iv_more_tag);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list != null && list.get(position) != null && list.get(position).get("freshTypeName")!=null) {
            String name = list.get(position).get("freshTypeName").toString();
            holder.tv_type_name.setText(name);//  标签名称
            if(name.equals(context.getString(R.string.more))){//更多
                holder.iv_more_tag.setImageResource(R.drawable.icon_pull_down);
                holder.iv_more_tag.setVisibility(View.VISIBLE);
            }else if(name.equals(context.getString(R.string.put_away))){//收起
                holder.iv_more_tag.setImageResource(R.drawable.icon_pull_up);
                holder.iv_more_tag.setVisibility(View.VISIBLE);
            }
        }
        if (index == position) {
            holder.item_lay_tag.setBackgroundResource(R.drawable.submit_corner_gv);
//            if(Double.valueOf(list.get(position).get("freshTypeID").toString()).intValue() != -1){
//                holder.item_lay_tag.setClickable(false); //当前选中的禁止点击
//            }
        }

        final LinearLayout layout = holder.item_lay_tag;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.arg1 = position;
                if (position == 0) {
                    msg.what = 0;
                } else if (Double.valueOf(list.get(position).get("freshTypeID").toString()).intValue() == -1) {
                    msg.what = 2;
                } else {
                    msg.what = 1;
                }
                handler.sendMessage(msg);
            }
        });

//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {
        public TextView tv_type_name; //标签名
        public ImageView iv_more_tag;
        private LinearLayout item_lay_tag;
    }
}
