package com.example.paulg.comautis.mvp.Model;

/**
 * Created by paulg on 24/01/2018.
 */

public class Page extends Model {
    private String name;
    private String mChildId;

    public Page(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChildId() {
        return mChildId;
    }

    public void setChildId(String childId) {
        mChildId = childId;
    }
}
