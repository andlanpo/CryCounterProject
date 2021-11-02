package com.example.crycounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SignUpProfile extends AppCompatActivity {

    private boolean moreOrLess;
    private FirebaseFirestore db;         // ref to entire database
    private String firstName;
    private String lastName;
    private boolean privacy;
    private ArrayList<String> stressors = new ArrayList<>();
    private ArrayList<String> locations = new ArrayList<>();
    private FireStoreHelper dbHelper;
    public static Profile current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profile);
        dbHelper = new FireStoreHelper();
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        current = intent.getParcelableExtra("profiles");
        EditText firstNameField = (EditText) findViewById(R.id.editFirstName);
        firstNameField.setText(current.getFirstName());
        EditText lastNameField = (EditText) findViewById(R.id.editLastName);
        lastNameField.setText(current.getLastName());


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
    }


    public void saveInfo(View v){
        EditText editFirstName = findViewById(R.id.editFirstName);
        EditText editLastName = findViewById(R.id.editLastName);

        firstName = editFirstName.getText().toString();
        lastName = editLastName.getText().toString();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = currentUser.getUid();

        DocumentReference profileRef = db.collection("profiles").document(uid);
        for(String i: locations){
            profileRef.update("locations", FieldValue.arrayUnion(i));
        }

        for(String f: stressors){
            profileRef.update("stressors", FieldValue.arrayUnion(f));

        }
        profileRef.update("firstName", firstName);
        profileRef.update("lastName", lastName);
        profileRef.update("privacy", privacy);
        profileRef.update("moreOrLess", moreOrLess);
        Toast.makeText(getApplicationContext(),"Saved Profile",Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, LoadingScreen.class);
        this.startActivity(intent1);



    }
    /**
     * This method will be called to minimize the on screen keyboard in the Activity
     * When we get the current view, it is the view that has focus, which is the keyboard
     *
     * Source:  https://www.youtube.com/watch?v=CW5Xekqfx3I
     */
    private void closeKeyboard() {
        View view = this.getCurrentFocus();     // view will refer to the keyboard
        if (view != null ){                     // if there is a view that has focus
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}