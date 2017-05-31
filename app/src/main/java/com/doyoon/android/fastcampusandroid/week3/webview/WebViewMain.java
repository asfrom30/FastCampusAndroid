package com.doyoon.android.fastcampusandroid.week3.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.doyoon.android.fastcampusandroid.R;

public class WebViewMain extends AppCompatActivity implements View.OnClickListener{

    private WebView webView;

    private Button fowardBtn;
    private Button goBtn;
    private EditText urlInput;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission_main);

        webView = (WebView) findViewById(R.id.webview);

        /*
            -. WebViewClient는 http만 호출이 가능하다.
            -. WebChormeClient https도 가져올 수 있다. (크롬에서 html을 해석하는 크로뮴프로젝트에서 가져온것)
         */


        /* 네트웍을 사용하기 위해서는, 네트웍 사용 권한이 필요하다.
           권한중에 인터넷 권한이 있는데 이것을 manifest에서 설정한다
           Manifest에 해당 코드를 삽입, Runtime Permission과 다르다.
           설치시 권한 이다.
           <uses-permission android:name="android.permission.INTERNET" />
        */

        /* 클라이언트 생성해줘야 한다. */
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        /* Javascript 사용설정 */
        webView.getSettings().setJavaScriptEnabled(true);

        fowardBtn  = (Button) findViewById(R.id.runtimePermission_btn_foward);
        backBtn = (Button) findViewById(R.id.runtimePermission_btn_back);
        goBtn = (Button) findViewById(R.id.runtimePermission_btn_go);
        urlInput = (EditText) findViewById(R.id.runtimePermission_et_inputUrl);

        fowardBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        goBtn.setOnClickListener(this);


        loadUrl("naver.com");
    }

    private void loadUrl(String url) {
        // If start of word is not http://, Add http://
        if (!url.startsWith("http://")) {
            url = "http://" + url;
        }
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.runtimePermission_btn_foward:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.runtimePermission_btn_back:
                if(webView.canGoBack()){
                    webView.goBack();
                }
                break;
            case R.id.runtimePermission_btn_go:
                String url = this.urlInput.getText().toString();
                loadUrl(url);
                break;
        }
    }
}
