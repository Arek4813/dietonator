DELIMITER $$
CREATE TRIGGER product_meal_update_trigger BEFORE UPDATE ON product_meal
FOR EACH ROW
BEGIN
	UPDATE meal m INNER JOIN product_meal pm ON  m.name=NEW.meal INNER JOIN product p ON p.name=NEW.product
    SET
    m.energy = m.energy - OLD.amount / 100 * p.energy + NEW.amount / 100 * p.energy,
    m.fat = m.fat - OLD.amount / 100 * p.fat  + NEW.amount / 100 * p.fat,
    m.carbohydrates = m.carbohydrates - OLD.amount / 100 * p.carbohydrates  + NEW.amount / 100 * p.carbohydrates,
    m.of_which_saturates = m.of_which_saturates - OLD.amount / 100 * p.of_which_saturates  + NEW.amount / 100 * p.of_which_saturates,
    m.of_which_sugars = m.of_which_sugars - OLD.amount / 100 * p.of_which_sugars  + NEW.amount / 100 * p.of_which_sugars,
    m.protein = m.protein - OLD.amount / 100 * p.protein  + NEW.amount / 100 * p.protein,
    m.salt = m.salt - OLD.amount / 100 * p.salt  + NEW.amount / 100 * p.salt;
    END $$

CREATE TRIGGER product_meal_before_update_trigger BEFORE UPDATE ON product_meal
FOR EACH ROW
BEGIN
	IF NEW.product NOT IN (SELECT name FROM product)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='This product does not exist';
	END IF;
    IF NEW.meal NOT IN (SELECT name FROM meal)
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='This meal does not exist';
	END IF;
	IF NEW.amount <= 0 
		THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT='Amount of product has to be more than 0';
    END IF;
END $$

DELIMITER ;

DROP TRIGGER product_meal_update_trigger;