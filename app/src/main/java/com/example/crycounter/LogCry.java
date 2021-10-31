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
import android.widget.Spinner;
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
    FireStoreHelper dbHelper;     // ref to entire database
    public static Profile current;
    Spinner stressors;
    Spinner locations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_cry);
        dbHelper = new FireStoreHelper();

        Intent intent = getIntent();
        current = intent.getParcelableExtra("profiles");
        // you need to have a list of data that you want the spinner to display
        ArrayList<String> locationsArray =  current.getLocations();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, locationsArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locations = (Spinner) findViewById(R.id.locations);
        locations.setAdapter(adapter);



        // you need to have a list of data that you want the spinner to display

        ArrayList<String> stressorsArray = current.getStressors();

        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, stressorsArray);

        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stressors = (Spinner) findViewById(R.id.stressors);
        stressors.setAdapter(sAdapter);

    }

    //public void fillSpinner(List<String> s){
    //would be something like db.collection(Profile) and then get the stressors
    // }

    public void submit (View v){
        String stress = stressors.getItemAtPosition(stressors.getSelectedItemPosition()).toString();
        String locate = locations.getItemAtPosition(locations.getSelectedItemPosition()).toString();
        Cry newCry = new Cry(0, stress, locate, 0);
        dbHelper.addCry(newCry); // add cry to profile

    }




}