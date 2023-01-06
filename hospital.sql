SET NAMES utf8mb4;

DROP TABLE IF EXISTS `appointments`;

CREATE TABLE `appointments` (
  `appointmentID` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `patientID` bigint(11) unsigned NOT NULL,
  `doctorID` bigint(11) unsigned NOT NULL,
  `date` date NOT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`appointmentID`),
  KEY `patientID` (`patientID`),
  KEY `doctorID` (`doctorID`),
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`identification_number`) ON DELETE CASCADE,
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `appointments` WRITE;

INSERT INTO `appointments` (`appointmentID`, `patientID`, `doctorID`, `date`, `time`)
VALUES
	(1,1,1,'2023-01-02','14:45:00'),
	(3,3,2,'2022-11-09','09:00:00'),
	(4,1,1,'2023-03-04','09:45:00');

UNLOCK TABLES;

DROP TABLE IF EXISTS `bills`;

CREATE TABLE `bills` (
  `patientID` bigint(11) unsigned NOT NULL,
  `basic_amount` double NOT NULL,
  `amount_assured` double NOT NULL,
  `date` date NOT NULL,
  KEY `patientID` (`patientID`),
  CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `bills` WRITE;

INSERT INTO `bills` (`patientID`, `basic_amount`, `amount_assured`, `date`)
VALUES
	(3,100,0,'2023-01-02'),
	(2,200,100,'2023-01-02');

UNLOCK TABLES;

DROP TABLE IF EXISTS `diagnosis`;

CREATE TABLE `diagnosis` (
  `patientID` bigint(11) unsigned NOT NULL,
  `symptoms` text NOT NULL,
  `diagnosis` varchar(225) NOT NULL,
  `date` date NOT NULL,
  KEY `patientID` (`patientID`),
  CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `identification_number` bigint(11) unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `pass` varchar(12) NOT NULL,
  `subspecialty` varchar(50) NOT NULL,
  `gender` enum('Female','Male','Other') NOT NULL,
  `phone` varchar(25) NOT NULL,
  PRIMARY KEY (`identification_number`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `doctor` WRITE;

INSERT INTO `doctor` (`identification_number`, `name`, `pass`, `subspecialty`, `gender`, `phone`)
VALUES
	(1,'Michael Joseph Giordano','1','Neurological Surgeon','Male','1'),
	(2,'Lee Ann Van Houten-Sauter','lavhs','Family Doctor','Female','3'),
	(3,'Glenn Howard Perelson','ghp','Cardiologist','Male','4'),
	(12345678911,'Mike Drop','mike123','Dermatologist','Male','+905324567865'),
	(21161191122,'Mimi Stantez Gordino','LoveMeMimi','Family Doctor','Other','+18398723081');


UNLOCK TABLES;

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `medicineID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `stock` int(225) unsigned DEFAULT NULL,
  PRIMARY KEY (`medicineID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `medicine` WRITE;

INSERT INTO `medicine` (`medicineID`, `name`, `stock`)
VALUES
	(1,'Metformin',65),
	(2,'Bactepan',23),
	(3,'Lysotrope Ethozoxane',14),
	(4,'Magvital',25);

UNLOCK TABLES;

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `identification_number` bigint(11) unsigned NOT NULL,
  `name` varchar(100) NOT NULL,
  `pass` varchar(12) NOT NULL,
  `age` int(3) unsigned NOT NULL,
  `gender` enum('Female','Male','Other') NOT NULL,
  `phone` varchar(25) NOT NULL,
  PRIMARY KEY (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `patient` WRITE;

INSERT INTO `patient` (`identification_number`, `name`, `pass`, `age`, `gender`, `phone`)
VALUES
	(1,'test','test',40,'Male','+904325009020'),
	(2,'testing','testing',50,'Other','+354424143413'),
	(3,'Adelina Nairn','61771991',41,'Female','1'),
	(12345678900,'Jake Tormend','2',23,'Male','+905324567742'),
	(12475408654,'Ziggi Zaggi','zigzag',54,'Other','+907358367837'),
	(22222222222,'Light Saber','111',22,'Male','22222222222'),
	(53076288498,'Fatih Kaza','demo',32,'Male','+905307628849'),
	(63870583195,'sarah dienta','dinadina',12,'Female','+63870583195'),
	(65914025136,'Sirin Gulec','sirin',20,'Female','+905441320016'),
	(98147918741,'Kelsey Candy','nice',24,'Female','+905237652343');

UNLOCK TABLES;

DROP TABLE IF EXISTS `prescription`;

CREATE TABLE `prescription` (
  `patientID` bigint(11) unsigned NOT NULL,
  `medicineID` int(11) unsigned NOT NULL,
  `dosage` varchar(225) NOT NULL,
  `date` date NOT NULL,
  KEY `medicineID` (`medicineID`),
  KEY `patientID` (`patientID`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`medicineID`) REFERENCES `medicine` (`medicineID`),
  CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `prescription` WRITE;

INSERT INTO `prescription` (`patientID`, `medicineID`, `dosage`, `date`)
VALUES
	(3,3,'750mg','2022-11-14'),
	(1,4,'500mg','2023-01-01'),
	(53076288498,1,'500mg','2023-01-02'),
	(1,1,'100mg','2022-12-02');

UNLOCK TABLES;

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `patientID` bigint(11) unsigned NOT NULL,
  `type_of_test` bigint(11) unsigned NOT NULL,
  `results` text DEFAULT NULL,
  `date` date NOT NULL,
  KEY `patientID` (`patientID`),
  KEY `type_of_test` (`type_of_test`),
  CONSTRAINT `test_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `test_ibfk_2` FOREIGN KEY (`type_of_test`) REFERENCES `type_of_test` (`typeID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `test` WRITE;


INSERT INTO `test` (`patientID`, `type_of_test`, `results`, `date`)
VALUES
	(1,1,NULL,'2023-01-02'),
	(2,5,NULL,'2023-01-02'),
	(3,9,NULL,'2023-01-02'),
	(3,9,NULL,'2023-01-02');

UNLOCK TABLES;

DROP TABLE IF EXISTS `type_of_test`;

CREATE TABLE `type_of_test` (
  `typeID` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `type_of_test` varchar(225) NOT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

LOCK TABLES `type_of_test` WRITE;

INSERT INTO `type_of_test` (`typeID`, `type_of_test`)
VALUES
	(1,'X-ray'),
	(2,'Concussion Test'),
	(3,'MR'),
	(4,'Ultrasound'),
	(5,'Allergy Blood '),
	(6,'Heart Disease Risk Assessment'),
	(7,'Vitamin D'),
	(8,'Yeast Infection'),
	(9,'Hemoglobin'),
	(10,'Glucose in Urine'),
	(11,'Calcium in Blood'),
	(12,'Blood Glucose'),
	(13,'Appendicitis Tests'),
	(14,'Pregnancy Test'),
	(15,'Sleep Study'),
	(16,'TSH ');

UNLOCK TABLES;
