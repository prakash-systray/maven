DROP TABLE IF EXISTS `place_order`;
CREATE TABLE `place_order` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_uuid VARCHAR(20)  NOT NULL,
  vendor_uuid VARCHAR(20) NOT NULL,
  service_uuid VARCHAR(20) NOT NULL,
  master_service_uuid VARCHAR(20) NOT NULL,
  quantity INT NOT NULL,
  order_amount DOUBLE NOT NULL,
  total_amount DOUBLE NOT NULL,
  currency VARCHAR(10) NOT NULL,
  service_ordered_date DATE NOT NULL,
  service_time_slot TEXT DEFAULT NULL,
  package_menu_items TEXT DEFAULT NULL,
  order_status VARCHAR(50) NOT NULL,
  order_status_modified_date DATETIME DEFAULT NULL,
  is_user_arrived VARCHAR(10) DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;

