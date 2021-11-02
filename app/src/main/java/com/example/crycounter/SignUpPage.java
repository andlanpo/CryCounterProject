package com.example.crycounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpPage extends AppCompatActivity {
    EditText emailET, passwordET;
    TextView authStatusTV;
    private FireStoreHelper dbHelper;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        dbHelper = new FireStoreHelper();
        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.editTextEmail);
        passwordET = findViewById(R.id.editTextPassword);
        authStatusTV = findViewById(R.id.authStatusView);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            authStatusTV.setText("onStart reloaded and " + currentUser.getEmail() + " is logged in");
            // Take any action needed here when screen loads and a user is logged in
        }
        else {
            authStatusTV.setText("You are not currently signed up");
            // Take any action needed here when screen loads and a user is NOT logged in
        }
    }

    public void handleAuthChange(View v) {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        Log.i("Denna",  email + " " + password);

       signUp(email, password);


    }
    public void signUp(String email, String password) {
        Intent intent = new Intent(this, SignUpProfile.class);

        // If the email and password passed in are not null, then try to create a User
        // if (email != null && password != null) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("Denna", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                authStatusTV.setText("Signed up " + user.getEmail() + " successfully");
                                Profile profile = new Profile(user.getUid());
                                profile.setUID(user.getUid());
                                dbHelper.addProfile(profile, user.getUid());
                                intent.putExtra("profiles", profile);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("Denna", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpPage.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                authStatusTV.setText("Signed up - FAILED");
                            }
                        }
                    });
       // }
    }
}