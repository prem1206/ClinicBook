package com.example.customactionbar;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


class User{
    public String firstname;
    public String lastname;
    public String password;
    public String email;
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String password,String fname,String lname) {
        this.firstname = fname;
        this.lastname = lname;
        this.password = password;
        this.email = email;
    }
}

public class Main2Activity extends AppCompatActivity  {


    EditText emailId , password1, password2,fname,lname;
    TextView view;
    Button btn;

    public  void signin(View view){
        Intent i = new Intent(Main2Activity.this,Main3Activity.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        emailId = findViewById(R.id.email);
        password1 = findViewById(R.id.pass);
        fname = findViewById(R.id.editText4);
        lname = findViewById(R.id.editText6);


        btn = findViewById(R.id.button);
        view = findViewById(R.id.view);
        SignUp();
        View();

    }
    public void SignUp(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password1.getText().toString();
                String fn = fname.getText().toString();
                String ln = lname.getText().toString();

                FireBaseHelper FBH = new FireBaseHelper();
                FBH.addData(fn,ln,email,pwd);







            }
        });

    }
    public void View() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent i = new Intent(Main2Activity.this,Main3Activity.class);
               startActivity(i);


            }
        });

    }




}
