delimiter $$
drop procedure if exists creatingNormalUserAccount$$
create procedure creatingNormalUserAccount (IN `p_Name` VARCHAR(45), IN `p_Passw` VARCHAR(200))
BEGIN
    DECLARE `_HOST` CHAR(14) DEFAULT '@\'localhost\'';
    SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''),
    `p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
    SET @`sql` := CONCAT('CREATE USER ', `p_Name`, `_HOST`, ' IDENTIFIED BY ', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT PRIVILEGES ON project.category, project.product, project.product_meal, project.dieticians, project.users_and_dieticians TO ', `p_Name`, `_HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
	SET @`sql` := CONCAT('GRANT SELECT, INSERT PRIVILEGES ON project.meal, project.nutritional_plan TO ', `p_Name`, `_HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON project.normal_users, project.diet_and_user, project.plan_meal TO ', `p_Name`, `HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    DEALLOCATE PREPARE `stmt`;
    FLUSH PRIVILEGES;
END$$
delimiter ;


call creatingNormalUserAccount('login', 'aaa');




delimiter $$
drop procedure if exists creatingDieticianAccount$$
create procedure creatingDieticianAccount (IN `p_Name` VARCHAR(45), IN `p_Passw` VARCHAR(200))
BEGIN
    DECLARE `_HOST` CHAR(14) DEFAULT '@\'localhost\'';
    SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''),
    `p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
    SET @`sql` := CONCAT('CREATE USER ', `p_Name`, `_HOST`, ' IDENTIFIED BY ', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT PRIVILEGES ON project.category, project.product, project.product_meal, project.normal_users TO ', `p_Name`, `_HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
	SET @`sql` := CONCAT('GRANT SELECT, INSERT PRIVILEGES ON project.meal, project.nutritional_plan TO ', `p_Name`, `_HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON project.dieticians, project.diet_and_user, project.plan_meal, project.users_and_dieticians TO ', `p_Name`, `HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    DEALLOCATE PREPARE `stmt`;
    FLUSH PRIVILEGES;
END$$
delimiter ;






delimiter $$
drop procedure if exists creatingAdminAccount$$
create procedure creatingAdminAccount (IN `p_Name` VARCHAR(45), IN `p_Passw` VARCHAR(200))
BEGIN
    DECLARE `_HOST` CHAR(14) DEFAULT '@\'localhost\'';
    SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''),
    `p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
    SET @`sql` := CONCAT('CREATE USER ', `p_Name`, `_HOST`, ' IDENTIFIED BY ', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT ALL PRIVILEGES ON project.* TO ', `p_Name`, `_HOST`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    DEALLOCATE PREPARE `stmt`;
    FLUSH PRIVILEGES;
END$$
delimiter ;