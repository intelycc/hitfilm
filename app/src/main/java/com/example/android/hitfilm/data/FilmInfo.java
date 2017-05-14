package com.example.android.hitfilm.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by intelycc on 17/4/19.
 */

public class FilmInfo implements Serializable{
    private static final long serialVersionUID=1L;
    private String posterPath;    //缩略图
    private Boolean adult;
    private String releaseDate;  //上映日期
    private String overView;  //简介
    private double voteAverage;  //评分
    private String originalTitle;  //片名

    public String getPosterPath() {
        return posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverView() {
        return overView;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.posterPath);
//        dest.writeValue(this.adult);
//        dest.writeString(this.releaseDate);
//        dest.writeString(this.overView);
//        dest.writeDouble(this.voteAverage);
//        dest.writeString(this.originalTitle);
//    }

    public FilmInfo() {
    }

//    protected FilmInfo(Parcel in) {
//        this.posterPath = in.readString();
//        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
//        this.releaseDate = in.readString();
//        this.overView = in.readString();
//        this.voteAverage = in.readDouble();
//        this.originalTitle = in.readString();
//    }
//
//    public static final Creator<FilmInfo> CREATOR = new Creator<FilmInfo>() {
//        @Override
//        public FilmInfo createFromParcel(Parcel source) {
//            return new FilmInfo(source);
//        }
//
//        @Override
//        public FilmInfo[] newArray(int size) {
//            return new FilmInfo[size];
//        }
//    };
}
