package com.data_management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileDataReader implements DataReader {

    private final String directoryPath;

    /**
     * Constructs a FileDataReader with the specified directory path.
     * 
     * @param directoryPath the path to the directory containing the output files
     */
    public FileDataReader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public void readData(DataStorage dataStorage) throws IOException {
        File directory = new File(directoryPath);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IOException("Invalid directory: " + directoryPath);
        }

        // Iterate through all files in the directory
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                parseFile(file, dataStorage);
            }
        }
    }

    /**
     * Parses a single file and stores the data in the DataStorage.
     * 
     * @param file        the file to parse
     * @param dataStorage the storage where parsed data will be stored
     * @throws IOException if there is an error reading the file
     */
    private void parseFile(File file, DataStorage dataStorage) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Example line format: "Patient ID: 1, Timestamp: 1700000000000, Label: HeartRate, Data: 72.5"
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    int patientId = Integer.parseInt(parts[0].split(": ")[1]);
                    long timestamp = Long.parseLong(parts[1].split(": ")[1]);
                    String recordType = parts[2].split(": ")[1];
                    double measurementValue = Double.parseDouble(parts[3].split(": ")[1]);

                    // Add the parsed data to the DataStorage
                    dataStorage.addPatientData(patientId, measurementValue, recordType, timestamp);
                }
            }
        }
    }
}