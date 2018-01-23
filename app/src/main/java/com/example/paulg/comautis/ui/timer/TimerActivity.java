package com.example.paulg.comautis.ui.timer;

import android.os.CountDownTimer;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.paulg.comautis.R;

public class TimerActivity extends AppCompatActivity implements TimerFragmentSetup.TimerListener, FragmentNavigator {

    private TimerFragmentTiny tinyFragment;
    private TimerFragmentSetup setupTimerFragment;
    private CountDownTimer countDownTimer;
    private long mTime = 0;

    public void initComponents(){
        setupTimerFragment = new TimerFragmentSetup();
        tinyFragment = new TimerFragmentTiny();
        getTime(mTime);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initComponents();
        displayTinyFragment();
    }

    protected void displayTinyFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (tinyFragment.isAdded()) {
            fragmentTransaction.show(tinyFragment);
        } else {
            fragmentTransaction.addToBackStack("tinyFragmentBack");
            fragmentTransaction.add(R.id.my_placeholder, tinyFragment);
        }
        if (setupTimerFragment.isAdded()) {
            fragmentTransaction.hide(setupTimerFragment);
        }

        fragmentTransaction.commit();

    }

    protected void displaySetupTimerFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (setupTimerFragment.isAdded()) {
            fragmentTransaction.show(setupTimerFragment);
        } else {
            fragmentTransaction.add(R.id.my_placeholder, setupTimerFragment);
        }
        if (tinyFragment.isAdded()) {
            fragmentTransaction.hide(tinyFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void getTime(long time) {
        mTime = time;
    }

    @Override
    public void showSetupTimerFragment() {
        displaySetupTimerFragment();
    }

    @Override
    public void showTinyTimerFragment() {
        displayTinyFragment();
            //tinyFragment = (TimerFragmentTiny)getSupportFragmentManager().findFragmentById(R.id.my_placeholder);
            tinyFragment.refreshTime(mTime);
    }
}
