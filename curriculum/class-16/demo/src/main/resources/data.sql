DROP TABLE IF EXISTS `Department`;
CREATE TABLE `Department`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `password`   varchar(255) DEFAULT NULL,
    `username`   varchar(255) DEFAULT NULL UNIQUE,
    `department` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY          `department` (`department`),
    CONSTRAINT `Department_fk_1` FOREIGN KEY (`department`) REFERENCES `Department` (`id`)
);

CREATE TABLE `Role`
(
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name`    varchar(45) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `user_role`
(
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    KEY       `user_id` (`user_id`),
    KEY       `role_id` (`role_id`),
    CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`),
    CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

INSERT INTO `Department` (`name`)
VALUES ('ASAC');
INSERT INTO `Department` (`name`)
VALUES ('Hospitality');

INSERT INTO `User` (`username`, `password`, `department`)
VALUES ('jason', '$2a$10$Z5FccVXkJb3Hy1zofcopmeaNyo3mv3cGk4UBclicYvy0BwhyyEXhq', '1');
INSERT INTO `User` (`username`, `password`, `department`)
VALUES ('oneal', '$2a$10$Z5FccVXkJb3Hy1zofcopmeaNyo3mv3cGk4UBclicYvy0BwhyyEXhq', '2');

INSERT INTO `Role` (`name`) VALUES ('STUDENT');
INSERT INTO `Role` (`name`) VALUES ('ADMIN');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1); -- user jason has role USER
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2); -- user oneal has role USER
