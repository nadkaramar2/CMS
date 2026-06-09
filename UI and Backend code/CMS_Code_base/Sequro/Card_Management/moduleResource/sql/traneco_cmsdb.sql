-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.39-log - MySQL Community Server (GPL)
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
  `action_name` char(25) DEFAULT NULL,
  `parent_submenu_id` int(10) DEFAULT NULL,
  `menu_icon` char(20) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;


-- Dumping structure for table traneco_cmsdb.cfg_interface
CREATE TABLE IF NOT EXISTS `cfg_interface` (
  `id` int(10) NOT NULL,
  `participant_id` int(10) NOT NULL AUTO_INCREMENT,
  `interface_type` char(10) NOT NULL,
  `participant_desc` varchar(50) DEFAULT NULL,
  KEY `participant_id` (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_interface: ~0 rows (approximately)
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
  `last_modified_date` datetime DEFAULT NULL
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
  `last_modified_date` datetime DEFAULT NULL
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
  PRIMARY KEY (`id`)
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
  PRIMARY KEY (`id`)
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
  `password_policy` int(10) DEFAULT NULL
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
  `response_message` char(50) NOT NULL
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_role: ~4 rows (approximately)
/*!40000 ALTER TABLE `cfg_role` DISABLE KEYS */;
INSERT INTO `cfg_role` (`id`, `participant_id`, `role_name`, `role_description`, `status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `approved_by`, `approved_date`, `remarks`, `group_id`) VALUES
	(1, 6, 'DefaultRole', _binary 0x44656661756C74526F6C65, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C742041646D696E526F6C65, 1),
	(2, 6, 'admin', _binary 0x61646D696E, b'1', '1', '2020-08-15 04:44:00', NULL, NULL, '2', '2020-08-15 04:44:30', NULL, 2),
	(3, 6, 'mumbaiAdmin', _binary 0x6D756D62616941646D696E, b'1', '3', '2020-09-11 18:56:02', NULL, NULL, '1', '2020-09-11 18:56:25', NULL, 3),
	(6, 6, '', _binary '', b'1', '6', '2022-11-14 16:54:35', NULL, NULL, '3', '2022-11-14 16:55:09', NULL, 1);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cfg_role_temp: ~6 rows (approximately)
/*!40000 ALTER TABLE `cfg_role_temp` DISABLE KEYS */;
INSERT INTO `cfg_role_temp` (`id`, `role_id`, `participant_id`, `role_name`, `role_description`, `status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `approved_by`, `approved_date`, `remarks`, `approval_status_id`, `group_id`) VALUES
	(1, 1, 6, 'DefaultRole', _binary 0x44656661756C74526F6C65, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C742041646D696E526F6C65, 1, 1),
	(2, 2, 6, 'admin', _binary 0x61646D696E, b'1', '1', '2020-08-15 04:44:00', NULL, NULL, '2', '2020-08-15 04:44:30', NULL, 1, 2),
	(3, 3, 6, 'mumbaiAdmin', _binary 0x6D756D62616941646D696E, b'1', '3', '2020-09-11 18:56:02', NULL, NULL, '1', '2020-09-11 18:56:25', NULL, 1, 3),
	(4, 4, 6, '', _binary '', b'1', '4', '2022-11-14 16:52:12', NULL, NULL, NULL, NULL, NULL, 3, 2),
	(5, 4, 6, '', _binary '', b'1', '3', '2022-11-14 16:53:52', NULL, NULL, NULL, NULL, NULL, 3, 2),
	(6, 4, 6, '', _binary '', b'1', '6', '2022-11-14 16:54:35', NULL, NULL, '3', '2022-11-14 16:55:09', NULL, 1, 1);
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
  KEY `id` (`id`)
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
  `user_status` char(20) DEFAULT NULL
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
  `table_name` char(100) DEFAULT NULL,
  `column_name` char(100) DEFAULT NULL,
  `new_field` char(200) DEFAULT NULL,
  `old_field` char(200) DEFAULT NULL,
  `dml_action` char(10) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` char(20) DEFAULT NULL,
  `approved_by` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.cms_audit: ~0 rows (approximately)
/*!40000 ALTER TABLE `cms_audit` DISABLE KEYS */;
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
  PRIMARY KEY (`id`)
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
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.group_mapping: ~3 rows (approximately)
/*!40000 ALTER TABLE `group_mapping` DISABLE KEYS */;
INSERT INTO `group_mapping` (`id`, `participant_id`, `name`, `description`, `created_by`, `created_date`) VALUES
	(1, 6, 'DefaultGroup', 'DefaultGroup', 0, '2020-07-19 17:58:34'),
	(2, 6, 'Admin', 'Admin', 1, '2020-08-15 04:42:01'),
	(3, 6, 'mumbaiAdmin', 'mumbaiAdmin', 3, '2020-09-11 18:55:12');
/*!40000 ALTER TABLE `group_mapping` ENABLE KEYS */;

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

-- Dumping data for table traneco_cmsdb.role_menu_mapping: ~112 rows (approximately)
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
	(29, 6, 2, 5, b'1', '1', '2020-08-15 04:44:00', '2', '2020-08-15 04:44:30', 2),
	(30, 6, 2, 6, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(31, 6, 2, 7, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(32, 6, 2, 8, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(33, 6, 2, 9, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(34, 6, 2, 11, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(35, 6, 2, 30, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(36, 6, 2, 13, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(37, 6, 2, 15, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(38, 6, 2, 33, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(39, 6, 2, 34, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(40, 6, 2, 36, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(41, 6, 2, 37, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(42, 6, 2, 39, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(43, 6, 2, 16, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(44, 6, 2, 19, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(45, 6, 2, 20, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(46, 6, 2, 21, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(47, 6, 2, 22, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(48, 6, 2, 23, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(49, 6, 2, 24, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(50, 6, 2, 25, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(51, 6, 2, 26, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(52, 6, 2, 28, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(53, 6, 2, 29, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(54, 6, 2, 31, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 2),
	(55, 6, 2, 32, b'1', '1', '2020-08-15 04:44:02', '2', '2020-08-15 04:44:30', 2),
	(56, 6, 2, 35, b'1', '1', '2020-08-15 04:44:02', '2', '2020-08-15 04:44:30', 2),
	(57, 6, 2, 38, b'1', '1', '2020-08-15 04:44:02', '2', '2020-08-15 04:44:30', 2),
	(58, 6, 3, 5, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(59, 6, 3, 6, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(60, 6, 3, 7, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(61, 6, 3, 8, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(62, 6, 3, 9, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(63, 6, 3, 11, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(64, 6, 3, 30, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(65, 6, 3, 13, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(66, 6, 3, 15, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(67, 6, 3, 33, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(68, 6, 3, 34, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(69, 6, 3, 36, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(70, 6, 3, 37, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 3),
	(71, 6, 3, 39, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(72, 6, 3, 41, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(73, 6, 3, 16, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(74, 6, 3, 19, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(75, 6, 3, 20, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(76, 6, 3, 21, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(77, 6, 3, 22, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(78, 6, 3, 23, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(79, 6, 3, 24, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(80, 6, 3, 25, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(81, 6, 3, 26, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(82, 6, 3, 28, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(83, 6, 3, 29, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(84, 6, 3, 31, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(85, 6, 3, 32, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(86, 6, 3, 35, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(87, 6, 3, 38, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(88, 6, 3, 40, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(89, 6, 3, 42, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(90, 6, 3, 43, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 3),
	(91, 6, 3, 44, b'1', '3', '2022-02-15 02:31:07', '1', NULL, 3),
	(92, 6, 4, 13, b'1', '4', '2022-03-24 16:49:50', '3', '2022-03-24 16:53:01', 2),
	(93, 6, 4, 15, b'1', '4', '2022-03-24 16:49:50', '3', '2022-03-24 16:53:01', 2),
	(94, 6, 4, 33, b'1', '4', '2022-03-24 16:49:50', '3', '2022-03-24 16:53:01', 2),
	(95, 6, 4, 34, b'1', '4', '2022-03-24 16:49:50', '3', '2022-03-24 16:53:01', 2),
	(96, 6, 4, 16, b'1', '4', '2022-03-24 16:49:50', '3', '2022-03-24 16:53:01', 2),
	(97, 6, 4, 38, b'1', '4', '2022-03-24 16:49:50', '3', '2022-03-24 16:53:01', 2),
	(98, 6, 3, 45, b'1', '4', '2022-06-03 11:56:56', '3', '2022-06-03 11:56:59', 3),
	(99, 6, 3, 46, b'1', '4', '2022-06-03 14:56:25', '3', '2022-06-03 14:56:27', 3),
	(100, 6, 3, 47, b'1', '4', '2022-06-13 15:43:20', '3', '2022-06-13 15:43:22', 3),
	(101, 6, 3, 48, b'1', '4', '2022-06-13 18:16:53', '3', '2022-06-13 18:16:54', 3),
	(102, 6, 3, 49, b'1', '4', '2022-06-13 18:17:16', '3', '2022-06-13 18:17:17', 3),
	(103, 6, 3, 50, b'1', '3', '2022-10-18 15:01:52', '1', '2022-10-18 15:01:55', 3),
	(104, 6, 3, 51, b'1', '3', '2022-10-19 11:32:25', '1', '2022-10-19 11:32:28', 3),
	(105, 6, 3, 52, b'1', '3', '2022-10-29 11:59:14', '1', '2022-10-29 11:59:15', 3),
	(106, 6, 3, 53, b'1', '3', '2022-10-29 12:02:59', '1', '2022-10-29 12:03:00', 3),
	(107, 6, 3, 54, b'1', '3', '2022-11-11 14:16:03', '1', '2022-11-11 14:16:05', 3),
	(123, 6, 6, 45, b'1', '6', '2022-11-14 16:54:35', '3', '2022-11-14 16:55:09', 1),
	(124, 6, 6, 46, b'1', '6', '2022-11-14 16:54:35', '3', '2022-11-14 16:55:09', 1),
	(125, 6, 6, 47, b'1', '6', '2022-11-14 16:54:35', '3', '2022-11-14 16:55:09', 1),
	(126, 6, 6, 48, b'1', '6', '2022-11-14 16:54:35', '3', '2022-11-14 16:55:09', 1),
	(127, 6, 6, 49, b'1', '6', '2022-11-14 16:54:35', '3', '2022-11-14 16:55:09', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.role_menu_mapping_temp: ~127 rows (approximately)
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
	(29, 2, 29, 6, 2, 5, b'1', '1', '2020-08-15 04:44:00', '2', '2020-08-15 04:44:30', 1, 2),
	(30, 2, 30, 6, 2, 6, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(31, 2, 31, 6, 2, 7, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(32, 2, 32, 6, 2, 8, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(33, 2, 33, 6, 2, 9, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(34, 2, 34, 6, 2, 11, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(35, 2, 35, 6, 2, 30, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(36, 2, 36, 6, 2, 13, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(37, 2, 37, 6, 2, 15, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(38, 2, 38, 6, 2, 33, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(39, 2, 39, 6, 2, 34, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(40, 2, 40, 6, 2, 36, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(41, 2, 41, 6, 2, 37, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(42, 2, 42, 6, 2, 39, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(43, 2, 43, 6, 2, 16, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(44, 2, 44, 6, 2, 19, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(45, 2, 45, 6, 2, 20, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(46, 2, 46, 6, 2, 21, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(47, 2, 47, 6, 2, 22, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(48, 2, 48, 6, 2, 23, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(49, 2, 49, 6, 2, 24, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(50, 2, 50, 6, 2, 25, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(51, 2, 51, 6, 2, 26, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(52, 2, 52, 6, 2, 28, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(53, 2, 53, 6, 2, 29, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(54, 2, 54, 6, 2, 31, b'1', '1', '2020-08-15 04:44:01', '2', '2020-08-15 04:44:30', 1, 2),
	(55, 2, 55, 6, 2, 32, b'1', '1', '2020-08-15 04:44:02', '2', '2020-08-15 04:44:30', 1, 2),
	(56, 2, 56, 6, 2, 35, b'1', '1', '2020-08-15 04:44:02', '2', '2020-08-15 04:44:30', 1, 2),
	(57, 2, 57, 6, 2, 38, b'1', '1', '2020-08-15 04:44:02', '2', '2020-08-15 04:44:30', 1, 2),
	(58, 3, 58, 6, 3, 5, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(59, 3, 59, 6, 3, 6, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(60, 3, 60, 6, 3, 7, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(61, 3, 61, 6, 3, 8, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(62, 3, 62, 6, 3, 9, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(63, 3, 63, 6, 3, 11, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(64, 3, 64, 6, 3, 30, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(65, 3, 65, 6, 3, 13, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(66, 3, 66, 6, 3, 15, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(67, 3, 67, 6, 3, 33, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(68, 3, 68, 6, 3, 34, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(69, 3, 69, 6, 3, 36, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(70, 3, 70, 6, 3, 37, b'1', '3', '2020-09-11 18:56:02', '1', '2020-09-11 18:56:25', 1, 3),
	(71, 3, 71, 6, 3, 39, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(72, 3, 72, 6, 3, 41, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(73, 3, 73, 6, 3, 16, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(74, 3, 74, 6, 3, 19, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(75, 3, 75, 6, 3, 20, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(76, 3, 76, 6, 3, 21, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(77, 3, 77, 6, 3, 22, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(78, 3, 78, 6, 3, 23, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(79, 3, 79, 6, 3, 24, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(80, 3, 80, 6, 3, 25, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(81, 3, 81, 6, 3, 26, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(82, 3, 82, 6, 3, 28, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(83, 3, 83, 6, 3, 29, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(84, 3, 84, 6, 3, 31, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(85, 3, 85, 6, 3, 32, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(86, 3, 86, 6, 3, 35, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(87, 3, 87, 6, 3, 38, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(88, 3, 88, 6, 3, 40, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(89, 3, 89, 6, 3, 42, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(90, 3, 90, 6, 3, 43, b'1', '3', '2020-09-11 18:56:03', '1', '2020-09-11 18:56:25', 1, 3),
	(91, 3, 91, 6, 3, 44, b'1', '3', '2022-02-15 02:32:18', '1', NULL, 1, 3),
	(92, 4, 92, 6, 4, 13, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(93, 4, 93, 6, 4, 15, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(94, 4, 94, 6, 4, 33, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(95, 4, 95, 6, 4, 34, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(96, 4, 96, 6, 4, 36, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(97, 4, 97, 6, 4, 39, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(98, 4, 98, 6, 4, 41, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(99, 4, 99, 6, 4, 51, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(100, 4, 100, 6, 4, 52, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(101, 4, 101, 6, 4, 53, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(102, 4, 102, 6, 4, 54, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(103, 4, 103, 6, 4, 16, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(104, 4, 104, 6, 4, 19, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(105, 4, 105, 6, 4, 20, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(106, 4, 106, 6, 4, 21, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(107, 4, 107, 6, 4, 23, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(108, 4, 108, 6, 4, 24, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(109, 4, 109, 6, 4, 25, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(110, 4, 110, 6, 4, 26, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(111, 4, 111, 6, 4, 28, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(112, 4, 112, 6, 4, 50, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(113, 4, 113, 6, 4, 45, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(114, 4, 114, 6, 4, 46, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(115, 4, 115, 6, 4, 47, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(116, 4, 116, 6, 4, 48, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(117, 4, 117, 6, 4, 49, b'1', '4', '2022-11-14 16:52:12', NULL, NULL, 3, 2),
	(118, 5, 118, 6, 5, 45, b'1', '3', '2022-11-14 16:53:52', NULL, NULL, 3, 2),
	(119, 5, 119, 6, 5, 46, b'1', '3', '2022-11-14 16:53:52', NULL, NULL, 3, 2),
	(120, 5, 120, 6, 5, 47, b'1', '3', '2022-11-14 16:53:52', NULL, NULL, 3, 2),
	(121, 5, 121, 6, 5, 48, b'1', '3', '2022-11-14 16:53:52', NULL, NULL, 3, 2),
	(122, 5, 122, 6, 5, 49, b'1', '3', '2022-11-14 16:53:52', NULL, NULL, 3, 2),
	(123, 6, 123, 6, 6, 45, b'1', '6', '2022-11-14 16:54:35', NULL, NULL, 3, 1),
	(124, 6, 124, 6, 6, 46, b'1', '6', '2022-11-14 16:54:35', NULL, NULL, 3, 1),
	(125, 6, 125, 6, 6, 47, b'1', '6', '2022-11-14 16:54:35', NULL, NULL, 3, 1),
	(126, 6, 126, 6, 6, 48, b'1', '6', '2022-11-14 16:54:35', NULL, NULL, 3, 1),
	(127, 6, 127, 6, 6, 49, b'1', '6', '2022-11-14 16:54:35', NULL, NULL, 3, 1);
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
  `user_id` char(20) NOT NULL,
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
  `login_failed_attemps_count` int(10) DEFAULT NULL,
  `forgot_password_validation_failed_attempt` int(10) DEFAULT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_details: ~6 rows (approximately)
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` (`id`, `user_id`, `user_type_id`, `participant_id`, `user_access_type_id`, `user_full_name`, `user_name`, `mobile_number`, `email_id`, `secret_question_id`, `secret_question_answer`, `last_successful_logon`, `login_failed_attemps_count`, `forgot_password_validation_failed_attempt`, `enforce_password_change`, `last_password_change_datetime`, `user_password`, `sensitive_pwd`, `user_status_id`, `sensitive_date`, `login_flag`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `LastModifiedDate`, `approved_by`, `approved_date`, `approval_remarks`, `approval_status_id`, `login_session_id`) VALUES
	(1, '1', 2, 6, 1, 'Admin Maker', 'ICICIAdminMaker', NULL, NULL, NULL, NULL, '2022-03-24 18:50:45', 0, NULL, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, NULL, NULL, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C742041646D696E55736572, 1, NULL),
	(2, '2', 2, 6, 1, 'Admin Checker', 'ICICIAdminChecker', NULL, NULL, NULL, NULL, '2022-03-24 18:50:33', 0, NULL, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, NULL, NULL, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C742041646D696E55736572, 1, NULL),
	(3, '3', 3, 6, 1, 'sachin koti', 'sachinbk', '8971299111', 'sachin@gmail.com', 1, 'red', '2022-11-14 16:54:57', 0, NULL, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '2', '2020-08-15 04:46:59', NULL, NULL, '1', '2020-08-15 04:46:59', NULL, NULL, NULL),
	(4, '4', 3, 6, 1, 'sachin Koti', 'Sachin', '1234567890', 'sachin@gmail.com', 1, 'red', '2022-11-28 15:20:47', 0, NULL, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '1', '2020-09-11 18:57:08', NULL, NULL, '2', '2020-09-11 18:57:08', NULL, NULL, NULL),
	(5, '5', 3, 6, 1, 'Suraj Prabhu', 'Suraj', '1234567890', 'Suraj@gmail.com', 1, 'red', '2022-11-04 20:55:38', 0, NULL, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '4', '2022-11-04 20:55:26', NULL, NULL, '3', '2022-11-04 20:55:26', NULL, NULL, NULL),
	(6, '6', 3, 6, 1, 'Prashant Tayde', 'Prashant23', '8291305922', 'prashanttayde98@gmail.com', 1, 'Black', '2022-11-14 16:55:24', 0, NULL, NULL, NULL, 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 'PlDcr4tPSElQ0FjwSEFQJ7G2LAI4r6du0Pkze+fjU0M=', 1, 1, NULL, b'1', '4', '2022-11-14 16:48:56', NULL, NULL, '3', '2022-11-14 16:48:56', NULL, NULL, NULL);
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_details_temp
CREATE TABLE IF NOT EXISTS `user_details_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` char(20) NOT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_details_temp: ~7 rows (approximately)
/*!40000 ALTER TABLE `user_details_temp` DISABLE KEYS */;
INSERT INTO `user_details_temp` (`id`, `user_id`, `user_type_id`, `participant_id`, `user_access_type_id`, `user_full_name`, `user_name`, `mobile_number`, `email_id`, `secret_question_id`, `secret_question_answer`, `last_successful_logon`, `login_failed_attemps_count`, `forgot_password_validation_failed_attempt`, `enforce_password_change`, `last_password_change_datetime`, `user_password`, `user_status_id`, `sensitive_date`, `login_flag`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `LastModifiedDate`, `approved_by`, `approved_date`, `approval_remarks`, `approval_status_id`, `login_session_id`) VALUES
	(1, '1', 2, 6, 1, 'Admin Maker', 'ICICIAdminMaker', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'g/a6fhIAh3Jw2wcSlfqEq3iH4y3bjLO+uVRMX6qO2aw=', 1, NULL, NULL, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C742041646D696E55736572, 1, NULL),
	(2, '2', 2, 6, 1, 'Admin Checker', 'ICICIAdminChecker', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'g/a6fhIAh3Jw2wcSlfqEq3iH4y3bjLO+uVRMX6qO2aw=', 1, NULL, NULL, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, '0', '2020-07-19 17:58:34', _binary 0x44656661756C742041646D696E55736572, 1, NULL),
	(3, '3', 3, 6, 1, 'sachin koti', 'sachinbk', '8971299111', 'sachin@gmail.com', 1, 'red', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '2', '2020-08-15 04:46:39', NULL, NULL, '1', '2020-08-15 04:46:59', NULL, 1, NULL),
	(4, '4', 3, 6, 1, 'sachin Koti', 'Sachin', '1234567890', 'sachin@gmail.com', 1, 'red', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '1', '2020-09-11 18:56:43', NULL, NULL, '2', '2020-09-11 18:57:08', NULL, 1, NULL),
	(5, '5', 3, 6, 1, 'Suraj Prabhu', 'Suraj', '1234567890', 'Suraj@gmail.com', 1, 'red', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '4', '2022-11-04 20:54:56', NULL, NULL, '3', '2022-11-04 20:55:27', NULL, 1, NULL),
	(6, '6', 3, 6, 1, 'Prashant Tayde', 'Prashant23', '8291305922', 'prashanttayde98@gmail.com', 1, 'Black', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, b'1', '4', '2022-11-14 16:48:04', NULL, NULL, '3', '2022-11-14 16:48:56', NULL, 1, NULL),
	(7, '4', 3, 6, 1, 'sachin Koti', 'Sachin', '1234567890', 'sachin@gmail.com', 1, 'red', NULL, NULL, NULL, NULL, NULL, 'YWhrgE7G8sgldyVe63VSrFYv4/dn6zPH8ktXE4Gp1ak=', 1, 1, NULL, b'1', '4', '2022-11-27 12:38:25', NULL, NULL, NULL, NULL, NULL, 3, NULL);
/*!40000 ALTER TABLE `user_details_temp` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_login_log
CREATE TABLE IF NOT EXISTS `user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `participant_id` int(10) unsigned NOT NULL,
  `user_id` char(20) NOT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `logout_datetime` datetime DEFAULT NULL,
  `client_ip` char(30) DEFAULT NULL,
  `is_successful` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=571 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_login_log: ~570 rows (approximately)
/*!40000 ALTER TABLE `user_login_log` DISABLE KEYS */;
INSERT INTO `user_login_log` (`id`, `participant_id`, `user_id`, `login_datetime`, `logout_datetime`, `client_ip`, `is_successful`) VALUES
	(1, 6, '1', '2020-07-19 17:59:55', NULL, '', '1'),
	(2, 6, '1', '2020-07-19 18:01:22', NULL, '', '1'),
	(3, 6, '1', '2020-07-19 18:02:03', NULL, '', '1'),
	(4, 6, '1', '2020-07-19 18:04:38', NULL, '', '1'),
	(5, 6, '1', '2020-07-19 18:05:24', NULL, '', '1'),
	(6, 6, '1', '2020-07-19 18:48:34', NULL, '', '1'),
	(7, 6, '1', '2020-07-19 18:53:40', NULL, '', '1'),
	(8, 6, '1', '2020-07-19 18:54:42', NULL, '', '1'),
	(9, 6, '1', '2020-07-19 19:21:03', NULL, '', '1'),
	(10, 6, '1', '2020-07-19 19:33:57', NULL, '', '1'),
	(11, 6, '1', '2020-07-19 19:54:22', NULL, '', '1'),
	(12, 6, '1', '2020-07-19 22:39:08', NULL, '', '1'),
	(13, 6, '1', '2020-07-20 00:24:12', NULL, '', '1'),
	(14, 6, '1', '2020-07-20 00:27:40', NULL, '', '1'),
	(15, 6, '1', '2020-07-20 01:13:52', NULL, '', '1'),
	(16, 6, '1', '2020-07-20 01:28:31', NULL, '', '1'),
	(17, 6, '1', '2020-07-20 02:17:21', NULL, '', '1'),
	(18, 6, '1', '2020-08-11 01:55:17', NULL, '', '1'),
	(19, 6, '1', '2020-08-11 01:55:24', NULL, '', '1'),
	(20, 6, '1', '2020-08-11 02:02:02', NULL, '', '1'),
	(21, 6, '1', '2020-08-15 02:47:36', NULL, '', '1'),
	(22, 6, '1', '2020-08-15 02:52:30', NULL, '', '1'),
	(23, 6, '1', '2020-08-15 02:55:55', NULL, '', '1'),
	(24, 6, '1', '2020-08-15 03:07:18', NULL, '', '1'),
	(25, 6, '1', '2020-08-15 04:36:16', NULL, '', '1'),
	(26, 6, '2', '2020-08-15 04:44:16', NULL, '', '1'),
	(27, 6, '1', '2020-08-15 04:46:49', NULL, '', '1'),
	(28, 6, '3', '2020-08-15 04:47:13', NULL, '', '0'),
	(29, 6, '1', '2020-08-15 04:49:14', NULL, '', '1'),
	(30, 6, '3', '2020-08-15 04:49:36', NULL, '', '1'),
	(31, 6, '3', '2020-08-15 05:13:06', NULL, '', '1'),
	(32, 6, '3', '2020-08-15 05:14:52', NULL, '', '1'),
	(33, 6, '3', '2020-08-15 05:19:58', NULL, '', '0'),
	(34, 6, '3', '2020-08-15 05:20:14', NULL, '', '1'),
	(35, 6, '3', '2020-09-11 18:54:34', NULL, '', '1'),
	(36, 6, '1', '2020-09-11 18:56:18', NULL, '', '1'),
	(37, 6, '2', '2020-09-11 18:56:59', NULL, '', '1'),
	(38, 6, '4', '2020-09-11 18:57:18', NULL, '', '1'),
	(39, 6, '4', '2020-09-11 19:27:17', NULL, '', '1'),
	(40, 6, '4', '2020-09-11 19:33:07', NULL, '', '1'),
	(41, 6, '4', '2020-09-11 19:57:18', NULL, '', '1'),
	(42, 6, '4', '2020-09-11 20:09:00', NULL, '', '1'),
	(43, 6, '4', '2021-09-03 16:29:40', NULL, '', '1'),
	(44, 6, '4', '2021-09-04 13:18:02', NULL, '', '1'),
	(45, 6, '4', '2021-09-04 16:02:22', NULL, '', '1'),
	(46, 6, '4', '2021-09-04 16:14:22', NULL, '', '1'),
	(47, 6, '4', '2021-09-04 17:43:05', NULL, '', '1'),
	(48, 6, '4', '2021-09-04 17:55:30', NULL, '', '1'),
	(49, 6, '4', '2021-09-04 18:01:57', NULL, '', '1'),
	(50, 6, '4', '2021-09-04 18:18:41', NULL, '', '1'),
	(51, 6, '4', '2021-09-04 18:35:10', NULL, '', '1'),
	(52, 6, '4', '2021-09-04 18:38:24', NULL, '', '1'),
	(53, 6, '4', '2021-09-04 20:08:54', NULL, '', '1'),
	(54, 6, '4', '2021-09-04 23:52:50', NULL, '', '1'),
	(55, 6, '4', '2021-09-07 00:57:29', NULL, '', '1'),
	(56, 6, '4', '2021-09-07 01:52:31', NULL, '', '1'),
	(57, 6, '4', '2021-09-07 11:26:54', NULL, '', '1'),
	(58, 6, '4', '2021-09-12 11:49:58', NULL, '', '1'),
	(59, 6, '4', '2021-09-12 11:58:37', NULL, '', '1'),
	(60, 6, '4', '2021-09-12 12:29:57', NULL, '', '0'),
	(61, 6, '4', '2021-09-12 12:30:09', NULL, '', '1'),
	(62, 6, '4', '2021-09-12 12:37:34', NULL, '', '1'),
	(63, 6, '4', '2021-09-12 23:44:07', NULL, '', '1'),
	(64, 6, '4', '2021-09-13 01:37:27', NULL, '', '1'),
	(65, 6, '4', '2021-09-13 01:42:42', NULL, '', '1'),
	(66, 6, '4', '2021-09-13 01:48:19', NULL, '', '1'),
	(67, 6, '4', '2021-11-01 17:05:28', NULL, '', '1'),
	(68, 6, '4', '2021-11-22 23:26:26', NULL, '', '1'),
	(69, 6, '4', '2021-11-22 23:32:02', NULL, '', '1'),
	(70, 6, '4', '2021-11-22 23:35:20', NULL, '', '1'),
	(71, 6, '4', '2021-11-22 23:40:33', NULL, '', '1'),
	(72, 6, '4', '2021-11-22 23:47:01', NULL, '', '1'),
	(73, 6, '4', '2021-11-23 00:27:44', NULL, '', '1'),
	(74, 6, '4', '2021-11-23 00:43:52', NULL, '', '1'),
	(75, 6, '4', '2021-11-23 00:48:42', NULL, '', '1'),
	(76, 6, '4', '2021-11-23 10:40:13', NULL, '', '1'),
	(77, 6, '4', '2021-11-23 11:53:38', NULL, '', '1'),
	(78, 6, '4', '2021-11-23 12:12:29', NULL, '', '1'),
	(79, 6, '4', '2021-11-23 13:46:26', NULL, '', '1'),
	(80, 6, '4', '2021-11-23 14:54:34', NULL, '', '1'),
	(81, 6, '4', '2021-11-23 14:59:36', NULL, '', '1'),
	(82, 6, '4', '2021-11-23 15:02:49', NULL, '', '1'),
	(83, 6, '4', '2021-11-23 15:20:01', NULL, '', '1'),
	(84, 6, '4', '2021-11-23 16:50:35', NULL, '', '1'),
	(85, 6, '4', '2021-11-23 17:16:55', NULL, '', '1'),
	(86, 6, '4', '2021-11-23 17:44:57', NULL, '', '1'),
	(87, 6, '4', '2021-11-23 17:59:43', NULL, '', '1'),
	(88, 6, '4', '2021-11-23 18:08:10', NULL, '', '1'),
	(89, 6, '4', '2021-11-23 18:45:19', NULL, '', '1'),
	(90, 6, '4', '2021-11-24 17:02:53', NULL, '', '1'),
	(91, 6, '4', '2021-11-24 17:08:48', NULL, '', '1'),
	(92, 6, '4', '2021-11-24 17:12:14', NULL, '', '0'),
	(93, 6, '4', '2021-11-24 17:12:24', NULL, '', '1'),
	(94, 6, '4', '2021-11-25 01:31:57', NULL, '', '1'),
	(95, 6, '4', '2021-11-25 01:38:21', NULL, '', '1'),
	(96, 6, '4', '2021-11-25 01:43:28', NULL, '', '1'),
	(97, 6, '4', '2021-11-25 02:00:22', NULL, '', '1'),
	(98, 6, '4', '2021-11-25 02:15:02', NULL, '', '1'),
	(99, 6, '4', '2021-11-25 03:28:42', NULL, '', '1'),
	(100, 6, '4', '2022-02-13 18:28:15', NULL, '', '0'),
	(101, 6, '4', '2022-02-13 18:29:01', NULL, '', '1'),
	(102, 6, '4', '2022-02-13 18:38:42', NULL, '', '1'),
	(103, 6, '4', '2022-02-13 19:26:51', NULL, '', '1'),
	(104, 6, '4', '2022-02-13 20:56:24', NULL, '', '1'),
	(105, 6, '4', '2022-02-15 01:42:19', NULL, '', '1'),
	(106, 6, '4', '2022-02-15 02:32:28', NULL, '', '1'),
	(107, 6, '4', '2022-02-15 02:43:26', NULL, '', '1'),
	(108, 6, '4', '2022-02-15 02:58:32', NULL, '', '1'),
	(109, 6, '4', '2022-02-20 19:46:07', NULL, '', '1'),
	(110, 6, '4', '2022-02-20 20:02:34', NULL, '', '1'),
	(111, 6, '4', '2022-02-21 00:35:41', NULL, '', '1'),
	(112, 6, '4', '2022-02-21 02:15:28', NULL, '', '1'),
	(113, 6, '4', '2022-02-21 02:27:42', NULL, '', '1'),
	(114, 6, '4', '2022-02-21 02:32:27', NULL, '', '1'),
	(115, 6, '4', '2022-02-22 00:02:52', NULL, '', '1'),
	(116, 6, '4', '2022-02-23 23:35:03', NULL, '', '1'),
	(117, 6, '4', '2022-02-27 15:52:04', NULL, '', '1'),
	(118, 6, '4', '2022-03-24 17:47:59', NULL, '', '1'),
	(119, 6, '3', '2022-03-24 18:50:14', NULL, '', '1'),
	(120, 6, '2', '2022-03-24 18:50:33', NULL, '', '1'),
	(121, 6, '1', '2022-03-24 18:50:45', NULL, '', '1'),
	(122, 6, '4', '2022-05-22 14:06:29', NULL, '', '1'),
	(123, 6, '4', '2022-10-30 00:17:51', NULL, '', '1'),
	(124, 6, '4', '2022-11-04 20:11:14', NULL, '', '1'),
	(125, 6, '4', '2022-11-04 20:53:27', NULL, '', '1'),
	(126, 6, '3', '2022-11-04 20:55:17', NULL, '', '1'),
	(127, 6, '5', '2022-11-04 20:55:37', NULL, '', '1'),
	(128, 6, '4', '2022-11-07 17:32:16', NULL, '', '1'),
	(129, 6, '4', '2022-11-07 17:45:05', NULL, '', '1'),
	(130, 6, '4', '2022-11-07 17:57:47', NULL, '', '1'),
	(131, 6, '4', '2022-11-07 18:17:42', NULL, '', '1'),
	(132, 6, '4', '2022-11-07 18:21:36', NULL, '', '1'),
	(133, 6, '4', '2022-11-07 18:30:11', NULL, '', '1'),
	(134, 6, '4', '2022-11-07 18:31:14', NULL, '', '1'),
	(135, 6, '4', '2022-11-07 18:55:43', NULL, '', '1'),
	(136, 6, '4', '2022-11-07 18:58:49', NULL, '', '1'),
	(137, 6, '4', '2022-11-07 19:06:42', NULL, '', '1'),
	(138, 6, '4', '2022-11-08 10:51:36', NULL, '', '1'),
	(139, 6, '4', '2022-11-08 10:55:31', NULL, '', '1'),
	(140, 6, '4', '2022-11-08 12:56:35', NULL, '', '1'),
	(141, 6, '4', '2022-11-08 13:14:55', NULL, '', '1'),
	(142, 6, '4', '2022-11-08 14:04:35', NULL, '', '1'),
	(143, 6, '4', '2022-11-08 14:55:59', NULL, '', '1'),
	(144, 6, '4', '2022-11-08 14:57:52', NULL, '', '1'),
	(145, 6, '4', '2022-11-08 14:59:19', NULL, '', '1'),
	(146, 6, '4', '2022-11-08 15:05:47', NULL, '', '1'),
	(147, 6, '4', '2022-11-08 15:14:16', NULL, '', '1'),
	(148, 6, '4', '2022-11-08 15:23:27', NULL, '', '1'),
	(149, 6, '4', '2022-11-08 15:24:24', NULL, '', '1'),
	(150, 6, '4', '2022-11-08 15:26:45', NULL, '', '1'),
	(151, 6, '4', '2022-11-08 15:27:44', NULL, '', '1'),
	(152, 6, '4', '2022-11-08 15:34:02', NULL, '', '1'),
	(153, 6, '4', '2022-11-08 15:39:00', NULL, '', '1'),
	(154, 6, '4', '2022-11-08 15:41:16', NULL, '', '1'),
	(155, 6, '4', '2022-11-08 16:21:18', NULL, '', '1'),
	(156, 6, '4', '2022-11-08 16:26:47', NULL, '', '1'),
	(157, 6, '4', '2022-11-08 22:57:25', NULL, '', '1'),
	(158, 6, '4', '2022-11-08 23:10:33', NULL, '', '1'),
	(159, 6, '4', '2022-11-09 10:49:26', NULL, '', '1'),
	(160, 6, '4', '2022-11-09 10:54:58', NULL, '', '1'),
	(161, 6, '4', '2022-11-09 10:58:41', NULL, '', '1'),
	(162, 6, '4', '2022-11-09 11:00:26', NULL, '', '1'),
	(163, 6, '4', '2022-11-09 11:02:31', NULL, '', '1'),
	(164, 6, '4', '2022-11-09 11:03:22', NULL, '', '1'),
	(165, 6, '4', '2022-11-09 11:31:19', NULL, '', '1'),
	(166, 6, '4', '2022-11-09 11:50:21', NULL, '', '1'),
	(167, 6, '4', '2022-11-09 11:54:25', NULL, '', '1'),
	(168, 6, '4', '2022-11-09 11:57:31', NULL, '', '1'),
	(169, 6, '4', '2022-11-09 12:14:13', NULL, '', '1'),
	(170, 6, '4', '2022-11-09 16:47:45', NULL, '', '1'),
	(171, 6, '4', '2022-11-09 16:49:40', NULL, '', '1'),
	(172, 6, '4', '2022-11-09 16:55:32', NULL, '', '1'),
	(173, 6, '4', '2022-11-09 16:56:43', NULL, '', '1'),
	(174, 6, '4', '2022-11-09 17:00:59', NULL, '', '1'),
	(175, 6, '4', '2022-11-09 17:04:24', NULL, '', '1'),
	(176, 6, '4', '2022-11-09 17:05:50', NULL, '', '1'),
	(177, 6, '4', '2022-11-09 17:11:30', NULL, '', '1'),
	(178, 6, '4', '2022-11-09 17:22:25', NULL, '', '1'),
	(179, 6, '4', '2022-11-09 17:49:14', NULL, '', '1'),
	(180, 6, '4', '2022-11-09 17:51:12', NULL, '', '1'),
	(181, 6, '4', '2022-11-09 17:52:35', NULL, '', '1'),
	(182, 6, '4', '2022-11-09 18:18:24', NULL, '', '1'),
	(183, 6, '4', '2022-11-09 18:21:19', NULL, '', '1'),
	(184, 6, '4', '2022-11-09 19:15:39', NULL, '', '1'),
	(185, 6, '4', '2022-11-09 19:18:22', NULL, '', '1'),
	(186, 6, '4', '2022-11-09 19:19:47', NULL, '', '1'),
	(187, 6, '4', '2022-11-09 19:22:35', NULL, '', '1'),
	(188, 6, '4', '2022-11-09 19:23:06', NULL, '', '1'),
	(189, 6, '4', '2022-11-09 19:24:34', NULL, '', '1'),
	(190, 6, '4', '2022-11-09 19:25:30', NULL, '', '1'),
	(191, 6, '4', '2022-11-10 11:04:23', NULL, '', '1'),
	(192, 6, '4', '2022-11-10 11:23:10', NULL, '', '1'),
	(193, 6, '4', '2022-11-10 11:46:39', NULL, '', '1'),
	(194, 6, '4', '2022-11-10 12:59:40', NULL, '', '1'),
	(195, 6, '4', '2022-11-10 13:10:14', NULL, '', '1'),
	(196, 6, '4', '2022-11-10 13:18:04', NULL, '', '1'),
	(197, 6, '4', '2022-11-10 13:20:59', NULL, '', '1'),
	(198, 6, '4', '2022-11-10 14:25:52', NULL, '', '1'),
	(199, 6, '4', '2022-11-10 14:27:03', NULL, '', '1'),
	(200, 6, '4', '2022-11-10 15:30:47', NULL, '', '1'),
	(201, 6, '4', '2022-11-10 15:31:33', NULL, '', '1'),
	(202, 6, '4', '2022-11-10 15:47:15', NULL, '', '1'),
	(203, 6, '4', '2022-11-10 15:49:48', NULL, '', '1'),
	(204, 6, '4', '2022-11-10 15:54:56', NULL, '', '1'),
	(205, 6, '4', '2022-11-10 15:55:29', NULL, '', '1'),
	(206, 6, '4', '2022-11-10 16:09:40', NULL, '', '1'),
	(207, 6, '4', '2022-11-10 16:36:29', NULL, '', '1'),
	(208, 6, '4', '2022-11-10 16:39:07', NULL, '', '1'),
	(209, 6, '4', '2022-11-10 16:41:14', NULL, '', '1'),
	(210, 6, '4', '2022-11-10 16:46:16', NULL, '', '1'),
	(211, 6, '4', '2022-11-10 16:48:26', NULL, '', '1'),
	(212, 6, '4', '2022-11-10 16:50:14', NULL, '', '1'),
	(213, 6, '4', '2022-11-10 16:52:35', NULL, '', '1'),
	(214, 6, '4', '2022-11-10 16:54:49', NULL, '', '1'),
	(215, 6, '4', '2022-11-10 16:57:09', NULL, '', '1'),
	(216, 6, '4', '2022-11-10 17:13:05', NULL, '', '1'),
	(217, 6, '4', '2022-11-10 17:17:35', NULL, '', '1'),
	(218, 6, '4', '2022-11-10 17:20:11', NULL, '', '1'),
	(219, 6, '4', '2022-11-10 17:26:45', NULL, '', '1'),
	(220, 6, '4', '2022-11-10 17:29:19', NULL, '', '1'),
	(221, 6, '4', '2022-11-10 17:36:01', NULL, '', '1'),
	(222, 6, '4', '2022-11-10 17:40:26', NULL, '', '1'),
	(223, 6, '4', '2022-11-10 17:42:16', NULL, '', '1'),
	(224, 6, '4', '2022-11-10 17:46:22', NULL, '', '1'),
	(225, 6, '4', '2022-11-10 17:54:03', NULL, '', '1'),
	(226, 6, '4', '2022-11-10 17:57:13', NULL, '', '1'),
	(227, 6, '4', '2022-11-10 17:59:27', NULL, '', '1'),
	(228, 6, '4', '2022-11-10 18:04:14', NULL, '', '1'),
	(229, 6, '4', '2022-11-10 18:07:47', NULL, '', '1'),
	(230, 6, '4', '2022-11-10 18:09:22', NULL, '', '1'),
	(231, 6, '4', '2022-11-10 18:10:20', NULL, '', '1'),
	(232, 6, '4', '2022-11-10 18:12:10', NULL, '', '1'),
	(233, 6, '4', '2022-11-10 18:12:52', NULL, '', '1'),
	(234, 6, '4', '2022-11-10 18:15:54', NULL, '', '1'),
	(235, 6, '4', '2022-11-10 18:17:46', NULL, '', '1'),
	(236, 6, '4', '2022-11-10 18:20:56', NULL, '', '1'),
	(237, 6, '4', '2022-11-11 11:35:58', NULL, '', '1'),
	(238, 6, '4', '2022-11-11 12:01:38', NULL, '', '1'),
	(239, 6, '4', '2022-11-11 12:03:18', NULL, '', '1'),
	(240, 6, '4', '2022-11-11 12:08:13', NULL, '', '1'),
	(241, 6, '4', '2022-11-11 12:25:04', NULL, '', '1'),
	(242, 6, '4', '2022-11-11 12:27:59', NULL, '', '1'),
	(243, 6, '4', '2022-11-11 12:31:31', NULL, '', '1'),
	(244, 6, '4', '2022-11-11 12:34:59', NULL, '', '1'),
	(245, 6, '4', '2022-11-11 13:44:23', NULL, '', '1'),
	(246, 6, '4', '2022-11-11 14:16:26', NULL, '', '1'),
	(247, 6, '4', '2022-11-11 14:19:45', NULL, '', '1'),
	(248, 6, '4', '2022-11-11 15:06:29', NULL, '', '1'),
	(249, 6, '4', '2022-11-11 15:07:37', NULL, '', '1'),
	(250, 6, '4', '2022-11-11 15:08:07', NULL, '', '1'),
	(251, 6, '4', '2022-11-11 15:09:10', NULL, '', '1'),
	(252, 6, '4', '2022-11-11 15:11:02', NULL, '', '1'),
	(253, 6, '4', '2022-11-11 15:15:05', NULL, '', '1'),
	(254, 6, '4', '2022-11-11 15:17:04', NULL, '', '1'),
	(255, 6, '4', '2022-11-11 15:17:47', NULL, '', '1'),
	(256, 6, '4', '2022-11-11 15:19:17', NULL, '', '1'),
	(257, 6, '4', '2022-11-11 15:21:46', NULL, '', '1'),
	(258, 6, '4', '2022-11-11 15:24:02', NULL, '', '1'),
	(259, 6, '4', '2022-11-11 15:25:57', NULL, '', '1'),
	(260, 6, '4', '2022-11-11 16:16:57', NULL, '', '1'),
	(261, 6, '4', '2022-11-11 16:36:48', NULL, '', '1'),
	(262, 6, '4', '2022-11-11 17:23:10', NULL, '', '1'),
	(263, 6, '4', '2022-11-11 17:24:39', NULL, '', '1'),
	(264, 6, '4', '2022-11-11 17:48:31', NULL, '', '1'),
	(265, 6, '4', '2022-11-11 18:06:30', NULL, '', '1'),
	(266, 6, '4', '2022-11-11 18:40:18', NULL, '', '1'),
	(267, 6, '4', '2022-11-11 19:48:53', NULL, '', '1'),
	(268, 6, '4', '2022-11-12 16:09:55', NULL, '', '1'),
	(269, 6, '4', '2022-11-12 16:15:04', NULL, '', '1'),
	(270, 6, '4', '2022-11-12 16:16:37', NULL, '', '1'),
	(271, 6, '4', '2022-11-12 16:19:08', NULL, '', '1'),
	(272, 6, '4', '2022-11-12 16:19:58', NULL, '', '1'),
	(273, 6, '4', '2022-11-12 16:22:15', NULL, '', '1'),
	(274, 6, '4', '2022-11-12 16:25:34', NULL, '', '1'),
	(275, 6, '4', '2022-11-12 16:28:50', NULL, '', '1'),
	(276, 6, '4', '2022-11-12 16:34:35', NULL, '', '1'),
	(277, 6, '4', '2022-11-12 16:57:23', NULL, '', '1'),
	(278, 6, '4', '2022-11-12 17:02:53', NULL, '', '1'),
	(279, 6, '4', '2022-11-12 17:30:38', NULL, '', '1'),
	(280, 6, '4', '2022-11-12 17:41:05', NULL, '', '1'),
	(281, 6, '4', '2022-11-12 17:49:32', NULL, '', '1'),
	(282, 6, '4', '2022-11-12 17:54:55', NULL, '', '1'),
	(283, 6, '4', '2022-11-12 18:24:01', NULL, '', '1'),
	(284, 6, '4', '2022-11-12 18:28:28', NULL, '', '1'),
	(285, 6, '4', '2022-11-12 18:39:05', NULL, '', '1'),
	(286, 6, '4', '2022-11-12 19:01:23', NULL, '', '1'),
	(287, 6, '4', '2022-11-12 19:21:32', NULL, '', '1'),
	(288, 6, '4', '2022-11-12 19:37:51', NULL, '', '1'),
	(289, 6, '4', '2022-11-12 19:41:37', NULL, '', '1'),
	(290, 6, '4', '2022-11-13 18:31:35', NULL, '', '1'),
	(291, 6, '4', '2022-11-13 18:35:24', NULL, '', '1'),
	(292, 6, '4', '2022-11-14 10:13:19', NULL, '', '1'),
	(293, 6, '4', '2022-11-14 10:16:13', NULL, '', '1'),
	(294, 6, '4', '2022-11-14 10:22:19', NULL, '', '1'),
	(295, 6, '4', '2022-11-14 10:34:06', NULL, '', '1'),
	(296, 6, '4', '2022-11-14 10:38:37', NULL, '', '1'),
	(297, 6, '4', '2022-11-14 10:43:15', NULL, '', '1'),
	(298, 6, '4', '2022-11-14 11:28:07', NULL, '', '1'),
	(299, 6, '4', '2022-11-14 11:30:00', NULL, '', '1'),
	(300, 6, '4', '2022-11-14 11:31:56', NULL, '', '1'),
	(301, 6, '4', '2022-11-14 11:34:14', NULL, '', '1'),
	(302, 6, '4', '2022-11-14 11:36:59', NULL, '', '1'),
	(303, 6, '4', '2022-11-14 11:50:45', NULL, '', '1'),
	(304, 6, '4', '2022-11-14 11:58:47', NULL, '', '1'),
	(305, 6, '4', '2022-11-14 12:01:55', NULL, '', '1'),
	(306, 6, '4', '2022-11-14 12:03:30', NULL, '', '1'),
	(307, 6, '4', '2022-11-14 12:05:37', NULL, '', '1'),
	(308, 6, '4', '2022-11-14 12:09:21', NULL, '', '1'),
	(309, 6, '4', '2022-11-14 12:15:28', NULL, '', '1'),
	(310, 6, '4', '2022-11-14 12:17:25', NULL, '', '1'),
	(311, 6, '4', '2022-11-14 12:22:16', NULL, '', '1'),
	(312, 6, '4', '2022-11-14 13:08:19', NULL, '', '1'),
	(313, 6, '4', '2022-11-14 13:23:27', NULL, '', '1'),
	(314, 6, '4', '2022-11-14 13:52:42', NULL, '', '1'),
	(315, 6, '4', '2022-11-14 13:59:11', NULL, '', '1'),
	(316, 6, '4', '2022-11-14 14:37:58', NULL, '', '1'),
	(317, 6, '4', '2022-11-14 15:40:40', NULL, '', '1'),
	(318, 6, '4', '2022-11-14 16:36:02', NULL, '', '1'),
	(319, 6, '3', '2022-11-14 16:48:40', NULL, '', '1'),
	(320, 6, '6', '2022-11-14 16:49:37', NULL, '', '1'),
	(321, 6, '4', '2022-11-14 16:50:11', NULL, '', '1'),
	(322, 6, '6', '2022-11-14 16:53:07', NULL, '', '1'),
	(323, 6, '4', '2022-11-14 16:53:23', NULL, '', '1'),
	(324, 6, '3', '2022-11-14 16:53:33', NULL, '', '1'),
	(325, 6, '6', '2022-11-14 16:54:04', NULL, '', '1'),
	(326, 6, '3', '2022-11-14 16:54:57', NULL, '', '1'),
	(327, 6, '6', '2022-11-14 16:55:24', NULL, '', '1'),
	(328, 6, '4', '2022-11-14 16:55:44', NULL, '', '1'),
	(329, 6, '4', '2022-11-15 10:31:06', NULL, '', '1'),
	(330, 6, '4', '2022-11-15 10:44:26', NULL, '', '1'),
	(331, 6, '4', '2022-11-15 11:02:36', NULL, '', '1'),
	(332, 6, '4', '2022-11-15 11:34:48', NULL, '', '1'),
	(333, 6, '4', '2022-11-15 11:37:50', NULL, '', '1'),
	(334, 6, '4', '2022-11-15 11:44:06', NULL, '', '1'),
	(335, 6, '4', '2022-11-15 11:52:57', NULL, '', '1'),
	(336, 6, '4', '2022-11-15 11:54:06', NULL, '', '1'),
	(337, 6, '4', '2022-11-15 11:57:28', NULL, '', '1'),
	(338, 6, '4', '2022-11-15 12:16:41', NULL, '', '1'),
	(339, 6, '4', '2022-11-15 14:31:19', NULL, '', '1'),
	(340, 6, '4', '2022-11-15 15:14:15', NULL, '', '1'),
	(341, 6, '4', '2022-11-15 16:25:36', NULL, '', '1'),
	(342, 6, '4', '2022-11-15 16:27:09', NULL, '', '1'),
	(343, 6, '4', '2022-11-15 17:19:16', NULL, '', '1'),
	(344, 6, '4', '2022-11-15 17:41:39', NULL, '', '1'),
	(345, 6, '4', '2022-11-15 17:44:29', NULL, '', '1'),
	(346, 6, '4', '2022-11-15 17:49:03', NULL, '', '1'),
	(347, 6, '4', '2022-11-15 18:07:59', NULL, '', '1'),
	(348, 6, '4', '2022-11-15 18:25:11', NULL, '', '1'),
	(349, 6, '4', '2022-11-15 18:50:36', NULL, '', '1'),
	(350, 6, '4', '2022-11-15 22:52:51', NULL, '', '1'),
	(351, 6, '4', '2022-11-15 23:27:37', NULL, '', '1'),
	(352, 6, '4', '2022-11-15 23:40:14', NULL, '', '1'),
	(353, 6, '4', '2022-11-16 00:15:43', NULL, '', '1'),
	(354, 6, '4', '2022-11-16 10:45:40', NULL, '', '1'),
	(355, 6, '4', '2022-11-16 11:46:38', NULL, '', '1'),
	(356, 6, '4', '2022-11-16 12:42:04', NULL, '', '1'),
	(357, 6, '4', '2022-11-16 13:25:01', NULL, '', '1'),
	(358, 6, '4', '2022-11-16 13:26:03', NULL, '', '1'),
	(359, 6, '4', '2022-11-16 14:15:12', NULL, '', '1'),
	(360, 6, '4', '2022-11-16 14:19:22', NULL, '', '1'),
	(361, 6, '4', '2022-11-16 15:06:48', NULL, '', '1'),
	(362, 6, '4', '2022-11-16 15:10:04', NULL, '', '1'),
	(363, 6, '4', '2022-11-16 15:11:39', NULL, '', '1'),
	(364, 6, '4', '2022-11-16 17:26:57', NULL, '', '1'),
	(365, 6, '4', '2022-11-16 18:08:03', NULL, '', '1'),
	(366, 6, '4', '2022-11-17 10:37:52', NULL, '', '1'),
	(367, 6, '4', '2022-11-17 10:43:04', NULL, '', '1'),
	(368, 6, '4', '2022-11-17 11:32:08', NULL, '', '1'),
	(369, 6, '4', '2022-11-17 12:07:34', NULL, '', '1'),
	(370, 6, '4', '2022-11-17 12:14:35', NULL, '', '1'),
	(371, 6, '4', '2022-11-17 12:15:18', NULL, '', '1'),
	(372, 6, '4', '2022-11-17 12:22:42', NULL, '', '1'),
	(373, 6, '4', '2022-11-17 12:36:49', NULL, '', '1'),
	(374, 6, '4', '2022-11-17 12:44:36', NULL, '', '1'),
	(375, 6, '4', '2022-11-17 14:30:28', NULL, '', '1'),
	(376, 6, '4', '2022-11-17 14:33:14', NULL, '', '1'),
	(377, 6, '4', '2022-11-17 15:15:18', NULL, '', '1'),
	(378, 6, '4', '2022-11-17 15:16:35', NULL, '', '1'),
	(379, 6, '4', '2022-11-17 15:25:23', NULL, '', '1'),
	(380, 6, '4', '2022-11-17 15:31:05', NULL, '', '1'),
	(381, 6, '4', '2022-11-17 15:52:38', NULL, '', '1'),
	(382, 6, '4', '2022-11-17 16:14:38', NULL, '', '1'),
	(383, 6, '4', '2022-11-17 16:15:05', NULL, '', '1'),
	(384, 6, '4', '2022-11-17 16:52:03', NULL, '', '1'),
	(385, 6, '4', '2022-11-17 16:54:39', NULL, '', '1'),
	(386, 6, '4', '2022-11-17 17:01:58', NULL, '', '1'),
	(387, 6, '4', '2022-11-17 17:20:22', NULL, '', '1'),
	(388, 6, '4', '2022-11-17 18:09:50', NULL, '', '1'),
	(389, 6, '4', '2022-11-17 18:45:04', NULL, '', '1'),
	(390, 6, '4', '2022-11-17 18:49:48', NULL, '', '1'),
	(391, 6, '4', '2022-11-17 19:09:19', NULL, '', '1'),
	(392, 6, '4', '2022-11-17 19:13:48', NULL, '', '1'),
	(393, 6, '4', '2022-11-18 11:16:01', NULL, '', '1'),
	(394, 6, '4', '2022-11-18 11:34:13', NULL, '', '1'),
	(395, 6, '4', '2022-11-18 12:45:37', NULL, '', '1'),
	(396, 6, '4', '2022-11-18 13:11:43', NULL, '', '1'),
	(397, 6, '4', '2022-11-18 13:17:00', NULL, '', '1'),
	(398, 6, '4', '2022-11-18 14:21:35', NULL, '', '1'),
	(399, 6, '4', '2022-11-18 14:46:42', NULL, '', '1'),
	(400, 6, '4', '2022-11-18 15:02:52', NULL, '', '1'),
	(401, 6, '4', '2022-11-18 15:17:40', NULL, '', '1'),
	(402, 6, '4', '2022-11-18 15:50:03', NULL, '', '1'),
	(403, 6, '4', '2022-11-18 15:51:53', NULL, '', '1'),
	(404, 6, '4', '2022-11-18 16:53:06', NULL, '', '1'),
	(405, 6, '4', '2022-11-18 16:57:07', NULL, '', '1'),
	(406, 6, '4', '2022-11-18 17:00:47', NULL, '', '1'),
	(407, 6, '4', '2022-11-18 17:10:05', NULL, '', '1'),
	(408, 6, '4', '2022-11-18 17:14:12', NULL, '', '1'),
	(409, 6, '4', '2022-11-18 17:15:57', NULL, '', '1'),
	(410, 6, '4', '2022-11-18 17:19:24', NULL, '', '1'),
	(411, 6, '4', '2022-11-18 17:20:29', NULL, '', '1'),
	(412, 6, '4', '2022-11-18 17:22:38', NULL, '', '1'),
	(413, 6, '4', '2022-11-18 17:25:20', NULL, '', '1'),
	(414, 6, '4', '2022-11-18 17:36:43', NULL, '', '1'),
	(415, 6, '4', '2022-11-18 17:40:03', NULL, '', '1'),
	(416, 6, '4', '2022-11-18 17:42:03', NULL, '', '1'),
	(417, 6, '4', '2022-11-18 17:42:35', NULL, '', '1'),
	(418, 6, '4', '2022-11-18 17:45:22', NULL, '', '1'),
	(419, 6, '4', '2022-11-18 17:45:42', NULL, '', '1'),
	(420, 6, '4', '2022-11-18 17:46:05', NULL, '', '1'),
	(421, 6, '4', '2022-11-18 17:46:46', NULL, '', '1'),
	(422, 6, '4', '2022-11-18 17:48:26', NULL, '', '1'),
	(423, 6, '4', '2022-11-18 17:49:50', NULL, '', '1'),
	(424, 6, '4', '2022-11-18 17:52:06', NULL, '', '1'),
	(425, 6, '4', '2022-11-18 17:52:54', NULL, '', '1'),
	(426, 6, '4', '2022-11-18 17:53:47', NULL, '', '1'),
	(427, 6, '4', '2022-11-18 18:26:28', NULL, '', '1'),
	(428, 6, '4', '2022-11-18 18:28:34', NULL, '', '1'),
	(429, 6, '4', '2022-11-19 11:01:50', NULL, '', '1'),
	(430, 6, '4', '2022-11-19 14:09:14', NULL, '', '1'),
	(431, 6, '4', '2022-11-19 15:35:29', NULL, '', '1'),
	(432, 6, '4', '2022-11-19 15:46:43', NULL, '', '1'),
	(433, 6, '4', '2022-11-19 16:19:32', NULL, '', '1'),
	(434, 6, '4', '2022-11-19 16:22:41', NULL, '', '1'),
	(435, 6, '4', '2022-11-19 16:25:02', NULL, '', '1'),
	(436, 6, '4', '2022-11-21 10:39:56', NULL, '', '1'),
	(437, 6, '4', '2022-11-21 10:46:32', NULL, '', '1'),
	(438, 6, '4', '2022-11-21 11:11:56', NULL, '', '1'),
	(439, 6, '4', '2022-11-21 11:15:53', NULL, '', '1'),
	(440, 6, '4', '2022-11-21 11:22:10', NULL, '', '1'),
	(441, 6, '4', '2022-11-21 11:29:41', NULL, '', '1'),
	(442, 6, '4', '2022-11-21 12:13:05', NULL, '', '1'),
	(443, 6, '4', '2022-11-21 12:17:18', NULL, '', '1'),
	(444, 6, '4', '2022-11-21 12:58:34', NULL, '', '1'),
	(445, 6, '4', '2022-11-21 13:02:16', NULL, '', '1'),
	(446, 6, '4', '2022-11-21 14:19:37', NULL, '', '1'),
	(447, 6, '4', '2022-11-21 14:32:36', NULL, '', '1'),
	(448, 6, '4', '2022-11-21 14:40:55', NULL, '', '1'),
	(449, 6, '4', '2022-11-21 14:48:22', NULL, '', '1'),
	(450, 6, '4', '2022-11-21 15:35:00', NULL, '', '1'),
	(451, 6, '4', '2022-11-21 15:44:00', NULL, '', '1'),
	(452, 6, '4', '2022-11-21 16:02:52', NULL, '', '1'),
	(453, 6, '4', '2022-11-21 16:04:53', NULL, '', '1'),
	(454, 6, '4', '2022-11-21 16:06:09', NULL, '', '1'),
	(455, 6, '4', '2022-11-21 16:24:02', NULL, '', '1'),
	(456, 6, '4', '2022-11-21 16:25:47', NULL, '', '1'),
	(457, 6, '4', '2022-11-21 16:26:28', NULL, '', '1'),
	(458, 6, '4', '2022-11-21 16:27:24', NULL, '', '1'),
	(459, 6, '4', '2022-11-21 16:29:30', NULL, '', '1'),
	(460, 6, '4', '2022-11-21 16:30:36', NULL, '', '1'),
	(461, 6, '4', '2022-11-21 16:30:57', NULL, '', '1'),
	(462, 6, '4', '2022-11-21 16:32:09', NULL, '', '1'),
	(463, 6, '4', '2022-11-21 16:32:53', NULL, '', '1'),
	(464, 6, '4', '2022-11-21 16:47:51', NULL, '', '1'),
	(465, 6, '4', '2022-11-21 16:51:42', NULL, '', '1'),
	(466, 6, '4', '2022-11-21 16:58:58', NULL, '', '1'),
	(467, 6, '4', '2022-11-21 17:09:21', NULL, '', '1'),
	(468, 6, '4', '2022-11-21 17:14:07', NULL, '', '1'),
	(469, 6, '4', '2022-11-21 17:17:27', NULL, '', '1'),
	(470, 6, '4', '2022-11-21 17:25:16', NULL, '', '1'),
	(471, 6, '4', '2022-11-21 17:36:25', NULL, '', '1'),
	(472, 6, '4', '2022-11-21 17:57:41', NULL, '', '1'),
	(473, 6, '4', '2022-11-21 18:07:54', NULL, '', '1'),
	(474, 6, '4', '2022-11-21 18:46:45', NULL, '', '1'),
	(475, 6, '4', '2022-11-21 18:56:10', NULL, '', '1'),
	(476, 6, '4', '2022-11-22 10:38:32', NULL, '', '1'),
	(477, 6, '4', '2022-11-22 10:41:33', NULL, '', '1'),
	(478, 6, '4', '2022-11-22 11:02:31', NULL, '', '1'),
	(479, 6, '4', '2022-11-22 11:10:54', NULL, '', '1'),
	(480, 6, '4', '2022-11-22 11:11:41', NULL, '', '1'),
	(481, 6, '4', '2022-11-22 11:13:01', NULL, '', '1'),
	(482, 6, '4', '2022-11-22 11:16:05', NULL, '', '1'),
	(483, 6, '4', '2022-11-22 11:21:58', NULL, '', '1'),
	(484, 6, '4', '2022-11-22 11:27:51', NULL, '', '1'),
	(485, 6, '4', '2022-11-22 12:19:50', NULL, '', '1'),
	(486, 6, '4', '2022-11-22 12:21:36', NULL, '', '1'),
	(487, 6, '4', '2022-11-22 12:26:48', NULL, '', '1'),
	(488, 6, '4', '2022-11-22 12:53:10', NULL, '', '1'),
	(489, 6, '4', '2022-11-22 12:55:01', NULL, '', '1'),
	(490, 6, '4', '2022-11-22 12:58:13', NULL, '', '1'),
	(491, 6, '4', '2022-11-22 13:01:56', NULL, '', '1'),
	(492, 6, '4', '2022-11-22 13:18:52', NULL, '', '1'),
	(493, 6, '4', '2022-11-22 13:24:14', NULL, '', '1'),
	(494, 6, '4', '2022-11-22 13:24:48', NULL, '', '1'),
	(495, 6, '4', '2022-11-22 13:26:01', NULL, '', '1'),
	(496, 6, '4', '2022-11-22 14:11:31', NULL, '', '1'),
	(497, 6, '4', '2022-11-22 14:12:37', NULL, '', '1'),
	(498, 6, '4', '2022-11-22 14:13:58', NULL, '', '1'),
	(499, 6, '4', '2022-11-22 14:15:04', NULL, '', '1'),
	(500, 6, '4', '2022-11-22 14:17:14', NULL, '', '1'),
	(501, 6, '4', '2022-11-22 14:18:39', NULL, '', '1'),
	(502, 6, '4', '2022-11-22 14:23:37', NULL, '', '1'),
	(503, 6, '4', '2022-11-22 14:24:56', NULL, '', '1'),
	(504, 6, '4', '2022-11-22 14:27:12', NULL, '', '1'),
	(505, 6, '4', '2022-11-22 14:28:32', NULL, '', '1'),
	(506, 6, '4', '2022-11-22 14:29:20', NULL, '', '1'),
	(507, 6, '4', '2022-11-22 14:29:57', NULL, '', '1'),
	(508, 6, '4', '2022-11-22 14:31:09', NULL, '', '1'),
	(509, 6, '4', '2022-11-22 14:50:44', NULL, '', '1'),
	(510, 6, '4', '2022-11-22 14:51:15', NULL, '', '1'),
	(511, 6, '4', '2022-11-22 14:51:51', NULL, '', '1'),
	(512, 6, '4', '2022-11-22 14:52:15', NULL, '', '1'),
	(513, 6, '4', '2022-11-22 14:52:58', NULL, '', '1'),
	(514, 6, '4', '2022-11-22 14:53:49', NULL, '', '1'),
	(515, 6, '4', '2022-11-22 15:04:14', NULL, '', '1'),
	(516, 6, '4', '2022-11-22 15:17:52', NULL, '', '1'),
	(517, 6, '4', '2022-11-22 15:18:41', NULL, '', '1'),
	(518, 6, '4', '2022-11-22 15:23:47', NULL, '', '1'),
	(519, 6, '4', '2022-11-22 15:27:19', NULL, '', '1'),
	(520, 6, '4', '2022-11-22 15:27:43', NULL, '', '1'),
	(521, 6, '4', '2022-11-22 15:28:51', NULL, '', '1'),
	(522, 6, '4', '2022-11-22 15:32:43', NULL, '', '1'),
	(523, 6, '4', '2022-11-22 15:34:05', NULL, '', '1'),
	(524, 6, '4', '2022-11-22 15:37:19', NULL, '', '1'),
	(525, 6, '4', '2022-11-22 15:39:12', NULL, '', '1'),
	(526, 6, '4', '2022-11-22 15:40:17', NULL, '', '1'),
	(527, 6, '4', '2022-11-22 15:41:30', NULL, '', '1'),
	(528, 6, '4', '2022-11-22 15:42:41', NULL, '', '1'),
	(529, 6, '4', '2022-11-22 15:52:02', NULL, '', '1'),
	(530, 6, '4', '2022-11-22 15:53:12', NULL, '', '1'),
	(531, 6, '4', '2022-11-22 16:06:16', NULL, '', '1'),
	(532, 6, '4', '2022-11-22 16:28:54', NULL, '', '1'),
	(533, 6, '4', '2022-11-22 16:53:53', NULL, '', '1'),
	(534, 6, '4', '2022-11-22 17:08:00', NULL, '', '1'),
	(535, 6, '4', '2022-11-22 17:08:18', NULL, '', '1'),
	(536, 6, '4', '2022-11-22 17:38:09', NULL, '', '1'),
	(537, 6, '4', '2022-11-22 18:11:54', NULL, '', '1'),
	(538, 6, '4', '2022-11-23 11:07:37', NULL, '', '1'),
	(539, 6, '4', '2022-11-23 11:27:02', NULL, '', '1'),
	(540, 6, '4', '2022-11-23 11:27:35', NULL, '', '1'),
	(541, 6, '4', '2022-11-23 11:28:08', NULL, '', '1'),
	(542, 6, '4', '2022-11-23 11:56:26', NULL, '', '1'),
	(543, 6, '4', '2022-11-23 11:56:52', NULL, '', '1'),
	(544, 6, '4', '2022-11-23 12:22:27', NULL, '', '1'),
	(545, 6, '4', '2022-11-23 13:15:53', NULL, '', '1'),
	(546, 6, '4', '2022-11-23 13:30:01', NULL, '', '1'),
	(547, 6, '4', '2022-11-23 14:25:58', NULL, '', '1'),
	(548, 6, '4', '2022-11-23 14:30:08', NULL, '', '1'),
	(549, 6, '4', '2022-11-23 14:58:28', NULL, '', '1'),
	(550, 6, '4', '2022-11-23 15:00:51', NULL, '', '1'),
	(551, 6, '4', '2022-11-23 15:18:44', NULL, '', '1'),
	(552, 6, '4', '2022-11-23 15:29:37', NULL, '', '1'),
	(553, 6, '4', '2022-11-23 15:38:52', NULL, '', '1'),
	(554, 6, '4', '2022-11-23 16:03:16', NULL, '', '1'),
	(555, 6, '4', '2022-11-23 16:05:35', NULL, '', '1'),
	(556, 6, '4', '2022-11-23 16:20:45', NULL, '', '1'),
	(557, 6, '4', '2022-11-23 16:23:08', NULL, '', '1'),
	(558, 6, '4', '2022-11-23 16:24:46', NULL, '', '1'),
	(559, 6, '4', '2022-11-23 16:26:29', NULL, '', '1'),
	(560, 6, '4', '2022-11-23 16:29:43', NULL, '', '1'),
	(561, 6, '4', '2022-11-23 16:38:28', NULL, '', '1'),
	(562, 6, '4', '2022-11-23 16:42:21', NULL, '', '1'),
	(563, 6, '4', '2022-11-23 16:44:43', NULL, '', '1'),
	(564, 6, '4', '2022-11-23 18:17:39', NULL, '', '1'),
	(565, 6, '4', '2022-11-23 18:35:10', NULL, '', '1'),
	(566, 6, '4', '2022-11-27 11:07:36', NULL, '', '1'),
	(567, 6, '4', '2022-11-27 12:32:06', NULL, '', '1'),
	(568, 6, '4', '2022-11-28 13:48:23', NULL, '', '1'),
	(569, 6, '4', '2022-11-28 14:58:00', NULL, '', '1'),
	(570, 6, '4', '2022-11-28 15:20:47', NULL, '', '1');
/*!40000 ALTER TABLE `user_login_log` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_role_mapping
CREATE TABLE IF NOT EXISTS `user_role_mapping` (
  `id` int(10) NOT NULL,
  `user_id` char(20) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `user_status` bit(1) NOT NULL,
  `created_by` char(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_role_mapping: ~6 rows (approximately)
/*!40000 ALTER TABLE `user_role_mapping` DISABLE KEYS */;
INSERT INTO `user_role_mapping` (`id`, `user_id`, `role_id`, `participant_id`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `group_id`) VALUES
	(1, '1', 1, 6, b'0', '0', '2020-07-19 19:19:22', NULL, NULL, 1),
	(2, '2', 1, 6, b'0', '0', '2020-07-19 19:19:22', NULL, NULL, 1),
	(3, '3', 2, 6, b'0', '2', '2020-08-15 04:46:59', NULL, NULL, 2),
	(4, '4', 3, 6, b'0', '1', '2020-09-11 18:57:08', NULL, NULL, 3),
	(5, '5', 1, 6, b'0', '4', '2022-11-04 20:55:26', NULL, NULL, 1),
	(6, '6', 2, 6, b'0', '4', '2022-11-14 16:48:56', NULL, NULL, 2);
/*!40000 ALTER TABLE `user_role_mapping` ENABLE KEYS */;

-- Dumping structure for table traneco_cmsdb.user_role_mapping_temp
CREATE TABLE IF NOT EXISTS `user_role_mapping_temp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_role_id` int(10) NOT NULL,
  `user_id` char(20) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `participant_id` int(10) DEFAULT NULL,
  `user_status` bit(1) NOT NULL DEFAULT b'1',
  `created_by` char(20) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` char(20) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `approval_status_id` int(10) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table traneco_cmsdb.user_role_mapping_temp: ~7 rows (approximately)
/*!40000 ALTER TABLE `user_role_mapping_temp` DISABLE KEYS */;
INSERT INTO `user_role_mapping_temp` (`id`, `user_role_id`, `user_id`, `role_id`, `participant_id`, `user_status`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `approval_status_id`, `group_id`) VALUES
	(1, 0, '1', 1, 6, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(2, 0, '2', 1, 6, b'1', '0', '2020-07-19 17:58:34', NULL, NULL, 1, 1),
	(3, 3, '3', 2, 6, b'1', '2', '2020-08-15 04:46:39', NULL, NULL, 1, 2),
	(4, 4, '4', 3, 6, b'1', '1', '2020-09-11 18:56:43', NULL, NULL, 1, 3),
	(5, 5, '5', 1, 6, b'1', '4', '2022-11-04 20:54:56', NULL, NULL, 1, 1),
	(6, 6, '6', 2, 6, b'1', '4', '2022-11-14 16:48:04', NULL, NULL, 1, 2),
	(7, 7, '4', NULL, 6, b'1', '4', '2022-11-27 12:38:25', NULL, NULL, 3, 1);
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
 IN  P_Action VARCHAR(20),
 IN  P_participantID INT, 
 IN  P_RoleTempId INT,
 IN  P_RoleId INT,
 IN  P_RoleName VARCHAR(100),
 IN  P_Description VARCHAR(200),
 IN  P_MenuIds VARCHAR(500),
 IN  P_Remark VARCHAR(500),
 IN  P_CreatedBy INT,
 IN  P_ApprovedBy INT,
 IN  P_ApprovalStatusID INT, 
 IN  P_GroupID INT, 
 OUT P_Responsecode VARCHAR(2)
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
		CREATE TEMPORARY TABLE temp1(OutputList CHAR(255) );
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
IN  P_Action VARCHAR(20),
IN  P_ParticipantId INT,
IN  P_UserTempID BIGINT,
IN  P_UserID BIGINT,
IN  P_RoleID INT,
IN  P_UserAccessTypeId INT,
IN  P_FirstName VARCHAR(30),
IN  P_LastName VARCHAR(30), 
IN  P_UserName VARCHAR(30),
IN  P_MobileNumber VARCHAR(20),
IN  P_EmailID VARCHAR(254),
IN  P_SecretQuestionID INT,
IN  P_SecretQuestionAnswer  VARCHAR(200),
IN  P_UserPass VARCHAR(255),
IN  P_SensitiveData INT,
IN  P_Remark VARCHAR(500),
IN  P_CreatedBy INT,
IN  P_ApprovedBy INT,
IN  P_ApprovalStatusID INT, 
IN  P_GroupID INT, 
OUT P_Responsecode VARCHAR(2)
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
			
					INSERT INTO `user_details`(User_ID,participant_id,User_Type_Id,User_Access_Type_Id,user_full_name,User_Name,Mobile_Number,Email_ID,Secret_Question_ID,Secret_Question_Answer,User_Password,sensitive_pwd,User_Status_Id,Sensitive_Date,Created_By,
					Approved_By,Approved_Date) SELECT user_id,participant_id,User_Type_Id,User_Access_Type_Id,user_full_name,User_Name,Mobile_Number,Email_ID,
					Secret_Question_ID,Secret_Question_Answer,P_UserPass,P_UserPass,User_Status_Id,Sensitive_Date,Created_By,P_ApprovedBy,NOW() 
					FROM `user_details_temp` WHERE participant_id = P_ParticipantId AND id = P_UserTempID 
					AND User_ID = P_UserID AND Approval_Status_ID = 3;
					
					INSERT INTO user_role_mapping(id,participant_id,Role_ID,User_Id,Created_By,group_id)
					SELECT id,participant_id,Role_ID,User_Id,Created_By ,group_id
					FROM user_role_mapping_temp WHERE participant_id = P_ParticipantId AND id = id AND User_ID = P_UserID AND Approval_Status_ID = 3;
					
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
