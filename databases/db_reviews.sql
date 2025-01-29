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
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `restaurant_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `rating` float NOT NULL,
  `hidden` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_restaurant` (`restaurant_id`),
  KEY `fk_user` (`user_id`),
  CONSTRAINT `fk_restaurant` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurants` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (4,3,5,'Εξαιρετική γαστρονομική εμπειρία!\nΤο φαγητό ήταν αυθεντικό, γεμάτο γεύση και άρωμα από Βραζιλία. Το προσωπικό ευγενικό και εξυπηρετικό, ενώ η ατμόσφαιρα πραγματικά σε ταξιδεύει. Δοκιμάστε οπωσδήποτε την picanha και τις caipirinhas!',5,_binary '\0'),(6,5,5,'Λίγο ακριβό αλλά αξίζει.\nΟι τιμές είναι λίγο υψηλές, αλλά η ποιότητα του κρέατος τα δικαιολογεί. Η γεύση ήταν εξαιρετική και η ατμόσφαιρα του μαγαζιού πολύ φιλική και χαλαρή.',4,_binary '\0'),(7,6,5,'Μέτριο για την τιμή του.\nΗ θέα ήταν όντως εντυπωσιακή, αλλά το φαγητό δεν ανταποκρινόταν στις προσδοκίες. Οι γεύσεις ήταν απλά καλές, τίποτα το ξεχωριστό. Η εξυπηρέτηση ήταν ευγενική, αλλά υπήρχαν καθυστερήσεις.',5,_binary ''),(8,7,5,'Η αυθεντική εμπειρία της Νάπολης!\nΗ ζύμη ήταν ελαφριά και αφράτη, ενώ τα υλικά εξαιρετικής ποιότητας. Η μοτσαρέλα και η σάλτσα ντομάτας είχαν απίστευτη γεύση. Ένιωσα σαν να τρώω πίτσα στη Νάπολη. Σίγουρα θα ξαναπάω!',5,_binary '\0'),(9,6,6,'Ρομαντική ατμόσφαιρα με υπέροχες γεύσεις.\nΤο εστιατόριο ήταν ιδανικό για μια ιδιαίτερη βραδιά. Οι γεύσεις ήταν εκλεπτυσμένες και το προσωπικό πολύ προσεκτικό. Η θέα το έκανε ακόμα πιο ξεχωριστό.',5,_binary '\0'),(10,6,7,'Καλοφτιαγμένο αλλά υπερτιμημένο.\nΗ θέα και το περιβάλλον είναι μαγευτικά, αλλά οι μερίδες ήταν πολύ μικρές για την τιμή τους. Το φαγητό ήταν καλό, αλλά περίμενα κάτι πιο εντυπωσιακό από ένα fine dining εστιατόριο.',5,_binary '\0'),(11,6,8,'Όχι τόσο καλό όσο περίμενα.\nΠαρόλο που η θέα ήταν υπέροχη, το φαγητό δεν μας ενθουσίασε. Οι μερίδες ήταν μικρές και οι γεύσεις κάπως αδιάφορες. Με βάση τις τιμές, θα περίμενα περισσότερη ποιότητα.',3,_binary '\0'),(14,7,6,'',4,_binary '\0'),(16,4,5,'',5,_binary '\0'),(17,4,17,'καταπληκτικο',5,_binary '\0'),(18,6,17,'test2',5,_binary '\0'),(19,8,4,'test',5,_binary '\0');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
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
