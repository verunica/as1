package com.example.mateverunica.verunica_fueltrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by verunica on 1/23/16.
 */

public class EditLog extends AppCompatActivity{

    private EditText dateText;
    private EditText gasStation;
    private EditText odometer;
    private EditText fuelGrade;
    private EditText fuelAmount;
    private EditText unitCost;
    private Button saveButton;
    private Button cancelButton;
    private int position;
    private static final String FILENAME = "file.sav";
    private ArrayList<Entry> arrayList = new ArrayList<Entry>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_log);
        Intent i = getIntent();
        final Entry entry = (Entry) i.getSerializableExtra("message");
        arrayList = (ArrayList) i.getSerializableExtra("arrayList");
        position = (int) i.getIntExtra("position", 0);
        final Button saveButton = (Button) findViewById(R.id.saveButton_edit);
        Button cancelButton = (Button) findViewById(R.id.cancelButton_edit);

        dateText = (EditText) findViewById(R.id.editText_edit);
        gasStation = (EditText) findViewById(R.id.editText2_edit);
        odometer = (EditText) findViewById(R.id.editText3_edit);
        fuelGrade = (EditText) findViewById(R.id.editText4_edit);
        fuelAmount = (EditText) findViewById(R.id.editText5_edit);
        unitCost = (EditText) findViewById(R.id.editText6_edit);


        dateText.setText(entry.getDate());
        gasStation.setText(entry.getGasStation());
        odometer.setText(entry.getOdometerReading().toString());
        fuelGrade.setText(entry.getFuelGrade().toString());
        fuelAmount.setText(entry.getFuelAmount().toString());
        unitCost.setText(entry.getUnitCost().toString());




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if(!validation.validUnitCost(unitCost.getText().toString())) {
                    unitCost.setError("Please enter a number with 2 decimal places");
                    setter = false;
                }
                if(setter == true){
                    arrayList.remove(position);
                    Entry e = new Entry(dateText.getText().toString(), gasStation.getText().toString(), Float.valueOf(odometer.getText().toString()), fuelGrade.getText().toString(), Float.valueOf(fuelAmount.getText().toString()), Float.valueOf(unitCost.getText().toString()));
                    arrayList.add(position, e);
                    saveInFile();
                    finish();
                }
            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(arrayList, out);
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
