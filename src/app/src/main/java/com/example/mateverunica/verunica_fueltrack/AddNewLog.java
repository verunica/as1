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

// This class will take care of adding a new log to the arraylist
public class AddNewLog extends AppCompatActivity{


    // Initializing variables
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


        // Set the EditText boxes to variables
        dateText = (EditText) findViewById(R.id.editText);
        gasStation = (EditText) findViewById(R.id.editText2);
        odometer = (EditText) findViewById(R.id.editText3);
        fuelGrade = (EditText) findViewById(R.id.editText4);
        fuelAmount = (EditText) findViewById(R.id.editText5);
        unitCost = (EditText) findViewById(R.id.editText6);


        // Initialize the saveButton and then create a OnClickListener
        Button saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // First Validate Format of entries
                Boolean setter = true;
                setResult(RESULT_OK);
                Validation validation = new Validation();
                if(!validation.validDate(dateText.getText().toString())){
                    dateText.setError("Please add a valid date YYYY-MM-DD");
                    setter = false;
                }
                if(!validation.validGasStation(gasStation.getText().toString())){
                    gasStation.setError("Please enter text for the gas station");
                    setter = false;
                }
                if(!validation.validOdometer(odometer.getText().toString())){
                    odometer.setError("Please enter a number with one decimal place");
                    setter = false;
                }
                if(!validation.validFuelGrade(fuelGrade.getText().toString())){
                    fuelGrade.setError("Please enter text for the fuel grade");
                    setter = false;
                }
                if(!validation.validFuelAmount(fuelAmount.getText().toString())){
                    fuelAmount.setError("Please enter a number with 3 decimal places");
                    setter = false;
                }
                if(!validation.validUnitCost(unitCost.getText().toString())){
                    unitCost.setError("Please enter a number with 2 decimal places");
                    setter = false;
                }
                // If the input passes all validation tests then proceed to add to log
                // If fails then show errors and
                if (setter == true) {
                    Entry e = new Entry(dateText.getText().toString(), gasStation.getText().toString(), Float.valueOf(odometer.getText().toString()), fuelGrade.getText().toString(), Float.valueOf(fuelAmount.getText().toString()), Float.valueOf(unitCost.getText().toString()));
                    entryArrayList.add(e);
                    saveInFile();
                    finish();
                }
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
