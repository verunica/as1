package com.example.mateverunica.verunica_fueltrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Mate Verunica on 1/14/2016.
 */
public class AddNewLog extends AppCompatActivity{

    private EditText dateText;
    private EditText gasStation;
    private EditText odometer;
    private EditText fuelGrade;
    private EditText fuelAmount;
    private EditText unitCost;
    private Button saveButton;
    private static final String FILENAME = "file.sav";
    private ArrayList<Entry> entryArrayList = new ArrayList<Entry>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_log_layout);

        dateText = (EditText) findViewById(R.id.editText);
        gasStation = (EditText) findViewById(R.id.editText2);
        odometer = (EditText) findViewById(R.id.editText3);
        fuelGrade = (EditText) findViewById(R.id.editText4);
        fuelAmount = (EditText) findViewById(R.id.editText5);
        unitCost = (EditText) findViewById(R.id.editText6);



        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // First Validate Format of entries
                setResult(RESULT_OK);
                Entry e = new Entry(dateText.getText().toString(), gasStation.getText().toString(), Float.valueOf(odometer.getText().toString()), fuelGrade.getText().toString(), Float.valueOf(fuelAmount.getText().toString()), Float.valueOf(unitCost.getText().toString()));
                entryArrayList.add(e);
                saveInFile();
                finish();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();

    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<Entry>>() {}.getType();
             entryArrayList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            entryArrayList = new ArrayList<Entry>();
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
            gson.toJson(entryArrayList, out);
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
