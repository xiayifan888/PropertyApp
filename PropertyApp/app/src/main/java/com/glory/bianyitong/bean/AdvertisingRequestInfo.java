package com.glory.bianyitong.bean;

/**
 * Created by lucy on 2017/5/9.
 */
public class AdvertisingRequestInfo {
    /**
     * advertising : {"communityID":0}
     * systemMsg : {"communityID":0}
     */

    private AdvertisingBean advertising;
    private SystemMsgBean systemMsg;

    public AdvertisingBean getAdvertising() {
        return advertising;
    }

    public void setAdvertising(AdvertisingBean advertising) {
        this.advertising = advertising;
    }

    public SystemMsgBean getSystemMsg() {
        return systemMsg;
    }

    public void setSystemMsg(SystemMsgBean systemMsg) {
        this.systemMsg = systemMsg;
    }

    public static class AdvertisingBean {
        /**
         * communityID : 0
         */

        private int communityID;

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }
    }

    public static class SystemMsgBean {
        /**
         * communityID : 0
         */

        private int communityID;

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }
    }
    //广告请求 字段

}
