package com.exmaple.android.idiomsdisplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    int count = 0;
    Temporary1 tempArray = new Temporary1();
    Control ccc = new Control();
    TextView engIdiom;
    TextView korIdiom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onNextButtonClick(View view) {
        setTexts();
        count++;
    }

    public void onPreviousButtonClick(View view) {
        count =-2;
        setTexts();

    }
    public void setTexts(){
        if(count%2!=0) count++;
        engIdiom = (TextView) findViewById(R.id.engIdiom);
        engIdiom.setText((String) ccc.list.get(count));
        korIdiom = (TextView) findViewById(R.id.korIdiom);
        korIdiom.setText(ccc.list.get(count + 1));

    }
}
