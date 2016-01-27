package com.example.mateverunica.verunica_fueltrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by verunica on 1/23/16.
 */

// Got this code from https://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView and modified on Jan 26th 2016
public class EntryAdapter extends ArrayAdapter<Entry> {
    public EntryAdapter(Context context, ArrayList<Entry> entries) {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Entry entry = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView gasStation = (TextView) convertView.findViewById(R.id.gasStation);
        TextView odometerReading = (TextView) convertView.findViewById(R.id.odometer);
        TextView fuelGrade = (TextView) convertView.findViewById(R.id.fuelGrade);
        TextView fuelAmount = (TextView) convertView.findViewById(R.id.fuelAmount);
        TextView unitCost = (TextView) convertView.findViewById(R.id.unitCost);
        // Populate the data into the template view using the data object

        date.setText("Date: " + entry.getDate());
        gasStation.setText("Gas Station: " + entry.getGasStation());
        odometerReading.setText("Odometer Reading: " + entry.getOdometerReading().toString());
        fuelGrade.setText("Fuel Grade: " + entry.getFuelGrade());
        fuelAmount.setText("Fuel Amount: " + entry.getFuelAmount().toString());
        unitCost.setText("Unit Cost: " + entry.getUnitCost().toString());

        // Return the completed view to render on screen
        return convertView;
    }
}