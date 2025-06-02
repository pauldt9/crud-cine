-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: crud_cine
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
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`idEmployee`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'when','hola','Taquillero','but','$2a$10$FWH22OBeOZtGKuStcqWdGusVzGKsQZOCoSghRv13bx8gC5puUSISO'),(4,'but','but','Taquillero','when','$2a$10$WdJcJt7yrVmFAseQAH7seeg/H5AizGrehpzASQqgwhQR/2/x9DyNu'),(6,'brr brr','patapim','Taquillero','brrpata','$2a$10$wymJW6GJlV.05FQG811ggeWRbITUzwRlUOKztqFaaSx4w1eEONLAm');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movieShowtimes`
--

DROP TABLE IF EXISTS `movieShowtimes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movieShowtimes` (
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
-- Dumping data for table `movieShowtimes`
--

LOCK TABLES `movieShowtimes` WRITE;
/*!40000 ALTER TABLE `movieShowtimes` DISABLE KEYS */;
/*!40000 ALTER TABLE `movieShowtimes` ENABLE KEYS */;
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
  PRIMARY KEY (`idMovie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
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
  PRIMARY KEY (`idRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `isOccupied` tinyint NOT NULL,
  PRIMARY KEY (`idSeat`),
  KEY `fk_idRoom_idx` (`idRoom`),
  CONSTRAINT `fk_idRoom_seats` FOREIGN KEY (`idRoom`) REFERENCES `rooms` (`idRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  CONSTRAINT `fk_idFunction_tickets` FOREIGN KEY (`idFunction`) REFERENCES `movieShowtimes` (`idFunction`),
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

-- Dump completed on 2025-05-31 22:45:41
