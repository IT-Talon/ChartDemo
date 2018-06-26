package com.trust.chartdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyLineChart myLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLineChart = findViewById(R.id.chart);
        setData();
    }

    private void setData() {
        for (int i = 0; i < 15; i++) {
            float val = (float) (Math.random() * 50);
            myLineChart.addEntry(val);
        }
    }
}
