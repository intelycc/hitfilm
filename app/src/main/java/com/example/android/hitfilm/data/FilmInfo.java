package com.example.android.hitfilm.data;

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
}
