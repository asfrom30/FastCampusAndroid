package com.doyoon.android.fastcampusandroid.week5.restapi.Util;

/**
 * Created by DOYOON on 6/12/2017.
 */


public class Task {

    /* 네트워크는 메인에서 사용할 수 없게 강제가 되어 있다.
    * Thread or Async Task 사용. */
//    public void newTask(String url) {
//        new AsyncTask<String, Void, String>() {
//
//            @Override
//            protected String doInBackground(String... params) {
//                String result ="";
//                try {
//                    result = getData(params[0]);
//                    Log.i("Network", result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                super.onPostExecute(result);
//                textView.setText(result);
//            }
//        }.execute(url);
//    }

    /* 이건 Remote가 없으면 사용할수가 없으므로.... Task는 Remote 안으로 들어가야 한다. */
//    public static void newTask(final TaskInterface taskInterface) {
//        new AsyncTask<String, Void, String>() {
//
//            @Override
//            protected String doInBackground(String... params) {
//                String result ="";
//                try {
//                    result = getData(params[0]);
//                    Log.i("Network", result);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                taskInterface.postExecute(result);
//            }
//        }.execute(taskInterface.getUrl());
//    }
}
