// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  final Drivetrain m_drivetrain = new Drivetrain();
  final Climber m_climber = new Climber();
  final LeafBlower m_leafBlower = new LeafBlower();
  final XboxController m_joystick = new XboxController(Constants.Controller.kMainID);
  final SequentialCommandGroup m_autoCommand = new Autonomous(m_drivetrain);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    m_drivetrain.setDefaultCommand(
      new Drive(m_joystick::getLeftY, m_joystick::getLeftX, m_joystick::getRightX, m_drivetrain));
    SmartDashboard.putData(m_drivetrain);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //this is scuffed cram coding at scriw... pls fix
    new JoystickButton(m_joystick, XboxController.Button.kA.value).whenHeld(
      new StartEndCommand(() -> m_climber.runClimber(Climber.ClimbDirection.kDown), () -> m_climber.runClimber(Climber.ClimbDirection.kOff), m_climber)
    );

    new JoystickButton(m_joystick, XboxController.Button.kB.value).whenHeld(
      new StartEndCommand(() -> m_climber.runClimber(Climber.ClimbDirection.kUp), () -> m_climber.runClimber(Climber.ClimbDirection.kOff), m_climber)
    );

    new JoystickButton(m_joystick, XboxController.Button.kX.value).whenHeld(
      new StartEndCommand(() -> m_climber.runPivot(Climber.PivotDirection.kForward), () -> m_climber.runPivot(Climber.PivotDirection.kOff), m_climber)
    );

    new JoystickButton(m_joystick, XboxController.Button.kY.value).whenHeld(
      new StartEndCommand(() -> m_climber.runPivot(Climber.PivotDirection.kReverse), () -> m_climber.runPivot(Climber.PivotDirection.kOff), m_climber)
    );

    new JoystickButton(m_joystick, XboxController.Button.kLeftBumper.value).whenHeld(
      new StartEndCommand(() -> m_leafBlower.runLeafBlower(LeafBlower.LeafBlowerDirection.kOn), () -> m_leafBlower.runLeafBlower(LeafBlower.LeafBlowerDirection.kOff), m_climber)
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}
