package com.alerts;

public class BasicAlert implements AlertInterface {
    private final int priority;
    private final String recipient;

    public BasicAlert(int priority, String recipient) {
        this.priority = priority;
        this.recipient = recipient;
    }

    @Override
    public void send(String message) {
        System.out.println("Sending basic alert to " + recipient + ": " + message);
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
