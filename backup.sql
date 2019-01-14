-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: dietonator
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('mieso'),('nabial');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal` (
  `name` varchar(25) NOT NULL,
  `energy` int(11) NOT NULL DEFAULT '0',
  `fat` int(11) NOT NULL DEFAULT '0',
  `of_which_saturates` int(11) NOT NULL DEFAULT '0',
  `carbohydrates` int(11) NOT NULL DEFAULT '0',
  `of_which_sugars` int(11) NOT NULL DEFAULT '0',
  `protein` int(11) NOT NULL DEFAULT '0',
  `salt` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES ('gulasz',510,35,30,25,0,62,0);
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER meal_update_trigger AFTER UPDATE ON meal
FOR EACH ROW
BEGIN
	UPDATE nutritional_plan np INNER JOIN plan_meal pm ON pm.plan = np.name
    SET
    np.energy = np.energy - OLD.energy + NEW.energy,
    np.fat = np.fat - OLD.fat + NEW.fat,
    np.of_which_saturates = np.of_which_saturates - OLD.of_which_saturates + NEW.of_which_saturates,
    np.carbohydrates = np.carbohydrates - OLD.carbohydrates + NEW.carbohydrates,
    np.of_which_sugars = np.of_which_sugars - OLD.of_which_sugars + NEW.of_which_sugars,
    np.protein = np.protein - OLD.protein + NEW.protein,
    np.salt = np.salt - OLD.salt + NEW.salt
    WHERE pm.meal = OLD.name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER meal_delete_before_trigger BEFORE DELETE ON meal
FOR EACH ROW
BEGIN 
    DELETE FROM plan_meal WHERE meal=OLD.name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER meal_delete_trigger AFTER DELETE ON meal
FOR EACH ROW
BEGIN 
	DELETE FROM product_meal WHERE meal=OLD.name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `nutritional_plan`
--

DROP TABLE IF EXISTS `nutritional_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nutritional_plan` (
  `name` varchar(30) NOT NULL,
  `energy` int(11) NOT NULL DEFAULT '0',
  `fat` int(11) NOT NULL DEFAULT '0',
  `of_which_saturates` int(11) NOT NULL DEFAULT '0',
  `carbohydrates` int(11) NOT NULL DEFAULT '0',
  `of_which_sugars` int(11) NOT NULL DEFAULT '0',
  `protein` int(11) NOT NULL DEFAULT '0',
  `salt` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nutritional_plan`
--

LOCK TABLES `nutritional_plan` WRITE;
/*!40000 ALTER TABLE `nutritional_plan` DISABLE KEYS */;
INSERT INTO `nutritional_plan` VALUES ('Dieta cud',510,35,30,25,0,62,0);
/*!40000 ALTER TABLE `nutritional_plan` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER delete_nutritional_plan_trigger AFTER DELETE ON nutritional_plan
FOR EACH ROW 
BEGIN 
	DELETE FROM plan_meal WHERE plan = OLD.name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `plan_meal`
--

DROP TABLE IF EXISTS `plan_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan_meal` (
  `plan` varchar(30) NOT NULL,
  `meal` varchar(25) NOT NULL,
  `day_of_week` enum('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') NOT NULL,
  `time_of_day` enum('Breakfast','Elevenses','Dinner','Tea','Supper') NOT NULL,
  PRIMARY KEY (`plan`,`day_of_week`,`time_of_day`),
  KEY `meal` (`meal`),
  CONSTRAINT `plan_meal_ibfk_1` FOREIGN KEY (`plan`) REFERENCES `nutritional_plan` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plan_meal_ibfk_2` FOREIGN KEY (`meal`) REFERENCES `meal` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_meal`
--

LOCK TABLES `plan_meal` WRITE;
/*!40000 ALTER TABLE `plan_meal` DISABLE KEYS */;
INSERT INTO `plan_meal` VALUES ('Dieta cud','gulasz','Monday','Elevenses');
/*!40000 ALTER TABLE `plan_meal` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER insert_plan_meal_trigger AFTER INSERT ON plan_meal
FOR EACH ROW
BEGIN 
	UPDATE nutritional_plan np INNER JOIN plan_meal pm ON pm.plan = np.name INNER JOIN meal m ON m.name = pm.meal
    SET 
    np.energy = np.energy + m.energy,
    np.fat = np.fat + m.fat,
    np.of_which_saturates = np.of_which_saturates + m.of_which_saturates,
    np.carbohydrates = np.carbohydrates + m.carbohydrates,
    np.of_which_sugars = np.of_which_sugars + m.of_which_sugars,
    np.protein = np.protein + m.protein,
    np.salt = np.salt + m.salt
    WHERE np.name = NEW.plan;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER delete_plan_meal_trigger BEFORE DELETE ON plan_meal
FOR EACH ROW
BEGIN
	UPDATE meal m INNER JOIN plan_meal pm ON m.name = pm.meal INNER JOIN nutritional_plan np ON np.name = pm.plan
    SET
    np.energy = np.energy - m.energy,
    np.fat = np.fat - m.fat,
    np.of_which_saturates = np.of_which_saturates - m.of_which_saturates,
    np.carbohydrates = np.carbohydrates - m.carbohydrates,
    np.of_which_sugars = np.of_which_sugars - m.of_which_sugars,
    np.protein = np.protein - m.protein,
    np.salt = np.salt - m.salt
    WHERE np.name = OLD.plan;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `name` varchar(25) NOT NULL,
  `category` varchar(15) NOT NULL,
  `energy` int(11) NOT NULL,
  `fat` int(11) NOT NULL,
  `of_which_saturates` int(11) NOT NULL,
  `carbohydrates` int(11) NOT NULL,
  `of_which_sugars` int(11) NOT NULL,
  `protein` int(11) NOT NULL,
  `salt` int(11) NOT NULL,
  PRIMARY KEY (`name`),
  KEY `category` (`category`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('wieprzowina','mieso',210,15,14,5,0,18,0),('wolowina','mieso',150,10,8,10,0,22,0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_insert_trigger BEFORE INSERT ON product
FOR EACH ROW
BEGIN
	IF NEW.category NOT IN (SELECT name FROM category)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Category not found';
	ELSEIF NEW.energy < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Energy cannot be less than 0';
	ELSEIF NEW.fat < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Fat cannot be less than 0';
	ELSEIF NEW.of_which_saturates < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be less than 0';
	ELSEIF NEW.of_which_saturates > NEW.fat
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be higher than fat';
	ELSEIF NEW.carbohydrates < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Carbohydrates cannot be less than 0';
	ELSEIF NEW.of_which_sugars > NEW.carbohydrates
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sugars cannot be higher than carbohydrates';
	ELSEIF NEW.protein < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Protein cannot be less than 0';
	ELSEIF NEW.salt < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Salt cannot be less than 0';
	ELSEIF NEW.fat > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Fat cannot be higher than 100';
    ELSEIF NEW.carbohydrates > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Carbohydrates cannot be higher than 100';
    ELSEIF NEW.protein > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Protein cannot be higher than 100';
    ELSEIF NEW.salt > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Salt cannot be higher than 100';
    ELSEIF NEW.of_which_saturates > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be higher than 100';
    ELSEIF NEW.of_which_sugars > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sugars cannot be higher than 100';
	ELSEIF NEW.fat + NEW.carbohydrates + NEW.protein + NEW.salt > 100
			THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sum of nutrients cannot be higher than 100';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_update_trigger BEFORE UPDATE ON product
FOR EACH ROW
BEGIN
	IF NEW.category NOT IN (SELECT name FROM category)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Category not found';
	ELSEIF NEW.energy < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Energy cannot be less than 0';
	ELSEIF NEW.fat < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Fat cannot be less than 0';
	ELSEIF NEW.of_which_saturates < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be less than 0';
	ELSEIF NEW.of_which_saturates > NEW.fat
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be higher than fat';
	ELSEIF NEW.carbohydrates < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Carbohydrates cannot be less than 0';
	ELSEIF NEW.of_which_sugars > NEW.carbohydrates
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sugars cannot be higher than carbohydrates';
	ELSEIF NEW.protein < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Protein cannot be less than 0';
	ELSEIF NEW.salt < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Salt cannot be less than 0';
	ELSEIF NEW.fat > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Fat cannot be higher than 100';
    ELSEIF NEW.carbohydrates > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Carbohydrates cannot be higher than 100';
    ELSEIF NEW.protein > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Protein cannot be higher than 100';
    ELSEIF NEW.salt > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Salt cannot be higher than 100';
    ELSEIF NEW.of_which_saturates > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be higher than 100';
    ELSEIF NEW.of_which_sugars > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sugars cannot be higher than 100';
	ELSEIF NEW.fat + NEW.carbohydrates + NEW.protein + NEW.salt > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sum of nutrients cannot be higher than 100';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_after_update_trigger AFTER UPDATE ON product
FOR EACH ROW 
BEGIN 
	UPDATE meal m INNER JOIN product_meal pm ON m.name = pm.meal
    SET
    m.energy = m.energy - pm.amount/100*OLD.energy + pm.amount/100*NEW.energy,
    m.fat = m.fat - pm.amount/100*OLD.fat + pm.amount/100*NEW.fat,
    m.carbohydrates = m.carbohydrates - pm.amount/100*OLD.carbohydrates + pm.amount/100*NEW.carbohydrates,
    m.of_which_saturates = m.of_which_saturates - pm.amount/100*OLD.of_which_saturates + pm.amount/100*NEW.of_which_saturates,
    m.of_which_sugars = m.of_which_sugars - pm.amount/100*OLD.of_which_sugars + pm.amount/100*NEW.of_which_sugars,
    m.protein = m.protein - pm.amount/100*OLD.protein + pm.amount/100*NEW.protein,
    m.salt = m.salt - pm.amount/100*OLD.salt + pm.amount/100*NEW.salt
    WHERE pm.product = NEW.name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_delete_trigger BEFORE DELETE ON product
FOR EACH ROW 
BEGIN 
    DELETE FROM product_meal WHERE product=OLD.name;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `product_meal`
--

DROP TABLE IF EXISTS `product_meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_meal` (
  `product` varchar(15) NOT NULL,
  `meal` varchar(25) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`product`,`meal`),
  KEY `product_meal_ibfk_2` (`meal`),
  CONSTRAINT `product_meal_ibfk_1` FOREIGN KEY (`product`) REFERENCES `product` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_meal_ibfk_2` FOREIGN KEY (`meal`) REFERENCES `meal` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_meal`
--

LOCK TABLES `product_meal` WRITE;
/*!40000 ALTER TABLE `product_meal` DISABLE KEYS */;
INSERT INTO `product_meal` VALUES ('wieprzowina','gulasz',100),('wolowina','gulasz',200);
/*!40000 ALTER TABLE `product_meal` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_meal_before_insert_trigger BEFORE INSERT ON product_meal
FOR EACH ROW
BEGIN
	IF NEW.product NOT IN (SELECT name FROM product)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='This product does not exist';
	END IF;
    IF NEW.meal NOT IN (SELECT name FROM meal)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='This meal does not exist';
	END IF;
	IF NEW.amount <= 0 
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Amount of product has to be more than 0';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_meal_insert_trigger AFTER INSERT ON product_meal
FOR EACH ROW
BEGIN
	UPDATE meal m INNER JOIN product_meal pm ON m.name=NEW.meal INNER JOIN product p ON p.name=NEW.product
    SET
    m.energy = m.energy + NEW.amount / 100 * p.energy,
    m.fat = m.fat + NEW.amount / 100 * p.fat,
    m.carbohydrates = m.carbohydrates + NEW.amount / 100 * p.carbohydrates,
    m.of_which_saturates = m.of_which_saturates + NEW.amount / 100 * p.of_which_saturates,
    m.of_which_sugars = m.of_which_sugars + NEW.amount / 100 * p.of_which_sugars,
    m.protein = m.protein + NEW.amount / 100 * p.protein,
    m.salt = m.salt + NEW.amount / 100 * p.salt;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_meal_before_update_trigger BEFORE UPDATE ON product_meal
FOR EACH ROW
BEGIN
	IF NEW.product NOT IN (SELECT name FROM product)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='This product does not exist';
	END IF;
    IF NEW.meal NOT IN (SELECT name FROM meal)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='This meal does not exist';
	END IF;
	IF NEW.amount <= 0 
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Amount of product has to be more than 0';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_meal_update_trigger AFTER UPDATE ON product_meal
FOR EACH ROW
BEGIN
	UPDATE meal m INNER JOIN product_meal pm ON  m.name=NEW.meal INNER JOIN product p ON p.name=NEW.product
    SET
    m.energy = m.energy - OLD.amount / 100 * p.energy + NEW.amount / 100 * p.energy,
    m.fat = m.fat - OLD.amount / 100 * p.fat  + NEW.amount / 100 * p.fat,
    m.carbohydrates = m.carbohydrates - OLD.amount / 100 * p.carbohydrates  + NEW.amount / 100 * p.carbohydrates,
    m.of_which_saturates = m.of_which_saturates - OLD.amount / 100 * p.of_which_saturates  + NEW.amount / 100 * p.of_which_saturates,
    m.of_which_sugars = m.of_which_sugars - OLD.amount / 100 * p.of_which_sugars  + NEW.amount / 100 * p.of_which_sugars,
    m.protein = m.protein - OLD.amount / 100 * p.protein  + NEW.amount / 100 * p.protein,
    m.salt = m.salt - OLD.amount / 100 * p.salt  + NEW.amount / 100 * p.salt;
    END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`marcin`@`localhost`*/ /*!50003 TRIGGER product_meal_delete_trigger BEFORE DELETE ON product_meal
FOR EACH ROW 
BEGIN 
	UPDATE meal m INNER JOIN product_meal pm ON m.name=pm.meal INNER JOIN product p ON p.name=pm.product
    SET
    m.energy = m.energy - OLD.amount / 100 * p.energy,
    m.fat = m.fat - OLD.amount / 100 * p.fat,
    m.carbohydrates = m.carbohydrates - OLD.amount / 100 * p.carbohydrates,
    m.of_which_saturates = m.of_which_saturates - OLD.amount / 100 * p.of_which_saturates,
    m.of_which_sugars = m.of_which_sugars - OLD.amount / 100 * p.of_which_sugars,
    m.protein = m.protein - OLD.amount / 100 * p.protein,
    m.salt = m.salt - OLD.amount / 100 * p.salt
    WHERE pm.meal=OLD.meal AND pm.product=OLD.product;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'dietonator'
--
/*!50003 DROP PROCEDURE IF EXISTS `create_category` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `create_category`(IN _name VARCHAR(15))
BEGIN
	INSERT INTO category VALUES (_name);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `create_meal`(IN _name VARCHAR(25))
BEGIN
	INSERT INTO meal(name) VALUES(_name);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_nutritional_plan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `create_nutritional_plan`(IN _name VARCHAR(30))
BEGIN 
	INSERT INTO nutritional_plan(name) VALUES (_name);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_plan_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `create_plan_meal`(IN _plan VARCHAR(30), IN _meal VARCHAR(25), IN _day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
IN _time_of_day ENUM('Breakfast', 'Elevenses', 'Dinner', 'Tea', 'Supper'))
BEGIN 
	INSERT INTO plan_meal VALUES (
    _plan,
    _meal,
    _day_of_week,
    _time_of_day
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `create_product`(IN _name VARCHAR(25), IN _category VARCHAR(15), IN _energy INT, IN _fat INT, IN _saturates INT,
IN _carbohydrates INT, IN _sugars INT, IN _protein INT, IN _salt INT)
BEGIN
	INSERT INTO product VALUES (_name, _category, _energy, _fat, _saturates, _carbohydrates, _sugars, _protein, _salt);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `create_product_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `create_product_meal`(IN pname VARCHAR (15), IN mname VARCHAR(25), IN amount INT)
BEGIN 
	INSERT INTO product_meal VALUES (pname, mname, amount);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_category` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `delete_category`(IN _name VARCHAR(15))
BEGIN
	DELETE FROM category WHERE name=_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `delete_meal`(IN _name VARCHAR(25))
BEGIN 
	DELETE FROM meal WHERE _name=name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_nutritional_plan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `delete_nutritional_plan`(IN _name VARCHAR(30))
BEGIN 
	DELETE FROM nutritional_plan WHERE name = _name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_plan_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `delete_plan_meal`(IN _plan VARCHAR(30), IN _day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'),
IN _time_of_day ENUM('Breakfast', 'Elevenses', 'Dinner', 'Tea', 'Supper'))
BEGIN 
	DELETE FROM plan_meal WHERE
    plan = _plan AND 
    day_of_week = _day_of_week AND 
    time_of_day = _time_of_day;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `delete_product`(IN _name VARCHAR(25))
BEGIN
	DELETE FROM product WHERE name = _name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_product_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `delete_product_meal`(IN pname VARCHAR(15), IN mname VARCHAR(25))
BEGIN 
	DELETE FROM product_meal WHERE product=pname AND meal=mname; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_all_categories` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_all_categories`()
BEGIN
	SELECT * FROM category;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_all_products` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_all_products`()
BEGIN 
	SELECT * FROM product;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_category` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_category`(IN _name VARCHAR(15))
BEGIN
	SELECT * FROM category WHERE name LIKE CONCAT('%',_name, '%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_meal`(IN _name VARCHAR(25))
BEGIN 
	SELECT name, energy , fat, of_which_saturates, carbohydrates, of_which_sugars, protein, salt FROM meal WHERE name LIKE CONCAT('%', _name, '%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_meals_of_plan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_meals_of_plan`(IN _plan VARCHAR(30))
BEGIN 
	SELECT m.name, m.energy, m.fat, m.of_which_saturates, m.carbohydrates, m.of_which_sugars, m.protein, m.salt FROM meal m 
    INNER JOIN plan_meal pm ON m.name = pm.meal 
    INNER JOIN nutritional_plan np ON np.name = pm.plan
    WHERE _plan = np.name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_nutritional_plan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_nutritional_plan`(IN _name VARCHAR(30))
BEGIN 
	SELECT name, energy , fat, of_which_saturates, carbohydrates, of_which_sugars, protein, salt FROM nutritional_plan WHERE name LIKE CONCAT ('%', _name, '%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_product`(IN _name VARCHAR(25))
BEGIN 
	SELECT * FROM product WHERE name LIKE CONCAT('%', _name, '%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_products_of_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `select_products_of_meal`(IN mname VARCHAR(25))
BEGIN 
	SELECT p.name, amount FROM product p INNER JOIN product_meal pm ON pm.product = p.name
    INNER JOIN meal m ON m.name = pm.meal;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `update_meal`(IN oldname VARCHAR(25), IN newname VARCHAR (25))
BEGIN 
	UPDATE meal SET name=newname WHERE name=oldname;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_nutritional_plan` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `update_nutritional_plan`(IN oldname VARCHAR(30), IN newname VARCHAR(30))
BEGIN 
	UPDATE nutritional_plan SET name = newname WHERE name = oldname;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `update_product`(IN _name VARCHAR(25), IN _category VARCHAR(15), IN _energy INT, IN _fat INT, IN _saturates INT,
IN _carbohydrates INT, IN _sugars INT, IN _protein INT, IN _salt INT)
BEGIN
	UPDATE product SET
    category=_category,
    energy=_energy,
    fat=_fat,
    of_which_saturates=_saturates,
    carbohydrates=_carbohydrates,
    of_which_sugars=_sugars,
    protein=_protein,
    salt=_salt
    WHERE name=_name;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_product_meal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`marcin`@`localhost` PROCEDURE `update_product_meal`(IN pname VARCHAR(15), IN mname VARCHAR(25), IN _amount INT)
BEGIN 
	UPDATE product_meal SET amount=_amount WHERE product=pname AND meal=mname;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-14 17:08:59
