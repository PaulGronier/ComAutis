package com.example.paulg.comautis.ui;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.paulg.comautis.mvp.Database.LocalDataBase;
import com.example.paulg.comautis.mvp.Database.SQLDataBase;

/**
 * Created by paulg on 24/01/2018.
 */

public class BaseActivity extends AppCompatActivity {

    protected SQLDataBase myDB;
    protected LocalDataBase mLocalDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase mComAutisDB = openOrCreateDatabase("ComAutisDB",MODE_PRIVATE,null);

        myDB = new SQLDataBase(getApplicationContext());
        //myDB.onUpgrade(mComAutisDB, mComAutisDB.getVersion(),myDB.getVERSION());
        myDB.onCreate(mComAutisDB);

        mLocalDb = new LocalDataBase(mComAutisDB,null);
    }
}
