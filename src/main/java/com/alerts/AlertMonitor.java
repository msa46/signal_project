package com.alerts;

import com.data.HealthData;

// Alert context class that uses the strategies
public class AlertMonitor {
    private AlertStrategy strategy;
    
    public void setStrategy(AlertStrategy strategy) {
        this.strategy = strategy;
    }
    
    public boolean checkForAlert(HealthData data) {
        if (strategy == null) {
            throw new IllegalStateException("Alert strategy not set");
        }
        return strategy.checkAlert(data);
    }
}
