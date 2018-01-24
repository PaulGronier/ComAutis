package com.example.paulg.comautis.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


import com.example.paulg.comautis.R;
import com.example.paulg.comautis.ui.child.ChooseChildActivity;
import com.example.paulg.comautis.ui.images.ChooseImagesActivity;
import com.example.paulg.comautis.ui.page.ChoosePageActivity;

/**
 * Created by Cervo on 23/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pictures);

        navigationView = findViewById(R.id.bottom_navigation);
        //navigationView.setOnNavigationItemSelectedListener(this);

        initMenuListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavigationBarState();
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_folder) {
                startActivity(new Intent(this, ChooseImagesActivity.class));
            } else if (itemId == R.id.action_child) {
                startActivity(new Intent(this, ChooseChildActivity.class));
            }
            finish();
        }, 300);
        return true;
    }

    private void updateNavigationBarState(){
        int actionId = getNavigationMenuItemId();
        selectBottomNavigationBarItem(actionId);
    }

    void selectBottomNavigationBarItem(int itemId) {
        Menu menu = navigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem item = menu.getItem(i);
            boolean shouldBeChecked = item.getItemId() == itemId;
            if (shouldBeChecked) {
                item.setChecked(true);
                break;
            }
        }
    }

    public void initMenuListener() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_folder:
                        Intent intentName = new Intent(getBaseContext(), ChooseImagesActivity.class);
                        startActivity(intentName);
                        return true;
                    case R.id.action_child:
                        Intent intentChild = new Intent(getBaseContext(), ChooseChildActivity.class);
                        startActivity(intentChild);
                        return true;
                }
                return true;
            }
        });
    }

    public abstract int getContentViewId();

    public abstract int getNavigationMenuItemId();

}
