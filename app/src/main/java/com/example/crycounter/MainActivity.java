package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FireStoreHelper dbHelper;
    private FirebaseFirestore db;
    public static Profile profile;
    ArrayList<String> locations = new ArrayList<String>();
    ArrayList<String> stressors = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new FireStoreHelper();
        db = FirebaseFirestore.getInstance();

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        Button statistics = findViewById(R.id.statistics);
        Button leaderboard = findViewById(R.id.leaderboard);
        Button button = findViewById(R.id.button5);

        constraintLayout.setBackgroundColor(getResources().getColor(R.color.sunset_background));

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.sunset_navBar)));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.sunset_navBar));

        statistics.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
        leaderboard.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
        button.setBackgroundColor((getResources().getColor(R.color.sunset_button)));
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

    public void logCry(View v){
        Intent intent = new Intent(MainActivity.this, LogCry.class);
        intent.putExtra("profiles", profile);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                Intent intent1 = new Intent(this, SignUpProfile.class);
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
            case R.id.logIn:
                Intent intent4 = new Intent(this, LogInScreen.class);
                this.startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToStats(View v){
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    public void goToLeader(View v){
        Intent intent = new Intent(this, Leaderboard.class);
        startActivity(intent);
    }

    public void setProfile(View v){
        DocumentReference docRef = db.collection("profiles").document(dbHelper.getCurrent());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                profile = documentSnapshot.toObject(Profile.class);
                Toast.makeText(getApplicationContext(),profile.getFirstName() + "profile set",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void displayALocation(View v){
        locations = profile.getLocations();
        stressors = profile.getStressors();
        Toast.makeText(getApplicationContext(),profile.getLocations().get(0) ,Toast.LENGTH_SHORT).show();

    }

}
