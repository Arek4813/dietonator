DELIMITER $$
CREATE PROCEDURE create_product(IN _name VARCHAR(25), IN _category VARCHAR(15), IN _energy INT, IN _fat INT, IN _saturates INT,
IN _carbohydrates INT, IN _sugars INT, IN _protein INT, IN _salt INT)
BEGIN
	INSERT INTO product VALUES (_name, _category, _energy, _fat, _saturates, _carbohydrates, _sugars, _protein, _salt);
END $$

CREATE PROCEDURE update_product(IN _name VARCHAR(25), IN _category VARCHAR(15), IN _energy INT, IN _fat INT, IN _saturates INT,
IN _carbohydrates INT, IN _sugars INT, IN _protein INT, IN _salt INT)
BEGIN
	UPDATE product SET
    category=_category,
    energy=_energy,
    fat=_fat,
    of_which_saturates=_saturates,
    carbohydrates=_carbohydrates,
    of_which_sugars=_sugars,
    protein=_protein,
    salt=_salt
    WHERE name=_name;
END $$

CREATE PROCEDURE delete_product(IN _name VARCHAR(25))
BEGIN
	DELETE FROM product WHERE name = _name;
END $$

CREATE PROCEDURE select_product(IN _name VARCHAR(25))
BEGIN 
	SELECT * FROM product WHERE name LIKE CONCAT('%', _name, '%');
END $$

CREATE PROCEDURE select_all_products()
BEGIN 
	SELECT * FROM product;
END $$

DELIMITER ;
DROP procedure delete_product;