package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/14.
 */
public class MessageInfo {

    /**
     * tableColumnPermission : []
     * listSystemMsg : [{"messageID":1,"communityID":0,"messageTime":"2017-01-11T00:00:00","messageTitle":"系统消息","messageContext":"极光推送","userID":1,"messageType":1},{"messageID":2,"communityID":1,"messageTime":"2016-12-20T00:00:00","messageTitle":"DF","messageContext":"SD","userID":1,"messageType":3},{"messageID":3,"communityID":1,"messageTime":"2016-12-20T00:00:00","messageTitle":"DF","messageContext":"SD","userID":1,"messageType":3}]
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
    private List<ListSystemMsgBean> listSystemMsg;

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

    public List<ListSystemMsgBean> getListSystemMsg() {
        return listSystemMsg;
    }

    public void setListSystemMsg(List<ListSystemMsgBean> listSystemMsg) {
        this.listSystemMsg = listSystemMsg;
    }

    public static class ListSystemMsgBean {
        /**
         * messageID : 1
         * communityID : 0
         * messageTime : 2017-01-11T00:00:00
         * messageTitle : 系统消息
         * messageContext : 极光推送
         * userID : 1
         * messageType : 1
         */

        private String messageID;
        private int communityID;
        private String messageTime;
        private String messageTitle;
        private String messageContext;
        private int userID;
        private int messageType;

        public String getMessageID() {
            return messageID;
        }

        public void setMessageID(String messageID) {
            this.messageID = messageID;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getMessageTime() {
            return messageTime;
        }

        public void setMessageTime(String messageTime) {
            this.messageTime = messageTime;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }

        public String getMessageContext() {
            return messageContext;
        }

        public void setMessageContext(String messageContext) {
            this.messageContext = messageContext;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getMessageType() {
            return messageType;
        }

        public void setMessageType(int messageType) {
            this.messageType = messageType;
        }
    }
}
