import lejos.nxt.MotorPort;


public class RobotMethods {

	//we keep all the methods for the robot in here
	MotorPort motorRight = MotorPort.A;
	MotorPort motorLeft = MotorPort.B;
	MotorPort lift = MotorPort.C;
	
	//we write names for the different states, to make it easier to change
	int driveForward = 1;
	int driveBackwards = 2;
	
	//we decide the different motorpowers, to make it easier to change
	//There is one for the driving motors, and one for the lifting
	int driveMotorPower = 60;
	int liftMotorPower = 50;
	
	//We give the different states names to make the code easier to use and change
	int down = 1;
	int up = 2;
	int stop = 3;
	
	//a method the the robot to start, by starting the motors
	public void start(){
		motorRight.controlMotor(driveMotorPower, driveForward);
		motorLeft.controlMotor(driveMotorPower, driveForward);
	}
	
	//a method the the robot to start, by changing the state 
	public void driveBack(){
		motorRight.controlMotor(driveMotorPower, driveBackwards);
		motorLeft.controlMotor(driveMotorPower, driveBackwards);
	}
	
	//a method the the robot to stop, by stopping the motors
	public void stop(){
		motorRight.controlMotor(driveMotorPower, 3);
		motorLeft.controlMotor(driveMotorPower, 3);
	}
	
	//a method the the robot to lift the fork, by starting the motor with the state we initialized to "Up"
	public void liftUp(){
		lift.controlMotor(liftMotorPower, up);
	}
	
	//a method the the robot to lower the fork, by starting the motor with the state we initialized to "Down"
	public void putDown(){
		lift.controlMotor(liftMotorPower, down);
	}
	
	//a method the the robot to stop the fork, by starting the motor with the state we initialized to "Stop"
	public void stopLift(){
		lift.controlMotor(liftMotorPower, stop);
	}

}
