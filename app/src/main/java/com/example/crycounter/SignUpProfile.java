package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    public void onStart() {
        super.onStart();
//        EditText
//        Profile user = new Profile()
    }
}