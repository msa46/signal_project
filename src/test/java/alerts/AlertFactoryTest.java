package com.alerts;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlertFactoryTest {

    @Test
    public void testBloodPressureAlertFactory() {
        AlertFactory factory = new BloodPressureAlertFactory();
        
        // Test high diastolic alert
        Alert highDiastolicAlert = factory.createAlert("P123", "High Diastolic", System.currentTimeMillis());
        assertEquals("P123", highDiastolicAlert.getPatientId());
        assertEquals("High Blood Pressure Diastolic", highDiastolicAlert.getMessage());
        
        // Test low diastolic alert
        Alert lowDiastolicAlert = factory.createAlert("P123", "Low Diastolic", System.currentTimeMillis());
        assertEquals("Low Blood Pressure Diastolic", lowDiastolicAlert.getMessage());
        
        // Test high systolic alert
        Alert highSystolicAlert = factory.createAlert("P123", "High Systolic", System.currentTimeMillis());
        assertEquals("High Blood Pressure Systolic", highSystolicAlert.getMessage());
        
        // Test low systolic alert
        Alert lowSystolicAlert = factory.createAlert("P123", "Low Systolic", System.currentTimeMillis());
        assertEquals("Low Blood Pressure Systolic", lowSystolicAlert.getMessage());
        
        // Test critical alert
        Alert criticalAlert = factory.createAlert("P123", "Critical Hypertension", System.currentTimeMillis());
        assertEquals("Critical Blood Pressure - Critical Hypertension", criticalAlert.getMessage());
        
        // Test generic alert
        Alert genericAlert = factory.createAlert("P123", "Fluctuating", System.currentTimeMillis());
        assertEquals("Blood Pressure Alert - Fluctuating", genericAlert.getMessage());
    }
    
    @Test
    public void testBloodOxygenAlertFactory() {
        AlertFactory factory = new BloodOxygenAlertFactory();
        
        // Test critical saturation alert
        Alert criticalAlert = factory.createAlert("P123", "Critical", System.currentTimeMillis());
        assertEquals("P123", criticalAlert.getPatientId());
        assertEquals("Critical Blood Saturation", criticalAlert.getMessage());
        
        // Test high difference alert
        Alert highDiffAlert = factory.createAlert("P123", "High Difference", System.currentTimeMillis());
        assertEquals("High Blood Saturation Difference", highDiffAlert.getMessage());
        
        // Test generic alert
        Alert genericAlert = factory.createAlert("P123", "Decreasing Trend", System.currentTimeMillis());
        assertEquals("Blood Oxygen Alert - Decreasing Trend", genericAlert.getMessage());
    }
    
    @Test
    public void testECGAlertFactory() {
        AlertFactory factory = new ECGAlertFactory();
        
        // Test ECG alert
        Alert ecgAlert = factory.createAlert("P123", "Irregular Rhythm", System.currentTimeMillis());
        assertEquals("P123", ecgAlert.getPatientId());
        assertEquals("ECG Alert - Irregular Rhythm", ecgAlert.getMessage());
        
        // Test another ECG alert
        Alert tachycardiaAlert = factory.createAlert("P123", "Tachycardia", System.currentTimeMillis());
        assertEquals("ECG Alert - Tachycardia", tachycardiaAlert.getMessage());
    }
}