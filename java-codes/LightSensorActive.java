
import java.util.Random;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class LightSensorActive implements Behavior {
	
	private LightSensor light;
	private LightSensor light2;
	private LightSensor light3;
	private DifferentialPilot motor;
	private boolean suppressed;
	static Random rand = new Random();
	static int  random;
	static int  travel;
	private static int LIGHT = 410;
	
	
	public LightSensorActive(LightSensor light,LightSensor light2,LightSensor light3, DifferentialPilot motor){
		this.light = light;
		this.light2 = light2;
		this.light3 = light3;
		this.motor=motor;
		this.suppressed=false;
		motor.setTravelSpeed(120);
		motor.setRotateSpeed(120);
		
	}

	@Override
	public boolean takeControl() {
		if(light.getNormalizedLightValue()>LIGHT || light2.getNormalizedLightValue()>LIGHT ||
				light3.getNormalizedLightValue()>LIGHT) return true;
		else return false;
	}

	@Override
	public void action() {
		motor.setTravelSpeed(120);
		motor.setRotateSpeed(120);
		this.suppressed = false;
		motor.stop();
		
		switch (Project.state) {
			case SEARCH	: 
				
				Project.state = State.HIT;
				random = rand.nextInt(2);
				travel = rand.nextInt(200) -300;
				if(random==0){random = rand.nextInt(360) - 360;}
		    	else{random = rand.nextInt(360) +1;}
				motor.stop();
				motor.travel(travel);
				motor.rotate(random);
				
				
				Project.state = State.SEARCH;
				
				break;
								  	 						  	  
			case MOVE:	
				
				Project.state = State.HIT;
				random = rand.nextInt(2);
				travel = rand.nextInt(200) -300;
				if(random==0){random = rand.nextInt(360) - 360;}
		    	else{random = rand.nextInt(360) +1;}
				motor.stop();
				motor.travel(travel);
				motor.rotate(random);
				
				
				Project.state = State.MOVE;
					
							 	  	  break;
							 	  	  
			case TAKE	: 
				
				Project.state = State.HIT;
				random = rand.nextInt(2);
				travel = rand.nextInt(200) -300;
				if(random==0){random = rand.nextInt(360) - 360;}
		    	else{random = rand.nextInt(360) +1;}
				motor.stop();
				motor.travel(travel);
				motor.rotate(random);
				
				Project.state = State.TAKE;
				
									  break;
							 	  	  
			case SHOOT	: 
				
				Project.state = State.HIT;
				random = rand.nextInt(2);
				travel = rand.nextInt(200) -300;
				if(random==0){random = rand.nextInt(360) - 360;}
		    	else{random = rand.nextInt(360) +1;}
				motor.stop();
				motor.travel(travel);
				motor.rotate(random);
				
				Project.state = State.SHOOT;
				
									  break;
		
			default					: 
				Project.state = State.HIT;
				 				  	  break;
		}
		
	}

	@Override
	public void suppress() {
		this.suppressed = true;
		//DON'T SUPPRESS THIS AT ANY TIME
	}

}
