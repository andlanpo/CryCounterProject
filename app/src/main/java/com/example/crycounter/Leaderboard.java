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
        mAuth = FirebaseAuth.getInstance();
        dbHelper = new FireStoreHelper();
        db = FirebaseFirestore.getInstance();
        intent = getIntent();
        profile = intent.getParcelableExtra("profiles");
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
        setContentView(R.layout.activity_leaderboard);


        leaderboardObjectArrayList = dbHelper.getLeaderboardObjects();
        leaderboardObjectArrayList = merge(leaderboardObjectArrayList);
        TextView text1 = findViewById(R.id.textView5);
        text1.setText("1\t" + leaderboardObjectArrayList.get(0).toString());
        TextView text2 = findViewById(R.id.textView7);
        text2.setText("2\t" + leaderboardObjectArrayList.get(1).toString());
        TextView text3 = findViewById(R.id.textView8);
        text3.setText("3\t" + leaderboardObjectArrayList.get(2).toString());
        TextView text4 = findViewById(R.id.textView9);
        text4.setText("4\t" + leaderboardObjectArrayList.get(3).toString());
        TextView text5 = findViewById(R.id.textView6);
        text5.setText("5\t" + leaderboardObjectArrayList.get(4).toString());
        TextView text6 = findViewById(R.id.textView10);
        text6.setText("6\t" + leaderboardObjectArrayList.get(5).toString());
        TextView text7 = findViewById(R.id.textView11);
        text7.setText("7\t" + leaderboardObjectArrayList.get(6).toString());
        TextView text8 = findViewById(R.id.textView12);
        text8.setText("8\t" + leaderboardObjectArrayList.get(7).toString());
        TextView text9 = findViewById(R.id.textView13);
        text9.setText("9\t" + leaderboardObjectArrayList.get(8).toString());
        TextView text10 = findViewById(R.id.textView14);
        text10.setText("10\t" + leaderboardObjectArrayList.get(9).toString());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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