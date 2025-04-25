package com.alerts;

import com.data_management.Patient;

public class AlertFactory {
    
    public Alert createHighBloodPressureDiastolicAlert(String patientId) {
        return new Alert(patientId, "High Blood Pressure Diastolic", System.currentTimeMillis());
    }

    public Alert createLowBloodPressureDiastolicAlert(String patientId) {
        return new Alert( patientId, "Low Blood Pressure Diastolic", System.currentTimeMillis());
    }
        public Alert createHighBloodPressureSystolicAlert(String patientId) {
        return new Alert(patientId, "High Blood Pressure Systolic", System.currentTimeMillis());
    }

    public Alert createLowBloodPressureSystolicAlert(String patientId) {
        return new Alert( patientId, "Low Blood Pressure Systolic", System.currentTimeMillis());
    }

    public Alert createCriticalBloodPressureAlert(String patientId, String condition) {
        return new Alert(patientId, "Critical Blood Pressure -" + condition, System.currentTimeMillis());
    }
}
