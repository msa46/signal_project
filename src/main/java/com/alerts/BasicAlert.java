package com.alerts;

public class BasicAlert implements AlertInterface {
    private final int priority;
    private final String recipient;

    public BasicAlert(int priority, String recipient) {
        this.priority = priority;
        this.recipient = recipient;
    }
    // A message based system for sending alerts. Again, This is not the way for the final alerts but in my mind the alerts should be sent TO someone.
    @Override
    public void send(String message) {
        System.out.println("Sending basic alert to " + recipient + ": " + message);
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
