package com.example.paulg.comautis.mvp.page;

/**
 * Created by paulg on 17/01/2018.
 */


import com.example.paulg.comautis.mvp.Model.Model;

public class Page extends Model {
    private String title;
    private int mIsFavorite;
    private String mChildId;

    public Page (String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.mIsFavorite = isFavorite;
    }

    public String getChildId() {
        return mChildId;
    }

    public void setChildId(String childId) {
        mChildId = childId;
    }
}
