drop database film;
create database film;
USE film;

DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                              `points` int DEFAULT NULL,
                              `register_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE KEY `unique_email` (`email`) USING BTREE,
                              UNIQUE KEY `unique_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `db_account`
--

LOCK TABLES `db_account` WRITE;
/*!40000 ALTER TABLE `db_account` DISABLE KEYS */;
INSERT INTO `db_account` VALUES (4,'test','2945484861@qq.com','$2a$10$JMAXpofcmZT64o7/QV0twOo0J9zNAwQlaAhv068dCNkbBsIquKacS','USER',500,'2024-01-09 21:42:41'),(5,'admin','2703001087@qq.com','$2a$10$UWUq3Tv9boHp5s4NIhhZG.nfyqcpXbglWDsfL2fUhNOq/c/EMixGe','ADMIN',500,'2024-01-10 09:27:01');
/*!40000 ALTER TABLE `db_account` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `db_film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `db_film` (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '电影ID',
                           `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电影名称',
                           `intro` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电影简介',
                           `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电影类型',
                           `actor` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电影主演',
                           `region` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电影地区',
                           `good` double DEFAULT NULL COMMENT '电影好评',
                           `wheat` int DEFAULT NULL COMMENT '电影周热度',
                           `mheat` int DEFAULT NULL COMMENT '电影月热度',
                           `time` date DEFAULT NULL COMMENT '电影上映时间',
                           `picurl` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图片链接',
                           `filmurl` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '视频链接',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `db_film`
--

LOCK TABLES `db_film` WRITE;
/*!40000 ALTER TABLE `db_film` DISABLE KEYS */;
INSERT INTO `db_film` VALUES (1,'tes','test','test','t','t',0.98,10,20,'2024-01-09','',''),(2,'test','test','t','b','test',0.81,30,50,'2024-01-10','',''),(3,'tests','test','t','a','test',0.77,2,23,'3911-02-01','',''),(4,'te','test','test','t','t',0.1,12,100,'2011-11-11','',''),(5,'ta','test','e','b','a',0.3,10,20,'2011-11-11','','');
/*!40000 ALTER TABLE `db_film` ENABLE KEYS */;
UNLOCK TABLES;