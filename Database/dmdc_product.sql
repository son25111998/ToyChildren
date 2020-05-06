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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) DEFAULT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  `THUMBAI` varchar(45) DEFAULT NULL,
  `PRICE` float NOT NULL,
  `amount` int(11) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `MANUFACTURER_ID` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `lenght` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_PRODUCT_MANUFACTURER1_idx` (`MANUFACTURER_ID`),
  KEY `fk_PRODUCT_CATEGORY1_idx` (`CATEGORY_ID`),
  CONSTRAINT `fk_PRODUCT_CATEGORY1` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`),
  CONSTRAINT `fk_PRODUCT_MANUFACTURER1` FOREIGN KEY (`MANUFACTURER_ID`) REFERENCES `manufacturer` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'Đồ chơi trẻ con 1998','bdddđ','5.jpg',34000,1,NULL,NULL,2,2,'2020-04-27','2020-04-27',NULL,NULL,NULL,1,10),(3,'Ô tô','c','8.jpg',12,1,'1','1',1,5,'2020-04-27','2020-04-27',NULL,NULL,NULL,1,50),(7,'Robot','2','5.jpg',500,1,'1','1',1,5,'2020-04-27','2020-04-27',NULL,NULL,NULL,1,0),(8,'Đồ chơi xếp hình','dsa','4.jpg',22,1,'admin','admin',1,5,'2020-04-27','2020-04-27',NULL,NULL,NULL,1,0),(9,'Đồ chơi trẻ con','das','8.jpg',2,1,'admin','admin',1,5,'2020-04-27','2020-04-27',NULL,NULL,NULL,1,0),(10,'Moto','das','5.jpg',2,1,'1','1',1,5,'2020-04-27','2020-04-27',NULL,NULL,NULL,1,0),(11,'Xếp hình','dasd','7.jpg',2000,1,'1','1',1,5,'2020-04-27','2020-04-27',NULL,NULL,NULL,1,0),(13,'cdđdsadsa','b','a.jpeg',3400,112121,'admin','admin',1,5,'2020-02-21','2020-02-21',NULL,NULL,NULL,1,0),(40,'dsds','1221','c.jpeg',2100,21121,'admin','admin',3,2,'2020-02-24','2020-02-24',NULL,NULL,NULL,1,0),(41,'nguyencongson','212121','h.jpeg',5000,112121,'admin','admin',1,2,'2020-02-24','2020-02-24',NULL,NULL,NULL,1,0),(42,'hahahahh','2121','b.jpeg',1210,21221,'admin','admin',2,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(43,'ưew','121','h.jpeg',1210,121,'admin','admin',2,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(44,'1','1','5.jpg',1000,1,'admin','admin',3,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(45,'ss','121','7.jpg',1210,121,'admin','admin',2,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(46,'ưq','212','4.jpg',1100,2121,'admin','admin',2,3,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(47,'ưq','212','a.jpeg',1100,2121,'admin','admin',2,3,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(48,'d','11','b.jpeg',1110,11,'admin','admin',2,3,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(49,'sd','21','a.jpeg',1210,21,'admin','admin',2,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(50,'hihihihihi','2121','b.jpeg',1210,2121,'admin','admin',1,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(51,'đ','21','h.jpeg',1210,2121,'admin','admin',2,1,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(52,'ds','21','5.jpg',1210,2121,'admin','admin',2,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(53,'son1998','qưqw','a.jpeg',20000,20000,'admin','admin',2,2,'2020-02-25','2020-02-25',NULL,NULL,NULL,1,0),(54,'1234','121','5.jpg',1212120,1212121,'admin','admin',2,2,'2020-03-05','2020-03-05',NULL,NULL,NULL,1,0),(55,'sds','ds','5.jpg',1414,121,'admin','admin',2,2,'2020-03-10','2020-04-27',NULL,NULL,NULL,1,0),(56,'ds','121','5.jpg',121,2121,'admin','admin',1,2,'2020-03-10','2020-04-27',NULL,NULL,NULL,1,0),(59,'dsdsdsdsd','21','5.jpg',21,21,'admin','admin',1,2,'2020-03-10','2020-04-27',NULL,NULL,NULL,1,0),(60,'dsadsa','dsd','5.jpg',211,121,'admin','admin',2,2,'2020-03-10','2020-04-27',NULL,NULL,NULL,1,0),(61,'dsadsa','213','5.jpg',2121320000000,21321,'admin','admin',2,2,'2020-03-14','2020-04-27',NULL,NULL,NULL,1,0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-04 15:37:16
