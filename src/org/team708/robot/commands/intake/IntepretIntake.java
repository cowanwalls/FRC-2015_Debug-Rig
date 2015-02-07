package org.team708.robot.commands.intake;

import org.team708.robot.Robot;
import org.team708.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntepretIntake extends Command {

    public IntepretIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.intake.isOn() && !Robot.intake.isToteSwitchTriggered()) {
			if (Robot.intake.isIn()) {
				Robot.intake.set(Intake.IN_SPEED);
			} else {
				Robot.intake.set(Intake.OUT_SPEED);
			}
    	} else {
    		Robot.intake.set(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
