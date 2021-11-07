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
        leaderboardObjectArrayList = merge(leaderboardObjectArrayList);
        TextView text1 = findViewById(R.id.textView5);
        text1.setText("1 " + leaderboardObjectArrayList.get(0).toString());
        TextView text2 = findViewById(R.id.textView7);
        text2.setText("2 " + leaderboardObjectArrayList.get(1).toString());
        TextView text3 = findViewById(R.id.textView8);
        text3.setText("3 " + leaderboardObjectArrayList.get(2).toString());
        TextView text4 = findViewById(R.id.textView9);
        text4.setText("4 " + leaderboardObjectArrayList.get(3).toString());
        TextView text5 = findViewById(R.id.textView6);
        text5.setText("5 " + leaderboardObjectArrayList.get(4).toString());
        TextView text6 = findViewById(R.id.textView10);
        text6.setText("6 " + leaderboardObjectArrayList.get(5).toString());
        TextView text7 = findViewById(R.id.textView11);
        text7.setText("7 " + leaderboardObjectArrayList.get(6).toString());
        TextView text8 = findViewById(R.id.textView12);
        text8.setText("8 " + leaderboardObjectArrayList.get(7).toString());
        TextView text9 = findViewById(R.id.textView13);
        text9.setText("9 " + leaderboardObjectArrayList.get(8).toString());
        TextView text10 = findViewById(R.id.textView14);
        text10.setText("10 " + leaderboardObjectArrayList.get(9).toString());



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

    private static ArrayList mergeSort(ArrayList<LeaderboardObject> left, ArrayList<LeaderboardObject> right, ArrayList<LeaderboardObject> whole) {
        int leftIndex = 0;
        int rightIndex = 0;
        int wholeIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if ( (left.get(leftIndex).getCryCount() > right.get(rightIndex).getCryCount() )) {
                whole.set(wholeIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                whole.set(wholeIndex, right.get(rightIndex) );
                rightIndex++;
            }
            wholeIndex++;
        }

        ArrayList<LeaderboardObject> rest;
        int restIndex;
        if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
            rest = right;
            restIndex = rightIndex;
        } else {
            // The right ArrayList has been used up...
            rest = left;
            restIndex = leftIndex;
        }

        for (int i=restIndex; i<rest.size(); i++) {
            whole.set(wholeIndex, rest.get(i) );
            wholeIndex++;
        }

        return whole;
    }

    public static ArrayList<LeaderboardObject> merge(ArrayList<LeaderboardObject> a)
    {
        int total = a.size();
        if(total < 2)
        {
            return a;
        }
        else
        {
            int mid = a.size() / 2;
            ArrayList<LeaderboardObject> left = new ArrayList<>();
            ArrayList<LeaderboardObject> right = new ArrayList<>();

            for(int i = 0; i < mid; i++)
            {
                left.add(a.get(i));
            }
            for(int i = mid; i < total; i ++)
            {
                right.add(a.get(i));
            }

            left = merge(left);
            right = merge(right);

            return mergeSort(left, right, a);
        }
    }




}