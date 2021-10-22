package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

public class SignUpProfile extends AppCompatActivity {

    private boolean moreOrLess;
    private String firstName;
    private String lastName;
    private boolean privacy;

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

    public void saveInfo(View v){
        EditText editFirstName = findViewById(R.id.editFirstName);
        EditText editLastName = findViewById(R.id.editLastName);

        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();

        Profile user = new Profile(moreOrLess, firstName, lastName, privacy);

    }
}