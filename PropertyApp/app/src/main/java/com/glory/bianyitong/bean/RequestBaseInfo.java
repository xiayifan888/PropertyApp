package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/5/9.
 *   Request基类 包头
 */
public class RequestBaseInfo {

    /**
     * timeStemp : “2017-4-2”
     * accessToken : “5488945897fdg89”
     * version : “版本号”
     * deviceType : “设备类型”
     * currentPageNumber : 0
     * pageRowNumber : 0
     * controllerName : “ApiIndex”
     * actionName : “Query”
     * userID : ”ddsfsgr-dfgr45hgj”
     * communityID : 0
     */

    private String timeStemp; //发送时间戳
    private String accessToken; //AccessToken值
    private String version;   //版本号
    private String deviceType; //设备类型
    private int currentPageNumber; //当前页
    private int pageRowNumber;  //每页记录数
    private String controllerName; //控制器名称
    private String actionName; //方法名称
    private String userID;  //加密的用户ID
    private int communityID; //社区ID

    public String getTimeStemp() {
        return timeStemp;
    }

    public void setTimeStemp(String timeStemp) {
        this.timeStemp = timeStemp;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getPageRowNumber() {
        return pageRowNumber;
    }

    public void setPageRowNumber(int pageRowNumber) {
        this.pageRowNumber = pageRowNumber;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCommunityID() {
        return communityID;
    }

    public void setCommunityID(int communityID) {
        this.communityID = communityID;
    }
}
