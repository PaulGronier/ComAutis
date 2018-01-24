package com.example.paulg.comautis.ui.timer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulg.comautis.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Cervo on 18/01/2018.
 */

public class TimerFragmentTiny extends Fragment {


    @BindView(R.id.imageCountdown) ImageView image;
    @BindView(R.id.time) TextView mTimeView;

    private FragmentActivity listener;
    private TimerGraphic timerGraphic;
    private long mTimeCountInMilliSeconds;
    private Handler mHandler;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer_tiny, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initComponent();
    }

    public void initComponent(){
        timerGraphic = new TimerGraphic();
        Bitmap b = timerGraphic.getInitialTimer();
        image.setImageBitmap(b);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.imageCountdown)
    public void onImageClick() {
        ((FragmentNavigator)getActivity()).showSetupTimerFragment();
    }

    public void refreshTime(long time) {
        mTimeCountInMilliSeconds = time;
        mTimeView.setText(String.valueOf(time));
        //startCountDownTimer();
        refreshTinyTimer(time);
    }

    public void refreshTinyTimer (long milliSeconds) {
        if (milliSeconds != 0) {
            //long startAngle = milliSeconds/1000*360/3600;
            //float floatStartAngle = (float)startAngle;

            float tempsFixe = 3600f * 1000f;
            float sweepAngle = 360f - ((milliSeconds / tempsFixe) * 360f);

            Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
            //long tempstotal = milliSeconds /1000;
            //double angleforsec = 360.00 / tempstotal / 2.00;
            bitmap = timerGraphic.redrawTimer(bitmap,sweepAngle);
            image.setImageBitmap(bitmap);
        }
    }

    private void startCountDownTimer() {

        CountDownTimer countDownTimer = new CountDownTimer(mTimeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                long tempstotal = mTimeCountInMilliSeconds /1000;
                double angleforsec = 360.00 / tempstotal / 2.00;
                bitmap = timerGraphic.redrawTimer(bitmap,(float)angleforsec);
                image.setImageBitmap(bitmap);

                //Log.d("INFO", "angle : " + angleforsec);

            }

            @Override
            public void onFinish() {
                // NOTHING
            }

        }.start();
        countDownTimer.start();
    }

}
