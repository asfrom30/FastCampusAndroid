package com.doyoon.android.fastcampusandroid.week5.async;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

public class ThreadAsyncTaskMain extends AppCompatActivity {

    public static final String TAG = ThreadAsyncTaskMain.class.getName();

    TextView textView;
    public static final int SET_DONE = 55;
    ProgressDialog progressDialog;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_DONE:
                    setDone();
                    postProcess();
                    break;
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_async_task_main);

        textView = (TextView) findViewById(R.id.threadAsync_tv_main);
        findViewById(R.id.threadAsync_tv_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Type 1 : Run Sub thread */
                // progressDialog.show();
                // new CustomThread(handler).start();

                /* Type 2 : Run Async Task */
                runAsync();
            }
        });
        // 화면의 진행 상태를 표시
        // Activity의 테마를 사용하게 된다. Context로 부터 확장한다.(extend) Activity에 다른 기능들이 있따...
        // 그 자원을 사용하기 때문에 되지 않는 것이다.
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("진행중....");
        progressDialog.setMessage("진행중 표시되는 메세지 입니다.");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

    }

    public void runAsync(){

        new AsyncTask<String, Integer, Float>() {

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                progressDialog.setMessage("진행율 =" + values[0] + "%");
            }

            /* 3번째 인자 */
            // doInbackground의 return
            // postProgress의 Param

            // 미리 정의가 되어 있다.
            // Async 하기전에 실행하는 method
            /* Main Thread에서 실행(UI 조작가능) */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.show();
            }

            /* Sub Thread에서 실행 */
            // doInBackground의 서브스레드에서 백그라운드에서 처리할 것을 작성
            @Override
            protected Float doInBackground(String... params) { // ... 세개표시가 배열이라는 표시고 몇개가 들어올지 모르는것.
                try {
                    for(int i=0; i<10; i++) {
                        publishProgress(i*10);  // onProgressUpdate 주기적으로 업데이트 해준다.
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 1000.4f;
            }

            /* Main Thread에서 실행 */
            // Async 한 후에 실행하는 method
            @Override
            protected void onPostExecute(Float result) {
                super.onPostExecute(result);
                setDone();
                postProcess();
                Log.e(TAG, "Do in Background result = " + result);
            }
        }.execute("안녕", "하세요");    // 같은타입이 여러개의 인자를 넘겨줄 수 있다. excute("str", "str", "str")

    }

    private void runSync(){
        setDone();
        postProcess();
    }
    private void setDone() {
        textView.setText("You're Great!!!!");
    }

    private void postProcess(){
        progressDialog.dismiss();
    }




}
