package com.doyoon.android.fastcampusandroid.week5.restapi;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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

public class HttpUrlConnectionMain extends AppCompatActivity implements TaskInterface, OnMapReadyCallback{

    public static final String TAG = HttpUrlConnectionMain.class.getName();

    private String url;
    TextView textView;
    private ListView listView;

    // http://openapi.seoul.go.kr:8088/6a734e76676d697235397548734e42/json/GeoInfoPublicToiletWGS/1/10
    static final String URL_PREFIX = "http://openapi.seoul.go.kr:8088/";
    static final String URL_CERT = "6a734e76676d697235397548734e42/";
    static final String URL_MID = "json/GeoInfoPublicToiletWGS";

    int pageBegin = 1;
    int pageEnd = 10;

    // 페이지간 몇개씩 이동을 할 것인지?
    int offset = 10;

    // Adpater
    ArrayAdapter<String> adapter;
    // 어댑터에서 사용할 공간
    final List<String> toiletList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection);

        textView = (TextView) findViewById(R.id.httpUrlConnection_tv);
        listView = (ListView) findViewById(R.id.httpUrlConnection_lv);

        /* List */
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toiletList);
        listView.setAdapter(adapter);

        /* 스크롤의 상태값을 체크해주는 리스너 */

        /* Map */
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.mapViewFragment);
        mapFragment.getMapAsync(this);

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

    public void setURL(int page) {

        int end = page * this.offset;
        int begin = end  - this.offset + 1;

        this.url = URL_PREFIX + URL_CERT + URL_MID + "/" + begin + "/" + end;
    }

    @Override
    public String getUrl(){
        return this.url;
    }

    @Override
    public void postExecute(String jsonResult) {
        Gson gson = new Gson();

        // 1. json String -> class로 변환
        Data data = gson.fromJson(jsonResult, Data.class);

        // 전체 가져온 사이즈를 표시해준다.
        this.textView.setText(data.getGeoInfoPublicToiletWGS().getList_total_count() + "");

        // Get Row....
        Row rows[] = data.getGeoInfoPublicToiletWGS().getRow();

        for (Row row : rows) {
            toiletList.add(row.getGU_NM() + row.getHNR_NAM());

            MarkerOptions marker = new MarkerOptions();

            LatLng tempCoord = new LatLng(Double.parseDouble(row.getLAT()), Double.parseDouble(row.getLNG()));
            marker.position(tempCoord);
            marker.title(row.getGU_NM() + row.getHNR_NAM());

            googleMap.addMarker(marker);

            googleMap.addMarker(marker);
        }

         /* Camera Move */
        LatLng latLng = new LatLng(37.516066, 127.019361);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

        /* notify to adapter*/
        adapter.notifyDataSetChanged();
    }

    GoogleMap googleMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // setPage(1)
        // setURl(pageBegin, pageEnd);
        this.setURL(1);
        Remote.newTask(this);

        /* Camera Move */
        this.googleMap = googleMap;
        // 이동한다. 갱신할때마다 업데이트 해야 하므로. 그리고 변수명을 던져준다.
//        LatLng latLng = new LatLng(37.516066, 127.019361);
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));


    }
}
