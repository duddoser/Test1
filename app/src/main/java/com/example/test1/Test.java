package com.example.test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class Test extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private int startRadius;


    public Test(Context context) {
        super(context);
        getHolder().addCallback(this);
        surfaceHolder = getHolder();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public boolean onTouchEvent(MotionEvent motionEvent){
        if (motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
            canvas = surfaceHolder.lockCanvas();
            Paint paint = new Paint();
            canvas.drawCircle(motionEvent.getX(), motionEvent.getY(), startRadius, paint); // v draw jjjoopa
            return true;
        }
        return false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

}


class DrawThread extends Thread{

    public void run(){

    }
}
