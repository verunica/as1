package com.example.mateverunica.verunica_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Mate Verunica on 1/14/2016.
 */

// This class allows the user to view the items in the listview
public class ViewLog extends AppCompatActivity{

    private ArrayList<Entry> entryArray;
    private ListView entryList;
    private static final String FILENAME = "file.sav";
    private TextView totalCostView;
    private Entry currEntry;
    private float totalCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_log_layout);

        entryList = (ListView) findViewById(R.id.listView);
        loadFromFile();
        EntryAdapter adapter = new EntryAdapter(this, entryArray);
        entryList.setAdapter(adapter);
        totalCostView = (TextView) findViewById(R.id.textView2);


        // Using Iterator to calculate the total cost
        Iterator arrayListIterator = entryArray.iterator();

        while(arrayListIterator.hasNext()){
            currEntry = (Entry) arrayListIterator.next();
            totalCost = totalCost + (currEntry.getFuelAmount() * (currEntry.getUnitCost() / 100 ));
        }
        totalCostView.setText(String.format("%.2f",totalCost));

        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterView, View view, int position, long id) {
                Entry editEntry = entryArray.get(position);
                saveInFile();
                Intent intent = new Intent(view.getContext(), EditLog.class);
                intent.putExtra("message", (Serializable) editEntry);
                intent.putExtra("arrayList", (Serializable) entryArray);
                intent.putExtra("position", position);

                startActivity(intent);



            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        totalCost = 0;
        Iterator arrayListIterator = entryArray.iterator();

        while(arrayListIterator.hasNext()){
            currEntry = (Entry) arrayListIterator.next();
            totalCost = totalCost + (currEntry.getFuelAmount() * (currEntry.getUnitCost() / 100 ));
        }
        totalCostView.setText(String.format("%.2f",totalCost));
        EntryAdapter adapter = new EntryAdapter(this, entryArray);
        entryList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Entry>>() {}.getType();
            entryArray = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            entryArray = new ArrayList<Entry>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(entryArray, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
