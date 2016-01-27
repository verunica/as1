package com.example.mateverunica.verunica_fueltrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // When Add New Log is clicked start new intent to go to AddNewLog.class
    public void newLog (View view){
        Intent intent = new Intent(this, AddNewLog.class);

        startActivity(intent);

    }

    // When View Log is clicked start new intent to go to ViewLog.class
    public void viewLog (View view){
        Intent intent = new Intent(this, ViewLog.class);

        startActivity(intent);
    }
}
