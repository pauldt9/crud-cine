-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: crud_cine
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `idEmployee` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `employeeType` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (20,'Jose ','Rafael','Taquillero','Hbs650','$2a$10$e4K62oAXdWce6U.2P.4BsuE51dgHnD82F34SspKl8.biC2/FOgegS'),(21,'Paul','Miguel','Taquillero','Miguelon','$2a$10$NH3rc3c.TDKdIz5aRq.Xk.w1iArYj.YTjORyTbdMk94u1OCur8L..'),(34,'a','a','Taquillero','aa','$2a$10$KPSqz1GExorDNHUFj5RmJujYi1nIpyJSn2IYJzJYVl2LAvtXXE7AW'),(35,'Gibran','Genji','Taquillero','Girban','$2a$10$UFMtWjclHCXywcwFTFkNm.NPmBhpH5lZZUu1GL.xLH3E3B/3lR2TO');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `functions`
--

DROP TABLE IF EXISTS `functions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `functions` (
  `idFunction` int NOT NULL AUTO_INCREMENT,
  `idMovie` int NOT NULL,
  `idRoom` int NOT NULL,
  `showTime` varchar(45) NOT NULL,
  PRIMARY KEY (`idFunction`),
  KEY `fk_idMovie_idx` (`idMovie`),
  KEY `fk_idRoom_idx` (`idRoom`),
  CONSTRAINT `fk_idMovie_functions` FOREIGN KEY (`idMovie`) REFERENCES `movies` (`idMovie`),
  CONSTRAINT `fk_idRoom_functions` FOREIGN KEY (`idRoom`) REFERENCES `rooms` (`idRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functions`
--

LOCK TABLES `functions` WRITE;
/*!40000 ALTER TABLE `functions` DISABLE KEYS */;
/*!40000 ALTER TABLE `functions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `idMovie` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `duration` int NOT NULL,
  `genre` varchar(45) NOT NULL,
  `classification` varchar(45) NOT NULL,
  `imgRoute` varchar(100) NOT NULL,
  PRIMARY KEY (`idMovie`),
  UNIQUE KEY `title_UNIQUE` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (13,'Suzume',122,'Drama','A','moviesImages/suzume.png'),(14,'Avengers Infinity War',149,'Accion','B','moviesImages/avengers infinity war.png');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occupiedseats`
--

DROP TABLE IF EXISTS `occupiedseats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occupiedseats` (
  `idReservation` int NOT NULL AUTO_INCREMENT,
  `idSeat` int NOT NULL,
  `idFunction` int NOT NULL,
  `isOccupied` tinyint NOT NULL,
  PRIMARY KEY (`idReservation`),
  KEY `fk_idSeat_idx` (`idSeat`),
  KEY `fk_schedule_occupiedseats_idx` (`idFunction`),
  CONSTRAINT `fk_idFunction_occupiedseats` FOREIGN KEY (`idFunction`) REFERENCES `functions` (`idFunction`),
  CONSTRAINT `fk_idSeat_occupiedseats` FOREIGN KEY (`idSeat`) REFERENCES `seats` (`idSeat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupiedseats`
--

LOCK TABLES `occupiedseats` WRITE;
/*!40000 ALTER TABLE `occupiedseats` DISABLE KEYS */;
/*!40000 ALTER TABLE `occupiedseats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `idRoom` int NOT NULL AUTO_INCREMENT,
  `roomName` varchar(45) NOT NULL,
  `capacity` int NOT NULL,
  `roomType` varchar(45) NOT NULL,
  `roomRows` int NOT NULL,
  `roomCols` int NOT NULL,
  PRIMARY KEY (`idRoom`),
  UNIQUE KEY `roomName_UNIQUE` (`roomName`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `idSeat` int NOT NULL AUTO_INCREMENT,
  `idRoom` int NOT NULL,
  `seatName` varchar(45) NOT NULL,
  `seatNumber` int NOT NULL,
  PRIMARY KEY (`idSeat`),
  KEY `fk_idRoom_idx` (`idRoom`),
  CONSTRAINT `fk_idRoom_seats` FOREIGN KEY (`idRoom`) REFERENCES `rooms` (`idRoom`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `idFunction` int NOT NULL,
  `idSeat` int NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `fk_idFunction_tickets_idx` (`idFunction`),
  KEY `fk_idSeats_tickets_idx` (`idSeat`),
  CONSTRAINT `fk_idFunction_tickets` FOREIGN KEY (`idFunction`) REFERENCES `functions` (`idFunction`),
  CONSTRAINT `fk_idSeats_tickets` FOREIGN KEY (`idSeat`) REFERENCES `seats` (`idSeat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-04  2:36:45
