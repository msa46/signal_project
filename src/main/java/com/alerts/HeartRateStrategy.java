package com.alerts;

import com.data.HealthData;

// Concrete strategy for heart rate monitoring
public class HeartRateStrategy implements AlertStrategy {
    private final int maxHeartRate;
    private final int minHeartRate;
    
    public HeartRateStrategy(int maxHeartRate, int minHeartRate) {
        this.maxHeartRate = maxHeartRate;
        this.minHeartRate = minHeartRate;
    }
    
    @Override
    public boolean checkAlert(HealthData data) {
        int heartRate = data.getHeartRate();
        return heartRate > maxHeartRate || heartRate < minHeartRate;
    }
}
