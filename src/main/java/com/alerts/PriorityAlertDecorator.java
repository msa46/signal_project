package com.alerts;

public class PriorityAlertDecorator extends AlertDecorator {
    private final int priorityIncrease;

    public PriorityAlertDecorator(BasicAlert decoratedAlert, int priorityIncrease) {
        super(decoratedAlert);
        this.priorityIncrease = priorityIncrease;
    }

    @Override
    public void send(String message) {
        String priorityMessage = "[PRIORITY LEVEL " + getPriority() + "] " + message;
        decoratedAlert.send(priorityMessage);
    }

    @Override
    public int getPriority() {
        return decoratedAlert.getPriority() + priorityIncrease;
    }
}
