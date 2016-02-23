import lejos.nxt.LightSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class SearchingBall implements Behavior{

	private UltrasonicSensor ultra;
	private LightSensor light;
	private LightSensor light2;
	private LightSensor light3;
	private DifferentialPilot motor;
	
	private boolean suppressed;
	private static int SENSIVITY = 20, TRAVEL_DISTANCE = 1000,LIGHT = 410;
	
	
	public SearchingBall(UltrasonicSensor ultra, DifferentialPilot motor, LightSensor light,
			LightSensor light2,LightSensor light3 ) {
		
	
		this.ultra = ultra;
		this.motor = motor;
		this.light = light;
		this.light2 = light2;
		this.light3 = light3;
		this.suppressed = false;
		motor.setTravelSpeed(120);
	}
	
	
	public boolean takeControl() {
		if(Project.state == State.SEARCH && (light.getNormalizedLightValue()<LIGHT)
				&& (light2.getNormalizedLightValue()<LIGHT) && (light3.getNormalizedLightValue()<LIGHT) ) return true;
		else return false;
	}

	@Override
	public void action() {
		this.suppressed = false;
		while(Project.state == State.SEARCH && (light.getNormalizedLightValue()<LIGHT) && (light2.getNormalizedLightValue()<LIGHT) 
				&& (light3.getNormalizedLightValue()<LIGHT) ){
			motor.stop();
			
			//motor.steer(50, 360, true);
			while(motor.isMoving() && !suppressed && (light.getNormalizedLightValue()<LIGHT) && (light2.getNormalizedLightValue()<LIGHT) 
					&& (light3.getNormalizedLightValue()<LIGHT) ){
				if(ultra.getDistance() < SENSIVITY){
					motor.stop();
					Project.state = State.TAKE;
					return;
				}
				
			}
			if(suppressed){
				motor.stop();
				return;
			}
			
			if(!suppressed){
				motor.travel(TRAVEL_DISTANCE, true);
				while(motor.isMoving() && !suppressed &&(light.getNormalizedLightValue()<LIGHT)
						&& (light2.getNormalizedLightValue()<LIGHT) && (light3.getNormalizedLightValue()<LIGHT) ){
					if(ultra.getDistance() < SENSIVITY){
						motor.stop();
						Project.state = State.TAKE;
						return;
					}
				}
			}
				
			if(suppressed && (light.getNormalizedLightValue()<LIGHT) && (light2.getNormalizedLightValue()<LIGHT) 
					&& (light3.getNormalizedLightValue()<LIGHT) ){
				motor.stop();
				return;
			}
			
			if(!suppressed && (light.getNormalizedLightValue()<LIGHT) && (light2.getNormalizedLightValue()<LIGHT) 
					&& (light3.getNormalizedLightValue()<LIGHT) ){
				//motor.rotate(-90,true);
				while(motor.isMoving() && !suppressed ){
					if(ultra.getDistance() < SENSIVITY){
						motor.stop();
						Project.state = State.TAKE;
						return;
					}
				}
			}
			
			if(suppressed && (light.getNormalizedLightValue()<LIGHT) && (light2.getNormalizedLightValue()<LIGHT) 
					&& (light3.getNormalizedLightValue()<LIGHT) ){
				motor.stop();
				return;
			}
		}
		
	}

	@Override
	public void suppress() {
		this.suppressed = true;
	}

}
