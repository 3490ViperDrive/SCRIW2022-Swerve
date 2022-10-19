package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LeafBlower extends SubsystemBase {
    public enum LeafBlowerDirection {
        kOff,
        kOn
    }

    TalonSRX m_MotorLeafBlower;
    
    public LeafBlower() {
        super();
        m_MotorLeafBlower = new TalonSRX(Constants.kMotorLeafBlowerID);
        m_MotorLeafBlower.setInverted(InvertType.None);
    }

    public void runLeafBlower(LeafBlowerDirection direction) {
        switch(direction) {
            case kOn:
                m_MotorLeafBlower.set(ControlMode.PercentOutput, 1.0);
            case kOff:
                m_MotorLeafBlower.set(ControlMode.PercentOutput, 0);
            default:
                m_MotorLeafBlower.set(ControlMode.PercentOutput, 0);
        }
    }
}
