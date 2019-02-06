-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`notification` (
  `notification_id` BINARY(16) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `message` TEXT NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `validity` TIMESTAMP NOT NULL,
  `reading` TINYINT(1) NULL,
  `repeated` TINYINT(1) NULL,
  `visibility` INT NULL,
  `user` VARCHAR(50) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NULL,
  PRIMARY KEY (`notification_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`local` (
  `local_id` INT(10) NOT NULL AUTO_INCREMENT,
  `local` INT(10) NOT NULL,
  PRIMARY KEY (`local_id`),
  UNIQUE INDEX `local_UNIQUE` (`local` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`channel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`channel` (
  `channel_id` INT(10) NOT NULL AUTO_INCREMENT,
  `channel` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`channel_id`),
  UNIQUE INDEX `channel_UNIQUE` (`channel` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`notification_local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`notification_local` (
  `notification_local_id` INT(10) NOT NULL AUTO_INCREMENT,
  `notification_id` BINARY(16) NOT NULL,
  `local_id` INT(10) NOT NULL,
  INDEX `fk_table1_notification_idx` (`notification_id` ASC),
  INDEX `fk_table1_local1_idx` (`local_id` ASC),
  PRIMARY KEY (`notification_local_id`),
  CONSTRAINT `fk_table1_notification`
    FOREIGN KEY (`notification_id`)
    REFERENCES `mydb`.`notification` (`notification_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_local1`
    FOREIGN KEY (`local_id`)
    REFERENCES `mydb`.`local` (`local_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`notification_channel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`notification_channel` (
  `notification_channel_id` INT(10) NOT NULL AUTO_INCREMENT,
  `channel_id` INT(10) NOT NULL,
  `notification_id` BINARY(16) NOT NULL,
  INDEX `fk_table1_channel1_idx` (`channel_id` ASC),
  INDEX `fk_table1_notification1_idx` (`notification_id` ASC),
  PRIMARY KEY (`notification_channel_id`),
  CONSTRAINT `fk_table1_channel1`
    FOREIGN KEY (`channel_id`)
    REFERENCES `mydb`.`channel` (`channel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_table1_notification1`
    FOREIGN KEY (`notification_id`)
    REFERENCES `mydb`.`notification` (`notification_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
