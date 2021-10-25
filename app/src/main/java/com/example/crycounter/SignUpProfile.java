package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SignUpProfile extends AppCompatActivity {

    private boolean moreOrLess;
    private String firstName;
    private String lastName;
    private boolean privacy;
    private ArrayList<String> stressors = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile);

    }

    public void onRadioButtonClicked(View v){
        switch(v.getId())
        {
            case R.id.cryLess:
                moreOrLess = true;
                //
                break;
            case R.id.cryMore:
                //
                moreOrLess = false;
                break;
            case R.id.showCry:
                //
                privacy = false;
                break;
            case R.id.noShowCry:
                //
                privacy = true;
                break;
        }
    }

    public void addStressor(View v){
        EditText stressorText = (EditText)findViewById(R.id.addStressor);
        String stressor = stressorText.getText().toString();
        if(stressor.length() == 0){
            stressorText.setError("Field cannot be left blank.");
        }
        stressors.add(stressor);
        Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
        stressorText.setHint("Stressor");
    }

    public void addLocation(View v){
        EditText locationText = (EditText)findViewById(R.id.addLocation);
        String location = locationText.getText().toString();
        if(location.length() == 0){
            locationText.setError("Field cannot be left blank.");
        }
        locations.add(location);
        Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
        locationText.setHint("Location");
    }


    public void saveInfo(View v){
        EditText editFirstName = findViewById(R.id.editFirstName);
        EditText editLastName = findViewById(R.id.editLastName);

        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();

        Profile user = new Profile(moreOrLess, firstName, lastName, privacy);

    }
}