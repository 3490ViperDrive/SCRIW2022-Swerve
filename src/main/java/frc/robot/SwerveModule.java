package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;

public class SwerveModule {
    /*TODO: 
        -constructor for swerve module
            -initialize talonFXs and cancoder
        -method to return SwerveModuleState
        -process desired SwerveModuleStates from Drivetrain
            -optimize
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
        //should be configured to absolute
        m_encoderTurn.configMagnetOffset(offset);
    }

    public SwerveModuleState getState() {
        //speed of the motor (in native units per decisecond)
        double motorSpeed = m_motorDrive.getSelectedSensorVelocity(0);
        //speed of the module (should be in m/s)
        double wheelSpeed = ((motorSpeed * Constants.Calculations.kNUPerDStoRotPerS) * Constants.Calculations.kWheelCircumference) / Constants.Calculations.kFinalDriveRatio;
        return new SwerveModuleState(
            wheelSpeed,
            new Rotation2d(Units.degreesToRadians(m_encoderTurn.getPosition()))
        );
    }

    public void setDesiredState (SwerveModuleState desiredState) {
        
    }

    public void stopMotors () {
        m_motorDrive.set(TalonFXControlMode.PercentOutput, 0);
        m_motorTurn.set(TalonFXControlMode.PercentOutput, 0);
    }
}
