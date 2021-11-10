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

public class LogInScreen extends AppCompatActivity {
    EditText emailET, passwordET;
    TextView authStatusTV;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Default);
        setContentView(R.layout.activity_log_in_screen);

        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.logInEmail);
        passwordET = findViewById(R.id.logInPassword);
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
            Intent intent = new Intent(LogInScreen.this, LoadingScreen.class);
            startActivity(intent);
        }
        else {
            authStatusTV.setText("onStart reloaded and user is null");
            // Take any action needed here when screen loads and a user is NOT logged in
        }
    }

    public void handleAuthChange(View v) {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        Log.i("Denna",  email + " " + password);
        logIn(email, password);
    }

    public void logIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(LogInScreen.this, LoadingScreen.class);
                            startActivity(intent);
                            Log.i("Denna", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            authStatusTV.setText("Signed in " + user.getEmail());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Denna", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    public void signUpPage(View v){
        Intent intent3 = new Intent(this, SignUpPage.class);
        this.startActivity(intent3);
    }


}