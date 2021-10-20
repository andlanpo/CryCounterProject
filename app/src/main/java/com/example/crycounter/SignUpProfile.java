package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

    public SignUpProfile(boolean moreOrLess, String firstName, String lastName, boolean privacy) {
        this.moreOrLess = moreOrLess;
        this.firstName = firstName;
        this.lastName = lastName;
        this.privacy = privacy;
    }
}