package org.usfirst.frc.team1518.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.GenericHID;


public class Drive extends Robot {
    RobotDrive sirPhillip;  // class that handles basic drive operations
    Joystick leftStick;  // set to ID 1 in DriverStation
    Joystick rightStick; // set to ID 2 in DriverStation
    JoystickButton reverseButton; //set up drive reverse button
    boolean isReversed;
    public Drive() {
        sirPhillip = new RobotDrive(0, 1); // PWM0=LEFT | PWM1=RIGHT
        sirPhillip.setExpiration(0.1);
        leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        reverseButton = new JoystickButton(leftStick, 6);
        isReversed = false;
    }
    
    /**
     * Runs the motors with tank steering.
     * @return 
     */
    public Boolean init(){
    	Boolean testing = true;
       // isReversed = true;
        sirPhillip.setSafetyEnabled(true);
        // while (isOperatorControl() && isEnabled()) {
        	
        	sirPhillip.tankDrive(leftStick, rightStick);
        // 	}

    	return testing;
    }
    
    public void operatorControl() {
        sirPhillip.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	
        	sirPhillip.tankDrive(leftStick, rightStick);
        	}
        	if (reverseButton.get() && isReversed == true) {
        		sirPhillip.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        		sirPhillip.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        		sirPhillip.tankDrive(rightStick, leftStick);
        		isReversed = false;
        	}
        	else if(reverseButton.get()) {
        		sirPhillip.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
        		sirPhillip.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
        		isReversed = true;
        	}
            Timer.delay(0.005);		// wait for a motor update time
        }
    }


