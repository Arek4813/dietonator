DELIMITER $$
CREATE PROCEDURE create_nutritional_plan(IN _name VARCHAR(30))
BEGIN 
	INSERT INTO nutritional_plan(name) VALUES (_name);
END $$

CREATE PROCEDURE update_nutritional_plan(IN oldname VARCHAR(30), IN newname VARCHAR(30)) 
BEGIN 
	UPDATE nutritional_plan SET name = newname WHERE name = oldname;
END $$

CREATE PROCEDURE delete_nutritional_plan(IN _name VARCHAR(30))
BEGIN 
	DELETE FROM nutritional_plan WHERE name = _name;
END $$

CREATE PROCEDURE select_nutritional_plan(IN _name VARCHAR(30))
BEGIN 
	SELECT name, energy , fat, of_which_saturates, carbohydrates, of_which_sugars, protein, salt FROM nutritional_plan WHERE name LIKE CONCAT ('%', _name, '%');
END $$

CREATE PROCEDURE create_plan_by_dietician(IN plan VARCHAR(30))


DELIMITER ;