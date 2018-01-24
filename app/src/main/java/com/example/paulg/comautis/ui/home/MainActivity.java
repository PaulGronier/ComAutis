package com.example.paulg.comautis.ui.home;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Database.LocalDataBase;
import com.example.paulg.comautis.mvp.Database.SQLDataBase;
import com.example.paulg.comautis.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    private ListView navigationView;
    private DrawerLayout fullLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private int selectedNavItemId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_child_add);

    }


}
