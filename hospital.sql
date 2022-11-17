
SET NAMES utf8mb4;



# Dump of table appointments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `appointments`;

CREATE TABLE `appointments` (
  `appointmentID` int(11) unsigned NOT NULL,
  `patientID` int(11) unsigned NOT NULL,
  `doctorID` int(11) unsigned NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`appointmentID`),
  KEY `patientID` (`patientID`),
  KEY `doctorID` (`doctorID`),
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`),
  CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`doctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;

INSERT INTO `appointments` (`appointmentID`, `patientID`, `doctorID`, `date`)
VALUES
	(1,1,1,'2022-11-09 09:00:00'),
	(2,2,3,'2022-11-09 09:30:00'),
	(3,3,2,'2022-11-09 09:00:00');

/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bills
# ------------------------------------------------------------

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
  CONSTRAINT `bills_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `bills` WRITE;
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;

INSERT INTO `bills` (`bill_id`, `patientID`, `received_treatment`, `basic_amount`, `amount_assured`, `bill_status`)
VALUES
	(1,2,'Send home for rest with prescripted',200,40,'Paid'),
	(2,1,'Still undergoing treatment ',NULL,NULL,'Pending'),
	(3,3,'Still undergoing treatment ',NULL,NULL,'Pending');

/*!40000 ALTER TABLE `bills` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table diagnosis
# ------------------------------------------------------------

DROP TABLE IF EXISTS `diagnosis`;

CREATE TABLE `diagnosis` (
  `diagnosisID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` int(11) unsigned NOT NULL,
  `testID` int(11) unsigned NOT NULL,
  `diagnosis` text DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `prescriptionID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`diagnosisID`),
  KEY `patientID` (`patientID`),
  KEY `testID` (`testID`),
  KEY `prescriptionID` (`prescriptionID`),
  CONSTRAINT `diagnosis_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`),
  CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`testID`) REFERENCES `test` (`testID`),
  CONSTRAINT `diagnosis_ibfk_3` FOREIGN KEY (`prescriptionID`) REFERENCES `prescription` (`prescriptionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `diagnosis` WRITE;
/*!40000 ALTER TABLE `diagnosis` DISABLE KEYS */;

INSERT INTO `diagnosis` (`diagnosisID`, `patientID`, `testID`, `diagnosis`, `status`, `prescriptionID`)
VALUES
	(1,1,1,'Diabetes',X'66696E616C697A6564',1),
	(2,2,2,NULL,X'70656E64696E67',NULL),
	(3,3,3,'Acinetobacter Infection',X'66696E616C697A6564',3);

/*!40000 ALTER TABLE `diagnosis` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table doctor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `doctorID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `subspecialty` varchar(50) DEFAULT NULL,
  `password` varchar(12) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `gender` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` text DEFAULT NULL,
  `SSN` int(9) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`doctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;

INSERT INTO `doctor` (`doctorID`, `name`, `subspecialty`, `password`, `age`, `gender`, `address`, `SSN`, `email`)
VALUES
	(1,'Michael Joseph Giordano','Neurological Surgeon','1234567809',52,X'4D','263 Farmington Ave  Farmington/Hartford',274829705,'micheal@doctor.com'),
	(2,'Lee Ann Van Houten-Sauter','Family Doctor','34yo14910-1',34,X'46','220 Pine St Williamstown/Gloucester',847108740,NULL),
	(3,'Glenn Howard Perelson','Cardiologist','392i-fjmjwp3',43,X'4D','740 Bay Blvd Chula Vista/San Diego',402898482,NULL);

/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table medicine
# ------------------------------------------------------------

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `medicineID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `stock` int(225) DEFAULT NULL,
  PRIMARY KEY (`medicineID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;

INSERT INTO `medicine` (`medicineID`, `name`, `stock`)
VALUES
	(1,'Metformin',65),
	(2,'Bactepan',23),
	(3,'Lysotrope Ethozoxane',14);

/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table patient
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `patientID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(12) NOT NULL,
  `ssn` int(9) NOT NULL,
  `age` int(3) NOT NULL,
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` text DEFAULT NULL,
  PRIMARY KEY (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;

INSERT INTO `patient` (`patientID`, `name`, `password`, `ssn`, `age`, `gender`, `address`)
VALUES
	(1,'Mabel Mcgregor','i3ejr2ooujfo',274298402,18,X'46','9534 Fulton Street Salem, MA 01970'),
	(2,'Nicholas Cervantes','wklemdfw',525028119,61,X'4D','8 Bridgeton Street Bradenton, FL 34203'),
	(3,'Adelina Nairn','61771991',817719810,41,X'46','9109 Richardson Street Grand Rapids, MI 49503');

/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table prescription
# ------------------------------------------------------------

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
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;

INSERT INTO `prescription` (`prescriptionID`, `medicineID`, `dosage`)
VALUES
	(1,1,'500mg'),
	(2,3,'750mg'),
	(3,2,'250mg');

/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table room
# ------------------------------------------------------------

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_number` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `purpose` varchar(50) DEFAULT NULL,
  `availability` tinytext DEFAULT NULL,
  PRIMARY KEY (`room_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;

INSERT INTO `room` (`room_number`, `purpose`, `availability`)
VALUES
	(1,'Operating Room','Y'),
	(2,'Patient Room','N'),
	(3,'Patient Room','Y'),
	(4,'Emergency Room','N'),
	(5,'Patient Room','Y');

/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table stay
# ------------------------------------------------------------

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
  CONSTRAINT `stay_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `stay` WRITE;
/*!40000 ALTER TABLE `stay` DISABLE KEYS */;

INSERT INTO `stay` (`room_number`, `patientID`, `reason`, `date_entered`, `recovery_status`, `number_of_days`)
VALUES
	(2,1,'High blood sugar','2022-11-09','Patient has shown improvement.',2),
	(4,3,'Dangerous Infection','2022-11-09','Shows signs of possible expo',5);

/*!40000 ALTER TABLE `stay` ENABLE KEYS */;
UNLOCK TABLES;


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

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;

INSERT INTO `test` (`testID`, `patientID`, `type_of_test`, `results`)
VALUES
	(1,1,'Blood Test',NULL),
	(2,2,'Urine Test',NULL),
	(3,3,'X-ray',NULL);

/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
