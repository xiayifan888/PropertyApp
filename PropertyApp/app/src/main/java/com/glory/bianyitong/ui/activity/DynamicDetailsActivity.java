package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.NewsDeletePopuWindow;
import com.glory.bianyitong.ui.dialog.ReportPopuWindow;
import com.glory.bianyitong.view.MyGridView;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.ui.adapter.DynamicCommentAdapter;
import com.glory.bianyitong.ui.adapter.DynamicPicsAdapter;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DateUtil;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.widght.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by lucy on 2016/11/14.
 * 动态正文(详情)
 */
public class DynamicDetailsActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.ll_addcomment_dy)//评论
            LinearLayout ll_addcomment_dy;
    @BindView(R.id.lay_like_dy)
    LinearLayout lay_like_dy; //点赞
    @BindView(R.id.iv_like)
    ImageView iv_like; //点赞图标
    @BindView(R.id.listView_dynamic) //评论列表
            ListView listView_dynamic;
    Handler rhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
//                bundle.putInt("reportType",1);//举报类型：1近邻2近邻评论3新闻评论
//                bundle.putInt("reportID",neighborhoodid);//举报ID(近邻id或评论id)
//                bundle.putInt("reportUserID",0);//举报人ID（默认0）
//                bundle.putString("reportUserName",Database.USER_MAP.getUserName());//举报人姓名
//                bundle.putInt("publisherID", publisherID);//发布者ID

                int reportType = msg.getData().getInt("reportType");
                int reportID = msg.getData().getInt("reportID");
                int reportUserID = msg.getData().getInt("reportUserID");
                String reportUserName = msg.getData().getString("reportUserName");
                int publisherID = msg.getData().getInt("publisherID");
                reports(reportType, reportID, reportUserID, reportUserName, publisherID);
            }
        }
    };
    private LinkedTreeMap<String, Object> neighborhood;//近邻详情数据
    //近邻动态
    private View view_dynamic;
    private CircleImageView dynamic_user_head;//头像
    private TextView dynamic_user_name;//名称
    private TextView tv_likeNumber; //点赞数
    private TextView dynamic_tv_content; //文字描述
    private MyGridView gridView; //图片
    private ArrayList<String> pictureList = null; //图片数组
    private DynamicPicsAdapter picsAdapter;
    private TextView dynamic_tv_date;//发布时间
    private RelativeLayout dynamic_right_more;//更多 (举报)
    private TextView dynamic_tv_comment_number;//评论数
    //评论列表
    private ArrayList<LinkedTreeMap<String, Object>> commentlist;//评论数据
    private DynamicCommentAdapter dcAdapter;
    private View view_loading;
    private TextView noGoods;
    private LinearLayout loading_lay;
    private boolean have_GoodsList = true;// 判断是否还有
    private boolean getGoodsListStart = false; //
    private ProgressDialog progressDialog = null;
    private int neighborhoodid; //近邻 id
    private int publisherID;//发布者 id
    private boolean islike = false; //近邻是否可以点赞
    private Handler handler;
    private String userID;
    private ReportPopuWindow reportPopuWindow;

    @Override
    protected int getContentId() {
        return R.layout.lay_dynamic_comment;
    }

    @Override
    protected void init() {
        super.init();
        neighborhoodid = getIntent().getIntExtra("neighborhoodID", 0); //近邻 id
        inintTitle(getString(R.string.dynamic_text), true, "");//动态正文
        left_return_btn.setOnClickListener(this);

        view_dynamic = getLayoutInflater().inflate(R.layout.ac_dynamic_details, null);// 加载中.....页面
        userID = RequestUtil.getuserid();
        initdynamic();
    }

    private void initdynamic() {
        ll_addcomment_dy.setOnClickListener(this);
        lay_like_dy.setOnClickListener(this);

        dynamic_user_head = (CircleImageView) view_dynamic.findViewById(R.id.dynamic_user_head);
        dynamic_user_name = (TextView) view_dynamic.findViewById(R.id.dynamic_user_name);
        dynamic_tv_date = (TextView) view_dynamic.findViewById(R.id.dynamic_tv_date);
        dynamic_right_more = (RelativeLayout) view_dynamic.findViewById(R.id.dynamic_right_more);
        dynamic_tv_content = (TextView) view_dynamic.findViewById(R.id.dynamic_tv_content);
        gridView = (MyGridView) view_dynamic.findViewById(R.id.gv_dynamic_pic);
        dynamic_tv_comment_number = (TextView) view_dynamic.findViewById(R.id.dynamic_tv_comment_number);
        tv_likeNumber = (TextView) view_dynamic.findViewById(R.id.tv_likeNumber);
        dynamic_right_more.setOnClickListener(this);
        //----------
        view_loading = getLayoutInflater().inflate(R.layout.loading_lay, null);// 加载中.....页面
        loading_lay = (LinearLayout) view_loading.findViewById(R.id.loading_lay);
        noGoods = (TextView) view_loading.findViewById(R.id.noGoods);
        listView_dynamic.addHeaderView(view_dynamic);
        listView_dynamic.addFooterView(view_loading);
        listView_dynamic.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 当不滚动时
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // 判断是否滚动到底部
                    if (view.getLastVisiblePosition() == view.getCount() - 1) {
                        //加载更多功能的代码
//                        if (Database.GOODS_LIST != null && have_GoodsList && !getGoodsListStart) {
//                            getGoodsListStart = true;
//                            loading_lay.setVisibility(View.VISIBLE);
//                            index_page++;
//                            getGoodsList(index_page, false);
//                        }
                    }
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {}
        });
        view_loading.setVisibility(View.GONE);
        handler = new Handler() { //点赞
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:  //点赞
                        request_like(neighborhoodid, msg.arg1);
                        break;
                    case 1: //取消点赞
                        request_like_cancel(msg.arg1);
                        break;
                    case 2:
                        reportPopuWindow = new ReportPopuWindow(DynamicDetailsActivity.this, rhandler, msg.getData());//, msg.arg1, del_handler
                        // 显示窗口
                        reportPopuWindow.showAtLocation(DynamicDetailsActivity.this.findViewById(R.id.lay_dynamic_commment),
                                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                        break;
                }
            }
        };
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //看大图
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(DynamicDetailsActivity.this, ImagePagerActivity.class);
                intent.putExtra("pictureList", pictureList);
                startActivity(intent);
            }
        });
        commentlist = new ArrayList<>();
        progressDialog = ProgressDialog.show(DynamicDetailsActivity.this, "", "", true);
        progressDialog.setCanceledOnTouchOutside(true);
        request(false);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_addcomment_dy: //评论
                if (Database.USER_MAP != null) {
                    Intent intent = new Intent(DynamicDetailsActivity.this, AddCommentActivity.class);
                    intent.putExtra("neighborhoodID", neighborhoodid);
                    intent.putExtra("CommentToID", 0);
                    intent.putExtra("commentToUserID", 0);
                    intent.putExtra("commentToUserName", "");
                    startActivity(intent);
                } else {//登录w
                    Intent intent_login = new Intent();
                    intent_login.setClass(DynamicDetailsActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                }

                break;
            case R.id.left_return_btn:
                DynamicDetailsActivity.this.finish();
                break;
            case R.id.lay_like_dy:
                if (Database.USER_MAP != null) {
                    if (islike) {// 点赞
                        request_like(neighborhoodid, 0);
                    } else {//取消点赞
                        request_like_cancel(Double.valueOf(neighborhood.get("likeStatu").toString()).intValue());
                    }
                } else {//登录
                    Intent intent_login = new Intent();
                    intent_login.setClass(DynamicDetailsActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                }
                break;
            case R.id.dynamic_right_more: //更多
                ServiceDialog.ButtonClickZoomInAnimation(dynamic_right_more, 0.95f);
                if (Database.USER_MAP != null) {
                    Message msg = new Message();
                    msg.what = 2;
                    Bundle bundle = new Bundle();
                    bundle.putInt("reportType", 1);//举报类型：1近邻2近邻评论3新闻评论
                    bundle.putInt("reportID", neighborhoodid);//举报ID(近邻id或评论id)
                    bundle.putInt("reportUserID", 0);//举报人ID（默认0）
                    bundle.putString("reportUserName", Database.USER_MAP.getUserName());//举报人姓名
                    bundle.putInt("publisherID", publisherID);//发布者ID
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                } else {//登录
                    Intent intent_login = new Intent();
                    intent_login.setClass(DynamicDetailsActivity.this, LoginActivity.class);
                    startActivity(intent_login);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("resultString", "------------onStart");
        if (Database.isAddComment || Database.islogin) {  //评论后刷新数据
            Log.i("resultString", "------------进来了");
            Database.isAddComment = false;
            Database.islogin = false;
            request(false);
        }
    }

    private void showdata(boolean isrefresh) {//展示数据
        if (neighborhood != null && neighborhood.get("userPhoto") != null) {//头像
            ServiceDialog.setPicture(neighborhood.get("userPhoto").toString(), dynamic_user_head, null);
        }
        if (neighborhood != null && neighborhood.get("userName") != null) {//名称
            dynamic_user_name.setText(neighborhood.get("userName").toString());
        }
        if (neighborhood != null && neighborhood.get("userID") != null) {//名称
            publisherID = Double.valueOf(neighborhood.get("userID").toString()).intValue();
        } else {
            publisherID = 0;
        }
        if (neighborhood != null && neighborhood.get("neighborhoodContent") != null) {//文字内容
            dynamic_tv_content.setText(neighborhood.get("neighborhoodContent").toString());
        }
        if (neighborhood != null && neighborhood.get("datetime") != null) {//显示时间
            String date = neighborhood.get("datetime").toString().substring(0, 10);
            dynamic_tv_date.setText(date);
        }
        if (neighborhood != null && neighborhood.get("listNeighborhoodPic") != null) {//图片组
            ArrayList<LinkedTreeMap<String, Object>> piclist = (ArrayList<LinkedTreeMap<String, Object>>) neighborhood.get("listNeighborhoodPic");
            if (piclist.size() > 0) {
                picsAdapter = new DynamicPicsAdapter(DynamicDetailsActivity.this, piclist);
                gridView.setAdapter(picsAdapter);

                pictureList = new ArrayList<>();
                for (int i = 0; i < piclist.size(); i++) {
                    String str_pic = piclist.get(i).get("picturePath").toString();
                    pictureList.add(str_pic);
                }
            }
        }
        //判断是否可以点赞
        if (neighborhood != null && neighborhood.get("likeStatu") != null
                && Double.valueOf(neighborhood.get("likeStatu").toString()).intValue() != -1) {//点赞
            iv_like.setImageResource(R.drawable.log_already_like);
            islike = false;
        } else {
            iv_like.setImageResource(R.drawable.icon_praise);
            islike = true;
        }

        if (neighborhood != null && neighborhood.get("commentCount") != null) {//评论数
            dynamic_tv_comment_number.setText(getString(R.string.comment)+" " + Double.valueOf(neighborhood.get("commentCount").toString()).intValue());
        }
        if (neighborhood != null && neighborhood.get("likeCount") != null) {//点赞数
            tv_likeNumber.setText(getString(R.string.praise)+" " + Double.valueOf(neighborhood.get("likeCount").toString()).intValue());
        }

        if (neighborhood != null && neighborhood.get("listNeighborhoodComment") != null) {//评论列表
            ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) neighborhood.get("listNeighborhoodComment");
            if (commentlist != null) {
                commentlist.clear();
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    commentlist.add(list.get(i));
                }
            }
            if (commentlist != null && commentlist.size() != 0) {
                if (dcAdapter == null || isrefresh) {
                    have_GoodsList = true;
                    dcAdapter = new DynamicCommentAdapter(DynamicDetailsActivity.this, commentlist, handler);
                    listView_dynamic.setAdapter(dcAdapter);
                } else {
                    have_GoodsList = true;
//                    listView_dynamic.requestLayout();
                    dcAdapter.notifyDataSetChanged();
                }
            } else {//没有数据
                noGoods.setVisibility(View.VISIBLE);
                listView_dynamic.setAdapter(null); //隐藏listview  可以显示headview
                have_GoodsList = false;
                getGoodsListStart = false;
            }
        }
    }

    private void request(final boolean isrefresh) { //获取近邻详情
        String json = "{\"neighborhood\":{\"neighborhoodID\":" + neighborhoodid + "}," +
                "\"controllerName\":\"Neighborhood\",\"actionName\":\"StructureQuery\",\"userID\":\"" + userID + "\"}";

        Log.i("resultString", "json------------" + json);
        String url = HttpURL.HTTP_LOGIN_AREA + "/Neighborhood/StructureQueryDetail";
//        OkGo.post(HttpURL.HTTP_LOGIN_AREA + "/Neighborhood/StructureQueryDetail") //近邻
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("listNeighborhood") != null) {
//                            ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listNeighborhood");
//                            if (list != null && list.size() > 0) {
//                                neighborhood = list.get(0); //近邻详情数据
//
//                                showdata(isrefresh);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        getGoodsListStart = false;
//                        loading_lay.setVisibility(View.GONE);
//                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.failed_to_connect_to_server));//无法连接到服务器
//                    }
//
//                    @Override
//                    public void parseError(Call call, Exception e) {
//                        super.parseError(call, e);
//                        Log.i("resultString", "网络解析错误------");
//                    }
//
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                    }
//
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                        if (progressDialog != null) {
//                            progressDialog.dismiss();
//                            progressDialog = null;
//                        }
//                    }
//                });
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("listNeighborhood") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listNeighborhood");
                    if (list != null && list.size() > 0) {
                        neighborhood = list.get(0); //近邻详情数据

                        showdata(isrefresh);
                    }
                }
            }

            @Override
            public void onError() {
                getGoodsListStart = false;
                loading_lay.setVisibility(View.GONE);
            }
            @Override
            public void parseError() {}
            @Override
            public void onBefore() { }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(url,json);
    }

    private void request_like(int neighborhoodID, int commentToID) { //点赞
        String loginName = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getLoginName() != null) {
            loginName = Database.USER_MAP.getLoginName();
        }
        String user_pic = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getCustomerPhoto() != null) {
            user_pic = Database.USER_MAP.getCustomerPhoto();
        }
        String nowdate = DateUtil.formatDefaultDate(DateUtil.getCurrentDate());//获取当前时间
        String json = "{\"neighborhoodLike\": {\"neighborhoodLikeID\": 1,\"neighborhoodID\": " + neighborhoodID + ",\"userID\": 1,\"userName\": \"" + loginName + "\"," +
                "\"userPicture\": \"" + user_pic + "\",\"likeDateTime\": \"" + nowdate + "\",\"commentToID\":" + commentToID + "},\"userid\": \"" + userID + "\"," +
                "\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\"," +
                "\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\",\"controllerName\": \"NeighborhoodLike\"," +
                "\"actionName\": \"ADD\"}";
        String url = HttpURL.HTTP_LOGIN;
//        OkGo.post(HttpURL.HTTP_LOGIN)
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
//                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
//                            ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.has_been_praised));//已点赞
//                            request(false); //刷新
//                            EventBus.getDefault().post("addComment");
//                        }
//                    }
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.failed_to_connect_to_server));//未能连接到服务器
//                    }
//                    @Override
//                    public void parseError(Call call, Exception e) {
//                        super.parseError(call, e);
//                        Log.i("resultString", "网络解析错误------");
//                    }
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                        lay_like_dy.setClickable(false);
////                        progressDialog = ProgressDialog.show(DynamicDetailsActivity.this, "", "加载..", true);
////                        progressDialog.setCanceledOnTouchOutside(true);
//                    }
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                        lay_like_dy.setClickable(true);
////                        if (progressDialog != null) {
////                            progressDialog.dismiss();
////                            progressDialog = null;
////                        }
//                    }
//                });
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
                    ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.has_been_praised));//已点赞
                    request(false); //刷新
                    EventBus.getDefault().post("addComment");
                }
            }
            @Override
            public void onError() { }
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                lay_like_dy.setClickable(false);
            }
            @Override
            public void onAfter() {
                lay_like_dy.setClickable(true);
            }
        }).getEntityData(url,json);

    }

    private void request_like_cancel(int neighborhoodLikeID) { //取消点赞
        String json = "{\"neighborhoodLike\": {\"neighborhoodLikeID\": " + neighborhoodLikeID + "},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\"," +
                "\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"\",\"nowpagenum\": \"\",\"pagerownum\": \"\"," +
                "\"controllerName\": \"NeighborhoodLike\",\"actionName\": \"Delete\"}";
        String url = HttpURL.HTTP_LOGIN;
//        OkGo.post(HttpURL.HTTP_LOGIN)
//                .tag(this)//
////                .headers("", "")//
//                .params("request", json)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.i("resultString", "------------");
//                        Log.i("resultString", s);
//                        Log.i("resultString", "------------");
//                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
//                        });
//                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
//                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
////                            if (neighborhood != null && neighborhood.get("likeCount") != null) {//点赞数
////                                int z = Double.valueOf(neighborhood.get("likeCount").toString()).intValue();
////                                neighborhood.put("likeCount", z - 1);
////                                tv_likeNumber.setText("赞 " + Double.valueOf(neighborhood.get("likeCount").toString()).intValue());
////                                iv_like.setImageResource(R.drawable.icon_praise);
////                            }
//                            ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.cancel_the_point_of_praise));//取消点赞
//                            request(false); //刷新
//                            EventBus.getDefault().post("addComment");
//                        }
//                    }
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.failed_to_connect_to_server));//未能连接到服务器
//                    }
//                    @Override
//                    public void parseError(Call call, Exception e) {
//                        super.parseError(call, e);
//                        Log.i("resultString", "网络解析错误------");
//                    }
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                        lay_like_dy.setClickable(false);
////                        progressDialog = ProgressDialog.show(DynamicDetailsActivity.this, "", "加载..", true);
////                        progressDialog.setCanceledOnTouchOutside(true);
//                    }
//                    @Override
//                    public void onAfter(@Nullable String s, @Nullable Exception e) {
//                        super.onAfter(s, e);
//                        lay_like_dy.setClickable(true);
////                        if (progressDialog != null) {
////                            progressDialog.dismiss();
////                            progressDialog = null;
////                        }
//                    }
//                });
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
                    ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.cancel_the_point_of_praise));//取消点赞
                    request(false); //刷新
                    EventBus.getDefault().post("addComment");
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() { }
            @Override
            public void onBefore() {
                lay_like_dy.setClickable(false);
            }
            @Override
            public void onAfter() {
                lay_like_dy.setClickable(true);
            }
        }).getEntityData(url,json);
    }

    private void reports(int reportType, int reportID, int reportUserID, String reportUserName, int publisherID) {
        //reportType 举报类型：1近邻2近邻评论3新闻评论
        //reportID 举报ID(近邻id或评论id)
        //reportUserID 举报人ID（默认0）
        //reportUserName 举报人姓名
        //publisherID 发布者ID
        //reportTime 举报日期
        //statu 是否处理（默认false）
        String json = "{\"reports\":{\"reportType\":" + reportType + ",\"reportID\":" + reportID + ",\"reportUserID\":" + reportUserID + ",\"reportUserName\":\"" + reportUserName + "\"," +
                "\"publisherID\":" + publisherID + ",\"reportTime\":\"" + RequestUtil.getCurrentTime() + "\",\"statu\":false},\"controllerName\": \"Report\",\"actionName\": \"ADD\"," +
                "\"userID\": \"" + RequestUtil.getuserid() + "\",\"datetime\": \"" + RequestUtil.getCurrentTime() + "\"}";
        String url = HttpURL.HTTP_LOGIN;
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {});
                if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                        Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {

                    ToastUtils.showToast(DynamicDetailsActivity.this, getString(R.string.has_been_reported));//已举报
                }
            }
            @Override
            public void onError() {}
            @Override
            public void parseError() { }
            @Override
            public void onBefore() { }
            @Override
            public void onAfter() { }
        }).getEntityData(url, json);
    }

}
