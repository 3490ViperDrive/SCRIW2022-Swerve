// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

/** An example command that uses an example subsystem. */
public class Drive extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_xSpeed;
  private final DoubleSupplier m_ySpeed;
  private final DoubleSupplier m_zRot;

  /**
   * creates a new Drive command
   *
   * @param subsystem the drivetrain subsystem
   */
  public Drive(DoubleSupplier xSpeed, DoubleSupplier ySpeed, DoubleSupplier zRot, Drivetrain drivetrain) {
    m_drivetrain = drivetrain;
    m_xSpeed = xSpeed;
    m_ySpeed = ySpeed;
    m_zRot = zRot;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_xSpeed.getAsDouble(), m_ySpeed.getAsDouble(), m_zRot.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}