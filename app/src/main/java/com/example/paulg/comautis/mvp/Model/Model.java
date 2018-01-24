package com.example.paulg.comautis.mvp.Model;

/**
 * Created by iem on 16/01/2018.
 */

public class Model {

    /**
     * Unique identifier
     */
    String mId;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    /**
     * Compare if an object define the same entity
     *
     * @param o An object
     * @return true if the object define the same entity
     */
    @Override
    public boolean equals(Object o) {
        // Same memory address, equal
        if (this == o) return true;
        //Null object or different classes, not equal
        if (o == null || getClass() != o.getClass()) return false;
        // If same ids, equal,
        // Else, not equal
        return ((Model) o).getId() != null && ((Model) o).getId().equals(mId);
    }

    public static class Page extends Model {
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
}
