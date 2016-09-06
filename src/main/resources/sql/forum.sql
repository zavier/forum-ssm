/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.6.19 : Database - forum_1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`forum` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `forum`;

/*Table structure for table `t_board` */

DROP TABLE IF EXISTS `t_board`;

CREATE TABLE `t_board` (
  `id` varchar(255) NOT NULL,
  `board_desc` varchar(255) DEFAULT NULL,
  `board_name` varchar(255) DEFAULT NULL,
  `topic_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_board` */

/*Table structure for table `t_login_log` */

DROP TABLE IF EXISTS `t_login_log`;

CREATE TABLE `t_login_log` (
  `id` varchar(255) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9ewx8t59gl5pyoxtw467a4phv` (`user_id`),
  CONSTRAINT `FK_9ewx8t59gl5pyoxtw467a4phv` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_login_log` */

/*Table structure for table `t_post` */

DROP TABLE IF EXISTS `t_post`;

CREATE TABLE `t_post` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `post_text` varchar(255) DEFAULT NULL,
  `post_title` varchar(255) DEFAULT NULL,
  `post_type` int(11) DEFAULT NULL,
  `board_id` varchar(255) DEFAULT NULL,
  `topic_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6i9r3u1e64xgc0yey8jjl3di3` (`board_id`),
  KEY `FK_t3iu1n5uayjh585r182qgimwm` (`topic_id`),
  KEY `FK_s8ds9wudctcypjgvid4ln3nnp` (`user_id`),
  CONSTRAINT `FK_s8ds9wudctcypjgvid4ln3nnp` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_6i9r3u1e64xgc0yey8jjl3di3` FOREIGN KEY (`board_id`) REFERENCES `t_board` (`id`),
  CONSTRAINT `FK_t3iu1n5uayjh585r182qgimwm` FOREIGN KEY (`topic_id`) REFERENCES `t_topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_post` */

/*Table structure for table `t_topic` */

DROP TABLE IF EXISTS `t_topic`;

CREATE TABLE `t_topic` (
  `id` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `digest` int(11) DEFAULT NULL,
  `last_post` datetime DEFAULT NULL,
  `topic_content` varchar(255) DEFAULT NULL,
  `topic_replies` int(11) DEFAULT NULL,
  `topic_title` varchar(255) DEFAULT NULL,
  `topic_view` int(11) DEFAULT NULL,
  `board_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9gvlrkj9o2o32rsno3rft3ggr` (`board_id`),
  KEY `FK_2k0o897cw1avfcyl4svhs7p57` (`user_id`),
  CONSTRAINT `FK_2k0o897cw1avfcyl4svhs7p57` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_9gvlrkj9o2o32rsno3rft3ggr` FOREIGN KEY (`board_id`) REFERENCES `t_board` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_topic` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(255) NOT NULL,
  `credit` int(11) DEFAULT NULL,
  `locked` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
