DELIMITER $$
CREATE PROCEDURE create_meal(IN _name VARCHAR(25))
BEGIN
	INSERT INTO meal(name) VALUES(_name);
END $$

CREATE PROCEDURE update_meal(IN oldname VARCHAR(25), IN newname VARCHAR (25))
BEGIN 
	UPDATE meal SET name=newname WHERE name=oldname;
END $$

CREATE PROCEDURE delete_meal(IN _name VARCHAR(25)) 
BEGIN 
	DELETE FROM meal WHERE _name=name;
END $$

CREATE PROCEDURE select_meal(IN _name VARCHAR(25))
BEGIN 
	SELECT name, energy , fat, of_which_saturates, carbohydrates, of_which_sugars, protein, salt FROM meal WHERE name LIKE CONCAT('%', _name, '%');
END $$

DELIMITER ;