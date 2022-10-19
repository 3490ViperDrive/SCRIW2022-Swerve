package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Drivetrain;

public class Autonomous extends SequentialCommandGroup {
    public Autonomous(Drivetrain drivetrain) {
        //i hope this works
        addCommands(
            new ParallelDeadlineGroup(
                new WaitCommand(3),
                new InstantCommand(
                    () -> drivetrain.arcadeDrive(-0.4, 0, 0),
                    drivetrain)
            ),
            new InstantCommand(
                    () -> drivetrain.arcadeDrive(0, 0, 0),
                    drivetrain));
    }
}
