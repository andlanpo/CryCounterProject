package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class LogCry extends AppCompatActivity {
    FireStoreHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_cry);
        dbHelper = new FireStoreHelper();


    }

    //public void fillSpinner(List<String> s){
    //would be something like db.collection(Profile) and then get the stressors
    // }

    public void submit (View v){
    //int date = findViewById(R.id.calendar); something like this to extract the date
    //String stressor = findViewById(R.id.stressor); extract the stressor
    // String location = findViewById(R.id.location);
    // int time = findViewById(R.id.time);

            Cry newCry = new Cry(0, "a", "l", 0);
          dbHelper.addCry(newCry); // add cry to profile

    }

}