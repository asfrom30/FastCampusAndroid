package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        listView = (ListView) findViewById(R.id.customListView);

        //1. 데이터
        ArrayList<Data> datas = Loader.getData();

        //2. 아답터
        CustomAdapter adapter = new CustomAdapter(datas, this);

        //3. 연결
        listView.setAdapter(adapter);
    }
}

// Custom은 무언가를 상속받아서 만들때 사용하는 네이밍
// BaseAdapter는 Adapter의 기본이 되는 기능이 정의되어 있다.
class CustomAdapter extends BaseAdapter {

    private ArrayList<Data> datas;
    private Context context;
    LayoutInflater inflater;


    public CustomAdapter(ArrayList<Data> datas, Context context){
        this.datas = datas;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  // system 서비스 안에는 다양한 것이 포함 되어 있으므로 형변환 해서 사용한다.

    }

    @Override
    public int getCount() { // 사용하는 데이터의 총 개수를 리턴(리스트 뷰의 길이를 추정)
        return datas.size();
    }

    @Override
    public Object getItem(int position) { // 데이터 클래스 하나를 리턴
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) { // 뷰의 아이디 값이 리턴(대부분 인덱스가 그대로 리턴된다.
        return position;
    }

    @Override   // 중요 아이템 뷰 하나를 리턴한다.
    // converView 계속해서 new로 View를 생성하지 않기 위해서 converView로 이전 클래스들이 넘어오게 된다.
    // View를 13개를 생성해두고 계속해서 값만 바꾸는 것이다. View의 재사용성을 위해서
    // 재사용을 하면 new를 하지 않아도 된다.
    public View getView(int position, View convertView, ViewGroup parent) {


        // 1. xml을 class로 변환한다.
//        View view = 인플레이트(R.layout.item_custom_list)  // context에서 inflate를 꺼낼 수 있다.
        // Inflate를 꺼내는 방법은 1. System에서 꺼내는것 2. View에서 꺼내는 것
        // View 에서 context를 사용할 수 있다. => 이말은 View에서 View를 꺼낼 수 있다

        // 코드를 아래처럼 수정
//        View view = inflater.inflate(R.layout.item_custom_list, null);
//        TextView no = (TextView) view.findViewById(R.id.txtNo);
//        TextView title = (TextView) view.findViewById(R.id.txtTitle);

        Log.d("ConvertView", position+ " : convertView=" + convertView);
        // 성능이 향상이 되는 것이다. 위처럼 코드를 짜면 계속해서 View를 만들어낸다.
        // 2단계 View를 재사용하는것 한계가 View만 재사용, 3단계에서 TextView, TextView를 재사용하기 위해 holder를 사용한다.
        /*
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_custom_list, null);
        }
        TextView no = (TextView) convertView.findViewById(R.id.txtNo);
        TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        */

        // 매줄에 해당되는 데이터를 꺼낸다.
        /*
        Data data = datas.get(position);
        no.setText(data.getId()+"");    // Int to String
        title.setText(data.getTitle());
        */

        //3단계 홀더(변수를 재사용)
        //이런 converView와 변수를 재사용하는 형태가 형태가 Recycler 뷰와 같다.
        //개발자들이 먼저 만들어서 사용하고 있었음, 물론 Recylcer 뷰가 조금 더 안정적
        Holder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_custom_list, null);
            holder = new Holder();

            holder.no = (TextView) convertView.findViewById(R.id.txtNo);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Data data = datas.get(position);
        holder.no.setText(data.getId()+"");    // Int to String
        holder.title.setText(data.getTitle());
        /**/


        return convertView;
    }

    class Holder{
        public TextView no;
        public TextView title;
    }
}



// 데이터랑 실제 코드를 분리하는 이유가 MVP, MVC 사용하기 때문
// Loader를 사용하면 코드가 분리되므로, 지금은 데이터를 생성했지만
// 인터넷에 가져오는 코드는 Loader만 수정하면 된다.
// 메인클래스를 수정할 필요가 없다.
class Loader{

    public static ArrayList<Data> getData(){
        ArrayList<Data> result = new ArrayList<>();
        for(int i=0; i < 100; i++){
            Data data = new Data();
            data.setId(i+1);
            data.setTitle("타이틀"+i);
            result.add(data);
        }
        return result;
    }
}

class Data {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}