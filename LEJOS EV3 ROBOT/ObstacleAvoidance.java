import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.hardware.motor.Motor;

public class ObstacleAvoidance implements Behavior {

    private static EV3UltrasonicSensor sensor;
    private static SampleProvider distanceProvider;
    private static float[] sample;

    public ObstacleAvoidance() {
        // Initialize ultrasonic sensor
        Port sensorPort = SensorPort.S1;
        sensor = new EV3UltrasonicSensor(sensorPort);
        distanceProvider = sensor.getDistanceMode();
        sample = new float[distanceProvider.sampleSize()];
    }

    @Override
    public boolean takeControl() {
        // Obstacle detected within 20cm
        distanceProvider.fetchSample(sample, 0);
        float distance = sample[0] * 100; // Convert distance to centimeters
        return distance < 20;
    }

    @Override
    public void action() {
       
        Motor.A.stop(); // Stop motor A (left motor)
        Motor.B.stop(); // Stop motor B (right motor)

        Motor.A.backward(); 
        Motor.B.backward(); 
        try {
            Thread.sleep(1000); // Back off for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Turn
        Motor.A.rotate(180); // Rotate motor A (left motor) 180 degrees
        Motor.B.rotate(-180); // Rotate motor B (right motor) -180 degrees (opposite direction)
    }

    @Override  //THERE MAY BE AN ERROR HERE
    public void suppress() {
        // Stop any current movement
        Motor.A.stop();
        Motor.B.stop();
    }
}
