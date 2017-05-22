-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: ghealth
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointmentsettings`
--

DROP TABLE IF EXISTS `appointmentsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointmentsettings` (
  `apsID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `apsPtID` varchar(45) DEFAULT NULL,
  `apsDate` date DEFAULT NULL,
  `apsTime` time DEFAULT NULL,
  `apsCreateDate` date DEFAULT NULL,
  `apsCreateTime` time DEFAULT NULL,
  `apsStatus` varchar(45) DEFAULT NULL,
  `apsDocID` varchar(45) DEFAULT NULL,
  `apsSummery` varchar(150) DEFAULT NULL,
  `apsStartTime` time DEFAULT NULL,
  PRIMARY KEY (`apsID`),
  KEY `apsPtID_idx` (`apsPtID`),
  KEY `apsDocID_idx` (`apsDocID`),
  CONSTRAINT `apsDocID` FOREIGN KEY (`apsDocID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `apsPtID` FOREIGN KEY (`apsPtID`) REFERENCES `patient` (`ptID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=609 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointmentsettings`
--

LOCK TABLES `appointmentsettings` WRITE;
/*!40000 ALTER TABLE `appointmentsettings` DISABLE KEYS */;
INSERT INTO `appointmentsettings` VALUES (1,'1001','2016-06-05','10:30:00','2016-05-15','14:01:43','ARRIVED','5001','The patient not feeling good.','10:32:12'),(2,'1002','2016-06-05','11:00:00','2016-05-15','14:17:24','NOSHOW','5004','',NULL),(3,'1003','2016-06-05','11:30:00','2016-05-15','15:11:42','ARRIVED','5005','The patient has a fever of 40 degrees','11:37:00'),(4,'1004','2016-06-05','12:00:00','2016-05-15','15:19:12','ARRIVED','5002','The patient not feeling good.','12:01:00'),(5,'1005','2016-06-05','12:30:00','2016-05-16','14:01:43','ARRIVED','5003','The patient have alergy.','12:30:12'),(6,'1006','2016-06-05','13:00:00','2016-05-16','14:17:24','ARRIVED','5002','The patient has a fever of 38 degrees','13:12:23'),(7,'1007','2016-06-05','13:30:00','2016-05-16','14:01:43','ARRIVED','5003','The patient not feeling good.','13:32:00'),(8,'1012','2016-06-06','14:00:00','2016-05-16','15:19:12','NOSHOW','5009','',NULL),(9,'1013','2016-06-06','14:30:00','2016-05-17','14:01:43','ARRIVED','5009','The patient has a fever of 40 degrees','14:36:00'),(10,'1014','2016-06-06','15:00:00','2016-05-17','14:17:24','ARRIVED','5008','The patient not feeling good.','15:02:50'),(11,'1015','2016-06-06','15:30:00','2016-05-17','15:11:42','ARRIVED','5011','The patient have alergy.','15:37:00'),(12,'1016','2016-06-06','16:00:00','2016-05-17','15:19:12','CANCELED','5011','',NULL),(13,'1017','2016-06-06','16:30:00','2016-05-17','16:10:12','ARRIVED','5015','The patient have have asthma','16:31:50'),(14,'1019','2016-06-06','17:00:00','2016-05-18','14:01:43','ARRIVED','5004','The patient have alergy.','17:10:00'),(16,'1020','2016-06-06','18:00:00','2016-05-18','15:11:42','ARRIVED','5015','The patient has a fever of 40 degrees','18:00:12'),(17,'1021','2016-06-07','18:30:00','2016-05-18','15:19:12','CANCELED','5008',NULL,NULL),(18,'1008','2016-06-07','10:30:00','2016-05-18','14:01:43','ARRIVED','5001','The patient have have asthma','10:32:07'),(19,'1009','2016-06-07','11:00:00','2016-05-19','14:17:24','NOSHOW','5013',NULL,NULL),(20,'1010','2016-06-07','11:30:00','2016-05-19','15:11:42','ARRIVED','5014','The patient not feeling good.','11:37:12'),(21,'1011','2016-06-07','12:00:00','2016-05-19','15:19:12','ARRIVED','5001','The patient not feeling good.','12:01:05'),(22,'1001','2016-06-07','12:30:00','2016-05-19','16:49:52','CANCELED','5001',NULL,NULL),(23,'1002','2016-06-08','13:00:00','2016-05-20','14:01:43','ARRIVED','5004','The patient not feeling good.','13:03:20'),(24,'1003','2016-06-08','13:30:00','2016-05-20','14:17:24','ARRIVED','5005','The patient not feeling good.','13:31:03'),(25,'1004','2016-06-08','14:00:00','2016-05-20','15:11:42','ARRIVED','5002','The patient not feeling good.','14:06:00'),(26,'1005','2016-06-08','14:30:00','2016-05-20','15:19:12','NOSHOW','5003','',NULL),(27,'1006','2016-06-08','15:00:00','2016-05-21','14:01:43','ARRIVED','5002','The patient not feeling good.','15:02:07'),(28,'1007','2016-06-08','15:30:00','2016-05-21','14:17:24','ARRIVED','5003','The patient not feeling good.','15:38:00'),(29,'1008','2016-06-08','16:00:00','2016-05-21','15:11:42','CANCELED','5001','',NULL),(30,'1009','2016-06-08','16:30:00','2016-05-21','15:19:12','ARRIVED','5013','The patient not feeling good.','16:30:01'),(31,'1010','2016-06-08','17:00:00','2016-05-22','14:01:43','ARRIVED','5014','The patient not feeling good.','17:09:00'),(32,'1011','2016-06-08','17:30:00','2016-05-22','14:17:24','NOSHOW','5001',NULL,NULL),(33,'1012','2016-06-08','18:00:00','2016-05-22','15:11:42','ARRIVED','5009','The patient not feeling good.','18:20:00'),(34,'1013','2016-06-08','18:30:00','2016-05-22','15:19:12','ARRIVED','5009','The patient not feeling good.','18:32:21'),(35,'1014','2016-06-08','10:00:00','2016-05-23','14:01:43','ARRIVED','5008','The patient not feeling good.','10:01:20'),(36,'1016','2016-06-08','10:30:00','2016-05-23','14:17:24','CANCELED','5011',NULL,NULL),(37,'1015','2016-06-08','11:00:00','2016-05-23','15:11:42','ARRIVED','5011','The patient not feeling good.','11:05:06'),(38,'1017','2016-06-08','11:30:00','2016-05-23','15:19:12','ARRIVED','5015','The patient not feeling good.','11:31:07'),(39,'1018','2016-06-08','12:00:00','2016-05-23','14:01:43','NOSHOW','5008','',NULL),(40,'1019','2016-06-08','12:30:00','2016-05-24','14:17:24','ARRIVED','5004','The patient not feeling good.','12:32:09'),(41,'1020','2016-06-08','13:00:00','2016-05-24','15:11:42','CANCELED','5015',NULL,NULL),(42,'1021','2016-06-09','13:30:00','2016-05-24','15:19:12','ARRIVED','5008','The patient not feeling good.','13:36:10'),(43,'1001','2016-06-09','14:00:00','2016-05-27','14:01:43','ARRIVED','5001','The patient not feeling good.','14:03:10'),(44,'1002','2016-06-09','14:30:00','2016-05-27','14:17:24','ARRIVED','5004','The patient not feeling good.','14:35:02'),(45,'1003','2016-06-17','15:00:00','2016-05-27','15:11:42','SCHEDUELD','5005',NULL,NULL),(46,'1004','2016-06-18','15:30:00','2016-05-27','15:19:12','SCHEDUELD','5002',NULL,NULL),(47,'1005','2016-06-16','16:00:00','2016-06-01','14:01:43','SCHEDUELD','5003',NULL,NULL),(48,'1006','2016-06-21','16:30:00','2016-06-01','14:17:24','SCHEDUELD','5002',NULL,NULL),(49,'1007','2016-07-03','17:00:00','2016-06-02','15:11:42','SCHEDUELD','5003',NULL,NULL),(50,'1008','2016-07-10','17:30:00','2016-06-02','15:19:12','SCHEDUELD','5001',NULL,NULL),(61,'1018','2016-05-24','17:30:00','2016-05-18','14:17:24','ARRIVED','5008','The patient not feeling good.','17:37:20'),(300,'1010','2016-05-23','11:30:00','2016-05-19','15:11:42','ARRIVED','5014','The patient not feeling good.','11:37:12'),(310,'1011','2016-05-24','12:00:00','2016-05-19','15:19:12','ARRIVED','5001','The patient not feeling good.','12:01:05'),(320,'1001','2016-05-24','12:30:00','2016-05-19','16:49:52','CANCELED','5001',NULL,NULL),(330,'1002','2016-05-25','13:00:00','2016-05-20','14:01:43','ARRIVED','5004','The patient not feeling good.','13:03:20'),(340,'1003','2016-05-25','13:30:00','2016-05-20','14:17:24','ARRIVED','5005','The patient not feeling good.','13:31:03'),(350,'1004','2016-05-23','14:00:00','2016-05-20','15:11:42','ARRIVED','5002','The patient not feeling good.','14:06:00'),(360,'1005','2016-05-23','14:30:00','2016-05-20','15:19:12','CANCELED','5003','',NULL),(370,'1006','2016-05-24','15:00:00','2016-05-21','14:01:43','ARRIVED','5002','The patient not feeling good.','15:02:07'),(380,'1007','2016-05-24','15:30:00','2016-05-21','14:17:24','ARRIVED','5003','The patient not feeling good.','15:38:00'),(390,'1008','2016-05-25','16:00:00','2016-05-21','15:11:42','ARRIVED','5001','The patient not feeling good.',NULL),(400,'1009','2016-05-25','16:30:00','2016-05-21','15:19:12','ARRIVED','5013','The patient not feeling good.','16:30:01'),(410,'1010','2016-05-26','17:00:00','2016-05-22','14:01:43','ARRIVED','5014','The patient not feeling good.','17:09:00'),(420,'1011','2016-05-26','17:30:00','2016-05-22','14:17:24','ARRIVED','5001','The patient not feeling good.',NULL),(430,'1012','2016-05-24','18:00:00','2016-05-22','15:11:42','ARRIVED','5009','The patient not feeling good.','18:20:00'),(440,'1013','2016-05-24','18:30:00','2016-05-22','15:19:12','ARRIVED','5009','The patient not feeling good.','18:32:21'),(450,'1014','2016-05-25','10:00:00','2016-05-23','14:01:43','ARRIVED','5008','The patient not feeling good.','10:01:20'),(460,'1016','2016-05-25','10:30:00','2016-05-23','14:17:24','CANCELED','5011',NULL,NULL),(470,'1015','2016-05-26','11:00:00','2016-05-23','15:11:42','ARRIVED','5011','The patient not feeling good.','11:05:06'),(480,'1017','2016-05-26','11:30:00','2016-05-23','15:19:12','ARRIVED','5015','The patient not feeling good.','11:31:07'),(490,'1018','2016-05-27','12:00:00','2016-05-23','14:01:43','CANCELED','5008','',NULL),(500,'1019','2016-05-27','12:30:00','2016-05-24','14:17:24','ARRIVED','5004','The patient not feeling good.','12:32:09'),(510,'1020','2016-06-25','13:00:00','2016-05-24','15:11:42','ARRIVED','5015','The patient not feeling good.','13:13:10'),(520,'1021','2016-06-25','13:30:00','2016-05-24','15:19:12','ARRIVED','5008','The patient not feeling good.','13:33:10'),(530,'1001','2016-06-15','14:00:00','2016-05-27','14:01:43','NOSHOW','5001','','14:03:10'),(540,'1002','2016-06-15','14:30:00','2016-05-27','14:17:24','ARRIVED','5004','The patient not feeling good.','14:35:02'),(550,'1003','2016-06-17','15:00:00','2016-05-27','15:11:42','SCHEDUELD','5005',NULL,NULL),(560,'1004','2016-06-18','15:30:00','2016-05-27','15:19:12','SCHEDUELD','5002',NULL,NULL),(570,'1005','2016-06-16','16:00:00','2016-06-01','14:01:43','SCHEDUELD','5003',NULL,NULL),(580,'1006','2016-06-21','16:30:00','2016-06-01','14:17:24','SCHEDUELD','5002',NULL,NULL),(590,'1007','2016-07-03','17:00:00','2016-06-02','15:11:42','SCHEDUELD','5003',NULL,NULL),(600,'1008','2016-07-10','17:30:00','2016-06-02','15:19:12','SCHEDUELD','5001',NULL,NULL),(601,'1008','2016-06-06','08:30:00','2016-05-27','15:19:12','ARRIVED','5001',NULL,'09:00:00'),(602,'1009','2016-06-06','08:00:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'00:00:00'),(603,'1010','2016-06-06','09:00:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'00:00:00'),(604,'1011','2016-06-06','09:30:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'00:00:00'),(605,'1012','2016-06-06','10:00:00','2016-05-27','15:19:12','NOSHOW','5001',NULL,'00:00:00'),(607,'1013','2016-06-05','10:00:00','2016-05-27','15:19:12','ARRIVED','5001',NULL,'10:30:00'),(608,'1000','2016-06-13','10:00:00','2016-06-12','13:46:37','SCHEDUELD','5008',NULL,NULL);
/*!40000 ALTER TABLE `appointmentsettings` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `ghealth`.`appointmentsettings_AFTER_INSERT` AFTER INSERT ON `appointmentsettings` FOR EACH ROW
BEGIN

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar` (
  `calendar_date` date NOT NULL,
  PRIMARY KEY (`calendar_date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES ('2016-06-05'),('2016-06-06'),('2016-06-07'),('2016-06-08'),('2016-06-09'),('2016-06-10'),('2016-06-11'),('2016-06-12');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic`
--

DROP TABLE IF EXISTS `clinic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinic` (
  `cID` int(11) NOT NULL,
  `cName` varchar(45) DEFAULT NULL,
  `cLocation` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic`
--

LOCK TABLES `clinic` WRITE;
/*!40000 ALTER TABLE `clinic` DISABLE KEYS */;
INSERT INTO `clinic` VALUES (1,'Asuta','Haifa'),(2,'ISPN','Tel-Aviv'),(3,'Hadassah','Jerusalem'),(4,'Yoseftal ','Eilat'),(5,'Assaf Harofe','Acko'),(6,'CCCP','Karmiel'),(7,'Elisha','Beersheba');
/*!40000 ALTER TABLE `clinic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `dID` varchar(45) NOT NULL,
  `dSpeciality` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dID`),
  CONSTRAINT `dID` FOREIGN KEY (`dID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('5000','Orthopedist'),('5001','Orthopedist'),('5002','Gynecologist'),('5003','Orthopedist'),('5004','Orthopedist'),('5005','Orthopedist'),('5006','Gynecologist'),('5007','Eyes'),('5008','Cardio'),('5009','Neurologist'),('5010','Cardio'),('5011','Neurologist'),('5012','Cardio'),('5013','Gynecologist'),('5014','Gynecologist'),('5015','Eyes');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labsettings`
--

DROP TABLE IF EXISTS `labsettings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labsettings` (
  `labID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `labPtID` varchar(45) DEFAULT NULL,
  `labCreateDate` date DEFAULT NULL,
  `labCreateTime` time DEFAULT NULL,
  `labStatus` varchar(45) DEFAULT NULL,
  `labDocID` varchar(45) DEFAULT NULL,
  `labworkerID` varchar(45) DEFAULT NULL,
  `labDocReq` varchar(500) DEFAULT NULL,
  `labWorkerSummery` varchar(500) DEFAULT NULL,
  `labPhotoPath` varchar(150) DEFAULT 'NO FILE',
  PRIMARY KEY (`labID`),
  KEY `labPtID_idx` (`labPtID`),
  KEY `labDocID` (`labDocID`),
  CONSTRAINT `labDocID` FOREIGN KEY (`labDocID`) REFERENCES `user` (`uID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `labPtID` FOREIGN KEY (`labPtID`) REFERENCES `patient` (`ptID`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labsettings`
--

LOCK TABLES `labsettings` WRITE;
/*!40000 ALTER TABLE `labsettings` DISABLE KEYS */;
INSERT INTO `labsettings` VALUES (1,'1010','2016-06-15','14:01:43','ARRIVED','5004','6000','Please make X-Ray','Done','src//Server//files//1.jpg'),(2,'1010','2016-07-15','14:01:43','ARRIVED','5003','6000','Please make X-Ray','Done','src//Server//files//2.jpg'),(3,'1010','2016-06-20','14:01:43','ARRIVED','5005','6000','Please make X-Ray','Done','src//Server//files//3.png'),(4,'1010','2016-05-30','14:01:43','ARRIVED','5002','6000','Please make X-Ray','Done','src//Server//files//4.jpg'),(5,'1010','2016-09-30','14:01:43','ARRIVED','5002','6000','Please make X-Ray','Done','src//Server//files//5.jpg'),(6,'1010','2016-06-08','18:33:10','SCHEDUELD','5000',NULL,'Please make X-Ray',NULL,'NO FILE'),(7,'1010','2016-06-08','18:36:20','ARRIVED','5000','6000','Please make X-Ray','Done','src//Server//files//7.jpg');
/*!40000 ALTER TABLE `labsettings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `nmonthlyview`
--

DROP TABLE IF EXISTS `nmonthlyview`;
/*!50001 DROP VIEW IF EXISTS `nmonthlyview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `nmonthlyview` AS SELECT 
 1 AS `weekNumar`,
 1 AS `NumOfPatients`,
 1 AS `AvgProcessTime`,
 1 AS `AvgWaitingTime`,
 1 AS `weekNumns`,
 1 AS `NumOfNoshows`,
 1 AS `weekNuml`,
 1 AS `LeaveClients`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `ptID` varchar(45) NOT NULL,
  `ptFirstName` varchar(45) DEFAULT NULL,
  `ptLastName` varchar(45) DEFAULT NULL,
  `ptEmail` varchar(45) DEFAULT NULL,
  `ptPhone` varchar(45) DEFAULT NULL,
  `ptPrivateClinic` varchar(45) DEFAULT NULL,
  `ptDoctorID` varchar(45) DEFAULT NULL,
  `ptIsRegistered` varchar(45) DEFAULT NULL,
  `ptLeaveDate` date DEFAULT NULL,
  PRIMARY KEY (`ptID`),
  KEY `PersonalDoctorID_idx` (`ptDoctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('1000','hava','lotringer','savta@gmail.com','0544876542','Klalit','2','IS_REG',NULL),('1001','gefen','kaplinsky','kgefen@gmail.com','054-4665760','Klalit','1','IS_REG',NULL),('1002','yarden','kaplinsky','kapyarden@gmail.com','054-1235760','Klalit','2','NOT_REG','2016-06-11'),('1003','hila','gazit','ghila@walla.co.il','054-4664444','Klalit','1','IS_REG',NULL),('1004','doron','lev','levdoron@yahoo.com','054-4665333','Klalit','1','IS_REG',NULL),('1005','din','kanuk','dinkan@walla.com','054-4665760','Maccabi','1','IS_REG',NULL),('1006','Ori','erel','temp@gmail.com','054-4611111','Klalit','2','IS_REG',NULL),('1007','Amir','Sht','amamam@yahoo.com','054-3344123','Meuhedet','1','IS_REG',NULL),('1008','dan','shalmon','shalmon23@gmail.com','055-5544336','Maccabi','2','IS_REG',NULL),('1009','mor','yahalom','yahalomor@gmail.com','052-4222456','Meuhedet','1','IS_REG',NULL),('1010','noga','levi','noglev@hotmail.com','050-3332415','Maccabi','1','IS_REG',NULL),('1011','asahel','bar-ilan','godmade@hotmail.com','052-2444423','Meuhedet','2','IS_REG',NULL),('1012','merav','froim','ffmer@walla.com','054-3544232','Maccabi','2','IS_REG',NULL),('1013','nir ','shalmon','shalmnir@gmail.com','055-2442324','Maccabi','2','IS_REG',NULL),('1014','guy','sharon','guy.sharon@outlook.com','+987-255442324','Klalit','2','IS_REG',NULL),('1015','guy','leibovits','leibo69@yahoo.com','052-47654232','Klalit','1','IS_REG',NULL),('1016','alon','shohat','ashohet@walla.com','052-5732897','Maccabi','1','IS_REG',NULL),('1017','koby','toget','togetk@gmail.com','055-5524321','Klalit','1','IS_REG',NULL),('1018','roni','erel','minicatch@walla.co.il','052-2441155','Klalit','1','IS_REG',NULL),('1019','noy','lotringer','lotr.noy@outlook.com','054-4778923','Meuhedet','2','IS_REG',NULL),('1020','dina','belsky','belskydina@walla.co.il','055-2345412','Meuhedet','2','IS_REG',NULL),('1021','guy','levin','tattoosguy@walla.com','055-2314512','Meuhedet','2','IS_REG',NULL);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personaldoctor`
--

DROP TABLE IF EXISTS `personaldoctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaldoctor` (
  `PersonalDoctorID` varchar(45) NOT NULL,
  `PersonalDoctorName` varchar(45) DEFAULT NULL,
  `PersonalDoctorEmail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PersonalDoctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personaldoctor`
--

LOCK TABLES `personaldoctor` WRITE;
/*!40000 ALTER TABLE `personaldoctor` DISABLE KEYS */;
INSERT INTO `personaldoctor` VALUES ('1','Muhamad Ali','blabla@gmail.com'),('2','Yaser Arafat','asdfas@gmail.com');
/*!40000 ALTER TABLE `personaldoctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privateclniic`
--

DROP TABLE IF EXISTS `privateclniic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `privateclniic` (
  `PrivateClinicName` varchar(45) NOT NULL,
  `PrivateClinicEmail` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`PrivateClinicName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privateclniic`
--

LOCK TABLES `privateclniic` WRITE;
/*!40000 ALTER TABLE `privateclniic` DISABLE KEYS */;
INSERT INTO `privateclniic` VALUES ('Klalit','Klalit@gmail.com'),('Maccabi','Maccabi@gmail.com'),('Meuhedet','Meuhedet@gmail.com');
/*!40000 ALTER TABLE `privateclniic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `tryleavea`
--

DROP TABLE IF EXISTS `tryleavea`;
/*!50001 DROP VIEW IF EXISTS `tryleavea`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `tryleavea` AS SELECT 
 1 AS `ptID`,
 1 AS `ptFirstName`,
 1 AS `ptLastName`,
 1 AS `ptEmail`,
 1 AS `ptPhone`,
 1 AS `ptPrivateClinic`,
 1 AS `ptDoctorID`,
 1 AS `ptIsRegistered`,
 1 AS `ptLeaveDate`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `tryleaveb`
--

DROP TABLE IF EXISTS `tryleaveb`;
/*!50001 DROP VIEW IF EXISTS `tryleaveb`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `tryleaveb` AS SELECT 
 1 AS `weekNuml`,
 1 AS `LeaveClients`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `trynoshowa`
--

DROP TABLE IF EXISTS `trynoshowa`;
/*!50001 DROP VIEW IF EXISTS `trynoshowa`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `trynoshowa` AS SELECT 
 1 AS `apsID`,
 1 AS `apsPtID`,
 1 AS `apsDate`,
 1 AS `apsTime`,
 1 AS `apsCreateDate`,
 1 AS `apsCreateTime`,
 1 AS `apsStatus`,
 1 AS `apsDocID`,
 1 AS `apsSummery`,
 1 AS `apsStartTime`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `trynoshowb`
--

DROP TABLE IF EXISTS `trynoshowb`;
/*!50001 DROP VIEW IF EXISTS `trynoshowb`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `trynoshowb` AS SELECT 
 1 AS `weekNumns`,
 1 AS `NumOfNoshows`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `trypa`
--

DROP TABLE IF EXISTS `trypa`;
/*!50001 DROP VIEW IF EXISTS `trypa`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `trypa` AS SELECT 
 1 AS `apsID`,
 1 AS `apsPtID`,
 1 AS `apsDate`,
 1 AS `apsTime`,
 1 AS `apsCreateDate`,
 1 AS `apsCreateTime`,
 1 AS `apsStatus`,
 1 AS `apsDocID`,
 1 AS `apsSummery`,
 1 AS `apsStartTime`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `trypb`
--

DROP TABLE IF EXISTS `trypb`;
/*!50001 DROP VIEW IF EXISTS `trypb`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `trypb` AS SELECT 
 1 AS `weekNumar`,
 1 AS `NumOfPatients`,
 1 AS `AvgProcessTime`,
 1 AS `AvgWaitingTime`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uID` varchar(45) NOT NULL,
  `uPassword` varchar(45) DEFAULT NULL,
  `uFirstName` varchar(45) DEFAULT NULL,
  `uLastName` varchar(45) DEFAULT NULL,
  `uEmail` varchar(45) DEFAULT NULL,
  `ucID` int(11) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uID`),
  KEY `cID_idx` (`ucID`),
  CONSTRAINT `cID` FOREIGN KEY (`ucID`) REFERENCES `clinic` (`cID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('4000','1234','Amir','Sht','amir@gm.com',1,'CUSTOMER_SERVICE'),('5000','1234','Moshe','Pinto','Pinto@gmail.com',1,'DOCTOR'),('5001','1234','Eran','Tamir','Eran.Tamir@walla.co.il',2,'DOCTOR'),('5002','1234','Yossi','Leitner','Yossi.Leitner@walla.co.il',3,'DOCTOR'),('5003','1234','Hossam','Ra\'d','Hossam.Rara@walla.com',4,'DOCTOR'),('5004','1234','Viktor','Klein','ViktorVikKlein@yahoo.com',5,'DOCTOR'),('5005','1234','Slava','Babayev','SlavSlavaBBB@gmail.com',6,'DOCTOR'),('5006','1234','Vitaliy','Gitman','Vital@iol.com',7,'DOCTOR'),('5007','1234','Alexei','Yad-Shalom','Alexei@yahoo.coom',1,'DOCTOR'),('5008','1234','Vladimir','Ruhman','Vladimirruhruh@yahoo.com',2,'DOCTOR'),('5009','1234','Guy','Reznik','guyrez@walla.com',3,'DOCTOR'),('5010','1234','Guri','Alfi','AlfiGurgur@gmail.com',4,'DOCTOR'),('5011','1234','Ovadia','Cohen','ovad@gmail.com',5,'DOCTOR'),('5012','1234','Ilya','Vered','ilyaver@gmail.com',6,'DOCTOR'),('5013','1234','Shasha','Stoyko','Stoykoshasha@gmail.com',7,'DOCTOR'),('5014','1234','Sveta','Aronov','SvetaRonova@gmail.com',5,'DOCTOR'),('5015','1234','Irena','Glushko','Irenaglushko@gmail.com',2,'DOCTOR'),('6000','1234','Lauren','Smith','SmithMail@gmail.com',6,'LAB_WORKER'),('6001','1234','Isabelle','Olaru','OlaruMail@gmail.com',5,'LAB_WORKER'),('6002','1234','Florina','Tilea','Tilea.Not@gmail.com',4,'LAB_WORKER'),('7001','1234','Yoram','Mendel','Yoram2cool@gmail.com',1,'CLINIC_MANAGER'),('7002','1234','Avner','Fisher','Avner@gmail.com',2,'CLINIC_MANAGER'),('7003','1234','Matan','Boochbinder','Matan888@gmail.com',3,'CLINIC_MANAGER'),('7004','1234','Yoav','Izner','shoomBatzal@gmail.com',4,'CLINIC_MANAGER'),('7005','1234','Dan','Brochman','Sunshine@gmail.com',5,'CLINIC_MANAGER'),('7006','1234','Amos','Dichter','FlowerGirl@gmail.com',6,'CLINIC_MANAGER'),('7007','1234','Gidon','Dorfman','Loveyourself@gmail.com',7,'CLINIC_MANAGER'),('8000','1234','Baruch','Frankfurter','FunAndLove@gmail.com',7,'GENERAL_MANAGER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vweeklyrep`
--

DROP TABLE IF EXISTS `vweeklyrep`;
/*!50001 DROP VIEW IF EXISTS `vweeklyrep`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vweeklyrep` AS SELECT 
 1 AS `apsDate`,
 1 AS `AvgProcessTime`,
 1 AS `AvgWaitingTime`,
 1 AS `NumOfPatients`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `weeks`
--

DROP TABLE IF EXISTS `weeks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weeks` (
  `idweeks` int(11) NOT NULL,
  PRIMARY KEY (`idweeks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weeks`
--

LOCK TABLES `weeks` WRITE;
/*!40000 ALTER TABLE `weeks` DISABLE KEYS */;
INSERT INTO `weeks` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),(33),(34),(35),(36),(37),(38),(39),(40),(41),(42),(43),(44),(45),(46),(47),(48),(49),(50),(51),(52);
/*!40000 ALTER TABLE `weeks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ghealth'
--
/*!50003 DROP PROCEDURE IF EXISTS `filldates` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `filldates`(dateStart DATE, dateEnd DATE)
BEGIN
  WHILE dateStart <= dateEnd DO
    INSERT INTO calendar (calendar_date) VALUES (dateStart);
    SET dateStart = date_add(dateStart, INTERVAL 1 DAY);
  END WHILE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `nmonthlyview`
--

/*!50001 DROP VIEW IF EXISTS `nmonthlyview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `nmonthlyview` AS (select `ar`.`weekNumar` AS `weekNumar`,`ar`.`NumOfPatients` AS `NumOfPatients`,`ar`.`AvgProcessTime` AS `AvgProcessTime`,`ar`.`AvgWaitingTime` AS `AvgWaitingTime`,`ns`.`weekNumns` AS `weekNumns`,`ns`.`NumOfNoshows` AS `NumOfNoshows`,`l`.`weekNuml` AS `weekNuml`,`l`.`LeaveClients` AS `LeaveClients` from ((`trypb` `ar` join `trynoshowb` `ns`) join `tryleaveb` `l`) where ((`ns`.`weekNumns` = `l`.`weekNuml`) and (`l`.`weekNuml` = `ar`.`weekNumar`) and (`ns`.`weekNumns` = `ar`.`weekNumar`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tryleavea`
--

/*!50001 DROP VIEW IF EXISTS `tryleavea`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `tryleavea` AS (select `p`.`ptID` AS `ptID`,`p`.`ptFirstName` AS `ptFirstName`,`p`.`ptLastName` AS `ptLastName`,`p`.`ptEmail` AS `ptEmail`,`p`.`ptPhone` AS `ptPhone`,`p`.`ptPrivateClinic` AS `ptPrivateClinic`,`p`.`ptDoctorID` AS `ptDoctorID`,`p`.`ptIsRegistered` AS `ptIsRegistered`,`p`.`ptLeaveDate` AS `ptLeaveDate` from `patient` `p` where ((`p`.`ptIsRegistered` = 'NOT_REG') and (`p`.`ptLeaveDate` is not null))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tryleaveb`
--

/*!50001 DROP VIEW IF EXISTS `tryleaveb`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `tryleaveb` AS (select `weeks`.`idweeks` AS `weekNuml`,count(`pa`.`ptLeaveDate`) AS `LeaveClients` from (`weeks` left join `tryleavea` `pa` on((week(`pa`.`ptLeaveDate`,0) = `weeks`.`idweeks`))) where (((`weeks`.`idweeks` >= week('2016-05-15',0)) and (`weeks`.`idweeks` <= week('2016-06-13',0)) and isnull(`pa`.`ptLeaveDate`)) or ((`pa`.`ptIsRegistered` = 'NOT_REG') and (`pa`.`ptLeaveDate` >= '2016-05-15') and (`pa`.`ptLeaveDate` <= '2016-06-13'))) group by `weeks`.`idweeks` order by `weeks`.`idweeks`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `trynoshowa`
--

/*!50001 DROP VIEW IF EXISTS `trynoshowa`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `trynoshowa` AS (select `a`.`apsID` AS `apsID`,`a`.`apsPtID` AS `apsPtID`,`a`.`apsDate` AS `apsDate`,`a`.`apsTime` AS `apsTime`,`a`.`apsCreateDate` AS `apsCreateDate`,`a`.`apsCreateTime` AS `apsCreateTime`,`a`.`apsStatus` AS `apsStatus`,`a`.`apsDocID` AS `apsDocID`,`a`.`apsSummery` AS `apsSummery`,`a`.`apsStartTime` AS `apsStartTime` from `appointmentsettings` `a` where (`a`.`apsStatus` = 'NOSHOW')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `trynoshowb`
--

/*!50001 DROP VIEW IF EXISTS `trynoshowb`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `trynoshowb` AS (select `weeks`.`idweeks` AS `weekNumns`,count(distinct `a`.`apsPtID`) AS `NumOfNoshows` from (`weeks` left join `trynoshowa` `a` on((week(`a`.`apsDate`,0) = `weeks`.`idweeks`))) where (((`weeks`.`idweeks` >= week('2016-05-15',0)) and (`weeks`.`idweeks` <= week('2016-06-13',0)) and isnull(`a`.`apsStatus`)) or ((`a`.`apsStatus` = 'NOSHOW') and (`a`.`apsDate` >= '2016-05-15') and (`a`.`apsDate` <= '2016-06-13') and `a`.`apsDocID` in (select `doc`.`uID` from `user` `doc` where (`doc`.`ucID` = 2)))) group by `weeks`.`idweeks`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `trypa`
--

/*!50001 DROP VIEW IF EXISTS `trypa`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `trypa` AS (select `a`.`apsID` AS `apsID`,`a`.`apsPtID` AS `apsPtID`,`a`.`apsDate` AS `apsDate`,`a`.`apsTime` AS `apsTime`,`a`.`apsCreateDate` AS `apsCreateDate`,`a`.`apsCreateTime` AS `apsCreateTime`,`a`.`apsStatus` AS `apsStatus`,`a`.`apsDocID` AS `apsDocID`,`a`.`apsSummery` AS `apsSummery`,`a`.`apsStartTime` AS `apsStartTime` from `appointmentsettings` `a` where (`a`.`apsStatus` = 'ARRIVED')) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `trypb`
--

/*!50001 DROP VIEW IF EXISTS `trypb`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `trypb` AS (select `weeks`.`idweeks` AS `weekNumar`,count(distinct `a`.`apsPtID`) AS `NumOfPatients`,avg((to_days(`a`.`apsDate`) - to_days(`a`.`apsCreateDate`))) AS `AvgProcessTime`,avg((timediff(`a`.`apsStartTime`,`a`.`apsTime`) / 60)) AS `AvgWaitingTime` from (`weeks` left join `trypa` `a` on((week(`a`.`apsDate`,0) = `weeks`.`idweeks`))) where (((`weeks`.`idweeks` >= week('2016-05-15',0)) and (`weeks`.`idweeks` <= week('2016-06-13',0)) and isnull(`a`.`apsStatus`)) or ((`a`.`apsStatus` = 'ARRIVED') and (`a`.`apsDate` >= '2016-05-15') and (`a`.`apsDate` <= '2016-06-13') and `a`.`apsDocID` in (select `doc`.`uID` from `user` `doc` where (`doc`.`ucID` = 2)))) group by `weeks`.`idweeks` order by `weeks`.`idweeks`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vweeklyrep`
--

/*!50001 DROP VIEW IF EXISTS `vweeklyrep`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vweeklyrep` AS (select `calendar`.`calendar_date` AS `apsDate`,avg((to_days(`a`.`apsDate`) - to_days(`a`.`apsCreateDate`))) AS `AvgProcessTime`,avg(timediff(`a`.`apsStartTime`,`a`.`apsTime`)) AS `AvgWaitingTime`,count(distinct `a`.`apsPtID`) AS `NumOfPatients` from (`calendar` left join `trypa` `a` on((`a`.`apsDate` = `calendar`.`calendar_date`))) where (isnull(`a`.`apsStatus`) or ((`a`.`apsStatus` = 'ARRIVED') and (`a`.`apsDate` >= '2016-06-05') and (`a`.`apsDate` <= '2016-06-12') and `a`.`apsDocID` in (select `doc`.`uID` from `user` `doc` where (`doc`.`ucID` = '7')))) group by `calendar`.`calendar_date`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-13 10:00:18
