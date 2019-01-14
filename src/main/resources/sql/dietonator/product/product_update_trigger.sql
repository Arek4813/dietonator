DELIMITER $$
CREATE TRIGGER product_update_trigger BEFORE UPDATE ON product
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

CREATE TRIGGER product_after_update_trigger AFTER UPDATE ON product
FOR EACH ROW 
BEGIN 
	UPDATE meal m INNER JOIN product_meal pm ON m.name = pm.meal
    SET
    m.energy = m.energy - pm.amount/100*OLD.energy + pm.amount/100*NEW.energy,
    m.fat = m.fat - pm.amount/100*OLD.fat + pm.amount/100*NEW.fat,
    m.carbohydrates = m.carbohydrates - pm.amount/100*OLD.carbohydrates + pm.amount/100*NEW.carbohydrates,
    m.of_which_saturates = m.of_which_saturates - pm.amount/100*OLD.of_which_saturates + pm.amount/100*NEW.of_which_saturates,
    m.of_which_sugars = m.of_which_sugars - pm.amount/100*OLD.of_which_sugars + pm.amount/100*NEW.of_which_sugars,
    m.protein = m.protein - pm.amount/100*OLD.protein + pm.amount/100*NEW.protein,
    m.salt = m.salt - pm.amount/100*OLD.salt + pm.amount/100*NEW.salt
    WHERE pm.product = NEW.name;
END $$

DELIMITER ;

DROP TRIGGER product_after_update_trigger;