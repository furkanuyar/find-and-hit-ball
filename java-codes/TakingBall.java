import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class TakingBall implements Behavior{
	
	
	private LightSensor light;
	private LightSensor light2;
	private LightSensor light3;
	private boolean suppressed;
	private final int rotateSpeed = 50;
	private static int LIGHT = 410;
	
	public TakingBall( LightSensor light, LightSensor light2, LightSensor light3){

		this.light = light;
		this.light2 = light2;
		this.light3 = light3;
		this.suppressed = false;
	}

	@Override
	public boolean takeControl() {
		if(Project.state == State.TAKE&& (light.getNormalizedLightValue()<LIGHT)
	&& (light2.getNormalizedLightValue()<LIGHT)&& (light3.getNormalizedLightValue()<LIGHT)) return true;
		else return false;
	}

	@Override
	public void action() {
		this.suppressed = false;
		if(!suppressed){
			
			
			closeGrab();
			
			
			while(Motor.B.isMoving() && !suppressed);
			
			if(!suppressed){
				Project.state = State.MOVE;
			}
		}
	}

	@Override
	public void suppress() {
		this.suppressed = true;
	}

	public void closeGrab()
	{
		
			Motor.B.setSpeed(rotateSpeed*150);
			Motor.B.rotate(220, false);		 
	}


}






