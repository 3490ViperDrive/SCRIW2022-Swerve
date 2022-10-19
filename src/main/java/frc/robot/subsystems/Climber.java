package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    //1x Talon SRX, CIM Motor, Encoder: pivot
    //1x Talon FX: climb
    public enum ClimbDirection {kUp, kDown, kOff}
    public enum PivotDirection {kForward, kReverse, kOff}

    final TalonFX m_motorClimb;
    final TalonSRX m_motorPivot;

    double kMotorClimbSpeed = 0.5;
    double kMotorPivotSpeed = 0.5;
    public Climber() {
        super();
        m_motorClimb = new TalonFX(12);
        m_motorPivot = new TalonSRX(13);

        m_motorClimb.setInverted(TalonFXInvertType.Clockwise); //these are assumptions
        m_motorPivot.setInverted(InvertType.None);


    }

    public void runClimber (ClimbDirection direction) {
        switch(direction) {
            case kUp:
                m_motorClimb.set(TalonFXControlMode.PercentOutput, kMotorClimbSpeed);
                break;
            case kDown:
                m_motorClimb.set(TalonFXControlMode.PercentOutput, -kMotorClimbSpeed);
                break;
            case kOff:
                m_motorClimb.set(TalonFXControlMode.PercentOutput, 0);
                break;
            default:
                m_motorClimb.set(TalonFXControlMode.PercentOutput, 0);
                break;
        }
    }

    public void runPivot (PivotDirection direction) {
        switch(direction) {
            case kForward:
                m_motorPivot.set(ControlMode.PercentOutput, kMotorPivotSpeed);
                break;
            case kReverse:
                m_motorPivot.set(ControlMode.PercentOutput, -kMotorPivotSpeed);
                break;
            case kOff:
                m_motorPivot.set(ControlMode.PercentOutput, 0);
                break;
            default:
                m_motorPivot.set(ControlMode.PercentOutput, 0);
                break;
        }
    }
}
