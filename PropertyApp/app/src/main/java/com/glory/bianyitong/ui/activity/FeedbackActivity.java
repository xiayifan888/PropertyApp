package com.glory.bianyitong.ui.activity;

import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.http.HttpURL;
import com.glory.bianyitong.http.OkGoRequest;
import com.glory.bianyitong.http.RequestUtil;
import com.glory.bianyitong.ui.dialog.ServiceDialog;
import com.google.gson.reflect.TypeToken;
import com.glory.bianyitong.R;
import com.glory.bianyitong.base.BaseActivity;
import com.glory.bianyitong.constants.Database;
import com.glory.bianyitong.util.DateUtil;
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
 * Created by lucy on 2016/11/14.
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.left_return_btn)
    RelativeLayout left_return_btn;
    @BindView(R.id.iv_title_text_right)
    TextView iv_title_text_right;
    @BindView(R.id.et_feed_content)
    ContainsEmojiEditText et_feed_content;

    private ProgressDialog progressDialog = null;
    @Override
    protected int getContentId() {
        return R.layout.ac_feedback;
    }

    @Override
    protected void init() {
        super.init();
        inintTitle(getString(R.string.feedback), false, getString(R.string.send));
        left_return_btn.setOnClickListener(new View.OnClickListener() { //返回
            @Override
            public void onClick(View view) {
                FeedbackActivity.this.finish();
            }
        });
        iv_title_text_right.setOnClickListener(new View.OnClickListener() {//保存
            @Override
            public void onClick(View v) {
                String feed = et_feed_content.getText().toString();
                if (feed.equals("")) {
                    ToastUtils.showToast(FeedbackActivity.this, getString(R.string.opinion_can_not_be_empty));//意见不能为空
                } else {
                    save(feed);
                }
            }
        });
    }

    //保存
    private void save(final String feed) {//
        String userID = RequestUtil.getuserid();
        String nowdate = DateUtil.formatTimesTampDate(DateUtil.getCurrentDate());//获取当前时间

        String json = "{\"feedback\": {\"feedbackID\": 1,\"presenterID\": 1,\"feedbackContext\": \""+feed+"\",\"feedbackDateTime\": \""+nowdate+"\"}," +
                "\"userid\": \"" + userID + "\",\"groupid\": \"\",\"datetime\": \"\",\"accesstoken\": \"\"," +
                "\"version\": \"\",\"messagetoken\": \"\",\"DeviceType\": \"3\",\"nowpagenum\": \"\"," +
                "\"pagerownum\": \"\",\"controllerName\": \"Feedback\",\"actionName\": \"ADD\"}";
        Log.i("resultString", "json------------" + json);
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

                    ToastUtils.showToast(FeedbackActivity.this, getString(R.string.feedback_is_successful));//反馈成功
                    FeedbackActivity.this.finish();
                } else {
                    ToastUtils.showToast(FeedbackActivity.this, getString(R.string.feedback_failed));//反馈失败
                }
            }

            @Override
            public void onError() {}
            @Override
            public void parseError() {}
            @Override
            public void onBefore() {
                progressDialog = ProgressDialog.show(FeedbackActivity.this, "", getString(R.string.commit), true);//提交
                progressDialog.setCanceledOnTouchOutside(true);
            }
            @Override
            public void onAfter() {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        }).getEntityData(url,json);
    }

}
