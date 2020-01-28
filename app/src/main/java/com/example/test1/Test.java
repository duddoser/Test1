package com.example.test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class Test extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread;

    public Test(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    public boolean onTouchEvent(MotionEvent motionEvent){
        drawThread.setParams(motionEvent.getX(), motionEvent.getY(), 0);
        return super.onTouchEvent(motionEvent);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(),getHolder());
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }

}


class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;
    private float x = 0, y = 0, r = 0;
    private Paint paint;

    DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        paint = new Paint();
    }

    void setParams(float x, float y, float r){
        this.x = x;
        this.y = y;
        this.r = r;
        paint.setColor(Color.YELLOW);
    }

    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        paint.setColor(Color.BLUE);

        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawColor(Color.BLUE);
                    canvas.drawCircle(x, y, r, paint);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                r += 5;

                try {
                    Thread.sleep(17);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}