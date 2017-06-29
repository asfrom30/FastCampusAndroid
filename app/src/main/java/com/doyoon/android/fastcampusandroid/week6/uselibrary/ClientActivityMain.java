package com.doyoon.android.fastcampusandroid.week6.uselibrary;

import android.support.v7.app.AppCompatActivity;

// @EActivity(R.layout.activity_main)  // Activity Binding....
public class ClientActivityMain extends AppCompatActivity {

    /* Java Annotation */
    // 컴파일러에 정보를 전달하는 역할.. 특수기능, 코드 자동생성..
    // Anotaion을 사용할 수 도 있다...
    // @target, @Retention으로 사용 영겨을 지정해줄 수 있다.
    // Source, Class, Runtime -->

    /* DI 도움주는게 세개가 있는데
    * 1. android annotation (기능도 막강하고, 성능도 괜찮다.) 안드로이드 3.0으로 넘어가면서 내부에서 지원이 된다.
    * 2. 버터나이프....
    * 3. 대거 ....
    * */

    /* ButterKnife
    @BindView(R.id.client_rv_bbslist)
    RecyclerView recyclerView;

    @BindView(R.id.client_btn_move_write)
    Button onBtnWrite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        ButterKnife.bind(this);
    }


    @OnClick(R.id.client_btn_move_write)
    public void goWrite(){
        Intent intent = new Intent(this, WriteActivity.class);
        startActivity(intent);
    }
    */
}
