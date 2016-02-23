import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Project {
	
	protected static UltrasonicSensor ultra;
	protected static LightSensor light;
	protected static LightSensor light2;
	protected static LightSensor light3;
	protected static DifferentialPilot motor;
	
	public static volatile State state;
	
	private static Arbitrator arby;
	
	public static void main(String [] args) {
		
		initialize();
		
		arby.start();
		
	}
	
	public static void initialize(){
		
		ultra = new UltrasonicSensor(SensorPort.S4);
		light = new LightSensor(SensorPort.S3);
		light2 = new LightSensor(SensorPort.S1);
		light3 = new LightSensor(SensorPort.S2);
		
		ultra.continuous();
		
		
		
		
		motor = new DifferentialPilot(56.0, 168.0, Motor.C, Motor.A);
		
		state = State.SEARCH;
		
		Behavior [] bArray = {
				new LightSensorActive(light,light2,light3, motor),
				new SearchingBall(ultra,motor, light,light2,light3),
				new TakingBall(light,light2,light3),
				new MovingBall(motor, light,light2,light3),
				new ShootingBall(motor,light,light2,light3),
				
				};
		
		
		arby = new Arbitrator(bArray);
		
		
		
   }
	
}
