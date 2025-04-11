package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOutputStrategy implements OutputStrategy {

    private static final Logger LOGGER = Logger.getLogger(FileOutputStrategy.class.getName());

    private String baseDirectory;

    public final ConcurrentHashMap<String, String> fileMap = new ConcurrentHashMap<>();

    public FileOutputStrategy(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error creating base directory: " + baseDirectory, e);
            return;
        }

        // Set the filePath variable
        String filePath = fileMap.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error writing to file " + filePath, e);
        }
    }
}