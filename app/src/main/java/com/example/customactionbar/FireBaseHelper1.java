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

public class FireBaseHelper1 extends MultiDexApplication {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public boolean addData(String name,String date,String time){

        Map<String, Object> user = new HashMap<>();

        user.put("name",name);
        user.put("date", date);
        user.put("time", time);


// Add a new document with a generated ID
        db.collection("doctors")
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


        db.collection("doctors1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {



                    }
                });


        return true;
    }
}
