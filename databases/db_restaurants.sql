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
-- Table structure for table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurants` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `average_rating` float DEFAULT '0',
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurants`
--

LOCK TABLES `restaurants` WRITE;
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` VALUES (3,'Picanha Meat and More','Αιγύπτου 3, Θεσσαλονίκη, 546 25','231 055 5458','Traditional brazilian steakhouse in the heart of Thessaloniki, Greece. Come and let us introduce you to the most famous beef cuts from Brazil while enjoying wines from all around Latin America',0,'https://lh3.googleusercontent.com/p/AF1QipMzd0jgDnSJHdi7Bhl8IXRHUfSD79J2rAPI9koK=s680-w680-h510'),(4,'Juicy Grill','Leof. Perikleous 37, Cholargos 155 61','2155203201','Inspired by one, loved by many, Juicy Grill is a burger, itself. Love is our bread, care is our homemade sauce, quality is our meat, freshness is our vegetables and all this is served in mystery.',0,'https://juicygrill.gr/jg/wp-content/uploads/2024/02/DLH05757.jpg'),(5,'BarBara Que','Eth. Antistaseos 27, Vrilissia 152 38',' 21 3034 9058','Quality food, good service great environment and decent prices for what you get. Compared with other burger places in Athens this is one of the top ones.',0,'https://lh3.googleusercontent.com/p/AF1QipOwB4o0pnUN1eONnVqyTbAG0xhFf69Komh_ohLJ=s680-w680-h510'),(6,'Ελαίας Γη','Eleas Gi, Kifisia 145 63','21 0620 6433','Ελλάδα… Το µεγάλο σταυροδρόµι γεύσεων και πολιτιστικών παραδόσεων. Η Ελληνική γη και τα προϊόντα που µας προσφέρει, συνεχίζουν να µας εµπνέουν και να σµιλεύουν ακατέργαστα τη φιλοσοφία µας.',0,'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1b/da/23/56/view-to-the-city.jpg?w=900&h=500&s=1'),(7,'Puff Daddy Pizzeria','Skra 16, Pefki 151 21','21 0806 3366','Η σύχρονη ναπολιτάνικη πίτσα στο πιάτο σου μαζί με νόστιμες σαλάτες και γλυκά!',0,'https://imageproxy.wolt.com/menu/menu-images/63638a5bd946ee917bca2384/39e2cf8e-5c58-11ed-8dad-6e7a8e1962a7_puff6.jpeg?w=600'),(8,'test','test','test','test',0,'https://lh3.googleusercontent.com/p/AF1QipOwB4o0pnUN1eONnVqyTbAG0xhFf69Komh_ohLJ=s680-w680-h510');
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;
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
