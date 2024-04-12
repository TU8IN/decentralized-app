import lejos.hardware.Button;
import lejos.hardware.Bluetooth;
import lejos.robotics.subsumption.Behavior;

public class BluetoothController implements Behavior {
    private volatile boolean suppressed = false;
    private boolean bluetoothMessageReceived = false;

    public boolean takeControl() {
        // Returns true if a Bluetooth message is available
        return Bluetooth.getAvailable() > 0;
    }

    public void suppress() {
        suppressed = true;  // Just notifies the action method by setting a field to true
    }

    public void action() {
        while (!suppressed) {
            // Set a state variable when a Bluetooth message is received
            bluetoothMessageReceived = true;
            // Perform additional actions as needed
        }
        suppressed = false;
    }

    // Additional methods and variables as needed
}
