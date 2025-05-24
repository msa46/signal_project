package com.alerts;
import com.data.HealthData;

public class BloodPressureStrategy implements AlertStrategy {
    private final int systolicThresholdHigh;
    private final int systolicThresholdLow;
    private final int diastolicThresholdHigh;
    private final int diastolicThresholdLow;
    
    public BloodPressureStrategy(int systolicThresholdHigh, int systolicThresholdLow, 
                                int diastolicThresholdHigh, int diastolicThresholdLow) {
        this.systolicThresholdHigh = systolicThresholdHigh;
        this.systolicThresholdLow = systolicThresholdLow;
        this.diastolicThresholdHigh = diastolicThresholdHigh;
        this.diastolicThresholdLow = diastolicThresholdLow;
    }
    
    @Override
    public boolean checkAlert(HealthData data) {
        int systolic = data.getSystolicPressure();
        int diastolic = data.getDiastolicPressure();
        
        return systolic > systolicThresholdHigh || 
               systolic < systolicThresholdLow || 
               diastolic > diastolicThresholdHigh || 
               diastolic < diastolicThresholdLow;
    }
}
