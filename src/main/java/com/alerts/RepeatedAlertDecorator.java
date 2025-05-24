package com.alerts;

public class RepeatedAlertDecorator extends AlertDecorator {
    private final int repeatInterval; // in seconds
    private final int maxRepetitions;

    public RepeatedAlertDecorator(BasicAlert decoratedAlert, int repeatInterval, int maxRepetitions) {
        super(decoratedAlert);
        this.repeatInterval = repeatInterval;
        this.maxRepetitions = maxRepetitions;
    }

    @Override
    public void send(String message) {
        decoratedAlert.send(message);
        scheduleRepeatedAlerts(message);
    }

    private void scheduleRepeatedAlerts(String message) {
        System.out.println("Scheduling repeated alerts every " + repeatInterval +
                " seconds, up to " + maxRepetitions + " times");

        // Simulating the scheduling of repeated alerts
        // This would not be the way that repeated alerts would happen in a real application
        for (int i = 1; i <= maxRepetitions; i++) {
            System.out.println("Will repeat alert in " + (i * repeatInterval) + " seconds: " + message);
        }
    }
}
