package com.doyoon.android.fastcampusandroid.week6.abstractoop;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.doyoon.android.fastcampusandroid.R;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientActivityWrite extends AppCompatActivity implements View.OnClickListener {

    private static String serverUrlStr = "http://192.168.10.250/bbs/insert.jsp";

    Button btnPost;
    EditText authorText, titleText, contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client);

        btnPost = (Button) findViewById(R.id.btnPost);
        authorText = (EditText) findViewById(R.id.textAuthor);
        titleText = (EditText) findViewById(R.id.textTitle);
        contentText = (EditText) findViewById(R.id.textContent);

        btnPost.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String title = titleText.getText().toString();
        String author = authorText.getText().toString();
        String content = contentText.getText().toString();


        // Json 파싱..
        BBS bbs = new BBS(title, author, content);
        Gson gson = new Gson();
        String jsonBbs = gson.toJson(gson);


        CustomAsyncTask async = new CustomAsyncTask();
        async.execute(serverUrlStr, jsonBbs);
    }


    class CustomAsyncTask extends AsyncTask<String, Object, Object> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(String... params) {
            try {
                RemoteData.requestInPost(params[0], params[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

    public static class RemoteData {

        public static void fetchDataInGet(String urlStr) throws Exception{
            URL serverUrl = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

            }
        }

        public static void requestInPost(String urlStr, String jsonString) throws Exception{

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true); // 전송할 데이터가 있다고 알려줌
            OutputStream os = connection.getOutputStream();
            os.write(jsonString.getBytes());
            os.flush();
            os.close();
        }

        /* 서브 스레드가 돌아가는 곳에는 대부분 콜백함수를 만들어야 한다. */
        public interface CallBack{

        }
    }
}
