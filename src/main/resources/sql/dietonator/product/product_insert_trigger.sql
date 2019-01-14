DELIMITER $$
CREATE TRIGGER product_insert_trigger BEFORE INSERT ON product
FOR EACH ROW
BEGIN
	IF NEW.category NOT IN (SELECT name FROM category)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Category not found';
	ELSEIF NEW.energy < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Energy cannot be less than 0';
	ELSEIF NEW.fat < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Fat cannot be less than 0';
	ELSEIF NEW.of_which_saturates < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be less than 0';
	ELSEIF NEW.of_which_saturates > NEW.fat
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be higher than fat';
	ELSEIF NEW.carbohydrates < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Carbohydrates cannot be less than 0';
	ELSEIF NEW.of_which_sugars > NEW.carbohydrates
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sugars cannot be higher than carbohydrates';
	ELSEIF NEW.protein < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Protein cannot be less than 0';
	ELSEIF NEW.salt < 0
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Salt cannot be less than 0';
	ELSEIF NEW.fat > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Fat cannot be higher than 100';
    ELSEIF NEW.carbohydrates > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Carbohydrates cannot be higher than 100';
    ELSEIF NEW.protein > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Protein cannot be higher than 100';
    ELSEIF NEW.salt > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Salt cannot be higher than 100';
    ELSEIF NEW.of_which_saturates > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Saturates cannot be higher than 100';
    ELSEIF NEW.of_which_sugars > 100
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sugars cannot be higher than 100';
	ELSEIF NEW.fat + NEW.carbohydrates + NEW.protein + NEW.salt > 100
			THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Sum of nutrients cannot be higher than 100';
	END IF;
END $$

DELIMITER ;