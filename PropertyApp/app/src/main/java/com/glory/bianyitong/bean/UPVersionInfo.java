package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/9.
 */
public class UPVersionInfo {

    /**
     * tableColumnPermission : []
     * listVersion : [{"versionID":1,"versionDate":"2017-02-09T00:00:00","versionNumber":"1.0.0","versionCode":"1.0.0","deviceTypeID":3,"imprint":"测试版","appTypeID":0,"prerequisite":false,"updatePath":"http://www.baidu.com"}]
     * version : null
     * datetime : null
     * accesstoken : null
     * statuscode : 1
     * statusmessage : 消息处理成功
     * alertmessage : 消息处理成功
     * totalrownum : null
     * totalpagenum : null
     * nowpagenum : null
     * pagerownum : null
     */

    private Object version;
    private Object datetime;
    private Object accesstoken;
    private int statuscode;
    private String statusmessage;
    private String alertmessage;
    private Object totalrownum;
    private Object totalpagenum;
    private Object nowpagenum;
    private Object pagerownum;
    private List<?> tableColumnPermission;
    private List<ListVersionBean> listVersion;

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public Object getDatetime() {
        return datetime;
    }

    public void setDatetime(Object datetime) {
        this.datetime = datetime;
    }

    public Object getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(Object accesstoken) {
        this.accesstoken = accesstoken;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public String getAlertmessage() {
        return alertmessage;
    }

    public void setAlertmessage(String alertmessage) {
        this.alertmessage = alertmessage;
    }

    public Object getTotalrownum() {
        return totalrownum;
    }

    public void setTotalrownum(Object totalrownum) {
        this.totalrownum = totalrownum;
    }

    public Object getTotalpagenum() {
        return totalpagenum;
    }

    public void setTotalpagenum(Object totalpagenum) {
        this.totalpagenum = totalpagenum;
    }

    public Object getNowpagenum() {
        return nowpagenum;
    }

    public void setNowpagenum(Object nowpagenum) {
        this.nowpagenum = nowpagenum;
    }

    public Object getPagerownum() {
        return pagerownum;
    }

    public void setPagerownum(Object pagerownum) {
        this.pagerownum = pagerownum;
    }

    public List<?> getTableColumnPermission() {
        return tableColumnPermission;
    }

    public void setTableColumnPermission(List<?> tableColumnPermission) {
        this.tableColumnPermission = tableColumnPermission;
    }

    public List<ListVersionBean> getListVersion() {
        return listVersion;
    }

    public void setListVersion(List<ListVersionBean> listVersion) {
        this.listVersion = listVersion;
    }

    public static class ListVersionBean {
        /**
         * versionID : 1
         * versionDate : 2017-02-09T00:00:00
         * versionNumber : 1.0.0
         * versionCode : 1.0.0
         * deviceTypeID : 3
         * imprint : 测试版
         * appTypeID : 0
         * prerequisite : false
         * updatePath : http://www.baidu.com
         */

        private int versionID;
        private String versionDate;
        private String versionNumber;
        private int versionCode;
        private int deviceTypeID;
        private String imprint;
        private int appTypeID;
        private boolean prerequisite;
        private String updatePath;

        public int getVersionID() {
            return versionID;
        }

        public void setVersionID(int versionID) {
            this.versionID = versionID;
        }

        public String getVersionDate() {
            return versionDate;
        }

        public void setVersionDate(String versionDate) {
            this.versionDate = versionDate;
        }

        public String getVersionNumber() {
            return versionNumber;
        }

        public void setVersionNumber(String versionNumber) {
            this.versionNumber = versionNumber;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public int getDeviceTypeID() {
            return deviceTypeID;
        }

        public void setDeviceTypeID(int deviceTypeID) {
            this.deviceTypeID = deviceTypeID;
        }

        public String getImprint() {
            return imprint;
        }

        public void setImprint(String imprint) {
            this.imprint = imprint;
        }

        public int getAppTypeID() {
            return appTypeID;
        }

        public void setAppTypeID(int appTypeID) {
            this.appTypeID = appTypeID;
        }

        public boolean isPrerequisite() {
            return prerequisite;
        }

        public void setPrerequisite(boolean prerequisite) {
            this.prerequisite = prerequisite;
        }

        public String getUpdatePath() {
            return updatePath;
        }

        public void setUpdatePath(String updatePath) {
            this.updatePath = updatePath;
        }
    }
}
