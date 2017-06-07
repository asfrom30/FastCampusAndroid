package com.doyoon.android.fastcampusandroid.week4.handwritememo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DOYOON on 6/7/2017.
 */

class PaintBook extends View {

    public static final String TAG = PaintBook.class.getName();
    private Path bufferPath;
    Paint bufferPaint, currentPaint;
    List<AdvancedPath> advancedPathList;


    public PaintBook(Context context) {
        super(context);

        this.advancedPathList = new ArrayList<>();
        this.bufferPaint = new Paint();
    }

    public void setPaint(Paint paint) {
        this.currentPaint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (AdvancedPath advancedPath : advancedPathList) {
            this.bufferPaint.setColor(advancedPath.getColor());
            this.bufferPaint.setStrokeWidth(advancedPath.getStrokeWidth());
            this.bufferPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(advancedPath.getPath(), bufferPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.bufferPath = new Path();
                int color = this.currentPaint.getColor();
                float width = this.currentPaint.getStrokeWidth();
                this.advancedPathList.add(new AdvancedPath(color, width, bufferPath));
                bufferPath.moveTo(x,y); // 그리지 않고 이동한다...
                break;
            case MotionEvent.ACTION_MOVE:
                bufferPath.lineTo(x,y); // ,바로 이전점과 현재점 사이에 줄을 그어준다.
                break;
            case MotionEvent.ACTION_UP:
                bufferPath.lineTo(x,y); // ,바로 이전점과 현재점 사이에 줄을 그어준다.
                break;
        }

        invalidate();

        // 다음 동작을 연속적으로 생성하지 않는다.
        // false일 경우 touch 이벤트를 연속해서 발생시키지 않는다., 드래그시 터치 이벤트가 발생하지 않는다.
        return true;
//            return super.onTouchEvent(event);
    }

    class AdvancedPath {
        private int color;
        private float strokeWidth;
        private Path path;

        public AdvancedPath(int color, float strokeWidth, Path path) {
            this.color = color;
            this.strokeWidth = strokeWidth;
            this.path = path;
        }

        public int getColor() {
            return color;
        }

        public float getStrokeWidth() {
            return strokeWidth;
        }

        public Path getPath() {
            return path;
        }
    }
}
