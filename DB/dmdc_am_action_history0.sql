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
-- Table structure for table `am_action_history`
--

DROP TABLE IF EXISTS `am_action_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `am_action_history` (
  `action_history_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `action` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `module` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `date_action` date DEFAULT NULL,
  `detail_action` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `record_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `product` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `manipulation` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `detail_action1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`action_history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `am_action_history`
--

LOCK TABLES `am_action_history` WRITE;
/*!40000 ALTER TABLE `am_action_history` DISABLE KEYS */;
INSERT INTO `am_action_history` VALUES (1,'admin','Created','PRODUCT','2020-03-10','com.ncs.entity.ProductEntity@1caf11c','0','dsdsdsdsd',NULL,NULL,NULL,'Tạo mới sản phẩm','1',NULL),(2,'admin','Deleted','PRODUCT','2020-03-10','com.ncs.entity.ProductEntity@532b71ef','57','hihihiiisiadas',NULL,NULL,NULL,'Xóa Huyện','0',NULL),(3,'admin','Deleted','PRODUCT','2020-03-10','com.ncs.entity.ProductEntity@34bd4fce','58','hihihiiisiadas',NULL,NULL,NULL,'Xóa Sản Phẩm','0',NULL),(4,'admin','Created','CATEGORY','2020-03-10','com.ncs.entity.CategoryEntity@7d64d6ac','0',NULL,NULL,'ô tô địa hình',NULL,'Tạo mới thể loại','1',NULL),(5,'admin','Created','MANUFACTURER','2020-03-10','com.ncs.entity.ManufacturerEntity@d8ef70c9','0',NULL,NULL,NULL,'Vinfast','Tạo mới nhà sản xuất','1',NULL),(6,'admin','Created','CATEGORY','2020-03-10','com.ncs.entity.CategoryEntity@b126ce81','0',NULL,NULL,'dsadsa',NULL,'Tạo mới thể loại','1',NULL),(7,'admin','Deleted','CATEGORY','2020-03-10','com.ncs.entity.CategoryEntity@b126d033','14',NULL,NULL,'dsadsa',NULL,'Xóa thể loại','0',NULL),(8,'admin','Created','MANUFACTURER','2020-03-10','com.ncs.entity.ManufacturerEntity@2ad3272','0',NULL,NULL,NULL,'dsadsa','Tạo mới nhà sản xuất','1',NULL),(9,'admin','Deleted','MANUFACTURER','2020-03-10','com.ncs.entity.ManufacturerEntity@2b060da','7',NULL,NULL,NULL,'dsadsa','Xóa nhà sản xuất','0',NULL),(10,'admin','Updated','MANUFACTURER','2020-03-10','com.ncs.entity.ManufacturerEntity@9db0d887','5',NULL,NULL,NULL,'Vinfastcdc','chỉnh sửa nhà sản xuất','2',NULL),(11,'admin','Updated','ACCOUNT','2020-03-10','  Mã: 8, Tên Tài khoản: nguyencongson1998, email thangcubin198@gmail.com@gmail.com@gmail.com, Mật Khẩu: 1998s, Thời gian tạo: null, Thời gian tạo: null, Active: 1','8',NULL,'nguyencongson1998',NULL,NULL,'Chỉnh sửa tài khoản','2','Dữ liệu mới:   Mã: 8, Tên Tài khoản: nguyencongson1998, email thangcubin198@gmail.com@gmail.com@gmail.com, Mật Khẩu: 1998s, Thời gian tạo: null, Thời gian tạo: null, Active: 1'),(12,'admin','Created','PRODUCT','2020-03-10','  Mã: 0, Tên sản phẩm: dsadsa, Mô tả dsd, Giá: 211.0, Thời gian tạo: null, Số lượng: 121, Nhà sản xuất: 2,Thể loại:2,Trạng thái:null','0','dsadsa',NULL,NULL,NULL,'Tạo mới sản phẩm','1',NULL),(13,'admin','Created','CATEGORY','2020-03-11','  Mã: 0, Tên Thể loại: ssd, Active: null','0',NULL,NULL,'ssd',NULL,'Tạo mới thể loại','1',NULL),(14,'admin','Updated','CATEGORY','2020-03-11','Dữ liệu cũ:   Mã: 15, Tên Thể loại: ssd, Active: 1','15',NULL,NULL,'ssddsds',NULL,'Chỉnh sửa thể loại','2','Dữ liệu mới:   Mã: 15, Tên Thể loại: ssddsds, Active: null'),(15,'admin','Created','ACCOUNT','2020-03-14','  Mã: 0, Tên Tài khoản: dsa, email dsada, Mật Khẩu: dsada, Thời gian tạo: null, Thời gian tạo: null, Active: 1','0',NULL,'dsa',NULL,NULL,'Tạo mới tài khoản','1',NULL),(16,'admin','Created','ACCOUNT','2020-03-14','  Mã: 0, Tên Tài khoản: dsa, email dsa, Mật Khẩu: dsa, Thời gian tạo: null, Thời gian tạo: null, Active: 1','0',NULL,'dsa',NULL,NULL,'Tạo mới tài khoản','1',NULL),(17,'admin','Created','ACCOUNT','2020-03-14','  Mã: 0, Tên Tài khoản: sad, email dsada, Mật Khẩu: dấda, Thời gian tạo: null, Thời gian tạo: null, Active: 1','0',NULL,'sad',NULL,NULL,'Tạo mới tài khoản','1',NULL),(18,'admin','Created','PRODUCT','2020-03-14','  Mã: 0, Tên sản phẩm: dsadsa, Mô tả 213, Giá: 2.121321321312E12, Thời gian tạo: null, Số lượng: 21321, Nhà sản xuất: 2,Thể loại:2,Trạng thái:null','0','dsadsa',NULL,NULL,NULL,'Tạo mới sản phẩm','1',NULL);
/*!40000 ALTER TABLE `am_action_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-28  9:04:25
