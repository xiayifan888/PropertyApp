package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/1/10.
 * 物业管家 实体类
 */
public class listHousekeeperInfo extends ResponseBaseInfo{

    private List<ListHousekeeperBean> listHousekeeper;

    public List<ListHousekeeperBean> getListHousekeeper() {
        return listHousekeeper;
    }

    public void setListHousekeeper(List<ListHousekeeperBean> listHousekeeper) {
        this.listHousekeeper = listHousekeeper;
    }

    public static class ListHousekeeperBean {
        /**
         * houseKepperID : 1
         * communityID : 1
         * buildingID : 1
         * unitID : 1
         * houseKeeperName : 物业中心
         * houseKepperPhoto : https://byt.bytsz.com.cn/images/head/index.jpg
         * workWeek : 2
         * workTime : 1
         * workPhoneNum : 13011112222
         */

        private int houseKepperID;   //社区公告唯一ID
        private int communityID;   //社区ID
        private int buildingID;   //楼栋ID
        private int unitID;   //单元ID
        private String houseKeeperName;   //管家姓名
        private String houseKepperPhoto;   //管家头像
        private String workWeek;   //工作星期
        private String workTime;   //工作时间
        private String workPhoneNum;   //工作电话

        public int getHouseKepperID() {
            return houseKepperID;
        }

        public void setHouseKepperID(int houseKepperID) {
            this.houseKepperID = houseKepperID;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public int getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(int buildingID) {
            this.buildingID = buildingID;
        }

        public int getUnitID() {
            return unitID;
        }

        public void setUnitID(int unitID) {
            this.unitID = unitID;
        }

        public String getHouseKeeperName() {
            return houseKeeperName;
        }

        public void setHouseKeeperName(String houseKeeperName) {
            this.houseKeeperName = houseKeeperName;
        }

        public String getHouseKepperPhoto() {
            return houseKepperPhoto;
        }

        public void setHouseKepperPhoto(String houseKepperPhoto) {
            this.houseKepperPhoto = houseKepperPhoto;
        }

        public String getWorkWeek() {
            return workWeek;
        }

        public void setWorkWeek(String workWeek) {
            this.workWeek = workWeek;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public String getWorkPhoneNum() {
            return workPhoneNum;
        }

        public void setWorkPhoneNum(String workPhoneNum) {
            this.workPhoneNum = workPhoneNum;
        }
    }
}
