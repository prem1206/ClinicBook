package com.example.customactionbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);



        final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        final FireBaseHelper1 FBH = new FireBaseHelper1();
        TextView name = (TextView) findViewById(R.id.name123);
        name.setText("Dr. "+sessionId);




        Toast.makeText(this,sessionId,Toast.LENGTH_LONG).show();

        Button btn = (Button) findViewById(R.id.enter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = (EditText) findViewById(R.id.date);
                EditText time = (EditText) findViewById(R.id.time1);
                String d = date.getText().toString();
                String t = time.getText().toString();
                FBH.addData(sessionId,d,t);
                //FBH.addData(sessionId,date.getText().toString(),time.getText().toString());
            }
        });

        //
    }

    public void AddCalendarEvent(View view) {
        Calendar calendarEvent = Calendar.getInstance();
        Intent i = new Intent(Intent.ACTION_EDIT);
        i.setType("vnd.android.cursor.item/event");
        i.putExtra("beginTime", calendarEvent.getTimeInMillis());
        i.putExtra("allDay", true);
        i.putExtra("rule", "FREQ=YEARLY");
        i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
        i.putExtra("title", "Calendar Event");
        startActivity(i);
    }
}
