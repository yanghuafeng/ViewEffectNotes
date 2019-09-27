package com.example.vieweffectnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.vieweffectnotes.view.MyTextView;
import com.example.vieweffectnotes.view.NormalView;
import com.example.vieweffectnotes.view.PaintView;


public class MainActivity extends AppCompatActivity {
    private RelativeLayout layout;
    private RelativeLayout.LayoutParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout)findViewById(R.id.layout);
        params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        NormalView normalView = new NormalView(this);
        layout.addView(normalView);


        params.setMargins(500,0,0,0);
        PaintView paintView = new PaintView(this);
        layout.addView(paintView,params);
        MyTextView myTextView = new MyTextView(this);
        layout.addView(myTextView,params);
    }
}
