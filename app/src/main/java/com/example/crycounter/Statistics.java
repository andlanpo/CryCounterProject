package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Statistics extends AppCompatActivity {
    LineChart lineChart;
    LineData lineData;
    List<Entry> entryList = new ArrayList<>();
    public static Profile current;
    private FirebaseFirestore db;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    BarChart barChartLocation;
    BarData barDataLocation;
    BarDataSet barDataSetLocation;
    ArrayList<String> barLocationX;
    ArrayList<BarEntry> barEntriesLocations;
    ArrayList<String> barStressorX;
    ArrayList<Integer> barStressorValues;
    ArrayList<BarEntry> barEntries;
    FireStoreHelper dbHelper;
    SharedPreferences sharedPreferences;
    public static final String THEME_VAL = "THEME";

    private int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("THEME_PREF",MODE_PRIVATE);
        theme = sharedPreferences.getInt(THEME_VAL, 0);
        if(theme == 0){
            setTheme(R.style.Evermore);
        }
        else if(theme == 1){
            setTheme(R.style.SunsetSeason);
        }
        else if(theme == 2){
            setTheme(R.style.Punisher);
        }
        else if(theme == 3){
            setTheme(R.style.Multiply);
        }
        //Add theme stuff before this line from Themes.java
        dbHelper = new FireStoreHelper();
        Intent intent = getIntent();
        current = intent.getParcelableExtra("profiles");
        TextView text4 = findViewById(R.id.likelyHour);
        TextView text5 = findViewById(R.id.likelyDay);
        TextView text6 = findViewById(R.id.likelyLocation);

        setContentView(R.layout.activity_statistics);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Line chart
        ArrayList<Integer> months = new ArrayList<Integer>();
        ArrayList<Cry> cries = current.getCries();
        for (int i = 0; i < cries.size(); i++) {
            //get the amount of cries in each month
            months.add(cries.get(i).getMonth());
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

        for (int month : months) {
            if (month == 1) {
                jan++;
            } else if (month == 2) {
                feb++;
            } else if (month == 3) {
                mar++;
            } else if (month == 4) {
                apr++;
            } else if (month == 5) {
                may++;
            } else if (month == 6) {
                jun++;
            } else if (month == 7) {
                jul++;
            } else if (month == 8) {
                aug++;
            } else if (month == 9) {
                sep++;
            } else if (month == 10) {
                oct++;
            } else if (month == 11) {
                nov++;
            } else if (month == 12) {
                feb++;
            }
        }


        lineChart = findViewById(R.id.lineChart);
        entryList.add(new Entry(1, jan));
        entryList.add(new Entry(2, feb));
        entryList.add(new Entry(3, mar));
        entryList.add(new Entry(4, apr));
        entryList.add(new Entry(5, may));
        entryList.add(new Entry(6, jun));
        entryList.add(new Entry(7, jul));
        entryList.add(new Entry(8, aug));
        entryList.add(new Entry(9, sep));
        entryList.add(new Entry(10, oct));
        entryList.add(new Entry(11, nov));
        entryList.add(new Entry(12, dec));


        LineDataSet lineDataSet = new LineDataSet(entryList, "");
        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        lineDataSet.setFillAlpha(110);
        lineDataSet.setValueTextColor(Color.WHITE);
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.animateY(1500);
        lineChart.setVisibleXRangeMaximum(10);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.getAxisRight().setTextColor(Color.WHITE);
        lineChart.getDescription().setText("");
        lineChart.invalidate();

        //https://learntodroid.com/how-to-display-a-line-chart-in-your-android-app/
        //https://geekstocode.com/line-chart-in-android-studio/
        //https://www.codingdemos.com/draw-android-line-chart/ (Look at this)


        //Stressor bar chart
        //https://www.tutorialspoint.com/how-to-use-bar-chart-graph-in-android
        barData= new BarData();
        barEntries = new ArrayList<BarEntry>();
        //Initializes the bar chart
        barChart = (BarChart) findViewById(R.id.barChart);

        int notInList = 0;
        ArrayList<BarGraphStressor> listStressors = new ArrayList<BarGraphStressor>();
        for(int i = 0; i < current.getCries().size(); i++){
            notInList = 0;
           for(int j = 0; j < listStressors.size(); j++){
                if(current.getCries().get(i).getStressor().equals(listStressors.get(j).getStressorName())){
                    listStressors.get(j).updateAmount();
                }
                else {
                    notInList++;
                   // Kept track of how many times else executed
                    // if this count is = to size of array, then you know it isn't found and after for loop
                    // you add it
                }
            }
            if (listStressors.size() == 0) {
                // this is the first element, it must be added
                listStressors.add(new BarGraphStressor(current.getCries().get(i).getStressor(), 1));
            }
            if(notInList == listStressors.size()){
                listStressors.add(new BarGraphStressor(current.getCries().get(i).getStressor(), 1));
            }
        }

        for(int i = 0; i < listStressors.size(); i++){
            barEntries.add(new BarEntry(i, listStressors.get(i).getStressorAmount())); //Had the parameters switched https://www.youtube.com/watch?v=sXo2SkX7rGk
        }
        barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        barDataSet.setValueTextSize(16f);
        barStressorX = new ArrayList<String>();
        for(int i =0; i < listStressors.size(); i++){
            barStressorX.add(listStressors.get(i).getStressorName());
        }

        barData = new BarData(barDataSet); //https://www.youtube.com/watch?v=vhKtbECeazQ&t=750s
        barChart.setFitBars(true);
        barChart.setData(barData);
        XAxis xAxisStressor = barChart.getXAxis(); //https://www.youtube.com/watch?v=sXo2SkX7rGk
        xAxisStressor.setValueFormatter(new IndexAxisValueFormatter(barStressorX));
        xAxisStressor.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisStressor.setDrawGridLines(false);
        xAxisStressor.setDrawGridLines(false);
        xAxisStressor.setGranularity(1f);
        xAxisStressor.setLabelCount(barStressorX.size());
        xAxisStressor.setTextColor(Color.WHITE);
        xAxisStressor.setLabelRotationAngle(270);
        xAxisStressor.setAxisLineColor(300);
        barChart.animateY(1500);
        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getAxisRight().setTextColor(Color.WHITE);
        barChart.getDescription().setText("");
        barChart.setTouchEnabled(true);


        //Locations bar chart
        barDataLocation = new BarData();
        barEntriesLocations = new ArrayList<BarEntry>();
        barChartLocation = (BarChart) findViewById(R.id.barChartLocation);
        ArrayList<String> barLocationNames = new ArrayList<String>();
        for (int i = 0; i < cries.size(); i++) {
            barLocationNames.add(cries.get(i).getLocation());
        }
        Collections.sort(barLocationNames); //https://beginnersbook.com/2013/12/how-to-sort-arraylist-in-java/

        int notInLocations = 0;
        ArrayList<BarGraphStressor> listLocations = new ArrayList<BarGraphStressor>();
        for(int i = 0; i < current.getCries().size(); i++){
            notInLocations = 0;
            for(int j = 0; j < listLocations.size(); j++){
                if(current.getCries().get(i).getLocation().equals(listLocations.get(j).getStressorName())){
                    listLocations.get(j).updateAmount();
                }
                else {
                    notInLocations++;
                    // Kept track of how many times else executed
                    // if this count is = to size of array, then you know it isn't found and after for loop
                    // you add it
                }
            }
            if (listLocations.size() == 0) {
                // this is the first element, it must be added
                listLocations.add(new BarGraphStressor(current.getCries().get(i).getLocation(), 1));
            }
            if(notInLocations == listLocations.size()){
                listLocations.add(new BarGraphStressor(current.getCries().get(i).getLocation(), 1));
            }
        }
        for(int i = 0; i < listLocations.size(); i++){
            barEntriesLocations.add(new BarEntry(i,listLocations.get(i).getStressorAmount()));
        }
        barDataSetLocation = new BarDataSet(barEntriesLocations, "");
        barDataSetLocation.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSetLocation.setValueTextColor(Color.WHITE);
        barDataSetLocation.setValueTextSize(16f);
        barLocationX = new ArrayList<String>();
        for(int i =0; i < listLocations.size(); i++){
            barLocationX.add(listLocations.get(i).getStressorName());
        }
        barDataLocation = new BarData(barDataSetLocation); //https://www.youtube.com/watch?v=vhKtbECeazQ&t=750s
        barChartLocation.setFitBars(true);
        barChartLocation.setData(barDataLocation);
        XAxis xAxisLocation = barChartLocation.getXAxis();
        xAxisLocation.setValueFormatter(new IndexAxisValueFormatter(barLocationX));
        xAxisLocation.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisLocation.setDrawGridLines(false);
        xAxisLocation.setDrawGridLines(false);
        xAxisLocation.setGranularity(1f);
        xAxisLocation.setLabelCount(barLocationX.size());
        xAxisLocation.setTextColor(Color.WHITE);
        xAxisLocation.setLabelRotationAngle(270);
        barChartLocation.setTouchEnabled(true);
        barChartLocation.animateY(1500);
        barChartLocation.getAxisLeft().setAxisMinimum(0);
        barChartLocation.getAxisRight().setAxisMinimum(0);
        barChartLocation.getAxisLeft().setTextColor(Color.WHITE);
        barChartLocation.getAxisRight().setTextColor(Color.WHITE);
        barChartLocation.getDescription().setText("");
        mostLikelyToHour();
        mostLikelyToDay();
        mostLikelyToLocation();

    }

    public void mostLikelyToHour(){
        ArrayList<Integer> cryHours = new ArrayList<Integer>();
        for(int i = 0; i < current.getCries().size(); i++){
            cryHours.add(current.getCries().get(i).getHour());
        }
        Collections.sort(cryHours);
        int index = 0;
        int countHighest = 0;
        int count = 0;
        int hour = cryHours.get(0);
        while(index < cryHours.size()-1){
            if(cryHours.get(index) == cryHours.get(index + 1)){
                count++;
            }
            else if (countHighest < count){
                countHighest = count;
                hour = cryHours.get(index);
                count = 0;
            }
            else{
                count = 0;
            }
            index++;
        }
        TextView text4 = findViewById(R.id.likelyHour);
        if(hour > 12){
            text4.setText("The time you cry the most is: \n " + hour + "PM");
        }
        else{
            text4.setText("The time you cry the most is: \n " + hour + "AM");
        }

    }

    public void mostLikelyToDay(){
        ArrayList<String> cryStressors = new ArrayList<String>();
        for(int i = 0; i < current.getCries().size(); i++){
            cryStressors.add(current.getCries().get(i).getStressor());
        }
        Collections.sort(cryStressors);
        int index = 0;
        int countHighest = 0;
        int count = 0;
        String stressor = cryStressors.get(0);
        while(index < cryStressors.size()-1){
            if(cryStressors.get(index).equals(cryStressors.get(index + 1))){
                count++;
            }
            else if (countHighest < count){
                countHighest = count;
                stressor = cryStressors.get(index);
                count = 0;
            }
            else{
                count = 0;
            }
            index++;
        }
        TextView text5 = findViewById(R.id.likelyDay);
        text5.setText("Your biggest stressor is: \n" + stressor);

    }

    public void mostLikelyToLocation(){
        ArrayList<String> cryLocations = new ArrayList<String>();
        for(int i = 0; i < current.getCries().size(); i++){
            cryLocations.add(current.getCries().get(i).getLocation());
        }
        Collections.sort(cryLocations);
        int index = 0;
        int countHighest = 0;
        int count = 0;
        String location = cryLocations.get(0);
        while(index < cryLocations.size()-1){
            if(cryLocations.get(index).equals(cryLocations.get(index + 1))){
                count++;
            }
            else if (countHighest < count){
                countHighest = count;
                location = cryLocations.get(index);
                count = 0;
            }
            else{
                count = 0;
            }
            index++;
        }
        TextView text6 = findViewById(R.id.likelyLocation);
        text6.setText("You cry the most at: \n" + location);

    }

}