

DROP TABLE IF EXISTS `vendors`;
CREATE TABLE `vendors` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_name TEXT DEFAULT NULL,
  vendor_code VARCHAR(50) DEFAULT NULL,
  vendor_capacity INT DEFAULT NULL,
  established_year INT DEFAULT NULL,
  vendor_email TEXT DEFAULT NULL,
  vendor_mobile VARCHAR(50) DEFAULT NULL,
  vendor_description TEXT DEFAULT NULL,
  best_selling_items TEXT DEFAULT NULL,
  cost_for_two_people INT DEFAULT NULL,
  country VARCHAR(250) DEFAULT NULL,
  state VARCHAR(250) DEFAULT NULL,
  city VARCHAR(250) DEFAULT NULL,
  pincode VARCHAR(250) DEFAULT NULL,
  location TEXT DEFAULT NULL,
  address TEXT DEFAULT NULL,
  longitude VARCHAR(100) DEFAULT NULL,
  latitude VARCHAR(100) DEFAULT NULL,
  phone_number VARCHAR(50) DEFAULT NULL,
  profile_image TEXT DEFAULT NULL,
  vendor_categories TEXT DEFAULT NULL,
  vendor_facilities TEXT DEFAULT NULL,
  vendor_music_genre TEXT DEFAULT NULL,
  vendor_cuisine TEXT DEFAULT NULL,
  terms_conditions LONGTEXT DEFAULT NULL,
  working_hours LONGTEXT DEFAULT NULL,
  slider_images LONGTEXT DEFAULT NULL,
  gallery_images LONGTEXT DEFAULT NULL,
  menu_images LONGTEXT DEFAULT NULL,
  vendor_videos LONGTEXT DEFAULT NULL,
  created_date DATETIME DEFAULT NULL,
  updated_date DATETIME DEFAULT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  status INT DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;


DROP TABLE IF EXISTS `wayuparty_roles`;
CREATE TABLE `wayuparty_roles` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(50)  DEFAULT NULL,
  display_name VARCHAR(50) DEFAULT NULL,
  uuid VARCHAR(20) NOT NULL,
  status INT DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 ;



DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_id BIGINT DEFAULT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) DEFAULT NULL,
  gender VARCHAR(10) DEFAULT NULL,
  mobile_number VARCHAR(50) NOT NULL,
  email TEXT NOT NULL,
  dob DATE DEFAULT NULL,
  user_name TEXT NOT NULL,
  password TEXT NOT NULL,
  role_id BIGINT NOT NULL,
  address TEXT DEFAULT NULL,
  user_image TEXT DEFAULT NULL,
  user_status VARCHAR(100) DEFAULT NULL,
  is_email_verified VARCHAR(100) DEFAULT NULL,
  uuid VARCHAR(20) NOT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id),
  FOREIGN KEY(role_id) REFERENCES `wayuparty_roles`(id)
) DEFAULT CHARSET=utf8 ;

INSERT INTO `wayuparty_roles` (`id`, `role_name`, `display_name`, `uuid`, `status`) VALUES ('1', 'ROLE_SUPER_ADMIN', 'Super Admin', 'superadmn', '1');
INSERT INTO `wayuparty_roles` (`id`, `role_name`, `display_name`, `uuid`, `status`) VALUES ('2', 'ROLE_ADMIN', 'Admin', 'admin', '1');
INSERT INTO `wayuparty_roles` (`id`, `role_name`, `display_name`, `uuid`, `status`) VALUES ('3', 'ROLE_USER', 'User', 'appuser', '1');


INSERT INTO `wayuparty`.`user` (`id`, `first_name`, `mobile_number`, `email`, `user_name`, `password`, `role_id`, `user_status`, `is_email_verified`, `uuid`, `status`) VALUES ('1', 'WAYUPARTY ADMIN', '1234567894', 'admin@acculytixs.com', 'admin@acculytixs.com', 'c9436c72cea8f1f90296f70c2acb902cq7bff165a0b9c02b4da1a6bc299652afffq73f719ffdc2520d71b0d78f871ac9f0ecq710277cf00cdc41a422eefee6c7a1de11q7', '1', 'LOGGED_IN', 'Y', 'WUPADMIN', '1');


DROP TABLE IF EXISTS `vendor_bank_account`;
CREATE TABLE `vendor_bank_account` (
  id BIGINT NOT NULL AUTO_INCREMENT,
  vendor_id BIGINT NOT NULL,
  beneficiary_name VARCHAR(250) NOT NULL,
  account_number VARCHAR(50) NOT NULL,
  bank_name TEXT NOT NULL,
  bank_branch TEXT NOT NULL,
  bank_code VARCHAR(50) NOT NULL,
  account_type VARCHAR(50) NOT NULL,
  uuid VARCHAR(20) DEFAULT NULL,
  created_date DATETIME,
  status INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(vendor_id) REFERENCES `vendors`(id)
) DEFAULT CHARSET=utf8 ;



