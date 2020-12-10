
DROP TABLE IF EXISTS `wayuparty_categories`;
CREATE TABLE `wayuparty_categories` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(250) NOT NULL,
  category_type VARCHAR(250) NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;

INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (1,'Casual Dining','Category',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (2,'Lounge','Category',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (3,'Night Club','Category',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (4,'Pub','Category',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (5,'Brewery','Category',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (6,'Cafe','Category',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (7,'Bar','Category',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (8,'Outdoor','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (9,'Indoor','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (10,'Smoking Zone','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (11,'Outdoor Catering','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (12,'Karaoke','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (13,'Wifi Zone','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (14,'Air Conditioned','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (15,'Cards Accepted','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (16,'AMEX Cards Accepted','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (17,'Screening','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (18,'Valet Parking','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (19,'Roof Top','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (20,'Pool Side','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (21,'Take Away','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (22,'Barbeque and Grill','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (23,'Dance Floor','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (24,'Lift','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (25,'Games','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (26,'Brunch','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (27,'Private Dining','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (28,'Home Delivery','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (29,'Pet Friendly','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (30,'Live','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (31,'Live Music','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (32,'DJ','Facilities',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (33,'Bollywood','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (34,'EDM','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (35,'Hip Hop','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (36,'Rock and Roll','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (37,'Techno','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (38,'Progressive House','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (39,'Live','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (40,'International','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (41,'Dubstep','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (42,'Electro','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (43,'Retro','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (44,'Trans','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (45,'House Music','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (46,'Pop','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (47,'Indie Rock','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (48,'Heavy Metal','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (49,'Fusion','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (50,'Jazz','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (51,'Disco','Music Genre',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (52,'Continental','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (53,'Indian','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (54,'Italian','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (55,'Mexican','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (56,'Chinese','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (57,'Oriental','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (58,'Chef Special','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (59,'American','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (60,'Multi Cuisine','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (61,'Mediterranean','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (62,'Asian','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (63,'European','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (64,'Parsi','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (65,'Iranian','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (66,'Greek','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (67,'Spanish','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (68,'Labanese','Cuisine',1);
INSERT INTO `wayuparty_categories` (`id`,`category_name`,`category_type`,`status`) VALUES (69,'Finger Food','Cuisine',1);
