package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/5/9.
 *发送手机验证码 返回
 */
public class GetSMSCheckInfo extends ResponseBaseInfo{

    /**
     * phoneCode : null
     */

    private Object phoneCode; //电话号码

    public Object getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Object phoneCode) {
        this.phoneCode = phoneCode;
    }
}
