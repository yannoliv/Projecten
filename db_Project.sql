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
-- Table structure for table `highScores`
--

DROP TABLE IF EXISTS `highScores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `highScores` (
  `spelerID` int(1) NOT NULL AUTO_INCREMENT,
  `spelerNaam` varchar(45) NOT NULL,
  `rHout` int(1) unsigned zerofill DEFAULT '0',
  `pHout` int(1) unsigned zerofill DEFAULT '0',
  `rLeem` int(1) DEFAULT '0',
  `pLeem` int(1) DEFAULT '0',
  `rSteen` int(1) DEFAULT '0',
  `pSteen` int(1) DEFAULT '0',
  `rGoud` int(1) DEFAULT '0',
  `pGoud` int(1) DEFAULT '0',
  `rVoedsel` int(1) DEFAULT '12',
  `pVoedsel` int(1) DEFAULT '0',
  `rAkkerbouw` int(1) DEFAULT '0',
  `pAkkerbouw` int(1) DEFAULT '0',
  `rGereedschap` int(1) DEFAULT '0',
  `pSmith` int(1) DEFAULT '0',
  `rStamleden` int(1) DEFAULT '5',
  `pHut` int(1) DEFAULT '0',
  `rPunten` int(1) DEFAULT '0',
  `hut1` varchar(2) DEFAULT '0',
  `hut2` varchar(2) DEFAULT '0',
  `hut3` varchar(2) DEFAULT '0',
  `highScore` int(11) DEFAULT NULL,
  PRIMARY KEY (`spelerID`),
  UNIQUE KEY `spelerNaam_UNIQUE` (`spelerNaam`),
  UNIQUE KEY `spelerId_UNIQUE` (`spelerID`),
  UNIQUE KEY `idx_highScores_spelerId` (`spelerID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `highScores`
--

LOCK TABLES `highScores` WRITE;
/*!40000 ALTER TABLE `highScores` DISABLE KEYS */;
INSERT INTO `highScores` VALUES (1,'Yannick',9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'0','0','0',NULL),(3,'Rayen',17,37,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'0','0','0',NULL);
/*!40000 ALTER TABLE `highScores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-09 10:34:39
