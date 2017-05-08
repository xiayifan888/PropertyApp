package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/1/10.
 */
public class AdvertisingInfo {

    /**
     * tableColumnPermission : []
     * listAdvertising : [{"advertisingID":1,"advertisingType":"1","advertisingPicture":"https://www.pgagolf.cn:4432/images/Advertising/ad1.jpg","advertisingVideo":"","advertisingStartDate":"2016-11-20T00:00:00","advertisingEndDate":"2016-12-20T00:00:00","advertisingShowTime":1,"communityID":1,"advertisingLocation":1},{"advertisingID":2,"advertisingType":"1","advertisingPicture":"https://www.pgagolf.cn:4432/images/Advertising/ad2.jpg","advertisingVideo":"","advertisingStartDate":"2016-11-20T00:00:00","advertisingEndDate":"2016-12-20T00:00:00","advertisingShowTime":1,"communityID":1,"advertisingLocation":1},{"advertisingID":3,"advertisingType":"1","advertisingPicture":"https://www.pgagolf.cn:4432/images/Advertising/ad3.jpg","advertisingVideo":"","advertisingStartDate":"2016-11-20T00:00:00","advertisingEndDate":"2016-12-20T00:00:00","advertisingShowTime":1,"communityID":1,"advertisingLocation":1},{"advertisingID":12,"advertisingType":"1","advertisingPicture":"https://www.pgagolf.cn:4432/images/Advertising/ad1.jpg","advertisingVideo":"","advertisingStartDate":"2016-11-20T00:00:00","advertisingEndDate":"2016-12-20T00:00:00","advertisingShowTime":1,"communityID":0,"advertisingLocation":1},{"advertisingID":13,"advertisingType":"1","advertisingPicture":"https://www.pgagolf.cn:4432/images/Advertising/ad2.jpg","advertisingVideo":"","advertisingStartDate":"2016-11-20T00:00:00","advertisingEndDate":"2016-12-20T00:00:00","advertisingShowTime":1,"communityID":0,"advertisingLocation":1},{"advertisingID":14,"advertisingType":"1","advertisingPicture":"https://www.pgagolf.cn:4432/images/Advertising/ad5.jpg","advertisingVideo":"","advertisingStartDate":"2016-11-20T00:00:00","advertisingEndDate":"2016-12-20T00:00:00","advertisingShowTime":1,"communityID":0,"advertisingLocation":1}]
     * version : null
     * datetime : null
     * accesstoken : null
     * statuscode : 1
     * statusmessage : 消息处理成功
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
    private Object totalrownum;
    private Object totalpagenum;
    private Object nowpagenum;
    private Object pagerownum;
    private List<?> tableColumnPermission;
    private List<ListAdvertisingBean> listAdvertising;

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

    public List<ListAdvertisingBean> getListAdvertising() {
        return listAdvertising;
    }

    public void setListAdvertising(List<ListAdvertisingBean> listAdvertising) {
        this.listAdvertising = listAdvertising;
    }

    public static class ListAdvertisingBean {
        /**
         * advertisingID : 1
         * advertisingType : 1
         * advertisingPicture : https://www.pgagolf.cn:4432/images/Advertising/ad1.jpg
         * advertisingVideo :
         * advertisingStartDate : 2016-11-20T00:00:00
         * advertisingEndDate : 2016-12-20T00:00:00
         * advertisingShowTime : 1
         * communityID : 1
         * advertisingLocation : 1
         */

        private int advertisingID;
        private String advertisingType;
        private String advertisingPicture;
        private String advertisingVideo;
        private String advertisingStartDate;
        private String advertisingEndDate;
        private int advertisingShowTime;
        private int communityID;
        private int advertisingLocation;

        public int getAdvertisingID() {
            return advertisingID;
        }

        public void setAdvertisingID(int advertisingID) {
            this.advertisingID = advertisingID;
        }

        public String getAdvertisingType() {
            return advertisingType;
        }

        public void setAdvertisingType(String advertisingType) {
            this.advertisingType = advertisingType;
        }

        public String getAdvertisingPicture() {
            return advertisingPicture;
        }

        public void setAdvertisingPicture(String advertisingPicture) {
            this.advertisingPicture = advertisingPicture;
        }

        public String getAdvertisingVideo() {
            return advertisingVideo;
        }

        public void setAdvertisingVideo(String advertisingVideo) {
            this.advertisingVideo = advertisingVideo;
        }

        public String getAdvertisingStartDate() {
            return advertisingStartDate;
        }

        public void setAdvertisingStartDate(String advertisingStartDate) {
            this.advertisingStartDate = advertisingStartDate;
        }

        public String getAdvertisingEndDate() {
            return advertisingEndDate;
        }

        public void setAdvertisingEndDate(String advertisingEndDate) {
            this.advertisingEndDate = advertisingEndDate;
        }

        public int getAdvertisingShowTime() {
            return advertisingShowTime;
        }

        public void setAdvertisingShowTime(int advertisingShowTime) {
            this.advertisingShowTime = advertisingShowTime;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public int getAdvertisingLocation() {
            return advertisingLocation;
        }

        public void setAdvertisingLocation(int advertisingLocation) {
            this.advertisingLocation = advertisingLocation;
        }
    }
}
