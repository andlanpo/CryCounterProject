package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//https://www.tutorialspoint.com/how-to-use-bar-chart-graph-in-android
public class Statistics extends AppCompatActivity {
    LineChart lineChart;
    LineData lineData;
    List<Entry> entryList = new ArrayList<>();
    Profile current;
    private FirebaseFirestore db;
    public static Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Intent intent = getIntent();
        current = intent.getParcelableExtra("profiles");

       ArrayList<Integer> months = new ArrayList<Integer>();
        ArrayList<Cry> cries = current.getCries();
        for(int i = 0; i < cries.size(); i++) {
            //get the amount of cries in each month
            months.set(i, cries.get(i).getMonth());
        }

        int jan = 0;
        int feb = 0;
        int mar = 0;
        int apr = 0;
        int may = 0;
        int jun = 0;
        int jul = 0;
        int aug = 0;
        int sep = 0;
        int oct = 0;
        int nov = 0;
        int dec = 0;

        for(int month : months){
            if(month == 1){
                jan++;
            }
            else if(month == 2){
                feb++;
            }
            else if(month == 3){
                mar++;
            }
            else if(month == 4){
                apr++;
            }
            else if(month == 5){
                may++;
            }
            else if(month == 6){
                jun++;
            }
            else if(month == 7){
                jul++;
            }
            else if(month == 8){
                aug++;
            }
            else if(month == 9){
                sep++;
            }
            else if(month == 10){
                oct++;
            }
            else if(month == 11){
                nov++;
            }
            else if(month == 12) {
                feb++;
            }
        }


        lineChart = findViewById(R.id.lineChart);
        entryList.add(new Entry(1,jan));
        entryList.add(new Entry(2,feb));
        entryList.add(new Entry(3,mar));
        entryList.add(new Entry(4,apr));
        entryList.add(new Entry(5,may));
        entryList.add(new Entry(6,jun));
        entryList.add(new Entry(7,jul));
        entryList.add(new Entry(8,aug));
        entryList.add(new Entry(9,sep));
        entryList.add(new Entry(10,oct));
        entryList.add(new Entry(11,nov));
        entryList.add(new Entry(12,dec));


        LineDataSet lineDataSet = new LineDataSet(entryList,"Months");
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setFillAlpha(110);
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setVisibleXRangeMaximum(10);
        lineChart.invalidate();
    }

    //https://learntodroid.com/how-to-display-a-line-chart-in-your-android-app/
    //https://geekstocode.com/line-chart-in-android-studio/

    //https://www.codingdemos.com/draw-android-line-chart/ (Look at this)



}