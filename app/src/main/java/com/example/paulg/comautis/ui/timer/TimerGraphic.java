package com.example.paulg.comautis.ui.timer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Cervo on 23/01/2018.
 */

public class TimerGraphic {

    private float startAngle;
    private static final float angleForSec = 0.1f;//Angle de départ
    private static final int radius = 100;   // centre
    private int iterator = 0;
    private int left;
    private int right;
    private int bottom;
    private int top;
    private Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;

    public TimerGraphic(){
        paint = new Paint();
        canvas = new Canvas();
        bitmap = Bitmap.createBitmap(1200, 1200, Bitmap.Config.ARGB_8888);
    }

    public Bitmap getInitialTimer(){
        Canvas canvas = new Canvas(bitmap);

        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(37, 181, 215));
        paint.setStyle(Paint.Style.FILL);

        // dessin arc externe
        RectF oval = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawArc(oval, -90f, 360, true, paint);

        // dessin arc interne
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        int left = (canvas.getWidth() / 2) - radius;
        int top = (canvas.getHeight() / 2) - radius;
        int right = (canvas.getWidth() / 2) + radius;
        int bottom = (canvas.getHeight() / 2) + radius;
        oval = new RectF(left, top , right, bottom);
        canvas.drawArc(oval, -90f, 360f, true, paint);
        return bitmap;
    }

    public Bitmap redrawTimer(Bitmap b, double startAngle){

        canvas = new Canvas(b);

        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(37, 181, 215));
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        // dessin arc externe
        RectF oval = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawArc(oval, -90f, (long)(startAngle+(angleForSec*iterator)), true, paint);
        Log.d("infoPUUTAIN", String.valueOf(startAngle+(angleForSec*iterator)));

        iterator++;
        return b;
    }

}

