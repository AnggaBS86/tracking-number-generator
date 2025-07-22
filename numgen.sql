-- MySQL dump 10.13  Distrib 8.0.42, for Linux (x86_64)
--
-- Host: localhost    Database: numgen
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tracking_numbers`
--

DROP TABLE IF EXISTS `tracking_numbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tracking_numbers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tracking_number` varchar(16) NOT NULL,
  `origin_country_id` varchar(2) NOT NULL,
  `destination_country_id` varchar(2) NOT NULL,
  `weight` decimal(10,3) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `customer_id` binary(16) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `customer_slug` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tracking_number` (`tracking_number`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracking_numbers`
--

LOCK TABLES `tracking_numbers` WRITE;
/*!40000 ALTER TABLE `tracking_numbers` DISABLE KEYS */;
INSERT INTO `tracking_numbers` VALUES (52,'MYID4KT07CFIC4JK','MY','ID',1.234,'2025-07-21 11:00:00.000000',_binary '\ﬁaòTµõB^ù¥î9y\·ΩI','RedBox Logistics','redbox-logistics'),(53,'MYID4KT07H76UW3K','MY','ID',1.234,'2025-07-21 11:00:00.000000',_binary '\ﬁaòTµõB^ù¥î9y\·ΩI','RedBox Logistics','redbox-logistics'),(54,'MYID4KT07ISWIFPC','MY','ID',1.234,'2025-07-21 11:00:00.000000',_binary '\ﬁaòTµõB^ù¥î9y\·ΩI','RedBox Logistics','redbox-logistics'),(55,'MYID4KT07K7BYN0G','MY','ID',1.234,'2025-07-21 11:00:00.000000',_binary '\ﬁaòTµõB^ù¥î9y\·ΩI','RedBox Logistics','redbox-logistics'),(102,'MYID4KV094AKKH6O','MY','ID',1.234,'2025-07-21 11:00:00.000000',_binary '\ﬁaòTµõB^ù¥î9y\·ΩI','RedBox Logistics','redbox-logistics'),(103,'MYID4KV09TWV2TC0','MY','ID',1.234,'2025-07-21 11:00:00.000000',_binary '\ﬁaòTµõB^ù¥î9y\·ΩI','RedBox Logistics','redbox-logistics'),(152,'MYID4KV47WW5X8U8','MY','ID',1.234,'2025-07-21 11:00:00.000000',_binary '\ﬁaòTµõB^ù¥î9y\·ΩI','RedBox Logistics','redbox-logistics');
/*!40000 ALTER TABLE `tracking_numbers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking_numbers_seq`
--

DROP TABLE IF EXISTS `tracking_numbers_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tracking_numbers_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracking_numbers_seq`
--

LOCK TABLES `tracking_numbers_seq` WRITE;
/*!40000 ALTER TABLE `tracking_numbers_seq` DISABLE KEYS */;
INSERT INTO `tracking_numbers_seq` VALUES (251);
/*!40000 ALTER TABLE `tracking_numbers_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-22 14:54:57
