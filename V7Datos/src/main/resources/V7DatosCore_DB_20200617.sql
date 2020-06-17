-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.18 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for rezoom
CREATE DATABASE IF NOT EXISTS `rezoom` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rezoom`;

-- Dumping structure for table rezoom.channel_messages
CREATE TABLE IF NOT EXISTS `channel_messages` (
  `messageId` varchar(50) NOT NULL,
  `message` longtext NOT NULL,
  `messageName` varchar(50) NOT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `media` varchar(20) NOT NULL,
  `producerId` varchar(50) NOT NULL,
  `scheduledDate` varchar(50) DEFAULT NULL,
  `expiryDate` varchar(50) DEFAULT NULL,
  `dataMapTemplateName` varchar(100) DEFAULT NULL,
  `nextDeliveryDate` datetime DEFAULT NULL,
  `createdBy` varchar(50) NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedBy` varchar(50) NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `textHTML` bit(1) DEFAULT b'1',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.channel_messages: ~0 rows (approximately)
DELETE FROM `channel_messages`;
/*!40000 ALTER TABLE `channel_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `channel_messages` ENABLE KEYS */;

-- Dumping structure for table rezoom.city
CREATE TABLE IF NOT EXISTS `city` (
  `city` varchar(50) NOT NULL,
  `state` varchar(50) DEFAULT NULL,
  `zipCode` varchar(10) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  PRIMARY KEY (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.city: ~17 rows (approximately)
DELETE FROM `city`;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`city`, `state`, `zipCode`, `status`) VALUES
	('Bangalore', 'Karnataka', NULL, b'1'),
	('Chennai', 'Tamil Nadu', NULL, b'1'),
	('Coimbatore', 'Tamil Nadu', NULL, b'1'),
	('Dharmapuri', 'Tamil Nadu', NULL, b'1'),
	('Erode', 'Tamil Nadu', NULL, b'1'),
	('Hyderabad', 'Andhra Pradesh', NULL, b'1'),
	('Kanyakumari', 'Tamil Nadu', NULL, b'1'),
	('Kodaikanal', 'Tamil Nadu', NULL, b'1'),
	('Madurai', 'Tamil Nadu', NULL, b'1'),
	('Mysore', 'Karnataka', NULL, b'1'),
	('Nammakkal', 'Tamil Nadu', NULL, b'1'),
	('Ooty', 'Tamil Nadu', NULL, b'1'),
	('Salem', 'Tamil Nadu', NULL, b'1'),
	('Theni', 'Tamil Nadu', NULL, b'1'),
	('Thenkasi', 'Tamil Nadu', NULL, b'1'),
	('Tirunelveli', 'Tamil Nadu', NULL, b'1'),
	('Trichy', 'Tamil Nadu', NULL, b'1'),
	('Vizagapatnam', 'Andhra Pradesh', NULL, b'1');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;

-- Dumping structure for table rezoom.country
CREATE TABLE IF NOT EXISTS `country` (
  `country` varchar(20) NOT NULL,
  `countryName` varchar(50) NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'0',
  `displayOrder` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.country: ~474 rows (approximately)
DELETE FROM `country`;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`country`, `countryName`, `status`, `displayOrder`) VALUES
	('Africa/Abidjan', 'Africa/Abidjan', b'0', 0),
	('Africa/Accra', 'Africa/Accra', b'0', 0),
	('Africa/Addis_Ababa', 'Africa/Addis_Ababa', b'0', 0),
	('Africa/Algiers', 'Africa/Algiers', b'0', 0),
	('Africa/Asmara', 'Africa/Asmara', b'0', 0),
	('Africa/Asmera', 'Africa/Asmera', b'0', 0),
	('Africa/Bamako', 'Africa/Bamako', b'0', 0),
	('Africa/Bangui', 'Africa/Bangui', b'0', 0),
	('Africa/Banjul', 'Africa/Banjul', b'0', 0),
	('Africa/Bissau', 'Africa/Bissau', b'0', 0),
	('Africa/Blantyre', 'Africa/Blantyre', b'0', 0),
	('Africa/Brazzaville', 'Africa/Brazzaville', b'0', 0),
	('Africa/Bujumbura', 'Africa/Bujumbura', b'0', 0),
	('Africa/Cairo', 'Africa/Cairo', b'0', 0),
	('Africa/Casablanca', 'Africa/Casablanca', b'0', 0),
	('Africa/Ceuta', 'Africa/Ceuta', b'0', 0),
	('Africa/Conakry', 'Africa/Conakry', b'0', 0),
	('Africa/Dakar', 'Africa/Dakar', b'0', 0),
	('Africa/Dar_es_Salaam', 'Africa/Dar_es_Salaam', b'0', 0),
	('Africa/Djibouti', 'Africa/Djibouti', b'0', 0),
	('Africa/Douala', 'Africa/Douala', b'0', 0),
	('Africa/El_Aaiun', 'Africa/El_Aaiun', b'0', 0),
	('Africa/Freetown', 'Africa/Freetown', b'0', 0),
	('Africa/Gaborone', 'Africa/Gaborone', b'0', 0),
	('Africa/Harare', 'Africa/Harare', b'0', 0),
	('Africa/Johannesburg', 'Africa/Johannesburg', b'0', 0),
	('Africa/Juba', 'Africa/Juba', b'0', 0),
	('Africa/Kampala', 'Africa/Kampala', b'0', 0),
	('Africa/Khartoum', 'Africa/Khartoum', b'0', 0),
	('Africa/Kigali', 'Africa/Kigali', b'0', 0),
	('Africa/Kinshasa', 'Africa/Kinshasa', b'0', 0),
	('Africa/Lagos', 'Africa/Lagos', b'0', 0),
	('Africa/Libreville', 'Africa/Libreville', b'0', 0),
	('Africa/Lome', 'Africa/Lome', b'0', 0),
	('Africa/Luanda', 'Africa/Luanda', b'0', 0),
	('Africa/Lubumbashi', 'Africa/Lubumbashi', b'0', 0),
	('Africa/Lusaka', 'Africa/Lusaka', b'0', 0),
	('Africa/Malabo', 'Africa/Malabo', b'0', 0),
	('Africa/Maputo', 'Africa/Maputo', b'0', 0),
	('Africa/Maseru', 'Africa/Maseru', b'0', 0),
	('Africa/Mbabane', 'Africa/Mbabane', b'0', 0),
	('Africa/Mogadishu', 'Africa/Mogadishu', b'0', 0),
	('Africa/Monrovia', 'Africa/Monrovia', b'0', 0),
	('Africa/Nairobi', 'Africa/Nairobi', b'0', 0),
	('Africa/Ndjamena', 'Africa/Ndjamena', b'0', 0),
	('Africa/Niamey', 'Africa/Niamey', b'0', 0),
	('Africa/Nouakchott', 'Africa/Nouakchott', b'0', 0),
	('Africa/Ouagadougou', 'Africa/Ouagadougou', b'0', 0),
	('Africa/Porto-Novo', 'Africa/Porto-Novo', b'0', 0),
	('Africa/Sao_Tome', 'Africa/Sao_Tome', b'0', 0),
	('Africa/Timbuktu', 'Africa/Timbuktu', b'0', 0),
	('Africa/Tripoli', 'Africa/Tripoli', b'0', 0),
	('Africa/Tunis', 'Africa/Tunis', b'0', 0),
	('Africa/Windhoek', 'Africa/Windhoek', b'0', 0),
	('America/Adak', 'America/Adak', b'0', 0),
	('America/Anchorage', 'America/Anchorage', b'0', 0),
	('America/Anguilla', 'America/Anguilla', b'0', 0),
	('America/Antigua', 'America/Antigua', b'0', 0),
	('America/Araguaina', 'America/Araguaina', b'0', 0),
	('America/Aruba', 'America/Aruba', b'0', 0),
	('America/Asuncion', 'America/Asuncion', b'0', 0),
	('America/Atikokan', 'America/Atikokan', b'0', 0),
	('America/Atka', 'America/Atka', b'0', 0),
	('America/Bahia', 'America/Bahia', b'0', 0),
	('America/Barbados', 'America/Barbados', b'0', 0),
	('America/Belem', 'America/Belem', b'0', 0),
	('America/Belize', 'America/Belize', b'0', 0),
	('America/Blanc-Sablon', 'America/Blanc-Sablon', b'0', 0),
	('America/Boa_Vista', 'America/Boa_Vista', b'0', 0),
	('America/Bogota', 'America/Bogota', b'0', 0),
	('America/Boise', 'America/Boise', b'0', 0),
	('America/Buenos_Aires', 'America/Buenos_Aires', b'0', 0),
	('America/Campo_Grande', 'America/Campo_Grande', b'0', 0),
	('America/Cancun', 'America/Cancun', b'0', 0),
	('America/Caracas', 'America/Caracas', b'0', 0),
	('America/Catamarca', 'America/Catamarca', b'0', 0),
	('America/Cayenne', 'America/Cayenne', b'0', 0),
	('America/Cayman', 'America/Cayman', b'0', 0),
	('America/Chicago', 'America/Chicago', b'0', 0),
	('America/Chihuahua', 'America/Chihuahua', b'0', 0),
	('America/Cordoba', 'America/Cordoba', b'0', 0),
	('America/Costa_Rica', 'America/Costa_Rica', b'0', 0),
	('America/Creston', 'America/Creston', b'0', 0),
	('America/Cuiaba', 'America/Cuiaba', b'0', 0),
	('America/Curacao', 'America/Curacao', b'0', 0),
	('America/Danmarkshavn', 'America/Danmarkshavn', b'0', 0),
	('America/Dawson', 'America/Dawson', b'0', 0),
	('America/Dawson_Creek', 'America/Dawson_Creek', b'0', 0),
	('America/Denver', 'America/Denver', b'0', 0),
	('America/Detroit', 'America/Detroit', b'0', 0),
	('America/Dominica', 'America/Dominica', b'0', 0),
	('America/Edmonton', 'America/Edmonton', b'0', 0),
	('America/Eirunepe', 'America/Eirunepe', b'0', 0),
	('America/El_Salvador', 'America/El_Salvador', b'0', 0),
	('America/Ensenada', 'America/Ensenada', b'0', 0),
	('America/Fortaleza', 'America/Fortaleza', b'0', 0),
	('America/Fort_Nelson', 'America/Fort_Nelson', b'0', 0),
	('America/Fort_Wayne', 'America/Fort_Wayne', b'0', 0),
	('America/Glace_Bay', 'America/Glace_Bay', b'0', 0),
	('America/Godthab', 'America/Godthab', b'0', 0),
	('America/Goose_Bay', 'America/Goose_Bay', b'0', 0),
	('America/Grand_Turk', 'America/Grand_Turk', b'0', 0),
	('America/Grenada', 'America/Grenada', b'0', 0),
	('America/Guadeloupe', 'America/Guadeloupe', b'0', 0),
	('America/Guatemala', 'America/Guatemala', b'0', 0),
	('America/Guayaquil', 'America/Guayaquil', b'0', 0),
	('America/Guyana', 'America/Guyana', b'0', 0),
	('America/Halifax', 'America/Halifax', b'0', 0),
	('America/Havana', 'America/Havana', b'0', 0),
	('America/Hermosillo', 'America/Hermosillo', b'0', 0),
	('America/Indiana/Knox', 'America/Indiana/Knox', b'0', 0),
	('America/Indianapolis', 'America/Indianapolis', b'0', 0),
	('America/Inuvik', 'America/Inuvik', b'0', 0),
	('America/Iqaluit', 'America/Iqaluit', b'0', 0),
	('America/Jamaica', 'America/Jamaica', b'0', 0),
	('America/Jujuy', 'America/Jujuy', b'0', 0),
	('America/Juneau', 'America/Juneau', b'0', 0),
	('America/Knox_IN', 'America/Knox_IN', b'0', 0),
	('America/Kralendijk', 'America/Kralendijk', b'0', 0),
	('America/La_Paz', 'America/La_Paz', b'0', 0),
	('America/Lima', 'America/Lima', b'0', 0),
	('America/Los_Angeles', 'America/Los_Angeles', b'0', 0),
	('America/Louisville', 'America/Louisville', b'0', 0),
	('America/Maceio', 'America/Maceio', b'0', 0),
	('America/Managua', 'America/Managua', b'0', 0),
	('America/Manaus', 'America/Manaus', b'0', 0),
	('America/Marigot', 'America/Marigot', b'0', 0),
	('America/Martinique', 'America/Martinique', b'0', 0),
	('America/Matamoros', 'America/Matamoros', b'0', 0),
	('America/Mazatlan', 'America/Mazatlan', b'0', 0),
	('America/Mendoza', 'America/Mendoza', b'0', 0),
	('America/Menominee', 'America/Menominee', b'0', 0),
	('America/Merida', 'America/Merida', b'0', 0),
	('America/Metlakatla', 'America/Metlakatla', b'0', 0),
	('America/Mexico_City', 'America/Mexico_City', b'0', 0),
	('America/Miquelon', 'America/Miquelon', b'0', 0),
	('America/Moncton', 'America/Moncton', b'0', 0),
	('America/Monterrey', 'America/Monterrey', b'0', 0),
	('America/Montevideo', 'America/Montevideo', b'0', 0),
	('America/Montreal', 'America/Montreal', b'0', 0),
	('America/Montserrat', 'America/Montserrat', b'0', 0),
	('America/Nassau', 'America/Nassau', b'0', 0),
	('America/New_York', 'America/New_York', b'0', 0),
	('America/Nipigon', 'America/Nipigon', b'0', 0),
	('America/Nome', 'America/Nome', b'0', 0),
	('America/Noronha', 'America/Noronha', b'0', 0),
	('America/Ojinaga', 'America/Ojinaga', b'0', 0),
	('America/Panama', 'America/Panama', b'0', 0),
	('America/Pangnirtung', 'America/Pangnirtung', b'0', 0),
	('America/Paramaribo', 'America/Paramaribo', b'0', 0),
	('America/Phoenix', 'America/Phoenix', b'0', 0),
	('America/Porto_Acre', 'America/Porto_Acre', b'0', 0),
	('America/Porto_Velho', 'America/Porto_Velho', b'0', 0),
	('America/Puerto_Rico', 'America/Puerto_Rico', b'0', 0),
	('America/Punta_Arenas', 'America/Punta_Arenas', b'0', 0),
	('America/Rainy_River', 'America/Rainy_River', b'0', 0),
	('America/Rankin_Inlet', 'America/Rankin_Inlet', b'0', 0),
	('America/Recife', 'America/Recife', b'0', 0),
	('America/Regina', 'America/Regina', b'0', 0),
	('America/Resolute', 'America/Resolute', b'0', 0),
	('America/Rio_Branco', 'America/Rio_Branco', b'0', 0),
	('America/Rosario', 'America/Rosario', b'0', 0),
	('America/Santarem', 'America/Santarem', b'0', 0),
	('America/Santa_Isabel', 'America/Santa_Isabel', b'0', 0),
	('America/Santiago', 'America/Santiago', b'0', 0),
	('America/Sao_Paulo', 'America/Sao_Paulo', b'0', 0),
	('America/Scoresbysund', 'America/Scoresbysund', b'0', 0),
	('America/Shiprock', 'America/Shiprock', b'0', 0),
	('America/Sitka', 'America/Sitka', b'0', 0),
	('America/St_Johns', 'America/St_Johns', b'0', 0),
	('America/St_Kitts', 'America/St_Kitts', b'0', 0),
	('America/St_Lucia', 'America/St_Lucia', b'0', 0),
	('America/St_Thomas', 'America/St_Thomas', b'0', 0),
	('America/St_Vincent', 'America/St_Vincent', b'0', 0),
	('America/Tegucigalpa', 'America/Tegucigalpa', b'0', 0),
	('America/Thule', 'America/Thule', b'0', 0),
	('America/Thunder_Bay', 'America/Thunder_Bay', b'0', 0),
	('America/Tijuana', 'America/Tijuana', b'0', 0),
	('America/Toronto', 'America/Toronto', b'0', 0),
	('America/Tortola', 'America/Tortola', b'0', 0),
	('America/Vancouver', 'America/Vancouver', b'0', 0),
	('America/Virgin', 'America/Virgin', b'0', 0),
	('America/Whitehorse', 'America/Whitehorse', b'0', 0),
	('America/Winnipeg', 'America/Winnipeg', b'0', 0),
	('America/Yakutat', 'America/Yakutat', b'0', 0),
	('America/Yellowknife', 'America/Yellowknife', b'0', 0),
	('Antarctica/Casey', 'Antarctica/Casey', b'0', 0),
	('Antarctica/Davis', 'Antarctica/Davis', b'0', 0),
	('Antarctica/Macquarie', 'Antarctica/Macquarie', b'0', 0),
	('Antarctica/Mawson', 'Antarctica/Mawson', b'0', 0),
	('Antarctica/McMurdo', 'Antarctica/McMurdo', b'0', 0),
	('Antarctica/Palmer', 'Antarctica/Palmer', b'0', 0),
	('Antarctica/Rothera', 'Antarctica/Rothera', b'0', 0),
	('Antarctica/Syowa', 'Antarctica/Syowa', b'0', 0),
	('Antarctica/Troll', 'Antarctica/Troll', b'0', 0),
	('Antarctica/Vostok', 'Antarctica/Vostok', b'0', 0),
	('Arctic/Longyearbyen', 'Arctic/Longyearbyen', b'0', 0),
	('Asia/Aden', 'Asia/Aden', b'0', 0),
	('Asia/Almaty', 'Asia/Almaty', b'0', 0),
	('Asia/Amman', 'Asia/Amman', b'0', 0),
	('Asia/Anadyr', 'Asia/Anadyr', b'0', 0),
	('Asia/Aqtau', 'Asia/Aqtau', b'0', 0),
	('Asia/Aqtobe', 'Asia/Aqtobe', b'0', 0),
	('Asia/Ashgabat', 'Asia/Ashgabat', b'0', 0),
	('Asia/Ashkhabad', 'Asia/Ashkhabad', b'0', 0),
	('Asia/Atyrau', 'Asia/Atyrau', b'0', 0),
	('Asia/Baghdad', 'Asia/Baghdad', b'0', 0),
	('Asia/Bahrain', 'Bahrain', b'0', 12),
	('Asia/Baku', 'Asia/Baku', b'0', 0),
	('Asia/Bangkok', 'Thailand', b'0', 15),
	('Asia/Barnaul', 'Asia/Barnaul', b'0', 0),
	('Asia/Beirut', 'Asia/Beirut', b'0', 0),
	('Asia/Bishkek', 'Asia/Bishkek', b'0', 0),
	('Asia/Brunei', 'Asia/Brunei', b'0', 0),
	('Asia/Calcutta', 'Asia/Calcutta', b'0', 0),
	('Asia/Chita', 'Asia/Chita', b'0', 0),
	('Asia/Choibalsan', 'Asia/Choibalsan', b'0', 0),
	('Asia/Chongqing', 'Asia/Chongqing', b'0', 0),
	('Asia/Chungking', 'Asia/Chungking', b'0', 0),
	('Asia/Colombo', 'Sri Lanka', b'0', 3),
	('Asia/Damascus', 'Asia/Damascus', b'0', 0),
	('Asia/Dhaka', 'Bangladesh', b'0', 17),
	('Asia/Dili', 'Asia/Dili', b'0', 0),
	('Asia/Dubai', 'Dubai', b'0', 13),
	('Asia/Dushanbe', 'Asia/Dushanbe', b'0', 0),
	('Asia/Famagusta', 'Asia/Famagusta', b'0', 0),
	('Asia/Gaza', 'Asia/Gaza', b'0', 0),
	('Asia/Harbin', 'Asia/Harbin', b'0', 0),
	('Asia/Hebron', 'Asia/Hebron', b'0', 0),
	('Asia/Hong_Kong', 'Asia/Hong_Kong', b'0', 0),
	('Asia/Hovd', 'Asia/Hovd', b'0', 0),
	('Asia/Ho_Chi_Minh', 'Asia/Ho_Chi_Minh', b'0', 0),
	('Asia/Irkutsk', 'Asia/Irkutsk', b'0', 0),
	('Asia/Istanbul', 'Asia/Istanbul', b'0', 0),
	('Asia/Jakarta', 'Indonesia', b'0', 4),
	('Asia/Jayapura', 'Asia/Jayapura', b'0', 0),
	('Asia/Jerusalem', 'Asia/Jerusalem', b'0', 0),
	('Asia/Kabul', 'Pakistan', b'0', 22),
	('Asia/Kamchatka', 'Asia/Kamchatka', b'0', 0),
	('Asia/Karachi', 'Asia/Karachi', b'0', 0),
	('Asia/Kashgar', 'Asia/Kashgar', b'0', 0),
	('Asia/Kathmandu', 'Nepal', b'0', 23),
	('Asia/Katmandu', 'Asia/Katmandu', b'0', 14),
	('Asia/Khandyga', 'Asia/Khandyga', b'0', 0),
	('Asia/Kolkata', 'India', b'1', 1),
	('Asia/Krasnoyarsk', 'Asia/Krasnoyarsk', b'0', 0),
	('Asia/Kuala_Lumpur', 'Malaysia', b'0', 5),
	('Asia/Kuching', 'Asia/Kuching', b'0', 0),
	('Asia/Kuwait', 'Kuwait', b'0', 19),
	('Asia/Macao', 'Asia/Macao', b'0', 0),
	('Asia/Macau', 'Asia/Macau', b'0', 0),
	('Asia/Magadan', 'Asia/Magadan', b'0', 0),
	('Asia/Makassar', 'Asia/Makassar', b'0', 0),
	('Asia/Manila', 'Phillippines', b'0', 20),
	('Asia/Muscat', 'Oman', b'0', 6),
	('Asia/Nicosia', 'Asia/Nicosia', b'0', 0),
	('Asia/Novokuznetsk', 'Asia/Novokuznetsk', b'0', 0),
	('Asia/Novosibirsk', 'Asia/Novosibirsk', b'0', 0),
	('Asia/Omsk', 'Asia/Omsk', b'0', 0),
	('Asia/Oral', 'Asia/Oral', b'0', 0),
	('Asia/Phnom_Penh', 'Asia/Phnom_Penh', b'0', 0),
	('Asia/Pontianak', 'Asia/Pontianak', b'0', 0),
	('Asia/Pyongyang', 'Asia/Pyongyang', b'0', 0),
	('Asia/Qatar', 'Qatar', b'0', 7),
	('Asia/Qyzylorda', 'Asia/Qyzylorda', b'0', 0),
	('Asia/Rangoon', 'Asia/Rangoon', b'0', 0),
	('Asia/Riyadh', 'Riyadh', b'0', 8),
	('Asia/Saigon', 'Asia/Saigon', b'0', 0),
	('Asia/Sakhalin', 'Asia/Sakhalin', b'0', 0),
	('Asia/Samarkand', 'Asia/Samarkand', b'0', 0),
	('Asia/Seoul', 'Asia/Seoul', b'0', 0),
	('Asia/Shanghai', 'Asia/Shanghai', b'0', 0),
	('Asia/Singapore', 'Singapore', b'0', 2),
	('Asia/Srednekolymsk', 'Asia/Srednekolymsk', b'0', 0),
	('Asia/Taipei', 'Asia/Taipei', b'0', 0),
	('Asia/Tashkent', 'Asia/Tashkent', b'0', 0),
	('Asia/Tbilisi', 'Asia/Tbilisi', b'0', 0),
	('Asia/Tehran', 'Asia/Tehran', b'0', 0),
	('Asia/Tel_Aviv', 'Asia/Tel_Aviv', b'0', 0),
	('Asia/Thimbu', 'Asia/Thimbu', b'0', 0),
	('Asia/Thimphu', 'Asia/Thimphu', b'0', 0),
	('Asia/Tokyo', 'Asia/Tokyo', b'0', 0),
	('Asia/Tomsk', 'Asia/Tomsk', b'0', 0),
	('Asia/Ujung_Pandang', 'Asia/Ujung_Pandang', b'0', 0),
	('Asia/Ulaanbaatar', 'Asia/Ulaanbaatar', b'0', 0),
	('Asia/Ulan_Bator', 'Asia/Ulan_Bator', b'0', 0),
	('Asia/Urumqi', 'Asia/Urumqi', b'0', 0),
	('Asia/Ust-Nera', 'Asia/Ust-Nera', b'0', 0),
	('Asia/Vientiane', 'Asia/Vientiane', b'0', 0),
	('Asia/Vladivostok', 'Asia/Vladivostok', b'0', 0),
	('Asia/Yakutsk', 'Asia/Yakutsk', b'0', 0),
	('Asia/Yangon', 'Asia/Yangon', b'0', 0),
	('Asia/Yekaterinburg', 'Asia/Yekaterinburg', b'0', 0),
	('Asia/Yerevan', 'Asia/Yerevan', b'0', 0),
	('Atlantic/Azores', 'Atlantic/Azores', b'0', 0),
	('Atlantic/Bermuda', 'Atlantic/Bermuda', b'0', 0),
	('Atlantic/Canary', 'Atlantic/Canary', b'0', 0),
	('Atlantic/Cape_Verde', 'Atlantic/Cape_Verde', b'0', 0),
	('Atlantic/Faeroe', 'Atlantic/Faeroe', b'0', 0),
	('Atlantic/Faroe', 'Atlantic/Faroe', b'0', 0),
	('Atlantic/Jan_Mayen', 'Atlantic/Jan_Mayen', b'0', 0),
	('Atlantic/Madeira', 'Atlantic/Madeira', b'0', 0),
	('Atlantic/Reykjavik', 'Atlantic/Reykjavik', b'0', 0),
	('Atlantic/Stanley', 'Atlantic/Stanley', b'0', 0),
	('Atlantic/St_Helena', 'Atlantic/St_Helena', b'0', 0),
	('Australia/ACT', 'Australia/ACT', b'0', 0),
	('Australia/Adelaide', 'Australia/Adelaide', b'0', 0),
	('Australia/Brisbane', 'Australia/Brisbane', b'0', 0),
	('Australia/Canberra', 'Australia/Canberra', b'0', 0),
	('Australia/Currie', 'Australia/Currie', b'0', 0),
	('Australia/Darwin', 'Australia/Darwin', b'0', 0),
	('Australia/Eucla', 'Australia/Eucla', b'0', 0),
	('Australia/Hobart', 'Australia/Hobart', b'0', 0),
	('Australia/LHI', 'Australia/LHI', b'0', 0),
	('Australia/Lindeman', 'Australia/Lindeman', b'0', 0),
	('Australia/Lord_Howe', 'Australia/Lord_Howe', b'0', 0),
	('Australia/Melbourne', 'Australia/Melbourne', b'0', 0),
	('Australia/North', 'Australia/North', b'0', 0),
	('Australia/NSW', 'Australia/NSW', b'0', 0),
	('Australia/Perth', 'Australia/Perth', b'0', 0),
	('Australia/Queensland', 'Australia/Queensland', b'0', 0),
	('Australia/South', 'Australia/South', b'0', 0),
	('Australia/Sydney', 'Australia/Sydney', b'0', 0),
	('Australia/Tasmania', 'Australia/Tasmania', b'0', 0),
	('Australia/Victoria', 'Australia/Victoria', b'0', 0),
	('Australia/West', 'Australia/West', b'0', 0),
	('Australia/Yancowinna', 'Australia/Yancowinna', b'0', 0),
	('Brazil/Acre', 'Brazil/Acre', b'0', 0),
	('Brazil/DeNoronha', 'Brazil/DeNoronha', b'0', 0),
	('Brazil/East', 'Brazil/East', b'0', 0),
	('Brazil/West', 'Brazil/West', b'0', 0),
	('Canada/Atlantic', 'Canada/Atlantic', b'0', 0),
	('Canada/Central', 'Canada/Central', b'0', 0),
	('Canada/Eastern', 'Canada/Eastern', b'0', 0),
	('Canada/Mountain', 'Canada/Mountain', b'0', 0),
	('Canada/Newfoundland', 'Canada/Newfoundland', b'0', 0),
	('Canada/Pacific', 'Canada/Pacific', b'0', 0),
	('Canada/Saskatchewan', 'Canada/Saskatchewan', b'0', 0),
	('Canada/Yukon', 'Canada/Yukon', b'0', 0),
	('Chile/Continental', 'Chile/Continental', b'0', 0),
	('Chile/EasterIsland', 'Chile/EasterIsland', b'0', 0),
	('Europe/Amsterdam', 'Europe/Amsterdam', b'0', 0),
	('Europe/Andorra', 'Europe/Andorra', b'0', 0),
	('Europe/Astrakhan', 'Europe/Astrakhan', b'0', 0),
	('Europe/Athens', 'Europe/Athens', b'0', 0),
	('Europe/Belfast', 'Europe/Belfast', b'0', 0),
	('Europe/Belgrade', 'Europe/Belgrade', b'0', 0),
	('Europe/Berlin', 'Europe/Berlin', b'0', 0),
	('Europe/Bratislava', 'Europe/Bratislava', b'0', 0),
	('Europe/Brussels', 'Europe/Brussels', b'0', 0),
	('Europe/Bucharest', 'Europe/Bucharest', b'0', 0),
	('Europe/Budapest', 'Europe/Budapest', b'0', 0),
	('Europe/Busingen', 'Europe/Busingen', b'0', 0),
	('Europe/Chisinau', 'Europe/Chisinau', b'0', 0),
	('Europe/Copenhagen', 'Europe/Copenhagen', b'0', 0),
	('Europe/Dublin', 'Europe/Dublin', b'0', 0),
	('Europe/Gibraltar', 'Europe/Gibraltar', b'0', 0),
	('Europe/Guernsey', 'Europe/Guernsey', b'0', 0),
	('Europe/Helsinki', 'Europe/Helsinki', b'0', 0),
	('Europe/Isle_of_Man', 'Europe/Isle_of_Man', b'0', 0),
	('Europe/Istanbul', 'Europe/Istanbul', b'0', 0),
	('Europe/Jersey', 'Europe/Jersey', b'0', 0),
	('Europe/Kaliningrad', 'Europe/Kaliningrad', b'0', 0),
	('Europe/Kiev', 'Europe/Kiev', b'0', 0),
	('Europe/Kirov', 'Europe/Kirov', b'0', 0),
	('Europe/Lisbon', 'Europe/Lisbon', b'0', 0),
	('Europe/Ljubljana', 'Europe/Ljubljana', b'0', 0),
	('Europe/London', 'United Kingdom', b'0', 9),
	('Europe/Luxembourg', 'Europe/Luxembourg', b'0', 0),
	('Europe/Madrid', 'Europe/Madrid', b'0', 0),
	('Europe/Malta', 'Europe/Malta', b'0', 0),
	('Europe/Mariehamn', 'Europe/Mariehamn', b'0', 0),
	('Europe/Minsk', 'Europe/Minsk', b'0', 0),
	('Europe/Monaco', 'Europe/Monaco', b'0', 0),
	('Europe/Moscow', 'Europe/Moscow', b'0', 0),
	('Europe/Nicosia', 'Europe/Nicosia', b'0', 0),
	('Europe/Oslo', 'Europe/Oslo', b'0', 0),
	('Europe/Paris', 'Europe/Paris', b'0', 0),
	('Europe/Podgorica', 'Europe/Podgorica', b'0', 0),
	('Europe/Prague', 'Europe/Prague', b'0', 0),
	('Europe/Riga', 'Europe/Riga', b'0', 0),
	('Europe/Rome', 'Europe/Rome', b'0', 0),
	('Europe/Samara', 'Europe/Samara', b'0', 0),
	('Europe/San_Marino', 'Europe/San_Marino', b'0', 0),
	('Europe/Sarajevo', 'Europe/Sarajevo', b'0', 0),
	('Europe/Saratov', 'Europe/Saratov', b'0', 0),
	('Europe/Simferopol', 'Europe/Simferopol', b'0', 0),
	('Europe/Skopje', 'Europe/Skopje', b'0', 0),
	('Europe/Sofia', 'Europe/Sofia', b'0', 0),
	('Europe/Stockholm', 'Europe/Stockholm', b'0', 0),
	('Europe/Tallinn', 'Europe/Tallinn', b'0', 0),
	('Europe/Tirane', 'Europe/Tirane', b'0', 0),
	('Europe/Tiraspol', 'Europe/Tiraspol', b'0', 0),
	('Europe/Ulyanovsk', 'Europe/Ulyanovsk', b'0', 0),
	('Europe/Uzhgorod', 'Europe/Uzhgorod', b'0', 0),
	('Europe/Vaduz', 'Europe/Vaduz', b'0', 0),
	('Europe/Vatican', 'Europe/Vatican', b'0', 0),
	('Europe/Vienna', 'Europe/Vienna', b'0', 0),
	('Europe/Vilnius', 'Europe/Vilnius', b'0', 0),
	('Europe/Volgograd', 'Europe/Volgograd', b'0', 0),
	('Europe/Warsaw', 'Europe/Warsaw', b'0', 0),
	('Europe/Zagreb', 'Europe/Zagreb', b'0', 0),
	('Europe/Zaporozhye', 'Europe/Zaporozhye', b'0', 0),
	('Europe/Zurich', 'Europe/Zurich', b'0', 0),
	('Indian/Antananarivo', 'Indian/Antananarivo', b'0', 0),
	('Indian/Chagos', 'Indian/Chagos', b'0', 0),
	('Indian/Christmas', 'Indian/Christmas', b'0', 0),
	('Indian/Cocos', 'Indian/Cocos', b'0', 0),
	('Indian/Comoro', 'Indian/Comoro', b'0', 0),
	('Indian/Kerguelen', 'Indian/Kerguelen', b'0', 0),
	('Indian/Mahe', 'Mahe', b'0', 10),
	('Indian/Maldives', 'Maldives', b'0', 11),
	('Indian/Mauritius', 'Mauritius', b'0', 21),
	('Indian/Mayotte', 'Indian/Mayotte', b'0', 0),
	('Indian/Reunion', 'Indian/Reunion', b'0', 0),
	('Mexico/BajaNorte', 'Mexico/BajaNorte', b'0', 0),
	('Mexico/BajaSur', 'Mexico/BajaSur', b'0', 0),
	('Mexico/General', 'Mexico/General', b'0', 0),
	('Pacific/Apia', 'Pacific/Apia', b'0', 0),
	('Pacific/Auckland', 'Pacific/Auckland', b'0', 0),
	('Pacific/Bougainville', 'Pacific/Bougainville', b'0', 0),
	('Pacific/Chatham', 'Pacific/Chatham', b'0', 0),
	('Pacific/Chuuk', 'Pacific/Chuuk', b'0', 0),
	('Pacific/Easter', 'Pacific/Easter', b'0', 0),
	('Pacific/Efate', 'Pacific/Efate', b'0', 0),
	('Pacific/Enderbury', 'Pacific/Enderbury', b'0', 0),
	('Pacific/Fakaofo', 'Pacific/Fakaofo', b'0', 0),
	('Pacific/Fiji', 'Pacific/Fiji', b'0', 0),
	('Pacific/Funafuti', 'Pacific/Funafuti', b'0', 0),
	('Pacific/Galapagos', 'Pacific/Galapagos', b'0', 0),
	('Pacific/Gambier', 'Pacific/Gambier', b'0', 0),
	('Pacific/Guadalcanal', 'Pacific/Guadalcanal', b'0', 0),
	('Pacific/Guam', 'Pacific/Guam', b'0', 0),
	('Pacific/Honolulu', 'Pacific/Honolulu', b'0', 0),
	('Pacific/Johnston', 'Pacific/Johnston', b'0', 0),
	('Pacific/Kiritimati', 'Pacific/Kiritimati', b'0', 0),
	('Pacific/Kosrae', 'Pacific/Kosrae', b'0', 0),
	('Pacific/Kwajalein', 'Pacific/Kwajalein', b'0', 0),
	('Pacific/Majuro', 'Pacific/Majuro', b'0', 0),
	('Pacific/Marquesas', 'Pacific/Marquesas', b'0', 0),
	('Pacific/Midway', 'Pacific/Midway', b'0', 0),
	('Pacific/Nauru', 'Pacific/Nauru', b'0', 0),
	('Pacific/Niue', 'Pacific/Niue', b'0', 0),
	('Pacific/Norfolk', 'Pacific/Norfolk', b'0', 0),
	('Pacific/Noumea', 'Pacific/Noumea', b'0', 0),
	('Pacific/Pago_Pago', 'Pacific/Pago_Pago', b'0', 0),
	('Pacific/Palau', 'Pacific/Palau', b'0', 0),
	('Pacific/Pitcairn', 'Pacific/Pitcairn', b'0', 0),
	('Pacific/Pohnpei', 'Pacific/Pohnpei', b'0', 0),
	('Pacific/Ponape', 'Pacific/Ponape', b'0', 0),
	('Pacific/Port_Moresby', 'Pacific/Port_Moresby', b'0', 0),
	('Pacific/Rarotonga', 'Pacific/Rarotonga', b'0', 0),
	('Pacific/Saipan', 'Pacific/Saipan', b'0', 0),
	('Pacific/Samoa', 'Pacific/Samoa', b'0', 0),
	('Pacific/Tahiti', 'Pacific/Tahiti', b'0', 0),
	('Pacific/Tarawa', 'Pacific/Tarawa', b'0', 0),
	('Pacific/Tongatapu', 'Pacific/Tongatapu', b'0', 0),
	('Pacific/Truk', 'Pacific/Truk', b'0', 0),
	('Pacific/Wake', 'Pacific/Wake', b'0', 0),
	('Pacific/Wallis', 'Pacific/Wallis', b'0', 0),
	('Pacific/Yap', 'Pacific/Yap', b'0', 0),
	('US/Alaska', 'US/Alaska', b'0', 0),
	('US/Aleutian', 'US/Aleutian', b'0', 0),
	('US/Arizona', 'US/Arizona', b'0', 0),
	('US/Central', 'US/Central', b'0', 0),
	('US/East-Indiana', 'US/East-Indiana', b'0', 0),
	('US/Eastern', 'US/Eastern', b'0', 0),
	('US/Hawaii', 'US/Hawaii', b'0', 0),
	('US/Indiana-Starke', 'US/Indiana-Starke', b'0', 0),
	('US/Michigan', 'US/Michigan', b'0', 0),
	('US/Mountain', 'US/Mountain', b'0', 0),
	('US/Pacific', 'US/Pacific', b'0', 0),
	('US/Pacific-New', 'US/Pacific-New', b'0', 0),
	('US/Samoa', 'US/Samoa', b'0', 0);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;

-- Dumping structure for table rezoom.menu
CREATE TABLE IF NOT EXISTS `menu` (
  `menuId` varchar(10) NOT NULL,
  `parentId` varchar(10) DEFAULT NULL,
  `menuName` varchar(50) DEFAULT NULL,
  `cssClassIcon` varchar(100) DEFAULT NULL,
  `actionURL` varchar(200) DEFAULT NULL,
  `cssClass` varchar(100) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `haveSubMenu` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.menu: ~16 rows (approximately)
DELETE FROM `menu`;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`menuId`, `parentId`, `menuName`, `cssClassIcon`, `actionURL`, `cssClass`, `level`, `haveSubMenu`) VALUES
	('M0001', 'M0001\r\n', 'Dashboard\r\n', 'icon-home\r\n', 'dc7161be3dbf2250c8954e560cc35060?q=', 'title\r\n', 'A', b'0'),
	('M0002', 'M0002\r\n', 'Settings\r\n', 'icon-settings\r\n', 'javascript:;', 'title\r\n', 'B', b'1'),
	('M0003', 'M0002\r\n', 'Generate Key\r\n', 'icon-tag', 'javascript:loadContent(\'a9dc3f7729b51d26dc20af48c03dfee6\', \'\',\'false\');', 'title\r\n', 'BA', b'0'),
	('M0005', 'M0005\r\n', 'Search\r\n', 'icon-pencil\r\n', 'javascript:;', 'title\r\n', 'C', b'1'),
	('M0007', 'M0005\r\n', 'Student Search\r\n', 'icon-bar-chart\r\n', 'javascript:loadContent(\'bcca2120ed2c00bcf732c74ca8026490/Consumer\', \'\',\'false\');', 'title\r\n', 'CA', b'0'),
	('M0007_1', 'M0005\r\n', 'Employee Search\r\n', 'icon-bar-chart\r\n', 'javascript:loadContent(\'bcca2120ed2c00bcf732c74ca8026490/Employee\', \'\',\'false\');', 'title\r\n', 'CB', b'0'),
	('M0008', 'M0005\r\n', 'RSearch', 'icon-bar-chart\r\n', 'javascript:loadContent(\'bcca2120ed2c00bcf732c74ca8026490/Producer\', \'\',\'false\');', 'title\r\n', 'CC', b'0'),
	('M00081', 'M0005\r\n', 'Organization Search', 'icon-bar-chart\r\n', 'javascript:loadContent(\'ba6443f8aa1c6e707ccb0cd4f03aa1e9\', \'\',\'false\');', 'title\r\n', 'CD', b'0'),
	('M00082', 'M0005\r\n', 'Question Search', 'fa fa-file-text', 'javascript:loadContent(\'d0f9600d5f8d0b22a87adf6d58b32059/Question\', \'\',\'false\');', 'title\r\n', 'CE', b'0'),
	('M00083', 'M0005\r\n', 'Online Search', 'fa fa-file-text', 'javascript:loadContent(\'d0f9600d5f8d0b22a87adf6d58b32059/Online\', \'\',\'false\');', 'title\r\n', 'CG', b'0'),
	('M0009', 'M0009', 'Uploads', 'icon-bar-chart', 'javascript:;', 'title\r\n', 'D', b'1'),
	('M0010', 'M0009', 'E-Upload', 'icon-tag', 'javascript:loadContent(\'a500fe2139d875b2f6d9679600577191\', \'\',\'false\');', 'title\r\n', 'DA', b'0'),
	('M0011', 'M0009', 'E-Books', 'icon-tag', 'javascript:loadContent(\'d1ebf572dba98851c34e281efec5de26/EBooks/eupload-view\', \'\',\'false\');', 'title\r\n', 'DB', b'0'),
	('M0012', 'M0009', 'E-KeyPoints', 'icon-tag', 'javascript:loadContent(\'d1ebf572dba98851c34e281efec5de26/EKeyPoints/eupload-view\', \'\',\'false\');', 'title\r\n', 'DC', b'0'),
	('M0013', 'M0009', 'E-BluePrint', 'icon-tag', 'javascript:loadContent(\'d1ebf572dba98851c34e281efec5de26/EBluePrint/eupload-view\', \'\',\'false\');', 'title\r\n', 'DC', b'0'),
	('M0016', 'M0002\r\n', 'Information Alerts', 'icon-bar-chart\r\n', 'javascript:loadContent(\'f44c136f4bd6db98e4bec455066422c5\', \'\',\'false\');', 'title\r\n', 'BC', b'0');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;

-- Dumping structure for table rezoom.menurole
CREATE TABLE IF NOT EXISTS `menurole` (
  `autoId` int(11) NOT NULL AUTO_INCREMENT,
  `menuId` varchar(50) NOT NULL,
  `producerId` varchar(50) NOT NULL DEFAULT 'PRD000001',
  `roleId` varchar(50) NOT NULL,
  PRIMARY KEY (`autoId`),
  KEY `FK_mamenurole_mamenu` (`menuId`),
  KEY `FK_mamenurole_producers` (`producerId`),
  KEY `FK_mamenurole_roles` (`roleId`),
  CONSTRAINT `FK_mamenurole_producers` FOREIGN KEY (`producerId`) REFERENCES `producers` (`producerId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.menurole: ~0 rows (approximately)
DELETE FROM `menurole`;
/*!40000 ALTER TABLE `menurole` DISABLE KEYS */;
INSERT INTO `menurole` (`autoId`, `menuId`, `producerId`, `roleId`) VALUES
	(1, 'M0001', 'PRD000001', 'Admin');
/*!40000 ALTER TABLE `menurole` ENABLE KEYS */;

-- Dumping structure for table rezoom.messages
CREATE TABLE IF NOT EXISTS `messages` (
  `messageId` varchar(50) NOT NULL,
  `message` longtext NOT NULL,
  `messageName` varchar(50) NOT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `media` varchar(20) NOT NULL,
  `producerId` varchar(50) NOT NULL,
  `dataMapTemplateName` varchar(100) DEFAULT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `textHTML` bit(1) DEFAULT b'1',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.messages: ~2 rows (approximately)
DELETE FROM `messages`;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` (`messageId`, `message`, `messageName`, `subject`, `media`, `producerId`, `dataMapTemplateName`, `createdDate`, `modifiedDate`, `status`, `textHTML`) VALUES
	('Create_User_Admin', 'Hi ${user_producerName} <BR> <BR>  User ${user_userName} from ${user_country_countryName} has been created. Pls verify the user. ', 'Create_User_Admin', 'Hi ${user_producerName} User Created Info Admin ', 'Email', 'PRD000001', NULL, '2020-01-01 00:00:00', '2020-01-01 00:00:00', b'1', b'1'),
	('Create_User_Employee', 'Rezoom: Your Account has been created successfully', 'Create_User_Employee', 'User Created Info Employee', 'Email', 'PRD000001', NULL, '2020-01-01 00:00:00', '2020-01-01 00:00:00', b'1', b'1'),
	('Create_User_Employee_SMS', 'Rezoom: Your Account has been created successfully', 'Create_User_Employee_SMS', 'User Created Info Employee SMS', 'SMS', 'PRD000001', NULL, '2020-01-01 00:00:00', '2020-01-01 00:00:00', b'1', b'1');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;

-- Dumping structure for table rezoom.portlets
CREATE TABLE IF NOT EXISTS `portlets` (
  `portletId` varchar(50) NOT NULL,
  `portletName` varchar(50) NOT NULL,
  `portletBeanName` varchar(50) NOT NULL,
  `portletTemplatePath` varchar(100) NOT NULL,
  `createdBy` varchar(50) NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  PRIMARY KEY (`portletId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.portlets: ~9 rows (approximately)
DELETE FROM `portlets`;
/*!40000 ALTER TABLE `portlets` DISABLE KEYS */;
INSERT INTO `portlets` (`portletId`, `portletName`, `portletBeanName`, `portletTemplatePath`, `createdBy`, `createdDate`, `modifiedBy`, `modifiedDate`, `status`) VALUES
	('PRT000001', 'Login Logout Information', 'org.hbs.sg.portlet.executors.LoginLogoutPortlet', '../../jsp/content/portlets/login-pt.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000002', 'Activity Information', 'org.hbs.sg.portlet.executors.ActivityPortlet', '../../jsp/content/portlets/login-pt.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000003', 'Assessment Information', 'org.hbs.sg.portlet.executors.AssessmentPortlet', '../../jsp/content/portlets/assessment-portlet.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000004', 'Calendar Information', 'org.hbs.sg.portlet.executors.CalendarPortlet', '../../jsp/content/portlets/login-pt.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000005', 'Discussion Information', 'org.hbs.sg.portlet.executors.DiscussionPortlet', '../../jsp/content/portlets/login-pt.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000006', 'Notifications Information', 'org.hbs.sg.portlet.executors.NotificationsPortlet', '../../jsp/content/portlets/login-pt.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000007', 'Practice Information', 'org.hbs.sg.portlet.executors.PracticeExamPortlet', '../../jsp/content/portlets/practice-portlet.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000008', 'Report Card Information', 'org.hbs.sg.portlet.executors.ReportCardPortlet', '../../jsp/content/portlets/login-pt.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1'),
	('PRT000009', 'Users Information', 'org.hbs.sg.portlet.executors.UsersPortlet', '../../jsp/content/portlets/login-pt.jsp', 'PRDADM0001', '2018-10-07 20:18:28', 'PRDADM0001', '2018-10-07 20:18:28', b'1');
/*!40000 ALTER TABLE `portlets` ENABLE KEYS */;

-- Dumping structure for table rezoom.portletsroles
CREATE TABLE IF NOT EXISTS `portletsroles` (
  `prAutoId` int(10) NOT NULL AUTO_INCREMENT,
  `portletId` varchar(50) NOT NULL,
  `roleId` varchar(50) NOT NULL,
  `status` bit(1) DEFAULT b'1',
  `displayOrder` int(11) NOT NULL,
  PRIMARY KEY (`prAutoId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.portletsroles: ~0 rows (approximately)
DELETE FROM `portletsroles`;
/*!40000 ALTER TABLE `portletsroles` DISABLE KEYS */;
INSERT INTO `portletsroles` (`prAutoId`, `portletId`, `roleId`, `status`, `displayOrder`) VALUES
	(1, 'PRT000001', 'Admin', b'1', 1);
/*!40000 ALTER TABLE `portletsroles` ENABLE KEYS */;

-- Dumping structure for table rezoom.portletsusers
CREATE TABLE IF NOT EXISTS `portletsusers` (
  `ptAutoId` bigint(11) NOT NULL AUTO_INCREMENT,
  `displayOrder` int(11) NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'0',
  `employeeId` varchar(50) DEFAULT NULL,
  `portletId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ptAutoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.portletsusers: ~0 rows (approximately)
DELETE FROM `portletsusers`;
/*!40000 ALTER TABLE `portletsusers` DISABLE KEYS */;
INSERT INTO `portletsusers` (`ptAutoId`, `displayOrder`, `status`, `employeeId`, `portletId`) VALUES
	(1, 1, b'1', 'USR1513438024799', 'PRT000001');
/*!40000 ALTER TABLE `portletsusers` ENABLE KEYS */;

-- Dumping structure for table rezoom.producers
CREATE TABLE IF NOT EXISTS `producers` (
  `producerId` varchar(50) NOT NULL,
  `producerType` varchar(50) NOT NULL DEFAULT 'Producer',
  `producerName` varchar(50) DEFAULT NULL,
  `pwdExpiryDays` datetime DEFAULT NULL,
  `domainContext` varchar(100) DEFAULT NULL,
  `employeeId` varchar(50) DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'0',
  `primary` bit(1) DEFAULT b'0',
  `emailId` varchar(50) DEFAULT NULL,
  `mobileNo` varchar(15) DEFAULT NULL,
  `phoneNo` varchar(20) DEFAULT NULL,
  `whatsAppNo` varchar(15) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `registrationDate` varchar(20) DEFAULT NULL,
  `customerStatus` varchar(25) DEFAULT 'Pending',
  PRIMARY KEY (`producerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.producers: ~7 rows (approximately)
DELETE FROM `producers`;
/*!40000 ALTER TABLE `producers` DISABLE KEYS */;
INSERT INTO `producers` (`producerId`, `producerType`, `producerName`, `pwdExpiryDays`, `domainContext`, `employeeId`, `createdBy`, `createdDate`, `modifiedDate`, `modifiedBy`, `status`, `primary`, `emailId`, `mobileNo`, `phoneNo`, `whatsAppNo`, `description`, `registrationDate`, `customerStatus`) VALUES
	('PRD000001', 'Producer', 'Rezoom Admin', '2017-08-02 07:33:06', '/rezoom', 'PRDADM0001', 'PRDADM0001', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'PRDADM0001', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, 'Pending'),
	('PRD000002', 'Producer', 'Rezoom Tambaram', '2017-08-02 07:33:06', '/rezoom', 'PRDADM0001', 'PRDADM0001', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'PRDADM0001', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, 'Pending'),
	('PRD000003', 'Producer', 'Rezoom Medavakkam', '2017-08-02 07:33:06', '/rezoom', 'PRDADM0001', 'PRDADM0001', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'PRDADM0001', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, 'Pending'),
	('PRD000004', 'Producer', 'Rezoom Villivakkam', '2017-08-02 07:33:06', '/rezoom', 'PRDADM0001', 'PRDADM0001', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'PRDADM0001', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, 'Pending'),
	('PRD000005', 'Producer', 'Rezoom Annanagar', '2017-08-02 07:33:06', '/rezoom', 'PRDADM0001', 'PRDADM0001', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'PRDADM0001', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, 'Pending'),
	('PRD000006', 'Producer', 'Rezoom Madipakkam', '2017-08-02 07:33:06', '/rezoom', 'PRDADM0001', 'PRDADM0001', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'PRDADM0001', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, 'Pending'),
	('PRD000007', 'Producer', 'Rezoom Vadapalani', '2017-08-02 07:33:06', '/rezoom', 'PRDADM0001', 'PRDADM0001', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 'PRDADM0001', b'1', b'0', NULL, NULL, NULL, NULL, NULL, NULL, 'Pending');
/*!40000 ALTER TABLE `producers` ENABLE KEYS */;

-- Dumping structure for table rezoom.producersattachments
CREATE TABLE IF NOT EXISTS `producersattachments` (
  `autoId` int(11) NOT NULL AUTO_INCREMENT,
  `producerId` varchar(50) DEFAULT NULL,
  `documentStatus` varchar(50) DEFAULT NULL,
  `documentDesc` varchar(100) DEFAULT NULL,
  `uploadFileDate` datetime DEFAULT NULL,
  `uploadFileFolderURL` varchar(500) DEFAULT NULL,
  `uploadFileLastModifiedDate` datetime DEFAULT NULL,
  `uploadFileName` varchar(100) DEFAULT NULL,
  `uploadFileNameForDisplay` varchar(50) DEFAULT NULL,
  `uploadFileSize` bigint(20) NOT NULL,
  `uploadResourceHandler` varchar(50) DEFAULT NULL,
  `uploadDocumentForType` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.producersattachments: ~0 rows (approximately)
DELETE FROM `producersattachments`;
/*!40000 ALTER TABLE `producersattachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `producersattachments` ENABLE KEYS */;

-- Dumping structure for table rezoom.producerscollabrate
CREATE TABLE IF NOT EXISTS `producerscollabrate` (
  `autoId` bigint(20) NOT NULL AUTO_INCREMENT,
  `producerId` varchar(50) NOT NULL,
  `collabraterId` varchar(50) NOT NULL,
  `createdBy` varchar(50) NOT NULL,
  `modifiedBy` varchar(50) NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.producerscollabrate: ~0 rows (approximately)
DELETE FROM `producerscollabrate`;
/*!40000 ALTER TABLE `producerscollabrate` DISABLE KEYS */;
/*!40000 ALTER TABLE `producerscollabrate` ENABLE KEYS */;

-- Dumping structure for table rezoom.producersproperty
CREATE TABLE IF NOT EXISTS `producersproperty` (
  `autoId` varchar(50) NOT NULL,
  `producerId` varchar(50) NOT NULL,
  `groupName` varchar(50) NOT NULL,
  `enumKey` varchar(50) NOT NULL,
  `property` varchar(200) NOT NULL,
  `value` text NOT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `createdBy` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `media` varchar(50) NOT NULL,
  `mediaMode` varchar(50) NOT NULL,
  `mediaType` varchar(50) NOT NULL,
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.producersproperty: ~0 rows (approximately)
DELETE FROM `producersproperty`;
/*!40000 ALTER TABLE `producersproperty` DISABLE KEYS */;
INSERT INTO `producersproperty` (`autoId`, `producerId`, `groupName`, `enumKey`, `property`, `value`, `status`, `createdBy`, `modifiedBy`, `createdDate`, `modifiedDate`, `comments`, `media`, `mediaMode`, `mediaType`) VALUES
	('1', 'PRD000001', 'ConfigurationEmail', 'ConfigurationEmail', 'org.hbs.core.beans.model.channel.ConfigurationEmail', '{\r\n   "socketFactory":"javax.net.ssl.SSLSocketFactory",\r\n   "fallback":"true",\r\n   "enablessl":"true",\r\n   "ttls":"true",\r\n   "protocol":"imaps",\r\n   "hostAddress":"imap.gmail.com",\r\n   "port":"587",\r\n   "userName":"tamilselvan.k@gmail.com",\r\n   "fromId":"tamilselvan.k@gmail.com",\r\n   "fromName":"Tamilselvan K",\r\n   "password":"Tam$elva78",\r\n   "source":"Gmail_IMAP",\r\n   "additionalProperties":{\r\n      "mail.smtp.ssl.trust": "smtp.gmail.com",\r\n      "mail.transport.protocol":"smtp",\r\n      "mail.smtp.auth":"true",\r\n      "mail.smtp.starttls.enable":"true",\r\n      "mail.debug":"false"\r\n   }\r\n}', b'1', NULL, NULL, '2019-12-07 10:35:43', '2019-12-07 10:35:46', NULL, 'Email', 'External', 'Primary'),
	('2', 'PRD000001', 'ConfigurationEmail', 'ConfigurationEmail', 'org.hbs.core.beans.model.channel.ConfigurationEmail', '{\r\n   "socketFactory":"javax.net.ssl.SSLSocketFactory",\r\n   "hostAddress":"smtp.gmail.com",\r\n   "port":"587",\r\n   "userName":"anandb.hbs@gmail.com",\r\n   "fromId":"anandb.hbs@gmail.com",\r\n   "fromName":"Anand",\r\n   "password":"Thiripura@7",\r\n   "source":"Gmail_SMTP",\r\n   "additionalProperties":{\r\n      "mail.smtp.ssl.trust": "smtp.gmail.com",\r\n      "mail.smtp.auth":"true",\r\n      "mail.smtp.starttls.enable":"true"\r\n   }\r\n}', b'1', NULL, NULL, '2019-12-07 10:35:43', '2019-12-07 10:35:46', NULL, 'Email', 'Internal', 'Primary');
/*!40000 ALTER TABLE `producersproperty` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume
CREATE TABLE IF NOT EXISTS `resume` (
  `dataURN` varchar(50) NOT NULL,
  `dataId` varchar(50) NOT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  PRIMARY KEY (`dataURN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume: ~0 rows (approximately)
DELETE FROM `resume`;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume-data-extractor-pattern
CREATE TABLE IF NOT EXISTS `resume-data-extractor-pattern` (
  `autoId` int(11) NOT NULL,
  `dataFilters` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `fromColonColonTo` varchar(100) DEFAULT NULL,
  `regExFor` varchar(100) DEFAULT NULL,
  `regExForOrder` int(11) DEFAULT NULL,
  `regExpression` varchar(100) DEFAULT NULL,
  `sentenceCaps` bit(1) DEFAULT b'0',
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume-data-extractor-pattern: ~0 rows (approximately)
DELETE FROM `resume-data-extractor-pattern`;
/*!40000 ALTER TABLE `resume-data-extractor-pattern` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume-data-extractor-pattern` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_address
CREATE TABLE IF NOT EXISTS `resume_address` (
  `addressId` varchar(50) NOT NULL,
  `dataId` varchar(50) NOT NULL,
  `addressLine1` varchar(50) DEFAULT NULL,
  `addressLine2` varchar(50) DEFAULT NULL,
  `addressLine3` varchar(50) DEFAULT NULL,
  `addressType` varchar(50) DEFAULT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT 'No State',
  `country` varchar(20) DEFAULT 'Asia/Kolkata',
  `pincode` int(11) DEFAULT NULL,
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_address: ~0 rows (approximately)
DELETE FROM `resume_address`;
/*!40000 ALTER TABLE `resume_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_address` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_attachments
CREATE TABLE IF NOT EXISTS `resume_attachments` (
  `autoId` int(11) NOT NULL AUTO_INCREMENT,
  `dataURN` varchar(50) DEFAULT NULL,
  `incomingId` varchar(50) DEFAULT NULL,
  `trace` varchar(50) DEFAULT 'YetToTrace',
  `uploadFileDate` datetime DEFAULT NULL,
  `uploadFileFolderURL` varchar(500) DEFAULT NULL,
  `uploadFileLastModifiedDate` datetime DEFAULT NULL,
  `uploadFileName` varchar(100) DEFAULT NULL,
  `uploadFileNameForDisplay` varchar(50) DEFAULT NULL,
  `uploadFileSize` bigint(20) NOT NULL,
  `uploadDocumentForType` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifiedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdBy` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  `uploadResourceHandler` varchar(50) DEFAULT NULL,
  `uploadSubFolderPath` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_attachments: ~0 rows (approximately)
DELETE FROM `resume_attachments`;
/*!40000 ALTER TABLE `resume_attachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_attachments` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_comments
CREATE TABLE IF NOT EXISTS `resume_comments` (
  `commentId` varchar(50) NOT NULL,
  `comments` mediumtext,
  `jobId` varchar(50) DEFAULT NULL,
  `dataId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_comments: ~0 rows (approximately)
DELETE FROM `resume_comments`;
/*!40000 ALTER TABLE `resume_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_comments` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_credits
CREATE TABLE IF NOT EXISTS `resume_credits` (
  `creditId` varchar(50) NOT NULL,
  `packageId` varchar(50) DEFAULT NULL,
  `purchaseStartDate` datetime DEFAULT NULL,
  `purchaseEndDate` datetime DEFAULT NULL,
  `purchaseValue` double DEFAULT NULL,
  `creditStatus` varchar(20) DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  PRIMARY KEY (`creditId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_credits: ~0 rows (approximately)
DELETE FROM `resume_credits`;
/*!40000 ALTER TABLE `resume_credits` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_credits` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_data
CREATE TABLE IF NOT EXISTS `resume_data` (
  `dataId` varchar(50) NOT NULL,
  `resumeURN` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `dateOfBirth` varchar(20) DEFAULT NULL,
  `dateOfYear` varchar(20) DEFAULT NULL,
  `fatherName` varchar(50) DEFAULT NULL,
  `gaurdianName` varchar(50) DEFAULT NULL,
  `motherName` varchar(50) DEFAULT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `martial` varchar(20) DEFAULT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `passportNo` varchar(20) DEFAULT NULL,
  `uid` varchar(50) DEFAULT NULL,
  `minSalaryExpected` varchar(50) DEFAULT NULL,
  `maxSalaryExpected` varchar(50) DEFAULT NULL,
  `currentSalary` varchar(50) DEFAULT NULL,
  `skills` varchar(100) DEFAULT NULL,
  `noticePeriod` varchar(20) DEFAULT 'NotApplicable',
  `availability` varchar(20) DEFAULT 'NotSpecify',
  PRIMARY KEY (`dataId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_data: ~0 rows (approximately)
DELETE FROM `resume_data`;
/*!40000 ALTER TABLE `resume_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_data` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_incoming_data
CREATE TABLE IF NOT EXISTS `resume_incoming_data` (
  `incomingId` varchar(50) NOT NULL,
  `media` varchar(50) NOT NULL,
  `candidateEmail` varchar(50) DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `description` longtext,
  `sentTime` bigint(11) NOT NULL,
  `portal` varchar(50) DEFAULT NULL,
  `readerInstance` varchar(50) NOT NULL,
  `incomingStatus` varchar(20) NOT NULL DEFAULT 'New',
  `producerId` varchar(50) DEFAULT NULL,
  `propertyId` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `uniqueId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`incomingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_incoming_data: ~0 rows (approximately)
DELETE FROM `resume_incoming_data`;
/*!40000 ALTER TABLE `resume_incoming_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_incoming_data` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_job_position
CREATE TABLE IF NOT EXISTS `resume_job_position` (
  `jobId` varchar(50) NOT NULL,
  `producerId` varchar(50) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `specialisation` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `requirements` varchar(45) DEFAULT NULL,
  `salaryMin` varchar(45) DEFAULT NULL,
  `salaryMax` varchar(45) DEFAULT NULL,
  `salaryType` varchar(45) DEFAULT 'Monthly',
  `workHours` varchar(45) DEFAULT '_8_Hours',
  `workWeek` varchar(45) DEFAULT '_5_Days',
  `positionType` varchar(20) DEFAULT 'Temporary',
  `availability` varchar(20) DEFAULT 'NotSpecify',
  `institutionRate` varchar(20) DEFAULT 'Normal',
  `priority` varchar(20) DEFAULT 'Normal',
  `workingLocation` varchar(45) DEFAULT NULL,
  `workingCountry` varchar(45) DEFAULT NULL,
  `openPositions` int(11) NOT NULL,
  `minimumQualification` varchar(45) DEFAULT NULL,
  `yearsOfExperience` varchar(45) DEFAULT NULL,
  `desiredSkills` varchar(45) DEFAULT NULL,
  `additionalSkills` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`jobId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_job_position: ~0 rows (approximately)
DELETE FROM `resume_job_position`;
/*!40000 ALTER TABLE `resume_job_position` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_job_position` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_job_position_mapping
CREATE TABLE IF NOT EXISTS `resume_job_position_mapping` (
  `autoId` varchar(50) NOT NULL,
  `producerId` varchar(50) DEFAULT NULL,
  `resumeURN` varchar(50) DEFAULT NULL,
  `jobId` varchar(50) DEFAULT NULL,
  `resumeStatus` varchar(20) DEFAULT 'New',
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_job_position_mapping: ~0 rows (approximately)
DELETE FROM `resume_job_position_mapping`;
/*!40000 ALTER TABLE `resume_job_position_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_job_position_mapping` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_media
CREATE TABLE IF NOT EXISTS `resume_media` (
  `mediaId` varchar(50) NOT NULL,
  `dataId` varchar(50) NOT NULL,
  `mediaType` varchar(20) NOT NULL,
  `emailId` varchar(50) DEFAULT NULL,
  `mobileNo` varchar(20) DEFAULT NULL,
  `phoneNo` varchar(20) DEFAULT NULL,
  `whatsAppNo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`mediaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_media: ~0 rows (approximately)
DELETE FROM `resume_media`;
/*!40000 ALTER TABLE `resume_media` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_media` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_packages
CREATE TABLE IF NOT EXISTS `resume_packages` (
  `packageId` varchar(50) NOT NULL,
  `producerId` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `durationInDays` int(11) DEFAULT '1',
  `discountPercent` int(11) DEFAULT '0',
  `description` text,
  `currency` varchar(10) DEFAULT 'USD',
  `credits` int(11) DEFAULT '1',
  `creditReduceRate` int(11) DEFAULT '1',
  `carryOverPercent` int(11) DEFAULT '0',
  `carryOver` bit(1) DEFAULT b'1',
  `actualCost` double DEFAULT '0',
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  PRIMARY KEY (`packageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_packages: ~0 rows (approximately)
DELETE FROM `resume_packages`;
/*!40000 ALTER TABLE `resume_packages` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_packages` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_producers
CREATE TABLE IF NOT EXISTS `resume_producers` (
  `producerId` varchar(50) NOT NULL,
  `resumeURN` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_producers: ~0 rows (approximately)
DELETE FROM `resume_producers`;
/*!40000 ALTER TABLE `resume_producers` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_producers` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_professional
CREATE TABLE IF NOT EXISTS `resume_professional` (
  `professionalId` varchar(50) NOT NULL,
  `dataId` varchar(50) DEFAULT NULL,
  `projectName` varchar(50) DEFAULT NULL,
  `companyName` varchar(50) DEFAULT NULL,
  `description` longtext,
  `designation` varchar(50) DEFAULT NULL,
  `duration` varchar(50) DEFAULT NULL,
  `responsibilities` mediumtext,
  PRIMARY KEY (`professionalId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_professional: ~0 rows (approximately)
DELETE FROM `resume_professional`;
/*!40000 ALTER TABLE `resume_professional` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_professional` ENABLE KEYS */;

-- Dumping structure for table rezoom.resume_qualification
CREATE TABLE IF NOT EXISTS `resume_qualification` (
  `qualificationId` varchar(50) NOT NULL,
  `dataId` varchar(50) NOT NULL,
  `institutionName` varchar(50) DEFAULT NULL,
  `companyName` varchar(50) DEFAULT NULL,
  `course` varchar(50) DEFAULT NULL,
  `grade` varchar(50) DEFAULT NULL,
  `passedOutYear` varchar(50) DEFAULT NULL,
  `weightage` varchar(50) DEFAULT NULL,
  `weightageDescription` varchar(50) DEFAULT NULL,
  `isHighest` bit(1) DEFAULT b'1',
  PRIMARY KEY (`qualificationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.resume_qualification: ~0 rows (approximately)
DELETE FROM `resume_qualification`;
/*!40000 ALTER TABLE `resume_qualification` DISABLE KEYS */;
/*!40000 ALTER TABLE `resume_qualification` ENABLE KEYS */;

-- Dumping structure for table rezoom.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `roleId` varchar(50) NOT NULL,
  `producerId` varchar(50) NOT NULL,
  `enumKey` varchar(50) NOT NULL,
  `roleName` varchar(50) NOT NULL,
  `roleShortName` varchar(50) NOT NULL,
  `roleLongName` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `roleType` varchar(50) DEFAULT NULL,
  `createdBy` varchar(50) NOT NULL,
  `createdDate` datetime NOT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  `isAdminRole` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.roles: ~3 rows (approximately)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`roleId`, `producerId`, `enumKey`, `roleName`, `roleShortName`, `roleLongName`, `description`, `roleType`, `createdBy`, `createdDate`, `modifiedBy`, `modifiedDate`, `status`, `isAdminRole`) VALUES
	('Admin', 'PRD000001', 'Admin', 'Admin Role', 'Admin', 'Admin Role', 'Admin Role', 'Admin Role', 'PRDADM0001', '2014-07-06 10:30:00', 'PRDADM0001', '2014-07-06 10:30:00', b'1', b'1'),
	('Dummy', 'PRD000001', 'Dummy', 'Dummy', 'Dummy', 'Dummy', 'Dummy', 'EmployeeRole', 'PRDADM0001', '2014-03-22 17:40:42', 'PRDADM0001', '2014-07-06 10:30:00', b'1', b'0'),
	('Employee', 'PRD000001', 'EmployeeRole', 'Employee Role', 'Employee Role', 'Employeeistrator Role', 'Employee Role', 'EmployeeRole', 'PRDADM0001', '2014-07-06 10:30:00', 'PRDADM0001', '2014-07-06 10:30:00', b'1', b'0');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table rezoom.sequence
CREATE TABLE IF NOT EXISTS `sequence` (
  `autoId` int(11) NOT NULL AUTO_INCREMENT,
  `producerId` varchar(50) NOT NULL,
  `sequenceId` int(11) NOT NULL,
  `sequenceKey` varchar(50) NOT NULL,
  `prepend` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.sequence: ~1 rows (approximately)
DELETE FROM `sequence`;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` (`autoId`, `producerId`, `sequenceId`, `sequenceKey`, `prepend`) VALUES
	(1, 'PRD000001', 10011, 'Users', 'USR');
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;

-- Dumping structure for table rezoom.state
CREATE TABLE IF NOT EXISTS `state` (
  `state` varchar(50) NOT NULL,
  `country` varchar(20) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.state: ~35 rows (approximately)
DELETE FROM `state`;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` (`state`, `country`, `status`) VALUES
	('Andhra Pradesh', 'Asia/Kolkata', b'1'),
	('Arunachal Pradesh', 'Asia/Kolkata', b'1'),
	('Assam', 'Asia/Kolkata', b'1'),
	('Bihar', 'Asia/Kolkata', b'1'),
	('Chandigarh', 'Asia/Kolkata', b'1'),
	('Chhattisgarh', 'Asia/Kolkata', b'1'),
	('Dadra and Nagar Haveli', 'Asia/Kolkata', b'1'),
	('Daman & Diu', 'Asia/Kolkata', b'1'),
	('Delhi', 'Asia/Kolkata', b'1'),
	('Goa', 'Asia/Kolkata', b'1'),
	('Gujarat', 'Asia/Kolkata', b'1'),
	('Haryana', 'Asia/Kolkata', b'1'),
	('Himachal Pradesh', 'Asia/Kolkata', b'1'),
	('Jammu & Kashmir', 'Asia/Kolkata', b'1'),
	('Jharkhand', 'Asia/Kolkata', b'1'),
	('Karnataka', 'Asia/Kolkata', b'1'),
	('Kerala', 'Asia/Kolkata', b'1'),
	('Lakshadweep', 'Asia/Kolkata', b'1'),
	('Madhya Pradesh', 'Asia/Kolkata', b'1'),
	('Maharashtra', 'Asia/Kolkata', b'1'),
	('Manipur', 'Asia/Kolkata', b'1'),
	('Meghalaya', 'Asia/Kolkata', b'1'),
	('Mizoram', 'Asia/Kolkata', b'1'),
	('Nagaland', 'Asia/Kolkata', b'1'),
	('No State', 'Asia/Kolkata', b'1'),
	('Odisha', 'Asia/Kolkata', b'1'),
	('Punjab', 'Asia/Kolkata', b'1'),
	('Rajasthan', 'Asia/Kolkata', b'1'),
	('Sikkim', 'Asia/Kolkata', b'1'),
	('Tamil Nadu', 'Asia/Kolkata', b'1'),
	('Telangana', 'Asia/Kolkata', b'1'),
	('Tripura', 'Asia/Kolkata', b'1'),
	('Uttar Pradesh', 'Asia/Kolkata', b'1'),
	('Uttarakhand', 'Asia/Kolkata', b'1'),
	('West Bengal', 'Asia/Kolkata', b'1');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;

-- Dumping structure for table rezoom.userlog
CREATE TABLE IF NOT EXISTS `userlog` (
  `autoId` int(10) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(50) NOT NULL DEFAULT '0',
  `userLoginTime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `userLogoutTime` datetime DEFAULT '0000-00-00 00:00:00',
  `ipAddress` varchar(50) NOT NULL DEFAULT '0',
  `fetchBlock` bit(1) DEFAULT b'0',
  PRIMARY KEY (`autoId`),
  KEY `FK_LogUser_User_idx` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.userlog: ~0 rows (approximately)
DELETE FROM `userlog`;
/*!40000 ALTER TABLE `userlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `userlog` ENABLE KEYS */;

-- Dumping structure for table rezoom.userroles
CREATE TABLE IF NOT EXISTS `userroles` (
  `autoId` bigint(11) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(50) NOT NULL,
  `roleId` varchar(50) NOT NULL,
  PRIMARY KEY (`autoId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.userroles: ~2 rows (approximately)
DELETE FROM `userroles`;
/*!40000 ALTER TABLE `userroles` DISABLE KEYS */;
INSERT INTO `userroles` (`autoId`, `employeeId`, `roleId`) VALUES
	(1, 'USR1513438024799', 'Admin'),
	(2, 'USR1513438024799', 'Employee');
/*!40000 ALTER TABLE `userroles` ENABLE KEYS */;

-- Dumping structure for table rezoom.users
CREATE TABLE IF NOT EXISTS `users` (
  `employeeId` varchar(50) NOT NULL,
  `userType` varchar(50) DEFAULT NULL,
  `producerId` varchar(50) DEFAULT NULL,
  `parentProducerId` varchar(50) DEFAULT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `folderToken` varchar(50) DEFAULT NULL,
  `userName` varchar(100) DEFAULT '0',
  `lastName` varchar(100) DEFAULT NULL,
  `fatherName` varchar(100) DEFAULT NULL,
  `userPwd` varchar(100) DEFAULT NULL,
  `userPwdModFlag` bit(1) DEFAULT b'0',
  `userPwdModDate` datetime DEFAULT NULL,
  `dob` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `otp` varchar(20) DEFAULT NULL,
  `userStatus` varchar(50) DEFAULT 'Pending',
  `dateOfJoin` varchar(50) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  `tokenExpiryDate` datetime DEFAULT NULL,
  `country` varchar(20) NOT NULL DEFAULT 'IN',
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT b'1',
  PRIMARY KEY (`employeeId`),
  KEY `FK_users_producers` (`producerId`),
  KEY `FK_users_country` (`country`),
  CONSTRAINT `FK_users_producers` FOREIGN KEY (`producerId`) REFERENCES `producers` (`producerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.users: ~5 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`employeeId`, `userType`, `producerId`, `parentProducerId`, `userId`, `folderToken`, `userName`, `lastName`, `fatherName`, `userPwd`, `userPwdModFlag`, `userPwdModDate`, `dob`, `sex`, `otp`, `userStatus`, `dateOfJoin`, `token`, `tokenExpiryDate`, `country`, `createdBy`, `createdDate`, `modifiedBy`, `modifiedDate`, `status`) VALUES
	('PRDADM0001', 'Producer', 'PRD000001', 'PRD000001', 'EduTel-Id', '013ea493-8fcc-4c68-b4b8-431a82a375bb', 'Rezoom', 'Admin', NULL, '$2a$10$7hnkWav9ZOLtz/Q2y.FLvuIyS3NFQ1J42jkV3SMbo9NRzi1P9m2fW', b'0', NULL, NULL, NULL, NULL, 'Activated', NULL, NULL, '2017-09-23 22:00:21', 'Asia/Singapore', 'PRDADM0001', '2018-01-30 23:47:50', 'PRDADM0001', '2018-01-30 23:47:50', b'1'),
	('USR0000000000000', 'Consumer', 'PRD000001', 'PRD000001', 'Demo-Id', '013ea493-8fcc-4c68-b4b8-431a82a375bb', 'Demo', 'User', NULL, '$2a$10$7hnkWav9ZOLtz/Q2y.FLvuIyS3NFQ1J42jkV3SMbo9NRzi1P9m2fW', NULL, NULL, NULL, NULL, NULL, 'Activated', NULL, NULL, '2017-12-16 20:57:29', 'Asia/Kolkata', 'PRDADM0001', '2017-12-16 20:57:33', 'PRDADM0001', '2018-01-30 23:47:50', b'1'),
	('USR1513438024799', 'Consumer', 'PRD000001', 'PRD000001', 'Ananth-Id', '1aa2850a-6717-4664-9215-56a2bf52806e', 'Ananth', 'Balasubramanian', NULL, '$2a$10$7hnkWav9ZOLtz/Q2y.FLvuIyS3NFQ1J42jkV3SMbo9NRzi1P9m2fW', b'0', NULL, NULL, NULL, NULL, 'Activated', NULL, '6f82f43d-809d-4da2-ba16-4403dbc288d5', '2020-06-17 20:23:10', 'Asia/Kolkata', 'PRDADM0001', '2017-12-16 20:57:33', 'PRDADM0001', '2020-06-14 19:48:35', b'0'),
	('USR1513438024800', 'Employee', 'PRD000001', 'PRD000001', 'Tamil-Id', '486f11d5-4972-44b0-9bdc-50f7b572ce11', 'Tamil', 'Selvan', NULL, '$2a$10$7hnkWav9ZOLtz/Q2y.FLvuIyS3NFQ1J42jkV3SMbo9NRzi1P9m2fW', NULL, NULL, NULL, NULL, NULL, 'Activated', NULL, NULL, '2017-12-16 20:57:29', 'Asia/Kolkata', 'PRDADM0001', '2017-12-16 20:57:33', 'PRDADM0001', '2018-01-30 23:47:50', b'1'),
	('USR1513438024801', 'Employee', 'PRD000001', 'PRD000001', 'HariHaran-Id', 'ca7621f6-a0e0-425a-a1ca-e0c171e6b282', 'HariHaran', 'Thavaselvam', NULL, '$2a$10$7hnkWav9ZOLtz/Q2y.FLvuIyS3NFQ1J42jkV3SMbo9NRzi1P9m2fW', NULL, NULL, NULL, NULL, NULL, 'Activated', NULL, NULL, '2017-12-16 20:57:29', 'Asia/Kolkata', 'PRDADM0001', '2018-01-30 23:47:50', 'PRDADM0001', '2018-01-30 23:47:50', b'1');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table rezoom.usersaddress
CREATE TABLE IF NOT EXISTS `usersaddress` (
  `addressId` varchar(50) NOT NULL,
  `employeeId` varchar(50) NOT NULL,
  `addressLine1` varchar(50) DEFAULT NULL,
  `addressLine2` varchar(50) DEFAULT NULL,
  `addressLine3` varchar(50) DEFAULT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT 'No State',
  `country` varchar(50) DEFAULT 'IN',
  `pincode` varchar(50) DEFAULT NULL,
  `addressType` varchar(50) NOT NULL,
  PRIMARY KEY (`addressId`),
  KEY `FK_useraddress_users` (`employeeId`),
  KEY `FK_usersaddress_state` (`state`),
  KEY `FK_usersaddress_country` (`country`),
  CONSTRAINT `FK_useraddress_users` FOREIGN KEY (`employeeId`) REFERENCES `users` (`employeeId`),
  CONSTRAINT `FK_usersaddress_state` FOREIGN KEY (`state`) REFERENCES `state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.usersaddress: ~0 rows (approximately)
DELETE FROM `usersaddress`;
/*!40000 ALTER TABLE `usersaddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `usersaddress` ENABLE KEYS */;

-- Dumping structure for table rezoom.usersattachments
CREATE TABLE IF NOT EXISTS `usersattachments` (
  `autoId` int(11) NOT NULL AUTO_INCREMENT,
  `employeeId` varchar(50) DEFAULT NULL,
  `uploadDocumentForType` varchar(50) DEFAULT NULL,
  `uploadFileFolderURL` varchar(500) DEFAULT NULL,
  `uploadResourceHandler` varchar(50) NOT NULL DEFAULT 'Default',
  `uploadFileName` varchar(100) DEFAULT NULL,
  `uploadFileDate` datetime DEFAULT NULL,
  `uploadFileLastModifiedDate` datetime DEFAULT NULL,
  `uploadFileSize` bigint(20) NOT NULL,
  `createdDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `modifiedBy` varchar(50) DEFAULT NULL,
  `status` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`autoId`),
  KEY `FK_userattachments_users` (`employeeId`),
  CONSTRAINT `FK_userattachments_users` FOREIGN KEY (`employeeId`) REFERENCES `users` (`employeeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.usersattachments: ~0 rows (approximately)
DELETE FROM `usersattachments`;
/*!40000 ALTER TABLE `usersattachments` DISABLE KEYS */;
/*!40000 ALTER TABLE `usersattachments` ENABLE KEYS */;

-- Dumping structure for table rezoom.usersmedia
CREATE TABLE IF NOT EXISTS `usersmedia` (
  `mediaId` varchar(50) NOT NULL,
  `employeeId` varchar(50) NOT NULL,
  `mediaType` varchar(20) NOT NULL,
  `emailId` varchar(50) DEFAULT NULL,
  `mobileNo` varchar(20) DEFAULT NULL,
  `phoneNo` varchar(20) DEFAULT NULL,
  `whatsAppNo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`mediaId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rezoom.usersmedia: ~1 rows (approximately)
DELETE FROM `usersmedia`;
/*!40000 ALTER TABLE `usersmedia` DISABLE KEYS */;
INSERT INTO `usersmedia` (`mediaId`, `employeeId`, `mediaType`, `emailId`, `mobileNo`, `phoneNo`, `whatsAppNo`) VALUES
	('UMEDIA00001', 'USR1513438024799', 'Primary', 'tamilselvan.k@gmail.com', '9677101112', ' ', '9789875832');
/*!40000 ALTER TABLE `usersmedia` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
