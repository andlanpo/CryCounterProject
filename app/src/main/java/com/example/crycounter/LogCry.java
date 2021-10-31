package com.example.crycounter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LogCry extends AppCompatActivity {
    FireStoreHelper dbHelper;
    private FirebaseFirestore db;         // ref to entire database
    private CollectionReference profileRef;
    private DocumentReference ref;
    EventListener listener;
    ArrayList<Profile> list;
    ArrayAdapter<String> adapter;
    public static Profile current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_cry);
        dbHelper = new FireStoreHelper();
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        current = intent.getParcelableExtra("profiles");
        Toast.makeText(getApplicationContext(),current.getFirstName() + "profile set",Toast.LENGTH_SHORT).show();















    }

    //public void fillSpinner(List<String> s){
    //would be something like db.collection(Profile) and then get the stressors
    // }

    public void submit (View v){
        Cry newCry = new Cry(0, "a", "l", 0);
        dbHelper.addCry(newCry); // add cry to profile

    }




}