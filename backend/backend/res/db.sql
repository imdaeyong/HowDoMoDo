create schema movie_project;
use movie_project;

CREATE TABLE `users` (
  `user_code` int,
  `user_email` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_nick` varchar(45) NOT NULL,
  `user_pw` varchar(45) NOT NULL,
  `user_gender` int NOT NULL,
  `user_birth` date NOT NULL,
  `user_loginkind` int NOT NULL,
  PRIMARY KEY (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `users_roles` (
  `user_code` int,
  `roles` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_code`),
  KEY `role_id_idx` (`roles`),
  CONSTRAINT `fk_users` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `cities` (
    `city_id` int NOT NULL AUTO_INCREMENT,
    `si_name` varchar(100),
    `gu_name` varchar(100),
    PRIMARY KEY (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `theaters` (
    `theater_id` int NOT NULL AUTO_INCREMENT,
    `city_id` int NOT NULL,
    `theater_name` varchar(255) NOT NULL,
    `theater_address` varchar(255) NOT NULL,
    `theater_brand` varchar(50) NOT NULL,
    `theater_lat` float,
    `theater_lon` float,
	PRIMARY KEY (`theater_id`),
    CONSTRAINT `city_fk` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `historys` (
  `history_id` int NOT NULL AUTO_INCREMENT,
  `user_code` int DEFAULT NULL,
  `movie_id` varchar(45) DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  `theater_name` varchar(45) DEFAULT NULL,
  `room_name` varchar(45) DEFAULT NULL,
  `select_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`),
  KEY `user_fore_idx` (`user_code`),
  KEY `theater_fore_idx` (`theater_id`),
  CONSTRAINT `theater_fore` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`theater_id`) ON DELETE CASCADE,
  CONSTRAINT `user_fore` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `selected_seat` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `history_id` int DEFAULT NULL,
  `seat_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `history_fore_idx` (`history_id`),
  CONSTRAINT `history_fore` FOREIGN KEY (`history_id`) REFERENCES `historys` (`history_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `recommand` (
  `recommend_id` int(10) NOT NULL AUTO_INCREMENT COLLATE utf8mb4_general_ci,
  `recommend_category` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `recommend_age` int(10) COLLATE utf8mb4_general_ci NOT NULL,
  `recommend_gender` TINYINT(1) COLLATE utf8mb4_general_ci NOT NULL,
  `recommend_time` int(10) COLLATE utf8mb4_general_ci NOT NULL,
  `recommend_image` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `recommend_title` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `recommend_description` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `recommend_lat` DECIMAL(10,6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `recommend_lng` DECIMAL(10,6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `city_id` int(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`recommend_id`),
  CONSTRAINT `city_fk2` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `foods` (
  `store_id` int auto_increment,
  `store_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `city_id` int not null,
  `store_type` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `store_review` int DEFAULT '0',
  `store_star` float DEFAULT '0',
  `store_info` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  CONSTRAINT `city_id` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `chatlist` (
  `chatlist_id` int  AUTO_INCREMENT,
  `user_code` int DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  PRIMARY KEY (`chatlist_id`),
  CONSTRAINT `fk_users2` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) on delete cascade,
  CONSTRAINT `fk_theater` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`theater_id`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `chatroom` (
  `message_id` int AUTO_INCREMENT,
  `user_code` int DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  `message_content` text(200) NOT NULL,
  `message_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(`message_id`),
  CONSTRAINT `fk_message_theater` FOREIGN KEY (`theater_id`) REFERENCES `theaters` (`theater_id`) on delete cascade,
   CONSTRAINT `fk_message_users` FOREIGN KEY (`user_code`) REFERENCES `users` (`user_code`) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;




