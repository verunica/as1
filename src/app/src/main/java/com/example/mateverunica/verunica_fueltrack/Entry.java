package com.example.mateverunica.verunica_fueltrack;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by verunica on 1/20/16.
 */
public class Entry implements Serializable {

    //Initialize the class variables
    private String date;
    private String gasStation;
    private Float odometerReading;
    private String fuelGrade;
    private Float fuelAmount;
    private Float unitCost;

    //Constructor of the class
    public Entry(String date, String gasStation, Float odometerReading, String fuelGrade, Float fuelAmount, Float unitCost) {
        this.date = date;
        this.gasStation = gasStation;
        this.odometerReading = odometerReading;
        this.fuelGrade = fuelGrade;
        this.fuelAmount = fuelAmount;
        this.unitCost = unitCost;
    }

    // The setters and getters for the variables
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGasStation() {
        return gasStation;
    }

    public void setGasStation(String gasStation) {
        this.gasStation = gasStation;
    }

    public Float getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(Float odometerReading) {
        this.odometerReading = odometerReading;
    }

    public String getFuelGrade() {
        return fuelGrade;
    }

    public void setFuelGrade(String fuelGrade) {
        this.fuelGrade = fuelGrade;
    }

    public Float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }


}
