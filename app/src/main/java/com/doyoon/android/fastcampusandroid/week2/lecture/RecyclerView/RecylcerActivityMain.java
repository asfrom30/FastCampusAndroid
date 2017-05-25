package com.doyoon.android.fastcampusandroid.week2.lecture.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;

public class RecylcerActivityMain extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recylcer_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // 1. 데이터
        ArrayList<Data> dataList = Loader.getData(this);

        // 2. 어댑터
        CustomRecylcerAdapter customRecylcerAdapter = new CustomRecylcerAdapter(dataList, this);

        // 3. 연결
        recyclerView.setAdapter(customRecylcerAdapter);

        // 4. 레이아웃 매니저 등록
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
// Holder는 99% Adapter 안에서 사용되므로 이 안에서 선언한다.
class CustomRecylcerAdapter extends RecyclerView.Adapter<CustomRecylcerAdapter.Holder> {

    private ArrayList<Data> dataList;
    private Context context;

    public CustomRecylcerAdapter(ArrayList<Data> datasList, Context context) {
        this.dataList = datasList;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context를 Inflate용도로만 쓸거면 context를 param으로 받지 않아도 된다. 그 이유는 parent.getContext()로 사용 가능하므로.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyler_list, parent, false);// Inflate처럼 view를 가져오는 코드
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // 1. 데이터를 꺼내고
        Data data = dataList.get(position);
        // 2. 데이터를 세팅한다.
        holder.setNumber(data.getId());
        holder.setTitle(data.getTitle());
        holder.setImage(data.getResId());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class Holder extends RecyclerView.ViewHolder { // View Holder를 상속 받아서 만든다.
        private ImageView imageView;
        private TextView titleView;
        private TextView numberView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            numberView = (TextView) itemView.findViewById(R.id.txtNo);
            titleView = (TextView) itemView.findViewById(R.id.txtTitle);
        }

        public void setNumber(int number){
            this.numberView.setText(number + "");
        }

        public void setTitle(String title){
            this.titleView.setText(title);
        }

        public void setImage(int resId) {
            this.imageView.setImageResource(resId);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTitleView() {
            return titleView;
        }

        public void setTitleView(TextView titleView) {
            this.titleView = titleView;
        }

        public TextView getNumberView() {
            return numberView;
        }

        public void setNumberView(TextView numberView) {
            this.numberView = numberView;
        }
    }
}


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

