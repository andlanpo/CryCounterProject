package com.example.crycounter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class FireStoreHelper {
    private final FirebaseFirestore db;         // ref to entire database
    private CollectionReference profileRef;  // ref to profile collection only

    private String currentUID;

    // arraylist of all profiles in database
    public static ArrayList<Profile> profileArrayList= new ArrayList<>();
    FirebaseAuth mAuth;
    public static Profile current;
    private static String firstName;



    public FireStoreHelper() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        profileRef = db.collection("profiles");
        currentUID = mAuth.getCurrentUser().getUid();

        profileRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                // clear out the array list so that none of the events are duplicated in the display
                profileArrayList.clear();

                // this for each loop will get each Document Snapshot from the query, and one at a time,
                // convert them to an object of the Event class and then add them to the array list

                for (QueryDocumentSnapshot doc: value) {
                    Profile profile = doc.toObject(Profile.class);
                    profileArrayList.add(profile);
                }
            }
        });
    }

    /* You can add custom objects with Firestore as long as there is a public constructor
    that takes no arguments AND a public getter for each property.  Because we included these
    fields in the Event class, we can simply add an Event object and we don't have to use a Map object

     https://firebase.google.com/docs/firestore/manage-data/add-data?authuser=0
    */
    public void addProfile(Profile profile, String uid) {
        // use .add when you don't have a unique ID number for each document.  This will generate
        // sets
        db.collection("profiles").document(uid).set(profile);
    }

    public void deleteProfile(String uid) {
        db.collection("profiles").document(uid)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("Andrew", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Andrew", "Error deleting document", e);
                    }
                });

    }

    // set will override an existing Event object with this key
    // if one isn't available, then it will add the object.
    public void updateProfile(Profile profile) {
        db.collection("profiles").document(profile.getUID())
                .set(profile);
    }

    public void addCry(Cry cry){

        DocumentReference profileRef = db.collection("profiles").document(currentUID);
        profileRef.update("cries", FieldValue.arrayUnion(cry));
    }
    public String getCurrent(){
        return currentUID;
    }


    public String getFirstName(){
        return firstName;
    }





}


