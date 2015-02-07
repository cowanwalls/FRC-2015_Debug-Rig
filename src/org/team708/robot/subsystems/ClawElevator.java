package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * 
 */
public class ClawElevator extends PIDSubsystem {

	// Sets PID variables
	private static double Kp = 0.0;
	private static double Ki = 0.0;
	private static double Kd = 0.0;
	
	// Sets critical encoder constant values
	public static final double UPPER_LIMIT = 60.0;			// The upper limit, set to the max height of the robot (Will need to change)
	public static final double LOWER_LIMIT = 5.0;			// The lower limit, set to 5 inches above zero
	public static final double TOTE_HEIGHT = 12.1;			// The height of a tote
	public static final double HEIGHT_PER_REV = (1/12);		// The amount of inches the lead screw outputs upwards per one revolution
	public static final double REVS_PER_COUNT = (1/360);	// The number of revolutions produced by one pulse
	
	// Makes encoder
	private Encoder clawElevatorEncoder;
		
	//Makes motor
	private Talon clawElevatorMotor;
	
    // Initialize your subsystem here
    public ClawElevator() {
    	
    	super(Kp, Ki, Kd);
        
    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.

    	// Creates the encoder and Talon for the elevator
    	clawElevatorEncoder = new Encoder(RobotMap.clawElevatorEncoderA, RobotMap.clawElevatorEncoderB);
    	clawElevatorMotor = new Talon(RobotMap.debugMotor2);
    	
    	// Resets the encoder on initialization
    	clawElevatorEncoder.reset();
    	
    	// Sets the distance per pulse to inches traveled upwards
    	clawElevatorEncoder.setDistancePerPulse(REVS_PER_COUNT*HEIGHT_PER_REV);
    	
    	// Sets the PID parameter to distance
    	clawElevatorEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kDistance);
    	
    	// Sets up the PID parameter
    	setSetpoint(0.0);
    	disable();
    	
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setElevatorSpeed_Manual(double input) {
    	
    	clawElevatorMotor.set(input);
    	
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return clawElevatorEncoder.getDistance();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	clawElevatorMotor.set(output);
    }
}
