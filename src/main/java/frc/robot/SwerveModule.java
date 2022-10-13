package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;

public class SwerveModule {
    /*TODO: 
        -constructor for swerve module
            -initialize talonFXs and cancoder
        -method to return SwerveModuleState
            -method to return optimized swerve module state, possibly
        -process desired SwerveModuleStates from Drivetrain
            -set values to motors
    */ 

    final TalonFX m_motorDrive;
    final TalonFX m_motorTurn;
    final CANCoder m_encoderTurn;
  
    public SwerveModule(int motorDriveID, int motorTurnID, int encoderTurnID, double offset) {
        m_motorDrive = new TalonFX(motorDriveID);
        m_motorTurn = new TalonFX(motorTurnID);
        m_encoderTurn = new CANCoder(encoderTurnID);
        //config for drive motor
        m_motorDrive.setInverted(TalonFXInvertType.Clockwise);
        //config for turn motor
        m_motorTurn.setInverted(TalonFXInvertType.CounterClockwise);
        //config for turn cancoder
        m_encoderTurn.configMagnetOffset(offset);
    }

    public SwerveModuleState GetState() {

    }

    public void SetDesiredState (SwerveModuleState desiredState) {

    }
}
