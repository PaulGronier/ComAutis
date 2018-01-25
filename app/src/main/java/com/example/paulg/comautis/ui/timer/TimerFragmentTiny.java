package com.example.paulg.comautis.ui.timer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Model.Picture;
import com.example.paulg.comautis.ui.picture.RecyclerPictureAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Cervo on 18/01/2018.
 */

public class TimerFragmentTiny extends Fragment implements RecyclerPictureAdapter.OnClickListener {

    @BindView(R.id.imageCountdown) ImageView imageCountDownView;
    @BindView(R.id.list_picture) RecyclerView listPicturesView;

    private FragmentActivity listener;
    private TimerGraphic timerGraphic;
    private long mTimeCountInMilliSeconds;
    private RecyclerPictureAdapter mPictureAdapter;
    private ArrayList<Picture> mPictureList;


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
        initRecyclerView();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initComponent();
    }

    public void initComponent(){
        timerGraphic = new TimerGraphic();
        Bitmap b = timerGraphic.getInitialTimer();
        imageCountDownView.setImageBitmap(b);
    }

    public void initRecyclerView() {
        mPictureAdapter = new RecyclerPictureAdapter(getContext(), mPictureList, this);
        listPicturesView.setAdapter(mPictureAdapter);
        listPicturesView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listPicturesView.setVisibility(View.VISIBLE);
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
        //mTimeView.setText(String.valueOf(time));
        //startCountDownTimer();
        refreshTinyTimer(time);
    }

    public void setListImage(ArrayList<Picture> imageList) {
        this.mPictureList = imageList;
    }

    public void refreshTinyTimer (long milliSeconds) {
        if (milliSeconds != 0) {

            float tempsFixe = 3600f * 1000f;
            float sweepAngle = 360f - ((milliSeconds / tempsFixe) * 360f);

            Bitmap bitmap = ((BitmapDrawable) imageCountDownView.getDrawable()).getBitmap();
            bitmap = timerGraphic.redrawTimer(bitmap,sweepAngle);
            imageCountDownView.setImageBitmap(bitmap);
        }
    }


}
