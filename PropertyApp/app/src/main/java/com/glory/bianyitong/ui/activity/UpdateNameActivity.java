package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.glory.bianyitong.util.DataUtils;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.JsonHelper;
import com.glory.bianyitong.util.ToastUtils;
import com.glory.bianyitong.view.ContainsEmojiEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.HashMap;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lucy on 2016/11/21.
 * 修改昵称
 */
public class UpdateNameActivity extends BaseActivity {
    @BindView(R.id.et_nickname)
    ContainsEmojiEditText et_nickname;

    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;

    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;

    String nick = "";
    private ProgressDialog progressDialog = null;

    @Override
    protected int getContentId() {
        return R.layout.activity_update_nickname;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.change_username), false, getString(R.string.save));//修改昵称  保存

        nick = getIntent().getStringExtra("nick");
        et_nickname.setText(nick);

        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                UpdateNameActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//保存
            @Override
            public void onClick(View v) {
                String name = et_nickname.getText().toString();
                if (name.equals("")) {
                    ToastUtils.showToast(UpdateNameActivity.this, getString(R.string.name_is_required));//名称不能为空
                } else {
                    save(name);
                }
            }
        });

    }

    //保存
    private void save(final String name) {//
        String userID = RequestUtil.getuserid();
        String phoneNumber = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getPhoneNumber() != null) {
            phoneNumber = Database.USER_MAP.getPhoneNumber();
        }
        String joinDate = "";
        if (Database.USER_MAP != null && Database.USER_MAP.getJoinDate() != null) {
            joinDate = Database.USER_MAP.getJoinDate();
        }
        //phoneNumber 跟 joinDate 一定要传
        String json = "{\"user\": {\"loginName\": \"" + name + "\",\"phoneNumber\": \"" + phoneNumber + "\", " +
                "\"joinDate\": \"" + joinDate + "\"},\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\"," +
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
//                            Database.USER_MAP.put("loginName", name);
                            Database.USER_MAP.setLoginName(name);
                            ToastUtils.showToast(UpdateNameActivity.this, getString(R.string.successfully_modified));//修改成功

                            DataUtils.saveSharePreToolsKits(UpdateNameActivity.this);

                            UpdateNameActivity.this.finish();
                        } else {
                            ToastUtils.showToast(UpdateNameActivity.this, getString(R.string.fail_to_edit));//修改失败
                        }
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.i("resultString", "请求错误------");
//                        ToastUtils.showToast(UpdateNameActivity.this, "未能链接到服务器");
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
                        progressDialog = ProgressDialog.show(UpdateNameActivity.this, "", getString(R.string.save), true);//保存
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
