package com.example.crycounter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class LogCry extends AppCompatActivity {
    FireStoreHelper dbHelper;     // ref to entire database
    public static Profile current;
    Spinner stressors;
    Spinner locations;
    Calendar cal;
    SimpleDateFormat simpleDateFormat;
    SimpleDateFormat simpleTimeFormat;
    private int dayOfWeek;
    private int dayOfMonth;
    private int month;
    private int year;
    private int hour;
    private int minute;
    String time;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_cry);
        dbHelper = new FireStoreHelper();
        Intent intent = getIntent();
        current = intent.getParcelableExtra("profiles");
        cal = Calendar.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            simpleDateFormat = new SimpleDateFormat("u-dd-MM-yyyy");
        }
            simpleTimeFormat = new SimpleDateFormat("HH-mm");
        String date = simpleDateFormat.format(cal.getTime());
        String[] values = date.split("-");
        dayOfWeek = Integer.parseInt(values[0]);
        dayOfMonth = Integer.parseInt(values[1]);
        month = Integer.parseInt(values[2]);
        year = Integer.parseInt(values[3]);


        time = simpleTimeFormat.format(cal.getTime());
        String[] val = time.split("-");
        hour = Integer.parseInt(val[0]);
        minute = Integer.parseInt(val[1]);

        // you need to have a list of data that you want the spinner to display
        ArrayList<String> locationsArray =  current.getLocations();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, locationsArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locations = (Spinner) findViewById(R.id.locations);
        locations.setAdapter(adapter);



        // you need to have a list of data that you want the spinner to display

        ArrayList<String> stressorsArray = current.getStressors();
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, stressorsArray);
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stressors = (Spinner) findViewById(R.id.stressors);
        stressors.setAdapter(sAdapter);

    }


    public void submit (View v){
        String stress = stressors.getItemAtPosition(stressors.getSelectedItemPosition()).toString();
        String locate = locations.getItemAtPosition(locations.getSelectedItemPosition()).toString();
        Cry newCry = new Cry(hour,minute, dayOfWeek, dayOfMonth, month, year, locate, stress);
        dbHelper.addCry(newCry); // add cry to profile
        Intent intent = new Intent(LogCry.this, MainActivity.class);
        Toast.makeText(getApplicationContext(),time,Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }
}