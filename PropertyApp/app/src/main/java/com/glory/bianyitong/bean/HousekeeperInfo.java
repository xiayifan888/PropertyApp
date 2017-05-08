package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/1/10.
 */
public class HousekeeperInfo {

    /**
     * tableColumnPermission : [{"columnID":0,"columnName":"houseKepperID","permissionValues":1,"columnDescription":"管家ID"},{"columnID":0,"columnName":"communityID","permissionValues":2,"columnDescription":"社区ID"},{"columnID":0,"columnName":"communityName","permissionValues":4,"columnDescription":"社区名称"},{"columnID":0,"columnName":"unitID","permissionValues":8,"columnDescription":"单元ID"},{"columnID":0,"columnName":"unitName","permissionValues":16,"columnDescription":"单元名称"},{"columnID":0,"columnName":"buildingID","permissionValues":32,"columnDescription":"楼栋ID"},{"columnID":0,"columnName":"buildingName","permissionValues":64,"columnDescription":"楼栋名称"},{"columnID":0,"columnName":"houseKeeperName","permissionValues":128,"columnDescription":"管家姓名"},{"columnID":0,"columnName":"houseKepperPhoto","permissionValues":256,"columnDescription":"管家头像"},{"columnID":0,"columnName":"workWeek","permissionValues":512,"columnDescription":"工作星期"},{"columnID":0,"columnName":"workTime","permissionValues":1024,"columnDescription":"工作时间 "},{"columnID":0,"columnName":"workPhoneNum","permissionValues":2048,"columnDescription":"工作电话"}]
     * listHousekeeper : [{"houseKepperID":1,"communityID":1,"communityName":"西丽小区","unitID":1,"unitName":"A","buildingID":1,"buildingName":"1号楼","houseKeeperName":"安远山","houseKepperPhoto":"https://www.pgagolf.cn:4432/images/head/index.jpg","workWeek":"1","workTime":"10-12","workPhoneNum":"18800001111"}]
     * version : Alpha 0.3
     * statuscode : 1
     * statusmessage : 消息处理成功
     */

    private String version;
    private int statuscode;
    private String statusmessage;
    private List<TableColumnPermissionBean> tableColumnPermission;
    private List<ListHousekeeperBean> listHousekeeper;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public List<TableColumnPermissionBean> getTableColumnPermission() {
        return tableColumnPermission;
    }

    public void setTableColumnPermission(List<TableColumnPermissionBean> tableColumnPermission) {
        this.tableColumnPermission = tableColumnPermission;
    }

    public List<ListHousekeeperBean> getListHousekeeper() {
        return listHousekeeper;
    }

    public void setListHousekeeper(List<ListHousekeeperBean> listHousekeeper) {
        this.listHousekeeper = listHousekeeper;
    }

    public static class TableColumnPermissionBean {
        /**
         * columnID : 0
         * columnName : houseKepperID
         * permissionValues : 1
         * columnDescription : 管家ID
         */

        private int columnID;
        private String columnName;
        private int permissionValues;
        private String columnDescription;

        public int getColumnID() {
            return columnID;
        }

        public void setColumnID(int columnID) {
            this.columnID = columnID;
        }

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public int getPermissionValues() {
            return permissionValues;
        }

        public void setPermissionValues(int permissionValues) {
            this.permissionValues = permissionValues;
        }

        public String getColumnDescription() {
            return columnDescription;
        }

        public void setColumnDescription(String columnDescription) {
            this.columnDescription = columnDescription;
        }
    }

    public static class ListHousekeeperBean {
        /**
         * houseKepperID : 1
         * communityID : 1
         * communityName : 西丽小区
         * unitID : 1
         * unitName : A
         * buildingID : 1
         * buildingName : 1号楼
         * houseKeeperName : 安远山
         * houseKepperPhoto : https://www.pgagolf.cn:4432/images/head/index.jpg
         * workWeek : 1
         * workTime : 10-12
         * workPhoneNum : 18800001111
         */

        private int houseKepperID;
        private int communityID;
        private String communityName;
        private int unitID;
        private String unitName;
        private int buildingID;
        private String buildingName;
        private String houseKeeperName;
        private String houseKepperPhoto;
        private String workWeek;
        private String workTime;
        private String workPhoneNum;

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

        public String getCommunityName() {
            return communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
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
