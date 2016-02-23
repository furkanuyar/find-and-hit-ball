import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class MovingBall implements Behavior {
		
	private LightSensor light;
	private LightSensor light2;
	private LightSensor light3;
	

	
	private boolean suppressed;
	private DifferentialPilot motor;
	private final int rotateSpeed = 50;
	private static int LIGHT = 410;
	
	public MovingBall(DifferentialPilot motor, LightSensor light,LightSensor light2, LightSensor light3 ) {

		this.light = light;
		this.light2 = light2;
		this.light3 = light3;
		this.suppressed = false;
		this.motor = motor;
	}

	@Override
	public boolean takeControl() {
		if(Project.state == State.MOVE&& (light.getNormalizedLightValue()<LIGHT)
				&& (light2.getNormalizedLightValue()<LIGHT)
				&& (light3.getNormalizedLightValue()<LIGHT)) return true;
		else return false;
	}

	@Override
	public void action() {
		this.suppressed = false;
		if(!suppressed){
		
			while(Motor.B.isMoving()){
				if(suppressed){
				}
			}
			
			motor.travel(150,true);
			
		
			
			while(motor.isMoving()){
				if(suppressed){
					motor.stop();
				}
			}
			
			if(!suppressed){
						
				Project.state = State.SHOOT;
			}
			
		}
	}
	

	@Override
	public void suppress() {
		this.suppressed = true;
	}

}
