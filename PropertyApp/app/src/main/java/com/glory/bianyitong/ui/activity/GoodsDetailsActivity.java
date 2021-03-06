package com.glory.bianyitong.ui.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.bean.FreashInfo;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.NetworkImageHolderView;
import com.glory.bianyitong.widght.convenientbanner.ConvenientBanner;
import com.glory.bianyitong.widght.convenientbanner.holder.CBViewHolderCreator;
import com.glory.bianyitong.widght.convenientbanner.listener.OnItemClickListener;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lucy on 2016/11/22.
 * 商品详情
 */
public class GoodsDetailsActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    ConvenientBanner convenientBanner2;
    @BindView(R.id.lay_goods_pic)
    LinearLayout lay_goods_pic;
    @BindView(R.id.tv_goods_name)
    TextView tv_goods_name;//商品名称
    @BindView(R.id.tv_goods_price)
    TextView tv_goods_price;//商品价格
    @BindView(R.id.tv_quick_deliver_goods)
    TextView tv_quick_deliver_goods;// 快速发货
    @BindView(R.id.tv_goods_weight)
    TextView tv_goods_weight; //净含量
    @BindView(R.id.goods_packaging)
    TextView goods_packaging; //包装方式
    @BindView(R.id.tv_goods_brand)
    TextView tv_goods_brand; //品牌
    @BindView(R.id.tv_producing_area)
    TextView tv_producing_area; //产地
    @BindView(R.id.tv_vender_phone)
    TextView tv_vender_phone; //厂家联系方式
    @BindView(R.id.tv_expiration_date)
    TextView tv_expiration_date; //保质期
    @BindView(R.id.tv_nutritiveValue)
    TextView tv_nutritiveValue; //营养价值
    //    @BindView(R.id.lay_goodsImage_list)
//    LinearLayout lay_goodsImage_list;
    @BindView(R.id.webview_ervery)
    WebView webview_ervery;
    @BindView(R.id.my_progress)
    ProgressBar my_progress;
    //    private String[] images = {"http://pic19.nipic.com/20120310/6608733_161926686000_2.jpg",
//            "http://pic.baike.soso.com/p/20140314/20140314163748-240481789.jpg",
//            "http://pic.baike.soso.com/p/20140314/20140314161519-1600894624.jpg"
//    };
    private String[] images;
    private List<String> imageList = new ArrayList<String>();

    @Override
    protected int getContentId() {
        return R.layout.ac_goods_details;
    }

    @Override
    protected void init() {
        super.init();

        inintTitle(getString(R.string.product_details), true, "");//商品详情
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                GoodsDetailsActivity.this.finish();
            }
        });
        View headview = LayoutInflater.from(GoodsDetailsActivity.this).inflate(R.layout.index_headview_goods_details, null);
        convenientBanner2 = (ConvenientBanner) headview.findViewById(R.id.convenientBanner2);
        lay_goods_pic.addView(headview);

        initview();

    }

    private void initview() {
        Log.i("resultString", "Database.goodsdetails-----" + Database.goodsdetails);
//        if (Database.goodsdetails != null && Database.goodsdetails.size() != 0) {
//            if (Database.goodsdetails.get("freshName") != null) { //商品名称
//                tv_goods_name.setText(Database.goodsdetails.get("freshName").toString());
//            }
//            if (Database.goodsdetails.get("freshPrice") != null) { //商品价格
//                tv_goods_price.setText("¥ " + Database.goodsdetails.get("freshPrice").toString());
//            }
//            if (Database.goodsdetails.get("freshTypeName") != null) { // freshTypeName
//                tv_quick_deliver_goods.setText(Database.goodsdetails.get("freshTypeName").toString());
//            }
//            if (Database.goodsdetails.get("weight") != null) { //净含量
//                tv_goods_weight.setText(Double.valueOf(Database.goodsdetails.get("weight").toString()).intValue() + "g");
//            }
//            if (Database.goodsdetails.get("packingType") != null) { //包装方式
//                goods_packaging.setText(Database.goodsdetails.get("packingType").toString());
//            }
//            if (Database.goodsdetails.get("brandName") != null) { //品牌
//                tv_goods_brand.setText(Database.goodsdetails.get("brandName").toString());
//            }
//            if (Database.goodsdetails.get("originName") != null) { //产地
//                tv_producing_area.setText(Database.goodsdetails.get("originName").toString());
//            }
//            if (Database.goodsdetails.get("brandTEL") != null) { //厂家联系方式
//                tv_vender_phone.setText(Database.goodsdetails.get("brandTEL").toString());
//            }
//            if (Database.goodsdetails.get("shelfLife") != null) { //保质期
//                tv_expiration_date.setText(Database.goodsdetails.get("shelfLife").toString());
//            }
//            if (Database.goodsdetails.get("freshUrl") != null) { //
//                load(Database.goodsdetails.get("freshUrl").toString()); //
//            }
//            if (Database.goodsdetails.get("nutritiveValue") != null) { //营养价值
//                tv_nutritiveValue.setText(Database.goodsdetails.get("nutritiveValue").toString()); //
//            }
//
//            if (Database.goodsdetails.get("listfreshPicture") != null) { //生鲜标题图片路径
//                ArrayList<LinkedTreeMap<String, Object>> picture_list = (ArrayList<LinkedTreeMap<String, Object>>) Database.goodsdetails.get("listfreshPicture");
//                if (picture_list != null && picture_list.size() != 0) {
//                    String pic_str = "";
//                    for (int i = 0; i < picture_list.size(); i++) {
//                        if (picture_list.get(i) != null && picture_list.get(i).get("picturePath") != null) {
//                            pic_str = pic_str + picture_list.get(i).get("picturePath").toString() + ","; //生鲜图片  ,号隔开
//                        }
//                    }
//                    if (pic_str.split(",") != null && pic_str.split(",").length > 0) {
//                        images = pic_str.split(",");
//                    } else {
//                        images = new String[]{pic_str};
//                    }
//                    getBanner(); //开始广告轮播
////                    ScrollViewLayout(GoodsDetailsActivity.this, picture_list, lay_goodsImage_list);
//                }
//            }
//        }
        if (Database.goodsdetails != null) {
            if (Database.goodsdetails.getFreshName() != null) { //商品名称
                tv_goods_name.setText(Database.goodsdetails.getFreshName());
            }
            if (Database.goodsdetails.getFreshPrice() != 0) { //商品价格
                tv_goods_price.setText("¥ " + Database.goodsdetails.getFreshPrice());
            }
            if (Database.goodsdetails.getFreshTypeName() != null) { // freshTypeName
                tv_quick_deliver_goods.setText(Database.goodsdetails.getFreshTypeName());
            }
            if (Database.goodsdetails.getWeight() != 0) { //净含量
                tv_goods_weight.setText(Database.goodsdetails.getWeight() + "g");
            }
            if (Database.goodsdetails.getPackingType() != null) { //包装方式
                goods_packaging.setText(Database.goodsdetails.getPackingType().toString());
            }
            if (Database.goodsdetails.getBrandName() != null) { //品牌
                tv_goods_brand.setText(Database.goodsdetails.getBrandName());
            }
            if (Database.goodsdetails.getOriginName() != null) { //产地
                tv_producing_area.setText(Database.goodsdetails.getOriginName());
            }
            if (Database.goodsdetails.getBrandTEL() != null) { //厂家联系方式
                tv_vender_phone.setText(Database.goodsdetails.getBrandTEL());
            }
            if (Database.goodsdetails.getShelfLife() != null) { //保质期
                tv_expiration_date.setText(Database.goodsdetails.getShelfLife());
            }
            if (Database.goodsdetails.getFreshUrl() != null) { //
                load(Database.goodsdetails.getFreshUrl()); //
            }
            if (Database.goodsdetails.getNutritiveValue() != null) { //营养价值
                tv_nutritiveValue.setText(Database.goodsdetails.getNutritiveValue()+""); //
            }
            if (Database.goodsdetails.getListfreshPicture() != null) { //生鲜标题图片路径
                List<FreashInfo.ListFreshBean.ListfreshPictureBean> picture_list = Database.goodsdetails.getListfreshPicture();
                if (picture_list != null && picture_list.size() != 0) {
                    String pic_str = "";
                    for (int i = 0; i < picture_list.size(); i++) {
                        if (picture_list.get(i) != null && picture_list.get(i).getPicturePath() != null) {
                            pic_str = pic_str + picture_list.get(i).getPicturePath() + ","; //生鲜图片  ,号隔开
                        }
                    }
                    if (pic_str.split(",") != null && pic_str.split(",").length > 0) {
                        images = pic_str.split(",");
                    } else {
                        images = new String[]{pic_str};
                    }
                    getBanner(); //开始广告轮播
//                    ScrollViewLayout(GoodsDetailsActivity.this, picture_list, lay_goodsImage_list);
                }
            }
        }
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<LinkedTreeMap<String, Object>> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_goods_pic, lay_gallery, false);
                final ImageView iv_goods_pic = (ImageView) view.findViewById(R.id.iv_goods_pic);

                if (list != null && list.get(i).get("picturePath") != null && list.get(i).get("picturePath").toString().length() != 0 && !list.get(i).get("picturePath").toString().equals("")) {
                    String data = list.get(i).get("picturePath").toString();
//                    ServiceDialog.setPicture(list.get(i).get("picturePath").toString(), iv_goods_pic, null);
                    Glide.with(context).load(data).error(R.drawable.wait).placeholder(R.drawable.wait).into(iv_goods_pic);
                }

                lay_gallery.addView(view);
            }
        }
    }

    /**
     * 商品详情图片
     */
    private void getBanner() {
        Collections.addAll(imageList, images);
        convenientBanner2.startTurning(4000);
        convenientBanner2.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                }, imageList)
                //显示小圆圈
                .setPageIndicator(new int[]{R.drawable.point_unfocused, R.drawable.point_focused})
                //小圆圈的位置
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)

                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //每一张图片的点击事件

                    }
                });
    }

    private void load(String html) {
        //加载需要显示的网页
        webview_ervery.loadUrl(html);
        //设置WebView属性，能够执行Javascript脚本
        webview_ervery.getSettings().setPluginState(WebSettings.PluginState.ON); //支持插件
        WebSettings webSettings = webview_ervery.getSettings();  //android 5.0以上
//        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        //Android webview 从Lollipop(5.0)开始webview默认不允许混合模式，https当中不能加载http资源，需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setUseWideViewPort(false); //将图片调整到适合webview的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows(); //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口

        // 在Android中点击一个链接，默认是调用应用程序来启动，因此WebView需要代为处理这个动作 通过WebViewClient
        // 设置WebViewClient
        webview_ervery.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) { //https
                handler.proceed(); //接受证书
            }
        });

        webview_ervery.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    my_progress.setVisibility(View.GONE);
                } else {
                    if (View.GONE == my_progress.getVisibility()) {
                        my_progress.setVisibility(View.VISIBLE);
                    }
                    my_progress.setProgress(newProgress);
                    int x = (int) (newProgress / 2);
                    my_progress.setSecondaryProgress(newProgress + x);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Database.goodsdetails = null;
    }
}
