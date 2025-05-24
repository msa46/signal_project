package com.alerts;

import com.data.HealthData;
// Strategy interface
public interface AlertStrategy {
    boolean checkAlert(HealthData data);
}


