package com.example.customactionbar;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

import static android.content.ContentValues.TAG;

public class FireBaseHelper extends MultiDexApplication {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public boolean addData(String fname,String lname,String email,String password){

        Map<String, Object> user = new HashMap<>();
        user.put("fname", fname);
        user.put("lname", lname);
        user.put("email", email);
        user.put("password", password);


// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
        return true;
    }
    boolean flag = false;
    public boolean viewData(final String emai, final String passwor){


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String email = String.valueOf(document.get("email"));
                                String password = String.valueOf(document.get("password"));

                                Log.d(TAG, document.getId() + " => " + email);
                                Log.d(TAG, document.getId() + " => " + password);
                                Log.d(TAG, document.getId() + " => " + emai);
                                Log.d(TAG, document.getId() + " => " + passwor);



                                if(emai.compareToIgnoreCase(email)==0 && passwor.compareTo(password)==0 ){

                                    Log.d(TAG,"Email found");
                                    Log.d(TAG,"Password found");





                                }
                                else {
                                    Log.d(TAG,"Email not found");
                                    Log.d(TAG,"Password not found");
                                }


                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


        return true;
    }
}
