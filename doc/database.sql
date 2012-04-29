-- =============================================================================
-- Diagram Name: database
-- Created on: 2012/4/25 23:46:32
-- Diagram Version: 
-- =============================================================================


SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `role` int(11),
  `password` varchar(50),
  `organization_id` int(11),
  `email` varchar(100),
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100),
  `content` text,
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;

CREATE TABLE `apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `began` date NOT NULL,
  `end` date NOT NULL,
  `time` timestamp NOT NULL,
  `state` int(11),
  `reason` text,
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;

CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `apply_id` int(11) NOT NULL,
  `began` date NOT NULL,
  `end` date NOT NULL,
  `content` text,
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;

CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` text,
  `time` timestamp NOT NULL,
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `major` varchar(50),
  `academy` varchar(50),
  `grade` int(11),
  `student_id` varchar(12),
  `organization_id` int(11) NOT NULL,
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;

CREATE TABLE `organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `content` text,
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;

CREATE TABLE `inform` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content` text,
  `tiem` timestamp NOT NULL,
  PRIMARY KEY(`id`)
)
ENGINE=MYISAM
ROW_FORMAT=default
CHARACTER SET utf8 
COLLATE utf8_bin ;


ALTER TABLE `user` ADD
  CONSTRAINT `Ref_01` FOREIGN KEY (`organization_id`)
    REFERENCES `organization`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `apply` ADD
  CONSTRAINT `Ref_04` FOREIGN KEY (`user_id`)
    REFERENCES `user`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `apply` ADD
  CONSTRAINT `Ref_06` FOREIGN KEY (`goods_id`)
    REFERENCES `goods`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `record` ADD
  CONSTRAINT `Ref_03` FOREIGN KEY (`organization_id`)
    REFERENCES `organization`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `record` ADD
  CONSTRAINT `Ref_05` FOREIGN KEY (`goods_id`)
    REFERENCES `goods`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `member` ADD
  CONSTRAINT `Ref_02` FOREIGN KEY (`organization_id`)
    REFERENCES `organization`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `inform` ADD
  CONSTRAINT `Ref_07` FOREIGN KEY (`user_id`)
    REFERENCES `user`(`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

SET FOREIGN_KEY_CHECKS=1;
