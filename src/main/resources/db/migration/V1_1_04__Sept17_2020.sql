DROP TABLE IF EXISTS `vendor_master_service`;
CREATE TABLE `vendor_master_service` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_id BIGINT DEFAULT NULL,
  service_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  sub_category_id BIGINT DEFAULT NULL,
  actual_price DOUBLE DEFAULT NULL,
  offer_price DOUBLE DEFAULT NULL,
  allowed INT DEFAULT NULL,
  max_booking_allowed INT DEFAULT NULL,
  service_name VARCHAR(200) DEFAULT NULL,
  service_start_date DATE DEFAULT NULL,
  service_end_date DATE DEFAULT NULL,
  service_time_slots LONGTEXT DEFAULT NULL,
  service_description LONGTEXT DEFAULT NULL,
  service_offer LONGTEXT DEFAULT NULL,
  terms_and_conditions LONGTEXT DEFAULT NULL,
  service_image LONGTEXT DEFAULT NULL,
  event_location LONGTEXT DEFAULT NULL,
  music_genre LONGTEXT DEFAULT NULL,
  artist LONGTEXT DEFAULT NULL,
  package_menu LONGTEXT DEFAULT NULL,
  guest_entry_time VARCHAR(50) DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  created_by BIGINT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(service_id) REFERENCES `wayuparty_services`(id),
  FOREIGN KEY(category_id) REFERENCES `wayuparty_serivce_category`(id),
  FOREIGN KEY(sub_category_id) REFERENCES `wayuparty_serivce_sub_category`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;

CALL AlterTableAddColumn ('vendors','currency','VARCHAR(20) NULL AFTER `phone_number`');


DROP TABLE IF EXISTS `package_menu_offering`;
CREATE TABLE `package_menu_offering` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_id BIGINT DEFAULT NULL,
  offering_item TEXT DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  created_by BIGINT DEFAULT NULL,
  status INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `package_menu_offering_items`;
CREATE TABLE `package_menu_offering_items` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_id BIGINT DEFAULT NULL,
  menu_offering_id BIGINT DEFAULT NULL,
  menu_offering_item TEXT DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  created_by BIGINT DEFAULT NULL,
  status INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(menu_offering_id) REFERENCES `package_menu_offering`(id),
  FOREIGN KEY(created_by) REFERENCES `user`(id)
) DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `user_cart`;
CREATE TABLE `user_cart` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT DEFAULT NULL,
  vendor_id BIGINT DEFAULT NULL,
  vendor_master_service_id BIGINT NOT NULL,
  order_amount DOUBLE DEFAULT NULL,
  quantity INT DEFAULT NULL,
  total_amount DOUBLE DEFAULT NULL,
  currency VARCHAR(10) DEFAULT NULL,
  service_ordered_date DATE DEFAULT NULL,
  service_time_slot TEXT DEFAULT NULL,
  package_menu_items TEXT DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(user_id) REFERENCES `user`(id),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(vendor_master_service_id) REFERENCES `vendor_master_service`(id)
) DEFAULT CHARSET=utf8 ;

