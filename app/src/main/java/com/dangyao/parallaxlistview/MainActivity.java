package com.dangyao.parallaxlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.dangyao.parallaxlistviewlib.PallaraxListView;

public class MainActivity extends AppCompatActivity {

    private PallaraxListView myPallaraxListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPallaraxListView = (PallaraxListView) findViewById(R.id.lv);
        View layout_header = getLayoutInflater().inflate(R.layout.header_layout, null);
        final ImageView imageview = (ImageView) layout_header.findViewById(R.id.iv);
        myPallaraxListView.addHeaderView(layout_header);

        //在oncreate方法中 获取控件的高度
        //1.getViewTreeObserver 2.measure  3.layout队列  post
        myPallaraxListView.post(new Runnable() {// layout队列后
            @Override
            public void run() {
                myPallaraxListView.setHeaderImage(imageview);
            }
        });
        myPallaraxListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cheeses.NAMES));
    }

}
