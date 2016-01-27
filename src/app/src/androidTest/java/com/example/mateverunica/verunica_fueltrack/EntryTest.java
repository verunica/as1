package com.example.mateverunica.verunica_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by verunica on 1/26/16.
 */

// This class will be testing the functionality of the class Entry
// Tests the Setting and Getting functionality
public class EntryTest extends ActivityInstrumentationTestCase2{
    public EntryTest() {
        super(MainActivity.class);
    }

    public void testGetDate(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        String date = entry.getDate();

        assertEquals(date, "2016-12-11");
    }

    public void testSetDate(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        entry.setDate("2016-12-15");

        assertEquals(entry.getDate(), "2016-12-15");
    }

    public void testGetGasStation(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        String gasStation = entry.getGasStation();

        assertEquals(gasStation, "Costco");
    }

    public void testSetGasStation(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        entry.setGasStation("Esso");

        assertEquals(entry.getGasStation(), "Esso");
    }

    public void testGetOdometer(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        Float odometerReading = entry.getOdometerReading();

        assertEquals(odometerReading, 45.5f);
    }

    public void testSetOdometer(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        entry.setOdometerReading(55.5f);

        assertEquals(entry.getOdometerReading(), 55.5f);
    }

    public void testGetFuelGrade(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        String fuelGrade = entry.getFuelGrade();

        assertEquals(fuelGrade, "regular");
    }

    public void testSetFuelGrade(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        entry.setFuelGrade("premium");

        assertEquals(entry.getFuelGrade(), "premium");
    }

    public void testGetFuelAmount(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        Float fuelAmount = entry.getFuelAmount();

        assertEquals(fuelAmount, 500.000f);
    }

    public void testSetFuelAmount(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        entry.setFuelAmount(501.111f);

        assertEquals(entry.getFuelAmount(), 501.111f);
    }

    public void testGetUnitCost(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        Float unitCost = entry.getUnitCost();

        assertEquals(unitCost, 30.65f);
    }

    public void testSetUnitCost(){
        Entry entry = new Entry("2016-12-11", "Costco", 45.5f , "regular", 500.000f, 30.65f );

        entry.setUnitCost(35.65f);

        assertEquals(entry.getUnitCost(), 35.65f);
    }
}
