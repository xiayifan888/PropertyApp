package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/4.
 */
public class UserInfo {
    /**
     * tableColumnPermission : []
     * user : {"userID":"94FEcAbyfgAm6BRLR4iClg==","userName":"里斯","userName_en":null,"gender":"1","loginName":"狼与羊","password":"","phoneNumber":"15024070082","birthDay":null,"openID":null,"joinDate":"2016-12-30T16:43:27","status":"1","email":null,"qQ":null,"customerPhoto":"https://bianyitong.oss-cn-shenzhen.aliyuncs.com/android/userHeader/94FEcAbyfgAm6BRLR4iClg==_1484127916629_logo.png","chinaCity_ID":0,"chinaCity_Name":null,"signature":"红红火火恍恍惚惚","jGPushID":"18071adc030183a30dd","jGPushName":"V6NV8H86","roles":null}
     * userCommnunity : [{"userCommunityID":1024,"userID":25,"userName":"里斯","communityID":1,"communityName":"西丽小区","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":1,"buildingName":"1号楼","roomID":2,"roomName":"102","approvalStatus":1,"unitID":1,"unitName":"A单元","approvalDate":"2017-01-03T16:14:00"},{"userCommunityID":1025,"userID":25,"userName":"里斯","communityID":4,"communityName":"阳光花园","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":10,"buildingName":"1号楼","roomID":42,"roomName":"303","approvalStatus":1,"unitID":18,"unitName":"A单元","approvalDate":"2017-01-03T16:14:39"},{"userCommunityID":1039,"userID":25,"userName":"里斯","communityID":4,"communityName":"阳光花园","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":10,"buildingName":"1号楼","roomID":45,"roomName":"606","approvalStatus":2,"unitID":18,"unitName":"A单元","approvalDate":"2017-01-11T15:17:50"},{"userCommunityID":1050,"userID":25,"userName":"里斯","communityID":33,"communityName":"便易通社区","userIDentityID":1,"userIDentityName":"业主","provinceID":440000,"provinceName":"广东省","cityID":440300,"cityName":"深圳市","buildingID":18,"buildingName":"1号楼","roomID":26,"roomName":"505","approvalStatus":1,"unitID":35,"unitName":"A单元","approvalDate":"2017-01-13T17:00:21"}]
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
    private UserBean user;
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
    private List<UserCommnunityBean> userCommnunity;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

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

    public List<UserCommnunityBean> getUserCommnunity() {
        return userCommnunity;
    }

    public void setUserCommnunity(List<UserCommnunityBean> userCommnunity) {
        this.userCommnunity = userCommnunity;
    }

    public static class UserBean {
        /**
         * userID : 94FEcAbyfgAm6BRLR4iClg==
         * userName : 里斯
         * userName_en : null
         * gender : 1
         * loginName : 狼与羊
         * password :
         * phoneNumber : 15024070082
         * birthDay : null
         * openID : null
         * joinDate : 2016-12-30T16:43:27
         * status : 1
         * email : null
         * qQ : null
         * customerPhoto : https://bianyitong.oss-cn-shenzhen.aliyuncs.com/android/userHeader/94FEcAbyfgAm6BRLR4iClg==_1484127916629_logo.png
         * chinaCity_ID : 0
         * chinaCity_Name : null
         * signature : 红红火火恍恍惚惚
         * jGPushID : 18071adc030183a30dd
         * jGPushName : V6NV8H86
         * roles : null
         */
        private String userID;
        private String userName;
        private Object userName_en;
        private int gender;
        private String loginName;
        private String password;
        private String phoneNumber;
        private Object birthDay;
        private Object openID;
        private String joinDate;
        private int status;
        private Object email;
        private Object qQ;
        private String customerPhoto;
        private int chinaCity_ID;
        private Object chinaCity_Name;
        private String signature;
        private String jGPushID;
        private String jGPushName;
        private Object roles;

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getUserName_en() {
            return userName_en;
        }

        public void setUserName_en(Object userName_en) {
            this.userName_en = userName_en;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Object getBirthDay() {
            return birthDay;
        }

        public void setBirthDay(Object birthDay) {
            this.birthDay = birthDay;
        }

        public Object getOpenID() {
            return openID;
        }

        public void setOpenID(Object openID) {
            this.openID = openID;
        }

        public String getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(String joinDate) {
            this.joinDate = joinDate;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getQQ() {
            return qQ;
        }

        public void setQQ(Object qQ) {
            this.qQ = qQ;
        }

        public String getCustomerPhoto() {
            return customerPhoto;
        }

        public void setCustomerPhoto(String customerPhoto) {
            this.customerPhoto = customerPhoto;
        }

        public int getChinaCity_ID() {
            return chinaCity_ID;
        }

        public void setChinaCity_ID(int chinaCity_ID) {
            this.chinaCity_ID = chinaCity_ID;
        }

        public Object getChinaCity_Name() {
            return chinaCity_Name;
        }

        public void setChinaCity_Name(Object chinaCity_Name) {
            this.chinaCity_Name = chinaCity_Name;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getJGPushID() {
            return jGPushID;
        }

        public void setJGPushID(String jGPushID) {
            this.jGPushID = jGPushID;
        }

        public String getJGPushName() {
            return jGPushName;
        }

        public void setJGPushName(String jGPushName) {
            this.jGPushName = jGPushName;
        }

        public Object getRoles() {
            return roles;
        }

        public void setRoles(Object roles) {
            this.roles = roles;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"userID\":\"" + userID + "\"" +
                    ",\"userName\":\"" + userName + "\"" +
                    ",\"userName_en\":" + userName_en +
                    ",\"gender\":" + gender +
                    ",\"loginName\":\"" + loginName + "\"" +
                    ",\"password\":\"" + password + "\"" +
                    ",\"phoneNumber\":\"" + phoneNumber + "\"" +
                    ",\"birthDay\":" + birthDay +
                    ",\"openID\":" + openID +
                    ",\"joinDate\":\"" + joinDate + "\"" +
                    ",\"status\":" + status +
                    ",\"email\":" + email +
                    ",\"qQ\":" + qQ +
                    ",\"customerPhoto\":\"" + customerPhoto + "\"" +
                    ",\"chinaCity_ID\":" + chinaCity_ID +
                    ",\"chinaCity_Name\":" + chinaCity_Name +
                    ",\"signature\":\"" + signature + "\"" +
                    ",\"jGPushID\":\"" + jGPushID + "\"" +
                    ",\"jGPushName\":\"" + jGPushName + "\"" +
                    ",\"roles\":" + roles +
                    "}";
        }
    }

    public static class UserCommnunityBean {
        /**
         * userCommunityID : 1024
         * userID : 25
         * userName : 里斯
         * communityID : 1
         * communityName : 西丽小区
         * userIDentityID : 1
         * userIDentityName : 业主
         * provinceID : 440000
         * provinceName : 广东省
         * cityID : 440300
         * cityName : 深圳市
         * buildingID : 1
         * buildingName : 1号楼
         * roomID : 2
         * roomName : 102
         * approvalStatus : 1
         * unitID : 1
         * unitName : A单元
         * approvalDate : 2017-01-03T16:14:00
         */
        private int userCommunityID;
        private int userID;
        private String userName;
        private int communityID;
        private String communityName;
        private int userIDentityID;
        private String userIDentityName;
        private int provinceID;
        private String provinceName;
        private int cityID;
        private String cityName;
        private int buildingID;
        private String buildingName;
        private int roomID;
        private String roomName;
        private int approvalStatus;
        private int unitID;
        private String unitName;
        private String approvalDate;

        public int getUserCommunityID() {
            return userCommunityID;
        }

        public void setUserCommunityID(int userCommunityID) {
            this.userCommunityID = userCommunityID;
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

        public int getUserIDentityID() {
            return userIDentityID;
        }

        public void setUserIDentityID(int userIDentityID) {
            this.userIDentityID = userIDentityID;
        }

        public String getUserIDentityName() {
            return userIDentityName;
        }

        public void setUserIDentityName(String userIDentityName) {
            this.userIDentityName = userIDentityName;
        }

        public int getProvinceID() {
            return provinceID;
        }

        public void setProvinceID(int provinceID) {
            this.provinceID = provinceID;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getCityID() {
            return cityID;
        }

        public void setCityID(int cityID) {
            this.cityID = cityID;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(int buildingID) {
            this.buildingID = buildingID;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public int getRoomID() {
            return roomID;
        }

        public void setRoomID(int roomID) {
            this.roomID = roomID;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public int getApprovalStatus() {
            return approvalStatus;
        }

        public void setApprovalStatus(int approvalStatus) {
            this.approvalStatus = approvalStatus;
        }

        public int getUnitID() {
            return unitID;
        }

        public void setUnitID(int unitID) {
            this.unitID = unitID;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getApprovalDate() {
            return approvalDate;
        }

        public void setApprovalDate(String approvalDate) {
            this.approvalDate = approvalDate;
        }
        @Override
        public String toString() {
            return "{" +
                    "\"userCommunityID\":" + userCommunityID +
                    ",\"userID\":" + userID +
                    ",\"userName\":\"" + userName + "\"" +
                    ",\"communityID\":" + communityID +
                    ",\"communityName\":\"" + communityName + "\"" +
                    ",\"userIDentityID\":" + userIDentityID +
                    ",\"userIDentityName\":\"" + userIDentityName + "\"" +
                    ",\"provinceID\":" + provinceID +
                    ",\"provinceName\":\"" + provinceName + "\"" +
                    ",\"cityID\":" + cityID +
                    ",\"cityName\":\"" + cityName + "\"" +
                    ",\"buildingID\":" + buildingID +
                    ",\"buildingName\":\"" + buildingName + "\"" +
                    ",\"roomID\":" + roomID +
                    ",\"roomName\":\"" + roomName + "\"" +
                    ",\"approvalStatus\":" + approvalStatus +
                    ",\"unitID\":" + unitID +
                    ",\"unitName\":\"" + unitName + "\"" +
                    ",\"approvalDate\":\"" + approvalDate + "\"" +
                    "}";
        }
    }

}
