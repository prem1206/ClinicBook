package com.example.customactionbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.*;

import static android.content.ContentValues.TAG;

class Item {
    private String itemName;
    private String itemDescription;
    private String itemHospital;
    private LocalDateTime itemTime;

    public Item(String name, String description,String hospital,LocalDateTime time) {
        this.itemName = name;
        this.itemDescription = description;
        this.itemHospital = hospital;
        this.itemTime = time;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
    public String getItemHospital() {
        return itemHospital;
    }
    public LocalDateTime getItemTime(){
        return itemTime;
    }

}




class CustomListAdapter extends BaseAdapter {
    private Context context; //context
    private ArrayList<Item> items; //data source of the list adapter

    //public constructor
    public CustomListAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.layout_list_view_row_items, parent, false);
        }

        // get current item to be displayed
        Item currentItem = (Item) getItem(position);

        // get the TextView for item name and item description
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.text_view_item_name);
        TextView textViewItemDescription = (TextView)
                convertView.findViewById(R.id.text_view_item_description);
        TextView textViewItemHospital = (TextView)
                convertView.findViewById(R.id.text_view_item_hospital);
        TextView textViewItemDate = (TextView)
                convertView.findViewById(R.id.text_view_item_date);
        TextView textViewItemTime = (TextView)
                convertView.findViewById(R.id.text_view_item_time);

        //sets the text for item name and item description from the current item object
        textViewItemName.setText(currentItem.getItemName());
        textViewItemDescription.setText(currentItem.getItemDescription());
        textViewItemHospital.setText(currentItem.getItemHospital());

        LocalDateTime DateTime = currentItem.getItemTime();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = DateTime.format(myFormatObj);
        textViewItemDate.setText(formattedDate);


        DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("HH-mm");
        String formattedDate1 = DateTime.format(myFormatObj1);
        textViewItemTime.setText(formattedDate1);

        // returns the view for the current row
        return convertView;
    }
}




public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setting the title
        toolbar.setTitle("");
        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
        String fname = getIntent().getStringExtra("EXTRA_SESSION_ID");
        Log.d(TAG,fname);
        SearchView srch = (SearchView)findViewById(R.id.sch);

        TextView tv = (TextView) findViewById(R.id.textView);
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv.setText("Hello, "+fname+"!");

        // Setup the data source
        ArrayList<Item> items1 = new ArrayList<>(); // calls function to get items list

        LocalDateTime np = LocalDateTime.now();
        items1.add(new Item("Dr Sophia Richards","Oncologist","Fortis",np));
        items1.add(new Item("Dr Melissa Richards","Chiropractor","Fortis",np));

        // instantiate the custom list adapter
        CustomListAdapter adapter = new CustomListAdapter(this, items1);

        // get the ListView and attach the adapter
        ListView itemsListView  = (ListView) findViewById(R.id.ListView1);
        itemsListView.setAdapter(adapter);


        ArrayList<Item> items2 = new ArrayList<>(); // calls function to get items list

        items2.add(new Item("ABC","ABC","For",np));
        items2.add(new Item("ABC","ABC","For",np));

        // instantiate the custom list adapter
        CustomListAdapter adapter2 = new CustomListAdapter(this, items2);

        // get the ListView and attach the adapter
        ListView itemsListView2  = (ListView) findViewById(R.id.ListView2);
        itemsListView2.setAdapter(adapter);

        ImageView btn = (ImageView)findViewById(R.id.search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchView sc = (SearchView) findViewById(R.id.sch);
                Intent i = new Intent(v.getContext(),listofdoc.class);
                i.putExtra("EXTRA_SESSION_ID",sc.getQuery().toString());
                startActivity(i);

            }
        });






        /*
        listview1 = (ListView) findViewById(R.id.ListView1);
        listview2 = (ListView) findViewById(R.id.ListView2);


        ArrayList<String> list1 = new ArrayList<>();

        ArrayList<String> list2 = new ArrayList<>();

        list1.add("Dr. Patricia Simson");
        list1.add("Dr. Patricia Simson");
        list1.add("Dr. Patricia Simson");
        list1.add("Dr. Patricia Simson");

        ArrayAdapter  arr1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list1);


        list2.add("Dr. Patricia Simson");
        list2.add("Dr. Patricia Simson");
        list2.add("Dr. Patricia Simson");
        list2.add("Dr. Patricia Simson");

        ArrayAdapter  arr2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list2);
        listview1.setAdapter(arr1);
        listview2.setAdapter(arr2);


         */

    }
/*
    public void Search(final View view){
        final SearchView sch  = findViewById(R.id.sch);
        sch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

 */



}
