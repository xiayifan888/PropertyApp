package com.glory.bianyitong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.glory.bianyitong.R;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.activity.AddCommentActivity;
import com.glory.bianyitong.ui.activity.AddNewsCommentActivity;
import com.glory.bianyitong.ui.activity.LoginActivity;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.widght.CircleImageView;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucy on 2016/11/14.
 * 新闻 评论
 */
public class NewsCommentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<LinkedTreeMap<String, Object>> list;
    private Handler handler;

    private LayoutInflater mInflater = null;
    private int newsid;

    public NewsCommentAdapter(Context context, ArrayList<LinkedTreeMap<String, Object>> list, Handler handler, int newsid) {
        this.context = context;
        this.list = list;
        this.handler = handler;
        this.newsid = newsid;
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
            convertView = mInflater.inflate(R.layout.view_item_comment, null);

            holder.comment_user_head = (CircleImageView) convertView.findViewById(R.id.comment_user_head);
            holder.comment_user_name = (TextView) convertView.findViewById(R.id.comment_user_name);
            holder.comment_user_content = (TextView) convertView.findViewById(R.id.comment_user_content);
            holder.comment_time = (TextView) convertView.findViewById(R.id.comment_time);
            holder.tv_likeNumber_to_comment = (TextView) convertView.findViewById(R.id.tv_likeNumber_to_comment);
            holder.tv_comment_to_comment_num = (TextView) convertView.findViewById(R.id.tv_comment_to_comment_num);
            holder.lay_like_ddll = (LinearLayout) convertView.findViewById(R.id.lay_like_ddll);
            holder.tv_likeImage = (ImageView) convertView.findViewById(R.id.tv_likeImage);
            holder.lay_comment_ddll = (LinearLayout) convertView.findViewById(R.id.lay_comment_ddll);
            holder.scrollView_comment_list = (ScrollView) convertView.findViewById(R.id.scrollView_comment_list);
            holder.lay_comment_list = (LinearLayout) convertView.findViewById(R.id.lay_comment_list);
            holder.view_comment_line = (TextView) convertView.findViewById(R.id.view_comment_line);
            holder.lay_comment_to_comment = (RelativeLayout) convertView.findViewById(R.id.lay_comment_to_comment);
            holder.commment_right_more = (RelativeLayout) convertView.findViewById(R.id.commment_right_more);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list != null && list.size() != 0 && list.get(position) != null) {
            if (position == list.size() - 1) {
                holder.view_comment_line.setVisibility(View.GONE);
            }
            if (list.get(position).get("userPhoto") != null && !list.get(position).get("userPhoto").equals("")) {
//                ServiceDialog.setPicture(list.get(position).get("userPhoto").toString(), holder.comment_user_head, null); //头像
                String pic = list.get(position).get("userPhoto").toString();
                Glide.with(context).load(pic).error(R.drawable.wait).placeholder(R.drawable.wait).into(holder.comment_user_head);
            } else {
                holder.comment_user_head.setImageResource(R.drawable.wait);
            }
            if (list.get(position).get("userName") != null && !list.get(position).get("userName").equals("")) {
                holder.comment_user_name.setText(list.get(position).get("userName").toString());//  评论人名称
            } else {
                holder.comment_user_name.setText("");
            }
            if (list.get(position).get("commentContent") != null && !list.get(position).get("commentContent").equals("")) {
                holder.comment_user_content.setText(list.get(position).get("commentContent").toString());//  评论内容
            } else {
                holder.comment_user_content.setText("");
            }
            if (list.get(position).get("commentDateTime") != null && !list.get(position).get("commentDateTime").equals("")) {//评论时间
                String date = list.get(position).get("commentDateTime").toString().substring(0, 10);
                holder.comment_time.setText(date);//
            } else {
                holder.comment_time.setText("");
            }
            if (list.get(position).get("likeCount") != null && !list.get(position).get("likeCount").equals("")) {
                holder.tv_likeNumber_to_comment.setText(Double.valueOf(list.get(position).get("likeCount").toString()).intValue() + "");//  评论点赞数
            } else {
                holder.tv_likeNumber_to_comment.setText("0");
            }
            if (list.get(position).get("likeStatu") != null && Double.valueOf(list.get(position).get("likeStatu").toString()).intValue() != -1) {
                //点赞
                holder.tv_likeImage.setImageResource(R.drawable.log_already_like);
            } else {
                holder.tv_likeImage.setImageResource(R.drawable.icon_praise);
            }

            if (list.get(position).get("listComment") != null && !list.get(position).get("listComment").equals("")) {
                ArrayList<LinkedTreeMap<String, Object>> listComment = (ArrayList<LinkedTreeMap<String, Object>>) list.get(position).get("listComment");
                if (listComment != null && listComment.size() > 0) {
                    holder.tv_comment_to_comment_num.setText(listComment.size() + ""); //评论评论数
                    holder.lay_comment_to_comment.setVisibility(View.VISIBLE);
                    if (list.get(position).get("userName") != null) { //传名称过去
                        ScrollViewLayout(context, listComment, holder.lay_comment_list
                                , newsid
                                , Double.valueOf(list.get(position).get("newCommentID").toString()).intValue());
                    }

                } else {
                    holder.lay_comment_to_comment.setVisibility(View.GONE);
                    holder.tv_comment_to_comment_num.setText("0"); //评论评论数
                }
            } else {
                holder.lay_comment_to_comment.setVisibility(View.GONE);
                holder.tv_comment_to_comment_num.setText("0"); //评论评论数
            }
        } else {
            holder.comment_user_head.setImageResource(R.drawable.wait);
            holder.comment_user_name.setText("");
            holder.comment_user_content.setText("");
            holder.comment_time.setText("");
            holder.tv_likeNumber_to_comment.setText("0");
            holder.tv_likeImage.setImageResource(R.drawable.icon_praise);
            holder.lay_comment_to_comment.setVisibility(View.GONE);
            holder.tv_comment_to_comment_num.setText("0"); //评论评论数
        }

        final LinearLayout lay_like_ddll = holder.lay_like_ddll;
        holder.lay_like_ddll.setOnClickListener(new View.OnClickListener() {//点赞
            @Override
            public void onClick(View v) {
                ServiceDialog.ButtonClickZoomInAnimation(lay_like_ddll, 0.95f);
                if (Database.USER_MAP != null) {
                    if (list.get(position).get("likeStatu") != null && Double.valueOf(list.get(position).get("likeStatu").toString()).intValue() != -1) {
                        //取消点赞
                        Message msg = new Message();
                        msg.what = 1;
                        msg.arg1 = Double.valueOf(list.get(position).get("likeStatu").toString()).intValue();
                        handler.sendMessage(msg);
                    } else {
                        //点赞
                        Message msg = new Message();
                        msg.what = 0;
                        msg.arg1 = Double.valueOf(list.get(position).get("newCommentID").toString()).intValue();
                        handler.sendMessage(msg);
                    }
                } else {//登录
                    login();
                }
            }
        });
        final LinearLayout lay_comment = holder.lay_comment_ddll;
        lay_comment.setOnClickListener(new View.OnClickListener() { //评论
            @Override
            public void onClick(View v) {
                ServiceDialog.ButtonClickZoomInAnimation(lay_comment, 0.95f);
                if (Database.USER_MAP != null) {
                    if (list != null && newsid != 0 &&
                            list.get(position).get("newCommentID") != null) {
                        Intent intent = new Intent(context, AddNewsCommentActivity.class);
                        intent.putExtra("from", "2");
                        intent.putExtra("newsID", newsid);
                        intent.putExtra("CommentToID", Double.valueOf(list.get(position).get("newCommentID").toString()).intValue());
                        intent.putExtra("commentToUserID", Double.valueOf(list.get(position).get("userID").toString()).intValue());
                        intent.putExtra("commentToUserName", list.get(position).get("userName").toString());
                        context.startActivity(intent);
                    }
                } else {//登录
                    login();
                }
            }
        });
        final RelativeLayout commment_right_more = holder.commment_right_more;
        holder.commment_right_more.setOnClickListener(new View.OnClickListener() {//举报
            @Override
            public void onClick(View v) {
                ServiceDialog.ButtonClickZoomInAnimation(commment_right_more, 0.95f);
                if (Database.USER_MAP != null) {
                    Message msg = new Message();
                    msg.what = 2;
//                msg.arg1 = Double.valueOf(list.get(position).get("neighboCommentID").toString()).intValue();
                    Bundle bundle = new Bundle();
                    bundle.putInt("reportType", 3);//举报类型：1近邻2近邻评论3新闻评论
                    bundle.putInt("reportID", Double.valueOf(list.get(position).get("newCommentID").toString()).intValue());//举报ID(近邻id或评论id)
                    bundle.putInt("reportUserID", 0);//举报人ID（默认0）
                    bundle.putString("reportUserName", Database.USER_MAP.getUserName());//举报人姓名
                    bundle.putInt("publisherID", Double.valueOf(list.get(position).get("userID").toString()).intValue());//发布者ID
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } else {//登录
                    login();
                }

            }
        });

        return convertView;
    }

    private void login() { //登录
        Intent intent_login = new Intent();
        intent_login.setClass(context, LoginActivity.class);
        context.startActivity(intent_login);
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<LinkedTreeMap<String, Object>> list, LinearLayout lay_gallery
            , final int newsID, final int CommentToID) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.item_comment_to_comment, lay_gallery, false);
                final LinearLayout lay_comment1 = (LinearLayout) view.findViewById(R.id.lay_comment1); // xx : xxxx
                final TextView tv_comment_text = (TextView) view.findViewById(R.id.tv_comment_text);
//                final TextView tv_comment_user = (TextView) view.findViewById(R.id.tv_comment_user);
//                final TextView tv_comment_content = (TextView) view.findViewById(R.id.tv_comment_content);
//
//                final LinearLayout lay_comment2 = (LinearLayout) view.findViewById(R.id.lay_comment2); // xx2 回复 xx3 : xxxx
//                final TextView tv_comment_user2 = (TextView) view.findViewById(R.id.tv_comment_user2);
//                final TextView tv_comment_user3 = (TextView) view.findViewById(R.id.tv_comment_user3);
//                final TextView tv_comment_content2 = (TextView) view.findViewById(R.id.tv_comment_content2);
//
//                lay_comment2.setVisibility(View.GONE);
//                if (list != null && list.get(i).get("commentToUserID") != null && list.get(i).get("userID") != null &&
//                        !list.get(i).get("userID").toString().equals(list.get(i).get("commentToUserID").toString())){
//                    lay_comment1.setVisibility(View.GONE);
//                    lay_comment2.setVisibility(View.VISIBLE);
//                }else{
//                    lay_comment1.setVisibility(View.VISIBLE);
//                    lay_comment2.setVisibility(View.GONE);
//                }
//
//                // xx : xxxx
//                if (list != null && list.get(i).get("userName") != null && list.get(i).get("userName").toString().length() != 0 && !list.get(i).get("userName").toString().equals("")) {
//                    tv_comment_user.setText(list.get(i).get("userName").toString() + " : ");
//                }else {
//                    tv_comment_user.setText("");
//                }
//                if (list != null && list.get(i).get("commentContent") != null && list.get(i).get("commentContent").toString().length() != 0 && !list.get(i).get("commentContent").toString().equals("")) {
//                    tv_comment_content.setText(list.get(i).get("commentContent").toString());
//                }else {
//                    tv_comment_content.setText("");
//                }

                // xx2 回复 xx3 : xxxx
                String userName = "";
                String commentContent = "";
                String commentToUserName = "";
                String text = "";
                if (list != null && list.get(i).get("userName") != null && list.get(i).get("userName").toString().length() != 0 && !list.get(i).get("userName").toString().equals("")) {
//                    tv_comment_user2.setText(list.get(i).get("userName").toString());
                    userName = list.get(i).get("userName").toString();
                } else {
//                    tv_comment_user2.setText("");
                }
                if (list != null && list.get(i).get("commentToUserName") != null && list.get(i).get("commentToUserName").toString().length() != 0 && !list.get(i).get("commentToUserName").toString().equals("")) {
//                    tv_comment_user3.setText(list.get(i).get("commentToUserName").toString() + " : ");
                    commentToUserName = list.get(i).get("commentToUserName").toString();
                } else {
//                    tv_comment_user3.setText("");
                }
                if (list != null && list.get(i).get("commentContent") != null && list.get(i).get("commentContent").toString().length() != 0 && !list.get(i).get("commentContent").toString().equals("")) {
//                    tv_comment_content2.setText(list.get(i).get("commentContent").toString());
                    commentContent = list.get(i).get("commentContent").toString();
                } else {
//                    tv_comment_content2.setText("");
                }

                if (list != null && list.get(i).get("commentToUserID") != null && list.get(i).get("userID") != null &&
                        !list.get(i).get("userID").toString().equals(list.get(i).get("commentToUserID").toString())) {
//                    lay_comment1.setVisibility(View.GONE);
//                    lay_comment2.setVisibility(View.VISIBLE);
                    text = userName + " 回复 " + commentToUserName + " : " + commentContent;
                    SpannableStringBuilder builder = new SpannableStringBuilder(text);
                    ForegroundColorSpan color_somber = new ForegroundColorSpan(context.getResources().getColor(R.color.text_color_somber));
                    ForegroundColorSpan color_somber2 = new ForegroundColorSpan(context.getResources().getColor(R.color.text_color_somber));
                    int size = userName.length() + 4 + commentToUserName.length() + 3;
                    builder.setSpan(color_somber, userName.length() + 1, userName.length() + 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    builder.setSpan(color_somber2, size, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    tv_comment_text.setText(builder);
                } else {
//                    lay_comment1.setVisibility(View.VISIBLE);
//                    lay_comment2.setVisibility(View.GONE);
                    text = userName + " : " + commentContent;
                    SpannableStringBuilder builder = new SpannableStringBuilder(text);
                    ForegroundColorSpan color_somber = new ForegroundColorSpan(context.getResources().getColor(R.color.text_color_somber));
                    builder.setSpan(color_somber, userName.length() + 3, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    tv_comment_text.setText(builder);
                }

                final int j = i;
                lay_comment1.setOnClickListener(new View.OnClickListener() { //回复用户评论

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        ServiceDialog.ButtonClickZoomInAnimation(lay_comment1, 0.95f);
                        if (Database.USER_MAP != null) {
                            Intent intent = new Intent(context, AddNewsCommentActivity.class);
                            intent.putExtra("from", "2");
                            intent.putExtra("newsID", newsID);
                            intent.putExtra("CommentToID", CommentToID);
                            intent.putExtra("commentToUserID", Double.valueOf(list.get(j).get("userID").toString()).intValue());
                            intent.putExtra("commentToUserName", list.get(j).get("userName").toString());
                            context.startActivity(intent);
                        } else {//登录
                            login();
                        }
                    }
                });
//                lay_comment2
                tv_comment_text.setOnClickListener(new View.OnClickListener() { //回复用户评论的评论

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        ServiceDialog.ButtonClickZoomInAnimation(tv_comment_text, 0.95f);
                        if (Database.USER_MAP != null) {
                            Intent intent = new Intent(context, AddNewsCommentActivity.class);
                            intent.putExtra("from", "2");
                            intent.putExtra("newsID", newsID);
                            intent.putExtra("CommentToID", CommentToID);
                            intent.putExtra("commentToUserID", Double.valueOf(list.get(j).get("userID").toString()).intValue());
                            intent.putExtra("commentToUserName", list.get(j).get("userName").toString());
                            context.startActivity(intent);
                        } else {//登录
                            login();
                        }
                    }
                });
                lay_gallery.addView(view);
            }
        }
    }

    public static class ViewHolder {
        CircleImageView comment_user_head; //评论人头像
        TextView comment_user_name; //名称
        TextView comment_user_content; //评论内容
        TextView comment_time; //评论时间
        TextView tv_likeNumber_to_comment; //评论点赞数
        TextView tv_comment_to_comment_num; //评论评论数
        LinearLayout lay_like_ddll; //点赞
        ImageView tv_likeImage;
        LinearLayout lay_comment_ddll;//评论
        ScrollView scrollView_comment_list;
        LinearLayout lay_comment_list; //评论评论列表
        TextView view_comment_line; //线
        RelativeLayout lay_comment_to_comment; //评论的评论布局
        RelativeLayout commment_right_more; //举报
    }


}
