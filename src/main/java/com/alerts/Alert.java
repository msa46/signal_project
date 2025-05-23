package com.alerts;

// Represents an alert
public class Alert {
    private String patientId;
    private String condition;
    private long timestamp;

    public Alert(String patientId, String condition, long timestamp) {
        this.patientId = patientId;
        this.condition = condition;
        this.timestamp = timestamp;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getCondition() {
        return condition;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
    public String getMessage() {
        // Format the message based on the condition
        if (condition.equals("High Diastolic")) {
            return "High Blood Pressure Diastolic";
        } else if (condition.equals("Low Diastolic")) {
            return "Low Blood Pressure Diastolic";
        } else if (condition.equals("High Systolic")) {
            return "High Blood Pressure Systolic";
        } else if (condition.equals("Low Systolic")) {
            return "Low Blood Pressure Systolic";
        } else if (condition.equals("Critical Hypertension")) {
            return "Critical Blood Pressure - Critical Hypertension";
        } else if (condition.equals("Fluctuating")) {
            return "Blood Pressure Alert - Fluctuating";
        } else if (condition.equals("Critical")) {
            return "Critical Blood Saturation";
        } else if (condition.equals("High Difference")) {
            return "High Blood Saturation Difference";
        } else if (condition.equals("Decreasing Trend")) {
            return "Blood Oxygen Alert - Decreasing Trend";
        } else if (condition.equals("Irregular Rhythm")) {
            return "ECG Alert - Irregular Rhythm";
        } else if (condition.equals("Tachycardia")) {
            return "ECG Alert - Tachycardia";
        } else {
            // Default case for other types of alerts
            return condition;
        }
    }
}
