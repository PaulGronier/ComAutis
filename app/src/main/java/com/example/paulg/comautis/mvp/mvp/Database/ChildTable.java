package com.example.paulg.comautis.mvp.mvp.Database;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.paulg.comautis.mvp.mvp.Model.Child;


/**
 * Created by alexislp on 06/01/16.
 */
public class ChildTable extends AbstractTable<Child> {

    /**
     * The name of the table
     */
    public static final String TABLE_NAME = "child";

    /**
     * Represents the unique Id of a {@link Child}
     */
    public static final String KEY_ID = "id";

    /**
     * Represents the name of a {@link Child}
     */
    public static final String KEY_NAME = "name";

    /**
     * Represents the path for the picture of a {@link Child}
     */
    public static final String KEY_PICTURE_PATH = "picture_path";

    /**
     * To know the child was a favorite
     */
    public static final String KEY_IS_FAVORITE = "is_favorite";

    /**
     * Represents the unique Id of a {@link Folder}
     */
    public static final String KEY_FOLDER = "id_folder";


    /**
     * The creation SQLite command of {@link Child}
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            KEY_ID + " integer primary key autoincrement," +
            KEY_NAME + TYPE_TEXT + ", " +
            KEY_PICTURE_PATH + TYPE_TEXT + ", " +
            KEY_IS_FAVORITE + TYPE_BOOLEAN +  " DEFAULT 0 , " +
            KEY_FOLDER + TYPE_SMALLTEXT + ")";

    @Override
    public ContentValues getContentValues(Child object) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, object.getId());
        contentValues.put(KEY_NAME, object.getName());
        contentValues.put(KEY_PICTURE_PATH, object.getPicturePath());
        contentValues.put(KEY_IS_FAVORITE, object.isFavorite());
        contentValues.put(KEY_FOLDER, object.getFolderId());
        return contentValues;
    }

    @Override
    public Child fromContentValues(ContentValues contentValues) {
        return null;
    }

    @Override
    public Child fromCursor(Cursor cursor) {
        Child child = new Child();
        child.setId(cursor.getString(cursor.getColumnIndex(ChildTable.KEY_ID)));
        child.setName(cursor.getString(cursor.getColumnIndex(ChildTable.KEY_NAME)));
        child.setPicturePath(cursor.getString(cursor.getColumnIndex(ChildTable.KEY_PICTURE_PATH)));
        child.setIsFavorite(cursor.getInt(cursor.getColumnIndex(ChildTable.KEY_IS_FAVORITE)));
        child.setFolderId(cursor.getString(cursor.getColumnIndex(ChildTable.KEY_FOLDER)));
        return child;
    }
}
