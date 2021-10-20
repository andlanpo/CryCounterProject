package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.melodrama_navBar)));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        window.setStatusBarColor(getResources().getColor(R.color.melodrama_navBar));

        Button statistics = findViewById(R.id.statistics);
        statistics.setBackgroundColor((getResources().getColor(R.color.melodrama_button)));

        Button leaderboard = findViewById(R.id.leaderboard);
        leaderboard.setBackgroundColor((getResources().getColor(R.color.melodrama_button)));

        Button button = findViewById(R.id.button5);
        button.setBackgroundColor((getResources().getColor(R.color.melodrama_button)));
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
        
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                Intent intent1 = new Intent(this, Profile.class);
                this.startActivity(intent1);
                return true;
            case R.id.themes:
                Intent intent2 = new Intent(this, Themes.class);
                this.startActivity(intent2);
                return true;
            case R.id.signUp:
                Intent intent3 = new Intent(this, SignUpPage.class);
                this.startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
