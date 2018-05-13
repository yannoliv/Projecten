-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: db_Project
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `HighScores`
--

DROP TABLE IF EXISTS `HighScores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HighScores` (
  `spelerNr` int(11) NOT NULL AUTO_INCREMENT,
  `spelerNaam` varchar(45) DEFAULT NULL,
  `spelerPunten` int(11) DEFAULT NULL,
  PRIMARY KEY (`spelerNr`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HighScores`
--

LOCK TABLES `HighScores` WRITE;
/*!40000 ALTER TABLE `HighScores` DISABLE KEYS */;
INSERT INTO `HighScores` VALUES (1,'King',150),(2,'Roberto',111),(3,'Juliano',50),(4,'Yannick',120),(5,'XXPO',70),(6,'tarantino',30),(7,'Bertje',109),(8,'King',55),(9,'Rayen',60),(10,'Bertje',20),(11,'King',60),(12,'Bertje',100),(13,'Roberto',99);
/*!40000 ALTER TABLE `HighScores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HuttenLijst`
--

DROP TABLE IF EXISTS `HuttenLijst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HuttenLijst` (
  `hutNr` int(11) NOT NULL AUTO_INCREMENT,
  `hutLijstNr` int(1) DEFAULT NULL,
  `rNaam1` varchar(45) DEFAULT NULL,
  `rAantal1` int(1) DEFAULT NULL,
  `rNaam2` varchar(45) DEFAULT NULL,
  `rAantal2` int(1) DEFAULT NULL,
  `rNaam3` varchar(45) DEFAULT NULL,
  `rAantal3` int(1) DEFAULT NULL,
  `punten` int(11) DEFAULT NULL,
  `aantalSpots` int(11) DEFAULT NULL,
  PRIMARY KEY (`hutNr`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HuttenLijst`
--

LOCK TABLES `HuttenLijst` WRITE;
/*!40000 ALTER TABLE `HuttenLijst` DISABLE KEYS */;
INSERT INTO `HuttenLijst` VALUES (1,0,'hout',3,'steen',2,'goud',2,15,1),(2,0,'hout',3,'steen',1,'goud',1,12,1),(3,0,'hout',1,'leem',2,'goud',4,14,1),(4,0,'steen',3,'hout',2,'leem',3,14,1),(5,0,'steen',1,'goud',1,'leem',3,15,1),(6,0,'goud',1,'hout',3,'steen',1,12,1),(7,0,'hout',1,'steen',4,'leem',4,12,1),(8,1,'steen',2,'leem',1,'hout',2,14,0),(9,1,'steen',1,'hout',2,'goud',3,15,1),(10,1,'leem',2,'steen',4,'hout',4,17,1),(11,1,'leem',2,'hout',4,'goud',3,15,1),(12,1,'goud',1,'hout',1,'leem',1,14,1),(13,1,'goud',3,'steen',2,'leem',3,12,1),(14,1,'hout',1,'goud',2,'steen',3,15,1),(15,2,'goud',1,'hout',1,'leem',2,14,1),(16,2,'steen',2,'hout',3,'leem',2,12,1),(17,2,'goud',1,'hout',2,'leem',4,12,1),(18,2,'steen',2,'hout',3,'leem',2,15,1),(19,2,'leem',2,'hout',3,'steen',1,13,1),(20,2,'goud',2,'hout',4,'steen',4,16,1),(21,2,'goud',3,'steen',4,'leem',2,14,1),(22,3,'goud',4,'hout',3,'steen',4,16,1),(23,3,'steen',4,'steen',1,'leem',3,17,1),(24,3,'goud',2,'leem',2,'steen',2,14,1),(25,3,'steen',1,'leem',3,'steen',3,15,1),(26,3,'leem',3,'steen',2,'hout',2,13,1),(27,3,'goud',1,'hout',1,'goud',1,12,1),(28,3,'goud',3,'hout',2,'leem',2,14,1);
/*!40000 ALTER TABLE `HuttenLijst` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SpelerLijst`
--

DROP TABLE IF EXISTS `SpelerLijst`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SpelerLijst` (
  `spelerID` int(1) NOT NULL AUTO_INCREMENT,
  `spelerNaam` varchar(45) NOT NULL,
  `rHout` int(1) unsigned zerofill DEFAULT '0',
  `pHout` int(1) unsigned zerofill DEFAULT '0',
  `rLeem` int(1) unsigned zerofill DEFAULT '0',
  `pLeem` int(1) unsigned zerofill DEFAULT '0',
  `rSteen` int(1) unsigned zerofill DEFAULT '0',
  `pSteen` int(1) unsigned zerofill DEFAULT '0',
  `rGoud` int(1) unsigned zerofill DEFAULT '0',
  `pGoud` int(1) unsigned zerofill DEFAULT '0',
  `rVoedsel` int(1) unsigned zerofill DEFAULT '12',
  `pVoedsel` int(1) unsigned zerofill DEFAULT '0',
  `rAkkerbouw` int(1) unsigned zerofill DEFAULT '0',
  `pAkkerbouw` int(1) unsigned zerofill DEFAULT '0',
  `rGereedschap` int(1) unsigned zerofill DEFAULT '0',
  `pSmith` int(1) unsigned zerofill DEFAULT '0',
  `rStamleden` int(1) unsigned zerofill DEFAULT '5',
  `pHut` int(1) unsigned zerofill DEFAULT '0',
  `gebruikteStamleden` int(1) unsigned zerofill DEFAULT NULL,
  `rPunten` int(1) unsigned zerofill DEFAULT '0',
  `hut1` int(1) unsigned zerofill DEFAULT '0',
  `hut2` int(1) unsigned zerofill DEFAULT '0',
  `hut3` int(1) unsigned zerofill DEFAULT '0',
  `hut4` int(1) unsigned zerofill DEFAULT NULL,
  `isAanBeurt` int(11) DEFAULT '0',
  PRIMARY KEY (`spelerID`),
  UNIQUE KEY `spelerId_UNIQUE` (`spelerID`),
  UNIQUE KEY `idx_highScores_spelerId` (`spelerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SpelerLijst`
--

LOCK TABLES `SpelerLijst` WRITE;
/*!40000 ALTER TABLE `SpelerLijst` DISABLE KEYS */;
/*!40000 ALTER TABLE `SpelerLijst` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-13 14:50:29
