delimiter $$
drop procedure if exists creatingNormalUserAccount$$
create procedure creatingNormalUserAccount (IN `p_Name` VARCHAR(45), IN `p_Passw` VARCHAR(200))
BEGIN

    SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''),
	`p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
    SET @`sql` := CONCAT('CREATE USER ', `p_Name`, ' IDENTIFIED BY ', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
   
    
    SET @`sql` := CONCAT('GRANT SELECT ON Dietonator.category TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT ON Dietonator.product TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT ON Dietonator.dieticians TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT ON Dietonator.users_and_dieticians TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
	SET @`sql` := CONCAT('GRANT SELECT, INSERT ON Dietonator.meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT, INSERT ON Dietonator.nutritional_plan TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT, INSERT ON Dietonator.product_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
	SET @`sql` := concat('GRANT ALL PRIVILEGES ON Dietonator.diet_and_user TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON Dietonator.normal_users TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON Dietonator.plan_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_all_categories TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_category TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_nutritional_plan TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_plan_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_product_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE delete_plan_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_all_products TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_meals_of_plan TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_nutritional_plan TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_product TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_products_of_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE writeDieticiansForUser TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE writeDietsForUser TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_products_of_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE writeDieticiansForChosenUser TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_nutritional_plan_for_user TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
    
    DEALLOCATE PREPARE `stmt`;
    FLUSH PRIVILEGES;
    
END$$
delimiter ;


delimiter $$
drop procedure if exists creatingDieticianAccount$$
create procedure creatingDieticianAccount (IN `p_Name` VARCHAR(45), IN `p_Passw` VARCHAR(200))
BEGIN

    SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''),
    `p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
    SET @`sql` := CONCAT('CREATE USER ', `p_Name`, ' IDENTIFIED BY ', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
    SET @`sql` := CONCAT('GRANT SELECT ON Dietonator.category TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT ON Dietonator.product TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT ON Dietonator.normal_users TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
    
	SET @`sql` := CONCAT('GRANT SELECT, INSERT ON Dietonator.meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := CONCAT('GRANT SELECT, INSERT ON Dietonator.nutritional_plan TO ', `p_Name`);
    SET @`sql` := CONCAT('GRANT SELECT, INSERT ON Dietonator.product_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
    
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON Dietonator.dieticians TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON Dietonator.diet_and_user TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON Dietonator.plan_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT ALL PRIVILEGES ON Dietonator.users_and_dieticians TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_all_categories TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_category TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_nutritional_plan TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_plan_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_product_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE delete_plan_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_all_products TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_meals_of_plan TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_nutritional_plan TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_product TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_products_of_meal TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE writeUsersForDietician TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE addingUserAndDieticianConnection TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE removingUserAndDieticianConnection TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_plans_of_user TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_plans_for_user_by_dietician TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE select_plans_by_dietician TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE create_plan_for_user_by_dietician TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    SET @`sql` := concat('GRANT EXECUTE ON PROCEDURE delete_users_plan TO ', `p_Name`);
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

    SET `p_Name` := CONCAT('\'', REPLACE(TRIM(`p_Name`), CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\''),
    `p_Passw` := CONCAT('\'', REPLACE(`p_Passw`, CHAR(39), CONCAT(CHAR(92), CHAR(39))), '\'');
    SET @`sql` := CONCAT('CREATE USER ', `p_Name`, ' IDENTIFIED BY ', `p_Passw`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    SET @`sql` := CONCAT('GRANT ALL PRIVILEGES ON Dietonator.* TO ', `p_Name`);
    PREPARE `stmt` FROM @`sql`;
    EXECUTE `stmt`;
    
    DEALLOCATE PREPARE `stmt`;
    FLUSH PRIVILEGES;
    
END$$
delimiter ;
