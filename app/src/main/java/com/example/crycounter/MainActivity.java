package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new FireStoreHelper();
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        profile = intent.getParcelableExtra("profiles");
        Log.i("Theme", profile.getTheme() + " ");
        if(profile.getTheme() == 0){
            setTheme(R.style.Evermore);
        }
        else if(profile.getTheme() == 1){
            setTheme(R.style.SunsetSeason);
        }
        else if(profile.getTheme() == 2){
            setTheme(R.style.Punisher);
        }
        else if(profile.getTheme() == 3){
            setTheme(R.style.Multiply);
        }

        setContentView(R.layout.activity_main);

        TextView cryCount = findViewById(R.id.cryCount);
        cryCount.setText("You Have Cried " + profile.getCries().size() + " Times");


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
                intent1.putExtra("profiles", profile);
                this.startActivity(intent1);
                return true;
            case R.id.themes:
                Intent intent2 = new Intent(this, Themes.class);
                intent2.putExtra("profiles", profile);
                this.startActivity(intent2);
                return true;
            case R.id.logOut:
                Intent intent5 = new Intent(this, LogInScreen.class);
                mAuth.signOut();
                this.startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToStats(View v){
        Intent intent = new Intent(this, Statistics.class);
        intent.putExtra("profiles", profile);
        startActivity(intent);
    }

    public void goToLeader(View v){
        Intent intent = new Intent(this, Leaderboard.class);
        intent.putExtra("profiles", profile);
        startActivity(intent);
    }

    public void setProfile(View v){
        DocumentReference docRef = db.collection("profiles").document(mAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                profile = documentSnapshot.toObject(Profile.class);
                Toast.makeText(getApplicationContext(),profile.getFirstName() + " profile set",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void displayALocation(View v){
        locations = profile.getLocations();
        stressors = profile.getStressors();
        Toast.makeText(getApplicationContext(),profile.getLocations().get(0) ,Toast.LENGTH_SHORT).show();

    }

}
