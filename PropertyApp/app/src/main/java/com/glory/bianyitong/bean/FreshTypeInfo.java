package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/1/10.
 */
public class FreshTypeInfo {

    /**
     * tableColumnPermission : []
     * list_FreshType : [{"freshTypeID":3,"freshTypeName":"水果","freshTypeLeaf":3,"communityID":0},{"freshTypeID":6,"freshTypeName":"蔬菜","freshTypeLeaf":6,"communityID":0},{"freshTypeID":7,"freshTypeName":"水产","freshTypeLeaf":7,"communityID":0},{"freshTypeID":8,"freshTypeName":"肉类","freshTypeLeaf":8,"communityID":0},{"freshTypeID":12,"freshTypeName":"苹果","freshTypeLeaf":3,"communityID":0},{"freshTypeID":13,"freshTypeName":"奇异果","freshTypeLeaf":3,"communityID":0},{"freshTypeID":14,"freshTypeName":"石榴","freshTypeLeaf":3,"communityID":0},{"freshTypeID":15,"freshTypeName":"哈密瓜","freshTypeLeaf":3,"communityID":0},{"freshTypeID":16,"freshTypeName":"柠檬","freshTypeLeaf":3,"communityID":0},{"freshTypeID":17,"freshTypeName":"西柚","freshTypeLeaf":3,"communityID":0},{"freshTypeID":18,"freshTypeName":"香梨","freshTypeLeaf":3,"communityID":0},{"freshTypeID":19,"freshTypeName":"桔子","freshTypeLeaf":3,"communityID":0},{"freshTypeID":20,"freshTypeName":"白菜","freshTypeLeaf":6,"communityID":0},{"freshTypeID":21,"freshTypeName":"茄果","freshTypeLeaf":6,"communityID":0},{"freshTypeID":22,"freshTypeName":"葱蒜","freshTypeLeaf":6,"communityID":0},{"freshTypeID":23,"freshTypeName":"豆类","freshTypeLeaf":6,"communityID":0},{"freshTypeID":24,"freshTypeName":"薯类","freshTypeLeaf":6,"communityID":0},{"freshTypeID":25,"freshTypeName":"菌类","freshTypeLeaf":6,"communityID":0},{"freshTypeID":26,"freshTypeName":"姜芥","freshTypeLeaf":6,"communityID":0},{"freshTypeID":27,"freshTypeName":"调料","freshTypeLeaf":6,"communityID":0},{"freshTypeID":28,"freshTypeName":"虾","freshTypeLeaf":7,"communityID":0},{"freshTypeID":29,"freshTypeName":"鱼","freshTypeLeaf":7,"communityID":0},{"freshTypeID":30,"freshTypeName":"蟹","freshTypeLeaf":7,"communityID":0},{"freshTypeID":31,"freshTypeName":"花甲","freshTypeLeaf":7,"communityID":0},{"freshTypeID":32,"freshTypeName":"扇贝","freshTypeLeaf":7,"communityID":0},{"freshTypeID":33,"freshTypeName":"生蚝","freshTypeLeaf":7,"communityID":0},{"freshTypeID":34,"freshTypeName":"海蜇","freshTypeLeaf":7,"communityID":0},{"freshTypeID":35,"freshTypeName":"鱿鱼","freshTypeLeaf":7,"communityID":0},{"freshTypeID":36,"freshTypeName":"牛肉","freshTypeLeaf":8,"communityID":0},{"freshTypeID":37,"freshTypeName":"牛排","freshTypeLeaf":8,"communityID":0},{"freshTypeID":38,"freshTypeName":"澳洲","freshTypeLeaf":8,"communityID":0},{"freshTypeID":39,"freshTypeName":"联豪","freshTypeLeaf":8,"communityID":0},{"freshTypeID":40,"freshTypeName":"冰鲜","freshTypeLeaf":8,"communityID":0},{"freshTypeID":41,"freshTypeName":"鲜猪肉","freshTypeLeaf":8,"communityID":0},{"freshTypeID":42,"freshTypeName":"鸡鸭","freshTypeLeaf":8,"communityID":0},{"freshTypeID":43,"freshTypeName":"羊肉","freshTypeLeaf":8,"communityID":0}]
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
    private List<ListFreshTypeBean> list_FreshType;

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

    public List<ListFreshTypeBean> getList_FreshType() {
        return list_FreshType;
    }

    public void setList_FreshType(List<ListFreshTypeBean> list_FreshType) {
        this.list_FreshType = list_FreshType;
    }

    public static class ListFreshTypeBean {
        /**
         * freshTypeID : 3
         * freshTypeName : 水果
         * freshTypeLeaf : 3
         * communityID : 0
         */

        private int freshTypeID;
        private String freshTypeName;
        private int freshTypeLeaf;
        private int communityID;

        public int getFreshTypeID() {
            return freshTypeID;
        }

        public void setFreshTypeID(int freshTypeID) {
            this.freshTypeID = freshTypeID;
        }

        public String getFreshTypeName() {
            return freshTypeName;
        }

        public void setFreshTypeName(String freshTypeName) {
            this.freshTypeName = freshTypeName;
        }

        public int getFreshTypeLeaf() {
            return freshTypeLeaf;
        }

        public void setFreshTypeLeaf(int freshTypeLeaf) {
            this.freshTypeLeaf = freshTypeLeaf;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }
    }
}
