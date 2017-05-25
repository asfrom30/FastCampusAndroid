package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.doyoon.android.fastcampusandroid.R;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_acitivity);

        ArrayList<Data> datas = Loader.getData(this);

        GridAdapter adapter = new GridAdapter(datas, this);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

    }
}

class GridAdapter extends BaseAdapter {

    private ArrayList<Data> datas;
    private Context context;
    LayoutInflater inflater;


    public GridAdapter(ArrayList<Data> datas, Context context){
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
        Holder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_custom_grid, null);

            holder = new Holder();
            holder.image = (ImageView) convertView.findViewById(R.id.gridImageView);
            holder.title = (TextView) convertView.findViewById(R.id.gridTextView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Data data = datas.get(position);

        int image_suffix = (data.getId()%5) + 1;

        // activity 자원을 사용하기 위해서는 context를 넘겨줘서 이를 이용하여 사용한다.
        // getResources는 activity에서 사용할 수 있으므로 현재는 어댑터이기 때문에 context를 넘겨줘서 사용한다.
                                                // 리소스아이디       // 리소스패키지
        int id = context.getResources().getIdentifier("images_" + image_suffix, "mipmap", context.getPackageName());

        holder.image.setImageResource(id);
        holder.title.setText(data.getTitle());

        return convertView;
    }

    class Holder{
        public ImageView image;
        public TextView title;
    }
}
