package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2017/2/13.
 */
public class FreashInfo {

    /**
     * tableColumnPermission : []
     * list_Fresh : [{"freshID":54,"freshTypeLeaf":6,"freshTypeID":20,"freshTypeName":"白菜","freshName":"甘蓝","freshPrice":3,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/cabbage.jpg","weight":500,"packingType":"保鲜膜包装","brandID":1,"brandName":"青岛伊家鲜","brandTEL":"13576584888","originID":1,"originName":"广东","shelfLife":"12个月","tag":"蔬菜","freshContent":"小汤山 三宝白菜 约750g 自营蔬菜","communityID":0,"nutritiveValue":null,"listfreshPicture":[{"freshPictureID":221,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/1.jpg"},{"freshPictureID":222,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/2.jpg"},{"freshPictureID":223,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/3.jpg"},{"freshPictureID":224,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/4.jpg"},{"freshPictureID":225,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/5.jpg"}],"featuredType":"\u0001","featuredID":4,"freshUrl":"https://byt.bytsz.com.cn/Fresh/FreshDetail.html?id=54"},{"freshID":55,"freshTypeLeaf":6,"freshTypeID":25,"freshTypeName":"菌类","freshName":"花菇","freshPrice":7,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/Mushroom.jpg","weight":500,"packingType":"保鲜膜包装","brandID":1,"brandName":"青岛伊家鲜","brandTEL":"15966897452","originID":1,"originName":"广东","shelfLife":"12个月","tag":"蔬菜","freshContent":"金佰伦 长茄子 2个 约500g 京东自营新鲜","communityID":0,"nutritiveValue":null,"listfreshPicture":[{"freshPictureID":226,"freshID":55,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/蘑菇/1.jpg"},{"freshPictureID":227,"freshID":55,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/蘑菇/2.jpg"},{"freshPictureID":228,"freshID":55,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/蘑菇/3.jpg"},{"freshPictureID":229,"freshID":55,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/蘑菇/4.jpg"},{"freshPictureID":230,"freshID":55,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/蘑菇/5.jpg"}],"featuredType":"\u0001","featuredID":5,"freshUrl":"https://byt.bytsz.com.cn/Fresh/FreshDetail.html?id=55"},{"freshID":56,"freshTypeLeaf":6,"freshTypeID":22,"freshTypeName":"葱蒜","freshName":"甜葱","freshPrice":1,"freshPicture":"https://byt.bytsz.com.cn/images/Fresh/onion.jpg","weight":500,"packingType":"保鲜膜包装","brandID":1,"brandName":"青岛伊家鲜","brandTEL":"17877453255","originID":1,"originName":"广东","shelfLife":"12个月","tag":"蔬菜","freshContent":"家美舒达 山东大葱 约700g 4-6根 自营蔬菜 ","communityID":0,"nutritiveValue":null,"listfreshPicture":[{"freshPictureID":231,"freshID":56,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/大葱/1.jpg"},{"freshPictureID":232,"freshID":56,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/大葱/2.jpg"},{"freshPictureID":233,"freshID":56,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/大葱/3.jpg"},{"freshPictureID":234,"freshID":56,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/大葱/4.jpg"},{"freshPictureID":235,"freshID":56,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/大葱/5.jpg"}],"featuredType":"\u0001","featuredID":6,"freshUrl":"https://byt.bytsz.com.cn/Fresh/FreshDetail.html?id=56"}]
     * tags : null
     * version : null
     * datetime : null
     * accesstoken : null
     * statuscode : 1
     * statusmessage : 消息处理成功
     * alertmessage : 消息处理成功
     * totalrownum : null
     * totalpagenum : 5
     * nowpagenum : null
     * pagerownum : 10
     */

    private Object tags;
    private Object version;
    private Object datetime;
    private Object accesstoken;
    private int statuscode;
    private String statusmessage;
    private String alertmessage;
    private Object totalrownum;
    private String totalpagenum;
    private Object nowpagenum;
    private String pagerownum;
    private List<?> tableColumnPermission;
    private List<ListFreshBean> list_Fresh;

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
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

    public String getTotalpagenum() {
        return totalpagenum;
    }

    public void setTotalpagenum(String totalpagenum) {
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

    public List<ListFreshBean> getList_Fresh() {
        return list_Fresh;
    }

    public void setList_Fresh(List<ListFreshBean> list_Fresh) {
        this.list_Fresh = list_Fresh;
    }

    public static class ListFreshBean {
        /**
         * freshID : 54
         * freshTypeLeaf : 6
         * freshTypeID : 20
         * freshTypeName : 白菜
         * freshName : 甘蓝
         * freshPrice : 3.0
         * freshPicture : https://byt.bytsz.com.cn/images/Fresh/cabbage.jpg
         * weight : 500.0
         * packingType : 保鲜膜包装
         * brandID : 1
         * brandName : 青岛伊家鲜
         * brandTEL : 13576584888
         * originID : 1
         * originName : 广东
         * shelfLife : 12个月
         * tag : 蔬菜
         * freshContent : 小汤山 三宝白菜 约750g 自营蔬菜
         * communityID : 0
         * nutritiveValue : null
         * listfreshPicture : [{"freshPictureID":221,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/1.jpg"},{"freshPictureID":222,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/2.jpg"},{"freshPictureID":223,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/3.jpg"},{"freshPictureID":224,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/4.jpg"},{"freshPictureID":225,"freshID":54,"picturePath":"https://byt.bytsz.com.cn/images/Fresh/白菜/5.jpg"}]
         * featuredType : 
         * featuredID : 4
         * freshUrl : https://byt.bytsz.com.cn/Fresh/FreshDetail.html?id=54
         */

        private int freshID;
        private int freshTypeLeaf;
        private int freshTypeID;
        private String freshTypeName;
        private String freshName;
        private double freshPrice;
        private String freshPicture;
        private double weight;
        private String packingType;
        private int brandID;
        private String brandName;
        private String brandTEL;
        private int originID;
        private String originName;
        private String shelfLife;
        private String tag;
        private String freshContent;
        private int communityID;
        private Object nutritiveValue;
        private String featuredType;
        private int featuredID;
        private String freshUrl;
        private List<ListfreshPictureBean> listfreshPicture;

        public int getFreshID() {
            return freshID;
        }

        public void setFreshID(int freshID) {
            this.freshID = freshID;
        }

        public int getFreshTypeLeaf() {
            return freshTypeLeaf;
        }

        public void setFreshTypeLeaf(int freshTypeLeaf) {
            this.freshTypeLeaf = freshTypeLeaf;
        }

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

        public String getFreshName() {
            return freshName;
        }

        public void setFreshName(String freshName) {
            this.freshName = freshName;
        }

        public double getFreshPrice() {
            return freshPrice;
        }

        public void setFreshPrice(double freshPrice) {
            this.freshPrice = freshPrice;
        }

        public String getFreshPicture() {
            return freshPicture;
        }

        public void setFreshPicture(String freshPicture) {
            this.freshPicture = freshPicture;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getPackingType() {
            return packingType;
        }

        public void setPackingType(String packingType) {
            this.packingType = packingType;
        }

        public int getBrandID() {
            return brandID;
        }

        public void setBrandID(int brandID) {
            this.brandID = brandID;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandTEL() {
            return brandTEL;
        }

        public void setBrandTEL(String brandTEL) {
            this.brandTEL = brandTEL;
        }

        public int getOriginID() {
            return originID;
        }

        public void setOriginID(int originID) {
            this.originID = originID;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public String getShelfLife() {
            return shelfLife;
        }

        public void setShelfLife(String shelfLife) {
            this.shelfLife = shelfLife;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getFreshContent() {
            return freshContent;
        }

        public void setFreshContent(String freshContent) {
            this.freshContent = freshContent;
        }

        public int getCommunityID() {
            return communityID;
        }

        public void setCommunityID(int communityID) {
            this.communityID = communityID;
        }

        public Object getNutritiveValue() {
            return nutritiveValue;
        }

        public void setNutritiveValue(Object nutritiveValue) {
            this.nutritiveValue = nutritiveValue;
        }

        public String getFeaturedType() {
            return featuredType;
        }

        public void setFeaturedType(String featuredType) {
            this.featuredType = featuredType;
        }

        public int getFeaturedID() {
            return featuredID;
        }

        public void setFeaturedID(int featuredID) {
            this.featuredID = featuredID;
        }

        public String getFreshUrl() {
            return freshUrl;
        }

        public void setFreshUrl(String freshUrl) {
            this.freshUrl = freshUrl;
        }

        public List<ListfreshPictureBean> getListfreshPicture() {
            return listfreshPicture;
        }

        public void setListfreshPicture(List<ListfreshPictureBean> listfreshPicture) {
            this.listfreshPicture = listfreshPicture;
        }

        public static class ListfreshPictureBean {
            /**
             * freshPictureID : 221
             * freshID : 54
             * picturePath : https://byt.bytsz.com.cn/images/Fresh/白菜/1.jpg
             */

            private int freshPictureID;
            private int freshID;
            private String picturePath;

            public int getFreshPictureID() {
                return freshPictureID;
            }

            public void setFreshPictureID(int freshPictureID) {
                this.freshPictureID = freshPictureID;
            }

            public int getFreshID() {
                return freshID;
            }

            public void setFreshID(int freshID) {
                this.freshID = freshID;
            }

            public String getPicturePath() {
                return picturePath;
            }

            public void setPicturePath(String picturePath) {
                this.picturePath = picturePath;
            }
        }
    }
}
