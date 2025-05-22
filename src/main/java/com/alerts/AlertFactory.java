package com.alerts;

public abstract class AlertFactory {
    
    // Factory method to be implemented by subclasses
    public abstract Alert createAlert(String patientId, String condition, long timestamp);
}

// Concrete factory for Blood Pressure alerts
class BloodPressureAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        if (condition.equals("High Diastolic")) {
            return new Alert(patientId, "High Blood Pressure Diastolic", timestamp);
        } else if (condition.equals("Low Diastolic")) {
            return new Alert(patientId, "Low Blood Pressure Diastolic", timestamp);
        } else if (condition.equals("High Systolic")) {
            return new Alert(patientId, "High Blood Pressure Systolic", timestamp);
        } else if (condition.equals("Low Systolic")) {
            return new Alert(patientId, "Low Blood Pressure Systolic", timestamp);
        } else if (condition.startsWith("Critical")) {
            return new Alert(patientId, "Critical Blood Pressure - " + condition, timestamp);
        } else {
            return new Alert(patientId, "Blood Pressure Alert - " + condition, timestamp);
        }
    }
}

// Concrete factory for Blood Oxygen alerts
class BloodOxygenAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        if (condition.equals("Critical")) {
            return new Alert(patientId, "Critical Blood Saturation", timestamp);
        } else if (condition.equals("High Difference")) {
            return new Alert(patientId, "High Blood Saturation Difference", timestamp);
        } else {
            return new Alert(patientId, "Blood Oxygen Alert - " + condition, timestamp);
        }
    }
}

// Concrete factory for ECG alerts
class ECGAlertFactory extends AlertFactory {
    @Override
    public Alert createAlert(String patientId, String condition, long timestamp) {
        return new Alert(patientId, "ECG Alert - " + condition, timestamp);
    }
}