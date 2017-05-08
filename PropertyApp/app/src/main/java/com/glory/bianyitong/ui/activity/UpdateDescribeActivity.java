package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.constants.Constant;
import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.SharePreToolsKits;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/21.
 * 个人描述
 */
public class UpdateDescribeActivity extends BaseActivity {

    @BindView(R.id.et_describe)
    EditText et_describe;
    //标题
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.iv_title_text_left)
    TextView iv_title_text_left;

    String describe = "";
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_personal_describe;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.personal_description), false, getString(R.string.determine));//个人描述  确定
        left_return_btn.setVisibility(View.GONE);

        describe = getIntent().getStringExtra("desc");
        et_describe.setText(describe);

        iv_title_text_left.setVisibility(View.VISIBLE);
        iv_title_text_left.setText(getString(R.string.cancel));//取消
        iv_title_text_left.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                UpdateDescribeActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//保存
            @Override
            public void onClick(View v) {
                String desc = et_describe.getText().toString();
                if (desc.equals("")) {
                    ToastUtils.showToast(UpdateDescribeActivity.this, getString(R.string.description_can_not_be_empty));//描述不能为空
                } else {
                    save(desc);
                }
            }
        });

    }

    //保存
    private void save(final String desc) {//
        String userID = RequestUtil.getuserid();
        String phoneNumber = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getPhoneNumber() != null) {
            phoneNumber = Database.USER_MAP.getPhoneNumber();
        }
        String joinDate = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getJoinDate() != null) {
            joinDate = Database.USER_MAP.getJoinDate();
        }
        String json = "{\"user\": {\"signature\": \"" + desc + "\",\"phoneNumber\": \"" + phoneNumber + "\"," +
                " \"joinDate\": \"" + joinDate + "\"},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
                "\"accesstoken\": \"\",\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"3\",\"nowpagenum\": \"\"," +
                "\"pagerownum\": \"\",\"controllerName\": \"User\",\"actionName\": \"Edit\"}";
        Log.i("resultString", "json------------" + json);
        OkGo.post(HttpURL.HTTP_LOGIN) //编辑
                .tag(this)//
//                .headers("", "")//
                .params("request", json)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.i("resultString", "------------");
                        Log.i("resultString", s);
                        Log.i("resultString", "------------");
                        HashMap<String, Object> hashMap2 = JsonHelper.fromJson(s, new TypeToken<HashMap<String, Object>>() {
                        });
                        if (hashMap2 != null && hashMap2.get("statuscode") != null &&
                                Double.valueOf(hashMap2.get("statuscode").toString()).intValue() == 1) {
//                            Database.USER_MAP.put("signature", desc);
                            Database.USER_MAP.setSignature(desc);
                            ToastUtils.showToast(UpdateDescribeActivity.this, getString(R.string.successfully_modified));//修改成功

                            HashMap<String, Object> hashMap3 = new HashMap<>();
                            hashMap3.put("user", Database.USER_MAP);
                            hashMap3.put("userCommnunity", Database.my_community_List);
                            String json = JsonHelper.toJson(hashMap3);
                            SharePreToolsKits.putJsonDataString(UpdateDescribeActivity.this, Constant.user, json); //缓存登录后信息 修改

                            UpdateDescribeActivity.this.finish();
                        } else {
                            ToastUtils.showToast(UpdateDescribeActivity.this, getString(R.string.fail_to_edit));//修改失败
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(UpdateDescribeActivity.this, "请求失败...");
                        ServiceDialog.showRequestFailed();
                    }

                    @Override
                    public void parseError(Call call, Exception e) {
                        super.parseError(call, e);
                        Log.i("resultString", "网络解析错误------");
                    }

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        progressDialog = ProgressDialog.show(UpdateDescribeActivity.this, "", getString(R.string.save), true);//保存
                        progressDialog.setCanceledOnTouchOutside(true);
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                    }
                });
    }

}
