import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {
    private volatile boolean suppressed = false;

    public boolean takeControl() {
        // Returns true if the ESCAPE button is pressed
        return Button.ESCAPE.isDown();
    }

    public void suppress() {
        suppressed = true;  // Just notifies the action method by setting a field to true
    }

    public void action() {
        while (!suppressed) {
            // Stop the movement
            Motor.A.stop();
            Motor.B.stop();
        }
        suppressed = false;
    }
}
