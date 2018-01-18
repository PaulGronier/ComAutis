package com.example.paulg.comautis.ui.timer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulg.comautis.R;

import java.util.concurrent.TimeUnit;


/**
 * Created by Cervo on 18/01/2018.
 */

public class TimerFragmentTiny extends Fragment implements View.OnClickListener {

    private long timeCountInMilliSeconds = 1 * 60000;
    private ProgressBar progressBarCircle;
    private Button startCountdownButton;
    private TimerFragment.TimerStatus timerStatus = TimerFragment.TimerStatus.STOPPED;
    private CountDownTimer countDownTimer;
    private TextView mTextField;

    public enum TimerStatus {
        STARTED,
        STOPPED
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_timer_tiny, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initViews();
//        initListeners();
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    /**
     * implemented method to listen clicks
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        startCountDownTimer();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * method to initialize the views
     */
    private void initViews() {
        mTextField = (TextView) getView().findViewById(R.id.timerText);
        progressBarCircle = (ProgressBar) getView().findViewById(R.id.barTimer);
        startCountdownButton = (Button) getView().findViewById(R.id.myButton);
        startCountdownButton.setOnClickListener(this);
    }

//    /**
//     * method to start and stop count down timer
//     */
//    private void startStop() {
//        if (timerStatus == TimerFragment.TimerStatus.STOPPED) {
//
//            // call to initialize the timer values
////            setTimerValues();
//            // call to initialize the progress bar values
//            setProgressBarValues();
//            // showing the reset icon
////            imageViewReset.setVisibility(View.VISIBLE);
//            // changing play icon to stop icon
////            imageViewStartStop.setImageResource(R.drawable.ic_stop);
//            // making edit text not editable
////            editTextMinute.setEnabled(false);
//            // changing the timer status to started
//            timerStatus = TimerFragment.TimerStatus.STARTED;
//            // call to start the count down timer
//            startCountDownTimer();
//
//        } else {
//
//            // hiding the reset icon
////            imageViewReset.setVisibility(View.GONE);
//            // changing stop icon to start icon
////            imageViewStartStop.setImageResource(R.drawable.ic_start);
//            // making edit text editable
////            editTextMinute.setEnabled(true);
//            // changing the timer status to stopped
//            timerStatus = TimerFragment.TimerStatus.STOPPED;
//            stopCountDownTimer();
//
//        }
//
//    }
//
//    private void setTimerValues() {
//        int time = 1;
//        timeCountInMilliSeconds = time * 60 * 1000;
//    }
//
//    /**
//     * method to start count down timer
//     */
    private void startCountDownTimer() {
        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                mTextField.setText(hmsTimeFormatter(millisUntilFinished));
                Drawable circle  = ContextCompat.getDrawable(getActivity(), R.drawable.drawable_circle_full);
                circle.set
                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();
    }


    /**
     * method to set circular progress bar values
     */
//    private void setProgressBarValues() {
//        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
//        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
//    }

    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return HH:mm:ss time formatted string
     */
    private String hmsTimeFormatter(long milliSeconds) {

        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));

        return hms;


    }
}
