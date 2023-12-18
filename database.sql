USE film;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_account
-- ----------------------------
DROP TABLE IF EXISTS `db_account`;
CREATE TABLE `db_account` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `username` varchar(255) DEFAULT NULL,
                              `email` varchar(255) DEFAULT NULL,
                              `password` varchar(255) DEFAULT NULL,
                              `role` varchar(255) DEFAULT NULL,
                              `register_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `unique_email` (`email`),
                              UNIQUE KEY `unique_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of db_account
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;