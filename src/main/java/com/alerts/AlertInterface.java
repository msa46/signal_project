package com.alerts;

// Base Alert interface - Coded like this so it wouldn't affect the existing code structure
public interface AlertInterface {
    void send(String message);
    int getPriority();
}


