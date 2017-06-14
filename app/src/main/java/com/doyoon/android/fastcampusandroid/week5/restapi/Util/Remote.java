package com.doyoon.android.fastcampusandroid.week5.restapi.Util;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DOYOON on 6/12/2017.
 */

public class Remote {

    public static void newTask(final TaskInterface taskInterface){
        //    public static void newTask(final TaskInterface taskInterface) {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                String result ="";
                try {
                    result = getData(params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                taskInterface.postExecute(result);
            }
        }.execute(taskInterface.getUrl());
    }

    /* Url만 가져오면 자체적으로 쓸수 있다. 아주 설계가 잘된 예... */

    /* 데이터를 가져오는 부분을 독립적으로 잘 설계해야한다.
    왜냐하면 아직 View를 설계하지 않았기 때문에 무엇을 가져와야 할지 모른다....*/
    public static String getData(String url) throws Exception {
        String result = "";
        // 데이터를 가져오는 것은 가져오는 순간 무조건 String


        // 네트웍 처리
        /* Request */
        // 1. url을 가진 서버와 연결
        // 1.1 연결객체 생성
            /* Throw Exception */
        // 요청한 쪽으로 Exception을 넘기는 경우는, 요청한쪽에서 Exception에 대한 것을
        // 알림을 한다거나, 다음 액션을 어떻게 처리할지 알려줄때 throw를 사용한다.
        // 예를 들자면 액티비티 마다 실행하는 게 다른데.. 팝업을 할지, 토스트를 할지
        // 그건 호출한 쪽에서 처리하는 것이다 대부분 그럴 것이다.
        URL serverURL = new URL(url);
        // 1.2 연결 객체 생성
        HttpURLConnection httpUrlConnection = (HttpURLConnection) serverURL.openConnection();// url 객체에서 꺼낸다.
        // 1.3 http method 결정
        httpUrlConnection.setRequestMethod("GET");

        /* Response */
        // 2.1 응답코드 분석
        int responseCode = httpUrlConnection.getResponseCode();
        if (responseCode == httpUrlConnection.HTTP_OK) {

//            InputStream inputStream = httpUrlConnection.getInputStream();
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//            while (bufferedInputStream.read() != null) {
//
//            }

            BufferedReader br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            String temp = null;
            while ((temp = br.readLine()) != null) {
                result += temp;
            }
        } else {
            // 각자 호출측으로 Exception을 만들어서 넘겨줄것.
            // responseCode에 따라 Exception이 다르다.
        }

        return result;
    }

}
