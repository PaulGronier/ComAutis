package com.example.paulg.comautis.ui.home;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.mvp.Database.LocalDataBase;
import com.example.paulg.comautis.mvp.mvp.Database.SQLDataBase;

public class MainActivity extends AppCompatActivity  {
    private ListView navigationView;
    private DrawerLayout fullLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private int selectedNavItemId;
    public SQLDataBase myDB;
    public LocalDataBase mLocalDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_child_add);

        SQLiteDatabase mComAutisDB = openOrCreateDatabase("ComAutisDB",MODE_PRIVATE,null);

        myDB = new SQLDataBase(getApplicationContext());
        myDB.onUpgrade(mComAutisDB, mComAutisDB.getVersion(),myDB.getVERSION());
        myDB.onCreate(mComAutisDB);

        mLocalDb = new LocalDataBase(mComAutisDB,null);
    }


}
