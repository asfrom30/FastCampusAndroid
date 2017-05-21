package com.doyoon.android.fastcampusandroid.week2.lecture.BasicWidget;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.doyoon.android.fastcampusandroid.R;

public class BasicWidgetActivity extends AppCompatActivity implements
                                                            View.OnClickListener,
                                                            CompoundButton.OnCheckedChangeListener,
                                                            RadioGroup.OnCheckedChangeListener{
    Button btnDog, btnPig, btnHorse;
    ToggleButton toggleButton;
    RadioGroup radioGroup;

    SeekBar seekBar;

    TextView seekCount;

    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_widget);

        //2 위젯 변수를 화면과 연결
        btnDog = (Button) findViewById(R.id.btnDog);
        btnPig = (Button) findViewById(R.id.btnPig);
        btnHorse = (Button) findViewById(R.id.btnHorse);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        radioGroup = (RadioGroup) findViewById(R.id.radioRgbGroup) ;

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekCount = (TextView) findViewById(R.id.seekCount);

        // * extra : Edit 속성 변경하기
//        editText = (EditText) findViewById();
//        editText.setInputType();

        //3. 클릭 리스너 연결
        // 버튼이 여러개 있을때는 익명객체로 하면 코드량이 너무 많아진다. Listenr를 implements로 받아서 구현
        btnDog.setOnClickListener(this);
        btnPig.setOnClickListener(this);
        btnHorse.setOnClickListener(this);

        toggleButton.setOnCheckedChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this);

        seekBar.setOnSeekBarChangeListener(listener);
    }

    @Override
    public void onClick(View v) {
        // v에 클릭된 위젯값이 넘어온다.
        switch (v.getId()){
            case R.id.btnDog:
                // Context는 현재 시스템의 자원을 사용하겠다. getApplicationContext()를 사용해도 된다.
                // 최상위 클래스 Context, View,
                Toast.makeText(this, "멍멍~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnHorse:
                Toast.makeText(this, "꿀꿀~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnPig:
                Toast.makeText(this, "이힝~", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.toggleButton:
                if(isChecked){
                    Toast.makeText(this, "켜졌습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "꺼졌습니다.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if(group.getId() == R.id.radioRgbGroup){
            switch (checkedId){
                case R.id.radioRed:
                    Toast.makeText(this, "RED.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioBlue:
                    Toast.makeText(this, "BLE.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radioGreen:
                    Toast.makeText(this, "GREEN.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // seek bar에 변경사항이 있을때마다 호출한다
            seekCount.setText(progress + ""); // setText에 숫자값만 단독으로 들어가면 앱이 다운된다. (int + "")
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
