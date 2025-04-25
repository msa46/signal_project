package com.alerts;

import com.data_management.DataStorage;
import com.data_management.Patient;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data_management.PatientRecord;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data
 * and generating alerts when certain predefined conditions are met. This class
 * relies on a {@link DataStorage} instance to access patient data and evaluate
 * it against specific health criteria.
 */
public class AlertGenerator {
    private DataStorage dataStorage;

    /**
     * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}.
     * The {@code DataStorage} is used to retrieve patient data that this class
     * will monitor and evaluate.
     *
     * @param dataStorage the data storage system that provides access to patient
     *                    data
     */
    public AlertGenerator(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    /**
     * Evaluates the specified patient's data to determine if any alert conditions
     * are met. If a condition is met, an alert is triggered via the
     * {@link #triggerAlert}
     * method. This method should define the specific conditions under which an
     * alert
     * will be triggered.
     *
     * @param patient the patient data to evaluate for alert conditions
     */
    public void evaluateData(Patient patient) {
        List<PatientRecord> records = patient.getRecords(0, Instant.now().toEpochMilli());

        // Blood pressure Data alert
        // build as a map. Later on will pass this map to the needed function
        Map<String, Integer> bloodPressureTrends = new HashMap<>();
        bloodPressureTrends.put("increasingCountDiastolic", 0);
        bloodPressureTrends.put("decreasingCountDiastolic", 0);
        bloodPressureTrends.put("increasingCountSysstolic", 0);
        bloodPressureTrends.put("decreasingCountSysstolic", 0);
        double lastBloodPressureSystolic = 0;
        double lastBloodPressureDiastolic = 0;
        double lastBloodSaturation = 0;
        long lastBloodSaturationTime = 0;

        for (int i = 1; i < records.size(); i++) {
            String recordType = records.get(i).getRecordType();
            double measurementValue = records.get(i).getMeasurementValue();
            String patientId = records.get(i).getPatientId() + "";
            long timestamp = records.get(i).getTimestamp();
            if (recordType.equals("BloodPressureDiastolic")) {
                if (measurementValue > 120) {
                    Alert alert = new AlertFactory().createCriticalBloodPressureAlert(patientId, " High, Disastolic");
                    triggerAlert(alert);
                } else if (measurementValue < 60) {
                    Alert alert = new AlertFactory().createCriticalBloodPressureAlert(patientId, " Low, Disastolic");
                    triggerAlert(alert);
                }
                if (lastBloodPressureDiastolic == 0) {
                    lastBloodPressureDiastolic = measurementValue;
                } else if (measurementValue > lastBloodPressureDiastolic
                        && (measurementValue - lastBloodPressureDiastolic > 10)) {
                    bloodPressureTrends.put("decreasingCountDiastolic", 0);
                    bloodPressureTrends.put("increasingCountDiastolic", bloodPressureTrends.get("increasingCountDiastolic") + 1);
                    lastBloodPressureDiastolic = measurementValue;
                } else if (measurementValue < lastBloodPressureDiastolic
                        && (lastBloodPressureDiastolic - measurementValue > 10)) {
                    bloodPressureTrends.put("increasingCountDiastolic", 0);
                    bloodPressureTrends.put("decreasingCountDiastolic", bloodPressureTrends.get("decreasingCountDiastolic") + 1);
                    lastBloodPressureDiastolic = measurementValue;
                }
                if (bloodPressureTrends.get("increasingCountDiastolic") > 3) {
                    Alert alert = new AlertFactory().createHighBloodPressureDiastolicAlert(patientId);
                    triggerAlert(alert);
                } else if (bloodPressureTrends.get("decreasingCountDiastolic") > 3) {
                    Alert alert = new AlertFactory().createLowBloodPressureDiastolicAlert(patientId);
                    triggerAlert(alert);
                }
            } else if (recordType.equals("BloodPressureSystolic")) {
                if (measurementValue > 180) {
                    Alert alert = new AlertFactory().createCriticalBloodPressureAlert(patientId, " High, Systolic");
                    triggerAlert(alert);
                } else if (measurementValue < 90) {
                    Alert alert = new AlertFactory().createCriticalBloodPressureAlert(patientId, " Low, Systolic");
                    triggerAlert(alert);
                }
                if (lastBloodPressureDiastolic == 0) {
                    lastBloodPressureDiastolic = measurementValue;
                } else if (measurementValue > lastBloodPressureDiastolic
                        && (measurementValue - lastBloodPressureSystolic > 10)) {
                    bloodPressureTrends.put("decreasingCountSysstolic", 0);
                    bloodPressureTrends.put("increasingCountSysstolic", bloodPressureTrends.get("increasingCountSysstolic") + 1);
                    lastBloodPressureDiastolic = measurementValue;
                } else if (measurementValue < lastBloodPressureDiastolic
                        && (lastBloodPressureDiastolic - measurementValue > 10)) {
                    bloodPressureTrends.put("increasingCountSysstolic", 0);
                    bloodPressureTrends.put("decreasingCountSysstolic", bloodPressureTrends.get("decreasingCountSysstolic") + 1);
                    lastBloodPressureDiastolic = measurementValue;
                }
                if (bloodPressureTrends.get("increasingCountSysstolic") > 3) {
                    Alert alert = new AlertFactory().createHighBloodPressureSystolicAlert(patientId);
                    triggerAlert(alert);
                } else if (bloodPressureTrends.get("decreasingCountSysstolic") > 3) {
                    Alert alert = new AlertFactory().createLowBloodPressureSystolicAlert(patientId);
                    triggerAlert(alert);
                }
            }
            else if (recordType.equals("BloodSaturation")) {
                if (measurementValue < 92) {
                    Alert alert = new AlertFactory().createCriticalBloodSaturationAlert(patientId);
                    triggerAlert(alert);
                }
                if (lastBloodSaturation == 0) {
                    lastBloodSaturation = measurementValue;
                    lastBloodSaturationTime = timestamp;
                } else if ((lastBloodSaturation - measurementValue > 5) && (timestamp - lastBloodSaturationTime < 600) ){
                    Alert alert = new AlertFactory().createHighBloodSaturationDifference(patientId);
                    triggerAlert(alert);
                    lastBloodSaturation = measurementValue;
                    lastBloodSaturationTime = timestamp;
                } 
            }
        }
 
        // Implementation goes here
    }

    /**
     * Triggers an alert for the monitoring system. This method can be extended to
     * notify medical staff, log the alert, or perform other actions. The method
     * currently assumes that the alert information is fully formed when passed as
     * an argument.
     *
     * @param alert the alert object containing details about the alert condition
     */
    private void triggerAlert(Alert alert) {
        // Implementation might involve logging the alert or notifying staff
    }
}
