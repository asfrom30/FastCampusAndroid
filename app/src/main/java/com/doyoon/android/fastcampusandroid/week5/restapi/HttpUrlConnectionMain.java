package com.doyoon.android.fastcampusandroid.week5.restapi;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;
import com.doyoon.android.fastcampusandroid.week5.restapi.Util.Remote;
import com.doyoon.android.fastcampusandroid.week5.restapi.Util.TaskInterface;
import com.doyoon.android.fastcampusandroid.week5.restapi.domain.Data;
import com.doyoon.android.fastcampusandroid.week5.restapi.domain.Row;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionMain extends AppCompatActivity implements TaskInterface, OnMapReadyCallback, AbsListView.OnScrollListener{

    public static final String TAG = HttpUrlConnectionMain.class.getName();

    private String url;
    TextView textView;
    private ListView listView;
    private GoogleMap googleMap;

    // http://openapi.seoul.go.kr:8088/6a734e76676d697235397548734e42/json/GeoInfoPublicToiletWGS/1/10
    static final String URL_PREFIX = "http://openapi.seoul.go.kr:8088/";
    static final String URL_CERT = "6a734e76676d697235397548734e42/";
    static final String URL_MID = "json/GeoInfoPublicToiletWGS";

    // 페이지간 몇개씩 이동을 할 것인지?
    int page = 0;
    private final int PAGE_OFFSET = 10;

    // Adpater
    ArrayAdapter<String> adapter;
    // 어댑터에서 사용할 공간
    final List<String> toiletList = new ArrayList<>();


    //
    boolean lastItemVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViews();


        /* deprecated */
        try {
//            String url = "google.com";
            String url = "http://google.com"; // 프로토콜 오류... http:// 지정해야 한다.
//            newTask(url); /* 기존 방식 */
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "네트워크 에러 발생" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setViews() {
        setContentView(R.layout.activity_http_url_connection);

        textView = (TextView) findViewById(R.id.httpUrlConnection_tv);
        listView = (ListView) findViewById(R.id.httpUrlConnection_lv);

        /* List */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toiletList);
        listView.setAdapter(adapter);

        /* Listener */
        this.setListener();

        /* Map */
        this.setMap();
    }

    public void nextPage() {
        this.page += 1;
    }

    public void setURL() {

        int end = this.page * this.PAGE_OFFSET;
        int begin = end  - this.PAGE_OFFSET + 1;

        this.url = URL_PREFIX + URL_CERT + URL_MID + "/" + begin + "/" + end;
    }

    private void setListener(){
        /* 스크롤의 상태값을 체크해주는 리스너 */
        listView.setOnScrollListener(this);
    }

    private void setMap(){
        /* Map */
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.mapViewFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public String getUrl(){
        return this.url;
    }

    public Data converJson(String jsonString) {
        Gson gson = new Gson();
        // 1. json String -> class로 변환
        Data data = gson.fromJson(jsonString, Data.class);
        return data;
    }

    public void setItemCount(int totalCount) {
        this.textView.setText(totalCount + ""); /* 한줄이지만 빼내주는게 좋다.*/
    }

    private void addDatas(Row[] items) {
        for (Row item : items) {
            toiletList.add(item.getGU_NM() + item.getHNR_NAM());
        }
    }

    private void addMarkers(Row[] items){
        for (Row item : items) {
            MarkerOptions marker = new MarkerOptions();

            LatLng tempCoord = new LatLng(Double.parseDouble(item.getLAT()), Double.parseDouble(item.getLNG()));
            marker.position(tempCoord);
            marker.title(item.getGU_NM() + item.getHNR_NAM());

            googleMap.addMarker(marker);

        }
    }

    private void movdCamera(LatLng latLng){
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

    /* 페이징 하는객체를 따로 분리하는 것.... */

    @Override
    public void postExecute(String jsonResult) {

        Data data = converJson(jsonResult);

         /*Note : Gson에서 숫자값이 없으면 null이 반환되지 않는다. 숫자는 null이 없으므로,
         * 따라서 모든 데이터를 String으로 가져오고 null 값일때 예외처리를 하는 방식이 좋다.
          * Data를 int로 받지 말자...*/
        int totalCount = Integer.parseInt(data.getGeoInfoPublicToiletWGS().getList_total_count());
        Row items[] = data.getGeoInfoPublicToiletWGS().getRow();


        setItemCount(totalCount);

        addDatas(items);
        addMarkers(items);

        /* Map Control */
        LatLng sinsa = new LatLng(37.516066, 127.019361);
        movdCamera(sinsa);

        /* notify to adapter*/
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.loadPage();

        /* Camera Move */
        this.googleMap = googleMap;
        // 이동한다. 갱신할때마다 업데이트 해야 하므로. 그리고 변수명을 던져준다.
//        LatLng latLng = new LatLng(37.516066, 127.019361);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

    /* 내 현재 스크롤이 끝까지 가서 더이상 움직이지 않는다를 체크해준다. */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && lastItemVisible) { // 마지막에 가면 아이들 상태가 된다.
            this.loadPage();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // firstVisibleItem 현재 화면에 보여지는 첫번째 아이템의 번호
        // visibleItemCount 현재 화면에 보여지는 아이템의 개수
        // totalItemCount 리스트에 담겨 있는 전체 아이템의 개수...
        Log.e(TAG, "firstVisibleItem : " + firstVisibleItem + ", visibleItemCount" + visibleItemCount + ", totalItemCount :" + totalItemCount);
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            lastItemVisible = true;
        } else {
            lastItemVisible = false;
        }
    }

    private void loadPage(){
        nextPage();
        setURL();
        Remote.newTask(this);
    }
}
