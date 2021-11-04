package com.example.crycounter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        leaderboardObjectArrayList = dbHelper.getLeaderboardObjects();
        TextView text1 = findViewById(R.id.textView5);
        text1.setText(leaderboardObjectArrayList.get(0).toString());
        TextView text2 = findViewById(R.id.textView7);
        text2.setText(leaderboardObjectArrayList.get(1).toString());
        TextView text3 = findViewById(R.id.textView8);
        text3.setText(leaderboardObjectArrayList.get(2).toString());
        TextView text4 = findViewById(R.id.textView9);
        text4.setText(leaderboardObjectArrayList.get(3).toString());



    }
}