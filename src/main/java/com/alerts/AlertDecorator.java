package com.alerts;

public abstract class AlertDecorator implements AlertInterface {
    protected BasicAlert decoratedAlert;

    public AlertDecorator(BasicAlert decoratedAlert) {
        this.decoratedAlert = decoratedAlert;
    }

    @Override
    public void send(String message) {
        decoratedAlert.send(message);
    }

    @Override
    public int getPriority() {
        return decoratedAlert.getPriority();
    }
}
