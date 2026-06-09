-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.7.27-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for traneco_cmsdb
CREATE DATABASE IF NOT EXISTS `traneco_cmsdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `traneco_cmsdb`;

-- Dumping structure for table traneco_cmsdb.cfg_cms_menu
CREATE TABLE IF NOT EXISTS `cfg_cms_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menu_name` char(100) NOT NULL,
  `description` char(200) DEFAULT NULL,
  `parent_menu_id` int(10) DEFAULT NULL,
  `action_name` char(40) DEFAULT NULL,
  `parent_submenu_id` int(10) DEFAULT NULL,
  `menu_icon` char(20) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menuname_index` (`menu_name`),
  KEY `parentmenuid_index` (`parent_menu_id`),
  KEY `actionname_index` (`action_name`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_cms_menu: ~34 rows (approximately)
/*!40000 ALTER TABLE `cfg_cms_menu` DISABLE KEYS */;
INSERT INTO `cfg_cms_menu` (`id`, `menu_name`, `description`, `parent_menu_id`, `action_name`, `parent_submenu_id`, `menu_icon`, `status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES
	(5, 'Add User', 'Add User', 1, 'addUserForm', 2, NULL, b'1', NULL, '2019-01-28 22:59:02', NULL, NULL),
	(6, 'Edit User', 'Edit User', 1, 'editUserForm', 2, NULL, b'1', NULL, '2019-01-28 22:59:12', NULL, NULL),
	(7, 'Approve User', 'Approve User', 1, 'approveUserForm', 2, NULL, b'1', NULL, '2019-01-28 22:59:23', NULL, NULL),
	(8, 'View User', 'View User', 1, 'viewUserForm', 2, NULL, b'1', NULL, '2019-01-28 22:59:32', NULL, NULL),
	(9, 'Add Role', 'Add Role', 1, 'addRoleForm', 3, NULL, b'1', NULL, '2019-01-28 22:59:38', NULL, NULL),
	(11, 'Approve Role', 'Approve Role', 1, 'approveRoleForm', 3, NULL, b'1', NULL, '2019-01-28 23:00:03', NULL, NULL),
	(13, 'Add Client', 'Add Client', 1, 'addClientForm', 4, NULL, b'1', NULL, '2019-02-03 20:56:22', NULL, NULL),
	(15, 'Embossing', 'Embossing', 1, 'addEmbossingForm', 4, NULL, b'1', NULL, '2019-03-21 15:12:50', NULL, NULL),
	(16, 'Bin Configuration', 'Bin Configuration', 1, 'binConfigForm', 5, NULL, b'1', NULL, '2019-04-07 13:33:49', NULL, NULL),
	(17, 'Country Configuration', 'Country Configuration', 100, 'countryConfigForm', 500, NULL, b'1', NULL, '2019-04-07 15:24:38', NULL, NULL),
	(18, 'State Configuration', 'State Configuration', 100, 'stateConfigForm', 500, NULL, b'1', NULL, '2019-04-07 16:22:00', NULL, NULL),
	(19, 'Branch Configuartion', 'Branch Configuartion', 1, 'branchConfigForm', 5, NULL, b'1', NULL, '2019-04-07 18:42:24', NULL, NULL),
	(27, 'Phone Type Configuartion', 'Phone Type Configuartion', 100, 'phoneConfigForm', 500, NULL, b'1', NULL, '2019-04-16 19:14:44', NULL, NULL),
	(28, 'NCMC Code Configuartion', 'NCMC Code Configuartion', 1, 'ncmcConfigForm', 5, NULL, b'1', NULL, '2019-04-16 20:11:57', NULL, NULL),
	(29, 'Key Configuartion', 'Key Configuartion', 1, 'keyConfigForm', 5, NULL, b'1', NULL, '2019-04-16 20:50:53', NULL, NULL),
	(30, 'Add Role Group', 'Add Role Group', 1, 'addGroupForm', 3, NULL, b'1', NULL, '2019-05-04 12:32:10', NULL, NULL),
	(32, 'NCMC Service Configuartion', 'NCMC Service Configuartion', 1, 'addNcmcConfigForm', 5, NULL, b'1', NULL, '2019-05-28 22:48:54', NULL, NULL),
	(33, 'Manage Instant Cards', 'Manage Instant Cards', 1, 'addInstantCardsForm', 4, NULL, b'1', NULL, '2019-05-31 19:47:41', NULL, NULL),
	(34, 'Pin Mailer Printing', 'Pin Mailer Printing', 1, 'addPinPrintingForm', 4, NULL, b'1', NULL, '2019-06-03 22:10:41', NULL, NULL),
	(35, 'MCC Configuration', 'MCC Configuration', 1, 'addMccConfigForm', 5, NULL, b'1', NULL, '2019-07-03 07:47:44', NULL, NULL),
	(36, 'Bulk Upload', 'Bulk Upload', 1, 'addBulkUploadForm', 4, NULL, b'1', NULL, '2020-06-28 22:59:11', NULL, NULL),
	(37, 'Transaction View', 'Transaction View', 1, 'addTxnViewForm', 4, NULL, b'1', NULL, '2020-06-28 23:08:37', NULL, NULL),
	(38, 'Card Token Configuration', 'Card Token Configuration', 1, 'addCardTokenConfigForm', 5, NULL, b'1', NULL, '2020-07-19 13:10:33', NULL, NULL),
	(39, 'Inventory Management', 'Inventory Management', 1, 'inventoryForm', 4, NULL, b'1', NULL, '2020-08-15 04:40:26', NULL, NULL),
	(41, 'Mobile Token View', 'Mobile Token View', 1, 'mobileTokenViewForm', 4, NULL, b'1', NULL, '2020-09-11 18:35:32', NULL, NULL),
	(44, 'Participant Configuration', 'Participant Configuration', 1, 'partConfigForm', 5, NULL, b'1', NULL, '2022-02-15 02:30:21', NULL, NULL),
	(45, 'Transaction Report Data', 'Transaction Report Data', 1, 'transactionData', 6, NULL, b'1', NULL, '2022-05-31 16:00:11', NULL, NULL),
	(46, 'Card Inventory Issued', 'Card Inventory Issued Card', 1, 'cardInventoryIssued', 6, NULL, b'1', NULL, '2022-06-03 14:58:08', NULL, '2022-06-03 14:58:50'),
	(47, 'Card Inventory Pending', 'Card Inventory Pending', 1, 'cardInventoryPending', 6, NULL, b'1', NULL, '2022-06-13 15:42:20', NULL, NULL),
	(48, 'Card Hot Listing', 'Card Hot Listing', 1, 'cardHotListing', 6, NULL, b'1', NULL, '2022-06-13 18:15:24', NULL, '2022-06-13 18:15:25'),
	(49, 'Card Hot Listing MIS', 'Card Hot Listing MIS', 1, 'cardHotListingMIS', 6, NULL, b'1', NULL, '2022-06-13 18:16:10', NULL, '2022-06-13 18:16:11'),
	(50, 'Generate Key', 'Generate Key', 1, 'generateKey', 5, NULL, b'1', NULL, '2024-02-21 15:26:10', NULL, '2024-02-21 15:26:11'),
	(51, 'Re-Enter Key', 'Re-Enter Key', 1, 'reEnterKeyForm', 5, NULL, b'1', NULL, '2024-02-21 15:26:38', NULL, NULL),
	(52, 'Start Stop Logging', 'Start Stop Logging', 1, 'startStopLogging', 5, NULL, b'1', NULL, '2024-03-17 22:11:43', NULL, '2024-03-17 22:11:46');
/*!40000 ALTER TABLE `cfg_cms_menu` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_interface
CREATE TABLE IF NOT EXISTS `cfg_interface` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) NOT NULL AUTO_INCREMENT,
  `interface_type` char(10) NOT NULL,
  `participant_desc` varchar(50) DEFAULT NULL,
  KEY `participant_id` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_interface: ~2 rows (approximately)
/*!40000 ALTER TABLE `cfg_interface` DISABLE KEYS */;
INSERT INTO `cfg_interface` (`id`, `participant_id`, `interface_type`, `participant_desc`) VALUES
	(1, 6, 'ICICI', 'ICICIBank');
/*!40000 ALTER TABLE `cfg_interface` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_menu_action_mapping
CREATE TABLE IF NOT EXISTS `cfg_menu_action_mapping` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `menu_id` int(10) DEFAULT NULL,
  `action_name` char(30) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  KEY `Index 1` (`id`),
  KEY `Index 2` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_menu_action_mapping: ~0 rows (approximately)
/*!40000 ALTER TABLE `cfg_menu_action_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfg_menu_action_mapping` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_menu_action_mapping_copy
CREATE TABLE IF NOT EXISTS `cfg_menu_action_mapping_copy` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `menu_id` int(10) DEFAULT NULL,
  `action_name` char(30) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  KEY `Index 1` (`id`),
  KEY `Index 2` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_menu_action_mapping_copy: ~0 rows (approximately)
/*!40000 ALTER TABLE `cfg_menu_action_mapping_copy` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfg_menu_action_mapping_copy` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_parentmenu
CREATE TABLE IF NOT EXISTS `cfg_parentmenu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parentmenu_name` char(100) NOT NULL,
  `description` char(200) DEFAULT NULL,
  `action` char(30) DEFAULT NULL,
  `menu_icon` char(20) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index 2` (`parentmenu_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_parentmenu: ~0 rows (approximately)
/*!40000 ALTER TABLE `cfg_parentmenu` DISABLE KEYS */;
INSERT INTO `cfg_parentmenu` (`id`, `parentmenu_name`, `description`, `action`, `menu_icon`, `status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES
	(1, 'System Configuration', 'System Configuration', NULL, NULL, b'1', NULL, '2019-01-28 22:54:18', NULL, NULL);
/*!40000 ALTER TABLE `cfg_parentmenu` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_parent_submenu
CREATE TABLE IF NOT EXISTS `cfg_parent_submenu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `submenu_name` char(100) DEFAULT NULL,
  `description` char(200) DEFAULT NULL,
  `parentmenu_id` int(10) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index 2` (`submenu_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_parent_submenu: ~6 rows (approximately)
/*!40000 ALTER TABLE `cfg_parent_submenu` DISABLE KEYS */;
INSERT INTO `cfg_parent_submenu` (`id`, `submenu_name`, `description`, `parentmenu_id`, `status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`) VALUES
	(1, 'Institution Configuration', 'Institution Configuration', 1, b'1', NULL, '2019-01-28 22:54:45', NULL, NULL),
	(2, 'USER Management', 'USER Management', 1, b'1', NULL, '2019-01-28 22:54:53', NULL, NULL),
	(3, 'Role Management', 'Role Management', 1, b'1', NULL, '2019-01-28 22:54:57', NULL, NULL),
	(4, 'Services', 'Services', 1, b'1', NULL, '2019-02-03 20:55:09', NULL, NULL),
	(5, 'Configuration Management', 'Configuration Management', 1, b'1', NULL, '2019-04-07 13:32:08', NULL, NULL),
	(6, 'Reports', 'Reports', 1, b'1', NULL, '2022-06-02 15:12:43', NULL, '2022-06-02 15:12:44');
/*!40000 ALTER TABLE `cfg_parent_submenu` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_password_policy
CREATE TABLE IF NOT EXISTS `cfg_password_policy` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `password_policy` int(10) DEFAULT NULL,
  KEY `Index 1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_password_policy: ~0 rows (approximately)
/*!40000 ALTER TABLE `cfg_password_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfg_password_policy` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_response_code
CREATE TABLE IF NOT EXISTS `cfg_response_code` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `response_code` char(3) NOT NULL,
  `application_id` int(10) NOT NULL,
  `response_message` char(50) NOT NULL,
  KEY `Index 1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_response_code: ~0 rows (approximately)
/*!40000 ALTER TABLE `cfg_response_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfg_response_code` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_role
CREATE TABLE IF NOT EXISTS `cfg_role` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `role_name` char(100) NOT NULL,
  `role_description` tinyblob,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `approved_by` char(20) DEFAULT NULL,
  `approved_date` datetime DEFAULT NULL,
  `remarks` blob,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_index` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_role: ~9 rows (approximately)
/*!40000 ALTER TABLE `cfg_role` DISABLE KEYS */;
INSERT INTO `cfg_role` (`id`, `participant_id`, `role_name`, `role_description`, `status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `approved_by`, `approved_date`, `remarks`, `group_id`) VALUES
	(1, 6, 'Default Maker', _binary 0x44656661756C74526F6C65, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C74204D616B657220526F6C65, 1),
	(2, 6, 'Default Checker', _binary 0x53757065722041646D696E20526F6C65, b'1', '0', '2024-01-27 14:53:56', '1', '2024-01-28 23:00:14', '2', '2024-01-28 23:03:16', NULL, 2),
	(33, 6, 'SUPER ADMIN', _binary 0x53757065722041646D696E20526F6C65, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, '2', '2024-01-28 23:42:21', NULL, 31),
	(34, 6, 'ADMIN', _binary 0x41646D696E20526F6C65, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, '2', '2024-01-28 23:44:28', NULL, 32),
	(38, 6, 'MAKER', _binary 0x4D616B657220526F6C65, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, '3', '2024-01-29 00:35:24', NULL, 34),
	(39, 6, 'CHECKER', _binary 0x436865636B657220526F6C65, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, '3', '2024-01-29 00:38:24', NULL, 35),
	(40, 6, 'USER', _binary 0x5573657220526F6C65, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, '3', '2024-01-29 00:40:51', NULL, 36),
	(41, 6, 'CUSTODIAN', _binary 0x437573746F6469616E2055736572, b'1', '1', '2024-02-29 12:13:00', NULL, NULL, '2', '2024-02-29 12:13:23', NULL, 37),
	(43, 6, 'QPS_Admin', _binary 0x41646D696E20515053, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, '2', '2024-03-31 19:40:09', NULL, 38),
	(86, 6, 'Prashant Test', _binary 0x466F722054657374696E67, b'1', '1', '2024-05-09 16:05:49', NULL, NULL, NULL, NULL, NULL, 41);
/*!40000 ALTER TABLE `cfg_role` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_role_temp
CREATE TABLE IF NOT EXISTS `cfg_role_temp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `role_name` char(100) NOT NULL,
  `role_description` tinyblob,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `approved_by` char(20) DEFAULT NULL,
  `approved_date` datetime DEFAULT NULL,
  `remarks` blob,
  `approval_status_id` int(10) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index 2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_role_temp: ~10 rows (approximately)
/*!40000 ALTER TABLE `cfg_role_temp` DISABLE KEYS */;
INSERT INTO `cfg_role_temp` (`id`, `role_id`, `participant_id`, `role_name`, `role_description`, `status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `approved_by`, `approved_date`, `remarks`, `approval_status_id`, `group_id`) VALUES
	(1, 1, 6, 'Default Maker', _binary 0x44656661756C74526F6C65, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C742041646D696E526F6C65, 1, 1),
	(2, 2, 6, 'Default Checker', _binary 0x44656661756C74526F6C65, b'1', '0', '2024-01-27 12:31:35', NULL, '2024-01-27 12:31:52', '0', '2024-01-27 12:31:53', _binary 0x44656661756C742041646D696E526F6C65, 4, 2),
	(3, 31, 6, 'SUPER ADMIN', _binary 0x53757065722041646D696E20526F6C65, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, '2', '2024-01-28 23:42:21', NULL, 4, 31),
	(4, 32, 6, 'ADMIN', _binary 0x41646D696E20526F6C65, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, '2', '2024-01-28 23:44:28', NULL, 1, 32),
	(5, 33, 6, 'MAKER', _binary 0x4D616B657220526F6C65, b'1', '4', '2024-01-29 00:13:39', NULL, NULL, '3', '2024-01-29 00:17:00', NULL, 4, 34),
	(39, 34, 6, 'CHECKER', _binary 0x436865636B657220526F6C65, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, '3', '2024-01-29 00:38:24', NULL, 4, 35),
	(40, 35, 6, 'USER', _binary 0x5573657220526F6C65, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, '3', '2024-01-29 00:40:51', NULL, 1, 36),
	(41, 36, 6, 'CUSTODIAN', _binary 0x437573746F6469616E2055736572, b'1', '1', '2024-02-29 12:13:00', NULL, NULL, '2', '2024-02-29 12:13:23', NULL, 1, 37),
	(43, 42, 6, 'QPS_Admin', _binary 0x41646D696E20515053, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, '2', '2024-03-31 19:40:09', NULL, 1, 38),
	(86, 43, 6, 'Prashant Test', _binary 0x466F722054657374696E67, b'1', '1', '2024-05-09 16:05:49', NULL, NULL, NULL, NULL, NULL, 1, 41);
/*!40000 ALTER TABLE `cfg_role_temp` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_secret_question
CREATE TABLE IF NOT EXISTS `cfg_secret_question` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `participant_id` int(10) DEFAULT NULL,
  `description` char(100) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `created_by` int(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_by` int(10) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  KEY `id` (`id`),
  KEY `part_index` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_secret_question: ~2 rows (approximately)
/*!40000 ALTER TABLE `cfg_secret_question` DISABLE KEYS */;
INSERT INTO `cfg_secret_question` (`id`, `participant_id`, `description`, `status`, `created_by`, `created_date`, `modified_by`, `modified_date`) VALUES
	(1, 6, 'What is your fav color ?', b'1', NULL, '2019-02-21 22:57:00', NULL, NULL),
	(2, 6, 'What is your school name ?', b'1', NULL, '2019-02-21 22:58:29', NULL, NULL);
/*!40000 ALTER TABLE `cfg_secret_question` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_user_status
CREATE TABLE IF NOT EXISTS `cfg_user_status` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `user_status` char(20) DEFAULT NULL,
  KEY `Index 1` (`id`),
  KEY `Index 2` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_user_status: ~0 rows (approximately)
/*!40000 ALTER TABLE `cfg_user_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfg_user_status` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cfg_user_type
CREATE TABLE IF NOT EXISTS `cfg_user_type` (
  `id` int(10) NOT NULL,
  `user_type_description` char(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_user_type: ~0 rows (approximately)
/*!40000 ALTER TABLE `cfg_user_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `cfg_user_type` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.cms_audit
CREATE TABLE IF NOT EXISTS `cms_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant_id` int(10) DEFAULT NULL,
  `table_name` varchar(100) DEFAULT NULL,
  `column_name` varchar(1000) DEFAULT NULL,
  `new_field` varchar(1000) DEFAULT NULL,
  `old_field` varchar(1000) DEFAULT NULL,
  `dml_action` char(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` char(20) DEFAULT NULL,
  `approved_by` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index 2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cms_audit: ~208 rows (approximately)
/*!40000 ALTER TABLE `cms_audit` DISABLE KEYS */;
INSERT INTO `cms_audit` (`id`, `participant_id`, `table_name`, `column_name`, `new_field`, `old_field`, `dml_action`, `created_date`, `created_by`, `approved_by`) VALUES
	(1, 6, 'user_details', NULL, '[strId : 0, lastSuccessfulLogon : 2024-05-10 06:20:04, forgotPasswordValidationFailedAttempt : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, lastSuccessfulLogon : 2024-05-09 10:35:15, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-10 11:50:04', 'prashantT', NULL),
	(2, 6, 'user_details', NULL, '[strId : 0, lastSuccessfulLogon : 2024-05-16 10:49:17, forgotPasswordValidationFailedAttempt : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, lastSuccessfulLogon : 2024-05-10 06:20:04, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-16 16:19:18', 'prashantT', NULL),
	(3, 6, 'user_details', NULL, '[strId : 0, lastSuccessfulLogon : 2024-05-16 10:53:02, forgotPasswordValidationFailedAttempt : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, lastSuccessfulLogon : 2024-05-16 10:49:18, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-16 16:23:02', 'prashantT', NULL),
	(4, 6, 'user_details', NULL, '[strId : 0, lastSuccessfulLogon : 2024-05-16 11:00:36, forgotPasswordValidationFailedAttempt : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, lastSuccessfulLogon : 2024-05-16 10:53:03, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-16 16:30:37', 'prashantT', NULL),
	(5, 6, 'user_details', NULL, '[strId : 0, lastSuccessfulLogon : 2024-05-16 11:05:48, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, lastSuccessfulLogon : 2024-05-16 11:00:37, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-16 16:35:49', 'prashantT', NULL),
	(6, 6, 'user_details', NULL, '[strId : 0, lastSuccessfulLogon : 2024-05-16 11:20:34, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, lastSuccessfulLogon : 2024-05-16 11:05:49, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-16 16:50:35', 'prashantT', NULL),
	(7, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, lastSuccessfulLogon : 2024-05-16 11:48:52, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, lastSuccessfulLogon : 2024-05-16 11:20:35, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-16 17:18:53', 'prashantT', NULL),
	(8, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, lastSuccessfulLogon : 2024-05-16 12:08:13, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, lastSuccessfulLogon : 2024-05-16 12:07:23, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-16 17:38:14', 'prashantT', NULL),
	(9, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-17 09:19:42, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-16 12:08:14, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-17 14:49:43', 'prashantT', NULL),
	(10, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 06:02:19, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-17 09:19:43, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 11:32:20', 'prashantT', NULL),
	(11, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 07:36:03, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 06:02:20, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 13:06:05', 'prashantT', NULL),
	(12, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 07:39:59, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 07:36:05, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 13:09:59', 'prashantT', NULL),
	(13, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 07:45:26, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 07:39:59, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 13:15:27', 'prashantT', NULL),
	(14, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 08:06:17, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 07:45:27, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 13:36:17', 'prashantT', NULL),
	(15, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 09:55:13, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 08:06:17, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 15:25:13', 'prashantT', NULL),
	(16, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 09:57:47, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 09:55:13, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 15:27:47', 'prashantT', NULL),
	(17, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 10:00:47, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 09:57:47, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 15:30:48', 'prashantT', NULL),
	(18, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 10:02:59, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 10:00:48, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 15:33:00', 'prashantT', NULL),
	(19, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 10:06:09, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 10:03:00, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 15:36:09', 'prashantT', NULL),
	(20, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 11:10:27, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 2, strUserID : 2, strParticipantID : 6, strUserName : sachinK, strMobileNo : 8928845502, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 1, isPasswordReset : Y, plainPassword : xWAYsncUgjkAYs4xfnr7Ww==, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-09 10:56:34, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 16:40:27', 'sachinK', NULL),
	(21, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 11:22:01, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 2, strUserID : 2, strParticipantID : 6, strUserName : sachinK, strMobileNo : 8928845502, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 1, isPasswordReset : Y, plainPassword : xWAYsncUgjkAYs4xfnr7Ww==, strSensitiveDate : 0, userRoleId : 0, lastSuccessfulLogon : 2024-05-23 11:10:27, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 16:52:01', 'sachinK', NULL),
	(22, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 11:29:09, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 2, strUserID : 2, strParticipantID : 6, strUserName : sachinK, strMobileNo : 8928845502, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 1, isPasswordReset : Y, plainPassword : xWAYsncUgjkAYs4xfnr7Ww==, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 11:22:01, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 16:59:10', 'sachinK', NULL),
	(23, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 11:34:30, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 2, strUserID : 2, strParticipantID : 6, strUserName : sachinK, strMobileNo : 8928845502, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 1, isPasswordReset : Y, plainPassword : xWAYsncUgjkAYs4xfnr7Ww==, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 11:29:10, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 17:04:30', 'sachinK', NULL),
	(24, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 11:41:59, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 2, strUserID : 2, strParticipantID : 6, strUserName : sachinK, strMobileNo : 8928845502, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 1, isPasswordReset : Y, plainPassword : xWAYsncUgjkAYs4xfnr7Ww==, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 11:41:01, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-23 17:12:00', 'sachinK', NULL),
	(25, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-24 05:22:43, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 2, strUserID : 2, strParticipantID : 6, strUserName : sachinK, strMobileNo : 8928845502, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 1, isPasswordReset : Y, plainPassword : xWAYsncUgjkAYs4xfnr7Ww==, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 11:42:00, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-24 10:52:44', 'sachinK', NULL),
	(26, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-24 08:55:30, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 2, strUserID : 2, strParticipantID : 6, strUserName : sachinK, strMobileNo : 8928845502, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 1, isPasswordReset : Y, plainPassword : xWAYsncUgjkAYs4xfnr7Ww==, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-24 05:22:44, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-24 14:25:31', 'sachinK', NULL),
	(27, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 04:25:50, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-23 10:06:09, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-29 09:55:51', 'prashantT', NULL),
	(28, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 08:05:29, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 04:25:51, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-29 13:35:29', 'prashantT', NULL),
	(29, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 12:59:04, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 08:05:29, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-29 18:29:05', 'prashantT', NULL),
	(30, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 13:09:25, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 12:59:05, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-29 18:39:26', 'prashantT', NULL),
	(31, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1716988367178, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-29 18:42:47', NULL, NULL),
	(32, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 13:45:06, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 13:09:26, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-29 19:15:07', 'prashantT', NULL),
	(33, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080707132537600, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1716990503211, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-29 19:18:23', NULL, NULL),
	(34, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080707132537600, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1716991553847, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-29 19:35:55', NULL, NULL),
	(35, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080707132537600, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1716991648574, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-29 19:37:29', NULL, NULL),
	(36, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080707132537600, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717047538955, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-30 11:09:01', NULL, NULL),
	(37, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080707132537600, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717048196958, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-30 11:19:58', NULL, NULL),
	(38, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080707132537600, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717051170861, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-30 12:09:31', NULL, NULL),
	(39, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 07:59:23, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-29 13:45:07, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-30 13:29:24', 'prashantT', NULL),
	(40, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 08:14:08, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 07:59:24, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-30 13:44:09', 'prashantT', NULL),
	(41, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 10:37:18, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 08:14:09, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-30 16:07:19', 'prashantT', NULL),
	(42, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 13:54:13, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 10:37:19, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-30 19:24:14', 'prashantT', NULL),
	(43, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 18:15:16, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 13:54:14, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-30 23:45:16', 'prashantT', NULL),
	(44, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 18:37:08, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 18:15:16, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 00:07:09', 'prashantT', NULL),
	(45, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 18:57:28, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 18:37:09, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 00:27:29', 'prashantT', NULL),
	(46, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 19:03:15, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 18:57:29, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 00:33:16', 'prashantT', NULL),
	(47, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717097605202, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 01:03:28', NULL, NULL),
	(48, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717097734053, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 01:05:34', NULL, NULL),
	(49, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717097922397, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 01:08:42', NULL, NULL),
	(50, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717098372926, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 01:16:13', NULL, NULL),
	(51, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717130715756, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 10:15:16', NULL, NULL),
	(52, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717131431821, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 10:27:12', NULL, NULL),
	(53, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 06:47:35, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-30 19:03:16, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 12:17:35', 'prashantT', NULL),
	(54, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717149794964, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 15:33:15', NULL, NULL),
	(55, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717150196047, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 15:39:56', NULL, NULL),
	(56, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717153196405, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 16:29:56', NULL, NULL),
	(57, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717153349988, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 16:32:30', NULL, NULL),
	(58, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717153565832, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 16:36:06', NULL, NULL),
	(59, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080614164137500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717153915160, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 16:41:55', NULL, NULL),
	(60, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:17:48, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 06:47:35, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 16:47:48', 'prashantT', NULL),
	(61, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:26:09, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:17:48, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 16:56:10', 'prashantT', NULL),
	(62, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:37:37, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:26:10, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 17:07:37', 'prashantT', NULL),
	(63, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:52:37, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:37:37, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 17:22:38', 'prashantT', NULL),
	(64, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 218661118105316000, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717156693964, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 17:28:15', NULL, NULL),
	(65, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 13:29:23, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 11:52:38, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 18:59:24', 'prashantT', NULL),
	(66, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080708073104900, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717162279424, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 19:01:19', NULL, NULL),
	(67, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 13:47:54, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 13:29:24, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 19:17:55', 'prashantT', NULL),
	(68, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 13:56:23, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 13:47:55, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 19:26:24', 'prashantT', NULL),
	(69, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 14:09:02, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 13:56:24, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 19:39:03', 'prashantT', NULL),
	(70, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 221270101065501100, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717164928843, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 19:45:29', NULL, NULL),
	(71, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 14:36:32, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 14:09:03, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 20:06:32', 'prashantT', NULL),
	(72, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 14:55:58, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 14:36:32, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 20:25:59', 'prashantT', NULL),
	(73, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080708073104900, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717178897607, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 23:38:18', NULL, NULL),
	(74, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080708073104900, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717179040220, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 23:40:40', NULL, NULL),
	(75, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080708073104900, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717179226509, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 23:43:47', NULL, NULL),
	(76, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 18:15:15, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 14:55:59, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-05-31 23:45:16', 'prashantT', NULL),
	(77, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 225960301031902800, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717179734074, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-05-31 23:52:14', NULL, NULL),
	(78, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 18:31:05, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 18:15:16, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 00:01:06', 'prashantT', NULL),
	(79, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 165080708073104900, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717181188056, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-06-01 00:16:28', NULL, NULL),
	(80, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 18:47:35, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 18:31:06, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 00:17:35', 'prashantT', NULL),
	(81, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 19:11:50, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 18:47:35, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 00:41:51', 'prashantT', NULL),
	(82, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 08:06:16, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-05-31 19:11:51, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 13:36:17', 'prashantT', NULL),
	(83, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 08:40:03, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 08:06:17, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 14:10:04', 'prashantT', NULL),
	(84, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 09:26:21, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 08:40:04, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 14:56:22', 'prashantT', NULL),
	(85, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 11:07:11, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 09:26:22, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 16:37:11', 'prashantT', NULL),
	(86, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 11:17:04, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 11:07:11, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 16:47:04', 'prashantT', NULL),
	(87, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 13:47:49, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 11:17:04, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 19:17:50', 'prashantT', NULL),
	(88, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 14:02:51, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 13:47:50, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-01 19:32:51', 'prashantT', NULL),
	(89, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-02 08:12:18, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-01 14:02:51, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-02 13:42:19', 'prashantT', NULL),
	(90, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:26:01, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-02 08:12:19, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 11:56:02', 'prashantT', NULL),
	(91, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:30:23, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:26:02, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 12:00:23', 'prashantT', NULL),
	(92, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:32:57, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:30:23, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 12:02:57', 'prashantT', NULL),
	(93, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:36:23, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:32:57, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 12:06:24', 'prashantT', NULL),
	(94, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 07:20:45, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 06:36:24, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 12:50:45', 'prashantT', NULL),
	(95, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 07:28:19, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 07:20:45, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 12:58:20', 'prashantT', NULL),
	(96, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 07:53:50, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 07:28:20, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 13:23:50', 'prashantT', NULL),
	(97, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 08:12:11, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 07:53:50, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 13:42:12', 'prashantT', NULL),
	(98, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 08:18:52, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 08:12:12, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 13:48:53', 'prashantT', NULL),
	(99, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 11:15:37, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 08:18:53, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 16:45:37', 'prashantT', NULL),
	(100, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 11:21:06, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 11:15:37, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 16:51:07', 'prashantT', NULL),
	(101, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 18:08:15, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 11:21:07, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 23:38:15', 'prashantT', NULL),
	(102, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 18:18:43, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 18:08:15, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-03 23:48:44', 'prashantT', NULL),
	(103, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 18:47:46, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 18:18:44, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-04 00:17:47', 'prashantT', NULL),
	(104, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-04 11:06:58, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-03 18:47:47, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-04 16:36:59', 'prashantT', NULL),
	(105, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-04 12:41:01, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-04 11:06:59, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-04 18:11:01', 'prashantT', NULL),
	(106, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 329041111031504500, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717504948472, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-06-04 18:12:28', NULL, NULL),
	(107, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 10:59:07, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-04 12:41:01, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 16:29:07', 'prashantT', NULL),
	(108, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 11:20:37, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 10:59:07, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 16:50:37', 'prashantT', NULL),
	(109, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 11:32:22, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 11:20:37, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 17:02:23', 'prashantT', NULL),
	(110, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 11:41:51, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 11:32:23, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 17:11:52', 'prashantT', NULL),
	(111, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:06:19, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 11:41:52, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 17:36:20', 'prashantT', NULL),
	(112, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 383141118135759000, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717675652995, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-06-06 17:37:33', NULL, NULL),
	(113, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:23:38, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:06:20, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 17:53:39', 'prashantT', NULL),
	(114, NULL, 'card_details', NULL, '[strCardType : null, strCardSeqNumber : null, strTokenCard : 383491117171932900, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : 1717676754074, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', '[strCardType : null, strCardSeqNumber : null, strTokenCard : null, strServiceCode : null, strEmbossLine1 : null, strEmbossLine2 : null, strEncodeFirstName : null, strEncodeMiddleName : null, strEncodeLastName : null, strCardIssueDate : null, strCardIssueCode : null, strCardHolderSince : null, strExpiryDate : null, strNewExpiryDate : null, strCardStatus : null, strDailyPinRetryLimit : null, strDailyPinRetryCount : null, strConsecutivePinRetryLimit : null, strConsecutivePinRetryCount : null, strLastUpdatedDate : null, strCardIssuedUser : null, strLastUpdatedUser : null, strCardMailerIssueDate : null, strPinMailerIssueDate : null, strPinMailerIssueFlag : null, strCardMailerIssueFlag : null, strPinMailerUpdateFlag : null, strPinRetryFlag : null, strUserID : null, strClearCardNo : null, strIssuedToCustomer : null, strCustomerId : null, strParticipantID : null, strCardNo : null, strMemberNo : 0]', 'UPDATE', '2024-06-06 17:55:54', NULL, NULL),
	(115, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:39:45, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:23:39, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 18:09:45', 'prashantT', NULL),
	(116, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:49:47, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:39:45, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 18:19:47', 'prashantT', NULL),
	(117, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 13:44:39, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 12:49:47, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-06 19:14:40', 'prashantT', NULL),
	(118, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:12:00, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-06 13:44:40, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-12 23:42:01', 'prashantT', NULL),
	(119, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:18:18, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:12:01, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-12 23:48:18', 'prashantT', NULL),
	(120, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:26:47, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:18:18, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-12 23:56:48', 'prashantT', NULL),
	(121, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:37:18, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:26:48, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-13 00:07:19', 'prashantT', NULL),
	(122, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-13 07:52:13, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-12 18:37:19, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-13 13:22:13', 'prashantT', NULL),
	(123, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-13 11:18:06, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-13 07:52:13, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-13 16:48:07', 'prashantT', NULL),
	(124, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-14 06:11:27, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-13 11:18:07, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-14 11:41:28', 'prashantT', NULL),
	(125, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 09:05:49, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-14 06:11:28, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-15 14:35:49', 'prashantT', NULL),
	(126, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:01:58, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 09:05:50, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-15 15:31:58', 'prashantT', NULL),
	(127, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:07:47, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:01:58, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-15 15:37:47', 'prashantT', NULL),
	(128, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:17:08, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:07:47, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-15 15:47:09', 'prashantT', NULL),
	(129, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:26:44, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:17:09, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-15 15:56:44', 'prashantT', NULL),
	(130, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 11:20:51, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 10:26:44, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-15 16:50:52', 'prashantT', NULL),
	(131, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-16 12:56:42, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-15 11:20:52, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-16 18:26:43', 'prashantT', NULL),
	(132, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-18 06:11:37, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-16 12:56:43, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-18 11:41:38', 'prashantT', NULL),
	(133, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:10:02, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-18 06:11:38, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 11:40:03', 'prashantT', NULL),
	(134, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:24:21, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:10:03, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 11:54:22', 'prashantT', NULL),
	(135, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:45:55, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:24:22, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 12:15:56', 'prashantT', NULL),
	(136, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:59:03, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:45:56, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 12:29:03', 'prashantT', NULL),
	(137, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:17:46, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 06:59:03, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 12:47:47', 'prashantT', NULL),
	(138, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:23:34, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:17:47, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 12:53:34', 'prashantT', NULL),
	(139, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:36:47, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:23:34, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 13:06:47', 'prashantT', NULL),
	(140, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:39:03, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:36:47, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 13:09:03', 'prashantT', NULL),
	(141, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 08:25:53, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 07:39:03, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 13:55:54', 'prashantT', NULL),
	(142, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 11:20:34, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 08:25:54, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 16:50:34', 'prashantT', NULL),
	(143, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 11:59:53, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 11:20:34, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-20 17:29:53', 'prashantT', NULL),
	(144, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 11:11:25, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-20 11:59:53, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 16:41:26', 'prashantT', NULL),
	(145, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 11:12:48, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 11:11:26, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 16:42:49', 'prashantT', NULL),
	(146, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:01:09, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 11:12:49, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 17:31:10', 'prashantT', NULL),
	(147, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:08:50, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:01:10, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 17:38:50', 'prashantT', NULL),
	(148, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:12:02, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:08:50, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 17:42:03', 'prashantT', NULL),
	(149, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:13:10, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:12:03, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 17:43:10', 'prashantT', NULL),
	(150, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:17:34, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:13:10, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 17:47:34', 'prashantT', NULL),
	(151, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:24:11, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:17:34, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 17:54:12', 'prashantT', NULL),
	(152, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:30:50, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:24:12, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 18:00:51', 'prashantT', NULL),
	(153, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:31:22, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:30:51, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 18:01:23', 'prashantT', NULL),
	(154, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 13:38:50, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 12:31:23, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-21 19:08:51', 'prashantT', NULL),
	(155, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 11:12:41, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-21 13:38:51, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-24 16:42:41', 'prashantT', NULL),
	(156, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 11:19:30, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 11:12:41, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-24 16:49:31', 'prashantT', NULL),
	(157, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 11:37:12, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 11:19:31, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-24 17:07:13', 'prashantT', NULL),
	(158, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 13:49:22, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 11:37:13, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-24 19:19:23', 'prashantT', NULL),
	(159, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 13:54:16, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 13:49:23, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-24 19:24:17', 'prashantT', NULL),
	(160, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 14:22:05, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 13:54:17, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-24 19:52:05', 'prashantT', NULL),
	(161, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-25 17:56:00, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-24 14:22:05, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-25 23:26:01', 'prashantT', NULL),
	(162, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-25 17:57:52, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-25 17:56:01, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-25 23:27:52', 'prashantT', NULL),
	(163, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-25 18:11:31, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-25 17:57:52, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-25 23:41:32', 'prashantT', NULL),
	(164, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-26 06:08:15, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-25 18:11:32, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-26 11:38:15', 'prashantT', NULL),
	(165, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-27 06:35:12, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-26 06:08:15, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-27 12:05:13', 'prashantT', NULL),
	(166, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:38:30, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-27 06:35:13, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:08:31', 'prashantT', NULL),
	(167, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:40:05, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:38:31, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:10:05', 'prashantT', NULL),
	(168, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:40:26, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:40:05, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:10:26', 'prashantT', NULL),
	(169, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:42:09, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:40:26, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:12:09', 'prashantT', NULL),
	(170, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:45:18, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:42:09, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:15:19', 'prashantT', NULL),
	(171, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:51:09, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:45:19, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:21:09', 'prashantT', NULL),
	(172, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 10:24:04, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 09:51:09, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:54:05', 'prashantT', NULL),
	(173, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 10:27:42, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 10:24:05, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 15:57:42', 'prashantT', NULL),
	(174, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 10:53:01, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 10:27:42, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-28 16:23:02', 'prashantT', NULL),
	(175, 6, 'cfg_bin', '[strBin : 415371, strBinDesc : Montra Test Bin, strNetworkScheme : Visa, flag : Y]', NULL, NULL, 'DELETE', '2024-06-28 16:23:44', 'prashantT', NULL),
	(176, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 10:47:31, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-28 10:53:02, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 16:17:32', 'prashantT', NULL),
	(177, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 10:58:54, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 10:47:32, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 16:28:55', 'prashantT', NULL),
	(178, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 11:18:17, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 10:58:55, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 16:48:18', 'prashantT', NULL),
	(179, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 11:37:58, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 11:18:18, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 17:07:59', 'prashantT', NULL),
	(180, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 12:10:24, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 11:37:59, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 17:40:25', 'prashantT', NULL),
	(181, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 12:15:13, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 12:10:25, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 17:45:13', 'prashantT', NULL),
	(182, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 13:34:21, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 12:15:13, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 19:04:22', 'prashantT', NULL),
	(183, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 13:40:00, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 13:34:22, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-06-29 19:10:00', 'prashantT', NULL),
	(184, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 06:50:04, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-06-29 13:40:00, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-10 12:20:05', 'prashantT', NULL),
	(185, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 08:38:40, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 06:50:05, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-10 14:08:41', 'prashantT', NULL),
	(186, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 17:17:31, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 08:38:41, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-10 22:47:32', 'prashantT', NULL),
	(187, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 18:06:05, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 17:17:32, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-10 23:36:06', 'prashantT', NULL),
	(188, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 18:20:14, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 18:06:06, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-10 23:50:15', 'prashantT', NULL),
	(189, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 06:55:45, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-10 18:20:15, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 12:25:46', 'prashantT', NULL),
	(190, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 07:03:24, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 06:55:46, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 12:33:25', 'prashantT', NULL),
	(191, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 12:57:03, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 07:03:26, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 18:27:04', 'prashantT', NULL),
	(192, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 13:36:31, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 12:57:04, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 19:06:32', 'prashantT', NULL),
	(193, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 13:55:06, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 13:36:32, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 19:25:07', 'prashantT', NULL),
	(194, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 14:04:38, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 13:55:07, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 19:34:39', 'prashantT', NULL),
	(195, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 14:12:36, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 14:04:39, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 19:42:36', 'prashantT', NULL),
	(196, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 14:30:17, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 14:12:36, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-11 20:00:17', 'prashantT', NULL),
	(197, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-14 16:58:43, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-11 14:30:17, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-14 22:28:44', 'prashantT', NULL),
	(198, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-14 17:00:46, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-14 16:58:44, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-14 22:30:47', 'prashantT', NULL),
	(199, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-15 10:40:59, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-14 17:00:47, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-15 16:11:00', 'prashantT', NULL),
	(200, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 08:31:09, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-15 10:41:00, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 14:01:10', 'prashantT', NULL),
	(201, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 08:47:25, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 08:31:10, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 14:17:26', 'prashantT', NULL),
	(202, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 08:56:30, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 08:47:26, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 14:26:32', 'prashantT', NULL),
	(203, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 09:31:43, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 08:56:32, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 15:01:43', 'prashantT', NULL),
	(204, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:00:00, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 09:31:44, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 15:30:01', 'prashantT', NULL),
	(205, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:13:51, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:00:01, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 15:43:51', 'prashantT', NULL),
	(206, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:23:25, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:13:51, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 15:53:26', 'prashantT', NULL),
	(207, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:55:45, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:23:26, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 16:25:45', 'prashantT', NULL),
	(208, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 11:07:41, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 10:55:45, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 16:37:42', 'prashantT', NULL),
	(209, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 11:42:35, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 11:07:42, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 17:12:36', 'prashantT', NULL),
	(210, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 11:49:48, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 11:42:36, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-16 17:19:49', 'prashantT', NULL),
	(211, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-20 14:48:26, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-16 11:49:49, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-20 20:18:27', 'prashantT', NULL),
	(212, 6, 'user_details', NULL, '[strId : 0, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-20 15:09:42, forgotPasswordValidationFailedAttempt : 0, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', '[strId : 1, strUserID : 1, strParticipantID : 6, strUserName : prashantT, strMobileNo : 8291305922, strEmailID : prashant.tayde@montra.org, strPassword : PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=, strUserStatusID : 1, strCreatedBy : 26, isPasswordReset : Y, plainPassword : GcoldN<d, strSensitiveDate : 0, userRoleId : 0, approvalStatusId : 0, lastSuccessfulLogon : 2024-07-20 14:48:27, forgotPasswordValidationFailedAttempt : 0, lastPasswordChange : 1731612374000, strUserTypeId : 0, loginFailedAttemptsCount : 0, ienforcePasswordChange : 0]', 'UPDATE', '2024-07-20 20:39:43', 'prashantT', NULL);
/*!40000 ALTER TABLE `cms_audit` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.error_log
CREATE TABLE IF NOT EXISTS `error_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_type` char(50) DEFAULT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `source` blob,
  `form_name` blob,
  `url_name` blob,
  `target_site` blob,
  `message` blob,
  `stack_trace` blob,
  `created_by` char(20) DEFAULT NULL,
  `error_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `error_message_xml` blob,
  PRIMARY KEY (`id`),
  KEY `Index 2` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.error_log: ~0 rows (approximately)
/*!40000 ALTER TABLE `error_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `error_log` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.group_mapping
CREATE TABLE IF NOT EXISTS `group_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `participant_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  KEY `id` (`id`),
  KEY `part_index` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.group_mapping: ~10 rows (approximately)
/*!40000 ALTER TABLE `group_mapping` DISABLE KEYS */;
INSERT INTO `group_mapping` (`id`, `participant_id`, `name`, `description`, `created_by`, `created_date`) VALUES
	(1, 6, 'Default Maker', 'DefaultGroup', 0, '2020-07-19 17:58:34'),
	(2, 6, 'Default Checker', 'DefaultGroup', 0, '2024-01-27 12:30:07'),
	(31, 6, 'SUPER ADMIN', 'Super Admin Role', 1, '2024-01-28 22:58:51'),
	(32, 6, 'ADMIN', 'Admin Role', 1, '2024-01-28 22:59:16'),
	(34, 6, 'MAKER', 'Maker Role', 4, '2024-01-29 00:11:19'),
	(35, 6, 'CHECKER', 'Checker Role', 4, '2024-01-29 00:11:32'),
	(36, 6, 'USER', 'User Role', 4, '2024-01-29 00:11:45'),
	(37, 6, 'CUSTODIAN', 'Custodian User', 1, '2024-02-29 12:08:26'),
	(38, 6, 'QPSAdmin', 'Admin QPS', 1, '2024-03-30 15:54:37'),
	(39, 6, 'QPSMaker', 'QPS Maker', 1, '2024-03-30 15:54:54'),
	(40, 6, 'QPS Checker', 'Checker QPS', 1, '2024-03-30 15:55:20'),
	(41, 6, 'Prashant Test', 'For Testiing', 1, '2024-05-02 17:07:42');
/*!40000 ALTER TABLE `group_mapping` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.password_reset
CREATE TABLE IF NOT EXISTS `password_reset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `is_password_reset` char(1) DEFAULT 'N',
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username_index` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.password_reset: ~0 rows (approximately)
/*!40000 ALTER TABLE `password_reset` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.role_menu_mapping
CREATE TABLE IF NOT EXISTS `role_menu_mapping` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `menu_id` int(10) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_datetime` datetime DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.role_menu_mapping: ~196 rows (approximately)
/*!40000 ALTER TABLE `role_menu_mapping` DISABLE KEYS */;
INSERT INTO `role_menu_mapping` (`id`, `participant_id`, `role_id`, `menu_id`, `status`, `created_by`, `created_datetime`, `last_modified_by`, `last_modified_datetime`, `group_id`) VALUES
	(1, 6, 1, 5, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(2, 6, 1, 6, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(3, 6, 1, 7, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(4, 6, 1, 8, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(5, 6, 1, 9, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(6, 6, 1, 11, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(7, 6, 1, 13, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(8, 6, 1, 15, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(9, 6, 1, 16, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(10, 6, 1, 19, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(11, 6, 1, 20, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(12, 6, 1, 21, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(13, 6, 1, 22, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(14, 6, 1, 23, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(15, 6, 1, 24, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(16, 6, 1, 25, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(17, 6, 1, 26, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(18, 6, 1, 28, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(19, 6, 1, 29, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(20, 6, 1, 30, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(21, 6, 1, 31, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(22, 6, 1, 32, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(23, 6, 1, 33, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(24, 6, 1, 34, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(25, 6, 1, 35, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(26, 6, 1, 36, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(27, 6, 1, 37, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(28, 6, 1, 38, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1),
	(29, 6, 1, 44, b'1', '0', '2024-01-27 12:41:05', NULL, '2024-01-27 12:41:06', 1),
	(30, 6, 1, 45, b'1', '0', '2024-01-27 12:41:21', NULL, NULL, 1),
	(31, 6, 1, 46, b'1', '0', '2024-01-27 12:41:38', NULL, NULL, 1),
	(32, 6, 1, 47, b'1', '0', '2024-01-27 12:41:47', NULL, NULL, 1),
	(33, 6, 1, 48, b'1', '0', '2024-01-27 12:42:14', NULL, NULL, 1),
	(34, 6, 1, 49, b'1', '0', '2024-01-27 12:41:55', NULL, NULL, 1),
	(35, 6, 2, 5, b'1', '0', '2024-01-28 23:17:57', NULL, '2024-01-28 23:17:57', 2),
	(36, 6, 2, 6, b'1', '0', '2024-01-28 23:18:13', NULL, '2024-01-28 23:18:14', 2),
	(37, 6, 2, 7, b'1', '0', '2024-01-28 23:18:27', NULL, '2024-01-28 23:18:27', 2),
	(38, 6, 2, 8, b'1', '0', '2024-01-28 23:19:19', NULL, '2024-01-28 23:19:19', 2),
	(39, 6, 2, 9, b'1', '0', '2024-01-28 23:19:35', NULL, NULL, 2),
	(40, 6, 2, 11, b'1', NULL, '2024-01-28 23:19:42', NULL, NULL, 2),
	(41, 6, 2, 13, b'1', NULL, '2024-01-28 23:19:46', NULL, NULL, 2),
	(42, 6, 2, 15, b'1', NULL, '2024-01-28 23:19:56', NULL, NULL, 2),
	(43, 6, 2, 16, b'1', NULL, '2024-01-28 23:20:02', NULL, NULL, 2),
	(44, 6, 2, 19, b'1', NULL, '2024-01-28 23:20:08', NULL, NULL, 2),
	(45, 6, 2, 20, b'1', NULL, '2024-01-28 23:23:56', NULL, NULL, 2),
	(46, 6, 2, 21, b'1', NULL, '2024-01-28 23:24:02', NULL, NULL, 2),
	(47, 6, 2, 22, b'1', NULL, '2024-01-28 23:25:55', NULL, NULL, 2),
	(48, 6, 2, 23, b'1', NULL, '2024-01-28 23:26:01', NULL, NULL, 2),
	(49, 6, 2, 24, b'1', NULL, '2024-01-28 23:26:09', NULL, NULL, 2),
	(50, 6, 2, 25, b'1', NULL, '2024-01-28 23:26:14', NULL, NULL, 2),
	(51, 6, 2, 26, b'1', NULL, '2024-01-28 23:26:20', NULL, NULL, 2),
	(52, 6, 2, 28, b'1', NULL, '2024-01-28 23:26:26', NULL, NULL, 2),
	(53, 6, 2, 29, b'1', NULL, '2024-01-28 23:26:32', NULL, NULL, 2),
	(54, 6, 2, 30, b'1', NULL, '2024-01-28 23:26:39', NULL, NULL, 2),
	(55, 6, 2, 31, b'1', NULL, '2024-01-28 23:28:00', NULL, NULL, 2),
	(56, 6, 2, 32, b'1', NULL, '2024-01-28 23:28:07', NULL, NULL, 2),
	(57, 6, 2, 33, b'1', NULL, '2024-01-28 23:28:14', NULL, NULL, 2),
	(58, 6, 2, 34, b'1', NULL, '2024-01-28 23:28:21', NULL, NULL, 2),
	(59, 6, 2, 35, b'1', NULL, '2024-01-28 23:28:28', NULL, NULL, 2),
	(60, 6, 2, 36, b'1', NULL, '2024-01-28 23:28:36', NULL, NULL, 2),
	(61, 6, 2, 37, b'1', NULL, '2024-01-28 23:28:41', NULL, NULL, 2),
	(62, 6, 2, 38, b'1', NULL, '2024-01-28 23:28:47', NULL, NULL, 2),
	(63, 6, 2, 44, b'1', NULL, '2024-01-28 23:28:52', NULL, NULL, 2),
	(64, 6, 2, 45, b'1', NULL, '2024-01-28 23:28:56', NULL, NULL, 2),
	(65, 6, 2, 46, b'1', NULL, '2024-01-28 23:29:04', NULL, NULL, 2),
	(66, 6, 2, 47, b'1', NULL, '2024-01-28 23:29:10', NULL, NULL, 2),
	(67, 6, 2, 48, b'1', NULL, '2024-01-28 23:29:15', NULL, NULL, 2),
	(68, 6, 2, 49, b'1', NULL, '2024-01-28 23:29:19', NULL, NULL, 2),
	(150, 6, 33, 5, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(151, 6, 33, 6, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(152, 6, 33, 7, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(153, 6, 33, 8, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(154, 6, 33, 9, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(155, 6, 33, 11, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(156, 6, 33, 30, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(157, 6, 33, 13, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(158, 6, 33, 15, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(159, 6, 33, 33, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(160, 6, 33, 34, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(161, 6, 33, 36, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(162, 6, 33, 37, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(163, 6, 33, 39, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(164, 6, 33, 41, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(165, 6, 33, 16, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(166, 6, 33, 19, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(167, 6, 33, 28, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(168, 6, 33, 29, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(169, 6, 33, 32, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(170, 6, 33, 35, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(171, 6, 33, 38, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(172, 6, 33, 44, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(173, 6, 33, 45, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(174, 6, 33, 46, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(175, 6, 33, 47, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(176, 6, 33, 48, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(177, 6, 33, 49, b'1', '1', '2024-01-28 23:40:52', '2', '2024-01-28 23:42:21', 31),
	(185, 6, 34, 13, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(186, 6, 34, 15, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(187, 6, 34, 33, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(188, 6, 34, 34, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(189, 6, 34, 36, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(190, 6, 34, 37, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(191, 6, 34, 39, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(192, 6, 34, 41, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(193, 6, 34, 16, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(194, 6, 34, 19, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(195, 6, 34, 28, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(196, 6, 34, 29, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(197, 6, 34, 32, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(198, 6, 34, 35, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(199, 6, 34, 38, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(201, 6, 34, 45, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(202, 6, 34, 46, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(203, 6, 34, 47, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(204, 6, 34, 48, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(205, 6, 34, 49, b'1', '1', '2024-01-28 23:43:57', '2', '2024-01-28 23:44:28', 32),
	(208, 6, 38, 13, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(209, 6, 38, 15, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(210, 6, 38, 33, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(211, 6, 38, 34, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(212, 6, 38, 36, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(213, 6, 38, 37, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(214, 6, 38, 39, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(215, 6, 38, 41, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(216, 6, 38, 16, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(217, 6, 38, 19, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(218, 6, 38, 28, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(219, 6, 38, 29, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(220, 6, 38, 32, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(221, 6, 38, 35, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(222, 6, 38, 38, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(224, 6, 38, 45, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(225, 6, 38, 46, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(226, 6, 38, 47, b'1', '4', '2024-01-29 00:34:53', '3', '2024-01-29 00:35:24', 34),
	(228, 6, 39, 13, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(229, 6, 39, 15, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(230, 6, 39, 33, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(231, 6, 39, 34, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(232, 6, 39, 36, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(233, 6, 39, 37, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(234, 6, 39, 39, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(235, 6, 39, 41, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(236, 6, 39, 16, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(237, 6, 39, 19, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(238, 6, 39, 28, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(239, 6, 39, 29, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(240, 6, 39, 32, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(241, 6, 39, 35, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(242, 6, 39, 38, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(244, 6, 39, 45, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(245, 6, 39, 46, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(246, 6, 39, 47, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(247, 6, 39, 48, b'1', '4', '2024-01-29 00:37:56', '3', '2024-01-29 00:38:24', 35),
	(248, 6, 40, 13, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(249, 6, 40, 15, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(250, 6, 40, 33, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(251, 6, 40, 34, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(252, 6, 40, 36, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(253, 6, 40, 37, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(254, 6, 40, 39, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(255, 6, 40, 41, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(256, 6, 40, 16, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(257, 6, 40, 19, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(258, 6, 40, 28, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(259, 6, 40, 29, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(260, 6, 40, 32, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(261, 6, 40, 35, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(262, 6, 40, 38, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(263, 6, 40, 44, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(264, 6, 40, 45, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(265, 6, 40, 46, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(266, 6, 40, 47, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(267, 6, 40, 48, b'1', '4', '2024-01-29 00:40:24', '3', '2024-01-29 00:40:51', 36),
	(268, 6, 33, 50, b'1', '4', '2024-02-21 15:29:45', '3', '2024-02-21 15:29:46', 31),
	(269, 6, 33, 51, b'1', '4', '2024-02-21 15:30:06', '3', '2024-02-21 15:30:07', 31),
	(270, 6, 33, 52, b'1', '4', '2024-03-17 22:12:48', '3', '2024-03-17 22:12:50', 31),
	(289, 6, 43, 13, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(290, 6, 43, 15, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(291, 6, 43, 33, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(292, 6, 43, 34, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(293, 6, 43, 36, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(294, 6, 43, 37, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(295, 6, 43, 39, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(296, 6, 43, 41, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(297, 6, 43, 16, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(298, 6, 43, 19, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(299, 6, 43, 28, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(300, 6, 43, 29, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(301, 6, 43, 32, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(302, 6, 43, 35, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(303, 6, 43, 38, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(304, 6, 43, 45, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(305, 6, 43, 46, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(306, 6, 43, 47, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(307, 6, 43, 48, b'1', '1', '2024-03-31 19:37:20', '2', '2024-03-31 19:40:09', 38),
	(308, 6, 43, 5, b'1', '1', '2024-05-09 16:20:14', '0', '2024-05-09 16:20:14', 41),
	(309, 6, 43, 6, b'1', '1', '2024-05-09 16:20:14', '0', '2024-05-09 16:20:14', 41),
	(310, 6, 43, 7, b'1', '1', '2024-05-09 16:20:14', '0', '2024-05-09 16:20:14', 41),
	(311, 6, 43, 8, b'1', '1', '2024-05-09 16:20:14', '0', '2024-05-09 16:20:14', 41),
	(312, 6, 43, 9, b'1', '1', '2024-05-09 16:20:14', '0', '2024-05-09 16:20:14', 41),
	(313, 6, 43, 11, b'1', '1', '2024-05-09 16:20:14', '0', '2024-05-09 16:20:14', 41),
	(314, 6, 43, 30, b'1', '1', '2024-05-09 16:20:14', '0', '2024-05-09 16:20:14', 41);
/*!40000 ALTER TABLE `role_menu_mapping` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.role_menu_mapping_temp
CREATE TABLE IF NOT EXISTS `role_menu_mapping_temp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `role_temp_id` int(10) DEFAULT NULL,
  `role_menu_mapping_id` int(10) NOT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `menu_id` int(10) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_datetime` datetime DEFAULT NULL,
  `approval_status_id` int(10) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1279 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.role_menu_mapping_temp: ~303 rows (approximately)
/*!40000 ALTER TABLE `role_menu_mapping_temp` DISABLE KEYS */;
INSERT INTO `role_menu_mapping_temp` (`id`, `role_temp_id`, `role_menu_mapping_id`, `participant_id`, `role_id`, `menu_id`, `status`, `created_by`, `created_datetime`, `last_modified_by`, `last_modified_datetime`, `approval_status_id`, `group_id`) VALUES
	(1, NULL, 1, 6, 1, 5, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(2, NULL, 2, 6, 1, 6, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(3, NULL, 3, 6, 1, 7, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(4, NULL, 4, 6, 1, 8, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(5, NULL, 5, 6, 1, 9, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(6, NULL, 6, 6, 1, 11, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(7, NULL, 7, 6, 1, 13, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(8, NULL, 8, 6, 1, 15, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(9, NULL, 9, 6, 1, 16, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(10, NULL, 10, 6, 1, 19, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(11, NULL, 11, 6, 1, 20, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(12, NULL, 12, 6, 1, 21, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(13, NULL, 13, 6, 1, 22, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(14, NULL, 14, 6, 1, 23, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(15, NULL, 15, 6, 1, 24, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(16, NULL, 16, 6, 1, 25, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(17, NULL, 17, 6, 1, 26, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(18, NULL, 18, 6, 1, 28, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(19, NULL, 19, 6, 1, 29, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(20, NULL, 20, 6, 1, 30, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(21, NULL, 21, 6, 1, 31, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(22, NULL, 22, 6, 1, 32, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(23, NULL, 23, 6, 1, 33, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(24, NULL, 24, 6, 1, 34, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(25, NULL, 25, 6, 1, 35, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(26, NULL, 26, 6, 1, 36, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(27, NULL, 27, 6, 1, 37, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(28, NULL, 28, 6, 1, 38, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(29, NULL, 29, 6, 1, 44, b'1', '0', '2024-01-27 12:37:58', NULL, '2024-01-27 12:37:59', 1, 1),
	(30, NULL, 30, 6, 1, 45, b'1', '0', '2024-01-27 12:38:21', NULL, '2024-01-27 12:38:21', 1, 1),
	(31, NULL, 31, 6, 1, 46, b'1', '0', '2024-01-27 12:39:00', NULL, NULL, 1, 1),
	(32, NULL, 32, 6, 1, 47, b'1', '0', '2024-01-27 12:39:11', NULL, NULL, 1, 1),
	(33, NULL, 33, 6, 1, 48, b'1', '0', '2024-01-27 12:39:25', NULL, NULL, 1, 1),
	(34, NULL, 34, 6, 1, 49, b'1', '0', '2024-01-27 12:39:38', NULL, NULL, 1, 1),
	(35, 2, 35, 6, 2, 5, b'1', '1', '2024-01-28 14:38:55', NULL, NULL, 4, 2),
	(36, 2, 36, 6, 2, 6, b'1', '1', '2024-01-28 14:47:36', NULL, '2024-01-28 14:47:37', 4, 2),
	(37, 2, 37, 6, 2, 7, b'1', '1', '2024-01-28 14:55:49', NULL, '2024-01-28 14:55:50', 4, 2),
	(38, 2, 38, 6, 2, 8, b'1', '1', '2024-01-28 14:56:21', NULL, NULL, 4, 2),
	(39, 2, 39, 6, 2, 9, b'1', '1', '2024-01-28 14:56:37', NULL, NULL, 4, 2),
	(40, 2, 40, 6, 2, 11, b'1', '1', '2024-01-28 14:56:48', NULL, NULL, 4, 2),
	(41, 2, 41, 6, 2, 13, b'1', '1', '2024-01-28 14:57:03', NULL, NULL, 4, 2),
	(42, 2, 42, 6, 2, 15, b'1', '1', '2024-01-28 14:57:23', NULL, NULL, 4, 2),
	(43, 2, 43, 6, 2, 16, b'1', '1', '2024-01-28 14:57:38', NULL, NULL, 4, 2),
	(44, 2, 44, 6, 2, 19, b'1', '1', '2024-01-28 14:57:49', NULL, NULL, 4, 2),
	(45, 2, 45, 6, 2, 20, b'1', '1', '2024-01-28 14:58:01', NULL, NULL, 4, 2),
	(46, 2, 46, 6, 2, 21, b'1', '1', '2024-01-28 14:58:14', NULL, NULL, 4, 2),
	(47, 2, 47, 6, 2, 22, b'1', '1', '2024-01-28 14:58:26', NULL, NULL, 4, 2),
	(48, 2, 48, 6, 2, 23, b'1', '1', '2024-01-28 14:58:38', NULL, NULL, 4, 2),
	(49, 2, 49, 6, 2, 24, b'1', '1', '2024-01-28 14:58:49', NULL, NULL, 4, 2),
	(50, 2, 50, 6, 2, 25, b'1', '1', '2024-01-28 14:59:25', NULL, NULL, 4, 2),
	(51, 2, 51, 6, 2, 26, b'1', '1', '2024-01-28 14:59:38', NULL, NULL, 4, 2),
	(52, 2, 52, 6, 2, 28, b'1', '1', '2024-01-28 15:01:26', NULL, NULL, 4, 2),
	(53, 2, 53, 6, 2, 29, b'1', '1', '2024-01-28 15:04:56', NULL, NULL, 4, 2),
	(54, 2, 54, 6, 2, 30, b'1', '1', '2024-01-28 15:05:07', NULL, NULL, 4, 2),
	(55, 2, 55, 6, 2, 31, b'1', '1', '2024-01-28 15:05:21', NULL, NULL, 4, 2),
	(56, 2, 56, 6, 2, 32, b'1', '1', '2024-01-28 15:05:57', NULL, NULL, 4, 2),
	(57, 2, 57, 6, 2, 33, b'1', '1', '2024-01-28 15:06:11', NULL, NULL, 4, 2),
	(58, 2, 58, 6, 2, 34, b'1', '1', '2024-01-28 15:06:20', NULL, NULL, 4, 2),
	(59, 2, 59, 6, 2, 35, b'1', '1', '2024-01-28 15:06:38', NULL, NULL, 4, 2),
	(60, 2, 60, 6, 2, 36, b'1', '1', '2024-01-28 15:06:46', NULL, NULL, 4, 2),
	(61, 2, 61, 6, 2, 37, b'1', '1', '2024-01-28 15:07:18', NULL, NULL, 4, 2),
	(62, 2, 62, 6, 2, 38, b'1', '1', '2024-01-28 15:07:35', NULL, NULL, 4, 2),
	(63, 2, 63, 6, 2, 44, b'1', '1', '2024-01-28 15:08:04', NULL, NULL, 4, 2),
	(64, 2, 64, 6, 2, 45, b'1', '1', '2024-01-28 15:08:18', NULL, NULL, 4, 2),
	(65, 2, 65, 6, 2, 46, b'1', '1', '2024-01-28 15:09:06', NULL, NULL, 4, 2),
	(66, 2, 66, 6, 2, 47, b'1', '1', '2024-01-28 15:09:19', NULL, NULL, 4, 2),
	(67, 2, 67, 6, 2, 48, b'1', '1', '2024-01-28 15:09:32', NULL, NULL, 4, 2),
	(68, 2, 68, 6, 2, 49, b'1', '1', '2024-01-28 15:09:44', NULL, '2024-01-28 15:09:44', 4, 2),
	(934, 30, 75, 6, 30, 30, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(935, 30, 76, 6, 30, 13, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(936, 30, 77, 6, 30, 15, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(937, 30, 78, 6, 30, 33, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(938, 30, 79, 6, 30, 34, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(939, 30, 80, 6, 30, 36, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(940, 30, 81, 6, 30, 37, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(941, 30, 82, 6, 30, 39, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(942, 30, 83, 6, 30, 41, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(943, 30, 84, 6, 30, 16, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(944, 30, 85, 6, 30, 19, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(945, 30, 86, 6, 30, 28, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(946, 30, 87, 6, 30, 29, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(947, 30, 88, 6, 30, 32, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(948, 30, 89, 6, 30, 35, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(949, 30, 90, 6, 30, 38, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(950, 30, 91, 6, 30, 44, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(951, 30, 92, 6, 30, 45, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(952, 30, 93, 6, 30, 46, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(953, 30, 94, 6, 30, 47, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(954, 30, 95, 6, 30, 48, b'1', '1', '2024-01-28 23:00:14', NULL, NULL, 3, 31),
	(955, 31, 96, 6, 31, 5, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(956, 31, 97, 6, 31, 6, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(957, 31, 98, 6, 31, 7, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(958, 31, 99, 6, 31, 8, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(959, 31, 100, 6, 31, 9, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(960, 31, 101, 6, 31, 11, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(961, 31, 102, 6, 31, 30, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(962, 31, 103, 6, 31, 13, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(963, 31, 104, 6, 31, 15, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(964, 31, 105, 6, 31, 33, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(965, 31, 106, 6, 31, 34, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(966, 31, 107, 6, 31, 36, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(967, 31, 108, 6, 31, 37, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(968, 31, 109, 6, 31, 39, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(969, 31, 110, 6, 31, 41, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(970, 31, 111, 6, 31, 16, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(971, 31, 112, 6, 31, 19, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(972, 31, 113, 6, 31, 28, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(973, 31, 114, 6, 31, 29, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(974, 31, 115, 6, 31, 32, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(975, 31, 116, 6, 31, 35, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(976, 31, 117, 6, 31, 38, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(977, 31, 118, 6, 31, 44, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(978, 31, 119, 6, 31, 45, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(979, 31, 120, 6, 31, 46, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(980, 31, 121, 6, 31, 47, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(981, 31, 122, 6, 31, 48, b'1', '1', '2024-01-28 23:01:04', NULL, NULL, 3, 32),
	(982, 32, 123, 6, 32, 5, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(983, 32, 124, 6, 32, 6, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(984, 32, 125, 6, 32, 7, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(985, 32, 126, 6, 32, 8, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(986, 32, 127, 6, 32, 9, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(987, 32, 128, 6, 32, 11, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(988, 32, 129, 6, 32, 30, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(989, 32, 130, 6, 32, 13, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(990, 32, 131, 6, 32, 15, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(991, 32, 132, 6, 32, 33, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(992, 32, 133, 6, 32, 34, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(993, 32, 134, 6, 32, 36, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(994, 32, 135, 6, 32, 37, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(995, 32, 136, 6, 32, 39, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(996, 32, 137, 6, 32, 41, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(997, 32, 138, 6, 32, 16, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(998, 32, 139, 6, 32, 19, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(999, 32, 140, 6, 32, 28, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1000, 32, 141, 6, 32, 29, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1001, 32, 142, 6, 32, 32, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1002, 32, 143, 6, 32, 35, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1003, 32, 144, 6, 32, 38, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1004, 32, 145, 6, 32, 44, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1005, 32, 146, 6, 32, 45, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1006, 32, 147, 6, 32, 46, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1007, 32, 148, 6, 32, 47, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1008, 32, 149, 6, 32, 48, b'1', '1', '2024-01-28 23:13:17', NULL, NULL, 3, 31),
	(1009, 33, 150, 6, 33, 5, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1010, 33, 151, 6, 33, 6, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1011, 33, 152, 6, 33, 7, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1012, 33, 153, 6, 33, 8, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1013, 33, 154, 6, 33, 9, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1014, 33, 155, 6, 33, 11, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1015, 33, 156, 6, 33, 30, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1016, 33, 157, 6, 33, 13, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1017, 33, 158, 6, 33, 15, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1018, 33, 159, 6, 33, 33, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1019, 33, 160, 6, 33, 34, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1020, 33, 161, 6, 33, 36, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1021, 33, 162, 6, 33, 37, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1022, 33, 163, 6, 33, 39, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1023, 33, 164, 6, 33, 41, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1024, 33, 165, 6, 33, 16, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1025, 33, 166, 6, 33, 19, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1026, 33, 167, 6, 33, 28, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1027, 33, 168, 6, 33, 29, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1028, 33, 169, 6, 33, 32, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1029, 33, 170, 6, 33, 35, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1030, 33, 171, 6, 33, 38, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1031, 33, 172, 6, 33, 44, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1032, 33, 173, 6, 33, 45, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1033, 33, 174, 6, 33, 46, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1034, 33, 175, 6, 33, 47, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1035, 33, 176, 6, 33, 48, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1036, 33, 177, 6, 33, 49, b'1', '1', '2024-01-28 23:40:52', NULL, NULL, 3, 31),
	(1037, 34, 178, 6, 34, 5, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1038, 34, 179, 6, 34, 6, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1039, 34, 180, 6, 34, 7, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1040, 34, 181, 6, 34, 8, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1041, 34, 182, 6, 34, 9, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1042, 34, 183, 6, 34, 11, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1043, 34, 184, 6, 34, 30, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1044, 34, 185, 6, 34, 13, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1045, 34, 186, 6, 34, 15, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1046, 34, 187, 6, 34, 33, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1047, 34, 188, 6, 34, 34, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1048, 34, 189, 6, 34, 36, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1049, 34, 190, 6, 34, 37, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1050, 34, 191, 6, 34, 39, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1051, 34, 192, 6, 34, 41, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1052, 34, 193, 6, 34, 16, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1053, 34, 194, 6, 34, 19, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1054, 34, 195, 6, 34, 28, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1055, 34, 196, 6, 34, 29, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1056, 34, 197, 6, 34, 32, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1057, 34, 198, 6, 34, 35, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1058, 34, 199, 6, 34, 38, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1059, 34, 200, 6, 34, 44, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1060, 34, 201, 6, 34, 45, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1061, 34, 202, 6, 34, 46, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1062, 34, 203, 6, 34, 47, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1063, 34, 204, 6, 34, 48, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1064, 34, 205, 6, 34, 49, b'1', '1', '2024-01-28 23:43:57', NULL, NULL, 3, 32),
	(1128, 38, 206, 6, 38, 5, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1129, 38, 207, 6, 38, 8, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1130, 38, 208, 6, 38, 13, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1131, 38, 209, 6, 38, 15, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1132, 38, 210, 6, 38, 33, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1133, 38, 211, 6, 38, 34, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1134, 38, 212, 6, 38, 36, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1135, 38, 213, 6, 38, 37, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1136, 38, 214, 6, 38, 39, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1137, 38, 215, 6, 38, 41, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1138, 38, 216, 6, 38, 16, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1139, 38, 217, 6, 38, 19, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1140, 38, 218, 6, 38, 28, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1141, 38, 219, 6, 38, 29, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1142, 38, 220, 6, 38, 32, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1143, 38, 221, 6, 38, 35, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1144, 38, 222, 6, 38, 38, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1145, 38, 223, 6, 38, 44, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1146, 38, 224, 6, 38, 45, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1147, 38, 225, 6, 38, 46, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1148, 38, 226, 6, 38, 47, b'1', '4', '2024-01-29 00:34:53', NULL, NULL, 3, 34),
	(1149, 39, 227, 6, 39, 7, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1150, 39, 228, 6, 39, 13, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1151, 39, 229, 6, 39, 15, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1152, 39, 230, 6, 39, 33, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1153, 39, 231, 6, 39, 34, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1154, 39, 232, 6, 39, 36, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1155, 39, 233, 6, 39, 37, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1156, 39, 234, 6, 39, 39, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1157, 39, 235, 6, 39, 41, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1158, 39, 236, 6, 39, 16, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1159, 39, 237, 6, 39, 19, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1160, 39, 238, 6, 39, 28, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1161, 39, 239, 6, 39, 29, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1162, 39, 240, 6, 39, 32, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1163, 39, 241, 6, 39, 35, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1164, 39, 242, 6, 39, 38, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1165, 39, 243, 6, 39, 44, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1166, 39, 244, 6, 39, 45, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1167, 39, 245, 6, 39, 46, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1168, 39, 246, 6, 39, 47, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1169, 39, 247, 6, 39, 48, b'1', '4', '2024-01-29 00:37:56', NULL, NULL, 3, 35),
	(1170, 40, 248, 6, 40, 13, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1171, 40, 249, 6, 40, 15, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1172, 40, 250, 6, 40, 33, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1173, 40, 251, 6, 40, 34, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1174, 40, 252, 6, 40, 36, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1175, 40, 253, 6, 40, 37, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1176, 40, 254, 6, 40, 39, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1177, 40, 255, 6, 40, 41, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1178, 40, 256, 6, 40, 16, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1179, 40, 257, 6, 40, 19, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1180, 40, 258, 6, 40, 28, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1181, 40, 259, 6, 40, 29, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1182, 40, 260, 6, 40, 32, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1183, 40, 261, 6, 40, 35, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1184, 40, 262, 6, 40, 38, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1185, 40, 263, 6, 40, 44, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1186, 40, 264, 6, 40, 45, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1187, 40, 265, 6, 40, 46, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1188, 40, 266, 6, 40, 47, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1189, 40, 267, 6, 40, 48, b'1', '4', '2024-01-29 00:40:24', NULL, NULL, 3, 36),
	(1190, 33, 268, 6, 31, 50, b'1', '4', '2024-02-21 15:31:07', NULL, '2024-02-21 15:31:08', 3, 31),
	(1191, 33, 269, 6, 31, 51, b'1', '4', '2024-02-21 15:31:42', NULL, '2024-02-21 15:31:43', 2, 31),
	(1192, 42, 270, 6, 42, 13, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1193, 42, 271, 6, 42, 15, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1194, 42, 272, 6, 42, 33, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1195, 42, 273, 6, 42, 34, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1196, 42, 274, 6, 42, 36, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1197, 42, 275, 6, 42, 37, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1198, 42, 276, 6, 42, 39, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1199, 42, 277, 6, 42, 41, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1200, 42, 278, 6, 42, 16, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1201, 42, 279, 6, 42, 19, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1202, 42, 280, 6, 42, 28, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1203, 42, 281, 6, 42, 29, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1204, 42, 282, 6, 42, 32, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1205, 42, 283, 6, 42, 35, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1206, 42, 284, 6, 42, 38, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1207, 42, 285, 6, 42, 45, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1208, 42, 286, 6, 42, 46, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1209, 42, 287, 6, 42, 47, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1210, 42, 288, 6, 42, 48, b'1', '1', '2024-03-30 15:56:29', '2', '2024-03-30 15:57:48', 4, 38),
	(1211, 43, 289, 6, 43, 13, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1212, 43, 290, 6, 43, 15, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1213, 43, 291, 6, 43, 33, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1214, 43, 292, 6, 43, 34, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1215, 43, 293, 6, 43, 36, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1216, 43, 294, 6, 43, 37, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1217, 43, 295, 6, 43, 39, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1218, 43, 296, 6, 43, 41, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1219, 43, 297, 6, 43, 16, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1220, 43, 298, 6, 43, 19, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1221, 43, 299, 6, 43, 28, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1222, 43, 300, 6, 43, 29, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1223, 43, 301, 6, 43, 32, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1224, 43, 302, 6, 43, 35, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1225, 43, 303, 6, 43, 38, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1226, 43, 304, 6, 43, 45, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1227, 43, 305, 6, 43, 46, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1228, 43, 306, 6, 43, 47, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1229, 43, 307, 6, 43, 48, b'1', '1', '2024-03-31 19:37:20', NULL, NULL, 3, 38),
	(1272, 44, 308, 6, 43, 5, b'1', '1', '2024-05-09 16:05:50', '0', '2024-05-09 16:05:50', NULL, 41),
	(1273, 44, 309, 6, 43, 6, b'1', '1', '2024-05-09 16:05:50', '0', '2024-05-09 16:05:50', NULL, 41),
	(1274, 44, 310, 6, 43, 7, b'1', '1', '2024-05-09 16:05:50', '0', '2024-05-09 16:05:50', NULL, 41),
	(1275, 44, 311, 6, 43, 8, b'1', '1', '2024-05-09 16:05:50', '0', '2024-05-09 16:05:50', NULL, 41),
	(1276, 44, 312, 6, 43, 9, b'1', '1', '2024-05-09 16:05:50', '0', '2024-05-09 16:05:50', NULL, 41),
	(1277, 44, 313, 6, 43, 11, b'1', '1', '2024-05-09 16:05:50', '0', '2024-05-09 16:05:50', NULL, 41),
	(1278, 44, 314, 6, 43, 30, b'1', '1', '2024-05-09 16:05:50', '0', '2024-05-09 16:05:50', NULL, 41);
/*!40000 ALTER TABLE `role_menu_mapping_temp` ENABLE KEYS */;

-- Dumping structure for function traneco_cmsdb.SPLIT_STR
DELIMITER //
CREATE FUNCTION `SPLIT_STR`(X VARCHAR(255),
	delim VARCHAR(12),
	pos INT    
    ) RETURNS varchar(255) CHARSET latin1
    DETERMINISTIC
BEGIN
	RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(X, delim, pos),
       LENGTH(SUBSTRING_INDEX(X, delim, pos -1)) + 1),
       delim, '');
    END//
DELIMITER ;

-- Dumping structure for table traneco_cmsdb.user_details
CREATE TABLE IF NOT EXISTS `user_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `user_type_id` int(10) DEFAULT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `user_access_type_id` int(10) DEFAULT NULL,
  `user_full_name` char(50) DEFAULT NULL,
  `user_name` char(30) DEFAULT NULL,
  `mobile_number` char(20) DEFAULT NULL,
  `email_id` char(254) DEFAULT NULL,
  `secret_question_id` int(10) DEFAULT NULL,
  `secret_question_answer` char(200) DEFAULT NULL,
  `last_successful_logon` datetime DEFAULT NULL,
  `login_failed_attemps_count` int(10) DEFAULT '0',
  `forgot_password_validation_failed_attempt` int(10) DEFAULT '0',
  `enforce_password_change` bit(1) DEFAULT NULL,
  `last_password_change_datetime` datetime DEFAULT NULL,
  `user_password` char(255) DEFAULT NULL,
  `sensitive_pwd` char(255) DEFAULT NULL,
  `user_status_id` int(10) DEFAULT NULL,
  `sensitive_date` int(10) DEFAULT NULL,
  `login_flag` char(1) DEFAULT NULL,
  `user_status` bit(1) DEFAULT b'1',
  `created_by` char(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `LastModifiedDate` datetime DEFAULT NULL,
  `approved_by` char(20) DEFAULT NULL,
  `approved_date` datetime DEFAULT NULL,
  `approval_remarks` blob,
  `approval_status_id` int(10) DEFAULT NULL,
  `login_session_id` char(100) DEFAULT NULL,
  `plain_password` varchar(255) DEFAULT NULL,
  `isPasswordReset` char(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  KEY `userid_index` (`user_id`),
  KEY `partid_index` (`participant_id`),
  KEY `userpass_index` (`user_password`),
  KEY `username_index` (`user_name`),
  KEY `mobile_index` (`mobile_number`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_details: ~10 rows (approximately)
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`id`, `user_id`, `user_type_id`, `participant_id`, `user_access_type_id`, `user_full_name`, `user_name`, `mobile_number`, `email_id`, `secret_question_id`, `secret_question_answer`, `last_successful_logon`, `login_failed_attemps_count`, `forgot_password_validation_failed_attempt`, `enforce_password_change`, `last_password_change_datetime`, `user_password`, `sensitive_pwd`, `user_status_id`, `sensitive_date`, `login_flag`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `LastModifiedDate`, `approved_by`, `approved_date`, `approval_remarks`, `approval_status_id`, `login_session_id`, `plain_password`, `isPasswordReset`) VALUES
	(1, 1, 3, 6, 1, 'Default Maker', 'prashantT', '8291305922', 'prashant.tayde@montra.org', 1, 'Yellow', '2024-07-20 20:39:43', 0, 0, NULL, '2024-11-15 00:56:14', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '26', '2023-11-06 16:02:05', NULL, NULL, '2', '2023-11-06 16:02:05', NULL, NULL, NULL, 'GcoldN<d', 'Y'),
	(2, 2, 3, 6, 1, 'Default Checker', 'sachinK', '8928845502', 'prashant.tayde@montra.org', 1, 'Black', '2024-05-24 14:25:31', 0, 0, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '1', '2024-01-17 16:47:58', NULL, NULL, '1', '2024-01-17 16:47:58', NULL, NULL, NULL, 'xWAYsncUgjkAYs4xfnr7Ww==', 'Y'),
	(146, 3, 3, 6, 1, 'Nirdesh Malik', 'NirdeshM', '8928845502', 'ptayde@sequrotechnologies.com', 1, 'Blue', '2024-03-30 16:11:35', 0, 0, NULL, '2024-01-29 00:16:08', 'BHu5Gf6D1YczmFkiLU15meX0vamAl7gPE10sb+oEVug=', 'VCsCIT5q7mPC48wa8PFmGCXXfD1dy0PG/HZtsxjqMJc=', 1, 1, NULL, b'1', '1', '2024-01-29 00:04:41', NULL, NULL, '2', '2024-01-29 00:04:41', NULL, NULL, NULL, 'UXCJFsI/m+pEWoxo79NgPw==', 'Y'),
	(147, 4, 3, 6, 1, 'Hemanth Kumar', 'QPSAdmin', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Yellow', '2024-03-30 16:27:54', 0, 0, NULL, '2024-01-29 00:07:52', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '1', '2024-01-29 00:04:49', NULL, NULL, '2', '2024-01-29 00:04:49', NULL, NULL, NULL, '0YM73oBvQI9CcKuCjpPGzw==', 'Y'),
	(148, 5, 3, 6, 1, 'Kantesh Sakri', 'QPSMaker', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Blue', '2024-03-30 16:39:46', 0, 0, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '4', '2024-01-29 00:44:12', NULL, NULL, '3', '2024-01-29 00:44:12', NULL, NULL, NULL, 'mePCxgw2hycCRwdRqzEk5Q==', 'Y'),
	(149, 7, 3, 6, 1, 'Jyothis David', 'QPSChecker', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Blue', '2024-03-30 16:39:57', 0, 0, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '4', '2024-01-29 00:44:18', NULL, NULL, '3', '2024-01-29 00:44:18', NULL, NULL, NULL, 'vOS4OLYrwDG0ml5lnW8LDQ==', 'Y'),
	(150, 6, 3, 6, 1, 'Vishnu Vishwa', 'vishnuV', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Blue', '2024-03-30 16:31:11', 0, 0, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '4', '2024-01-29 00:44:22', NULL, NULL, '3', '2024-01-29 00:44:22', NULL, NULL, NULL, 'W3iJZoCt4GqDyI0mwUfO6w==', 'Y'),
	(151, 8, 3, 6, 1, 'Rohit Sharma', 'RohitS', '8928845502', 'ptayde@sequrotechnologies.com', 1, 'Black', '2024-03-30 16:30:33', 0, 0, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '1', '2024-02-03 11:10:45', NULL, NULL, '2', '2024-02-03 11:10:45', NULL, NULL, NULL, 'VJ8EcfHE55lJbzPOxDNAXg==', 'Y'),
	(157, 9, 3, 6, 1, 'Montra Custodian1', 'custodian1', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Black', '2024-03-31 18:43:21', 0, 0, NULL, '2024-03-01 14:39:59', '+V79zu0tyMM0SeU3c3I8bz2jjIN2tkTop4tL4QOau/Y=', 'v7Ptgmjm1FrfOUsTfTGen/mW/tu1OzYGq3yirviE0E0=', 1, 1, NULL, b'1', '1', '2024-03-01 13:20:17', NULL, NULL, '2', '2024-03-01 13:20:17', NULL, NULL, NULL, '6IzgSoH1PNc5t3YDSlm/4Q==:43A59AF49A2BBCB7E71924AF3CC030B3', 'Y'),
	(158, 10, 3, 6, 1, 'Montra Custodian2', 'custodian2', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Yellow', '2024-03-01 14:45:37', 0, 0, NULL, '2024-03-01 14:43:57', 'TGJkG5kEsDeyMNc8Mc1Y5ewtoDVRyfFhJYqwdcYI92Y=', '3NldUkClcG3oYfAcJWcR6v2U4AEAOOVdl3jWzv0CO7M=', 1, 1, NULL, b'1', '1', '2024-03-01 13:23:18', NULL, NULL, '2', '2024-03-01 13:23:18', NULL, NULL, NULL, '4OP2gLiemRHehgoHJBInNA==:01EFC9E04A3889E42CD76766C32A7DB1', 'Y');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_details_temp
CREATE TABLE IF NOT EXISTS `user_details_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_type_id` int(10) DEFAULT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `user_access_type_id` int(10) DEFAULT NULL,
  `user_full_name` char(50) DEFAULT NULL,
  `user_name` char(30) DEFAULT NULL,
  `mobile_number` char(20) DEFAULT NULL,
  `email_id` char(250) DEFAULT NULL,
  `secret_question_id` int(10) DEFAULT NULL,
  `secret_question_answer` char(200) DEFAULT NULL,
  `last_successful_logon` datetime DEFAULT NULL,
  `login_failed_attemps_count` int(10) DEFAULT NULL,
  `forgot_password_validation_failed_attempt` int(10) DEFAULT NULL,
  `enforce_password_change` bit(1) DEFAULT NULL,
  `last_password_change_datetime` datetime DEFAULT NULL,
  `user_password` char(255) DEFAULT NULL,
  `user_status_id` int(10) DEFAULT NULL,
  `sensitive_date` int(10) DEFAULT NULL,
  `login_flag` char(1) DEFAULT NULL,
  `user_status` bit(1) DEFAULT b'1',
  `created_by` char(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `LastModifiedDate` datetime DEFAULT NULL,
  `approved_by` char(20) DEFAULT NULL,
  `approved_date` datetime DEFAULT NULL,
  `approval_remarks` blob,
  `approval_status_id` int(10) DEFAULT NULL,
  `login_session_id` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usrid_index` (`user_id`),
  KEY `partid_index` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=357 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_details_temp: ~13 rows (approximately)
/*!40000 ALTER TABLE `user_details_temp` DISABLE KEYS */;
INSERT INTO `user_details_temp` (`id`, `user_id`, `user_type_id`, `participant_id`, `user_access_type_id`, `user_full_name`, `user_name`, `mobile_number`, `email_id`, `secret_question_id`, `secret_question_answer`, `last_successful_logon`, `login_failed_attemps_count`, `forgot_password_validation_failed_attempt`, `enforce_password_change`, `last_password_change_datetime`, `user_password`, `user_status_id`, `sensitive_date`, `login_flag`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `LastModifiedDate`, `approved_by`, `approved_date`, `approval_remarks`, `approval_status_id`, `login_session_id`) VALUES
	(1, 1, 3, 6, 1, 'Default Maker', 'montraMaker', '8928845502', 'prashant.tayde@montra.org', 1, 'Blue', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2023-11-27 11:14:37', NULL, NULL, '2', '2023-11-27 11:19:26', NULL, 1, NULL),
	(2, 2, 3, 6, 1, 'Default Checker', 'montraChecker', '8928845502', 'prashant.tayde@montra.org', 2, 'Yellow', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-01-17 16:56:47', NULL, NULL, '2', NULL, NULL, 1, NULL),
	(324, 3, 3, 6, 1, 'Nirdesh Malik', 'NirdeshM', '8928845502', 'ptayde@sequrotechnologies.com', 1, 'Blue', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-01-29 00:03:24', NULL, NULL, '2', '2024-01-29 00:04:41', NULL, 1, NULL),
	(325, 4, 3, 6, 1, 'Hemanth Kumar', 'HemanthK', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Yellow', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-01-29 00:03:53', NULL, NULL, '2', '2024-01-29 00:04:49', NULL, 1, NULL),
	(326, 5, 3, 6, 1, 'Kantesh Sakri', 'KanteshS', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Blue', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '4', '2024-01-29 00:42:40', NULL, NULL, '3', '2024-01-29 00:44:12', NULL, 1, NULL),
	(327, 6, 3, 6, 1, 'Vishnu Vishwa', 'vishnuV', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Blue', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '4', '2024-01-29 00:43:02', NULL, NULL, '3', '2024-01-29 00:44:22', NULL, 1, NULL),
	(328, 7, 3, 6, 1, 'Jyothis David', 'JyothisD', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Blue', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '4', '2024-01-29 00:43:28', NULL, NULL, '3', '2024-01-29 00:44:18', NULL, 1, NULL),
	(329, 8, 3, 6, 1, 'Rohit Sharma', 'RohitS', '8928845502', 'ptayde@sequrotechnologies.com', 1, 'Black', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-02-03 11:05:41', NULL, NULL, '2', '2024-02-03 11:10:45', NULL, 1, NULL),
	(336, 9, 3, 6, 1, 'Montra Custodian1', 'custodian1', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Black', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-03-01 13:20:00', NULL, NULL, '2', '2024-03-01 13:20:17', NULL, 1, NULL),
	(337, 10, 3, 6, 1, 'Montra Custodian2', 'custodian2', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Yellow', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-03-01 13:23:01', NULL, NULL, '2', '2024-03-01 13:23:18', NULL, 1, NULL),
	(341, 11, 3, 6, 1, 'QPS Admin', 'AdminQPS', '8291305922', 'ptayde@sequrotechnologies.com', 1, 'Blue', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-03-31 19:42:22', NULL, NULL, NULL, NULL, NULL, 3, NULL),
	(342, 12, 3, 6, 1, 'Akshata Pawar', 'AkshuTa', '8928845502', 'ptayde@sequrotechnologies.com', 1, 'Black', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '2', '2024-05-09 16:27:36', NULL, NULL, NULL, NULL, NULL, 3, NULL),
	(356, 13, 3, 6, 1, 'Aditi mistey', 'Adinino', '8928845502', 'ptayde@sequrotechnologies.com', 1, 'Blue', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2024-05-23 15:36:32', NULL, NULL, NULL, NULL, NULL, 3, NULL);
/*!40000 ALTER TABLE `user_details_temp` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_login_log
CREATE TABLE IF NOT EXISTS `user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant_id` int(10) unsigned NOT NULL,
  `user_id` char(20) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `logout_datetime` datetime DEFAULT NULL,
  `client_ip` char(30) DEFAULT NULL,
  `is_successful` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `partid_index` (`participant_id`),
  KEY `userid_index` (`user_id`),
  KEY `username_index` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=885 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_login_log: ~227 rows (approximately)
/*!40000 ALTER TABLE `user_login_log` DISABLE KEYS */;
INSERT INTO `user_login_log` (`id`, `participant_id`, `user_id`, `username`, `login_datetime`, `logout_datetime`, `client_ip`, `is_successful`) VALUES
	(654, 6, '1', 'prashantT', '2024-05-02 17:15:41', NULL, '', '1'),
	(655, 6, '1', 'prashantT', '2024-05-02 17:20:46', NULL, '', '1'),
	(656, 6, '1', 'prashantT', '2024-05-02 17:28:01', NULL, '', '1'),
	(657, 6, '1', 'prashantT', '2024-05-02 17:54:44', NULL, '', '1'),
	(658, 6, '1', 'prashantT', '2024-05-02 18:02:40', NULL, '', '0'),
	(659, 6, '1', 'prashantT', '2024-05-02 18:02:49', NULL, '', '1'),
	(660, 6, '1', 'prashantT', '2024-05-02 18:15:43', NULL, '', '1'),
	(661, 6, '1', 'prashantT', '2024-05-02 18:27:53', NULL, '', '1'),
	(662, 6, '1', 'prashantT', '2024-05-02 18:39:43', NULL, '', '1'),
	(663, 6, '1', 'prashantT', '2024-05-02 18:45:30', NULL, '', '1'),
	(664, 6, '1', 'prashantT', '2024-05-02 22:59:51', NULL, '', '1'),
	(665, 6, '1', 'prashantT', '2024-05-02 23:57:03', NULL, '', '1'),
	(666, 6, '1', 'prashantT', '2024-05-03 16:14:38', NULL, '', '1'),
	(667, 6, '1', 'prashantT', '2024-05-03 16:26:41', NULL, '', '1'),
	(668, 6, '1', 'prashantT', '2024-05-03 16:34:36', NULL, '', '1'),
	(669, 6, '1', 'prashantT', '2024-05-03 17:23:03', NULL, '', '1'),
	(670, 6, '1', 'prashantT', '2024-05-03 17:52:11', NULL, '', '1'),
	(671, 6, '1', 'prashantT', '2024-05-03 17:56:21', NULL, '', '1'),
	(672, 6, '1', 'prashantT', '2024-05-03 18:03:36', NULL, '', '1'),
	(673, 6, '1', 'prashantT', '2024-05-03 18:05:29', NULL, '', '1'),
	(674, 6, '1', 'prashantT', '2024-05-03 18:09:15', NULL, '', '1'),
	(675, 6, '1', 'prashantT', '2024-05-03 18:11:25', NULL, '', '1'),
	(676, 6, '1', 'prashantT', '2024-05-03 18:15:24', NULL, '', '1'),
	(677, 6, '1', 'prashantT', '2024-05-03 18:17:17', NULL, '', '1'),
	(678, 6, '1', 'prashantT', '2024-05-04 15:47:31', '2024-05-04 15:48:26', '', '1'),
	(679, 6, '2', 'sachinK', '2024-05-04 15:48:42', NULL, '', '1'),
	(680, 6, '2', 'sachinK', '2024-05-04 17:43:12', NULL, '', '1'),
	(681, 6, '2', 'sachinK', '2024-05-08 11:00:35', NULL, '', '1'),
	(682, 6, '2', 'sachinK', '2024-05-08 12:04:59', NULL, '', '1'),
	(683, 6, '2', 'sachinK', '2024-05-08 12:28:56', NULL, '', '1'),
	(684, 6, '2', 'sachinK', '2024-05-08 12:31:17', NULL, '', '1'),
	(685, 6, '2', 'sachinK', '2024-05-08 14:51:12', NULL, '', '1'),
	(686, 6, '2', 'sachinK', '2024-05-08 14:59:51', NULL, '', '1'),
	(687, 6, '2', 'sachinK', '2024-05-08 15:53:28', '2024-05-08 15:57:40', '', '1'),
	(688, 6, '1', 'prashantT', '2024-05-08 15:57:48', NULL, '', '1'),
	(689, 6, '1', 'prashantT', '2024-05-08 16:04:14', '2024-05-08 16:05:35', '', '1'),
	(690, 6, '2', 'sachinK', '2024-05-08 16:05:44', NULL, '', '1'),
	(691, 6, '2', 'sachinK', '2024-05-08 16:14:20', NULL, '', '1'),
	(692, 6, '2', 'sachinK', '2024-05-08 16:23:52', NULL, '', '1'),
	(693, 6, '1', 'prashantT', '2024-05-09 16:05:14', '2024-05-09 16:06:49', '', '1'),
	(694, 6, '2', 'sachinK', '2024-05-09 16:06:58', NULL, '', '1'),
	(695, 6, '2', 'sachinK', '2024-05-09 16:12:11', NULL, '', '1'),
	(696, 6, '2', 'sachinK', '2024-05-09 16:26:33', NULL, '', '1'),
	(697, 6, '1', 'prashantT', '2024-05-10 11:50:04', NULL, '', '1'),
	(698, 6, '1', 'prashantT', '2024-05-16 16:19:17', NULL, '', '1'),
	(699, 6, '1', 'prashantT', '2024-05-16 16:23:02', NULL, '', '1'),
	(700, 6, '1', 'prashantT', '2024-05-16 16:30:37', NULL, '', '1'),
	(701, 6, '1', 'prashantT', '2024-05-16 16:35:48', NULL, '', '1'),
	(702, 6, '1', 'prashantT', '2024-05-16 16:50:35', NULL, '', '1'),
	(703, 6, '1', 'prashantT', '2024-05-16 17:18:53', NULL, '', '1'),
	(704, 6, '1', 'prashantT', '2024-05-16 17:37:05', NULL, '', '1'),
	(705, 6, '1', 'prashantT', '2024-05-16 17:37:23', NULL, '', '1'),
	(706, 6, '1', 'prashantT', '2024-05-16 17:38:14', NULL, '', '1'),
	(707, 6, '1', 'prashantT', '2024-05-17 14:49:43', NULL, '', '1'),
	(708, 6, '1', 'prashantT', '2024-05-23 11:32:19', NULL, '', '1'),
	(709, 6, '1', 'prashantT', '2024-05-23 13:06:04', NULL, '', '1'),
	(710, 6, '1', 'prashantT', '2024-05-23 13:09:59', NULL, '', '1'),
	(711, 6, '1', 'prashantT', '2024-05-23 13:15:27', '2024-05-23 13:36:09', '', '1'),
	(712, 6, '1', 'prashantT', '2024-05-23 13:36:17', NULL, '', '1'),
	(713, 6, '1', 'prashantT', '2024-05-23 15:25:13', NULL, '', '1'),
	(714, 6, '1', 'prashantT', '2024-05-23 15:27:47', NULL, '', '1'),
	(715, 6, '1', 'prashantT', '2024-05-23 15:30:37', NULL, '', '0'),
	(716, 6, '1', 'prashantT', '2024-05-23 15:30:47', NULL, '', '1'),
	(717, 6, '1', 'prashantT', '2024-05-23 15:32:59', NULL, '', '1'),
	(718, 6, '1', 'prashantT', '2024-05-23 15:36:09', NULL, '', '1'),
	(719, 6, '2', 'sachinK', '2024-05-23 16:40:27', NULL, '', '1'),
	(720, 6, '2', 'sachinK', '2024-05-23 16:52:01', NULL, '', '1'),
	(721, 6, '2', 'sachinK', '2024-05-23 16:59:10', NULL, '', '1'),
	(722, 6, '2', 'sachinK', '2024-05-23 17:04:30', NULL, '', '1'),
	(723, 6, '2', 'sachinK', '2024-05-23 17:10:44', NULL, '', '1'),
	(724, 6, '2', 'sachinK', '2024-05-23 17:11:01', NULL, '', '1'),
	(725, 6, '2', 'sachinK', '2024-05-23 17:11:59', NULL, '', '1'),
	(726, 6, '2', 'sachinK', '2024-05-24 10:52:44', NULL, '', '1'),
	(727, 6, '2', 'sachinK', '2024-05-24 14:25:30', NULL, '', '1'),
	(728, 6, '1', 'prashantT', '2024-05-29 09:55:51', NULL, '', '1'),
	(729, 6, '1', 'prashantT', '2024-05-29 13:35:29', NULL, '', '1'),
	(730, 6, '1', 'prashantT', '2024-05-29 18:29:05', NULL, '', '1'),
	(731, 6, '1', 'prashantT', '2024-05-29 18:39:26', NULL, '', '1'),
	(732, 6, '1', 'prashantT', '2024-05-29 19:15:07', NULL, '', '1'),
	(733, 6, '1', 'prashantT', '2024-05-30 13:29:24', '2024-05-30 13:44:01', '', '1'),
	(734, 6, '1', 'prashantT', '2024-05-30 13:44:09', '2024-05-30 16:04:48', '', '1'),
	(735, 6, '1', 'prashantT', '2024-05-30 16:07:19', NULL, '', '1'),
	(736, 6, '1', 'prashantT', '2024-05-30 19:24:13', NULL, '', '1'),
	(737, 6, '1', 'prashantT', '2024-05-30 23:45:16', '2024-05-31 00:07:00', '', '1'),
	(738, 6, '1', 'prashantT', '2024-05-31 00:07:09', NULL, '', '1'),
	(739, 6, '1', 'prashantT', '2024-05-31 00:27:29', NULL, '', '1'),
	(740, 6, '1', 'prashantT', '2024-05-31 00:33:16', NULL, '', '1'),
	(741, 6, '1', 'prashantT', '2024-05-31 12:17:35', NULL, '', '1'),
	(742, 6, '1', 'prashantT', '2024-05-31 16:47:48', '2024-05-31 16:55:59', '', '1'),
	(743, 6, '1', 'prashantT', '2024-05-31 16:56:10', NULL, '', '1'),
	(744, 6, '1', 'prashantT', '2024-05-31 17:07:37', '2024-05-31 17:22:27', '', '1'),
	(745, 6, '1', 'prashantT', '2024-05-31 17:22:38', NULL, '', '1'),
	(746, 6, '1', 'prashantT', '2024-05-31 18:59:24', NULL, '', '1'),
	(747, 6, '1', 'prashantT', '2024-05-31 19:17:55', NULL, '', '1'),
	(748, 6, '1', 'prashantT', '2024-05-31 19:26:23', NULL, '', '1'),
	(749, 6, '1', 'prashantT', '2024-05-31 19:39:03', '2024-05-31 20:04:44', '', '1'),
	(750, 6, '1', 'prashantT', '2024-05-31 20:06:32', '2024-05-31 20:25:20', '', '1'),
	(751, 6, '1', 'prashantT', '2024-05-31 20:25:59', NULL, '', '1'),
	(752, 6, '1', 'prashantT', '2024-05-31 23:45:16', NULL, '', '1'),
	(753, 6, '1', 'prashantT', '2024-06-01 00:00:58', NULL, '', '0'),
	(754, 6, '1', 'prashantT', '2024-06-01 00:01:06', NULL, '', '1'),
	(755, 6, '1', 'prashantT', '2024-06-01 00:17:35', '2024-06-01 00:39:41', '', '1'),
	(756, 6, '1', 'prashantT', '2024-06-01 00:41:51', NULL, '', '1'),
	(757, 6, '1', 'prashantT', '2024-06-01 13:36:16', NULL, '', '1'),
	(758, 6, '1', 'prashantT', '2024-06-01 14:10:04', '2024-06-01 14:55:51', '', '1'),
	(759, 6, '1', 'prashantT', '2024-06-01 14:56:22', NULL, '', '1'),
	(760, 6, '1', 'prashantT', '2024-06-01 16:37:11', NULL, '', '1'),
	(761, 6, '1', 'prashantT', '2024-06-01 16:47:04', NULL, '', '1'),
	(762, 6, '1', 'prashantT', '2024-06-01 19:17:50', '2024-06-01 19:32:34', '', '1'),
	(763, 6, '1', 'prashantT', '2024-06-01 19:32:51', NULL, '', '1'),
	(764, 6, '1', 'prashantT', '2024-06-02 13:42:19', NULL, '', '1'),
	(765, 6, '1', 'prashantT', '2024-06-03 11:56:02', NULL, '', '1'),
	(766, 6, '1', 'prashantT', '2024-06-03 12:00:23', NULL, '', '1'),
	(767, 6, '1', 'prashantT', '2024-06-03 12:02:48', NULL, '', '0'),
	(768, 6, '1', 'prashantT', '2024-06-03 12:02:57', NULL, '', '1'),
	(769, 6, '1', 'prashantT', '2024-06-03 12:06:24', NULL, '', '1'),
	(770, 6, '1', 'prashantT', '2024-06-03 12:50:45', NULL, '', '1'),
	(771, 6, '1', 'prashantT', '2024-06-03 12:58:20', NULL, '', '1'),
	(772, 6, '1', 'prashantT', '2024-06-03 13:23:50', NULL, '', '1'),
	(773, 6, '1', 'prashantT', '2024-06-03 13:42:12', NULL, '', '1'),
	(774, 6, '1', 'prashantT', '2024-06-03 13:48:53', NULL, '', '1'),
	(775, 6, '1', 'prashantT', '2024-06-03 16:45:37', '2024-06-03 16:50:59', '', '1'),
	(776, 6, '1', 'prashantT', '2024-06-03 16:51:07', NULL, '', '1'),
	(777, 6, '1', 'prashantT', '2024-06-03 23:38:15', NULL, '', '1'),
	(778, 6, '1', 'prashantT', '2024-06-03 23:48:44', NULL, '', '1'),
	(779, 6, '1', 'prashantT', '2024-06-04 00:17:47', NULL, '', '1'),
	(780, 6, '1', 'prashantT', '2024-06-04 16:36:59', NULL, '', '1'),
	(781, 6, '1', 'prashantT', '2024-06-04 18:11:01', NULL, '', '1'),
	(782, 6, '1', 'prashantT', '2024-06-06 16:29:07', NULL, '', '1'),
	(783, 6, '1', 'prashantT', '2024-06-06 16:50:37', NULL, '', '1'),
	(784, 6, '1', 'prashantT', '2024-06-06 17:02:23', '2024-06-06 17:11:44', '', '1'),
	(785, 6, '1', 'prashantT', '2024-06-06 17:11:52', NULL, '', '1'),
	(786, 6, '1', 'prashantT', '2024-06-06 17:36:20', NULL, '', '1'),
	(787, 6, '1', 'prashantT', '2024-06-06 17:53:38', '2024-06-06 18:09:38', '', '1'),
	(788, 6, '1', 'prashantT', '2024-06-06 18:09:45', '2024-06-06 18:19:40', '', '1'),
	(789, 6, '1', 'prashantT', '2024-06-06 18:19:47', NULL, '', '1'),
	(790, 6, '1', 'prashantT', '2024-06-06 19:14:40', NULL, '', '1'),
	(791, 6, '1', 'prashantT', '2024-06-12 23:42:01', '2024-06-12 23:48:10', '', '1'),
	(792, 6, '1', 'prashantT', '2024-06-12 23:48:18', NULL, '', '1'),
	(793, 6, '1', 'prashantT', '2024-06-12 23:56:48', NULL, '', '1'),
	(794, 6, '1', 'prashantT', '2024-06-13 00:07:19', NULL, '', '1'),
	(795, 6, '1', 'prashantT', '2024-06-13 13:22:13', NULL, '', '1'),
	(796, 6, '1', 'prashantT', '2024-06-13 16:48:07', NULL, '', '1'),
	(797, 6, '1', 'prashantT', '2024-06-14 11:41:28', NULL, '', '1'),
	(798, 6, '1', 'prashantT', '2024-06-15 14:35:49', NULL, '', '1'),
	(799, 6, '1', 'prashantT', '2024-06-15 15:31:58', NULL, '', '1'),
	(800, 6, '1', 'prashantT', '2024-06-15 15:37:47', NULL, '', '1'),
	(801, 6, '1', 'prashantT', '2024-06-15 15:47:09', NULL, '', '1'),
	(802, 6, '1', 'prashantT', '2024-06-15 15:56:44', NULL, '', '1'),
	(803, 6, '1', 'prashantT', '2024-06-15 16:50:52', NULL, '', '1'),
	(804, 6, '1', 'prashantT', '2024-06-16 18:26:43', NULL, '', '1'),
	(805, 6, '1', 'prashantT', '2024-06-18 11:41:37', NULL, '', '1'),
	(806, 6, '1', 'prashantT', '2024-06-20 11:40:03', NULL, '', '1'),
	(807, 6, '1', 'prashantT', '2024-06-20 11:54:22', NULL, '', '1'),
	(808, 6, '1', 'prashantT', '2024-06-20 12:15:56', NULL, '', '1'),
	(809, 6, '1', 'prashantT', '2024-06-20 12:29:03', NULL, '', '1'),
	(810, 6, '1', 'prashantT', '2024-06-20 12:47:47', NULL, '', '1'),
	(811, 6, '1', 'prashantT', '2024-06-20 12:53:34', NULL, '', '1'),
	(812, 6, '1', 'prashantT', '2024-06-20 13:06:47', NULL, '', '1'),
	(813, 6, '1', 'prashantT', '2024-06-20 13:09:03', NULL, '', '1'),
	(814, 6, '1', 'prashantT', '2024-06-20 13:55:54', NULL, '', '1'),
	(815, 6, '1', 'prashantT', '2024-06-20 16:50:34', NULL, '', '1'),
	(816, 6, '1', 'prashantT', '2024-06-20 17:29:53', NULL, '', '1'),
	(817, 6, '1', 'prashantT', '2024-06-21 16:41:26', NULL, '', '1'),
	(818, 6, '1', 'prashantT', '2024-06-21 16:42:49', NULL, '', '1'),
	(819, 6, '1', 'prashantT', '2024-06-21 17:31:09', NULL, '', '1'),
	(820, 6, '1', 'prashantT', '2024-06-21 17:38:50', NULL, '', '1'),
	(821, 6, '1', 'prashantT', '2024-06-21 17:42:03', NULL, '', '1'),
	(822, 6, '1', 'prashantT', '2024-06-21 17:43:10', NULL, '', '1'),
	(823, 6, '1', 'prashantT', '2024-06-21 17:47:34', NULL, '', '1'),
	(824, 6, '1', 'prashantT', '2024-06-21 17:54:12', NULL, '', '1'),
	(825, 6, '1', 'prashantT', '2024-06-21 18:00:51', NULL, '', '1'),
	(826, 6, '1', 'prashantT', '2024-06-21 18:01:23', NULL, '', '1'),
	(827, 6, '1', 'prashantT', '2024-06-21 19:08:50', NULL, '', '1'),
	(828, 6, '1', 'prashantT', '2024-06-24 16:42:41', NULL, '', '1'),
	(829, 6, '1', 'prashantT', '2024-06-24 16:49:30', NULL, '', '1'),
	(830, 6, '1', 'prashantT', '2024-06-24 17:07:12', NULL, '', '1'),
	(831, 6, '1', 'prashantT', '2024-06-24 19:19:23', NULL, '', '1'),
	(832, 6, '1', 'prashantT', '2024-06-24 19:24:16', NULL, '', '1'),
	(833, 6, '1', 'prashantT', '2024-06-24 19:52:05', NULL, '', '1'),
	(834, 6, '1', 'prashantT', '2024-06-25 23:26:01', NULL, '', '1'),
	(835, 6, '1', 'prashantT', '2024-06-25 23:27:52', NULL, '', '1'),
	(836, 6, '1', 'prashantT', '2024-06-25 23:41:32', NULL, '', '1'),
	(837, 6, '1', 'prashantT', '2024-06-26 11:38:15', NULL, '', '1'),
	(838, 6, '1', 'prashantT', '2024-06-27 12:05:13', NULL, '', '1'),
	(839, 6, '1', 'prashantT', '2024-06-28 15:08:30', NULL, '', '1'),
	(840, 6, '1', 'prashantT', '2024-06-28 15:10:05', NULL, '', '1'),
	(841, 6, '1', 'prashantT', '2024-06-28 15:10:26', NULL, '', '1'),
	(842, 6, '1', 'prashantT', '2024-06-28 15:12:09', NULL, '', '1'),
	(843, 6, '1', 'prashantT', '2024-06-28 15:15:18', '2024-06-28 15:20:59', '', '1'),
	(844, 6, '1', 'prashantT', '2024-06-28 15:21:09', NULL, '', '1'),
	(845, 6, '1', 'prashantT', '2024-06-28 15:54:05', NULL, '', '1'),
	(846, 6, '1', 'prashantT', '2024-06-28 15:57:42', NULL, '', '1'),
	(847, 6, '1', 'prashantT', '2024-06-28 16:23:02', NULL, '', '1'),
	(848, 6, '1', 'prashantT', '2024-06-29 16:17:32', NULL, '', '1'),
	(849, 6, '1', 'prashantT', '2024-06-29 16:28:54', NULL, '', '1'),
	(850, 6, '1', 'prashantT', '2024-06-29 16:48:17', NULL, '', '1'),
	(851, 6, '1', 'prashantT', '2024-06-29 17:07:58', NULL, '', '1'),
	(852, 6, '1', 'prashantT', '2024-06-29 17:40:25', NULL, '', '1'),
	(853, 6, '1', 'prashantT', '2024-06-29 17:45:13', NULL, '', '1'),
	(854, 6, '1', 'prashantT', '2024-06-29 19:04:21', NULL, '', '1'),
	(855, 6, '1', 'prashantT', '2024-06-29 19:10:00', NULL, '', '1'),
	(856, 6, '1', 'prashantT', '2024-07-10 12:20:04', NULL, '', '1'),
	(857, 6, '1', 'prashantT', '2024-07-10 14:08:41', NULL, '', '1'),
	(858, 6, '1', 'prashantT', '2024-07-10 22:47:32', NULL, '', '1'),
	(859, 6, '1', 'prashantT', '2024-07-10 23:36:05', NULL, '', '1'),
	(860, 6, '1', 'prashantT', '2024-07-10 23:50:15', NULL, '', '1'),
	(861, 6, '1', 'prashantT', '2024-07-11 12:25:46', NULL, '', '1'),
	(862, 6, '1', 'prashantT', '2024-07-11 12:33:25', NULL, '', '1'),
	(863, 6, '1', 'prashantT', '2024-07-11 18:27:02', NULL, '', '1'),
	(864, 6, '1', 'prashantT', '2024-07-11 19:06:32', '2024-07-11 19:24:49', '', '1'),
	(865, 6, '1', 'prashantT', '2024-07-11 19:25:07', NULL, '', '1'),
	(866, 6, '1', 'prashantT', '2024-07-11 19:34:39', NULL, '', '1'),
	(867, 6, '1', 'prashantT', '2024-07-11 19:42:36', NULL, '', '1'),
	(868, 6, '1', 'prashantT', '2024-07-11 20:00:17', NULL, '', '1'),
	(869, 6, '1', 'prashantT', '2024-07-14 22:28:44', '2024-07-14 22:30:40', '', '1'),
	(870, 6, '1', 'prashantT', '2024-07-14 22:30:47', NULL, '', '1'),
	(871, 6, '1', 'prashantT', '2024-07-15 16:11:00', NULL, '', '1'),
	(872, 6, '1', 'prashantT', '2024-07-16 14:01:09', '2024-07-16 14:17:18', '', '1'),
	(873, 6, '1', 'prashantT', '2024-07-16 14:17:26', NULL, '', '1'),
	(874, 6, '1', 'prashantT', '2024-07-16 14:26:31', NULL, '', '1'),
	(875, 6, '1', 'prashantT', '2024-07-16 15:01:43', NULL, '', '1'),
	(876, 6, '1', 'prashantT', '2024-07-16 15:30:01', '2024-07-16 15:43:43', '', '1'),
	(877, 6, '1', 'prashantT', '2024-07-16 15:43:51', NULL, '', '1'),
	(878, 6, '1', 'prashantT', '2024-07-16 15:53:25', '2024-07-16 16:25:37', '', '1'),
	(879, 6, '1', 'prashantT', '2024-07-16 16:25:45', '2024-07-16 16:37:29', '', '1'),
	(880, 6, '1', 'prashantT', '2024-07-16 16:37:42', NULL, '', '1'),
	(881, 6, '1', 'prashantT', '2024-07-16 17:12:36', '2024-07-16 17:19:41', '', '1'),
	(882, 6, '1', 'prashantT', '2024-07-16 17:19:49', NULL, '', '1'),
	(883, 6, '1', 'prashantT', '2024-07-20 20:18:26', NULL, '', '1'),
	(884, 6, '1', 'prashantT', '2024-07-20 20:39:43', NULL, '', '1');
/*!40000 ALTER TABLE `user_login_log` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_passwords_history
CREATE TABLE IF NOT EXISTS `user_passwords_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `old_password` char(250) DEFAULT NULL,
  `new_password` char(250) DEFAULT NULL,
  `last_password_changed_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid_index` (`user_id`),
  KEY `username_index` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_passwords_history: ~4 rows (approximately)
/*!40000 ALTER TABLE `user_passwords_history` DISABLE KEYS */;
INSERT INTO `user_passwords_history` (`id`, `user_id`, `user_name`, `email`, `old_password`, `new_password`, `last_password_changed_date`) VALUES
	(3, 4, 'HemanthK', 'ptayde@sequrotechnologies.com', '1Az9+tZGLJyvM1yu63TwgnUxgWMq4QVEwMRfzyNqqks=', 'HD0kIHxTjHJDmdLLW6UrddO+whpzBC2yTveh3zu3ssM=', '2024-01-29 00:07:52'),
	(4, 3, 'NirdeshM', 'ptayde@sequrotechnologies.com', 'VCsCIT5q7mPC48wa8PFmGCXXfD1dy0PG/HZtsxjqMJc=', 'BHu5Gf6D1YczmFkiLU15meX0vamAl7gPE10sb+oEVug=', '2024-01-29 00:16:08'),
	(5, 9, 'custodian1', 'ptayde@sequrotechnologies.com', 'v7Ptgmjm1FrfOUsTfTGen/mW/tu1OzYGq3yirviE0E0=', '+V79zu0tyMM0SeU3c3I8bz2jjIN2tkTop4tL4QOau/Y=', '2024-03-01 14:39:59'),
	(6, 10, 'custodian2', 'ptayde@sequrotechnologies.com', '3NldUkClcG3oYfAcJWcR6v2U4AEAOOVdl3jWzv0CO7M=', 'TGJkG5kEsDeyMNc8Mc1Y5ewtoDVRyfFhJYqwdcYI92Y=', '2024-03-01 14:43:57');
/*!40000 ALTER TABLE `user_passwords_history` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_role_mapping
CREATE TABLE IF NOT EXISTS `user_role_mapping` (
  `id` int(10) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `user_status` bit(1) NOT NULL,
  `created_by` char(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usr_index` (`user_id`),
  KEY `role_index` (`role_id`),
  KEY `part_index` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_role_mapping: ~9 rows (approximately)
/*!40000 ALTER TABLE `user_role_mapping` DISABLE KEYS */;
INSERT INTO `user_role_mapping` (`id`, `user_id`, `role_id`, `participant_id`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `group_id`) VALUES
	(1, 1, 1, 6, b'0', '2', '2023-12-12 15:27:40', NULL, NULL, 1),
	(2, 2, 2, 6, b'0', '2', '2024-01-28 23:11:21', NULL, NULL, 2),
	(292, 3, 33, 6, b'1', '1', '2024-01-29 00:04:41', NULL, NULL, 31),
	(293, 4, 34, 6, b'1', '1', '2024-01-29 00:04:49', NULL, NULL, 32),
	(294, 5, 39, 6, b'1', '4', '2024-01-29 00:44:12', NULL, NULL, 35),
	(295, 6, 38, 6, b'1', '4', '2024-01-29 00:44:22', NULL, NULL, 34),
	(296, 7, 38, 6, b'1', '4', '2024-01-29 00:44:18', NULL, NULL, 34),
	(297, 8, 40, 6, b'1', '1', '2024-02-03 11:10:45', NULL, NULL, 36),
	(304, 9, 41, 6, b'1', '1', '2024-03-01 13:20:17', NULL, NULL, 37),
	(305, 10, 41, 6, b'1', '1', '2024-03-01 13:23:18', NULL, NULL, 37);
/*!40000 ALTER TABLE `user_role_mapping` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_role_mapping_temp
CREATE TABLE IF NOT EXISTS `user_role_mapping_temp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_role_id` int(10) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `user_status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `approval_status_id` int(10) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usrrole_index` (`user_role_id`),
  KEY `userid_index` (`user_id`),
  KEY `roleid_index` (`role_id`),
  KEY `partid_index` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_role_mapping_temp: ~13 rows (approximately)
/*!40000 ALTER TABLE `user_role_mapping_temp` DISABLE KEYS */;
INSERT INTO `user_role_mapping_temp` (`id`, `user_role_id`, `user_id`, `role_id`, `participant_id`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `approval_status_id`, `group_id`) VALUES
	(1, 1, 1, 1, 6, b'1', '1', '2023-11-27 11:14:37', NULL, NULL, 1, 1),
	(2, 1, 2, 2, 6, b'1', '1', '2024-01-17 16:57:38', NULL, '2024-01-17 16:57:38', 1, 2),
	(292, 324, 3, 33, 6, b'1', '1', '2024-01-29 00:03:24', NULL, NULL, 1, 31),
	(293, 325, 4, 34, 6, b'1', '1', '2024-01-29 00:03:53', NULL, NULL, 1, 32),
	(294, 326, 5, 39, 6, b'1', '4', '2024-01-29 00:42:40', NULL, NULL, 1, 35),
	(295, 327, 6, 38, 6, b'1', '4', '2024-01-29 00:43:02', NULL, NULL, 1, 34),
	(296, 328, 7, 38, 6, b'1', '4', '2024-01-29 00:43:28', NULL, NULL, 1, 34),
	(297, 329, 8, 40, 6, b'1', '1', '2024-02-03 11:05:41', NULL, NULL, 1, 36),
	(304, 336, 9, 41, 6, b'1', '1', '2024-03-01 13:20:00', NULL, NULL, 1, 37),
	(305, 337, 10, 41, 6, b'1', '1', '2024-03-01 13:23:01', NULL, NULL, 1, 37),
	(322, 341, 11, 43, 6, b'1', '1', '2024-03-31 19:42:22', NULL, NULL, 3, 38),
	(323, 342, 12, 86, 6, b'1', '2', '2024-05-09 16:27:36', NULL, NULL, 3, 41),
	(325, 343, 13, 43, 6, b'1', '1', '2024-05-23 15:36:32', NULL, NULL, 3, 41);
/*!40000 ALTER TABLE `user_role_mapping_temp` ENABLE KEYS */;

-- Dumping structure for procedure traneco_cmsdb.USP_institution_add
DELIMITER //
CREATE PROCEDURE `USP_institution_add`(
IN  P_InstitutionCode VARCHAR(50),
IN  P_InstitutionDesc VARCHAR(200),
IN  P_UserPass VARCHAR(255),
IN  P_CreatedBy INT,
OUT P_Response_code VARCHAR(2)
)
BEGIN
	DECLARE P_UserId BIGINT;
	DECLARE P_InstitutionId INT;
	DECLARE P_RoleId INT;
	DECLARE P_finished,P_indexM INT DEFAULT 0;
	DECLARE P_MenuId INT;
	declare P_GroupID int;
	DECLARE P_RoleMenuMappingId INT;
	DECLARE P_UserRoleInstitutionMappingID INT;
	DECLARE V_UserId BIGINT;
	/*Cursor for Default MenuMapping to the Role*/
	DECLARE menumapping_cursor CURSOR FOR 
	SELECT id AS MenuID FROM cfg_cms_menu WHERE parent_menu_id = 1 AND STATUS = 1;
	
	/*Cursor for Default User Role Institution Mapping*/
	DECLARE userroleinstmapping_cursor CURSOR FOR 
	SELECT user_id AS UserID FROM user_details WHERE user_status = 1 AND participant_id = P_InstitutionId AND user_name IN (CONCAT(P_InstitutionCode,'AdminMaker'),CONCAT(P_InstitutionCode,'AdminChecker'));
	
	/*DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        SET P_Response_code = '01';
		INSERT INTO errorlog(ApplicationType,Source,UrlValue,Message) VALUES
		('USP_institution_add',RETURNED_SQLSTATE,MYSQL_ERRNO,MESSAGE_TEXT);
		
		ROLLBACK;
    END;
	DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET P_Response_code = '01';
		INSERT INTO errorlog(ApplicationType,Source,UrlValue,Message) VALUES
		('USP_institution_add',RETURNED_SQLSTATE,MYSQL_ERRNO,MESSAGE_TEXT);
		ROLLBACK;
    END;*/
 
START TRANSACTION;
    			
	IF NOT EXISTS (SELECT participant_id FROM cfg_interface WHERE interface_type = P_InstitutionCode)
	THEN
		/*Add new Institution in imps_inst_master table*/
		INSERT INTO cfg_interface(interface_type,participant_desc) 
		VALUES(P_InstitutionCode,P_InstitutionDesc);
		
		/*Add AdminMaker User in imps_user_master_temp & imps_user_master tables*/
		SET P_InstitutionId=(SELECT participant_id FROM cfg_interface WHERE interface_type = P_InstitutionCode);
		/*SET P_UserId = (SELECT IFNULL(MAX(UserId),0) + 1 FROM imps_user_master_temp WHERE InstitutionId = P_InstitutionId AND IsActive = 1 AND ApprovalStatusID <> 2);*/		
		SET P_UserId = (SELECT IFNULL(MAX(user_Id),0) + 1 FROM user_details_temp);
		
		INSERT INTO user_details_temp(user_ID,participant_id,user_type_id,user_access_type_id,user_full_name,user_name,user_password,user_status_id,created_by,approved_by,approved_date,approval_remarks,approval_status_id,Login_session_id)	
		VALUES (P_UserId,P_InstitutionId,2,1,'Admin Maker',CONCAT(P_InstitutionCode,'AdminMaker'),P_UserPass,1,P_CreatedBy,P_CreatedBy,NOW(),'Default AdminUser',1,NULL);
		
		INSERT INTO user_details(user_ID,participant_id,user_type_id,user_access_type_id,user_full_name,user_name,user_password,user_status_id,created_by,approved_by,approved_date,approval_remarks,approval_status_id,Login_session_id)	
		VALUES (P_UserId,P_InstitutionId,2,1,'Admin Maker',CONCAT(P_InstitutionCode,'AdminMaker'),P_UserPass,1,P_CreatedBy,P_CreatedBy,NOW(),'Default AdminUser',1,NULL);
		
		/*Add AdminChecker User in imps_user_master_temp & imps_user_master tables*/
		SET P_UserId = (SELECT IFNULL(MAX(user_id),0) + 1 FROM user_details_temp);
				
		INSERT INTO user_details_temp(user_ID,participant_id,user_type_id,user_access_type_id,user_full_name,user_name,user_password,user_status_id,created_by,approved_by,approved_date,approval_remarks,approval_status_id,Login_session_id)	
		VALUES (P_UserId,P_InstitutionId,2,1,'Admin Checker',CONCAT(P_InstitutionCode,'AdminChecker'),P_UserPass,1,P_CreatedBy,P_CreatedBy,NOW(),'Default AdminUser',1,NULL);
		
		INSERT INTO user_details(user_ID,participant_id,user_type_id,user_access_type_id,user_full_name,user_name,user_password,user_status_id,created_by,approved_by,approved_date,approval_remarks,approval_status_id,Login_session_id)	
		VALUES (P_UserId,P_InstitutionId,2,1,'Admin Checker',CONCAT(P_InstitutionCode,'AdminChecker'),P_UserPass,1,P_CreatedBy,P_CreatedBy,NOW(),'Default AdminUser',1,NULL);
		
		insert into group_mapping (participant_id,`name`,description,created_by,created_date)
		values (P_InstitutionId,'DefaultGroup','DefaultGroup',P_CreatedBy,NOW());
		
		SET P_GroupID = LAST_INSERT_ID();	
		
		/*Add DefaultRole in imps_role_master_temp & imps_role_master tables*/
		SET P_RoleId = (SELECT IFNULL(MAX(role_id),0) + 1 FROM cfg_role_temp);
	
		INSERT INTO cfg_role_temp(role_id,participant_id,role_name,role_description,created_by,approved_by,approved_date,remarks,approval_status_id,group_id)
		VALUES (P_RoleId,P_InstitutionId,'DefaultRole','DefaultRole',P_CreatedBy,P_CreatedBy,NOW(),'Default AdminRole',1,P_GroupID);
		
		INSERT INTO cfg_role(id,participant_id,role_name,role_description,created_by,approved_by,approved_date,remarks,group_id)
		VALUES (P_RoleId,P_InstitutionId,'DefaultRole','DefaultRole',P_CreatedBy,P_CreatedBy,NOW(),'Default AdminRole',P_GroupID);
		
		/*Add DefaultMenu mapping for Above Role Created in imps_role_menu_mapping_temp & imps_role_menu_mapping tables*/
		SET P_finished = (SELECT COUNT(id) FROM cfg_cms_menu WHERE parent_menu_id = 1); 
		SET P_RoleMenuMappingId = (SELECT IFNULL(MAX(role_menu_mapping_id),0) + 1 FROM role_menu_mapping_temp);	

			
			 
		OPEN menumapping_cursor;
	 
		WHILE P_indexM < P_finished DO
		FETCH menumapping_cursor INTO P_MenuId;
			 
		INSERT INTO role_menu_mapping_temp(role_menu_mapping_id,participant_id,role_id,menu_id,created_by,approval_status_id,group_id)
		VALUES(P_RoleMenuMappingId,P_InstitutionId,P_RoleId,P_MenuId,P_CreatedBy,1,P_GroupID);
		
		INSERT INTO role_menu_mapping(id,participant_id,role_id,menu_id,created_by,group_id)
		VALUES(P_RoleMenuMappingId,P_InstitutionId,P_RoleId,P_MenuId,P_CreatedBy,P_GroupID);
		
		SET P_RoleMenuMappingId=P_RoleMenuMappingId+1;		
		SET P_indexM= P_indexM+1;
		END WHILE; 
			  
		CLOSE menumapping_cursor;
				
		/*Add Default UserRole mapping for Above Institution Created in imps_role_menu_mapping_temp & imps_role_menu_mapping tables*/
		SET P_finished = (SELECT COUNT(user_id) FROM user_details WHERE  participant_id = P_InstitutionId AND user_name IN (CONCAT(P_InstitutionCode,'AdminMaker'),CONCAT(P_InstitutionCode,'AdminChecker'))); 
		SET P_UserRoleInstitutionMappingID = (SELECT IFNULL(MAX(id),0) + 1 FROM user_role_mapping_temp);	
		SET P_indexM = 0;
 
		OPEN userroleinstmapping_cursor;
	 
		WHILE P_indexM < P_finished DO
		FETCH userroleinstmapping_cursor INTO V_UserId;
			 
		INSERT INTO user_role_mapping_temp(id,participant_id,role_id,user_id,created_by,approval_status_id,group_id)
		VALUES(P_UserRoleInstitutionMappingID,P_InstitutionId,P_RoleId,V_UserId,P_CreatedBy,1,P_GroupID);
		
		INSERT INTO user_role_mapping(id,participant_id,role_id,user_id,created_by,group_id)
		VALUES(P_UserRoleInstitutionMappingID,P_InstitutionId,P_RoleId,V_UserId,P_CreatedBy,P_GroupID);
		
		SET P_UserRoleInstitutionMappingID=P_UserRoleInstitutionMappingID+1;		
		SET P_indexM= P_indexM+1;
		END WHILE; 
			  
		CLOSE userroleinstmapping_cursor;
		INSERT INTO `cardmanagement`.`cfg_participant` (id,participant_name,description) VALUES(P_InstitutionId,P_InstitutionCode,P_InstitutionDesc);
		SET P_Response_code = '00';
	ELSE
		SET P_Response_code = '02';
	END IF;
    COMMIT;
END//
DELIMITER ;

-- Dumping structure for procedure traneco_cmsdb.usp_role_add
DELIMITER //
CREATE PROCEDURE `usp_role_add`(
	IN `P_Action` VARCHAR(20),
	IN `P_participantID` INT,
	IN `P_RoleTempId` INT,
	IN `P_RoleId` INT,
	IN `P_RoleName` VARCHAR(100),
	IN `P_Description` VARCHAR(200),
	IN `P_MenuIds` VARCHAR(500),
	IN `P_Remark` VARCHAR(500),
	IN `P_CreatedBy` INT,
	IN `P_ApprovedBy` INT,
	IN `P_ApprovalStatusID` INT,
	IN `P_GroupID` INT,
	OUT `P_Responsecode` VARCHAR(2)
)
BEGIN
 DECLARE V_finished,V_indexm INT DEFAULT 0;
 DECLARE V_MenuId INT;
 DECLARE V_RoleMenuMappingId INT;
 DECLARE V_IsRecordExist INT;
 
 /*Cursor for Default MenuMapping to the Role*/
 DECLARE menumapping_cursor CURSOR FOR 
	SELECT OutputList FROM temp1;
	 
	IF (UPPER(P_Action) = 'ADD') THEN
	
	  DROP TEMPORARY TABLE IF EXISTS temp;
		CREATE TEMPORARY TABLE temp(InputList VARCHAR(500));
		INSERT INTO temp(InputList) VALUES (P_MenuIds);
		
	  DROP TEMPORARY TABLE IF EXISTS temp1;
		CREATE TEMPORARY TABLE temp1(OutputList CHAR(255) );
		SET @sql = CONCAT("insert into temp1 (OutputList) values ('", REPLACE(( SELECT GROUP_CONCAT(DISTINCT InputList) AS DATA FROM temp), ",", "'),('"),"');");
		PREPARE stmt1 FROM @sql;
		EXECUTE stmt1;
	    
	    SET P_RoleId = (SELECT IFNULL(MAX(Role_Id),0) + 1 FROM `user_role_mapping_temp`);
	
            INSERT INTO `cfg_role_temp`(Role_ID,participant_id,Role_Name,role_Description,Created_By,Remarks,approval_status_id,group_id)
			VALUES (P_RoleId,P_participantID,P_RoleName,P_Description,P_CreatedBy,P_Remark,3,P_GroupID);
	    
	    SET P_RoleTempId = LAST_INSERT_ID();
	    /*Add DefaultMenu mapping for Above Role Created in imps_role_menu_mapping_temp & imps_role_menu_mapping tables*/
		SET V_finished = (SELECT COUNT(OutputList) FROM temp1); 
		SET V_RoleMenuMappingId = (SELECT IFNULL(MAX(Role_Menu_Mapping_Id),0) + 1 FROM `role_menu_mapping_temp`);	
			 
		OPEN menumapping_cursor;
	 
		WHILE V_indexm < V_finished DO
		FETCH menumapping_cursor INTO V_MenuId;
		 
		INSERT INTO `role_menu_mapping_temp`(role_temp_id,role_menu_mapping_id,participant_id,role_id,menu_id,created_by,approval_status_id,group_id)
		VALUES(P_RoleTempId,V_RoleMenuMappingId,P_participantID,P_RoleTempId,V_MenuId,P_CreatedBy,3,P_GroupID);
			
		SET V_RoleMenuMappingId=V_RoleMenuMappingId+1;		
		SET V_indexm= V_indexm+1;
		END WHILE; 
			  
		CLOSE menumapping_cursor;
		
		SET P_Responsecode = '00';
	ELSEIF (UPPER(P_Action) = 'EDIT') THEN
	
		DROP TEMPORARY TABLE IF EXISTS temp;
		CREATE TEMPORARY TABLE temp(InputList VARCHAR(500));
		INSERT INTO temp(InputList) VALUES (P_MenuIds);
		
		DROP TEMPORARY TABLE IF EXISTS temp1;
		CREATE TEMPORARY TABLE temp1(OutputList CHAR(255));
		SET @sql = CONCAT("insert into temp1 (OutputList) values ('", REPLACE(( SELECT GROUP_CONCAT(DISTINCT InputList) AS DATA FROM temp), ",", "'),('"),"');");
		PREPARE stmt1 FROM @sql;
		EXECUTE stmt1;		
		
		INSERT INTO `cfg_role_temp`(Role_ID,participant_id,Role_Name,role_description,`status`,Created_By,Created_Date,Remarks,approval_status_id,group_id)
		SELECT id,participant_id,Role_Name,role_description,`status`,P_CreatedBy,NOW(),P_Remark,3,P_GroupID
		FROM `cfg_role` WHERE Role_Id = P_RoleId AND participant_id = P_participantID AND `status` = 1;
		
 	        SET P_RoleTempId = LAST_INSERT_ID();
		/*Add DefaultMenu mapping for Above Role Created in imps_role_menu_mapping_temp & imps_role_menu_mapping tables*/
		SET V_finished = (SELECT COUNT(OutputList) FROM temp1); 
		SET V_RoleMenuMappingId = (SELECT IFNULL(MAX(role_menu_mapping_id),0) + 1 FROM role_menu_mapping_temp);	
				 
		OPEN menumapping_cursor;
		 
		WHILE V_indexm < V_finished DO
		FETCH menumapping_cursor INTO V_MenuId;
				 
		INSERT INTO `role_menu_mapping_temp`(role_menu_mapping_id,participant_id,Role_ID,Menu_ID,Created_By,Approval_Status_ID,group_id)
		VALUES(V_RoleMenuMappingId,P_participantID,P_RoleId,V_MenuId,P_CreatedBy,3,P_GroupID);
				
		SET V_RoleMenuMappingId=V_RoleMenuMappingId+1;		
		SET V_indexm= V_indexm+1;
		END WHILE; 
				  
		CLOSE menumapping_cursor;
			
		SET P_Responsecode = '00';
		
	 ELSEIF (UPPER(P_Action) = 'APPROVE') THEN
		IF (P_ApprovalStatusID = 1) THEN
		SET V_IsRecordExist = (SELECT COUNT(id) FROM `cfg_role` WHERE id = P_RoleId AND participant_id = P_participantID AND status = 1);
		
			IF (V_IsRecordExist = 0) THEN
		
				INSERT INTO `cfg_role`(id,participant_id,Role_Name,role_description,created_by,created_date,approved_by,approved_date,remarks,group_id) 
				SELECT id,participant_id,role_name,role_description,created_by,created_date,P_ApprovedBy,NOW(),P_Remark,group_id
				FROM `cfg_role_temp` WHERE id = P_RoleTempId AND role_id = P_RoleId AND participant_id = P_participantID AND approval_status_id = 3;	
				
				INSERT INTO `role_menu_mapping`(id,participant_id,role_id,menu_id,created_by,created_datetime,last_modified_by,last_modified_datetime,group_id)
				SELECT role_menu_mapping_id,participant_id,role_id,menu_id,created_by,created_datetime,P_ApprovedBy,NOW(),group_id
				FROM `role_menu_mapping_temp` WHERE role_temp_id = P_RoleTempId AND participant_id = P_participantID AND approval_status_id = 3;
			
			ELSE			
				UPDATE `cfg_role` M
				INNER JOIN `cfg_role_temp` T ON M.participant_id = T.participant_id AND M.id = T.role_id
				SET M.role_name = T.role_name,
				M.role_description = T.role_description, 
				M.approved_by = P_ApprovedBy,
				M.approved_date = NOW(),
				M.remarks = P_Remark,
				M.last_modified_by = T.created_by,
				M.last_modified_date = T.created_date,
				M.group_id = T.group_id
				WHERE T.id = P_RoleTempId AND T.role_id = P_RoleId AND T.participant_id = P_participantID;
				
				DELETE FROM `role_menu_mapping` WHERE role_id = P_RoleId AND participant_id = P_participantID;	
				
				INSERT INTO `role_menu_mapping`(id,participant_id,role_id,menu_id,created_by,created_datetime,last_modified_by,last_modified_datetime,group_id)
				SELECT role_menu_mapping_id,participant_id,role_id,menu_id,created_by,created_datetime,P_ApprovedBy,NOW(),group_id
				FROM `role_menu_mapping_temp` WHERE id = P_RoleTempId AND participant_id = P_participantID AND approval_status_id = 3;
				
			END IF;		
				
	     	UPDATE `cfg_role_temp` 
			SET approval_status_id = 4
			WHERE id <> P_RoleTempId AND  role_id = P_RoleId AND participant_id = P_participantID AND approval_status_id = 1;
	     	
	     	UPDATE `cfg_role_temp` 
			SET approval_status_id = 1,
			approved_by = P_ApprovedBy,
			approved_date = NOW(),
			remarks = P_Remark
			WHERE id = P_RoleTempId AND  role_id = P_RoleId AND participant_id = P_participantID; 
				
	     	
	     	UPDATE `role_menu_mapping_temp` 
			SET approval_status_id = 4
			WHERE id <> P_RoleTempId AND  role_id = P_RoleId AND participant_id = P_participantID AND approval_status_id = 1;
	     	
	     	UPDATE `role_menu_mapping_temp` 
			SET approval_status_id = 1,
			last_modified_by = P_ApprovedBy,
			last_modified_datetime = NOW()
			WHERE role_temp_id = P_RoleTempId AND  role_id = P_RoleId AND participant_id = P_participantID; 
	     	
	     	ELSE
	     	
		UPDATE `cfg_role_temp` 
		SET approval_status_id = 2,
		approved_by = P_ApprovedBy,
		approved_date = NOW(),
		remarks = P_Remark
		WHERE id = P_RoleTempId AND  role_id = P_RoleId AND participant_id = P_participantID; 
		    
		UPDATE `role_menu_mapping_temp` 
		SET approval_status_id = 2,
		created_by = P_ApprovedBy,
		created_datetime = NOW()
		WHERE role_temp_id = P_RoleTempId AND  role_id = P_RoleId AND participant_id = P_participantID;     
	     	
		END IF;	
		SET P_Responsecode = '00';	
	ELSE
		SET P_Responsecode = '02';
	END IF;
    COMMIT;
    
END//
DELIMITER ;

-- Dumping structure for procedure traneco_cmsdb.USP_USER_MANAGEMENT
DELIMITER //
CREATE PROCEDURE `USP_USER_MANAGEMENT`(
	IN `P_Action` VARCHAR(20),
	IN `P_ParticipantId` INT,
	IN `P_UserTempID` BIGINT,
	IN `P_UserID` BIGINT,
	IN `P_RoleID` INT,
	IN `P_UserAccessTypeId` INT,
	IN `P_FirstName` VARCHAR(30),
	IN `P_LastName` VARCHAR(30),
	IN `P_UserName` VARCHAR(30),
	IN `P_MobileNumber` VARCHAR(20),
	IN `P_EmailID` VARCHAR(254),
	IN `P_SecretQuestionID` INT,
	IN `P_SecretQuestionAnswer` VARCHAR(200),
	IN `P_UserPass` VARCHAR(255),
	IN `P_SensitiveData` INT,
	IN `P_Remark` VARCHAR(500),
	IN `P_CreatedBy` INT,
	IN `P_ApprovedBy` INT,
	IN `P_ApprovalStatusID` INT,
	IN `P_GroupID` INT,
	IN `plain_password` VARCHAR(255),
	OUT `P_Responsecode` VARCHAR(2)
)
BEGIN
	
 DECLARE V_IsRecordExist INT;
 DECLARE V_UserRoleInstitutionMappingID INT;	
 
    /*DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        SET P_Responsecode = '01';
	INSERT INTO `error_log`(Application_Type,Source,Url_name,Message) VALUES
	('USP_USER_MANAGEMENT',RETURNED_SQLSTATE,MYSQL_ERRNO,MESSAGE_TEXT);
	ROLLBACK;
    END;
    DECLARE EXIT HANDLER FOR SQLWARNING
    BEGIN
        SET P_Responsecode = '01';
	INSERT INTO `error_log`(Application_Type,Source,Url_name,Message) VALUES
	('USP_USER_MANAGEMENT',RETURNED_SQLSTATE,MYSQL_ERRNO,MESSAGE_TEXT);
	ROLLBACK;
    END;*/
    START TRANSACTION;
	IF (UPPER(P_Action) = 'ADD')
	THEN
	    SET V_IsRecordExist = (SELECT COUNT(User_ID) FROM user_details WHERE participant_id = P_ParticipantId AND UPPER(User_Name) = UPPER(P_UserName)) + (SELECT COUNT(User_ID)FROM user_details_temp WHERE participant_id = P_ParticipantId AND UPPER(User_Name) = UPPER(P_UserName));
	    IF (V_IsRecordExist = 0) THEN
		SET P_UserID = (SELECT IFNULL(MAX(User_Id),0) + 1 FROM user_details_temp);
			
		INSERT INTO user_details_temp(User_ID,participant_id,User_Type_Id,User_Access_Type_Id,user_full_name,User_Name,Mobile_Number,Email_ID,Secret_Question_ID,Secret_Question_Answer,User_Password,User_Status_Id,Sensitive_Date,Created_By,Approval_Status_ID)	
		VALUES (P_UserID,P_ParticipantId,3,P_UserAccessTypeId,CONCAT(P_FirstName,' ',P_LastName),P_UserName,P_MobileNumber,P_EmailID,P_SecretQuestionID,P_SecretQuestionAnswer,P_UserPass,1,P_SensitiveData,P_CreatedBy,3);
		
		
		SET P_UserTempID = LAST_INSERT_ID();
		SET V_UserRoleInstitutionMappingID = (SELECT IFNULL(MAX(id),0) + 1 FROM user_role_mapping);
			
		set P_RoleID = (SELECT id FROM `cfg_role` where group_id=P_GroupID);	
		
		INSERT INTO user_role_mapping_temp(user_role_id, participant_id,Role_ID,User_Id,Created_By,Approval_Status_ID,group_id)
		VALUES(P_UserTempID,P_ParticipantId,P_RoleID,P_UserID,P_CreatedBy,3,P_GroupID);
					     
		SET P_Responsecode = '00';
	     ELSE
		SET P_Responsecode = '02';
	     END IF;	
	ELSEIF (UPPER(P_Action) = 'EDIT') THEN
		
		INSERT INTO user_details_temp(User_ID,participant_id,User_Type_Id,User_Access_Type_Id,
		user_full_name,User_Name,Mobile_Number,Email_ID,secret_question_id,secret_question_answer,
		User_Password,User_Status_Id,Sensitive_Date,Created_By,Created_Date,Approval_Status_ID)	
		SELECT User_ID,participant_id,User_Type_Id,P_UserAccessTypeId,CONCAT(P_FirstName,' ',P_LastName),
		User_Name,P_MobileNumber,P_EmailID,P_SecretQuestionID,
		P_SecretQuestionAnswer,P_UserPass,User_Status_Id,P_SensitiveData,P_CreatedBy,NOW(),3 
		FROM user_details WHERE participant_id = P_ParticipantId AND User_ID = P_UserID AND user_status = 1;	
		
		SET P_UserTempID = LAST_INSERT_ID();
		
		INSERT INTO user_role_mapping_temp(participant_id,Role_ID,User_Id,Created_By,
		Created_Date,Approval_Status_ID,user_role_id,group_id)
		SELECT participant_id,P_RoleID,User_Id,P_CreatedBy,NOW(),3,P_UserTempID ,P_GroupID
		FROM user_role_mapping WHERE participant_id = P_ParticipantId AND User_ID = P_UserID; 
		
		SET P_Responsecode = '00';
				
	ELSEIF (UPPER(P_Action) = 'APPROVE') THEN
		IF (P_ApprovalStatusID = 1) THEN
		
			SET V_IsRecordExist = (SELECT COUNT(User_ID)FROM user_details WHERE participant_id = P_ParticipantId AND User_ID = P_UserID AND user_status = 1);
				IF (V_IsRecordExist = 0) THEN
			
					INSERT INTO `user_details`(User_ID,participant_id,User_Type_Id,User_Access_Type_Id,user_full_name,User_Name,Mobile_Number,Email_ID,Secret_Question_ID,Secret_Question_Answer,User_Password,sensitive_pwd,User_Status_Id,Sensitive_Date,Created_By,Approved_By,Approved_Date,plain_password) SELECT user_id,participant_id,User_Type_Id,User_Access_Type_Id,user_full_name,User_Name,Mobile_Number,Email_ID,
					Secret_Question_ID,Secret_Question_Answer,P_UserPass,P_UserPass,User_Status_Id,Sensitive_Date,Created_By,P_ApprovedBy,NOW(),plain_password
					FROM `user_details_temp` WHERE participant_id = P_ParticipantId AND id = P_UserTempID 
					AND User_ID = P_UserID AND Approval_Status_ID = 3;
					
					INSERT INTO user_role_mapping(id,participant_id,Role_ID,User_Id,Created_By,group_id,user_status)
					SELECT id,participant_id,Role_ID,User_Id,Created_By ,group_id,user_status
					FROM user_role_mapping_temp WHERE participant_id = P_ParticipantId AND user_role_id = P_UserTempID AND User_ID = P_UserID AND Approval_Status_ID = 3;
					
					/*SET P_RoleID = (SELECT RoleID FROM imps_user_role_inst_mapping_temp WHERE InstitutionId = P_InstitutionId AND UserID = P_UserID AND ApprovalStatusID = 3);*/
			
				ELSE
				
					UPDATE user_details M
					INNER JOIN user_details_temp T ON M.participant_id = T.participant_id AND M.User_ID = T.User_ID
					SET
					M.User_ID = T.User_ID,
					M.participant_id = T.participant_id,
					M.User_Type_Id = T.User_Type_Id,
					M.User_Access_Type_Id = T.User_Access_Type_Id,
					M.user_full_name = t.user_full_name,
					M.User_Name = T.User_Name,
					M.Mobile_Number = T.Mobile_Number,
					M.Email_ID = T.Email_ID,
					M.Secret_Question_ID = T.Secret_Question_ID,
					M.Secret_Question_Answer = T.Secret_Question_Answer,
					M.User_Password = T.User_Password,
					M.User_Status_Id = T.User_Status_Id,
					M.Sensitive_Date = T.Sensitive_Date,
					M.Last_Modified_By = T.Created_By,
					M.LastModifiedDate = T.Created_Date,
					M.Approved_By = P_ApprovedBy,
					M.Approved_Date = NOW(),
					M.Login_Session_Id = T.Login_Session_Id
					WHERE T.id = P_UserTempID AND T.User_ID = P_UserID AND T.participant_id = P_ParticipantId;
					
					UPDATE user_role_mapping M
					INNER JOIN user_role_mapping_temp T ON M.participant_id = T.participant_id AND M.User_ID = T.User_ID
					SET 
					M.id = T.id,
					M.participant_id = T.participant_id,
					M.Role_ID = T.Role_ID,
					M.User_Id = T.User_Id,
					M.Last_Modified_By = T.Created_By,
					M.last_modified_date = T.Created_Date,
					M.group_id = T.group_id
					WHERE T.user_role_id= P_UserTempID AND T.User_ID = P_UserID AND T.participant_id = P_ParticipantId;
					END IF;		
							
					UPDATE user_details_temp 
					SET Approval_Status_ID = 4
					WHERE id <> P_UserTempID AND User_ID = P_UserID AND participant_id = P_participantId AND Approval_Status_ID = 1;	
					
					UPDATE user_details_temp 
					SET Approval_Status_ID = 1,
					Approved_By = P_ApprovedBy,
					Approved_Date = NOW()
					WHERE id = P_UserTempID AND User_ID = P_UserID AND participant_id = P_participantId;
					
					UPDATE user_role_mapping_temp 
					SET Approval_Status_ID = 4
					WHERE id <> P_UserTempID AND  User_ID = P_UserID AND participant_id = P_participantId AND Approval_Status_ID = 1;
					
					UPDATE user_role_mapping_temp 
					SET Approval_Status_ID = 1
					WHERE user_role_id = P_UserTempID AND User_ID = P_UserID AND participant_id = P_participantId;
	     		
		ELSE
	     	
			UPDATE user_details_temp 
			SET Approval_Status_ID = 2,
			Approved_By = P_ApprovedBy,
			Approved_Date = NOW()
			WHERE id = P_UserTempID AND User_ID = P_UserID AND participant_id = P_participantId;
			
			UPDATE user_role_mapping_temp 
			SET Approval_Status_ID = 2
			WHERE user_role_id = P_UserTempID AND User_ID = P_UserID AND participant_id = P_participantId;
	     		
		END IF;	
		SET P_Responsecode = '00';
		ELSE
		SET P_Responsecode = '02';
	END IF;
    COMMIT;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
