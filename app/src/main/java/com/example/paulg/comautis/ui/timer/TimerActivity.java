package com.example.paulg.comautis.ui.timer;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.paulg.comautis.R;

public class TimerActivity extends AppCompatActivity implements TimerFragmentSetup.TimerInterface {

    private TimerFragmentTiny simpleCountdown;
    private TimerFragmentSetup detailsCountdown;
    private FragmentTransaction fManager;
    private CountDownTimer countDownTimer;
    private int mTime;

    public void iniComponents(){
        detailsCountdown = new TimerFragmentSetup();
        simpleCountdown = new TimerFragmentTiny();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        iniComponents();
        fManager = getSupportFragmentManager().beginTransaction();
        if(true){
            fManager.replace(R.id.fragmentSetup, detailsCountdown);
        }
//        else{
//            fManager.replace(R.id.fragmentTiny, simpleCountdown);
//        }
        fManager.commit();
//        detailsCountdown.getView().findViewById(R.id.imageViewStartStop).setOnClickListener(this);
    }

    @Override
    public void getTime(int time) {
        mTime = time;
    }
}
