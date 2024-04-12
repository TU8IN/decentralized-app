import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class BatteryLevel implements Behavior {
    private volatile boolean suppressed = false;

    public boolean takeControl() {
        // Returns true if the battery level is low
        return Battery.getVoltage() < 7.0;
    }

    public void suppress() {
        suppressed = true;  // Just notifies the action method by setting a field to true
    }

    public void action() {
        while (!suppressed) {
            // Flash the battery low message, and beeps
            LCD.drawString("Battery Low!", 0, 0);
            Sound.beep();
        }
        suppressed = false;
    }
}
