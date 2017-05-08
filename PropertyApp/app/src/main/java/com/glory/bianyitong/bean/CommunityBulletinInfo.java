package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/14.
 */
public class CommunityBulletinInfo {

    /**
     * tableColumnPermission : [{"columnID":0,"columnName":"bulletinID","permissionValues":1,"columnDescription":"公告ID"},{"columnID":0,"columnName":"communityID","permissionValues":2,"columnDescription":"社区ID"},{"columnID":0,"columnName":"communityName","permissionValues":4,"columnDescription":"社区名称"},{"columnID":0,"columnName":"bulletinTittle","permissionValues":8,"columnDescription":"公告标题"},{"columnID":0,"columnName":"bulletinContent","permissionValues":16,"columnDescription":"公告内容"},{"columnID":0,"columnName":"bulletinDatetime","permissionValues":32,"columnDescription":"公告内容日期"},{"columnID":0,"columnName":"bulletinDeadline","permissionValues":64,"columnDescription":"公告截止日期"}]
     * listCommunityBulletin : [{"bulletinID":1,"communityID":1,"communityName":"西丽小区","bulletinTittle":"公告","bulletinContent":"为了今后从根本上解决本小区的供水问题，不再发生经常停水的情况，经过各位业主的齐心协力和热心人士的积极斡旋，与水务、建设部门及小区开发商达成协议，同意把我们小区民用水和商铺用水分离。为此，经水务部门预算，需要我们小区居民共同分摊6万多元的分离安装费。经小区业主委员与业主代表们协议，按每户住房面积计收，每平方米分摊4元，兹定于10月30日起收缴。请各位业主积极配合，自觉交清！如有业主不愿分摊安装费用，自愿放弃此次分离供水后的用水权利，请您签好表明自行解决用水、自愿放弃小区供水权利的协议！敬请各位业主自觉配合，积极参加利自己利邻居的大好事！！！祝各位业主安居乐业、阖家幸福！！！","bulletinDatetime":"2016-10-10T00:00:00","bulletinDeadline":"2016-10-30T00:00:00"},{"bulletinID":4,"communityID":1,"communityName":"西丽小区","bulletinTittle":"临时停电","bulletinContent":"根据电力公司2016年11月14日下发的停电通知，因需要进行设备检修，暂停供电：停电时间为2016年11月15日上午5时30分--下午18时止停电造成的不便敬请谅解！","bulletinDatetime":"2016-11-15T05:30:00","bulletinDeadline":"2016-11-15T18:00:00"},{"bulletinID":7,"communityID":1,"communityName":"西丽小区","bulletinTittle":"停水通知","bulletinContent":"因学校建立节能监管平台，10月27日，后勤管理处物业管理中心将对校内水表进行整体更换，届时将暂停自来水供应，具体安排如下： 停水时间：12月10日 23：00至次日5：00 停水地点：44号楼、47号楼、23号楼、学生一餐厅 由此给您带来的不便，敬请谅解","bulletinDatetime":"2016-12-10T23:00:00","bulletinDeadline":"2016-12-11T05:00:00"},{"bulletinID":11,"communityID":1,"communityName":"西丽小区","bulletinTittle":"防盗通告","bulletinContent":"您好，年关将近，为了确保本小区安全，提高防范意识，严控盗窃案件发生，防患于未然，勤兴物业特别提醒居民及商户，务必做好如下自防工作：1、请大家睡觉或出门前务必关好门窗，家里不要存放大量现金，妥善保管好自己财物和贵重物品；2、发现异常情况或可疑人员及时与相关部门联系3、请大家出入小区大门和单元门时对尾随的陌生人提高警觉；4、如有陌生人造访，请勿轻易开门；5、需外出探亲、旅游或有长期出行计划的业主，在出发前，请切断家中水、电、气和其它弱电接口，同时知会物业服务中心，以便秩序维护人员在巡查中多加注意；小区的安全离不开您的支持与配合，为了小区与业主记商户自身的安全，让我们共同做好安防工作，共同保护我们的美好家园。","bulletinDatetime":"2016-12-16T00:00:00","bulletinDeadline":"2016-12-25T00:00:00"}]
     * version : Alpha 0.3
     * statuscode : 1
     * statusmessage : 消息处理成功
     */

    private String version;
    private int statuscode;
    private String statusmessage;
    private List<TableColumnPermissionBean> tableColumnPermission;
    private List<ListCommunityBulletinBean> listCommunityBulletin;

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

    public List<ListCommunityBulletinBean> getListCommunityBulletin() {
        return listCommunityBulletin;
    }

    public void setListCommunityBulletin(List<ListCommunityBulletinBean> listCommunityBulletin) {
        this.listCommunityBulletin = listCommunityBulletin;
    }

    public static class TableColumnPermissionBean {
        /**
         * columnID : 0
         * columnName : bulletinID
         * permissionValues : 1
         * columnDescription : 公告ID
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

    public static class ListCommunityBulletinBean {
        /**
         * bulletinID : 1
         * communityID : 1
         * communityName : 西丽小区
         * bulletinTittle : 公告
         * bulletinContent : 为了今后从根本上解决本小区的供水问题，不再发生经常停水的情况，经过各位业主的齐心协力和热心人士的积极斡旋，与水务、建设部门及小区开发商达成协议，同意把我们小区民用水和商铺用水分离。为此，经水务部门预算，需要我们小区居民共同分摊6万多元的分离安装费。经小区业主委员与业主代表们协议，按每户住房面积计收，每平方米分摊4元，兹定于10月30日起收缴。请各位业主积极配合，自觉交清！如有业主不愿分摊安装费用，自愿放弃此次分离供水后的用水权利，请您签好表明自行解决用水、自愿放弃小区供水权利的协议！敬请各位业主自觉配合，积极参加利自己利邻居的大好事！！！祝各位业主安居乐业、阖家幸福！！！
         * bulletinDatetime : 2016-10-10T00:00:00
         * bulletinDeadline : 2016-10-30T00:00:00
         */

        private String bulletinID;
        private int communityID;
        private String communityName;
        private String bulletinTittle;
        private String bulletinContent;
        private String bulletinDatetime;
        private String bulletinDeadline;

        public String getBulletinID() {
            return bulletinID;
        }

        public void setBulletinID(String bulletinID) {
            this.bulletinID = bulletinID;
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

        public String getBulletinTittle() {
            return bulletinTittle;
        }

        public void setBulletinTittle(String bulletinTittle) {
            this.bulletinTittle = bulletinTittle;
        }

        public String getBulletinContent() {
            return bulletinContent;
        }

        public void setBulletinContent(String bulletinContent) {
            this.bulletinContent = bulletinContent;
        }

        public String getBulletinDatetime() {
            return bulletinDatetime;
        }

        public void setBulletinDatetime(String bulletinDatetime) {
            this.bulletinDatetime = bulletinDatetime;
        }

        public String getBulletinDeadline() {
            return bulletinDeadline;
        }

        public void setBulletinDeadline(String bulletinDeadline) {
            this.bulletinDeadline = bulletinDeadline;
        }
    }
}
