package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;

public class CustomListActivity extends AppCompatActivity {

    ListView listView;

    public static final String DATA_KEY = "DyDataKey";
    public static final String DATA_TITLE = "DyDataTitle";
    public static final String RESOURCE_ID = "DyResourceID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        listView = (ListView) findViewById(R.id.customListView);

        //1. 데이터
        final ArrayList<Data> datas = Loader.getData(this);

        //2. 아답터
        CustomAdapter adapter = new CustomAdapter(datas, this);

        //3. 연결
        listView.setAdapter(adapter);

        // 4. 다른 연결
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomListActivity.this, CustomDetailActivity.class);
                Data data = datas.get(position);
                intent.putExtra(DATA_KEY, position);
                intent.putExtra(DATA_TITLE, data.getTitle());
                intent.putExtra(RESOURCE_ID, data.getResId());
                startActivity(intent);
            }
        });
        */ // 아답터 안으로 세부 리스너로 변경해본다.
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

        Log.d("ConvertView", position + " : convertView=" + convertView);
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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_custom_list, null);
            holder = new Holder(convertView, this.context);

            /*
            holder.no = (TextView) convertView.findViewById(R.id.txtNo);
            holder.title = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            */ // 함수를 Holder로 이동. M과 P를 분리

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Data data = datas.get(position);

        /*
        holder.no.setText(data.getId()+"");    // Int to String
        holder.title.setText(data.getTitle());
        holder.imageView.setImageResource(data.getResId());
        */ // View 모델로 변경, 이렇게하면 직접 View 속성을 쓰는게 하나도 없다.

        // MVP
        holder.setPosition(position);
        holder.setNo(data.getId());
        holder.setTitle(data.getTitle());
        holder.setImage(data.getResId());
        // MVC  // 종속성이 생긴다. 객체 전달
        // holder.setData(data)

        return convertView;
    }

    class Holder{           // Holder가 View Class가 된다.(여기서 입력과 출력을 부르는다.)
                            // Adapter는 presenter이다.
        public TextView no;
        public TextView title;
        public ImageView imageView;

        public int positon;
        private int resId;


        // 1. 이미지 뷰에 온클릭 리스너를 달고 상세 페이지로 이동시킨다.

        public Holder(View view, final Context context){        // final로 선언이 된다. 클로져
            no = (TextView) view.findViewById(R.id.txtNo);
            title = (TextView) view.findViewById(R.id.txtTitle);
            imageView = (ImageView) view.findViewById(R.id.imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CustomDetailActivity.class);

                    intent.putExtra(CustomListActivity.DATA_KEY, Holder.this.positon); // 클래스 내에 객체의 변수를 가르키게 해준다.
                    intent.putExtra(CustomListActivity.DATA_TITLE, title.getText().toString());    // 세가지 종류의 사용법
                    intent.putExtra(CustomListActivity.RESOURCE_ID, resId);

                    context.startActivity(intent);
                }
            });

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, title.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setNo(int no){
            this.no.setText(no + "");
        }

        public void setTitle(String title){
            this.title.setText(title);
        }

        public void setImage(int resId){
            this.resId = resId;
            this.imageView.setImageResource(resId);
        }

        public void setPosition(int position) {
            this.positon = position;
        }
    }
}



// 데이터랑 실제 코드를 분리하는 이유가 MVP, MVC 사용하기 때문
// Loader를 사용하면 코드가 분리되므로, 지금은 데이터를 생성했지만
// 인터넷에 가져오는 코드는 Loader만 수정하면 된다.
// 메인클래스를 수정할 필요가 없다.
class Loader{

    public static ArrayList<Data> getData(Context context){
        ArrayList<Data> result = new ArrayList<>();
        for(int i=0; i < 7; i++){
            Data data = new Data();
            data.setId(i);
            data.setTitle("타이틀"+(i+1));
            data.setImage(i+1, context);
            result.add(data);
        }
        return result;
    }
}

class Data {
    private int id;
    private String title;
    private String image;
    private int resId;

    public String getImage() {
        return image;
    }

    public void setImage(int id, Context context) {
        this.image = "images_" + id;
        this.resId = context.getResources().getIdentifier("images_" + id, "mipmap", context.getPackageName());
    }

    public int getResId(){
        return this.resId;
    }


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