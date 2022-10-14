// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //controller id
    public static final class Controller {
        public static final int kMainID = 0;
        public static final double kRateLimit = 0.3; // adjust robot acceleration speeds (units/second)
    }

    public static final class Drivetrain {
        //set this to false if you absolutely loathe the superior form of robot control
        public static final boolean kFieldOriented = true;

        //CAN IDs
        public static final int kMotorDriveFrontRightID = 0;
        public static final int kMotorDriveRearRightID = 1;
        public static final int kMotorDriveFrontLeftID = 2;
        public static final int kMotorDriveRearLeftID = 3;

        public static final int kMotorTurnFrontRightID = 4;
        public static final int kMotorTurnRearRightID = 5;
        public static final int kMotorTurnFrontLeftID = 6;
        public static final int kMotorTurnRearLeftID = 7;

        public static final int kEncoderTurnFrontRightID = 8;
        public static final int kEncoderTurnRearRightID = 9;
        public static final int kEncoderTurnFrontLeftID = 10;
        public static final int kEncoderTurnRearLeftID = 11;

        /** CANcoder absolute offsets */
        public static final class Offsets {
            public static final double kFrontRight = 54.844;
            public static final double kRearRight = -154.951;
            public static final double kFrontLeft = -139.922;
            public static final double kRearLeft = 103.008;
        }
        
        /** All the IDs needed to instantiate a SwerveModule in one place */
        public static final class ModuleInfo {
            public static final int[] kFrontRight = {
                kMotorDriveFrontRightID,
                kMotorTurnFrontRightID,
                kEncoderTurnFrontRightID};
            public static final int[] kRearRight = {
                kMotorDriveRearRightID,
                kMotorTurnRearRightID,
                kEncoderTurnRearRightID};
            public static final int[] kFrontLeft = {
                kMotorDriveFrontLeftID,
                kMotorTurnFrontLeftID,
                kEncoderTurnFrontLeftID};
            public static final int[] kRearLeft = {
                kMotorDriveRearLeftID,
                kMotorTurnRearLeftID,
                kEncoderTurnRearLeftID};
        }
    }

    public static final class Calculations {
        //Module location is (+-kModuleDistance, +-kModuleDistance)
        //public static final double kModuleDistance = 0.296863; //11.6875" in meters

        public static final double kModuleMaxSpeed = Units.feetToMeters(16.3); //16.3 feet per second in m/s
        public static final double kChassisMaxSpeed = Units.feetToMeters(16.3);

        public static final double kModuleMaxAngularVelocity = Units.degreesToRadians(180); //pi radians per second (180 deg/s)
        public static final double kModuleMaxAngularAcceleration = Math.PI * 2; //2pi radians per second^2

        public static final double kMotorMaxOutput = 0.5;
        public static final double kMotorDeadband = 0.1;

        public static final double kWheelCircumference = Math.PI * Units.inchesToMeters(3.8); //diameter 3.8" in meters
        public static final double kFinalDriveRatio = 6.75;
    }

    public static final class UnitConvert {
        //conversion factors
        /** native units (falcon encoder output for position) to full rotations */
        public static final double kNativeUnitsToRot = 1/2048;
        /** native units per decisecond (falcon encoder output for speed; tick readings every 100ms) to rotations per second */
        public static final double kNUPerDStoRotPerS = 1/204.8;
        /** rotations per second to native units per decisecond (falcon encoder output for speed; tick readings every 100ms)*/
        public static final double kRotPerStoNUperDS = 204.8;
    }
}