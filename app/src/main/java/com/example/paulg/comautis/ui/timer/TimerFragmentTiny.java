package com.example.paulg.comautis.ui.timer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.paulg.comautis.R;



/**
 * Created by Cervo on 18/01/2018.
 */

public class TimerFragmentTiny extends Fragment implements View.OnClickListener, TimerFragmentSetup.TimerInterface {

    private long timeCountInMilliSeconds = 1 * 60000;
    private ImageView image;
    private Canvas actualCanvas;

    @Override
    public void getTime(int time) {

    }

    public enum TimerStatus {
        STARTED,
        STOPPED
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer_tiny, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initViews();
        initComponent();
    }

    /**
     * implemented method to listen clicks
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        refreshFragment(0);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void initViews(){
        image = (ImageView) getActivity().findViewById(R.id.imageCountdown);
        image.setOnClickListener(this);
    }

    public void initComponent(){
        Bitmap b = Bitmap.createBitmap(1200, 1200, Bitmap.Config.ARGB_8888);
        actualCanvas = new Canvas(b);

        int radius = 100;   // centre
        float startAngle = -90f;   // angle de départ
        float sweepAngle = startAngle-80;   // angle d'ouverture

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(37, 181, 215));
        paint.setStyle(Paint.Style.FILL);

        // dessin arc externe
        RectF oval = new RectF(0, 0, actualCanvas.getWidth(), actualCanvas.getHeight());
        actualCanvas.drawArc(oval, startAngle, sweepAngle, true, paint);

        // dessin arc interne
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        oval = new RectF((actualCanvas.getWidth() / 2) - radius, (actualCanvas.getHeight() / 2) - radius, (actualCanvas.getWidth() / 2) + radius, (actualCanvas.getHeight() / 2) + radius);
        actualCanvas.drawArc(oval, startAngle, sweepAngle, true, paint);
        image.setImageBitmap(b);
        // image.setBackgroundColor(Color.RED);
    }

    public void refreshFragment(long time) {
        Log.d("TAG", String.valueOf(time));
//        Bitmap b = Bitmap.createBitmap(1200, 1200, Bitmap.Config.ARGB_8888);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setColor(Color.rgb(37, 99, 50));
//        paint.setStyle(Paint.Style.FILL);
//        RectF oval = new RectF(0, 0, actualCanvas.getWidth(), actualCanvas.getHeight());
//        oval = new RectF((actualCanvas.getWidth() / 2) - 100, (actualCanvas.getHeight() / 2) - 100, (actualCanvas.getWidth() / 2) + 100, (actualCanvas.getHeight() / 2) + 100);
//        actualCanvas.drawArc(oval, 90, 120, true, paint);
//        image.setImageBitmap(b);
    }

}
