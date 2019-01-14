DELIMITER $$
CREATE PROCEDURE create_plan_for_user(IN _login VARCHAR(40), IN _diet VARCHAR (30))
BEGIN 
	INSERT INTO diet_and_user(UserLogin, DietName) VALUES (_login, _diet);
END $$

CREATE PROCEDURE create_plan_for_user_by_dietician(IN _login VARCHAR(40), IN _diet VARCHAR (30), IN _dietician VARCHAR(40))
BEGIN 
	INSERT INTO diet_and_user VALUES (_login, _diet, _dietician);
END $$

CREATE PROCEDURE delete_users_plan(IN _login VARCHAR(40), IN _diet VARCHAR (30))
BEGIN 
	DELETE FROM diet_and_user WHERE UserLogin = _login AND DietName = _diet;
END $$

CREATE PROCEDURE select_plans_of_user(IN _login VARCHAR(40))
BEGIN 
	SELECT * FROM diet_and_user WHERE UserLogin = _login;
END $$

CREATE PROCEDURE select_plans_for_user_by_dietician(IN _login VARCHAR(40), IN _dietician VARCHAR(40))
BEGIN
	SELECT * FROM diet_and_user WHERE UserLogin = _login AND DieticianLogin = _dietician;
END $$

CREATE PROCEDURE seleremovingDieticianct_plans_by_dietician(IN _dietician VARCHAR (40))
BEGIN 
	SELECT * FROM diet_and_user WHERE DieticianLogin = _dietician;
END $$

DELIMITER ;

DROP PROCEDURE select_plans_by_dietician;

DROP PROCEDURE cr