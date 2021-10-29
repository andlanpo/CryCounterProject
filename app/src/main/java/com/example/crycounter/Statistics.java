package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class Statistics extends AppCompatActivity {
    LineChart lineChart;
    LineData lineData;
    List<EntryString> entryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);


        lineChart = findViewById(R.id.lineChart);
        entryList.add(new EntryString("",20));
        entryList.add(new EntryString("",10));
        entryList.add(new EntryString("",31));
        entryList.add(new EntryString("",14));
//        LineDataSet lineDataSet = new LineDataSet(entryList,"country");
//        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        lineDataSet.setFillAlpha(110);
//        lineData = new LineData(lineDataSet);
//        lineChart.setData(lineData);
//        lineChart.setVisibleXRangeMaximum(10);
//        lineChart.invalidate();
    }

    //https://learntodroid.com/how-to-display-a-line-chart-in-your-android-app/
    //https://geekstocode.com/line-chart-in-android-studio/

    //https://www.codingdemos.com/draw-android-line-chart/ (Look at this)



}