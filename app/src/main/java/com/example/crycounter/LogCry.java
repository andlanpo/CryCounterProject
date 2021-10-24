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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_cry);

        LinearLayout linearLayout = findViewById(R.id.logCryLayout);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.punisher_background));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.punisher_navBar)));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.punisher_navBar));

        TextView whyCry = findViewById(R.id.whyCry);
        whyCry.setTextColor(getResources().getColor(R.color.punisher_navBar));

        TextView whereCry = findViewById(R.id.whereCry);
        whereCry.setTextColor(getResources().getColor(R.color.punisher_navBar));

        TextView howCry = findViewById(R.id.howCry);
        howCry.setTextColor(getResources().getColor(R.color.punisher_navBar));

        TextView whenCry = findViewById(R.id.whenCry);
        whenCry.setTextColor(getResources().getColor(R.color.punisher_navBar));


    }

    //public void fillSpinner(List<String> s){
    //would be something like db.collection(Profile) and then get the stressors
    // }

    //public void submitButton(View v){
    //int date = findViewById(R.id.calendar); something like this to extract the date
    //String stressor = findViewById(R.id.stressor); extract the stressor
    // String location = findViewById(R.id.location);
    // int time = findViewById(R.id.time);
    // Cry newCry = new Cry(date, stressor, location, time);
    // db.addEvent(newCry) // add cry to profile

    //  }

}