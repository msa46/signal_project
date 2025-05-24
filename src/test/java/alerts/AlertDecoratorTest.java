package com.alerts;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AlertDecoratorTest {

    @Test
    public void testBasicAlert() {
        BasicAlert alert = new BasicAlert(1, "alert");
        assertEquals(1, alert.getPriority());
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        alert.send("Test message");

        assertTrue(outContent.toString().contains("Sending basic alert to alert: Test message"));

        System.setOut(System.out);
    }
    
    @Test
    public void testPriorityAlertDecorator() {
        BasicAlert basicAlert = new BasicAlert(1, "alert");
        PriorityAlertDecorator priorityAlert = new PriorityAlertDecorator(basicAlert, 2);

        assertEquals(3, priorityAlert.getPriority());
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        priorityAlert.send("Emergency");
        
        assertTrue(outContent.toString().contains("[PRIORITY LEVEL 3] Emergency"));
        assertTrue(outContent.toString().contains("alert"));
        
        System.setOut(System.out);
    }
    
    @Test
    public void testRepeatedAlertDecorator() {
        BasicAlert basicAlert = new BasicAlert(1, "HQ");
        RepeatedAlertDecorator repeatedAlert = new RepeatedAlertDecorator(basicAlert, 30, 2);
        
        assertEquals(1, repeatedAlert.getPriority());
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        repeatedAlert.send("Warning");
        
        String output = outContent.toString();
        assertTrue(output.contains("Sending basic alert to HQ: Warning"));
        assertTrue(output.contains("Scheduling repeated alerts every 30 seconds"));
        assertTrue(output.contains("Will repeat alert in 30 seconds: Warning"));
        assertTrue(output.contains("Will repeat alert in 60 seconds: Warning"));
        
        System.setOut(System.out);
    }
    

}