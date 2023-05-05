SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `hospital`
USE `hospital`;

DROP TABLE IF EXISTS `appointments`;
CREATE TABLE IF NOT EXISTS `appointments` (
  `appointmentID` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `patientID` bigint(11) UNSIGNED NOT NULL,
  `doctorID` bigint(11) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`appointmentID`),
  KEY `patientID` (`patientID`),
  KEY `doctorID` (`doctorID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `appointments` (`appointmentID`, `patientID`, `doctorID`, `date`, `time`) VALUES
(1, 1, 1, '2023-01-02', '14:45:00'),
(3, 3, 2, '2022-11-09', '09:00:00'),
(20, 12345678900, 34158205463, '2023-01-27', '10:30:00'),
(21, 12475408654, 36618328161, '2023-05-06', '10:15:00'),
(22, 22222222222, 80482780129, '2023-05-06', '10:00:00'),
(23, 22222222222, 79038763357, '2023-05-27', '16:45:00'),
(24, 2, 3, '2023-04-23', '13:15:00');


DROP TABLE IF EXISTS `bills`;
CREATE TABLE IF NOT EXISTS `bills` (
  `patientID` bigint(11) UNSIGNED NOT NULL,
  `basic_amount` double NOT NULL,
  `amount_assured` double NOT NULL,
  `date` date NOT NULL,
  KEY `patientID` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `bills` (`patientID`, `basic_amount`, `amount_assured`, `date`) VALUES
(3, 100, 10, '2023-01-02'),
(2, 200, 100, '2023-01-02'),
(3, 100, 80, '2023-01-08'),
(12345678900, 125, 0, '2023-01-08'),
(12475408654, 1800, 0, '2023-01-08'),
(35745795416, 180, 0, '2023-01-08'),
(53076288498, 100, 80, '2023-01-08'),
(63870583195, 90, 75, '2023-01-08'),
(98147918741, 250, 150, '2023-01-08'),
(22222222222, 250, 100, '2023-01-08');

DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE IF NOT EXISTS `diagnosis` (
  `patientID` bigint(11) UNSIGNED NOT NULL,
  `symptoms` text NOT NULL,
  `diagnosis` varchar(225) NOT NULL,
  `date` date NOT NULL,
  KEY `patientID` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `diagnosis` (`patientID`, `symptoms`, `diagnosis`, `date`) VALUES
(12475408654, 'pain\nswelling\ntenderness\ndeformity', 'Nondisplaced fracture of neck of other metacarpal bone', '2023-01-08'),
(35745795416, '- headache\n- moving difficulty\n- easy bruising\n- chills \n- heartburn', 'Numbing Pox', '2023-01-08'),
(53076288498, '- mouth sores\n- hair loss\n- dizziness\n- bad breath \n- feeling tired', 'Mild Poisoning', '2023-01-08'),
(63870583195, '- shoulder pain\n- tingling feet\n- leg pain\n- temporary blindness \n- breathing difficulty ', 'Nervous Influenza', '2023-01-08'),
(98147918741, '- coughing\n- a runny nose\n- neck pain\n- excessive yawning \n- confusion', 'Ancient Euphoria', '2023-01-08'),
(12345678900, '- feeling sleepy\n- knee pain\n- hair loss\n- bloody nose \n- a shortness of breath ', 'Wired Syndrome', '2023-01-08'),
(1, '- bad breath\n- eye twitching\n- memory loss\n- drooling \n- headache ', 'Vein Head', '2023-01-08'),
(2, '- a fever\n- a rash\n- mouth sores\n- temporary blindness \n- leg pain', 'Hell Cramps', '2023-01-08'),
(3, '- cloudy urine\n- inattention\n- confusion\n- ankle pain \n- vomiting', 'Terrifying Heart', '2023-01-08');

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `identification_number` bigint(11) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL,
  `pass` varchar(12) NOT NULL,
  `subspecialty` varchar(50) NOT NULL,
  `gender` enum('Female','Male','Other') NOT NULL,
  `phone` varchar(25) NOT NULL,
  PRIMARY KEY (`identification_number`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `doctor` (`identification_number`, `name`, `pass`, `subspecialty`, `gender`, `phone`) VALUES
(1, 'Michael Joseph Giordano', '1', 'Neurological Surgeon', 'Male', '1'),
(2, 'Lee Ann Van Houten-Sauter', 'lavhs', 'Family Doctor', 'Female', '3'),
(3, 'Glenn Howard Perelson', 'ghp', 'Cardiologist', 'Male', '4'),
(12341234123, 'Kelvin Smith', 'doctor', 'Doctor', 'Male', '+12341234123'),
(14728995091, 'Bessie Mccarty', 'bessi', 'Interventional radiology', 'Female', '+1 202-918-2132'),
(34158205463, 'Agnes Avila', 'agnes', 'musculoskeletal oncology', 'Other', '+90 507 207 58 50'),
(36618328161, 'Tobias Anderson', 'tobias', 'Pediatric dermatology', 'Male', '+1 239-257-5499'),
(66452485532, 'Safia Cantu', 'safia', 'medical toxicology', 'Female', '+90 532 054 13 00'),
(79038763357, 'Idris Cabrera', 'idris', 'pediatric anesthesiology', 'Male', '+90 561 612 72 18'),
(80482780129, 'Edward Wiley', 'edward', 'Immunopathology', 'Male', '+90 561 619 15 19');


DROP TABLE IF EXISTS `medicine`;
CREATE TABLE IF NOT EXISTS `medicine` (
  `medicineID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `stock` int(225) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`medicineID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `medicine` (`medicineID`, `name`, `stock`) VALUES
(1, 'Metformin', 65),
(2, 'Bactepan', 23),
(3, 'Lysotrope Ethozoxane', 14),
(4, 'Magvital', 25),
(10, 'Mepronase', 34),
(11, 'Amlofase', 75),
(12, 'Cafestar', 32),
(13, 'Betacetam', 95),
(14, 'Tercoxacin', 89),
(15, 'Infudipine', 45),
(16, 'Beclostone Cereclotide', 12),
(17, 'Dextrosomax Albendamide', 56),
(18, 'Atracupenem Olazoxane', 74),
(19, 'Tetrasomax Baratensin', 87);

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `identification_number` bigint(11) UNSIGNED NOT NULL,
  `name` varchar(100) NOT NULL,
  `pass` varchar(12) NOT NULL,
  `age` int(3) UNSIGNED NOT NULL,
  `gender` enum('Female','Male','Other') NOT NULL,
  `phone` varchar(25) NOT NULL,
  PRIMARY KEY (`identification_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `patient` (`identification_number`, `name`, `pass`, `age`, `gender`, `phone`) VALUES
(1, 'test', 'test', 40, 'Male', '+904325009020'),
(2, 'testing', 'testing', 50, 'Other', '+354424143413'),
(3, 'Adelina Nairn', '61771991', 41, 'Female', '1'),
(12345678900, 'Jake Tormend', '2', 23, 'Male', '+905324567742'),
(12475408654, 'Ziggi Zaggi', 'zigzag', 54, 'Other', '+907358367837'),
(22222222222, 'Light Saber', '111', 22, 'Male', '22222222222'),
(35745795416, 'Derek French', 'derek', 34, 'Male', '+90 561 610 23 23'),
(53076288498, 'Fatih Kaza', 'demo', 32, 'Male', '+905307628849'),
(63870583195, 'sarah dienta', 'dinadina', 12, 'Female', '+63870583195'),
(98147918741, 'Kelsey Candy', 'nice', 24, 'Female', '+905237652343');

DROP TABLE IF EXISTS `prescription`;
CREATE TABLE IF NOT EXISTS `prescription` (
  `patientID` bigint(11) UNSIGNED NOT NULL,
  `medicineID` int(11) UNSIGNED NOT NULL,
  `dosage` varchar(225) NOT NULL,
  `date` date NOT NULL,
  KEY `medicineID` (`medicineID`),
  KEY `patientID` (`patientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `prescription` (`patientID`, `medicineID`, `dosage`, `date`) VALUES
(3, 3, '750mg', '2022-11-14'),
(53076288498, 1, '500mg', '2023-01-02'),
(1, 10, '500mg', '2023-01-08'),
(2, 1, '500mg', '2023-01-08'),
(3, 12, '1000mg', '2023-01-08'),
(12345678900, 13, '1000mg', '2023-01-08'),
(12475408654, 4, '100mg', '2023-01-08'),
(35745795416, 15, '500mg', '2023-01-08'),
(53076288498, 16, '500mg', '2023-01-08'),
(63870583195, 17, '100mg', '2023-01-08'),
(63870583195, 18, '1000mg', '2023-01-08');


DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `patientID` bigint(11) UNSIGNED NOT NULL,
  `type_of_test` bigint(11) UNSIGNED NOT NULL,
  `results` text DEFAULT NULL,
  `date` date NOT NULL,
  KEY `patientID` (`patientID`),
  KEY `type_of_test` (`type_of_test`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `test` (`patientID`, `type_of_test`, `results`, `date`) VALUES
(1, 1, '101,01', '2023-01-02'),
(2, 5, '5,5', '2023-01-02'),
(3, 9, '10,9', '2023-01-02'),
(3, 9, '3,9', '2023-01-02'),
(1, 5, '99,8', '2023-01-07'),
(1, 4, '10,44', '2023-01-07'),
(3, 1, '20,9', '2023-01-07'),
(1, 3, '103,3', '2023-01-07');

DROP TABLE IF EXISTS `type_of_test`;
CREATE TABLE IF NOT EXISTS `type_of_test` (
  `typeID` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_of_test` varchar(225) NOT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `type_of_test` (`typeID`, `type_of_test`) VALUES
(1, 'X-ray'),
(2, 'Concussion Test'),
(3, 'MR'),
(4, 'Ultrasound'),
(5, 'Allergy Blood '),
(6, 'Heart Disease Risk Assessment'),
(7, 'Vitamin D'),
(8, 'Yeast Infection'),
(9, 'Hemoglobin'),
(10, 'Glucose in Urine'),
(11, 'Calcium in Blood'),
(12, 'Blood Glucose'),
(13, 'Appendicitis Tests'),
(14, 'Pregnancy Test'),
(15, 'Sleep Study'),
(16, 'TSH ');

ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`doctorID`) REFERENCES `doctor` (`identification_number`) ON DELETE CASCADE,
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE;

ALTER TABLE `bills`
  ADD CONSTRAINT `bills_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE;

ALTER TABLE `diagnosis`
  ADD CONSTRAINT `diagnosis_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE;

ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`medicineID`) REFERENCES `medicine` (`medicineID`),
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE;

ALTER TABLE `test`
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`patientID`) REFERENCES `patient` (`identification_number`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `test_ibfk_2` FOREIGN KEY (`type_of_test`) REFERENCES `type_of_test` (`typeID`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

