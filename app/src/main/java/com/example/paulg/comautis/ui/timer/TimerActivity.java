package com.example.paulg.comautis.ui.timer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.paulg.comautis.R;

public class TimerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_timer);
//        if (savedInstanceState == null) {
//            // Let's first dynamically add a fragment into a frame container
//            getSupportFragmentManager().beginTransaction().
//                    replace(R.id.fragmentID, new TimerFragment(), "SOMETAG").
//                    commit();
//            // Now later we can lookup the fragment by tag
//            TimerFragment fragmentDemo = (TimerFragment)
//                    getSupportFragmentManager().findFragmentByTag("SOMETAG");
//        }
        Fragment TimerFragment = null;
        TimerFragment = new TimerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentID, TimerFragment).commit();
    }

}
