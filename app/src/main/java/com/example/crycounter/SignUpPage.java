package com.example.crycounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private FireStoreHelper dbHelper;
    SharedPreferences sharedPreferences;
    public static final String THEME_VAL = "THEME";
    private int theme;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("THEME_PREF",MODE_PRIVATE);
        theme = sharedPreferences.getInt(THEME_VAL, 0);
        if(theme == 0){
            setTheme(R.style.Evermore);
        }
        else if(theme == 1){
            setTheme(R.style.SunsetSeason);
        }
        else if(theme == 2){
            setTheme(R.style.Punisher);
        }
        else if(theme == 3){
            setTheme(R.style.Multiply);
        }
        setContentView(R.layout.activity_sign_up_page);
        dbHelper = new FireStoreHelper();
        mAuth = FirebaseAuth.getInstance();

        emailET = findViewById(R.id.editTextEmail);
        passwordET = findViewById(R.id.editTextPassword);

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

                                Profile profile = new Profile(user.getUid());
                                profile.setUID(user.getUid());
                                dbHelper.addProfile(profile, user.getUid());
                                intent.putExtra("profiles", profile);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.i("Denna", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpPage.this, "Sign Up failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
       // }
    }
}