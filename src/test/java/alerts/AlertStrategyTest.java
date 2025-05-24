package com.alerts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.data.HealthData;

public class AlertStrategyTest {
    // The tests were made with the consideration that the health data would be parsed into a Health data object(HealthData.java)
    @Test
    public void testBloodPressureStrategy() {
        // Normal blood pressure
        HealthData normalData = new HealthData(120, 80, 70, 98);
        BloodPressureStrategy strategy = new BloodPressureStrategy(140, 90, 90, 60);
        assertFalse(strategy.checkAlert(normalData));
        
        // High systolic pressure
        HealthData highSystolicData = new HealthData(150, 80, 70, 98);
        assertTrue(strategy.checkAlert(highSystolicData));
        
        // Low systolic pressure
        HealthData lowSystolicData = new HealthData(85, 80, 70, 98);
        assertTrue(strategy.checkAlert(lowSystolicData));
        
        // High diastolic pressure
        HealthData highDiastolicData = new HealthData(120, 95, 70, 98);
        assertTrue(strategy.checkAlert(highDiastolicData));
        
        // Low diastolic pressure
        HealthData lowDiastolicData = new HealthData(120, 55, 70, 98);
        assertTrue(strategy.checkAlert(lowDiastolicData));
    }
    
    @Test
    public void testHeartRateStrategy() {
        // Normal heart rate
        HealthData normalData = new HealthData(120, 80, 70, 98);
        HeartRateStrategy strategy = new HeartRateStrategy(100, 50);
        assertFalse(strategy.checkAlert(normalData));
        
        // High heart rate
        HealthData highHeartRateData = new HealthData(120, 80, 110, 98);
        assertTrue(strategy.checkAlert(highHeartRateData));
        
        // Low heart rate
        HealthData lowHeartRateData = new HealthData(120, 80, 45, 98);
        assertTrue(strategy.checkAlert(lowHeartRateData));
    }
    
    @Test
    public void testOxygenSaturationStrategy() {
        // Normal oxygen level
        HealthData normalData = new HealthData(120, 80, 70, 98);
        OxygenSaturationStrategy strategy = new OxygenSaturationStrategy(90);
        assertFalse(strategy.checkAlert(normalData));
        
        // Low oxygen level
        HealthData lowOxygenData = new HealthData(120, 80, 70, 88);
        assertTrue(strategy.checkAlert(lowOxygenData));
    }
    
    @Test
    public void testAlertMonitor() {
        AlertMonitor monitor = new AlertMonitor();
        HealthData data = new HealthData(120, 80, 70, 98);
        
        // Test with blood pressure strategy
        monitor.setStrategy(new BloodPressureStrategy(140, 90, 90, 60));
        assertFalse(monitor.checkForAlert(data));
        
        // Test with heart rate strategy
        monitor.setStrategy(new HeartRateStrategy(100, 50));
        assertFalse(monitor.checkForAlert(data));
        
        // Test with oxygen saturation strategy
        monitor.setStrategy(new OxygenSaturationStrategy(90));
        assertFalse(monitor.checkForAlert(data));
        
        // Test with abnormal data
        HealthData abnormalData = new HealthData(160, 95, 110, 85);
        monitor.setStrategy(new BloodPressureStrategy(140, 90, 90, 60));
        assertTrue(monitor.checkForAlert(abnormalData));
    }
}