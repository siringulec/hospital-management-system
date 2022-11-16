# ************************************************************
# Sequel Ace SQL dump
# Version 20039
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.00.01 (MySQL 5.5.5-10.4.21-MariaDB)
# Database: hospital
# Generation Time: 2022-11-11 13:02:40 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table appointments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `appointments`;

CREATE TABLE `appointments` (
  `appoinntmentID` int(11) unsigned NOT NULL,
  `patientID` int(11) unsigned NOT NULL,
  `doctorID` int(11) unsigned NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`appoinntmentID`),
  KEY `patientID` (`patientID`),
  KEY `doctorID` (`doctorID`),
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`),
  CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`doctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table bills
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bills`;

CREATE TABLE `bills` (
  `bill_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` int(11) unsigned NOT NULL,
  `received_treatment` text DEFAULT NULL,
  `basic_amount` double DEFAULT NULL,
  `amount_assured` double DEFAULT NULL,
  `bill_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table diagnosis
# ------------------------------------------------------------

DROP TABLE IF EXISTS `diagnosis`;

CREATE TABLE `diagnosis` (
  `diagnosisID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` int(11) unsigned NOT NULL,
  `testID` int(11) unsigned NOT NULL,
  `diagonosis` text DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`diagnosisID`),
  KEY `patientID` (`patientID`),
  KEY `testID` (`testID`),
  CONSTRAINT `diagnosis_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`),
  CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`testID`) REFERENCES `test` (`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table doctor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `doctorID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `full_name` varchar(50) NOT NULL,
  `speciality` varchar(50) NOT NULL,
  `password` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`doctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;

INSERT INTO `doctor` (`doctorID`, `full_name`, `speciality`, `password`)
VALUES
	(1,'Eileen Marin','Allergist / Immunologist',NULL);

/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table medecine
# ------------------------------------------------------------

DROP TABLE IF EXISTS `medecine`;

CREATE TABLE `medecine` (
  `medicineID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `stock` int(225) DEFAULT NULL,
  PRIMARY KEY (`medicineID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table patient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `patientID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(12) NOT NULL,
  `ssn` int(9) NOT NULL,
  `age` int(3) NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table prescription
# ------------------------------------------------------------

DROP TABLE IF EXISTS `prescription`;

CREATE TABLE `prescription` (
  `prescriptionID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `medicineID` int(11) unsigned NOT NULL,
  `dosage` varchar(225) NOT NULL,
  PRIMARY KEY (`prescriptionID`),
  KEY `medicineID` (`medicineID`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`medicineID`) REFERENCES `medecine` (`medicineID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table room
# ------------------------------------------------------------

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_number` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `purpose` varchar(50) DEFAULT NULL,
  `availability` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table stay
# ------------------------------------------------------------

DROP TABLE IF EXISTS `stay`;

CREATE TABLE `stay` (
  `room_number` int(11) unsigned NOT NULL,
  `patientID` int(11) unsigned NOT NULL,
  `disease` int(11) DEFAULT NULL,
  `date_entered` date DEFAULT NULL,
  `recovery_status` text DEFAULT NULL,
  `number_of_days` int(225) DEFAULT NULL,
  KEY `room_number` (`room_number`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `stay_ibfk_1` FOREIGN KEY (`room_number`) REFERENCES `room` (`room_number`),
  CONSTRAINT `stay_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table test
# ------------------------------------------------------------

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `testID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` int(11) unsigned NOT NULL,
  `type_of_test` varchar(50) DEFAULT NULL,
  `results` text DEFAULT NULL,
  PRIMARY KEY (`testID`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `test_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
