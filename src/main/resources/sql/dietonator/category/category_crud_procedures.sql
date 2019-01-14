DELIMITER $$
CREATE PROCEDURE create_category(IN _name VARCHAR(15))
BEGIN
	INSERT INTO category VALUES (_name);
END $$

CREATE PROCEDURE delete_category(IN _name VARCHAR(15))
BEGIN
	DELETE FROM category WHERE name=_name;
END $$

CREATE PROCEDURE select_category(IN _name VARCHAR(15))
BEGIN
	SELECT * FROM category WHERE name LIKE CONCAT('%',_name, '%');
END $$

CREATE PROCEDURE select_all_categories()
BEGIN
	SELECT * FROM category;
END $$

DELIMITER ;