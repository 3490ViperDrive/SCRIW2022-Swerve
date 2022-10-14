package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;

public class SwerveModule {

    final TalonFX m_motorDrive;
    final TalonFX m_motorTurn;
    final CANCoder m_encoderTurn;

    public SwerveModule(int[] moduleInfo, double offset) {
        this(moduleInfo[0], moduleInfo[1], moduleInfo[2], offset);
    }

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

    public double getModuleSpeed() {
        //speed of the motor (in native units per decisecond)
        double motorSpeed = m_motorDrive.getSelectedSensorVelocity(0);
        //speed of the module (should be in m/s)
        //TalonFX built-in encoders report angles in native units (2048 nu = 1 rotation = 360 deg)
        //so it must be converted to rotations or degrees before using
        double wheelSpeed = ((motorSpeed * Constants.UnitConvert.kNUPerDStoRotPerS) * Constants.Calculations.kWheelCircumference) / Constants.Calculations.kFinalDriveRatio;
        return wheelSpeed;
    }

    public double getTurnPosition() {
        //CANCoder reports angles in degrees or radians according to config
        //no conversion needed
        return m_encoderTurn.getPosition();
    }
    public SwerveModuleState getState() {
        return new SwerveModuleState(
            getModuleSpeed(),
            new Rotation2d(Units.degreesToRadians(getTurnPosition())));
    }

    public void setDesiredState (SwerveModuleState desiredState) {
        desiredState = SwerveModuleState.optimize(desiredState, getState().angle);
        double targetWheelSpeed = desiredState.speedMetersPerSecond;
        //convert target angle and wheel speeds to native units
        double targetMotorSpeed = ((targetWheelSpeed * Constants.Calculations.kFinalDriveRatio) / Constants.Calculations.kWheelCircumference) * Constants.UnitConvert.kRotPerStoNUperDS;
        double turnOutput = (desiredState.angle.getDegrees()) * (4096.0/360.0); //conversion factor i think ,move to constants later
        m_motorDrive.set(TalonFXControlMode.Velocity, targetMotorSpeed); //if this doesn't work, try ControlMode.Velocity instead
        m_motorTurn.set(TalonFXControlMode.Position, turnOutput);
    }

    public void stopMotors () {
        m_motorDrive.set(TalonFXControlMode.PercentOutput, 0);
        m_motorTurn.set(TalonFXControlMode.PercentOutput, 0);
    }
}
