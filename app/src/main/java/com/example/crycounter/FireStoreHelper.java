package com.example.crycounter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class FireStoreHelper {
    private final FirebaseFirestore db;         // ref to entire database
    private CollectionReference profileRef;  // ref to profile collection only
    private CollectionReference cryRef; // ref to cry collection only

    // arraylist of all profiles in database
    private ArrayList<Profile> profileArrayList= new ArrayList<>();


    public FireStoreHelper() {
        db = FirebaseFirestore.getInstance();
        profileRef = db.collection("profiles");

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
    public void addProfile(Profile profile) {
        // use .add when you don't have a unique ID number for each document.  This will generate
        // one for you.  If you did have a unique ID number, then you would use set.
        db.collection("profiles")
                .add(profile) // adds an event without a key defined yet
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    // documentReference contains a reference to the newly created Document if done successfully
                    public void onSuccess(DocumentReference documentReference) {
                        db.collection("profiles").document(documentReference.getId())
                                .update("key", documentReference.getId());  // sets the DocID key for the Event that was just added
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Denna", "Error adding document", e);
                    }
                });

    }

    public void deleteProfile(String key) {
        db.collection("profiles").document(key)
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
        db.collection("profiles").document(profile.getKey())
                .set(profile);
    }

    public ArrayList<Profile> getProfileArrayList() {
        return profileArrayList;
    }
}


