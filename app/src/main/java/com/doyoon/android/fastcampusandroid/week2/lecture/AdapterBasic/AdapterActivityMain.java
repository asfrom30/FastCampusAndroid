package com.doyoon.android.fastcampusandroid.week2.lecture.AdapterBasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.doyoon.android.fastcampusandroid.R;

public class AdapterActivityMain extends AppCompatActivity {

    private final int LIST_VIEW = 1;
    private final int GRID_VIEW = 2;
    private final int RECYCLER_VIEW = 3;
    private final int CUSTOM_LIST_VIEW = 4;

    String datas[] = {"선택하세요", "ListView", "GridView", "RecyclerView", "CustomView"};

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        // How to connect between Data and List View
        // 1. declare Data
        spinner = (Spinner) findViewById(R.id.spinner);

        // 2. create Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);

        // 3. connect each other
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("SpinnerValue", datas[position]);

                // Call Activity

                Intent intent;
                switch (position){
                    case LIST_VIEW:
                        intent = new Intent(AdapterActivityMain.this, ListActivity.class);
                        startActivity(intent);
                        break;
                    case GRID_VIEW:
                        intent = new Intent(AdapterActivityMain.this, GridActivity.class);
                        startActivity(intent);
                        break;
                    case RECYCLER_VIEW:
                        intent = new Intent(AdapterActivityMain.this, RecyclerActivity.class);
                        startActivity(intent);
                        break;
                    case CUSTOM_LIST_VIEW:
                        intent = new Intent(AdapterActivityMain.this, CustomListActivity.class);
                        startActivity(intent);
                        break;
                    default:





                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
