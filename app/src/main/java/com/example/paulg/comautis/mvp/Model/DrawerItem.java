package com.example.paulg.comautis.mvp.Model;

public class DrawerItem {

    private String mName;
    private int mIconId;

    public DrawerItem(String name, int iconId) {
        this.mIconId = iconId;
        this.mName = name;
    }

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int iconId) {
        this.mIconId = iconId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
