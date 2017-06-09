package com.doyoon.android.fastcampusandroid.week4.handwritememo.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week4.handwritememo.HandWriteMemoActivityMain;

/**
 * Created by DOYOON on 6/7/2017.
 */

public class HandWriteMemoAcitivityView implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener{

    public static final String TAG = HandWriteMemoAcitivityView.class.getName();

    private Activity activity;
    private HandWriteMemoActivityMain presenter;


    /* component */
    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    private SeekBar seekBar;
    private ImageView imageView;

    public HandWriteMemoAcitivityView(HandWriteMemoActivityMain presenter, Paint paint) {

        this.activity =  (Activity) presenter;
        this.presenter = presenter;

        /* dependency injection */
        this.activity.setContentView(R.layout.activity_hand_write_memo_main);
        this.frameLayout = (FrameLayout) activity.findViewById(R.id.handwriteMemo_frameLayout);

        this.radioGroup = (RadioGroup) this.activity.findViewById(R.id.handwriteMemo_radioGroup);
        this.seekBar = (SeekBar) this.activity.findViewById(R.id.handwriteMemo_seekbar);

        this.imageView = (ImageView) this.activity.findViewById(R.id.handwriteMemo_iv_thumbnail);

        /* Add Listener */
//        this.activity.findViewById(R.id.handwriteMemo_radio_blue).setOnClickListener(this);
//        this.activity.findViewById(R.id.handwriteMemo_radio_red).setOnClickListener(this);
//        this.activity.findViewById(R.id.handwriteMemo_radio_green).setOnClickListener(this);
        this.radioGroup.setOnCheckedChangeListener(this);
        this.seekBar.setOnSeekBarChangeListener(this);


        this.activity.findViewById(R.id.handwriteMemo_btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.destroyDrawingCache();
                // 캡쳐를 할 뷰의 캐쉬를 사용한다
                frameLayout.buildDrawingCache();
                // 레이아웃에 그려진 내용을 bitmap 형태로 가져온다.(bitmap 픽셀당 색상값을 가지고 있는것이 bitmap)
                Bitmap capture = frameLayout.getDrawingCache();
                // 캡쳐한 이미지를 화면에 보여준다.
                imageView.setImageBitmap(capture);

                capture.recycle();

            }
        });

        // 1. 보드를 새로 생성한다.
        // Board board = new Board(getBaseContext());
        PaintBook paintBook = new PaintBook(this.activity);
        paintBook.setPaint(paint);

        // 3. 생성된 보드를 화면에 세팅한다.
        this.frameLayout.addView(paintBook);

    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (group.getId() == R.id.handwriteMemo_radioGroup) {
            switch (checkedId) {
                case R.id.handwriteMemo_radio_blue:
                    this.presenter.setColor(Color.BLUE);
                    break;
                case R.id.handwriteMemo_radio_red:
                    this.presenter.setColor(Color.RED);
                    break;
                case R.id.handwriteMemo_radio_green:
                    this.presenter.setColor(Color.GREEN);
                    break;
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.presenter.setStrokeWidth(progress/10.0f);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
