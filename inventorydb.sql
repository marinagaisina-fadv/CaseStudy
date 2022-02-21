CREATE SCHEMA `inventorydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;

CREATE TABLE `inventorydb`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `avail_quantity` INT UNSIGNED NOT NULL DEFAULT 0,
  `image_url` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE TABLE `inventorydb`.`customers` (
  `id` INT NOT NULL,
  `fullname` VARCHAR(100) NOT NULL,
  `full_delivery_address` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE TABLE `inventorydb`.`item_customer` (
  `item_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`item_id`, `customer_id`),
  INDEX `fk_customers_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_customers`
    FOREIGN KEY (`customer_id`)
    REFERENCES `inventorydb`.`customers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_items`
    FOREIGN KEY (`item_id`)
    REFERENCES `inventorydb`.`items` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE TABLE `inventorydb`.`pallets` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `barcode` VARCHAR(45) NULL,
  `location_created` INT NOT NULL,
  `location_destination` INT NOT NULL,
  `date_created` DATETIME NOT NULL,
  `date_pickedup` DATETIME NULL DEFAULT NULL,
  `date_delivered` DATETIME NULL DEFAULT NULL,
  `status_will_be_generated` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `barcode_UNIQUE` (`barcode` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

ALTER TABLE `inventorydb`.package
ADD COLUMN `pallet_id` INT NULL DEFAULT NULL AFTER `customer_id`,
ADD INDEX `fk_pallet_idx` (`pallet_id` ASC) VISIBLE;
;
ALTER TABLE `inventorydb`.package
ADD CONSTRAINT `fk_pallet`
  FOREIGN KEY (`pallet_id`)
  REFERENCES `inventorydb`.`pallets` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `inventorydb`.`locations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(100) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `address_UNIQUE` (`address` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

ALTER TABLE `inventorydb`.`pallets` 
ADD INDEX `kdkdf_idx` (`location_created` ASC) VISIBLE,
DROP INDEX `fk_locations_idx` ;
;
ALTER TABLE `inventorydb`.`pallets` 
ADD CONSTRAINT `fk_loc_created`
  FOREIGN KEY (`location_created`)
  REFERENCES `inventorydb`.`locations` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  ALTER TABLE `inventorydb`.`pallets` 
ADD INDEX `fk_loc_destination_idx` (`location_destination` ASC) VISIBLE;
;
ALTER TABLE `inventorydb`.`pallets` 
ADD CONSTRAINT `fk_loc_destination`
  FOREIGN KEY (`location_destination`)
  REFERENCES `inventorydb`.`locations` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `inventorydb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `default_location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE TABLE `inventorydb`.`user_roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `user_role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_role_UNIQUE` (`user_role` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

ALTER TABLE `inventorydb`.`user_roles` 
ADD INDEX `fk_users_idx` (`user_id` ASC) VISIBLE;
;
ALTER TABLE `inventorydb`.`user_roles` 
ADD CONSTRAINT `fk_users`
  FOREIGN KEY (`user_id`)
  REFERENCES `inventorydb`.`users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `inventorydb`.`users` 
ADD INDEX `fk_default_loc_idx` (`default_location_id` ASC) VISIBLE;
;
ALTER TABLE `inventorydb`.`users` 
ADD CONSTRAINT `fk_default_loc`
  FOREIGN KEY (`default_location_id`)
  REFERENCES `inventorydb`.`locations` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
