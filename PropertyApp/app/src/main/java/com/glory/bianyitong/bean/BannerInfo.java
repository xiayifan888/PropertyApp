package com.glory.bianyitong.bean;

import java.util.List;

/**
 * Created by lucy on 2016/11/10.
 */
public class BannerInfo {

    /**
     * Success : true
     * ApiName : ImgList
     * Msg : 成功
     * ResultData : [{"Img_ID":1,"Img_Type":1,"Img_URL":"Update/Banner/1.jpg","Img_Link":"http://www.123.com"},{"Img_ID":1,"Img_Type":1,"Img_URL":"Update/Banner/1.jpg","Img_Link":"http://www.123.com"}]
     * RowCount : 10
     * ErrNum : 0
     */

    private boolean Success;
    private String ApiName;
    private String Msg;
    private int RowCount;
    private int ErrNum;
    /**
     * Img_ID : 1
     * Img_Type : 1
     * Img_URL : Update/Banner/1.jpg
     * Img_Link : http://www.123.com
     */

    private List<ResultDataBean> ResultData;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getApiName() {
        return ApiName;
    }

    public void setApiName(String ApiName) {
        this.ApiName = ApiName;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public int getRowCount() {
        return RowCount;
    }

    public void setRowCount(int RowCount) {
        this.RowCount = RowCount;
    }

    public int getErrNum() {
        return ErrNum;
    }

    public void setErrNum(int ErrNum) {
        this.ErrNum = ErrNum;
    }

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean {
        private int Img_ID;
        private int Img_Type;
        private String Img_URL;
        private String Img_Link;

        public int getImg_ID() {
            return Img_ID;
        }

        public void setImg_ID(int Img_ID) {
            this.Img_ID = Img_ID;
        }

        public int getImg_Type() {
            return Img_Type;
        }

        public void setImg_Type(int Img_Type) {
            this.Img_Type = Img_Type;
        }

        public String getImg_URL() {
            return Img_URL;
        }

        public void setImg_URL(String Img_URL) {
            this.Img_URL = Img_URL;
        }

        public String getImg_Link() {
            return Img_Link;
        }

        public void setImg_Link(String Img_Link) {
            this.Img_Link = Img_Link;
        }
    }
}
