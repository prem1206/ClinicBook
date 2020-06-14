package com.example.customactionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.content.ContentValues.TAG;

public class Main3Activity extends AppCompatActivity {

    EditText password , ussername;
    Button btn;
    boolean val ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final FireBaseHelper FBH = new FireBaseHelper();  //Can be commented as it is not using the helper

        password = findViewById(R.id.pass);
        ussername = findViewById(R.id.email);


       btn = findViewById(R.id.login_btn);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String passwor = password.getText().toString();
               final String emai = ussername.getText().toString();
               Log.i("Val",passwor+" "+emai);


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
                                           Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                           intent.putExtra("EXTRA_SESSION_ID", String.valueOf(document.get("fname"))+" "+String.valueOf(document.get("lname")));
                                           Log.d(TAG,document.getId()+String.valueOf(document.get("fname")));
                                           startActivity(intent);


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

           }
       });


    }
}
