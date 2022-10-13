// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /*TODO:
    -create SwerveDriveKinematics object
      -Odometry would be nice but is not a requirement
    -arcade drive method (interface with arcade drive command)
    -make navX and reset
  */

  Translation2d m_locationFrontRight = new Translation2d(0.296863, -0.296863); //11.6875" in meters
  Translation2d m_locationRearRight = new Translation2d(-0.296863, -0.296863);
  Translation2d m_locationFrontLeft = new Translation2d(0.296863, 0.296863);
  Translation2d m_locationRearLeft = new Translation2d(-0.296863, -0.296863);
  /** Creates a new Drivetrain. */
  public Drivetrain() {
      super();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

