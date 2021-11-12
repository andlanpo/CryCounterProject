package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    public static final String THEME_VAL = "THEME";

    private int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new FireStoreHelper();
        db = FirebaseFirestore.getInstance();
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

        setContentView(R.layout.activity_main);

        TextView cryCount = findViewById(R.id.cryCount);
        cryCount.setText("Cry Count:\n  " + profile.getCries().size() + " Cries");


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


}
