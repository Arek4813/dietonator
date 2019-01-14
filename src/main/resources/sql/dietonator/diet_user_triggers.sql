DELIMITER $$
CREATE TRIGGER diet_user_insert BEFORE INSERT ON diet_and_user
FOR EACH ROW
BEGIN 
	IF NEW.UserLogin NOT IN (SELECT UserLogin FROM normal_users)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This user does not exist';
	ELSEIF NEW.DietName NOT IN (SELECT name FROM nutritional_plan)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This plan does not exist';
	ELSEIF NEW.DieticianLogin NOT IN (SELECT dieticianLogin FROM dieticians) AND NEW.DieticianLogin IS NOT NULL
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This dietician does not exist';
	ELSEIF NEW.DieticianLogin IS NOT NULL AND NEW.UserLogin NOT IN (SELECT UserLogin FROM users_and_dieticians WHERE DieticianLogin = NEW.DieticianLogin)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'This user is not customer of this dietician';
    END IF;
END $$
DELIMITER ;

DROP TRIGGER diet_user_insert;