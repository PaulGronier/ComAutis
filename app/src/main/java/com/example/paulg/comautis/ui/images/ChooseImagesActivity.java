package com.example.paulg.comautis.ui.images;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.ui.home.BaseActivity;

/**
 * Created by Cervo on 23/01/2018.
 */

public class ChooseImagesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pictures);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_show_pictures;
    }

    @Override
    public int getNavigationMenuItemId() {
        return R.id.action_folder;
    }

}
