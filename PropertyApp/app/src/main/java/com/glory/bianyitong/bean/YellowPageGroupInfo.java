package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/8.
 */
public class YellowPageGroupInfo {
    /**
     * tableColumnPermission : [{"columnID":0,"columnName":"yellowPageGroupID","permissionValues":1,"columnDescription":"黄页分组ID"},{"columnID":0,"columnName":"yellowPageGroupName","permissionValues":2,"columnDescription":"黄页分组名称"},{"columnID":0,"columnName":"yellowPageGroupLeaf","permissionValues":4,"columnDescription":"递归字段"},{"columnID":0,"columnName":"yellowPageGroupPicture","permissionValues":8,"columnDescription":"黄页分组图标"}]
     * listYellowPageGroup : [{"yellowPageGroupID":1,"yellowPageGroupName":"警务","yellowPageGroupLeaf":2,"yellowPageGroupPicture":"https://byt.bytsz.com.cn/images/YellowPageGroup/iconpoliceservice.png"},{"yellowPageGroupID":2,"yellowPageGroupName":"行政单位","yellowPageGroupLeaf":1,"yellowPageGroupPicture":"https://byt.bytsz.com.cn/images/YellowPageGroup/iconadministrativeunit.png"},{"yellowPageGroupID":3,"yellowPageGroupName":"小区物管","yellowPageGroupLeaf":1,"yellowPageGroupPicture":"https://byt.bytsz.com.cn/images/YellowPageGroup/iconahousingestate.png"}]
     * version : Alpha 0.3
     * statuscode : 1
     * statusmessage : 消息处理成功
     */

    private String version;
    private int statuscode;
    private String statusmessage;
    private List<TableColumnPermissionBean> tableColumnPermission;
    private List<ListYellowPageGroupBean> listYellowPageGroup;

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

    public List<ListYellowPageGroupBean> getListYellowPageGroup() {
        return listYellowPageGroup;
    }

    public void setListYellowPageGroup(List<ListYellowPageGroupBean> listYellowPageGroup) {
        this.listYellowPageGroup = listYellowPageGroup;
    }

    public static class TableColumnPermissionBean {
        /**
         * columnID : 0
         * columnName : yellowPageGroupID
         * permissionValues : 1
         * columnDescription : 黄页分组ID
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

    public static class ListYellowPageGroupBean {
        /**
         * yellowPageGroupID : 1
         * yellowPageGroupName : 警务
         * yellowPageGroupLeaf : 2
         * yellowPageGroupPicture : https://byt.bytsz.com.cn/images/YellowPageGroup/iconpoliceservice.png
         */
        private int yellowPageGroupID;
        private String yellowPageGroupName;
        private int yellowPageGroupLeaf;
        private String yellowPageGroupPicture;

        public int getYellowPageGroupID() {
            return yellowPageGroupID;
        }

        public void setYellowPageGroupID(int yellowPageGroupID) {
            this.yellowPageGroupID = yellowPageGroupID;
        }

        public String getYellowPageGroupName() {
            return yellowPageGroupName;
        }

        public void setYellowPageGroupName(String yellowPageGroupName) {
            this.yellowPageGroupName = yellowPageGroupName;
        }

        public int getYellowPageGroupLeaf() {
            return yellowPageGroupLeaf;
        }

        public void setYellowPageGroupLeaf(int yellowPageGroupLeaf) {
            this.yellowPageGroupLeaf = yellowPageGroupLeaf;
        }

        public String getYellowPageGroupPicture() {
            return yellowPageGroupPicture;
        }

        public void setYellowPageGroupPicture(String yellowPageGroupPicture) {
            this.yellowPageGroupPicture = yellowPageGroupPicture;
        }
    }
}
