package org.team708.robot.subsystems;

import org.team708.robot.RobotMap;
import org.team708.robot.commands.claw.ClawMotorControl;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A claw that picks up recycling containers with a motor, 
 * two arms that open and close off of a single solenoid
 */
public class Claw extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	// Limit switch for the intake motors to stop
	
	// Double solenoids to control pistons
	private DoubleSolenoid clawFingerSolenoid;
	private DoubleSolenoid clawWristSolenoid;
	
	// Finger solenoid states
	private static final DoubleSolenoid.Value OPEN = DoubleSolenoid.Value.kReverse;
	private static final DoubleSolenoid.Value CLOSED = DoubleSolenoid.Value.kForward;
	
	// Wrist solenoid states
	private static final DoubleSolenoid.Value VERTICAL = DoubleSolenoid.Value.kReverse;
	private static final DoubleSolenoid.Value HORIZONTAL = DoubleSolenoid.Value.kForward;
	
	// Spike to move the wheels at the end of the claw
	private Talon clawFingerMotor;
	
	// Finger spike states
	private static final double INTAKE_SPEED = 1.0;
	private static final double DISPENSE_SPEED = -1.0;
	private static final double OFF_SPEED = 0.0;
	
	public Claw() {
		
		// Makes the solenoids
		clawFingerSolenoid = new DoubleSolenoid(RobotMap.clawDoubleSolenoidA, RobotMap.clawDoubleSolenoidB);
		clawWristSolenoid = new DoubleSolenoid(RobotMap.clawWristDoubleSolenoidA, RobotMap.clawWristDoubleSolenoidB);
		
		// Sets solenoids to initial positions
		clawFingerSolenoid.set(OPEN);
		clawWristSolenoid.set(HORIZONTAL);
		
		clawFingerMotor = new Talon(RobotMap.debugMotor1);
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ClawMotorControl());
    }
    
    
    // Self explanatory getter/setter methods.
    
    public void openClaw() {
    	
    	clawFingerSolenoid.set(OPEN);
    
    }
    
    public void closeClaw() {
    	
    	clawFingerSolenoid.set(CLOSED);
    	
    }
    
    public boolean isClawOpen() {
    	
    	return clawFingerSolenoid.get().equals(OPEN);
    
    }
    
    public boolean isClawClosed() {
    	return clawFingerSolenoid.get().equals(CLOSED);
    }
    
    public void setClawVertical() {
    	
    	clawWristSolenoid.set(VERTICAL);
    
    }
    
    public void setClawHorizontal() {
    	
    	clawWristSolenoid.set(HORIZONTAL);
    	
    }
    
    public boolean isClawVertical() {
    	
    	return clawWristSolenoid.get().equals(VERTICAL);
    
    }
    
    public boolean isClawHorizontal() {
    	return clawWristSolenoid.get().equals(HORIZONTAL);
    }
    
    public void intake() {
    	
    	clawFingerMotor.set(INTAKE_SPEED);
    
    }
    
    public void dispense() {
    	
    	clawFingerMotor.set(DISPENSE_SPEED);
    	
    }
    
    public void stopFingerMotor() {
    	
    	clawFingerMotor.set(OFF_SPEED);
    	
    }
    
}

