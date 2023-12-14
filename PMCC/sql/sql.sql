-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.3.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for timekeeper
DROP DATABASE IF EXISTS `timekeeper`;
CREATE DATABASE IF NOT EXISTS `timekeeper` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `timekeeper`;

-- Dumping structure for table timekeeper.department
DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table timekeeper.department: ~2 rows (approximately)
INSERT INTO `department` (`id`, `NAME`) VALUES
	(1, 'Phòng backend'),
	(2, 'Phòng frontend'),
	(3, 'Phòng nhân sự');

-- Dumping structure for table timekeeper.detail_records
DROP TABLE IF EXISTS `detail_records`;
CREATE TABLE IF NOT EXISTS `detail_records` (
  `employee_id` int(11) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `detail_records_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table timekeeper.detail_records: ~4 rows (approximately)
INSERT INTO `detail_records` (`employee_id`, `log_time`) VALUES
	(5, '2023-12-11 21:54:50'),
	(4, '2023-12-11 21:48:47'),
	(3, '2023-12-11 21:55:01');

-- Dumping structure for table timekeeper.employee
DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL DEFAULT 0,
  `NAME` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CODE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table timekeeper.employee: ~6 rows (approximately)
INSERT INTO `employee` (`id`, `NAME`, `CODE`) VALUES
	(1, 'Trịnh Văn Quyết', '1001'),
	(2, 'Phạm Nhật Vượng', '1002'),
	(3, 'Nguyễn Phương Hằng', '1003'),
	(4, 'Nguyễn Chí Tôn', '1004'),
	(5, 'Trần Tử Quân', '1005'),
	(6, 'Nguyễn Quốc Tế', '1006'),
	(7, 'Trịnh Minh Hiếu', '20204554');

-- Dumping structure for table timekeeper.employee_department
DROP TABLE IF EXISTS `employee_department`;
CREATE TABLE IF NOT EXISTS `employee_department` (
  `employee_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `position` int(11) DEFAULT NULL,
  KEY `employee_id` (`employee_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `employee_department_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `employee_department_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table timekeeper.employee_department: ~4 rows (approximately)
INSERT INTO `employee_department` (`employee_id`, `department_id`, `position`) VALUES
	(7, 1, 4),
	(1, 1, 1),
	(7, 3, 4),
	(7, 2, 4);

-- Dumping structure for table timekeeper.officers_records
DROP TABLE IF EXISTS `officers_records`;
CREATE TABLE IF NOT EXISTS `officers_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_code` varchar(20) DEFAULT NULL,
  `morningSession` binary(1) DEFAULT NULL,
  `afternoonSession` binary(1) DEFAULT NULL,
  `hoursLate` decimal(10,2) DEFAULT 0.00,
  `hoursEarlyLeave` decimal(10,2) DEFAULT 0.00,
  `record_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table timekeeper.officers_records: ~2 rows (approximately)
INSERT INTO `officers_records` (`id`, `employee_code`, `morningSession`, `afternoonSession`, `hoursLate`, `hoursEarlyLeave`, `record_date`) VALUES
	(1, '20204554', _binary 0x31, _binary 0x30, 0.25, 0.10, '2023-12-11'),
	(2, '20204554', _binary 0x31, _binary 0x30, 0.30, 0.20, '2023-12-11');

-- Dumping structure for table timekeeper.workers_records
DROP TABLE IF EXISTS `workers_records`;
CREATE TABLE IF NOT EXISTS `workers_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_code` varchar(20) DEFAULT NULL,
  `shift1` decimal(10,2) DEFAULT 0.00,
  `shift2` decimal(10,2) DEFAULT 0.00,
  `shift3` decimal(10,2) DEFAULT 0.00,
  `record_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table timekeeper.workers_records: ~2 rows (approximately)
INSERT INTO `workers_records` (`id`, `employee_code`, `shift1`, `shift2`, `shift3`, `record_date`) VALUES
	(1, '20204555', 4.00, 2.50, 3.00, '2023-12-11'),
	(2, '20204555', 0.00, 3.00, 4.00, '2023-12-10');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
