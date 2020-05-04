-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: dmdc
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `LAST_ACCESS` date DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ACCOUNT_ROLE_idx` (`ROLE_ID`),
  CONSTRAINT `FK_ACCOUNT_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'nbduc1996','nbduc1996@gmail.com','1234',1,'2020-03-10','2020-03-10',1),(2,'ducit196','ducit196@gmail.com','1234',1,'2020-03-10','2020-03-10',1),(4,'dddd','ddd','dsd',2,'2020-03-10','2020-03-10',1),(6,'lam96','ocho123','1234',2,'2020-03-10','2020-03-10',1),(7,'admin','admin','$2b$10$3SDUzmTkVjjDOlXC9rCqhOf7oUA8VMr.JhQ9mDk7QGyUua6qmYzrO',1,'2020-03-10','2020-03-10',1),(8,'nguyencongson1998','thangcubin198@gmail.com@gmail.com@gmail.com','1998s',1,'2020-03-10','2020-03-10',1),(9,'nguyencongson','thangcubin198@gmail.com@gmail.com','1234ds',1,'2020-03-10','2020-03-10',1),(10,'rurikeigo','thientamyp@gmail.com@gmail.com','Nguyentam99',1,'2020-03-03','2020-03-03',1),(11,'dsa','dsada@gmail.com','dsada',1,'2020-03-14','2020-03-14',1),(12,'dsa','dsa@gmail.com','dsa',1,'2020-03-14','2020-03-14',1),(13,'sad','dsada@gmail.com','dấda',1,'2020-03-14','2020-03-14',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-04 15:37:19
