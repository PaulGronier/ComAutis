package com.example.paulg.comautis.mvp.Model;

/**
 * Created by iem on 16/01/2018.
 */

public class Child extends Model {
    private String mName;
    private String mPicturePath;
    private int mIsFavorite;
    private String mFolderId;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPicturePath() {
        return mPicturePath;
    }

    public void setPicturePath(String picturePath) {
        this.mPicturePath = picturePath;
    }

    public int isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.mIsFavorite = isFavorite;
    }

    public String getFolderId() {
        return mFolderId;
    }

    public void setFolderId(String folderId) {
        this.mFolderId = folderId;
    }
}
