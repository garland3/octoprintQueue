-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ThreeDPrintingQueue
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ThreeDPrintingQueue
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ThreeDPrintingQueue` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ThreeDPrintingQueue` ;

-- -----------------------------------------------------
-- Table `ThreeDPrintingQueue`.`Queue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ThreeDPrintingQueue`.`Queue` (
  `idQueue` INT NOT NULL AUTO_INCREMENT,
  `filename` VARCHAR(100) NULL,
  `activeprint` TINYINT(1) NULL,
  `apicall` VARCHAR(150) NULL,
  PRIMARY KEY (`idQueue`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
