DROP TABLE IF EXISTS `wayuparty_serivce_sub_category`;
DROP TABLE IF EXISTS `wayuparty_serivce_category`;
DROP TABLE IF EXISTS `wayuparty_services`;

CREATE TABLE `wayuparty_services` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_id BIGINT DEFAULT NULL,
  service_name TEXT NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  service_image TEXT DEFAULT NULL,
  created_by BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;


CREATE TABLE `wayuparty_serivce_category` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  service_id BIGINT NOT NULL,
  vendor_id BIGINT DEFAULT NULL,
  category_name TEXT NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  category_image TEXT DEFAULT NULL,
  created_by BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(service_id) REFERENCES `wayuparty_services`(id),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;



CREATE TABLE `wayuparty_serivce_sub_category` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  category_id BIGINT NOT NULL,
  vendor_id BIGINT DEFAULT NULL,
  sub_category_name TEXT NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  created_by BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(category_id) REFERENCES `wayuparty_serivce_category`(id),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;


INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('1', 'Book a bottle', 'TWo6afD0', '1', '/resources/img/bottle.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('2', 'Schedule a table', 'J6rUaf5n', '1', '/resources/img/table.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('3', 'Entry', 'Hr3Fol7r', '1', '/resources/img/entry.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('4', 'Guest List', 'Y5R4WsxP', '1', '/resources/img/guest.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('5', 'Surprise', 'Tg5e3Jko', '1', '/resources/img/surprise.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('6', 'Deals and Offers', 'Yre4bn6y', '1', '/resources/img/offers.jpg');
INSERT INTO `wayuparty`.`wayuparty_services` (`id`, `service_name`, `uuid`, `status`, `service_image`) VALUES ('7', 'Packages', 'Jhb5S3nB', '1', '/resources/img/packages.jpg');

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (1,1,NULL,'Wine','5fSdGu7w',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (2,1,NULL,'Vodka','U76FdwU5',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (3,1,NULL,'Whisky','6Rfd7fEs',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (4,1,NULL,'Gin','Y7uDwe4R',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (5,1,NULL,'Brandy','6Rfe4fRd',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (6,1,NULL,'Rum','Y86Fde2A',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (7,1,NULL,'Single Malt','Ujh56Ds3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (8,1,NULL,'Beer','8ORdfe3S',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (9,1,NULL,'Champagne','Yh43DcXd',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (10,1,NULL,'Tequila','Gfr4WsxQ',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (11,1,NULL,'Baileys','I8n4Dcfs',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (12,1,NULL,'Liqueurs','Y65rfd5E',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (13,1,NULL,'Signature Cocktails','Uy6EsDaP',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (14,1,NULL,'Shots','Hgt5EsoL',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (15,1,NULL,'Mocktails','Tgf5ExcO',NULL,1,NULL);

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (16,2,NULL,'2','G6jHgfWs',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (17,2,NULL,'4','Hbg6wFgp',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (18,2,NULL,'6','7HgtrdEo',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (19,2,NULL,'8','Uhjt5Rfd',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (20,2,NULL,'10','Io9Ygf4E',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (21,2,NULL,'12','Oli8YgvR',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (22,2,NULL,'14','Po9UhgtE',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (23,2,NULL,'16','Gb5RdcfU',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (24,2,NULL,'18','Jgt1AxfL',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (25,2,NULL,'20','I9lhTr5F',NULL,1,NULL);

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (26,3,NULL,'Stag','Frg5SweA',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (27,3,NULL,'Couple','K8yGfcVw',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (28,3,NULL,'Single Lady','Jui8Tfdr',NULL,1,NULL);

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (29,4,NULL,'Stag','Uyg5I9Yd',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (30,4,NULL,'Couple','Nb6yrC3w',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (31,4,NULL,'Single Lady','Nju7R4dH',NULL,1,NULL);

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (32,5,NULL,'Bouquet','Tfr4DcxK',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (33,5,NULL,'Cake','O9y6V4eH',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (34,5,NULL,'Setup','K9t5FvdU',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (35,5,NULL,'Live Artist','Nju7RfdC',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (36,5,NULL,'Bottle of wine','Mju8rDco',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (37,5,NULL,'Capture your moment','Nht6eF5d',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (38,5,NULL,'Others','Nji9Hfrw',NULL,1,NULL);

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (39,6,NULL,'Discount','Bgr5DcsO',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (40,6,NULL,'1+1','H6rFcdQo',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (41,6,NULL,'2+1','7YhvfOlq',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (42,6,NULL,'2+2','Bht7FcsX',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (43,6,NULL,'Happy Hours','Xt6HjwSa',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (44,6,NULL,'MRP','Mju7lfSa',NULL,1,NULL);

INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (45,7,NULL,'Titanium','L9uF4s3d',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (46,7,NULL,'Platinum','B6rFd3x2',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (47,7,NULL,'Diamond','Yt6ldfC3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (48,7,NULL,'Gold','G6dX4td3',NULL,1,NULL);
INSERT INTO `wayuparty_serivce_category` (`id`,`service_id`,`vendor_id`,`category_name`,`uuid`,`created_date`,`status`,`created_by`) VALUES (49,7,NULL,'Silver','Vhy5D5f3',NULL,1,NULL);

