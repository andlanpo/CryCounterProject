package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Themes extends AppCompatActivity {
    public static final String THEME_VAL = "THEME";
    FireStoreHelper dbHelper;
    public static Profile profile;
    SharedPreferences sharedPreferences;
    private int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new FireStoreHelper();

        Intent intent = getIntent();
        profile = intent.getParcelableExtra("profiles");


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
        setContentView(R.layout.activity_themes);
    }

    public void goBack(View v){
        Intent intent = new Intent(Themes.this, LoadingScreen.class);
        intent.putExtra("profiles", profile);
        startActivity(intent);
    }

    public void setTheme0(View v){
        dbHelper.setTheme(0);

        SharedPreferences sharedPreferences = getSharedPreferences("THEME_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(THEME_VAL, 0);
        editor.apply();

        ConstraintLayout constraintLayout = findViewById(R.id.ThemeLayout);
        Button sunsetSeason = findViewById(R.id.sunsetSeasonTheme);
        Button punisher = findViewById(R.id.punisherTheme);
        Button evermore = findViewById(R.id.evermoreTheme);
        Button multiply = findViewById(R.id.multiplyTheme);
        Button goBack = findViewById(R.id.goBack);

        constraintLayout.setBackgroundColor(getResources().getColor(R.color.evermore_background));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.evermore_button)));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.evermore_button));

        sunsetSeason.setBackgroundColor((getResources().getColor(R.color.evermore_button)));
        punisher.setBackgroundColor((getResources().getColor(R.color.evermore_button)));
        evermore.setBackgroundColor((getResources().getColor(R.color.evermore_button)));
        multiply.setBackgroundColor((getResources().getColor(R.color.evermore_button)));
        goBack.setBackgroundColor((getResources().getColor(R.color.evermore_button)));
    }
    public void setTheme1(View v){
        dbHelper.setTheme(1);

        SharedPreferences sharedPreferences = getSharedPreferences("THEME_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(THEME_VAL, 1);
        editor.apply();

        ConstraintLayout constraintLayout = findViewById(R.id.ThemeLayout);
        Button sunsetSeason = findViewById(R.id.sunsetSeasonTheme);
        Button punisher = findViewById(R.id.punisherTheme);
        Button evermore = findViewById(R.id.evermoreTheme);
        Button multiply = findViewById(R.id.multiplyTheme);
        Button goBack = findViewById(R.id.goBack);

        constraintLayout.setBackgroundColor(getResources().getColor(R.color.sunset_background));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.sunset_button)));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.sunset_button));

        sunsetSeason.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
        punisher.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
        evermore.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
        multiply.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
        goBack.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
    }

    public void setTheme2(View v){
        dbHelper.setTheme(2);

        SharedPreferences sharedPreferences = getSharedPreferences("THEME_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(THEME_VAL, 2);
        editor.apply();

        ConstraintLayout constraintLayout = findViewById(R.id.ThemeLayout);
        Button sunsetSeason = findViewById(R.id.sunsetSeasonTheme);
        Button punisher = findViewById(R.id.punisherTheme);
        Button evermore = findViewById(R.id.evermoreTheme);
        Button multiply = findViewById(R.id.multiplyTheme);
        Button goBack = findViewById(R.id.goBack);

        constraintLayout.setBackgroundColor(getResources().getColor(R.color.punisher_background));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.punisher_navBar)));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.punisher_navBar));

        sunsetSeason.setBackgroundColor((getResources().getColor(R.color.punisher_navBar)));
        punisher.setBackgroundColor((getResources().getColor(R.color.punisher_navBar)));
        evermore.setBackgroundColor((getResources().getColor(R.color.punisher_navBar)));
        multiply.setBackgroundColor((getResources().getColor(R.color.punisher_navBar)));
        goBack.setBackgroundColor((getResources().getColor(R.color.punisher_navBar)));
    }
    public void setTheme3(View v){
        dbHelper.setTheme(3);

        SharedPreferences sharedPreferences = getSharedPreferences("THEME_PREF",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(THEME_VAL, 3);
        editor.apply();

        ConstraintLayout constraintLayout = findViewById(R.id.ThemeLayout);
        Button sunsetSeason = findViewById(R.id.sunsetSeasonTheme);
        Button punisher = findViewById(R.id.punisherTheme);
        Button evermore = findViewById(R.id.evermoreTheme);
        Button multiply = findViewById(R.id.multiplyTheme);
        Button goBack = findViewById(R.id.goBack);

        constraintLayout.setBackgroundColor(getResources().getColor(R.color.multiply_background));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));

        sunsetSeason.setBackgroundColor((getResources().getColor(R.color.black)));
        punisher.setBackgroundColor((getResources().getColor(R.color.black)));
        evermore.setBackgroundColor((getResources().getColor(R.color.black)));
        multiply.setBackgroundColor((getResources().getColor(R.color.black)));
        goBack.setBackgroundColor((getResources().getColor(R.color.black)));
    }
}