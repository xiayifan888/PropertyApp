package com.glory.bianyitong.ui.activity;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/14.
 * 添加小区
 */
public class AddAreaCityActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    @BindView(R.id.tv_near)
    TextView tv_near;
    @BindView(R.id.city_community_list)
    LinearLayout city_community_list;

    @BindView(R.id.clean_word)
    RelativeLayout clean_word;
    @BindView(R.id.et_search_area_txt)
    EditText et_search_area_txt;

    private String userID = "";
    private ProgressDialog progressDialog = null;

    private LocationManager locationManager;
    private String provider;
    private double latitude; //维度
    private double longitude; //经度
    private ArrayList<LinkedTreeMap<String, Object>> nearlist;
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onLocationChanged(Location arg0) {
            // TODO Auto-generated method stub
            latitude = arg0.getLatitude(); //纬度
            longitude = arg0.getLongitude(); //经度
            request_community(latitude, longitude);
            Log.i("resultString", "latitude---------" + latitude);
            Log.i("resultString", "longitude---------" + longitude);
        }
    };
    private Handler mhandler;
    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                /*隐藏软键盘*/
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
                }
                //这里写发送信息的方法
                String tag_name = et_search_area_txt.getText().toString().trim();
                if (tag_name.equals("")) {
                    ToastUtils.showToast(AddAreaCityActivity.this, getResources().getString(R.string.search_can_not_be_empty));//搜索不能为空
                } else {
                    Message msg = new Message();
                    msg.obj = tag_name;
                    msg.what = 0;
                    mhandler.sendMessage(msg);
                }
                return true;
            }
            return false;
        }
    };

    @Override
    protected int getContentId() {
        return R.layout.activity_add_area_city;
    }

    @Override
    protected void init() {
        super.init();
        //初始化标题栏
        inintTitle(getResources().getString(R.string.add_community), true, ""); //添加小区
        initview();
        userID = RequestUtil.getuserid();
        //定位
        startlocation();
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        request_community2(msg.obj.toString());
                        break;
                }
            }
        };
    }

    /**
     * 动态添加布局
     */
    public void ScrollViewLayout(final Context context, final List<LinkedTreeMap<String, Object>> list, LinearLayout lay_gallery) {
        lay_gallery.removeAllViews();
        LayoutInflater mInflater = LayoutInflater.from(context);
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final View view = mInflater.inflate(R.layout.view_item_community_city, lay_gallery, false);
                final RelativeLayout item_auth_lay = (RelativeLayout) view.findViewById(R.id.item_auth_lay);
                final TextView auth_area_name = (TextView) view.findViewById(R.id.auth_area_name);
                final TextView tv_city_name = (TextView) view.findViewById(R.id.tv_city_name);
                final TextView auth_area_line = (TextView) view.findViewById(R.id.auth_area_line);

                if (list != null && list.get(i).get("communityName") != null && list.get(i).get("communityName").toString().length() != 0 && !list.get(i).get("communityName").toString().equals("")) {
                    auth_area_name.setText(list.get(i).get("communityName").toString()); //小区名称
                }
                if (list != null && list.get(i).get("cityName") != null && list.get(i).get("cityName").toString().length() != 0 && !list.get(i).get("cityName").toString().equals("")) {
                    tv_city_name.setText(list.get(i).get("cityName").toString());
                }
                if (i == list.size() - 1) { //最后一根线
                    auth_area_line.setVisibility(View.GONE);
                }
                final int j = i;
                item_auth_lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        if (list != null && list.get(j).get("communityID") != null) {
                            Database.communityID = Double.valueOf(list.get(j).get("communityID").toString()).intValue();
                            if (list != null && list.get(j).get("communityName") != null) {
                                Database.communityName = list.get(j).get("communityID").toString();
                            }
                            if (list != null && list.get(j).get("provinceID") != null) {
                                Database.id_province = Double.valueOf(list.get(j).get("provinceID").toString()).intValue();
                            }
                            if (list != null && list.get(j).get("provinceName") != null) {
                                Database.str_province = list.get(j).get("provinceName").toString();
                            }
                            if (list != null && list.get(j).get("cityID") != null) {
                                Database.id_city = Double.valueOf(list.get(j).get("cityID").toString()).intValue();
                            }
                            if (list != null && list.get(j).get("cityName") != null) {
                                Database.str_city = list.get(j).get("cityName").toString();
                            }
                            Intent intent = new Intent(AddAreaCityActivity.this, AddRoomActivity.class);
                            intent.putExtra("communityID", Double.valueOf(list.get(j).get("communityID").toString()).intValue());
                            startActivity(intent);
                        }
                    }
                });
                lay_gallery.addView(view);
            }
        }
    }

    private void request_community2(String name) { //获取小区
        String json = "{\"community\": {\"communityName\": \"" + name + "\"}," +
                "\"userid\": \"" + userID + "\",\"controllerName\": \"Community\"," +
                "\"actionName\": \"FuzzyQuery\"}";
        Log.i("resultString", "json------" + json);
        String url = HttpURL.HTTP_LOGIN_AREA + "/Community/FuzzyQuery"; //附近小区
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                });
                if (hashMap2 != null && hashMap2.get("listCommunity") != null) {
                    ArrayList<LinkedTreeMap<String, Object>> list = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunity");
                    if (list != null && list.size() != 0) {
                        ScrollViewLayout(AddAreaCityActivity.this, list, city_community_list);
                    }
                } else if (hashMap2.get("alertmessage") != null) {
                    ToastUtils.showToast(AddAreaCityActivity.this, hashMap2.get("alertmessage").toString());
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(AddAreaCityActivity.this, "", getResources().getString(R.string.load), true);//加载
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(url, json);
    }

    private void request_community(double latitude, double longitude) { //获取小区
        String json = "{\"community\": {\"latitude\":" + latitude + ",\"longitude\":" + longitude + "}," +
                "\"userid\": \"" + userID + "\",\"controllerName\": \"Community\"," +
                "\"actionName\": \"StructureQuery\"}";
        Log.i("resultString", "json------" + json);
        String url = HttpURL.HTTP_LOGIN_AREA + "/Community/StructureQuery"; //附近小区
        OkGoRequest.getRequest().setOnOkGoUtilListener(new OkGoRequest.OnOkGoUtilListener() {
            @Override
            public void onSuccess(String s) {
                Log.i("resultString", "------------");
                Log.i("resultString", s);
                Log.i("resultString", "------------");
                HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {});
                if (hashMap2 != null && hashMap2.get("listCommunity") != null) {
                    nearlist = (ArrayList<LinkedTreeMap<String, Object>>) hashMap2.get("listCommunity");
                    if (nearlist != null && nearlist.size() != 0) {
                        ScrollViewLayout(AddAreaCityActivity.this, nearlist, city_community_list);
                    }
                } else if (hashMap2.get("alertmessage") != null) {
                    ToastUtils.showToast(AddAreaCityActivity.this, hashMap2.get("alertmessage").toString());
                }
            }
            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {}
            @Override
            public void onAfter() {}
        }).getEntityData(url, json);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.left_return_btn: //返回
                AddAreaCityActivity.this.finish();
                break;
            case R.id.clean_word:
                et_search_area_txt.setText("");
                if (nearlist != null && nearlist.size() != 0) {
                    ScrollViewLayout(AddAreaCityActivity.this, nearlist, city_community_list);
                }
                break;
        }
    }

    private void location() {
        //获取定位服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
//            Toast.makeText(this, GPS模块正常 ,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.please_turn_on_gps), Toast.LENGTH_SHORT).show(); //请开启GPS
            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
            startActivityForResult(intent, 0); //此为设置完成后返回到获取界面
        }
        //获取当前可用的位置控制器
        List<String> list = locationManager.getProviders(true);
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER;
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, getResources().getString(R.string.check_whether_the_network_or_gps_is_open), Toast.LENGTH_LONG).show();//请检查网络或GPS是否打开
            return;
        }
        try {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                //获取当前位置，这里只用到了经纬度
                latitude = location.getLatitude(); //纬度
                longitude = location.getLongitude(); //经度
                request_community(latitude, longitude);
                Log.i("resultString", "latitude---------" + latitude);
                Log.i("resultString", "longitude---------" + longitude);
            }
            //绑定定位事件，监听位置是否改变
            //第一个参数为控制器类型第二个参数为监听位置变化的时间间隔（单位：毫秒）
            //第三个参数为位置变化的间隔（单位：米）第四个参数为位置监听器
            locationManager.requestLocationUpdates(provider, 60000, 500, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private void initview() {
        et_search_area_txt.setOnKeyListener(onKeyListener);

        clean_word.setOnClickListener(this);
        left_return_btn.setOnClickListener(this);
        et_search_area_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                if (arg0.length() > 0) {
                    clean_word.setVisibility(View.VISIBLE);
                } else {
                    clean_word.setVisibility(View.GONE);
                }
            }
        });
    }

    // android 6.0d 权限管理变了，属于隐私权限，需要在运行时手动申请，参考如下代码
    private void startlocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(AddAreaCityActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                return;
            } else {
                //有权限
                location();
            }
        } else {
            location();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    location();
                } else {
                    location();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //关闭时解除监听器
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            if (locationManager != null) {
                locationManager.removeUpdates(locationListener);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

}
