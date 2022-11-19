SET NAMES utf8mb4;

DROP TABLE IF EXISTS `appointments`;

CREATE TABLE `appointments` (
  `appointmentID` int(11) unsigned NOT NULL,
  `patientID` int(11) unsigned NOT NULL,
  `doctorID` int(11) unsigned NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`appointmentID`),
  KEY `patientID` (`patientID`),
  KEY `doctorID` (`doctorID`),
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`),
  CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `appointments` WRITE;


INSERT INTO `appointments` (`appointmentID`, `patientID`, `doctorID`, `date`)
VALUES
	(1,1,1,'2022-11-09 09:00:00'),
	(2,2,3,'2022-11-09 09:30:00'),
	(3,3,2,'2022-11-09 09:00:00');


UNLOCK TABLES;


DROP TABLE IF EXISTS `bills`;

CREATE TABLE `bills` (
  `bill_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` int(11) unsigned NOT NULL,
  `received_treatment` text DEFAULT NULL,
  `basic_amount` double DEFAULT NULL,
  `amount_assured` double DEFAULT NULL,
  `bill_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bill_id`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `bills` WRITE;


INSERT INTO `bills` (`bill_id`, `patientID`, `received_treatment`, `basic_amount`, `amount_assured`, `bill_status`)
VALUES
	(1,2,'Send home for rest with prescripted',200,40,'Paid'),
	(2,1,'Still undergoing treatment ',NULL,NULL,'Pending'),
	(3,3,'Still undergoing treatment ',NULL,NULL,'Pending');


UNLOCK TABLES;



DROP TABLE IF EXISTS `diagnosis`;

CREATE TABLE `diagnosis` (
  `diagnosisID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` int(11) unsigned NOT NULL,
  `testID` int(11) unsigned DEFAULT NULL,
  `diagnosis` text DEFAULT NULL,
  `status` varchar(10) NOT NULL,
  `prescriptionID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`diagnosisID`),
  KEY `patientID` (`patientID`),
  KEY `testID` (`testID`),
  KEY `prescriptionID` (`prescriptionID`),
  CONSTRAINT `diagnosis_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`),
  CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`testID`) REFERENCES `test` (`testID`),
  CONSTRAINT `diagnosis_ibfk_3` FOREIGN KEY (`prescriptionID`) REFERENCES `prescription` (`prescriptionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `diagnosis` WRITE;


INSERT INTO `diagnosis` (`diagnosisID`, `patientID`, `testID`, `diagnosis`, `status`, `prescriptionID`)
VALUES
	(1,1,1,'Diabetes','finalized',1),
	(2,2,2,NULL,'pending',NULL),
	(3,3,3,'Acinetobacter Infection','finalized',3);


UNLOCK TABLES;


DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `identification_number` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `subspecialty` varchar(50) DEFAULT NULL,
  `pass` varchar(12) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `gender` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` text DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `doctor` WRITE;


INSERT INTO `doctor` (`identification_number`, `name`, `subspecialty`, `pass`, `age`, `gender`, `address`, `phone`)
VALUES
	(1,'Michael Joseph Giordano','Neurological Surgeon','1',52,X'4D','263 Farmington Ave  Farmington/Hartford','1'),
	(2,'Lee Ann Van Houten-Sauter','Family Doctor','lavhs',34,X'46','220 Pine St Williamstown/Gloucester','3'),
	(3,'Glenn Howard Perelson','Cardiologist','ghp',43,X'4D','740 Bay Blvd Chula Vista/San Diego','4');


UNLOCK TABLES;




DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `medicineID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `stock` int(225) DEFAULT NULL,
  PRIMARY KEY (`medicineID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `medicine` WRITE;


INSERT INTO `medicine` (`medicineID`, `name`, `stock`)
VALUES
	(1,'Metformin',65),
	(2,'Bactepan',23),
	(3,'Lysotrope Ethozoxane',14);


UNLOCK TABLES;




DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `identification_number` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `password` varchar(12) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  PRIMARY KEY (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `patient` WRITE;


INSERT INTO `patient` (`identification_number`, `name`, `password`, `age`, `gender`, `phone`)
VALUES
	(1,'Mabel Mcgregor','i3ejr2ooujfo',18,X'46','1'),
	(2,'Nicholas Cervantes','wklemdfw',61,X'4D','1'),
	(3,'Adelina Nairn','61771991',41,X'46','1');

UNLOCK TABLES;



DROP TABLE IF EXISTS `prescription`;

CREATE TABLE `prescription` (
  `prescriptionID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `medicineID` int(11) unsigned NOT NULL,
  `dosage` varchar(225) NOT NULL,
  PRIMARY KEY (`prescriptionID`),
  KEY `medicineID` (`medicineID`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`medicineID`) REFERENCES `medicine` (`medicineID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `prescription` WRITE;


INSERT INTO `prescription` (`prescriptionID`, `medicineID`, `dosage`)
VALUES
	(1,1,'500mg'),
	(2,3,'750mg'),
	(3,2,'250mg');


UNLOCK TABLES;



DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_number` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `purpose` varchar(50) DEFAULT NULL,
  `availability` tinytext DEFAULT NULL,
  PRIMARY KEY (`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `room` WRITE;


INSERT INTO `room` (`room_number`, `purpose`, `availability`)
VALUES
	(1,'Operating Room','Y'),
	(2,'Patient Room','N'),
	(3,'Patient Room','Y'),
	(4,'Emergency Room','N'),
	(5,'Patient Room','Y');


UNLOCK TABLES;



DROP TABLE IF EXISTS `stay`;

CREATE TABLE `stay` (
  `room_number` int(11) unsigned NOT NULL,
  `patientID` int(11) unsigned NOT NULL,
  `reason` text DEFAULT NULL,
  `date_entered` date DEFAULT NULL,
  `recovery_status` text DEFAULT NULL,
  `number_of_days` int(225) DEFAULT NULL,
  KEY `room_number` (`room_number`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `stay_ibfk_1` FOREIGN KEY (`room_number`) REFERENCES `room` (`room_number`),
  CONSTRAINT `stay_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `stay` WRITE;


INSERT INTO `stay` (`room_number`, `patientID`, `reason`, `date_entered`, `recovery_status`, `number_of_days`)
VALUES
	(2,1,'High blood sugar','2022-11-09','Patient has shown improvement.',2),
	(4,3,'Dangerous Infection','2022-11-09','Shows signs of possible expo',5);


UNLOCK TABLES;



DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `testID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` int(11) unsigned NOT NULL,
  `type_of_test` varchar(50) DEFAULT NULL,
  `results` text DEFAULT NULL,
  PRIMARY KEY (`testID`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `test_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `test` WRITE;


INSERT INTO `test` (`testID`, `patientID`, `type_of_test`, `results`)
VALUES
	(1,1,'Blood Test',NULL),
	(2,2,'Urine Test',NULL),
	(3,3,'X-ray',NULL);


UNLOCK TABLES;
