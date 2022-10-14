// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SwerveModule;

public class Drivetrain extends SubsystemBase {
  //used to process inputs
  SlewRateLimiter m_xSpeedLimiter = new SlewRateLimiter(Constants.Controller.kRateLimit);
  SlewRateLimiter m_ySpeedLimiter = new SlewRateLimiter(Constants.Controller.kRateLimit);
  SlewRateLimiter m_zRotationLimiter = new SlewRateLimiter(Constants.Controller.kRateLimit);

  Translation2d m_locationFrontRight = new Translation2d(0.296863, -0.296863); //11.6875" in meters
  Translation2d m_locationRearRight = new Translation2d(-0.296863, -0.296863);
  Translation2d m_locationFrontLeft = new Translation2d(0.296863, 0.296863);
  Translation2d m_locationRearLeft = new Translation2d(-0.296863, -0.296863);

  SwerveModule m_frontRight = new SwerveModule(Constants.Drivetrain.ModuleInfo.kFrontRight, Constants.Drivetrain.Offsets.kFrontRight);
  SwerveModule m_rearRight = new SwerveModule(Constants.Drivetrain.ModuleInfo.kRearRight, Constants.Drivetrain.Offsets.kRearRight);
  SwerveModule m_frontLeft = new SwerveModule(Constants.Drivetrain.ModuleInfo.kFrontLeft, Constants.Drivetrain.Offsets.kFrontLeft);
  SwerveModule m_rearLeft = new SwerveModule(Constants.Drivetrain.ModuleInfo.kRearLeft, Constants.Drivetrain.Offsets.kRearLeft);

  AHRS m_navX = new AHRS(Port.kMXP);

  SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
    m_locationFrontRight, m_locationRearRight, m_locationFrontLeft, m_locationRearLeft);

  /** Creates a new Drivetrain. */
  public Drivetrain() {
      super();
      m_navX.zeroYaw();
  }

  public void arcadeDrive(double xSpeed, double ySpeed, double zRot) {
    SwerveModuleState[] moduleStates = m_kinematics.toSwerveModuleStates(
      Constants.Drivetrain.kFieldOriented ?
      ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, zRot, m_navX.getRotation2d()) :
      new ChassisSpeeds(xSpeed, ySpeed, zRot));
    SwerveDriveKinematics.desaturateWheelSpeeds(moduleStates, Constants.Calculations.kModuleMaxSpeed);
    
    m_frontRight.setDesiredState(moduleStates[0]);
    m_rearRight.setDesiredState(moduleStates[1]);
    m_frontLeft.setDesiredState(moduleStates[2]);
    m_rearLeft.setDesiredState(moduleStates[3]);
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

