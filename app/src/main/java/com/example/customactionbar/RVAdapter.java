package com.example.customactionbar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public  class RVAdapter extends RecyclerView.Adapter<RVAdapter.ExampleViewHolder>{
    private ArrayList<Person> person;
    private OnNoteListener mOnNoteListener;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CardView cv;
        TextView name;
        TextView description;
        TextView hospital;
        TextView date;
        OnNoteListener onNoteListener;
        @SuppressLint("ResourceType")
        public ExampleViewHolder(View itemView, OnNoteListener onNoteListener){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.layout.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
            hospital = (TextView) itemView.findViewById(R.id.hospital);
            date = (TextView) itemView.findViewById(R.id.date);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }
    public RVAdapter(ArrayList<Person> egperson,OnNoteListener onNoteListener){
        person = egperson;
        this.mOnNoteListener = onNoteListener;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mOnNoteListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder personViewHolder, int i) {
        Person persons =   person.get(i);
        personViewHolder.name.setText(persons.getItemName());
        personViewHolder.description.setText(persons.getItemDescription());
        personViewHolder.hospital.setText(persons.getItemHospital());
        personViewHolder.date.setText(String.valueOf(persons.getItemTime()));

    }

    @Override
    public int getItemCount() {
        return person.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}

/*
List<Person> persons;

    RVAdapter(List<Person> persons){
        this.persons = persons;
    }
    @Override
    public int getItemCount() {
        return persons.size();
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_listofdoc, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.name.setText(persons.get(i).getItemName());
        personViewHolder.description.setText(persons.get(i).getItemDescription());
        personViewHolder.hospital.setText(persons.get(i).getItemHospital());
        personViewHolder.date.setText((persons.get(i).getItemTime()).toString());

    }
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView description;
        TextView hospital;
        TextView date;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
            hospital = (TextView) itemView.findViewById(R.id.hospital);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
 */