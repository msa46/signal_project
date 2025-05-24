package com.alerts;

import com.data.HealthData;

// Concrete strategy for oxygen saturation monitoring
public class OxygenSaturationStrategy implements AlertStrategy {
    private final int minOxygenLevel;
    
    public OxygenSaturationStrategy(int minOxygenLevel) {
        this.minOxygenLevel = minOxygenLevel;
    }
    
    @Override
    public boolean checkAlert(HealthData data) {
        int oxygenLevel = data.getOxygenSaturation();
        return oxygenLevel < minOxygenLevel;
    }
}
