

//imports all lejos API's
import lejos.nxt.*;

public class Main {
	
	public static void main (String[] args) {
		
		//motor right is in sensor port B
		//motor left is in sensor port A
		MotorPort motorRight = MotorPort.B;
		MotorPort motorLeft = MotorPort.A;
		
		//an instance of the class RobotMethods is created
		RobotMethods robot = new RobotMethods();
		
		//creates new sensors
		UltrasonicSensor ultraSens = new UltrasonicSensor(SensorPort.S1);
		TouchSensor touchSens = new TouchSensor(SensorPort.S3);
		LightSensor lightSens = new LightSensor(SensorPort.S4);
		
		
		//sets the motor power
		int motorPower = 60;
		//sets the sleep time
		int sleep = 100;
		
		//The screen is cleared
		LCD.clear();
		while(!(Button.ESCAPE.isDown())){
		//while the escape button is not pressed, the robot will ask the user to place the sensor at a light value
		LCD.drawString("Place at light value", 0, 0);
		//the robot will display the value the sensor reads
		LCD.drawInt(lightSens.getNormalizedLightValue(), 0, 2);
		}
		//the robot will callibrate when the user has pressed the button
		lightSens.calibrateHigh();
		//the robot will sleep to give the robot time the callibrate
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//the LCD screen clears and tells the user, that it is accomplished
		LCD.clear();
		LCD.drawString("Success", 0, 0);
		//the robot will sleep, to give it time to display on the LCD screen
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LCD.clear();
		//The next piece of code works like above, where the robot instead callibrates for dark values
		while(!(Button.ESCAPE.isDown())){
			LCD.drawString("Place at dark value", 0, 0);
			LCD.drawInt(lightSens.getNormalizedLightValue(), 0, 2);
			}

		lightSens.calibrateLow();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		LCD.clear();
		LCD.drawString("Success", 0, 0);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LCD.clear();
		//The user is asked to press the button to start the program
		LCD.drawString("Press button to",0,0);
		LCD.drawString("start",2,2);
		
		Button.waitForAnyPress();
		//as the button is pressed, the robot will start driving back
		robot.driveBack();
		//the robot will stay in the loop as long as the light value is above 50 

		while(lightSens.getLightValue() > 50){
				//while doing this, it will display the value on the LCD screen
				LCD.clear();
				LCD.drawInt(lightSens.getLightValue(), 0, 0);
		}
		//when the statement is no longer true, the loop will break, and the robot will stop
		robot.stop();
		
		//while the touch sensor is not pressed, the robot will lower the fork
		while(!(touchSens.isPressed())){
			robot.putDown();
		}
		//it will sleep 2000 milliseconds to give the robot time to do it
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//after sleeping for the 2000 milliseconds, the motor will stop
		robot.stopLift();
		
		while(touchSens.isPressed()){
		//while the touch sensor is pressed, the robot will drive the fork upwards
			robot.liftUp();
			try {
				Thread.sleep(700);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		//we use a sleep, to make it drive upwards for a bit longer, than the sensor is actually pressed
		}
		//the loop will break and the motor will stop
		robot.stopLift();
		
		//makes the program run, as long as the escape button is not pressed
		while (!Button.ESCAPE.isDown()) {
			//clears the LCD screen, and writes the values the ultra sensor reads
			LCD.clear();
			LCD.drawInt(ultraSens.getDistance(), 0, 3);
			LCD.drawString("status", 0, 2);
			
		//set the two motors to the given motorPower, and drive them forward
		motorRight.controlMotor(motorPower, 1);
		motorLeft.controlMotor(motorPower, 1);
		
		//status is the status (value) of the ultra sensor
		boolean status;
		
		//while the status value of the ultra sensor is between 30 and 50, the robot drive forward
		while ((status = ultraSens.getDistance()>30) && ultraSens.getDistance()<50){
			try {
				//makes the robot sleep for the given value
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		//when the value of the ultra sensor is more than 30, the robot turns right by stopping the right motor
		if(status){
			while (ultraSens.getDistance()>30) {
				//the right motor moves with a speed of 50, to make the robot turn slowly
				motorRight.controlMotor(50, 1);
				//a value of 1 makes the motor moves forward
				motorLeft.controlMotor(motorPower, 1);
				try {
					//makes the robot sleep for the given value
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//if the value of the ultra sensor is less than 50, the robot will turn left, by stopping the left motor
		} else {
			while (ultraSens.getDistance()<50) {
				//a value of 1 makes the motor moves forward
				motorRight.controlMotor(motorPower, 1);
				//the right motor moves with a speed of 50, to make the robot turn slowly
				motorLeft.controlMotor(50, 1);	
				try {
					//makes the robot sleep for the given value
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		}
	}
} 
