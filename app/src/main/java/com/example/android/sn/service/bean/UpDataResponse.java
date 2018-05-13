package com.example.android.sn.service.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 类描述：
 * 作者： YinJin
 * 创建时间：2018/5/13.0:41
 */
public class UpDataResponse implements Parcelable {


    /**
     * data : {"appid":"cs999","appname":"安卓测试","is_jump":"1","jump_url":"https://app.yhzs168.com/193dd.cc/dayingjiacaizy.apk","status":"2"}
     * type : 200
     */

    private DataBean data;
    private String type;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBean implements Parcelable {
        /**
         * appid : cs999
         * appname : 安卓测试
         * is_jump : 1
         * jump_url : https://app.yhzs168.com/193dd.cc/dayingjiacaizy.apk
         * status : 2
         */

        private String appid;
        private String appname;
        private String is_jump;
        private String jump_url;
        private String status;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getAppname() {
            return appname;
        }

        public void setAppname(String appname) {
            this.appname = appname;
        }

        public String getIs_jump() {
            return is_jump;
        }

        public void setIs_jump(String is_jump) {
            this.is_jump = is_jump;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.appid);
            dest.writeString(this.appname);
            dest.writeString(this.is_jump);
            dest.writeString(this.jump_url);
            dest.writeString(this.status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.appid = in.readString();
            this.appname = in.readString();
            this.is_jump = in.readString();
            this.jump_url = in.readString();
            this.status = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.type);
    }

    public UpDataResponse() {
    }

    protected UpDataResponse(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.type = in.readString();
    }

    public static final Parcelable.Creator<UpDataResponse> CREATOR = new Parcelable.Creator<UpDataResponse>() {
        @Override
        public UpDataResponse createFromParcel(Parcel source) {
            return new UpDataResponse(source);
        }

        @Override
        public UpDataResponse[] newArray(int size) {
            return new UpDataResponse[size];
        }
    };
}
