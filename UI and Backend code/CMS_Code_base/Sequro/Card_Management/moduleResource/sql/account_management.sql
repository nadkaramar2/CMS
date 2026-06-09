-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.27-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for account_management
CREATE DATABASE IF NOT EXISTS `account_management` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `account_management`;

-- Dumping structure for table account_management.account_credit_limit_category
CREATE TABLE IF NOT EXISTS `account_credit_limit_category` (
  `id` int(11) DEFAULT NULL,
  `credit_type` varchar(50) DEFAULT NULL,
  `credit_limit` bigint(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.account_credit_limit_category: ~0 rows (approximately)
/*!40000 ALTER TABLE `account_credit_limit_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_credit_limit_category` ENABLE KEYS */;

-- Dumping structure for table account_management.account_master
CREATE TABLE IF NOT EXISTS `account_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant_id` int(20) DEFAULT NULL,
  `account_type` varchar(20) NOT NULL,
  `account_number` varchar(20) NOT NULL,
  `title` varchar(10) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `middle_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(50) DEFAULT '',
  `mobile_no` bigint(20) DEFAULT NULL,
  `address1` varchar(250) DEFAULT NULL,
  `address2` varchar(250) DEFAULT NULL,
  `address3` varchar(250) DEFAULT NULL,
  `pincode` varchar(20) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `phone_no` bigint(20) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(50) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `credit_limit_category` varchar(10) DEFAULT NULL,
  `credit_limit_amount` bigint(20) NOT NULL,
  `available_credit_limit` bigint(20) DEFAULT NULL,
  `tax_type` varchar(10) DEFAULT NULL,
  `is_instant_account` varchar(5) DEFAULT NULL,
  `opening_balance` varchar(50) DEFAULT NULL,
  `closing_balance` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.account_master: ~17 rows (approximately)
/*!40000 ALTER TABLE `account_master` DISABLE KEYS */;
INSERT INTO `account_master` (`id`, `participant_id`, `account_type`, `account_number`, `title`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `email`, `mobile_no`, `address1`, `address2`, `address3`, `pincode`, `city`, `state`, `country`, `phone_no`, `creation_date`, `created_by`, `status`, `credit_limit_category`, `credit_limit_amount`, `available_credit_limit`, `tax_type`, `is_instant_account`, `opening_balance`, `closing_balance`) VALUES
	(18, 6, 'CRD', '300000000000004', 'Mr', 'Rahul', 'Vijay', 'Ajay', 'Male', '2022-11-01', 'prashanttayde98@gmail.com', 291305922, 'Bhandup', 'Bhandup', '', '400042', '6433', '165', '101', 8291305922, '2022-11-16 00:18:11', NULL, 'Active', NULL, 0, NULL, NULL, 'N', NULL, NULL),
	(19, 6, 'GEN', '100000000010', 'Mr', 'Prashant', 'Devidas', 'Tayde', 'Male', '2022-11-15', 'prashanttayde98@gmail.com', 8291305922, 'Bhandup', 'Bhandup', 'Bhandup', '400042', '2707', '22', '101', 8291305922, '2022-11-18 17:01:38', NULL, 'Active', NULL, 0, NULL, NULL, 'N', NULL, NULL),
	(21, 6, 'TEST', '10000000000001', 'Mr', 'aftab', '', 'Khan', 'Male', '2022-12-06', 'prashanttayde98@gmail.com', 8291305922, 'Bhandup', 'Bhandup', 'Bhandup', '400042', '2707', '22', '101', 8291305922, '2022-11-18 17:05:33', NULL, 'Active', NULL, 0, NULL, NULL, 'N', NULL, NULL),
	(22, 6, 'GEN', '100000000021', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(23, 6, 'GEN', '100000000022', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(24, 6, 'GEN', '100000000023', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(25, 6, 'GEN', '100000000024', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(26, 6, 'GEN', '100000000025', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(27, 6, 'GEN', '100000000026', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(28, 6, 'GEN', '100000000027', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(29, 6, 'GEN', '100000000028', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(30, 6, 'GEN', '100000000029', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(31, 6, 'GEN', '100000000030', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(32, 6, 'GEN', '100000000031', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(33, 6, 'GEN', '100000000032', NULL, 'Prashant', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-11-24 14:31:44', NULL, NULL, NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(34, 6, 'GEN', '100000000033', 'Mr', 'Prashant', 'Tayde', 'Ajay', 'Male', '2022-11-21', 'prashanttayde98@gmail.com', 8291305922, 'Bhandup', 'Bhandup', 'Bhandup', '400042', '5978', '60', '1', 8291305922, '2022-11-29 10:59:34', NULL, 'Active', NULL, 0, NULL, NULL, 'Y', NULL, NULL),
	(35, 6, 'GEN', '100000000034', 'Mr', 'Prashant', 'Prashant', 'Prashant', 'Male', '2022-11-08', 'prashanttayde98@gmail.com', 8291305922, 'Bhandup', 'Bhandup', 'Bhandup', '400042', '2707', '22', '101', 8291305922, '2022-11-29 11:35:50', NULL, 'Active', NULL, 0, NULL, NULL, 'Y', NULL, NULL);
/*!40000 ALTER TABLE `account_master` ENABLE KEYS */;

-- Dumping structure for table account_management.account_statement
CREATE TABLE IF NOT EXISTS `account_statement` (
  `id` int(11) NOT NULL,
  `account_holder_name` varchar(50) DEFAULT NULL,
  `account_number` int(11) DEFAULT NULL,
  `account_type` varchar(50) DEFAULT NULL,
  `transaction_id` varchar(50) DEFAULT NULL,
  `transaction_date` datetime DEFAULT NULL,
  `transaction_details` varchar(50) DEFAULT NULL,
  `credit_amount` bigint(20) DEFAULT NULL,
  `debit_amount` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.account_statement: ~0 rows (approximately)
/*!40000 ALTER TABLE `account_statement` DISABLE KEYS */;
/*!40000 ALTER TABLE `account_statement` ENABLE KEYS */;

-- Dumping structure for table account_management.account_type_master
CREATE TABLE IF NOT EXISTS `account_type_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant_id` int(20) DEFAULT NULL,
  `account_type` varchar(10) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `account_number_length` varchar(5) DEFAULT NULL,
  `account_number_start_digit` varchar(5) DEFAULT NULL,
  `last_account_number` varchar(20) DEFAULT NULL,
  `is_credit_account` varchar(5) DEFAULT NULL,
  `creation_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.account_type_master: ~6 rows (approximately)
/*!40000 ALTER TABLE `account_type_master` DISABLE KEYS */;
INSERT INTO `account_type_master` (`id`, `participant_id`, `account_type`, `description`, `account_number_length`, `account_number_start_digit`, `last_account_number`, `is_credit_account`, `creation_date`, `status`, `created_by`) VALUES
	(10, 6, 'GEN', 'GENERAL', '12', '1', '100000000034', NULL, '2022-11-14 14:45:00', 'YES', NULL),
	(11, 6, 'CRD', 'Credit', '15', '4', '300000000000018', NULL, '2022-11-14 16:43:58', 'YES', NULL),
	(13, 6, 'TEST', 'wwwww', '14', '1', '10000000000001', NULL, '2022-11-18 16:55:31', 'YES', NULL),
	(14, 6, 'SAVING', 'Test', '14', '3', '30000000000000', NULL, '2022-11-25 14:40:48', 'YES', NULL),
	(15, 6, 'GENRAL', 'Testing', '12', '1', '100000000000', NULL, '2022-11-25 14:49:14', 'YES', NULL),
	(16, 6, 'TESTTTT', 'Data', '12', '1', '100000000000', NULL, '2022-11-25 14:50:30', 'YES', NULL);
/*!40000 ALTER TABLE `account_type_master` ENABLE KEYS */;

-- Dumping structure for table account_management.card_account_linkage_master
CREATE TABLE IF NOT EXISTS `card_account_linkage_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant_id` int(20) DEFAULT NULL,
  `account_type` varchar(20) NOT NULL,
  `account_number` varchar(20) NOT NULL,
  `card_number` varchar(20) NOT NULL,
  `card_type` varchar(20) NOT NULL,
  `card_status` varchar(10) DEFAULT NULL,
  `account_status` varchar(10) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.card_account_linkage_master: ~2 rows (approximately)
/*!40000 ALTER TABLE `card_account_linkage_master` DISABLE KEYS */;
INSERT INTO `card_account_linkage_master` (`id`, `participant_id`, `account_type`, `account_number`, `card_number`, `card_type`, `card_status`, `account_status`, `creation_date`, `created_by`) VALUES
	(4, 6, 'GEN', '100000000001', '2702980504203726705', 'GOLD', NULL, NULL, NULL, NULL),
	(6, 6, 'CRD', '100000000001', '1111111111122222', 'TPS', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `card_account_linkage_master` ENABLE KEYS */;

-- Dumping structure for table account_management.interest_calculation_account_wise
CREATE TABLE IF NOT EXISTS `interest_calculation_account_wise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` varchar(50) DEFAULT NULL,
  `account_number` varchar(50) DEFAULT NULL,
  `transaction_amount` bigint(20) DEFAULT NULL,
  `transaction_id` varchar(50) DEFAULT NULL,
  `transaction_date` timestamp NULL DEFAULT NULL,
  `calculated_interest` bigint(20) DEFAULT NULL,
  `calculated_GST` varchar(50) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.interest_calculation_account_wise: ~0 rows (approximately)
/*!40000 ALTER TABLE `interest_calculation_account_wise` DISABLE KEYS */;
/*!40000 ALTER TABLE `interest_calculation_account_wise` ENABLE KEYS */;

-- Dumping structure for table account_management.mcc_wise_percentange_account_type
CREATE TABLE IF NOT EXISTS `mcc_wise_percentange_account_type` (
  `id` int(11) NOT NULL,
  `mcc_code` int(11) DEFAULT NULL,
  `percentage` bigint(20) DEFAULT NULL,
  `account_type` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.mcc_wise_percentange_account_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `mcc_wise_percentange_account_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `mcc_wise_percentange_account_type` ENABLE KEYS */;

-- Dumping structure for table account_management.outstanding_interest
CREATE TABLE IF NOT EXISTS `outstanding_interest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_type` varchar(50) DEFAULT NULL,
  `account_number` varchar(50) DEFAULT NULL,
  `total_ountstanding_interset` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.outstanding_interest: ~0 rows (approximately)
/*!40000 ALTER TABLE `outstanding_interest` DISABLE KEYS */;
/*!40000 ALTER TABLE `outstanding_interest` ENABLE KEYS */;

-- Dumping structure for table account_management.tax_config
CREATE TABLE IF NOT EXISTS `tax_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tax_type` varchar(50) DEFAULT NULL,
  `tax_value` bigint(20) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table account_management.tax_config: ~0 rows (approximately)
/*!40000 ALTER TABLE `tax_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax_config` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
