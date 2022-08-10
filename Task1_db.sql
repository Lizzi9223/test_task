-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema test_task
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema test_task
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_task` DEFAULT CHARACTER SET utf8 ;
USE `test_task` ;

-- -----------------------------------------------------
-- Table `test_task`.`strings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_task`.`strings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `english_line` VARCHAR(20) NOT NULL,
  `russian_line` VARCHAR(20) NOT NULL,
  `int_number` INT NOT NULL,
  `float_number` DOUBLE(10,8) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
