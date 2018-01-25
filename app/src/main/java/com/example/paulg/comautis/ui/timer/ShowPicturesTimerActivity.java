package com.example.paulg.comautis.ui.timer;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Database.RequestCallback;
import com.example.paulg.comautis.mvp.Model.Model;
import com.example.paulg.comautis.mvp.Model.Picture;
import com.example.paulg.comautis.mvp.Model.PictureUtils;
import com.example.paulg.comautis.ui.BaseActivity;
import com.example.paulg.comautis.ui.page.ChoosePageActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowPicturesTimerActivity extends BaseActivity implements TimerFragmentSetup.TimerListener, FragmentNavigator {

    private String mPageId;
    private String mPageName;
    private ArrayList<Picture> mListPictures = new ArrayList<>();

    private TimerFragmentTiny tinyFragment;
    private TimerFragmentSetup setupTimerFragment;
    private long mTime = 0;
    private boolean mIsStopped = true;

    public void initComponents(){
        setupTimerFragment = new TimerFragmentSetup();
        tinyFragment = new TimerFragmentTiny();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        if(getIntent() != null){
            if(getIntent().getExtras() != null) {
                mPageId = getIntent().getExtras().getString(ChoosePageActivity.EXTRA_PAGE_ID);
                mPageName = getIntent().getExtras().getString(ChoosePageActivity.EXTRA_PAGE_NAME);
            }
        }

        initComponents();
        loadPictures();
        getSupportActionBar().setTitle("Images de " + mPageName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        displayTinyFragment();
    }

    private void loadPictures(){
        mLocalDb.requestPictureFromPage(mPageId, new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListPictures = (ArrayList<Picture>) entities;
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        int pagePictureSize = mListPictures.size();
        for(int i = 0 ; i < pagePictureSize ; i++){
            Picture pic = mListPictures.get(i);
            pic.setBitmap(PictureUtils.getBitmapFromPath(pic.getPicturePath()));
        }
    }


    protected void displayTinyFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (tinyFragment.isAdded()) {
            fragmentTransaction.show(tinyFragment);
        } else {
            fragmentTransaction.addToBackStack("tinyFragmentBack");
            fragmentTransaction.add(R.id.my_tiny_timer, tinyFragment);
        }
        if (setupTimerFragment.isAdded()) {
            fragmentTransaction.hide(setupTimerFragment);
        }
        fragmentTransaction.commit();
        tinyFragment.setListImage(mListPictures);
    }

    protected void displaySetupTimerFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (setupTimerFragment.isAdded()) {
            fragmentTransaction.show(setupTimerFragment);
        } else {
            fragmentTransaction.addToBackStack("setupFragmentBack");
            fragmentTransaction.add(R.id.my_setup_timer, setupTimerFragment);
        }
        if (tinyFragment.isAdded()) {
            fragmentTransaction.hide(tinyFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void getTime(long time, boolean isStoped) {
        mIsStopped = isStoped;
        mTime = time;
        tinyFragment.refreshTime(mTime);
    }

    @Override
    public void showSetupTimerFragment() {
        displaySetupTimerFragment();
        getTime(mTime, mIsStopped);
    }

    @Override
    public void showTinyTimerFragment() {
        displayTinyFragment();
            //tinyFragment = (TimerFragmentTiny)getSupportFragmentManager().findFragmentById(R.id.my_placeholder);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.anim_slide_none, R.anim.anim_slide_down);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_none, R.anim.anim_slide_down);
    }
}
