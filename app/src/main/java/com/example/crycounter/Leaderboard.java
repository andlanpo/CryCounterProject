package com.example.crycounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard extends AppCompatActivity {
    FireStoreHelper dbHelper;
    private FirebaseFirestore db;
    public static Profile profile;
    FirebaseAuth mAuth;
    Intent intent;
    ArrayList<LeaderboardObject> leaderboardObjectArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new FireStoreHelper();
        db = FirebaseFirestore.getInstance();
        intent = getIntent();
        profile = intent.getParcelableExtra("profiles");

        leaderboardObjectArrayList = dbHelper.getLeaderboardObjects();
        TextView text1 = findViewById(R.id.textView5);
        text1.setText(leaderboardObjectArrayList.get(0).toString());
        TextView text2 = findViewById(R.id.textView7);
        text2.setText(leaderboardObjectArrayList.get(1).toString());
        TextView text3 = findViewById(R.id.textView8);
        text3.setText(leaderboardObjectArrayList.get(2).toString());



    }

    public void mostLikelyToHour(View v){
        ArrayList<Integer> cryHours = new ArrayList<Integer>();
        for(int i = 0; i < profile.getCries().size(); i++){
            cryHours.add(profile.getCries().get(i).getHour());
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
        TextView text4 = findViewById(R.id.textView9);
        text4.setText("you are most likely to cry at " + hour);

    }

    public void mostLikelyToDay(View v){
        ArrayList<String> cryStressors = new ArrayList<String>();
        for(int i = 0; i < profile.getCries().size(); i++){
            cryStressors.add(profile.getCries().get(i).getStressor());
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
        TextView text5 = findViewById(R.id.textView8);
        text5.setText("you are most likely to cry at " + stressor);

    }

    public void mostLikelyToLocation(View v){
        ArrayList<String> cryLocations = new ArrayList<String>();
        for(int i = 0; i < profile.getCries().size(); i++){
            cryLocations.add(profile.getCries().get(i).getLocation());
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
        TextView text6 = findViewById(R.id.textView7);
        text6.setText("you are most likely to cry at " + location);

    }




}