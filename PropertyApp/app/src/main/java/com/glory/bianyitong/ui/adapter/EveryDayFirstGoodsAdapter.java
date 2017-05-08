package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.util.DataUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.GoodsDetailsActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/21.
 * 每日抢鲜
 */
public class EveryDayFirstGoodsAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    public static final int APP_PAGE_SIZE = 3;// 每一页装载数据的大小
    private List<LinkedTreeMap<String, Object>> mList;// 定义一个list对象
    //    private List<FreashInfo> mList;//定义一个list对象
    private Context mContext;// 上下文
    private LayoutInflater mInflater = null;
    private int page;

    /**
     * 构造方法
     *
     * @param context 上下文
     * @param list    所有APP的集合
     * @param page    当前页
     */
    public EveryDayFirstGoodsAdapter(Context context, List<LinkedTreeMap<String, Object>> list, int page) {// List<FreashInfo> list
        mContext = context;
        this.page = page;
        mList = new ArrayList<LinkedTreeMap<String, Object>>();
//        mList = new ArrayList<>();
//         根据当前页计算装载的应用，每页只装载3个
        int i = page * APP_PAGE_SIZE;// 当前页的其实位置
        int iEnd = i + APP_PAGE_SIZE;// 所有数据的结束位置
        while ((i < list.size()) && (i < iEnd)) {
            mList.add(list.get(i));
            i++;
        }
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_everydayfirst_goods, null);

            holder.iv_ef_goods_pic = (ImageView) convertView.findViewById(R.id.iv_ef_goods_pic);
            holder.tv_ef_goods_name = (TextView) convertView.findViewById(R.id.tv_ef_goods_name);
            holder.ef_goods_price = (TextView) convertView.findViewById(R.id.ef_goods_price);
            holder.lay_ef_goods = (LinearLayout) convertView.findViewById(R.id.lay_ef_goods);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        holder.iv_type.setImageResource((Integer)mList.get(position).get("log"));

        if (mList != null && mList.size() != 0 && mList.get(position) != null
                && mList.get(position).get("fresh") != null) {
            LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) mList.get(position).get("fresh");

            if (map.get("freshName") != null) {
                holder.tv_ef_goods_name.setText(map.get("freshName").toString());
            } else {
                holder.tv_ef_goods_name.setText("");
            }
            if (map.get("freshPrice") != null) {
                holder.ef_goods_price.setText("¥ " + map.get("freshPrice").toString());
            } else {
                holder.ef_goods_price.setText("");
            }
            if (map.get("freshPicture") != null && !map.get("freshPicture").equals("")) {
                String url_pic = map.get("freshPicture").toString(); //图片
                String[] pics;
                if (url_pic.split(",") != null && url_pic.split(",").length > 0) { //如果只有一张图 就没有,
                    pics = url_pic.split(",");
                    ServiceDialog.setPicture(pics[0], holder.iv_ef_goods_pic, null);//多张图 显示第一张
                } else {
                    ServiceDialog.setPicture(url_pic, holder.iv_ef_goods_pic, null);
                }
            } else {
                holder.iv_ef_goods_pic.setImageResource(R.drawable.wait);
            }
        } else {
            holder.tv_ef_goods_name.setText("");
            holder.ef_goods_price.setText("");
            holder.iv_ef_goods_pic.setImageResource(R.drawable.wait);
        }

        LinearLayout lay = holder.lay_ef_goods;
        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList != null && mList.size() != 0 && mList.get(position) != null
                        && mList.get(position).get("fresh") != null) {
                    LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) mList.get(position).get("fresh");
                    DataUtils.getgoods(map);
                }

                Intent intent = new Intent(mContext, GoodsDetailsActivity.class);//商品详情
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        int index = this.getPage() * APP_PAGE_SIZE + arg2;

        for (int i = 0; i < APP_PAGE_SIZE; i++) {

        }

    }

    public class ViewHolder {

        public ImageView iv_ef_goods_pic; //商品

        public TextView tv_ef_goods_name; //

        public TextView ef_goods_price; //

        public LinearLayout lay_ef_goods; //item 布局
    }

}
