package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.RelativeDateFormat;
import com.google.gson.internal.LinkedTreeMap;
import com.glory.bianyitong.R;
import com.glory.bianyitong.ui.activity.EveryDayDetailsActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;

import java.util.ArrayList;

/**
 * Created by lucy on 2016/11/10.
 */
public class EveryDayRecommendAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<LinkedTreeMap<String, Object>> list;

    private LayoutInflater mInflater = null;

    public EveryDayRecommendAdapter(Context context, ArrayList<LinkedTreeMap<String, Object>> list) {
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
            convertView = mInflater.inflate(R.layout.view_item_everyday, null);
            holder.item_everyday_lay = (LinearLayout) convertView.findViewById(R.id.item_everyday_lay);

            holder.everyday_lay2 = (LinearLayout) convertView.findViewById(R.id.everyday_lay2); //1图布局
            holder.item_every_title2 = (TextView) convertView.findViewById(R.id.item_every_title2);//标题
//            holder.item_every_text2 = (TextView) convertView.findViewById(R.id.item_every_text2);  //文字内容
//            holder.tv_everyday_praise2 = (TextView) convertView.findViewById(R.id.tv_everyday_praise2); //赞
//            holder.tv_everyday_comment2 = (TextView) convertView.findViewById(R.id.tv_everyday_comment2);//评论
            holder.item_every_time2 = (TextView) convertView.findViewById(R.id.item_every_time2); //时间
            holder.tv_newsGroupName = (TextView) convertView.findViewById(R.id.tv_newsGroupName); //新闻组名称
            holder.item_pic_every_one = (ImageView) convertView.findViewById(R.id.item_pic_every_one);//图片

//            holder.everyday_lay1 = (LinearLayout) convertView.findViewById(R.id.everyday_lay1); //3图布局
//            holder.item_every_title = (TextView) convertView.findViewById(R.id.item_every_title); //标题
//            holder.item_pic_every_one1 = (ImageView) convertView.findViewById(R.id.item_pic_every_one1); //图1
//            holder.item_pic_every_one2 = (ImageView) convertView.findViewById(R.id.item_pic_every_one2);//图2
//            holder.item_pic_every_one3 = (ImageView) convertView.findViewById(R.id.item_pic_every_one3);//图3
//            holder.tv_everyday_praise = (TextView) convertView.findViewById(R.id.tv_everyday_praise); //赞
//            holder.tv_everyday_comment = (TextView) convertView.findViewById(R.id.tv_everyday_comment);//评论
//            holder.item_every_time = (TextView) convertView.findViewById(R.id.item_every_time); //时间

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (list != null && list.size() != 0 && list.get(position) != null) {
            if (list.get(position).get("listNewsPic") != null && !list.get(position).get("listNewsPic").equals("")) {// 图片数量
                ArrayList<LinkedTreeMap<String, Object>> pics = (ArrayList<LinkedTreeMap<String, Object>>) list.get(position).get("listNewsPic");
                if (pics != null && pics.size() >= 3) {
                    holder.everyday_lay2.setVisibility(View.GONE);
//                    holder.everyday_lay1.setVisibility(View.VISIBLE);
//                    if (list.get(position).get("newsTittle") != null && !list.get(position).get("newsTittle").equals("")) {
//                        holder.item_every_title.setText(list.get(position).get("newsTittle").toString());//  标题
//                    }
////                    if (list.get(position).get("newsSubTittle") != null && !list.get(position).get("newsSubTittle").equals("")) {
////                        holder.tv_near_text.setText(list.get(position).get("newsSubTittle").toString());//  文字内容
////                    }
//                    if (list.get(position).get("likeCount") != null && !list.get(position).get("likeCount").equals("")) {
//                        holder.tv_everyday_praise.setText(Double.valueOf(list.get(position).get("likeCount").toString()).intValue() + "");//  赞
//                    }
//                    if (list.get(position).get("commentCount") != null && !list.get(position).get("commentCount").equals("")) {
//                        holder.tv_everyday_comment.setText(Double.valueOf(list.get(position).get("commentCount").toString()).intValue() + "");//  评论
//                    }
//                    if (list.get(position).get("newsDateTime") != null && !list.get(position).get("newsDateTime").equals("")) {
//                        String date = list.get(position).get("newsDateTime").toString().substring(5, 10);
//                        holder.item_every_time.setText(date);//  时间
//                    }
//                    if (pics.get(0) != null && pics.get(0).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(0).get("picturePath").toString(), holder.item_pic_every_one1, null);
//                    }
//                    if (pics.get(1) != null && pics.get(1).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(1).get("picturePath").toString(), holder.item_pic_every_one2, null);
//                    }
//                    if (pics.get(2) != null && pics.get(2).get("picturePath") != null) {
//                        ServiceDialog.setPicture(pics.get(2).get("picturePath").toString(), holder.item_pic_every_one3, null);
//                    }
                } else {
                    holder.everyday_lay2.setVisibility(View.VISIBLE);
//                    holder.everyday_lay1.setVisibility(View.GONE);
                    if (list.get(position).get("newsTittle") != null && !list.get(position).get("newsTittle").equals("")) {
                        holder.item_every_title2.setText(list.get(position).get("newsTittle").toString());//  标题
                    }else {
                        holder.item_every_title2.setText("");
                    }
//                    if (list.get(position).get("newsSubTittle") != null && !list.get(position).get("newsSubTittle").equals("")) {//副标题
//                        holder.item_every_text2.setText(list.get(position).get("newsSubTittle").toString());//  文字内容
//                    }
//                    if (list.get(position).get("likeCount") != null && !list.get(position).get("likeCount").equals("")) {
//                        holder.tv_everyday_praise2.setText(Double.valueOf(list.get(position).get("likeCount").toString()).intValue() + "");//  赞
//                    }
//                    if (list.get(position).get("commentCount") != null && !list.get(position).get("commentCount").equals("")) {
//                        holder.tv_everyday_comment2.setText(Double.valueOf(list.get(position).get("commentCount").toString()).intValue() + "");//  评论
//                    }
                    if (list.get(position).get("newsGroupName") != null && !list.get(position).get("newsGroupName").equals("")) {
                        holder.tv_newsGroupName.setText(list.get(position).get("newsGroupName").toString());//  新闻组名称
                    }else {
                        holder.tv_newsGroupName.setText("");
                    }

                    if (list.get(position).get("newsDateTime") != null && !list.get(position).get("newsDateTime").equals("")) {
                        String date = list.get(position).get("newsDateTime").toString().substring(0, 10);
                        String time = list.get(position).get("newsDateTime").toString().substring(11, 19);
                        String datetime = date + " " + time;
                        long dateTimeStamp = DateUtil.parseTimesTampDate(datetime).getTime();
                        holder.item_every_time2.setText(RelativeDateFormat.getDateDiff(dateTimeStamp));//  时间
                    }else {
                        holder.item_every_time2.setText("");
                    }
                    if (pics != null && pics.size() > 0 && pics.get(0) != null && pics.get(0).get("picturePath") != null) {
                        ServiceDialog.setPicture(pics.get(0).get("picturePath").toString(), holder.item_pic_every_one, null);
                    }else {
                        holder.item_pic_every_one.setImageResource(R.drawable.wait);
                    }
                }
            }else {
                holder.item_every_title2.setText("");
                holder.tv_newsGroupName.setText("");
                holder.item_every_time2.setText("");
                holder.item_pic_every_one.setImageResource(R.drawable.wait);
            }

        }

        final LinearLayout layout = holder.item_everyday_lay;
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EveryDayDetailsActivity.class);
                if (list.get(position).get("newsID") != null) {
                    intent.putExtra("newsID", Double.valueOf(list.get(position).get("newsID").toString()).intValue());
                } else {
                    intent.putExtra("newsID", "");
                }
                if (list.get(position).get("newsURL") != null) {
                    intent.putExtra("newsURL", list.get(position).get("newsURL").toString());
                } else {
                    intent.putExtra("newsURL", "");
                }
                if (list.get(position).get("newsTittle") != null) {
                    intent.putExtra("newsTittle", list.get(position).get("newsTittle").toString());
                } else {
                    intent.putExtra("newsTittle", "");
                }
                if (list.get(position).get("newsSubTittle") != null) {
                    intent.putExtra("newsSubTittle", list.get(position).get("newsSubTittle").toString());
                } else {
                    intent.putExtra("newsSubTittle", "");
                }
                context.startActivity(intent);
            }
        });

//        ServiceDialog.setPicture(HttpURL.HTTP_LOGIN2 + url_pic, holder.pic_goods, null);
//		Log.i("resultString","url_pic--"+HttpURL.HTTP_LOGIN2+url_pic);
        return convertView;
    }

    public static class ViewHolder {
        public LinearLayout item_everyday_lay;//布局

        public LinearLayout everyday_lay2; //布局  1张图
        public ImageView item_pic_every_one;  //图片
        public TextView item_every_title2; // 标题
//        public TextView item_every_text2;  // 文字内容
//        public TextView tv_everyday_praise2;// 点赞数
//        public TextView tv_everyday_comment2;// 评论
        public TextView item_every_time2;// 发布时间
        public TextView tv_newsGroupName;//新闻组名称

//        public LinearLayout everyday_lay1; //布局  3张图
//        public TextView item_every_title; // 标题
//        public ImageView item_pic_every_one1;  //图片
//        public ImageView item_pic_every_one2;  //图片
//        public ImageView item_pic_every_one3;  //图片
//        public TextView tv_everyday_praise;// 点赞数
//        public TextView tv_everyday_comment;// 评论
//        public TextView item_every_time;// 发布时间
    }
}
