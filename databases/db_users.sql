-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (4,'enolic','Kostas','Gialantzis','kgialant@gmail.com','$2a$10$N.Wtd9w4DzyQhjjgsEFHhOHzjdtwi2pawTeu22Qh2S9rEkYv28fUG',0),(5,'stefbou9','Stefanos','Bousdekis','stefbou9@gmail.com','$2a$10$ZwrO/n4kMLtVWbOMFDpDoOOmJF3R0uzGqBe8sZOXAE3yY7BGRWyUe',0),(6,'UserTest1','First1','Last1','firstuser@gmail.com','$2a$10$M5fMEjt3wjd3j.FNaxfBkOCGohVVWA53xoiJNkAsldcbo8Ji.g8fW',1),(7,'UserTest2','First2','Last2','seconduser@gmail.com','$2a$10$oMRXjFkQEckMh1VoffdRHuKHwyu3J1UAO6EmJwsvuVtCKFUGm2uh2',1),(8,'UserTest3','First3','Last3','thirduser@gmail.com','$2a$10$/oZtieszwXCcD3U7ohZw..Wy5clIuLLHJ6HN/dkjsnaf31OkSQrdW',1),(9,'UserTest4','First4','Last4','fourthuser@gmail.com','$2a$10$Z8PrwEPqH1qgSkcxk8qczOMtaplVylgfnrouapNZUUh.jnlv98uda',1),(10,'UserTest5','First5','Last5','fifthuser@gmail.com','$2a$10$/uft4ZR/Pg2UVoFu2xW2.OkVkUGP3IkS.ruWm55GoFV6o2.ujgrGu',1),(11,'UserTest6','First6','Last6','sixthuser@gmail.com','$2a$10$T8KYn6zyzaQYcafrrsK0...ARK7IHvE.AEhkY1cGKoGTudGJehIdm',1),(12,'UserTest7','First7','Last7','seventhuser@gmail.com','$2a$10$.ELxYAtixDaGDXYPi35t2.8L0JAkOnN/EiLzaMprQWTOEWIeyK8hO',1),(13,'UserTest8','First8','Last8','eighthuser@gmail.com','$2a$10$WywDhVKp.l725qgL5d.PS.vdhdM4TO8JC2.1KJPVlb7O1RTLedQIK',1),(14,'UserTest9','First9','Last9','ninthuser@gmail.com','$2a$10$.DIoMt1kwzToloBEm/vCfeqBQ6wjIbKWVvARGkI4ErqbHy4ECGm8S',1),(15,'UserTest10','First10','Last10','tenthuser@gmail.com','$2a$10$TC3uDfnE.N1ytKf3U4qZA.6ZDp9pQyVWNFz9kmRx9IDC4bU9m1r16',1),(17,'giorgo2','Giorgos','Pavlakis','giorgos2@gmail.com','$2a$10$DVgti1UxsxC22ukRlP8PhO45T22Ywgf8sM8Eij92kmD9niC/62/TW',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-27 19:04:04
