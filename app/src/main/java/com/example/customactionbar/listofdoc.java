package com.example.customactionbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.time.*;
import java.util.ArrayList;


public class listofdoc extends AppCompatActivity implements RVAdapter.OnNoteListener {
    private ArrayList<Person> persons;
    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listofdoc);
        final String i = getIntent().getStringExtra("EXTRA_SESSION_ID");
        TextView t = (TextView) findViewById(R.id.tbt);

        t.setText(i);

        persons = new ArrayList<>();
        LocalDateTime np = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            np = LocalDateTime.now();
        }


        persons.add(new Person("Emma Wilson", "35 years old", "Fortis",np));
        persons.add(new Person("Janet Willy", "54 years old", "Fortis",np));
        persons.add(new Person("Brie Jackson", "34 years old", "Fortis",np));
        persons.add(new Person("Harvey Spectre", "35 years old", "Fortis",np));
        persons.add(new Person("Pamela Halpert", "54 years old", "Fortis",np));
        persons.add(new Person("Charlie Harper", "34 years old", "Fortis",np));
        persons.add(new Person("Jim Halpert", "35 years old", "Fortis",np));
        persons.add(new Person("Dwigth Schrute", "54 years old", "Fortis",np));
        persons.add(new Person("Alan Harper", "34 years old", "Fortis",np));



        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        adapter = new RVAdapter(persons,this);
        rv.setAdapter(adapter);


    }

    @Override
    public void onNoteClick(int position) {
        persons.get(position);
        Intent i = new Intent(this, Main5Activity.class);
        i.putExtra("EXTRA_SESSION_ID", persons.get(position).getItemName());
        startActivity(i);
    }
}
