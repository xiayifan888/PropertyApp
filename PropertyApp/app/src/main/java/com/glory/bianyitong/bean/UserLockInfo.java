package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/23.
 */
public class UserLockInfo {
    /**
     * tableColumnPermission : []
     * listUserLock : [{"userLockID":2,"userID":1,"userName":"Admin","communityID":1,"communityName":"西丽小区","lockID":10,"lockName":"荣耀大门","authorizationType":"A","authorizationUserID":25,"authorizationUserName":"里斯","authorizationUserPhone":null,"authorizationDateTime":"2016-12-23T20:26:01","userIdentity":1,"timeLimit":true,"startDate":"2016-12-23T00:00:00","endDate":"2017-01-01T00:00:00","status":"E","lockSort":1}]
     * version : null
     * datetime : null
     * accesstoken : null
     * statuscode : 1
     * statusmessage : 消息处理成功
     * alertmessage : 消息处理成功
     * totalrownum : null
     * totalpagenum : null
     * nowpagenum : null
     * pagerownum : 10
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
    private String pagerownum;
    private List<?> tableColumnPermission;
    private List<ListUserLockBean> listUserLock;

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

    public String getPagerownum() {
        return pagerownum;
    }

    public void setPagerownum(String pagerownum) {
        this.pagerownum = pagerownum;
    }

    public List<?> getTableColumnPermission() {
        return tableColumnPermission;
    }

    public void setTableColumnPermission(List<?> tableColumnPermission) {
        this.tableColumnPermission = tableColumnPermission;
    }

    public List<ListUserLockBean> getListUserLock() {
        return listUserLock;
    }

    public void setListUserLock(List<ListUserLockBean> listUserLock) {
        this.listUserLock = listUserLock;
    }

    public static class ListUserLockBean {
        /**
         * userLockID : 2
         * userID : 1
         * userName : Admin
         * communityID : 1
         * communityName : 西丽小区
         * lockID : 10
         * lockName : 荣耀大门
         * authorizationType : A
         * authorizationUserID : 25
         * authorizationUserName : 里斯
         * authorizationUserPhone : null
         * authorizationDateTime : 2016-12-23T20:26:01
         * userIdentity : 1
         * timeLimit : true
         * startDate : 2016-12-23T00:00:00
         * endDate : 2017-01-01T00:00:00
         * status : E
         * lockSort : 1
         */

        private int userLockID;
        private int userID;
        private String userName;
        private int communityID;
        private String communityName;
        private int lockID;
        private String lockName;
        private String authorizationType;
        private int authorizationUserID;
        private String authorizationUserName;
        private Object authorizationUserPhone;
        private String authorizationDateTime;
        private int userIdentity;
        private boolean timeLimit;
        private String startDate;
        private String endDate;
        private String status;
        private int lockSort;

        public int getUserLockID() {
            return userLockID;
        }

        public void setUserLockID(int userLockID) {
            this.userLockID = userLockID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public int getLockID() {
            return lockID;
        }

        public void setLockID(int lockID) {
            this.lockID = lockID;
        }

        public String getLockName() {
            return lockName;
        }

        public void setLockName(String lockName) {
            this.lockName = lockName;
        }

        public String getAuthorizationType() {
            return authorizationType;
        }

        public void setAuthorizationType(String authorizationType) {
            this.authorizationType = authorizationType;
        }

        public int getAuthorizationUserID() {
            return authorizationUserID;
        }

        public void setAuthorizationUserID(int authorizationUserID) {
            this.authorizationUserID = authorizationUserID;
        }

        public String getAuthorizationUserName() {
            return authorizationUserName;
        }

        public void setAuthorizationUserName(String authorizationUserName) {
            this.authorizationUserName = authorizationUserName;
        }

        public Object getAuthorizationUserPhone() {
            return authorizationUserPhone;
        }

        public void setAuthorizationUserPhone(Object authorizationUserPhone) {
            this.authorizationUserPhone = authorizationUserPhone;
        }

        public String getAuthorizationDateTime() {
            return authorizationDateTime;
        }

        public void setAuthorizationDateTime(String authorizationDateTime) {
            this.authorizationDateTime = authorizationDateTime;
        }

        public int getUserIdentity() {
            return userIdentity;
        }

        public void setUserIdentity(int userIdentity) {
            this.userIdentity = userIdentity;
        }

        public boolean isTimeLimit() {
            return timeLimit;
        }

        public void setTimeLimit(boolean timeLimit) {
            this.timeLimit = timeLimit;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getLockSort() {
            return lockSort;
        }

        public void setLockSort(int lockSort) {
            this.lockSort = lockSort;
        }
    }
}
