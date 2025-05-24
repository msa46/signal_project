package com.data;

// A helper class to hold health data metrics for a patient.
public class HealthData {
    private int systolicPressure;
    private int diastolicPressure;
    private int heartRate;
    private int oxygenSaturation;
    
    // Constructor
    public HealthData(int systolicPressure, int diastolicPressure, int heartRate, int oxygenSaturation) {
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.oxygenSaturation = oxygenSaturation;
    }
    
    // Getters
    public int getSystolicPressure() { return systolicPressure; }
    public int getDiastolicPressure() { return diastolicPressure; }
    public int getHeartRate() { return heartRate; }
    public int getOxygenSaturation() { return oxygenSaturation; }
}

