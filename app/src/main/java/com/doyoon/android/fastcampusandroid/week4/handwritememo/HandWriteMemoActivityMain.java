package com.doyoon.android.fastcampusandroid.week4.handwritememo;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.doyoon.android.fastcampusandroid.week4.handwritememo.view.HandWriteMemoAcitivityView;

public class HandWriteMemoActivityMain extends AppCompatActivity {

    HandWriteMemoAcitivityView view;
    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.paint = createDefaultPaint();
        this.view = new HandWriteMemoAcitivityView(this, this.paint);
    }

    public void setColor(int color){
        this.paint.setColor(color);
    }

    public void setStrokeWidth(float width){
        this.paint.setStrokeWidth(width);
    }

    public Paint createDefaultPaint(){
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1.0f);
        paint.setStyle(Paint.Style.STROKE);     // 이게??

        return paint;
    }


}
